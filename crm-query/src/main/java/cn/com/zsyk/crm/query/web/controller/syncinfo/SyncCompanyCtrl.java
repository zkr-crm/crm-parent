package cn.com.zsyk.crm.query.web.controller.syncinfo;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.util.GetDataAuthUtils;
import cn.com.zsyk.crm.common.util.LogUtil;
import cn.com.zsyk.crm.query.service.homePage.HomePageService;
import cn.com.zsyk.crm.query.service.synchinfo.SyncCompanyService;

@RestController
@RequestMapping("/crm/query")
public class SyncCompanyCtrl {

    @Autowired
    private SyncCompanyService service;

    Log log = LogUtil.getLogger(SyncCompanyCtrl.class);

    /**
     * @api {GET} /crm/query/usermng/users 查询所有用户列表
     * @apiDescription
     * @apiName getAllUsers
     * @apiSuccess Result 返回值对象
     */
    @RequestMapping(path = "/syncinfo/company", method = RequestMethod.POST)
    public Result getAllCompany() {
        log = LogUtil.getLogger(SyncCompanyCtrl.class);
        Result res = new Result();
        res.setStatus(service.getAllCompany()+"");
        return res;
    }

}
