package cn.com.zsyk.crm.common.config.advisor;

import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.com.zsyk.crm.common.annotation.SysOprtLog;
import cn.com.zsyk.crm.common.dto.ContextContainer;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.entity.SysOperLog;
import cn.com.zsyk.crm.common.mapper.SysOperLogMapper;
import cn.com.zsyk.crm.common.util.DateUtil;
import cn.com.zsyk.crm.common.util.IPUtil;
import cn.com.zsyk.crm.common.util.IdGenerator;
import cn.com.zsyk.crm.common.util.JsonUtil;
import cn.com.zsyk.crm.common.util.LogUtil;

/**
 * 系统操作日志拦截器，拦截cn.com.zsyk.crm.*.web..包下，使用SysOprtLog注解的操作
 * @author
 */
@Aspect
@Order(2)
@Component
public class SysOprtLogAspect {
    private final Log logger = LogUtil.getLogger(SysOprtLogAspect.class);
    @Autowired
    private SysOperLogMapper logMapper;
    
    @Resource(name="txTemplate_manage")
    private TransactionTemplate txTemplate;
    
    /**
     * 代理在cn.com.zsyk.crm.*.web..包下  使用 SysOprtLog的注解<br/>
     * 执行成功记录系统操作日志
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(pointcut="execution(* cn.com.zsyk.crm.*.web..*.*(..)) && @annotation(log)", returning="ret")
    public void afterReturning(JoinPoint joinPoint, SysOprtLog log, Object ret) throws Throwable {
    	if(ret instanceof Result){
    		txTemplate.execute(txStatus -> {
	    		String userId = ContextContainer.getContext().getUserId();
	    		if(null == userId) {
	    			userId = "未登录";
	    		}
	    		String param = "";
	    		Object[] args = joinPoint.getArgs();
	    		param = args.length>0 ? JsonUtil.toJSONString(args) : param;
	    		String ipAddress = IPUtil.getIpAddress(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest());
	    		
	    		SysOperLog sysOperLog = new SysOperLog();
	    		sysOperLog.setSeqNo(IdGenerator.getDistributedID());
	    		sysOperLog.setUserId(userId);
	    		sysOperLog.setInputParam(param);
	    		sysOperLog.setClientIp(ipAddress);
	    		
	    		sysOperLog.setBizDesc(log.bizDesc());
	    		sysOperLog.setModuleName(log.model().desc());
	    		sysOperLog.setOperTime(DateUtil.getCurTime());
	    		sysOperLog.setCreateDate(DateUtil.nowDate());
	    		sysOperLog.setCreateTime(DateUtil.nowTime());
	    		int result = logMapper.insert(sysOperLog);

	    		if(1 == result) {
		    		logger.info("记录系统操作日志："+JsonUtil.toJSONString(sysOperLog));
	    		}
	    		
	    		return result;
    		});
    	}
    }
    
}