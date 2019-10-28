package cn.com.zsyk.crm.ocrm.service.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.zsyk.crm.common.dao.CoreDaoImpl;
import cn.com.zsyk.crm.common.dto.PageBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.DateUtil;
import cn.com.zsyk.crm.common.util.IdGenerator;
import cn.com.zsyk.crm.generator.EnumType;
import cn.com.zsyk.crm.ocrm.entity.OcGroupOper;
import cn.com.zsyk.crm.ocrm.entity.OcGroupOperCust;
import cn.com.zsyk.crm.ocrm.entity.OcTask;
import cn.com.zsyk.crm.ocrm.mapper.OcGroupOperCustMapper;
import cn.com.zsyk.crm.ocrm.mapper.OcGroupOperMapper;
import cn.com.zsyk.crm.ocrm.mapper.OcTaskMapper;
import cn.com.zsyk.crm.ocrm.service.user.UserService;

@Service
@Transactional
public class TaskService {

	@Autowired
	private OcTaskMapper mapper;
	@Autowired
	private UserService userService;
	/**
	 * 群组运营任务成员表
	 */
	@Autowired
	private OcGroupOperCustMapper groupOperCustMapper;
	
	/**
	 * 群组运营任务表
	 */
	@Autowired
	private OcGroupOperMapper groupOperMapper;
	/* 分页查询对象 */
	@Autowired
	private CoreDaoImpl coreDaoImpl;
	// 查询一条记录pk
	public OcTask getOneByPk(String id) {

		OcTask task = mapper.selectByPrimaryKey(id);
		if (null == task.getId() || "".equals(task.getId())) {
			return null;
		}
		return task;
	}

	// 查询一条记录entity
	public OcTask getOneByEntity(OcTask record) {
		List<OcTask> resList = mapper.selectByEntity(record);
		if (resList != null && resList.size() > 0) {
			return resList.get(0);
		}

		return null;
	}

	// 查询一条记录entity rec-0
	public OcTask getOneRecByEntity(OcTask record) {
		if (StringUtils.isEmpty(record.getId())) {
			throw new ServiceException("任务Id不能为空");
		}
		record.setRecStat("0");
		OcTask ocTask = mapper.selectByPrimaryKey(record.getId());
		return ocTask;
	}

	// 查询复数记录all
	public List<OcTask> getAll() {
		List<OcTask> retList = mapper.selectAll();
		if (retList != null && retList.size() > 0) {
			return retList;
		}

		return null;
	}

	// 查询复数记录entity
	public List<OcTask> getAllByEntity(OcTask record) {
		List<OcTask> retList = mapper.selectByEntity(record);
		if (retList != null && retList.size() > 0) {
			return retList;
		}

		return null;
	}

	// 查询复数记录entity rec-0
	public PageBean getAllRecByEntity(OcTask record) {
		record.setRecStat("0");
		//List<OcTask> retList = mapper.selectByEntity(record);
		PageBean pageRetlst = coreDaoImpl.selectPageByMapper(mapper,"selectByEntity",record);
		List<OcTask> retList=pageRetlst.getList();
		if (retList != null && retList.size() > 0) {
			Map<String, String> userIdMap = userService.getAgentListMapByUserId();
			if (retList != null) {
				for (OcTask obj : retList) {
					// createUser
					obj.setCreateUserNam(userIdMap.get(obj.getCreateUser()));
				}
			}
			//return pageRetlst;
		}

		return pageRetlst;
	}

	// 新增
	public String insertOne(OcTask record) {

		List<OcTask> checkList = this.getAllByEntity(record);
		if (checkList != null && checkList.size() > 0) {
			throw new ServiceException("欲新增数据已存在，无法新增！[任务名称：" + checkList.get(0).getTaskName() + "，任务类型：" + checkList.get(0).getTaskType() + "，任务状态：" + checkList.get(0).getTaskStat() + "]");
		}
		if(record.getTaskBgnDate()==null ||"".equals(record.getTaskBgnDate())){
			record.setTaskBgnDate(DateUtil.getNow("yyyy-MM-dd"));
		}
		record.setRecStat("0");
		String taskID = IdGenerator.getDistributedID() + "";
		record.setId(taskID);
		int count = mapper.insert(record);
		if (count < 0) {
			throw new ServiceException("新增操作失败，请稍后再试！");
		}
		return taskID;
	}

	// 修改（bean需要传入pk）
	public int updateOne(OcTask record) {

		OcTask checkBean = this.getOneByPk(record.getId());
		if (checkBean.getId() == null || "".equals(checkBean.getId()) || checkBean.getRecStat().equals("1")) {
			throw new ServiceException("欲修改数据不存在或已被删除，无法完成修改操作！");
		}

		if (record.getContactId() != null || "".equals(record.getContactId())) {
			checkBean.setContactId(record.getContactId());
		}
		if (record.getContactName() != null || "".equals(record.getContactName())) {
			checkBean.setContactName(record.getContactName());
		}
		if (record.getCustId() != null || "".equals(record.getCustId())) {
			checkBean.setCustId(record.getCustId());
		}
		if (record.getCustName() != null || "".equals(record.getCustName())) {
			checkBean.setCustName(record.getCustName());
		}
		if (record.getOppertId() != null || "".equals(record.getOppertId())) {
			checkBean.setOppertId(record.getOppertId());
		}
		if (record.getOppertName() != null || "".equals(record.getOppertName())) {
			checkBean.setOppertName(record.getOppertName());
		}
		if (record.getResponsId() != null || "".equals(record.getResponsId())) {
			checkBean.setResponsId(record.getResponsId());
		}
		if (record.getResponsName() != null || "".equals(record.getResponsName())) {
			checkBean.setResponsName(record.getResponsName());
		}
		if (record.getTaskBgnDate() != null || "".equals(record.getTaskBgnDate())) {
			checkBean.setTaskBgnDate(record.getTaskBgnDate());
		}
		if (record.getTaskEndDate() != null || "".equals(record.getTaskEndDate())) {
			checkBean.setTaskEndDate(record.getTaskEndDate());
		}
		if (record.getTaskDesc() != null || "".equals(record.getTaskDesc())) {
			checkBean.setTaskDesc(record.getTaskDesc());
		}
		if (record.getTaskName() != null || "".equals(record.getTaskName())) {
			checkBean.setTaskName(record.getTaskName());
		}
		if (record.getTaskStat() != null || "".equals(record.getTaskStat())) {

			//当任务状态置为“已完成”时，执行下面的处理,若本来就是已完成的状态，则不执行
			/*if(!EnumType.TaskStat.done.value.equals(checkBean.getTaskStat())  && EnumType.TaskStat.done.value.equals(record.getTaskStat())) {
				// 判断该任务是否为群组任务，若是则判断该任务所属的群组任务下所有子任务是否全部完成，若全部完成则置群组任务状态为已完成
				OcGroupOperCust groupOperCust = new OcGroupOperCust();
				groupOperCust.setTaskId(record.getId());
				List<OcGroupOperCust> list = groupOperCustMapper.selectByEntity(groupOperCust);
				if(list!=null && list.size()>0) {
					groupOperCust =  new OcGroupOperCust();
					groupOperCust.setGroupOperId( list.get(0).getGroupOperId());

					list = groupOperCustMapper.selectByEntity(groupOperCust);

					boolean flg = true; 
					
					for(OcGroupOperCust item : list ) {
						if(!checkBean.getId().equals(item.getTaskId())) {
							OcTask task = this.getOneByPk(item.getTaskId());
							if(!EnumType.TaskStat.done.value.equals(task.getTaskStat())) {
								flg = false;
								break;
							}
						}
					}
					if(flg) {
						//获取群组任务信息
						OcGroupOper groupOper = groupOperMapper.selectByPrimaryKey(list.get(0).getGroupOperId());
						
						groupOper.setGroupTaskStatus(EnumType.TaskStat.done.value);
						groupOperMapper.updateByPrimaryKey(groupOper);
					}
				}
			}*/
			
			checkBean.setTaskStat(record.getTaskStat());
		}
		if (record.getTaskType() != null || "".equals(record.getTaskType())) {
			checkBean.setTaskType(record.getTaskType());
		}

		checkBean.setRecStat("0");
		int count = mapper.updateByPrimaryKey(checkBean);
		if (count < 0) {
			throw new ServiceException("修改操作失败，请稍后再试！");
		}
		

		
		
		return count;
	}

	// 删除delete
	public int deleteByPk(String id) {

		OcTask checkBean = this.getOneByPk(id);
		if (checkBean.getId() == null || "".equals(checkBean.getId()) || "1".equals(checkBean.getRecStat())) {
			throw new ServiceException("欲删除数据不存在或已被删除，无法完成删除操作！");
		}

		int count = mapper.deleteByPrimaryKey(id);
		if (count < 0) {
			throw new ServiceException("删除操作失败，请稍后再试！");
		}
		return count;
	}

	// 删除update rec-1
	public int modDelByEntity(String id) {

		OcTask checkBean = this.getOneByPk(id);
		if (checkBean.getId() == null || "".equals(checkBean.getId()) || checkBean.getRecStat().equals("1")) {
			throw new ServiceException("欲删除数据不存在或已被删除，无法完成删除操作！");
		}

		checkBean.setRecStat("1");
		int count = mapper.updateByPrimaryKey(checkBean);
		if (count < 0) {
			throw new ServiceException("删除操作失败，请稍后再试！");
		}
		return count;
	}

}
