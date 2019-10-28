package cn.com.zsyk.crm.ecif.bo.cust;

import java.util.ArrayList;
import java.util.List;

import cn.com.zsyk.crm.ecif.entity.EcCustMergeApply;
import cn.com.zsyk.crm.ecif.entity.EcCustSplitApply;

public class SimCustTask {

	// 任务编号
	private String taskId;

	// 规则编号
	private Integer ruleId;

	// 相似客户合并申请
	private EcCustMergeApply custMergeApply = null;

	// 相似客户拆分申请
	private EcCustSplitApply custSplitApply = null;

	// 相似客户信息列表
	List<SimCustBaseInfo> simCustList = new ArrayList<SimCustBaseInfo>();

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public Integer getRuleId() {
		return ruleId;
	}

	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}

	public EcCustMergeApply getCustMergeApply() {
		return custMergeApply;
	}

	public void setCustMergeApply(EcCustMergeApply custMergeApply) {
		this.custMergeApply = custMergeApply;
	}

	public EcCustSplitApply getCustSplitApply() {
		return custSplitApply;
	}

	public void setCustSplitApply(EcCustSplitApply custSplitApply) {
		this.custSplitApply = custSplitApply;
	}

	public List<SimCustBaseInfo> getSimCustList() {
		return simCustList;
	}

	public void setSimCustList(List<SimCustBaseInfo> simCustList) {
		this.simCustList = simCustList;
	}
}
