package cn.com.zsyk.crm.manage.mqreceiver;


import cn.com.zsyk.crm.common.dto.ContextContainer;
import cn.com.zsyk.crm.common.dto.CoreContext;
import cn.com.zsyk.crm.manage.entity.SysEnterInfo;
import cn.com.zsyk.crm.manage.entity.SysUserInfo;
import cn.com.zsyk.crm.manage.service.mngcenter.deptorg.EnterService;
import cn.com.zsyk.crm.manage.service.mngcenter.user.UserMngService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: message
 * @description: 接收消息，即消费者
 * @author: linbangbo
 * @create: 2019-08-23
 **/
@Slf4j
@Component
public class CompanyReceiver {
    @Autowired
    private EnterService service;
    /**
     * 接收消息并打印
     *
     * @param message message
     */
    @RabbitListener(queues = "companyQueue")
    public void process(String message) {
        // @RabbitListener注解用于监听RabbitMQ，queues指定监听哪个队列
        log.info(message);
        System.out.println(message);
        SysEnterInfo enterInfo;
        enterInfo=JSON.parseObject(message,SysEnterInfo.class);
        SysEnterInfo oldEnterInfo=service.getOneEnter(enterInfo.getEnterCode());
        try{
            CoreContext cc = new CoreContext();
            Map map = new HashMap();
            map.put("userId", "admin");
            cc.putAll(map);
            ContextContainer.setContext(cc);
            if(oldEnterInfo ==null){
                service.addEnter(enterInfo);
            }else{
                oldEnterInfo.setEnterName(enterInfo.getEnterName());
                oldEnterInfo.setSuperEnterCode(enterInfo.getSuperEnterCode());
                oldEnterInfo.setEnterLevel(enterInfo.getEnterLevel());
                oldEnterInfo.setEnterHead(enterInfo.getEnterHead());
                oldEnterInfo.setEnterAddr(enterInfo.getEnterAddr());
                oldEnterInfo.setEnterTel(enterInfo.getEnterTel());
                service.modEnter(oldEnterInfo);
            }
        }catch (Exception e){
            e.printStackTrace();
            //此处应该添加异常处理
        }
    }


}
