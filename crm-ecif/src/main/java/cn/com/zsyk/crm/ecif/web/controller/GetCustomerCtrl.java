package cn.com.zsyk.crm.ecif.web.controller;

import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.ecif.entity.EcCustPer;
import cn.com.zsyk.crm.ecif.service.GetCustomerService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetCustomerCtrl {

    @Autowired GetCustomerService getCustomerService;
    @RequestMapping(path = "/crm/ecif/getCustomerByBirthday", method = RequestMethod.POST)
    public JSONObject getCustomerByBirthday(@RequestBody String birthDate) {
        Result res = new Result();
        List<EcCustPer> list = getCustomerService.getCustomerByBirthday(birthDate);
        JSONObject json = new JSONObject();
       json.put("list",list);
        return json;
    }
}
