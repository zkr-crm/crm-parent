package cn.com.zsyk.crm.ecif.web.controller.customer.portrait;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.ecif.entity.EcCustCareer;
import cn.com.zsyk.crm.ecif.service.customer.portrait.CustCareerService;

@RestController
public class CustCareerCtrl {

	@Autowired
	private CustCareerService custCareerService;
	/**
	 * @api {GET} /crm/ecif/cust/custCareerList 查询客户职业列表
	 * @apiDescription 
	 * @apiName getCustCareerList
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcCustCareer} ecCustCareer 职业信息bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			客户号			cust_no<br/>
	 * 			行业				trade->BusinessType<br/>
	 * 			单位性质		unit_nature->GrpNature<br/>
	 * 			单位名称		unit_nam<br/>
	 * 			部门				department->(输入)<br/>
	 * 			职务				headship->(输入)<br/>
	 * 			岗位				position->(输入)<br/>
	 * 			职级				post_grade->(输入)<br/>
	 * 			负责工作简述		work_desc<br/>
	 * 			入职日期		entry_date<br/>
	 * 			离职日期		leave_date<br/>
	 * 			工作性质		work_nature->WorkNature<br/>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/custCareerList", method = RequestMethod.GET)
	public Result getCustCareerList(EcCustCareer ecCustCareer) {
		Result res = new Result();
		res.setData(custCareerService.getCustCareerList(ecCustCareer));
		return res;
	}

	/**
	 * @api {GET} /crm/ecif/cust/custCareerOne 查询客户职业信息
	 * @apiDescription 
	 * @apiName getCustCareerOne
	 * @apiGroup Customer
	 * 
	 * @apiParam {String} custNo 客户号
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			客户号			cust_no<br/>
	 * 			行业				trade->BusinessType<br/>
	 * 			单位性质		unit_nature->GrpNature<br/>
	 * 			单位名称		unit_nam<br/>
	 * 			部门				department->(输入)<br/>
	 * 			职务				headship->(输入)<br/>
	 * 			岗位				position->(输入)<br/>
	 * 			职级				post_grade->(输入)<br/>
	 * 			负责工作简述		work_desc<br/>
	 * 			入职日期		entry_date<br/>
	 * 			离职日期		leave_date<br/>
	 * 			工作性质		work_nature->WorkNature<br/>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/custCareerOne", method = RequestMethod.GET)
	public Result getCustCareerOne(String custNo) {
		Result res = new Result();
		res.setData(custCareerService.getCustCareerOne(custNo));
		return res;
	}
}
