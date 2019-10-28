package cn.com.zsyk.crm.ecif.web.controller.customer.tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.com.zsyk.crm.common.util.JsonUtil;
import cn.com.zsyk.crm.ecif.bo.cust.CustTagList;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.com.zsyk.crm.common.annotation.SysOprtLog;
import cn.com.zsyk.crm.common.annotation.SysOprtLog.Module;
import cn.com.zsyk.crm.common.constant.ServiceType;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.RestUtil;
import cn.com.zsyk.crm.ecif.entity.EcCustTag;
import cn.com.zsyk.crm.ecif.service.customer.portrait.CustTagService;
import cn.com.zsyk.crm.generator.EnumType;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
public class CustTagCtrl {

	@Autowired
	private CustTagService custTagService;

	@Autowired
	RestUtil restUtil;
	/**
	 * @api {GET} /crm/ecif/cust/custTagList 查询客户标签列表
	 * @apiDescription 
	 * @apiName getCustTagList
	 * @apiGroup Customer
	 *
	 * @apiParam {String} custNo 客户号
	 * 
	 * @apiSuccess {Object} Response 返回值对象<br/>
	 * 			客户号		cust_no<br/>
	 * 			客户标签编码		cust_tag_cd<br/>
	 * 			客户标签名称		cust_tag_nam<br/>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ecif/cust/custTagList", method = RequestMethod.GET)
	public Result getCustTagList(String custNo) {
		Result res = new Result();
		List<EcCustTag> ecCustTagLst = custTagService.selectByCustNo(custNo);
		// 标签列表
		List<String> tagIdList = new ArrayList<String>();
		for (EcCustTag obj : ecCustTagLst) {
			tagIdList.add(obj.getCustTagCd());
		}
		Result response = null;
		try {
			String tagIdListStr = JSONArray.fromObject(tagIdList).toString();
			 response = restUtil.getForObject(ServiceType.MANAGE, "/crm/manage/tagmng/getTagByIdList?tagIdListStr={tagIdListStr}", Result.class,  tagIdListStr);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("获取标签名信息失败");
		}

		if (response == null) {
			res.setData(null);
		} else {
			res.setData(response.getData());
		}
		return res;
	}

	/**
	 * @api {PUT} /crm/ecif/cust/setCustTag 设置单客户标签
	 * @apiDescription 
	 * @apiName setCustTag
	 * @apiGroup Customer
	 *
	 * @apiParam {String} custNo 客户号
	 * @apiParam {List} tagList 客户标签列表
	 * 
	 * @apiSuccess {Object} Response 返回值对象
	 * 			客户号		cust_no<br/>
	 * 			客户标签编码		cust_tag_cd<br/>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ecif/cust/setCustTag", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.CUSTOMER, bizDesc="设置单客户标签")
	public Result setCustTag(@RequestParam("custNo") String custNo, @RequestParam(name = "tagList",required = false)  List tagList) {
		Result res = new Result();
		try {
			if (tagList == null|| tagList.size() == 0 || StringUtils.isEmpty(custNo)) {
				res.setData(false);
			} else {
				custTagService.setCustTag(custNo, tagList, EnumType.MarkTagType.manual.getValue());
				res.setData(true);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("设置客户标签失败");
		}

		return res;
	}

	/**
	 * @api {GET} /crm/ecif/cust/setCustListTag 设置多客户标签
	 * @apiDescription 
	 * @apiName setCustListTag
	 * @apiGroup Customer
	 *
	 * @apiParam {String} tagListStr 多客户标签列表
	 * 
	 * @apiSuccess {Object} Response 返回值对象
	 * 			客户号		cust_no<br/>
	 * 			客户标签编码		cust_tag_cd<br/>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ecif/cust/setCustListTag", method = RequestMethod.GET)
	@SysOprtLog(model = Module.CUSTOMER, bizDesc="设置多客户标签")
	public Result setCustListTag(@RequestParam(name = "tagListStr",required = false) String tagListStr) {
		
		Result res = new Result();
		
		if(tagListStr==null||tagListStr.isEmpty()) {
			res.setData(false);
		}
		
		Map<String,  List<String>> tagList = (Map<String, List<String>>) JSONObject.toBean(JSONObject.fromObject(tagListStr),Map.class);
		
		try {
			if (tagList == null || tagList.size() == 0) {
				res.setData(false);
			} else {
				custTagService.setCustListTag(tagList);
				res.setData(true);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("设置客户标签失败");
		}

		return res;
	}

	/**
	 * @api {PUT} /crm/ecif/cust/addCustTag 添加客户标签
	 * @apiDescription 
	 * @apiName addCustTag
	 * @apiGroup Customer
	 *
	 * @apiParam {String} custNo 客户号
	 * @apiParam {String} tagCd 客户标签
	 * @apiParam {String} tagNam 客户标签名
	 * 
	 * @apiSuccess {Object} Response 返回值对象
	 * 			客户号		cust_no<br/>
	 * 			客户标签编码		cust_tag_cd<br/>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ecif/cust/addCustTag", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.CUSTOMER, bizDesc="添加客户标签")
	public Result addCustTag(@RequestParam("custNo") String custNo, @RequestParam(name = "tagCd",required = false)  String tagCd, @RequestParam("tagNam") String tagNam) {
		Result res = new Result();
		try {
			custTagService.addCustTag(custNo, tagCd, tagNam, EnumType.MarkTagType.manual.getValue());
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("添加客户标签失败");
		}

		return res;
	}

	/**
	 * @api {GET} /crm/ecif/cust/addCustTagList  批量设置客户标签
	 * @apiDescription
	 * @apiName addCustTagList
	 * @apiGroup Customer
	 *
	 * @apiParam {String} tagListStr 批量设置客户标签
	 *
	 * @apiSuccess {Object} Response 返回值对象
	 * 			客户号		cust_no<br/>
	 * 			客户标签编码		cust_tag_cd<br/>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ecif/cust/addCustTagList", method = RequestMethod.POST)
	@SysOprtLog(model = Module.CUSTOMER, bizDesc="批量设置客户标签")
	public Result addCustTagList(@RequestBody String tagListStr) {

		Result res = new Result();

		if(tagListStr==null||tagListStr.isEmpty()) {
			res.setData(false);
		}

		List<CustTagList> tagList = JsonUtil.parseArray(tagListStr, CustTagList.class);

		try {
			if (tagList == null || tagList.size() == 0) {
				res.setData(false);
			} else {
				custTagService.addCustTagList(tagList);
				res.setData(true);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("设置客户标签失败");
		}

		return res;
	}

	/**
	 * @api {PUT} /crm/ecif/cust/delCustTag 删除客户标签
	 * @apiDescription 
	 * @apiName delCustTag
	 * @apiGroup Customer
	 *
	 * @apiParam {String} custNo 客户号
	 * @apiParam {String} tagCd 客户标签
	 * @apiParam {String} tagNam 客户标签名
	 * 
	 * @apiSuccess {Object} Response 返回值对象
	 * 			客户号		cust_no<br/>
	 * 			客户标签编码		cust_tag_cd<br/>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ecif/cust/delCustTag", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.CUSTOMER, bizDesc="删除客户标签")
	public Result delCustTag(@RequestParam("custNo") String custNo, @RequestParam(name = "tagCd",required = false)  String tagCd, @RequestParam("tagNam") String tagNam) {
		Result res = new Result();
		try {
			custTagService.delCustTag(custNo, tagCd, tagNam);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("删除客户标签失败");
		}
		return res;
	}
}
