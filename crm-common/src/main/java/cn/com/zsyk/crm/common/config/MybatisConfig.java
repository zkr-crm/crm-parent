package cn.com.zsyk.crm.common.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import cn.com.zsyk.crm.common.config.mybatis.interceptor.ExecutorInterceptor;
import cn.com.zsyk.crm.common.config.mybatis.interceptor.PubColumnsInterceptor;
import cn.com.zsyk.crm.common.config.mybatis.typehandler.DateTypeHandler;
import cn.com.zsyk.crm.common.config.mybatis.typehandler.EnumTypeHandler;

/**
 * Mybatis的自定义配置，公共模块的Mapper接口扫描，具体的业务模块在方法中配置.
 * 
 * {@link org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration}
 * 
 * @author
 */
@org.springframework.context.annotation.Configuration
@EnableConfigurationProperties(MybatisProperties.class)
@MapperScan(basePackages="cn.com.zsyk.crm.common.mapper", sqlSessionTemplateRef="sqlSessionTemplate_manage")
public class MybatisConfig {
	/**
	 * 性能、日志拦截器（最内层拦截器）
	 */
	private ExecutorInterceptor executorInterceptor = new ExecutorInterceptor();
	/**
	 * 公共字段拦截器插件
	 */
	private PubColumnsInterceptor pubColumnsInterceptor = new PubColumnsInterceptor();
	/**
	 * 默认的sqlSessionFactory
	 */
	
	@Primary
	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource, MybatisProperties properties) throws Exception {
		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		factory.setDataSource(dataSource);
		factory.setVfs(SpringBootVFS.class);
		if (properties.getConfigurationProperties() != null) {
			factory.setConfigurationProperties(properties.getConfigurationProperties());
		}
		if (StringUtils.hasLength(properties.getTypeAliasesPackage())) {
			factory.setTypeAliasesPackage(properties.getTypeAliasesPackage());
		}
		if (StringUtils.hasLength(properties.getTypeHandlersPackage())) {
			factory.setTypeHandlersPackage(properties.getTypeHandlersPackage());
		}
		if (!ObjectUtils.isEmpty(properties.resolveMapperLocations())) {
			factory.setMapperLocations(properties.resolveMapperLocations());
		}
		
		//创建Configuration
		Configuration config = properties.getConfiguration();
		if (config == null) {
			config = new Configuration();
		}
		
		config.setCallSettersOnNulls(true);
		
		factory.setConfiguration(config);
		
		// 设置枚举类型处理器
		config.setDefaultEnumTypeHandler(EnumTypeHandler.class);
		// 设置日期类型处理器
		config.getTypeHandlerRegistry().register(DateTypeHandler.class);

		// 添加性能、日志拦截器插件（最内层拦截器）
		config.addInterceptor(executorInterceptor);
		// 添加公共字段拦截器插件
		config.addInterceptor(pubColumnsInterceptor);
		
		
		return factory.getObject();
	}

	/**
	 * 默认的sqlSessionTemplate
	 */
	@Primary
	@Bean(name = "sqlSessionTemplate")
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory, MybatisProperties properties) {
		ExecutorType executorType = properties.getExecutorType();
		if (executorType != null) {
			return new SqlSessionTemplate(sqlSessionFactory, executorType);
		} else {
			return new SqlSessionTemplate(sqlSessionFactory);
		}
	}
	
	/**
	 * 默认的mapperscan
	 */
	@Primary
	@Bean(name = "mapperScannerConfigurer")
	public MapperScannerConfigurer mapperScanner(Environment env) {
		MapperScannerConfigurer mapperScanner = new MapperScannerConfigurer();
		mapperScanner.setBasePackage(env.getProperty("mybatis.mapper-packages", "cn.com.zsyk.crm.*.mapper"));
		mapperScanner.setSqlSessionTemplateBeanName("sqlSessionTemplate");
		return mapperScanner;
	}
	
	
	/**
	 * crm_manage的sqlSessionFactory
	 */
	@Bean(name = "sqlSessionFactory_mamage")
	@ConditionalOnBean(name = "dataSource_manage")
	public SqlSessionFactory sqlSessionFactory1(@Qualifier("dataSource_manage") DataSource dataSource, MybatisProperties properties) throws Exception {
		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		factory.setDataSource(dataSource);
		factory.setVfs(SpringBootVFS.class);
		if (properties.getConfigurationProperties() != null) {
			factory.setConfigurationProperties(properties.getConfigurationProperties());
		}

		Configuration configuration = properties.getConfiguration();
		if (configuration == null) {
			configuration = new Configuration();
		}
		factory.setConfiguration(configuration);
		
		// 设置枚举类型处理器
		configuration.setDefaultEnumTypeHandler(EnumTypeHandler.class);
		// 设置日期类型处理器
		configuration.getTypeHandlerRegistry().register(DateTypeHandler.class);
		// 添加性能、日志拦截器（最内层拦截器）
		configuration.addInterceptor(executorInterceptor);
		
		//设置类型别名目录
		factory.setTypeAliasesPackage("cn.com.zsyk.crm.common.entity");
		//设置xml文件目录
		factory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/common/*.xml"));
		
		return factory.getObject();
	}

	/**
	 * crm_manage的SqlSessionTemplate
	 */
	@Bean(name = "sqlSessionTemplate_manage")
	@ConditionalOnBean(name = "sqlSessionFactory_mamage")
	public SqlSessionTemplate sqlSessionTemplate1(@Qualifier("sqlSessionFactory_mamage")SqlSessionFactory sqlSessionFactory, MybatisProperties properties) {
		ExecutorType executorType = properties.getExecutorType();
		if (executorType != null) {
			return new SqlSessionTemplate(sqlSessionFactory, executorType);
		} else {
			return new SqlSessionTemplate(sqlSessionFactory);
		}
	}
}
