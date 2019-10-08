package cn.com.zsyk.crm.ecif.web.controller.mngcenter.tag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.ecif.entity.EcTagMutex;
import cn.com.zsyk.crm.ecif.service.mngcenter.tag.TagMutexMngService;

@RestController
public class TagMutexMngCtrl {
	@Autowired
	private TagMutexMngService service;

	/**
	 * @api {get} /crm/ecif/tagmng/tagMutex 查询 - 标签互斥信息信息（单条）
	 * @apiDescription 
	 * @apiName getTagMutex
	 * @apiGroup Tag
	 *
	 * @apiParam {String} EcTagMutex.rule_seq_no 标签互斥信息id
	 * @apiParam {String} EcTagMutex.tagId 标签id
	 * @apiSuccess {Object} data 标签互斥信息对象
	 * 
	 */
	@RequestMapping(path = "/crm/ecif/tagmng/tagMutex", method = RequestMethod.GET)
	public Result getTagMutex(EcTagMutex tagMutex) {
		Result res = new Result();
		EcTagMutex resTagMutex = service.getTagMutex(tagMutex.getRuleSeqNo(), tagMutex.getTagId());
		res.setData(resTagMutex);
		return res;
	}

	/**
	 * @api {get} /crm/ecif/tagmng/tagMutexs 查询 - 标签互斥信息列表
	 * @apiDescription 
	 * @apiName getTagMutexs
	 * @apiGroup Tag
	 *
	 * @apiParam {String} EcTagMutex.rule_seq_no 标签互斥id <选输>
	 * @apiParam {String} EcTagMutex.tagId 标签id<选输>
	 * @apiParam {String} EcTagMutex.tagName 标签名称<选输>
	 * @apiSuccess {Object[]} data 标签互斥信息列表
	 */

	@RequestMapping(path = "/crm/ecif/tagmng/tagMutexs", method = RequestMethod.GET)
	public Result getTagMutexs(EcTagMutex tagMutex) {
		Result res = new Result();
		List<EcTagMutex> tagMutexs = service.getTagMutexes(tagMutex);
		res.setData(tagMutexs);
		return res;
	}

	/**
	 * @api {POST} /crm/ecif/tagmng/tagMutex 新增 - 标签互斥信息(单条)
	 * @apiDescription 
	 * @apiName addTagMutex
	 * @apiGroup Tag
	 *
	 * @apiParam {Object} EcTagMutex 标签互斥信息对象 <必输>
	 * @apiSuccess {int} data 增量
	 */

	@RequestMapping(path = "/crm/ecif/tagmng/tagMutex", method = RequestMethod.POST)
	public Result addTagMutex(EcTagMutex tagMutex) {
		Result res = new Result();
		int addCount = service.addTagMutex(tagMutex);
		if (addCount > 0) {
			System.out.println("插入数据成功。");
		} else {
			throw new ServiceException("新增数据失败！");
		}
		res.setData(addCount);
		return res;
	}

	/**
	 * @api {POST} /crm/ecif/tagmng/tagMutex 新增 - 标签互斥信息(组)
	 * @apiDescription 
	 * @apiName addTagMutexes
	 * @apiGroup Tag
	 *
	 * @apiParam {String[]} tagMutexes 标签id数组 <必输>
	 * @apiSuccess {String} data 互斥组id
	 */

	@RequestMapping(path = "/crm/ecif/tagmng/tagMutexes", method = RequestMethod.POST)
	public Result addTagMutexes(String[] tagMutexes) {
		Result res = new Result();
		String rule_seq_no = service.addTagMutexes(tagMutexes);
		if (rule_seq_no == null || rule_seq_no == "") {
			throw new ServiceException("新增数据失败！");
		} else {
			System.out.println("插入数据成功。");
		}
		res.setData(rule_seq_no);
		return res;
	}

	/**
	 * @api {PUT} /crm/ecif/tagmng/delModTagMutexes 逻辑删除 - 标签互斥组
	 * @apiDescription 
	 * @apiName delModTagMutexes
	 * @apiGroup Tag
	 *
	 * @apiParam {String} EcTagMutex.rule_seq_no 标签互斥组id <必输>
	 * @apiSuccess {int} data 逻辑删除数量
	 */
	@RequestMapping(path = "/crm/ecif/tagmng/delModTagMutexes", method = RequestMethod.PUT)
	public Result delModTagMutexes(EcTagMutex TagMutex) {
		Result res = new Result();
		int delModCount = service.delModTagMutexes(TagMutex);

		if (delModCount > 0) {
			System.out.println("删除数据成功。");
		} else {
			throw new ServiceException("删除数据失败");
		}
		res.setData(delModCount);
		return res;
	}

	/**
	 * @api {PUT} /crm/ecif/tagmng/delModTagMutex 逻辑删除 - 标签互斥(单条)
	 * @apiDescription 
	 * @apiName delModTagMutex
	 * @apiGroup Tag
	 *
	 * @apiParam {String} EcTagMutex.rule_seq_no 标签互斥组id <必输>
	 * @apiParam {String} EcTagMutex.tagId 标签id <必输>
	 * @apiSuccess {int} data 逻辑删除数量
	 */
	@RequestMapping(path = "/crm/ecif/tagmng/delModTagMutex", method = RequestMethod.PUT)
	public Result delModTagMutex(EcTagMutex TagMutex) {
		Result res = new Result();
		int delModCount = service.delModTagMutex(TagMutex);

		if (delModCount > 0) {
			System.out.println("删除数据成功。");
		} else {
			throw new ServiceException("删除数据失败");
		}
		res.setData(delModCount);
		return res;
	}

}
