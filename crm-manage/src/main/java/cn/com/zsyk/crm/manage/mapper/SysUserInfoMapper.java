package cn.com.zsyk.crm.manage.mapper;

import java.util.List;
import java.util.Map;

import cn.com.zsyk.crm.manage.bom.SysUserRemindInfo;
import cn.com.zsyk.crm.manage.entity.SysUserInfo;

public interface SysUserInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_info
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_info
     *
     * @mbggenerated
     */
    int insert(SysUserInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_info
     *
     * @mbggenerated
     */
    SysUserInfo selectByPrimaryKey(String userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_info
     *
     * @mbggenerated
     */
    List<SysUserInfo> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SysUserInfo record);


    List<SysUserRemindInfo> getUserByEventAndDate(Map map);
    List<SysUserInfo> selectByEntity(Map param);
}