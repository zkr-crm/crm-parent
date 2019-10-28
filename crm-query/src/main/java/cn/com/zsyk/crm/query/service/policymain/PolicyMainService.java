package cn.com.zsyk.crm.query.service.policymain;

import cn.com.zsyk.crm.common.dao.CoreDaoImpl;
import cn.com.zsyk.crm.common.dto.PageBean;
import cn.com.zsyk.crm.common.dto.PageContainer;
import cn.com.zsyk.crm.query.entity.GUPolicymain;
import cn.com.zsyk.crm.query.entity.Manager;
import cn.com.zsyk.crm.query.mapper.GuPolicyMainMapper;
import com.alibaba.fastjson.JSON;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PolicyMainService {
    @Autowired
    private GuPolicyMainMapper guPolicymainMapper;
    @Autowired
    private AmqpTemplate amqpTemplate;
    /* 分页查询对象 */
    @Autowired
    private CoreDaoImpl coreDaoImpl;

    public int selectPolicyMain(String stampTime){
        int perSelectCount = 1000;// 每次查询的数据量
        // 客户的总查询量
        long totalSelectCount = guPolicymainMapper.selectAllCustCount(stampTime);
        // 循环查询次数
        long cycleCount = totalSelectCount % perSelectCount == 0 ? totalSelectCount / perSelectCount
                : totalSelectCount / perSelectCount + 1;
        List<GUPolicymain> list = new ArrayList<GUPolicymain>();
        for (int i = 0; i < cycleCount; i++) {
            // [0,999] [1000,1999] ... 这里用到了分页的思想
            PageContainer pageContainer =new PageContainer();
            pageContainer.setPageNum(i);
            pageContainer.setPageSize(perSelectCount);
            PageBean bean = coreDaoImpl.selectPageByMapper(guPolicymainMapper, "selectByList", stampTime);

            list= bean.getList();
            for (GUPolicymain map : list) {
                    Manager manager = addCustNames(map);
                    amqpTemplate.convertAndSend("guPolicymain", JSON.toJSONString(manager));
            }

        }
            return 1;
    }


    public Manager addCustNames(GUPolicymain map){
        Manager manager = new Manager();
        if(null!=map.getApplicode()){
            manager.setCustNo(map.getApplicode());
        }
        if(null!=map.getSalesmancode()){
            manager.setManagerNo(map.getSalesmancode());
        }
        if(null!=map.getIssuedate()){
            manager.setRiseTime(map.getIssuedate());
        }
        if(null!=map.getInsuredcode()){
            manager.setRefCustNo(map.getInsuredcode());
        }
        return manager;
    }
}
