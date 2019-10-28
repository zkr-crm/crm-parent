package cn.com.zsyk.crm.common.constant;

import java.nio.charset.Charset;

import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 系统配置相关常量
 * @author
 */
public interface ConfigConsts {
	String DATA_STYLE1 = "yyyy-MM-dd HH:mm:ss";
	String DATA_STYLE2 = "yyyy/MM/dd HH:mm:ss";
	String SHORT_DATE_STYLE1 = "yyyy-MM-dd";
	String SHORT_DATE_STYLE2 = "yyyy/MM/dd";
	
	/**
	 * FastJson序列化特性
	 */
	SerializerFeature[] SERIALIZER_FEATURE = { 
			//SerializerFeature.PrettyFormat,		//格式化
			//SerializerFeature.WriteNullStringAsEmpty,//String为null时输出""
			SerializerFeature.WriteDateUseDateFormat, //使用dataFormat输出日期类型
			SerializerFeature.WriteEnumUsingToString //使用枚举的toString方法输出枚举值
	};
	
	
	Charset CHARSET_UTF8 = Charset.forName("UTF-8");
}
