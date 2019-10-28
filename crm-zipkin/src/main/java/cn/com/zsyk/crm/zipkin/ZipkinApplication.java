package cn.com.zsyk.crm.zipkin;

import zipkin.server.EnableZipkinServer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 服务追踪框架Zipkin的服务端
 * 
 * @author
 */
@EnableZipkinServer
@SpringBootApplication
public class ZipkinApplication implements ApplicationRunner{
	private final Log logger = LogFactory.getLog(ZipkinApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(ZipkinApplication.class, args);
	}
	
	/**
	 * springboot启动的回调函数，可以访问系统参数
	 * @see org.springframework.boot.ApplicationRunner#run(org.springframework.boot.ApplicationArguments)
	 */
	@Override
	public void run(ApplicationArguments args) throws Exception {
		logger.info("ZipkinServer服务启动完成......");
	}
}