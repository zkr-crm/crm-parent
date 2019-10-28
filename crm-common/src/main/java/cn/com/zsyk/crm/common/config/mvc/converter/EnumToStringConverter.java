package cn.com.zsyk.crm.common.config.mvc.converter;

import java.lang.reflect.Method;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.ReflectionUtils;


/**
 * Enum->String的类型转换器
 * @author
 */
public class EnumToStringConverter implements Converter<Enum<?>, String> {

	@Override
	public String convert(Enum<?> source) {
		//获取自定义枚举的toEnum方法
		Method valueMethod = ReflectionUtils.findMethod(source.getClass(), "getValue");
		if(null != valueMethod){
			return (String)ReflectionUtils.invokeMethod(valueMethod, source);
		}
		
		return source.name();
	}

}