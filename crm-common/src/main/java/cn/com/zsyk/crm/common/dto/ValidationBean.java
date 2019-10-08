package cn.com.zsyk.crm.common.dto;

/**
 * 验证错误对象
 * @author
 */
public class ValidationBean{
	private String field;
	private Object value;
	private String errMsg;
	
	public String getField() {
		return field;
	}
	public Object getValue() {
		return value;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setField(String field) {
		this.field = field;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	@Override
	public String toString() {
		return "ValidationBean [field=" + field + ", value=" + value + ", errMsg=" + errMsg + "]";
	}
}