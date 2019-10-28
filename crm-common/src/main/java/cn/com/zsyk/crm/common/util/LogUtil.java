package cn.com.zsyk.crm.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 日志工具类
 * @author
 */
public class LogUtil {
	/**
	 * 获取日志组件入口
	 * @param clazz
	 * @return
	 */
	public static Log getLogger(Class<?> clazz) {
		return LogFactory.getLog(clazz);
	}
}
