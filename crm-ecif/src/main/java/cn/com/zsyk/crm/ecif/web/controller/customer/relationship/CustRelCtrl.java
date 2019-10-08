package cn.com.zsyk.crm.ecif.web.controller.customer.relationship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import cn.com.zsyk.crm.common.annotation.SysOprtLog;
import cn.com.zsyk.crm.common.annotation.SysOprtLog.Module;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.ecif.entity.EcCustRelation;
import cn.com.zsyk.crm.ecif.service.customer.relationship.CustRelService;

@RestController
public class CustRelCtrl {

	@Autowired
	CustRelService custRelService;

	/**
	 * @api {GET} /crm/ecif/cust/custRelList 查询客户关系列表
	 * @apiDescription 
	 * @apiName getCustRelList
	 * @apiGroup Customer
	 *
	 * @apiParam {EcCustRelation} ecCustRelation 客户关系类型
	 * 
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			客户号		cust_no<br/>
	 * 			关系类型		relation_typ->Relation<br/>
	 * 			关联客户号		ref_cust_no<br/>
	 * 			关系描述		relation_desc<br/>
	 * 			有效日期		eff_date<br/>
	 * 			失效日期		exp_date<br/>
	 * 			关系结束原因		rel_end_reason<br/>
	 * 			关联客户号类型		ref_cust_no_typ->RefCustNoType<br/>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ecif/cust/custRelList", method = RequestMethod.GET)
	public Result getCustRelList(EcCustRelation ecCustRelation) {
		Result res = new Result();
		res.setData(custRelService.getCustRelList(ecCustRelation));
		return res;
	}

	/**
	 * @api {GET} /crm/ecif/cust/custRelOne 查询客户关系(单条)
	 * @apiDescription 
	 * @apiName getCustRel
	 * @apiGroup Customer
	 *
	 * @apiParam {String} custNo 客户号
	 * @apiParam {String} relationTyp 关系类型 Relation
	 * @apiParam {String} refCustNo 关联客户号
	 * 
	 * @apiSuccess {Object} Result 返回值对象
	 * 			客户号		cust_no<br/>
	 * 			关系类型		relation_typ->Relation<br/>
	 * 			关联客户号		ref_cust_no<br/>
	 * 			关系描述		relation_desc<br/>
	 * 			有效日期		eff_date<br/>
	 * 			失效日期		exp_date<br/>
	 * 			关系结束原因		rel_end_reason<br/>
	 * 			关联客户号类型		ref_cust_no_typ->RefCustNoType<br/>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ecif/cust/getCustRel", method = RequestMethod.GET)
	public Result getCustRel(String custNo, String relationTyp, String refCustNo) {
		Result res = new Result();
		res.setData(custRelService.getCustRelOne(custNo, relationTyp, refCustNo));
		return res;
	}

	
	/**
	 * @api {PUT} /crm/ecif/cust/addCustRel 新增客户关系
	 * @apiDescription 
	 * @apiName addCustRel
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcCustRelation} ecCustRelation 客户关系Bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/addCustRel", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.CUSTOMER, bizDesc="新增客户关系")
	public Result addCustRel(EcCustRelation ecCustRelation) {
		Result res = new Result();
		try {
			custRelService.addCustRel(ecCustRelation);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("新增客户关系信息失败");
		}
		return res;
	}

	
	/**
	 * @api {PUT} /crm/ecif/cust/uptCustRel 修改客户关系
	 * @apiDescription 
	 * @apiName uptCustRel
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcCustRelation} ecCustRelation 客户关系Bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/uptCustRel", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.CUSTOMER, bizDesc="修改客户关系")
	public Result uptCustRel(EcCustRelation ecCustRelation) {
		Result res = new Result();
		try {
			custRelService.uptCustRel(ecCustRelation);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("修改客户关系信息失败");
		}
		return res;
	}

	/**
	 * @api {PUT} /crm/ecif/cust/delCustRel 删除客户关系
	 * @apiDescription 
	 * @apiName delCustRel
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcCustRelation} ecCustRelation 客户关系Bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/delCustRel", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.CUSTOMER, bizDesc="删除客户关系")
	public Result delCustRel(EcCustRelation ecCustRelation) {
		Result res = new Result();
		try {
			custRelService.delCustRel(ecCustRelation);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("删除客户关系信息失败");
		}
		return res;
	}
}
