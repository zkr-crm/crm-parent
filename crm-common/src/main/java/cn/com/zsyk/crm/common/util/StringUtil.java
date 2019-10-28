package cn.com.zsyk.crm.common.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * User: linbangbo
 * Date: 2018-02-09 14:59
 */
public class StringUtil {
	public static boolean isBlank(String str) {
		return StringUtils.isBlank(str);
	}

	public static boolean isNotBlank(String str) {
		return StringUtils.isNotBlank(str);
	}

	public static String toEmpty(Object object) {
		return object == null ? "" : object.toString();
	}

	/**
	 * 首字母大写，其余字符不变
	 * @param str 输入字符串
	 * @return 首字母大写的字符串
	 */
	public static String toCapture(String str) {
		return Character.toUpperCase(str.charAt(0)) + str.substring(1);
	}

	/**
	 * 将驼峰字符串中大写字母、数字前插入下划线
	 * @param camel 驼峰形式字符串
	 * @return 带有下划线的字符串
	 */
	public static String camelStringInsertUnderline(String camel) {
		List<Integer> indexList = new ArrayList<>();
		for (int i = 0, length = camel.length(); i < length; i++) {
			char c = camel.charAt(i);

			int size = indexList.size();
			if (size == 0) {
				indexList.add(i);
			} else {
				char pc = camel.charAt(i - 1);
				if (Character.isDigit(pc) == Character.isDigit(c)) {
					if (Character.isLowerCase(pc) && Character.isUpperCase(c)) {
						indexList.add(i);
					}
				} else {
					indexList.add(i);
				}
			}
		}

		StringBuilder result = new StringBuilder();
		for (int i = 0, size = indexList.size() - 1; i < size; i++) {
			result.append(camel, indexList.get(i), indexList.get(i + 1)).append("_");
		}
		result.append(camel.substring(indexList.get(indexList.size() - 1)));

		return result.toString();
	}

	/**
	 * long 转字符串
	 * @param lon
	 * @return
	 */
	public static String lonToStr(Long lon) {
		if ("".equals(lon) || null == lon) {
			return "";
		}
		return String.valueOf(lon);
	}

	/**
	 * bigDecimal转字符串
	 * @param bigDecimal
	 * @return
	 */
	public static String bigToStr(BigDecimal bigDecimal) {
		if ("".equals(bigDecimal) || null == bigDecimal) {
			return null;
		}
		return String.valueOf(bigDecimal);
	}

	/**
	 * 字符串转bigDecimal
	 * @param
	 * @return
	 */
	public static BigDecimal strToBig(String str) {
		if (null == str || "".equals(str)) {
			str = "0";
		}
		BigDecimal bigDecimal = null;
		try {
			bigDecimal = new BigDecimal(str);
		}catch (Exception e){
			bigDecimal = new BigDecimal("0");
		}

		return bigDecimal;
	}
	/**
	 * str转Integer
	 * @param
	 * @return
	 */
	public static Integer strToInt(String str) {
		if (null == str || "".equals(str)) {
			return  null;
		}
		Integer integer = null;
		try {
			integer = Integer.parseInt(str);
		}catch (Exception e){
			integer = 0;
		}

		return integer;
	}
	/**
	 * str转Long
	 * @param
	 * @return
	 */
	public static Long strToLong(String str) {
		if (null == str || "".equals(str)) {
			return  null;
		}
		Long lon = null;
		try {
			lon = Long.parseLong(str);
		}catch (Exception e){
			lon = 0l;
		}
		return lon;
	}

	/**
	 * str转short
	 * @param str
	 * @return
	 */
	public static Short strToShort(String str) {
		if ("".equals(str) || null == str) {
			return 0;
		}
		return Short.valueOf(str);
	}
}
