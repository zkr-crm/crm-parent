package cn.com.zsyk.crm.manage.service.engine;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import cn.com.zsyk.crm.common.dto.ContextContainer;
import cn.com.zsyk.crm.common.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ResourceProperties.Strategy;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.IdGenerator;
import cn.com.zsyk.crm.generator.EnumType;
import cn.com.zsyk.crm.manage.entity.SysRuleBaseFactorRel;
import cn.com.zsyk.crm.manage.entity.SysRuleBaseRule;
import cn.com.zsyk.crm.manage.entity.SysRuleCombineBaseRel;
import cn.com.zsyk.crm.manage.entity.SysRuleCombineRule;
import cn.com.zsyk.crm.manage.entity.SysRuleStrategy;
import cn.com.zsyk.crm.manage.entity.SysRuleStrategyCombineRel;
import cn.com.zsyk.crm.manage.mapper.SysRuleBaseRuleMapper;
import cn.com.zsyk.crm.manage.mapper.SysRuleCombineBaseRelMapper;
import cn.com.zsyk.crm.manage.mapper.SysRuleCombineRuleMapper;
import cn.com.zsyk.crm.manage.mapper.SysRuleStrategyCombineRelMapper;
import cn.com.zsyk.crm.manage.mapper.SysRuleStrategyMapper;

@Service
public class CombineRuleService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysRuleBaseRuleMapper baseRuleMapper;

    @Autowired
    private SysRuleCombineRuleMapper combineRuleMapper;
    
    @Autowired
    private SysRuleCombineBaseRelMapper combineBaseRelMapper;

    @Autowired
    private SysRuleStrategyMapper strategyMapper;
    
    @Autowired
    private SysRuleStrategyCombineRelMapper strategyCombRelMapper;

    @Autowired
    private StrategyService strategyService;

    @Autowired
    protected HttpSession session;
    /**
     * 保存组合规则，及与基本规则关系
     * @param combineRule
     * @param baseRuleList
     * @return
     */
    public SysRuleCombineRule saveCombineRule(SysRuleCombineRule combineRule, List<SysRuleCombineBaseRel> baseRuleList){
        combineRule.setCombineRuleId(IdGenerator.getUUID());
        combineRule.setRecStat("0");
        combineRuleMapper.saveCombineRule(combineRule);
        if(baseRuleList != null && baseRuleList.size()>= 0) {
        	for (SysRuleCombineBaseRel combineBaseRel:baseRuleList) {
        		combineBaseRel.setCombineRuleId(combineRule.getCombineRuleId());
        		combineBaseRel.setId(IdGenerator.getUUID());
        		combineBaseRel.setRecStat("0");
        		combineBaseRelMapper.insert(combineBaseRel);
        	}
        }
        return combineRule;
    }

    /**
     * 更新所有的组合规则
     * @param combineRule
     * @param baseRuleList
     */
    public void updateCombineRule(SysRuleCombineRule combineRule, List<SysRuleCombineBaseRel> baseRuleList){
        logger.info("将原始的组合规则查询出来，修改");
        
        SysRuleCombineRule updCr = combineRuleMapper.selectByPrimaryKey(combineRule.getCombineRuleId());
        if( updCr == null  ) {
        	throw new ServiceException("欲修改的数据不存在或已删除！");
        }
        
        updCr.setCombineExpression(combineRule.getCombineExpression());
        updCr.setCombineRuleCode(combineRule.getCombineRuleCode());
        updCr.setCombineRuleId(combineRule.getCombineRuleId());
        updCr.setCombineRuleName(combineRule.getCombineRuleName());
        updCr.setRecStat("0");
        updCr.setRemark(combineRule.getRemark());
        updCr.setRiskFeature(combineRule.getRiskFeature());
        updCr.setVersion(combineRule.getVersion());
        updCr.setBaseRules(combineRule.getBaseRules());
        updCr.setStrategys(combineRule.getStrategys());
        updCr.setUpdateDate(DateUtil.nowDate());
        updCr.setUpdateTime(DateUtil.nowTime());
        updCr.setUpdateUser(ContextContainer.getContext().getUserId());
        combineRuleMapper.updateByPrimaryKey(updCr);

        logger.info("将原始的组合-基本关联规则查询出来，删除，重新写入新关联");
        //删除旧关联信息
        List<SysRuleCombineBaseRel> toDelList = combineBaseRelMapper.selectAllByCombineRuleId(updCr.getCombineRuleId());
        if(toDelList != null && toDelList.size()>= 0) {
        	for( SysRuleCombineBaseRel delItem : toDelList ) {
        		combineBaseRelMapper.deleteByPrimaryKey(delItem.getId());
        	}
        }
        //新增新关联信息
        if(baseRuleList != null && baseRuleList.size()>= 0) {
            for (SysRuleCombineBaseRel cbr:baseRuleList) {
            	
            	cbr.setId(IdGenerator.getUUID());
            	cbr.setCombineRuleId(combineRule.getCombineRuleId());
            	cbr.setRecStat("0");
            	combineBaseRelMapper.insert(cbr);
            }
        }
        
        //获取所有使用该combrule的strategy
        List<SysRuleStrategy> straList =  strategyMapper.findStrategysByCombineRuleId(updCr.getCombineRuleId());
        
        if(straList != null && straList.size() > 0  ) {
        	for(SysRuleStrategy strategy : straList ) {
//        		for (int i = 0; i < straList.size(); i++) {
//        			Map rel = (Map) straList.get(i);
//        			if (rel.get("combine_rule_id").equals(crule.getCombineRuleId())){
//        				rel.put("combine_expression",crule.getCombineExpression());
//        			}
//        		}
        		
        		List<Map> straCombRelMaps = strategyMapper.findStrategyCombineRels(strategy.getStrategyId());
        		strategyService.updateStategy(strategy, straCombRelMaps);
        	}
        }
        
//        //获取所有使用该combrule的strategy
//        List<SysRuleStrategy> straList =  strategyMapper.findStrategysByCombineRuleId(updCr.getCombineRuleId());
//        
//        if(straList != null && straList.size() > 0  ) {
//        	for(SysRuleStrategy strategy : straList ) {
//        		for (int i = 0; i < straList.size(); i++) {
//        			
//        			Map rel = (Map) straList.get(i);
//        			if (rel.get("combine_rule_id").equals(updCr.getCombineRuleId())){
//        				rel.put("combine_expression",combineRule.getCombineExpression());
//        			}
//        		}
//        		
//        		List<Map> straCombRelMaps = strategyMapper.findStrategyCombineRels(updCr.getCombineRuleId());
//        		strategyService.updateStategy(strategy, straCombRelMaps);
//        	}
//        }
    }

    
    public List<SysRuleStrategyCombineRel> findStrategyCombineRels( String strategyId ){
    	SysRuleStrategyCombineRel scr = new SysRuleStrategyCombineRel();
    	scr.setStrategyId(strategyId);
    	List<SysRuleStrategyCombineRel> straCombRelLst = strategyCombRelMapper.selectAllByEntity(scr);
    	return straCombRelLst;
    }
    
    /**
     *  根据combineRuleId 获得对应的strategy
     * @param combineRuleId
     * @return
     */
    public List<SysRuleStrategy> findStrategysByCombineRuleId(String combineRuleId){
    	SysRuleStrategyCombineRel scr = new SysRuleStrategyCombineRel();
    	scr.setCombineRuleId(combineRuleId);
    	Set<SysRuleStrategy> straSet = new HashSet<SysRuleStrategy>();
    	List<SysRuleStrategyCombineRel> straCombRelLst = strategyCombRelMapper.selectAllByEntity(scr);
    	if( straCombRelLst != null && straCombRelLst.size() >= 0  ) {
    		for( SysRuleStrategyCombineRel rela : straCombRelLst ) {
    			SysRuleStrategy stra = strategyMapper.selectByPrimaryKey(rela.getStrategyId());
    			straSet.add(stra);
    		}
    	}
    	List<SysRuleStrategy> straList = new ArrayList<SysRuleStrategy>(straSet);
    	
    	return straList;
    }
    
    
    
    
    
    
    
    /**
     * 查询规则引用关系
     * @param combineRuleId
     * @return
     */
    public List<SysRuleStrategy> findRelations(String combineRuleId) {
        return strategyMapper.findStrategysByCombineRuleId(combineRuleId);
    }

    /**
     * 反向查询规则引用关系
     * @param combineRuleId
     * @return
     */
    public List<SysRuleBaseRule> findReverseRelations(String combineRuleId) {
        return baseRuleMapper.findBaseRulesByCombineRuleId(combineRuleId);
    }

    /**
     * 删除规则,需要确认组合规则没有被引用的情况
     * @param combineRuleId
     */
    public void deleteCombineRule(String combineRuleId) {
        SysRuleCombineRule cbr = this.findById(combineRuleId);
        if( cbr == null ) {
        	throw new ServiceException("欲删除的数据不存在或已经删除！");
        }
        //根据组合规则id获取策略-组合关联表数据
        SysRuleStrategyCombineRel rscr = new SysRuleStrategyCombineRel();
        rscr.setCombineRuleId(combineRuleId);
        List<SysRuleStrategyCombineRel>  rscrList = strategyCombRelMapper.selectAllByEntity(rscr);
        if( rscrList != null && rscrList.size() >= 0 ) {
        	for(SysRuleStrategyCombineRel sc : rscrList) {
        		//根据策略id获取策略表未删除记录
        		SysRuleStrategy rs = strategyMapper.selectByPrimaryKey(sc.getStrategyId());
        		if(rs != null) {
        			throw new ServiceException("有某策略规则正在使用该组合规则，不可删除！策略名称[" + rs.getStrategyName() + "]");
        		}
        	}
        }
        combineRuleMapper.deleteByPrimaryKey(combineRuleId);
    }

    /**
     * 查询组合规则名字是否重复
     * @param ruleName
     * @return
     */
    public int findCombRuleByName(String ruleName,String ruleId){
        if(StringUtils.isNotEmpty(ruleName)){
        	SysRuleCombineRule cbr = new SysRuleCombineRule();
        	cbr.setCombineRuleName(ruleName);
        	cbr.setCombineRuleId(ruleId);
//            Map map = new HashMap();
//            map.put("ruleName",ruleName);
//            map.put("ruleId",ruleId);
            return  combineRuleMapper.findByName(cbr);
        }
        return 0;
    }

//    /**
//     * 更新组合规则的备注内容
//     * @param combineRule
//     * @param combineBaseRels
//     */
//    public void updateCombineRuleExp(SysRuleCombineRule combineRule, List<Map> combineBaseRels) {
//        logger.info("更新组合规则的备注内容");
//        logger.info("获取组合规则的公式");
//        String exp = combineRule.getCombineExpression();
//
//        logger.info("获取基本规则的表达式");
//        for (int i = 0; i < combineBaseRels.size(); i++) {
//            Map map = combineBaseRels.get(i);
//            String baseRuleRemark = baseRuleMapper.findById((String) map.get("base_rule_id")).getRemark();
//            String baseRuleOrder = (String) map.get("base_rule_order");
//            //3、采用正则替换
//            exp = exp.replaceAll(baseRuleOrder,baseRuleRemark);
//        }
//        logger.info("表达式内容："+exp);
//        combineRule.setRemark(exp);
//        combineRule.setRecStat("0");
//        combineRuleMapper.updateCombineRule(combineRule);
//    }
    
    /**
     * 更新组合规则备注 remark baserule角度
     */
    public void updateCombRuleRemarkFromBaseRule(String baseruleId) {
    	
    	//根据baseruleid获得所有使用该baserule 的combrule  
    	List<SysRuleCombineRule> combRules = combineRuleMapper.findCombineRulesByBaseRuleId(baseruleId);
    	if(combRules != null && combRules.size() > 0) {
    		for(SysRuleCombineRule crule : combRules ) {
    	        String combExp = crule.getCombineExpression();
    	        
    	        //根据combrule获得对应所有baserule
    	        List<SysRuleBaseRule>  usingBaserule = this.findReverseRelations(crule.getCombineRuleId());
    	        //根据combruleid 获得所有combbaserel
    	        List<SysRuleCombineBaseRel> combBaseRels = combineBaseRelMapper.selectAllByCombineRuleId(crule.getCombineRuleId());
    	        
    	        if(combBaseRels != null && combBaseRels.size() > 0) {
    	        	for(SysRuleCombineBaseRel cbr : combBaseRels) {
    	        		String ruleOrder = cbr.getBaseRuleOrder();
    	        		String baseRemark = "";
    	        		if(usingBaserule != null && usingBaserule.size() > 0) {
    	        			for(SysRuleBaseRule ubr : usingBaserule) {
    	        				if(ubr.getBaseRuleId().equals(cbr.getBaseRuleId())) {
    	        					baseRemark = ubr.getRemark();
    	        					break;
    	        				}
    	        			}
    	        		}
    	        		combExp = combExp.replaceAll(ruleOrder, baseRemark);
    	        	}
    	        }
    	        
    	        crule.setRemark(combExp);
    	        crule.setRecStat(EnumType.RecStat.normal.value);
    	        //更新combrule
                combineRuleMapper.updateByPrimaryKey(crule);
    	        
                
                //获取所有使用该combrule的strategy
                List<SysRuleStrategy> straList =  strategyMapper.findStrategysByCombineRuleId(crule.getCombineRuleId());
                
                if(straList != null && straList.size() > 0  ) {
                	for(SysRuleStrategy strategy : straList ) {
//                		for (int i = 0; i < straList.size(); i++) {
//                			Map rel = (Map) straList.get(i);
//                			if (rel.get("combine_rule_id").equals(crule.getCombineRuleId())){
//                				rel.put("combine_expression",crule.getCombineExpression());
//                			}
//                		}
                		
                		List<Map> straCombRelMaps = strategyMapper.findStrategyCombineRels(strategy.getStrategyId());
                		strategyService.updateStategy(strategy, straCombRelMaps);
                	}
                }
    		}
    	}
    	
    }
    

    /**
     * 查询所有规则
     * @param combineRule
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<SysRuleCombineRule> findAll() {
    	List<SysRuleCombineRule> combList = combineRuleMapper.selectAll();
        return combList;
    }
    
    /**
     * 查询所有规则ByEntity
     * @param combineRule
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<SysRuleCombineRule> findAllByEntity(SysRuleCombineRule combineRule) {
    	List<SysRuleCombineRule> combList = combineRuleMapper.findAll(combineRule);
    	return combList;
    }
    
    
    /**
     * 查询组合规则关联的基本规则
     * @param combineRuleId
     * @return
     */
    public List<Map> findCombineBaseRels(String combineRuleId){
        return combineRuleMapper.findCombineBaseRels(combineRuleId);
    }
    /**
     * 查询组合规则关联的基本规则内容
     * @param combineRuleId
     * @return
     */
    public List<Map> findCombineBaseRelsDetail(String combineRuleId) {
        return combineRuleMapper.findCombineBaseRelsDetail(combineRuleId);
    }
    /**
     * 查看组合规则
     * @param combineRuleId
     * @return
     */
    public SysRuleCombineRule findById(String combineRuleId) {
        return combineRuleMapper.findById(combineRuleId);
    }

    
    //多条删除组合规则
	public void multiDeleteCombineRule(List<SysRuleCombineRule> combineRuleList) {
    	
    	//循环校验web工程输入数据及删除
    	if(combineRuleList != null ){
    		for(SysRuleCombineRule toDelBean : combineRuleList) {
    			
    			//查询欲删除数据是否存在，不存在报错
    			List<SysRuleCombineRule> toDelList = combineRuleMapper.selectAllByEntity(toDelBean);
    			if(toDelList == null || toDelList.size() <= 0){
    				throw new ServiceException("欲删除数据不存在或已经删除[规则名称：" + toDelBean.getCombineRuleName() + "，规则代码：" + toDelBean.getCombineRuleCode() + "]！");
    			}
    			
    			//循环每条数据进行关联判断，有关联报错提示，无关联则删除
    			for(SysRuleCombineRule item : toDelList) {
    				//根据基本规则id获取组合规则列表
	    			List<SysRuleStrategy> strategys = this.findRelations(item.getCombineRuleId());
	    			if (strategys != null && strategys.size()>0){
	    				//根据欲删除基本规则id可查询出组合规则列表，则上下级存在关系，业务报错
	    				throw new ServiceException("组合规则已经被策略规则引用，请先解除引用关系再删除！[策略规则：" + strategys.get(0).getStrategyName() + "]");
	    			}else{
	    				//根据基本规则id未查询到组合规则，可删除
	    				combineRuleMapper.deleteByPrimaryKey(item.getCombineRuleId());
	    				//根据基本规则id，删除对应的基本规则-因子关联数据
	    				List<SysRuleCombineBaseRel>  cbrList = combineBaseRelMapper.selectAllByCombineRuleId(item.getCombineRuleId());
	    				if (cbrList != null && cbrList.size()>0){
	    					for(SysRuleCombineBaseRel cbrItem : cbrList) {
	    						combineBaseRelMapper.deleteByPrimaryKey(cbrItem.getId());
	    					}
	    				}
	    			}
    			}
    		}
    	}
    }

}
