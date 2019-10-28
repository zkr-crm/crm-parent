package cn.com.zsyk.crm.ecif.web.controller.scheduler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.zsyk.crm.common.constant.ServiceType;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.util.JsonUtil;
import cn.com.zsyk.crm.common.util.RestUtil;
import cn.com.zsyk.crm.ecif.bo.cust.MsgInfo;
import cn.com.zsyk.crm.ecif.bo.cust.PerCustList;
import cn.com.zsyk.crm.ecif.bo.cust.SysJobMsgRela;
import cn.com.zsyk.crm.ecif.service.scheduler.birthmng.BirthdayBlessingService;

@RestController
public class BirthdayBlessingCtrl {

	@Autowired
	private BirthdayBlessingService service;
	@Autowired
	private RestUtil restUtil;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(path = "/crm/ecif/birthdayBlessing", method = RequestMethod.POST)
	public Result sendBirthBLess(HttpServletRequest request) {
		Result res = new Result();
		
		SysJobMsgRela jobMsg = new SysJobMsgRela();
		jobMsg.setJobGroup(request.getParameter("jobGroup"));
		jobMsg.setJobName(request.getParameter("jobId"));
		jobMsg.setSchedName(request.getParameter("schedulerName"));
		List<String> codeList =  service.getMsgCode(jobMsg);
		List<PerCustList> custList = service.sendBirthdayBlessing( );
		for(String code : codeList) {
			
			if (custList != null && custList.size() > 0) {
				for (PerCustList custInfo : custList) {
					String custNo = custInfo.getCustNo();
					String phone = custInfo.getPhoneNumber();
					String custName = custInfo.getCustName();
						// 连接短信平台 
					MsgInfo msgInfo = new MsgInfo();
					msgInfo.setBusiCode(custNo);
					msgInfo.setCustName(custName);
					msgInfo.setMobile(phone);
					msgInfo.setMsgCode(code);
					msgInfo.setParams(custName+",www.rsinsurance.com"); 
					String toMsg = JsonUtil.toJSONString(msgInfo);
					Result postForObject = restUtil.postForObject(ServiceType.MANAGE, "/crm/manage/msgmng/sendMsg", toMsg, Result.class);
				}
	
			}else {
				System.out.println("***************************没有祝福*****************************");
			}
			
		}
		
		
		
		
		
		
		
		return res;
	}
	
}
