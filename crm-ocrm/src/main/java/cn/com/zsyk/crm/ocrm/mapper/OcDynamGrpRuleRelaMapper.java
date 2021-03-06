package cn.com.zsyk.crm.ocrm.mapper;

import java.util.List;
import java.util.Map;

import cn.com.zsyk.crm.ocrm.entity.OcDynamGrpRuleRela;

public interface OcDynamGrpRuleRelaMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oc_dynam_grp_rule_rela
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String relaId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oc_dynam_grp_rule_rela
     *
     * @mbggenerated
     */
    int insert(OcDynamGrpRuleRela record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oc_dynam_grp_rule_rela
     *
     * @mbggenerated
     */
    OcDynamGrpRuleRela selectByPrimaryKey(String relaId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oc_dynam_grp_rule_rela
     *
     * @mbggenerated
     */
    List<OcDynamGrpRuleRela> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oc_dynam_grp_rule_rela
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(OcDynamGrpRuleRela record);
    
    /*根据条件查询*/
    List<OcDynamGrpRuleRela> selectRuleRelaByEntity(OcDynamGrpRuleRela dynamGrpRuleRela);
}