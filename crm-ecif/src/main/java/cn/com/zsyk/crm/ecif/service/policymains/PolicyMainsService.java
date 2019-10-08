package cn.com.zsyk.crm.ecif.service.policymains;

import cn.com.zsyk.crm.common.dto.ContextContainer;
import cn.com.zsyk.crm.common.dto.CoreContext;
import cn.com.zsyk.crm.common.util.LogUtil;
import cn.com.zsyk.crm.ecif.entity.EcCustManager;
import cn.com.zsyk.crm.ecif.entity.EcCustRelation;
import cn.com.zsyk.crm.ecif.entity.Manager;
import cn.com.zsyk.crm.ecif.mapper.EcCustManagerMapper;
import cn.com.zsyk.crm.ecif.mapper.EcCustPerMapper;
import cn.com.zsyk.crm.ecif.mapper.EcCustRelationMapper;
import cn.com.zsyk.crm.ecif.service.customer.relationship.CustRelService;
import cn.com.zsyk.crm.generator.EnumType;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Component
public class PolicyMainsService {
    /** logger */
    @SuppressWarnings("unused")
    private final Log log = LogUtil.getLogger(PolicyMainsService.class);
    @Autowired
    private CustRelService custRelService;
    @Autowired
    private EcCustManagerMapper ecCustManagerMapper;
    @Autowired
    private EcCustRelationMapper ecCustRelationMapper;
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Autowired
    private EcCustPerMapper ecCustPerMapper;
    @RabbitListener(queues = "guPolicymain")
    public void addPolicyMain(String message){
        log.info(message);
        try{
            Manager manager = JSON.parseObject(message, Manager.class);
            EcCustRelation ecCustRelation = new EcCustRelation();
            CoreContext cc = new CoreContext();
            Map map = new HashMap();
            map.put("userId", "admin");
            cc.putAll(map);
            ContextContainer.setContext(cc);
            if (!manager.getCustNo().equals(manager.getRefCustNo())) {
                String custNo = manager.getCustNo();
                List<String> custNoNewList= new ArrayList();
                List<String> custNoOldList= new ArrayList();
                custNoOldList.add(custNo);
                custNoNewList.add(custNo);
                while(true){
                    List<String> resData = ecCustPerMapper.getCustNoListByCustNo(custNoOldList);
                    custNoOldList.clear();
                    if(resData.size()>0){
                        custNoNewList.addAll(resData);
                        custNoOldList.addAll(resData);
                        continue;
                    }else{
                        break;
                    }
                }
                if(custNoNewList.size()>0) {
                    for(int i=1;i <= custNoNewList.size();i++) {
                        if (i == custNoNewList.size()) {
                            custNo = custNoNewList.get(i-1);
                            ecCustRelation.setCustNo(custNo);
                        }else{
                            continue;
                        }
                    }
                }else{
                    ecCustRelation.setCustNo(custNo);
                }
                String refCustNo = manager.getRefCustNo();
                custNoOldList.add(refCustNo);
                custNoNewList.add(refCustNo);
                while(true){
                    List<String> resData = ecCustPerMapper.getCustNoListByCustNo(custNoOldList);
                    custNoOldList.clear();
                    if(resData.size()>0){
                        custNoNewList.addAll(resData);
                        custNoOldList.addAll(resData);
                        continue;
                    }else{
                        break;
                    }
                }
                if(custNoNewList.size()>0) {
                    for(int i=1;i <= custNoNewList.size();i++) {
                        if (i == custNoNewList.size()) {
                            refCustNo = custNoNewList.get(i-1);
                            ecCustRelation.setRefCustNo(refCustNo);
                        }else{
                            continue;
                        }
                    }
                }else{
                    ecCustRelation.setRefCustNo(refCustNo);
                }
                EcCustRelation obj = ecCustRelationMapper.selectByPrimaryKey(ecCustRelation.getCustNo(), ecCustRelation.getRelationTyp(), ecCustRelation.getRefCustNo());
                if(obj==null) {
                    ecCustRelation.setRelationTyp(EnumType.Relation.unknown.value);
                    custRelService.addCustRel(ecCustRelation);
                }
            }
            EcCustManager ecCustManager = new EcCustManager();
            ecCustManager.setCustNo(manager.getCustNo());
            ecCustManager.setCustAgent(manager.getManagerNo());
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            Date date = manager.getRiseTime();
            String riseTime = format1.format(date);
            ecCustManager.setRiseTime(riseTime);
            //ecCustManagerMapper.insert(ecCustManager);
            String custNos = manager.getCustNo();
            List<String> custNoNewList= new ArrayList();
            List<String> custNoOldList= new ArrayList();
            custNoOldList.add(custNos);
            custNoNewList.add(custNos);
            while(true){
                List<String> resData = ecCustPerMapper.getCustNoListByCustNo(custNoOldList);
                custNoOldList.clear();
                if(resData.size()>0){
                    custNoNewList.addAll(resData);
                    custNoOldList.addAll(resData);
                    continue;
                }else{
                    break;
                }
            }
            if(custNoNewList.size()>0) {
                for(int i=1;i <= custNoNewList.size();i++) {
                    custNos = custNoNewList.get(i-1);
                    ecCustManager.setCustNo(custNos);
                    ecCustManager.setCustAgent(manager.getManagerNo());
                    ecCustManager.setRiseTime(riseTime);
                    ecCustManagerMapper.insert(ecCustManager);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            amqpTemplate.convertAndSend("guPolicymain_wrong", message);
        }
    }

}
