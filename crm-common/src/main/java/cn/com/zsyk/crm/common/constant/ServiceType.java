package cn.com.zsyk.crm.common.constant;

/**
 * 系统微服务列表枚举
 * @author
 */
public enum ServiceType {
	ECIF("crm-ecif"),MANAGE("crm-manage"),OCRM("crm-ocrm");
	private String value;
	
	private ServiceType(String value) {
		this.value = value;
	}
	
	public static ServiceType toEnum(String value) {
		if (null == value)
			throw new NullPointerException("value is null");

		for(ServiceType item : ServiceType.values()) {
			if(item.value.equals(value)){
				return item;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		return this.value;
	}
}
