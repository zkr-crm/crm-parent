package cn.com.zsyk.crm.ecif.web.controller.customer.portrait;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import cn.com.zsyk.crm.common.annotation.SysOprtLog;
import cn.com.zsyk.crm.common.annotation.SysOprtLog.Module;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.ecif.entity.EcContactAddr;
import cn.com.zsyk.crm.ecif.entity.EcContactWay;
import cn.com.zsyk.crm.ecif.service.customer.portrait.CustContactService;

@RestController
public class CustContactCtrl {

	@Autowired
	private CustContactService contactService;
	/**
	 * @api {GET} /crm/ecif/cust/contactWayList 查询客户联系方式列表
	 * @apiDescription 
	 * @apiName getContactWayList
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcContactWay} ecContactWay 联系方式bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			客户号		cust_no<br/>
	 * 			序号		contact_sqn<br/>
	 * 			联系人姓名		contact_nam<br/>
	 * 			手机号码1		phone_no1<br/>
	 * 			手机号码2		phone_no2<br/>
	 * 			手机号码3		phone_no3<br/>
	 * 			固定电话1		telephone1<br/>
	 * 			固定电话2		telephone2<br/>
	 * 			微信号码		wechat_no<br/>
	 * 			邮箱地址		email_addr<br/>
	 * 			传真		fax<br/>
	 * 			是否为默认方式		wht_default_way->YesNoFlg<br/>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/contactWayList", method = RequestMethod.GET)
	public Result getContactWayList(EcContactWay ecContactWay) {
		Result res = new Result();
		res.setData(contactService.getContactWayList(ecContactWay));
		return res;
	}

	/**
	 * @api {GET} /crm/ecif/cust/contactWayOne 查询客户联系方式(单条)
	 * @apiDescription 
	 * @apiName getContactWayOne
	 * @apiGroup Customer
	 * 
	 * @apiParam {String} custNo 客户号
	 * @apiParam {String} contactSqn 序号
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			客户号		cust_no<br/>
	 * 			序号		contact_sqn<br/>
	 * 			联系人姓名		contact_nam<br/>
	 * 			手机号码1		phone_no1<br/>
	 * 			手机号码2		phone_no2<br/>
	 * 			手机号码3		phone_no3<br/>
	 * 			固定电话1		telephone1<br/>
	 * 			固定电话2		telephone2<br/>
	 * 			微信号码		wechat_no<br/>
	 * 			邮箱地址		email_addr<br/>
	 * 			传真		fax<br/>
	 * 			是否为默认方式		wht_default_way->YesNoFlg<br/>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/contactWayOne", method = RequestMethod.GET)
	public Result getContactWayOne(String custNo, Integer contactSqn) {
		Result res = new Result();
		res.setData(contactService.getContactWayOne(custNo, contactSqn));
		return res;
	}

	
	/**
	 * @api {PUT} /crm/ecif/cust/addContactWay 新增联系方式
	 * @apiDescription 
	 * @apiName addContactWay
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcContactWay} ecContactWay 客户事件Bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/addContactWay", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.CUSTOMER, bizDesc="新增联系方式")
	public Result addContactWay(EcContactWay ecContactWay) {
		Result res = new Result();
		try {
			contactService.addContactWay(ecContactWay);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("新增客户联系方式信息失败");
		}

		return res;
	}

	/**
	 * @api {PUT} /crm/ecif/cust/uptContactWay 修改联系方式
	 * @apiDescription 
	 * @apiName uptContactWay
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcContactWay} ecContactWay 客户事件Bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/uptContactWay", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.CUSTOMER, bizDesc="修改联系方式")
	public Result uptContactWay(EcContactWay ecContactWay) {
		Result res = new Result();
		try {
			contactService.uptContactWay(ecContactWay);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("修改客户联系方式信息失败");
		}
		return res;
	}

	/**
	 * @api {PUT} /crm/ecif/cust/delContactWay 删除联系方式
	 * @apiDescription 
	 * @apiName delContactWay
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcContactWay} ecContactWay 客户事件Bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/delContactWay", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.CUSTOMER, bizDesc="删除联系方式")
	public Result delContactWay(EcContactWay ecContactWay) {
		Result res = new Result();
		try {
			contactService.delContactWay(ecContactWay);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("删除客户联系方式信息失败");
		}
		return res;
	}

	//--------------------------
	
	/**
	 * @api {GET} /crm/ecif/cust/contactAddrList 查询客户联系地址列表
	 * @apiDescription 
	 * @apiName getContactAddrList
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcContactAddr} ecContactAddr 联系地址bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			客户号			cust_no<br/>
	 * 			国家代码		country_cd->Nationality<br/>
	 * 			省份代码		prov_cdr->(码值)<br/>
	 * 			市代码			city_cdr->(码值)<br/>
	 * 			区县代码		county_cd->(码值)<br/>
	 * 			街道地址		street_addr<br/>
	 * 			邮编				postcode<br/>
	 * 			地址类型		addr_typ->AddrTyp<br/>
	 * 			是否为默认地址		wht_default_addr->YesNoFlg<br/>
	 * 			详细地址		det_addr<br/>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/contactAddrList", method = RequestMethod.GET)
	public Result getContactAddrList(EcContactAddr ecContactAddr) {
		Result res = new Result();
		res.setData(contactService.getContactAddrList(ecContactAddr));
		return res;
	}
	
	/**
	 * @api {GET} /crm/ecif/cust/contactAddrOne 查询客户联系地址列表
	 * @apiDescription 
	 * @apiName getContactAddrOne
	 * @apiGroup Customer
	 * 
	 * @apiParam {String} custNo 客户号
	 * @apiParam {String} addrTyp 地址类型
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			客户号			cust_no<br/>
	 * 			国家代码		country_cd->Nationality<br/>
	 * 			省份代码		prov_cdr->(码值)<br/>
	 * 			市代码			city_cdr->(码值)<br/>
	 * 			区县代码		county_cd->(码值)<br/>
	 * 			街道地址		street_addr<br/>
	 * 			邮编				postcode<br/>
	 * 			地址类型		addr_typ->AddrTyp<br/>
	 * 			是否为默认地址		wht_default_addr->YesNoFlg<br/>
	 * 			详细地址		det_addr<br/>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/contactAddrOne", method = RequestMethod.GET)
	public Result getContactAddrOne(String custNo, String addrTyp) {
		Result res = new Result();
		res.setData(contactService.getContactAddrOne(custNo, addrTyp));
		return res;
	}
}
