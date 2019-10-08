package cn.com.zsyk.crm.manage.mapper;

import cn.com.zsyk.crm.manage.entity.SysRuleReferParams;
import java.util.List;

public interface SysRuleReferParamsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_rule_refer_params
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String referName);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_rule_refer_params
     *
     * @mbggenerated
     */
    int insert(SysRuleReferParams record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_rule_refer_params
     *
     * @mbggenerated
     */
    SysRuleReferParams selectByPrimaryKey(String referName);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_rule_refer_params
     *
     * @mbggenerated
     */
    List<SysRuleReferParams> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_rule_refer_params
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SysRuleReferParams record);
}