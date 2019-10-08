package cn.com.zsyk.crm.common.config.advisor;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.com.zsyk.crm.common.util.LogUtil;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.IPUtil;
import cn.com.zsyk.crm.common.util.JsonUtil;

/**
 * SpringMVC的请求日志拦截
 * @author
 */
@Aspect
@Order(1)
@Component
public class WebLogAspect {
    private final Log logger = LogUtil.getLogger(WebLogAspect.class);
    private final ThreadLocal<Long> startTime = new ThreadLocal<>();
    
    /**
     * 所有RestController注解标注  在 cn.com.zsyk.crm.*.web..包下的所有方法
     */
    @Pointcut("@target(org.springframework.web.bind.annotation.RestController) && execution(* cn.com.zsyk.crm.*.web..*.*(..))")
    public void ctrlPointcut(){}

    /**
     * 方法执行前通知
     * @param joinPoint
     * @throws Throwable
     */
    @Before("ctrlPointcut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        //接收到请求，记录请求内容
        HttpServletRequest request  = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        StringBuilder argStr = new StringBuilder("");
        
        Object[] args = joinPoint.getArgs();
        if(null != args) {
        	int length = args.length;
        	argStr.append("[");
        	
        	for (int i=0; i<length; i++) {
        		if(args[i] instanceof ServletRequest ) {
        			argStr.append("ServletRequest");
        		}else if(args[i] instanceof ServletResponse ) {
        			argStr.append("ServletResponse");
        		}else {
        			argStr.append(JsonUtil.toJSONString(args[i]));
        		}
        		if(i<length-1) {
        			argStr.append(",");
        		}
			}
        	argStr.append("]");
        }
        
        // 记录下请求内容
        logger.info("记录Web请求日志........");
        logger.info("  Url：" + request.getMethod()+" "+request.getRequestURL());
        logger.info("  Ip： " + IPUtil.getIpAddress(request));
        logger.info("  Method：" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("  Argus：" + argStr);
    }
    
    /**
     * 正常返回通知
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(pointcut="ctrlPointcut()", returning="ret")
    public void doAfterReturning(Object ret) throws Throwable {
        logger.info("Web请求耗时：" + (System.currentTimeMillis() - startTime.get())+"ms，返回：" + JsonUtil.toJSONString(ret));
    }
    
    /**
     * 异常返回通知
     * @param e
     * @return
     * @throws Throwable 
     */
    @AfterThrowing(pointcut="ctrlPointcut()", throwing="e" )
    public void doAfterThrowing(Throwable e) throws Throwable {
		if (e instanceof ServiceException) {
	    	logger.info("Web请求耗时："+ (System.currentTimeMillis()-startTime.get())+"ms，返回：" + e.getMessage());
		}
		
		//异常向上抛出，由ExceptionAspect统一处理
		throw e;
    }
    
}