package cn.com.zsyk.crm.manage.service.engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.zsyk.crm.manage.entity.SysRuleScene;
import cn.com.zsyk.crm.manage.mapper.SysRuleSceneMapper;

@Service
@Transactional
public class RuleSceneService {


	@Autowired
	private SysRuleSceneMapper ruleSceneMapper;
	
    public SysRuleScene findBySceneCode(String sceneCode) {
        return ruleSceneMapper.selectByPrimaryKey(sceneCode);
    }
	
}