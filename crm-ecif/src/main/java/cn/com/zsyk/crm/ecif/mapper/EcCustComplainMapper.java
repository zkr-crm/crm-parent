package cn.com.zsyk.crm.ecif.mapper;

import cn.com.zsyk.crm.ecif.entity.EcCustComplain;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EcCustComplainMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_cust_complain
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(@Param("complainNo") String complainNo, @Param("custNo") String custNo, @Param("caseNo") String caseNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_cust_complain
     *
     * @mbggenerated
     */
    int insert(EcCustComplain record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_cust_complain
     *
     * @mbggenerated
     */
    EcCustComplain selectByPrimaryKey(@Param("complainNo") String complainNo, @Param("custNo") String custNo, @Param("caseNo") String caseNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_cust_complain
     *
     * @mbggenerated
     */
    List<EcCustComplain> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_cust_complain
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(EcCustComplain record);

    /**
     * Desc: 查询客户投诉信息列表
     * @param ecCustComplain
     * @return
     * @author
     */
	List<EcCustComplain> selectCustComplainList(EcCustComplain ecCustComplain);

	/**
	 * Desc: 根据客户逻辑删除信息
	 * @param custNo
	 * @return
	 * @author
	 */
	int deleteByCustNo(String custNo);
}