package cn.com.zsyk.crm.common.util;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 分布式ID生成器，提供Snowflake、Sequence和UUID三种实现
 * @author
 */
public class IdGenerator{
	private static final IdSnowflake idSnowflake = new IdSnowflake();
	/**
	 * 存放业务自增ID的线程安全非阻塞Map
	 */
	private static final ConcurrentHashMap<String, IdSequence> bizIdSequence = new ConcurrentHashMap<>();

	/**
	 * 获取long类型的趋势递增的分布式ID（基于Snowflake实现，不是每次自增1）
	 * @return 分布式ID
	 */
	public static long getDistributedID() {
		return idSnowflake.nextId();
	}
	
	/**
	 * 获取long类型的业务相关的自增ID（基于数据库实现，每次自增1）
	 * @param biz_tag 业务标识
	 * @return 自增ID
	 */
	public static long getSeqID(String bizTag) {
		if (!bizIdSequence.containsKey(bizTag)) {
			synchronized (bizIdSequence) {
				if (!bizIdSequence.containsKey(bizTag)) {
					bizIdSequence.putIfAbsent(bizTag, new IdSequence(bizTag));
				}
			}
		}

		return bizIdSequence.get(bizTag).nextId();
	}
	
	/**
     * 获取String类型的去掉"-"的32位UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
