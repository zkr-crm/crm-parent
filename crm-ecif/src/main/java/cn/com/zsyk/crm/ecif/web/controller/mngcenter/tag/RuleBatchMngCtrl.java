package cn.com.zsyk.crm.ecif.web.controller.mngcenter.tag;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import cn.com.zsyk.crm.common.constant.ServiceType;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.dto.Result.StatusEnum;
import cn.com.zsyk.crm.common.util.RestUtil;
import cn.com.zsyk.crm.ecif.service.customer.portrait.CustTagService;
import cn.com.zsyk.crm.ecif.service.mngcenter.tag.RuleBatchDataMngService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
public class RuleBatchMngCtrl {

	@Autowired
	private RuleBatchDataMngService service;
	@Autowired
	RestUtil restUtil;
	@Autowired
	CustTagService custTagService;

	/**
	 * @api {get} /crm/ecif/tagmng/data 查询 - 批量数据
	 * @apiName getRuleBatchData
	 * @apiGroup Tag
	 *
	 * @apiSuccess {Object} data 批量数据样板列表
	 * 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/tagmng/data", method = RequestMethod.GET)
	public Result getRuleBatchData() {
		Result res = new Result();
		List<Map<String, String>> maps = service.getRuleBatchData();
		res.setData(JSONArray.fromObject(maps).toString());
		return res;
	}

	/**
	 * @api {POST} /crm/ecif/tagmng/batchCustTag 批量打标签
	 * @apiName batch
	 * @apiGroup engine
	 * @apiSuccess {Object} data
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ecif/tagmng/batchCustTag", method = RequestMethod.POST)
	public Result batch() {
		Result res = new Result();
		List<Map<String, String>> maps = service.getRuleBatchData();
		String json_list = JSONArray.fromObject(maps).toString();
		
		// 调用自动打标签 TODO
		String sceneCode = "POBS02";
		Result chekResult = restUtil.getForObject(ServiceType.MANAGE, "/crm/manage/engine/analyseFactorMapList?json_list={json_list}&sceneCode={sceneCode}",Result.class,json_list, sceneCode);
		Object tagData = chekResult.getData();
		if (tagData != null ) {
			Map<String,  List<String>> tagList = (Map<String, List<String>>) JSONObject.toBean(JSONObject.fromObject(tagData),Map.class);
			custTagService.setCustListTag(tagList);
		} else {
			return res.fail("实时规则分析返回为空！");
		}
		String status = chekResult.getStatus();
		if (status.equals(StatusEnum.EXCEPTION.getValue())) {
			 return res.exception("按场景校验规则失败!");
			//throw new ServiceException("按场景校验规则失败!");
		}

		return res.success("打标签成功！");
	}
}
