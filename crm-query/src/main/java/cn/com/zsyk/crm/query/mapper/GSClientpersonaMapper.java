package cn.com.zsyk.crm.query.mapper;

import cn.com.zsyk.crm.query.entity.GSClientpermain;
import cn.com.zsyk.crm.query.entity.GSClientpersona;
import java.util.List;
import java.util.Map;

public interface GSClientpersonaMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GSCLIENTPERSONA
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String clientcode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GSCLIENTPERSONA
     *
     * @mbggenerated
     */
    int insert(GSClientpersona record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GSCLIENTPERSONA
     *
     * @mbggenerated
     */
    GSClientpersona selectByPrimaryKey(String clientcode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GSCLIENTPERSONA
     *
     * @mbggenerated
     */
    List<GSClientpersona> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GSCLIENTPERSONA
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(GSClientpersona record);

}