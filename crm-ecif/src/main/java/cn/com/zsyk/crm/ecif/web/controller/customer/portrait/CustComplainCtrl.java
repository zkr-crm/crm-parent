package cn.com.zsyk.crm.ecif.web.controller.customer.portrait;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.ecif.entity.EcCustComplain;
import cn.com.zsyk.crm.ecif.service.customer.portrait.CustComplainService;

@RestController
public class CustComplainCtrl {

	@Autowired
	private CustComplainService custComplainService;
	/**
	 * @api {GET} /crm/ecif/cust/custComplainList 查询客户投诉列表(N)
	 * @apiDescription 
	 * @apiName getCustComplainList
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcCustComplain} ecCustComplain 客户投诉bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			投诉编号		complain_no<br/>
	 * 			客户号		cust_no<br/>
	 * 			案件编号		case_no<br/>
	 * 			案件类型		case_typ->(待确认)<br/>
	 * 			立案日期		case_regi date<br/>
	 * 			案件状态		case_sts->(待确认)<br/>
	 * 			转办单号码		transf_order_no<br/>
	 * 			投诉内容		complain_dets<br/>
	 * 			坐席编号		agent_no<br/>
	 * 			投诉类型		complain_typ->(待确认)<br/>
	 * 			投诉日期		complain_date<br/>
	 * 			投诉状态		complain_sts->(待确认)<br/>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/custComplainList", method = RequestMethod.GET)
	public Result getCustComplainList(EcCustComplain ecCustComplain) {
		Result res = new Result();
		res.setData(custComplainService.getCustComplainList(ecCustComplain));
		return res;
	}
}
