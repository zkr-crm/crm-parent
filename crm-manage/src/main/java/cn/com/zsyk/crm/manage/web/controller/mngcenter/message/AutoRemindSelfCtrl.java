package cn.com.zsyk.crm.manage.web.controller.mngcenter.message;

import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.manage.entity.SysAutoRemind;
import cn.com.zsyk.crm.manage.service.mngcenter.message.AutoRemindSelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AutoRemindSelfCtrl {

    @Autowired AutoRemindSelfService autoRemidSelfService;
    @RequestMapping(path = "/crm/manage/msgConf/getAutoRemindSelfByEntity", method = RequestMethod.POST)
    public Result getAutoRemindSelfByEntity(String msgId, String schedulerName, String jobGroup, String jobId) {
        Result res = new Result();
        List<SysAutoRemind> list = autoRemidSelfService.getAutoRemindSelfByEntity(msgId, schedulerName, jobGroup, jobId);
        res.setData(list);
        return res;
    }
}
