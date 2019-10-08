package cn.com.zsyk.crm.manage.service.engine;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.generator.EnumType;
import cn.com.zsyk.crm.manage.entity.SysRuleSceneStrategyRel;
import cn.com.zsyk.crm.manage.entity.SysRuleStrategy;
import cn.com.zsyk.crm.manage.mapper.SysRuleSceneStrategyRelMapper;

@Service
@Transactional
public class RuleSceneStrategyRelService {


	@Autowired
	private SysRuleSceneStrategyRelMapper SSRMapper;
	
    public  List<SysRuleSceneStrategyRel> findBySceneCode(String sceneCode) {
        return SSRMapper.selectAll(sceneCode);
    }
	
    /**
     * 保存策略-场景关联表（删除原关联后保存）
     */
    public int updSceneStraRel(SysRuleStrategy strategy) {
    	
    	this.delSceneStraRel(strategy);
    	
    	String strategyId = strategy.getStrategyId();
    	String sceneId = strategy.getChannel();
    	
    	
    	SysRuleSceneStrategyRel ssr = new SysRuleSceneStrategyRel();
    	ssr.setRecStat(EnumType.RecStat.normal.value);
    	ssr.setStrategyId(strategyId);
    	ssr.setSceneCode(sceneId);
    	int count = SSRMapper.insert(ssr);
    	
    	return count;
    }
    
    /**
     * 删除策略-场景关联表
     */
    public int delSceneStraRel(SysRuleStrategy strategy) {
    	
    	String strategyId = strategy.getStrategyId();
    	if(StringUtils.isEmpty(strategyId)) {
    		throw new ServiceException("未选择策略！");
    	}
    	String sceneId = strategy.getChannel();
    	
    	SysRuleSceneStrategyRel ssr = new SysRuleSceneStrategyRel();
    	ssr.setStrategyId(strategyId);
    	ssr.setSceneCode(sceneId);
    	int count = SSRMapper.deleteByStraIdOrSceneCode(ssr);
    	
    	return count;
    }
    
    
//    /**
//     * 修改策略-场景关联表
//     */
//	public int updSceneStraRel() {
//		
//		return 0;
//	}
    
    
    
}