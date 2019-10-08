package cn.com.zsyk.crm.ecif.web.controller.mngcenter.tag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.ecif.entity.EcTagInclud;
import cn.com.zsyk.crm.ecif.service.mngcenter.tag.TagIncludMngService;

@RestController
public class TagIncludMngCtrl {
	@Autowired
	private TagIncludMngService service;

	/**
	 * @api {get} /crm/ecif/tagmng/tagInclud 查询 - 标签包含信息（单条）
	 * @apiName getTagInclud
	 * @apiGroup Tag
	 *
	 * @apiParam {String} tagId 标签包含信息id
	 * @apiParam {String} includ_tag_id 被包含标签包含信息id
	 * @apiSuccess {Object} data 标签包含信息对象
	 * 
	 */
	@RequestMapping(path = "/crm/ecif/tagmng/tagInclud", method = RequestMethod.GET)
	public Result getTagInclud(String tagId,String includ_tag_id) {
		Result res = new Result();
		EcTagInclud resTagInclud = service.getTagInclude(tagId,includ_tag_id);
		res.setData(resTagInclud);
		return res;
	}

	/**
	 * @api {get} /crm/ecif/tagmng/tagIncluds 查询 - 标签包含信息列表
	 * @apiDescription 
	 * @apiName getTagIncluds
	 * @apiGroup Tag
	 *
	 * @apiParam {String} EcTagInclud.tagId 标签id <选输>
	 * @apiParam {String} EcTagInclud.tagName 标签名称 <选输>
	 * @apiParam {String} EcTagInclud.tagIncludId 被包含标签id <选输>
	 * @apiParam {String} EcTagInclud.tagIncludName 被包含标签名称 <选输>
	 * @apiSuccess {Object[]} data 标签包含信息列表
	 */

	@RequestMapping(path = "/crm/ecif/tagmng/tagIncluds", method = RequestMethod.GET)
	public Result getTagIncluds(EcTagInclud tagInclud) {
		Result res = new Result();
		List<EcTagInclud> tagIncluds = service.getTagIncludes(tagInclud);
		res.setData(tagIncluds);
		return res;
	}

	/**
	 * @api {POST} /crm/ecif/tagmng/tagInclud 新增 - 标签包含信息
	 * @apiDescription 
	 * @apiName addTagInclud
	 * @apiGroup Tag
	 *
	 * @apiParam {Object} EcTagInclud 标签包含信息对象 <必输>
	 * @apiSuccess {int} data 增量
	 */

	@RequestMapping(path = "/crm/ecif/tagmng/tagInclud", method = RequestMethod.POST)
	public Result addTagInclud(EcTagInclud tagInclud) {
		Result res = new Result();
		int addCount = service.addTagInclude(tagInclud);
		if (addCount > 0) {
			System.out.println("插入数据成功。");
		} else {
			throw new ServiceException("新增数据失败！");
		}
		res.setData(addCount);
		return res;
	}

	/**
	 * @api {PUT} /crm/ecif/tagmng/tagInclud 修改 - 标签包含信息
	 * @apiDescription 
	 * @apiName modTagInclud
	 * @apiGroup Tag
	 *
	 * @apiParam {Object} EcTagInclud 标签包含信息对象 <必输>
	 * @apiSuccess {int} data 修改数量
	 */

	@RequestMapping(path = "/crm/ecif/tagmng/tagInclud", method = RequestMethod.PUT)
	public Result modTagInclud(EcTagInclud tagInclud) {
		Result res = new Result();
		int modCount = service.modTagInclude(tagInclud);

		if (modCount > 0) {
			System.out.println("修改数据成功。");
		} else {
			throw new ServiceException("修改数据失败！");
		}
		res.setData(modCount);
		return res;
	}

	/**
	 * @api {PUT} /crm/ecif/tagmng/delModTagInclud 逻辑删除 - 标签包含信息
	 * @apiDescription 
	 * @apiName delModTagInclud
	 * @apiGroup Tag
	 *
	 * @apiParam {Object} EcTagInclud 标签包含信息对象 <必输>
	 * @apiSuccess {int} data 逻辑删除数量
	 */
	@RequestMapping(path = "/crm/ecif/tagmng/delModTagInclud", method = RequestMethod.PUT)
	public Result delModTagInclud(EcTagInclud tagInclud) {
		Result res = new Result();
		int delModCount = service.delModTagInclude(tagInclud);

		if (delModCount > 0) {
			System.out.println("删除数据成功。");
		} else {
			throw new ServiceException("删除数据失败");
		}
		res.setData(delModCount);
		return res;
	}

	/**
	 * @api {DELETE} /crm/ecif/tagmng/tagInclud 物理删除 - 标签包含信息信息
	 * @apiDescription 
	 * @apiName delTagInclud
	 * @apiGroup Tag
	 *
	 * @apiParam {Object} EcTagInclud 标签包含信息对象 <必输>
	 * @apiSuccess {int} data 物理删除数量
	 */
	@RequestMapping(path = "/crm/ecif/tagmng/tagInclud", method = RequestMethod.DELETE)
	public Result delTagInclud(EcTagInclud tagInclud) {
		Result res = new Result();
		int delCount = service.delTagInclude(tagInclud);

		if (delCount > 0) {
			System.out.println("删除数据成功。");
		} else {
			throw new ServiceException("删除数据失败!");
		}

		res.setData(delCount);
		return res;
	}

}
