package cn.com.zsyk.crm.ecif.mapper;

import cn.com.zsyk.crm.ecif.entity.EcCustRelation;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface EcCustRelationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_cust_relation
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(@Param("custNo") String custNo, @Param("relationTyp") String relationTyp, @Param("refCustNo") String refCustNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_cust_relation
     *
     * @mbggenerated
     */
    int insert(EcCustRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_cust_relation
     *
     * @mbggenerated
     */
    EcCustRelation selectByPrimaryKey(@Param("custNo") String custNo, @Param("relationTyp") String relationTyp, @Param("refCustNo") String refCustNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_cust_relation
     *
     * @mbggenerated
     */
    List<EcCustRelation> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_cust_relation
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(EcCustRelation record);

    /**
     * Desc: 查询客户关系列表
     * @param ecCustRelation
     * @return
     * @author
     */
	List<EcCustRelation> selectCustRelList(EcCustRelation ecCustRelation);
    List<EcCustRelation> selectCustRelList(Map map);
	/**
	 * Desc: 根据客户逻辑删除信息
	 * @param custNo
	 * @return
	 * @author
	 */
	int deleteByCustNo(String custNo);
	
	EcCustRelation selectReverseCustRelation(EcCustRelation ecCustRelation);
}