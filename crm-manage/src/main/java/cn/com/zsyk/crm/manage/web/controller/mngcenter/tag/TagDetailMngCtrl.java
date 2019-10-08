package cn.com.zsyk.crm.manage.web.controller.mngcenter.tag;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.JsonUtil;
import cn.com.zsyk.crm.manage.entity.SysTag;
import cn.com.zsyk.crm.manage.entity.SysTagType;
import cn.com.zsyk.crm.manage.service.mngcenter.tag.TagDetailMngService;
import cn.com.zsyk.crm.manage.service.mngcenter.tag.TagTypeMngService;

@RestController
public class TagDetailMngCtrl {
	@Autowired
	private TagDetailMngService service;
	@Autowired
	private TagTypeMngService typeService;

	/**
	 * @api {get} /crm/manage/tagmng/tagDetail 查询 - 标签明细（单条）
	 * @apiName getTagDetailById
	 * @apiGroup Tag
	 *
	 * @apiParam {String} tagId 标签明细id
	 * @apiSuccess {Object} data 标签明细对象
	 * 
	 */
	@RequestMapping(path = "/crm/manage/tagmng/tagDetail", method = RequestMethod.GET)
	public Result getTagDetailById(String tagId) {
		Result res = new Result();
		SysTag resTagDetail = service.getOneTag(Integer.parseInt(tagId));
		res.setData(resTagDetail);
		return res;
	}

	/**
	 * @api {GET} /crm/manage/tagmng/getTagByIdList 查询 - 标签name（map）
	 * @apiName getTagByIdList
	 * @apiGroup Tag
	 *
	 * @apiParam {String} tagIdListStr 标签明细id Json
	 * @apiSuccess {Result} map 标签name map
	 * 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/manage/tagmng/getTagByIdList", method = RequestMethod.GET)
	public Result getTagByIdList(@RequestParam(name = "tagIdListStr",required = false)  String tagIdListStr) {
		Result res = new Result();

		if (StringUtils.isEmpty(tagIdListStr)) {
			res.setData(false);
		} else {
	        List<String> tagIdlist = JsonUtil.parseArray(tagIdListStr, String.class);
			List<SysTag> sysTagLst = new ArrayList<SysTag>();
			for(String tagId : tagIdlist) {
				SysTag  resTagDetail = service.getOneTag(Integer.parseInt(tagId));
				if (resTagDetail == null) {
					continue;
				}
				sysTagLst.add(resTagDetail);
			}
			res.setData(sysTagLst);
		}

		return res;
	}
	
	
	/**
	 * @api {get} /crm/manage/tagmng/tagDetials 查询 - 标签明细列表
	 * @apiName getTagDetials
	 * @apiGroup Tag
	 *
	 * @apiParam {String} tagTypeId 标签类型id <必输>
	 * @apiParam {String} tagName 标签名称 <选输>
	 * @apiSuccess {Object[]} data 标签明细列表
	 */

	@RequestMapping(path = "/crm/manage/tagmng/tagDetials", method = RequestMethod.GET)
	public Result getTagDetials(SysTag tagDetail) {
		Result res = new Result();
		//List<SysTag> tagDetails = service.getTagDetails(tagDetail);
		res.setData(service.getTagDetails(tagDetail));
		return res;
	}

	/**
	 * @api {POST} /crm/manage/tagmng/tagDetial 新增 - 标签明细
	 * @apiName addTagDetial
	 * @apiGroup Tag
	 *
	 * @apiParam {Object} SysTag 标签明细对象 <必输>
	 * @apiSuccess {int} data 增量
	 */

	@RequestMapping(path = "/crm/manage/tagmng/tagDetial", method = RequestMethod.POST)
	public Result addTagDetial(SysTag tagDetail) {
		Result res = new Result();
		int addCount = service.addTag(tagDetail);
		if (addCount > 0) {
			System.out.println("插入数据成功。");
		} else {
			throw new ServiceException("新增数据失败！");
		}
		res.setData(addCount);
		return res;
	}

	/**
	 * @api {PUT} /crm/manage/tagmng/tagDetial 修改 - 标签明细
	 * @apiName modTagDetial
	 * @apiGroup Tag
	 *
	 * @apiParam {Object} SysTag 标签明细对象 <必输>
	 * @apiSuccess {int} data 修改数量
	 */

	@RequestMapping(path = "/crm/manage/tagmng/tagDetial", method = RequestMethod.PUT)
	public Result modTagDetial(SysTag tagDetail) {
		Result res = new Result();
		int modCount = service.modTag(tagDetail);

		if (modCount > 0) {
			System.out.println("修改数据成功。");
		} else {
			throw new ServiceException("修改数据失败！");
		}
		res.setData(modCount);
		return res;
	}

	/**
	 * @api {PUT} /crm/manage/tagmng/delModTagDetial 逻辑删除 - 标签明细
	 * @apiName delModTagDetial
	 * @apiGroup Tag
	 *
	 * @apiParam {Object} SysTag 标签明细对象 <必输>
	 * @apiSuccess {int} data 逻辑删除数量
	 */
	@RequestMapping(path = "/crm/manage/tagmng/delModTagDetial", method = RequestMethod.PUT)
	public Result delModTagDetial(SysTag tagDetail) {
		Result res = new Result();
		int delModCount = service.delModTag(tagDetail);

		if (delModCount > 0) {
			System.out.println("删除数据成功。");
		} else {
			throw new ServiceException("删除数据失败");
		}
		res.setData(delModCount);
		return res;
	}

	/**
	 * @api {DELETE} /crm/manage/tagmng/tagDetial 物理删除 - 标签明细
	 * @apiName delTagDetial
	 * @apiGroup Tag
	 *
	 * @apiParam {Object} SysTag 标签明细对象 <必输>
	 * @apiSuccess {int} data 物理删除数量
	 */
	@RequestMapping(path = "/crm/manage/tagmng/tagDetial", method = RequestMethod.DELETE)
	public Result delTagDetial(SysTag tagDetail) {
		Result res = new Result();
		int delCount = service.delTag(tagDetail);

		if (delCount > 0) {
			System.out.println("删除数据成功。");
		} else {
			throw new ServiceException("删除数据失败!");
		}

		res.setData(delCount);
		return res;
	}
	

	@RequestMapping(path = "/crm/manage/tagmng/getTagsById", method = RequestMethod.GET)
	public Result getTagsById( String id ) {
		Result res = new Result();
		List<SysTag> retList = new ArrayList<SysTag>();
		SysTag parentTag = service.getOneTag(Integer.parseInt(id));
		retList.add(parentTag);
		List<SysTag> childTags = service.getChildTagsById(id);
		retList.addAll(childTags);
		
		res.setData(retList);
		return res;
	}
	
	
	
	@RequestMapping(path = "/crm/manage/tagmng/getTagsByTypeId", method = RequestMethod.GET)
	public Result getTagsByType( String tagTypeId ) {
		Result res = new Result();
		
		List<SysTagType> typeList = new ArrayList<SysTagType>();
		SysTagType parentType = typeService.getOneTagType(Integer.parseInt(tagTypeId));
		typeList.add(parentType);
				
		List<SysTagType> childTypeList = typeService.getChildTypesByTypeId(tagTypeId);
		typeList.addAll(childTypeList);
		
		List<SysTag> retList = new ArrayList<SysTag>();
		
		retList = service.getTagsByTypes(typeList);
//		SysTag parentTag = service.getOneTag(Integer.parseInt(id));
//		retList.add(parentTag);
//		List<SysTag> childTags = service.getChildTagsById(id);
//		retList.addAll(childTags);
		
		res.setData(retList);
		return res;
	}
	
	
	

}
