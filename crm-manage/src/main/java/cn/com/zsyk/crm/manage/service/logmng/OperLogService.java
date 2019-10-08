package cn.com.zsyk.crm.manage.service.logmng;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.zsyk.crm.common.entity.SysOperLog;
import cn.com.zsyk.crm.common.mapper.SysOperLogMapper;
@Service
@Transactional
public class OperLogService {
	@Autowired
	private SysOperLogMapper  sysOperLogMapper;
	/**
	 * 查询操作日志方法
	 * 
	 * @param operLog
	 *            操作日志{用户ID、日志时间}
	 * @return List<SysOperLog>
	 */
	public List<SysOperLog> getOperLog(String userId, String startDate, String endDate) {
		Map param = new HashMap();
		param.put("userId", userId);
		param.put("startDate", startDate);
		param.put("endDate", endDate);
        List<SysOperLog> logList = sysOperLogMapper.selectByEntity(param);
        return logList;
	}
}
