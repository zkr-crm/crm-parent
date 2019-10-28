package cn.com.zsyk.crm.ecif.web.controller.customer.portrait;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.ecif.service.customer.portrait.CustBaseInfoService;

@RestController
public class CustBaseInfoCtrl {
	@Autowired
	private CustBaseInfoService custBaseInfoService;

	/**
	 * @throws Exception 
	 * @api {GET} /crm/ecif/cust/perCustBaseInfo 查询个人客户基本信息
	 * @apiDescription <br/> 
	 * @apiName getPerCustBaseInfo
	 * @apiGroup Customer
	 * 
	 * @apiParam {String} custNo 客户号
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/> 
	 * 			客户类型 	cust_typ->CustType<br/> 
	 * 			婚姻状况 	marrige_sts->Marriage<br/> 
	 * 			国籍 			nationality->Nationality<br/> 
	 * 			民族 			nation->Nation<br/> 
	 * 			文化程度 	edu_degree->Degree<br/> 
	 * 			可以证实年龄的证件标识 cnf_age_cert_flg->YesNoFlg<br/> 
	 * 			性别			sex->Sex<br/> 
	 * 			政治面貌	polit_sts->PoliticalStatus<br/> 
	 * 			黑名单标识		blacklist_flg->YesNoFlg<br/> 
	 * 			重点客户标识		key_cust_flg->YesNoFlg<br/> 
	 * 			可见范围		visible_range->VisibleRange<br/> 
	 * 			客户来源		cust_source->DataSource<br/> 
	 * 			姓名类型		nam_typ->NameType<br/>
	 * 			证件状态		cert_sts->ValidFlg<br/>
	 * 			行业		trade->BusinessType<br/>
	 * 			单位性质		unit_nature->GrpNature<br/>
	 * 			岗位		position<br/>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/perCustBaseInfo", method = RequestMethod.GET)
	public Result getPerCustBaseInfo(String custNo) throws Exception {
		Result res = new Result();
		res.setData(custBaseInfoService.selectPerCustBaseInfoByCustNo(custNo));
		return res;
	}

	/**
	 * @api {GET} /crm/ecif/cust/entCustBaseInfo 查询企业客户(单条)(N)
	 * @apiDescription 
	 * @apiName getEntCustBaseInfo
	 * @apiGroup Customer
	 * 
	 * @apiParam {String} custNo 客户号
	 *
	 * @apiSuccess {Object} Result 返回值对象
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/entCustBaseInfo", method = RequestMethod.GET)
	public Result getEntCustBaseInfo(String custNo) {
		Result res = new Result();
		res.setData(custBaseInfoService.selectEntCustBaseInfoByCustNo(custNo));
		return res;
	}
}
