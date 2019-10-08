package cn.com.zsyk.crm.ecif.mapper;

import cn.com.zsyk.crm.ecif.entity.EcFinacePay;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EcFinacePayMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_finace_pay
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(@Param("custNo") String custNo, @Param("policyNo") String policyNo, @Param("payDate") String payDate, @Param("payChnl") String payChnl);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_finace_pay
     *
     * @mbggenerated
     */
    int insert(EcFinacePay record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_finace_pay
     *
     * @mbggenerated
     */
    EcFinacePay selectByPrimaryKey(@Param("custNo") String custNo, @Param("policyNo") String policyNo, @Param("payDate") String payDate, @Param("payChnl") String payChnl);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_finace_pay
     *
     * @mbggenerated
     */
    List<EcFinacePay> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_finace_pay
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(EcFinacePay record);

    /**
     * Desc: 查询财务缴费列表
     * @param ecFinacePay
     * @return
     * @author
     */
	List<EcFinacePay> selectFinacePayList(EcFinacePay ecFinacePay);

	/**
	 * Desc: 根据客户逻辑删除信息
	 * @param custNo
	 * @return
	 * @author
	 */
	int deleteByCustNo(String custNo);
}