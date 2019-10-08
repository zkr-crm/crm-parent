package cn.com.zsyk.crm.ecif.web.controller.customer.portrait;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import cn.com.zsyk.crm.common.annotation.SysOprtLog;
import cn.com.zsyk.crm.common.annotation.SysOprtLog.Module;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.ecif.entity.EcCustCert;
import cn.com.zsyk.crm.ecif.service.customer.portrait.CustCertService;

@RestController
public class CustCertCtrl {

	@Autowired
	private CustCertService custCertService;
	/**
	 * @api {GET} /crm/ecif/cust/custCertList 查询客户证件列表
	 * @apiDescription 
	 * @apiName getCustCertList
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcCustCert} ecCustCert 证件信息bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			客户号		cust_no<br/>
	 * 			证件类型  certTyp->IdType/EntIDType 个人证件使用不同枚举<br/>
	 * 			证件号码		cert_no<br/>
	 * 			激活日期		acti_date<br/>
	 * 			停用日期		stop_use_date<br/>
	 * 			证件有效期		cert_eff_date<br/>
	 * 			描述		desc_content<br/>
	 * 			证件发放地		cert_issued_plece<br/>
	 * 			源系统标识符		orig_sys_flg->(待确认)<br/>
	 * 			证件状态		cert_sts->ValidFlg<br/>

	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/custCertList", method = RequestMethod.GET)
	public Result getCustCertList(EcCustCert ecCustCert) {
		Result res = new Result();
		res.setData(custCertService.selectCustCertList(ecCustCert));
		return res;
	}

	/**
	 * @api {GET} /crm/ecif/cust/custCertOne 查询客户证件(单条)
	 * @apiDescription 
	 * @apiName getCustCertOne
	 * @apiGroup Customer
	 * 
	 * @apiParam {String} custNo 客户号
	 * @apiParam {int} certSqn 证件序号(0代表首条，1，2，3)
	 *
	 * @apiSuccess {Object} Result 返回值对象
	 * 			客户号		cust_no<br/>
	 * 			证件类型  certTyp->IdType/EntIDType 个人证件使用不同枚举<br/>
	 * 			证件号码		cert_no<br/>
	 * 			激活日期		acti_date<br/>
	 * 			停用日期		stop_use_date<br/>
	 * 			证件有效期		cert_eff_date<br/>
	 * 			描述		desc_content<br/>
	 * 			证件发放地		cert_issued_plece<br/>
	 * 			源系统标识符		orig_sys_flg->(待确认)<br/>
	 * 			证件状态		cert_sts->ValidFlg<br/>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/custCertOne", method = RequestMethod.GET)
	public Result getCustCertOne(String custNo, int certSqn) {
		Result res = new Result();
		res.setData(custCertService.selectCustCertOne(custNo, certSqn));
		return res;
	}

	/**
	 * @api {PUT} /crm/ecif/cust/addCustCert 新增证件
	 * @apiDescription 
	 * @apiName addCustCert
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcCustCert} ecCustCert 客户事件Bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/addCustCert", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.CUSTOMER, bizDesc="新增证件")
	public Result addCustCert(EcCustCert ecCustCert) {
		Result res = new Result();
		try {
			custCertService.addCustCert(ecCustCert);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("新增客户证件信息失败");
		}
		return res;
	}

	/**
	 * @api {PUT} /crm/ecif/cust/uptCustCert 修改证件
	 * @apiDescription 
	 * @apiName uptCustCert
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcCustCert} ecCustCert 客户事件Bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/uptCustCert", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.CUSTOMER, bizDesc="修改证件信息")
	public Result uptCustCert(EcCustCert ecCustCert) {
		Result res = new Result();
		try {
			custCertService.uptCustCert(ecCustCert);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("修改客户证件信息失败");
		}
		return res;
	}

	/**
	 * @api {PUT} /crm/ecif/cust/delCustCert 删除证件
	 * @apiDescription 
	 * @apiName delCustCert
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcCustCert} ecCustCert 客户事件Bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/delCustCert", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.CUSTOMER, bizDesc="删除证件信息")
	public Result delCustCert(EcCustCert ecCustCert) {
		Result res = new Result();
		try {
			custCertService.delCustCert(ecCustCert);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("删除客户证件信息失败");
		}
		return res;
	}
}
