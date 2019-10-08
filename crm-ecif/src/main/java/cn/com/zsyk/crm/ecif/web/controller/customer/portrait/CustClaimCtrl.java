package cn.com.zsyk.crm.ecif.web.controller.customer.portrait;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.ecif.entity.EcCustClaim;
import cn.com.zsyk.crm.ecif.service.customer.portrait.CustClaimService;

@RestController
public class CustClaimCtrl {

	@Autowired
	private CustClaimService custClaimService;
	/**
	 * @api {GET} /crm/ecif/cust/custClaimList 查询客户理赔列表
	 * @apiDescription 
	 * @apiName getCustClaimList
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcCustClaim} ecCustClaim 客户理赔bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			理赔号码		claim_no<br/>
	 * 			报案号码		report_no<br/>
	 * 			索赔金额		claim_amt<br/>
	 * 			赔付总额		claim_ttl_amt<br/>
	 * 			协议赔付金额		protocol_claim_amt<br/>
	 * 			最高理赔金额		max_claim_amt<br/>
	 * 			理赔类型		claim_typ->(待确认)<br/>
	 * 			险种名称		insure_kind_nam<br/>
	 * 			险种代码		insure_kind_cd->(待确认)<br/>
	 * 			理赔状态		claim_sts<br/>
	 * 			状态更新时间		sts_upt_tm<br/>
	 * 			立案日期		regi_date<br/>
	 * 			受理日期		accept_date<br/>
	 * 			报案日期		report_date<br/>
	 * 			出险日期		loss_occur_date<br/>
	 * 			保单号码		proposal_no<br/>
	 * 			是否已赔付		wht_claim->YesNoFlg<br/>
	 * 			描述		desc_content<br/>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/custClaimList", method = RequestMethod.GET)
	public Result getCustClaimList(EcCustClaim ecCustClaim) {
		Result res = new Result();
		res.setData(custClaimService.getCustClaimList(ecCustClaim));
		return res;
	}
}
