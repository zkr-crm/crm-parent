package cn.com.zsyk.crm.manage.service.mngcenter.job;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.zsyk.crm.common.dao.AbstractDao;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.DateUtil;
import cn.com.zsyk.crm.manage.entity.SysJobMsgRela;
import cn.com.zsyk.crm.manage.mapper.SysJobMsgRelaMapper;

@Service
@Transactional
public class JobMsgRelaService {
	@Autowired
	private AbstractDao daoUtil;
	@Autowired
	private SysJobMsgRelaMapper jobMapper;
	
	/**
	 * 获得一条调度任务信息发送关联信息的方法
	 * 
	 * @param SysJobMsgRela
	 *           	调度任务信息发送关联对象
	 * @return List<SysJobMsgRela>
	 * 				调度任务信息发送关联表集和
	 */
	public List<SysJobMsgRela> getMsgByEntity(SysJobMsgRela jobMsgRela){
		return daoUtil.selectList("cn.com.zsyk.crm.manage.mapper.SysJobMsgRelaMapper.selectByEntity", jobMsgRela);
	}

	
	//查询一条（记录正常）
	public SysJobMsgRela getOneRec(SysJobMsgRela record) {
		record.setRecStat("0");
		List<SysJobMsgRela> recList = jobMapper.selectByEntity(record);
		if( recList == null || recList.size() <= 0 ) {
			return null;
		}
		return recList.get(0);
	}
	//查询一条（记录正常）byPK
	public SysJobMsgRela getOneRecByPk(SysJobMsgRela record) {
		record.setRecStat("0");
		SysJobMsgRela rec = jobMapper.selectByPrimaryKey(record.getSchedName(), record.getJobName(), record.getJobGroup(), record.getMsgCode());
		return rec;
	}

	//查询一条（记录存在性）
	public SysJobMsgRela getOne(SysJobMsgRela record) {
		List<SysJobMsgRela> recList = jobMapper.selectByEntityByLike(record);
		if( recList == null || recList.size() <= 0 ) {
			return null;
		}
		return recList.get(0);
	}
	
	//查询多条（记录正常）
	public List<SysJobMsgRela> getMultiRec(SysJobMsgRela record) {
		record.setRecStat("0");
		List<SysJobMsgRela> recList = jobMapper.selectByEntityByLike(record);
		if( recList == null || recList.size() <= 0 ) {
			return null;
		}
		return recList;
	}
	
	//查询多条（记录存在性）
	public List<SysJobMsgRela> getMulti(SysJobMsgRela record) {
		List<SysJobMsgRela> recList = jobMapper.selectByEntityByLike(record);
		if( recList == null || recList.size() <= 0 ) {
			return null;
		}
		return recList;
	}

	//查询列表（全列表）
	public List<SysJobMsgRela> getAllRows() {
		List<SysJobMsgRela> recList = jobMapper.selectAll();
		return recList;
	}
	
	//查询列表（全列表正常）
	public List<SysJobMsgRela> getAllRecRows() {
		List<SysJobMsgRela> recList = jobMapper.selectAllRec();
		return recList;
	}
	
	//新增
	public SysJobMsgRela insertOne(SysJobMsgRela record) {
		//单条记录正常性校验
		SysJobMsgRela checkBean = this.getOneRec(record);		
		//校验已存在报错
		if( checkBean != null ) {
			throw new ServiceException("欲新增数据已经存在[调度名称："+record.getSchedName()+"，任务名称："+record.getJobName()+"，任务组："+record.getJobGroup()+"，信息编码："+record.getMsgCode()+"]");
		}
		//存入记录
		record.setRecStat("0");
		jobMapper.insert(record);
		return record;
	}
	
	//修改
	public SysJobMsgRela updateOne(SysJobMsgRela record) {
		//单条记录正常性校验
		SysJobMsgRela checkBean = this.getOneRecByPk(record);		
		//校验不存在报错
		if( checkBean == null ) {
			throw new ServiceException("欲修改数据不存在或已删除!");
		}
		
		//更新记录信息，使用已查询到进行赋值后更新
		checkBean.setRemindCode(record.getRemindCode());
		checkBean.setRecStat(record.getRecStat());
		checkBean.setUpdateDate(DateUtil.formatDate(new Date(), "yyyyMMdd"));
		checkBean.setUpdateTime(DateUtil.formatDate(new Date(), "hhmmss"));
		checkBean.setTimeStamp(new Date());
		jobMapper.updateByPrimaryKey(checkBean);
		
		return checkBean;
	}
	
	//删除
	public void deleteOne(SysJobMsgRela record) {
		//单条记录正常性校验
		SysJobMsgRela checkBean = this.getOneRec(record);		
		
		//校验不存在报错
		if( checkBean == null ) {
			throw new ServiceException("欲删除数据不存在或已删除!");
		}
		
		//删除记录信息，根据已查询到的记录进行主键删除
		jobMapper.deleteByPrimaryKey(checkBean.getSchedName(), checkBean.getJobName(), checkBean.getJobGroup(), checkBean.getMsgCode());
		
	}
	
	//逻辑删除
	public void invalidOne(SysJobMsgRela record) {
		//单条记录正常性校验
		SysJobMsgRela checkBean = this.getOneRec(record);		
		
		//校验不存在时报错
		if( checkBean == null ) {
			throw new ServiceException("欲删除数据不存在或已删除!");
		}
		
		//更新记录-记录状态为删除(1)，根据已查询到的记录进行删除
		checkBean.setRecStat("1");
		jobMapper.updateByPrimaryKey(checkBean);
		
	}
	
	
	

}
