package cn.com.zsyk.crm.manage.entity;

import java.io.Serializable;

public class SysTreeNode implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	
	private String code;
	
	private String text;

	private String type;

	private String parent;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

}