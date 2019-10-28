package cn.com.zsyk.crm.common.config.advisor;

import javax.servlet.http.HttpServletResponse;

import cn.com.zsyk.crm.common.constant.RedisConsts;
import cn.com.zsyk.crm.common.util.RedisUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.com.zsyk.crm.common.constant.JwtConsts;
import cn.com.zsyk.crm.common.dto.ContextContainer;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.dto.Result.StatusEnum;
import cn.com.zsyk.crm.common.util.JwtUtil;

/**
 * Token切面，用来拦截登录成功
 * @author
 */
@Aspect
@Component
@ConditionalOnClass(name=JwtConsts.LOGIN_CLASS)
public class JwtAspect {

	@Pointcut("execution(public * "+JwtConsts.LOGIN_CLASS+".userLoginChk(..))")
	private void loginPointcut() {}
	@Autowired
	private RedisUtil redisUtil;
	/**
	 * 拦截登录成功方法，生成token，放入Response的header中
	 * 
	 * @param joinPoint
	 * @param ret
	 * @throws Throwable
	 */
	@AfterReturning(pointcut = "loginPointcut()", returning = "ret")
	public void afterReturning(JoinPoint joinPoint, Object ret) throws Throwable {
		if (ret instanceof Result) {
			// 如果登录成功，生成token
			if (StatusEnum.SUCCESS.equals(((Result<?>) ret).getStatusEnum())) {
				String userId = ContextContainer.getContext().getUserId();
				
				//上下文中含有userId
				if (null != userId && userId.length() > 0) {
					HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
					String tokenStr=JwtUtil.createToken(userId);
					response.setHeader(JwtConsts.TOKEN, tokenStr);
					redisUtil.set(tokenStr,userId, RedisConsts.EXPIRATION);
				}
			}
		}
	}
}
