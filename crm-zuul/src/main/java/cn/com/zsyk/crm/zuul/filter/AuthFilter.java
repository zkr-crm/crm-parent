package cn.com.zsyk.crm.zuul.filter;

import cn.com.zsyk.crm.common.constant.JwtConsts;
import cn.com.zsyk.crm.common.constant.RedisConsts;
import cn.com.zsyk.crm.common.util.DateUtil;
import cn.com.zsyk.crm.common.util.IPUtil;
import cn.com.zsyk.crm.common.util.RedisUtil;
import cn.com.zsyk.crm.common.util.StringUtil;
import cn.com.zsyk.crm.zuul.config.AuthConfig;
import cn.com.zsyk.crm.zuul.util.DESUtil;
import cn.com.zsyk.crm.zuul.util.RSAUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.ServletInputStreamWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.*;

/**
 * User: Kyll
 * Date: 2017-12-17 09:16
 */
@Slf4j
@Configuration
public class AuthFilter extends ZuulFilter {
	@Autowired
	private AuthConfig authConfig;
	@Autowired
	private RedisUtil redisUtil;

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();
		String uri = request.getRequestURI();
		String jsonString="";
        StringBuffer stringBuffer = new StringBuffer();
		String sysStr="";
		String contentType=request.getContentType();
		try{
            String method = request.getMethod();

			// request params 处理
			Map params=new HashMap();
			Enumeration<String> enums= request.getParameterNames();
			List<String> arrayList;
			while(enums.hasMoreElements()){
				String paramName=enums.nextElement();
				String paramValue=request.getParameter(paramName);
				//解密处理
				paramValue=URLDecoder.decode(paramValue,"UTF-8");
				//由于前端存在不规范的代码，url中存在未加密的参数。 t_params 为统一加密传，除此之外为非加密串
				if(paramValue!=null && !"".equals(paramValue)){
					try{
						//自定义参数进行解密
						if("t_params".equals(paramName)){
							paramValue=decodeRsa(paramValue);
							//自定义参数转为JSON对象
							JSONObject jsonObject= (JSONObject)JSON.parse(paramValue);
							Iterator<String> it=jsonObject.keySet().iterator();
							while(it.hasNext()){
								String key=it.next();
								String value="";
								if(jsonObject.get(key)!=null){
									value=jsonObject.get(key).toString();
								}
								arrayList=new ArrayList<String>();
								arrayList.add(value+"");
								params.put(key,arrayList);
								if("sys".equals(key)){//sys中含有userId，在校验token时需要用到
									sysStr=value;
								}
							}
						}
					}catch(Exception e){
					}
				}
				arrayList=new ArrayList<String>();
				arrayList.add(paramValue+"");
				params.put(paramName,arrayList);
			}
			if(params.size()>0){
				request.getParameterMap();
				requestContext.setRequestQueryParams(params);
			}

			//参数处理完成后，覆盖 上下文中request，往实例服务中传递
			if (("POST".equals(method)||"PUT".equals(method))&&!(contentType!=null && contentType.indexOf("multipart/form-data")>-1)) {
				//body数据重写HttpServletRequestWrapper
				//requestBody 处理
				BufferedReader bufferedReader = request.getReader();
				while ((jsonString = bufferedReader.readLine()) != null) {
					stringBuffer.append(jsonString);
				}
				//RSA后台解密
				jsonString=decodeRsa(stringBuffer.toString());
				final byte[] reqBodyBytes = jsonString.getBytes();
				requestContext.setRequest(new HttpServletRequestWrapper(request){
					@Override
					public ServletInputStream getInputStream() throws IOException {
						return new ServletInputStreamWrapper(reqBodyBytes);
					}
					@Override
					public BufferedReader getReader() throws IOException{
						return new BufferedReader(new InputStreamReader(new ServletInputStreamWrapper(reqBodyBytes)));
					}
					@Override
					public int getContentLength() {
						return reqBodyBytes.length;
					}
					@Override
					public long getContentLengthLong() {
						return reqBodyBytes.length;
					}
				});
			}/*else{
				throw new Exception("不支持该请求得method方法!");
			}*/
        }catch (Exception e){
            requestContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
        }
		boolean exclude = false;
		for (String path : authConfig.getExcludePaths()) {
			if (uri.matches(path.replace("**/*", ".*"))) {
				exclude = true;
				break;
			}
		}

		if (exclude) {
			log.info("忽略验证: " + uri);
		} else {
			String token = this.getToken(request);
			boolean isBrowser = IPUtil.isRequestFromBrowser(request);

			if (StringUtils.isEmpty(token)) {
				log.info("缺少授权: " + uri);
				process401(requestContext, isBrowser);
			} else {
				//令牌过期后去redis缓存中查询
				String userId="";
				userId=request.getParameter(JwtConsts.USERID);
				if(userId==null || "".equals(userId)){
					userId=request.getHeader(JwtConsts.USERID);
				}
				if((userId==null || "".equals(userId))&&sysStr!=null&&!"".equals(sysStr)){
					JSONObject jsonObject= (JSONObject)JSON.parse(sysStr);
					userId=(String)jsonObject.get(JwtConsts.USERID);
				}
				if(!this.verify(token,userId)){
					log.info("令牌过期: " + uri);
					process401(requestContext, isBrowser);
				}
			}
		}
		return null;
	}

	private String decodeRsa(String txt) throws Exception{
		String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANJSY5fJCZhhKdlpynRu28BkI68I0+3OLECnt4mbOYAbiaDL9WCtj+zUEMuH2pM0chFZd6lS3Ayt/WRi6X8DjmoT/ajEht56rjbfxgNZDsyh05VdH3K0QexPSOkIW+hh9hdhmeehZGssgHKVB5fOrFssPHEHEYKzgCOhEqHlLuc1AgMBAAECgYEAqTB9zWx7u4juEWd45ZEIVgw4aGXBllt0Xc6NZrTn3JZKcH+iNNNqJCm0GQaAXkqiODKwgBWXzttoK4kmLHa/6D7rXouWN8PGYXj7DHUNzyOe3IgmzYanowp/A8gu99mJQJzyhZGQ+Uo9dZXAgUDin6HAVLaxF3yWD8/yTKWN4UECQQD8Q72r7qdAfzdLMMSQl50VxRmbdhQYbo3D9FmwUw6W1gy2jhJyPXMi0JZKdKaqhxMZIT3zy4jYqw8/0zF2xc5/AkEA1W+n24Ef3ucbPgyiOu+XGwW0DNpJ9F8D3ZkEKPBgjOMojM7oqlehRwgy52hU+HaL4Toq9ghL1SwxBQPxSWCYSwJAGQUO9tKAvCDh9w8rL7wZ1GLsG0Mm0xWD8f92NcrHE6a/NAv7QGFf3gAaJ+BR92/WMRPe9SMmu3ab2JS1vzX3OQJAdN70/T8RYo8N3cYxNzBmf4d59ee5wzQb+8WD/57QX5UraR8LS+s8Bpc4uHnqvTq8kZG2YI5eZ9YQ6XwlLVbVTQJAKOSXNT+XEPWaol1YdWZDvr2m/ChbX2uwz52s8577Tey96O4Z6S/YA7V6Fr7hZEzkNF+K0LNUd79EOB6m2eQq5w==";
		String[] jsonArray = txt.split(";;;;");
		String jsonString = "";
		String[] jsonArrays = new String[jsonArray.length];
		for(int i=0;i<jsonArray.length;i++){
			jsonArrays[i] = RSAUtil.decryptByPriKey(jsonArray[i],privateKey);
			jsonString = jsonString + jsonArrays[i];
		}
		return jsonString;
	}
	private void process401(RequestContext requestContext, boolean isBrowser) {
		requestContext.setSendZuulResponse(false);
		HttpServletRequest request = requestContext.getRequest();
		String uri = request.getRequestURI();
		log.info("401: " + uri);
		requestContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
	}

	public Boolean verify(String token,String userCode) {
		if (redisUtil.exists(token)) {
			String userCodeOld = (String)redisUtil.get(token);
			if(!userCodeOld.equals(userCode)){
				return false;
			}
			redisUtil.set(token, userCode, RedisConsts.EXPIRATION);
			//如果跟随系统刷新RSA钥匙redis时间在此添加（需要先根据token获取原钥匙，然后重新保存）
			String RSAPrivateKey = (String)redisUtil.get(token+"RSAKEY");
			redisUtil.set(token+"RSAKEY",RSAPrivateKey, RedisConsts.EXPIRATION);
			return true;
		} else {
			return false;
		}
	}

	public String getToken(HttpServletRequest request) {
		String token = null;

		String header = request.getHeader(JwtConsts.TOKEN);
		if (StringUtil.isNotBlank(header)) {
			token = header;
		}

		if (StringUtil.isBlank(token)) {
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals(JwtConsts.TOKEN)) {
						token = cookie.getValue();
						break;
					}
				}
			}
		}

		return token;
	}
}
