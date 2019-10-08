package cn.com.zsyk.crm.ecif.entity;

import java.io.Serializable;
import java.util.Date;

public class EcCustNoCreateParm  implements Serializable {

//客户姓名、性别、出生日期、证件类型、证件号码
    private String custNo;
    private String custNam;
    private String sex;
    //private Date birthDate;
    private String birthDate;
    private String certNo;
    
    private String certTyp;
    
    private String phoneNo1; //手机号码
    
    private String detAddr; //地址
    private String addrTyp; //地址类型
    
    private String detAddrUnit; //单位地址
    
    private String detAddrFamily; //家庭地址
    
    private String relationTyp; //关系类型
    private String relationTypNam; //关系名称
    
    private String relationTypNamFather; //关系名称
    
    private String relationTypNamMum; //关系名称
    
    private String custTyp; //客户类型
    

	public String getCustTyp() {
		return custTyp;
	}

	public void setCustTyp(String custTyp) {
		this.custTyp = custTyp;
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

	

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getCertTyp() {
		return certTyp;
	}

	public void setCertTyp(String certTyp) {
		this.certTyp = certTyp;
	}

	public String getPhoneNo1() {
		return phoneNo1;
	}

	public void setPhoneNo1(String phoneNo1) {
		this.phoneNo1 = phoneNo1;
	}

	public String getDetAddr() {
		return detAddr;
	}

	public void setDetAddr(String detAddr) {
		this.detAddr = detAddr;
	}

	public String getAddrTyp() {
		return addrTyp;
	}

	public void setAddrTyp(String addrTyp) {
		this.addrTyp = addrTyp;
	}

	public String getDetAddrUnit() {
		return detAddrUnit;
	}

	public void setDetAddrUnit(String detAddrUnit) {
		this.detAddrUnit = detAddrUnit;
	}

	public String getDetAddrFamily() {
		return detAddrFamily;
	}

	public void setDetAddrFamily(String detAddrFamily) {
		this.detAddrFamily = detAddrFamily;
	}

	public String getRelationTyp() {
		return relationTyp;
	}

	public void setRelationTyp(String relationTyp) {
		this.relationTyp = relationTyp;
	}

	public String getRelationTypNam() {
		return relationTypNam;
	}

	public void setRelationTypNam(String relationTypNam) {
		this.relationTypNam = relationTypNam;
	}

	public String getRelationTypNamFather() {
		return relationTypNamFather;
	}

	public void setRelationTypNamFather(String relationTypNamFather) {
		this.relationTypNamFather = relationTypNamFather;
	}

	public String getRelationTypNamMum() {
		return relationTypNamMum;
	}

	public void setRelationTypNamMum(String relationTypNamMum) {
		this.relationTypNamMum = relationTypNamMum;
	}
    
    
    
    

}
