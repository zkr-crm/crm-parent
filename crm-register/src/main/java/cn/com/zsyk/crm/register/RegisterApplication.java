package cn.com.zsyk.crm.register;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


/**
 * 注册中心Eureka服务端<br/>
 * 服务提供者在启动后，周期性（默认30秒）向EurekaServer发送心跳，以证明当前服务是可用状态。<br/>
 * EurekaServer在一定的时间（默认90秒）未收到客户端的心跳，则认为服务宕机，注销该实例。<br/>
 * 
 * @author
 */
@EnableEurekaServer
@SpringBootApplication
public class RegisterApplication implements ApplicationRunner{
	private final Log logger = LogFactory.getLog(RegisterApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RegisterApplication.class, args);
	}
	
	/**
	 * springboot启动的回调函数，可以访问系统参数
	 * @see org.springframework.boot.ApplicationRunner#run(org.springframework.boot.ApplicationArguments)
	 */
	@Override
	public void run(ApplicationArguments args) throws Exception {
		logger.info("EurekaServer启动完成......");
	}
}
