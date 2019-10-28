package cn.com.zsyk.crm.ecif.bo.cust;

public class CustTagList {

    private String custNo;

    private String tagCd;

    private String tagNam;

    public void setCustNo(String custNo){
        this.custNo = custNo;
    }
    public String getCustNo(){
        return this.custNo;
    }
    public void setTagCd(String tagCd){
        this.tagCd = tagCd;
    }
    public String getTagCd(){
        return this.tagCd;
    }
    public void setTagNam(String tagNam){
        this.tagNam = tagNam;
    }
    public String getTagNam(){
        return this.tagNam;
    }
}
