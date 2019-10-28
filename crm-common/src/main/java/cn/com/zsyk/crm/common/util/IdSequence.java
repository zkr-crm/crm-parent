package cn.com.zsyk.crm.common.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.logging.Log;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import cn.com.zsyk.crm.common.exception.IdGeneraterException;

/**
 * 基于数据库实现的自增ID生成器。<br/>
 * 为了解决每次生成都访问数据库的性能问题，批量获取一段范围的Id，当使用一半时去数据库预取。
 * @author
 */
@SuppressWarnings("unused")
class IdSequence {
	private static final Log log = LogUtil.getLogger(IdSequence.class);
	private String bizTag; 				//业务标识
	private boolean asynLoad = true; 	//是否异步获取下一批ID段
	private JdbcTemplate jdbcTemplate;

	/** 当期使用的ID */
	private AtomicLong currentId;
	/** 切换ID段数组下标的标志 */
	private volatile boolean swFlag;
	/** 存放ID段范围的数组，第一位为初始的ID段，第二位为预拉取的下一个ID段，交替使用  */
	private volatile IdSegment[] segment = new IdSegment[2]; 

	private FutureTask<Boolean> loadTask;  //拉取下一批ID段的任务
	private ReentrantLock lock = new ReentrantLock();
	private ExecutorService taskExecutor = Executors.newSingleThreadExecutor(); //异步线程池
	
	public IdSequence(String bizTag) {
		this.bizTag = bizTag;
		this.init();
	}
	
	/**
	 * 初始化操作
	 */
	void init() {
		if (this.bizTag == null) {
			throw new IdGeneraterException("业务ID生成器，业务标识不能为空...");
		}
		
		this.jdbcTemplate = (JdbcTemplate) ApplicationContextUtil.getBean("jdbcTemplate_manage");
		if (this.jdbcTemplate == null) {
			throw new IdGeneraterException("业务ID生成器，数据库连接不能为空...");
		}

		segment[0] = loadNextSegment(bizTag);

		//初始化时不需要切换
		swFlag = false;	
		currentId = new AtomicLong(segment[index()].getMinId());
		log.info("初始化"+bizTag+"的自增ID成功...");
	}
	
	/**
	 * 获取业务相关的自增id
	 * @return
	 */
	public long nextId() {
		if (asynLoad) {
			return asynGetId();
		} else {
			return synGetId();
		}
	}

	private Long asynGetId() {

		if (segment[index()].getMidId().equals(currentId.longValue())
				|| segment[index()].getMaxId().equals(currentId.longValue())) {
			try {
				lock.lock();
				if (segment[index()].getMidId().equals(currentId.longValue())) {
					// 前一段使用了50%
					loadTask = new FutureTask<>(() -> {
						segment[nextIndex()] = loadNextSegment(bizTag);
						return true;
					});
					taskExecutor.submit(loadTask);
				}
				
				if (segment[index()].getMaxId().equals(currentId.longValue())) {
					//当前Id到了ID段的最大值，阻塞获取新的ID段
					boolean loadingResult;
					try {
						loadingResult = loadTask.get();
						if (loadingResult) {
							//切换到下一个ID段
							swFlag = !swFlag;	
							currentId = new AtomicLong(segment[index()].getMinId());
						}
					} catch (InterruptedException|ExecutionException e) {
						log.error(e.getMessage(),e);
						// 强制同步切换
						segment[nextIndex()] = loadNextSegment(bizTag);
						// 切换到下一个ID段
						swFlag = !swFlag;	
						currentId = new AtomicLong(segment[index()].getMinId()); 
					} 
				}
			} finally {
				lock.unlock();
			}
		}

		return currentId.incrementAndGet();

	}
	
	private Long synGetId() {
		if (segment[index()].getMidId().equals(currentId.longValue())
				|| segment[index()].getMaxId().equals(currentId.longValue())) {
			try {
				lock.lock();
				if (segment[index()].getMidId().equals(currentId.longValue())) {
					// 使用50%进行加载
					segment[nextIndex()] = loadNextSegment(bizTag);
				}

				if (segment[index()].getMaxId().equals(currentId.longValue())) {
					swFlag = !swFlag;	//切换
					currentId = new AtomicLong(segment[index()].getMinId()); // 进行切换
				}
			} finally {
				lock.unlock();
			}
		}

		return currentId.incrementAndGet();
	}
	
	/**
	 * 获取下一批次的ID段
	 * @param bizTag
	 * @return
	 */
	private IdSegment loadNextSegment(String bizTag) {
		try {
			String querySql = "select max_id, add_step, last_time, time_stamp from sys_id_sequence where biz_tag=?";
			IdSegment currentSegment = jdbcTemplate.queryForObject(querySql, new Object[] {bizTag}, (rs, rowNum) -> {
				Date lastTime = new Date();
				if (rs.getTimestamp("last_time") != null) {
					lastTime = rs.getDate("last_time");
				}

				Date currentTime = new Date();
				if (rs.getTimestamp("time_stamp") != null) {
					currentTime = (java.util.Date) rs.getTimestamp("time_stamp");
				}
				IdSegment idSegment = new IdSegment();
				idSegment.setMaxId(rs.getLong("max_id"));
				idSegment.setStep(rs.getLong("add_step"));
				idSegment.setLastTime(lastTime);
				idSegment.setCurrentTime(currentTime);
				return idSegment;
			});
			
			Long newMaxId = currentSegment.getMaxId() + currentSegment.getStep();
			String updateSql = "update sys_id_sequence set max_id=?,last_time=? where biz_tag=? and max_id=?";
			int row = jdbcTemplate.update(updateSql, new Object[]{newMaxId, currentSegment.getCurrentTime(), bizTag, currentSegment.getMaxId() });
			
			if (row == 1) {
				IdSegment newSegment = new IdSegment();
				newSegment.setStep(currentSegment.getStep());
				newSegment.setMaxId(newMaxId);
				return newSegment;
			} else {
				// 递归，直至更新成功
				return loadNextSegment(bizTag); 
			}
		} catch (Exception e) {
			throw new IdGeneraterException("获取下一批次ID段失败："+e.getMessage(), e);
		}
	}

	/** @return 当前ID段的下标 */
	private int index() {
		return swFlag==true ? 1 : 0;
	}
	/** @return 下一个ID段的下标	 */
	private int nextIndex() {
		return swFlag==true ? 0 : 1;
	}
	
	public String getBizTag() {
		return bizTag;
	}
	public void setBizTag(String bizTag) {
		this.bizTag = bizTag;
	}
	public boolean isAsynLoad() {
		return asynLoad;
	}
	public void setAsynLoad(boolean asynLoad) {
		this.asynLoad = asynLoad;
	}
	public void setTaskExecutor(ExecutorService taskExecutor) {
		this.taskExecutor = taskExecutor;
	}


	/**
	 * ID段，存放自增ID范围的实体
	 * @author
	 */
	private class IdSegment {
	    private Long minId;	//最小ID
	    private Long midId; //中间ID
	    private Long maxId; //最大ID
	    private Long step;  //每次获取的步长
	    private Date lastTime;		//上次操作时间
	    private Date currentTime;	//当前操作时间

	    public Long getMinId() {
	        if (this.minId == null) {
	            if (this.maxId!=null && this.step!=null) {
	                this.minId = this.maxId - this.step;
	            } else {
	                throw new IdGeneraterException("maxid or step is null");
	            }
	        }
	        return minId;
	    }
	    public Long getMidId() {
	        if (this.midId == null) {
	            this.midId = this.maxId - (long) Math.round(step / 2);
	        }
	        return midId;
	    }
	    public Long getMaxId() {
	        return maxId;
	    }
	    public void setMaxId(Long maxId) {
	        this.maxId = maxId;
	    }
	    public Long getStep() {
	        return step;
	    }
	    public void setStep(Long step) {
	        this.step = step;
	    }
		public Date getLastTime() {
	        return lastTime;
	    }
	    public void setLastTime(Date lastUpdateTime) {
	        this.lastTime = lastUpdateTime;
	    }
	    public Date getCurrentTime() {
	        return currentTime;
	    }
	    public void setCurrentTime(Date currentUpdateTime) {
	        this.currentTime = currentUpdateTime;
	    }
	}
}

