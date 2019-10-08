package cn.com.zsyk.crm.manage.web.controller.mngcenter.message;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.manage.entity.SysAppMail;
import cn.com.zsyk.crm.manage.entity.SysAppRemind;
import cn.com.zsyk.crm.manage.service.mngcenter.message.AppMailService;
import cn.com.zsyk.crm.manage.service.mngcenter.message.RemindMsgService;

@RestController
public class AppMsgCtrl {
	@Autowired
	private RemindMsgService remindMsgService;
	@Autowired
	private AppMailService appMailService;

	/**
	 * @api {GET} /crm/manage/msgmng/getAppRemindByEntity 按条件查询站内提醒列表
	 * @apiDescription 
	 * @apiName getAppRemindByEntity
	 * @apiGroup MsgMng
	 *
	 * @apiParam {SysAppRemind} msgTpl 站内提醒对象
	 *
	 * @apiSuccess {SysAppRemind} data 站内提醒列表
	 */
	@RequestMapping(path = "/crm/manage/msgmng/getAppRemindByEntity", method = RequestMethod.GET)
	public Result getAppRemindByEntity(SysAppRemind msgTpl) {
		Result res = new Result();

		//List<SysAppRemind> list = remindMsgService.getAppRemindByEntity(msgTpl);
		res.setData(remindMsgService.getAppRemindByEntity(msgTpl));
		return res;
	}

	/**
	 * @api {GET} /crm/manage/msgmng/selectTop10ByEntity 按条件查询站内提醒列表前10条
	 * @apiDescription 
	 * @apiName selectTop10ByEntity
	 * @apiGroup MsgMng
	 *
	 * @apiParam {SysAppRemind} msgTpl 站内提醒对象
	 *
	 * @apiSuccess {SysAppRemind} data 站内提醒列表
	 */
	@RequestMapping(path = "/crm/manage/msgmng/selectTop10ByEntity", method = RequestMethod.GET)
	public Result selectTop10ByEntity(SysAppRemind msgTpl) {
		Result res = new Result();

		List<SysAppRemind> list = remindMsgService.selectTop10ByEntity(msgTpl);
		res.setData(list);
		return res;
	}

	/**
	 * @api {GET} /crm/manage/msgmng/getRemindByEmployeeId 根据员工ID获取员工所有的站内提醒信息
	 * @apiDescription 
	 * @apiName getRemindByEmployeeId
	 * @apiGroup MsgMng
	 *
	 * @apiParam {String} employeeID 员工ID
	 *
	 * @apiSuccess {SysAppRemind} data 站内提醒对象列表
	 */
	@RequestMapping(path = "/crm/manage/msgmng/getRemindByEmployeeId", method = RequestMethod.GET)
	public Result getRemindByEmployeeId(String employeeID) {
		Result res = new Result();

		List<SysAppRemind> msgTplList = remindMsgService.getAppRemindByEmployeeId(employeeID);
		res.setData(msgTplList);
		return res;
	}

	/**
	 * @api {DELETE} /crm/manage/msgmng/delRemindMsg 删除站内提醒
	 * @apiDescription 
	 * @apiName delRemindMsg
	 * @apiGroup MsgMng
	 *
	 * @apiParam {String} remindId 站内提醒代码
	 *
	 * @apiSuccess {int} data 删除记录条数
	 */
	@RequestMapping(path = "/crm/manage/msgmng/delRemindMsg", method = RequestMethod.DELETE)
	public Result delRemindMsg(String remindId) {
		Result res = new Result();

		int delCount = remindMsgService.delRemindMsg(remindId);
		if (delCount <= 0) {
			throw new ServiceException("删除站内提醒数据失败！");
		}
		res.setData(delCount);
		return res;
	}

	/**
	 * @api {PUT} /crm/manage/msgmng/modAllIsReadStat 批量更新未读为已读
	 * @apiDescription 
	 * @apiName modAllIsReadStat
	 * @apiGroup MsgMng
	 *
	 * @apiParam {String} remindId 站内提醒代码
	 *
	 * @apiSuccess {int} data 更新记录条数
	 */
	@RequestMapping(path = "/crm/manage/msgmng/modAllIsReadStat", method = RequestMethod.POST)
	public Result modAllIsReadStat(@RequestBody List<String> remindIdList) {
		Result res = new Result();

		int count = 0;

		count = remindMsgService.modAllIsReadStat(remindIdList);

		if (count <= 0) {
			throw new ServiceException("更新站内提醒已读未读状态失败！");
		}
		res.setData(count);
		return res;
	}

	/**
	 * @api {PUT} /crm/manage/msgmng/modIsReadStat 更新站内提醒的已读未读状态
	 * @apiDescription 
	 * @apiName modIsReadStat
	 * @apiGroup MsgMng
	 *
	 * @apiParam {String} remindId 站内提醒代码
	 *
	 * @apiSuccess {int} data 更新记录条数
	 */
	@RequestMapping(path = "/crm/manage/msgmng/modIsReadStat", method = RequestMethod.PUT)
	public Result modIsReadStat(String remindId) {
		Result res = new Result();

		int delCount = remindMsgService.modIsReadStat(remindId);
		if (delCount <= 0) {
			throw new ServiceException("更新站内提醒已读未读状态失败！");
		}
		res.setData(delCount);
		return res;
	}

	/*****************************************
	 * 站内信的分隔线
	 *****************************************/
	/**
	 * @api {GET} /crm/manage/msgmng/getAppMailByEntity 按条件查询站内提醒列表
	 * @apiDescription 
	 * @apiName getAppMailByEntity
	 * @apiGroup MsgMng
	 *
	 * @apiParam {SysAppMail} msgTpl 站内提醒对象
	 *
	 * @apiSuccess {SysAppMail} data 站内提醒列表
	 */
	@RequestMapping(path = "/crm/manage/msgmng/getAppMailByEntity", method = RequestMethod.GET)
	public Result getAppMailByEntity(SysAppMail appMail) {
		Result res = new Result();

		//List<SysAppMail> list = appMailService.getAppMailByEntity(appMail);
		res.setData(appMailService.getAppMailByEntity(appMail));
		return res;
	}

	/**
	 * @api {POST} /crm/manage/msgmng/addAppMailList 批量新增站内信
	 * @apiDescription 
	 * @apiName addAppMailList
	 * @apiGroup MsgMng
	 *
	 * @apiParam {SysAppMail} appMailList 站内信list对象json串
	 *
	 * @apiSuccess {int} data 更新记录条数
	 */
	@RequestMapping(path = "/crm/manage/msgmng/addAppMailList", method = RequestMethod.POST)
	public Result addAppMailList(@RequestBody String sysMail) {
		Result res = new Result();
		int delCount = appMailService.addAppMailList(sysMail);
		if (delCount <= 0) {
			throw new ServiceException("更新站内信已读未读状态失败！");
		}
		res.setData(delCount);
		return res;
	}

	/**
	 * @api {PUT} /crm/manage/msgmng/modMailIsReadStat 更新站内信的已读未读状态
	 * @apiDescription 
	 * @apiName modMailIsReadStat
	 * @apiGroup MsgMng
	 *
	 * @apiParam {String} mailId 站内信代码
	 *
	 * @apiSuccess {int} data 更新记录条数
	 */
	@RequestMapping(path = "/crm/manage/msgmng/modMailIsReadStat", method = RequestMethod.PUT)
	public Result modMailIsReadStat(String mailId) {
		Result res = new Result();

		int delCount = appMailService.modMailIsReadStat(mailId);
		if (delCount <= 0) {
			throw new ServiceException("更新站内信已读未读状态失败！");
		}
		res.setData(delCount);
		return res;
	}

	/**
	 * @api {DELETE} /crm/manage/msgmng/delAppMail 删除站内信
	 * @apiDescription 
	 * @apiName delAppMail
	 * @apiGroup MsgMng
	 *
	 * @apiParam {String} mailId 站内信代码
	 *
	 * @apiSuccess {int} data 删除记录条数
	 */
	@RequestMapping(path = "/crm/manage/msgmng/delAppMail", method = RequestMethod.DELETE)
	public Result delAppMail(String mailId) {
		Result res = new Result();

		int delCount = appMailService.delAppMail(mailId);
		if (delCount <= 0) {
			throw new ServiceException("删除站内信数据失败！");
		}
		res.setData(delCount);
		return res;
	}
	
	/**
	 * @api {PUT} /crm/manage/msgmng/modAllIsReadStat 批量更新未读为已读
	 * @apiDescription 
	 * @apiName modAllIsReadStat
	 * @apiGroup MsgMng
	 *
	 * @apiParam {String} remindId 站内提醒代码
	 *
	 * @apiSuccess {int} data 更新记录条数
	 */
	@RequestMapping(path = "/crm/manage/msgmng/modAllMailIsReadStat", method = RequestMethod.POST)
	public Result modAllMailIsReadStat(@RequestBody List<String> mailIdList) {
		Result res = new Result();

		int count = 0;

		count = remindMsgService.modAllMailIsReadStat(mailIdList);

		if (count <= 0) {
			throw new ServiceException("更新站内提醒已读未读状态失败！");
		}
		res.setData(count);
		return res;
	}
}
