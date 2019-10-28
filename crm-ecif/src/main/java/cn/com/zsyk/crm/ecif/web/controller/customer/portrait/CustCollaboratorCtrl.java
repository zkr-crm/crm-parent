package cn.com.zsyk.crm.ecif.web.controller.customer.portrait;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cn.com.zsyk.crm.common.annotation.SysOprtLog;
import cn.com.zsyk.crm.common.annotation.SysOprtLog.Module;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.ecif.entity.EcCustCollaborator;
import cn.com.zsyk.crm.ecif.service.customer.portrait.CustCollaboratorService;

@RestController
public class CustCollaboratorCtrl {
	@Autowired
	CustCollaboratorService custCollaboratorService;

	/**
	 * @api {GET} /crm/ecif/cust/custCollaboratorList 查询客户协作人列表
	 * @apiName getCustCollaboratorList
	 * @apiGroup Customer
	 *
	 * @apiParam {EcCustCollaborator} ecCustCollaborator 客户协作人信息bean
	 * 
	 * @apiSuccess {Result} data 返回值对象
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ecif/cust/custCollaboratorList", method = RequestMethod.GET)
	public Result getCustCollaboratorList(EcCustCollaborator ecCustCollaborator) {
		Result res = new Result();
		res.setData(custCollaboratorService.getCustCollaboratorList(ecCustCollaborator));
		return res;
	}

	/**
	 * @api {GET} /crm/ecif/cust/custCollaboratorOne 查询客户协作人(单条)
	 * @apiDescription 
	 * @apiName getCustCollaboratorOne
	 * @apiGroup Customer
	 * 
	 * @apiParam {String} custNo 客户协作人编号
	 * @apiParam {String} contractCustNo 协作人编号
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/custCollaboratorOne", method = RequestMethod.GET)
	public Result getCustCollaboratorOne(String custNo, String contractCustNo) {
		Result res = new Result();
		res.setData(custCollaboratorService.getCustCollaboratorOne(custNo, contractCustNo));
		return res;
	}

	/**
	 * @api {PUT} /crm/ecif/cust/addCust 新增客户协作人
	 * @apiDescription 
	 * @apiName addCustCollaborator
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcCustCollaborator} ecCustCollaborator 客户协作人信息Bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/addCustCollaborator", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.CUSTOMER, bizDesc="新增客户协作人")
	public Result addCustCollaborator(@RequestParam(name = "custNo") String custNo, 
			@RequestParam(name = "userIdList",required = false) List userIdList) {
		Result res = new Result();
		try {
			custCollaboratorService.addCustCollaborator(custNo, userIdList);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("新增客户协作人信息失败");
		}
		return res;
	}

	/**
	 * @api {PUT} /crm/ecif/cust/uptCust 修改客户协作人
	 * @apiDescription 
	 * @apiName uptCustCollaborator
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcCustCollaborator} ecCustCollaborator 客户协作人信息Bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/uptCustCollaborator", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.CUSTOMER, bizDesc="修改客户协作人")
	public Result uptCustCollaborator(EcCustCollaborator ecCustCollaborator) {
		Result res = new Result();
		try {
			custCollaboratorService.uptCustCollaborator(ecCustCollaborator);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("修改客户协作人信息失败");
		}
		return res;
	}

	/**
	 * @api {PUT} /crm/ecif/cust/delCust 删除客户协作人
	 * @apiDescription 
	 * @apiName delCustCollaborator
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcCustCollaborator} ecCustCollaborator 客户协作人信息Bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/delCustCollaborator", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.CUSTOMER, bizDesc="删除客户协作人")
	public Result delCustCollaborator(EcCustCollaborator ecCustCollaborator) {
		Result res = new Result();
		try {
			custCollaboratorService.delCustCollaborator(ecCustCollaborator);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("删除客户协作人信息失败");
		}
		return res;
	}
}
