package cn.com.zsyk.crm.manage.mapper;

import cn.com.zsyk.crm.manage.entity.SysRuleStrategyCombineRel;
import java.util.List;

public interface SysRuleStrategyCombineRelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_rule_strategy_combine_rel
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_rule_strategy_combine_rel
     *
     * @mbggenerated
     */
    int insert(SysRuleStrategyCombineRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_rule_strategy_combine_rel
     *
     * @mbggenerated
     */
    SysRuleStrategyCombineRel selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_rule_strategy_combine_rel
     *
     * @mbggenerated
     */
    List<SysRuleStrategyCombineRel> selectAll();

    List<SysRuleStrategyCombineRel> selectAllByEntity(SysRuleStrategyCombineRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_rule_strategy_combine_rel
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SysRuleStrategyCombineRel record);
}