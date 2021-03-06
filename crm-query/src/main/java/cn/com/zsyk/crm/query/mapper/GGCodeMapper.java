package cn.com.zsyk.crm.query.mapper;

import cn.com.zsyk.crm.query.entity.GGCode;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GGCodeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GGCODE
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(@Param("codetype") String codetype, @Param("companycode") String companycode, @Param("codecode") String codecode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GGCODE
     *
     * @mbggenerated
     */
    int insert(GGCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GGCODE
     *
     * @mbggenerated
     */
    GGCode selectByPrimaryKey(@Param("codetype") String codetype, @Param("companycode") String companycode, @Param("codecode") String codecode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GGCODE
     *
     * @mbggenerated
     */
    List<GGCode> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GGCODE
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(GGCode record);
}