package cn.com.zsyk.crm.manage.entity;

import java.io.Serializable;
import java.util.List;

public class SysRoleMenuOutBean {
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column zcs_role_menu.role_code
	 *
	 * 
	 */
	private String roleCode;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column zcs_role_menu.role_name
	 *
	 * 
	 */
	private String roleName;

	/**
	 * 菜单信息表列表
	 * 
	 */
	private List<SysMenuInfo> menuInfoList;

	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column zcs_role_menu.role_code
	 *
	 * @return the value of zcs_role_menu.role_code
	 *
	 * 
	 */
	public String getRoleCode() {
		return roleCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column zcs_role_menu.role_code
	 *
	 * @param roleCode
	 *            the value for zcs_role_menu.role_code
	 *
	 * 
	 */
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column zcs_role_menu.role_name
	 *
	 * @return the value of zcs_role_menu.role_name
	 *
	 * 
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column zcs_role_menu.role_name
	 *
	 * @param roleName
	 *            the value for zcs_role_menu.role_name
	 *
	 * 
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<SysMenuInfo> getMenuInfoList() {
		return menuInfoList;
	}

	public void setMenuInfoList(List<SysMenuInfo> menuInfoList) {
		this.menuInfoList = menuInfoList;
	}

}