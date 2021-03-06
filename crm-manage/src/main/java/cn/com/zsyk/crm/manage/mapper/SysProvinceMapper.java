package cn.com.zsyk.crm.manage.mapper;

import cn.com.zsyk.crm.manage.entity.SysProvince;
import java.util.List;

public interface SysProvinceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_province
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String provinceCode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_province
     *
     * @mbggenerated
     */
    int insert(SysProvince record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_province
     *
     * @mbggenerated
     */
    SysProvince selectByPrimaryKey(String provinceCode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_province
     *
     * @mbggenerated
     */
    List<SysProvince> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_province
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SysProvince record);

    String selectCode(String provinceName);
}