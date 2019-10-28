package cn.com.zsyk.crm.manage.mapper;

import cn.com.zsyk.crm.manage.entity.SysDataOption;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysDataOptionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zcs_data_option
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(@Param("tableCode") String tableCode, @Param("fieldCode") String fieldCode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zcs_data_option
     *
     * @mbggenerated
     */
    int insert(SysDataOption record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zcs_data_option
     *
     * @mbggenerated
     */
    SysDataOption selectByPrimaryKey(@Param("tableCode") String tableCode, @Param("fieldCode") String fieldCode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zcs_data_option
     *
     * @mbggenerated
     */
    List<SysDataOption> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zcs_data_option
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SysDataOption record);
}