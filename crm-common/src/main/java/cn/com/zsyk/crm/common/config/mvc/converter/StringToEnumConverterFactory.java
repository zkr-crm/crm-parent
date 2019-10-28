package cn.com.zsyk.crm.common.config.mvc.converter;

import java.lang.reflect.Method;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.util.ReflectionUtils;

/**
 * String->Enum的类型转换工厂
 * @author
 */
@SuppressWarnings("rawtypes")
public class StringToEnumConverterFactory implements ConverterFactory<String, Enum> {

	@Override
	@SuppressWarnings("unchecked")
	public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
		return new StringToEnum(getEnumType(targetType));
	}
	
	private class StringToEnum<T extends Enum> implements Converter<String, T> {

		private final Class<T> enumType;

		public StringToEnum(Class<T> enumType) {
			this.enumType = enumType;
		}

		@SuppressWarnings("unchecked")
		@Override
		public T convert(String source) {
			if (null==source || source.isEmpty()) {
				return null;
			}
			
			//获取自定义枚举的toEnum方法
			Method toEnumMethod = ReflectionUtils.findMethod(enumType, "toEnum", String.class);
			if(null != toEnumMethod){
				return (T)ReflectionUtils.invokeMethod(toEnumMethod, null, source.trim());
			}
			
			return (T) Enum.valueOf(this.enumType, source.trim());
		}
	}
	
	public static Class<?> getEnumType(Class<?> targetType) {
		Class<?> enumType = targetType;
		while (enumType != null && !enumType.isEnum()) {
			enumType = enumType.getSuperclass();
		}
		if (enumType == null) {
			throw new IllegalArgumentException("The target type " + targetType.getName() + " does not refer to an enum");
		}
		return enumType;
	}

}