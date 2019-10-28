package cn.com.zsyk.crm.ecif.web.controller.customer.portrait;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.ecif.entity.EcCustEndorse;
import cn.com.zsyk.crm.ecif.service.customer.portrait.CustEndorseService;

@RestController
public class CustEndorseCtrl {

	@Autowired
	private CustEndorseService custEndorseService;
	/**
	 * @api {GET} /crm/ecif/cust/custEndorseList 查询客户保全列表
	 * @apiDescription 
	 * @apiName getCustEndorseList
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcCustEndorse} ecCustEndorse 客户保全bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			保全申请号		endor_apply_no<br/>
	 * 			保全受理号		endor_accept_no<br/>
	 * 			批单号		endor_no<br/>
	 * 			保单号		proposal_no<br/>
	 * 			客户号		cust_no<br/>
	 * 			批改申请日期		endor_appli_date<br/>
	 * 			批改生效日期		endor_eff_date<br/>
	 * 			批改状态		endor_sts->(待确认)<br/>
	 * 			批改原因编码		endor_reason_no->(待确认)<br/>
	 * 			产品标识		produce_flg->(待确认)<br/>
	 * 			变动保费		chg_premium<br/>
	 * 			变动保额		chg_amount<br/>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/custEndorseList", method = RequestMethod.GET)
	public Result getCustEndorseList(EcCustEndorse ecCustEndorse) {
		Result res = new Result();
		res.setData(custEndorseService.getCustEndorseList(ecCustEndorse));
		return res;
	}

}
