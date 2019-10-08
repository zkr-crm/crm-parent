package cn.com.zsyk.crm.ecif.web.controller.customer.portrait;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.ecif.service.customer.relationship.CustRelService;

@RestController
public class CustGraphCtrl {

	@Autowired
	private CustRelService custRelService;
	/**
	 * @api {GET} /crm/ecif/cust/custGraph 客户关系图谱
	 * @apiDescription 
	 * @apiName getCustGraph
	 * @apiGroup Customer
	 * 
	 * @apiParam {String} custNo 客户号
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/custGraph", method = RequestMethod.GET)
	public Result getCustGraph(String custNo) {
		Result res = new Result();
		res.setData(custRelService.getCustGraph(custNo));
		return res;
	}
}
