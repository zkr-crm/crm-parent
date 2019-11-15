package cn.com.zsyk.crm.manage.service.engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.util.StringUtil;

import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.IdGenerator;
import cn.com.zsyk.crm.manage.entity.SysRuleBaseRule;
import cn.com.zsyk.crm.manage.entity.SysRuleScene;
import cn.com.zsyk.crm.manage.entity.SysRuleStrategy;
import cn.com.zsyk.crm.manage.entity.SysRuleStrategyCombineRel;
import cn.com.zsyk.crm.manage.mapper.SysRuleCombineRuleMapper;
import cn.com.zsyk.crm.manage.mapper.SysRuleSceneMapper;
import cn.com.zsyk.crm.manage.mapper.SysRuleStrategyCombineRelMapper;
import cn.com.zsyk.crm.manage.mapper.SysRuleStrategyMapper;

@Service
@Transactional
public class StrategyService {

	@Autowired
	private SysRuleCombineRuleMapper combineRuleMapper;

	@Autowired
	private ExpressionEvaluatorService expressionEvaluator;

	@Autowired
	protected HttpSession session;

	@Autowired
	private SysRuleStrategyMapper strategyMapper;

	@Autowired
	private SysRuleStrategyCombineRelMapper strategyConbineMapper;

	@Autowired
	private SysRuleSceneMapper ruleSceneMapper;
	
	@Autowired
	private RuleSceneStrategyRelService ssrService;

	/**
	 * 获取所有场景下的规则文件
	 * 
	 * @param strategyId
	 * @param runningStatus
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String, String> getAllRules(/* String strategyId, */ String scene_Code, String runningStatus)
			throws Exception {
		Map<String, String> resultRules = new HashMap<String, String>();
		Map<String, StringBuilder> rules = new HashMap<String, StringBuilder>();
		List<Map> rulesList = null;
		Result res = new Result();
		// if (StringUtils.isEmpty(strategyId)) {
		if (StringUtils.isEmpty(scene_Code)) {
			rulesList = strategyMapper.getAllRules(runningStatus);
		} else {
			
			SysRuleScene ruleScene = ruleSceneMapper.selectByPrimaryKey(scene_Code);
			if(ruleScene!=null) {
				rules.put(ruleScene.getSceneCode(), new StringBuilder(getFileHeader()));
			}else {
				res.fail("无该场景码数据!");
			}
			
//			List scenes = strategyMapper.findStrategyScenes(strategyId);
//			for (int i = 0; i < scenes.size(); i++) {
//				rules.put((String) scenes.get(i), new StringBuilder(getFileHeader()));
//			}
			Map map = new HashMap();
			// paramMap.put("strategyId", strategyId);
			map.put("sceneCode", scene_Code);
			map.put("runningStatus", runningStatus);
			// rulesList = strategyMapper.getAllRulesByStrategyId(paramMap);
			rulesList = strategyMapper.getAllRulesByScene(map);
		}

		for (Map rule : rulesList) {
			String sceneCode = (String) rule.get("SCENE_CODE");
			if (rules.get(sceneCode) == null) {
				StringBuilder sb = new StringBuilder(getFileHeader());
				rules.put(sceneCode, sb);
			}
			rules.put(sceneCode, rules.get(sceneCode).append(getRuleStr(rule)));
		}

		for (Iterator iterater = rules.keySet().iterator(); iterater.hasNext();) {
			String key = (String) iterater.next();
			String rule = rules.get(key).toString();
			resultRules.put(key, rule);
		}
		return resultRules;

	}

	/**
	 * 获取头部声明
	 *
	 * @return
	 */
	private String getFileHeader() throws IOException {
		StringBuffer ruleStr = new StringBuffer();
		ruleStr.append("global java.util.Map $result;\r\n");
		ruleStr.append("global java.util.Map $referMap;\r\n");

		ruleStr.append(getFileFunctions());
		return ruleStr.toString();
	}

	/**
	 * 获取函数定义
	 *
	 * @return
	 */
	private String getFileFunctions() throws IOException {
		org.springframework.core.io.Resource fileRource = new ClassPathResource("/rule/functions.txt");
		File file = fileRource.getFile();
		byte[] buffer = new byte[(int) file.length()];
		FileInputStream is = new FileInputStream(file);
		is.read(buffer, 0, buffer.length);
		is.close();
		String str = new String(buffer);
		return str;
	}

	/**
	 * 获取一条规则内容
	 *
	 * @param strategyRule
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String getRuleStr(Map strategyRule) {
		String strategyId = (String) strategyRule.get("strategy_id");// 策略ID
		String lhs = (String) strategyRule.get("rule_content");// 规则内容

		String strategyContent = strategyId;
		String lineSeparator = System.getProperty("line.separator", "\n");
		StringBuffer ruleStr = new StringBuffer();

		ruleStr.append("rule \"rule_" + strategyRule.get("strategy_id") + "\"" + lineSeparator);
		ruleStr.append("when " + lineSeparator);
		ruleStr.append("$factMap:Map(" + lhs + ")" + lineSeparator);
		ruleStr.append("then " + lineSeparator);

		ruleStr.append(lineSeparator + "        String strategys = (String)$result.get(\"strategy\");" + lineSeparator
				+ "        if (\"\".equals(strategys)||strategys==null){\n" + lineSeparator
				+ "            $result.put(\"strategy\",\"" + strategyContent + "\");" + lineSeparator
				+ "        }else{" + lineSeparator + "            $result.put(\"strategy\",strategys+\",\"+\""
				+ strategyContent + "\");" + lineSeparator + "        }" + lineSeparator);

		ruleStr.append("end" + lineSeparator);

		return ruleStr.toString();
	}

	/**
	 * 保存策略-组合规则关联
	 *
	 * @param strategy
	 */
	public void saveStrategyCombine(SysRuleStrategy strategy, List<Map> combineRuleList) {

		//如果存在旧关联，则删除后再新增
		SysRuleStrategyCombineRel record = new SysRuleStrategyCombineRel();
		record.setStrategyId(strategy.getStrategyId());
		List<SysRuleStrategyCombineRel> rels= strategyConbineMapper.selectAllByEntity(record);
//		List<Map> straCombRelMaps = strategyMapper.findStrategyCombineRels(strategy.getStrategyId());
		if(rels != null && rels.size() > 0) {
			for(SysRuleStrategyCombineRel scr : rels) {
				strategyConbineMapper.deleteByPrimaryKey(scr.getId());
			}
		}
		
		for (Map strategyCombineRel : combineRuleList) {
			SysRuleStrategyCombineRel rscr = new SysRuleStrategyCombineRel();
			rscr.setRecStat("0");
			rscr.setCombineOrder((String) strategyCombineRel.get("combine_order"));
			rscr.setCombineRuleId((String) strategyCombineRel.get("combine_rule_id"));
			rscr.setId(IdGenerator.getUUID());
			rscr.setStrategyId(strategy.getStrategyId());

			strategyConbineMapper.insert(rscr);
		}

	}

	/**
	 * 保存策略
	 *
	 * @param strategy
	 */
	public SysRuleStrategy saveStrategy(SysRuleStrategy strategy, List<Map> combineRuleList) {

		List<SysRuleStrategy> extList = strategyMapper.selectAllByEntity(strategy);
		if (extList != null && extList.size() > 0) {
			throw new ServiceException("欲新增的数据已经存在");
		}

//		strategy.setChannel(null);
		strategy.setStrategyId(IdGenerator.getUUID());
		strategy.setRecStat("0");
		strategy.setVersion((int) (new Date().getTime() / 1000));
		strategy.setRunningStatus("0"); // 默认启用状态

		strategyMapper.insert(strategy);

		this.saveStrategyCombine(strategy, combineRuleList);
		
		ssrService.updSceneStraRel(strategy);

		return strategy;
	}

	/**
	 * 修改策略
	 *
	 * @param strategy
	 * @param combineRuleList
	 * @param sceneList
	 */
	public SysRuleStrategy updateStategy(SysRuleStrategy strategy, List<Map> combineRuleList) {
		SysRuleStrategy searchObj = new SysRuleStrategy();
		searchObj.setStrategyId(strategy.getStrategyId());
		SysRuleStrategy extBean = this.findAllStrategysByEntity(searchObj).get(0);
		if (extBean == null) {
			throw new ServiceException("欲修改数据不存在或已删除！");
		}
		List<String> exps = new ArrayList<String>();
		for (Map combineRule : combineRuleList) {
			exps.add((String) combineRule.get("combine_order"));
		}
		String expression = org.apache.commons.lang.StringUtils.join(exps, " && ");
		expression = expressionEvaluator.evaluateStategy(expression, combineRuleList);
		extBean.setRuleContent(expression);

		if (StringUtils.isNotEmpty(strategy.getChannel())) {
			extBean.setChannel(strategy.getChannel());
		}
		if (StringUtils.isNotEmpty(strategy.getRemark())) {
			extBean.setRemark(strategy.getRemark());
		}
		if (StringUtils.isNotEmpty(strategy.getPriority())) {
			extBean.setPriority(strategy.getPriority());
		}
		if (StringUtils.isNotEmpty(strategy.getStrategyAction())) {
			extBean.setStrategyAction(strategy.getStrategyAction());
		}
		if (StringUtils.isNotEmpty(strategy.getStrategyCode())) {
			extBean.setStrategyCode(strategy.getStrategyCode());
		}
		if (StringUtils.isNotEmpty(strategy.getStrategyName())) {
			extBean.setStrategyName(strategy.getStrategyName());
		}
		if (StringUtils.isNotEmpty(strategy.getWarnLevel())) {
			extBean.setWarnLevel(strategy.getWarnLevel());
		}
		extBean.setVersion((int) (new Date().getTime() / 1000));
		extBean.setRecStat("0");
		strategyMapper.updateByPrimaryKey(extBean);

		this.saveStrategyCombine(extBean, combineRuleList);
		
		ssrService.updSceneStraRel(strategy);

		return extBean;
	}

	/**
	 * 查询关系图,引用的组合规则及基本规则
	 *
	 * @param strategyId
	 * @return
	 */
	public List<Map> findRelations(String strategyId) {
		List<Map> strategyCombineRels = strategyMapper.findStrategyCombineRels(strategyId);
		try{
			for (Map strategyCombineRel : strategyCombineRels) {
				String combineRuleId = (String) strategyCombineRel.get("COMBINE_RULE_ID");
				List combine_rules = combineRuleMapper.findCombineBaseRels(combineRuleId);
				strategyCombineRel.put("combine_rules", combine_rules);
			}
			return strategyCombineRels;
		}catch (Exception e){
			System.out.println("我有毛病");
		}
		return strategyCombineRels;
	}

	/**
	 * 删除策略
	 *
	 * @param strategyId
	 */
	public void deleteStategy(SysRuleStrategy record) {

		strategyMapper.deleteByPrimaryKey(record.getStrategyId());
		
		ssrService.delSceneStraRel(record);

	}

	/**
	 * 按条件查询所有策略
	 *
	 * @param strategy
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<SysRuleStrategy> findAllStrategysByEntity(SysRuleStrategy strategy) {
		List<SysRuleStrategy> retList = strategyMapper.selectAllByEntity(strategy);
		return retList;
	}

	/**
	 * 查询所有策略
	 *
	 */
	public List<SysRuleStrategy> findAll() {
		List<SysRuleStrategy> retList = strategyMapper.selectAll();
		return retList;
	}

	public List<Map> findAllScene() {
		return ruleSceneMapper.findAllScene();
	}

	/**
	 * 根据id查询策略内容
	 *
	 * @param strategyId
	 * @return
	 */
	public SysRuleStrategy findById(String strategyId) {
		return strategyMapper.selectByPrimaryKey(strategyId);
	}

	/**
	 * 查询策略和组合规则的关联关系
	 *
	 * @return
	 */
	public List findStrategyCombineRels(String strategyId) {
		return strategyMapper.findStrategyCombineRels(strategyId);
	}

	/**
	 * 获取函数定义
	 *
	 * @return
	 */
	private String getFileFunctions_bak() throws IOException {
		String function = " import java.text.SimpleDateFormat\n" + " import java.util.Date\n"
				+ " import java.util.ArrayList\n" + " import java.util.List\n" + " import java.util.Map\n" + "\n"
				+ "   function  boolean dateVar(String t,String t1){\n" + "        if(\"\".equals(t1) || t1 == null){\n"
				+ "            return true;\n" + "        }\n" + "        boolean param = false;\n"
				+ "        SimpleDateFormat sim=new SimpleDateFormat(\"yyyy-MM-dd hh:mm:ss\");\n"
				+ "        Date date = sim.parse(t1);\n" + "        String hours = date.getHours()+\"\";\n"
				+ "        if(t == hours){\n" + "            param = true;\n" + "            return param;\n"
				+ "        }\n" + "        return param;\n" + "    }\n"
				+ "    function boolean equipmentVar(String t,String t1,String t2){\n"
				+ "        if(\"\".equals(t1) || \"\".equals(t2)){\n" + "            return true;\n" + "        }\n"
				+ "        if(null == t1 || null == t2){\n" + "            return true;\n" + "        }\n"
				+ "        String agr1[] = t1.split(\",\");\n" + "        String agr2[] = t2.split(\",\");\n"
				+ "        List list = new ArrayList();\n" + "        for(String var :agr1){\n"
				+ "            list.add(var);\n" + "        }\n" + "        for(String var1:agr2){\n"
				+ "            list.add(var1);\n" + "        }\n" + "        return list.contains(t);\n" + "    }\n"
				+ "    function boolean tradingRivalsVar(String name,String name1){\n"
				+ "        if(\"\".equals(name1) || name1 == null){\n" + "            return true;\n" + "        }\n"
				+ "        List list = new ArrayList();\n" + "        String agr[] = name1.split(\",\");\n"
				+ "           for(String s:agr){\n" + "                list.add(s);\n" + "           }\n"
				+ "         return list.contains(name);\n" + "    }\n"
				+ "    function boolean tradingMoney(String t,String t1){\n"
				+ "        if(\"\".equals(t1) || t1 == null){\n" + "            return true;\n" + "        }\n"
				+ "         boolean param = false;\n" + "         String value[] = t1.split(\"-\");\n"
				+ "         Double money = Double.parseDouble(t);\n"
				+ "         Double min = Double.parseDouble(value[0]);\n"
				+ "         Double max = Double.parseDouble(value[1]);\n" + "         if(money>min && money<max){\n"
				+ "            param = true;\n" + "            return param;\n" + "         }\n"
				+ "         return param;\n" + "    }\n" + "    function int timeDifference(String t,String t1){\n"
				+ "        SimpleDateFormat sim=new SimpleDateFormat(\"yyyy-MM-dd hh:mm:ss\");\n"
				+ "        Date date = sim.parse(t);\n" + "        Date time = sim.parse(t);\n"
				+ "        long timeDay = Math.abs(date.getTime() - time.getTime());\n"
				+ "        int hours=(int)Math.floor(timeDay/(3600*1000));\n" + "        return hours;\n" + "    }\n"
				+ "    function String moneyDiffernce(String t,String t1){\n"
				+ "        double money = Math.abs(Double.parseDouble(t)-Double.parseDouble(t1));\n"
				+ "        String moneyDifference = money+\"\";\n" + "        return moneyDifference;\n" + "    }\n"
				+ " function boolean type(String type,String types ,Map map){\n"
				+ "     if(map == null || (!map.containsKey(type)) || map.size() == 0 || types == null){\n"
				+ "         return true;\n" + "     }\n" + "     List list = new ArrayList();\n"
				+ "     String agr[] = types.split(\",\");\n" + "        for(String s:agr){\n"
				+ "             list.add(s);\n" + "        }\n" + "     return list.contains(map.get(type));\n"
				+ "   }\n";
		return function;
	}

	/**
	 * 动帐类业务数
	 *
	 * @return
	 */
	public int getCountAll(String strategyId) {
		return strategyMapper.getCountAll(strategyId);
	}

	// /**
	// * 动帐类业务命中率
	// *
	// * @return
	// */
	// public int getCountThis(String strategyId) {
	// Integer countThis = strategyMapper.getCountThis(strategyId);
	// return StringUtils.isEmpty(countThis) ? 0 : countThis;
	// }

	/**
	 * 策略名字是否相同
	 *
	 * @param strategyName
	 * @return
	 */
	public int findByName(String strategyName, String strategyId) {
    	
        if(StringUtils.isNotEmpty(strategyName)){
       	 SysRuleStrategy rs = new SysRuleStrategy();
       	rs.setRecStat("0");
       	rs.setStrategyId(strategyId);
       	rs.setStrategyName(strategyName);
           return  strategyMapper.findByName(rs);
        }
       return 0;
       
	}

	/**
	 * 查询策略关联的场景
	 *
	 * @param strategyId
	 * @return
	 */
	public List findStrategyScenes(String strategyId) {
		return strategyMapper.findStrategyScenes(strategyId);
	}

	/**
	 * 根据规则ID返回对应的规则ID、名称
	 *
	 * @param strategyIds
	 * @return
	 */
	public List<Map<String, String>> findByIds(String strategyIds) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		if (StringUtil.isNotEmpty(strategyIds)) {
			String[] ids = strategyIds.split(",");
			for (String id : ids) {
				SysRuleStrategy strategy = strategyMapper.findById(id);
				Map<String, String> map = new HashMap<String, String>();
				map.put("strategyId", strategy.getStrategyId());
				map.put("strategyName", strategy.getStrategyName());
				list.add(map);
			}
		}
		return list;
	}

}