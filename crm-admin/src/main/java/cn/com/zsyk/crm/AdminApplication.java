package cn.com.zsyk.crm;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * User: chuyingfei
 * Date: 2019-07-20 11:30
 */
@EnableAdminServer
@SpringBootApplication
public class AdminApplication {

	public static void main(String[] args) {
		System.setProperty("appName","crm-admin");
		SpringApplication.run(AdminApplication.class, args);
	}
}
