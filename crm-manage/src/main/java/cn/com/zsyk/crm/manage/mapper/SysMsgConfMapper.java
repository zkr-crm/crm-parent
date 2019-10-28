package cn.com.zsyk.crm.manage.mapper;

import cn.com.zsyk.crm.manage.entity.SysMsgConf;
import java.util.List;

public interface SysMsgConfMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_MSG_CONF
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String msgId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_MSG_CONF
     *
     * @mbggenerated
     */
    int insert(SysMsgConf record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_MSG_CONF
     *
     * @mbggenerated
     */
    SysMsgConf selectByPrimaryKey(String msgId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_MSG_CONF
     *
     * @mbggenerated
     */
    List<SysMsgConf> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_MSG_CONF
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SysMsgConf record);
    /**
     * 短信定时发送
     */
    SysMsgConf getMsgConfSelfByEntity(String msgId);

    List<SysMsgConf> getMsgConfByEntity(SysMsgConf msgInfo);
}