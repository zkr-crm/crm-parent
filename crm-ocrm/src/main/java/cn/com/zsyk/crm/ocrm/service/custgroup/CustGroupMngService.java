package cn.com.zsyk.crm.ocrm.service.custgroup;

import cn.com.zsyk.crm.common.constant.ServiceType;
import cn.com.zsyk.crm.common.dao.CoreDaoImpl;
import cn.com.zsyk.crm.common.dto.ContextContainer;
import cn.com.zsyk.crm.common.dto.PageBean;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.DateUtil;
import cn.com.zsyk.crm.common.util.IdGenerator;
import cn.com.zsyk.crm.common.util.RestUtil;
import cn.com.zsyk.crm.generator.EnumType;
import cn.com.zsyk.crm.ocrm.bo.custgroup.CustGroupInfo;
import cn.com.zsyk.crm.ocrm.bo.custgroup.LineChartData;
import cn.com.zsyk.crm.ocrm.bo.custgroup.SendMsgInfo;
import cn.com.zsyk.crm.ocrm.bo.manage.MsgInfo;
import cn.com.zsyk.crm.ocrm.entity.*;
import cn.com.zsyk.crm.ocrm.mapper.*;
import cn.com.zsyk.crm.ocrm.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 客户群组管理服务
 *
 */
@Service
@Transactional
public class CustGroupMngService {

	/* 客户群组表 */
	@Autowired
	private OcCustGrpMapper custGrpMapper;
	/* 客户群组成员表 */
	@Autowired
	private OcCustGrpMemberMapper custGrpMemberMapper;
	/* 动态群组策略关联表 */
	@Autowired
	private OcDynamGrpRuleRelaMapper dynamGrpRuleRelaMapper;
	/* 客户群快照表 */
	@Autowired
	private OcDynamSnapshotMapper dynamSnapshotMapper;
	/* 群组运营任务表 */
	@Autowired
	private OcGroupOperCustMapper groupOperCustMapper;
	/* 群组运营任务成员表 */
	@Autowired
	private OcGroupOperMapper groupOperMapper;
	/* 动态客群客户轨迹表 */
	@Autowired
	private OcGrpCustTraceMapper grpCustTraceMapper;
	/* 动态客群组轨迹表 */
	@Autowired
	private OcGrpTraceMapper grpTraceMapper;

	/* 任务表service */
	@Autowired
	private TaskService taskService;

	/* 群组成员维护service */
	@Autowired
	private CustGrpMemberService custGrpMemberService;
	/* 群组任务管理service */
	@Autowired
	private GroupOperService groupOperService;

	/* 群组快照管理service */
	@Autowired
	private DynamSnapshotService dynamSnapshotService;
	/* 分页查询对象 */
	@Autowired
	private CoreDaoImpl coreDaoImpl;
	/* Rest工具类 */
	@Autowired
	private RestUtil restUtil;

	/**
	 * 按条件查询客户群组列表
	 * 
	 * @param custGrp
	 *            客户群组对象
	 * @return 客户群组信息列表
	 */
	public PageBean getCustGrpByEntity(OcCustGrp custGrp) {

		PageBean bean = coreDaoImpl.selectPageByMapper(custGrpMapper, "getCustGrpByEntity", custGrp);

		return bean;
	}

	/**
	 * 按条件查询客户群组详细信息
	 * 
	 * @param groupId
	 *            客户群组ID
	 * @return 群组内客户信息列表
	 */
	public CustGroupInfo getCustGroupInfoByGroupId(String groupId) {

		// 客户组ID
		CustGroupInfo custGroupInfo = new CustGroupInfo();
		custGroupInfo.setGroupId(groupId);

		// 客户组信息
		OcCustGrp custGrp = custGrpMapper.selectByPrimaryKey(groupId);
		custGroupInfo.setCustGrp(custGrp);

		// 客户组成员信息
		List<OcCustGrpMember> grpMemberList = custGrpMemberService.getCustListByGroupIdNoPaging(groupId);
		custGroupInfo.setCustGrpMemberList(grpMemberList);

		return custGroupInfo;
	}

	/**
	 * 创建客户群组
	 * 
	 * @param OcCustGrp
	 *            客户群组对象
	 * @return 群组内客户信息列表
	 */
	public String addCustGroup(OcCustGrp custGrp) {

		if (custGrp.getGroupName() == null || "".equals(custGrp.getGroupName())) {
			throw new ServiceException("群组名称不能为空！");
		}
//		if (custGrp.getGroupType() == null || "".equals(custGrp.getGroupType())) {
//			throw new ServiceException("群组类型不能为空！");
//		}
		if (custGrp.getEstablishType() == null || "".equals(custGrp.getEstablishType())) {
			throw new ServiceException("群组创建方式不能为空！");
		}
//		if (custGrp.getMemberType() == null || "".equals(custGrp.getMemberType())) {
//			throw new ServiceException("群组成员类型不能为空！");
//		}
		if (custGrp.getGroupDesc() == null || "".equals(custGrp.getGroupDesc())) {
			throw new ServiceException("群组说明不能为空！");
		}

		// 生成群组id
		Long id = IdGenerator.getSeqID("Grp");
		if (id > 99999999) {
			throw new ServiceException("群组数量已达上限，不可新增，请联系管理员！");
		}

		// 群组id号自动左侧补零
		String idStr = id.toString();
		StringBuffer strBuffer = null;
		while (idStr.length() < 8) {
			strBuffer = new StringBuffer();
			strBuffer.append("0").append(idStr);// 左补0
			idStr = strBuffer.toString();
		}
		String groupId = "G_" + idStr;
		custGrp.setGroupId(groupId);
		// 创建用户
		custGrp.setEstablishUser(ContextContainer.getContext().getUserId());
		// 创建时间
		custGrp.setEstablishTime(DateUtil.getTimestamp(new Date()));
		int count = custGrpMapper.insert(custGrp);
		if (count == 1) {
			return groupId;
		} else {
			return "";
		}
	}

	/**
	 * 修改客户群组
	 * 
	 * @param OcCustGrp
	 *            客户群组对象
	 * @return 群组内客户信息列表
	 */
	public int editCustGroup(OcCustGrp custGrp) {

		if (custGrp.getGroupId() == null || "".equals(custGrp.getGroupId())) {
			throw new ServiceException("群组ID不能为空！");
		}
		OcCustGrp newocCustGrp = new OcCustGrp();
		newocCustGrp.setGroupName(custGrp.getGroupName());
		List<OcCustGrp> grpList = custGrpMapper.getCustGrpByEntity(newocCustGrp);
		if(grpList.size()>0){
			throw new ServiceException("群组[" + custGrp.getGroupName() + "]名称已存在");
		}
		OcCustGrp bean = custGrpMapper.selectByPrimaryKey(custGrp.getGroupId());

		bean.setGroupName(custGrp.getGroupName());
		bean.setGroupDesc(custGrp.getGroupDesc());

		// BeanUtil.copy(custGrp, bean);

		int count = custGrpMapper.updateByPrimaryKey(bean);
		return count;
	}

	/**
	 * 删除一条客户群组
	 * 
	 * @param String
	 *            groupId 客户群组id
	 * @return 群组内客户信息列表
	 */
	public int delOneCustGroup(String groupId) {

		if (groupId == null || "".equals(groupId)) {
			throw new ServiceException("群组ID不能为空！");
		}

		int count = 0;

		// 查询要删除的群组是否存在
		OcCustGrp bean = custGrpMapper.selectByPrimaryKey(groupId);

		if (bean != null) {

			// 判断该群组是否已经产生了运营任务，若已存在则不允许删除群组

			OcGroupOper groupOper = new OcGroupOper();
			groupOper.setGroupId(groupId);
			List<OcGroupOper> groupOperList = groupOperMapper.selectByEntity(groupOper);
			if (groupOperList != null && groupOperList.size() > 0) {
				throw new ServiceException("群组[" + groupId + "]已产生运营任务，不允许删除！");
			}

			// 删除群组表记录
			count = custGrpMapper.deleteByPrimaryKey(groupId);

			// 根据群组ID删除所有组内成员
			custGrpMemberMapper.deleteByGroupId(groupId);

		} else {
			throw new ServiceException("要删除的群组不存在！");
		}

		return count;
	}

	/**
	 * 删除多条客户群组
	 * 
	 * @param OcCustGrp
	 *            客户群组对象
	 * @return 群组内客户信息列表
	 */
	public int delMultiCustGroup(List<OcCustGrp> custGrpList) {

		int count = 0;

		for (OcCustGrp item : custGrpList) {

			// 删除群组
			count += delOneCustGroup(item.getGroupId());
		}
		return count;
	}

	/**
	 * 生成快照群组和任务列表
	 * 
	 * @param CustGroupInfo
	 *            群组快照信息对象
	 */
	public void generateSnap(CustGroupInfo custGroupInfo) {

		// 判断客户群组信息是否存在，不存在则报错
		if (custGroupInfo.getCustGrp() != null) {

			// 新增群组
			String groupId = addCustGroup(custGroupInfo.getCustGrp());

			// 添加群组成员
			if (groupId != "") {

				// 成员数不能为空
				if (custGroupInfo.getCustGrpMemberList() != null) {

					// 添加群组成员
					custGrpMemberService.addGroupMember(custGroupInfo.getCustGrpMemberList());

					// 判断任务对象数据是否为空，若不为空则生成群组运营任务
					if (custGroupInfo.getGroupOper() != null) {
						// 生成运营任务
						String groupOperId = groupOperService.addOper(custGroupInfo.getGroupOper());

						if (custGroupInfo.getEmployeeId() != null && !"".equals(custGroupInfo.getEmployeeId())) {

							String[] employeeIds = custGroupInfo.getEmployeeId().split(",");

							List<OcGroupOperCust> groupOperCustList = new ArrayList<OcGroupOperCust>();

							int index = 0;
							for (OcCustGrpMember item : custGroupInfo.getCustGrpMemberList()) {

								index = index % employeeIds.length;

								OcGroupOperCust groupOperCust = new OcGroupOperCust();
								groupOperCust.setCustNo(item.getCustNo());
								groupOperCust.setCustAgent(employeeIds[index]);

								index++;
							}

							// 生成运营任务成员信息
							groupOperService.addOperCust(groupOperId, groupOperCustList);
						}
					}

				}
			} else {
				throw new ServiceException("新建群组失败！");
			}
		} else {
			throw new ServiceException("新建群组信息不能为空！");
		}

	}

	/**
	 * 群发短信
	 * 
	 * @param SendMsgInfo
	 *            发送群组信息对象
	 */
	public void batchSendMsg(SendMsgInfo sendMsgInfo) {

		if (sendMsgInfo == null) {
			throw new ServiceException("短信内容不能为空！");
		}
		if (sendMsgInfo.getCustNo() == null || sendMsgInfo.getCustNo() == "") {
			throw new ServiceException("客户信息不能为空！");
		}
		// 获取抄送用户的电话
		String[] employeeIds = sendMsgInfo.getEmployeeId().split(",");
		for (String employeeId : employeeIds) {

			Map map = new HashMap();
			map.put("employeeId", employeeId);
			// MANAGE发起GET请求
			Result getForObject = restUtil.getForObject(ServiceType.MANAGE,
					"/crm/manage/usermng/getUserByEmployeeID?employeeId={employeeId}", Result.class, map);

			// 获取查询到的用户信息
			Map<String, String> userInfo = (Map<String, String>) getForObject.getData();

			MsgInfo msgInfo = new MsgInfo();
			msgInfo.setChannel(EnumType.sendChannel.message.value);
			msgInfo.setMsgType(EnumType.msgType.manual.value);
			msgInfo.setCustGroupCode(null);
			msgInfo.setBusiCode(employeeId);
			msgInfo.setCustName(userInfo.get("userName"));
			msgInfo.setMsgTopic(sendMsgInfo.getMsgTopic());
			msgInfo.setMsgContent(sendMsgInfo.getTemplateContent());
			msgInfo.setSendWay(sendMsgInfo.getSendWay());
			msgInfo.setMobile(userInfo.get("telphone"));

			restUtil.postForObject(ServiceType.MANAGE, "/crm/manage/msgmng/sendMsg", msgInfo, Result.class);
		}

		// 获取客户电话
		String[] custNos = sendMsgInfo.getCustNo().split(",");
		for (String custNo : custNos) {

			Map map = new HashMap();
			map.put("custNo", custNo);
			// ECIF发起GET请求
			Result getForObject = restUtil.getForObject(ServiceType.ECIF,
					"/crm/ecif/cust/mng/perCustInfo?custNo={custNo}", Result.class, map);

			// 获取客户信息
			Map<String, String> perCustBaseInfo = (Map<String, String>) getForObject.getData();

			MsgInfo msgInfo = new MsgInfo();
			msgInfo.setChannel(EnumType.sendChannel.message.value);
			msgInfo.setMsgType(EnumType.msgType.manual.value);
			msgInfo.setCustGroupCode(null);
			msgInfo.setBusiCode(custNo);
			msgInfo.setCustName(perCustBaseInfo.get("custName"));
			msgInfo.setMsgTopic(sendMsgInfo.getMsgTopic());
			msgInfo.setMsgContent(sendMsgInfo.getTemplateContent());
			msgInfo.setSendWay(sendMsgInfo.getSendWay());
			msgInfo.setMobile(perCustBaseInfo.get("phoneNumber"));

			restUtil.postForObject(ServiceType.MANAGE, "/crm/manage/msgmng/sendMsg", msgInfo, Result.class);
		}

		if (sendMsgInfo.getDynamSnapshot() != null) {
			// 快照对象
			OcDynamSnapshot dynamSnapshot = sendMsgInfo.getDynamSnapshot();

			String snapshotId = "" + IdGenerator.getSeqID("SnapshotId");

			dynamSnapshot.setSnapshotId(snapshotId);
			// 生成快照
			dynamSnapshotService.addSnap(dynamSnapshot);
		}

	}

	/**
	 * 群发短信
	 * 
	 * @param CustGroupInfo
	 *            群组信息对象
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void assignOutBoundTask(CustGroupInfo custGroupInfo) {

		// 生成新的静态群组
		OcCustGrp custGrp = new OcCustGrp();
		custGrp.setGroupName(custGroupInfo.getGroupOper().getGroupOperName());
		custGrp.setGroupType(EnumType.CustGroupTyp.staticGroup.value);
		custGrp.setEstablishType(EnumType.EstablishType.operate.value);
		custGrp.setMemberType(custGroupInfo.getCustGrp().getMemberType());
		custGrp.setGroupDesc(custGroupInfo.getGroupOper().getGroupTaskDesc());
		String newGroupId = addCustGroup(custGrp);

		// 添加群组成员
		// 成员数不能为空
		if (custGroupInfo.getCustGrpMemberList() != null) {

			// 群组ID修改为新生成的群组的ID
			for (OcCustGrpMember item : custGroupInfo.getCustGrpMemberList()) {
				item.setGroupId(newGroupId);
			}
			// 添加群组成员
			custGrpMemberService.addGroupMember(custGroupInfo.getCustGrpMemberList());
		}

		// 生成群组运营任务基本信息
		OcGroupOper groupOper = custGroupInfo.getGroupOper();
		groupOper.setSnapshotId("");// TODO 该字段无用，不赋值，表中待删除
		groupOper.setEstablishTime(DateUtil.getDate(new Date()));
		groupOper.setGroupTaskStatus(EnumType.TaskStat.in_progress.value);// 任务状态默认为“进行中”，当群组任务下所有子任务全部完成后，才能置为已完成。
		String groupOperId = groupOperService.addOper(groupOper);

		// 生成快照表
		OcDynamSnapshot dynamSnapshot = custGroupInfo.getDynamSnapshot();
		dynamSnapshot.setNewGroupId(newGroupId);
		dynamSnapshot.setGroupOperId(groupOperId);
		dynamSnapshot.setGroupOperType(groupOper.getGroupOperType());
		dynamSnapshot.setEstablishUser(ContextContainer.getContext().getEmployeeId());
		dynamSnapshot.setEstablishTime(DateUtil.getDate(new Date()));
		String snapshotId = dynamSnapshotService.addSnap(dynamSnapshot);

		if (custGroupInfo.getEmployeeId() != null && !"".equals(custGroupInfo.getEmployeeId())) {

			// 给每一条任务分配负责人
			String[] employeeIds = custGroupInfo.getEmployeeId().split(",");

			List<OcGroupOperCust> groupOperCustList = new ArrayList<OcGroupOperCust>();

			int index = 0;
			for (OcCustGrpMember item : custGroupInfo.getCustGrpMemberList()) {

				// 用户分配负责人
				index = index % employeeIds.length;

				// 新增任务
				OcTask task = new OcTask();
				task.setTaskName(groupOper.getGroupOperName());// 任务名称
				task.setTaskType(EnumType.TaskType.others.value);// 任务类型：其他
				task.setTaskStat(EnumType.TaskStat.in_progress.value);// 任务状态
				task.setTaskBgnDate(DateUtil.getNow("yyyy-MM-dd"));// 开始日期
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				task.setTaskEndDate(sdf.format(groupOper.getEndTime()));// 截止日期
				task.setTaskDesc(groupOper.getGroupTaskDesc());// 任务描述
				task.setCustId(item.getCustNo());// 客户号
				task.setResponsId(employeeIds[index]);

				// 根据员工ID获取员工信息
				Map map = new HashMap();
				map.put("employeeId", employeeIds[index]);
				// MANAGE发起GET请求
				Result getForObject = restUtil.getForObject(ServiceType.MANAGE,
						"/crm/manage/usermng/getUserByEmployeeID?employeeId={employeeId}", Result.class, map);
				// 获取查询到的用户信息
				Map<String, String> userInfo = (Map<String, String>) getForObject.getData();
				task.setResponsName(userInfo.get("userName"));

				// 根据客户号获取客户名称
				map.clear();
				map.put("custNo", item.getCustNo());
				Result custResult = restUtil.getForObject(ServiceType.ECIF,
						"/crm/ecif/cust/getCustNameByCustNo?custNo={custNo}", Result.class, map);
				if (custResult.getData() != null) {
					task.setCustName(custResult.getData().toString());
				}

				String taskId = taskService.insertOne(task);

				OcGroupOperCust groupOperCust = new OcGroupOperCust();
				groupOperCust.setCustNo(item.getCustNo());
				groupOperCust.setTaskId(taskId);
				groupOperCust.setCustAgent(employeeIds[index]);
				groupOperCustList.add(groupOperCust);

				index++;

			}

			// 生成运营任务成员信息
			groupOperService.addOperCust(groupOperId, groupOperCustList);
		}
	}

	/**
	 * 查询动态群组策略关联关系
	 * 
	 * @param OcDynamGrpRuleRela
	 *            动态群组策略关联关系表对象
	 * 
	 */
	public List<OcDynamGrpRuleRela> queryGrpRuleRela(OcDynamGrpRuleRela dynamGrpRuleRela) {

		List<OcDynamGrpRuleRela> grpRuleRelaList = dynamGrpRuleRelaMapper.selectRuleRelaByEntity(dynamGrpRuleRela);

		return grpRuleRelaList;
	}

	/**
	 * 更新动态群组策略关联关系
	 * 
	 * @param OcDynamGrpRuleRela
	 *            动态群组策略关联关系表对象
	 * 
	 */
	public void modifGrpRuleRela(OcDynamGrpRuleRela dynamGrpRuleRela) {

		// 群组ID
		String groupId = dynamGrpRuleRela.getGroupId();
		// 规则ID
		String strategyId = dynamGrpRuleRela.getStrategyId();

		if (groupId == null || "".equals(groupId)) {
			throw new ServiceException("群组ID不能为空！");
		}
		if (strategyId == null || "".equals(strategyId)) {
			throw new ServiceException("规则ID不能为空！");
		}

		// 根据群组ID查询关联关系表
		OcDynamGrpRuleRela temp = new OcDynamGrpRuleRela();
		temp.setGroupId(groupId);
		List<OcDynamGrpRuleRela> grpRuleRelaList = dynamGrpRuleRelaMapper.selectRuleRelaByEntity(temp);

		// 若策略关联关系存在则跟新策略ID，否则新增策略关联关系记录
		if (grpRuleRelaList != null && grpRuleRelaList.size() > 0) {

			for (OcDynamGrpRuleRela item : grpRuleRelaList) {
				item.setStrategyId(dynamGrpRuleRela.getStrategyId());

				// 执行更新
				dynamGrpRuleRelaMapper.updateByPrimaryKey(item);
			}
		} else {

			// 生成策略关联表ID
			String grpRuleRelaId = "" + IdGenerator.getSeqID("GrpRuleRelaId");

			dynamGrpRuleRela.setRelaId(grpRuleRelaId);
			// 新增策略关联表记录
			dynamGrpRuleRelaMapper.insert(dynamGrpRuleRela);
		}

		// 调用动态群组生成接口，更新动态群组成员
		// 获取所有客户数据（包含所有用于匹配规则的字段的值）
		Result getCustResult = restUtil.getForObject(ServiceType.ECIF, "/crm/ecif/tagmng/data", Result.class);
		String custListJson = getCustResult.getData().toString();

		Map map = new HashMap();
		map.put("custListJson", custListJson);
		map.put("strategyId", strategyId);
		// MANAGE发起GET请求
		Result getForObject = restUtil.postForObject(ServiceType.MANAGE, "/crm/manage/engine/getCustByStrategyId", map,
				Result.class);

		Object obj = getForObject.getData();
		// 获取到该规则匹配到的所有的用户（字符串格式，逗号隔开）
		String custNos = obj.toString();
		String[] custNoArr = null;

		// 删除动态群组所有成员
		custGrpMemberMapper.deleteByGroupId(groupId);

		// 获取动态群组信息
		OcCustGrp custGrp = custGrpMapper.selectByPrimaryKey(groupId);

		// 生成轨迹ID
		String traceId = "" + IdGenerator.getSeqID("GrpCustTrace");

		// 新增动态群组轨迹表
		if (custNos != null && !"".equals(custNos)) {
			custNoArr = custNos.split(",");

			// 更新群组表群组人数字段
			custGrp.setCustNum(new Long(custNoArr.length));
			custGrpMapper.updateByPrimaryKey(custGrp);

			// 将查询到的客户添加至群组成员表
			for (String custNo : custNoArr) {

				OcCustGrpMember custGrpMember = new OcCustGrpMember();
				custGrpMember.setGroupId(groupId);// 群组ID
				custGrpMember.setGroupType(EnumType.CustGroupTyp.dynamicGroup.value);
				custGrpMember.setCustNo(custNo);// 客户号
				custGrpMember.setCustType("");// 客户类型 TODO 需要根据客户号获取客户类型
				custGrpMember.setAddTime(DateUtil.getDate(new Date()));// 添加时间
				// 新增动态群组成员
				custGrpMemberMapper.insert(custGrpMember);

				OcGrpCustTrace grpCustTrace = new OcGrpCustTrace();
				grpCustTrace.setTraceId(traceId);// 轨迹ID
				grpCustTrace.setGroupId(groupId);// 群组ID
				grpCustTrace.setCustNo(custNo);// 客户号
				grpCustTrace.setEstablishDate(DateUtil.getDate(new Date()));// 生成时间
				grpCustTrace.setStrategyId(strategyId);// 规则ID
				// 更新动态客户群客户轨迹表
				grpCustTraceMapper.insert(grpCustTrace);
			}
		} else {
			// 更新群组表群组人数字段为0
			custGrp.setCustNum(new Long(0));
			custGrpMapper.updateByPrimaryKey(custGrp);
		}

		// 新增客户群组轨迹表记录
		OcGrpTrace grpTrace = new OcGrpTrace();
		grpTrace.setTraceId(traceId);// 轨迹ID
		grpTrace.setGroupId(groupId);// 群组ID
		grpTrace.setCustNum(custNoArr == null ? 0 : custNoArr.length);
		grpTrace.setEstablishDate(DateUtil.getDate(new Date()));// 生成时间
		grpTrace.setStrategyId(strategyId);// 规则ID
		grpTraceMapper.insert(grpTrace);
	}

	/**
	 * 获取动态群组客户趋势数据 (按月计算当月的用户数的最大值)
	 * 
	 * @param String
	 *            groupId 群组ID
	 * 
	 * @throws ParseException
	 * 
	 */
	public List<LineChartData> grpMemberTrendAnalysisByMonth(String groupId) throws ParseException {

		// 线性chart图基础数据
		List<LineChartData> lineChartDataList = new ArrayList<LineChartData>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

		OcGrpCustTrace record = new OcGrpCustTrace();
		record.setGroupId(groupId);// 群组Id
		// 根据群组ID获取群组成员历史轨迹数据(按日期降序排列)
		List<OcGrpCustTrace> grpCustTraceList = grpCustTraceMapper.selectByEntity(record);

		// 轨迹数据存在时
		if (grpCustTraceList != null && grpCustTraceList.size() > 0) {
			// 获取成员列表记录中的起始日期和终止日期
			OcGrpCustTrace begin = grpCustTraceList.get(0);
			OcGrpCustTrace end = grpCustTraceList.get(grpCustTraceList.size() - 1);
			Date beginDate = new SimpleDateFormat("yyyy-MM").parse(sdf.format(begin.getEstablishDate()));
			Date endDate = new SimpleDateFormat("yyyy-MM").parse(sdf.format(end.getEstablishDate()));

			Calendar dateCalendar = Calendar.getInstance();// 定义日期实例

			// 因为后面用到了dateCalendar.getTime().before(beginDateAdd)，必须为最后一个月份加一个月份后的值，否则，最后一个月的数据无法取出
			dateCalendar.setTime(beginDate);
			dateCalendar.add(Calendar.MONTH, 1);// 进行当前日期月份加1
			Date beginDateAdd = dateCalendar.getTime();

			dateCalendar.setTime(endDate);
			// 按月递增，获取每个月的客户数量数据
			while (dateCalendar.getTime().before(beginDateAdd)) {

				// 月份字符串，用于和群组成员轨迹列表中的数据匹配，获取当月的客户数量
				String dateStr = sdf.format(dateCalendar.getTime());

				// 临时存储每个月的成员列表
				List<OcGrpCustTrace> grpCustTraceListTemp = new ArrayList<OcGrpCustTrace>();

				// 解析群组成员历史数据，计算每个月的成员数量 (每个月的客户去重求和)
				for (OcGrpCustTrace item : grpCustTraceList) {

					String monthTmp = sdf.format(item.getEstablishDate()).substring(0, 7);
					if (monthTmp.equals(dateStr)) {
						grpCustTraceListTemp.add(item);
					}
				}

				// 按客户号去重
				// 所有相似客户信息列表去除重复记录
				List<OcGrpCustTrace> grpCustTraceListNew = new ArrayList<OcGrpCustTrace>();
				for (OcGrpCustTrace item : grpCustTraceListTemp) {
					boolean flg = false;
					for (OcGrpCustTrace itemNew : grpCustTraceListNew) {// 去重处理
						if (item.getCustNo().equals(itemNew.getCustNo())) {
							flg = true;
							break;
						}
					}
					if (!flg) {
						grpCustTraceListNew.add(item);
					}
				}

				LineChartData lineCharData = new LineChartData();
				lineCharData.setDate(dateStr);
				lineCharData.setCount("" + grpCustTraceListNew.size());
				lineChartDataList.add(lineCharData);

				dateCalendar.add(Calendar.MONTH, 1);// 进行当前日期月份加1
			}
		} else {
			// 无处理
		}
		return lineChartDataList;
	}

	/**
	 * 获取动态群组客户趋势数据 (按轨迹表日期获取每次变动后的客户数量)
	 * 
	 * @param String
	 *            groupId 群组ID
	 * 
	 * @throws ParseException
	 * 
	 */
	public List<LineChartData> grpMemberTrendAnalysisByDays(String groupId) throws ParseException {

		// 线性chart图基础数据
		List<LineChartData> lineChartDataList = new ArrayList<LineChartData>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// 获取群组轨迹表数据
		List<OcGrpTrace> grpTraceList = grpTraceMapper.selectMaxCustNumByGroupId(groupId);

		// 遍历轨迹列表，编辑每次轨迹的日期及客户人数
		for (OcGrpTrace item : grpTraceList) {
			LineChartData lineCharData = new LineChartData();
			lineCharData.setDate(sdf.format(item.getEstablishDate()));
			lineCharData.setCount("" + item.getCustNum());
			lineChartDataList.add(lineCharData);
		}

		return lineChartDataList;
	}
}
