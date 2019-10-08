package cn.com.zsyk.crm.query.entity;

import java.util.Date;

public class Manager {
    private String custNo;
    private String managerNo;
    private Date riseTime;
    private String refCustNo;

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getManagerNo() {
        return managerNo;
    }

    public void setManagerNo(String managerNo) {
        this.managerNo = managerNo;
    }

    public Date getRiseTime() {
        return riseTime;
    }

    public void setRiseTime(Date riseTime) {
        this.riseTime = riseTime;
    }

    public String getRefCustNo() {
        return refCustNo;
    }

    public void setRefCustNo(String refCustNo) {
        this.refCustNo = refCustNo;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "custNo='" + custNo + '\'' +
                ", managerNo='" + managerNo + '\'' +
                ", riseTime='" + riseTime + '\'' +
                ", refCustNo='" + refCustNo + '\'' +
                '}';
    }
}
