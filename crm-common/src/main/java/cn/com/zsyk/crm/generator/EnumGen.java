package cn.com.zsyk.crm.generator;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cn.com.zsyk.crm.common.config.DataSourceConfig;

import cn.com.zsyk.crm.common.constant.RedisConsts;
import cn.com.zsyk.crm.common.util.RedisUtil;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnumGen {
	@Autowired
	private DataSourceConfig dataSourceConfig;
	@Autowired
	private RedisUtil redisUtil;
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws Exception {
		System.out.println("*******枚举文件生成start******");
		Configuration configuration = new Configuration(new Version("2.3.27"));
		configuration.setDefaultEncoding("UTF-8");
		configuration.setOutputEncoding("UTF-8");
		configuration.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
		configuration.setTemplateLoader(new ClassTemplateLoader(EnumGen.class, "/templates"));
		configuration.setObjectWrapper(new DefaultObjectWrapper(new Version("2.3.27")));

		Template template = configuration.getTemplate("enum.ftl");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("className", "EnumType");
		param.put("description", "自动生成java类");
		List<Map<String, String>> enums = EnumGen.getEnumDataFromDb();
		Map newEnums = (Map) enums.stream().collect(Collectors.groupingBy(map -> ((Map) map).get("CODE_TYPE")));
		param.put("enums", newEnums);

		String path = EnumGen.class.getResource("/").getPath();
		path = path.substring(0, path.length() - 16) + "/src/main/java/cn/com/zsyk/crm/generator/EnumType.java";
		try (Writer writer = new OutputStreamWriter(new FileOutputStream(path), "UTF-8");) {
			template.process(param, writer);
		}
		System.out.println("*******枚举文件生成end******");
	}

	/**
	 * maven clean or manually created enum class
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public static List<Map<String, String>> getEnumDataFromDb() throws Exception {
		List<Map<String, String>> list = null;
		Driver.class.forName("oracle.jdbc.driver.OracleDriver");
		String sql = "select code_val,code_type,code_name,code_desc,type_desc from sys_code_mng";
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//10.16.8.175:1521/crmdev", "crmdev",
				"crmdev$test"); Statement ps = conn.createStatement(); ResultSet rs = ps.executeQuery(sql);) {
			list = resultSetToList(rs);
		}
		return list;
	}

	@SuppressWarnings("static-access")
	//modify by linbangbo 20190719 begin 去除static。 写死的url调整为配置文件获取
	public List<Map<String, String>> getEnumFromDb() throws Exception {
		List<Map<String, String>> list = null;
		Driver.class.forName("oracle.jdbc.driver.OracleDriver");
		String sql = "select code_val,code_type,code_name,code_desc,type_desc from sys_code_mng";
		try{
			if(redisUtil.exists(sql)){
				return (List<Map<String, String>>)redisUtil.get(sql);
			}
		}catch (Exception e){

		}

		try (Connection conn = dataSourceConfig.manageDataSource().getConnection();
				Statement ps = conn.createStatement();
				ResultSet rs = ps.executeQuery(sql);) {
			list = resultSetToList(rs);
		}
		try{
			if(list!=null && list.size()>0){
				redisUtil.set(sql,list, RedisConsts.EXPIRATION);
			}
		}catch (Exception e){

		}
		return list;
	}
	//modify by linbangbo 20190719 end 去除static。 写死的url调整为配置文件获取
	
	public static List<Map<String, String>> resultSetToList(ResultSet rs) throws java.sql.SQLException {
		if (rs == null)
			return Collections.emptyList();
		ResultSetMetaData md = rs.getMetaData(); // 得到结果集(rs)的结构信息，比如字段数、字段名等
		int columnCount = md.getColumnCount(); // 返回此 ResultSet 对象中的列数
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> rowData = new HashMap<String, String>();
		while (rs.next()) {
			rowData = new HashMap<String, String>(columnCount);
			for (int i = 1; i <= columnCount; i++) {
				rowData.put((String) md.getColumnName(i), (String) rs.getObject(i));
			}
			list.add(rowData);
		}
		return list;
	}
}
