package cn.com.zsyk.crm.query.bo;

public class ClaimInfo {
    //理赔信息
    private String registNo;//报案号
    private String policyNo;//保单号
    private String insuredName;//被保险人
    private String riskCname;//险种名称
    private String riskCode;//险种编号
    private String damageDate;//出险日期
    private String reportDate;//报案日期
    private String endcaseDate;//结案日期
    private String reportorName;//报案人
    private String cancelFlag;//案件状态
    private String nodeCname;//案件处理进展
    private String damageAddress;//出险地点
    private String damageName;//事故原因
    private String reportorNumber;//报案人电话
    private String driverName;//驾驶员
    private String linkPhoneNumber;//驾驶员电话
    private String sumpaId;//分项赔付金额
    private String indemnityDate;//赔款日期
    private String indemnityMoney;//赔款赔款金额
    private String indemnityType;//赔款方式
    private String PayMent;//支付对象

    //险别信息
    private String suminSured;//分项保额
    private String grossPremium;//分项保费
    private String kindName;//险别名称

    //收付信息
    private String paymentNo;//缴费单号
    private String payDate;//缴费日期
    private String payFee;//缴费金额
    private String payWay;//缴费方式
    private String seqNo;//业务类型  000承保缴费  不等于000是批单缴费


    //批改项
    private String endorNo;//批单号
    private String fieldName;//批改项名称
    private String oldValue;//批改前值
    private String newValue;//批改后值

    //特别约定
    private String planCode;//险别代码
    private String clauseCode;//特别约定代码
    private String lineNo;//行号
    private String clauseCname;//特别约定名称
    private String clauseContext;//特别约定内容


    public String getRegistNo() {
        return registNo;
    }

    public void setRegistNo(String registNo) {
        this.registNo = registNo;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public String getInsuredName() {
        return insuredName;
    }

    public void setInsuredName(String insuredName) {
        this.insuredName = insuredName;
    }

    public String getRiskCode() {
        return riskCode;
    }

    public void setRiskCode(String riskCode) {
        this.riskCode = riskCode;
    }

    public String getRiskCname() {
        return riskCname;
    }

    public void setRiskCname(String riskCname) {
        this.riskCname = riskCname;
    }

    public String getDamageDate() {
        return damageDate;
    }

    public void setDamageDate(String damageDate) {
        this.damageDate = damageDate;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getEndcaseDate() {
        return endcaseDate;
    }

    public void setEndcaseDate(String endcaseDate) {
        this.endcaseDate = endcaseDate;
    }

    public String getReportorName() {
        return reportorName;
    }

    public void setReportorName(String reportorName) {
        this.reportorName = reportorName;
    }

    public String getCancelFlag() {
        return cancelFlag;
    }

    public void setCancelFlag(String cancelFlag) {
        this.cancelFlag = cancelFlag;
    }

    public String getNodeCname() {
        return nodeCname;
    }

    public void setNodeCname(String nodeCname) {
        this.nodeCname = nodeCname;
    }

    public String getDamageAddress() {
        return damageAddress;
    }

    public void setDamageAddress(String damageAddress) {
        this.damageAddress = damageAddress;
    }

    public String getDamageName() {
        return damageName;
    }

    public void setDamageName(String damageName) {
        this.damageName = damageName;
    }

    public String getReportorNumber() {
        return reportorNumber;
    }

    public void setReportorNumber(String reportorNumber) {
        this.reportorNumber = reportorNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getLinkPhoneNumber() {
        return linkPhoneNumber;
    }

    public void setLinkPhoneNumber(String linkPhoneNumber) {
        this.linkPhoneNumber = linkPhoneNumber;
    }

    public String getSumpaId() {
        return sumpaId;
    }

    public void setSumpaId(String sumpaId) {
        this.sumpaId = sumpaId;
    }

    public String getIndemnityDate() {
        return indemnityDate;
    }

    public void setIndemnityDate(String indemnityDate) {
        this.indemnityDate = indemnityDate;
    }

    public String getIndemnityMoney() {
        return indemnityMoney;
    }

    public void setIndemnityMoney(String indemnityMoney) {
        this.indemnityMoney = indemnityMoney;
    }

    public String getIndemnityType() {
        return indemnityType;
    }

    public void setIndemnityType(String indemnityType) {
        this.indemnityType = indemnityType;
    }

    public String getPayMent() {
        return PayMent;
    }

    public void setPayMent(String payMent) {
        PayMent = payMent;
    }

    public String getSuminSured() {
        return suminSured;
    }

    public void setSuminSured(String suminSured) {
        this.suminSured = suminSured;
    }

    public String getGrossPremium() {
        return grossPremium;
    }

    public void setGrossPremium(String grossPremium) {
        this.grossPremium = grossPremium;
    }

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }

    public String getEndorNo() {
        return endorNo;
    }

    public void setEndorNo(String endorNo) {
        this.endorNo = endorNo;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getPayFee() {
        return payFee;
    }

    public void setPayFee(String payFee) {
        this.payFee = payFee;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getPlanCode() {
        return planCode;
    }

    public void setPlanCode(String planCode) {
        this.planCode = planCode;
    }

    public String getClauseCode() {
        return clauseCode;
    }

    public void setClauseCode(String clauseCode) {
        this.clauseCode = clauseCode;
    }

    public String getClauseCname() {
        return clauseCname;
    }

    public void setClauseCname(String clauseCname) {
        this.clauseCname = clauseCname;
    }

    public String getClauseContext() {
        return clauseContext;
    }

    public void setClauseContext(String clauseContext) {
        this.clauseContext = clauseContext;
    }

    public String getLineNo() {
        return lineNo;
    }

    public void setLineNo(String lineNo) {
        this.lineNo = lineNo;
    }
}
