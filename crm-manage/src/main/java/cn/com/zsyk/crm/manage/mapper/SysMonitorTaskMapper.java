package cn.com.zsyk.crm.manage.mapper;

import java.util.List;
import cn.com.zsyk.crm.manage.entity.SysMonitorTask;
import cn.com.zsyk.crm.manage.entity.SysMonitorTask;

public interface SysMonitorTaskMapper {

	int insert(SysMonitorTask record);

	List<SysMonitorTask> selectByExample(SysMonitorTask example);

	int updateByPrimaryKey(SysMonitorTask record);

}