package cn.com.zsyk.crm.manage.web.controller.engine;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.manage.entity.SysRuleFactorOperType;
import cn.com.zsyk.crm.manage.service.engine.FactorOperTypeService;

@RestController
public class FactorOperTypeCtrl {
	
	@Autowired
	private FactorOperTypeService service;
	
	//查询列表
	@RequestMapping(path = "/crm/manage/getOperList", method = RequestMethod.GET)
	public Result getList() {
		Result res = new Result();
		List<SysRuleFactorOperType> retLst = service.getAllRecRows();
		res.setData(retLst);
		return res;
	}
	
	//根据条件查询列表
	@RequestMapping(path = "/crm/manage/getOperListByEntity", method = RequestMethod.GET)
	public Result getListByEntity(SysRuleFactorOperType param) {
		Result res = new Result();
		List<SysRuleFactorOperType> retLst = service.getMultiRec(param);
		res.setData(retLst);
		return res;
	}
	
	//查询单条
	@RequestMapping(path = "/crm/manage/getOper", method = RequestMethod.GET)
	public Result getOne(SysRuleFactorOperType param) {
		Result res = new Result();
		SysRuleFactorOperType retLst = service.getOneRec(param);
		res.setData(retLst);
		return res;
	}
	
	//增加一条
	@RequestMapping(path = "/crm/manage/saveOper", method = RequestMethod.POST)
	public Result saveOne(SysRuleFactorOperType param) {
		Result res = new Result();
		SysRuleFactorOperType retLst = service.insertOne(param);
		res.setData(retLst);
		return res;
	}
	
	//修改一条
	@RequestMapping(path = "/crm/manage/updateOper", method = RequestMethod.PUT)
	public Result updateOne(SysRuleFactorOperType param) {
		Result res = new Result();
		SysRuleFactorOperType retLst = service.updateOne(param);
		res.setData(retLst);
		return res;
	}
	
	//删除一条
	@RequestMapping(path = "/crm/manage/delOper", method = RequestMethod.DELETE)
	public Result deleteOne(SysRuleFactorOperType param) {
		Result res = new Result();
		service.deleteOne(param);
		return res;
	}
	
	//逻辑删除一条
	@RequestMapping(path = "/crm/manage/invalidOper", method = RequestMethod.PUT)
	public Result invalidOne(SysRuleFactorOperType param) {
		Result res = new Result();
		service.invalidOne(param);
		return res;
	}
	
	
	
	

}
