package cn.com.zsyk.crm.ecif.web.controller.customer.batchimport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.ecif.entity.EcCustSaleChgTrace;
import cn.com.zsyk.crm.ecif.service.customer.importallot.CustSaleChgTraceService;


@RestController
public class CustSaleChgTraceCtrl {

	@Autowired
	private CustSaleChgTraceService custService;
	

	/**
	 * @api {GET} /crm/ecif/cust/addCustSaleChgTrace 新增客户新增轨迹
	 * @apiName addCustSaleChgTrace
	 * @apiGroup Customer
	 *
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ecif/cust/addCustSaleChgTrace", method = RequestMethod.POST)
	public Result addCustSaleChgTrace(EcCustSaleChgTrace custSaleChgTrace) {
		Result res = new Result();
		res.setData(custService.addCustSaleChgTrace(custSaleChgTrace));
		return res;
	}


}
