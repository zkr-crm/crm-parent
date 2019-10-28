package cn.com.zsyk.crm.common.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import org.springframework.beans.BeanUtils;

/**
 * JavaBean的工具类
 * @author
 */
public class BeanUtil {
	
	/**
	 * 通过Class实例化对象
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static <T> T instantiate(Class<T> clazz) throws Exception {
		return BeanUtils.instantiate(clazz);
	}
	
	/**
	 * 获取clazz的相关方法
	 * @param clazz
	 * @param methodName
	 * @param paramTypes
	 * @return
	 */
	public static Method findMethod(Class<?> clazz, String methodName, Class<?>... paramTypes) {
		return BeanUtils.findMethod(clazz, methodName, paramTypes);
	}
	
	/**
	 * 获取Class的属性描述
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static PropertyDescriptor[] getPropertyDescriptors(Class<?> clazz) throws Exception {
		return BeanUtils.getPropertyDescriptors(clazz);
	}
	
	/**
	 * 获取Class的属性描述
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static PropertyDescriptor getPropertyDescriptor(Class<?> clazz, String propertyName) throws Exception{
		return BeanUtils.getPropertyDescriptor(clazz, propertyName);
	}
	/**
	 * 拷贝对象间的属性
	 * @param source
	 * @param target
	 */
	public static void copy(Object source, Object target) {
		BeanUtils.copyProperties(source, target);
	}
}
