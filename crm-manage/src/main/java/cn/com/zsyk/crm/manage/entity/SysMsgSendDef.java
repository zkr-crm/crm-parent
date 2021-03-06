package cn.com.zsyk.crm.manage.entity;

import java.io.Serializable;
import java.util.Date;

public class SysMsgSendDef implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_msg_send_def.msg_code
     *
     * @mbggenerated
     */
    private String msgCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_msg_send_def.msg_type
     *
     * @mbggenerated
     */
    private String msgType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_msg_send_def.msg_topic
     *
     * @mbggenerated
     */
    private String msgTopic;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_msg_send_def.template_code
     *
     * @mbggenerated
     */
    private String templateCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_msg_send_def.send_obj
     *
     * @mbggenerated
     */
    private String sendObj;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_msg_send_def.cc
     *
     * @mbggenerated
     */
    private String cc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_msg_send_def.send_channel
     *
     * @mbggenerated
     */
    private String sendChannel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_msg_send_def.send_way
     *
     * @mbggenerated
     */
    private String sendWay;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_msg_send_def.fix_date
     *
     * @mbggenerated
     */
    private Date fixDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_msg_send_def.fix_time
     *
     * @mbggenerated
     */
    private String fixTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_msg_send_def.loop_flag
     *
     * @mbggenerated
     */
    private String loopFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_msg_send_def.loop_type
     *
     * @mbggenerated
     */
    private String loopType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_msg_send_def.msg_order
     *
     * @mbggenerated
     */
    private String msgOrder;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_msg_send_def.resend_times
     *
     * @mbggenerated
     */
    private Integer resendTimes;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_msg_send_def.msg_stat
     *
     * @mbggenerated
     */
    private String msgStat;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_msg_send_def.create_date
     *
     * @mbggenerated
     */
    private String createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_msg_send_def.create_time
     *
     * @mbggenerated
     */
    private String createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_msg_send_def.create_user
     *
     * @mbggenerated
     */
    private String createUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_msg_send_def.update_date
     *
     * @mbggenerated
     */
    private String updateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_msg_send_def.update_time
     *
     * @mbggenerated
     */
    private String updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_msg_send_def.update_user
     *
     * @mbggenerated
     */
    private String updateUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_msg_send_def.time_stamp
     *
     * @mbggenerated
     */
    private Date timeStamp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_msg_send_def.rec_stat
     *
     * @mbggenerated
     */
    private String recStat;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_msg_send_def
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_msg_send_def.msg_code
     *
     * @return the value of sys_msg_send_def.msg_code
     *
     * @mbggenerated
     */
    public String getMsgCode() {
        return msgCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_msg_send_def.msg_code
     *
     * @param msgCode the value for sys_msg_send_def.msg_code
     *
     * @mbggenerated
     */
    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_msg_send_def.msg_type
     *
     * @return the value of sys_msg_send_def.msg_type
     *
     * @mbggenerated
     */
    public String getMsgType() {
        return msgType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_msg_send_def.msg_type
     *
     * @param msgType the value for sys_msg_send_def.msg_type
     *
     * @mbggenerated
     */
    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_msg_send_def.msg_topic
     *
     * @return the value of sys_msg_send_def.msg_topic
     *
     * @mbggenerated
     */
    public String getMsgTopic() {
        return msgTopic;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_msg_send_def.msg_topic
     *
     * @param msgTopic the value for sys_msg_send_def.msg_topic
     *
     * @mbggenerated
     */
    public void setMsgTopic(String msgTopic) {
        this.msgTopic = msgTopic;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_msg_send_def.template_code
     *
     * @return the value of sys_msg_send_def.template_code
     *
     * @mbggenerated
     */
    public String getTemplateCode() {
        return templateCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_msg_send_def.template_code
     *
     * @param templateCode the value for sys_msg_send_def.template_code
     *
     * @mbggenerated
     */
    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_msg_send_def.send_obj
     *
     * @return the value of sys_msg_send_def.send_obj
     *
     * @mbggenerated
     */
    public String getSendObj() {
        return sendObj;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_msg_send_def.send_obj
     *
     * @param sendObj the value for sys_msg_send_def.send_obj
     *
     * @mbggenerated
     */
    public void setSendObj(String sendObj) {
        this.sendObj = sendObj;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_msg_send_def.cc
     *
     * @return the value of sys_msg_send_def.cc
     *
     * @mbggenerated
     */
    public String getCc() {
        return cc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_msg_send_def.cc
     *
     * @param cc the value for sys_msg_send_def.cc
     *
     * @mbggenerated
     */
    public void setCc(String cc) {
        this.cc = cc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_msg_send_def.send_channel
     *
     * @return the value of sys_msg_send_def.send_channel
     *
     * @mbggenerated
     */
    public String getSendChannel() {
        return sendChannel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_msg_send_def.send_channel
     *
     * @param sendChannel the value for sys_msg_send_def.send_channel
     *
     * @mbggenerated
     */
    public void setSendChannel(String sendChannel) {
        this.sendChannel = sendChannel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_msg_send_def.send_way
     *
     * @return the value of sys_msg_send_def.send_way
     *
     * @mbggenerated
     */
    public String getSendWay() {
        return sendWay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_msg_send_def.send_way
     *
     * @param sendWay the value for sys_msg_send_def.send_way
     *
     * @mbggenerated
     */
    public void setSendWay(String sendWay) {
        this.sendWay = sendWay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_msg_send_def.fix_date
     *
     * @return the value of sys_msg_send_def.fix_date
     *
     * @mbggenerated
     */
    public Date getFixDate() {
        return fixDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_msg_send_def.fix_date
     *
     * @param fixDate the value for sys_msg_send_def.fix_date
     *
     * @mbggenerated
     */
    public void setFixDate(Date fixDate) {
        this.fixDate = fixDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_msg_send_def.fix_time
     *
     * @return the value of sys_msg_send_def.fix_time
     *
     * @mbggenerated
     */
    public String getFixTime() {
        return fixTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_msg_send_def.fix_time
     *
     * @param fixTime the value for sys_msg_send_def.fix_time
     *
     * @mbggenerated
     */
    public void setFixTime(String fixTime) {
        this.fixTime = fixTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_msg_send_def.loop_flag
     *
     * @return the value of sys_msg_send_def.loop_flag
     *
     * @mbggenerated
     */
    public String getLoopFlag() {
        return loopFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_msg_send_def.loop_flag
     *
     * @param loopFlag the value for sys_msg_send_def.loop_flag
     *
     * @mbggenerated
     */
    public void setLoopFlag(String loopFlag) {
        this.loopFlag = loopFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_msg_send_def.loop_type
     *
     * @return the value of sys_msg_send_def.loop_type
     *
     * @mbggenerated
     */
    public String getLoopType() {
        return loopType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_msg_send_def.loop_type
     *
     * @param loopType the value for sys_msg_send_def.loop_type
     *
     * @mbggenerated
     */
    public void setLoopType(String loopType) {
        this.loopType = loopType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_msg_send_def.msg_order
     *
     * @return the value of sys_msg_send_def.msg_order
     *
     * @mbggenerated
     */
    public String getMsgOrder() {
        return msgOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_msg_send_def.msg_order
     *
     * @param msgOrder the value for sys_msg_send_def.msg_order
     *
     * @mbggenerated
     */
    public void setMsgOrder(String msgOrder) {
        this.msgOrder = msgOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_msg_send_def.resend_times
     *
     * @return the value of sys_msg_send_def.resend_times
     *
     * @mbggenerated
     */
    public Integer getResendTimes() {
        return resendTimes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_msg_send_def.resend_times
     *
     * @param resendTimes the value for sys_msg_send_def.resend_times
     *
     * @mbggenerated
     */
    public void setResendTimes(Integer resendTimes) {
        this.resendTimes = resendTimes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_msg_send_def.msg_stat
     *
     * @return the value of sys_msg_send_def.msg_stat
     *
     * @mbggenerated
     */
    public String getMsgStat() {
        return msgStat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_msg_send_def.msg_stat
     *
     * @param msgStat the value for sys_msg_send_def.msg_stat
     *
     * @mbggenerated
     */
    public void setMsgStat(String msgStat) {
        this.msgStat = msgStat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_msg_send_def.create_date
     *
     * @return the value of sys_msg_send_def.create_date
     *
     * @mbggenerated
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_msg_send_def.create_date
     *
     * @param createDate the value for sys_msg_send_def.create_date
     *
     * @mbggenerated
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_msg_send_def.create_time
     *
     * @return the value of sys_msg_send_def.create_time
     *
     * @mbggenerated
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_msg_send_def.create_time
     *
     * @param createTime the value for sys_msg_send_def.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_msg_send_def.create_user
     *
     * @return the value of sys_msg_send_def.create_user
     *
     * @mbggenerated
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_msg_send_def.create_user
     *
     * @param createUser the value for sys_msg_send_def.create_user
     *
     * @mbggenerated
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_msg_send_def.update_date
     *
     * @return the value of sys_msg_send_def.update_date
     *
     * @mbggenerated
     */
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_msg_send_def.update_date
     *
     * @param updateDate the value for sys_msg_send_def.update_date
     *
     * @mbggenerated
     */
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_msg_send_def.update_time
     *
     * @return the value of sys_msg_send_def.update_time
     *
     * @mbggenerated
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_msg_send_def.update_time
     *
     * @param updateTime the value for sys_msg_send_def.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_msg_send_def.update_user
     *
     * @return the value of sys_msg_send_def.update_user
     *
     * @mbggenerated
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_msg_send_def.update_user
     *
     * @param updateUser the value for sys_msg_send_def.update_user
     *
     * @mbggenerated
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_msg_send_def.time_stamp
     *
     * @return the value of sys_msg_send_def.time_stamp
     *
     * @mbggenerated
     */
    public Date getTimeStamp() {
        return timeStamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_msg_send_def.time_stamp
     *
     * @param timeStamp the value for sys_msg_send_def.time_stamp
     *
     * @mbggenerated
     */
    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_msg_send_def.rec_stat
     *
     * @return the value of sys_msg_send_def.rec_stat
     *
     * @mbggenerated
     */
    public String getRecStat() {
        return recStat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_msg_send_def.rec_stat
     *
     * @param recStat the value for sys_msg_send_def.rec_stat
     *
     * @mbggenerated
     */
    public void setRecStat(String recStat) {
        this.recStat = recStat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_msg_send_def
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
        SysMsgSendDef other = (SysMsgSendDef) that;
        return (this.getMsgCode() == null ? other.getMsgCode() == null : this.getMsgCode().equals(other.getMsgCode()))
            && (this.getMsgType() == null ? other.getMsgType() == null : this.getMsgType().equals(other.getMsgType()))
            && (this.getMsgTopic() == null ? other.getMsgTopic() == null : this.getMsgTopic().equals(other.getMsgTopic()))
            && (this.getTemplateCode() == null ? other.getTemplateCode() == null : this.getTemplateCode().equals(other.getTemplateCode()))
            && (this.getSendObj() == null ? other.getSendObj() == null : this.getSendObj().equals(other.getSendObj()))
            && (this.getCc() == null ? other.getCc() == null : this.getCc().equals(other.getCc()))
            && (this.getSendChannel() == null ? other.getSendChannel() == null : this.getSendChannel().equals(other.getSendChannel()))
            && (this.getSendWay() == null ? other.getSendWay() == null : this.getSendWay().equals(other.getSendWay()))
            && (this.getFixDate() == null ? other.getFixDate() == null : this.getFixDate().equals(other.getFixDate()))
            && (this.getFixTime() == null ? other.getFixTime() == null : this.getFixTime().equals(other.getFixTime()))
            && (this.getLoopFlag() == null ? other.getLoopFlag() == null : this.getLoopFlag().equals(other.getLoopFlag()))
            && (this.getLoopType() == null ? other.getLoopType() == null : this.getLoopType().equals(other.getLoopType()))
            && (this.getMsgOrder() == null ? other.getMsgOrder() == null : this.getMsgOrder().equals(other.getMsgOrder()))
            && (this.getResendTimes() == null ? other.getResendTimes() == null : this.getResendTimes().equals(other.getResendTimes()))
            && (this.getMsgStat() == null ? other.getMsgStat() == null : this.getMsgStat().equals(other.getMsgStat()))
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
     * This method corresponds to the database table sys_msg_send_def
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMsgCode() == null) ? 0 : getMsgCode().hashCode());
        result = prime * result + ((getMsgType() == null) ? 0 : getMsgType().hashCode());
        result = prime * result + ((getMsgTopic() == null) ? 0 : getMsgTopic().hashCode());
        result = prime * result + ((getTemplateCode() == null) ? 0 : getTemplateCode().hashCode());
        result = prime * result + ((getSendObj() == null) ? 0 : getSendObj().hashCode());
        result = prime * result + ((getCc() == null) ? 0 : getCc().hashCode());
        result = prime * result + ((getSendChannel() == null) ? 0 : getSendChannel().hashCode());
        result = prime * result + ((getSendWay() == null) ? 0 : getSendWay().hashCode());
        result = prime * result + ((getFixDate() == null) ? 0 : getFixDate().hashCode());
        result = prime * result + ((getFixTime() == null) ? 0 : getFixTime().hashCode());
        result = prime * result + ((getLoopFlag() == null) ? 0 : getLoopFlag().hashCode());
        result = prime * result + ((getLoopType() == null) ? 0 : getLoopType().hashCode());
        result = prime * result + ((getMsgOrder() == null) ? 0 : getMsgOrder().hashCode());
        result = prime * result + ((getResendTimes() == null) ? 0 : getResendTimes().hashCode());
        result = prime * result + ((getMsgStat() == null) ? 0 : getMsgStat().hashCode());
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
     * This method corresponds to the database table sys_msg_send_def
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", msgCode=").append(msgCode);
        sb.append(", msgType=").append(msgType);
        sb.append(", msgTopic=").append(msgTopic);
        sb.append(", templateCode=").append(templateCode);
        sb.append(", sendObj=").append(sendObj);
        sb.append(", cc=").append(cc);
        sb.append(", sendChannel=").append(sendChannel);
        sb.append(", sendWay=").append(sendWay);
        sb.append(", fixDate=").append(fixDate);
        sb.append(", fixTime=").append(fixTime);
        sb.append(", loopFlag=").append(loopFlag);
        sb.append(", loopType=").append(loopType);
        sb.append(", msgOrder=").append(msgOrder);
        sb.append(", resendTimes=").append(resendTimes);
        sb.append(", msgStat=").append(msgStat);
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
}