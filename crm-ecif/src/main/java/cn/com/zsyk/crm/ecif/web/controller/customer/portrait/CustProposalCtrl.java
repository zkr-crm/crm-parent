package cn.com.zsyk.crm.ecif.web.controller.customer.portrait;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.ecif.entity.EcCustProposal;
import cn.com.zsyk.crm.ecif.service.customer.portrait.CustProposalService;

@RestController
public class CustProposalCtrl {

	@Autowired
	private CustProposalService custProposalService;
	/**
	 * @api {GET} /crm/ecif/cust/custProposalList 查询客户保单列表
	 * @apiDescription 
	 * @apiName getCustProposalList
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcCustProposal} ecCustProposal 客户保单bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			投保单号		apply_order_no<br/>
	 * 			客户号		cust_no<br/>
	 * 			保单号		proposal_no<br/>
	 * 			支付周期		pay_cycle->PayIntv<br/>
	 * 			结算类型		balc_typ->(待确认)<br/>
	 * 			替换该契约的契约号		repl_inden_no<br/>
	 * 			折扣后期交保费		aft_disc_pay_fee<br/>
	 * 			下次发送账单日期		next_bill_date<br/>
	 * 			总保额		ttl_amt<br/>
	 * 			产品组		product_grp->(待确认)<br/>
	 * 			归属渠道		bln_chnl->(待确认)<br/>
	 * 			归属机构		bln_orgn->(待确认)<br/>
	 * 			二级服务机构名称		sec_cls_serve_orgnam<br/>
	 * 			契约代理人标识		inden_agent_flg->(待确认)<br/>
	 * 			保单收费地址		order_pay_addr<br/>
	 * 			业务系统契约号		sys_inden_no<br/>
	 * 			业务系统标识		sys_flg->(待确认)<br/>
	 * 			是否为ECIF系统管理的账户		wht_ecif_ac_flg<br/>
	 * 			契约名称		inden_nam<br/>
	 * 			别名		alias_nam<br/>
	 * 			签约时间		sign_tm<br/>
	 * 			执行时间		excute_tm<br/>
	 * 			结束时间		end_tm<br/>
	 * 			取代契约标识		inst_inden_flg->(待确认)<br/>
	 * 			交易最后执行时间		trans_last_excute_tm<br/>
	 * 			契约终止时间		inden_end_tm<br/>
	 * 			契约终止原因		inden_end_reason<br/>
	 * 			契约备注		inden_note<br/>
	 * 			契约状态		inden_sts->(待确认)<br/>
	 * 			契约类型		inden_typ->(待确认)<br/>
	 * 			服务级别		serve_lvl<br/>
	 * 			契约最后核实时间		inden_last_chk_tm<br/>
	 * 			契约最后保单承保日期		inden_final_accept_date<br/>
	 * 			产品标识		product_flg->(待确认)<br/>
	 * 			索引列		index_column->(待确认)<br/>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/custProposalList", method = RequestMethod.GET)
	public Result getCustProposalList(EcCustProposal ecCustProposal) {
		Result res = new Result();
		res.setData(custProposalService.getCustProposalList(ecCustProposal));
		return res;
	}

}
