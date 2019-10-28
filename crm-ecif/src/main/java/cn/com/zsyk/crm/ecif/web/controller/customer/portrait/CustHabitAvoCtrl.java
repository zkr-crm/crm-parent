package cn.com.zsyk.crm.ecif.web.controller.customer.portrait;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.ecif.service.customer.portrait.CustHabitAvoService;

@RestController
public class CustHabitAvoCtrl {

	@Autowired
	private CustHabitAvoService custHabitAvoService;
	/**
	 * @api {GET} /crm/ecif/cust/custHabitAvoList 查询客户习惯嗜好列表
	 * @apiDescription 
	 * @apiName getCustHabitAvoList
	 * @apiGroup Customer
	 * 
	 * @apiParam {String} custNo 客户号
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			客户号		cust_no<br/>
	 * 			习惯嗜好编码		habit_avoc_cd<br/>
	 * 			习惯嗜好值		habit_avoc_val<br/>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/custHabitAvoList", method = RequestMethod.GET)
	public Result getCustHabitAvoList(String custNo) {
		Result res = new Result();
		res.setData(custHabitAvoService.getCustHabitAvoList(custNo));
		return res;
	}

}
