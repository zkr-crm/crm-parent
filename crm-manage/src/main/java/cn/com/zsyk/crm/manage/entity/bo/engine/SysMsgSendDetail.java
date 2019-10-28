package cn.com.zsyk.crm.manage.entity.bo.engine;

import java.io.Serializable;
import java.util.Date;

public class SysMsgSendDetail implements Serializable {

    private String msgCode;
    private String msgType;
    private String msgTopic;
    private String templateCode;
    private String sendObj;
    private String cc;
    private String sendChannel;
    private String sendWay; 
    private Date fixDate;
    private String fixTime;
    private String loopFlag; 
    private String loopType;
    private String msgOrder;  
    private Integer resendTimes; 
    private String msgStat;
    private String createDate;
    private String createTime; 
    private String createUser;
    private String updateDate;   
    private String updateTime;
    private String updateUser;
    private Date timeStamp;
    private String recStat;    
    private String tplCont;
    
    public String getTplCont() {
		return tplCont;
	}

	public void setTplCont(String tplCont) {
		this.tplCont = tplCont;
	}

    private static final long serialVersionUID = 1L;


    public String getMsgCode() {
        return msgCode;
    }


    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

 
    public String getMsgType() {
        return msgType;
    }


    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }


    public String getMsgTopic() {
        return msgTopic;
    }


    public void setMsgTopic(String msgTopic) {
        this.msgTopic = msgTopic;
    }

    public String getTemplateCode() {
        return templateCode;
    }

 
    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getSendObj() {
        return sendObj;
    }


    public void setSendObj(String sendObj) {
        this.sendObj = sendObj;
    }


    public String getCc() {
        return cc;
    }


    public void setCc(String cc) {
        this.cc = cc;
    }


    public String getSendChannel() {
        return sendChannel;
    }

  
    public void setSendChannel(String sendChannel) {
        this.sendChannel = sendChannel;
    }

 
    public String getSendWay() {
        return sendWay;
    }

 
    public void setSendWay(String sendWay) {
        this.sendWay = sendWay;
    }


    public Date getFixDate() {
        return fixDate;
    }


    public void setFixDate(Date fixDate) {
        this.fixDate = fixDate;
    }

    public String getFixTime() {
        return fixTime;
    }

    public void setFixTime(String fixTime) {
        this.fixTime = fixTime;
    }

    public String getLoopFlag() {
        return loopFlag;
    }

    public void setLoopFlag(String loopFlag) {
        this.loopFlag = loopFlag;
    }

    public String getLoopType() {
        return loopType;
    }
    public void setLoopType(String loopType) {
        this.loopType = loopType;
    }

    public String getMsgOrder() {
        return msgOrder;
    }

    public void setMsgOrder(String msgOrder) {
        this.msgOrder = msgOrder;
    }

    public Integer getResendTimes() {
        return resendTimes;
    }

    public void setResendTimes(Integer resendTimes) {
        this.resendTimes = resendTimes;
    }

    public String getMsgStat() {
        return msgStat;
    }

    public void setMsgStat(String msgStat) {
        this.msgStat = msgStat;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getRecStat() {
        return recStat;
    }

    public void setRecStat(String recStat) {
        this.recStat = recStat;
    }

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
        SysMsgSendDetail other = (SysMsgSendDetail) that;
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
            && (this.getTplCont() == null ? other.getTplCont() == null : this.getTplCont().equals(other.getTplCont()))
            && (this.getRecStat() == null ? other.getRecStat() == null : this.getRecStat().equals(other.getRecStat()));
    }

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
        result = prime * result + ((getTplCont() == null) ? 0 : getTplCont().hashCode());
        result = prime * result + ((getRecStat() == null) ? 0 : getRecStat().hashCode());
        return result;
    }

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
        sb.append(", tplCont=").append(tplCont);
        sb.append(", recStat=").append(recStat);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}