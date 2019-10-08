package cn.com.zsyk.crm.manage.entity;

import java.io.Serializable;
import java.util.Date;

public class SysSmsSend implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_sms_send.msg_id
     *
     * @mbggenerated
     */
    private Long msgId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_sms_send.msg_code
     *
     * @mbggenerated
     */
    private String msgCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_sms_send.msg_topic
     *
     * @mbggenerated
     */
    private String msgTopic;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_sms_send.send_obj
     *
     * @mbggenerated
     */
    private String sendObj;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_sms_send.biz_no
     *
     * @mbggenerated
     */
    private String bizNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_sms_send.cust_name
     *
     * @mbggenerated
     */
    private String custName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_sms_send.mobile
     *
     * @mbggenerated
     */
    private String mobile;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_sms_send.send_way
     *
     * @mbggenerated
     */
    private String sendWay;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_sms_send.fix_date
     *
     * @mbggenerated
     */
    private Date fixDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_sms_send.fix_time
     *
     * @mbggenerated
     */
    private String fixTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_sms_send.loop_flag
     *
     * @mbggenerated
     */
    private String loopFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_sms_send.loop_type
     *
     * @mbggenerated
     */
    private String loopType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_sms_send.msg_order
     *
     * @mbggenerated
     */
    private String msgOrder;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_sms_send.resend_times
     *
     * @mbggenerated
     */
    private Integer resendTimes;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_sms_send.enter_code
     *
     * @mbggenerated
     */
    private String enterCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_sms_send.dept_code
     *
     * @mbggenerated
     */
    private String deptCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_sms_send.msg_stat
     *
     * @mbggenerated
     */
    private String msgStat;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_sms_send.send_time
     *
     * @mbggenerated
     */
    private Date sendTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_sms_send.fail_reason
     *
     * @mbggenerated
     */
    private String failReason;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_sms_send.create_date
     *
     * @mbggenerated
     */
    private String createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_sms_send.create_time
     *
     * @mbggenerated
     */
    private String createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_sms_send.create_user
     *
     * @mbggenerated
     */
    private String createUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_sms_send.update_date
     *
     * @mbggenerated
     */
    private String updateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_sms_send.update_time
     *
     * @mbggenerated
     */
    private String updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_sms_send.update_user
     *
     * @mbggenerated
     */
    private String updateUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_sms_send.time_stamp
     *
     * @mbggenerated
     */
    private Date timeStamp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_sms_send.rec_stat
     *
     * @mbggenerated
     */
    private String recStat;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_sms_send.msg_content
     *
     * @mbggenerated
     */
    private String msgContent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_sms_send
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_sms_send.msg_id
     *
     * @return the value of sys_sms_send.msg_id
     *
     * @mbggenerated
     */
    public Long getMsgId() {
        return msgId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_sms_send.msg_id
     *
     * @param msgId the value for sys_sms_send.msg_id
     *
     * @mbggenerated
     */
    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_sms_send.msg_code
     *
     * @return the value of sys_sms_send.msg_code
     *
     * @mbggenerated
     */
    public String getMsgCode() {
        return msgCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_sms_send.msg_code
     *
     * @param msgCode the value for sys_sms_send.msg_code
     *
     * @mbggenerated
     */
    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_sms_send.msg_topic
     *
     * @return the value of sys_sms_send.msg_topic
     *
     * @mbggenerated
     */
    public String getMsgTopic() {
        return msgTopic;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_sms_send.msg_topic
     *
     * @param msgTopic the value for sys_sms_send.msg_topic
     *
     * @mbggenerated
     */
    public void setMsgTopic(String msgTopic) {
        this.msgTopic = msgTopic;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_sms_send.send_obj
     *
     * @return the value of sys_sms_send.send_obj
     *
     * @mbggenerated
     */
    public String getSendObj() {
        return sendObj;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_sms_send.send_obj
     *
     * @param sendObj the value for sys_sms_send.send_obj
     *
     * @mbggenerated
     */
    public void setSendObj(String sendObj) {
        this.sendObj = sendObj;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_sms_send.biz_no
     *
     * @return the value of sys_sms_send.biz_no
     *
     * @mbggenerated
     */
    public String getBizNo() {
        return bizNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_sms_send.biz_no
     *
     * @param bizNo the value for sys_sms_send.biz_no
     *
     * @mbggenerated
     */
    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_sms_send.cust_name
     *
     * @return the value of sys_sms_send.cust_name
     *
     * @mbggenerated
     */
    public String getCustName() {
        return custName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_sms_send.cust_name
     *
     * @param custName the value for sys_sms_send.cust_name
     *
     * @mbggenerated
     */
    public void setCustName(String custName) {
        this.custName = custName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_sms_send.mobile
     *
     * @return the value of sys_sms_send.mobile
     *
     * @mbggenerated
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_sms_send.mobile
     *
     * @param mobile the value for sys_sms_send.mobile
     *
     * @mbggenerated
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_sms_send.send_way
     *
     * @return the value of sys_sms_send.send_way
     *
     * @mbggenerated
     */
    public String getSendWay() {
        return sendWay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_sms_send.send_way
     *
     * @param sendWay the value for sys_sms_send.send_way
     *
     * @mbggenerated
     */
    public void setSendWay(String sendWay) {
        this.sendWay = sendWay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_sms_send.fix_date
     *
     * @return the value of sys_sms_send.fix_date
     *
     * @mbggenerated
     */
    public Date getFixDate() {
        return fixDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_sms_send.fix_date
     *
     * @param fixDate the value for sys_sms_send.fix_date
     *
     * @mbggenerated
     */
    public void setFixDate(Date fixDate) {
        this.fixDate = fixDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_sms_send.fix_time
     *
     * @return the value of sys_sms_send.fix_time
     *
     * @mbggenerated
     */
    public String getFixTime() {
        return fixTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_sms_send.fix_time
     *
     * @param fixTime the value for sys_sms_send.fix_time
     *
     * @mbggenerated
     */
    public void setFixTime(String fixTime) {
        this.fixTime = fixTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_sms_send.loop_flag
     *
     * @return the value of sys_sms_send.loop_flag
     *
     * @mbggenerated
     */
    public String getLoopFlag() {
        return loopFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_sms_send.loop_flag
     *
     * @param loopFlag the value for sys_sms_send.loop_flag
     *
     * @mbggenerated
     */
    public void setLoopFlag(String loopFlag) {
        this.loopFlag = loopFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_sms_send.loop_type
     *
     * @return the value of sys_sms_send.loop_type
     *
     * @mbggenerated
     */
    public String getLoopType() {
        return loopType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_sms_send.loop_type
     *
     * @param loopType the value for sys_sms_send.loop_type
     *
     * @mbggenerated
     */
    public void setLoopType(String loopType) {
        this.loopType = loopType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_sms_send.msg_order
     *
     * @return the value of sys_sms_send.msg_order
     *
     * @mbggenerated
     */
    public String getMsgOrder() {
        return msgOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_sms_send.msg_order
     *
     * @param msgOrder the value for sys_sms_send.msg_order
     *
     * @mbggenerated
     */
    public void setMsgOrder(String msgOrder) {
        this.msgOrder = msgOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_sms_send.resend_times
     *
     * @return the value of sys_sms_send.resend_times
     *
     * @mbggenerated
     */
    public Integer getResendTimes() {
        return resendTimes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_sms_send.resend_times
     *
     * @param resendTimes the value for sys_sms_send.resend_times
     *
     * @mbggenerated
     */
    public void setResendTimes(Integer resendTimes) {
        this.resendTimes = resendTimes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_sms_send.enter_code
     *
     * @return the value of sys_sms_send.enter_code
     *
     * @mbggenerated
     */
    public String getEnterCode() {
        return enterCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_sms_send.enter_code
     *
     * @param enterCode the value for sys_sms_send.enter_code
     *
     * @mbggenerated
     */
    public void setEnterCode(String enterCode) {
        this.enterCode = enterCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_sms_send.dept_code
     *
     * @return the value of sys_sms_send.dept_code
     *
     * @mbggenerated
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_sms_send.dept_code
     *
     * @param deptCode the value for sys_sms_send.dept_code
     *
     * @mbggenerated
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_sms_send.msg_stat
     *
     * @return the value of sys_sms_send.msg_stat
     *
     * @mbggenerated
     */
    public String getMsgStat() {
        return msgStat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_sms_send.msg_stat
     *
     * @param msgStat the value for sys_sms_send.msg_stat
     *
     * @mbggenerated
     */
    public void setMsgStat(String msgStat) {
        this.msgStat = msgStat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_sms_send.send_time
     *
     * @return the value of sys_sms_send.send_time
     *
     * @mbggenerated
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_sms_send.send_time
     *
     * @param sendTime the value for sys_sms_send.send_time
     *
     * @mbggenerated
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_sms_send.fail_reason
     *
     * @return the value of sys_sms_send.fail_reason
     *
     * @mbggenerated
     */
    public String getFailReason() {
        return failReason;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_sms_send.fail_reason
     *
     * @param failReason the value for sys_sms_send.fail_reason
     *
     * @mbggenerated
     */
    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_sms_send.create_date
     *
     * @return the value of sys_sms_send.create_date
     *
     * @mbggenerated
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_sms_send.create_date
     *
     * @param createDate the value for sys_sms_send.create_date
     *
     * @mbggenerated
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_sms_send.create_time
     *
     * @return the value of sys_sms_send.create_time
     *
     * @mbggenerated
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_sms_send.create_time
     *
     * @param createTime the value for sys_sms_send.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_sms_send.create_user
     *
     * @return the value of sys_sms_send.create_user
     *
     * @mbggenerated
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_sms_send.create_user
     *
     * @param createUser the value for sys_sms_send.create_user
     *
     * @mbggenerated
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_sms_send.update_date
     *
     * @return the value of sys_sms_send.update_date
     *
     * @mbggenerated
     */
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_sms_send.update_date
     *
     * @param updateDate the value for sys_sms_send.update_date
     *
     * @mbggenerated
     */
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_sms_send.update_time
     *
     * @return the value of sys_sms_send.update_time
     *
     * @mbggenerated
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_sms_send.update_time
     *
     * @param updateTime the value for sys_sms_send.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_sms_send.update_user
     *
     * @return the value of sys_sms_send.update_user
     *
     * @mbggenerated
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_sms_send.update_user
     *
     * @param updateUser the value for sys_sms_send.update_user
     *
     * @mbggenerated
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_sms_send.time_stamp
     *
     * @return the value of sys_sms_send.time_stamp
     *
     * @mbggenerated
     */
    public Date getTimeStamp() {
        return timeStamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_sms_send.time_stamp
     *
     * @param timeStamp the value for sys_sms_send.time_stamp
     *
     * @mbggenerated
     */
    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_sms_send.rec_stat
     *
     * @return the value of sys_sms_send.rec_stat
     *
     * @mbggenerated
     */
    public String getRecStat() {
        return recStat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_sms_send.rec_stat
     *
     * @param recStat the value for sys_sms_send.rec_stat
     *
     * @mbggenerated
     */
    public void setRecStat(String recStat) {
        this.recStat = recStat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_sms_send.msg_content
     *
     * @return the value of sys_sms_send.msg_content
     *
     * @mbggenerated
     */
    public String getMsgContent() {
        return msgContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_sms_send.msg_content
     *
     * @param msgContent the value for sys_sms_send.msg_content
     *
     * @mbggenerated
     */
    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sms_send
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
        SysSmsSend other = (SysSmsSend) that;
        return (this.getMsgId() == null ? other.getMsgId() == null : this.getMsgId().equals(other.getMsgId()))
            && (this.getMsgCode() == null ? other.getMsgCode() == null : this.getMsgCode().equals(other.getMsgCode()))
            && (this.getMsgTopic() == null ? other.getMsgTopic() == null : this.getMsgTopic().equals(other.getMsgTopic()))
            && (this.getSendObj() == null ? other.getSendObj() == null : this.getSendObj().equals(other.getSendObj()))
            && (this.getBizNo() == null ? other.getBizNo() == null : this.getBizNo().equals(other.getBizNo()))
            && (this.getCustName() == null ? other.getCustName() == null : this.getCustName().equals(other.getCustName()))
            && (this.getMobile() == null ? other.getMobile() == null : this.getMobile().equals(other.getMobile()))
            && (this.getSendWay() == null ? other.getSendWay() == null : this.getSendWay().equals(other.getSendWay()))
            && (this.getFixDate() == null ? other.getFixDate() == null : this.getFixDate().equals(other.getFixDate()))
            && (this.getFixTime() == null ? other.getFixTime() == null : this.getFixTime().equals(other.getFixTime()))
            && (this.getLoopFlag() == null ? other.getLoopFlag() == null : this.getLoopFlag().equals(other.getLoopFlag()))
            && (this.getLoopType() == null ? other.getLoopType() == null : this.getLoopType().equals(other.getLoopType()))
            && (this.getMsgOrder() == null ? other.getMsgOrder() == null : this.getMsgOrder().equals(other.getMsgOrder()))
            && (this.getResendTimes() == null ? other.getResendTimes() == null : this.getResendTimes().equals(other.getResendTimes()))
            && (this.getEnterCode() == null ? other.getEnterCode() == null : this.getEnterCode().equals(other.getEnterCode()))
            && (this.getDeptCode() == null ? other.getDeptCode() == null : this.getDeptCode().equals(other.getDeptCode()))
            && (this.getMsgStat() == null ? other.getMsgStat() == null : this.getMsgStat().equals(other.getMsgStat()))
            && (this.getSendTime() == null ? other.getSendTime() == null : this.getSendTime().equals(other.getSendTime()))
            && (this.getFailReason() == null ? other.getFailReason() == null : this.getFailReason().equals(other.getFailReason()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
            && (this.getTimeStamp() == null ? other.getTimeStamp() == null : this.getTimeStamp().equals(other.getTimeStamp()))
            && (this.getRecStat() == null ? other.getRecStat() == null : this.getRecStat().equals(other.getRecStat()))
            && (this.getMsgContent() == null ? other.getMsgContent() == null : this.getMsgContent().equals(other.getMsgContent()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sms_send
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMsgId() == null) ? 0 : getMsgId().hashCode());
        result = prime * result + ((getMsgCode() == null) ? 0 : getMsgCode().hashCode());
        result = prime * result + ((getMsgTopic() == null) ? 0 : getMsgTopic().hashCode());
        result = prime * result + ((getSendObj() == null) ? 0 : getSendObj().hashCode());
        result = prime * result + ((getBizNo() == null) ? 0 : getBizNo().hashCode());
        result = prime * result + ((getCustName() == null) ? 0 : getCustName().hashCode());
        result = prime * result + ((getMobile() == null) ? 0 : getMobile().hashCode());
        result = prime * result + ((getSendWay() == null) ? 0 : getSendWay().hashCode());
        result = prime * result + ((getFixDate() == null) ? 0 : getFixDate().hashCode());
        result = prime * result + ((getFixTime() == null) ? 0 : getFixTime().hashCode());
        result = prime * result + ((getLoopFlag() == null) ? 0 : getLoopFlag().hashCode());
        result = prime * result + ((getLoopType() == null) ? 0 : getLoopType().hashCode());
        result = prime * result + ((getMsgOrder() == null) ? 0 : getMsgOrder().hashCode());
        result = prime * result + ((getResendTimes() == null) ? 0 : getResendTimes().hashCode());
        result = prime * result + ((getEnterCode() == null) ? 0 : getEnterCode().hashCode());
        result = prime * result + ((getDeptCode() == null) ? 0 : getDeptCode().hashCode());
        result = prime * result + ((getMsgStat() == null) ? 0 : getMsgStat().hashCode());
        result = prime * result + ((getSendTime() == null) ? 0 : getSendTime().hashCode());
        result = prime * result + ((getFailReason() == null) ? 0 : getFailReason().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        result = prime * result + ((getTimeStamp() == null) ? 0 : getTimeStamp().hashCode());
        result = prime * result + ((getRecStat() == null) ? 0 : getRecStat().hashCode());
        result = prime * result + ((getMsgContent() == null) ? 0 : getMsgContent().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sms_send
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", msgId=").append(msgId);
        sb.append(", msgCode=").append(msgCode);
        sb.append(", msgTopic=").append(msgTopic);
        sb.append(", sendObj=").append(sendObj);
        sb.append(", bizNo=").append(bizNo);
        sb.append(", custName=").append(custName);
        sb.append(", mobile=").append(mobile);
        sb.append(", sendWay=").append(sendWay);
        sb.append(", fixDate=").append(fixDate);
        sb.append(", fixTime=").append(fixTime);
        sb.append(", loopFlag=").append(loopFlag);
        sb.append(", loopType=").append(loopType);
        sb.append(", msgOrder=").append(msgOrder);
        sb.append(", resendTimes=").append(resendTimes);
        sb.append(", enterCode=").append(enterCode);
        sb.append(", deptCode=").append(deptCode);
        sb.append(", msgStat=").append(msgStat);
        sb.append(", sendTime=").append(sendTime);
        sb.append(", failReason=").append(failReason);
        sb.append(", createDate=").append(createDate);
        sb.append(", createTime=").append(createTime);
        sb.append(", createUser=").append(createUser);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", timeStamp=").append(timeStamp);
        sb.append(", recStat=").append(recStat);
        sb.append(", msgContent=").append(msgContent);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}