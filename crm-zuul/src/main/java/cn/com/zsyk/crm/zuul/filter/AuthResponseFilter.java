package cn.com.zsyk.crm.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StreamUtils;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

/**
 * User: linbangbo
 * Date: 2018-12-13 09:16
 */
@Slf4j
@Configuration
public class AuthResponseFilter extends ZuulFilter {

	@Override
	public String filterType() {
		return FilterConstants.POST_TYPE;
	}

	@Override
	public int filterOrder() {
		return FilterConstants.SEND_RESPONSE_FILTER_ORDER -2;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		try {
			RequestContext context = RequestContext.getCurrentContext();
			String contentType=context.getRequest().getContentType();
			if(contentType==null ||contentType.indexOf("multipart/form-data")==-1){
				InputStream stream = context.getResponseDataStream();
				String body = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
				/*delete by linbangbo 20190726 前端未加密，暂时去除加密
				if (StringUtils.isNotBlank(body)) {
					body= DESUtil.encryption(body,DESUtil.key);
				}*/
				if(HttpStatus.SC_UNAUTHORIZED==context.getResponse().getStatus()){
					context.getResponse().setStatus(999);
				}
				context.setResponseBody(body);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
