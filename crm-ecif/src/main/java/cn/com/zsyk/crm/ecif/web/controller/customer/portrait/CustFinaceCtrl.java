package cn.com.zsyk.crm.ecif.web.controller.customer.portrait;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.ecif.entity.EcFinaceClaim;
import cn.com.zsyk.crm.ecif.entity.EcFinacePay;
import cn.com.zsyk.crm.ecif.service.customer.portrait.CustFinaceService;

@RestController
public class CustFinaceCtrl {

	@Autowired
	private CustFinaceService custFinaceService;
	/**
	 * @api {GET} /crm/ecif/cust/custFinacePayoList 查询财务缴费列表
	 * @apiDescription 
	 * @apiName getCustFinacePayoList
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcFinacePay} ecFinacePay 客户财务缴费Bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			客户号		cust_no<br/>
	 * 			保单号		policy_no<br/>
	 * 			缴费银行		pay_bank<br/>
	 * 			缴费账号		pay_account<br/>
	 * 			缴费账号后四位		pay_ac_last_four<br/>
	 * 			缴费年限		pay_years<br/>
	 * 			缴费次数		pay_tm<br/>
	 * 			缴费频次		pay_trequency->(待确认)<br/>
	 * 			缴费金额		pay_amt<br/>
	 * 			缴费日期		pay_date<br/>
	 * 			应缴日期		payable_date<br/>
	 * 			缴费方式		pay_mode->PayMode<br/>
	 * 			缴费渠道		pay_chnl->(待确认)<br/>
	 * 			缴费人		payer<br/>
	 * 			投保人		applicant<br/>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/custFinacePayoList", method = RequestMethod.GET)
	public Result getCustFinacePayoList(EcFinacePay ecFinacePay) {
		Result res = new Result();
		res.setData(custFinaceService.getCustFinacePayoList(ecFinacePay));
		return res;
	}

	/**
	 * @api {GET} /crm/ecif/cust/custFinaceClaimList 查询财务理赔列表
	 * @apiDescription 
	 * @apiName getCustFinaceClaimList
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcFinaceClaim} ecFinaceClaim 客户财务理赔Bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			客户号		cust_no<br/>
	 * 			理赔编号		claim_no<br/>
	 * 			保单号		policy_no<br/>
	 * 			受理日期		accept_date<br/>
	 * 			结案日期		closed_date<br/>
	 * 			理赔类型		claim_type->(待确认)<br/>
	 * 			产品名称		kind_name<br/>
	 * 			理赔状态		claim_sts->ClmState<br/>
	 * 			状态更新时间		status_upt_tm<br/>
	 * 			受益人		beneficiary<br/>
	 * 			赔款金额		indemnity_amt<br/>
	 * 			收益金额		profit_amt<br/>
	 * 			收益比例		profit_pro<br/>
	 * 			缴费银行		pay_bank<br/>
	 * 			缴费账号		pay_ac<br/>
	 * 			缴费账号后四位		pay_ac_last_four<br/>
	 * 			到账日期		arrival_date<br/>
	 * 			描述		claim_desc<br/>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/custFinaceClaimList", method = RequestMethod.GET)
	public Result getCustFinaceClaimList(EcFinaceClaim ecFinaceClaim) {
		Result res = new Result();
		res.setData(custFinaceService.getCustFinaceClaimList(ecFinaceClaim));
		return res;
	}

	/**
	 * @api {GET} /crm/ecif/cust/custFirstActList 查询首期账户列表
	 * @apiDescription 
	 * @apiName getCustFirstActList
	 * @apiGroup Customer
	 * 
	 * @apiParam {String} custNo 客户号
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			客户号码		cust_no<br/>
	 * 			是否默认账户		wht_def_ac->YesNoFlg<br/>
	 * 			银行编码		bank_cd->(码值)<br/>
	 * 			银行帐号		bank_ac<br/>
	 * 			银行帐户名		bank_ac_nam<br/>
	 * 			省(开户地)		prov_cd->(码值)<br/>
	 * 			市(开户地)		city_cd->(码值)<br/>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/custFirstActList", method = RequestMethod.GET)
	public Result getCustFirstActList(String custNo) {
		Result res = new Result();
		res.setData(custFinaceService.getCustFirstActList(custNo));
		return res;
	}
	
}
