package cn.com.zsyk.crm.ecif.web.controller.customer.portrait;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.ecif.entity.EcCustEvent;
import cn.com.zsyk.crm.ecif.service.customer.portrait.CustEventService;

@RestController
public class CustEventCtrl {

	@Autowired
	private CustEventService custEventService;

	/**
	 * @api {GET} /crm/ecif/cust/custEventList 查询客户事件列表
	 * @apiDescription 
	 * @apiName getCustEventList
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcCustEvent} ecCustEvent 客户事件Bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			客户号			cust_no<br/>
	 * 			事件类型		event_type->EventType<br/>
	 * 			事件日期		event_date<br/>
	 * 			事件描述		event_desc<br/>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/custEventList", method = RequestMethod.GET)
	public Result getCustEventList(EcCustEvent ecCustEvent) {
		Result res = new Result();
		res.setData(custEventService.selectCustEventList(ecCustEvent));
		return res;
	}

	/**
	 * @api {GET} /crm/ecif/cust/custEventOne 查询客户事件(单条)
	 * @apiDescription 
	 * @apiName getCustEventOne
	 * @apiGroup Customer
	 * 
	 * @apiParam {String} custNo 客户号
	 * @apiParam {String} eventType 事件类型
	 * @apiParam {String} eventDate 事件日期
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			客户号			cust_no<br/>
	 * 			事件类型		event_type->EventType<br/>
	 * 			事件日期		event_date<br/>
	 * 			事件描述		event_desc<br/>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/custEventOne", method = RequestMethod.GET)
	public Result getCustEventOne(String custNo, String eventType, String eventDate) {
		Result res = new Result();
		res.setData(custEventService.selectCustEventOne(custNo, eventType, eventDate));
		return res;
	}

	/**
	 * @api {PUT} /crm/ecif/cust/addCustEvent 新增客户事件
	 * @apiDescription 
	 * @apiName addCustEvent
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcCustEvent} ecCustEvent 客户事件Bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/addCustEvent", method = RequestMethod.PUT)
	public Result addCustEvent(EcCustEvent ecCustEvent) {
		Result res = new Result();
		try {
			custEventService.addCustEvent(ecCustEvent);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("新增客户事件信息失败");
		}
		return res;
	}

	/**
	 * @api {PUT} /crm/ecif/cust/uptCustEvent 修改客户事件
	 * @apiDescription 
	 * @apiName uptCustEvent
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcCustEvent} ecCustEvent 客户事件Bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/uptCustEvent", method = RequestMethod.PUT)
	public Result uptCustEvent(EcCustEvent ecCustEvent) {
		Result res = new Result();
		try {
			custEventService.uptCustEvent(ecCustEvent);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("修改客户事件信息失败");
		}
		return res;
	}

	/**
	 * @api {PUT} /crm/ecif/cust/delCustEvent 删除客户事件
	 * @apiDescription 
	 * @apiName delCustEvent
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcCustEvent} ecCustEvent 客户事件Bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/delCustEvent", method = RequestMethod.PUT)
	public Result delCustEvent(EcCustEvent ecCustEvent) {
		Result res = new Result();
		try {
			custEventService.delCustEvent(ecCustEvent);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("删除客户事件信息失败");
		}
		return res;
	}
}
