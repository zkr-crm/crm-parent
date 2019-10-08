package cn.com.zsyk.crm.ecif.service.clean;

import cn.com.zsyk.crm.common.dto.ContextContainer;
import cn.com.zsyk.crm.common.dto.CoreContext;
import cn.com.zsyk.crm.common.util.IdGenerator;
import cn.com.zsyk.crm.ecif.entity.ECCustpers;
import cn.com.zsyk.crm.ecif.entity.EcCustName;
import cn.com.zsyk.crm.ecif.entity.EcOurnalTag;
import cn.com.zsyk.crm.ecif.entity.EcSimCust;
import cn.com.zsyk.crm.ecif.mapper.EcCustNameMapper;
import cn.com.zsyk.crm.ecif.mapper.EcOurnalTagMapper;
import cn.com.zsyk.crm.ecif.mapper.EcSimCustMapper;
import cn.com.zsyk.crm.ecif.service.scheduler.birthmng.SimCustTaskGnrtService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SaveCustomerService {
    @Autowired
    private EcCustNameMapper ecCustNameMapper;
    @Autowired
    private SimCustTaskGnrtService simCustTaskGnrt;
    @Autowired
    private EcOurnalTagMapper ecOurnalTagMapper;
    @Autowired
    private EcSimCustMapper simCustMapper;
    @Autowired
    private AmqpTemplate amqpTemplate;
    /**
     * Desc: 新增客户信息合并
     * @param
     * @return
     * @author
     */
    public int saveCustomer(String stampTime){
        List<EcCustName> ecCustpers =ecCustNameMapper.selectCustNos(stampTime);
        if(ecCustpers!=null) {
            ECCustpers ecCustPer = new ECCustpers();
            String message = "";
            for (EcCustName ecCustName : ecCustpers) {
                EcSimCust ecSim = new EcSimCust();
                ecSim.setTaskId("");
                message =ecCustName.getCustNo();
                ecSim.setSimilarCustNo(message);
                List<EcSimCust> ecSimCust =  simCustMapper.selectByEntitys(ecSim);
                if(ecSimCust.size()!=0){
                    continue;
                }
                try {
                    ecCustPer.setCustNo(message);
                    simCustTaskGnrt.simCustTaskGnrt(ecCustPer);
                }catch(Exception e) {
                    e.printStackTrace();
                    amqpTemplate.convertAndSend("simCustTaskGnrt_wrong", message);
                }
            }
        }
        return 1;
    }
    public String selectOurnal(String ournalName){
        EcOurnalTag ecOurnalTags =ecOurnalTagMapper.selectByPrimaryKey(ournalName);
        String stampTime="";
        if(ecOurnalTags!=null){
            stampTime= ecOurnalTags.getStartTime();
        }
        return stampTime;
    }
    //添加修改清洗数据任务开始结束时间
    public int saveOurnal(String ournalName,String start,String end){
        EcOurnalTag ecOurnalTags =ecOurnalTagMapper.selectByPrimaryKey(ournalName);
        CoreContext cc = new CoreContext();
        Map map = new HashMap();
        map.put("userId", "admin");
        cc.putAll(map);
        ContextContainer.setContext(cc);
        EcOurnalTag ecOurnalTag;
        if(ecOurnalTags==null){
            ecOurnalTag =new EcOurnalTag();
            String custNo = String.valueOf(IdGenerator.getDistributedID());
            ecOurnalTag.setOurnalId(custNo);
            ecOurnalTag.setOurnalName(ournalName);
            ecOurnalTag.setStartTime(start);
            ecOurnalTag.setEndTime(end);
            ecOurnalTagMapper.insert(ecOurnalTag);
        }else{
            ecOurnalTags.setOurnalId(ecOurnalTags.getOurnalId());
            ecOurnalTags.setOurnalName(ecOurnalTags.getOurnalName());
            ecOurnalTags.setStartTime(start);
            ecOurnalTags.setEndTime(end);
            ecOurnalTagMapper.updateByPrimaryKey(ecOurnalTags);
        }
        return 1;
    }

}
