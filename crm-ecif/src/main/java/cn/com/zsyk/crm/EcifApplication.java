package cn.com.zsyk.crm;


import org.apache.commons.logging.Log;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import cn.com.zsyk.crm.common.util.LogUtil;
import tk.mybatis.spring.annotation.MapperScan;

/*
 * 以下三个注解可使用@SpringCloudApplication替换
 * */
@EnableEurekaClient		//开启Eureka注册中心客户端
@EnableCircuitBreaker	//开启断路器功能
@SpringBootApplication
@MapperScan(value = "cn.com.zsyk.crm.ecif.mapper")
public class EcifApplication implements ApplicationRunner{
	private final Log logger = LogUtil.getLogger(EcifApplication.class);

    /**
     * main()方法通过调用SpringApplication类的run()委托(delegates)给SpringBoot。 SpringApplication将引导我们的应用程序，启动Spring，然后启动自动配置的Tomcat Web服务器。 
     * 我们需要将Application.class作为一个参数传递给run方法来告诉SpringApplication，它是主要的Spring组件。 还传递了args数组以传递命令行参数。
     * @param args
     */
    public static void main(String[] args) {
    	System.setProperty("appName","crm-ecif");
        SpringApplication.run(EcifApplication.class, args);
    }


	/**
	 * springboot启动的回调函数，可以访问系统参数
	 * @see org.springframework.boot.ApplicationRunner#run(org.springframework.boot.ApplicationArguments)
	 */
	@Override
	public void run(ApplicationArguments args) throws Exception {
		logger.info("Ecif服务启动完成......");
	}

}