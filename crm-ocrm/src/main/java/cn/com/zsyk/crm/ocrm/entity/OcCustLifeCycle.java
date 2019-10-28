package cn.com.zsyk.crm.ocrm.entity;

import java.io.Serializable;
import java.util.Date;

public class OcCustLifeCycle implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_cust_life_cycle.stage_seq_no
     *
     * @mbggenerated
     */
    private Integer stageSeqNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_cust_life_cycle.cust_no
     *
     * @mbggenerated
     */
    private String custNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_cust_life_cycle.cust_typ
     *
     * @mbggenerated
     */
    private String custTyp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_cust_life_cycle.stage_id
     *
     * @mbggenerated
     */
    private Integer stageId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_cust_life_cycle.stage_start_date
     *
     * @mbggenerated
     */
    private Date stageStartDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_cust_life_cycle.stage_end_date
     *
     * @mbggenerated
     */
    private Date stageEndDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_cust_life_cycle.create_date
     *
     * @mbggenerated
     */
    private String createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_cust_life_cycle.create_time
     *
     * @mbggenerated
     */
    private String createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_cust_life_cycle.create_user
     *
     * @mbggenerated
     */
    private String createUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_cust_life_cycle.update_date
     *
     * @mbggenerated
     */
    private String updateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_cust_life_cycle.update_time
     *
     * @mbggenerated
     */
    private String updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_cust_life_cycle.update_user
     *
     * @mbggenerated
     */
    private String updateUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_cust_life_cycle.time_stamp
     *
     * @mbggenerated
     */
    private Date timeStamp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_cust_life_cycle.rec_stat
     *
     * @mbggenerated
     */
    private String recStat;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table oc_cust_life_cycle
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_cust_life_cycle.stage_seq_no
     *
     * @return the value of oc_cust_life_cycle.stage_seq_no
     *
     * @mbggenerated
     */
    public Integer getStageSeqNo() {
        return stageSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_cust_life_cycle.stage_seq_no
     *
     * @param stageSeqNo the value for oc_cust_life_cycle.stage_seq_no
     *
     * @mbggenerated
     */
    public void setStageSeqNo(Integer stageSeqNo) {
        this.stageSeqNo = stageSeqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_cust_life_cycle.cust_no
     *
     * @return the value of oc_cust_life_cycle.cust_no
     *
     * @mbggenerated
     */
    public String getCustNo() {
        return custNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_cust_life_cycle.cust_no
     *
     * @param custNo the value for oc_cust_life_cycle.cust_no
     *
     * @mbggenerated
     */
    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_cust_life_cycle.cust_typ
     *
     * @return the value of oc_cust_life_cycle.cust_typ
     *
     * @mbggenerated
     */
    public String getCustTyp() {
        return custTyp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_cust_life_cycle.cust_typ
     *
     * @param custTyp the value for oc_cust_life_cycle.cust_typ
     *
     * @mbggenerated
     */
    public void setCustTyp(String custTyp) {
        this.custTyp = custTyp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_cust_life_cycle.stage_id
     *
     * @return the value of oc_cust_life_cycle.stage_id
     *
     * @mbggenerated
     */
    public Integer getStageId() {
        return stageId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_cust_life_cycle.stage_id
     *
     * @param stageId the value for oc_cust_life_cycle.stage_id
     *
     * @mbggenerated
     */
    public void setStageId(Integer stageId) {
        this.stageId = stageId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_cust_life_cycle.stage_start_date
     *
     * @return the value of oc_cust_life_cycle.stage_start_date
     *
     * @mbggenerated
     */
    public Date getStageStartDate() {
        return stageStartDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_cust_life_cycle.stage_start_date
     *
     * @param stageStartDate the value for oc_cust_life_cycle.stage_start_date
     *
     * @mbggenerated
     */
    public void setStageStartDate(Date stageStartDate) {
        this.stageStartDate = stageStartDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_cust_life_cycle.stage_end_date
     *
     * @return the value of oc_cust_life_cycle.stage_end_date
     *
     * @mbggenerated
     */
    public Date getStageEndDate() {
        return stageEndDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_cust_life_cycle.stage_end_date
     *
     * @param stageEndDate the value for oc_cust_life_cycle.stage_end_date
     *
     * @mbggenerated
     */
    public void setStageEndDate(Date stageEndDate) {
        this.stageEndDate = stageEndDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_cust_life_cycle.create_date
     *
     * @return the value of oc_cust_life_cycle.create_date
     *
     * @mbggenerated
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_cust_life_cycle.create_date
     *
     * @param createDate the value for oc_cust_life_cycle.create_date
     *
     * @mbggenerated
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_cust_life_cycle.create_time
     *
     * @return the value of oc_cust_life_cycle.create_time
     *
     * @mbggenerated
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_cust_life_cycle.create_time
     *
     * @param createTime the value for oc_cust_life_cycle.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_cust_life_cycle.create_user
     *
     * @return the value of oc_cust_life_cycle.create_user
     *
     * @mbggenerated
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_cust_life_cycle.create_user
     *
     * @param createUser the value for oc_cust_life_cycle.create_user
     *
     * @mbggenerated
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_cust_life_cycle.update_date
     *
     * @return the value of oc_cust_life_cycle.update_date
     *
     * @mbggenerated
     */
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_cust_life_cycle.update_date
     *
     * @param updateDate the value for oc_cust_life_cycle.update_date
     *
     * @mbggenerated
     */
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_cust_life_cycle.update_time
     *
     * @return the value of oc_cust_life_cycle.update_time
     *
     * @mbggenerated
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_cust_life_cycle.update_time
     *
     * @param updateTime the value for oc_cust_life_cycle.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_cust_life_cycle.update_user
     *
     * @return the value of oc_cust_life_cycle.update_user
     *
     * @mbggenerated
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_cust_life_cycle.update_user
     *
     * @param updateUser the value for oc_cust_life_cycle.update_user
     *
     * @mbggenerated
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_cust_life_cycle.time_stamp
     *
     * @return the value of oc_cust_life_cycle.time_stamp
     *
     * @mbggenerated
     */
    public Date getTimeStamp() {
        return timeStamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_cust_life_cycle.time_stamp
     *
     * @param timeStamp the value for oc_cust_life_cycle.time_stamp
     *
     * @mbggenerated
     */
    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_cust_life_cycle.rec_stat
     *
     * @return the value of oc_cust_life_cycle.rec_stat
     *
     * @mbggenerated
     */
    public String getRecStat() {
        return recStat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_cust_life_cycle.rec_stat
     *
     * @param recStat the value for oc_cust_life_cycle.rec_stat
     *
     * @mbggenerated
     */
    public void setRecStat(String recStat) {
        this.recStat = recStat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oc_cust_life_cycle
     *
     * @mbggenerated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        OcCustLifeCycle other = (OcCustLifeCycle) that;
        return (this.getStageSeqNo() == null ? other.getStageSeqNo() == null : this.getStageSeqNo().equals(other.getStageSeqNo()))
            && (this.getCustNo() == null ? other.getCustNo() == null : this.getCustNo().equals(other.getCustNo()))
            && (this.getCustTyp() == null ? other.getCustTyp() == null : this.getCustTyp().equals(other.getCustTyp()))
            && (this.getStageId() == null ? other.getStageId() == null : this.getStageId().equals(other.getStageId()))
            && (this.getStageStartDate() == null ? other.getStageStartDate() == null : this.getStageStartDate().equals(other.getStageStartDate()))
            && (this.getStageEndDate() == null ? other.getStageEndDate() == null : this.getStageEndDate().equals(other.getStageEndDate()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
            && (this.getTimeStamp() == null ? other.getTimeStamp() == null : this.getTimeStamp().equals(other.getTimeStamp()))
            && (this.getRecStat() == null ? other.getRecStat() == null : this.getRecStat().equals(other.getRecStat()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oc_cust_life_cycle
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getStageSeqNo() == null) ? 0 : getStageSeqNo().hashCode());
        result = prime * result + ((getCustNo() == null) ? 0 : getCustNo().hashCode());
        result = prime * result + ((getCustTyp() == null) ? 0 : getCustTyp().hashCode());
        result = prime * result + ((getStageId() == null) ? 0 : getStageId().hashCode());
        result = prime * result + ((getStageStartDate() == null) ? 0 : getStageStartDate().hashCode());
        result = prime * result + ((getStageEndDate() == null) ? 0 : getStageEndDate().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        result = prime * result + ((getTimeStamp() == null) ? 0 : getTimeStamp().hashCode());
        result = prime * result + ((getRecStat() == null) ? 0 : getRecStat().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oc_cust_life_cycle
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", stageSeqNo=").append(stageSeqNo);
        sb.append(", custNo=").append(custNo);
        sb.append(", custTyp=").append(custTyp);
        sb.append(", stageId=").append(stageId);
        sb.append(", stageStartDate=").append(stageStartDate);
        sb.append(", stageEndDate=").append(stageEndDate);
        sb.append(", createDate=").append(createDate);
        sb.append(", createTime=").append(createTime);
        sb.append(", createUser=").append(createUser);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", timeStamp=").append(timeStamp);
        sb.append(", recStat=").append(recStat);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}