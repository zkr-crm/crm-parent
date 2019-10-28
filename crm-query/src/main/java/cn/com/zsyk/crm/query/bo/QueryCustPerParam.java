package cn.com.zsyk.crm.query.bo;

import java.math.BigDecimal;

public class QueryCustPerParam {
	private String custNo;//客户编号
	private String custName;//客户名称
	private String certTyp;//证件类型
	private String certNo;//证件编号
	private String sex;//性别
	private String custTyp;//客户类型
	private String telephone;//手机号码
	private Integer beginAge;//年龄区间
	private Integer endAge;//年龄区间
	private String product;
	private String orderNo;//手机号重复次数
	private String mark;//手机号重复次数符号 0等于 1小于 2大于 3小于等于 4 大于等于
	private String bNo;//保单号
	private String beginPolicy;//保险起期区间
	private String endPolicy;//保险起期区间
	private String beginsPolicy;//保险止期区间
	private String endsPolicy;//保险止期区间
	private String companycode;//业务渠道
	private String custAgent;//客户表默认业务员
	private String type;//业务员
	private String  beginAmount;//保费区间
	private String endAmount;//保费区间
	private String tag;//客户标签
	private String group;//客群
	private String eventType;//事件类型
	private String beginEvent;//事件区间
	private String endEvent;//事件区间
	private String prov;//省
	private String urban;//市
	private String area;//区
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

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
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

	public String getBeginAmount() {
		return beginAmount;
	}

	public void setBeginAmount(String beginAmount) {
		this.beginAmount = beginAmount;
	}

	public String getEndAmount() {
		return endAmount;
	}

	public void setEndAmount(String endAmount) {
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

	public String getBeginPolicy() {
		return beginPolicy;
	}

	public void setBeginPolicy(String beginPolicy) {
		this.beginPolicy = beginPolicy;
	}

	public String getEndPolicy() {
		return endPolicy;
	}

	public void setEndPolicy(String endPolicy) {
		this.endPolicy = endPolicy;
	}

	public String getBeginsPolicy() {
		return beginsPolicy;
	}

	public void setBeginsPolicy(String beginsPolicy) {
		this.beginsPolicy = beginsPolicy;
	}

	public String getEndsPolicy() {
		return endsPolicy;
	}

	public void setEndsPolicy(String endsPolicy) {
		this.endsPolicy = endsPolicy;
	}

	public String getCompanycode() {
		return companycode;
	}

	public void setCompanycode(String companycode) {
		this.companycode = companycode;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getBeginEvent() {
		return beginEvent;
	}

	public void setBeginEvent(String beginEvent) {
		this.beginEvent = beginEvent;
	}

	public String getEndEvent() {
		return endEvent;
	}

	public void setEndEvent(String endEvent) {
		this.endEvent = endEvent;
	}

	public String getProv() {
		return prov;
	}

	public void setProv(String prov) {
		this.prov = prov;
	}

	public String getUrban() {
		return urban;
	}

	public void setUrban(String urban) {
		this.urban = urban;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
}
