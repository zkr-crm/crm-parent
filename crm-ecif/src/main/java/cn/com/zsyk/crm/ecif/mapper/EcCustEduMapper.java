package cn.com.zsyk.crm.ecif.mapper;

import cn.com.zsyk.crm.ecif.entity.EcCustEdu;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EcCustEduMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_cust_edu
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(@Param("custNo") String custNo, @Param("eduTyp") String eduTyp);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_cust_edu
     *
     * @mbggenerated
     */
    int insert(EcCustEdu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_cust_edu
     *
     * @mbggenerated
     */
    EcCustEdu selectByPrimaryKey(@Param("custNo") String custNo, @Param("eduTyp") String eduTyp);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_cust_edu
     *
     * @mbggenerated
     */
    List<EcCustEdu> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_cust_edu
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(EcCustEdu record);

    /**
     * Desc: 查询客户教育列表
     * @param ecCustEdu
     * @return
     * @author
     */
	List<EcCustEdu> selectCustEduList(EcCustEdu ecCustEdu);

	/**
	 * Desc: 获取最高教育信息
	 * @param custNo
	 * @return
	 * @author
	 */
	EcCustEdu selectHighestEduByCustNo(String custNo);

	/**
	 * Desc: 根据客户逻辑删除信息
	 * @param custNo
	 * @return
	 * @author
	 */
	int deleteByCustNo(String custNo);
}