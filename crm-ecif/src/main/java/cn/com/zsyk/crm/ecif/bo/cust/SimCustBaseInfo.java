package cn.com.zsyk.crm.ecif.bo.cust;

import java.util.Date;

public class SimCustBaseInfo {

	// 任务编号
	// private String taskId;
	// 客户号
	private String custNo;
	// 客户姓名
	private String custNam;
	// 性别
	private String sex;
	// 出生日期
	private Date birthDate;
	// 证件类型
	private String certTyp;
	// 证件号码
	private String certNo;
	// 电话号码
	private String phoneNumber;
	// 住宅电话
	private String homeTel;
	// 公司电话
	private String officeTel;
	//客户类
	private String custTyp;
	//创建日期
	private String createDate;
	//出生地
	private String birthPlace;
	// 电话号码1
	private String phoneNo1;
	// 电话号码2
	private String phoneNo2;
	// 电话号码3
	private String phoneNo3;
	//客户经理
	private String custAgent;
	// 家庭电话1
	private String telephone1;
	// 家庭电话2
	private String telephone2;
	//地址
	private String detAddr;

	public String getDetAddr() {
		return detAddr;
	}

	public void setDetAddr(String detAddr) {
		this.detAddr = detAddr;
	}

	// 是否选中标志
	private boolean isChecked;

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

	public String getTelephone1() {
		return telephone1;
	}

	public void setTelephone1(String telephone1) {
		this.telephone1 = telephone1;
	}

	public String getTelephone2() {
		return telephone2;
	}

	public void setTelephone2(String telephone2) {
		this.telephone2 = telephone2;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getHomeTel() {
		return homeTel;
	}

	public void setHomeTel(String homeTel) {
		this.homeTel = homeTel;
	}

	public String getOfficeTel() {
		return officeTel;
	}

	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	public String getCustTyp() {
		return custTyp;
	}

	public void setCustTyp(String custTyp) {
		this.custTyp = custTyp;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getCustAgent() {
		return custAgent;
	}

	public void setCustAgent(String custAgent) {
		this.custAgent = custAgent;
	}
	
}
