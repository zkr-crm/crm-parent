package cn.com.zsyk.crm.ecif.service.similar;

import cn.com.zsyk.crm.common.dao.CoreDaoImpl;
import cn.com.zsyk.crm.common.dto.ContextContainer;
import cn.com.zsyk.crm.common.dto.PageBean;
import cn.com.zsyk.crm.common.util.*;
import cn.com.zsyk.crm.ecif.bo.cust.MsgInfo;
import cn.com.zsyk.crm.ecif.bo.cust.PerCustBaseInfo;
import cn.com.zsyk.crm.ecif.bo.cust.SimCustBaseInfo;
import cn.com.zsyk.crm.ecif.bo.cust.SimCustTask;
import cn.com.zsyk.crm.ecif.entity.*;
import cn.com.zsyk.crm.ecif.mapper.*;
import cn.com.zsyk.crm.ecif.service.customer.baseinfo.CustService;
import cn.com.zsyk.crm.ecif.service.customer.integrals.CustIntegralService;
import cn.com.zsyk.crm.generator.EnumType;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class CustSimTaskMngService {
	/** logger */
	private final Log logger = LogUtil.getLogger(CustIntegralService.class);

	/** 相似客户任务表 */
	@Autowired
	private EcSimTaskMapper simTaskMapper;
	/** 相似客户判断规则表 */
	@Autowired
	private EcSimCustRuleMapper simCustRuleMapper;
	/** 相似客户信息表 */
	@Autowired
	private EcSimCustMapper simCustMapper;
	/** 相似客户合并拆分轨迹表 */
	@Autowired
	private EcMergeSplitTraceMapper mergeSplitTraceMapper;
	/** 相似客户拆分申请表 */
	@Autowired
	private EcCustSplitApplyMapper custSplitApplyMapper;
	/** 相似客户合并申请表 */
	@Autowired
	private EcCustMergeApplyMapper custMergeApplyMapper;
	/** 客户合并关系表 */
	@Autowired
	private EcCustMergeRelaMapper custMergeRelaMapper;
	/** 不同客户标记表 */
	@Autowired
	private EcDiffCustMapper diffCustMapper;
	/** 个人客户基本信息表 */
	@Autowired
	private EcCustPerMapper custPerMapper;
	/** 客户拆分关系表 */
	@Autowired
	private EcCustSplitRelaMapper custSplitRelaMapper;

	/** 个人客户信息service */
	@Autowired
	private CustService custService;
	@Autowired
	private CoreDaoImpl coreDaoImpl;
	/** 后台发送rest请求对象 */
	@Autowired
	RestUtil restUtil;
	@Autowired
	private EcCustManagerMapper ecCustManagerMapper;

	/**
	 * Desc: 按条件查询所有任务
	 * 
	 * @return
	 * @author
	 */
	@SuppressWarnings("rawtypes")
	public PageBean selectAllTaskByEntity(EcSimTask simTask) {
		logger.info("simTask:" + simTask);

		PageBean bean = coreDaoImpl.selectPageByMapper(simTaskMapper, "selectAllTaskByEntity", simTask);
		return bean;
	}

	/**
	 * Desc: 按条件查询个人用户的所有合并审批任务
	 * 
	 * @return
	 * @author
	 */
	public PageBean getMyTaskByEmployeeId(EcSimTask simTask) {
		logger.info("simTask:" + simTask);

		PageBean bean = coreDaoImpl.selectPageByMapper(simTaskMapper, "getMyTaskByEmployeeId",
				ContextContainer.getContext().getEmployeeId());
		return bean;
	}

	/**
	 * Desc: 按条件查询个人用户的所有拆分审批任务
	 * 
	 * @return
	 * @author
	 */
	@SuppressWarnings("rawtypes")
	public PageBean getMySplitTaskByEntity(String employeeId, String taskState) {

		Map map = new HashMap();
		map.put("employeeId", employeeId);
		map.put("taskState", taskState);
		PageBean bean = coreDaoImpl.selectPageByMapper(simTaskMapper, "getMySplitTaskByEntity", map);
		return bean;
	}

	/**
	 * Desc: 查询所有未分配审批人的任务
	 * 
	 * @return
	 * @author
	 */
	public PageBean selectAllNoApproveUser(EcSimTask simTask) {
		logger.info("simTask:" + simTask);

		PageBean bean = coreDaoImpl.selectPageByMapper(simTaskMapper, "selectAllNoApproveUser", simTask);
		return bean;
	}

	/**
	 * Desc: 查询所有未分配审批人的任务
	 * 
	 * @return
	 * @author
	 */
	public PageBean selectAllNoApproveSplitTask(EcSimTask simTask) {
		logger.info("simTask:" + simTask);

		PageBean bean = coreDaoImpl.selectPageByMapper(simTaskMapper, "selectAllNoApproveSplitTask", simTask);
		return bean;
	}

	/**
	 * Desc: 根据任务编号获取任务的所有详细信息
	 * 
	 * @return
	 * @author
	 * @throws Exception
	 */
	public EcSimTaskDetil getTaskDetilByTaskId(String taskId) throws Exception {
		logger.info("taskId:" + taskId);

		// 任务详细信息对象初始化
		EcSimTaskDetil detil = new EcSimTaskDetil();
		// 任务编号
		detil.setTaskId(taskId);

		// 获取获取相似客户任务表信息
		EcSimTask simTask = null;
		simTask = simTaskMapper.selectByPrimaryKey(taskId);
		detil.setSimTask(simTask);

		// 获取相同客户判断规则表信息
		EcSimCustRule simCustRule = new EcSimCustRule();
		simCustRule = simCustRuleMapper.selectByPrimaryKey(simTask.getRuleId());
		detil.setSimCustRule(simCustRule);

		// 获取相似客户信息表信息
		List<EcSimCust> simCustList = null;
		EcSimCust simCust = new EcSimCust();
		simCust.setTaskId(taskId);
		simCustList = simCustMapper.selectByEntity(simCust);
		detil.setSimCustList(simCustList);

		// 获取个人客户详细信息
		List<PerCustBaseInfo> perCustBaseInfoList = new ArrayList<PerCustBaseInfo>();
		for (EcSimCust item : simCustList) {
			PerCustBaseInfo perCustBaseInfo = custService.getPerCustInfo(item.getSimilarCustNo());
			perCustBaseInfoList.add(perCustBaseInfo);
		}
		detil.setSimCustDetilList(perCustBaseInfoList);

		// 获取相似客户合并拆分轨迹表信息
		List<EcMergeSplitTrace> mergeSplitTraceList = null;
		EcMergeSplitTrace mergeSplitTrace = new EcMergeSplitTrace();
		mergeSplitTrace.setTaskId(taskId);
		mergeSplitTraceList = mergeSplitTraceMapper.selectByEntity(mergeSplitTrace);
		detil.setMergeSplitTraceList(mergeSplitTraceList);

		// 获取相似客户拆分申请表信息
		List<EcCustSplitApply> custSplitApplyList = null;
		EcCustSplitApply custSplitApply = new EcCustSplitApply();
		// custSplitApply.setApplyId(applyId);
		custSplitApply.setTaskId(taskId);
		custSplitApplyList = custSplitApplyMapper.selectByEntity(custSplitApply);
		detil.setCustSplitApplyList(custSplitApplyList);

		// 获取相似客户合并申请表信息
		List<EcCustMergeApply> custMergeApplyList = null;
		EcCustMergeApply custMergeApply = new EcCustMergeApply();
		custMergeApply.setTaskId(taskId);
		custMergeApplyList = custMergeApplyMapper.selectByEntity(custMergeApply);
		detil.setCustMergeApplyList(custMergeApplyList);

		// 客户关系合并表
		List<EcCustMergeRela> custMergeRelaList = custMergeRelaMapper.selectMergeCustByTaskId(taskId);
		detil.setCustMergeRelaList(custMergeRelaList);

		return detil;
	}

	/**
	 * Desc: 提交相似客户合并申请
	 * 
	 * @return
	 * @author
	 */
	public void applyMergeSimCust(SimCustTask applyInfo) {

		// 执行人员员工号
		String execEmployeeId = ContextContainer.getContext().getEmployeeId();
		// 执行时间
		Date execDate = DateUtil.getTimestamp(new Date());

		// 新增相似客户合并申请表
		EcCustMergeApply custMergeApply = new EcCustMergeApply();
		custMergeApply.setTaskId(applyInfo.getTaskId());
		custMergeApply.setApplyId("" + IdGenerator.getSeqID("MergeApply"));
		custMergeApply.setApplyOpinion(applyInfo.getCustMergeApply().getApplyOpinion());
		custMergeApply.setApplyState(EnumType.SimTaskState.merge_apply.value);
		custMergeApply.setApplyTime(execDate);
		custMergeApply.setApplyUser(execEmployeeId);
		custMergeApply.setApplyTyp(EnumType.TaskApplyTyp.merge_apply.value);
		// 合并申请提交
		custMergeApplyMapper.insert(custMergeApply);

		// 标志要合并的客户信息，合并的客户置为待合并,不合并的客户置为正常
		EcSimCust record = new EcSimCust();
		record.setTaskId(applyInfo.getTaskId());
		List<EcSimCust> allCustList = simCustMapper.selectByEntity(record);
		List<SimCustBaseInfo> custList = applyInfo.getSimCustList();

		// 待合并客户状态设置
		for (SimCustBaseInfo custBaseInfo : custList) {
			EcSimCust simCustInfo = simCustMapper.selectByPrimaryKey(custMergeApply.getTaskId(),
					custBaseInfo.getCustNo());

			// 相似客户合并状态:合并申请中
			simCustInfo.setSimCustState(EnumType.SimCustState.wait_for_merge.value);

			simCustMapper.updateByPrimaryKey(simCustInfo);
		}

		// 不合并客户状态设置
		for (EcSimCust item : allCustList) {
			boolean flg = false;
			for (SimCustBaseInfo custBaseInfo : custList) {
				if (item.getSimilarCustNo().equals(custBaseInfo.getCustNo())) {
					flg = true;
					break;
				}
			}
			if(!flg) {
				EcSimCust simCustInfo = simCustMapper.selectByPrimaryKey(custMergeApply.getTaskId(),
						item.getSimilarCustNo());

				// 相似客户合并状态:合并申请中
				simCustInfo.setSimCustState(EnumType.SimCustState.normal.value);

				simCustMapper.updateByPrimaryKey(simCustInfo);
			}
		}

		// 更新相似客户任务状态
		EcSimTask simTask = simTaskMapper.selectByPrimaryKey(applyInfo.getTaskId());
		simTask.setTaskState(EnumType.SimTaskState.merge_apply.value);// 申请合并
		simTaskMapper.updateByPrimaryKey(simTask);

		// 当任务合并审批人字段有值时，给对应的人发送站内提醒
		if (simTask.getApproveUser() != null && !"".equals(simTask.getApproveUser())) {

			// 消息发送对象
			MsgInfo msgInfo = new MsgInfo();
			msgInfo.setMsgCode("RDef_101");// 消息编码 TODO 消息编码不应该直接固定写法如此写法
			msgInfo.setBusiCode(execEmployeeId);// 员工编号

			String params = simTask.getApproveUser() + "," + "[" + applyInfo.getTaskId() + "]";
			msgInfo.setParams(params);// 提醒消息占位符参数
			msgInfo.setChannel(EnumType.sendChannel.remind.value);// 发送渠道：站内提醒
			String msgInfoJson = JsonUtil.toJSONString(msgInfo);
			/* 给负责审批的业务人员发送站内提醒 */
			//restUtil.postForObject(ServiceType.MANAGE, "/crm/manage/msgmng/sendRemindMsg", msgInfoJson, Result.class);
		}

		/* 更新相似客户合并拆分轨迹表 */
		EcMergeSplitTrace mergeSplitTrace = new EcMergeSplitTrace();
		mergeSplitTrace.setTaskId(applyInfo.getTaskId());// 任务编号
		mergeSplitTrace.setTraceId(generateTraceId(applyInfo.getTaskId()));// 轨迹ID
		mergeSplitTrace.setMergeSplitAction(EnumType.MergeSplitAction.merge_apply.value);// 合并拆分动作
		mergeSplitTrace.setDealUser(execEmployeeId);// 处理人员
		mergeSplitTrace.setDealTime(execDate);// 处理时间
		mergeSplitTrace.setDealConclusion("");// 处理结论
		mergeSplitTrace.setDealOpinion(applyInfo.getCustMergeApply().getApplyOpinion());// 处理意见
		mergeSplitTraceMapper.insert(mergeSplitTrace);
	}

	/**
	 * Desc: 客户信息合并申请撤销
	 * 
	 * @return
	 * @author
	 */
	private String revokeMergeApply(List<SimCustBaseInfo> mergeCustList) {

		// 合并后新客户号
		String newCustNo = "";

		return newCustNo;
	}

	/**
	 * Desc: 提交合并通过
	 * 
	 * @return
	 * @author
	 * @throws Exception
	 */
	public String applyMergePass(SimCustTask applyInfo) throws Exception {

		// 执行人员员工号
		String execEmployeeId = ContextContainer.getContext().getEmployeeId();
		// 执行时间
		Date execDate = DateUtil.getTimestamp(new Date());

		/* 更新任务状态 */
		EcSimTask simTask = simTaskMapper.selectByPrimaryKey(applyInfo.getTaskId());
		simTask.setTaskState(EnumType.SimTaskState.merged.value);// 合并
		simTaskMapper.updateByPrimaryKey(simTask);

		/* 更新用户列表状态 */
		for (SimCustBaseInfo item : applyInfo.getSimCustList()) {
			if (item.isChecked()) {
				EcSimCust simCust = simCustMapper.selectByPrimaryKey(applyInfo.getTaskId(), item.getCustNo());
				simCust.setSimCustState(EnumType.CustStat.merged.value);// 被合并
				simCustMapper.updateByPrimaryKey(simCust);
			}
		}

		/* 更新相似客户合并申请表 */
		// 根据任务ID获取合并申请ID
		EcCustMergeApply custMergeApply = new EcCustMergeApply();
		custMergeApply.setTaskId(applyInfo.getTaskId());
		List<EcCustMergeApply> list = custMergeApplyMapper.selectByEntity(custMergeApply);
		if (list != null && list.size() > 0) {
			// 更新相似客户合并申请表
			custMergeApply = list.get(0);
			custMergeApply.setApproveUser(execEmployeeId);// 审批人
			custMergeApply.setApproveTime(execDate);// 审批人时间
			custMergeApply.setApproveConclusion(applyInfo.getCustMergeApply().getApproveConclusion());// 审批人结论
			custMergeApply.setApproveSugges(applyInfo.getCustMergeApply().getApproveSugges());// 审批人意见
			custMergeApply.setApplyState(EnumType.SimTaskState.merged.value);// 申请状态
			custMergeApplyMapper.updateByPrimaryKey(custMergeApply);
		}

		/* 更新相似客户合并拆分轨迹表 */
		EcMergeSplitTrace mergeSplitTrace = new EcMergeSplitTrace();
		mergeSplitTrace.setTaskId(applyInfo.getTaskId());// 任务编号
		mergeSplitTrace.setTraceId(generateTraceId(applyInfo.getTaskId()));// 轨迹ID
		mergeSplitTrace.setMergeSplitAction(EnumType.MergeSplitAction.merge_approv_pass.value);// 合并拆分动作
		mergeSplitTrace.setDealUser(execEmployeeId);// 处理人员
		mergeSplitTrace.setDealTime(execDate);// 处理时间
		mergeSplitTrace.setDealConclusion(EnumType.ApprovConclusion.pass.value);// 处理结论
		mergeSplitTrace.setDealOpinion(applyInfo.getCustMergeApply().getApproveSugges());// 处理意见
		mergeSplitTraceMapper.insert(mergeSplitTrace);

		// 相似客户列表
		List<SimCustBaseInfo> simCustList = applyInfo.getSimCustList();

		// 合并列表
		List<SimCustBaseInfo> mergeCustList = new ArrayList<SimCustBaseInfo>();
		// 非合并列表
		List<SimCustBaseInfo> unMergeCustList = new ArrayList<SimCustBaseInfo>();
		for (SimCustBaseInfo simCust : simCustList) {
			if (simCust.isChecked()) {
				mergeCustList.add(simCust);
			} else {
				unMergeCustList.add(simCust);
			}
		}

		/* 客户合并 */
		String newCustNo = custBaseInfoMerge(mergeCustList);

		// 新增客户合并关系表记录
		for (SimCustBaseInfo item : mergeCustList) {
			EcCustMergeRela custMergeRela = new EcCustMergeRela();
			custMergeRela.setMergeId("" + IdGenerator.getSeqID("CustMerge"));// 合并ID
			custMergeRela.setTaskId(applyInfo.getTaskId());// 任务编号
			// 被合并客户
			custMergeRela.setOldCustNo(item.getCustNo());
			// 合并后客户
			custMergeRela.setNewCustNo(newCustNo);
			// 合并类型
			custMergeRela.setMergeType(EnumType.CustMergeTyp.simMerge.value);
			// 合并时间
			custMergeRela.setMergeTime(execDate);
			custMergeRelaMapper.insert(custMergeRela);
		}

		/* 不同客户标记表 */
		if (unMergeCustList != null && unMergeCustList.size() > 0) {
			for (SimCustBaseInfo item : unMergeCustList) {
				EcDiffCust diffCust = new EcDiffCust();
				diffCust.setTaskId(applyInfo.getTaskId());// 任务ID
				diffCust.setSourceCustNo(newCustNo);// 源客户号
				diffCust.setTargetCustNo(item.getCustNo());// 目标客户号
				diffCust.setMarkUser(execEmployeeId);// 标记人
				diffCust.setMarkReason("");// 标记原因 TODO:数据来源不明
				diffCust.setMarkTime(execDate);// 标记时间
				diffCust.setMarkConclusion("");// 标记结论 TODO:数据来源不明
				diffCustMapper.insert(diffCust);
			}
		}

		/* 被合并客户状态置为无效 客户状态置为:合并 */
		for (SimCustBaseInfo item : mergeCustList) {
			EcCustPer custPer = custPerMapper.selectByPrimaryKey(item.getCustNo());
			custPer.setCustStat(EnumType.CustStat.merged.value);
			custPerMapper.updateByPrimaryKey(custPer);
		}

		return newCustNo;// 合并后的客户号
	}

	/**
	 * Desc: 提交合并退回重审
	 * 
	 * @return
	 * @author
	 */
	public void applyMergeSendBack(SimCustTask applyInfo) {

		// 执行人员员工号
		String execEmployeeId = ContextContainer.getContext().getEmployeeId();
		// 执行时间
		Date execDate = DateUtil.getTimestamp(new Date());

		/* 更新任务状态 */
		EcSimTask simTask = simTaskMapper.selectByPrimaryKey(applyInfo.getTaskId());
		simTask.setTaskState(EnumType.SimTaskState.merge_send_back.value);// 合并审批退回
		simTaskMapper.updateByPrimaryKey(simTask);

		// 消息发送对象
		MsgInfo msgInfo = new MsgInfo();
		msgInfo.setMsgCode("RDef_101");// 消息编码 TODO 消息编码不应该直接固定写法如此写法
		msgInfo.setBusiCode(execEmployeeId);// 员工编号
		String params = simTask.getDealUser() + "," + "[" + applyInfo.getTaskId() + "]";
		msgInfo.setParams(params);// 提醒消息占位符参数
		msgInfo.setChannel(EnumType.sendChannel.remind.value);// 发送渠道：站内提醒
		String msgInfoJson = JsonUtil.toJSONString(msgInfo);
		/* 给提交申请的业务人员发送站内提醒 应该使用消息队列MQ解耦处理，避免消息异常导致任务分配失败。工期原因先注释掉*/
//		restUtil.postForObject(ServiceType.MANAGE, "/crm/manage/msgmng/sendRemindMsg", msgInfoJson, Result.class);

		/* 更新相似客户合并申请表 申请状态 */
		// 根据任务ID获取合并申请ID
		EcCustMergeApply custMergeApply = new EcCustMergeApply();
		custMergeApply.setTaskId(applyInfo.getTaskId());
		List<EcCustMergeApply> list = custMergeApplyMapper.selectByEntity(custMergeApply);
		if (list != null && list.size() > 0) {
			// 更新相似客户合并申请表
			custMergeApply = list.get(0);
			custMergeApply.setApplyState(EnumType.SimTaskState.merge_send_back.value);// 申请状态 ：退回
			custMergeApply.setApproveConclusion(EnumType.ApprovConclusion.send_back.value);// 审批人结论
			custMergeApply.setApproveSugges(applyInfo.getCustMergeApply().getApproveSugges());// 审批人意见
			custMergeApply.setApproveUser(execEmployeeId);// 审批人
			custMergeApply.setApproveTime(execDate);// 审批时间
			custMergeApplyMapper.updateByPrimaryKey(custMergeApply);
		}

		/* 更新相似客户合并拆分轨迹表 */
		EcMergeSplitTrace mergeSplitTrace = new EcMergeSplitTrace();
		mergeSplitTrace.setTaskId(applyInfo.getTaskId());// 任务编号
		mergeSplitTrace.setTraceId(generateTraceId(applyInfo.getTaskId()));// 轨迹ID
		mergeSplitTrace.setMergeSplitAction(EnumType.MergeSplitAction.merge_approv_not_pass.value);// 合并拆分动作
		mergeSplitTrace.setDealUser(execEmployeeId);// 处理人员
		mergeSplitTrace.setDealTime(execDate);// 处理时间
		mergeSplitTrace.setDealConclusion(EnumType.ApprovConclusion.send_back.value);// 处理结论
		mergeSplitTrace.setDealOpinion(applyInfo.getCustMergeApply().getApproveSugges());// 处理意见
		mergeSplitTraceMapper.insert(mergeSplitTrace);
	}

	/**
	 * Desc: 提交关闭退回重审
	 * 
	 * @return
	 * @author
	 */
	public void applyCloseSendBack(SimCustTask applyInfo) {

		// 执行人员员工号
		String execEmployeeId = ContextContainer.getContext().getEmployeeId();
		// 执行时间
		Date execDate = DateUtil.getTimestamp(new Date());

		/* 更新任务状态 */
		EcSimTask simTask = simTaskMapper.selectByPrimaryKey(applyInfo.getTaskId());
		simTask.setTaskState(EnumType.SimTaskState.close_send_back.value);// 关闭审批退回
		simTaskMapper.updateByPrimaryKey(simTask);

		/* 更新相似客户合并申请表 申请状态 */
		// 根据任务ID获取合并申请ID
		EcCustMergeApply custMergeApply = new EcCustMergeApply();
		custMergeApply.setTaskId(applyInfo.getTaskId());
		List<EcCustMergeApply> list = custMergeApplyMapper.selectByEntity(custMergeApply);
		if (list != null && list.size() > 0) {
			// 更新相似客户合并申请表
			custMergeApply = list.get(0);
			custMergeApply.setApplyState(EnumType.SimTaskState.close_send_back.value);// 申请状态 ：退回
			custMergeApply.setApproveConclusion(EnumType.ApprovConclusion.send_back.value);// 审批人结论
			custMergeApply.setApproveSugges(applyInfo.getCustMergeApply().getApproveSugges());// 审批人意见
			custMergeApply.setApproveUser(execEmployeeId);// 审批人
			custMergeApply.setApproveTime(execDate);// 审批时间
			custMergeApplyMapper.updateByPrimaryKey(custMergeApply);
		}

		/* 更新相似客户合并拆分轨迹表 */
		EcMergeSplitTrace mergeSplitTrace = new EcMergeSplitTrace();
		mergeSplitTrace.setTaskId(applyInfo.getTaskId());// 任务编号
		mergeSplitTrace.setTraceId(generateTraceId(applyInfo.getTaskId()));// 轨迹ID
		mergeSplitTrace.setMergeSplitAction(EnumType.MergeSplitAction.close_approve_not_pass.value);// 关闭申请未通过
		mergeSplitTrace.setDealUser(execEmployeeId);// 处理人员
		mergeSplitTrace.setDealTime(execDate);// 处理时间
		mergeSplitTrace.setDealConclusion(EnumType.ApprovConclusion.send_back.value);// 处理结论
		mergeSplitTrace.setDealOpinion(applyInfo.getCustMergeApply().getApproveSugges());// 处理意见
		mergeSplitTraceMapper.insert(mergeSplitTrace);

		// 消息发送对象
		MsgInfo msgInfo = new MsgInfo();
		msgInfo.setMsgCode("RDef_101");// 消息编码 TODO 消息编码不应该直接固定写法如此写法
		msgInfo.setBusiCode(execEmployeeId);// 员工编号
		String params = simTask.getDealUser() + "," + "[" + applyInfo.getTaskId() + "]";
		msgInfo.setParams(params);// 提醒消息占位符参数
		msgInfo.setChannel(EnumType.sendChannel.remind.value);// 发送渠道：站内提醒
		String msgInfoJson = JsonUtil.toJSONString(msgInfo);
//		/* 给分配任务的业务人员发送站内提醒 */应该使用消息队列MQ解耦处理，避免消息异常导致任务分配失败。工期原因先注释掉
//		restUtil.postForObject(ServiceType.MANAGE, "/crm/manage/msgmng/sendRemindMsg", msgInfoJson, Result.class);
	}

	/**
	 * Desc: 客户信息合并
	 * 
	 * @return
	 * @author
	 * @throws Exception
	 */
	private String custBaseInfoMerge(List<SimCustBaseInfo> mergeCustList) throws Exception {

		// TODO 客户合并规则不确定，暂时mock

		EcCustPer custPer1 = new EcCustPer();
		EcCustPer custPer2 = new EcCustPer();

		/** 按客户的创建时间合并客户，取最新的客户的信息生成新的客户号 */
		boolean flg = true;
		String custTyp = "";
		for (SimCustBaseInfo item : mergeCustList) {
			if (flg) {
				flg = false;
				custPer1 = custPerMapper.selectByPrimaryKey(item.getCustNo());
			} else {
				custPer2 = custPerMapper.selectByPrimaryKey(item.getCustNo());
				if (custPer2.getCreateDate().compareTo(custPer1.getCreateDate()) > 0) {
					custPer1 = custPer2;
					if(custPer1.getCustTyp().equals("01")){
						custTyp = "01";
					}
				} else if (custPer2.getCreateDate().compareTo(custPer1.getCreateDate()) == 0) {
					if (custPer2.getCreateTime().compareTo(custPer1.getCreateTime()) > 0) {
						custPer1 = custPer2;
						if(custPer1.getCustTyp().equals("01")){
							custTyp = "01";
						}
					}
				}
			}
		}

		// 获取个人客户全部信息
		PerCustBaseInfo custInfo = custService.getPerCustInfo(custPer1.getCustNo());
		if(custTyp.equals("01")){
			custInfo.setCustTyp(custTyp);
		}
		custInfo.setPerIconSmlBlob("");
		custInfo.setMergeMark(EnumType.MergeSplitAction.merge_approv_pass.desc);
		// 生成新的客户号
		// 新增客户信息
		String newCustNo = custService.addPerCustInfo(custInfo);
		for (SimCustBaseInfo item : mergeCustList) {
			List<EcCustManager> ecCustManagers = ecCustManagerMapper.selectByList(item.getCustNo());
			for(EcCustManager custManager : ecCustManagers){
				EcCustManager ecCustManager = new EcCustManager();
				ecCustManager.setCustNo(newCustNo);
				ecCustManager.setCustAgent(custManager.getCustAgent());
				ecCustManager.setRiseTime(custManager.getRiseTime());
				ecCustManagerMapper.insert(ecCustManager);
			}
		}
		return newCustNo;
	}

	/**
	 * Desc: 提交关闭任务申请
	 * 
	 * @return
	 * @author
	 */
	public void doCloseTaskApply(SimCustTask applyInfo) {
		// 执行人员员工号
		String execEmployeeId = ContextContainer.getContext().getEmployeeId();
		// 执行时间
		Date execDate = DateUtil.getTimestamp(new Date());

		// 新增相似客户关闭申请表
		EcCustMergeApply custMergeApply = new EcCustMergeApply();
		custMergeApply.setTaskId(applyInfo.getTaskId());
		custMergeApply.setApplyId("" + IdGenerator.getSeqID("MergeApply"));
		custMergeApply.setApplyOpinion(applyInfo.getCustMergeApply().getApplyOpinion());
		custMergeApply.setApplyState(EnumType.SimTaskState.close_apply.value);
		custMergeApply.setApplyTime(execDate);
		custMergeApply.setApplyUser(execEmployeeId);
		custMergeApply.setApplyTyp(EnumType.TaskApplyTyp.close_apply.value);
		// 关闭申请提交
		custMergeApplyMapper.insert(custMergeApply);

		// 更新相似客户任务状态
		EcSimTask simTask = simTaskMapper.selectByPrimaryKey(applyInfo.getTaskId());
		simTask.setTaskState(EnumType.SimTaskState.close_apply.value);// 关闭申请
		simTaskMapper.updateByPrimaryKey(simTask);

		/* 更新相似客户合并拆分轨迹表 */
		EcMergeSplitTrace mergeSplitTrace = new EcMergeSplitTrace();
		mergeSplitTrace.setTaskId(applyInfo.getTaskId());// 任务编号
		mergeSplitTrace.setTraceId(generateTraceId(applyInfo.getTaskId()));// 轨迹ID
		mergeSplitTrace.setMergeSplitAction(EnumType.MergeSplitAction.close_apply.value);// 合并拆分动作
		mergeSplitTrace.setDealUser(execEmployeeId);// 处理人员
		mergeSplitTrace.setDealTime(execDate);// 处理时间
		mergeSplitTrace.setDealConclusion("");// 处理结论
		mergeSplitTrace.setDealOpinion(applyInfo.getCustMergeApply().getApplyOpinion());// 处理意见
		mergeSplitTraceMapper.insert(mergeSplitTrace);

		// 当任务合并审批人字段有值时，给对应的人发送站内提醒
		if (simTask.getApproveUser() != null && !"".equals(simTask.getApproveUser())) {

			// 消息发送对象
			MsgInfo msgInfo = new MsgInfo();
			msgInfo.setMsgCode("RDef_101");// 消息编码 TODO 消息编码不应该直接固定写法如此写法
			msgInfo.setBusiCode(execEmployeeId);// 员工编号

			String params = simTask.getApproveUser() + "," + "[" + applyInfo.getTaskId() + "]";
			msgInfo.setParams(params);// 提醒消息占位符参数
			msgInfo.setChannel(EnumType.sendChannel.remind.value);// 发送渠道：站内提醒
			String msgInfoJson = JsonUtil.toJSONString(msgInfo);
//			/* 给负责审批的业务人员发送站内提醒 */应该使用消息队列MQ解耦处理，避免消息异常导致任务分配失败。工期原因先注释掉
//			restUtil.postForObject(ServiceType.MANAGE, "/crm/manage/msgmng/sendRemindMsg", msgInfoJson, Result.class);
		}
	}

	/**
	 * Desc: 提交关闭任务
	 * 
	 * @return
	 * @author
	 */
	public void applyCloseTask(SimCustTask applyInfo) {

		// 执行人员员工号
		String execEmployeeId = ContextContainer.getContext().getEmployeeId();
		// 执行时间
		Date execDate = DateUtil.getTimestamp(new Date());

		/* 更新任务状态 */
		EcSimTask simTask = simTaskMapper.selectByPrimaryKey(applyInfo.getTaskId());
		simTask.setTaskState(EnumType.SimTaskState.close.value);// 关闭
		simTaskMapper.updateByPrimaryKey(simTask);

		/* 更新用户列表状态 */
		for (SimCustBaseInfo item : applyInfo.getSimCustList()) {
			if (item.isChecked()) {
				EcSimCust simCust = simCustMapper.selectByPrimaryKey(applyInfo.getTaskId(), item.getCustNo());
				simCust.setSimCustState(EnumType.SimCustState.splited.value);// 被合并
				simCustMapper.updateByPrimaryKey(simCust);
			}
		}

		/* 更新相似客户合并申请表 */
		// 根据任务ID获取关闭申请ID
		EcCustMergeApply custMergeApply = new EcCustMergeApply();
		custMergeApply.setTaskId(applyInfo.getTaskId());
		List<EcCustMergeApply> list = custMergeApplyMapper.selectByEntity(custMergeApply);
		if (list != null && list.size() > 0) {
			// 更新相似客户合并申请表
			custMergeApply = list.get(0); // TODO Bug 不能取第一个
			custMergeApply.setApproveUser(execEmployeeId);// 审批人
			custMergeApply.setApproveTime(execDate);// 审批人时间
			custMergeApply.setApproveConclusion(applyInfo.getCustMergeApply().getApproveConclusion());// 审批人结论
			custMergeApply.setApproveSugges(applyInfo.getCustMergeApply().getApproveSugges());// 审批人意见
			custMergeApply.setApplyState(EnumType.SimTaskState.merged.value);// 申请状态
			custMergeApplyMapper.updateByPrimaryKey(custMergeApply);
		}

		/* 更新相似客户合并拆分轨迹表 */
		EcMergeSplitTrace mergeSplitTrace = new EcMergeSplitTrace();
		mergeSplitTrace.setTaskId(applyInfo.getTaskId());// 任务编号
		mergeSplitTrace.setTraceId(generateTraceId(applyInfo.getTaskId()));// 轨迹ID
		mergeSplitTrace.setMergeSplitAction(EnumType.MergeSplitAction.merge_task_clos.value);// 合并拆分动作
		mergeSplitTrace.setDealUser(execEmployeeId);// 处理人员
		mergeSplitTrace.setDealTime(execDate);// 处理时间
		mergeSplitTrace.setDealConclusion(EnumType.ApprovConclusion.pass.value);// 处理结论
		mergeSplitTrace.setDealOpinion(applyInfo.getCustMergeApply().getApproveSugges());// 处理意见
		mergeSplitTraceMapper.insert(mergeSplitTrace);

		// 相似客户列表
		List<SimCustBaseInfo> unMergeCustList = new ArrayList<SimCustBaseInfo>();
		unMergeCustList = applyInfo.getSimCustList();

		/* 不同客户标记表 */
		if (unMergeCustList != null && unMergeCustList.size() > 0) {

			for (int m = 0; m < unMergeCustList.size() - 1; m++) {
				for (int n = m + 1; n < unMergeCustList.size(); n++) {
					EcDiffCust diffCust = new EcDiffCust();
					diffCust.setTaskId(applyInfo.getTaskId());// 任务ID
					diffCust.setSourceCustNo(unMergeCustList.get(m).getCustNo());// 源客户号
					diffCust.setTargetCustNo(unMergeCustList.get(n).getCustNo());// 目标客户号
					diffCust.setMarkUser(execEmployeeId);// 标记人
					diffCust.setMarkReason("");// 标记原因 TODO:数据来源不明
					diffCust.setMarkTime(execDate);// 标记时间
					diffCust.setMarkConclusion("");// 标记结论 TODO:数据来源不明
					diffCustMapper.insert(diffCust);
				}
			}
		}
	}

	/**
	 * Desc: 已合并客户拆分申请
	 * 
	 * @return
	 * @author
	 */
	public void splitApply(SimCustTask applyInfo) {

		// 执行人员员工号
		String execEmployeeId = ContextContainer.getContext().getEmployeeId();
		// 执行时间
		Date execDate = DateUtil.getTimestamp(new Date());

		// 新增相似客户拆分申请表
		EcCustSplitApply custSplitApply = new EcCustSplitApply();
		custSplitApply.setTaskId(applyInfo.getTaskId());
		custSplitApply.setApplyId("" + IdGenerator.getSeqID("SplitApply"));
		custSplitApply.setApplyOpinion(applyInfo.getCustSplitApply().getApplyOpinion());
		custSplitApply.setApplyState(EnumType.SimTaskState.split_apply.value);
		custSplitApply.setApplyTyp(EnumType.TaskApplyTyp.split_apply.value);
		custSplitApply.setApplyTime(execDate);
		custSplitApply.setApplyUser(execEmployeeId);
		custSplitApply.setApplyTyp(EnumType.TaskApplyTyp.split_apply.value);
		// 拆分申请提交
		custSplitApplyMapper.insert(custSplitApply);

		// 更新相似客户任务状态
		EcSimTask simTask = simTaskMapper.selectByPrimaryKey(applyInfo.getTaskId());
		simTask.setTaskState(EnumType.SimTaskState.split_apply.value);// 申请合并
		simTask.setSplitDealUser(execEmployeeId);
		simTaskMapper.updateByPrimaryKey(simTask);

		/* 更新相似客户合并拆分轨迹表 */
		EcMergeSplitTrace mergeSplitTrace = new EcMergeSplitTrace();
		mergeSplitTrace.setTaskId(applyInfo.getTaskId());// 任务编号
		mergeSplitTrace.setTraceId(generateTraceId(applyInfo.getTaskId()));// 轨迹ID
		mergeSplitTrace.setMergeSplitAction(EnumType.MergeSplitAction.split_apply.value);// 合并拆分动作
		mergeSplitTrace.setDealUser(execEmployeeId);// 处理人员
		mergeSplitTrace.setDealTime(execDate);// 处理时间
		mergeSplitTrace.setDealConclusion("");// 处理结论
		mergeSplitTrace.setDealOpinion(applyInfo.getCustSplitApply().getApplyOpinion());// 处理意见
		mergeSplitTraceMapper.insert(mergeSplitTrace);

		// 当任务合并审批人字段有值时，给对应的人发送站内提醒
		if (simTask.getSplitApproveUser() != null && !"".equals(simTask.getSplitApproveUser())) {

			// 消息发送对象
			MsgInfo msgInfo = new MsgInfo();
			msgInfo.setMsgCode("RDef_101");// 消息编码 TODO 消息编码不应该直接固定写法如此写法
			msgInfo.setBusiCode(execEmployeeId);// 员工编号

			String params = simTask.getSplitApproveUser() + "," + "[" + applyInfo.getTaskId() + "]";
			msgInfo.setParams(params);// 提醒消息占位符参数
			msgInfo.setChannel(EnumType.sendChannel.remind.value);// 发送渠道：站内提醒
			String msgInfoJson = JsonUtil.toJSONString(msgInfo);
			/* 给负责审批的业务人员发送站内提醒 */
			//restUtil.postForObject(ServiceType.MANAGE, "/crm/manage/msgmng/sendRemindMsg", msgInfoJson, Result.class);
		}
	}

	/**
	 * Desc: 已合并客户拆分申请审批通过
	 * 
	 * @return
	 * @author
	 */
	public void splitApplyPass(SimCustTask applyInfo) {

		// 执行人员员工号
		String execEmployeeId = ContextContainer.getContext().getEmployeeId();
		// 执行时间
		Date execDate = DateUtil.getTimestamp(new Date());
		List<SimCustBaseInfo> simCustList = applyInfo.getSimCustList();

		/* 更新任务状态 */
		EcSimTask simTask = simTaskMapper.selectByPrimaryKey(applyInfo.getTaskId());
		simTask.setTaskState(EnumType.SimTaskState.split.value);// 拆分
		simTaskMapper.updateByPrimaryKey(simTask);

		/* 更新用户列表状态 */
		for (SimCustBaseInfo item : simCustList) {
			if (item.isChecked()) {
				EcSimCust simCust = simCustMapper.selectByPrimaryKey(applyInfo.getTaskId(), item.getCustNo());
				simCust.setSimCustState(EnumType.SimCustState.splited.value);
				simCustMapper.updateByPrimaryKey(simCust);
			}
		}

		// 获取当初被合并的客户号及合并后生成的客户号
		EcCustMergeRela record = new EcCustMergeRela();
		record.setTaskId(applyInfo.getTaskId());
		List<EcCustMergeRela> custMergeRelaList = custMergeRelaMapper.selectByEntity(record);
		if (custMergeRelaList != null && custMergeRelaList.size() > 0) {

			// 合并产生的用户状态置为“被拆分”
			EcCustPer custPerSplitedUser = custPerMapper.selectByPrimaryKey(custMergeRelaList.get(0).getNewCustNo());
			custPerSplitedUser.setCustStat(EnumType.CustStat.split.value);// 正常
			custPerMapper.updateByPrimaryKey(custPerSplitedUser);

			// 客户号拆分:客户号拆分为原来合并时生成的新客户号置为“被拆分”状态，原来被合并的客户号状态重新置为正常
			for (EcCustMergeRela item : custMergeRelaList) {
				// 被合并的用户状态更新
				EcCustPer custPerTemp = custPerMapper.selectByPrimaryKey(item.getOldCustNo());
				custPerTemp.setCustStat(EnumType.CustStat.normal.value);// 正常
				custPerMapper.updateByPrimaryKey(custPerTemp);

				// 新增客户拆分关系表记录
				EcCustSplitRela custSplitRela = new EcCustSplitRela();
				// 生成splitId
				custSplitRela.setSplitId("" + IdGenerator.getSeqID("CustSplit"));
				// 任务ID
				custSplitRela.setTaskId(applyInfo.getTaskId());
				// 被拆分客户号
				custSplitRela.setOldCustNo(custMergeRelaList.get(0).getNewCustNo());
				// 拆分后客户号
				custSplitRela.setNewCustNo(item.getOldCustNo());
				// 拆分类型
				custSplitRela.setSplitType("");
				// 拆分时间
				custSplitRela.setSplitTime(execDate);
				custSplitRelaMapper.insert(custSplitRela);
			}
		}

		/* 更新相似客户拆分申请表 */
		// 根据任务ID获取拆分申请ID
		EcCustSplitApply custSplitApply = new EcCustSplitApply();
		custSplitApply.setTaskId(applyInfo.getTaskId());
		List<EcCustSplitApply> list = custSplitApplyMapper.selectByEntity(custSplitApply);
		if (list != null && list.size() > 0) {
			// 更新相似客户拆分申请表
			custSplitApply = list.get(0);
			custSplitApply.setApproveUser(execEmployeeId);// 审批人
			custSplitApply.setApproveTime(execDate);// 审批人时间
			custSplitApply.setApproveConclusion(applyInfo.getCustSplitApply().getApproveConclusion());// 审批人结论
			custSplitApply.setApproveOpinion(applyInfo.getCustSplitApply().getApproveOpinion());// 审批人意见
			custSplitApply.setApplyState(EnumType.SimTaskState.split.value);// 申请状态
			custSplitApplyMapper.updateByPrimaryKey(custSplitApply);
		}

		/* 更新相似客户合并拆分轨迹表 */
		EcMergeSplitTrace mergeSplitTrace = new EcMergeSplitTrace();
		mergeSplitTrace.setTaskId(applyInfo.getTaskId());// 任务编号
		mergeSplitTrace.setTraceId(generateTraceId(applyInfo.getTaskId()));// 轨迹ID
		mergeSplitTrace.setMergeSplitAction(EnumType.MergeSplitAction.split_approv_pass.value);// 合并拆分动作：拆分成功
		mergeSplitTrace.setDealUser(execEmployeeId);// 处理人员
		mergeSplitTrace.setDealTime(execDate);// 处理时间
		mergeSplitTrace.setDealConclusion(EnumType.ApprovConclusion.pass.value);// 处理结论
		mergeSplitTrace.setDealOpinion(applyInfo.getCustSplitApply().getApproveOpinion());// 处理意见
		mergeSplitTraceMapper.insert(mergeSplitTrace);

		/* 不同客户标记表记录删除 */
		diffCustMapper.deleteByTaskId(applyInfo.getTaskId());

		/* 生成新的不同客户标记表记录 */
		/* 不同客户标记表 */
		if (simCustList != null && simCustList.size() > 0) {

			for (int m = 0; m < simCustList.size() - 1; m++) {
				for (int n = m + 1; n < simCustList.size(); n++) {
					EcDiffCust diffCust = new EcDiffCust();
					diffCust.setTaskId(applyInfo.getTaskId());// 任务ID
					diffCust.setSourceCustNo(simCustList.get(m).getCustNo());// 源客户号
					diffCust.setTargetCustNo(simCustList.get(n).getCustNo());// 目标客户号
					diffCust.setMarkUser(execEmployeeId);// 标记人
					diffCust.setMarkReason("");// 标记原因 TODO:数据来源不明
					diffCust.setMarkTime(execDate);// 标记时间
					diffCust.setMarkConclusion("");// 标记结论 TODO:数据来源不明
					diffCustMapper.insert(diffCust);
				}
			}
		}
	}

	/**
	 * Desc: 已合并客户拆分申请审批不通过
	 * 
	 * @return
	 * @author
	 */
	public void splitApplyUnPass(SimCustTask applyInfo) {

		// 执行人员员工号
		String execEmployeeId = ContextContainer.getContext().getEmployeeId();
		// 执行时间
		Date execDate = DateUtil.getTimestamp(new Date());

		/* 更新任务状态 */
		EcSimTask simTask = simTaskMapper.selectByPrimaryKey(applyInfo.getTaskId());
		simTask.setTaskState(EnumType.SimTaskState.split_send_back.value);// 合并审批退回
		simTaskMapper.updateByPrimaryKey(simTask);

		/* 更新相似客户合并申请表 申请状态 */
		// 根据任务ID获取合并申请ID
		EcCustSplitApply custSplitApply = new EcCustSplitApply();
		custSplitApply.setTaskId(applyInfo.getTaskId());
		List<EcCustSplitApply> list = custSplitApplyMapper.selectByEntity(custSplitApply);
		if (list != null && list.size() > 0) {
			// 更新相似客户拆分申请表
			custSplitApply = list.get(0);
			custSplitApply.setApplyState(EnumType.SimTaskState.split_send_back.value);// 申请状态 ：退回
			custSplitApply.setApproveConclusion(EnumType.ApprovConclusion.send_back.value);// 审批人结论
			custSplitApply.setApproveOpinion(applyInfo.getCustSplitApply().getApproveOpinion());// 审批人意见
			custSplitApply.setApproveUser(execEmployeeId);// 审批人
			custSplitApply.setApproveTime(execDate);// 审批时间
			custSplitApplyMapper.updateByPrimaryKey(custSplitApply);
		}

		/* 更新相似客户合并拆分轨迹表 */
		EcMergeSplitTrace mergeSplitTrace = new EcMergeSplitTrace();
		mergeSplitTrace.setTaskId(applyInfo.getTaskId());// 任务编号
		mergeSplitTrace.setTraceId(generateTraceId(applyInfo.getTaskId()));// 轨迹ID
		mergeSplitTrace.setMergeSplitAction(EnumType.MergeSplitAction.split_approv_not_pass.value);// 合并拆分动作
		mergeSplitTrace.setDealUser(execEmployeeId);// 处理人员
		mergeSplitTrace.setDealTime(execDate);// 处理时间
		mergeSplitTrace.setDealConclusion(EnumType.ApprovConclusion.send_back.value);// 处理结论
		mergeSplitTrace.setDealOpinion(applyInfo.getCustSplitApply().getApproveOpinion());// 处理意见
		mergeSplitTraceMapper.insert(mergeSplitTrace);

		// 消息发送对象
		MsgInfo msgInfo = new MsgInfo();
		msgInfo.setMsgCode("RDef_101");// 消息编码 TODO 消息编码不应该直接固定写法如此写法
		msgInfo.setBusiCode(execEmployeeId);// 员工编号
		String params = simTask.getSplitDealUser() + "," + "[" + applyInfo.getTaskId() + "]";
		msgInfo.setParams(params);// 提醒消息占位符参数
		msgInfo.setChannel(EnumType.sendChannel.remind.value);// 发送渠道：站内提醒
		String msgInfoJson = JsonUtil.toJSONString(msgInfo);
//		/* 给分配任务的业务人员发送站内提醒 */应该使用消息队列MQ解耦处理，避免消息异常导致任务分配失败。工期原因先注释掉
//		restUtil.postForObject(ServiceType.MANAGE, "/crm/manage/msgmng/sendRemindMsg", msgInfoJson, Result.class);
	}

	/**
	 * Desc: 相似客户任务分配
	 * 
	 * @return
	 * @author
	 */
	public void distributeTask(Map map) {

		// 执行人员员工号
		String execEmployeeId = ContextContainer.getContext().getEmployeeId();
		// 执行时间
		Date execDate = DateUtil.getTimestamp(new Date());

		String taskIds = map.get("taskIds").toString().substring(1, map.get("taskIds").toString().length() - 1);
		String employeeId = map.get("employeeId").toString();

		String[] taskIdArr = taskIds.split(",");

		// 参数字符串，用作发送提醒时的参数
		String params = employeeId + ",";// 添加员工ID

		for (String taskId : taskIdArr) {
			taskId = taskId.substring(1, taskId.length() - 1);
			params += "[" + taskId + "]";// 添加任务id字符串
			EcSimTask simTask = simTaskMapper.selectByPrimaryKey(taskId);
			simTask.setDealUser(employeeId);// 处理人员
			simTask.setTaskState(EnumType.SimTaskState.pending.value);// 任务状态
			simTaskMapper.updateByPrimaryKey(simTask);

			/* 更新相似客户合并拆分轨迹表 */
			EcMergeSplitTrace mergeSplitTrace = new EcMergeSplitTrace();
			mergeSplitTrace.setTaskId(taskId);// 任务编号
			mergeSplitTrace.setTraceId(generateTraceId(taskId));// 轨迹ID
			mergeSplitTrace.setMergeSplitAction(EnumType.MergeSplitAction.distribute.value);// 合并拆分动作
			mergeSplitTrace.setDealUser(execEmployeeId);// 处理人员
			mergeSplitTrace.setDealTime(execDate);// 处理时间
			mergeSplitTrace.setDealConclusion("");// 处理结论
			mergeSplitTrace.setDealOpinion("任务分配");// 处理意见
			mergeSplitTraceMapper.insert(mergeSplitTrace);
		}

		// 消息发送对象
		MsgInfo msgInfo = new MsgInfo();
		msgInfo.setMsgCode("RDef_101");// 消息编码 TODO 消息编码不应该如此写法
		msgInfo.setBusiCode(employeeId);// 员工编号
		msgInfo.setParams(params);// 提醒消息占位符参数
		msgInfo.setChannel(EnumType.sendChannel.remind.value);// 发送渠道：站内提醒
		String msgInfoJson = JsonUtil.toJSONString(msgInfo);
		/* 给分配任务的业务人员发送站内提醒 */
		//delete by linbangbo 20190909 begin 应该使用消息队列MQ解耦处理，避免消息异常导致任务分配失败。工期原因先注释掉
		//restUtil.postForObject(ServiceType.MANAGE, "/crm/manage/msgmng/sendRemindMsg", msgInfoJson, Result.class);
	}

	/**
	 * Desc: 根据任务编号生成轨迹ID
	 * 
	 * @return
	 * @author
	 */
	private int generateTraceId(String taskId) {
		EcMergeSplitTrace record = new EcMergeSplitTrace();
		record.setTaskId(taskId);
		List<EcMergeSplitTrace> recordList = mergeSplitTraceMapper.selectByEntity(record);
		if (recordList != null && recordList.size() > 0) {
			Integer max = 0;
			for (EcMergeSplitTrace item : recordList) {
				if (item.getTraceId() > max) {
					max = item.getTraceId();
				}
			}
			return max + 1;
		}
		return 1;
	}

	/**
	 * Desc: 相似客户合并任务审批申领
	 * 
	 * @return
	 * @author
	 */
	public void receiveMergeTask(String taskId, String taskState) {

		EcSimTask simTask = new EcSimTask();
		simTask.setTaskId(taskId);
		simTask = simTaskMapper.selectByPrimaryKey(taskId);

		if (taskState.equals(EnumType.SimTaskState.merge_apply.value)
				|| taskState.equals(EnumType.SimTaskState.close_apply.value)) {
			simTask.setApproveUser(ContextContainer.getContext().getEmployeeId());
		} else if (taskState.equals(EnumType.SimTaskState.split_apply.value)) {
			simTask.setSplitApproveUser(ContextContainer.getContext().getEmployeeId());
		}

		simTaskMapper.updateByPrimaryKey(simTask);
	}

	public void applyMergePasss(SimCustTask applyInfo)throws Exception{
		// 执行时间
		Date execDate = DateUtil.getTimestamp(new Date());
		// 新增相似客户合并申请表
		EcCustMergeApply custMergeApply = new EcCustMergeApply();
		custMergeApply.setTaskId(applyInfo.getTaskId());
		custMergeApply.setApplyId("" + IdGenerator.getSeqID("MergeApply"));
		//custMergeApply.setApplyOpinion(applyInfo.getCustMergeApply().getApplyOpinion());
		custMergeApply.setApplyState(EnumType.SimTaskState.merge_apply.value);
		custMergeApply.setApplyTime(execDate);
		custMergeApply.setApplyUser("admin");
		custMergeApply.setApplyTyp(EnumType.TaskApplyTyp.merge_apply.value);
		// 合并申请提交
		custMergeApplyMapper.insert(custMergeApply);

		List<SimCustBaseInfo> custList = applyInfo.getSimCustList();

		/* 更新任务状态 */
		EcSimTask simTask = simTaskMapper.selectByPrimaryKey(applyInfo.getTaskId());
		simTask.setTaskState(EnumType.SimTaskState.merged.value);// 合并
		simTaskMapper.updateByPrimaryKey(simTask);

		/* 更新用户列表状态 */
		for (SimCustBaseInfo item : custList) {
				EcSimCust simCust = simCustMapper.selectByPrimaryKey(applyInfo.getTaskId(), item.getCustNo());
				simCust.setSimCustState(EnumType.CustStat.merged.value);// 被合并
				simCustMapper.updateByPrimaryKey(simCust);
		}
		/* 更新相似客户合并拆分轨迹表 */
		EcMergeSplitTrace mergeSplitTrace = new EcMergeSplitTrace();
		mergeSplitTrace.setTaskId(applyInfo.getTaskId());// 任务编号
		mergeSplitTrace.setTraceId(generateTraceId(applyInfo.getTaskId()));// 轨迹ID
		mergeSplitTrace.setMergeSplitAction(EnumType.MergeSplitAction.merge_apply.value);// 合并拆分动作
		mergeSplitTrace.setDealUser("admin");// 处理人员
		mergeSplitTrace.setDealTime(execDate);// 处理时间
		mergeSplitTrace.setDealConclusion("");// 处理结论
		mergeSplitTrace.setDealOpinion("");// 处理意见
		mergeSplitTraceMapper.insert(mergeSplitTrace);
		/* 更新相似客户合并申请表 */
		// 根据任务ID获取合并申请ID
		EcCustMergeApply custMergeApplys = new EcCustMergeApply();
		custMergeApplys.setTaskId(applyInfo.getTaskId());
		List<EcCustMergeApply> list = custMergeApplyMapper.selectByEntity(custMergeApplys);
		if (list != null && list.size() > 0) {
			// 更新相似客户合并申请表
			custMergeApplys = list.get(0);
			custMergeApplys.setApproveUser("admin");// 审批人
			custMergeApplys.setApproveTime(execDate);// 审批人时间
			//custMergeApplys.setApproveConclusion(applyInfo.getCustMergeApply().getApproveConclusion());// 审批人结论
			//custMergeApplys.setApproveSugges(applyInfo.getCustMergeApply().getApproveSugges());// 审批人意见
			custMergeApplys.setApplyState(EnumType.SimTaskState.merged.value);// 申请状态
			custMergeApplyMapper.updateByPrimaryKey(custMergeApplys);
		}

		/* 更新相似客户合并拆分轨迹表 */
		mergeSplitTrace = new EcMergeSplitTrace();
		mergeSplitTrace.setTaskId(applyInfo.getTaskId());// 任务编号
		mergeSplitTrace.setTraceId(generateTraceId(applyInfo.getTaskId()));// 轨迹ID
		mergeSplitTrace.setMergeSplitAction(EnumType.MergeSplitAction.merge_approv_pass.value);// 合并拆分动作
		mergeSplitTrace.setDealUser("admin");// 处理人员
		mergeSplitTrace.setDealTime(execDate);// 处理时间
		mergeSplitTrace.setDealConclusion(EnumType.ApprovConclusion.pass.value);// 处理结论
		mergeSplitTrace.setDealOpinion(EnumType.SimTaskState.merged.name);// 处理意见
		mergeSplitTraceMapper.insert(mergeSplitTrace);

		// 相似客户列表
		List<SimCustBaseInfo> simCustList = applyInfo.getSimCustList();

		// 合并列表
		List<SimCustBaseInfo> mergeCustList = new ArrayList<SimCustBaseInfo>();
		for (SimCustBaseInfo simCust : simCustList) {
				mergeCustList.add(simCust);
		}

		/* 客户合并 */
		String newCustNo = custBaseInfoMerge(mergeCustList);

		// 新增客户合并关系表记录
		for (SimCustBaseInfo item : mergeCustList) {
			EcCustMergeRela custMergeRela = new EcCustMergeRela();
			custMergeRela.setMergeId("" + IdGenerator.getSeqID("CustMerge"));// 合并ID
			custMergeRela.setTaskId(applyInfo.getTaskId());// 任务编号
			// 被合并客户
			custMergeRela.setOldCustNo(item.getCustNo());
			// 合并后客户
			custMergeRela.setNewCustNo(newCustNo);
			// 合并类型
			custMergeRela.setMergeType(EnumType.CustMergeTyp.sameMerge.value);
			// 合并时间
			custMergeRela.setMergeTime(execDate);
			custMergeRelaMapper.insert(custMergeRela);
		}
		/* 被合并客户状态置为无效 客户状态置为:合并 */
		for (SimCustBaseInfo item : mergeCustList) {
			EcCustPer custPer = custPerMapper.selectByPrimaryKey(item.getCustNo());
			custPer.setCustStat(EnumType.CustStat.merged.value);
			custPerMapper.updateByPrimaryKey(custPer);
		}
	}


}
