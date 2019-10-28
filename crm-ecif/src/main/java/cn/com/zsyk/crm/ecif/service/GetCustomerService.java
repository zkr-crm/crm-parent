package cn.com.zsyk.crm.ecif.service;

import cn.com.zsyk.crm.common.dao.AbstractDao;
import cn.com.zsyk.crm.ecif.entity.EcCustPer;
import cn.com.zsyk.crm.ecif.mapper.EcCustPerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetCustomerService {
    @Autowired
    private AbstractDao daoUtil;
    @Autowired
    private EcCustPerMapper ecCustPerMapper;
    public List<EcCustPer> getCustomerByBirthday(String birthDate) {

        List<EcCustPer> infoList = daoUtil.selectList("cn.com.zsyk.crm.ecif.mapper.EcCustPerMapper.getCustomerByEntity", birthDate);

        return infoList;
    }

}
