package cn.com.zsyk.crm.manage.service.engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.zsyk.crm.manage.entity.SysRuleFactor;
import cn.com.zsyk.crm.manage.mapper.SysRuleFactorMapper;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class ExpressionEvaluatorService {

	@Autowired
    private SysRuleFactorMapper factorMapper;

    @Autowired
    private BaseRuleService baseRuleService;

    @Autowired
    private CombineRuleService combineRuleService;
    /**
     * 将基本规则公式转换为drools的Condition
     * @param expression
     * @param factors
     * @return
     */
    public String evaluateBaseRule(String expression, List<Map> factors) {
        Map map = getFactorExp(factors);
        for(Iterator iterator = map.keySet().iterator();iterator.hasNext();){
            String order = (String)iterator.next();
            expression =  expression.replace(order,(String)map.get(order));
        }
        return expression;
    }

    /**
     * 将组合规则公式转换为drools的Condition
     * @param expression
     * @param baseRules
     * @return
     */
    @SuppressWarnings("rawtypes")
	public String evaluateCombineRule(String expression, List<Map> baseRules) {
        Map map = getBaseRuleExp(baseRules);
        for(Iterator iterator = map.keySet().iterator();iterator.hasNext();){
            String order = (String)iterator.next();
            if(order==null) {
            	continue;
            }
            expression =  expression.replace(order,(String)map.get(order));
        }
        return expression;
    }

    /**
     * 将策略公式转换为drools的Condition
     * @param expression
     * @param combineRules
     * @return
     */
    @SuppressWarnings("rawtypes")
	public String evaluateStategy(String expression, List<Map> combineRules) {
        Map map = getCombineRuleExp(combineRules);
        for(Iterator iterator = map.keySet().iterator();iterator.hasNext();){
            String order = (String)iterator.next();
            expression =  expression.replace(order,(String)map.get(order));
        }
        return expression;
    }


    /**
     * 获取策略的规则内容
     * @param strategyId
     * @return
     */
    public String evaluateStategy(String strategyId) {

/*        Map map = getCombineRuleExp(combineRules);
        for(Iterator iterator = map.keySet().iterator();iterator.hasNext();){
            String order = (String)iterator.next();
            expression =  expression.replace(order,(String)map.get(order));
        }
        return expression;*/
        return null;
    }

    /**
     * 根据场景获得规则文件
     * @param expression
     * @param combineRules
     * @return
     */
    @SuppressWarnings("rawtypes")
	public String evaluateScene(String expression, List<Map> combineRules) {
        Map map = getCombineRuleExp(combineRules);
        for(Iterator iterator = map.keySet().iterator();iterator.hasNext();){
            String order = (String)iterator.next();
            expression =  expression.replace(order,(String)map.get(order));
        }
        return expression;
    }
    /**
     * 因子表达式计算
     * 根据基本规则的因子集合获取表达式内容
     * @param factors
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private Map getFactorExp(List<Map> factors){
        Map map = new HashMap();
        for (Map factorExp:factors){
            String order = (String)factorExp.get("factor_order");
            String id = (String)factorExp.get("factor_id");
//            String exp = (String)factorExp.get("factor_name")+(String)factorExp.get("operate")+"$paramMap[\""+(String)factorExp.get("factor_value")+"\"]";
            SysRuleFactor factor = factorMapper.selectByPrimaryKey(id);
            if(factor==null) {
            	continue;
            }
            String exp = factor.getFactorName()+" "+(String)factorExp.get("operate")+" "+(String)factorExp.get("factor_value");
            map.put(order,exp);
        }
        return map;
    }
    /**
     * 基本规则表达式计算
     * 根据组合规则表达式获取基本规则表达式内容集合
     * @param baseRules
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private Map getBaseRuleExp(List<Map> baseRules){
        Map map = new HashMap();
        for (Map baseRuleExp:baseRules){
            //将一个基本规则转换成表达式
            String order = (String)baseRuleExp.get("base_rule_order");
            String id = (String)baseRuleExp.get("id");
            String exp = (String)baseRuleExp.get("expression");
            List<Map> factorList = baseRuleService.findBaseFactorRels(id);
            map.put(order,this.evaluateBaseRule(exp,factorList));
        }
        return map;
    }

    /**
     * 组合规则表达式计算
     * 根据组合规则表达式获取基本规则表达式内容集合
     * @param combineRules
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private Map getCombineRuleExp(List<Map> combineRules){
        Map map = new HashMap();
        for (Map combineRuleExp:combineRules){
            //将一个组合规则转换成表达式
            String order = (String)combineRuleExp.get("combine_order");
//            String id = (String)combineRuleExp.get("id");
            String id = (String)combineRuleExp.get("combine_rule_id");
            String exp = (String)combineRuleExp.get("combine_expression");
            List<Map> baseRuleList = combineRuleService.findCombineBaseRels(id);
            map.put(order,this.evaluateCombineRule(exp,baseRuleList));
        }
        return map;
    }
}
