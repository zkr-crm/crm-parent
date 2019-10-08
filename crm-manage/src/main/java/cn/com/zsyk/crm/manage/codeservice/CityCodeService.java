package cn.com.zsyk.crm.manage.codeservice;

import cn.com.zsyk.crm.manage.mapper.SysCityMapper;
import cn.com.zsyk.crm.manage.mapper.SysCountyMapper;
import cn.com.zsyk.crm.manage.mapper.SysProvinceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

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

    public String getCitysCode(String cityName) {
        String cityCode = sysCityMapper.selectCode(cityName);
        if(cityCode==null){
            cityCode ="";
        }
        return cityCode;
    }
    public String getCountysCode(String countyName,String sysCity) {
        Map maps = new HashMap();
        maps.put("countyName",countyName);
        maps.put("cityCode",sysCity);
        String countyCode = sysCountyMapper.selectCode(maps);
        if(countyCode==null){
            countyCode = "";
        }
        return countyCode;
    }

}
