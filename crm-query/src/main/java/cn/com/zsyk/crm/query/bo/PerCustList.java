package cn.com.zsyk.crm.query.bo;



import cn.com.zsyk.crm.query.entity.EcCustTag;

import java.util.List;

public class PerCustList {
	// 客户号
	private String custNo;
	// 客户类型
	private String custTyp;
	// 客户名称
	private String custName;
	// 客户号码
	private String phoneNumber;
	// 标签
	private List<EcCustTag> custTag;
	// 客户来源
	private String custSource;
	// 客户阶段
	private String custPhase;
	// 客户经理
	private String custAgent;
	private String custAgentNam;
    private String custTagNamStr;

	// 下次跟进时间
	private String nextFollowUpTime;
	private String sex;
	// 总消费金额
	// 总消费次数
	// 备注
	private String remark;
	// 创建人
	private String createUser;
	
	//证件类型
	private String certTyp;
	//证件号码
	private String certNo;
	private String birthDate;
	private String custTagCd;
	private String regiDate;
	private String regiReason;
	
    //滞留时间、待分配原因-客户分配
	private Integer dateDiff;
	private String retentionReason;
	private String custStat;

	public Integer getDateDiff() {
		return dateDiff;
	}

	public void setDateDiff(Integer dateDiff) {
		this.dateDiff = dateDiff;
	}

	public String getRetentionReason() {
		return retentionReason;
	}

	public void setRetentionReason(String retentionReason) {
		this.retentionReason = retentionReason;
	}

	public String getRegiDate() {
		return regiDate;
	}

	public void setRegiDate(String regiDate) {
		this.regiDate = regiDate;
	}

	public String getRegiReason() {
		return regiReason;
	}

	public void setRegiReason(String regiReason) {
		this.regiReason = regiReason;
	}

	
	public String getBirthDate() {
		return birthDate;
	}

	
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getCustTagCd() {
		return custTagCd;
	}

	public void setCustTagCd(String custTagCd) {
		this.custTagCd = custTagCd;
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

	/**
	 * Desc: 
	 * @return 
	 * @author
	 */
	public List<EcCustTag> getCustTag() {
		return custTag;
	}

	/**
	 * Desc: 
	 * @return 
	 * @author
	 */
	public String getCustNo() {
		return custNo;
	}


	
	/**
	 * Desc: 
	 * custNo
	 * @author
	 */
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}


	
	/**
	 * Desc: 
	 * @return 
	 * @author
	 */
	public String getCustTyp() {
		return custTyp;
	}


	
	/**
	 * Desc: 
	 * custTyp
	 * @author
	 */
	public void setCustTyp(String custTyp) {
		this.custTyp = custTyp;
	}

	/**
	 * Desc: 
	 * custTag
	 * @author
	 */
	public void setCustTag(List<EcCustTag> custTag) {
		this.custTag = custTag;
	}

	/**
	 * Desc:
	 * @return
	 * @author
	 */
	public String getCustName() {
		return custName;
	}

	/**
	 * Desc: entName
	 * @author
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}

	/**
	 * Desc:
	 * @return
	 * @author
	 */
	public String getCustSource() {
		return custSource;
	}

	/**
	 * Desc: custSource
	 * @author
	 */
	public void setCustSource(String custSource) {
		this.custSource = custSource;
	}

	/**
	 * Desc:
	 * @return
	 * @author
	 */
	public String getCustPhase() {
		return custPhase;
	}

	/**
	 * Desc: custPhase
	 * @author
	 */
	public void setCustPhase(String custPhase) {
		this.custPhase = custPhase;
	}

	/**
	 * Desc:
	 * @return
	 * @author
	 */
	public String getCustAgent() {
		return custAgent;
	}

	/**
	 * Desc: custAgent
	 * @author
	 */
	public void setCustAgent(String custAgent) {
		this.custAgent = custAgent;
	}

	/**
	 * Desc:
	 * @return
	 * @author
	 */
	public String getNextFollowUpTime() {
		return nextFollowUpTime;
	}

	/**
	 * Desc: nextFollowUpTime
	 * @author
	 */
	public void setNextFollowUpTime(String nextFollowUpTime) {
		this.nextFollowUpTime = nextFollowUpTime;
	}

	/**
	 * Desc:
	 * @return
	 * @author
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * Desc: remark
	 * @author
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * Desc:
	 * @return
	 * @author
	 */
	public String getCreateUser() {
		return createUser;
	}

	/**
	 * Desc: createUser
	 * @author
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	
	/**
	 * Desc: 
	 * @return 
	 * @author
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	
	/**
	 * Desc: 
	 * phoneNumber
	 * @author
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
	public String getSex() {
		return sex;
	}

	
	
	public String getCustAgentNam() {
		return custAgentNam;
	}

	
	public void setCustAgentNam(String custAgentNam) {
		this.custAgentNam = custAgentNam;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCustTagNamStr() {
		return custTagNamStr;
	}

	public void setCustTagNamStr(String custTagNamStr) {
		this.custTagNamStr = custTagNamStr;
	}

	public String getCustStat() {
		return custStat;
	}

	public void setCustStat(String custStat) {
		this.custStat = custStat;
	}
}
