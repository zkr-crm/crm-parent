package cn.com.zsyk.crm.common.constant;

/**
 * Redis相关的常量类
 * @author
 */
public interface RedisConsts {

	/**
	 * Token失效时间，60分钟
	 */
	long EXPIRATION = 3600L;
	/**
	 * 具失效时间还有多长时间进行刷新的时间，20分钟；
	 */
	long REF_TIME = 1200L;
}
