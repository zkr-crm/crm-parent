package cn.com.zsyk.crm.manage.web.controller.engine;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.manage.entity.SysRuleFactorValueType;
import cn.com.zsyk.crm.manage.service.engine.FactorValueTypeService;

@RestController
public class FactorValueTypeCtrl {
	
	@Autowired
	private FactorValueTypeService service;
	
	//查询列表
	@RequestMapping(path = "/crm/manage/getValueList", method = RequestMethod.GET)
	public Result getList() {
		Result res = new Result();
		List<SysRuleFactorValueType> retLst = service.getAllRecRows();
		res.setData(retLst);
		return res;
	}
	
	//根据条件查询列表
	@RequestMapping(path = "/crm/manage/getValueListByEntity", method = RequestMethod.GET)
	public Result getListByEntity(SysRuleFactorValueType param) {
		Result res = new Result();
		List<SysRuleFactorValueType> retLst = service.getMultiRec(param);
		res.setData(retLst);
		return res;
	}
	
	//查询单条
	@RequestMapping(path = "/crm/manage/getValue", method = RequestMethod.GET)
	public Result getOne(SysRuleFactorValueType param) {
		Result res = new Result();
		SysRuleFactorValueType retLst = service.getOneRec(param);
		res.setData(retLst);
		return res;
	}
	
	//增加一条
	@RequestMapping(path = "/crm/manage/saveValue", method = RequestMethod.POST)
	public Result saveOne(SysRuleFactorValueType param) {
		Result res = new Result();
		SysRuleFactorValueType retLst = service.insertOne(param);
		res.setData(retLst);
		return res;
	}
	
	//修改一条
	@RequestMapping(path = "/crm/manage/updateValue", method = RequestMethod.PUT)
	public Result updateOne(SysRuleFactorValueType param) {
		Result res = new Result();
		SysRuleFactorValueType retLst = service.updateOne(param);
		res.setData(retLst);
		return res;
	}
	
	//删除一条
	@RequestMapping(path = "/crm/manage/delValue", method = RequestMethod.DELETE)
	public Result deleteOne(SysRuleFactorValueType param) {
		Result res = new Result();
		service.deleteOne(param);
		return res;
	}
	
	//逻辑删除一条
	@RequestMapping(path = "/crm/manage/invalidValue", method = RequestMethod.PUT)
	public Result invalidOne(SysRuleFactorValueType param) {
		Result res = new Result();
		service.invalidOne(param);
		return res;
	}
	
	
	
	

}
