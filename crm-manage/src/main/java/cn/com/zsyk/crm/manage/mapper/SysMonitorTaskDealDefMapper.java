package cn.com.zsyk.crm.manage.mapper;

import cn.com.zsyk.crm.manage.entity.SysMonitorTaskDealDef;
import java.util.List;

public interface SysMonitorTaskDealDefMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_monitor_task_deal_def
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_monitor_task_deal_def
     *
     * @mbggenerated
     */
    int insert(SysMonitorTaskDealDef record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_monitor_task_deal_def
     *
     * @mbggenerated
     */
    SysMonitorTaskDealDef selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_monitor_task_deal_def
     *
     * @mbggenerated
     */
    List<SysMonitorTaskDealDef> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_monitor_task_deal_def
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SysMonitorTaskDealDef record);
}