package cn.com.zsyk.crm.manage.entity;

import java.io.Serializable;
import java.util.Date;

public class SysTag implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_tag.tag_id
     *
     * @mbggenerated
     */
    private Integer tagId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_tag.tag_type_id
     *
     * @mbggenerated
     */
    private String tagTypeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_tag.tag_name
     *
     * @mbggenerated
     */
    private String tagName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_tag.tag_scope
     *
     * @mbggenerated
     */
    private String tagScope;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_tag.tag_scope_val
     *
     * @mbggenerated
     */
    private String tagScopeVal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_tag.parent_tag_id
     *
     * @mbggenerated
     */
    private String parentTagId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_tag.strategy_id
     *
     * @mbggenerated
     */
    private String strategyId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_tag.remark
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_tag.create_date
     *
     * @mbggenerated
     */
    private String createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_tag.create_time
     *
     * @mbggenerated
     */
    private String createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_tag.create_user
     *
     * @mbggenerated
     */
    private String createUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_tag.update_date
     *
     * @mbggenerated
     */
    private String updateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_tag.update_time
     *
     * @mbggenerated
     */
    private String updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_tag.update_user
     *
     * @mbggenerated
     */
    private String updateUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_tag.time_stamp
     *
     * @mbggenerated
     */
    private Date timeStamp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_tag.rec_stat
     *
     * @mbggenerated
     */
    private String recStat;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_tag
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_tag.tag_id
     *
     * @return the value of sys_tag.tag_id
     *
     * @mbggenerated
     */
    public Integer getTagId() {
        return tagId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_tag.tag_id
     *
     * @param tagId the value for sys_tag.tag_id
     *
     * @mbggenerated
     */
    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_tag.tag_type_id
     *
     * @return the value of sys_tag.tag_type_id
     *
     * @mbggenerated
     */
    public String getTagTypeId() {
        return tagTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_tag.tag_type_id
     *
     * @param tagTypeId the value for sys_tag.tag_type_id
     *
     * @mbggenerated
     */
    public void setTagTypeId(String tagTypeId) {
        this.tagTypeId = tagTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_tag.tag_name
     *
     * @return the value of sys_tag.tag_name
     *
     * @mbggenerated
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_tag.tag_name
     *
     * @param tagName the value for sys_tag.tag_name
     *
     * @mbggenerated
     */
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_tag.tag_scope
     *
     * @return the value of sys_tag.tag_scope
     *
     * @mbggenerated
     */
    public String getTagScope() {
        return tagScope;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_tag.tag_scope
     *
     * @param tagScope the value for sys_tag.tag_scope
     *
     * @mbggenerated
     */
    public void setTagScope(String tagScope) {
        this.tagScope = tagScope;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_tag.tag_scope_val
     *
     * @return the value of sys_tag.tag_scope_val
     *
     * @mbggenerated
     */
    public String getTagScopeVal() {
        return tagScopeVal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_tag.tag_scope_val
     *
     * @param tagScopeVal the value for sys_tag.tag_scope_val
     *
     * @mbggenerated
     */
    public void setTagScopeVal(String tagScopeVal) {
        this.tagScopeVal = tagScopeVal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_tag.parent_tag_id
     *
     * @return the value of sys_tag.parent_tag_id
     *
     * @mbggenerated
     */
    public String getParentTagId() {
        return parentTagId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_tag.parent_tag_id
     *
     * @param parentTagId the value for sys_tag.parent_tag_id
     *
     * @mbggenerated
     */
    public void setParentTagId(String parentTagId) {
        this.parentTagId = parentTagId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_tag.strategy_id
     *
     * @return the value of sys_tag.strategy_id
     *
     * @mbggenerated
     */
    public String getStrategyId() {
        return strategyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_tag.strategy_id
     *
     * @param strategyId the value for sys_tag.strategy_id
     *
     * @mbggenerated
     */
    public void setStrategyId(String strategyId) {
        this.strategyId = strategyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_tag.remark
     *
     * @return the value of sys_tag.remark
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_tag.remark
     *
     * @param remark the value for sys_tag.remark
     *
     * @mbggenerated
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_tag.create_date
     *
     * @return the value of sys_tag.create_date
     *
     * @mbggenerated
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_tag.create_date
     *
     * @param createDate the value for sys_tag.create_date
     *
     * @mbggenerated
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_tag.create_time
     *
     * @return the value of sys_tag.create_time
     *
     * @mbggenerated
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_tag.create_time
     *
     * @param createTime the value for sys_tag.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_tag.create_user
     *
     * @return the value of sys_tag.create_user
     *
     * @mbggenerated
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_tag.create_user
     *
     * @param createUser the value for sys_tag.create_user
     *
     * @mbggenerated
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_tag.update_date
     *
     * @return the value of sys_tag.update_date
     *
     * @mbggenerated
     */
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_tag.update_date
     *
     * @param updateDate the value for sys_tag.update_date
     *
     * @mbggenerated
     */
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_tag.update_time
     *
     * @return the value of sys_tag.update_time
     *
     * @mbggenerated
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_tag.update_time
     *
     * @param updateTime the value for sys_tag.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_tag.update_user
     *
     * @return the value of sys_tag.update_user
     *
     * @mbggenerated
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_tag.update_user
     *
     * @param updateUser the value for sys_tag.update_user
     *
     * @mbggenerated
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_tag.time_stamp
     *
     * @return the value of sys_tag.time_stamp
     *
     * @mbggenerated
     */
    public Date getTimeStamp() {
        return timeStamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_tag.time_stamp
     *
     * @param timeStamp the value for sys_tag.time_stamp
     *
     * @mbggenerated
     */
    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_tag.rec_stat
     *
     * @return the value of sys_tag.rec_stat
     *
     * @mbggenerated
     */
    public String getRecStat() {
        return recStat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_tag.rec_stat
     *
     * @param recStat the value for sys_tag.rec_stat
     *
     * @mbggenerated
     */
    public void setRecStat(String recStat) {
        this.recStat = recStat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_tag
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
        SysTag other = (SysTag) that;
        return (this.getTagId() == null ? other.getTagId() == null : this.getTagId().equals(other.getTagId()))
            && (this.getTagTypeId() == null ? other.getTagTypeId() == null : this.getTagTypeId().equals(other.getTagTypeId()))
            && (this.getTagName() == null ? other.getTagName() == null : this.getTagName().equals(other.getTagName()))
            && (this.getTagScope() == null ? other.getTagScope() == null : this.getTagScope().equals(other.getTagScope()))
            && (this.getTagScopeVal() == null ? other.getTagScopeVal() == null : this.getTagScopeVal().equals(other.getTagScopeVal()))
            && (this.getParentTagId() == null ? other.getParentTagId() == null : this.getParentTagId().equals(other.getParentTagId()))
            && (this.getStrategyId() == null ? other.getStrategyId() == null : this.getStrategyId().equals(other.getStrategyId()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
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
     * This method corresponds to the database table sys_tag
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTagId() == null) ? 0 : getTagId().hashCode());
        result = prime * result + ((getTagTypeId() == null) ? 0 : getTagTypeId().hashCode());
        result = prime * result + ((getTagName() == null) ? 0 : getTagName().hashCode());
        result = prime * result + ((getTagScope() == null) ? 0 : getTagScope().hashCode());
        result = prime * result + ((getTagScopeVal() == null) ? 0 : getTagScopeVal().hashCode());
        result = prime * result + ((getParentTagId() == null) ? 0 : getParentTagId().hashCode());
        result = prime * result + ((getStrategyId() == null) ? 0 : getStrategyId().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
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
     * This method corresponds to the database table sys_tag
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tagId=").append(tagId);
        sb.append(", tagTypeId=").append(tagTypeId);
        sb.append(", tagName=").append(tagName);
        sb.append(", tagScope=").append(tagScope);
        sb.append(", tagScopeVal=").append(tagScopeVal);
        sb.append(", parentTagId=").append(parentTagId);
        sb.append(", strategyId=").append(strategyId);
        sb.append(", remark=").append(remark);
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