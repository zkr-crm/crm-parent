package cn.com.zsyk.crm.ecif.web.controller.mngcenter.tag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.ecif.entity.EcTagDetail;
import cn.com.zsyk.crm.ecif.service.mngcenter.tag.TagDetailMngService;

@RestController
public class TagDetailMngCtrl {
	@Autowired
	private TagDetailMngService service;

	/**
	 * @api {get} /crm/ecif/tagmng/tagDetail 查询 - 标签明细（单条）
	 * @apiDescription 
	 * @apiName getTagDetailById
	 * @apiGroup Tag
	 *
	 * @apiParam {String} tagId 标签明细id
	 * @apiSuccess {Object} data 标签明细对象
	 * 
	 */
	@RequestMapping(path = "/crm/ecif/tagmng/tagDetail", method = RequestMethod.GET)
	public Result getTagDetailById(String tagId) {
		Result res = new Result();
		EcTagDetail resTagDetail = service.getOneTag(tagId);
		res.setData(resTagDetail);
		return res;
	}

	/**
	 * @api {get} /crm/ecif/tagmng/tagDetials 查询 - 标签明细列表
	 * @apiDescription 
	 * @apiName getTagDetials
	 * @apiGroup Tag
	 *
	 * @apiParam {String} tagTypeId 标签类型id <必输>
	 * @apiParam {String} tagName 标签名称 <选输>
	 * @apiSuccess {Object[]} data 标签明细列表
	 */

	@RequestMapping(path = "/crm/ecif/tagmng/tagDetials", method = RequestMethod.GET)
	public Result getTagDetials(EcTagDetail tagDetail) {
		Result res = new Result();
		List<EcTagDetail> tagDetails = service.getTagDetails(tagDetail);
		res.setData(tagDetails);
		return res;
	}

	/**
	 * @api {POST} /crm/ecif/tagmng/tagDetial 新增 - 标签明细
	 * @apiDescription 
	 * @apiName addTagDetial
	 * @apiGroup Tag
	 *
	 * @apiParam {Object} EcTagDetail 标签明细对象 <必输>
	 * @apiSuccess {int} data 增量
	 */

	@RequestMapping(path = "/crm/ecif/tagmng/tagDetial", method = RequestMethod.POST)
	public Result addTagDetial(EcTagDetail tagDetail) {
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
	 * @api {PUT} /crm/ecif/tagmng/tagDetial 修改 - 标签明细
	 * @apiDescription 
	 * @apiName modTagDetial
	 * @apiGroup Tag
	 *
	 * @apiParam {Object} EcTagDetail 标签明细对象 <必输>
	 * @apiSuccess {int} data 修改数量
	 */

	@RequestMapping(path = "/crm/ecif/tagmng/tagDetial", method = RequestMethod.PUT)
	public Result modTagDetial(EcTagDetail tagDetail) {
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
	 * @api {PUT} /crm/ecif/tagmng/delModTagDetial 逻辑删除 - 标签明细
	 * @apiDescription 
	 * @apiName delModTagDetial
	 * @apiGroup Tag
	 *
	 * @apiParam {Object} EcTagDetail 标签明细对象 <必输>
	 * @apiSuccess {int} data 逻辑删除数量
	 */
	@RequestMapping(path = "/crm/ecif/tagmng/delModTagDetial", method = RequestMethod.PUT)
	public Result delModTagDetial(EcTagDetail tagDetail) {
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
	 * @api {DELETE} /crm/ecif/tagmng/tagDetial 物理删除 - 标签明细
	 * @apiDescription 
	 * @apiName delTagDetial
	 * @apiGroup Tag
	 *
	 * @apiParam {Object} EcTagDetail 标签明细对象 <必输>
	 * @apiSuccess {int} data 物理删除数量
	 */
	@RequestMapping(path = "/crm/ecif/tagmng/tagDetial", method = RequestMethod.DELETE)
	public Result delTagDetial(EcTagDetail tagDetail) {
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

}
