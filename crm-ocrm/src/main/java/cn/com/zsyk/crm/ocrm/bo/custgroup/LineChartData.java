package cn.com.zsyk.crm.ocrm.bo.custgroup;

import java.io.Serializable;

public class LineChartData implements Serializable {

	private static final long serialVersionUID = 1L;

	// 日期：年/月
	private String date;
	// 客户数量
	private String count;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

}
