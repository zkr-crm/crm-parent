package cn.com.zsyk.crm.manage.bom;

import java.io.Serializable;

public class SysRuleTypeBean implements Serializable {
	private String tableCode;

	private String fieldCode;

	private String ruleCode;
	private String ruleName;
	private String ruleSymbol;

	private static final long serialVersionUID = 1L;

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

	public String getRuleCode() {
		return ruleCode;
	}

	public void setRuleCode(String ruleCode) {
		this.ruleCode = ruleCode;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getRuleSymbol() {
		return ruleSymbol;
	}

	public void setRuleSymbol(String ruleSymbol) {
		this.ruleSymbol = ruleSymbol;
	}
}