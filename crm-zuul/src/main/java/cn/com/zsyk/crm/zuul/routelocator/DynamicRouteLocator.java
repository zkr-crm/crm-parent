package cn.com.zsyk.crm.zuul.routelocator;

import cn.com.zsyk.crm.common.util.JsonUtil;
import cn.com.zsyk.crm.zuul.routelocator.dao.CrmZuulRouteDao;
import cn.com.zsyk.crm.zuul.routelocator.entity.CrmZuulRoute;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.discovery.DiscoveryClientRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.discovery.ServiceRouteMapper;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * User: chuyingfei
 * Date: 2019-07-20 11:30
 */
@Slf4j
public class DynamicRouteLocator extends DiscoveryClientRouteLocator {

	@Autowired
	private CrmZuulRouteDao crmZuulRouteDao;

	public DynamicRouteLocator(String servletPath, DiscoveryClient discovery, ZuulProperties properties, ServiceRouteMapper serviceRouteMapper, ServiceInstance localServiceInstance) {
		super(servletPath, discovery, properties, serviceRouteMapper, localServiceInstance);
	}

	public DynamicRouteLocator(String servletPath, DiscoveryClient discovery, ZuulProperties properties, ServiceRouteMapper serviceRouteMapper, ServiceInstance localServiceInstance, CrmZuulRouteDao evoZuulRouteDao) {
		super(servletPath, discovery, properties, serviceRouteMapper, localServiceInstance);
		this.crmZuulRouteDao = crmZuulRouteDao;
	}

	@Override
	protected LinkedHashMap<String, ZuulProperties.ZuulRoute> locateRoutes() {
		LinkedHashMap<String, ZuulProperties.ZuulRoute> routesMap = new LinkedHashMap();

		Example example = new Example(CrmZuulRoute.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("isEnabled", "1");

		List<CrmZuulRoute> crmZuulRouteList = crmZuulRouteDao.selectByExample(example);
		log.info("加载路由表: " + JsonUtil.toJSONString(crmZuulRouteList));

		for (CrmZuulRoute crmZuulRoute : crmZuulRouteList) {
			String path = crmZuulRoute.getPath();
			String retryableValue = crmZuulRoute.getRetryable();
			String sensitiveHeadersValue = crmZuulRoute.getSensitiveHeaders();

			ZuulProperties.ZuulRoute zuulRoute = new ZuulProperties.ZuulRoute(
					crmZuulRoute.getRouteId(), path, crmZuulRoute.getServiceId(), crmZuulRoute.getUrl(),
					"1".equals(crmZuulRoute.getStripPrefix()),
					StringUtils.isEmpty(retryableValue) ? null : "1".equals(retryableValue),
					StringUtils.isEmpty(sensitiveHeadersValue) ? null : new HashSet<>(Arrays.asList(sensitiveHeadersValue.split(","))));
			routesMap.put(path, zuulRoute);
		}

		return routesMap;
	}
}
