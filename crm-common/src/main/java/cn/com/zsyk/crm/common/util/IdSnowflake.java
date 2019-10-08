package cn.com.zsyk.crm.common.util;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;

import org.apache.commons.logging.Log;

/**
 * <p>
 *   基于Snowflake实现64位按时间趋势递增的ID算法, 整个分布式系统内不会产生ID碰撞（由工作机器ID区分），每秒最多可生产418万个有序的ID，即QPS=400w/s <br>
 * </p>
 * 
 * <pre>
 * （1|0）---（0000000000 0000000000 0000000000 0000000000 0） --- （00000 ---00000） ---（000000000000）
 *   未使用1位：    第一位未使用，可作为long的符号位，0整数，1负数；
 *   时间戳41位：距离2015-01-01的41位毫秒数，可支持到2015 + 2的41次方/1000/60/60/24/365=69.7年，2084年；
 *   工作机器ID10位：5位数据中心标识位(支持32) + 5位机器表示位(支持32)， 一共支持1024种情况。可根据业务类型自定义；
 *   序列号12位：同一毫秒内的自增ID（多线程建议使用atomic），最多支持4096个；若同一毫秒把序列号用完了，则等待至下一毫秒；
 *   
 *   加起来刚好64位，Java中的long可表示2的64次方个数，最小值是-2的63次方，最大值是2的63次方-1，-9223372036854775808到9223372036854775807
 * </pre>
 * 
 * @see {@link https://gitee.com/yu120/sequence } 和 {@link http://blog.csdn.net/shiyu_sy/article/details/53023847 } 
 * @author
 */
class IdSnowflake {
    private static final Log logger = LogUtil.getLogger(IdSnowflake.class);

    /** 时间起始标记点 (2015-01-01)，一旦确定不能变动*/
    private final long twepoch = 1420041600000L;
    
    /** 数据中心的位数*/
	private final static long datacenterBits = 5L;
	/** 机器ID的位数*/
	private final static long workerIdBits = 5L;
	/** 毫秒内自增位的位数*/
	private final static long sequenceBits = 12L;
	
	/** 机器ID最大值，等价于Math.pow(2, workerIdBits)-1=31， 共32个*/
	private final static long maxWorkerId = -1L ^ (-1L << workerIdBits);
	/** 数据中心最大值，等价于Math.pow(2, datacenterBits)-1=31， 共32个*/
	private final static long maxDatacenter = -1L ^ (-1L << datacenterBits);
	/** 毫秒内自增位的最大值，0~4095，共4096个*/
	private final static long maxSequence = -1L ^ (-1L << sequenceBits);
	
	/** 机器ID向左移12位 */
	private final static long workerIdShift = sequenceBits;
	/** 数据中心向左移17位(12+5) */
	private final static long datacenterShift = sequenceBits + workerIdBits;
	/** 时间截向左移22位(5+5+12) */
	private final static long timestampShift = sequenceBits + workerIdBits + datacenterBits;

	/** 机器ID(0~31) */
	private final long workerId;
	/** 数据中心ID(0~31) */
	private final long datacenter;
	
	/** 毫秒内序列(0~4095) */
	private long sequence = 0L;
    /** 上次生成ID的时间截 */
	private static long lastTimestamp = -1L;

	public IdSnowflake() {
		this.datacenter = getDatacenterId(maxDatacenter);
		this.workerId = getMaxWorkerId(datacenter, maxWorkerId);
	}

	/**
	 * @param workerId  工作机器ID
	 * @param datacenterId  序列号
	 */
	public IdSnowflake(long workerId, long datacenterId) {
		if (workerId > maxWorkerId || workerId < 0) {
			throw new IllegalArgumentException(
					String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
		}
		if (datacenterId > maxDatacenter || datacenterId < 0) {
			throw new IllegalArgumentException(
					String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenter));
		}
		this.workerId = workerId;
		this.datacenter = datacenterId;
	}

	/**
	 * 获取下一个ID
	 */
	public synchronized long nextId() {
		long timestamp = timeGen();
		
		//如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过
        if (timestamp < lastTimestamp) {
        	//闰秒的情况
            long offset = lastTimestamp - timestamp;
            if (offset <= 5) {
                try {
                    wait(offset << 1);
                    timestamp = timeGen();
                    if (timestamp < lastTimestamp) {
                        throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", offset));
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", offset));
            }
        }
		
		 //如果是同一时间生成的，则进行毫秒内序列
		if (lastTimestamp == timestamp) {
			sequence = (sequence + 1) & maxSequence;
			
			//毫秒内序列溢出
			if (sequence == 0) {
				// 当前毫秒内计数满了，则等待下一秒
				timestamp = tilNextMillis(lastTimestamp);
			}
		} else {
			//时间戳改变，毫秒内序列重置
			sequence = 0L;
		}
		
		//上次生成ID的时间截
		lastTimestamp = timestamp;
		
		// ID偏移组合生成最终的ID，并返回ID; //移位并通过或运算拼到一起组成64位的ID
		return ((timestamp - twepoch) << timestampShift)  	// 时间戳部分
				| (datacenter << datacenterShift)			// 数据中心部分
				| (workerId << workerIdShift)  				// 机器ID部分
				| sequence;									// 序列号部分
		
	}

	
	/**
	 * 阻塞到下一个毫秒，直到获得新的时间戳
	 * @param lastTimestamp
	 * @return
	 */
	private long tilNextMillis(final long lastTimestamp) {
		long timestamp = this.timeGen();
		while (timestamp <= lastTimestamp) {
			timestamp = this.timeGen();
		}
		return timestamp;
	}

	/**
	 * 返回以毫秒为单位的当前时间
	 */
	private long timeGen() {
		return System.currentTimeMillis();
	}

	/**
	 * <p>
	 * 自动获取数据中心部分
	 * </p>
	 */
	private long getDatacenterId(long maxDatacenterId) {
		long id = 0L;
		try {
			NetworkInterface network = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
			if (network == null) {
				id = 1L;
			} else {
				byte[] mac = network.getHardwareAddress();
				id = ((0x000000FF & (long) mac[mac.length - 1]) | (0x0000FF00 & (((long) mac[mac.length - 2]) << 8))) >> 6;
				
				//超过最大值时，取求余的结果
				id = id % (maxDatacenterId + 1);
			}
		} catch (Exception e) {
            logger.warn(" getDatacenterId: " + e.getMessage());
		}
		return id;
	}
	
	/**
	 * <p>
	 * 自动获取工作ID部分
	 * </p>
	 */
	private long getMaxWorkerId(long datacenterId, long maxWorkerId) {
		StringBuffer mpid = new StringBuffer();
		mpid.append(datacenterId);
		String name = ManagementFactory.getRuntimeMXBean().getName();
		if (!name.isEmpty()) {
			/*
			 * GET jvmPid
			 */
			mpid.append(name.split("@")[0]);
		}
		/*
		 * MAC + PID 的 hashcode 获取16个低位，超过最大值时，取求余的结果
		 */
		return (mpid.toString().hashCode() & 0xffff) % (maxWorkerId + 1);
	}

//	public static void main(String[] args) {
//		IdSnowflake idSnowflake = new IdSnowflake(31, 31);
//		System.out.println("idWorker=" + idSnowflake.nextId());
//		IdSnowflake id = new IdSnowflake();
//		System.out.println("id=" + id.nextId());
//		System.out.println(id.datacenter);
//		System.out.println(id.workerId);
//	}
}
