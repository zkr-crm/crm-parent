package cn.com.zsyk.crm.ecif.bo.cust;

public class ExportCustBaseInfo extends PerCustBaseInfo{


    //客户经理姓名
    private String custAgentName;

    public String getCustAgentName() {
        return custAgentName;
    }

    public void setCustAgentName(String custAgentName) {
        this.custAgentName = custAgentName;
    }

    public ExportCustBaseInfo(String custAgentName) {
        this.custAgentName = custAgentName;
    }

    public ExportCustBaseInfo() {
    }



}
