package cn.com.zsyk.crm.manage.drools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 */
public class PropertiesUtil {
    private static Properties properties = new Properties();
    private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    /**
     * 读取properties配置文件信息
     */
    static{
        try {
            properties.load(PropertiesUtil.class.getResourceAsStream("/config/config.properties"));
        } catch (Exception ex) {
            logger.error("load properties error:",ex);
        }
    }

    /**
     * 根据key得到value的值
     */
    public static String getProperty(String key)
    {
    	String value=properties.getProperty(key);
    	if(value!=null&&!value.isEmpty()){
    		return value.trim();	
    	}else{
    		return value;
    	}
    }

//    public static void main(String[] args) throws  Exception{
//        System.out.println(PropertiesUtil.getProperty("redis_password"));
//
//       /* Properties properties = new Properties();
//        Connection connection = null;
//
//        //读取配置文件
////            in = new BufferedInputStream(new FileInputStream("config/config.properties"));
//        InputStream in = PropertiesUtil.class.getResourceAsStream("/config/config.properties");
//        properties.load(in);
//        String user = properties.getProperty("userName");
//        System.out.print(user);
//        in.close();*/
//    }
}
