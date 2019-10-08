package cn.com.zsyk.crm.ecif.web.controller.customer.portrait;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.ecif.entity.EcCustTouch;
import cn.com.zsyk.crm.ecif.service.customer.portrait.CustTouchService;

@RestController
public class CustTouchCtrl {

	@Autowired
	private CustTouchService custTouchService;
	/**
	 * @api {GET} /crm/ecif/cust/custTouchList 查询客户接触列表(N)
	 * @apiDescription 
	 * @apiName getcustTouchList
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcCustContact} ecCustContact 客户接触bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			接触编号		contact_no<br/>
	 * 			处理人员		deal_person<br/>
	 * 			交互方式		interac_mode->InteracMode<br/>
	 * 			记录日期		rec_date<br/>
	 * 			客户交互主题的简短描述		interac_theme_desc<br/>
	 * 			附加描述		addi_desc<br/>
	 * 			交互日期		interac_date<br/>
	 * 			无效标识		invalid_flg-><br/>
	 * 			交互状态码		interac_sts_cd->(码值)<br/>
	 * 			反馈类型		feedback_typ->(待确认)<br/>
	 * 			客户交互信息标识（客户号）		cust_interac_flg->(待确认)<br/>
	 * 			客户交互类型		cust_interac_type->(待确认)<br/>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/custTouchList", method = RequestMethod.GET)
	public Result getcustTouchList(EcCustTouch ecCustTouch) {
		Result res = new Result();
		res.setData(custTouchService.getcustTouchList(ecCustTouch));
		return res;
	}
}
