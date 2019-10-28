package cn.com.zsyk.crm.ecif.bo.cust;

import cn.com.zsyk.crm.ecif.entity.EcContactAddress;
import cn.com.zsyk.crm.ecif.entity.EcCustPhone;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class PerCustBaseInfo {
	//合并标记
	private String mergeMark;

	public String getMergeMark() {
		return mergeMark;
	}

	public void setMergeMark(String mergeMark) {
		this.mergeMark = mergeMark;
	}

	// 客户号
	private String custNo;
	// 客户姓名
	private String custName;
	// 性别
	private String sex;
	// 出生日期
	private Date birthDate;
	// 证件类型
	private String certTyp;
	// 证件号码
	private String certNo;
	// 证件有效期 add lijiangcheng 2019-07-30
	private Date certDate;
	// 电话号码
	private String phoneNumber;
	//黑名单
	private String blacklistFlg;
	// 客户经理
	private String custAgent;
	// 客户类型1
	private String custTyp;
	// 客户来源
	private String custSource;
	// 其他来源 add lijiangcheng 2019-07-30
	private String otherSource;
	// 是否为重点
	private String keyCustFlg;
	// 下次跟进时间
	private String nextFollowUpTm;

	// 国籍
	private String nationality;
	// 婚姻状况
	private String marrigeSts;
	// 文化程度
	private String eduDegree;
	// 毕业院校
	private String schoolNam;
	// 职业
	private String position;
	// 所属行业
	private String trade;
	// 工作单位
	private String unitNam;
	// 居住地址类型
	private String liveAddrTyp;
	// 居住省
	private String liveProvince;
	// 居住市
	private String liveCity;
	// 居住区县
	private String liveCounty;
	// 居住街道
	private String liveStreet;
	// 居住邮编
	private String livePostcode;
	// 居住省市县地址名称
	private String  liveAddrNam;

	// 单位地址类型
	private String unitAddrTyp;
	// 单位省
	private String unitProvince;
	// 单位市
	private String  unitCity;
	// 单位区县
	private String  unitCounty;
	// 单位街道
	private String  unitStreet;
	// 单位邮编
	private String  unitPostcode;
	// 单位省市县地址名称
	private String  unitAddrNam;
	// 政治面貌
	private String politSts;
	// 民族
	private String nation;
	
	// 血型 
	private String bloodTyp;
	// 身高
	private BigDecimal height;
	// 体重
	private BigDecimal weight;
	// 是否有房产 - 个人资产信息表
	private String whtHouse;
	// 是否有车 
	private String whtCar;
	// 车牌号
	private String licensePlate;
	// 年收入 
	private BigDecimal annualIncome;
	// 健康情况 - 个人风险信息表
	private String healthCondition;
	// 是否有医保
	private String whtMedInsur;
    // 习惯嗜好
	private String habit;
	//地址集合信息 add lijiangcheng 2019-07-30
	private List<EcContactAddress> addrList;
	//其他电话集合信息 add lijiangcheng 2019-07-30
	private List<EcCustPhone> phoneList;
	public String getBloodTyp() {
		return bloodTyp;
	}


	public void setBloodTyp(String bloodTyp) {
		this.bloodTyp = bloodTyp;
	}


	public BigDecimal getHeight() {
		return height;
	}


	public void setHeight(BigDecimal height) {
		this.height = height;
	}


	public BigDecimal getWeight() {
		return weight;
	}


	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}


	public String getWhtHouse() {
		return whtHouse;
	}


	public void setWhtHouse(String whtHouse) {
		this.whtHouse = whtHouse;
	}


	public String getWhtCar() {
		return whtCar;
	}


	public void setWhtCar(String whtCar) {
		this.whtCar = whtCar;
	}


	public String getLicensePlate() {
		return licensePlate;
	}


	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}


	public BigDecimal getAnnualIncome() {
		return annualIncome;
	}


	public void setAnnualIncome(BigDecimal annualIncome) {
		this.annualIncome = annualIncome;
	}


	public String getHealthCondition() {
		return healthCondition;
	}


	public void setHealthCondition(String healthCondition) {
		this.healthCondition = healthCondition;
	}


	public String getWhtMedInsur() {
		return whtMedInsur;
	}


	public void setWhtMedInsur(String whtMedInsur) {
		this.whtMedInsur = whtMedInsur;
	}


	public String getHabit() {
		return habit;
	}


	public void setHabit(String habit) {
		this.habit = habit;
	}

	public List<EcContactAddress> getAddrList() {
		return addrList;
	}

	public void setAddrList(List<EcContactAddress> addrList) {
		this.addrList = addrList;
	}

	public List<EcCustPhone> getPhoneList() {
		return phoneList;
	}

	public void setPhoneList(List<EcCustPhone> phoneList) {
		this.phoneList = phoneList;
	}

	public String getEntNam() {
		return entNam;
	}


	public void setEntNam(String entNam) {
		this.entNam = entNam;
	}

	//企业名称
	private String entNam;;

	// 其他手机
	private String otherTel;
	// 住宅电话
	private String homeTel;
	// 公司电话
	private String officeTel;
	// 传真
	private String fax;

	// 微信
	private String wechatNo;
	// QQ
	private String qq;
	// 微博
	private String microblog;
	// 支付宝
	private String alipay;
	// 电子邮箱
	private String emailAddr;

	// 头像URL
	private MultipartFile perIconUrl;

    private String perIconSmlBlob;
    private String perIconBigBlob;
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

	public Date getCertDate() {
		return certDate;
	}

	public void setCertDate(Date certDate) {
		this.certDate = certDate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getBlacklistFlg() {
		return blacklistFlg;
	}

	public void setBlacklistFlg(String blacklistFlg) {
		this.blacklistFlg = blacklistFlg;
	}

	public String getCustAgent() {
		return custAgent;
	}

	
	public void setCustAgent(String custAgent) {
		this.custAgent = custAgent;
	}

	
	public String getCustTyp() {
		return custTyp;
	}

	
	public void setCustTyp(String custTyp) {
		this.custTyp = custTyp;
	}

	public String getOtherSource() {
		return otherSource;
	}

	public void setOtherSource(String otherSource) {
		this.otherSource = otherSource;
	}

	public String getCustSource() {
		return custSource;
	}

	
	public void setCustSource(String custSource) {
		this.custSource = custSource;
	}

	
	public String getKeyCustFlg() {
		return keyCustFlg;
	}

	
	public void setKeyCustFlg(String keyCustFlg) {
		this.keyCustFlg = keyCustFlg;
	}

	public String getNextFollowUpTm() {
		return nextFollowUpTm;
	}

	public void setNextFollowUpTm(String nextFollowUpTm) {
		this.nextFollowUpTm = nextFollowUpTm;
	}


	public String getNationality() {
		return nationality;
	}

	
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	
	public String getMarrigeSts() {
		return marrigeSts;
	}

	
	public void setMarrigeSts(String marrigeSts) {
		this.marrigeSts = marrigeSts;
	}

	
	public String getEduDegree() {
		return eduDegree;
	}

	
	public void setEduDegree(String eduDegree) {
		this.eduDegree = eduDegree;
	}

	
	public String getTrade() {
		return trade;
	}

	
	public void setTrade(String trade) {
		this.trade = trade;
	}

	
	
	public String getPosition() {
		return position;
	}


	
	public void setPosition(String position) {
		this.position = position;
	}


	public String getUnitNam() {
		return unitNam;
	}

	
	public void setUnitNam(String unitNam) {
		this.unitNam = unitNam;
	}

	
	public String getLiveAddrTyp() {
		return liveAddrTyp;
	}

	
	public void setLiveAddrTyp(String liveAddrTyp) {
		this.liveAddrTyp = liveAddrTyp;
	}

	
	public String getLiveProvince() {
		return liveProvince;
	}

	
	public void setLiveProvince(String liveProvince) {
		this.liveProvince = liveProvince;
	}

	
	public String getLiveCity() {
		return liveCity;
	}

	
	public void setLiveCity(String liveCity) {
		this.liveCity = liveCity;
	}

	
	public String getLiveCounty() {
		return liveCounty;
	}

	
	public void setLiveCounty(String liveCounty) {
		this.liveCounty = liveCounty;
	}

	
	public String getLiveStreet() {
		return liveStreet;
	}

	
	public void setLiveStreet(String liveStreet) {
		this.liveStreet = liveStreet;
	}

	
	public String getLivePostcode() {
		return livePostcode;
	}

	
	public void setLivePostcode(String livePostcode) {
		this.livePostcode = livePostcode;
	}

	
	public String getUnitAddrTyp() {
		return unitAddrTyp;
	}

	
	public void setUnitAddrTyp(String unitAddrTyp) {
		this.unitAddrTyp = unitAddrTyp;
	}

	
	public String getUnitProvince() {
		return unitProvince;
	}

	
	public void setUnitProvince(String unitProvince) {
		this.unitProvince = unitProvince;
	}

	
	public String getUnitCity() {
		return unitCity;
	}

	
	public void setUnitCity(String unitCity) {
		this.unitCity = unitCity;
	}

	
	public String getUnitCounty() {
		return unitCounty;
	}

	
	public void setUnitCounty(String unitCounty) {
		this.unitCounty = unitCounty;
	}

	
	public String getUnitStreet() {
		return unitStreet;
	}

	
	public void setUnitStreet(String unitStreet) {
		this.unitStreet = unitStreet;
	}

	
	public String getUnitPostcode() {
		return unitPostcode;
	}

	
	public void setUnitPostcode(String unitPostcode) {
		this.unitPostcode = unitPostcode;
	}

	
	public String getOtherTel() {
		return otherTel;
	}

	
	public void setOtherTel(String otherTel) {
		this.otherTel = otherTel;
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

	
	public String getFax() {
		return fax;
	}

	
	public void setFax(String fax) {
		this.fax = fax;
	}

	
	public String getWechatNo() {
		return wechatNo;
	}

	
	public void setWechatNo(String wechatNo) {
		this.wechatNo = wechatNo;
	}

	
	public String getQq() {
		return qq;
	}

	
	public void setQq(String qq) {
		this.qq = qq;
	}

	
	public String getMicroblog() {
		return microblog;
	}

	public void setMicroblog(String microblog) {
		this.microblog = microblog;
	}

	
	public String getAlipay() {
		return alipay;
	}

	
	public void setAlipay(String alipay) {
		this.alipay = alipay;
	}

	
	public String getEmailAddr() {
		return emailAddr;
	}

	
	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}

	
	public MultipartFile getPerIconUrl() {
		return perIconUrl;
	}

	
	public void setPerIconUrl(MultipartFile perIconUrl) {
		this.perIconUrl = perIconUrl;
	}

	public String getLiveAddrNam() {
		return liveAddrNam;
	}

	public void setLiveAddrNam(String liveAddrNam) {
		this.liveAddrNam = liveAddrNam;
	}

	public String getUnitAddrNam() {
		return unitAddrNam;
	}

	public void setUnitAddrNam(String unitAddrNam) {
		this.unitAddrNam = unitAddrNam;
	}

	public String getSchoolNam() {
		return schoolNam;
	}

	public void setSchoolNam(String schoolNam) {
		this.schoolNam = schoolNam;
	}


	
	public String getPerIconSmlBlob() {
		return perIconSmlBlob;
	}


	
	public void setPerIconSmlBlob(String perIconSmlBlob) {
		this.perIconSmlBlob = perIconSmlBlob;
	}


	
	public String getPerIconBigBlob() {
		return perIconBigBlob;
	}


	
	public void setPerIconBigBlob(String perIconBigBlob) {
		this.perIconBigBlob = perIconBigBlob;
	}


	
	public String getPolitSts() {
		return politSts;
	}


	
	public void setPolitSts(String politSts) {
		this.politSts = politSts;
	}


	
	public String getNation() {
		return nation;
	}


	
	public void setNation(String nation) {
		this.nation = nation;
	}

	private String customerId;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
}
