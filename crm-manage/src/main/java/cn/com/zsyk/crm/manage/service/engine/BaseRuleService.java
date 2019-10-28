package cn.com.zsyk.crm.manage.service.engine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import cn.com.zsyk.crm.common.dto.ContextContainer;
import cn.com.zsyk.crm.common.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.IdGenerator;
import cn.com.zsyk.crm.manage.entity.SysRuleBaseFactorRel;
import cn.com.zsyk.crm.manage.entity.SysRuleBaseRule;
import cn.com.zsyk.crm.manage.entity.SysRuleCombineRule;
import cn.com.zsyk.crm.manage.mapper.SysRuleBaseFactorRelMapper;
import cn.com.zsyk.crm.manage.mapper.SysRuleBaseRuleMapper;
import cn.com.zsyk.crm.manage.mapper.SysRuleCombineRuleMapper;
import cn.com.zsyk.crm.manage.mapper.SysRuleStrategyMapper;

@Service
public class BaseRuleService  {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysRuleBaseRuleMapper baseRuleMapper;

    @Autowired
    private SysRuleCombineRuleMapper combineRuleMapper;

    @Autowired
    private SysRuleStrategyMapper strategyMapper;

    @Autowired
    private CombineRuleService combineRuleService;

    @Autowired
    protected HttpSession session;
    
    @Autowired
    protected SysRuleBaseFactorRelMapper BFRMapper;
    /**
     * 保存基本规则，及关联的规则因子
     * @param baseRule
     * @param factorList
     * @return
     */
	public SysRuleBaseRule saveBaseRule(SysRuleBaseRule baseRule, List<SysRuleBaseFactorRel> factorList) {
        logger.info("执行保存基本规则业务层！");
        baseRule.setBaseRuleId(IdGenerator.getUUID());
        baseRule.setRecStat("0");
        baseRuleMapper.saveBaseRule(baseRule);

        if(factorList != null && factorList.size()>= 0) {
            for (SysRuleBaseFactorRel bfr:factorList) {
                bfr.setId(IdGenerator.getUUID());
                bfr.setBaseRuleId(baseRule.getBaseRuleId());
                bfr.setRecStat("0");
                BFRMapper.insert(bfr);
            }
        }
        return baseRule;
    }

    /**
     * 修改基本规则
     * @param baseRule
     * @param factorList
     */
    public void updateBaseRule(SysRuleBaseRule baseRule, List<SysRuleBaseFactorRel> factorList) {
        logger.info("将原始的基本规则查询出来，更新");
        SysRuleBaseRule checkVo = baseRuleMapper.findById(baseRule.getBaseRuleId());
        if( checkVo == null) {
        	throw new ServiceException("欲修改的数据不存在或已删除！");
        }
        baseRule.setUpdateDate(DateUtil.nowDate());
        baseRule.setUpdateTime(DateUtil.nowTime());
        baseRule.setUpdateUser(ContextContainer.getContext().getUserId());
        baseRuleMapper.updateBaseRule(baseRule);
        
        logger.info("将原始的基本-因子关联规则查询出来，删除，重新写入新关联");
        //删除旧关联信息
        List<SysRuleBaseFactorRel> toDelList = BFRMapper.selectAllByBaseRuleId(baseRule.getBaseRuleId());
        if(toDelList != null && toDelList.size()>= 0) {
        	for( SysRuleBaseFactorRel delItem : toDelList ) {
        		BFRMapper.deleteByPrimaryKey(delItem.getId());
        	}
        }
        //新增新关联信息
        if(factorList != null && factorList.size()>= 0) {
            for (SysRuleBaseFactorRel bfr:factorList) {
                bfr.setId(IdGenerator.getUUID());
                bfr.setBaseRuleId(baseRule.getBaseRuleId());
                bfr.setRecStat("0");
                BFRMapper.insert(bfr);
            }
        }
        combineRuleService.updateCombRuleRemarkFromBaseRule(baseRule.getBaseRuleId());
    }
    /**
     * 查询出基本规则->组合规则->策略的血缘关系
     * @return
     */
    public List<SysRuleCombineRule> findRelations(String baseRuleId) {

        List<SysRuleCombineRule> combineRules = combineRuleMapper.findCombineRulesByBaseRuleId(baseRuleId);
//        if( combineRules != null && combineRules.size() >= 0 ) {
//        	for (SysRuleCombineRule combineRule:combineRules) {
//        		List<SysRuleStrategy> strategy = combineRuleService.findStrategysByCombineRuleId(combineRule.getCombineRuleId());
//        		combineRule.setStrategys(strategy);
//        	}
//        }

        return combineRules;
    }

    /**
     * 删除基本规则
     * @param baseRuleId
     */
    public void deleteBaseRule(String baseRuleId) {
        //将原始的基本规则查询出来，删除
        SysRuleBaseRule br = baseRuleMapper.findById(baseRuleId);
        if(br == null){
        	throw new ServiceException("欲删除数据不存在或已经删除！");
        }
        baseRuleMapper.deleteByPrimaryKey(baseRuleId);
    }

    /**
     * 多条删除基本规则
     * @param baseRules
     */
    public void multiDeleteBaseRule(List<SysRuleBaseRule> baseRules) {
    	
    	//循环校验web工程输入数据及删除
    	if(baseRules != null ){
    		for(SysRuleBaseRule toDelBean : baseRules) {
    			
    			//查询欲删除数据是否存在，不存在报错
    			List<SysRuleBaseRule> toDelList = baseRuleMapper.selectAllByEntity(toDelBean);
    			if(toDelList == null || toDelList.size() <= 0){
    				throw new ServiceException("欲删除数据不存在或已经删除[规则名称：" + toDelBean.getRuleName() + "，规则代码：" + toDelBean.getRuleCode() + "]！");
    			}
    			
    			//循环每条数据进行关联判断，有关联报错提示，无关联则删除
    			for(SysRuleBaseRule item : toDelList) {
    				//根据基本规则id获取组合规则列表
	    			List<SysRuleCombineRule> combineRules = this.findRelations(item.getBaseRuleId());
	    			if (combineRules != null && combineRules.size()>0){
	    				//根据欲删除基本规则id可查询出组合规则列表，则上下级存在关系，业务报错
	    				throw new ServiceException("基本规则已经被组合规则引用，请先解除引用关系再删除！[组合规则：" + combineRules.get(0).getCombineRuleName() + "]");
	    			}else{
	    				//根据基本规则id未查询到组合规则，可删除
	    				baseRuleMapper.deleteByPrimaryKey(item.getBaseRuleId());
	    				//根据基本规则id，删除对应的基本规则-因子关联数据
	    				List<SysRuleBaseFactorRel>  bfrList = BFRMapper.selectAllByBaseRuleId(item.getBaseRuleId());
	    				if (bfrList != null && bfrList.size()>0){
	    					for(SysRuleBaseFactorRel bfrItem : bfrList) {
	    					BFRMapper.deleteByPrimaryKey(bfrItem.getId());
	    					}
	    				}
	    			}
    			}
    		}
    	}
    }
    
    
    /**
     * 查询基本规则名字
     * @param ruleName
     * @return
     */
    public int findBaseRuleByName(String ruleName,String ruleId) {
    	
         if(StringUtils.isNotEmpty(ruleName)){
        	 SysRuleBaseRule rbr = new SysRuleBaseRule();
        	 rbr.setRecStat("0");
        	 rbr.setBaseRuleId(ruleId);
        	 rbr.setRuleName(ruleName);
            return  baseRuleMapper.findByName(rbr);
         }
        return 0;
    }

    /**
     * 查询基本规则
     * @param vo
     * @return
     */
    public List<SysRuleBaseRule> getBaseRuleByEntity(SysRuleBaseRule vo) {
        return baseRuleMapper.findByEntity(vo);
    }

    /**
     * 查询基本规则
     * @return
     */
    public List<SysRuleBaseRule> findAll() {
    	
        List<SysRuleBaseRule> retList = baseRuleMapper.selectAll();
        return retList;
    }

    /**
     * 查询基本规则
     * @param baseRuleId
     * @return
     */
    public SysRuleBaseRule findById(String baseRuleId) {
        return baseRuleMapper.findById(baseRuleId);
    }

    /**
     * 查询基本规则关联关系
     * @param baseRuleId
     * @return
     */
    @SuppressWarnings("rawtypes")
	public List findBaseFactorRels(String baseRuleId) {
        return baseRuleMapper.findBaseFactorRels(baseRuleId);
    }

}
