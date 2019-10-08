package cn.com.zsyk.crm.query.web.controller.cleanclient;

import cn.com.zsyk.crm.common.constant.ServiceType;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.util.DateUtil;
import cn.com.zsyk.crm.common.util.RestUtil;
import cn.com.zsyk.crm.query.service.cleanclient.CleanClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CleanClientCtr {
    @Autowired
    private CleanClientService cleanClientService;
    @Autowired
    private RestUtil restUtil;
    @RequestMapping(path = "/crm/query/cleanclient/companys",method = RequestMethod.POST)
    public Result selectClient(){
        Result res = new Result();
        String starts = DateUtil.nowDateTimeStamp();
        String ournalName = "companys";
        Result getForObject = restUtil.getForObject(ServiceType.ECIF, "/crm/ecif/customer/selectNumCustList?ournalName={ournalName}", Result.class,  ournalName);
        String stampTime="";
        if(getForObject.getData()!=null){
            stampTime = getForObject.getData().toString();
        }
        res.setStatus(cleanClientService.selectClient(stampTime)+"");
        String end = DateUtil.nowDateTimeStamp();
        restUtil.getForObject(ServiceType.ECIF, "/crm/ecif/customer/inNumCustList?ournalName={ournalName}&starts={starts}&end={end}", Result.class,  ournalName,starts,end);
        return res;
    }
    @RequestMapping(path = "/crm/query/cleanclient/selectReportorMobile",method = RequestMethod.POST)
    public Result selectReportorMobiles(){
        Result res = new Result();
        String starts = DateUtil.nowDateTimeStamp();
        String ournalName = "reportormobile";
        Result getForObject = restUtil.getForObject(ServiceType.ECIF, "/crm/ecif/customer/selectNumCustList?ournalName={ournalName}", Result.class,  ournalName);
        String stampTime="";
        if(getForObject.getData()!=null){
            stampTime = getForObject.getData().toString();
        }
        res.setStatus(cleanClientService.selectReportorMobile(stampTime)+"");
        String end = DateUtil.nowDateTimeStamp();
        restUtil.getForObject(ServiceType.ECIF, "/crm/ecif/customer/inNumCustList?ournalName={ournalName}&starts={starts}&end={end}", Result.class,  ournalName,starts,end);
        return res;
    }

}
