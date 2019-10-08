package cn.com.zsyk.crm.register;

import com.netflix.appinfo.InstanceInfo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRenewedEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaRegistryAvailableEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaServerStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Eureka事件监听
 */
@Component
public class EurekaEventListener {
	private static final Log logger = LogFactory.getLog(EurekaEventListener.class);
   
	@EventListener
    public void listen(EurekaServerStartedEvent event) {
    	logger.info("EurekaServer启动事件...");
    }

    @EventListener
    public void listen(EurekaRegistryAvailableEvent event) {
    	logger.info("Eureka注册中心启动事件...");
    }

	@EventListener
    public void listen(EurekaInstanceRegisteredEvent event) {
		InstanceInfo instanceInfo = event.getInstanceInfo();
		logger.info(String.format("服务注册事件：%s-%s", instanceInfo.getAppName(), instanceInfo.getInstanceId()));
    }

	@EventListener
    public void listen(EurekaInstanceCanceledEvent event) {
		logger.info(String.format("服务下线事件：%s-%s", event.getAppName(), event.getServerId()));
    }

    @EventListener
    public void listen(EurekaInstanceRenewedEvent event) {
		logger.info(String.format("服务续约事件：%s-%s", event.getAppName(), event.getServerId()));
    }


}