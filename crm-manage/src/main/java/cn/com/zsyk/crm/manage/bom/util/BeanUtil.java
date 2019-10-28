package cn.com.zsyk.crm.manage.bom.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanCopier;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
* 类 copy 工具类
* @Author:
* @CreateDate:
*/
public class BeanUtil {
	private static final Logger log = LoggerFactory.getLogger(BeanUtil.class);
	private static final Map<String, BeanCopier> BEAN_COPIER_MAP = new HashMap<>();

	public static void copy(Object dest, Object orig) {
		String beanKey = dest.getClass().getName() + "_" + orig.getClass().getName();

		BeanCopier copier;
		if (BEAN_COPIER_MAP.containsKey(beanKey)) {
			copier = BEAN_COPIER_MAP.get(beanKey);
		} else {
			copier = BeanCopier.create(orig.getClass(), dest.getClass(), false);
			BEAN_COPIER_MAP.put(beanKey, copier);
		}

		copier.copy(orig, dest, null);
	}

	public static Map<String, Object> beanToMap(Object object) {
		Map<String, Object> map = new HashMap<>();
		Arrays.stream(object.getClass().getDeclaredFields()).forEach(field -> {
			if (field.getType() != Logger.class) {
				map.put(field.getName(), getFieldValue(object, field));
			}
		});
		return map;
	}

	public static <T> T newInstance(Class<T> clazz) {
		T t = null;
		try {
			t = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			log.error("实例化对象失败", e);
		}
		return t;
	}

	public static Object getFieldValue(Object object, Field field) {
		Object value = null;

		if (field != null) {
			field.setAccessible(true);

			try {
				value = field.get(object);
			} catch (IllegalAccessException e) {
				log.error("获取对象中的属性值失败", e);
			}
		}

		return value;
	}
	public static Object checkNull(Object vo) throws Exception {
		// 获取实体类的所有属性，返回Field数组
		Field[] field = vo.getClass().getDeclaredFields();
		// 获取属性的名字
		for (int i = 0; i < field.length; i++) {
			//可访问私有变量
			field[i].setAccessible(true);
			// 获取属性的名字
			String name = field[i].getName();
			// 获取属性类型
			String type = field[i].getGenericType().toString();
			// 将属性的首字母大写
			name = name.replaceFirst(name.substring(0, 1), name.substring(0, 1)
					.toUpperCase());

			if (type.equals("class java.lang.String")) {
				// 如果type是类类型，则前面包含"class "，后面跟类名
				Method m = vo.getClass().getMethod("get" + name);
				// 调用getter方法获取属性值
				String value = (String) m.invoke(vo);
				if (value==null) {
					//给属性设值
					field[i].set(vo, field[i].getType().getConstructor(field[i].getType()).newInstance(""));
				}
			}
		}
		return vo;
	}


	public static <T> T setNullValue(T source) throws IllegalArgumentException, IllegalAccessException, SecurityException {
		Field[] fields = source.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (field.getGenericType().toString().equals(
					"class java.lang.String")) {
				field.setAccessible(true);
				Object obj = field.get(source);
				if (obj == null ) {
					field.set(source, "");
				}
			}
		}
		return source;
	}
	/**
	 * 自动生成32位的UUid，对应数据库的主键id进行插入用。
	 * @return
	 */
	public static String getUUID() {
		/*UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		// 去掉"-"符号
		String temp = str.substring(0, 8) + str.substring(9, 13)
				+ str.substring(14, 18) + str.substring(19, 23)
				+ str.substring(24);
		return temp;*/

		return UUID.randomUUID().toString().replace("-", "");
	}

}
