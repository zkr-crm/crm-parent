package cn.com.zsyk.crm.common.util;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import cn.com.zsyk.crm.common.constant.ServiceType;

/**
 * Rest客户端工具
 * 
 * GET请求正确用法：<br/>
 * String url = "http://localhost:8080/test/sendSms?phone={phone}&msg={phone}";<br/>
 * Map<String, Object> uriVariables = new HashMap<String, Object>();<br/>
 * uriVariables.put("phone", "151xxxxxxxx");<br/>
 * uriVariables.put("msg", "测试短信内容");<br/>
 * String result = restUtil.getForObject(ServiceType, url, String.class, uriVariables); * <br/>
 * 等价于restUtil.getForObject(ServiceType, url, String.class,  "151xxxxxxxx", "测试短信内容"); <br/>
 * 
 * 
 * @author
 */
@Component
public class RestUtil {
	private final RestTemplate restTemplate;
	
	/**
	 * 构造方法的自动注入，使用@Autowired得到null
	 */
	public RestUtil(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	/**
	 * 调用get方法
	 * @param serviceType	微服务类型
	 * @param url			请求url，以/开头
	 * @param responseType	返回数据类型
	 * @param uriVariables	URL可变参数对象
	 * @return
	 * @throws RestClientException
	 */
	public <T> T getForObject(ServiceType serviceType, String url, Class<T> responseType, Object... uriVariables) throws RestClientException {
		return this.restTemplate.getForObject("http://"+serviceType+url, responseType, uriVariables);
	}
	
	/**
	 * 调用get方法
	 * @param serviceType	微服务类型
	 * @param url			请求url，以/开头
	 * @param responseType	返回数据类型
	 * @param uriVariables	URL参数Map
	 * @return
	 * @throws RestClientException
	 */
	public <T> T getForObject(ServiceType serviceType, String url, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException {
		return this.restTemplate.getForObject("http://"+serviceType+url, responseType, uriVariables);
	}
	
	/**
	 * 调用get方法
	 * @param serviceType	微服务类型
	 * @param url			请求url，以/开头
	 * @param responseType	返回数据类型
	 * @return
	 * @throws RestClientException
	 */
	public <T> T getForObject(ServiceType serviceType, String url, Class<T> responseType) throws RestClientException {
		return this.restTemplate.getForObject("http://"+serviceType+url, responseType);
	}
	
	/**
	 * 调用get方法
	 * @param serviceType	微服务类型
	 * @param url			请求url，以/开头
	 * @param responseType	返回数据类型
	 * @param uriVariables	URL可变参数对象
	 * @return
	 * @throws RestClientException
	 */
	public <T> ResponseEntity<T> getForEntity(ServiceType serviceType, String url, Class<T> responseType, Object... uriVariables) throws RestClientException {
		return this.restTemplate.getForEntity("http://"+serviceType+url, responseType, uriVariables);
	}
	
	/**
	 * 调用get方法
	 * @param serviceType	微服务类型
	 * @param url			请求url，以/开头
	 * @param responseType	返回数据类型
	 * @param uriVariables	URL参数Map
	 * @return
	 * @throws RestClientException
	 */
	public <T> ResponseEntity<T> getForEntity(ServiceType serviceType, String url, Class<T> responseType,Map<String, ?> uriVariables) throws RestClientException {
		return this.restTemplate.getForEntity("http://"+serviceType+url, responseType, uriVariables);
	}
	
	/**
	 * 调用get方法
	 * @param serviceType	微服务类型
	 * @param url			请求url，以/开头
	 * @param responseType	返回数据类型
	 * @return
	 * @throws RestClientException
	 */
	public <T> ResponseEntity<T> getForEntity(ServiceType serviceType, String url, Class<T> responseType) throws RestClientException {
		return this.restTemplate.getForEntity("http://"+serviceType+url, responseType);
	}
	
	
	/**
	 * 调用post方法
	 * @param serviceType	微服务类型
	 * @param url			请求url，以/开头
	 * @param postObj		post请求体对象或HttpEntity对象
	 * @param responseType	返回数据类型
	 * @return
	 * @throws RestClientException
	 */
	public <T> T postForObject(ServiceType serviceType, String url, Object postObj, Class<T> responseType) throws RestClientException {
		return this.restTemplate.postForObject("http://"+serviceType+url, postObj, responseType);
	}
	
	/**
	 * 调用post方法
	 * @param serviceType	微服务类型
	 * @param url		请求url，以/开头
	 * @param postObj		post请求对象或HttpEntity对象
	 * @param responseType	返回数据类型
	 * @return
	 * @throws RestClientException
	 */
	public <T> ResponseEntity<T> postForEntity(ServiceType serviceType, String url, Object postObj, Class<T> responseType) throws RestClientException {
		return this.restTemplate.postForEntity("http://"+serviceType+url, postObj, responseType);
	}
	
	
	
	/**
	 * 调用post方法
	 * @param serviceType	微服务类型
	 * @param url		请求url，以/开头
	 * @param postObj		post请求对象或HttpEntity对象
	 * @param responseType	返回数据类型
	 * @param uriVariables	URL参数对象
	 * @return
	 * @throws RestClientException
	 */
	public <T> T postForObject(ServiceType serviceType, String url, Object postObj, Class<T> responseType, Object... uriVariables) throws RestClientException {
		return this.restTemplate.postForObject("http://"+serviceType+url, postObj, responseType, uriVariables);
	}
	
	/**
	 * 调用post方法
	 * @param serviceType	微服务类型
	 * @param url		请求url，以/开头
	 * @param postObj		post请求对象或HttpEntity对象
	 * @param responseType	返回数据类型
	 * @param uriVariables	URL参数对象
	 * @return
	 * @throws RestClientException
	 */
	public <T> ResponseEntity<T> postForEntity(ServiceType serviceType, String url, Object postObj, Class<T> responseType, Object... uriVariables) throws RestClientException {
		return this.restTemplate.postForEntity("http://"+serviceType+url, postObj, responseType, uriVariables);
	}
	
	/**
	 * 调用post方法
	 * @param serviceType	微服务类型
	 * @param url			请求url，以/开头
	 * @param postObj		post请求对象或HttpEntity对象
	 * @param responseType	返回数据类型
	 * @param uriVariables	URL参数Map
	 * @return
	 * @throws RestClientException
	 */
	public <T> T postForObject(ServiceType serviceType, String url, Object postObj, Class<T> responseType, Map<String,?> uriVariables) throws RestClientException {
		return this.restTemplate.postForObject("http://"+serviceType+url, postObj, responseType, uriVariables);
	}
	
	/**
	 * 调用post方法
	 * @param serviceType	微服务类型
	 * @param url			请求url，以/开头
	 * @param postObj		post请求对象或HttpEntity对象
	 * @param responseType	返回数据类型
	 * @param uriVariables	URL参数Map
	 * @return
	 * @throws RestClientException
	 */
	public <T> ResponseEntity<T> postForEntity(ServiceType serviceType, String url, Object postObj, Class<T> responseType, Map<String,?> uriVariables) throws RestClientException {
		return this.restTemplate.postForEntity("http://"+serviceType+url, postObj, responseType, uriVariables);
	}
	
}
