package cn.com.zsyk.crm.manage.web.controller.engine;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.manage.entity.SysRuleFactorParam;
import cn.com.zsyk.crm.manage.service.engine.FactorValueListService;

@RestController
public class FactorParamCtrl {
	
	@Autowired
	private FactorValueListService service;
	
	//查询列表
	@RequestMapping(path = "/crm/manage/getParamList", method = RequestMethod.GET)
	public Result getList() {
		Result res = new Result();
		List<SysRuleFactorParam> retLst = service.getAllRecRows();
		res.setData(retLst);
		return res;
	}
	
	//根据条件查询列表
	@RequestMapping(path = "/crm/manage/getParamListByEntity", method = RequestMethod.GET)
	public Result getListByEntity(SysRuleFactorParam param) {
		Result res = new Result();
		List<SysRuleFactorParam> retLst = service.getMultiRec(param);
		res.setData(retLst);
		return res;
	}
	
	//查询单条
	@RequestMapping(path = "/crm/manage/getParam", method = RequestMethod.GET)
	public Result getOne(SysRuleFactorParam param) {
		Result res = new Result();
		SysRuleFactorParam retLst = service.getOneRec(param);
		res.setData(retLst);
		return res;
	}
	
	//增加一条
	@RequestMapping(path = "/crm/manage/saveParam", method = RequestMethod.POST)
	public Result saveOne(SysRuleFactorParam param) {
		Result res = new Result();
		SysRuleFactorParam retLst = service.insertOne(param);
		res.setData(retLst);
		return res;
	}
	
	//修改一条
	@RequestMapping(path = "/crm/manage/updateParam", method = RequestMethod.PUT)
	public Result updateOne(SysRuleFactorParam param) {
		Result res = new Result();
		SysRuleFactorParam retLst = service.updateOne(param);
		res.setData(retLst);
		return res;
	}
	
	//删除一条
	@RequestMapping(path = "/crm/manage/delParam", method = RequestMethod.DELETE)
	public Result deleteOne(SysRuleFactorParam param) {
		Result res = new Result();
		service.deleteOne(param);
		return res;
	}
	
	//逻辑删除一条
	@RequestMapping(path = "/crm/manage/invalidParam", method = RequestMethod.PUT)
	public Result invalidOne(SysRuleFactorParam param) {
		Result res = new Result();
		service.invalidOne(param);
		return res;
	}
	
	
	
	

}
