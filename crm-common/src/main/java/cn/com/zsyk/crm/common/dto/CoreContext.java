package cn.com.zsyk.crm.common.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 *
 * @param <I>
 * @param <O>
 */
public class CoreContext implements Map,Serializable {
	 
	private Map props = new HashMap();
	
	private String userId;
	private String employeeId;
	
	public String getEmployeeId() {
		return (String)props.get("employeeId");
	}

	public void setEmployeeId(String employeeId) {
		props.put("employeeId", employeeId);
	}

	public String getUserId() {
		return (String)props.get("userId");
	}

	public void setUserId(String userId) {
		props.put("userId", userId);
	}

	public CoreContext() {
	}

	public void clear() {
		props.clear();
	}

	public boolean containsKey(Object key) {
		return props.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return props.containsValue(value);
	}

	public Set entrySet() {
		return props.entrySet();
	}

	public Object get(Object key) {
		return props.get(key);
	}

	public boolean isEmpty() {
		return props.isEmpty();
	}

	public Set keySet() {
		return props.keySet();
	}

	public Object put(Object key, Object value) {
		return props.put(key, value);
	}
	
	 
	public void putAll(Map m) {
		props.putAll(m);
	}

	public Object remove(Object key) {
		return props.remove(key);
	}

	public int size() {
		return props.size();
	}

	public Collection values() {
		return props.values();
	}
}
