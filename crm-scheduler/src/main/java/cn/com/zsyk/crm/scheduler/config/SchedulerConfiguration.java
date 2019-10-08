package cn.com.zsyk.crm.scheduler.config;

import cn.com.zsyk.crm.scheduler.core.CoreJobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.client.RestTemplate;

@Configuration
@Import(CoreJobFactory.class)
public class SchedulerConfiguration {

    @Autowired
    private CoreJobFactory jobFactory;

    @Bean
    public SchedulerFactoryBean schedulerFactory() throws Exception {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setJobFactory(jobFactory);
        bean.setQuartzProperties(PropertiesLoaderUtils.loadAllProperties("quartz.properties"));
        bean.setSchedulerName("crmScheduler");
        return bean;
    }

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(1000);
        requestFactory.setReadTimeout(5 * 60 * 1000);
        return new RestTemplate(requestFactory);
    }

}
