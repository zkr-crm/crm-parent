package cn.com.zsyk.crm.ocrm.entity;

import java.io.Serializable;
import java.util.Date;

public class OcGroupOper implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_group_oper.group_oper_id
     *
     * @mbggenerated
     */
    private String groupOperId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_group_oper.group_oper_name
     *
     * @mbggenerated
     */
    private String groupOperName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_group_oper.group_oper_type
     *
     * @mbggenerated
     */
    private String groupOperType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_group_oper.oper_num
     *
     * @mbggenerated
     */
    private Integer operNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_group_oper.group_id
     *
     * @mbggenerated
     */
    private String groupId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_group_oper.group_type
     *
     * @mbggenerated
     */
    private String groupType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_group_oper.snapshot_id
     *
     * @mbggenerated
     */
    private String snapshotId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_group_oper.establish_time
     *
     * @mbggenerated
     */
    private Date establishTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_group_oper.group_task_status
     *
     * @mbggenerated
     */
    private String groupTaskStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_group_oper.end_time
     *
     * @mbggenerated
     */
    private Date endTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_group_oper.group_task_desc
     *
     * @mbggenerated
     */
    private String groupTaskDesc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_group_oper.create_user
     *
     * @mbggenerated
     */
    private String createUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_group_oper.create_date
     *
     * @mbggenerated
     */
    private String createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_group_oper.create_time
     *
     * @mbggenerated
     */
    private String createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_group_oper.update_user
     *
     * @mbggenerated
     */
    private String updateUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_group_oper.update_date
     *
     * @mbggenerated
     */
    private String updateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_group_oper.update_time
     *
     * @mbggenerated
     */
    private String updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_group_oper.time_stamp
     *
     * @mbggenerated
     */
    private Date timeStamp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_group_oper.rec_stat
     *
     * @mbggenerated
     */
    private String recStat;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table oc_group_oper
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_group_oper.group_oper_id
     *
     * @return the value of oc_group_oper.group_oper_id
     *
     * @mbggenerated
     */
    public String getGroupOperId() {
        return groupOperId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_group_oper.group_oper_id
     *
     * @param groupOperId the value for oc_group_oper.group_oper_id
     *
     * @mbggenerated
     */
    public void setGroupOperId(String groupOperId) {
        this.groupOperId = groupOperId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_group_oper.group_oper_name
     *
     * @return the value of oc_group_oper.group_oper_name
     *
     * @mbggenerated
     */
    public String getGroupOperName() {
        return groupOperName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_group_oper.group_oper_name
     *
     * @param groupOperName the value for oc_group_oper.group_oper_name
     *
     * @mbggenerated
     */
    public void setGroupOperName(String groupOperName) {
        this.groupOperName = groupOperName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_group_oper.group_oper_type
     *
     * @return the value of oc_group_oper.group_oper_type
     *
     * @mbggenerated
     */
    public String getGroupOperType() {
        return groupOperType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_group_oper.group_oper_type
     *
     * @param groupOperType the value for oc_group_oper.group_oper_type
     *
     * @mbggenerated
     */
    public void setGroupOperType(String groupOperType) {
        this.groupOperType = groupOperType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_group_oper.oper_num
     *
     * @return the value of oc_group_oper.oper_num
     *
     * @mbggenerated
     */
    public Integer getOperNum() {
        return operNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_group_oper.oper_num
     *
     * @param operNum the value for oc_group_oper.oper_num
     *
     * @mbggenerated
     */
    public void setOperNum(Integer operNum) {
        this.operNum = operNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_group_oper.group_id
     *
     * @return the value of oc_group_oper.group_id
     *
     * @mbggenerated
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_group_oper.group_id
     *
     * @param groupId the value for oc_group_oper.group_id
     *
     * @mbggenerated
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_group_oper.group_type
     *
     * @return the value of oc_group_oper.group_type
     *
     * @mbggenerated
     */
    public String getGroupType() {
        return groupType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_group_oper.group_type
     *
     * @param groupType the value for oc_group_oper.group_type
     *
     * @mbggenerated
     */
    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_group_oper.snapshot_id
     *
     * @return the value of oc_group_oper.snapshot_id
     *
     * @mbggenerated
     */
    public String getSnapshotId() {
        return snapshotId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_group_oper.snapshot_id
     *
     * @param snapshotId the value for oc_group_oper.snapshot_id
     *
     * @mbggenerated
     */
    public void setSnapshotId(String snapshotId) {
        this.snapshotId = snapshotId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_group_oper.establish_time
     *
     * @return the value of oc_group_oper.establish_time
     *
     * @mbggenerated
     */
    public Date getEstablishTime() {
        return establishTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_group_oper.establish_time
     *
     * @param establishTime the value for oc_group_oper.establish_time
     *
     * @mbggenerated
     */
    public void setEstablishTime(Date establishTime) {
        this.establishTime = establishTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_group_oper.group_task_status
     *
     * @return the value of oc_group_oper.group_task_status
     *
     * @mbggenerated
     */
    public String getGroupTaskStatus() {
        return groupTaskStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_group_oper.group_task_status
     *
     * @param groupTaskStatus the value for oc_group_oper.group_task_status
     *
     * @mbggenerated
     */
    public void setGroupTaskStatus(String groupTaskStatus) {
        this.groupTaskStatus = groupTaskStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_group_oper.end_time
     *
     * @return the value of oc_group_oper.end_time
     *
     * @mbggenerated
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_group_oper.end_time
     *
     * @param endTime the value for oc_group_oper.end_time
     *
     * @mbggenerated
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_group_oper.group_task_desc
     *
     * @return the value of oc_group_oper.group_task_desc
     *
     * @mbggenerated
     */
    public String getGroupTaskDesc() {
        return groupTaskDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_group_oper.group_task_desc
     *
     * @param groupTaskDesc the value for oc_group_oper.group_task_desc
     *
     * @mbggenerated
     */
    public void setGroupTaskDesc(String groupTaskDesc) {
        this.groupTaskDesc = groupTaskDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_group_oper.create_user
     *
     * @return the value of oc_group_oper.create_user
     *
     * @mbggenerated
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_group_oper.create_user
     *
     * @param createUser the value for oc_group_oper.create_user
     *
     * @mbggenerated
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_group_oper.create_date
     *
     * @return the value of oc_group_oper.create_date
     *
     * @mbggenerated
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_group_oper.create_date
     *
     * @param createDate the value for oc_group_oper.create_date
     *
     * @mbggenerated
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_group_oper.create_time
     *
     * @return the value of oc_group_oper.create_time
     *
     * @mbggenerated
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_group_oper.create_time
     *
     * @param createTime the value for oc_group_oper.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_group_oper.update_user
     *
     * @return the value of oc_group_oper.update_user
     *
     * @mbggenerated
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_group_oper.update_user
     *
     * @param updateUser the value for oc_group_oper.update_user
     *
     * @mbggenerated
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_group_oper.update_date
     *
     * @return the value of oc_group_oper.update_date
     *
     * @mbggenerated
     */
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_group_oper.update_date
     *
     * @param updateDate the value for oc_group_oper.update_date
     *
     * @mbggenerated
     */
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_group_oper.update_time
     *
     * @return the value of oc_group_oper.update_time
     *
     * @mbggenerated
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_group_oper.update_time
     *
     * @param updateTime the value for oc_group_oper.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_group_oper.time_stamp
     *
     * @return the value of oc_group_oper.time_stamp
     *
     * @mbggenerated
     */
    public Date getTimeStamp() {
        return timeStamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_group_oper.time_stamp
     *
     * @param timeStamp the value for oc_group_oper.time_stamp
     *
     * @mbggenerated
     */
    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_group_oper.rec_stat
     *
     * @return the value of oc_group_oper.rec_stat
     *
     * @mbggenerated
     */
    public String getRecStat() {
        return recStat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_group_oper.rec_stat
     *
     * @param recStat the value for oc_group_oper.rec_stat
     *
     * @mbggenerated
     */
    public void setRecStat(String recStat) {
        this.recStat = recStat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oc_group_oper
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
        OcGroupOper other = (OcGroupOper) that;
        return (this.getGroupOperId() == null ? other.getGroupOperId() == null : this.getGroupOperId().equals(other.getGroupOperId()))
            && (this.getGroupOperName() == null ? other.getGroupOperName() == null : this.getGroupOperName().equals(other.getGroupOperName()))
            && (this.getGroupOperType() == null ? other.getGroupOperType() == null : this.getGroupOperType().equals(other.getGroupOperType()))
            && (this.getOperNum() == null ? other.getOperNum() == null : this.getOperNum().equals(other.getOperNum()))
            && (this.getGroupId() == null ? other.getGroupId() == null : this.getGroupId().equals(other.getGroupId()))
            && (this.getGroupType() == null ? other.getGroupType() == null : this.getGroupType().equals(other.getGroupType()))
            && (this.getSnapshotId() == null ? other.getSnapshotId() == null : this.getSnapshotId().equals(other.getSnapshotId()))
            && (this.getEstablishTime() == null ? other.getEstablishTime() == null : this.getEstablishTime().equals(other.getEstablishTime()))
            && (this.getGroupTaskStatus() == null ? other.getGroupTaskStatus() == null : this.getGroupTaskStatus().equals(other.getGroupTaskStatus()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getGroupTaskDesc() == null ? other.getGroupTaskDesc() == null : this.getGroupTaskDesc().equals(other.getGroupTaskDesc()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getTimeStamp() == null ? other.getTimeStamp() == null : this.getTimeStamp().equals(other.getTimeStamp()))
            && (this.getRecStat() == null ? other.getRecStat() == null : this.getRecStat().equals(other.getRecStat()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oc_group_oper
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getGroupOperId() == null) ? 0 : getGroupOperId().hashCode());
        result = prime * result + ((getGroupOperName() == null) ? 0 : getGroupOperName().hashCode());
        result = prime * result + ((getGroupOperType() == null) ? 0 : getGroupOperType().hashCode());
        result = prime * result + ((getOperNum() == null) ? 0 : getOperNum().hashCode());
        result = prime * result + ((getGroupId() == null) ? 0 : getGroupId().hashCode());
        result = prime * result + ((getGroupType() == null) ? 0 : getGroupType().hashCode());
        result = prime * result + ((getSnapshotId() == null) ? 0 : getSnapshotId().hashCode());
        result = prime * result + ((getEstablishTime() == null) ? 0 : getEstablishTime().hashCode());
        result = prime * result + ((getGroupTaskStatus() == null) ? 0 : getGroupTaskStatus().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getGroupTaskDesc() == null) ? 0 : getGroupTaskDesc().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getTimeStamp() == null) ? 0 : getTimeStamp().hashCode());
        result = prime * result + ((getRecStat() == null) ? 0 : getRecStat().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oc_group_oper
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", groupOperId=").append(groupOperId);
        sb.append(", groupOperName=").append(groupOperName);
        sb.append(", groupOperType=").append(groupOperType);
        sb.append(", operNum=").append(operNum);
        sb.append(", groupId=").append(groupId);
        sb.append(", groupType=").append(groupType);
        sb.append(", snapshotId=").append(snapshotId);
        sb.append(", establishTime=").append(establishTime);
        sb.append(", groupTaskStatus=").append(groupTaskStatus);
        sb.append(", endTime=").append(endTime);
        sb.append(", groupTaskDesc=").append(groupTaskDesc);
        sb.append(", createUser=").append(createUser);
        sb.append(", createDate=").append(createDate);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", timeStamp=").append(timeStamp);
        sb.append(", recStat=").append(recStat);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}