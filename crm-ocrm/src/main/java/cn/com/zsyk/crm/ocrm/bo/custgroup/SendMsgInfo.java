package cn.com.zsyk.crm.ocrm.bo.custgroup;

import java.io.Serializable;
import java.util.Date;

import cn.com.zsyk.crm.ocrm.entity.OcDynamSnapshot;

/**
 * 客户群组信息
 * 
 * @author
 *
 */
public class SendMsgInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	// 客户群组Id
	private String groupId;
	// 员工编号：逗号隔开
	private String employeeId;
	// 客户号：逗号隔开
	private String custNo;
	// 信息标题
	private String msgTopic;
	// 发送内容
	private String templateContent;
	// 发送方式
	private String sendWay;
	// 定时时间
	private Date fixTime;

	/**
	 * 群组快照对象
	 */
	private OcDynamSnapshot dynamSnapshot = null;

	public OcDynamSnapshot getDynamSnapshot() {
		return dynamSnapshot;
	}

	public void setDynamSnapshot(OcDynamSnapshot dynamSnapshot) {
		this.dynamSnapshot = dynamSnapshot;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getMsgTopic() {
		return msgTopic;
	}

	public void setMsgTopic(String msgTopic) {
		this.msgTopic = msgTopic;
	}

	public String getTemplateContent() {
		return templateContent;
	}

	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent;
	}

	public String getSendWay() {
		return sendWay;
	}

	public void setSendWay(String sendWay) {
		this.sendWay = sendWay;
	}

	public Date getFixTime() {
		return fixTime;
	}

	public void setFixTime(Date fixTime) {
		this.fixTime = fixTime;
	}
}
