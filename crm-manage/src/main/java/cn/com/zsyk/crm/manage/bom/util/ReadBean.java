package cn.com.zsyk.crm.manage.bom.util;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class ReadBean {

	/**
	 * 返回指定bean的全部属性和对应类型
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> getModelAttriButeType(Object model) throws Exception {
		Field[] field = model.getClass().getDeclaredFields();
		Map<String, String> map = new HashMap<String, String>();
		for (int j = 0; j < field.length; j++) {
			String name = field[j].getName();
			String type = field[j].getGenericType().toString();
			type = type.replace("class ", "");
			map.put(name, type);
		}
		return map;
	}

	/**
	 * 返回指定bean的指定属性的类型
	 * 
	 * @param model
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> getModelAttriButeType(Object model, String name) throws Exception {
		Field[] field = model.getClass().getDeclaredFields();
		Map<String, String> map = new HashMap<String, String>();
		for (int j = 0; j < field.length; j++) {
			if (name.equals(field[j].getName())) {
				String type = field[j].getGenericType().toString();
				type = type.replace("class ", "");
				map.put(name, type);
				return map;
			}
		}
		return map;
	}

	/**
	 * 返回包下的所有类名字
	 * 包内不能有子包或者其他非class类型文件
	 * @param filePath
	 * @param className
	 * @return
	 */
	public static List<String> getBomClassNames(String packageName) {
		
		String file_package_name = packageName.replaceAll("\\.", "/");
		String filePath = ReadBean.class.getClassLoader().getResource("").getPath().replaceFirst("/", "");
		filePath = filePath+file_package_name;
		String package_name = packageName.concat(".");
		List<String> myClassName = new ArrayList<String>();
		File file = new File(filePath);
		String[] list = file.list();

		for (String string : list) {
			myClassName.add(package_name.concat(string.replace(".class", "")));
		}

		return myClassName;
	}

	/**
	 * 将SQLMAP经过bom的过滤，生成factorMap
	 * 
	 * @param sql_map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<Map> transFactorMap(List<Map> sql_map,String packageName) throws Exception {

		List<Map> list = new ArrayList<Map>();

		for (Map map : sql_map) {
			Map targetMap = new HashMap();
			List<String> bomTagClassName = getBomClassNames(packageName);
			for (String className : bomTagClassName) {
				Object obj = Class.forName(className).newInstance();
				BeanUtils.populate(obj, map);
				Map<String, String> describe = BeanUtils.describe(obj);
				describe.remove("class");
//				List<String> removeKeys = new ArrayList();
//				for (String key : describe.keySet()) {
//					if (describe.get(key) == null)
//						removeKeys.add(key);
//				}
//
//				for (String removeKey : removeKeys) {
//					describe.remove(removeKey);
//				}
//
//				removeKeys.clear();
				targetMap.putAll(describe);

			}
			list.add(targetMap);
		}

		return list;

	}

}