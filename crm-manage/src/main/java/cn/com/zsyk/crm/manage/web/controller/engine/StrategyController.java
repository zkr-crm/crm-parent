package cn.com.zsyk.crm.manage.web.controller.engine;


import cn.com.zsyk.crm.common.dto.PageBean;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.JsonUtil;
import cn.com.zsyk.crm.generator.EnumType;
import cn.com.zsyk.crm.manage.entity.SysRuleBaseRule;
import cn.com.zsyk.crm.manage.entity.SysRuleCombineBaseRel;
import cn.com.zsyk.crm.manage.entity.SysRuleCombineRule;
import cn.com.zsyk.crm.manage.entity.SysRuleStrategy;
import cn.com.zsyk.crm.manage.entity.SysRuleStrategyCombineRel;
import cn.com.zsyk.crm.manage.entity.SysTag;
import cn.com.zsyk.crm.manage.service.engine.ExpressionEvaluatorService;
import cn.com.zsyk.crm.manage.service.engine.StrategyService;
import cn.com.zsyk.crm.manage.service.engine.utils.DroolUtil;
import cn.com.zsyk.crm.manage.service.mngcenter.tag.TagDetailMngService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class StrategyController {

	@Autowired
    private StrategyService strategyService;

    @Autowired
    private ExpressionEvaluatorService expressionEvaluator;
    
	@Autowired
	private TagDetailMngService tagService;


    /**
     * 编译基本规则，测试语法是否正确
     *
     * @param expression
     * @param combRules
     * @return
     */
    @RequestMapping(path = "/crm/manage/engine/compileStrategyRule", method = RequestMethod.POST)
    public Result compileRule(String expression, @RequestBody String combRules) {
    	Result res = new Result();
    	int compileFlag = 0;//编译测试结果0-失败，1-通过
        List<Map> combRulesList = null;
        if (StringUtils.isNotEmpty(combRules)){
        	combRulesList  = JsonUtil.parseArray(combRules, Map.class);
        }
        
        List<String> exps = new ArrayList<String>();
        for (Map combineRule:combRulesList) {
            exps.add((String)combineRule.get("combine_order"));
        }
        String resultExp = org.apache.commons.lang.StringUtils.join(exps," & ");
        resultExp = expressionEvaluator.evaluateStategy(resultExp,combRulesList);

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
     *查询组合规则名字是否重复
     * @return
     */
    @RequestMapping(path = "/crm/manage/engine/checkStrategyName", method = RequestMethod.GET)
    public Result checkName(String strategyName,String strategyId){
    	Result res = new Result();
    	int count = 0;
        count =strategyService.findByName(strategyName,strategyId);
        res.setData(count);
        return res;
    }
    
    
    /**
     * 保存策略
     *
     * @param
     * @return
     */
    @RequestMapping(path = "/crm/manage/engine/saveStra", method = RequestMethod.POST)
    public Result saveStrategy(SysRuleStrategy strategy,@RequestBody String combRules) {
    	Result res = new Result();

        List<Map> combRuleList = JsonUtil.parseArray(combRules, Map.class);
        
        List<String> exps = new ArrayList<String>();
        for (Map combineRule:combRuleList) {
            exps.add((String)combineRule.get("combine_order"));
        }
        String expression = org.apache.commons.lang.StringUtils.join(exps," && ");
        expression = expressionEvaluator.evaluateStategy(expression,combRuleList);
        strategy.setRuleContent(expression);

//        List<String> sceneList = Arrays.asList(request.getParameter("sceneVals").split(","));

        strategyService.saveStrategy(strategy,combRuleList);
        return res;
    }

    /**
     * 修改策略
     *
     * @param
     * @return
     */
    @RequestMapping(path = "/crm/manage/engine/updStra", method = RequestMethod.PUT)
    public Result updateStrategy(SysRuleStrategy strategy,@RequestBody String combRules) {
    	Result res = new Result();
//        strategy.setChannel(null);

        List<Map> combineRuleList = JsonUtil.parseArray(combRules, Map.class);
        
        strategyService.updateStategy(strategy,combineRuleList);
        return res;
    }

    /**
     * 删除策略
     *
     * @param
     * @return
     */
    @RequestMapping(path = "/crm/manage/engine/delStra", method = RequestMethod.DELETE)
    public Result deleteStrategy(SysRuleStrategy record) {
    	Result res = new Result();
        SysRuleStrategy strategy = strategyService.findById(record.getStrategyId());
        if(strategy == null) {
        	throw new ServiceException("欲删除数据不存在或已经删除！");
        }
        strategyService.deleteStategy(strategy);
        return res;
    }

    /**
     * 所有策略查询
     * @return
     */
    @RequestMapping(path = "/crm/manage/engine/getAllStra", method = RequestMethod.GET)
    public Result findAll() {
    	Result res =new Result();
    	
    	List<SysRuleStrategy> retList =   strategyService.findAll();
    	res.setData(retList);
    	return res;
    }

    /**
     * 按条件查询策略
     * @return
     */
    @RequestMapping(path = "/crm/manage/engine/getAllStraByEntity", method = RequestMethod.GET)
    public Result findAllStrategys(SysRuleStrategy strategy){
    	Result res = new Result();
    	
        List<SysRuleStrategy> retList = strategyService.findAllStrategysByEntity(strategy);
        res.setData(retList);
        return res;
    }

//    /**
//     * 查询策略详情内容
//     * @param strategyId
//     * @return
//     */
//    @RequestMapping("/findById/{strategyId}")
//    @ResponseBody
//    public Object findById(@PathVariable String strategyId) {
//        SysRuleStrategy strategy = strategyService.findById(strategyId);
//        List strategyCombineRels = strategyService.findStrategyCombineRels(strategyId);
//        JSONArray jsonArray = new JSONArray(strategyCombineRels);
//
//        List scenes = strategyService.findStrategyScenes(strategyId);
//        JSONArray scenesArray = new JSONArray(scenes);
//
//        Map<String,Object> model = new HashMap<String, Object>();
//        if (strategy !=null && strategyCombineRels != null){
//            model.put("strategy",strategy);
//            model.put("strategyCombineRels",jsonArray);
//            model.put("scenes",scenesArray);
//        }
//        return new ModelAndView("rule/strategy/strategy_view",model);
//    }
//
//    @RequestMapping("/scoreById/{strategyId}/{view}")
//    @ResponseBody
//    public Object scoreById(@PathVariable String strategyId, @PathVariable String view) {
//        SysRuleStrategy strategy = strategyService.findById(strategyId);
//        List strategyCombineRels = strategyService.findStrategyCombineRels(strategyId);
//        JSONArray jsonArray = new JSONArray(strategyCombineRels);
//
//        List scenes = strategyService.findStrategyScenes(strategyId);
//        JSONArray scenesArray = new JSONArray(scenes);
//
//        Map<String,Object> model = new HashMap<String, Object>();
//        if (strategy !=null && strategyCombineRels != null){
//            model.put("strategy",strategy);
//            model.put("strategyCombineRels",jsonArray);
//            model.put("scenes",scenesArray);
//            model.put("viewParams", view);
//        }
//        return new ModelAndView("rule/evaluate/evaluate_score",model);
//    }

    /**
     * 跳转到修改页面
     * @param strategyId
     * @return
     */
    @RequestMapping(path = "/crm/manage/engine/getStrategyToUpdate", method = RequestMethod.GET)
    public Result toUpdate( String strategyId){
    	Result res = new Result();
        SysRuleStrategy strategy = strategyService.findById(strategyId);
        List strategyCombineRels = strategyService.findStrategyCombineRels(strategyId);
        
        SysTag t = new SysTag();
        t.setStrategyId(strategyId);
        t.setRecStat(EnumType.RecStat.normal.value);
        PageBean pageBean=tagService.getTagDetails(t);
        List<SysTag> tagList = pageBean.getList();

        Map<String,Object> model = new HashMap<>();
        if (strategy!=null&&CollectionUtils.isNotEmpty(strategyCombineRels)){
            model.put("strategy",strategy);
            model.put("strategyCombineRels",strategyCombineRels);
            if (tagList.size()>0){
                model.put("isUsed",true);//当策略规则被标签引用时，名称不允许修改
            } else {
                model.put("isUsed",false);
            }
        }
        res.setData(model);
        return res;
    }

    /**
     * 所有策略查询
     * @return
     */
    @RequestMapping(path = "/crm/manage/engine/findAllScene", method = RequestMethod.GET)
    public Object findAllScene() {
        Result res = new Result();
        res.setData(strategyService.findAllScene());
        return res;
    }

//    /**
//     * 按照状态
//     * @param response
//     * @param runningStatus
//     * @return
//     */
//    @RequestMapping("/getAllRules/{runningStatus}")
//    @ResponseBody
//    public Object getAllRules(HttpServletResponse response,@PathVariable String runningStatus){
//        try {
//            Map<String,String> rules = strategyService.getAllRules(null,runningStatus);
//            JSONObject jo = new JSONObject(rules);
//            return success(jo.toString());
//        } catch (Exception e) {
//            return error("模型查询错误");
//        }
//    }
//
//

    /**
     * 组装 模型树 数据
     * @return
     */
    @RequestMapping(path = "/crm/manage/engine/strategy/findRelations", method = RequestMethod.GET)
    public Result findRelations(String strategyId){
        Result res = new Result();

        strategyService.findRelations(strategyId);
        strategyService.findRelations(strategyId);


        List<Map> relation = strategyService.findRelations(strategyId);
        Map<String,Object> treeNodeMap = new HashMap<>();
        treeNodeMap.put("name",relation.get(0).get("strategy_name"));
        treeNodeMap.put("value", strategyId);

        List<Object> treeNodeList = new ArrayList<>();
        for(Map map : relation) {
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

        res.setData(treeNodeMap);
        return res;
    }


}
