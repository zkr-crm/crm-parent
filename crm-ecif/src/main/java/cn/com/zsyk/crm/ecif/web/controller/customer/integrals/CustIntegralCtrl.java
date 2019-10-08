package cn.com.zsyk.crm.ecif.web.controller.customer.integrals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.ecif.entity.EcIntegral;
import cn.com.zsyk.crm.ecif.service.customer.integrals.CustIntegralService;

@RestController
public class CustIntegralCtrl {

	@Autowired
	private CustIntegralService custIntegralService;

	/**
	 * @api {GET} /crm/ecif/cust/custIntegralList 查询客户积分列表
	 * @apiDescription 
	 * 
	 * @apiName getCustIntegralList
	 * @apiGroup Customer
	 *
	 * @apiParam {EcIntegral} ecIntegral 客户积分bean
	 * 
	 * @apiSuccess {Object} Response 返回值对象
	 * 			客户号				cust_no<br/>
	 * 			积分变化额度		integ_chg_quota<br/>
	 * 			积分名称			integ_nam<br/>
	 * 			积分变化时间		integ_chg_time<br/>
	 * 			产品名称			product_nam<br/>
	 * 			保单号				policy_no<br/>
	 * 			积分变化类型		integ_chg_typ->IntegralChgType<br/>
	 * 			备注					remark<br/>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ecif/cust/custIntegralList", method = RequestMethod.GET)
	public Result getCustIntegralList(EcIntegral ecIntegral) {
		Result res = new Result();
		res.setData(custIntegralService.getCustIntegralList(ecIntegral));
		return res;
	}

	/**
	 * @api {GET} /crm/ecif/cust/custIntegralOne 查询客户积分(单条)
	 * @apiDescription 
	 * 
	 * @apiName getCustIntegralByPK
	 * @apiGroup Customer
	 *
	 * @apiParam {String} custNo 客户号
	 * @apiParam {String} policyNo 保单号
	 * @apiParam {String} integChgTyp 积分变化类型 
	 * 			IntegralChgType 0-增加/1-减少
	 * 
	 * @apiSuccess {Object} Response 返回值对象<br/>
	 * 			客户号				cust_no<br/>
	 * 			积分变化额度		integ_chg_quota<br/>
	 * 			积分名称			integ_nam<br/>
	 * 			积分变化时间		integ_chg_time<br/>
	 * 			产品名称			product_nam<br/>
	 * 			保单号				policy_no<br/>
	 * 			积分变化类型		integ_chg_typ->IntegralChgType<br/>
	 * 			备注					remark<br/>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ecif/cust/custIntegralOne", method = RequestMethod.GET)
	public Result getCustIntegralByPK(String custNo, String policyNo, String integChgTyp) {
		Result res = new Result();
		res.setData(custIntegralService.getCustIntegralByPK(custNo, policyNo, integChgTyp));
		return res;
	}
}
