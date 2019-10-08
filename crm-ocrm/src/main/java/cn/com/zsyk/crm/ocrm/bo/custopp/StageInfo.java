package cn.com.zsyk.crm.ocrm.bo.custopp;


public class StageInfo {
	 // 商机编号
	private String busiOppNo;
	private String busiOppNam;
	// 新商机阶段状态
	private String newStage;
	 // 原商机阶段状态
	private String oldStage;
	 // 原因
	private String reason;
	// 负责人
	private String custAgent;

	//保单号
	private String policyNo;
	// 产品编码
	private String productCode;
	//保费
	private String policyPrem;
	//签单日期
	private String signDate;

	public String getBusiOppNo() {
		return busiOppNo;
	}
	
	public void setBusiOppNo(String busiOppNo) {
		this.busiOppNo = busiOppNo;
	}
	
	public String getNewStage() {
		return newStage;
	}
	
	public void setNewStage(String newStage) {
		this.newStage = newStage;
	}
	
	public String getOldStage() {
		return oldStage;
	}
	
	public void setOldStage(String oldStage) {
		this.oldStage = oldStage;
	}
	
	public String getReason() {
		return reason;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}

	
	public String getCustAgent() {
		return custAgent;
	}

	
	public void setCustAgent(String custAgent) {
		this.custAgent = custAgent;
	}

	
	public String getPolicyNo() {
		return policyNo;
	}

	
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	
	public String getProductCode() {
		return productCode;
	}

	
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	
	public String getPolicyPrem() {
		return policyPrem;
	}

	
	public void setPolicyPrem(String policyPrem) {
		this.policyPrem = policyPrem;
	}

	
	public String getSignDate() {
		return signDate;
	}

	
	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}

	
	public String getBusiOppNam() {
		return busiOppNam;
	}

	
	public void setBusiOppNam(String busiOppNam) {
		this.busiOppNam = busiOppNam;
	}
	
}
