package cn.com.zsyk.crm.ecif.web.controller.intf.percust;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.ecif.entity.EcCustNoCreateParm;
import cn.com.zsyk.crm.ecif.service.intf.percust.PerCustCreateService;

@RestController
public class PerCustCreateCtrl {
	@Autowired
	private PerCustCreateService perCustCreateService;
	
	/**
	 * 创建个人客户
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ecif/cust/createnewcustomer", method = RequestMethod.POST)
	public Result createnewcustomer(EcCustNoCreateParm parm) {
		Result res = new Result();
		System.out.println("");
		
		String custNo = perCustCreateService.createCustNoBy(parm,true);
		res.setData(custNo);
		return res;
	}
	/**
	 * 相同个人客户判断
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ecif/cust/samecustomer", method = RequestMethod.POST)
	public Result isSameCustomer(EcCustNoCreateParm parm) {
		Result res = new Result();
		System.out.println("");
		
		String custNo = perCustCreateService.createCustNoBy(parm,false);
		res.setData(custNo);
		return res;
	}
	

}
