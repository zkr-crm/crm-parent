package cn.com.zsyk.crm.ecif.web.controller.customer.portrait;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import cn.com.zsyk.crm.common.annotation.SysOprtLog;
import cn.com.zsyk.crm.common.annotation.SysOprtLog.Module;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.ecif.entity.EcCustAgent;
import cn.com.zsyk.crm.ecif.service.customer.portrait.CustAgentService;

@RestController
public class CustAgentCtrl {

	@Autowired
	private CustAgentService custAgentService;
	/**
	 * @api {GET} /crm/ecif/cust/custAgentList 查询客户代理人列表
	 * @apiDescription 
	 * @apiName getCustAgentList
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcCustAgent} ecCustAgent 客户代理人bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			客户号		cust_no<br/>
	 * 			源系统代理人编号		orig_sys_agent_no<br/>
	 * 			代理人展业机构代码		agent_devcom_cd->(待确认)<br/>
	 * 			代理人类别		agent_kind->(待确认)<br/>
	 * 			所属渠道		bln_chnl(待确认)<br/>
	 * 			绩优等级		perf_grade->Rating<br/>
	 * 			信用等级		credit_grade->Rating<br/>
	 * 			入司日期		inwork_date<br/>
	 * 			代理人状态		agent_sts->AgentStat<br/>
	 * 			离司日期		outwork_date<br/>
	 * 			职级		posit_grade->(待确认)<br/>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/custAgentList", method = RequestMethod.GET)
	public Result getCustAgentList(EcCustAgent ecCustAgent) {
		Result res = new Result();
		res.setData(custAgentService.getCustAgentList(ecCustAgent));
		return res;
	}

	/**
	 * @api {GET} /crm/ecif/cust/custAgentOne 查询客户代理人(单条)
	 * @apiDescription 
	 * @apiName getCustAgentOne
	 * @apiGroup Customer
	 * 
	 * @apiParam {String} cust_no 客户号
	 * @apiParam {String} orig_sys_agent_no代理人编号
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			客户号		cust_no<br/>
	 * 			源系统代理人编号		orig_sys_agent_no<br/>
	 * 			代理人展业机构代码		agent_devcom_cd->(待确认)<br/>
	 * 			代理人类别		agent_kind->(待确认)<br/>
	 * 			所属渠道		bln_chnl(待确认)<br/>
	 * 			绩优等级		perf_grade->Rating<br/>
	 * 			信用等级		credit_grade->Rating<br/>
	 * 			入司日期		inwork_date<br/>
	 * 			代理人状态		agent_sts->AgentStat<br/>
	 * 			离司日期		outwork_date<br/>
	 * 			职级		posit_grade->(待确认)<br/>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/custAgentOne", method = RequestMethod.GET)
	public Result getCustAgentOne(String custNo, String origSysAgentNo) {
		Result res = new Result();
		res.setData(custAgentService.getCustAgentOne(custNo, origSysAgentNo));
		return res;
	}

	/**
	 * @api {PUT} /crm/ecif/cust/addCustAgent 新增代理人
	 * @apiDescription 
	 * @apiName addCustAgent
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcCustAgent} ecCustAgent 客户事件Bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/addCustAgent", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.CUSTOMER, bizDesc="新增代理人")
	public Result addCustAgent(EcCustAgent ecCustAgent) {
		Result res = new Result();
		try {
			custAgentService.addCustAgent(ecCustAgent);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("新增客户代理人信息失败");
		}
		return res;
	}

	/**
	 * @api {PUT} /crm/ecif/cust/uptCustAgent 修改代理人
	 * @apiDescription 
	 * @apiName uptCustAgent
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcCustAgent} ecCustAgent 客户事件Bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/uptCustAgent", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.CUSTOMER, bizDesc="修改代理人")
	public Result uptCustAgent(EcCustAgent ecCustAgent) {
		Result res = new Result();
		try {
			custAgentService.uptCustAgent(ecCustAgent);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("修改客户代理人信息失败");
		}
		return res;
	}

	/**
	 * @api {PUT} /crm/ecif/cust/delCustAgent 删除代理人
	 * @apiDescription 
	 * @apiName delCustAgent
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcCustAgent} ecCustAgent 客户事件Bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/delCustAgent", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.CUSTOMER, bizDesc="删除代理人")
	public Result delCustAgent(EcCustAgent ecCustAgent) {
		Result res = new Result();
		try {
			custAgentService.delCustAgent(ecCustAgent);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("删除客户代理人信息失败");
		}
		return res;
	}
}
