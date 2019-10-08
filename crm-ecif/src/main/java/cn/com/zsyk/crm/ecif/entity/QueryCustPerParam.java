package cn.com.zsyk.crm.ecif.entity;

import java.math.BigDecimal;

public class QueryCustPerParam {
	private String custNo;
	private String custName;
	private String certTyp;
	private String certNo;
	private String custTyp;
	private String telephone;
	private Integer beginAge;
	private Integer endAge;
	private String product;
	private String orderNo;
	private String bNo;
	private String custAgent;
	private String type;
	private BigDecimal  beginAmount;
	private BigDecimal endAmount;
	private String creditGrade;
	private String blacklistFlg;
	private String staticCust;
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
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
	public String getCustTyp() {
		return custTyp;
	}
	public void setCustTyp(String custTyp) {
		this.custTyp = custTyp;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Integer getBeginAge() {
		return beginAge;
	}
	public void setBeginAge(Integer beginAge) {
		this.beginAge = beginAge;
	}
	public Integer getEndAge() {
		return endAge;
	}
	public void setEndAge(Integer endAge) {
		this.endAge = endAge;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getbNo() {
		return bNo;
	}
	public void setbNo(String bNo) {
		this.bNo = bNo;
	}
	public String getCustAgent() {
		return custAgent;
	}
	public void setCustAgent(String custAgent) {
		this.custAgent = custAgent;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public BigDecimal getBeginAmount() {
		return beginAmount;
	}
	public void setBeginAmount(BigDecimal beginAmount) {
		this.beginAmount = beginAmount;
	}
	public BigDecimal getEndAmount() {
		return endAmount;
	}
	public void setEndAmount(BigDecimal endAmount) {
		this.endAmount = endAmount;
	}
	public String getCreditGrade() {
		return creditGrade;
	}
	public void setCreditGrade(String creditGrade) {
		this.creditGrade = creditGrade;
	}
	public String getBlacklistFlg() {
		return blacklistFlg;
	}
	public void setBlacklistFlg(String blacklistFlg) {
		this.blacklistFlg = blacklistFlg;
	}
	public String getStaticCust() {
		return staticCust;
	}
	public void setStaticCust(String staticCust) {
		this.staticCust = staticCust;
	}
	
}
