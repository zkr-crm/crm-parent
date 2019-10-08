package cn.com.zsyk.crm.common.constant;

/**
 * JWT相关的常量类
 * @author
 */
public interface JwtConsts {
	String LOGIN_CLASS = "cn.com.zsyk.crm.manage.web.controller.mngcenter.user.UserMngCtrl";
	/**
	 * HttpHeaders中的token名
	 */
	String TOKEN = "token"; 

	/**
	 * JWT中的业务字段userID
	 */
	String USERID = "userId"; 
	
	/**
	 * 签发者
	 */
	String ISSUER = "sign_crm"; 
	/**
	 * 秘钥
	 */
	String SECRET = "crm_secret"; 
	/**
	 * Token失效时间，60分钟
	 */
	long EXPIRATION = 3_600_000L; 
	/**
	 * 具失效时间还有多长时间进行刷新的时间，20分钟；
	 */
	long REF_TIME = 1_200_000L; 
}
