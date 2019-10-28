package cn.com.zsyk.crm.common.util;

import com.github.pagehelper.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * IP工具类
 * @author
 */
@Slf4j
public class IPUtil {
	  
    /** 
     * 通过request对象获取用获取用户真实IP地址
     * 不使用request.getRemoteAddr()的原因是有可能用户使用了代理软件方式避免真实IP地址, 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，
     * 究竟哪个才是真正的用户端的真实IP呢？ 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。 
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130, 192.168.1.100 
     * 用户真实IP为： 192.168.1.110 
     *  
     * @param request 
     * @return 
     */  
    public static String getIpAddress(HttpServletRequest request) {  
        String ip = request.getHeader("x-forwarded-for");  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_CLIENT_IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getRemoteAddr();  
        }  
        return ip;  
    }

    public static boolean isRequestFromBrowser(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        log.info("Request User-Agent: " + userAgent);

        if (StringUtil.isEmpty(userAgent)) {
            return false;
        }

        userAgent = userAgent.toLowerCase();
        boolean result = (userAgent.contains("chrome") && userAgent.contains("safari"))
                || userAgent.contains("firefox")
                || (userAgent.contains("safari") && !userAgent.contains("chrome"))
                || userAgent.contains("edge")
                || (userAgent.contains("compatible") && userAgent.contains("msie"))
                || userAgent.contains("opera")
                || userAgent.contains("trident");

        log.info("Request From Browser: " + result);

        return result;
    }
}
