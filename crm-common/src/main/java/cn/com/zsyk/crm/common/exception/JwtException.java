package cn.com.zsyk.crm.common.exception;
/**
 * JWT验证异常
 * @author
 */
public class JwtException extends io.jsonwebtoken.JwtException {
	private static final long serialVersionUID = -2346730779736189612L;

	public JwtException(String message) {
		super(message);
	}

    public JwtException(String message, Throwable cause) {
        super(message, cause);
    }
}