package cn.com.zsyk.crm.common.util;

import java.lang.reflect.Field;

import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.support.AopUtils;

/**
 * AOP的工具类
 * @author
 */
public class AopTargetUtil {
	
	/**
	 * 获取 目标类
	 * @param proxy 代理对象
	 */
	public static Class<?> getTargetClass(Object proxy) {
		return AopUtils.getTargetClass(proxy);
	}
	
	/**
	 * 获取 目标对象
	 * @param proxy 代理对象
	 */
	public static Object getTarget(Object proxy) throws Exception {
		if (!AopUtils.isAopProxy(proxy)) {
			return proxy;// 不是代理对象
		}
		
		if (AopUtils.isJdkDynamicProxy(proxy)) {
			return getJdkDynamicProxyTargetObject(proxy);
		} else { // cglib
			return getCglibProxyTargetObject(proxy);
		}
	}

	/**
	 * 获取JDK动态代理的目标对象
	 * @param proxy
	 * @return
	 * @throws Exception
	 */
	private static Object getJdkDynamicProxyTargetObject(Object proxy) throws Exception {
		Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
		h.setAccessible(true);
		AopProxy aopProxy = (AopProxy) h.get(proxy);
		
		Field advised = aopProxy.getClass().getDeclaredField("advised");
		advised.setAccessible(true);
		
		return ((AdvisedSupport) advised.get(aopProxy)).getTargetSource().getTarget();
	}
	
	/**
	 * 获取CGLIB代理的目标对象
	 * @param proxy
	 * @return
	 * @throws Exception
	 */
	private static Object getCglibProxyTargetObject(Object proxy) throws Exception {
		Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
		h.setAccessible(true);
		Object dynamicAdvisedInterceptor = h.get(proxy);
		
		Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
		advised.setAccessible(true);
		
		return ((AdvisedSupport) advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();
	}


}