package cn.com.zsyk.crm.manage.codeservice;

import cn.com.zsyk.crm.manage.entity.SysCity;
import cn.com.zsyk.crm.manage.entity.SysCounty;
import cn.com.zsyk.crm.manage.mapper.SysCityMapper;
import cn.com.zsyk.crm.manage.mapper.SysCountyMapper;
import cn.com.zsyk.crm.manage.mapper.SysProvinceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CityCodeService {

    @Autowired
    SysProvinceMapper sysProvinceMapper;
    @Autowired
    SysCityMapper sysCityMapper;
    @Autowired
    SysCountyMapper sysCountyMapper;
    public String getProvincesCode(String provinceName) {
        String provinceCode = sysProvinceMapper.selectCode(provinceName);
        if(provinceCode==null){
            provinceCode ="";
        }
        return provinceCode;
    }

    public String getCitysCode(String cityName,String sysPr) {
        SysCity record = new SysCity();
        record.setCityName(cityName);
        record.setProvinceCode(sysPr);
        String cityCode = sysCityMapper.selectCode(record);
        if(cityCode==null){
            cityCode ="";
        }
        return cityCode;
    }
    public String getCountysCode(String countyName,String sysCity) {
        SysCounty record = new SysCounty();
        record.setCountyName(countyName);
        record.setCityCode(sysCity);
        String countyCode = sysCountyMapper.selectCode(record);
        if(countyCode==null){
            countyCode = "";
        }
        return countyCode;
    }

}
