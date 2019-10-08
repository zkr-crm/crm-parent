package cn.com.zsyk.crm.query.bo;

import java.io.Serializable;

/**
 * 客户事件映射类
 * @author houya
 *
 */
public class CusEventCount implements Serializable{

	private static final long serialVersionUID = 1L;
	//事件数量
	private Integer eventCount;
	//事件类型
	private String eventType;
	public Integer getEventCount() {
		return eventCount;
	}
	public void setEventCount(Integer eventCount) {
		this.eventCount = eventCount;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
}
