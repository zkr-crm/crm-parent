package cn.com.zsyk.crm.manage.web.controller.engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.util.JsonUtil;
import cn.com.zsyk.crm.common.util.RestUtil;
import cn.com.zsyk.crm.manage.bom.util.ReadBean;
import cn.com.zsyk.crm.manage.drools.DroolsRuleEngine;
import cn.com.zsyk.crm.manage.entity.SysRuleScene;
import cn.com.zsyk.crm.manage.entity.SysRuleSceneStrategyRel;
import cn.com.zsyk.crm.manage.service.engine.RuleSceneService;
import cn.com.zsyk.crm.manage.service.engine.RuleSceneStrategyRelService;
import cn.com.zsyk.crm.manage.service.engine.StrategyService;
import cn.com.zsyk.crm.manage.service.mngcenter.tag.TagDetailMngService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
public class EngineRuleController {

	@Autowired
	private StrategyService strategyService;

	@Autowired
	private RuleSceneService ruleSceneService;

	@Autowired
	private RuleSceneStrategyRelService ruleSceneStrategyRelService;

	@Autowired
	RestUtil restUtil;

	@Autowired
	private TagDetailMngService tagDetailMngService;

	/**
	 * 实时规则分析调用 或 批量规则分析调用
	 * 
	 * @api {get} /crm/manage/engine/analyseFactorMapList 按场景校验规则，并返回命中信息
	 * @apiName checkRuleWithFactorMap
	 * @apiGroup engine
	 * @apiParam {String} sceneCode 场景码 POBS02
	 * @apiParam {String} json_list 被序列化的json串 List<Map>类型
	 * @apiSuccess {Object} data 例如：Map<String, List> {客户号 = [tag1,tag2,tag3]}
	 * 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
	@RequestMapping(path = "/crm/manage/engine/analyseFactorMapList", method = RequestMethod.GET)
	public Result checkRuleWithFactorMap(@RequestParam(name = "json_list") String json_list,
			@RequestParam(name = "sceneCode") String sceneCode) {

		Result res = new Result();
		JSONArray jsonArray = JSONArray.fromObject(json_list);
		List<Map> factorMaps = JSONArray.toList(jsonArray, Map.class);

		if (factorMaps.isEmpty()) {
			return res.fail("无测试样例!");
		}

		List<Map<String, List>> resMapList = new ArrayList<Map<String, List>>();
		if (factorMaps.isEmpty() || factorMaps.size() == 0 || StringUtils.isEmpty(sceneCode)) {
			return res.fail("按场景校验规则方法的入参为空!");
		}

		SysRuleScene exit_ruleScene = ruleSceneService.findBySceneCode(sceneCode);
		if (exit_ruleScene == null) {
			return res.fail("无效的场景值!");
		}
		List<SysRuleSceneStrategyRel> startegy_list = ruleSceneStrategyRelService.findBySceneCode(sceneCode);
		if (startegy_list == null || startegy_list.isEmpty()) {
			return res.fail("该场景值下无有效策略!");
		}

		try {
			factorMaps = ReadBean.transFactorMap(factorMaps, "cn.com.zsyk.crm.manage.bom.tag");
		} catch (Exception e) {
			e.printStackTrace();
			return res.exception("过滤因子失败！");
		}

		String content = "";

		try {
			// 获取场景下的所有规则文件
			Map<String, String> startegy_all_rules = strategyService.getAllRules(sceneCode, "0");
			content = new org.json.JSONObject(startegy_all_rules).toString();
			Map<String, String> ruleMap = (Map<String, String>) JSONObject.toBean(JSONObject.fromObject(content),
					Map.class);
			DroolsRuleEngine engine = new DroolsRuleEngine();
			engine.initRules(ruleMap, content);
			for (Map map : factorMaps) {
				Map<String, Object> resultRule = engine.excute(sceneCode, map, new HashMap());
				if (!resultRule.isEmpty()) {
					Map<String, List> temp = new HashMap<String, List>();
					String strategys = (String) resultRule.get("strategy");
					List<String> strategy_list = Arrays.asList(strategys.split(","));
					temp.put((String) map.get("custNo"), strategy_list);
					resMapList.add(temp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.exception("按场景校验规则失败!");
		}

		Map<String, List> map = new HashMap<>();
		for (Map<String, List> m : resMapList) {// 归纳客户标签
			Iterator<String> it = m.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				if (!map.containsKey(key)) {
					map.put(key, m.get(key));
				} else {
					map.get(key).addAll(m.get(key));
				}
			}
		}

		Map<String, List> res_map = new HashMap<>();
		for (String key : map.keySet()) {// 去掉重复标签
			Set set = new HashSet();
			List newList = new ArrayList();
			for (Object strategy_id : map.get(key)) {
				if (set.add((String) strategy_id)) {
					newList.add(strategy_id);
				}
			}
			res_map.put(key, newList);
		}

		if (res_map.isEmpty()) {
			return res.fail("无可匹配的策略！");
		}

		// 转换策略数组为标签数组
		for (String key : res_map.keySet()) {
			List<String> list = res_map.get(key);
			List<String> tagsidsTransfered = tagDetailMngService.getTagidsByStrategyId(list);
			if (tagsidsTransfered.isEmpty()) {
				return res.fail("策略无对应标签！");
			}
			res_map.put(key, tagsidsTransfered);
		}

		String tagListStr = JSONObject.fromObject(res_map).toString();
		// Result addTagsRes = restUtil.getForObject(ServiceType.ECIF,
		// "/crm/ecif/cust/setCustListTag?tagListStr={tagListStr}",Result.class,tagListStr);
		// //Result addTagsRes = restUtil.postForObject(ServiceType.ECIF,
		// "/crm/ecif/cust/setCustListTag", tagListStr, Result.class);
		//
		// Object dataBoolean = addTagsRes.getData();
		res.setData(tagListStr);
		return res;
	}

	/**
	 * 实时规则分析调用 或 批量规则分析调用
	 * 
	 * @throws Exception
	 * 
	 * @api {get} /crm/manage/engine/getCustByStrategyId 根据规则模型编码查询用户信息
	 * @apiName getCustByStrategyId
	 * @apiGroup engine
	 * 
	 * @apiParam {Map} mapjson 包括客户信息列表和规则ID
	 * 
	 * @apiSuccess {String} data 客户号字符串，逗号隔开
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/manage/engine/getCustByStrategyId", method = RequestMethod.POST)
	public Result getCustByStrategyId(@RequestBody String mapjson) throws Exception {

		Map map1 = (Map) JsonUtil.parseObject(mapjson, Map.class);
		String custListJson = map1.get("custListJson").toString();// 客户信息列表
		String strategyId = map1.get("strategyId").toString();// 规则ID

		// 存放该规则匹配的所有客户ID，逗号隔开
		String custIds = "";

		Result res = new Result();

		JSONArray jsonArray = JSONArray.fromObject(custListJson);
		List<Map> factorMaps = JSONArray.toList(jsonArray, Map.class);

		List<Map<String, List>> resMapList = new ArrayList<Map<String, List>>();
		String content = "";
		String sceneCode = "POBS01";// 场景代码：客户动态群组场景
		try {
			// 获取场景下的所有规则文件
			Map<String, String> startegy_all_rules = strategyService.getAllRules(sceneCode, "0");
			content = new org.json.JSONObject(startegy_all_rules).toString();
			Map<String, String> ruleMap = (Map<String, String>) JSONObject.toBean(JSONObject.fromObject(content),
					Map.class);
			DroolsRuleEngine engine = new DroolsRuleEngine();
			engine.initRules(ruleMap, content);
			for (Map map : factorMaps) {
				Map<String, Object> resultRule = engine.excute(sceneCode, map, new HashMap());
				if (!resultRule.isEmpty()) {
					Map<String, List> temp = new HashMap<String, List>();
					String strategys = (String) resultRule.get("strategy");
					List<String> strategy_list = Arrays.asList(strategys.split(","));
					temp.put((String) map.get("custNo"), strategy_list);
					resMapList.add(temp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.exception("按场景校验规则失败!");
		}

		Map<String, List> map = new HashMap<>();
		// 遍历Map对象 数据格式：[{客户1：[规则ID1，规则ID2 ...]},{客户2：[规则ID1，规则ID2
		// ...]},{客户3：[规则ID1，规则ID2 ...]} ...]
		for (Map<String, List> item : resMapList) {
			Iterator<String> it = item.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();

				// 获取客户号所符合的，所有规则的ID数组。（Map中数据的格式：）
				String value = item.get(key).toString();
				String strategyIds = value.substring(1, value.length() - 1);
				String[] strategyIdArr = strategyIds.split(",");

				boolean flg = false;

				// 遍历规则ID数组，若其中存在传入的规则ID，则将该用户ID放入返回值中
				for (String strategyIdItem : strategyIdArr) {
					if (strategyIdItem.trim().equals(strategyId.trim())) {
						flg = true;
						break;
					}
				}
				// 将用户ID拼接到返回字符串中
				if (flg) {
					custIds += key + ",";
					flg = false;
				}
			}
		}

		res.setData(custIds);

		return res;
	}
}
