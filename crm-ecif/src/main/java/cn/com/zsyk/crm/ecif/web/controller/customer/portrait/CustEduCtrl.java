package cn.com.zsyk.crm.ecif.web.controller.customer.portrait;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import cn.com.zsyk.crm.common.annotation.SysOprtLog;
import cn.com.zsyk.crm.common.annotation.SysOprtLog.Module;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.ecif.entity.EcCustEdu;
import cn.com.zsyk.crm.ecif.service.customer.portrait.CustEduService;

@RestController
public class CustEduCtrl {

	@Autowired
	private CustEduService custEduService;
	/**
	 * @api {GET} /crm/ecif/cust/custEduList 查询客户教育列表
	 * @apiDescription 
	 * @apiName getCustEduList
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcCustEdu} ecCustEdu 教育信息bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			客户号		cust_no<br/>
	 * 			学校名称		school_nam<br/>
	 * 			学历代码		edu_cd<br/>
	 * 			学位代码		degree_cd<br/>
	 * 			专业1		speciality1<br/>
	 * 			专业2		speciality2<br/>
	 * 			专业3		speciality3<br/>
	 * 			入学日期		entrance_date<br/>
	 * 			毕业日期		graduate_date<br/>
	 * 			教育类型		edu_typ->Degree<br/>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/custEduList", method = RequestMethod.GET)
	public Result getCustEduList(EcCustEdu ecCustEdu) {
		Result res = new Result();
		res.setData(custEduService.getCustEduList(ecCustEdu));
		return res;
	}
	
	/**
	 * @api {GET} /crm/ecif/cust/custEduOne 查询客户教育(单条)
	 * @apiDescription 
	 * @apiName getCustEduOne
	 * @apiGroup Customer
	 * 
	 * @apiParam {String} custNo 客户号
	 * @apiParam {String} eduTyp 教育类型
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			客户号		cust_no<br/>
	 * 			学校名称		school_nam<br/>
	 * 			学历代码		edu_cd<br/>
	 * 			学位代码		degree_cd<br/>
	 * 			专业1		speciality1<br/>
	 * 			专业2		speciality2<br/>
	 * 			专业3		speciality3<br/>
	 * 			入学日期		entrance_date<br/>
	 * 			毕业日期		graduate_date<br/>
	 * 			教育类型		edu_typ->Degree<br/>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/custEduOne", method = RequestMethod.GET)
	public Result getCustEduOne(String custNo, String eduTyp) {
		Result res = new Result();
		res.setData(custEduService.getCustEduOne(custNo, eduTyp));
		return res;
	}
	

	/**
	 * @api {PUT} /crm/ecif/cust/addCustEdu 新增教育
	 * @apiDescription 
	 * @apiName addCustEdu
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcCustEdu} ecCustEdu 客户教育Bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/addCustEdu", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.CUSTOMER, bizDesc="新增客户教育信息")
	public Result addCustEdu(EcCustEdu ecCustEdu) {
		Result res = new Result();
		try {
			custEduService.addCustEdu(ecCustEdu);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("新增客户教育信息失败");
		}
		return res;
	}

	/**
	 * @api {PUT} /crm/ecif/cust/uptCustEdu 修改教育
	 * @apiDescription 
	 * @apiName uptCustEdu
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcCustEdu} ecCustEdu 客户教育Bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/uptCustEdu", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.CUSTOMER, bizDesc="修改客户教育信息")
	public Result uptCustEdu(EcCustEdu ecCustEdu) {
		Result res = new Result();
		try {
			custEduService.uptCustEdu(ecCustEdu);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("修改客户教育信息失败");
		}
		return res;
	}

	/**
	 * @api {PUT} /crm/ecif/cust/delCustEdu 删除教育
	 * @apiDescription 
	 * @apiName delCustEdu
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcCustEdu} ecCustEdu 客户教育Bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/delCustEdu", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.CUSTOMER, bizDesc="删除客户教育信息")
	public Result delCustEdu(EcCustEdu ecCustEdu) {
		Result res = new Result();
		try {
			custEduService.delCustEdu(ecCustEdu);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("删除客户教育信息失败");
		}
		return res;
	}
}
