package cn.com.zsyk.crm.supply.web.controller;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.JsonUtil;
import cn.com.zsyk.crm.supply.bo.SendMsg;
import cn.com.zsyk.crm.supply.entity.SysSmsSendGroup;
import cn.com.zsyk.crm.supply.service.SendSmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SendSmsCtrl {

    @Autowired
   private SendSmsService sendSmsService;

    /**
     * @api {POST} /crm/manage/sendMsg 客群短信发送
     * @apiName sendMsg
     *
     * @apiParam {String} msgJson 消息参数json串（MsgInfo）
     *
     * @apiSuccess {Object} Response 返回值对象
     */
    @RequestMapping(path = "/crm/supply/sendMsg", method = RequestMethod.POST)
    public Result sendMsg(@RequestBody String msgJson) {
        Result res = new Result();

        if (msgJson == null || "".equals(msgJson)) {
            throw new ServiceException("发送消息参数不能为空！");
        }
        List<SysSmsSendGroup> sendGroupList =JsonUtil.parseArray(msgJson.toString(), SysSmsSendGroup.class);
        for(SysSmsSendGroup   sysSmsSendGroup : sendGroupList ) {
            if (sysSmsSendGroup == null) {
                res.setData(false);
            } else {
                sendSmsService.dataCheck(sysSmsSendGroup);
                sendSmsService.sendSms(sysSmsSendGroup);
                res.setData(true);
            }
        }
        return  res;
    }

    /**
     * @api {POST} /crm/manage/sendMsg 客群短信列表查询
     * @apiName sendMsg
     *
     * @apiParam {String} msgJson 消息参数json串（MsgInfo）
     *
     * @apiSuccess {Object} Response 返回值对象
     */
    @RequestMapping(path = "/crm/supply/getSmsSendList", method = RequestMethod.POST)
    public Result getSmsSendList(String groupName,String startDate,String endDate) {
        Result res = new Result();
        Map map = new HashMap();
        map.put("groupName",groupName);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        res.setData(sendSmsService.getSendGroup(map));
        return  res;
    }

    /**
     * @api {POST} /crm/manage/sendMsg 群组短信发送 向manage提供
     * @apiName sendMsg
     *
     * @apiParam {String} msgJson 消息参数json串（MsgInfo）
     *
     * @apiSuccess {Object} Response 返回值对象
     */
    @RequestMapping(path = "/crm/supply/sendMsgManage", method = RequestMethod.POST)
    public Map<String,Object>  sendMsg(String contents,List<String> phoneList) {
        Result res = new Result();

        if (contents == null || "".equals(contents)) {
            throw new ServiceException("发送消息内容不能为空！");
        }
        if (phoneList.size()<0) {
            throw new ServiceException("发送消息手机号不能为空！");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date now=new Date();
        Date startDate=new Date(now .getTime() - 600000);
        Date endDate=new Date(now .getTime() + 600000);
        Map<String,Object> resMap =sendSmsService.smsInvokeManage(phoneList,contents,"",sdf.format(startDate), sdf.format(endDate));
        return  resMap;
    }
    
    /**
     * @api {POST} /crm/manage/sendMsg 群组短信发送 向manage提供
     * @apiName sendMsg
     *
     * @apiParam {String} msgJson 消息参数json串（MsgInfo）
     *
     * @apiSuccess {Object} Response 返回值对象
     */
    @RequestMapping(path = "/crm/supply/schedulerSendMsg", method = RequestMethod.POST)
    public Map<String,Object>  sendMsg(@RequestBody SendMsg sendMsg) {
        Result res = new Result();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date now=new Date();
        Date startDate=new Date(now .getTime() - 600000);
        Date endDate=new Date(now .getTime() + 600000);
        Map<String,Object> resMap =sendSmsService.smsInvokeManageByAuto(sendMsg.getMsgList(),"",sdf.format(startDate), sdf.format(endDate));
        return  resMap;
    }

}
