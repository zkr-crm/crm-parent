package cn.com.zsyk.crm.ecif.web.controller.mngcenter.tag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.ecif.entity.EcTagRule;
import cn.com.zsyk.crm.ecif.service.mngcenter.tag.TagRuleMngService;

@RestController
public class TagRuleMngCtrl {
	@Autowired
	private TagRuleMngService service;

	/**
	 * @api {get} /crm/ecif/tagmng/tagRule 查询 - 标签规则信息（单条）
	 * @apiDescription 
	 * @apiName getTagRule
	 * @apiGroup Tag
	 *
	 * @apiParam {int} seqNo 标签规则序号
	 * @apiParam {String} tagId 标签id
	 * @apiSuccess {Object} data 标签规则对象
	 * 
	 */
	@RequestMapping(path = "/crm/ecif/tagmng/tagRule", method = RequestMethod.GET)
	public Result getTagRule(int seqNo, String tagId) {
		Result res = new Result();
		EcTagRule resTagRule = service.getOneTagRule(seqNo, tagId);
		res.setData(resTagRule);
		return res;
	}

	/**
	 * @api {get} /crm/ecif/tagmng/TagRules 查询 - 标签规则信息列表
	 * @apiDescription 
	 * @apiName getTagRules
	 * @apiGroup Tag
	 *
	 * @apiParam {String} tagId 标签id <选输>
	 * @apiParam {String} tagTypeId 标签类别id <选输>
	 * @apiParam {String} tagName 标签名称 <选输>
	 * @apiParam {String} tagRuleName 标签规则名称 <选输>
	 * @apiParam {String} attrName 属性 <选输>
	 * @apiSuccess {Object[]} data 标签规则对象列表
	 */

	@RequestMapping(path = "/crm/ecif/tagmng/TagRules", method = RequestMethod.GET)
	public Result getTagRules(EcTagRule tagRule) {
		Result res = new Result();
		List<EcTagRule> TagRules = service.getTagRules(tagRule);
		res.setData(TagRules);
		return res;
	}

	/**
	 * @api {POST} /crm/ecif/tagmng/tagRule 新增 - 标签规则信息
	 * @apiDescription 
	 * @apiName addTagRule
	 * @apiGroup Tag
	 *
	 * @apiParam {Object} EcTagRule 标签规则对象 <必输>
	 * @apiSuccess {int} data 增量
	 */

	@RequestMapping(path = "/crm/ecif/tagmng/tagRule", method = RequestMethod.POST)
	public Result addTagRule(EcTagRule tagRule) {
		Result res = new Result();
		int addCount = service.addTagRule(tagRule);
		if (addCount > 0) {
			System.out.println("插入数据成功。");
		} else {
			throw new ServiceException("新增数据失败！");
		}
		res.setData(addCount);
		return res;
	}

	/**
	 * @api {PUT} /crm/ecif/tagmng/tagRule 修改 - 标签规则信息
	 * @apiDescription 
	 * @apiName modTagRule
	 * @apiGroup Tag
	 *
	 * @apiParam {Object} EcTagRule 标签规则对象 <必输>
	 * @apiSuccess {int} data 修改数量
	 */

	@RequestMapping(path = "/crm/ecif/tagmng/tagRule", method = RequestMethod.PUT)
	public Result modTagRule(EcTagRule tagRule) {
		Result res = new Result();
		int modCount = service.modTagRule(tagRule);

		if (modCount > 0) {
			System.out.println("修改数据成功。");
		} else {
			throw new ServiceException("修改数据失败！");
		}
		res.setData(modCount);
		return res;
	}

	/**
	 * @api {PUT} /crm/ecif/tagmng/delModTagRule 逻辑删除 - 标签规则信息
	 * @apiDescription 
	 * @apiName delModTagRule
	 * @apiGroup Tag
	 *
	 * @apiParam {Object} EcTagRule 标签规则对象 <必输>
	 * @apiSuccess {int} data 逻辑删除数量
	 */
	@RequestMapping(path = "/crm/ecif/tagmng/delModTagRule", method = RequestMethod.PUT)
	public Result delModTagRule(EcTagRule tagRule) {
		Result res = new Result();
		int delModCount = service.delModTagRule(tagRule);

		if (delModCount > 0) {
			System.out.println("删除数据成功。");
		} else {
			throw new ServiceException("删除数据失败");
		}
		res.setData(delModCount);
		return res;
	}

	/**
	 * @api {DELETE} /crm/ecif/tagmng/tagRule 物理删除 - 标签规则信息
	 * @apiDescription 
	 * @apiName delTagRule
	 * @apiGroup Tag
	 *
	 * @apiParam {Object} EcTagRule 标签规则对象 <必输>
	 * @apiSuccess {int} data 物理删除数量
	 */
	@RequestMapping(path = "/crm/ecif/tagmng/tagRule", method = RequestMethod.DELETE)
	public Result delTagRule(EcTagRule tagRule) {
		Result res = new Result();
		int delCount = service.delTagRule(tagRule);

		if (delCount > 0) {
			System.out.println("删除数据成功。");
		} else {
			throw new ServiceException("删除数据失败!");
		}

		res.setData(delCount);
		return res;
	}

}
