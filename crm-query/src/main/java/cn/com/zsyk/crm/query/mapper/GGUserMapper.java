package cn.com.zsyk.crm.query.mapper;

import cn.com.zsyk.crm.query.entity.GGUser;
import java.util.List;

public interface GGUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GGUSER
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String usercode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GGUSER
     *
     * @mbggenerated
     */
    int insert(GGUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GGUSER
     *
     * @mbggenerated
     */
    GGUser selectByPrimaryKey(String usercode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GGUSER
     *
     * @mbggenerated
     */
    List<GGUser> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GGUSER
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(GGUser record);
}