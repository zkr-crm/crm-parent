package cn.com.zsyk.crm.ecif.mapper;

import cn.com.zsyk.crm.ecif.entity.EcBlackList;
import java.util.List;

public interface EcBlackListMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_black_list
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String custNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_black_list
     *
     * @mbggenerated
     */
    int insert(EcBlackList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_black_list
     *
     * @mbggenerated
     */
    EcBlackList selectByPrimaryKey(String custNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_black_list
     *
     * @mbggenerated
     */
    List<EcBlackList> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_black_list
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(EcBlackList record);
    
    int updateByPrimaryKeyforcancel(EcBlackList record);
    
}