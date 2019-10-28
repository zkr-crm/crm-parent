package cn.com.zsyk.crm.ocrm.entity;

import java.io.Serializable;
import java.util.Date;

public class OcBusiOppCollaborator implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_busi_opp_collaborator.busi_opp_no
     *
     * @mbggenerated
     */
    private String busiOppNo;

    /**
     * 员工编号 employeeId
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_busi_opp_collaborator.collaborator
     *
     * @mbggenerated
     */
    private String collaborator;
    // 姓名
    private String collrNam;
    // 电话
    private String collrTel;
    // 所属机构
    private String enterName;
    // 所属部门
    private String deptName;
    // 所属岗位
    private String posiName;
    // 用户状态
    private String userStat;
    private String employeeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_busi_opp_collaborator.cust_no
     *
     * @mbggenerated
     */
    private String custNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_busi_opp_collaborator.collaborate_date
     *
     * @mbggenerated
     */
    private Date collaborateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_busi_opp_collaborator.create_user
     *
     * @mbggenerated
     */
    private String createUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_busi_opp_collaborator.create_date
     *
     * @mbggenerated
     */
    private String createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_busi_opp_collaborator.create_time
     *
     * @mbggenerated
     */
    private String createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_busi_opp_collaborator.update_user
     *
     * @mbggenerated
     */
    private String updateUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_busi_opp_collaborator.update_date
     *
     * @mbggenerated
     */
    private String updateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_busi_opp_collaborator.update_time
     *
     * @mbggenerated
     */
    private String updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_busi_opp_collaborator.time_stamp
     *
     * @mbggenerated
     */
    private Date timeStamp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oc_busi_opp_collaborator.rec_stat
     *
     * @mbggenerated
     */
    private String recStat;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table oc_busi_opp_collaborator
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_busi_opp_collaborator.busi_opp_no
     *
     * @return the value of oc_busi_opp_collaborator.busi_opp_no
     *
     * @mbggenerated
     */
    public String getBusiOppNo() {
        return busiOppNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_busi_opp_collaborator.busi_opp_no
     *
     * @param busiOppNo the value for oc_busi_opp_collaborator.busi_opp_no
     *
     * @mbggenerated
     */
    public void setBusiOppNo(String busiOppNo) {
        this.busiOppNo = busiOppNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_busi_opp_collaborator.collaborator
     *
     * @return the value of oc_busi_opp_collaborator.collaborator
     *
     * @mbggenerated
     */
    public String getCollaborator() {
        return collaborator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_busi_opp_collaborator.collaborator
     *
     * @param collaborator the value for oc_busi_opp_collaborator.collaborator
     *
     * @mbggenerated
     */
    public void setCollaborator(String collaborator) {
        this.collaborator = collaborator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_busi_opp_collaborator.cust_no
     *
     * @return the value of oc_busi_opp_collaborator.cust_no
     *
     * @mbggenerated
     */
    public String getCustNo() {
        return custNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_busi_opp_collaborator.cust_no
     *
     * @param custNo the value for oc_busi_opp_collaborator.cust_no
     *
     * @mbggenerated
     */
    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_busi_opp_collaborator.collaborate_date
     *
     * @return the value of oc_busi_opp_collaborator.collaborate_date
     *
     * @mbggenerated
     */
    public Date getCollaborateDate() {
        return collaborateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_busi_opp_collaborator.collaborate_date
     *
     * @param collaborateDate the value for oc_busi_opp_collaborator.collaborate_date
     *
     * @mbggenerated
     */
    public void setCollaborateDate(Date collaborateDate) {
        this.collaborateDate = collaborateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_busi_opp_collaborator.create_user
     *
     * @return the value of oc_busi_opp_collaborator.create_user
     *
     * @mbggenerated
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_busi_opp_collaborator.create_user
     *
     * @param createUser the value for oc_busi_opp_collaborator.create_user
     *
     * @mbggenerated
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_busi_opp_collaborator.create_date
     *
     * @return the value of oc_busi_opp_collaborator.create_date
     *
     * @mbggenerated
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_busi_opp_collaborator.create_date
     *
     * @param createDate the value for oc_busi_opp_collaborator.create_date
     *
     * @mbggenerated
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_busi_opp_collaborator.create_time
     *
     * @return the value of oc_busi_opp_collaborator.create_time
     *
     * @mbggenerated
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_busi_opp_collaborator.create_time
     *
     * @param createTime the value for oc_busi_opp_collaborator.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_busi_opp_collaborator.update_user
     *
     * @return the value of oc_busi_opp_collaborator.update_user
     *
     * @mbggenerated
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_busi_opp_collaborator.update_user
     *
     * @param updateUser the value for oc_busi_opp_collaborator.update_user
     *
     * @mbggenerated
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_busi_opp_collaborator.update_date
     *
     * @return the value of oc_busi_opp_collaborator.update_date
     *
     * @mbggenerated
     */
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_busi_opp_collaborator.update_date
     *
     * @param updateDate the value for oc_busi_opp_collaborator.update_date
     *
     * @mbggenerated
     */
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_busi_opp_collaborator.update_time
     *
     * @return the value of oc_busi_opp_collaborator.update_time
     *
     * @mbggenerated
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_busi_opp_collaborator.update_time
     *
     * @param updateTime the value for oc_busi_opp_collaborator.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_busi_opp_collaborator.time_stamp
     *
     * @return the value of oc_busi_opp_collaborator.time_stamp
     *
     * @mbggenerated
     */
    public Date getTimeStamp() {
        return timeStamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_busi_opp_collaborator.time_stamp
     *
     * @param timeStamp the value for oc_busi_opp_collaborator.time_stamp
     *
     * @mbggenerated
     */
    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oc_busi_opp_collaborator.rec_stat
     *
     * @return the value of oc_busi_opp_collaborator.rec_stat
     *
     * @mbggenerated
     */
    public String getRecStat() {
        return recStat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oc_busi_opp_collaborator.rec_stat
     *
     * @param recStat the value for oc_busi_opp_collaborator.rec_stat
     *
     * @mbggenerated
     */
    public void setRecStat(String recStat) {
        this.recStat = recStat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oc_busi_opp_collaborator
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
        OcBusiOppCollaborator other = (OcBusiOppCollaborator) that;
        return (this.getBusiOppNo() == null ? other.getBusiOppNo() == null : this.getBusiOppNo().equals(other.getBusiOppNo()))
            && (this.getCollaborator() == null ? other.getCollaborator() == null : this.getCollaborator().equals(other.getCollaborator()))
            && (this.getCustNo() == null ? other.getCustNo() == null : this.getCustNo().equals(other.getCustNo()))
            && (this.getCollaborateDate() == null ? other.getCollaborateDate() == null : this.getCollaborateDate().equals(other.getCollaborateDate()))
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
     * This method corresponds to the database table oc_busi_opp_collaborator
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBusiOppNo() == null) ? 0 : getBusiOppNo().hashCode());
        result = prime * result + ((getCollaborator() == null) ? 0 : getCollaborator().hashCode());
        result = prime * result + ((getCustNo() == null) ? 0 : getCustNo().hashCode());
        result = prime * result + ((getCollaborateDate() == null) ? 0 : getCollaborateDate().hashCode());
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
     * This method corresponds to the database table oc_busi_opp_collaborator
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", busiOppNo=").append(busiOppNo);
        sb.append(", collaborator=").append(collaborator);
        sb.append(", custNo=").append(custNo);
        sb.append(", collaborateDate=").append(collaborateDate);
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

	
	public String getCollrNam() {
		return collrNam;
	}

	
	public void setCollrNam(String collrNam) {
		this.collrNam = collrNam;
	}

	
	public String getCollrTel() {
		return collrTel;
	}

	
	public void setCollrTel(String collrTel) {
		this.collrTel = collrTel;
	}

	
	public String getEnterName() {
		return enterName;
	}

	
	public void setEnterName(String enterName) {
		this.enterName = enterName;
	}

	
	public String getDeptName() {
		return deptName;
	}

	
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	
	public String getPosiName() {
		return posiName;
	}

	
	public void setPosiName(String posiName) {
		this.posiName = posiName;
	}

	
	public String getUserStat() {
		return userStat;
	}

	
	public void setUserStat(String userStat) {
		this.userStat = userStat;
	}

	
	public String getEmployeeId() {
		return employeeId;
	}

	
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
    
}