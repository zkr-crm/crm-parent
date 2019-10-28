package cn.com.zsyk.crm.manage.bom;

import java.io.Serializable;

public class SysDataAuthInfo implements Serializable {

	/**
	 * 角色代码
	 */
	private String roleCode;

	/**
	 * 表代码
	 */
	private String tableCode;

	/**
	 * 字段代码
	 */
	private String fieldCode;

	/**
	 * 匹配条件
	 */
	private String matchCondition;

	/**
	 * 规则代码
	 */
	private String ruleCode;

	/**
	 * 是否包含子节点
	 */
	private String isContainChild;

	/**
	 * 字段类型
	 */
	private String fieldType;

	/**
	 * 规则类型
	 */
	private String ruleSymbol;

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getTableCode() {
		return tableCode;
	}

	public void setTableCode(String tableCode) {
		this.tableCode = tableCode;
	}

	public String getFieldCode() {
		return fieldCode;
	}

	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
	}

	public String getMatchCondition() {
		return matchCondition;
	}

	public void setMatchCondition(String matchCondition) {
		this.matchCondition = matchCondition;
	}

	public String getRuleCode() {
		return ruleCode;
	}

	public void setRuleCode(String ruleCode) {
		this.ruleCode = ruleCode;
	}

	public String getIsContainChild() {
		return isContainChild;
	}

	public void setIsContainChild(String isContainChild) {
		this.isContainChild = isContainChild;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getRuleSymbol() {
		return ruleSymbol;
	}

	public void setRuleSymbol(String ruleSymbol) {
		this.ruleSymbol = ruleSymbol;
	}

}