package cn.com.zsyk.crm.manage.service.engine;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.zsyk.crm.manage.entity.SysMonitorTask;
import cn.com.zsyk.crm.manage.entity.SysMonitorTask;
import cn.com.zsyk.crm.manage.mapper.SysMonitorTaskMapper;

@Service
@Transactional
public class MonitorTaskService {

	
	@Autowired
	private SysMonitorTaskMapper sysMonitorTaskMapper;
	
    /**
     * 保存监控任务，及关联的规则因子
     * @param monitorTask
     * @param factorList
     * @return
     */
	public String saveMonitorTask(SysMonitorTask monitorTask) throws Exception{
        try {
              sysMonitorTaskMapper.insert(monitorTask);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
		return null;
    }
    

    /**
     * 查询监控任务
     * @param monitorTask
     * @param pageNum
     * @param pageSize
     * @return
     */
	 public List<SysMonitorTask> findAll(SysMonitorTask monitorTaskExample) throws Exception{
	        try {
	        	List<SysMonitorTask> list = sysMonitorTaskMapper.selectByExample(monitorTaskExample);
	            return list;
	        } catch (Exception e) {
	            throw new Exception("监控任务查询业务层错误！");
	        }
	    }
 

    /**
     * 修改监控任务
     * @param monitorTask
     * @param factorList
     */
	 public void updateMonitorTask(SysMonitorTask monitorTask) throws Exception {
	        try {
	            monitorTask.setOperateDate(new Date());
	            sysMonitorTaskMapper.updateByPrimaryKey(monitorTask);

	        } catch (Exception e) {
	            e.printStackTrace();
	            throw e;
	        }
	    }
}
