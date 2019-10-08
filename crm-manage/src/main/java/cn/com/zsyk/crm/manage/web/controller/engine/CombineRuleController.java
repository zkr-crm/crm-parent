package cn.com.zsyk.crm.manage.web.controller.engine;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ResourceProperties.Strategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.util.JsonUtil;
import cn.com.zsyk.crm.manage.entity.SysRuleBaseFactorRel;
import cn.com.zsyk.crm.manage.entity.SysRuleBaseRule;
import cn.com.zsyk.crm.manage.entity.SysRuleCombineBaseRel;
import cn.com.zsyk.crm.manage.entity.SysRuleCombineRule;
import cn.com.zsyk.crm.manage.entity.SysRuleStrategy;
import cn.com.zsyk.crm.manage.service.engine.CombineRuleService;
import cn.com.zsyk.crm.manage.service.engine.ExpressionEvaluatorService;
import cn.com.zsyk.crm.manage.service.engine.utils.DroolUtil;

@RestController
public class CombineRuleController {

	@Autowired
    private CombineRuleService combineRuleService;

    @Autowired
    private ExpressionEvaluatorService expressionEvaluator;
    

    /**
     * @api {POST} /crm/manage/engine/combineRule/compileRule 编译测试规则
     * @apiDescription 
     * @apiName compileRule
     * @apiGroup Authority
     *
     * @apiParam {String} ruleName 基本规则名称
     * @apiParam {String} ruleId 基本规则id
     *
     * @return
     */
    @RequestMapping(path = "/crm/manage/engine/combineRule/compileRule", method = RequestMethod.POST)
    public Result compileRule(String expression, @RequestBody String baseRules) {
    	

    	Result res = new Result();
    	int compileFlag = 0;//编译测试结果0-失败，1-通过
        List<Map> baseRuleList = null;
        if (StringUtils.isNotEmpty(baseRules)){
        	baseRuleList  = JsonUtil.parseArray(baseRules, Map.class);
        }
        String resultExp = expressionEvaluator.evaluateCombineRule(expression, baseRuleList);
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
//    /**
//     *查询组合规则名字是否重复
//     * @return
//     */
//    @RequestMapping("/checkName")
//    @ResponseBody
//    public Object checkName(String ruleName,String ruleId){
//        int count = combineRuleService.findBaseRuleByName(ruleName,ruleId);
//        return count == 0;
//    }


    /**
     * @api {GET} /crm/manage/engine/combineRule/checkName 查询组合规则名字是否重复
     * @apiDescription 
     * @apiName checkName
     * @apiGroup Authority
     *
     * @apiParam {String} ruleName 基本规则名称
     * @apiParam {String} ruleId 基本规则id
     *
     * @return
     */
    @RequestMapping(path = "/crm/manage/engine/combineRule/checkName", method = RequestMethod.GET)
    public Result checkName(String ruleName,String ruleId){
    	Result res = new Result();
    	int count = 0;
        count = combineRuleService.findCombRuleByName(ruleName,ruleId);
        res.setData(count);
        return res;
    }


    /**
     * 保存组合规则
     * @param combineRule
     * @param request
     * @return
     */
    @RequestMapping(path = "/crm/manage/engine/saveCombineRule", method = RequestMethod.POST)
    public Result saveRule(SysRuleCombineRule combineRule,@RequestBody String baseRules) {
    	Result res = new Result();

        List<SysRuleCombineBaseRel> baseRuleList = JsonUtil.parseArray(baseRules, SysRuleCombineBaseRel.class);

        combineRule.setVersion((int)(new Date().getTime()/1000));

        combineRuleService.saveCombineRule(combineRule,baseRuleList);
        
        return res;
    }

    
    
    
  /**
  * 删除组合规则
  * @param combineRuleId
  * @return
  */
    @RequestMapping(path = "/crm/manage/engine/delCombineRule", method = RequestMethod.DELETE)
 public Result deleteRule(String combRuleId) {
    	Result res = new Result();
          combineRuleService.deleteCombineRule(combRuleId);
     return res;
 }
    
    
/**
 * 多条删除组合规则
 * @param combineRules
 * @return
 */
    @RequestMapping(path = "/crm/manage/engine/multiDelCombineRule", method = RequestMethod.POST)
    public Result multiDelBaseRule( @RequestBody String combineRules){

        List<SysRuleCombineRule> combineRuleList = JsonUtil.parseArray(combineRules, SysRuleCombineRule.class);

        Result res = new Result();

        combineRuleService.multiDeleteCombineRule(combineRuleList);

        return res;
    }
    
    
    /**
     * 修改组合规则
     * @param combineRule
     * @param request
     * @return
     */
    @RequestMapping(path = "/crm/manage/engine/updCombineRule", method = RequestMethod.PUT)
    public Result updateRule(SysRuleCombineRule combineRule,@RequestBody String baseRulesStr) {
    	Result res = new Result();
    	

        List<SysRuleCombineBaseRel> baseRuleList = JsonUtil.parseArray(baseRulesStr, SysRuleCombineBaseRel.class);

//        baseRuleServce.updateBaseRule(baseRule,baseFactorRelList);
        
        
//        String baseRules = request.getParameter("baseRules");
//
//        List<Map> baseRuleList = null;
//        if (StringUtils.isNotEmpty(baseRules)){
//        	baseRuleList  = JsonUtil.parseArray(baseRules, Map.class);
//        }
        combineRuleService.updateCombineRule(combineRule,baseRuleList);
        return res;
    }

    
    
    /**
     * 组合规则列表查询
     * @param combineRule
     * @param request
     * @return
     */
    @RequestMapping(path = "/crm/manage/engine/getAllCombineRule", method = RequestMethod.GET)
    public Result findAll() {
    	Result res = new Result();
        List<SysRuleCombineRule>combList = combineRuleService.findAll();
        
        res.setData(combList);
        return res;

    }

    
    
    

  /**
   * 组合规则列表查询ByEntity
   * @param combineRule
   * @param request
   * @return
   */
    @RequestMapping(path = "/crm/manage/engine/getAllCombineRuleByEntity", method = RequestMethod.GET)
  public Result findAllByEntity(SysRuleCombineRule combineRule) {
    	Result res = new Result();
        List<SysRuleCombineRule>combList = combineRuleService.findAllByEntity(combineRule);
        
        res.setData(combList);
          return res;
  }

    
    
    
//    /**
//     * 查看规则详情
//     * @param combineRuleId
//     * @return
//     */
//    @RequestMapping("/findById/{combineRuleId}")
//    public ModelAndView findById(@PathVariable("combineRuleId") String combineRuleId){
//        SysRuleCombineRule combineRule = combineRuleService.findById(combineRuleId);
//        List combineBaseRelsDetail = combineRuleService.findCombineBaseRelsDetail(combineRuleId);
//        JSONArray jsonArray = new JSONArray(combineBaseRelsDetail);
//        Map<String,Object> model = new HashMap<String, Object>();
//        if (combineRule!=null && combineBaseRelsDetail != null){
//            model.put("combineRule",combineRule);
//            model.put("combineBaseRels",jsonArray.toString());
//        }
//        return new ModelAndView("rule/combinerule/combine_rule_view",model);
//    }
//
//    /**
//     * 查看规则详情
//     * @param combineRuleId
//     * @return
//     */
//    @RequestMapping("/toUpdate/{combineRuleId}")
//    public ModelAndView toUpdate(@PathVariable("combineRuleId") String combineRuleId){
//        SysRuleCombineRule combineRule = combineRuleService.findById(combineRuleId);
//        List combineBaseRelsDetail = combineRuleService.findCombineBaseRelsDetail(combineRuleId);
//        List<SysRuleStrategy> strategies = combineRuleService.findRelations(combineRuleId);
//        JSONArray jsonArray = new JSONArray(combineBaseRelsDetail);
//        Map<String,Object> model = new HashMap<String, Object>();
//        if (combineRule!=null && combineBaseRelsDetail != null){
//            model.put("combineRule",combineRule);
//            model.put("combineBaseRels",jsonArray.toString());
//            if (strategies.size()>0){
//                model.put("isUsed",true);//当规则被模型引用时，名称不允许修改
//            }
//        }
//        return new ModelAndView("rule/combinerule/combine_rule_update",model);
//    }

    /**
     * @api {GET} /crm/manage/engine/getCombineRuleToUpdate 跳转修改前获取数据
     * @apiDescription 
     * @apiName toUpdate
     * @apiGroup Authority
     *
     * @apiParam {String} baseRuleId 基本规则id
     *
     */
    @SuppressWarnings("rawtypes")
	@RequestMapping(path = "/crm/manage/engine/getCombineRuleToUpdate", method = RequestMethod.GET)
    public Result toUpdate( String combineRuleId){
        Result res = new Result();
        SysRuleCombineRule combineRule = combineRuleService.findById(combineRuleId);
        List combineFactorRels = combineRuleService.findCombineBaseRelsDetail(combineRuleId);
        List<SysRuleStrategy> combineRules = combineRuleService.findRelations(combineRuleId);
        Map<String,Object> model = new HashMap<>();
        if (combineRule!=null&& CollectionUtils.isNotEmpty(combineFactorRels)){
            model.put("combineRule",combineRule);
            model.put("combineFactorRels",combineFactorRels);
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
     * 查询出组合规则->策略的血缘关系
     * @return
     */
    @RequestMapping(path = "/crm/manage/engine/combineRule/findRelations", method = RequestMethod.GET)
    public Result findRelations(String combineRuleId){
        Result res = new Result();

        Map<String,Object> treeNodeMap = new HashMap<>();
        treeNodeMap.put("name",combineRuleId);
        treeNodeMap.put("value",combineRuleId);

        List<SysRuleStrategy> relation = combineRuleService.findRelations(combineRuleId);

        List<Object> treeNodeList = new ArrayList<>();
        for(SysRuleStrategy vo : relation) {
            Map<String,Object> subTreeNodeMap = new HashMap<>();
            subTreeNodeMap.put("value",vo.getStrategyId());
            subTreeNodeMap.put("name",vo.getStrategyName());
            treeNodeList.add(subTreeNodeMap);
        }

        if(CollectionUtils.isNotEmpty(treeNodeList)){
            treeNodeMap.put("children",treeNodeList);
        }

        res.setData(treeNodeMap);
        return res;
    }

    /**
     * 查询出组合规则->策略的血缘关系
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/manage/engine/combineRule/findRelationsList", method = RequestMethod.POST)
    public Result findRelationsList(@RequestBody String combineRuleIdList){
        Result res = new Result();

        List<SysRuleCombineRule> combineRuleList = JsonUtil.parseArray(combineRuleIdList, SysRuleCombineRule.class);

        List<Map> resultList = new ArrayList<>();
        for (SysRuleCombineRule combineRule: combineRuleList) {
            Map<String,Object> treeNodeMap = new HashMap<>();
            treeNodeMap.put("name",combineRule.getCombineRuleName());
            treeNodeMap.put("value",combineRule.getCombineRuleId());
            List<Map> relation = combineRuleService.findCombineBaseRels(combineRule.getCombineRuleId());

            List<Object> treeNodeList = new ArrayList<>();
            for(Map<String,Object> map : relation) {
                Map<String,Object> subTreeNodeMap = new HashMap<>();
                subTreeNodeMap.put("value",map.get("combine_rule_id"));
                subTreeNodeMap.put("name",map.get("combine_rule_name"));
                treeNodeList.add(subTreeNodeMap);

                List baseRuleList = (List) map.get("combine_rules");
                List subBaseRuleList = new ArrayList();
                if(CollectionUtils.isNotEmpty(baseRuleList)){
                    for (Object baseRuleMap : baseRuleList) {
                        Map tempMap = (Map) baseRuleMap;
                        Map<String,Object> subBaseRuleMap = new HashMap<>();
                        subBaseRuleMap.put("value",tempMap.get("id"));
                        subBaseRuleMap.put("name",tempMap.get("rule_name"));
                        subBaseRuleList.add(subBaseRuleMap);
                    }
                    if(CollectionUtils.isNotEmpty(subBaseRuleList)){
                        subTreeNodeMap.put("children",subBaseRuleList);
                    }
                }
            }

            if(CollectionUtils.isNotEmpty(treeNodeList)){
                treeNodeMap.put("children",treeNodeList);
            }
            resultList.add(treeNodeMap) ;
        }

        res.setData(resultList);
        return res;
    }



//    /**
//     * 反向查询：查询出组合规则->基本规则血缘关系
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping("/findReverseRelations/{combineRuleIds}")
//    public Object findReverseRelations(@PathVariable String combineRuleIds){
//        List<SysRuleCombineRule> relations = new ArrayList<SysRuleCombineRule>();
//        if (!StringUtils.isEmpty(combineRuleIds)){
//            String[] ids = combineRuleIds.split(",");
//            for (String id:ids) {
//                SysRuleCombineRule combineRule = combineRuleService.findById(id);
//                combineRule.setBaseRules(combineRuleService.findReverseRelations(id));
//                relations.add(combineRule);
//            }
//        }else{
//            return error("没有组合规则！");
//        }
//        return success(relations);
//    }
//

}
