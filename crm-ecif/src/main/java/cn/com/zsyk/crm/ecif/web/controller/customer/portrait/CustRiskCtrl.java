package cn.com.zsyk.crm.ecif.web.controller.customer.portrait;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.ecif.service.customer.portrait.CustRiskService;

@RestController
public class CustRiskCtrl {

	@Autowired
	private CustRiskService custRiskService;
	/**
	 * @api {GET} /crm/ecif/cust/perCustRiskList 查询个人风险信息
	 * @apiDescription 
	 * @apiName getPerCustRiskInfo
	 * @apiGroup Customer
	 * 
	 * @apiParam {String} custNo 客户号
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			客户号		cust_no<br/>
	 * 			信用评级		credit_rating->Rating<br/>
	 * 			是否有逾期记录		wht_overdue_rec->YesNoFlg<br/>
	 * 			逾期次数		overdue_cnt<br/>
	 * 			健康情况		health_condition->HealthCondition<br/>
	 * 			工作年限		work_age_lmt<br/>
	 * 			是否有医保		wht_med_insur->YesNoFlg<br/>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/perCustRiskList", method = RequestMethod.GET)
	public Result getPerCustRiskInfo(String custNo) {
		Result res = new Result();
		res.setData(custRiskService.getPerCustRiskInfo(custNo));
		return res;
	}

	/**
	 * @api {GET} /crm/ecif/cust/entCustRiskList 查询企业风险信息(N)
	 * @apiDescription 
	 * @apiName getEntCustRiskInfo
	 * @apiGroup Customer
	 * 
	 * @apiParam {String} custNo 客户号
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			客户号		cust_no<br/>
	 * 			信用评级		credit_rating->Rating<br/>
	 * 			是否股权清晰		wht_equity_clear->YesNoFlg<br/>
	 * 			是否存在股权质押		wht_equity_pledge->YesNoFlg<br/>
	 * 			是否有企业贷款		wht_ent_loan->YesNoFlg<br/>
	 * 			贷款金额（万元）		loan_amt<br/>
	 * 			经营场所所有权		biz_site_owner->BizSiteOwner<br/>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/entCustRiskList", method = RequestMethod.GET)
	public Result getEntCustRiskInfo(String custNo) {
		Result res = new Result();
		res.setData(custRiskService.getEntCustRiskInfo(custNo));
		return res;
	}
}
