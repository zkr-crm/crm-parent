package cn.com.zsyk.crm.manage.mapper;

import cn.com.zsyk.crm.manage.entity.SysEnterInfo;
import java.util.List;
import java.util.Map;

public interface SysEnterInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_enter_info
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String enterCode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_enter_info
     *
     * @mbggenerated
     */
    int insert(SysEnterInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_enter_info
     *
     * @mbggenerated
     */
    SysEnterInfo selectByPrimaryKey(String enterCode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_enter_info
     *
     * @mbggenerated
     */
    List<SysEnterInfo> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_enter_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SysEnterInfo record);

    List<SysEnterInfo> selectByEntity(Map param);
}