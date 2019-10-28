package cn.com.zsyk.crm.manage.web.controller.mngcenter.message;

import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.manage.entity.SysRemindRule;
import cn.com.zsyk.crm.manage.service.mngcenter.message.RemindSendDefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RemindDefCtrl {
	@Autowired
	private RemindSendDefService remindDefService;

	/**
	 * @api {GET} /crm/manage/msgmng/getRemindDefByEntity 按条件查询站内提醒定义列表
	 * @apiDescription 
	 * @apiName getRemindDefByEntity
	 * @apiGroup MsgMng
	 *
	 * @apiParam {SysRemindRule} msgTpl 站内提醒定义对象
	 *
	 * @apiSuccess {SysRemindRule} data 站内提醒定义列表
	 */
	@RequestMapping(path = "/crm/manage/msgmng/getRemindDefByEntity", method = RequestMethod.GET)
	public Result getRemindDefByEntity(SysRemindRule msgTpl) {
		Result res = new Result();

		List<SysRemindRule> list = remindDefService.getRemindDefByEntity(msgTpl);
		res.setData(list);
		return res;
	}
	
	/**
	 * @api {GET} /crm/manage/msgmng/getOneRZa\PemindDef 获取一条站内提醒定义信息
	 * @apiDescription 
	 * @apiName getOneRemindDef
	 * @apiGroup MsgMng
	 *
	 * @apiParam {String} ruleId 规则ID
	 *
	 * @apiSuccess {SysRemindRule} data 站内提醒定义列表
	 */
	@RequestMapping(path = "/crm/manage/msgmng/getOneRemindDef", method = RequestMethod.GET)
	public Result getOneRemindDef(String ruleId) {
		Result res = new Result();

		SysRemindRule remindRule  = remindDefService.getOneRemindDef(ruleId);
		res.setData(remindRule);
		return res;
	}

	/**
	 * @api {POST} /crm/manage/msgmng/addRemindDefMsg 新增站内提醒定义
	 * @apiDescription 
	 * @apiName addRemindDefMsg
	 * @apiGroup MsgMng
	 *
	 * @apiParam {SysRemindRule} remindRule 站内提醒定义对象
	 *
	 * @apiSuccess {int} data 新增记录条数
	 */
	@RequestMapping(path = "/crm/manage/msgmng/updRemindDefMsg", method = RequestMethod.POST)
	public Result addRemindDefMsg(SysRemindRule remindRule) {
		Result res = new Result();

		int delCount = remindDefService.addRemindDefMsg(remindRule);
		if (delCount <= 0) {
			throw new ServiceException("删除站内提醒定义数据失败！");
		}
		res.setData(delCount);
		return res;
	}

	/**
	 * @api {PUT} /crm/manage/msgmng/updRemindDefMsg 修改站内提醒定义
	 * @apiDescription 
	 * @apiName updRemindDefMsg
	 * @apiGroup MsgMng
	 *
	 * @apiParam {SysRemindRule} remindRule 站内提醒定义对象
	 *
	 * @apiSuccess {int} data 修改记录条数
	 */
	@RequestMapping(path = "/crm/manage/msgmng/updRemindDefMsg", method = RequestMethod.PUT)
	public Result updRemindDefMsg(SysRemindRule remindRule) {
		Result res = new Result();

		int delCount = remindDefService.updRemindMsg(remindRule);
		if (delCount <= 0) {
			throw new ServiceException("删除站内提醒定义数据失败！");
		}
		res.setData(delCount);
		return res;
	}

	/**
	 * @api {DELETE} /crm/manage/msgmng/delRemindDefMsg 删除站内提醒定义
	 * @apiDescription 
	 * @apiName delRemindDefMsg
	 * @apiGroup MsgMng
	 *
	 * @apiParam {String} ruleId 站内提醒定义代码
	 *
	 * @apiSuccess {int} data 删除记录条数
	 */
	@RequestMapping(path = "/crm/manage/msgmng/delRemindDefMsg", method = RequestMethod.DELETE)
	public Result delRemindDefMsg(String ruleId) {
		Result res = new Result();

		int delCount = remindDefService.delRemindDefMsg(ruleId);
		if (delCount <= 0) {
			throw new ServiceException("删除站内提醒定义数据失败！");
		}
		res.setData(delCount);
		return res;
	}

}
