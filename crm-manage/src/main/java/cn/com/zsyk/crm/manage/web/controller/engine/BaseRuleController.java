package cn.com.zsyk.crm.manage.web.controller.engine;


import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.manage.entity.SysRuleBaseFactorRel;
import cn.com.zsyk.crm.manage.entity.SysUserInfo;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import cn.com.zsyk.crm.common.util.DateUtil;
import cn.com.zsyk.crm.common.util.JsonUtil;
import cn.com.zsyk.crm.manage.entity.SysRuleBaseRule;
import cn.com.zsyk.crm.manage.entity.SysRuleCombineRule;
import cn.com.zsyk.crm.manage.service.engine.BaseRuleService;
import cn.com.zsyk.crm.manage.service.engine.ExpressionEvaluatorService;
import cn.com.zsyk.crm.manage.service.engine.utils.DroolUtil;

@RestController
public class BaseRuleController{

    @Autowired
    private BaseRuleService baseRuleServce;

    @Autowired
    private ExpressionEvaluatorService expressionEvaluator;


    /**
     * @api {POST} /crm/manage/engine/compileBaseRule 
     * @apiDescription 编译测试基本规则
     * @apiName compileRule
     * @apiGroup manageGroup
     *
     * @apiParam {HttpServletRequest} request HttpServletRequest对象，页面opt.data参数，保存基本规则关联的因子信息
     *
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/manage/engine/compileBaseRule", method = RequestMethod.POST)
    public Result compileRule(String expression, @RequestBody String factors){
    	Result res = new Result();
    	int compileFlag = 0;//编译测试结果0-失败，1-通过
        List<Map> factorList = null;
        if (StringUtils.isNotEmpty(factors)){
        	factorList  = JsonUtil.parseArray(factors, Map.class);
        }

        String resultExp = expressionEvaluator.evaluateBaseRule(expression,factorList);

        if (StringUtils.isNotEmpty(resultExp)){
            if (DroolUtil.compileRule(resultExp)){
            	compileFlag = 1;
            }else{
            	compileFlag = 0;
            }
        }
        
        res.setData(compileFlag);
        return res;
    }

    /**
     * @api {POST} /crm/manage/engine/saveBaseRule 
     * @apiDescription 保存基本规则
     * @apiName saveRule
     * @apiGroup manageGroup
     *
     * @apiParam {SysRuleBaseRule} baseRule SysRuleBaseRule对象，页面opt.params参数，保存基本规则信息
     * @apiParam {HttpServletRequest} request HttpServletRequest对象，页面opt.data参数，保存基本规则关联的因子信息
     *
     */
    @RequestMapping(path = "/crm/manage/engine/saveRule", method = RequestMethod.PUT)
    public Result saveRuleByJson(SysRuleBaseRule baseRule, @RequestBody String roleMenuStr){

        List<SysRuleBaseFactorRel> baseFactorRelList = JsonUtil.parseArray(roleMenuStr, SysRuleBaseFactorRel.class);

        Result res = new Result();
        baseRule.setVersion((int)(new Date().getTime()/1000));

        baseRuleServce.saveBaseRule(baseRule,baseFactorRelList);

        return res;
    }


    /**
     * @api {PUT} /crm/manage/engine/updBaseRule 
     * @apiDescription 更新基本规则
     * @apiName updateRule
     * @apiGroup manageGroup
     *
     * @apiParam {SysRuleBaseRule} baseRule SysRuleBaseRule对象，页面opt.params参数，保存基本规则信息
     * @apiParam {HttpServletRequest} request HttpServletRequest对象，页面opt.data参数，保存基本规则关联的因子信息
     *
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/manage/engine/updBaseRule", method = RequestMethod.PUT)
    public Result updateRule(SysRuleBaseRule baseRule, @RequestBody String factorsStr){
    	Result res = new Result();
        
        List<SysRuleBaseFactorRel> baseFactorRelList = JsonUtil.parseArray(factorsStr, SysRuleBaseFactorRel.class);

        baseRuleServce.updateBaseRule(baseRule,baseFactorRelList);
        res.setData(baseRule);
        return res;

    }
    /**
     * @api {GET} /crm/manage/engine/checkName 查询基本规则名字是否重复
     * @apiDescription 
     * @apiName checkName
     * @apiGroup Authority
     *
     * @apiParam {String} ruleName 基本规则名称
     * @apiParam {String} ruleId 基本规则id
     *
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/manage/engine/checkName", method = RequestMethod.GET)
    public Result checkName(String ruleName,String ruleId){
    	Result res = new Result();
    	int count = 0;
        count = baseRuleServce.findBaseRuleByName(ruleName,ruleId);
        res.setData(count);
        return res;
    }

    /**
     * @api {PUT} /crm/manage/engine/getAllBaseRule 
     * @apiDescription 获取所有基本规则
     * @apiName findAll
     * @apiGroup manageGroup
     *
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/manage/engine/getAllBaseRule", method = RequestMethod.GET)
    public Result findAll(){
    	Result res = new Result();
    	
        List<SysRuleBaseRule> baseRules =   baseRuleServce.findAll();
        
        res.setData(baseRules);
        return res;
    }

    /**
     * @api {GET} /crm/manage/engine/getBaseRuleByEntity 批量变更单条角色菜单数据
     * @apiDescription 
     * @apiName getBaseRuleByEntity
     * @apiGroup Authority
     *
     * @apiParam {SysRuleBaseRule} baseRule SysRuleBaseRule对象
     *
     */
    @RequestMapping(path = "/crm/manage/engine/getBaseRuleByEntity", method = RequestMethod.GET)
    public Result getBaseRuleByEntity(SysRuleBaseRule baseRule) {
        Result res = new Result();
        System.out.println("获取基本规则数据。");
        List<SysRuleBaseRule> baseRuleList = baseRuleServce.getBaseRuleByEntity(baseRule);
        res.setData(baseRuleList);
        return res;
    }


    /**
     * @api {GET} /crm/manage/engine/getBaseRuleToDetail 跳转详情前获取数据
     * @apiDescription 
     * @apiName findById
     * @apiGroup Authority
     *
     * @apiParam {String} baseRuleId 基本规则id
     *
     */
    @SuppressWarnings("rawtypes")
	@RequestMapping(path = "/crm/manage/engine/getBaseRuleToDetail", method = RequestMethod.GET)
    public Result findById(String baseRuleId){
    	Result res = new Result();
        SysRuleBaseRule baseRule =  baseRuleServce.findById(baseRuleId);
        List baseFactorRels = baseRuleServce.findBaseFactorRels(baseRuleId);

        String bfrStr = JsonUtil.toJSONString(baseFactorRels);
        //JSONArray jsonArray = new JSONArray(baseFactorRels);

        Map<String,Object> model = new HashMap<String, Object>();
        if (baseRule!=null&&baseFactorRels!=null){
            model.put("baseRule",baseRule);
            model.put("baseFactorRels",bfrStr);
        }
        res.setData(model);
        return res;
    }

    

    /**
     * @api {GET} /crm/manage/engine/getBaseRuleToUpdate 跳转修改前获取数据
     * @apiDescription 
     * @apiName toUpdate
     * @apiGroup Authority
     *
     * @apiParam {String} baseRuleId 基本规则id
     *
     */
    @RequestMapping(path = "/crm/manage/engine/getBaseRuleToUpdate", method = RequestMethod.GET)
    public Result toUpdate( String baseRuleId){
    	Result res = new Result();
        SysRuleBaseRule baseRule =  baseRuleServce.findById(baseRuleId);
        List baseFactorRels = baseRuleServce.findBaseFactorRels(baseRuleId);
        List<SysRuleCombineRule> combineRules = baseRuleServce.findRelations(baseRuleId);
        
//        String bfrStr = JsonUtil.toJSONString(baseFactorRels);
        //JSONArray jsonArray = new JSONArray(baseFactorRels);

        Map<String,Object> model = new HashMap<>();
        if (baseRule!=null&&CollectionUtils.isNotEmpty(baseFactorRels)){
            model.put("baseRule",baseRule);
            model.put("baseFactorRels",baseFactorRels);
            if (combineRules.size()>0){
                model.put("isUsed",true);//当基本规则被组合规则引用时，名称不允许修改
            } else {
                model.put("isUsed",false);
            }
        }
        res.setData(model);
        return res;
    }

    
    /**
     * 查询出基本规则->组合规则->策略的血缘关系
     * @return
     */
    @RequestMapping(path = "/crm/manage/engine/findRelations", method = RequestMethod.GET)
    public Result findRelations(String baseRuleId){
        Result res = new Result();

        Map<String,Object> treeNodeMap = new HashMap<>();
        treeNodeMap.put("name",baseRuleId);
        treeNodeMap.put("value",baseRuleId);

        List<SysRuleCombineRule> relation =  baseRuleServce.findRelations(baseRuleId);

        List<Object> treeNodeList = new ArrayList<>();
        for(SysRuleCombineRule vo : relation) {
            Map<String,Object> subTreeNodeMap = new HashMap<>();
            subTreeNodeMap.put("value",vo.getCombineRuleId());
            subTreeNodeMap.put("name",vo.getCombineRuleName());
            treeNodeList.add(subTreeNodeMap);
        }

        if(CollectionUtils.isNotEmpty(treeNodeList)){
            treeNodeMap.put("children",treeNodeList);
        }

        res.setData(treeNodeMap);
        return res;
    }
    
    

    /**
     * @api {DELETE} /crm/manage/engine/delBaseRule 
     * @apiDescription 删除基本规则
     * @apiName deleteRule
     * @apiGroup manageGroup
     *
     * @apiParam {SysRuleBaseRule} baseRule 欲删除的基础规则
     *
     */
    @RequestMapping(path = "/crm/manage/engine/delBaseRule", method = RequestMethod.DELETE)
    public Result deleteRule( SysRuleBaseRule baseRule){
    	Result res = new Result();
    	List<SysRuleBaseRule> extList = baseRuleServce.getBaseRuleByEntity(baseRule);
    	if( extList != null && extList.size() >= 0 ) {
    		for(SysRuleBaseRule extBean : extList) {
    			List<SysRuleCombineRule> combineRules = baseRuleServce.findRelations(extBean.getBaseRuleId());
    			if (combineRules.size()>0){
    				throw new ServiceException("基本规则已经被组合规则引用，请先解除引用关系再删除！[组合规则：" + combineRules.get(0).getCombineRuleName() + "]");
    			}else{
    				baseRuleServce.deleteBaseRule(extBean.getBaseRuleId());
    			}
    		}
    	}
        
        return res;
    }
    

    /**
     * @api {POST} /crm/manage/engine/multiDelBaseRule 
     * @apiDescription 多条删除基本规则，同样适用单条删除
     * @apiName multiDelBaseRule
     * @apiGroup manageGroup
     *
     * @apiParam {String} baseRules String对象，页面opt.data参数，@RequestBody获取，传入为List<SysRuleBaseRule>的json形式
     *
     */
    @RequestMapping(path = "/crm/manage/engine/multiDelBaseRule", method = RequestMethod.POST)
    public Result multiDelBaseRule( @RequestBody String baseRules){

        List<SysRuleBaseRule> baseRuleList = JsonUtil.parseArray(baseRules, SysRuleBaseRule.class);

        Result res = new Result();

        baseRuleServce.multiDeleteBaseRule(baseRuleList);

        return res;
    }

    
    
}
