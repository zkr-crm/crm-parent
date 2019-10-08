package cn.com.zsyk.crm.common.dto;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 全局结果对象
 * @author
 */
public class Result<T> implements Serializable{
	private static final long serialVersionUID = 1L;
	private StatusEnum statusEnum = StatusEnum.SUCCESS;
	private String 	message;
	private T data;

    @JSONField(serialize = false)  
	public StatusEnum getStatusEnum() {
		return statusEnum;
	}
    @JSONField(serialize = false)  
	public void setStatusEnum(StatusEnum status) {
		this.statusEnum = status;
	}
    
	@JSONField(name = "status")  
    public String getStatus() {  
        return statusEnum.value;  
    }  
	@JSONField(name = "status")  
    public void setStatus(String value) {  
        this.statusEnum = StatusEnum.toEnum(value);  
    }  
	 
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	/**
	 * 执行成功，返回结果对象
	 * @param data 返回对象
	 * @return
	 */
	public Result<T> success(T data){
		this.setStatusEnum(StatusEnum.SUCCESS);
		this.setData(data);
		return this;
	}
	
	/**
	 * 执行失败，业务判断失败
	 * @param errMsg
	 * @return
	 */
	public Result<?> fail(String errMsg){
		this.setStatusEnum(StatusEnum.FAIL);
		this.setMessage(errMsg);
		return this;
	}
	
	/**
	 * 执行失败，代码异常
	 * @param errMsg
	 * @return
	 */
	public Result<?> exception(String errMsg){
		this.setStatusEnum(StatusEnum.EXCEPTION);
		this.setMessage(errMsg);
		return this;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((statusEnum == null) ? 0 : statusEnum.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Result<?> other = (Result<?>) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (statusEnum != other.statusEnum)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Result [status=" + statusEnum + ", message=" + message + ", data=" + data + "]";
	}

	public enum StatusEnum{
		EXCEPTION("-1","异常"),FAIL("0","失败"),SUCCESS("1","成功");
		private final String value;
		private final String desc;
		
		private StatusEnum(String value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getValue() {
			return this.value;
		}
		public String desc() {
			return this.desc;
		}
		
		public static StatusEnum toEnum(String value) {
			if (null == value)
				throw new NullPointerException("value is null");

			for(StatusEnum item : StatusEnum.values()) {
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
}


