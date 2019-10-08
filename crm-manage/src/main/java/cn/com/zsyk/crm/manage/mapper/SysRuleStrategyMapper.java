package cn.com.zsyk.crm.manage.mapper;

import java.util.List;
import java.util.Map;

import cn.com.zsyk.crm.manage.entity.SysRuleStrategy;
@SuppressWarnings("rawtypes")
public interface SysRuleStrategyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_rule_strategy
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String strategyId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_rule_strategy
     *
     * @mbggenerated
     */
    int insert(SysRuleStrategy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_rule_strategy
     *
     * @mbggenerated
     */
    SysRuleStrategy selectByPrimaryKey(String strategyId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_rule_strategy
     *
     * @mbggenerated
     */
    List<SysRuleStrategy> selectAll();
    List<SysRuleStrategy> selectAllByEntity(SysRuleStrategy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_rule_strategy
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SysRuleStrategy record);
    
    int saveStrategy(SysRuleStrategy strategy);

	int saveStrategyCombineRel(Map strategyCombineRel);

    /**
     * 保存场景策略关系
     * @param sceneStrategyRel
     */
    int saveSceneStrategyRel(Map<String, String> sceneStrategyRel);

    List<SysRuleStrategy> findAll(SysRuleStrategy strategy);

    /**
     * 查询所有的场景信息
     * @return
     */
    List<Map> findAllScene();

    List<Map> getRulesByStrategyId(String strategyId);

	List<Map> getAllRulesByStrategyId(Map paramMap);
	
	List<Map> getAllRulesByScene(Map map);
	

    List<Map> getAllRules(String flag);

    SysRuleStrategy findById(String strategyId);

    List findStrategyCombineRels(String strategyId);

    void updateStrategy(SysRuleStrategy strategy);

    List findSceneByStrategyId(String strategyId);

    List<SysRuleStrategy> findStrategysByCombineRuleId(String combineRuleId);

    List findAllStrategys(SysRuleStrategy strategy);

    int getTrainingNum();

    void updateStrategyStatus(String strategyId,String runningStatus);

    void updateStrategyStatusOnline(String strategyId,String runningStatus);

    void updateStrategyTrainStartTime(String strategyId);

    void updateStrategyTrainEndTime(String strategyId);

    int getAbNum(String strategyId);

    void updateStrategyAb(String strategyId);

    void updateStrategyA(String strategyId);

    int getCountAll(String strategyId);

    int getCountThis(String strategyId);


    int findByName(SysRuleStrategy rs);

    List findStrategyScenes(String strategyId);


    void check(String strategyId,String checkStatus);

    public String findNameByHit(String strategyid);

    public int findHitCount(String strategyid);

    public String findStrategyHitCount(String strategyId);

    public String findStrategyHitCountA(String strategyId);

    int findStrategySceneCount(String strategyId);

    int findStrategySceneCountA(String strategyId);

}