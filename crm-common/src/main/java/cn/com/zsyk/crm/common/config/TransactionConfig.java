package cn.com.zsyk.crm.common.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 事务的自定义配置，可参见{@link org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration}<br/>
 * 
 * @EnableTransactionManagement：开启注解事务管理,TransactionAutoConfiguration中默认已开启
 * 
 * 在存在多个事务管理器的情况下，可使用value具体指定使用哪个事务管理器
 * @Transactional(value="txManager") 
 * 如果不指定则默认使用方法annotationDrivenTransactionManager()返回的事务管理器
 * @Transactional
 *
 * @author
 */
@Configuration
public class TransactionConfig implements TransactionManagementConfigurer {
	@Autowired
	private PlatformTransactionManager txManager;

	/**
	 * 系统默认的事务管理器
	 */
	@Primary
	@Bean(name = "txManager")
	public PlatformTransactionManager txManager1(DataSource dataSource,
			ObjectProvider<TransactionManagerCustomizers> txManagerCustomizers) {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
		if (txManagerCustomizers != null) {
			((TransactionManagerCustomizers) txManagerCustomizers.getIfAvailable()).customize(transactionManager);
		}
		return transactionManager;
	}

	/**
	 * 系统默认的TransactionTemplate
	 */
	@Primary
	@Bean(name = "txTemplate")
	public TransactionTemplate txTemplate1() {
		return new TransactionTemplate(this.txManager);
	}
	
	/**
	 * crm-manage的事务管理器
	 */
	@Bean(name = "txManager_manage")
	@ConditionalOnBean(name="dataSource_manage")
	public PlatformTransactionManager txManage2(@Qualifier("dataSource_manage") DataSource dataSource,
			ObjectProvider<TransactionManagerCustomizers> txManagerCustomizers) {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
		if (txManagerCustomizers != null) {
			((TransactionManagerCustomizers) txManagerCustomizers.getIfAvailable()).customize(transactionManager);
		}
		return transactionManager;
	}

	/**
	 * crm-manage的TransactionTemplate
	 */
	@Bean(name = "txTemplate_manage")
	@ConditionalOnBean(name="txManager_manage")
	public TransactionTemplate txTemplate2(@Qualifier("txManager_manage") PlatformTransactionManager txManager_manage) {
		return new TransactionTemplate(txManager_manage);
	}
	
	/**
	 * 在拥有多个事务管理器的情况下默认使用该方法返回的事务管理器
	 */
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return txManager;
	}
}
