package cn.com.zsyk.crm.ecif.web.controller.customer.key;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.zsyk.crm.common.annotation.SysOprtLog;
import cn.com.zsyk.crm.common.annotation.SysOprtLog.Module;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.ecif.entity.EcCustPer;
import cn.com.zsyk.crm.ecif.service.customer.key.KeyCustService;

@RestController
public class KeyCustCtrl {

	@Autowired
	private KeyCustService keyCustService;
	
	/**
	 * @api {GET} /crm/ecif/cust/keyCustList 查询重点客户列表(N)
	 * @apiDescription 
	 * @apiName getAllkeyCust
	 * @apiGroup Customer
	 *
	 * @apiSuccess {Object} Response 返回值对象
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(path = "/crm/ecif/cust/keyCustList", method = RequestMethod.GET)
	public Result getKeyCustList() {
		Result res = new Result();
		res.setData(keyCustService.getKeyCustList());
		return res;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ecif/cust/notImportantCustList", method = RequestMethod.GET)
	public Result selectNotImportantPerCustList(EcCustPer custper) {
		Result res = new Result();
		res.setData(keyCustService.selectNotImportantPerCustList(custper));
		return res;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ecif/cust/importantCustList", method = RequestMethod.GET)
	public Result selectImportantPerCustList(EcCustPer custper) {
		Result res = new Result();
		res.setData(keyCustService.selectImportantPerCustList(custper));
		return res;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ecif/cust/importantCustomer/setkey", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.CUSTOMER, bizDesc="设置客户重点客户")
	public Result cancelBlacklist(String custNo, String blacklistSts, String reason) {
		Result res = new Result();
		keyCustService.setKeyCustomer(custNo, blacklistSts, reason);
		res.setData(true);
		return res;
	}
}
