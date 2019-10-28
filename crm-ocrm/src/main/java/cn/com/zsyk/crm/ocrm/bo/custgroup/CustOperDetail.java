package cn.com.zsyk.crm.ocrm.bo.custgroup;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户运营任务详细信息bean
 * 
 * @author
 *
 */
public class CustOperDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	// 任务ID
	private String groupOperId;
	// 任务名称
	private String groupOperName;
	// 任务描述
	private String groupTaskDesc;
	// 创建时间
	private Date establishTime;
	// 截止时间
	private Date endTime;
	// 客户号
	private String custNo;
	// 任务类型
	private String groupOperType;
	// 客户数量
	private Integer operNum;
	// 客户群组Id
	private String groupId;
	// 群组类型
	private String groupType;
	// 快照ID
	private String snapshotId;
	// 群组任务状态
	private String groupTaskStatus;
	// ********************************************
	// 客户姓名
	private String custNam;
	// 客户类型
	private String custType;
	// 证件类型
	private String certTyp;
	// 证件号码
	private String certNo;
	// 客户电话1
	private String phoneNo1;
	// 负责人
	private String custAgent;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupOperId() {
		return groupOperId;
	}

	public void setGroupOperId(String groupOperId) {
		this.groupOperId = groupOperId;
	}

	public String getGroupOperName() {
		return groupOperName;
	}

	public void setGroupOperName(String groupOperName) {
		this.groupOperName = groupOperName;
	}

	public String getGroupTaskDesc() {
		return groupTaskDesc;
	}

	public void setGroupTaskDesc(String groupTaskDesc) {
		this.groupTaskDesc = groupTaskDesc;
	}

	public Date getEstablishTime() {
		return establishTime;
	}

	public void setEstablishTime(Date establishTime) {
		this.establishTime = establishTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getGroupOperType() {
		return groupOperType;
	}

	public void setGroupOperType(String groupOperType) {
		this.groupOperType = groupOperType;
	}

	public Integer getOperNum() {
		return operNum;
	}

	public void setOperNum(Integer operNum) {
		this.operNum = operNum;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public String getSnapshotId() {
		return snapshotId;
	}

	public void setSnapshotId(String snapshotId) {
		this.snapshotId = snapshotId;
	}

	public String getGroupTaskStatus() {
		return groupTaskStatus;
	}

	public void setGroupTaskStatus(String groupTaskStatus) {
		this.groupTaskStatus = groupTaskStatus;
	}

	public String getCustNam() {
		return custNam;
	}

	public void setCustNam(String custNam) {
		this.custNam = custNam;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getCertTyp() {
		return certTyp;
	}

	public void setCertTyp(String certTyp) {
		this.certTyp = certTyp;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getPhoneNo1() {
		return phoneNo1;
	}

	public void setPhoneNo1(String phoneNo1) {
		this.phoneNo1 = phoneNo1;
	}

	public String getCustAgent() {
		return custAgent;
	}

	public void setCustAgent(String custAgent) {
		this.custAgent = custAgent;
	}
}
