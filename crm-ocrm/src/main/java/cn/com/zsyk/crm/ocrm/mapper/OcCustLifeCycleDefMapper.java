package cn.com.zsyk.crm.ocrm.mapper;

import cn.com.zsyk.crm.ocrm.entity.OcCustLifeCycleDef;
import java.util.List;

public interface OcCustLifeCycleDefMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oc_cust_life_cycle_def
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer stageId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oc_cust_life_cycle_def
     *
     * @mbggenerated
     */
    int insert(OcCustLifeCycleDef record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oc_cust_life_cycle_def
     *
     * @mbggenerated
     */
    OcCustLifeCycleDef selectByPrimaryKey(Integer stageId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oc_cust_life_cycle_def
     *
     * @mbggenerated
     */
    List<OcCustLifeCycleDef> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oc_cust_life_cycle_def
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(OcCustLifeCycleDef record);
}