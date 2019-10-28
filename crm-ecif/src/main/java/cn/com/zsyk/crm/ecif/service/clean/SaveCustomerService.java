package cn.com.zsyk.crm.ecif.service.clean;

import cn.com.zsyk.crm.common.dao.CoreDaoImpl;
import cn.com.zsyk.crm.common.dto.ContextContainer;
import cn.com.zsyk.crm.common.dto.CoreContext;
import cn.com.zsyk.crm.common.dto.PageBean;
import cn.com.zsyk.crm.common.dto.PageContainer;
import cn.com.zsyk.crm.common.util.IdGenerator;
import cn.com.zsyk.crm.ecif.entity.ECCustpers;
import cn.com.zsyk.crm.ecif.entity.EcCustName;
import cn.com.zsyk.crm.ecif.entity.EcOurnalTag;
import cn.com.zsyk.crm.ecif.mapper.EcCustNameMapper;
import cn.com.zsyk.crm.ecif.mapper.EcOurnalTagMapper;
import cn.com.zsyk.crm.ecif.mapper.EcSimCustMapper;
import cn.com.zsyk.crm.ecif.service.scheduler.birthmng.SimCustTaskGnrtService;
import com.alibaba.fastjson.JSON;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    @Autowired
    private CoreDaoImpl coreDaoImpl;
    /**
     * Desc: 新增客户信息合并
     * @param
     * @return
     * @author
     */
    public int saveCustomer(String stampTime){
        int perSelectCount = 1000;// 每次查询的数据量
        // 客户的总查询量
        long totalSelectCount = ecCustNameMapper.selectAllCustCount(stampTime);
        // 循环查询次数
        long cycleCount = totalSelectCount % perSelectCount == 0 ? totalSelectCount / perSelectCount
                : totalSelectCount / perSelectCount + 1;
        List<EcCustName> list=new ArrayList<EcCustName>();
        for (int i = 0; i < cycleCount; i++) {
            // [0,999] [1000,1999] ... 这里用到了分页的思想
            PageContainer pageContainer = new PageContainer();
            pageContainer.setPageNum(i);
            pageContainer.setPageSize(perSelectCount);
            PageBean bean = coreDaoImpl.selectPageByMapper(ecCustNameMapper, "selectCustNos", stampTime);
            if(bean!=null) {
            list = bean.getList();
                ECCustpers ecCustPer = new ECCustpers();
                String message = "";
                for (EcCustName ecCustName : list) {
                    message =ecCustName.getCustNo();
                    try {
                        ecCustPer.setCustNo(message);
                        amqpTemplate.convertAndSend("simCustTaskGnrt", JSON.toJSONString(ecCustPer));
                    }catch(Exception e) {
                        e.printStackTrace();
                        amqpTemplate.convertAndSend("clientQueue_wrong", message);                    }
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
