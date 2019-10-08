package cn.com.zsyk.crm.ocrm.mapper;

import cn.com.zsyk.crm.ocrm.entity.OcCustContract;
import java.util.List;

public interface OcCustContractMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oc_cust_contract
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String contractNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oc_cust_contract
     *
     * @mbggenerated
     */
    int insert(OcCustContract record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oc_cust_contract
     *
     * @mbggenerated
     */
    OcCustContract selectByPrimaryKey(String contractNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oc_cust_contract
     *
     * @mbggenerated
     */
    List<OcCustContract> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oc_cust_contract
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(OcCustContract record);
}