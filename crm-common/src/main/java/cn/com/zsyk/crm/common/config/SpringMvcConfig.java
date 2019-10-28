package cn.com.zsyk.crm.common.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
//import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

import cn.com.zsyk.crm.common.config.mvc.converter.EnumToStringConverter;
import cn.com.zsyk.crm.common.config.mvc.converter.StringToDateConverter;
import cn.com.zsyk.crm.common.config.mvc.converter.StringToEnumConverterFactory;
import cn.com.zsyk.crm.common.config.mvc.interceptor.ContextInterceptor;
import cn.com.zsyk.crm.common.config.mvc.interceptor.JwtInterceptor;
import cn.com.zsyk.crm.common.constant.ConfigConsts;

/**
 * SpringMVC的自定义配置，可参见{@link org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration}
 * SpringMVC的验证框架配置，可参见{@link org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration}
 * @author
 */
@Configuration
public class SpringMvcConfig extends WebMvcConfigurerAdapter {
	@Autowired
	private ContextInterceptor contextInterceptor;
	
	@Autowired
	private JwtInterceptor jwtInterceptor;
	
	@Autowired
	private FastJsonHttpMessageConverter fastJsonConverter;

	@Bean
	public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(ConfigConsts.SERIALIZER_FEATURE);
		fastJsonConfig.setDateFormat(ConfigConsts.DATA_STYLE1);
		fastJsonConfig.setCharset(ConfigConsts.CHARSET_UTF8);
		
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		fastConverter.setSupportedMediaTypes(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON_UTF8, MediaType.TEXT_HTML }));
		fastConverter.setFastJsonConfig(fastJsonConfig);
		
		return fastConverter;
	}
	
	@Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(10000);// ms
        factory.setConnectTimeout(10000);// ms
        return factory;
    }
	
	/**
	 * 注册RestTemplate
	 * 添加Ribbon的客户端负载均衡功能, 如果不加@LoadBalanced的话，会报java.net.UnknownHostException异常，此时无法通过注册到Eureka Server上的服务名来调用服务，
	 *  因为RestTemplate是无法从服务名映射到ip:port的，映射的功能是由 LoadBalancerClient 来实现的。
	 *  */
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
		HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(30000000);
        httpRequestFactory.setConnectTimeout(30000000);
        httpRequestFactory.setReadTimeout(60000000);

        RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
		
		//设置HttpMessageConverter
		List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
		messageConverters.forEach(converter -> {
			//StringHttpMessageConverter默认使用ISO-8859-1编码
			if(converter instanceof StringHttpMessageConverter) {
				((StringHttpMessageConverter) converter).setDefaultCharset(ConfigConsts.CHARSET_UTF8);
			} 
		});
		messageConverters.add(fastJsonConverter);
		
		return restTemplate;
	}
	
	/**
	 * 添加自定义Converter和Formatter
	 */
	@Override
	public void addFormatters(FormatterRegistry registry) {
		//添加日期类型格式化器，也可通过spring.mvc.date-format配置
		//registry.addFormatter(new DateFormatter("yyyy-MM-dd HH:mm:ss"));
		
		//添加自定义日期类型转换器
		registry.addConverter(new StringToDateConverter());

		//添加Enum->String的类型转换器
		registry.addConverter(new EnumToStringConverter());
		
		//添加String->Enum的类型转换工厂
		registry.addConverterFactory(new StringToEnumConverterFactory());
	}

	/**
	 * 添加自定义HttpMessageConverter
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(fastJsonConverter);
	}

	/**
	 * 添加自定义Interceptor
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(contextInterceptor).addPathPatterns("/crm/*/**");
		//registry.addInterceptor(jwtInterceptor).addPathPatterns("/crm/*/**")
		//		.excludePathPatterns("/crm/manage/usermng/userLoginChk",
		//							"/crm/manage/logmng/addLoginLog",
		//							"/crm/manage/menuAuth",
		//							"/crm/manage/enum");
	}

	/**
	 * 静态资源映射
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	}

	/**
	 * 添加跨域映射
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/crm/*/**")
			//.allowedHeaders("apidoc")
			.allowedMethods("GET", "PUT", "POST", "DELETE", "OPTIONS");
	}

}
