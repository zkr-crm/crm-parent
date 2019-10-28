package cn.com.zsyk.crm.ecif.web.controller.customer.portrait;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.ecif.service.customer.portrait.CustDemandService;

@RestController
public class CustDemandCtrl {

	@Autowired
	private CustDemandService custDemandService;

	/**
	 * @api {GET} /crm/ecif/cust/custDemandList 查询客户需求列表
	 * @apiDescription 
	 * @apiName getCustDemandList
	 * @apiGroup Customer
	 * 
	 * @apiParam {String} custNo 客户号
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			采集日期		gather_date<br/>
	 * 			客户号		cust_no<br/>
	 * 			需求编号		demand_no<br/>
	 * 			需要名称		demand_nam<br/>
	 * 			需求类型		demand_typ->(待确认)<br/>
	 * 			需求阶段		demand_phase->DemandType<br/>
	 * 			需求描述		demand_desc<br/>
	 * 			负责人		principal<br/>
	 * 			状态		process_sts->(预留字段)<br/>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/custDemandList", method = RequestMethod.GET)
	public Result getCustDemandList(String custNo) {
		Result res = new Result();
		res.setData(custDemandService.selectCustDemandList(custNo));
		return res;
	}

	/**
	 * @api {GET} /crm/ecif/cust/custDemandOne 查询客户需求(单条)
	 * @apiDescription 
	 * @apiName getCustDemandOne
	 * @apiGroup Customer
	 * 
	 * @apiParam {String} demandNo 客户需求编号
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			采集日期		gather_date<br/>
	 * 			客户号		cust_no<br/>
	 * 			需求编号		demand_no<br/>
	 * 			需要名称		demand_nam<br/>
	 * 			需求类型		demand_typ->(待确认)<br/>
	 * 			需求阶段		demand_phase->DemandType<br/>
	 * 			需求描述		demand_desc<br/>
	 * 			负责人		principal<br/>
	 * 			状态		process_sts->(预留字段)<br/>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/custDemandOne", method = RequestMethod.GET)
	public Result getCustDemandOne(String demandNo) {
		Result res = new Result();
		res.setData(custDemandService.selectCustDemandOne(demandNo));
		return res;
	}
}
