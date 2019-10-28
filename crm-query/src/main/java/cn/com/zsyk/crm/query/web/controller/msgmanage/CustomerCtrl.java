package cn.com.zsyk.crm.query.web.controller.msgmanage;

import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.query.entity.ECCustper;
import cn.com.zsyk.crm.query.service.msgmanage.CustomerService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CustomerCtrl {

    @Autowired
    CustomerService customerService;
    @RequestMapping(path = "/crm/query/getCustomerInfo", method = RequestMethod.POST)
    public JSONObject getCustomerInfo(@RequestBody Map<String, String> map) {
        Result res = new Result();
        List<ECCustper> list = customerService.getCustomerInfo(map);
        JSONObject json = new JSONObject();
        json.put("list",list);
        return json;
    }
}
