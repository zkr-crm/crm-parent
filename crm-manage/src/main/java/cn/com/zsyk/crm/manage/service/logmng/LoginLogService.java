package cn.com.zsyk.crm.manage.service.logmng;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.com.zsyk.crm.common.dao.AbstractDao;
import cn.com.zsyk.crm.common.util.IPUtil;
import cn.com.zsyk.crm.common.util.IdGenerator;
import cn.com.zsyk.crm.manage.entity.SysLoginLog;
import cn.com.zsyk.crm.manage.mapper.SysLoginLogMapper;

@Service
@Transactional
public class LoginLogService {
	@Autowired
	private AbstractDao daoUtil;
	@Autowired
	private SysLoginLogMapper loginLogMapper;
	@Autowired
	private ObjectMapper om;
	/**
	 * 新增登陆日志方法
	 * 
	 * @param loginLog
	 *            登录日志{用户ID、日志类型、日志内容}
	 * @return int
	 */
	public int addLoginLog(SysLoginLog loginLog) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
		// 获取IP
        loginLog.setClientIp(IPUtil.getIpAddress(request));
        // 日志时间 - 当前时间
        Date now = new Date();
        loginLog.setLogTime(now);
        // 记录状态
		loginLog.setRecStat("0");
		// 主键-日志序号自增
		loginLog.setSeqNo(IdGenerator.getDistributedID()+"");
		// loginLog.setCreateUser(loginLog.getUserId());
		int log = loginLogMapper.insert(loginLog);
		return log;
	}
	
	/**
	 * 查询登陆日志方法
	 * 
	 * @param loginLog
	 *            登录日志{用户ID、日志时间}
	 * @return List<SysLoginLog>
	 */
	public List<SysLoginLog> getLoginLog(String userId, String startDate, String endDate) {
		Map param = new HashMap();
		param.put("userId", userId);
		param.put("startDate", startDate);
		param.put("endDate", endDate);
        List<SysLoginLog> logList = daoUtil.selectList("cn.com.zsyk.crm.manage.mapper.SysLoginLogMapper.selectByEntity", param);
        return logList;
	}

}
