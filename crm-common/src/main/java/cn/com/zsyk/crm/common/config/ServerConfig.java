package cn.com.zsyk.crm.common.config;

//import org.apache.catalina.connector.Connector;
//import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
//import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
//import org.springframework.boot.context.embedded.jetty.JettyServerCustomizer;
//import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.core.env.Environment;

/**
 * 嵌入式容器的自定义配置，可参考{@link org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration}
 * @author
 */
//@Configuration
public class ServerConfig {

	/*
	 * Jetty容器的自定义配置
	 * @Bean
	public JettyServerCustomizer jettyServerCustomizer() {
		return server -> {
			// Tweak the connection config used by Jetty to handle incoming HTTP
			// connections
			
			final QueuedThreadPool threadPool = server.getBean(QueuedThreadPool.class);
			threadPool.setMaxThreads(100);
			threadPool.setMinThreads(20);
		};
	}

	@Bean
	public JettyEmbeddedServletContainerFactory jettyEmbeddedServletContainerFactory(
			JettyServerCustomizer jettyServerCustomizer) {
		JettyEmbeddedServletContainerFactory factory = new JettyEmbeddedServletContainerFactory();
		factory.addServerCustomizers(jettyServerCustomizer);
		return factory;
	}*/

	/*
	 * Tomcat容器的自定义配置
	 * @Bean
	public EmbeddedServletContainerFactory servletContainer(Environment env) {
		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
		int port = Integer.valueOf(env.getProperty("server.port", "8011"));
		factory.setPort(port );
		factory.addAdditionalTomcatConnectors(initiateHttpConnector(port));
		return factory;
	}

	private Connector initiateHttpConnector(int port) {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setScheme("http");
		connector.setPort(port - 100);
		connector.setSecure(false);
		connector.setRedirectPort(port);
		return connector;
	}*/
}
