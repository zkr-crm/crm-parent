package cn.com.zsyk.crm.common.config.mvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import cn.com.zsyk.crm.common.util.JwtUtil;
import cn.com.zsyk.crm.common.constant.JwtConsts;
import cn.com.zsyk.crm.common.dto.ContextContainer;

/**
 * Jwt拦截器，判断Token的合法性、刷新token
 * @author
 */
@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//验证Token，如果验证失败直接抛出自定义的JwtException异常，由 ExceptionAspect 处理
		Claims claims = JwtUtil.validateToken(request.getHeader(JwtConsts.TOKEN));
		
		//Token有效时间小于REF_TIME，自动生成新的Token
		long expiration = claims.getExpiration().getTime();
		long now = System.currentTimeMillis();
		if(expiration-now <= 0 ) {
			throw new JwtException("超过有效期！");
		}else {
			if(expiration-now < JwtConsts.REF_TIME) {
				//刷新Token
				response.setHeader(JwtConsts.TOKEN, JwtUtil.createToken(ContextContainer.getContext().getUserId()));
			}
		}
		
		return super.preHandle(request, response, handler);
	} 
}
