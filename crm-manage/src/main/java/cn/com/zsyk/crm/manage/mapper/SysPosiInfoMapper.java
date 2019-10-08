package cn.com.zsyk.crm.manage.mapper;

import cn.com.zsyk.crm.manage.entity.SysPosiInfo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysPosiInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zcs_posi_info
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(@Param("posiCode") String posiCode, @Param("posiName") String posiName);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zcs_posi_info
     *
     * @mbggenerated
     */
    int insert(SysPosiInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zcs_posi_info
     *
     * @mbggenerated
     */
    SysPosiInfo selectByPrimaryKey(@Param("posiCode") String posiCode, @Param("posiName") String posiName);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zcs_posi_info
     *
     * @mbggenerated
     */
    List<SysPosiInfo> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zcs_posi_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SysPosiInfo record);
}