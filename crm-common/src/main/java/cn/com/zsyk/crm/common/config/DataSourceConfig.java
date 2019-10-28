package cn.com.zsyk.crm.common.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;


/**
 * 数据源的自定义配置，可参见
 * {@link org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration}
 * @author
 */
@Configuration

public class DataSourceConfig {

	/**
	 * 系统默认数据源，默认tomcat连接池
	 */
	@Primary
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource defaultDataSource() {
        return DataSourceBuilder.create().build();
    }
    
	/**
	 * 系统默认的JdbcTemplate
	 * @param dataSource，自动注入@Primary标记的DataSource
	 */
	@Primary
	@Bean(name = "jdbcTemplate")
    public JdbcTemplate defaultJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
	
	/**
	 * 公共模块使用的crm-manage数据源，默认tomcat连接池
	 */
	@Bean(name = "dataSource_manage")
	@ConfigurationProperties(prefix = "info.datasource.manage")
	public DataSource manageDataSource() {
		return DataSourceBuilder.create().build();
	}

	/**
	 * dataSource_manage对应的JdbcTemplate
	 * @param dataSource 通过@Qualifier注入name对应的Bean
	 * @return
	 */
	@Bean(name = "jdbcTemplate_manage")
	@ConditionalOnBean(name="dataSource_manage")
    public JdbcTemplate manageJdbcTemplate(@Qualifier("dataSource_manage")DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
	
	/*@Bean(name = "JdbcTemplate2")
    public JdbcTemplate secondaryJdbcTemplate() {
        return new JdbcTemplate(manageDataSource());
    }*/
	
	/**
	 *  创建Druid数据源
	 *//*
	@Bean(name="ds1")
	public DataSource dataSource1(Environment env) throws ClassNotFoundException{
        RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "info.datasource.ds1");
        DataSourceBuilder factory = DataSourceBuilder.create()
				.driverClassName(propertyResolver.getProperty("driver-class-name"))
				.type((Class<? extends DataSource>) Class.forName(propertyResolver.getProperty("type")))
				.url(propertyResolver.getProperty("url"))
                .username(propertyResolver.getProperty("username"))
                .password(propertyResolver.getProperty("password"));
        return factory.build();
        
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));//用户名
        dataSource.setPassword(env.getProperty("spring.datasource.password"));//密码
        dataSource.setInitialSize(2);
        dataSource.setMaxActive(20);
        dataSource.setMinIdle(0);
        dataSource.setMaxWait(60000);
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setTestOnBorrow(false);
        dataSource.setTestWhileIdle(true);
        dataSource.setPoolPreparedStatements(false);
        return dataSource;
	}
   */
    
}
