package cn.com.zsyk.crm.common.exception;

/**
 * Id生成器异常类<br/>
 * Spring的事务默认回滚RuntimeException类型异常，故继承该类。
 * 
 * @author
 */
public class IdGeneraterException extends RuntimeException {
	private static final long serialVersionUID = 2691352654280923747L;

	public IdGeneraterException() {
		super();
	}

	public IdGeneraterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public IdGeneraterException(String message, Throwable cause) {
		super(message, cause);
	}

	public IdGeneraterException(String message) {
		super(message);
	}

	public IdGeneraterException(Throwable cause) {
		super(cause);
	}
}
