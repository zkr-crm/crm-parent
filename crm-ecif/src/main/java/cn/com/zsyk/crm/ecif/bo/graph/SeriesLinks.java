package cn.com.zsyk.crm.ecif.bo.graph;

public class SeriesLinks {

	private String source;
	private String sourceCustNo;
	private String target;
	private String targetCustNo;
	private String name;
	private String relType;
	private String des;
	private Normal lineStyle;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSourceCustNo() {
		return sourceCustNo;
	}

	public void setSourceCustNo(String sourceCustNo) {
		this.sourceCustNo = sourceCustNo;
	}

	public String getTargetCustNo() {
		return targetCustNo;
	}

	public void setTargetCustNo(String targetCustNo) {
		this.targetCustNo = targetCustNo;
	}

	public String getRelType() {
		return relType;
	}

	public void setRelType(String relType) {
		this.relType = relType;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Normal getLineStyle() {
		return lineStyle;
	}

	public void setLineStyle(Normal lineStyle) {
		this.lineStyle = lineStyle;
	}
	// [
	// {
	// source: '李四',
	// target: '张三',
	// name: '妻子',
	// des: '夫妻'
	// }, {
	// source: '张五',
	// target: '张三',
	// name: "父亲",
	// lineStyle: {
	// normal: {
	// type: 'dotted',
	// color: '#0000fe'
	// }
	// }
	// }, {
	// source: '李五',
	// target: '李四',
	// name: '同事',
	// des: '潜在客户'
	// }, {
	// source: '李六',
	// target: '李四',
	// name: '同事',
	// des: '高风险客户'
	// }]
}
