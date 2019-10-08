package cn.com.zsyk.crm.zuul.config;

import cn.com.zsyk.crm.zuul.routelocator.DynamicRouteLocator;
import cn.com.zsyk.crm.zuul.routelocator.dao.CrmZuulRouteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.discovery.DiscoveryClientRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.discovery.ServiceRouteMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * User: Kyll
 * Date: 2018-08-21 10:13
 */
@Configuration
public class ZuulConfig {
	@Autowired
	protected ServerProperties server;
	@Autowired
	private DiscoveryClient discovery;
	@Autowired
	protected ZuulProperties zuulProperties;
	@Autowired
	private ServiceRouteMapper serviceRouteMapper;
	@Autowired(required = false)
	private Registration registration;
	@Autowired
	private CrmZuulRouteDao crmZuulRouteDao;

	@Bean
	public DiscoveryClientRouteLocator discoveryRouteLocator() {
		return new DynamicRouteLocator(this.server.getServletPrefix(), this.discovery, this.zuulProperties, this.serviceRouteMapper, this.registration, this.crmZuulRouteDao);
	}
}
