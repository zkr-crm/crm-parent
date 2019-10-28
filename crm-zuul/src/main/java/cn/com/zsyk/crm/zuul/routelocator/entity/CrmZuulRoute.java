package cn.com.zsyk.crm.zuul.routelocator.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;

/**
 * User: Kyll
 * Date: 2018-08-21 09:16
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "ZUUL_ROUTE")
public class CrmZuulRoute extends BaseMapperEntity<Long> {
	private Long id;
	private String routeId;
	private String path;
	private String serviceId;
	private String url;
	private String stripPrefix;
	private String retryable;
	private String sensitiveHeaders;
	private String isEnabled;
	private String commentContent;
}
