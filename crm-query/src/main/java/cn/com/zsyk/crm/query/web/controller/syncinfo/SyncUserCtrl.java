package cn.com.zsyk.crm.query.web.controller.syncinfo;

import cn.com.zsyk.crm.query.service.synchinfo.SyncUserService;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.util.LogUtil;

@RestController
@RequestMapping("/crm/query")
public class SyncUserCtrl {

    @Autowired
    private SyncUserService service;
    Log log = LogUtil.getLogger(SyncUserCtrl.class);

    /**
     * @api {GET} /crm/query/usermng/users 查询所有用户列表
     * @apiDescription
     * @apiName getAllUsers
     * @apiSuccess Result 返回值对象
     */
    @RequestMapping(path = "/syncinfo/users", method = RequestMethod.POST)
    public Result getAllUsers() {
        log = LogUtil.getLogger(SyncUserCtrl.class);
        Result res = new Result();
        res.setStatus(service.getAllUser()+"");
        return res;
    }

}
