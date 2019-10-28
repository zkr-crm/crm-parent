package cn.com.zsyk.crm.ecif.mapper;

import cn.com.zsyk.crm.ecif.entity.EcProduct;
import java.util.List;

public interface EcProductMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_product
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String productCd);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_product
     *
     * @mbggenerated
     */
    int insert(EcProduct record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_product
     *
     * @mbggenerated
     */
    EcProduct selectByPrimaryKey(String productCd);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_product
     *
     * @mbggenerated
     */
    List<EcProduct> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_product
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(EcProduct record);
}