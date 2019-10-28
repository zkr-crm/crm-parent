package cn.com.zsyk.crm.ecif.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class EcTrackInfo implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_track_info.track_cd
     *
     * @mbggenerated
     */
    private String trackCd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_track_info.track_time
     *
     * @mbggenerated
     */
    private Date trackTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_track_info.cust_no
     *
     * @mbggenerated
     */
    private String custNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_track_info.track_typ
     *
     * @mbggenerated
     */
    private String trackTyp;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_track_info.track_sub_typ
     *
     * @mbggenerated
     */
    private String trackSubTyp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_track_info.track_content
     *
     * @mbggenerated
     */
    private String trackContent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_track_info.record_user
     *
     * @mbggenerated
     */
    private String recordUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_track_info.wht_through
     *
     * @mbggenerated
     */
    private String whtThrough;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_track_info.contacts
     *
     * @mbggenerated
     */
    private String contacts;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_track_info.phone_no
     *
     * @mbggenerated
     */
    private String phoneNo;
    
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_track_info.next_follow_up_tm
     *
     * @mbggenerated
     */
    private Date nextFollowUpTm;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_track_info.location
     *
     * @mbggenerated
     */
    private String location;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_track_info.comt_flg
     *
     * @mbggenerated
     */
    private String comtFlg;
    private List<EcTrackComt> trackComtList;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_track_info.create_date
     *
     * @mbggenerated
     */
    private String createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_track_info.create_time
     *
     * @mbggenerated
     */
    private String createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_track_info.create_user
     *
     * @mbggenerated
     */
    private String createUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_track_info.update_date
     *
     * @mbggenerated
     */
    private String updateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_track_info.update_time
     *
     * @mbggenerated
     */
    private String updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_track_info.update_user
     *
     * @mbggenerated
     */
    private String updateUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_track_info.time_stamp
     *
     * @mbggenerated
     */
    private Date timeStamp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_track_info.rec_stat
     *
     * @mbggenerated
     */
    private String recStat;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ec_track_info
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_track_info.track_cd
     *
     * @return the value of ec_track_info.track_cd
     *
     * @mbggenerated
     */
    public String getTrackCd() {
        return trackCd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_track_info.track_cd
     *
     * @param trackCd the value for ec_track_info.track_cd
     *
     * @mbggenerated
     */
    public void setTrackCd(String trackCd) {
        this.trackCd = trackCd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_track_info.track_time
     *
     * @return the value of ec_track_info.track_time
     *
     * @mbggenerated
     */
    public Date getTrackTime() {
        return trackTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_track_info.track_time
     *
     * @param trackTime the value for ec_track_info.track_time
     *
     * @mbggenerated
     */
    public void setTrackTime(Date trackTime) {
        this.trackTime = trackTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_track_info.cust_no
     *
     * @return the value of ec_track_info.cust_no
     *
     * @mbggenerated
     */
    public String getCustNo() {
        return custNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_track_info.cust_no
     *
     * @param custNo the value for ec_track_info.cust_no
     *
     * @mbggenerated
     */
    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_track_info.track_typ
     *
     * @return the value of ec_track_info.track_typ
     *
     * @mbggenerated
     */
    public String getTrackTyp() {
        return trackTyp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_track_info.track_typ
     *
     * @param trackTyp the value for ec_track_info.track_typ
     *
     * @mbggenerated
     */
    public void setTrackTyp(String trackTyp) {
        this.trackTyp = trackTyp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_track_info.track_sub_typ
     *
     * @param trackSubTyp the value for ec_track_info.track_sub_typ
     *
     * @mbggenerated
     */
    public void setTrackSubTyp(String trackSubTyp) {
        this.trackSubTyp = trackSubTyp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_track_info.track_sub_typ
     *
     * @return the value of ec_track_info.track_sub_typ
     *
     * @mbggenerated
     */
    public String getTrackSubTyp() {
        return trackSubTyp;
    }
    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_track_info.track_content
     *
     * @return the value of ec_track_info.track_content
     *
     * @mbggenerated
     */
    public String getTrackContent() {
        return trackContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_track_info.track_content
     *
     * @param trackContent the value for ec_track_info.track_content
     *
     * @mbggenerated
     */
    public void setTrackContent(String trackContent) {
        this.trackContent = trackContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_track_info.record_user
     *
     * @return the value of ec_track_info.record_user
     *
     * @mbggenerated
     */
    public String getRecordUser() {
        return recordUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_track_info.record_user
     *
     * @param recordUser the value for ec_track_info.record_user
     *
     * @mbggenerated
     */
    public void setRecordUser(String recordUser) {
        this.recordUser = recordUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_track_info.wht_through
     *
     * @return the value of ec_track_info.wht_through
     *
     * @mbggenerated
     */
    public String getWhtThrough() {
        return whtThrough;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_track_info.wht_through
     *
     * @param whtThrough the value for ec_track_info.wht_through
     *
     * @mbggenerated
     */
    public void setWhtThrough(String whtThrough) {
        this.whtThrough = whtThrough;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_track_info.contacts
     *
     * @return the value of ec_track_info.contacts
     *
     * @mbggenerated
     */
    public String getContacts() {
        return contacts;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_track_info.contacts
     *
     * @param contacts the value for ec_track_info.contacts
     *
     * @mbggenerated
     */
    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_track_info.next_follow_up_tm
     *
     * @return the value of ec_track_info.next_follow_up_tm
     *
     * @mbggenerated
     */
    public Date getNextFollowUpTm() {
        return nextFollowUpTm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_track_info.next_follow_up_tm
     *
     * @param nextFollowUpTm the value for ec_track_info.next_follow_up_tm
     *
     * @mbggenerated
     */
    public void setNextFollowUpTm(Date nextFollowUpTm) {
        this.nextFollowUpTm = nextFollowUpTm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_track_info.comt_flg
     *
     * @return the value of ec_track_info.comt_flg
     *
     * @mbggenerated
     */
    public String getComtFlg() {
        return comtFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_track_info.comt_flg
     *
     * @param comtFlg the value for ec_track_info.comt_flg
     *
     * @mbggenerated
     */
    public void setComtFlg(String comtFlg) {
        this.comtFlg = comtFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_track_info.create_date
     *
     * @return the value of ec_track_info.create_date
     *
     * @mbggenerated
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_track_info.create_date
     *
     * @param createDate the value for ec_track_info.create_date
     *
     * @mbggenerated
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_track_info.create_time
     *
     * @return the value of ec_track_info.create_time
     *
     * @mbggenerated
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_track_info.create_time
     *
     * @param createTime the value for ec_track_info.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_track_info.create_user
     *
     * @return the value of ec_track_info.create_user
     *
     * @mbggenerated
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_track_info.create_user
     *
     * @param createUser the value for ec_track_info.create_user
     *
     * @mbggenerated
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_track_info.update_date
     *
     * @return the value of ec_track_info.update_date
     *
     * @mbggenerated
     */
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_track_info.update_date
     *
     * @param updateDate the value for ec_track_info.update_date
     *
     * @mbggenerated
     */
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_track_info.update_time
     *
     * @return the value of ec_track_info.update_time
     *
     * @mbggenerated
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_track_info.update_time
     *
     * @param updateTime the value for ec_track_info.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_track_info.update_user
     *
     * @return the value of ec_track_info.update_user
     *
     * @mbggenerated
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_track_info.update_user
     *
     * @param updateUser the value for ec_track_info.update_user
     *
     * @mbggenerated
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_track_info.time_stamp
     *
     * @return the value of ec_track_info.time_stamp
     *
     * @mbggenerated
     */
    public Date getTimeStamp() {
        return timeStamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_track_info.time_stamp
     *
     * @param timeStamp the value for ec_track_info.time_stamp
     *
     * @mbggenerated
     */
    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_track_info.rec_stat
     *
     * @return the value of ec_track_info.rec_stat
     *
     * @mbggenerated
     */
    public String getRecStat() {
        return recStat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_track_info.rec_stat
     *
     * @param recStat the value for ec_track_info.rec_stat
     *
     * @mbggenerated
     */
    public void setRecStat(String recStat) {
        this.recStat = recStat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_track_info
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
        EcTrackInfo other = (EcTrackInfo) that;
        return (this.getTrackCd() == null ? other.getTrackCd() == null : this.getTrackCd().equals(other.getTrackCd()))
            && (this.getTrackTime() == null ? other.getTrackTime() == null : this.getTrackTime().equals(other.getTrackTime()))
            && (this.getCustNo() == null ? other.getCustNo() == null : this.getCustNo().equals(other.getCustNo()))
            && (this.getTrackTyp() == null ? other.getTrackTyp() == null : this.getTrackTyp().equals(other.getTrackTyp()))
            && (this.getTrackSubTyp() == null ? other.getTrackSubTyp() == null : this.getTrackSubTyp().equals(other.getTrackSubTyp()))
            && (this.getTrackContent() == null ? other.getTrackContent() == null : this.getTrackContent().equals(other.getTrackContent()))
            && (this.getRecordUser() == null ? other.getRecordUser() == null : this.getRecordUser().equals(other.getRecordUser()))
            && (this.getWhtThrough() == null ? other.getWhtThrough() == null : this.getWhtThrough().equals(other.getWhtThrough()))
            && (this.getContacts() == null ? other.getContacts() == null : this.getContacts().equals(other.getContacts()))
            && (this.getPhoneNo() == null ? other.getPhoneNo() == null : this.getPhoneNo().equals(other.getPhoneNo()))
            && (this.getNextFollowUpTm() == null ? other.getNextFollowUpTm() == null : this.getNextFollowUpTm().equals(other.getNextFollowUpTm()))
            && (this.getLocation() == null ? other.getLocation() == null : this.getLocation().equals(other.getLocation()))
            && (this.getComtFlg() == null ? other.getComtFlg() == null : this.getComtFlg().equals(other.getComtFlg()))
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
     * This method corresponds to the database table ec_track_info
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTrackCd() == null) ? 0 : getTrackCd().hashCode());
        result = prime * result + ((getTrackTime() == null) ? 0 : getTrackTime().hashCode());
        result = prime * result + ((getCustNo() == null) ? 0 : getCustNo().hashCode());
        result = prime * result + ((getTrackTyp() == null) ? 0 : getTrackTyp().hashCode());
        result = prime * result + ((getTrackSubTyp() == null) ? 0 : getTrackSubTyp().hashCode());
        result = prime * result + ((getTrackContent() == null) ? 0 : getTrackContent().hashCode());
        result = prime * result + ((getRecordUser() == null) ? 0 : getRecordUser().hashCode());
        result = prime * result + ((getWhtThrough() == null) ? 0 : getWhtThrough().hashCode());
        result = prime * result + ((getContacts() == null) ? 0 : getContacts().hashCode());
        result = prime * result + ((getPhoneNo() == null) ? 0 : getPhoneNo().hashCode());
        result = prime * result + ((getNextFollowUpTm() == null) ? 0 : getNextFollowUpTm().hashCode());
        result = prime * result + ((getLocation() == null) ? 0 : getLocation().hashCode());
        result = prime * result + ((getComtFlg() == null) ? 0 : getComtFlg().hashCode());
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
     * This method corresponds to the database table ec_track_info
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", trackCd=").append(trackCd);
        sb.append(", trackTime=").append(trackTime);
        sb.append(", custNo=").append(custNo);
        sb.append(", trackTyp=").append(trackTyp);
        sb.append(", trackSubTyp=").append(trackSubTyp);
        sb.append(", trackContent=").append(trackContent);
        sb.append(", recordUser=").append(recordUser);
        sb.append(", whtThrough=").append(whtThrough);
        sb.append(", contacts=").append(contacts);
        sb.append(", phoneNo=").append(phoneNo);
        sb.append(", nextFollowUpTm=").append(nextFollowUpTm);
        sb.append(", location=").append(location);
        sb.append(", comtFlg=").append(comtFlg);
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

	public List<EcTrackComt> getTrackComtList() {
		return trackComtList;
	}

	public void setTrackComtList(List<EcTrackComt> trackComtList) {
		this.trackComtList = trackComtList;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
    
}