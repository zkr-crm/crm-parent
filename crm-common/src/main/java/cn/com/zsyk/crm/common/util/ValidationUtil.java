package cn.com.zsyk.crm.common.util;

import java.lang.reflect.Method;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import cn.com.zsyk.crm.common.dto.ValidationBean;
import cn.com.zsyk.crm.common.dto.ValidationResult;

/**
 * 校验工具类
 * @author
 */
@Component
public class ValidationUtil {
	private static Validator validator;
	private static ParameterNameDiscoverer nameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
    
    /**
     * 静态注入Spring的Bean
     * @param validator
     */
    @Autowired 
    public void setValidator(Validator validator) {  
    	if((null!=validator) && (validator instanceof LocalValidatorFactoryBean) ) {
    		ValidationUtil.validator = validator;  
    	}else {
    		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    		ValidationUtil.validator = validatorFactory.getValidator();
    	}
    }
    
	/**
	 * 验证对象
	 * @param obj
	 * @return
	 */
	public static <T> ValidationResult validateEntity(T obj) {
		ValidationResult result = new ValidationResult();
		
		Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
		if (CollectionUtils.isNotEmpty(set)) {
			result.setErrors(true);
			set.forEach( cv -> {
				ValidationBean error = new ValidationBean();
				error.setField(cv.getPropertyPath().toString());
				error.setValue(cv.getInvalidValue());
                error.setErrMsg(cv.getMessage());
                result.addError(error);
			});
		}
		return result;
	}

	/**
	 * 验证对象的某个属性
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static <T> ValidationResult validateProperty(T obj, String fieldName) {
		ValidationResult result = new ValidationResult();
		
		Set<ConstraintViolation<T>> set = validator.validateProperty(obj, fieldName, Default.class);
		if (CollectionUtils.isNotEmpty(set)) {
			result.setErrors(true);
			for (ConstraintViolation<T> cv : set) {
				ValidationBean error = new ValidationBean();
                error.setField(fieldName);
                error.setValue(cv.getInvalidValue());
                error.setErrMsg(cv.getMessage());
                result.addError(error);
			}
		}
		return result;
	}
	
	/**
	 * 验证方法的参数值
	 * @param obj
	 * @param propertyName
	 * @return
	 */
	public static <T> ValidationResult validateParameters(T obj, Method method, Object[] parameterValues) {
		ValidationResult result = new ValidationResult();
		
		Set<ConstraintViolation<T>> validResult = null;
		if(validator instanceof LocalValidatorFactoryBean) {
			validResult = ((LocalValidatorFactoryBean)validator).getValidator().forExecutables().validateParameters(obj, method, parameterValues, Default.class);
		}else {
			validResult = validator.forExecutables().validateParameters(obj, method, parameterValues, Default.class);
		}
		
		if (CollectionUtils.isNotEmpty(validResult)) {
			result.setErrors(true);
            String[] parameterNames = nameDiscoverer.getParameterNames(method); // 获得方法的参数名称
            validResult.forEach(cv -> {
            	// 获得校验的参数路径信息
            	PathImpl pathImpl = (PathImpl) cv.getPropertyPath();  
            	// 获得校验的参数位置
                int paramIndex = pathImpl.getLeafNode().getParameterIndex();
                ValidationBean error = new ValidationBean();
                error.setField(parameterNames[paramIndex]);
                error.setValue(parameterValues[paramIndex]);
                error.setErrMsg(cv.getMessage());
                result.addError(error);
            });
        }

		return result;
	}
}
