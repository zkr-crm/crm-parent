package cn.com.zsyk.crm.query.mapper;

import cn.com.zsyk.crm.query.entity.CcTickets;
import java.math.BigDecimal;
import java.util.List;

public interface ccTicketsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CC_TICKETS
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(BigDecimal id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CC_TICKETS
     *
     * @mbggenerated
     */
    int insert(CcTickets record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CC_TICKETS
     *
     * @mbggenerated
     */
    CcTickets selectByPrimaryKey(BigDecimal id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CC_TICKETS
     *
     * @mbggenerated
     */
    List<CcTickets> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CC_TICKETS
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(CcTickets record);
}