package cn.com.zsyk.crm.manage.web.controller.mngcenter.message;

import cn.com.zsyk.crm.common.dto.ContextContainer;
import cn.com.zsyk.crm.common.dto.CoreContext;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.manage.entity.SysMsgConf;
import cn.com.zsyk.crm.manage.service.mngcenter.message.MsgConfSelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MsgConfSelfCtrl {

    @Autowired MsgConfSelfService msgConfSelfService;
    @RequestMapping(path = "/crm/manage/msgConf/getMsgConfSelfByEntity", method = RequestMethod.POST)
    public Result getMsgConfSelfByEntity(String msgId, String schedulerName, String jobGroup, String jobId) {
        CoreContext cc = new CoreContext();
        Map map = new HashMap();
        map.put("userId", "admin");
        cc.putAll(map);
        ContextContainer.setContext(cc);
        Result res = new Result();
        List<SysMsgConf> list = msgConfSelfService.getMsgConfSelfByEntity(msgId, schedulerName, jobGroup, jobId);
        res.setData(list);
        return res;
    }
}
