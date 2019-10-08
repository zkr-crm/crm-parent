package cn.com.zsyk.crm.common.config.advisor;

import org.apache.commons.logging.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import cn.com.zsyk.crm.common.util.JsonUtil;
import cn.com.zsyk.crm.common.util.LogUtil;
import cn.com.zsyk.crm.common.exception.ServiceException;

/**
 * Service层的切面（打印日志、统一处理异常）<br/>
 * 
 * 事务也可以在此切面处理，目前使用springboot自动配置的注解式事务@Transaction
 * @author
 */
@Aspect
@Component
public class ServiceAspect {
	private final Log logger = LogUtil.getLogger(ServiceAspect.class);
	
	/**
	 * 通过属性文件控制是否打印Service层的日志
	 */
	@Value("${info.log-switch.service}")
	private boolean logFlag = false;

	/**
	 * service层的所有public方法的切入点
	 */
	@Pointcut("execution(public * cn.com.zsyk.crm.*.service..*.*(..))")
	private void servicePointcut(){	}

	
	/**
	 * service切入点的环绕通知，记录进入退出日志，打印耗时、异常处理；
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around(value = "servicePointcut()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
		Long begin = null;
		String methodName = null;
		
		if(logFlag){
			begin = System.currentTimeMillis();
			methodName = beforeLogger(joinPoint);
		}
		
		//Service层异常统一向上层抛出
		Object result = null;
		try {
			result = joinPoint.proceed();
		} catch (Throwable e) {
			//ServiceException是正常的业务流程判断，把异常信息赋值给结果对象用来打印日志
			if(e instanceof ServiceException) {
				result = e.getMessage();
			}
			
			//向上层抛出异常
			throw e;
		}finally {
			if(logFlag){
				afterLogger(methodName, begin, result);
			}
		}
		
		return result;
	}
	
	/**
	 * 打印进入日志
	 * @param joinPoint
	 * @return 方法名
	 */
	private String beforeLogger(ProceedingJoinPoint joinPoint) {
		String className = joinPoint.getTarget().getClass().getSimpleName();
		String methodName = joinPoint.getSignature().getName();
		
		StringBuilder msg = new StringBuilder("进入")
				.append(className).append("@").append(methodName)
				.append("方法，参数：[");
		
		Object[] args = joinPoint.getArgs();
		for (int i=0; i<args.length; i++) {
			msg.append(JsonUtil.toJSONString(args[i]));
			if(i < args.length-1){
				msg.append("，");
			}
		}
		msg.append("]");

		logger.info(msg.toString());
		return className+"@"+methodName;
	}

	/**
	 * 打印退出日志
	 * @param begin
	 * @param result
	 */
	private void afterLogger(String methodName, Long begin, Object result) {
		StringBuilder msg = new StringBuilder(" 退出");
			msg.append(methodName).append("方法，耗时：").append(System.currentTimeMillis() - begin).append("ms，")
			.append("返回："+JsonUtil.toJSONString(result));

		logger.info(msg.toString());
	}
	
}