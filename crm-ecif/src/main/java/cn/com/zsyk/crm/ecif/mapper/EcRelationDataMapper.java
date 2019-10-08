package cn.com.zsyk.crm.ecif.mapper;

import cn.com.zsyk.crm.ecif.entity.EcRelationData;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EcRelationDataMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_relation_data
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(@Param("forwardRelation") String forwardRelation, @Param("sex") String sex);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_relation_data
     *
     * @mbggenerated
     */
    int insert(EcRelationData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_relation_data
     *
     * @mbggenerated
     */
    EcRelationData selectByPrimaryKey(@Param("forwardRelation") String forwardRelation, @Param("sex") String sex);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_relation_data
     *
     * @mbggenerated
     */
    List<EcRelationData> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_relation_data
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(EcRelationData record);
}