package cn.com.zsyk.crm.query.bo;

import lombok.Data;

@Data
public class RenwalEventBaseInfo {
	// 客户号
	private String policyNo;
	// 客户姓名
	private String custName;
	// 终保日期
	private String endDate;
	// 手机号
	private String mobile;
	private String clientCode;
}
