package cn.com.zsyk.crm.manage.drools;

import java.util.Map;

/**
 */
public interface IRuleEngine {
    /**
     * 根据规则字符串重新编译规则，并将编译后的KnowledgeBase存入缓存
     * sceneRules key:规则引擎的场景(渠道+交易类型),value:规则字符串
     * @param sceneRules
     */
    public void initRules(Map<String,String> sceneRules,String rule) throws Exception;
    //获取规则引擎目前的规则
    public String getRule();

    /**
     * 执行规则引擎
     * @param scene:场景
     * @param factor
     * @return
     * @throws Exception
     */
    public Map<String,Object> excute(String scene,Object factor,Map<String,Object> referMap) throws Exception;

    /**
     * 移除某个场景的规则
     * @param key
     */
    public void removeRuleMap(String key);

    /**
     * 清空所有的规则
     */
    public void removeRuleMap();



}
