package cn.com.zsyk.crm.manage.web.controller.engine;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.manage.entity.SysRuleFactor;
import cn.com.zsyk.crm.manage.entity.SysRuleFactorOperType;
import cn.com.zsyk.crm.manage.entity.SysRuleFactorParam;
import cn.com.zsyk.crm.manage.entity.SysRuleFactorValueType;
import cn.com.zsyk.crm.manage.mapper.SysRuleFactorOperTypeMapper;
import cn.com.zsyk.crm.manage.service.engine.FactorOperTypeService;
import cn.com.zsyk.crm.manage.service.engine.FactorValueTypeService;
import cn.com.zsyk.crm.manage.service.engine.RuleFactorService;

@RestController
public class RuleFactorController{

    @Autowired
    private RuleFactorService ruleFactorService;



    @Autowired
    private  FactorOperTypeService operTypeService;
    @Autowired
    private  FactorValueTypeService valueTypeService;
//    private static Map FACTOR_OPERATE_TYPE = new HashMap();
//    private static Map FACTOR_VALUE_TYPE = new HashMap();
//    {
//    	FactorOperTypeService innerOperService = new FactorOperTypeService();
//    	FactorValueTypeService innerValueService = new FactorValueTypeService();
//    	List<SysRuleFactorOperType> operTypes = innerOperService.getAllRecRows();
//    	for( SysRuleFactorOperType operInfo :  operTypes   ) {
//    		FACTOR_OPERATE_TYPE.put(operInfo.getOperateType(),operInfo.getDesc());
//    	}
//    	
//    	List<SysRuleFactorValueType> valueTypes = innerValueService.getAllRecRows();
//    	for( SysRuleFactorValueType valueInfo :  valueTypes   ) {
//    		FACTOR_VALUE_TYPE.put(valueInfo.getValueType(),valueInfo.getDesc());
//    	}
    
//        FACTOR_OPERATE_TYPE.put("LOGICAL","属于、不属于");
//        FACTOR_OPERATE_TYPE.put("COMPARISON",">、<、>=、<=、==、!=");
//        FACTOR_OPERATE_TYPE.put("EQUALNOT","==、!=");
//        FACTOR_OPERATE_TYPE.put("CONTAINS","包含、不包含");
//        FACTOR_OPERATE_TYPE.put("MEMBEROF","集合于、不集合于");
//        FACTOR_OPERATE_TYPE.put("MATCHES","匹配、不匹配");
//        FACTOR_VALUE_TYPE.put("SELECT","枚举");
//        FACTOR_VALUE_TYPE.put("TEXT","输入值");
//    }

    /**
     * 根据参数，获取所有因子
     * @param ruleFactor
     * @return
     */
	@RequestMapping(path = "/crm/manage/getAllFactorByEntity", method = RequestMethod.GET)
    public Result getAllByEntity(SysRuleFactor ruleFactor){
		Result res = new Result();
        if(!StringUtils.isEmpty(ruleFactor.getFactorName())){
            ruleFactor.setFactorName(ruleFactor.getFactorName().trim());
        }
        if(!StringUtils.isEmpty(ruleFactor.getDisplayName())){
            ruleFactor.setDisplayName(ruleFactor.getDisplayName().trim());
        }
        
        List<SysRuleFactor> retList = ruleFactorService.findMultyRecByEntity(ruleFactor);
        
        res.setData(retList);
        
        return  res;
    }

    /**
     * 获取因子列表
     * @return
     */
	@RequestMapping(path = "/crm/manage/getAllFactors", method = RequestMethod.GET)
    public Result getAll(){
		Result res = new Result();
		List<SysRuleFactor> retList = ruleFactorService.findAll();
		res.setData(retList);
        return  res;
    }

    /**
     * 获取所有因子
     */
	@RequestMapping(path = "/crm/manage/getAllFactorsForTree", method = RequestMethod.GET)
	//TODO 具体需求待定，了解页面功能后再修改
    public List findAllFactors(){
//        //获取因子类型
        WebApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();
        ServletContext servletContext = applicationContext.getServletContext();
        Map paramMap = (Map)servletContext.getAttribute("PARAM");
        Map types = (Map)paramMap.get("FACTOR_TYPE");

        //获取所有因子
        SysRuleFactor ruleFactor = new SysRuleFactor();
        List<SysRuleFactor> factors = ruleFactorService.findAll();

        List result = new ArrayList();
        for(Iterator iterator = types.keySet().iterator();iterator.hasNext();){
            String k = (String)iterator.next();
            Map type = new HashMap();
            type.put("name",types.get(k));
//            type.put("open","true");
            for (SysRuleFactor factor:factors) {
                if (factor.getFactorType().equals(k)){
                    if (type.get("children")!=null){
                        factor.setDisplayName(factor.getDisplayName());
                        ((List)type.get("children")).add(factor);
                    }else{
                        List typeFactors = new ArrayList();
                        factor.setDisplayName(factor.getDisplayName());
                        typeFactors.add(factor);
                        type.put("children",typeFactors);
                    }
                }
            }
            result.add(type);
        }
        return  result;
    }


    /**
     * 获取因子参数项
     * @return
     */
	@RequestMapping(path = "/crm/manage/getFactorParams", method = RequestMethod.GET)
    public Result getFactorParam(String paramName){
		Result res = new Result();
		SysRuleFactorParam record = new SysRuleFactorParam();
		record.setParamName(paramName);
		List<SysRuleFactorParam>  retList = ruleFactorService.getAllFactorParamByEntity(record);
		
		res.setData(retList);
        return  res;
    }
	
	//获取一条因子
	@RequestMapping(path = "/crm/manage/getOneFactor", method = RequestMethod.GET)
	public Result getOneFactor(SysRuleFactor param) {
		Result res = new Result();
		SysRuleFactor retObj = ruleFactorService.findOneRec(param);
		
		res.setData(retObj);
        return  res;
	}
	//增加一条因子
	@RequestMapping(path = "/crm/manage/insertOneFactor", method = RequestMethod.POST)
	public Result insertOneFactor(SysRuleFactor param) {
		Result res = new Result();

		SysRuleFactor retObj = ruleFactorService.insertOne(param);
		res.setData(retObj);
        return  res;
	}
	
	//修改一条因子
	@RequestMapping(path = "/crm/manage/updateOneFactor", method = RequestMethod.PUT)
	public Result updateOneFactor(SysRuleFactor param) {
		Result res = new Result();

		SysRuleFactor retObj = ruleFactorService.updateOne(param);
		res.setData(retObj);
        return  res;
	}
	
	
	//删除一条因子
	@RequestMapping(path = "/crm/manage/deleteOneFactor", method = RequestMethod.DELETE)
	public Result deleteOneFactor(SysRuleFactor param) {
		Result res = new Result();

		ruleFactorService.deleteOne(param);
        return  res;
	}
	
	//逻辑删除一条因子
	@RequestMapping(path = "/crm/manage/invalidOneFactor", method = RequestMethod.PUT)
	public Result invalidOneFactor(SysRuleFactor param) {
		Result res = new Result();

		ruleFactorService.invalidOne(param);
        return  res;
	}

	//分别为opertype和valuetype赋值
	@RequestMapping(path = "/crm/manage/getOperAndValueTypes", method = RequestMethod.PUT)
	public Result getOperAndValueType(){
		Result res = new Result();

	    Map FACTOR_OPERATE_TYPE = new HashMap();
	    Map FACTOR_VALUE_TYPE = new HashMap();
	    	List<SysRuleFactorOperType> operTypes = operTypeService.getAllRecRows();
	    	for( SysRuleFactorOperType operInfo :  operTypes   ) {
	    		FACTOR_OPERATE_TYPE.put(operInfo.getOperateType(),operInfo.getOperateDesc());
	    	}
	    	
	    	List<SysRuleFactorValueType> valueTypes = valueTypeService.getAllRecRows();
	    	for( SysRuleFactorValueType valueInfo :  valueTypes   ) {
	    		FACTOR_VALUE_TYPE.put(valueInfo.getValueType(),valueInfo.getValueDesc());
	    	}
	    
		Map retMap = new HashMap();
		retMap.put("valueType",FACTOR_VALUE_TYPE);
		retMap.put("operType",FACTOR_OPERATE_TYPE);
		
		res.setData(retMap);
        return  res;
		
	}
	
	
}
