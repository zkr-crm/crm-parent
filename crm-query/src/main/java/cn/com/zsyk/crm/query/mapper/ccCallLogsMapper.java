package cn.com.zsyk.crm.query.mapper;

import cn.com.zsyk.crm.query.entity.CcCallLogs;
import java.util.List;

public interface ccCallLogsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CC_CALL_LOGS
     *
     * @mbggenerated
     */
    int insert(CcCallLogs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CC_CALL_LOGS
     *
     * @mbggenerated
     */
    List<CcCallLogs> selectAll();
}