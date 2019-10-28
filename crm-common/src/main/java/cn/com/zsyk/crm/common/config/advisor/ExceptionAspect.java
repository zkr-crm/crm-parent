package cn.com.zsyk.crm.common.config.advisor;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.apache.commons.logging.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.IdGeneraterException;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.LogUtil;
import io.jsonwebtoken.JwtException;

/**
 * 全局异常处理切面：利用 @ControllerAdvice + @ExceptionHandler组合处理控制器层RuntimeException异常
 * 
 * @author
 */
@ResponseBody
@ControllerAdvice
@SuppressWarnings("rawtypes")
public class ExceptionAspect {
	private Log log = LogUtil.getLogger(ExceptionAspect.class);

	/**
	 * 400 - Bad Request
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public Result handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		log.error("控制器绑定方法参数异常...", e);
		return new Result().exception("控制器绑定方法参数异常...");
	}

	/**
	 * 500 - 控制器返回数据绑定异常
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(HttpMessageNotWritableException.class)
	public Result handleHttpMessageNotWritableException(HttpMessageNotWritableException e) {
		log.error("控制器绑定返回数据异常...", e);
		return new Result().exception("控制器绑定返回数据异常...");
	}

	/**
	 * 400 - 参数校验未通过，统一处理
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ValidationException.class, BindException.class, MethodArgumentNotValidException.class})
	public Result<?> handleValidationException(Exception e) {
		log.error("控制器校验参数异常...", e);
		
		//@Validated 校验方法【普通类型参数】，直接在参数前写@NotNull等校验注解
		if(e instanceof ValidationException) {
			if(e instanceof ConstraintViolationException) {
				Set<ConstraintViolation<?>> cvs = ((ConstraintViolationException)e).getConstraintViolations();
				StringBuilder errMsg = new StringBuilder();
				cvs.forEach(cv ->{
					errMsg.append(cv.getPropertyPath()+cv.getMessage()+"，输入值为["+cv.getInvalidValue()+"]。");
				});
				
				return new Result().exception("控制器校验参数异常："+errMsg.toString());
			}
		}else {
			//@Valid 校验方法【对象类型参数】，对象中写@NotNull等校验注解
			BindingResult br = null;
			if(e instanceof BindException) {
				br = ((BindException)e).getBindingResult();
			}else if(e instanceof MethodArgumentNotValidException) {
				br = ((MethodArgumentNotValidException)e).getBindingResult();
			}
			
			if (null != br) {
				StringBuilder errMsg = new StringBuilder();
				br.getFieldErrors().forEach(error ->{
					errMsg.append(error.getObjectName()+"."+error.getField()+error.getDefaultMessage()+"，输入值为["+error.getRejectedValue()+"]。");
				});
				return new Result().exception("控制器校验参数异常："+errMsg.toString());
			}
		}
		
		return new Result().exception("控制器校验参数异常...");
	}

	/**
	 * 405 - Method Not Allowed。HttpRequestMethodNotSupportedException
	 * 是ServletException的子类,需要Servlet API支持
	 */
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public Result<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
		log.error("控制器不支持Http请求的方式...", e);
		return new Result().exception("控制器不支持Http请求的方式...");
	}

	/**
	 * 415 - Unsupported Media Type。HttpMediaTypeNotSupportedException
	 * 是ServletException的子类,需要Servlet API支持
	 */
	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	@ExceptionHandler({ HttpMediaTypeNotSupportedException.class })
	public Result<?> handleHttpMediaTypeNotSupportedException(Exception e) {
		log.error("控制器不支持Http请求的MediaType...", e);
		return new Result().exception("控制器不支持Http请求的MediaType...");
	}
	
	/**
	 * 200 - 业务异常正常返回
	 */
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler({ServiceException.class})
	public Result<?> handleServiceException(Exception e) {
		//ServiceException是正常的业务情况，用来控制业务流畅，返回处理失败即可；
		return new Result().fail(e.getMessage());
	}
	
	/**
	 * 401 - JWT验证失败
	 */
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler({ JwtException.class })
	public Result<?> handleJwtException(Exception e) {
		log.error("Token验证失败："+e.getMessage(), e);
		return new Result().exception("Token验证失败："+e.getMessage());
	}
	
	/** 
	 * 500 - 服务端异常
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({IdGeneraterException.class})
	public Result<?> handleIdGeneraterException(Exception e) {
		log.error("获取分布式ID失败："+e.getMessage(), e);
		return new Result().exception("获取分布式ID失败："+e.getMessage());
	}
	
	/** 
	 * 500 - 服务端异常
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({Throwable.class})
	public Result<?> handleException(Exception e) {
		//其他类型的异常是系统真正的异常，需打印异常日志，返回异常信息
		log.error("系统异常："+e.getMessage(), e);
		return new Result().exception("系统异常："+e.getMessage());
	}

}