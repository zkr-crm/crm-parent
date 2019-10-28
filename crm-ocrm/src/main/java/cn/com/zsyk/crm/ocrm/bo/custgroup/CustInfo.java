package cn.com.zsyk.crm.ocrm.bo.custgroup;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import cn.com.zsyk.crm.ocrm.entity.OcCustGrp;
import cn.com.zsyk.crm.ocrm.entity.OcCustGrpMember;

/**
 * 客户群组信息
 * 
 * @author
 *
 */
public class CustInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	// 客户群组Id
	private String groupId;
	// 客户号
	private String custNo;
	// 客户姓名
	private String custNam;
	// 客户类型
	private String custType;
	// 群组类型
	private String groupType;
	// 加入时间
	private Date addTime;

	// 证件类型
	private String certTyp;
	// 证件号码
	private String certNo;
	// 客户电话1
	private String phoneNo1;
	// 客户电话2
	private String phoneNo2;
	// 客户电话3
	private String phoneNo3;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
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

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
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

	public String getPhoneNo2() {
		return phoneNo2;
	}

	public void setPhoneNo2(String phoneNo2) {
		this.phoneNo2 = phoneNo2;
	}

	public String getPhoneNo3() {
		return phoneNo3;
	}

	public void setPhoneNo3(String phoneNo3) {
		this.phoneNo3 = phoneNo3;
	}

}
