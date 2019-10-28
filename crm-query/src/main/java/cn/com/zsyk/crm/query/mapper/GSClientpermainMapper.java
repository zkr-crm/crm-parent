package cn.com.zsyk.crm.query.mapper;

import cn.com.zsyk.crm.query.entity.GSClientpermain;

import java.util.List;

public interface GSClientpermainMapper {
    List<GSClientpermain> selectCustlist(String stampTime);
    long selectAllCustCount(String stampTime);
    long selectReportormobileCount(String stampTime);
    List<GSClientpermain> selectReportormobile(String stampTime);
}
