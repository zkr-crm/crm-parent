package cn.com.zsyk.crm.zuul.model;

import lombok.Data;

/**
 * User: Kyll
 * Date: 2018-02-13 13:57
 */
@Data
public class ServiceResponse<T> {
	private String code;
	private String message;
	private T data;

	/**
	 * 适用于无返回值
	 * @param <T> 数据泛型
	 * @return ServiceResponse
	 */
	public static <T> ServiceResponse<T> ok() {
		return of("SUCC-0000", "成功", null);
	}

	/**
	 * 适用于有返回值
	 * @param data 数据
	 * @param <T> 数据泛型
	 * @return ServiceResponse
	 */
	public static <T> ServiceResponse<T> ok(T data) {
		return of("SUCC-0000", "成功", data);
	}

	public static <T> ServiceResponse<T> of(String code) {
		return of(code, null, null);
	}

	public static <T> ServiceResponse<T> of(String code, String message) {
		return of(code, message, null);
	}

	public static <T> ServiceResponse<T> of(String code, String message, T data) {
		ServiceResponse<T> serviceResponse = new ServiceResponse<>();
		serviceResponse.setCode(code);
		serviceResponse.setMessage(message);
		serviceResponse.setData(data);
		return serviceResponse;
	}
}
