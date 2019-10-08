package cn.com.zsyk.crm.ocrm.bo.manage;

import java.io.Serializable;

public class MsgInfo implements Serializable {

	/**
	 * 信息编码
	 */
	private String msgCode;

	/**
	 * 发送渠道
	 */
	private String channel;

	/**
	 * 信息类型 0-手动 1-自动
	 */
	private String msgType;

	/**
	 * 客群编码
	 */
	private String custGroupCode;

	/**
	 * 业务员编号
	 */
	private String busiCode;

	/**
	 * 客户姓名
	 */
	private String custName;

	/**
	 * 信息主题
	 */
	private String msgTopic;

	/**
	 * 信息内容
	 */
	private String msgContent;

	/**
	 * 发送方式：定时、直接
	 */
	private String sendWay;

	/**
	 * 手机号码
	 */
	private String mobile;

	/**
	 * 邮件地址
	 */
	private String emailAddr;

	/**
	 * 微信号
	 */
	private String weChat;

	/**
	 * 替换占位符的参数串-各参数按顺序以逗号隔开的字符串
	 *
	 */
	private String params;

	private static final long serialVersionUID = 1L;

	public String getCustGroupCode() {
		return custGroupCode;
	}

	public void setCustGroupCode(String custGroupCode) {
		this.custGroupCode = custGroupCode;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getBusiCode() {
		return busiCode;
	}

	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getMsgTopic() {
		return msgTopic;
	}

	public void setMsgTopic(String msgTopic) {
		this.msgTopic = msgTopic;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public String getSendWay() {
		return sendWay;
	}

	public void setSendWay(String sendWay) {
		this.sendWay = sendWay;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmailAddr() {
		return emailAddr;
	}

	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}

	public String getWeChat() {
		return weChat;
	}

	public void setWeChat(String weChat) {
		this.weChat = weChat;
	}

	public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}
}