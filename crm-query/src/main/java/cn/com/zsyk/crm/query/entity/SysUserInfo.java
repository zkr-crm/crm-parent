package cn.com.zsyk.crm.query.entity;

import java.io.Serializable;

public class SysUserInfo implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_info.user_id
     *
     * @mbggenerated
     */
    private String userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_info.user_name
     *
     * @mbggenerated
     */
    private String userName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_info.sex
     *
     * @mbggenerated
     */
    private String sex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_info.password
     *
     * @mbggenerated
     */
    private String password;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_info.employee_id
     *
     * @mbggenerated
     */
    private String employeeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_info.user_stat
     *
     * @mbggenerated
     */
    private String userStat;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_info.enter_code
     *
     * @mbggenerated
     */
    private String enterCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_info.enter_name
     *
     * @mbggenerated
     */
    private String enterName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_info.dept_code
     *
     * @mbggenerated
     */
    private String deptCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_info.dept_name
     *
     * @mbggenerated
     */
    private String deptName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_info.posi_code
     *
     * @mbggenerated
     */
    private String posiCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_info.posi_name
     *
     * @mbggenerated
     */
    private String posiName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_info.role_code
     *
     * @mbggenerated
     */
    private String roleCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_info.role_name
     *
     * @mbggenerated
     */
    private String roleName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_info.telphone
     *
     * @mbggenerated
     */
    private String telphone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_info.email
     *
     * @mbggenerated
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_info.create_date
     *
     * @mbggenerated
     */
    private String createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_info.create_time
     *
     * @mbggenerated
     */
    private String createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_info.create_user
     *
     * @mbggenerated
     */
    private String createUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_info.update_date
     *
     * @mbggenerated
     */
    private String updateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_info.update_time
     *
     * @mbggenerated
     */
    private String updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_info.update_user
     *
     * @mbggenerated
     */
    private String updateUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_info.time_stamp
     *
     * @mbggenerated
     */
    private String timeStamp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_info.rec_stat
     *
     * @mbggenerated
     */
    private String recStat;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_info.dept_posi
     *
     * @mbggenerated
     */
    private String deptPosi;
    private int followUpBusiOppQty;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_user_info
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_info.user_id
     *
     * @return the value of sys_user_info.user_id
     *
     * @mbggenerated
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_info.user_id
     *
     * @param userId the value for sys_user_info.user_id
     *
     * @mbggenerated
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_info.user_name
     *
     * @return the value of sys_user_info.user_name
     *
     * @mbggenerated
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_info.user_name
     *
     * @param userName the value for sys_user_info.user_name
     *
     * @mbggenerated
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_info.sex
     *
     * @return the value of sys_user_info.sex
     *
     * @mbggenerated
     */
    public String getSex() {
        return sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_info.sex
     *
     * @param sex the value for sys_user_info.sex
     *
     * @mbggenerated
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_info.password
     *
     * @return the value of sys_user_info.password
     *
     * @mbggenerated
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_info.password
     *
     * @param password the value for sys_user_info.password
     *
     * @mbggenerated
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_info.employee_id
     *
     * @return the value of sys_user_info.employee_id
     *
     * @mbggenerated
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_info.employee_id
     *
     * @param employeeId the value for sys_user_info.employee_id
     *
     * @mbggenerated
     */
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_info.user_stat
     *
     * @return the value of sys_user_info.user_stat
     *
     * @mbggenerated
     */
    public String getUserStat() {
        return userStat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_info.user_stat
     *
     * @param userStat the value for sys_user_info.user_stat
     *
     * @mbggenerated
     */
    public void setUserStat(String userStat) {
        this.userStat = userStat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_info.enter_code
     *
     * @return the value of sys_user_info.enter_code
     *
     * @mbggenerated
     */
    public String getEnterCode() {
        return enterCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_info.enter_code
     *
     * @param enterCode the value for sys_user_info.enter_code
     *
     * @mbggenerated
     */
    public void setEnterCode(String enterCode) {
        this.enterCode = enterCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_info.enter_name
     *
     * @return the value of sys_user_info.enter_name
     *
     * @mbggenerated
     */
    public String getEnterName() {
        return enterName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_info.enter_name
     *
     * @param enterName the value for sys_user_info.enter_name
     *
     * @mbggenerated
     */
    public void setEnterName(String enterName) {
        this.enterName = enterName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_info.dept_code
     *
     * @return the value of sys_user_info.dept_code
     *
     * @mbggenerated
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_info.dept_code
     *
     * @param deptCode the value for sys_user_info.dept_code
     *
     * @mbggenerated
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_info.dept_name
     *
     * @return the value of sys_user_info.dept_name
     *
     * @mbggenerated
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_info.dept_name
     *
     * @param deptName the value for sys_user_info.dept_name
     *
     * @mbggenerated
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_info.posi_code
     *
     * @return the value of sys_user_info.posi_code
     *
     * @mbggenerated
     */
    public String getPosiCode() {
        return posiCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_info.posi_code
     *
     * @param posiCode the value for sys_user_info.posi_code
     *
     * @mbggenerated
     */
    public void setPosiCode(String posiCode) {
        this.posiCode = posiCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_info.posi_name
     *
     * @return the value of sys_user_info.posi_name
     *
     * @mbggenerated
     */
    public String getPosiName() {
        return posiName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_info.posi_name
     *
     * @param posiName the value for sys_user_info.posi_name
     *
     * @mbggenerated
     */
    public void setPosiName(String posiName) {
        this.posiName = posiName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_info.role_code
     *
     * @return the value of sys_user_info.role_code
     *
     * @mbggenerated
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_info.role_code
     *
     * @param roleCode the value for sys_user_info.role_code
     *
     * @mbggenerated
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_info.role_name
     *
     * @return the value of sys_user_info.role_name
     *
     * @mbggenerated
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_info.role_name
     *
     * @param roleName the value for sys_user_info.role_name
     *
     * @mbggenerated
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_info.telphone
     *
     * @return the value of sys_user_info.telphone
     *
     * @mbggenerated
     */
    public String getTelphone() {
        return telphone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_info.telphone
     *
     * @param telphone the value for sys_user_info.telphone
     *
     * @mbggenerated
     */
    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_info.email
     *
     * @return the value of sys_user_info.email
     *
     * @mbggenerated
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_info.email
     *
     * @param email the value for sys_user_info.email
     *
     * @mbggenerated
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_info.create_date
     *
     * @return the value of sys_user_info.create_date
     *
     * @mbggenerated
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_info.create_date
     *
     * @param createDate the value for sys_user_info.create_date
     *
     * @mbggenerated
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_info.create_time
     *
     * @return the value of sys_user_info.create_time
     *
     * @mbggenerated
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_info.create_time
     *
     * @param createTime the value for sys_user_info.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_info.create_user
     *
     * @return the value of sys_user_info.create_user
     *
     * @mbggenerated
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_info.create_user
     *
     * @param createUser the value for sys_user_info.create_user
     *
     * @mbggenerated
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_info.update_date
     *
     * @return the value of sys_user_info.update_date
     *
     * @mbggenerated
     */
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_info.update_date
     *
     * @param updateDate the value for sys_user_info.update_date
     *
     * @mbggenerated
     */
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_info.update_time
     *
     * @return the value of sys_user_info.update_time
     *
     * @mbggenerated
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_info.update_time
     *
     * @param updateTime the value for sys_user_info.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_info.update_user
     *
     * @return the value of sys_user_info.update_user
     *
     * @mbggenerated
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_info.update_user
     *
     * @param updateUser the value for sys_user_info.update_user
     *
     * @mbggenerated
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_info.time_stamp
     *
     * @return the value of sys_user_info.time_stamp
     *
     * @mbggenerated
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_info.time_stamp
     *
     * @param timeStamp the value for sys_user_info.time_stamp
     *
     * @mbggenerated
     */
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_info.rec_stat
     *
     * @return the value of sys_user_info.rec_stat
     *
     * @mbggenerated
     */
    public String getRecStat() {
        return recStat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_info.rec_stat
     *
     * @param recStat the value for sys_user_info.rec_stat
     *
     * @mbggenerated
     */
    public void setRecStat(String recStat) {
        this.recStat = recStat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_info.dept_posi
     *
     * @return the value of sys_user_info.dept_posi
     *
     * @mbggenerated
     */
    public String getDeptPosi() {
        return deptPosi;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_info.dept_posi
     *
     * @param deptPosi the value for sys_user_info.dept_posi
     *
     * @mbggenerated
     */
    public void setDeptPosi(String deptPosi) {
        this.deptPosi = deptPosi;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_info
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
        SysUserInfo other = (SysUserInfo) that;
        return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getEmployeeId() == null ? other.getEmployeeId() == null : this.getEmployeeId().equals(other.getEmployeeId()))
            && (this.getUserStat() == null ? other.getUserStat() == null : this.getUserStat().equals(other.getUserStat()))
            && (this.getEnterCode() == null ? other.getEnterCode() == null : this.getEnterCode().equals(other.getEnterCode()))
            && (this.getEnterName() == null ? other.getEnterName() == null : this.getEnterName().equals(other.getEnterName()))
            && (this.getDeptCode() == null ? other.getDeptCode() == null : this.getDeptCode().equals(other.getDeptCode()))
            && (this.getDeptName() == null ? other.getDeptName() == null : this.getDeptName().equals(other.getDeptName()))
            && (this.getPosiCode() == null ? other.getPosiCode() == null : this.getPosiCode().equals(other.getPosiCode()))
            && (this.getPosiName() == null ? other.getPosiName() == null : this.getPosiName().equals(other.getPosiName()))
            && (this.getRoleCode() == null ? other.getRoleCode() == null : this.getRoleCode().equals(other.getRoleCode()))
            && (this.getRoleName() == null ? other.getRoleName() == null : this.getRoleName().equals(other.getRoleName()))
            && (this.getTelphone() == null ? other.getTelphone() == null : this.getTelphone().equals(other.getTelphone()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
            && (this.getTimeStamp() == null ? other.getTimeStamp() == null : this.getTimeStamp().equals(other.getTimeStamp()))
            && (this.getRecStat() == null ? other.getRecStat() == null : this.getRecStat().equals(other.getRecStat()))
            && (this.getDeptPosi() == null ? other.getDeptPosi() == null : this.getDeptPosi().equals(other.getDeptPosi()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_info
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getEmployeeId() == null) ? 0 : getEmployeeId().hashCode());
        result = prime * result + ((getUserStat() == null) ? 0 : getUserStat().hashCode());
        result = prime * result + ((getEnterCode() == null) ? 0 : getEnterCode().hashCode());
        result = prime * result + ((getEnterName() == null) ? 0 : getEnterName().hashCode());
        result = prime * result + ((getDeptCode() == null) ? 0 : getDeptCode().hashCode());
        result = prime * result + ((getDeptName() == null) ? 0 : getDeptName().hashCode());
        result = prime * result + ((getPosiCode() == null) ? 0 : getPosiCode().hashCode());
        result = prime * result + ((getPosiName() == null) ? 0 : getPosiName().hashCode());
        result = prime * result + ((getRoleCode() == null) ? 0 : getRoleCode().hashCode());
        result = prime * result + ((getRoleName() == null) ? 0 : getRoleName().hashCode());
        result = prime * result + ((getTelphone() == null) ? 0 : getTelphone().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        result = prime * result + ((getTimeStamp() == null) ? 0 : getTimeStamp().hashCode());
        result = prime * result + ((getRecStat() == null) ? 0 : getRecStat().hashCode());
        result = prime * result + ((getDeptPosi() == null) ? 0 : getDeptPosi().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_info
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", sex=").append(sex);
        sb.append(", password=").append(password);
        sb.append(", employeeId=").append(employeeId);
        sb.append(", userStat=").append(userStat);
        sb.append(", enterCode=").append(enterCode);
        sb.append(", enterName=").append(enterName);
        sb.append(", deptCode=").append(deptCode);
        sb.append(", deptName=").append(deptName);
        sb.append(", posiCode=").append(posiCode);
        sb.append(", posiName=").append(posiName);
        sb.append(", roleCode=").append(roleCode);
        sb.append(", roleName=").append(roleName);
        sb.append(", telphone=").append(telphone);
        sb.append(", email=").append(email);
        sb.append(", createDate=").append(createDate);
        sb.append(", createTime=").append(createTime);
        sb.append(", createUser=").append(createUser);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", timeStamp=").append(timeStamp);
        sb.append(", recStat=").append(recStat);
        sb.append(", deptPosi=").append(deptPosi);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

	
	public int getFollowUpBusiOppQty() {
		return followUpBusiOppQty;
	}

	
	public void setFollowUpBusiOppQty(int followUpBusiOppQty) {
		this.followUpBusiOppQty = followUpBusiOppQty;
	}
    
}