package cn.com.zsyk.crm.manage.web.controller.mngcenter.message;

import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.manage.entity.SysMsgConf;
import cn.com.zsyk.crm.manage.service.mngcenter.message.MsgConfSelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MsgConfSelfCtrl {

    @Autowired MsgConfSelfService msgConfSelfService;
    @RequestMapping(path = "/crm/manage/msgConf/getMsgConfSelfByEntity", method = RequestMethod.POST)
    public Result getMsgConfSelfByEntity(String msgId) {
        Result res = new Result();
        List<SysMsgConf> list = msgConfSelfService.getMsgConfSelfByEntity(msgId);
        res.setData(list);
        return res;
    }
}
