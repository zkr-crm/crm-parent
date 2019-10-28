package cn.com.zsyk.crm.ecif.service.policymains;

import cn.com.zsyk.crm.common.dto.ContextContainer;
import cn.com.zsyk.crm.common.dto.CoreContext;
import cn.com.zsyk.crm.common.util.LogUtil;
import cn.com.zsyk.crm.ecif.entity.EcCustManager;
import cn.com.zsyk.crm.ecif.entity.EcCustPer;
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

    /**
     * 接收客户经理信息及投保人被保人添加关系为‘未知’
     * @param message
     */
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
            /**
             * 将经理添加到包含此客户的合并客户信息内--投保人
             */
            if(custNoNewList.size()>0) {
                for(int i=1;i <= custNoNewList.size();i++) {
                    custNos = custNoNewList.get(i-1);
                    ecCustManager.setCustNo(custNos);
                    ecCustManager.setCustAgent(manager.getManagerNo());
                    ecCustManager.setRiseTime(riseTime);
                    ecCustManager.setRecStat("0");
                    List<EcCustManager> listTmp=ecCustManagerMapper.selectAgentByConditions(ecCustManager);
                    if(listTmp==null || listTmp.size()==0){
                        ecCustManagerMapper.insert(ecCustManager);
                    }

                    EcCustPer custPer = ecCustPerMapper.selectByPrimaryKey(custNos);
                    if(custPer!=null) {
                        custPer.setCustAgent(manager.getManagerNo());
                        ecCustPerMapper.updateByPrimaryKey(custPer);
                    }
                }
            }

            /**
             * 更新添加保单的客户为正式
             */
            if(custNoNewList.size()>0) {
                for(int i=1;i <= custNoNewList.size();i++) {
                    custNos = custNoNewList.get(i-1);
                    EcCustPer ecCustPer = ecCustPerMapper.selectByPrimaryKey(custNos);
                    if(ecCustPer!=null) {
                        ecCustPer.setCustNo(custNos);
                        ecCustPer.setCustTyp(EnumType.CustType.per_formal_cust.value);
                        ecCustPerMapper.updateByPrimaryKey(ecCustPer);
                    }
                }
            }
            custNoOldList.clear();
            custNoNewList.clear();
            /**
             * 将经理添加到包含此客户的合并客户信息内--被保人
             */
            if(!manager.getCustNo().equals(manager.getRefCustNo())) {
                String refCustNos = manager.getRefCustNo();
                custNoNewList= new ArrayList();
                custNoOldList= new ArrayList();
                custNoOldList.add(refCustNos);
                custNoNewList.add(refCustNos);
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
                if (custNoNewList.size() > 0) {
                    for (int i = 1; i <= custNoNewList.size(); i++) {
                        refCustNos = custNoNewList.get(i - 1);
                        ecCustManager.setCustNo(refCustNos);
                        ecCustManager.setCustAgent(manager.getManagerNo());
                        ecCustManager.setRiseTime(riseTime);
                        ecCustManager.setRecStat("0");
                        List<EcCustManager> listTmp=ecCustManagerMapper.selectAgentByConditions(ecCustManager);
                        if(listTmp==null || listTmp.size()==0){
                            ecCustManagerMapper.insert(ecCustManager);
                        }
                        EcCustPer custPer1 = ecCustPerMapper.selectByPrimaryKey(refCustNos);
                        if(custPer1!=null) {
                            custPer1.setCustAgent(manager.getManagerNo());
                            ecCustPerMapper.updateByPrimaryKey(custPer1);
                        }
                    }
                }
            }
            /**
             * 更新添加保单的客户为正式
             */
            if(custNoNewList.size()>0) {
                for(int i=1;i <= custNoNewList.size();i++) {
                    String refCustNoss = custNoNewList.get(i-1);
                    EcCustPer ecCustPer = ecCustPerMapper.selectByPrimaryKey(refCustNoss);
                    if(ecCustPer!=null) {
                        ecCustPer.setCustNo(refCustNoss);
                        ecCustPer.setCustTyp(EnumType.CustType.per_formal_cust.value);
                        ecCustPerMapper.updateByPrimaryKey(ecCustPer);
                    }
                }
            }
            custNoOldList.clear();
            custNoNewList.clear();
            /**
             * 添加包含客户的合并后的投保人和被保人关系为未知
             */
            if (!manager.getCustNo().equals(manager.getRefCustNo())) {
                String custNo = manager.getCustNo();
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
                List<String> custNoNewLists= new ArrayList();
                List<String> custNoOldLists= new ArrayList();
                custNoOldLists.add(refCustNo);
                custNoNewLists.add(refCustNo);
                while(true){
                    List<String> resData = ecCustPerMapper.getCustNoListByCustNo(custNoOldLists);
                    custNoOldLists.clear();
                    if(resData.size()>0){
                        custNoNewLists.addAll(resData);
                        custNoOldLists.addAll(resData);
                        continue;
                    }else{
                        break;
                    }
                }
                if(custNoNewLists.size()>0) {
                    for(int i=1;i <= custNoNewLists.size();i++) {
                        if (i == custNoNewLists.size()) {
                            refCustNo = custNoNewLists.get(i-1);
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
                    try{
                        custRelService.addCustRel(ecCustRelation);
                    }catch (Exception e){
                        amqpTemplate.convertAndSend("guPolicymain_wrong", message);
                    }

                }
            }

        }catch (Exception e){
            e.printStackTrace();
            amqpTemplate.convertAndSend("guPolicymain_wrong", message);
        }
    }

}
