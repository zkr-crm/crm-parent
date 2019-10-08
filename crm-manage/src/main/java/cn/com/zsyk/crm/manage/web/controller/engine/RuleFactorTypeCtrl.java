package cn.com.zsyk.crm.manage.web.controller.engine;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.manage.entity.SysRuleFactorType;
import cn.com.zsyk.crm.manage.service.engine.RuleFactorTypeService;

@RestController
public class RuleFactorTypeCtrl {
	
	@Autowired
	private RuleFactorTypeService service;
	
	//查询列表
	@RequestMapping(path = "/crm/manage/getTypeList", method = RequestMethod.GET)
	public Result getList() {
		Result res = new Result();
		List<SysRuleFactorType> retLst = service.findAllRec();
		res.setData(retLst);
		return res;
	}
	
	//根据条件查询列表
	@RequestMapping(path = "/crm/manage/getTypeListByEntity", method = RequestMethod.GET)
	public Result getListByEntity(SysRuleFactorType param) {
		Result res = new Result();
		List<SysRuleFactorType> retLst = service.findMultyRecByEntity(param);
		res.setData(retLst);
		return res;
	}
	
	//查询单条
	@RequestMapping(path = "/crm/manage/getType", method = RequestMethod.GET)
	public Result getOne(SysRuleFactorType param) {
		Result res = new Result();
		SysRuleFactorType retLst = service.findOneRec(param);
		res.setData(retLst);
		return res;
	}
	
	//增加一条
	@RequestMapping(path = "/crm/manage/saveType", method = RequestMethod.POST)
	public Result saveOne(SysRuleFactorType param) {
		Result res = new Result();
		SysRuleFactorType retLst = service.insertOne(param);
		res.setData(retLst);
		return res;
	}
	
	//修改一条
	@RequestMapping(path = "/crm/manage/updateType", method = RequestMethod.PUT)
	public Result updateOne(SysRuleFactorType param) {
		Result res = new Result();
		SysRuleFactorType retLst = service.updateOne(param);
		res.setData(retLst);
		return res;
	}
	
	//删除一条
	@RequestMapping(path = "/crm/manage/delType", method = RequestMethod.DELETE)
	public Result deleteOne(SysRuleFactorType param) {
		Result res = new Result();
		service.deleteOne(param);
		return res;
	}
	
	//逻辑删除一条
	@RequestMapping(path = "/crm/manage/invalidType", method = RequestMethod.PUT)
	public Result invalidOne(SysRuleFactorType param) {
		Result res = new Result();
		service.invalidOne(param);
		return res;
	}

}
