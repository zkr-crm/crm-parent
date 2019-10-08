package cn.com.zsyk.crm.common.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.zsyk.crm.common.constant.ConfigConsts;

/**
 * JSON的工具类
 * 
 * @author
 */
public class JsonUtil {
	/**
	 * 将对象序列化成json串
	 * 
	 * @param object
	 * @return
	 */
	public static String toJSONString(Object object) {
		return JSONObject.toJSONStringWithDateFormat(object, ConfigConsts.DATA_STYLE1, ConfigConsts.SERIALIZER_FEATURE);
	}

	/**
	 * 将对象序列化成json串后输出到OutputStream
	 * 
	 * @param out
	 * @param object
	 * @return
	 * @throws IOException
	 */
	public static int writeJSONString(OutputStream out, Object object) throws IOException {
		return JSONObject.writeJSONString(out, object, ConfigConsts.SERIALIZER_FEATURE);
	}

	/**
	 * 将JSON字符串反序列化成clazz类
	 * 
	 * @param javaObject
	 * @return
	 */
	 public static <T> T parseObject(String json, Class<T> clazz) {
        return JSONObject.parseObject(json, clazz);
    }

	/**
	 * 将JSON字符串反序列化成clazz列表
	 * 
	 * @param javaObject
	 * @return
	 */
	 public static <T> List<T> parseArray(String text, Class<T> clazz) {
		return JSONObject.parseArray(text, clazz);
    }
	
	/**
	 * 将JSON字符串反序列化成JSONObject
	 * 
	 * @param javaObject
	 * @return
	 */
	public static JSONObject parseObject(String text) {
		return JSONObject.parseObject(text);
    }

	/**
	 * 将JSON字符串反序列化成JSONArray
	 * 
	 * @param javaObject
	 * @return
	 */
	public static JSONArray parseArray(String text) {
		return JSONObject.parseArray(text);
    }

	
	/**
	 * 将JavaBean转换为JSONObject或者JSONArray
	 * 
	 * @param javaObject
	 * @return
	 */
	public static Object toJSON(Object javaObject) {
		return JSONObject.toJSON(javaObject);
	}

}
