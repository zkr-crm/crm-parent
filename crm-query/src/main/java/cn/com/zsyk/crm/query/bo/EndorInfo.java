package cn.com.zsyk.crm.query.bo;

public class EndorInfo {
    private String policyNo;//保单号
    private String endorNo;//批单号
    private String endorDate;//申请批改日期
    private String validDate;//批改生效日期
    private String endorType;//批改类型
    private String handlerCode;//申请批改人
    private String underWriteInd;//批单状态
    private String underWriteReason;//批改原因

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public String getEndorNo() {
        return endorNo;
    }

    public void setEndorNo(String endorNo) {
        this.endorNo = endorNo;
    }

    public String getEndorDate() {
        return endorDate;
    }

    public void setEndorDate(String endorDate) {
        this.endorDate = endorDate;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    public String getEndorType() {
        return endorType;
    }

    public void setEndorType(String endorType) {
        this.endorType = endorType;
    }

    public String getHandlerCode() {
        return handlerCode;
    }

    public void setHandlerCode(String handlerCode) {
        this.handlerCode = handlerCode;
    }

    public String getUnderWriteInd() {
        return underWriteInd;
    }

    public void setUnderWriteInd(String underWriteInd) {
        this.underWriteInd = underWriteInd;
    }

    public String getUnderWriteReason() {
        return underWriteReason;
    }

    public void setUnderWriteReason(String underWriteReason) {
        this.underWriteReason = underWriteReason;
    }
}
