package cn.com.zsyk.crm.ecif.bo.cust;


public class CustSumzInfo {
	// 保费
	private String premium;
	// 消费次数
	private String consumeCnt;
	// 首保日期
	private String firstPreDate;
	// 跟进日期
	private String followUpDate;
	// 跟进人
	private String followUpUser;
	
	public String getPremium() {
		return premium;
	}
	
	public void setPremium(String premium) {
		this.premium = premium;
	}
	
	public String getConsumeCnt() {
		return consumeCnt;
	}
	
	public void setConsumeCnt(String consumeCnt) {
		this.consumeCnt = consumeCnt;
	}
	
	public String getFirstPreDate() {
		return firstPreDate;
	}
	
	public void setFirstPreDate(String firstPreDate) {
		this.firstPreDate = firstPreDate;
	}
	
	public String getFollowUpDate() {
		return followUpDate;
	}
	
	public void setFollowUpDate(String followUpDate) {
		this.followUpDate = followUpDate;
	}
	
	public String getFollowUpUser() {
		return followUpUser;
	}
	
	public void setFollowUpUser(String followUpUser) {
		this.followUpUser = followUpUser;
	}

}
