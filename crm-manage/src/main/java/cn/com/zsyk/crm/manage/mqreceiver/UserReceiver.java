package cn.com.zsyk.crm.manage.mqreceiver;


import cn.com.zsyk.crm.common.dto.ContextContainer;
import cn.com.zsyk.crm.common.dto.CoreContext;
import cn.com.zsyk.crm.manage.entity.SysUserInfo;
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
public class UserReceiver {
    @Autowired
    private UserMngService service;
    /**
     * 接收消息并打印
     *
     * @param message message
     */
    @RabbitListener(queues = "userQueue")
    public void process(String message) {
        // @RabbitListener注解用于监听RabbitMQ，queues指定监听哪个队列
        log.info(message);
        System.out.println(message);
        SysUserInfo userInfo;
        userInfo=JSON.parseObject(message,SysUserInfo.class);
        SysUserInfo oldUserInfo=service.getOneUser(userInfo.getUserId());
        try{
            CoreContext cc = new CoreContext();
            Map map = new HashMap();
            map.put("userId", "admin");
            cc.putAll(map);
            ContextContainer.setContext(cc);
            if(oldUserInfo ==null){
                service.addUser(userInfo);
            }else{
                oldUserInfo.setPassword(userInfo.getPassword());
                oldUserInfo.setUserName(userInfo.getUserName());
                oldUserInfo.setEnterCode(userInfo.getEnterCode());
                oldUserInfo.setDeptCode(userInfo.getDeptCode());
                oldUserInfo.setEmail(userInfo.getEmail());
                oldUserInfo.setTelphone(userInfo.getTelphone());
                oldUserInfo.setUpdateUser(userInfo.getUpdateUser());
                System.out.println(service.modUser(oldUserInfo));
            }
        }catch (Exception e){
            //此处应该添加异常处理
        }


    }


}
