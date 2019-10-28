package cn.com.zsyk.crm.query.service.msgmanage;

import cn.com.zsyk.crm.common.dao.AbstractDao;
import cn.com.zsyk.crm.query.entity.ECCustper;
import cn.com.zsyk.crm.query.mapper.EcCustPerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CustomerService {
    @Autowired
    private AbstractDao daoUtil;
    @Autowired
    private EcCustPerMapper ecCustPerMapper;

    public List<ECCustper> getCustomerInfo(Map<String, String> map) {

        List<ECCustper> infoList = daoUtil.selectList("cn.com.zsyk.crm.query.mapper.EcCustPerMapper.getCustomerInfo", map);

        return infoList;
    }


    public List<ECCustper> infoList;

}
