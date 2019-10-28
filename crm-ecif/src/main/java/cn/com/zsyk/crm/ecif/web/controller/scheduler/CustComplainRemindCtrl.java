package cn.com.zsyk.crm.ecif.web.controller.scheduler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.zsyk.crm.common.constant.ServiceType;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.dto.Result.StatusEnum;
import cn.com.zsyk.crm.common.util.JsonUtil;
import cn.com.zsyk.crm.common.util.RestUtil;
import cn.com.zsyk.crm.ecif.bo.cust.MsgInfo;
import cn.com.zsyk.crm.ecif.bo.cust.SysJobMsgRela;
import cn.com.zsyk.crm.ecif.entity.EcCustComplain;
import cn.com.zsyk.crm.ecif.service.scheduler.birthmng.CustComplainRemindService;
import cn.com.zsyk.crm.generator.EnumType;

/**
 * 客户投诉提醒批量
 * 
 * @author
 *
 */
@RestController
public class CustComplainRemindCtrl {

	@Autowired
	private CustComplainRemindService service;
	@Autowired
	private RestUtil restUtil;

	@SuppressWarnings("rawtypes")
	@RequestMapping(path = "/crm/ecif/sendComplainRemind", method = RequestMethod.POST)
	public Result sendComplainRemind(HttpServletRequest request) {
		Result res = new Result();

		SysJobMsgRela jobMsg = new SysJobMsgRela();
		jobMsg.setJobGroup(request.getParameter("jobGroup"));
		jobMsg.setJobName(request.getParameter("jobId"));
		jobMsg.setSchedName(request.getParameter("schedulerName"));
		List<String> codeList = service.getRemindCode(jobMsg);
		List<EcCustComplain> complainList = service.getCustComplain();
		for (int i = 0 ;i<codeList.size();i++) {

			if (complainList != null && complainList.size() > 0) {
				for (EcCustComplain custInfo : complainList) {
					// 提醒信息体编辑
					MsgInfo msgInfo = new MsgInfo();
					// 员工号
					msgInfo.setBusiCode(custInfo.getAgentNo());
					// 消息定义编码
					msgInfo.setMsgCode(codeList.get(i));
					// 发送渠道
					msgInfo.setChannel(EnumType.sendChannel.remind.value);
					// 消息模板参数
					msgInfo.setParams(
							custInfo.getAgentNo() + "," + custInfo.getCustNo() + "," + custInfo.getComplainNo());
					String toMsg = JsonUtil.toJSONString(msgInfo);
					Result postForObject = restUtil.postForObject(ServiceType.MANAGE,"/crm/manage/msgmng/sendRemindMsg", toMsg, Result.class);
					if(StatusEnum.SUCCESS.equals(postForObject.getStatusEnum())) {
						int result = service.updateRemindFlg(custInfo);
					}
				}

			} else {
				System.out.println("***************************没有提醒*****************************");
			}

		}

		return res;
	}

}
