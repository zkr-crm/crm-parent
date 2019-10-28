package cn.com.zsyk.crm.scheduler.core;

import cn.com.zsyk.crm.scheduler.Constants;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CoreJob extends QuartzJobBean {
    private Logger logger = LogManager.getLogger(CoreJob.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${scheduler.target.ecif.ip}")
    private String ecifIp;

    @Value("${scheduler.target.ecif.port}")
    private String ecifPort;

    @Value("${scheduler.target.manage.ip}")
    private String manageIp;

    @Value("${scheduler.target.manage.port}")
    private String managePort;
    
    @Value("${scheduler.target.ocrm.ip}")
    private String ocrmIp;

    @Value("${scheduler.target.ocrm.port}")
    private String ocrmPort;

    @Value("${scheduler.target.query.ip}")
    private String queryIp;

    @Value("${scheduler.target.query.port}")
    private String queryPort;

    private String targetUrl;

    private String params;

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public void setParams(String params) {
        this.params = params;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        try {
            StringBuffer fullUrl = new StringBuffer();
            switch (context.getJobDetail().getKey().getGroup()) {
                case "ecif":
                    fullUrl.append("http://").append(ecifIp).append(":").append(ecifPort).append(targetUrl);
                    break;
                case "manage":
                    fullUrl.append("http://").append(manageIp).append(":").append(managePort).append(targetUrl);
                    break;
                case "ocrm":
                    fullUrl.append("http://").append(ocrmIp).append(":").append(ocrmPort).append(targetUrl);
                    break;
                case "query":
                    fullUrl.append("http://").append(queryIp).append(":").append(queryPort).append(targetUrl);
                    break;
                default:
                    throw new RuntimeException("不支持当前group");
            }


            long start = System.currentTimeMillis();
            logger.info("【{}】于【{}】开始执行...", context.getJobDetail().getKey().getName(), context.getFireTime());
            logger.info("作业描述【{}】，目标url【{}】", context.getJobDetail().getDescription(), fullUrl);


            HttpHeaders headers = new HttpHeaders();
//            headers.add("X-Auth-Token", "123456789");
            MultiValueMap body = new LinkedMultiValueMap();
            Map bodyMap = null;
            if(StringUtils.isNotBlank(this.params)){
                 bodyMap = JSON.parseObject(this.params, Map.class);
            }else{
                bodyMap = new HashMap();
            }

            bodyMap.put(Constants.JOB_ID,context.getJobDetail().getKey().getName());
            bodyMap.put(Constants.JOB_GROUP,context.getJobDetail().getKey().getGroup());
            bodyMap.put("schedulerName",context.getScheduler().getSchedulerName());
            
            body.setAll(bodyMap);


            String response = restTemplate.postForObject(fullUrl.toString(), new HttpEntity(body, headers), String.class);
            Map responseMap = JSON.parseObject(response, Map.class);


//            if (!responseMap.get("status").equals("1")) {
//                throw new JobExecutionException("");
//            }
            logger.info("【{}】于【{}】执行成功，用时【{}ms】，下次执行时间【{}】", context.getJobDetail().getKey().getName(), new Date(), System.currentTimeMillis() - start, context.getTrigger().getNextFireTime());
            logger.info("请求响应【{}】", response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
