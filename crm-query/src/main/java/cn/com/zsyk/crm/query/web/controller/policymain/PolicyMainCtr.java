package cn.com.zsyk.crm.query.web.controller.policymain;

import cn.com.zsyk.crm.common.constant.ServiceType;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.util.DateUtil;
import cn.com.zsyk.crm.common.util.RestUtil;
import cn.com.zsyk.crm.query.service.policymain.PolicyMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PolicyMainCtr {
    @Autowired
    private PolicyMainService policyMainService;
    @Autowired
    private RestUtil restUtil;
    @RequestMapping(path = "/crm/query/selectPolicy/policyMain",method = RequestMethod.POST)
    public Result selectPolicy(){
        Result res = new Result();
        String starts = DateUtil.nowDateTimeStamp();
        String ournalName = "policyMain";
        Result getForObject = restUtil.getForObject(ServiceType.ECIF, "/crm/ecif/customer/selectNumCustList?ournalName={ournalName}", Result.class,  ournalName);
        String stampTime="";
        if(getForObject.getData()!=null){
            stampTime = getForObject.getData().toString();
        }
        res.setStatus(policyMainService.selectPolicyMain(stampTime)+"");
        String end = DateUtil.nowDateTimeStamp();
        restUtil.getForObject(ServiceType.ECIF, "/crm/ecif/customer/inNumCustList?ournalName={ournalName}&starts={starts}&end={end}", Result.class,  ournalName,starts,end);
        return res;
    }
}
