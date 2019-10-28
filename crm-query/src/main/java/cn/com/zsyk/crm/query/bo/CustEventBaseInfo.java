package cn.com.zsyk.crm.query.bo;

public class CustEventBaseInfo {
	// 客户号
	private String custNo;
	// 客户姓名
	private String custName;
	// 客户经理
	private String custAgent;
	// 事件类型
	private String msgEventType;
	// 事件创建事件
	private String createDate;
	// 事件创建人
	private String createUser;
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustAgent() {
		return custAgent;
	}
	public void setCustAgent(String custAgent) {
		this.custAgent = custAgent;
	}
	public String getMsgEventType() {
		return msgEventType;
	}
	public void setMsgEventType(String msgEventType) {
		this.msgEventType = msgEventType;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

}
