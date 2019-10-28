package cn.com.zsyk.crm.zuul.routelocator.rest;

import cn.com.zsyk.crm.zuul.model.ServiceResponse;
import cn.com.zsyk.crm.zuul.routelocator.bizz.CrmZuulRouteBizz;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Kyll
 * Date: 2018-08-21 11:47
 */
@Slf4j
@RequestMapping("/route")
@RestController
public class EvoZuulRouteRest {
	@Autowired
	private CrmZuulRouteBizz crmZuulRouteBizz;

	@PostMapping("/refresh")
	public ServiceResponse refresh() {
		crmZuulRouteBizz.refresh();
		return ServiceResponse.ok();
	}
}
