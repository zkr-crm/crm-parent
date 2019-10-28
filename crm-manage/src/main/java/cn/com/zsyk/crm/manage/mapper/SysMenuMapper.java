package cn.com.zsyk.crm.manage.mapper;

import cn.com.zsyk.crm.manage.entity.SysMenu;
import cn.com.zsyk.crm.manage.entity.SysMenuInfo;

import java.util.List;

public interface SysMenuMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String menuId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbggenerated
     */
    int insert(SysMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbggenerated
     */
    SysMenu selectByPrimaryKey(String menuId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbggenerated
     */
    List<SysMenu> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SysMenu record);
    
    int deleteAll();
    
    List<SysMenu> getMenuInfoByMenuName(String menuName);

    List<SysMenu> selectAllWithOrder();
}