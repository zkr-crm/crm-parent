package cn.com.zsyk.crm.ecif.bo.graph;

public class SeriesData {

	// [
	// {
	// name: 'C',
	// des: '高消费客户<br/>xxx保险，yyy保险',
	// symbolSize: 100,
	// itemStyle: {
	// normal: {
	// color: 'red'
	// }
	// }
	// }, {
	// name: 'A',
	// des: 'xx公司雇员',
	// }, {
	// name: 'B',
	// des: 'yy公司',
	// symbolSize: 50
	// }, {
	// name: 'E',
	// des: 'xx公司',
	// symbolSize: 50,
	// itemStyle: {
	// normal: {
	// color: '#13219e'
	// }
	// }
	//
	// }, {
	// name: 'F',
	// des: 'yy公司',
	// symbolSize: 50,
	// itemStyle: {
	// normal: {
	// color: '#13219e'
	// }
	// }
	// }
	// ]
	private String custNo;
	private String name;
	private String des;
	private String symbolSize;
	private Normal itemStyle;

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getSymbolSize() {
		return symbolSize;
	}

	public void setSymbolSize(String symbolSize) {
		this.symbolSize = symbolSize;
	}

	public Normal getItemStyle() {
		return itemStyle;
	}

	public void setItemStyle(Normal itemStyle) {
		this.itemStyle = itemStyle;
	}
}
