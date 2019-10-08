package cn.com.zsyk.crm.ecif.bo.cust;

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
	 * 客群编码
	 */
	private String custGroupCode;

	/**
	 * 业务编号 （员工号或客户号）
	 */
	private String busiCode;

	/**
	 * 发送方编号
	 */
	private String sendUser;

	/**
	 * 客户姓名
	 */
	private String custName;

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

	public String getBusiCode() {
		return busiCode;
	}

	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}

	public String getSendUser() {
		return sendUser;
	}

	public void setSendUser(String sendUser) {
		this.sendUser = sendUser;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
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