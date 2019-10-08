package cn.com.zsyk.crm.manage.web.controller.codectr;

import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.manage.codeservice.CityCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityCodeCtr {
    @Autowired
    private CityCodeService nameCodeService;
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(path = "/crm/manage/nameCode/getprovinceCode", method = RequestMethod.GET)
    public Result getProvincesCode(@RequestParam("provinceName")String provinceName) {
        Result s = new Result();
        s.setData(nameCodeService.getProvincesCode(provinceName));
        return s;
    }
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(path = "/crm/manage/nameCode/getCountysCode", method = RequestMethod.GET)
    public Result getCountysCode(@RequestParam("countyName")String countyName,@RequestParam("sysCity")String sysCity) {
        Result s = new Result();
        s.setData(nameCodeService.getCountysCode(countyName,sysCity));
        return s;
    }
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(path = "/crm/manage/nameCode/getCitysCode", method = RequestMethod.GET)
    public Result getCitysCode(@RequestParam("cityName")String cityName) {
        Result s = new Result();
        s.setData(nameCodeService.getCitysCode(cityName));
        return s;
    }
}
