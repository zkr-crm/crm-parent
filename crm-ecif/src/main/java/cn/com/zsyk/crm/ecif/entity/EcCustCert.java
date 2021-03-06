package cn.com.zsyk.crm.ecif.entity;

import java.io.Serializable;
import java.util.Date;

public class EcCustCert implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_cust_cert.cust_no
     *
     * @mbggenerated
     */
    private String custNo;
    private String custNam;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_cust_cert.cert_sqn
     *
     * @mbggenerated
     */
    private Integer certSqn;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_cust_cert.cert_typ
     *
     * @mbggenerated
     */
    private String certTyp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_cust_cert.cert_no
     *
     * @mbggenerated
     */
    private String certNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_cust_cert.acti_date
     *
     * @mbggenerated
     */
    private String actiDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_cust_cert.stop_use_date
     *
     * @mbggenerated
     */
    private String stopUseDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_cust_cert.cert_eff_date
     *
     * @mbggenerated
     */
    private String certEffDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_cust_cert.desc_content
     *
     * @mbggenerated
     */
    private String descContent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_cust_cert.cert_issued_plece
     *
     * @mbggenerated
     */
    private String certIssuedPlece;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_cust_cert.orig_sys_flg
     *
     * @mbggenerated
     */
    private String origSysFlg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_cust_cert.cert_sts
     *
     * @mbggenerated
     */
    private String certSts;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_cust_cert.create_date
     *
     * @mbggenerated
     */
    private String createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_cust_cert.create_time
     *
     * @mbggenerated
     */
    private String createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_cust_cert.create_user
     *
     * @mbggenerated
     */
    private String createUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_cust_cert.update_date
     *
     * @mbggenerated
     */
    private String updateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_cust_cert.update_time
     *
     * @mbggenerated
     */
    private String updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_cust_cert.update_user
     *
     * @mbggenerated
     */
    private String updateUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_cust_cert.time_stamp
     *
     * @mbggenerated
     */
    private Date timeStamp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_cust_cert.rec_stat
     *
     * @mbggenerated
     */
    private String recStat;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ec_cust_cert
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_cust_cert.cust_no
     *
     * @return the value of ec_cust_cert.cust_no
     *
     * @mbggenerated
     */
    public String getCustNo() {
        return custNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_cust_cert.cust_no
     *
     * @param custNo the value for ec_cust_cert.cust_no
     *
     * @mbggenerated
     */
    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_cust_cert.cert_sqn
     *
     * @return the value of ec_cust_cert.cert_sqn
     *
     * @mbggenerated
     */
    public Integer getCertSqn() {
        return certSqn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_cust_cert.cert_sqn
     *
     * @param certSqn the value for ec_cust_cert.cert_sqn
     *
     * @mbggenerated
     */
    public void setCertSqn(Integer certSqn) {
        this.certSqn = certSqn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_cust_cert.cert_typ
     *
     * @return the value of ec_cust_cert.cert_typ
     *
     * @mbggenerated
     */
    public String getCertTyp() {
        return certTyp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_cust_cert.cert_typ
     *
     * @param certTyp the value for ec_cust_cert.cert_typ
     *
     * @mbggenerated
     */
    public void setCertTyp(String certTyp) {
        this.certTyp = certTyp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_cust_cert.cert_no
     *
     * @return the value of ec_cust_cert.cert_no
     *
     * @mbggenerated
     */
    public String getCertNo() {
        return certNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_cust_cert.cert_no
     *
     * @param certNo the value for ec_cust_cert.cert_no
     *
     * @mbggenerated
     */
    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_cust_cert.acti_date
     *
     * @return the value of ec_cust_cert.acti_date
     *
     * @mbggenerated
     */
    public String getActiDate() {
        return actiDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_cust_cert.acti_date
     *
     * @param actiDate the value for ec_cust_cert.acti_date
     *
     * @mbggenerated
     */
    public void setActiDate(String actiDate) {
        this.actiDate = actiDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_cust_cert.stop_use_date
     *
     * @return the value of ec_cust_cert.stop_use_date
     *
     * @mbggenerated
     */
    public String getStopUseDate() {
        return stopUseDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_cust_cert.stop_use_date
     *
     * @param stopUseDate the value for ec_cust_cert.stop_use_date
     *
     * @mbggenerated
     */
    public void setStopUseDate(String stopUseDate) {
        this.stopUseDate = stopUseDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_cust_cert.cert_eff_date
     *
     * @return the value of ec_cust_cert.cert_eff_date
     *
     * @mbggenerated
     */
    public String getCertEffDate() {
        return certEffDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_cust_cert.cert_eff_date
     *
     * @param certEffDate the value for ec_cust_cert.cert_eff_date
     *
     * @mbggenerated
     */
    public void setCertEffDate(String certEffDate) {
        this.certEffDate = certEffDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_cust_cert.desc_content
     *
     * @return the value of ec_cust_cert.desc_content
     *
     * @mbggenerated
     */
    public String getDescContent() {
        return descContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_cust_cert.desc_content
     *
     * @param descContent the value for ec_cust_cert.desc_content
     *
     * @mbggenerated
     */
    public void setDescContent(String descContent) {
        this.descContent = descContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_cust_cert.cert_issued_plece
     *
     * @return the value of ec_cust_cert.cert_issued_plece
     *
     * @mbggenerated
     */
    public String getCertIssuedPlece() {
        return certIssuedPlece;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_cust_cert.cert_issued_plece
     *
     * @param certIssuedPlece the value for ec_cust_cert.cert_issued_plece
     *
     * @mbggenerated
     */
    public void setCertIssuedPlece(String certIssuedPlece) {
        this.certIssuedPlece = certIssuedPlece;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_cust_cert.orig_sys_flg
     *
     * @return the value of ec_cust_cert.orig_sys_flg
     *
     * @mbggenerated
     */
    public String getOrigSysFlg() {
        return origSysFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_cust_cert.orig_sys_flg
     *
     * @param origSysFlg the value for ec_cust_cert.orig_sys_flg
     *
     * @mbggenerated
     */
    public void setOrigSysFlg(String origSysFlg) {
        this.origSysFlg = origSysFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_cust_cert.cert_sts
     *
     * @return the value of ec_cust_cert.cert_sts
     *
     * @mbggenerated
     */
    public String getCertSts() {
        return certSts;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_cust_cert.cert_sts
     *
     * @param certSts the value for ec_cust_cert.cert_sts
     *
     * @mbggenerated
     */
    public void setCertSts(String certSts) {
        this.certSts = certSts;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_cust_cert.create_date
     *
     * @return the value of ec_cust_cert.create_date
     *
     * @mbggenerated
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_cust_cert.create_date
     *
     * @param createDate the value for ec_cust_cert.create_date
     *
     * @mbggenerated
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_cust_cert.create_time
     *
     * @return the value of ec_cust_cert.create_time
     *
     * @mbggenerated
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_cust_cert.create_time
     *
     * @param createTime the value for ec_cust_cert.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_cust_cert.create_user
     *
     * @return the value of ec_cust_cert.create_user
     *
     * @mbggenerated
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_cust_cert.create_user
     *
     * @param createUser the value for ec_cust_cert.create_user
     *
     * @mbggenerated
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_cust_cert.update_date
     *
     * @return the value of ec_cust_cert.update_date
     *
     * @mbggenerated
     */
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_cust_cert.update_date
     *
     * @param updateDate the value for ec_cust_cert.update_date
     *
     * @mbggenerated
     */
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_cust_cert.update_time
     *
     * @return the value of ec_cust_cert.update_time
     *
     * @mbggenerated
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_cust_cert.update_time
     *
     * @param updateTime the value for ec_cust_cert.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_cust_cert.update_user
     *
     * @return the value of ec_cust_cert.update_user
     *
     * @mbggenerated
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_cust_cert.update_user
     *
     * @param updateUser the value for ec_cust_cert.update_user
     *
     * @mbggenerated
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_cust_cert.time_stamp
     *
     * @return the value of ec_cust_cert.time_stamp
     *
     * @mbggenerated
     */
    public Date getTimeStamp() {
        return timeStamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_cust_cert.time_stamp
     *
     * @param timeStamp the value for ec_cust_cert.time_stamp
     *
     * @mbggenerated
     */
    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_cust_cert.rec_stat
     *
     * @return the value of ec_cust_cert.rec_stat
     *
     * @mbggenerated
     */
    public String getRecStat() {
        return recStat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_cust_cert.rec_stat
     *
     * @param recStat the value for ec_cust_cert.rec_stat
     *
     * @mbggenerated
     */
    public void setRecStat(String recStat) {
        this.recStat = recStat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_cust_cert
     *
     * @mbggenerated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        EcCustCert other = (EcCustCert) that;
        return (this.getCustNo() == null ? other.getCustNo() == null : this.getCustNo().equals(other.getCustNo()))
            && (this.getCertSqn() == null ? other.getCertSqn() == null : this.getCertSqn().equals(other.getCertSqn()))
            && (this.getCertTyp() == null ? other.getCertTyp() == null : this.getCertTyp().equals(other.getCertTyp()))
            && (this.getCertNo() == null ? other.getCertNo() == null : this.getCertNo().equals(other.getCertNo()))
            && (this.getActiDate() == null ? other.getActiDate() == null : this.getActiDate().equals(other.getActiDate()))
            && (this.getStopUseDate() == null ? other.getStopUseDate() == null : this.getStopUseDate().equals(other.getStopUseDate()))
            && (this.getCertEffDate() == null ? other.getCertEffDate() == null : this.getCertEffDate().equals(other.getCertEffDate()))
            && (this.getDescContent() == null ? other.getDescContent() == null : this.getDescContent().equals(other.getDescContent()))
            && (this.getCertIssuedPlece() == null ? other.getCertIssuedPlece() == null : this.getCertIssuedPlece().equals(other.getCertIssuedPlece()))
            && (this.getOrigSysFlg() == null ? other.getOrigSysFlg() == null : this.getOrigSysFlg().equals(other.getOrigSysFlg()))
            && (this.getCertSts() == null ? other.getCertSts() == null : this.getCertSts().equals(other.getCertSts()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
            && (this.getTimeStamp() == null ? other.getTimeStamp() == null : this.getTimeStamp().equals(other.getTimeStamp()))
            && (this.getRecStat() == null ? other.getRecStat() == null : this.getRecStat().equals(other.getRecStat()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_cust_cert
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCustNo() == null) ? 0 : getCustNo().hashCode());
        result = prime * result + ((getCertSqn() == null) ? 0 : getCertSqn().hashCode());
        result = prime * result + ((getCertTyp() == null) ? 0 : getCertTyp().hashCode());
        result = prime * result + ((getCertNo() == null) ? 0 : getCertNo().hashCode());
        result = prime * result + ((getActiDate() == null) ? 0 : getActiDate().hashCode());
        result = prime * result + ((getStopUseDate() == null) ? 0 : getStopUseDate().hashCode());
        result = prime * result + ((getCertEffDate() == null) ? 0 : getCertEffDate().hashCode());
        result = prime * result + ((getDescContent() == null) ? 0 : getDescContent().hashCode());
        result = prime * result + ((getCertIssuedPlece() == null) ? 0 : getCertIssuedPlece().hashCode());
        result = prime * result + ((getOrigSysFlg() == null) ? 0 : getOrigSysFlg().hashCode());
        result = prime * result + ((getCertSts() == null) ? 0 : getCertSts().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        result = prime * result + ((getTimeStamp() == null) ? 0 : getTimeStamp().hashCode());
        result = prime * result + ((getRecStat() == null) ? 0 : getRecStat().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_cust_cert
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", custNo=").append(custNo);
        sb.append(", certSqn=").append(certSqn);
        sb.append(", certTyp=").append(certTyp);
        sb.append(", certNo=").append(certNo);
        sb.append(", actiDate=").append(actiDate);
        sb.append(", stopUseDate=").append(stopUseDate);
        sb.append(", certEffDate=").append(certEffDate);
        sb.append(", descContent=").append(descContent);
        sb.append(", certIssuedPlece=").append(certIssuedPlece);
        sb.append(", origSysFlg=").append(origSysFlg);
        sb.append(", certSts=").append(certSts);
        sb.append(", createDate=").append(createDate);
        sb.append(", createTime=").append(createTime);
        sb.append(", createUser=").append(createUser);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", timeStamp=").append(timeStamp);
        sb.append(", recStat=").append(recStat);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

	public String getCustNam() {
		return custNam;
	}

	public void setCustNam(String custNam) {
		this.custNam = custNam;
	}
}