package cn.com.zsyk.crm.manage.web.controller.mngcenter.tag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.manage.entity.SysTagType;
import cn.com.zsyk.crm.manage.service.mngcenter.tag.TagTypeMngService;

@RestController
public class TagTypeMngCtrl {
	@Autowired
	private TagTypeMngService service;

	/**
	 * @api {get} /crm/manage/tagmng/tagType 查询 - 标签类别信息（单条）
	 * @apiDescription 
	 * @apiName getTagTypeById
	 * @apiGroup Tag
	 *
	 * @apiParam {String} tagTypeId 标签类别信息id
	 * @apiSuccess {Object} data 标签类别对象
	 * 
	 */
	@RequestMapping(path = "/crm/manage/tagmng/tagType", method = RequestMethod.GET)
	public Result getTagTypeById(String tagTypeId) {
		Result res = new Result();
		SysTagType resTagType = service.getOneTagType(Integer.parseInt(tagTypeId));
		res.setData(resTagType);
		return res;
	}

	/**
	 * @api {get} /crm/manage/tagmng/tagTypes 查询 - 标签类别信息列表
	 * @apiDescription 
	 * @apiName getTagTypes
	 * @apiGroup Tag
	 *
	 * @apiParam {String} tagTypeLevel 标签类别等级 <必输>
	 * @apiParam {String} tagTypeName 标签类别名称 <选输>
	 * @apiSuccess {Object[]} data 标签类别信息列表
	 */

	@RequestMapping(path = "/crm/manage/tagmng/tagTypes", method = RequestMethod.GET)
	public Result getTagTypes(SysTagType TagType) {
		Result res = new Result();
		List<SysTagType> TagTypes = service.getTagTypes(TagType);
		res.setData(TagTypes);
		return res;
	}

	/**
	 * @api {POST} /crm/manage/tagmng/tagType 新增 - 标签类别信息
	 * @apiDescription 
	 * @apiName addTagType
	 * @apiGroup Tag
	 *
	 * @apiParam {Object} SysTagType 标签类别对象 <必输>
	 * @apiSuccess {int} data 增量
	 */

	@RequestMapping(path = "/crm/manage/tagmng/tagType", method = RequestMethod.POST)
	public Result addTagType(SysTagType TagType) {
		Result res = new Result();
		int addCount = service.addTagType(TagType);
		if (addCount > 0) {
			System.out.println("插入数据成功。");
		} else {
			throw new ServiceException("新增数据失败！");
		}
		res.setData(addCount);
		return res;
	}

	/**
	 * @api {PUT} /crm/manage/tagmng/tagType 修改 - 标签类别信息
	 * @apiDescription 
	 * @apiName modTagType
	 * @apiGroup Tag
	 *
	 * @apiParam {Object} SysTagType 标签类别对象 <必输>
	 * @apiSuccess {int} data 修改数量
	 */

	@RequestMapping(path = "/crm/manage/tagmng/tagType", method = RequestMethod.PUT)
	public Result modTagType(SysTagType TagType) {
		Result res = new Result();
		int modCount = service.modTagType(TagType);

		if (modCount > 0) {
			System.out.println("修改数据成功。");
		} else {
			throw new ServiceException("修改数据失败！");
		}
		res.setData(modCount);
		return res;
	}

	/**
	 * @api {PUT} /crm/manage/tagmng/delModTagType 逻辑删除 - 标签类别信息
	 * @apiDescription 
	 * @apiName delModTagType
	 * @apiGroup Tag
	 *
	 * @apiParam {Object} SysTagType 标签类别对象 <必输>
	 * @apiSuccess {int} data 逻辑删除数量
	 */
	@RequestMapping(path = "/crm/manage/tagmng/delModTagType", method = RequestMethod.PUT)
	public Result delModTagType(SysTagType TagType) {
		Result res = new Result();
		int delModCount = service.delModTagType(TagType);

		if (delModCount > 0) {
			System.out.println("删除数据成功。");
		} else {
			throw new ServiceException("删除数据失败");
		}
		res.setData(delModCount);
		return res;
	}

	/**
	 * @api {DELETE} /crm/manage/tagmng/tagType 物理删除 - 标签类别信息
	 * @apiDescription 
	 * @apiName delTagType
	 * @apiGroup Tag
	 *
	 * @apiParam {Object} SysTagType 标签类别对象 <必输>
	 * @apiSuccess {int} data 物理删除数量
	 */
	@RequestMapping(path = "/crm/manage/tagmng/tagType", method = RequestMethod.DELETE)
	public Result delTagType(SysTagType TagType) {
		Result res = new Result();
		int delCount = service.delTagType(TagType);

		if (delCount > 0) {
			System.out.println("删除数据成功。");
		} else {
			throw new ServiceException("删除数据失败!");
		}

		res.setData(delCount);
		return res;
	}

}
