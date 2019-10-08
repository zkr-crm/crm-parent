package cn.com.zsyk.crm.ecif.web.controller.intf.entcust;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.ecif.entity.EcCustNoCreateParm;
import cn.com.zsyk.crm.ecif.service.intf.entcust.CorpCustCreateService;

@RestController
public class CorpCustCreateCtrl {

	@Autowired
	private CorpCustCreateService corpCustCreateService;
	
	/**
	 * 创建法人客户
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ecif/cust/enterprise/createnewcustomer", method = RequestMethod.POST)
	public Result createnewcustomer(EcCustNoCreateParm parm) {
		Result res = new Result();
		System.out.println("");
		
		String custNo = corpCustCreateService.createCustNoBy(parm,true);
		res.setData(custNo);
		return res;
	}
	/**
	 * 相同法人客户判断
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ecif/cust/enterprise/samecustomer", method = RequestMethod.POST)
	public Result isSameCustomer(EcCustNoCreateParm parm) {
		Result res = new Result();
		System.out.println("");
		
		String custNo = corpCustCreateService.createCustNoBy(parm,false);
		res.setData(custNo);
		return res;
	}
	



}
