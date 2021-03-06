package cn.com.zsyk.crm.ecif.mapper;

import cn.com.zsyk.crm.ecif.entity.EcCustTag;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EcCustTagMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_cust_tag
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(@Param("custNo") String custNo, @Param("custTagCd") String custTagCd);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_cust_tag
     *
     * @mbggenerated
     */
    int insert(EcCustTag record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_cust_tag
     *
     * @mbggenerated
     */
    EcCustTag selectByPrimaryKey(@Param("custNo") String custNo, @Param("custTagCd") String custTagCd);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_cust_tag
     *
     * @mbggenerated
     */
    List<EcCustTag> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_cust_tag
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(EcCustTag record);

    /**
     * Desc: 根据客户号查询客户标签
     * @param custNo
     * @return
     * @author
     */
	List<EcCustTag> selectByCustNo(String custNo);

	/**
	 * Desc: 根据客户号删除客户标签
	 * @param custNo
	 * @return
	 * @author
	 */
	int deleteByCustNo(String custNo);
	/**
	 * Desc: 根据autoFlg删除客户标签
	 * @param autoFlg
	 * @return
	 * @author
	 */
	int deleteByAutoFlg(String autoFlg);
	/**
	 * Desc: 根据custNo, autoFlg删除客户标签
	 * @param autoFlg
	 * @return
	 * @author
	 */
	int deleteByCustNoAutoFlg(EcCustTag record);
}