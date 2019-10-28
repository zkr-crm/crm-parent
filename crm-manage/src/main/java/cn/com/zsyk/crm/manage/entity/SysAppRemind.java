package cn.com.zsyk.crm.manage.entity;

import java.io.Serializable;
import java.util.Date;

public class SysAppRemind implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_app_remind.remind_id
     *
     * @mbggenerated
     */
    private String remindId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_app_remind.send_user
     *
     * @mbggenerated
     */
    private String sendUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_app_remind.receiv_user
     *
     * @mbggenerated
     */
    private String receivUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_app_remind.send_time
     *
     * @mbggenerated
     */
    private Date sendTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_app_remind.remind_title
     *
     * @mbggenerated
     */
    private String remindTitle;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_app_remind.remind_content
     *
     * @mbggenerated
     */
    private String remindContent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_app_remind.is_read
     *
     * @mbggenerated
     */
    private String isRead;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_app_remind.remind_level
     *
     * @mbggenerated
     */
    private String remindLevel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_app_remind.remind_type
     *
     * @mbggenerated
     */
    private String remindType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_app_remind.remind_stat
     *
     * @mbggenerated
     */
    private String remindStat;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_app_remind.create_date
     *
     * @mbggenerated
     */
    private String createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_app_remind.create_time
     *
     * @mbggenerated
     */
    private String createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_app_remind.create_user
     *
     * @mbggenerated
     */
    private String createUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_app_remind.update_date
     *
     * @mbggenerated
     */
    private String updateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_app_remind.update_time
     *
     * @mbggenerated
     */
    private String updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_app_remind.update_user
     *
     * @mbggenerated
     */
    private String updateUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_app_remind.time_stamp
     *
     * @mbggenerated
     */
    private Date timeStamp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_app_remind
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;
    /**
     * 客户编号
     * @auther:xieruilaing
     */
    private String custNo;
    /**
     * 客户名称
     * @auther:xieruilaing
     */
    private String custName;

    /**
     * 事件类型
     * @auther:xieruilaing
     */
    private String eventType;
    /**
     * 事件日期
     * @auther:xieruilaing
     */
    private String eventDate;
    /**
     * 保单号
     * @auther:xieruilaing
     */
    private String policyNo;
    /**
     * 终保日期
     * @auther:xieruilaing
     */
    private String endDate;
    /**
     * 客户电话
     * @auther:xieruilaing
     */
    private String mobile;
    /**
     * 险种名称
     * @auther:wangsihui
     */
    private String riskCname;

    public String getRiskCname() {
        return riskCname;
    }

    public void setRiskCname(String riskCname) {
        this.riskCname = riskCname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_app_remind.remind_id
     *
     * @return the value of sys_app_remind.remind_id
     *
     * @mbggenerated
     */
    public String getRemindId() {
        return remindId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_app_remind.remind_id
     *
     * @param remindId the value for sys_app_remind.remind_id
     *
     * @mbggenerated
     */
    public void setRemindId(String remindId) {
        this.remindId = remindId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_app_remind.send_user
     *
     * @return the value of sys_app_remind.send_user
     *
     * @mbggenerated
     */
    public String getSendUser() {
        return sendUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_app_remind.send_user
     *
     * @param sendUser the value for sys_app_remind.send_user
     *
     * @mbggenerated
     */
    public void setSendUser(String sendUser) {
        this.sendUser = sendUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_app_remind.receiv_user
     *
     * @return the value of sys_app_remind.receiv_user
     *
     * @mbggenerated
     */
    public String getReceivUser() {
        return receivUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_app_remind.receiv_user
     *
     * @param receivUser the value for sys_app_remind.receiv_user
     *
     * @mbggenerated
     */
    public void setReceivUser(String receivUser) {
        this.receivUser = receivUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_app_remind.send_time
     *
     * @return the value of sys_app_remind.send_time
     *
     * @mbggenerated
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_app_remind.send_time
     *
     * @param sendTime the value for sys_app_remind.send_time
     *
     * @mbggenerated
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_app_remind.remind_title
     *
     * @return the value of sys_app_remind.remind_title
     *
     * @mbggenerated
     */
    public String getRemindTitle() {
        return remindTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_app_remind.remind_title
     *
     * @param remindTitle the value for sys_app_remind.remind_title
     *
     * @mbggenerated
     */
    public void setRemindTitle(String remindTitle) {
        this.remindTitle = remindTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_app_remind.remind_content
     *
     * @return the value of sys_app_remind.remind_content
     *
     * @mbggenerated
     */
    public String getRemindContent() {
        return remindContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_app_remind.remind_content
     *
     * @param remindContent the value for sys_app_remind.remind_content
     *
     * @mbggenerated
     */
    public void setRemindContent(String remindContent) {
        this.remindContent = remindContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_app_remind.is_read
     *
     * @return the value of sys_app_remind.is_read
     *
     * @mbggenerated
     */
    public String getIsRead() {
        return isRead;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_app_remind.is_read
     *
     * @param isRead the value for sys_app_remind.is_read
     *
     * @mbggenerated
     */
    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_app_remind.remind_level
     *
     * @return the value of sys_app_remind.remind_level
     *
     * @mbggenerated
     */
    public String getRemindLevel() {
        return remindLevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_app_remind.remind_level
     *
     * @param remindLevel the value for sys_app_remind.remind_level
     *
     * @mbggenerated
     */
    public void setRemindLevel(String remindLevel) {
        this.remindLevel = remindLevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_app_remind.remind_type
     *
     * @return the value of sys_app_remind.remind_type
     *
     * @mbggenerated
     */
    public String getRemindType() {
        return remindType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_app_remind.remind_type
     *
     * @param remindType the value for sys_app_remind.remind_type
     *
     * @mbggenerated
     */
    public void setRemindType(String remindType) {
        this.remindType = remindType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_app_remind.remind_stat
     *
     * @return the value of sys_app_remind.remind_stat
     *
     * @mbggenerated
     */
    public String getRemindStat() {
        return remindStat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_app_remind.remind_stat
     *
     * @param remindStat the value for sys_app_remind.remind_stat
     *
     * @mbggenerated
     */
    public void setRemindStat(String remindStat) {
        this.remindStat = remindStat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_app_remind.create_date
     *
     * @return the value of sys_app_remind.create_date
     *
     * @mbggenerated
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_app_remind.create_date
     *
     * @param createDate the value for sys_app_remind.create_date
     *
     * @mbggenerated
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_app_remind.create_time
     *
     * @return the value of sys_app_remind.create_time
     *
     * @mbggenerated
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_app_remind.create_time
     *
     * @param createTime the value for sys_app_remind.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_app_remind.create_user
     *
     * @return the value of sys_app_remind.create_user
     *
     * @mbggenerated
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_app_remind.create_user
     *
     * @param createUser the value for sys_app_remind.create_user
     *
     * @mbggenerated
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_app_remind.update_date
     *
     * @return the value of sys_app_remind.update_date
     *
     * @mbggenerated
     */
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_app_remind.update_date
     *
     * @param updateDate the value for sys_app_remind.update_date
     *
     * @mbggenerated
     */
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_app_remind.update_time
     *
     * @return the value of sys_app_remind.update_time
     *
     * @mbggenerated
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_app_remind.update_time
     *
     * @param updateTime the value for sys_app_remind.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_app_remind.update_user
     *
     * @return the value of sys_app_remind.update_user
     *
     * @mbggenerated
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_app_remind.update_user
     *
     * @param updateUser the value for sys_app_remind.update_user
     *
     * @mbggenerated
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_app_remind.time_stamp
     *
     * @return the value of sys_app_remind.time_stamp
     *
     * @mbggenerated
     */
    public Date getTimeStamp() {
        return timeStamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_app_remind.time_stamp
     *
     * @param timeStamp the value for sys_app_remind.time_stamp
     *
     * @mbggenerated
     */
    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getEventType() { return eventType; }

    public void setEventType(String eventType) { this.eventType = eventType; }

    public String getEventDate() { return eventDate; }

    public void setEventDate(String eventDate) { this.eventDate = eventDate; }

    public String getCustNo() { return custNo; }

    public void setCustNo(String custNo) { this.custNo = custNo; }

    public String getCustName() { return custName; }

    public void setCustName(String custName) { this.custName = custName; }

    public String getPolicyNo() { return policyNo; }

    public void setPolicyNo(String policyNo) { this.policyNo = policyNo; }

    public String getEndDate() { return endDate; }

    public void setEndDate(String endDate) { this.endDate = endDate; }

    public String getMobile() { return mobile; }

    public void setMobile(String mobile) { this.mobile = mobile; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SysAppRemind)) return false;

        SysAppRemind that = (SysAppRemind) o;

        if (getRemindId() != null ? !getRemindId().equals(that.getRemindId()) : that.getRemindId() != null)
            return false;
        if (getSendUser() != null ? !getSendUser().equals(that.getSendUser()) : that.getSendUser() != null)
            return false;
        if (getReceivUser() != null ? !getReceivUser().equals(that.getReceivUser()) : that.getReceivUser() != null)
            return false;
        if (getSendTime() != null ? !getSendTime().equals(that.getSendTime()) : that.getSendTime() != null)
            return false;
        if (getRemindTitle() != null ? !getRemindTitle().equals(that.getRemindTitle()) : that.getRemindTitle() != null)
            return false;
        if (getRemindContent() != null ? !getRemindContent().equals(that.getRemindContent()) : that.getRemindContent() != null)
            return false;
        if (getIsRead() != null ? !getIsRead().equals(that.getIsRead()) : that.getIsRead() != null) return false;
        if (getRemindLevel() != null ? !getRemindLevel().equals(that.getRemindLevel()) : that.getRemindLevel() != null)
            return false;
        if (getRemindType() != null ? !getRemindType().equals(that.getRemindType()) : that.getRemindType() != null)
            return false;
        if (getRemindStat() != null ? !getRemindStat().equals(that.getRemindStat()) : that.getRemindStat() != null)
            return false;
        if (getCreateDate() != null ? !getCreateDate().equals(that.getCreateDate()) : that.getCreateDate() != null)
            return false;
        if (getCreateTime() != null ? !getCreateTime().equals(that.getCreateTime()) : that.getCreateTime() != null)
            return false;
        if (getCreateUser() != null ? !getCreateUser().equals(that.getCreateUser()) : that.getCreateUser() != null)
            return false;
        if (getUpdateDate() != null ? !getUpdateDate().equals(that.getUpdateDate()) : that.getUpdateDate() != null)
            return false;
        if (getUpdateTime() != null ? !getUpdateTime().equals(that.getUpdateTime()) : that.getUpdateTime() != null)
            return false;
        if (getUpdateUser() != null ? !getUpdateUser().equals(that.getUpdateUser()) : that.getUpdateUser() != null)
            return false;
        if (getTimeStamp() != null ? !getTimeStamp().equals(that.getTimeStamp()) : that.getTimeStamp() != null)
            return false;
        if (getCustNo() != null ? !getCustNo().equals(that.getCustNo()) : that.getCustNo() != null) return false;
        if (getCustName() != null ? !getCustName().equals(that.getCustName()) : that.getCustName() != null)
            return false;
        if (getEventType() != null ? !getEventType().equals(that.getEventType()) : that.getEventType() != null)
            return false;
        if (getEventDate() != null ? !getEventDate().equals(that.getEventDate()) : that.getEventDate() != null)
            return false;
        if (getPolicyNo() != null ? !getPolicyNo().equals(that.getPolicyNo()) : that.getPolicyNo() != null)
            return false;
        if (getEndDate() != null ? !getEndDate().equals(that.getEndDate()) : that.getEndDate() != null) return false;
        if (getMobile() != null ? !getMobile().equals(that.getMobile()) : that.getMobile() != null) return false;
        return getRiskCname() != null ? getRiskCname().equals(that.getRiskCname()) : that.getRiskCname() == null;
    }

    @Override
    public int hashCode() {
        int result = getRemindId() != null ? getRemindId().hashCode() : 0;
        result = 31 * result + (getSendUser() != null ? getSendUser().hashCode() : 0);
        result = 31 * result + (getReceivUser() != null ? getReceivUser().hashCode() : 0);
        result = 31 * result + (getSendTime() != null ? getSendTime().hashCode() : 0);
        result = 31 * result + (getRemindTitle() != null ? getRemindTitle().hashCode() : 0);
        result = 31 * result + (getRemindContent() != null ? getRemindContent().hashCode() : 0);
        result = 31 * result + (getIsRead() != null ? getIsRead().hashCode() : 0);
        result = 31 * result + (getRemindLevel() != null ? getRemindLevel().hashCode() : 0);
        result = 31 * result + (getRemindType() != null ? getRemindType().hashCode() : 0);
        result = 31 * result + (getRemindStat() != null ? getRemindStat().hashCode() : 0);
        result = 31 * result + (getCreateDate() != null ? getCreateDate().hashCode() : 0);
        result = 31 * result + (getCreateTime() != null ? getCreateTime().hashCode() : 0);
        result = 31 * result + (getCreateUser() != null ? getCreateUser().hashCode() : 0);
        result = 31 * result + (getUpdateDate() != null ? getUpdateDate().hashCode() : 0);
        result = 31 * result + (getUpdateTime() != null ? getUpdateTime().hashCode() : 0);
        result = 31 * result + (getUpdateUser() != null ? getUpdateUser().hashCode() : 0);
        result = 31 * result + (getTimeStamp() != null ? getTimeStamp().hashCode() : 0);
        result = 31 * result + (getCustNo() != null ? getCustNo().hashCode() : 0);
        result = 31 * result + (getCustName() != null ? getCustName().hashCode() : 0);
        result = 31 * result + (getEventType() != null ? getEventType().hashCode() : 0);
        result = 31 * result + (getEventDate() != null ? getEventDate().hashCode() : 0);
        result = 31 * result + (getPolicyNo() != null ? getPolicyNo().hashCode() : 0);
        result = 31 * result + (getEndDate() != null ? getEndDate().hashCode() : 0);
        result = 31 * result + (getMobile() != null ? getMobile().hashCode() : 0);
        result = 31 * result + (getRiskCname() != null ? getRiskCname().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SysAppRemind{" +
                "remindId='" + remindId + '\'' +
                ", sendUser='" + sendUser + '\'' +
                ", receivUser='" + receivUser + '\'' +
                ", sendTime=" + sendTime +
                ", remindTitle='" + remindTitle + '\'' +
                ", remindContent='" + remindContent + '\'' +
                ", isRead='" + isRead + '\'' +
                ", remindLevel='" + remindLevel + '\'' +
                ", remindType='" + remindType + '\'' +
                ", remindStat='" + remindStat + '\'' +
                ", createDate='" + createDate + '\'' +
                ", createTime='" + createTime + '\'' +
                ", createUser='" + createUser + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", updateUser='" + updateUser + '\'' +
                ", timeStamp=" + timeStamp +
                ", custNo='" + custNo + '\'' +
                ", custName='" + custName + '\'' +
                ", eventType='" + eventType + '\'' +
                ", eventDate='" + eventDate + '\'' +
                ", policyNo='" + policyNo + '\'' +
                ", endDate='" + endDate + '\'' +
                ", mobile='" + mobile + '\'' +
                ", riskCname='" + riskCname + '\'' +
                '}';
    }
}