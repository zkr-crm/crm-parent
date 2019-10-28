package cn.com.zsyk.crm.manage.entity;

import java.io.Serializable;
import java.util.Date;

public class SysMenu implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.menu_id
     *
     * @mbggenerated
     */
    private String menuId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.menu_title
     *
     * @mbggenerated
     */
    private String menuTitle;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.menu_name
     *
     * @mbggenerated
     */
    private String menuName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.menu_level
     *
     * @mbggenerated
     */
    private String menuLevel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.menu_stateRef
     *
     * @mbggenerated
     */
    private String menuStateref;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.menu_permissionOnly
     *
     * @mbggenerated
     */
    private String menuPermissiononly;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.menu_icon
     *
     * @mbggenerated
     */
    private String menuIcon;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.menu_order
     *
     * @mbggenerated
     */
    private String menuOrder;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.menu_isShow
     *
     * @mbggenerated
     */
    private String menuIsshow;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.menu_pareId
     *
     * @mbggenerated
     */
    private String menuPareid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.create_date
     *
     * @mbggenerated
     */
    private String createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.create_time
     *
     * @mbggenerated
     */
    private String createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.create_user
     *
     * @mbggenerated
     */
    private String createUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.update_date
     *
     * @mbggenerated
     */
    private String updateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.update_time
     *
     * @mbggenerated
     */
    private String updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.update_user
     *
     * @mbggenerated
     */
    private String updateUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.time_stamp
     *
     * @mbggenerated
     */
    private Date timeStamp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.rec_stat
     *
     * @mbggenerated
     */
    private String recStat;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.rec_stat
     *
     * @mbggenerated
     */
    private String menuDesc;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.rec_stat
     *
     * @mbggenerated
     */
    private String menuParename;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_menu
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.menu_id
     *
     * @return the value of sys_menu.menu_id
     *
     * @mbggenerated
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.menu_id
     *
     * @param menuId the value for sys_menu.menu_id
     *
     * @mbggenerated
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.menu_title
     *
     * @return the value of sys_menu.menu_title
     *
     * @mbggenerated
     */
    public String getMenuTitle() {
        return menuTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.menu_title
     *
     * @param menuTitle the value for sys_menu.menu_title
     *
     * @mbggenerated
     */
    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.menu_name
     *
     * @return the value of sys_menu.menu_name
     *
     * @mbggenerated
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.menu_name
     *
     * @param menuName the value for sys_menu.menu_name
     *
     * @mbggenerated
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.menu_level
     *
     * @return the value of sys_menu.menu_level
     *
     * @mbggenerated
     */
    public String getMenuLevel() {
        return menuLevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.menu_level
     *
     * @param menuLevel the value for sys_menu.menu_level
     *
     * @mbggenerated
     */
    public void setMenuLevel(String menuLevel) {
        this.menuLevel = menuLevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.menu_stateRef
     *
     * @return the value of sys_menu.menu_stateRef
     *
     * @mbggenerated
     */
    public String getMenuStateref() {
        return menuStateref;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.menu_stateRef
     *
     * @param menuStateref the value for sys_menu.menu_stateRef
     *
     * @mbggenerated
     */
    public void setMenuStateref(String menuStateref) {
        this.menuStateref = menuStateref;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.menu_permissionOnly
     *
     * @return the value of sys_menu.menu_permissionOnly
     *
     * @mbggenerated
     */
    public String getMenuPermissiononly() {
        return menuPermissiononly;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.menu_permissionOnly
     *
     * @param menuPermissiononly the value for sys_menu.menu_permissionOnly
     *
     * @mbggenerated
     */
    public void setMenuPermissiononly(String menuPermissiononly) {
        this.menuPermissiononly = menuPermissiononly;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.menu_icon
     *
     * @return the value of sys_menu.menu_icon
     *
     * @mbggenerated
     */
    public String getMenuIcon() {
        return menuIcon;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.menu_icon
     *
     * @param menuIcon the value for sys_menu.menu_icon
     *
     * @mbggenerated
     */
    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.menu_order
     *
     * @return the value of sys_menu.menu_order
     *
     * @mbggenerated
     */
    public String getMenuOrder() {
        return menuOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.menu_order
     *
     * @param menuOrder the value for sys_menu.menu_order
     *
     * @mbggenerated
     */
    public void setMenuOrder(String menuOrder) {
        this.menuOrder = menuOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.menu_isShow
     *
     * @return the value of sys_menu.menu_isShow
     *
     * @mbggenerated
     */
    public String getMenuIsshow() {
        return menuIsshow;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.menu_isShow
     *
     * @param menuIsshow the value for sys_menu.menu_isShow
     *
     * @mbggenerated
     */
    public void setMenuIsshow(String menuIsshow) {
        this.menuIsshow = menuIsshow;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.menu_pareId
     *
     * @return the value of sys_menu.menu_pareId
     *
     * @mbggenerated
     */
    public String getMenuPareid() {
        return menuPareid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.menu_pareId
     *
     * @param menuPareid the value for sys_menu.menu_pareId
     *
     * @mbggenerated
     */
    public void setMenuPareid(String menuPareid) {
        this.menuPareid = menuPareid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.create_date
     *
     * @return the value of sys_menu.create_date
     *
     * @mbggenerated
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.create_date
     *
     * @param createDate the value for sys_menu.create_date
     *
     * @mbggenerated
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.create_time
     *
     * @return the value of sys_menu.create_time
     *
     * @mbggenerated
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.create_time
     *
     * @param createTime the value for sys_menu.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.create_user
     *
     * @return the value of sys_menu.create_user
     *
     * @mbggenerated
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.create_user
     *
     * @param createUser the value for sys_menu.create_user
     *
     * @mbggenerated
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.update_date
     *
     * @return the value of sys_menu.update_date
     *
     * @mbggenerated
     */
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.update_date
     *
     * @param updateDate the value for sys_menu.update_date
     *
     * @mbggenerated
     */
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.update_time
     *
     * @return the value of sys_menu.update_time
     *
     * @mbggenerated
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.update_time
     *
     * @param updateTime the value for sys_menu.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.update_user
     *
     * @return the value of sys_menu.update_user
     *
     * @mbggenerated
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.update_user
     *
     * @param updateUser the value for sys_menu.update_user
     *
     * @mbggenerated
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.time_stamp
     *
     * @return the value of sys_menu.time_stamp
     *
     * @mbggenerated
     */
    public Date getTimeStamp() {
        return timeStamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.time_stamp
     *
     * @param timeStamp the value for sys_menu.time_stamp
     *
     * @mbggenerated
     */
    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.rec_stat
     *
     * @return the value of sys_menu.rec_stat
     *
     * @mbggenerated
     */
    public String getRecStat() {
        return recStat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.rec_stat
     *
     * @param recStat the value for sys_menu.rec_stat
     *
     * @mbggenerated
     */
    public void setRecStat(String recStat) {
        this.recStat = recStat;
    }

    public String getMenuDesc() {
        return menuDesc;
    }

    public void setMenuDesc(String menuDesc) {
        this.menuDesc = menuDesc;
    }

    public String getMenuParename() {
        return menuParename;
    }

    public void setMenuParename(String menuParename) {
        this.menuParename = menuParename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
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
        SysMenu other = (SysMenu) that;
        return (this.getMenuId() == null ? other.getMenuId() == null : this.getMenuId().equals(other.getMenuId()))
            && (this.getMenuTitle() == null ? other.getMenuTitle() == null : this.getMenuTitle().equals(other.getMenuTitle()))
            && (this.getMenuName() == null ? other.getMenuName() == null : this.getMenuName().equals(other.getMenuName()))
            && (this.getMenuLevel() == null ? other.getMenuLevel() == null : this.getMenuLevel().equals(other.getMenuLevel()))
            && (this.getMenuStateref() == null ? other.getMenuStateref() == null : this.getMenuStateref().equals(other.getMenuStateref()))
            && (this.getMenuPermissiononly() == null ? other.getMenuPermissiononly() == null : this.getMenuPermissiononly().equals(other.getMenuPermissiononly()))
            && (this.getMenuIcon() == null ? other.getMenuIcon() == null : this.getMenuIcon().equals(other.getMenuIcon()))
            && (this.getMenuOrder() == null ? other.getMenuOrder() == null : this.getMenuOrder().equals(other.getMenuOrder()))
            && (this.getMenuIsshow() == null ? other.getMenuIsshow() == null : this.getMenuIsshow().equals(other.getMenuIsshow()))
            && (this.getMenuPareid() == null ? other.getMenuPareid() == null : this.getMenuPareid().equals(other.getMenuPareid()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
            && (this.getTimeStamp() == null ? other.getTimeStamp() == null : this.getTimeStamp().equals(other.getTimeStamp()))
            && (this.getMenuDesc() == null ? other.getMenuDesc() == null : this.getMenuDesc().equals(other.getMenuDesc()))
            && (this.getRecStat() == null ? other.getRecStat() == null : this.getRecStat().equals(other.getRecStat()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMenuId() == null) ? 0 : getMenuId().hashCode());
        result = prime * result + ((getMenuTitle() == null) ? 0 : getMenuTitle().hashCode());
        result = prime * result + ((getMenuName() == null) ? 0 : getMenuName().hashCode());
        result = prime * result + ((getMenuLevel() == null) ? 0 : getMenuLevel().hashCode());
        result = prime * result + ((getMenuStateref() == null) ? 0 : getMenuStateref().hashCode());
        result = prime * result + ((getMenuPermissiononly() == null) ? 0 : getMenuPermissiononly().hashCode());
        result = prime * result + ((getMenuIcon() == null) ? 0 : getMenuIcon().hashCode());
        result = prime * result + ((getMenuOrder() == null) ? 0 : getMenuOrder().hashCode());
        result = prime * result + ((getMenuIsshow() == null) ? 0 : getMenuIsshow().hashCode());
        result = prime * result + ((getMenuPareid() == null) ? 0 : getMenuPareid().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        result = prime * result + ((getTimeStamp() == null) ? 0 : getTimeStamp().hashCode());
        result = prime * result + ((getRecStat() == null) ? 0 : getRecStat().hashCode());
        result = prime * result + ((getMenuDesc() == null) ? 0 : getMenuDesc().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", menuId=").append(menuId);
        sb.append(", menuTitle=").append(menuTitle);
        sb.append(", menuName=").append(menuName);
        sb.append(", menuLevel=").append(menuLevel);
        sb.append(", menuStateref=").append(menuStateref);
        sb.append(", menuPermissiononly=").append(menuPermissiononly);
        sb.append(", menuIcon=").append(menuIcon);
        sb.append(", menuOrder=").append(menuOrder);
        sb.append(", menuIsshow=").append(menuIsshow);
        sb.append(", menuPareid=").append(menuPareid);
        sb.append(", createDate=").append(createDate);
        sb.append(", createTime=").append(createTime);
        sb.append(", createUser=").append(createUser);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", timeStamp=").append(timeStamp);
        sb.append(", recStat=").append(recStat);
        sb.append(", menuDesc=").append(menuDesc);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}