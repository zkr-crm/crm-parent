package cn.com.zsyk.crm.ecif.entity;

import java.io.Serializable;
import java.util.List;

import cn.com.zsyk.crm.ecif.bo.cust.PerCustBaseInfo;

public class EcSimTaskDetil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 任务ID
	 */
	private String taskId;

	/**
	 * 相似客户任务表
	 */
	private EcSimTask simTask = null;

	/**
	 * 相似客户判断规则表
	 */
	private EcSimCustRule simCustRule = null;

	/**
	 * 相似客户信息表
	 */
	private List<EcSimCust> simCustList = null;

	/**
	 * 相似客户详细信息
	 */
	private List<PerCustBaseInfo> simCustDetilList = null;

	/**
	 * 相似客户合并拆分轨迹表
	 */
	private List<EcMergeSplitTrace> mergeSplitTraceList = null;

	/**
	 * 相似客户拆分申请表
	 */
	private List<EcCustSplitApply> custSplitApplyList = null;

	/**
	 * 相似客户合并申请表
	 */
	private List<EcCustMergeApply> custMergeApplyList = null;

	/**
	 * 客户合并关系表
	 */
	private List<EcCustMergeRela> custMergeRelaList = null;

	public List<EcCustMergeRela> getCustMergeRelaList() {
		return custMergeRelaList;
	}

	public void setCustMergeRelaList(List<EcCustMergeRela> custMergeRelaList) {
		this.custMergeRelaList = custMergeRelaList;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public EcSimTask getSimTask() {
		return simTask;
	}

	public void setSimTask(EcSimTask simTask) {
		this.simTask = simTask;
	}

	public EcSimCustRule getSimCustRule() {
		return simCustRule;
	}

	public void setSimCustRule(EcSimCustRule simCustRule) {
		this.simCustRule = simCustRule;
	}

	public List<EcSimCust> getSimCustList() {
		return simCustList;
	}

	public void setSimCustList(List<EcSimCust> simCustList) {
		this.simCustList = simCustList;
	}

	public List<PerCustBaseInfo> getSimCustDetilList() {
		return simCustDetilList;
	}

	public void setSimCustDetilList(List<PerCustBaseInfo> simCustDetilList) {
		this.simCustDetilList = simCustDetilList;
	}

	public List<EcMergeSplitTrace> getMergeSplitTraceList() {
		return mergeSplitTraceList;
	}

	public void setMergeSplitTraceList(List<EcMergeSplitTrace> mergeSplitTraceList) {
		this.mergeSplitTraceList = mergeSplitTraceList;
	}

	public List<EcCustSplitApply> getCustSplitApplyList() {
		return custSplitApplyList;
	}

	public void setCustSplitApplyList(List<EcCustSplitApply> custSplitApplyList) {
		this.custSplitApplyList = custSplitApplyList;
	}

	public List<EcCustMergeApply> getCustMergeApplyList() {
		return custMergeApplyList;
	}

	public void setCustMergeApplyList(List<EcCustMergeApply> custMergeApplyList) {
		this.custMergeApplyList = custMergeApplyList;
	}
}