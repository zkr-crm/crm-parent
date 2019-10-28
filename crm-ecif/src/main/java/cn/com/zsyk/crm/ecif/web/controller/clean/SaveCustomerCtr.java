package cn.com.zsyk.crm.ecif.web.controller.clean;

import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.util.DateUtil;
import cn.com.zsyk.crm.common.util.LogUtil;
import cn.com.zsyk.crm.ecif.mapper.EcOurnalTagMapper;
import cn.com.zsyk.crm.ecif.service.clean.SaveCustomerService;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaveCustomerCtr {
    Log log = LogUtil.getLogger(SaveCustomerCtr.class);
    @Autowired
    private SaveCustomerService saveCustomerService;
    @Autowired
    private EcOurnalTagMapper ecOurnalTagMapper;

    @RequestMapping(path = "/crm/ecif/customer/getNumCustList", method = RequestMethod.POST)
    public Result saveCustomerCtr(){
        log = LogUtil.getLogger(SaveCustomerCtr.class);
        String starts = DateUtil.nowDateTimeStamp();
        Result res = new Result();
        String ournalName = "NumCustList";
        String startTime = saveCustomerService.selectOurnal(ournalName);
        res.setData(saveCustomerService.saveCustomer(startTime)+"");
        String end = DateUtil.nowDateTimeStamp();
        saveCustomerService.saveOurnal(ournalName,starts,end);
        return res;
    }
    @RequestMapping(path = "/crm/ecif/customer/selectNumCustList", method = RequestMethod.GET)
    public Result selectCustOmers(String ournalName){
        Result res = new Result();
        res.setData(saveCustomerService.selectOurnal(ournalName));
        return res;
    }
    @RequestMapping(path = "/crm/ecif/customer/inNumCustList", method = RequestMethod.GET)
    public Result inCutomerCtr(String ournalName,String starts,String end){
        Result res = new Result();
        res.setData(saveCustomerService.saveOurnal(ournalName,starts,end));
        return res;
    }
}
