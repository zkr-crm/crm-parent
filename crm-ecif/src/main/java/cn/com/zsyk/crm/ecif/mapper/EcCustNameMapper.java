package cn.com.zsyk.crm.ecif.mapper;

import cn.com.zsyk.crm.ecif.entity.EcCustName;
import java.util.List;

public interface EcCustNameMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_cust_name
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String custNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_cust_name
     *
     * @mbggenerated
     */
    int insert(EcCustName record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_cust_name
     *
     * @mbggenerated
     */
    EcCustName selectByPrimaryKey(String custNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_cust_name
     *
     * @mbggenerated
     */
    List<EcCustName> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_cust_name
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(EcCustName record);

    /**
     * Desc: 根据客户逻辑删除信息
     * @param ecCustName
     * @return
     * @author
     */
	int deleteByCustNo(String custNo);

	String selectCustNo(String custNo);
    List<EcCustName> selectCustNos(String timeStamp);
}