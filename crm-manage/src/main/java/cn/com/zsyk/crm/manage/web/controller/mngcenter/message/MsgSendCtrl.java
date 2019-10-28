package cn.com.zsyk.crm.manage.web.controller.mngcenter.message;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.JsonUtil;
import cn.com.zsyk.crm.generator.EnumType;
import cn.com.zsyk.crm.manage.bom.MsgInfo;
import cn.com.zsyk.crm.manage.entity.SysMsgSendDef;
import cn.com.zsyk.crm.manage.entity.bo.engine.SysSmsSendDetail;
import cn.com.zsyk.crm.manage.service.mngcenter.message.AppMailService;
import cn.com.zsyk.crm.manage.service.mngcenter.message.MsgSendDefService;
import cn.com.zsyk.crm.manage.service.mngcenter.message.RemindMsgService;
import cn.com.zsyk.crm.manage.service.mngcenter.message.SmsSendService;

@RestController
public class MsgSendCtrl {

	@Autowired
	private MsgSendDefService msgService;
	// 短信service
	@Autowired
	private SmsSendService smsSendService;
	// 微信service TODO
	// 邮件service TODO
	// APPservice TODO
	// 站内信service TODO
	@Autowired
	private AppMailService appMailSendService;
	@Autowired
	private RemindMsgService remindMsgSendService;

	/**
	 * @api {POST} /crm/manage/msgmng/sendMsg 消息发送（发送渠道：短信、微信、 邮件、 APP、站内信）
	 * @apiName sendMsg
	 * @apiGroup MsgMng
	 *
	 * @apiParam {String} msgJson 消息参数json串（MsgInfo）
	 *
	 * @apiSuccess {Object} Response 返回值对象
	 */
	@RequestMapping(path = "/crm/manage/msgmng/sendMsg", method = RequestMethod.POST)
	public Result sendMsg(@RequestBody String msgJson) {
		Result res = new Result();

		if (msgJson == null || "".equals(msgJson)) {
			throw new ServiceException("发送消息参数不能为空！");
		}

		// 获取信息发送参数
		MsgInfo msgInfo = JsonUtil.parseObject(msgJson, MsgInfo.class);

		// 自动发送
		if (EnumType.msgType.automatic.value.equals(msgInfo.getMsgType())) {

			// 信息编码不能为空
			if (msgInfo.getMsgCode() == null || "".equals(msgInfo.getMsgCode())) {
				throw new ServiceException("信息编码不能为空！");
			}

			// 获取消息编码信息
			SysMsgSendDef msgSendDef = msgService.getOneMsg(msgInfo.getMsgCode());

			if (msgSendDef == null) {
				throw new ServiceException("信息定义信息未取到！");
			}

			// 获取发送渠道
			String sendChannels[] = msgSendDef.getSendChannel().split(",");

			// 根据发送渠道发送信息
			for (String channel : sendChannels) {

				/*
				 * 发送渠道： 01-短信 02-微信 03-邮件 04-APP 05-站内信 用逗号隔开
				 */

				// 短信
				if (EnumType.sendChannel.message.value.equals(channel)) {

					// 数据合法性校验
					smsSendService.dataCheck(msgInfo);
					// 数据发送
					smsSendService.sendMsg(msgInfo);
				}
				// 微信
				if (EnumType.sendChannel.WeChat.value.equals(channel)) {
					// TODO
				}
				// 邮件
				if (EnumType.sendChannel.email.value.equals(channel)) {
					// TODO
				}
				// APP
				if (EnumType.sendChannel.app.value.equals(channel)) {
					// TODO
				}
				// 站内信
				if (EnumType.sendChannel.information.value.equals(channel)) {
					// TODO
					appMailSendService.dataCheck(msgInfo);
					appMailSendService.sendMsg(msgInfo);
				}
			}
		} else if (EnumType.msgType.manual.value.equals(msgInfo.getMsgType())) {
			// 手动发送

			// 获取发送渠道
			String sendChannels[] = msgInfo.getChannel().split(",");

			// 根据发送渠道发送信息
			for (String channel : sendChannels) {
				/*
				 * 发送渠道： 01-短信 02-微信 03-邮件 04-APP 05-站内信 用逗号隔开
				 */

				// 短信
				if (EnumType.sendChannel.message.value.equals(channel)) {

					// 数据合法性校验
					smsSendService.dataCheck(msgInfo);
					// 数据发送
					smsSendService.sendMsg(msgInfo);
				}
				// 微信
				if (EnumType.sendChannel.WeChat.value.equals(channel)) {
					// TODO
				}
				// 邮件
				if (EnumType.sendChannel.email.value.equals(channel)) {
					// TODO
				}
				// APP
				if (EnumType.sendChannel.app.value.equals(channel)) {
					// TODO
				}
				// 站内信
				if (EnumType.sendChannel.information.value.equals(channel)) {
					// TODO
					appMailSendService.dataCheck(msgInfo);
					appMailSendService.sendMsg(msgInfo);
				}
			}
		}

		return res;
	}

	/**
	 * @api {POST} /crm/manage/msgmng/sendRemindMsg 提醒消息发送
	 * @apiName sendRemindMsg
	 * @apiGroup MsgMng
	 *
	 * @apiParam {String} msgJson 消息参数json串（MsgInfo）
	 *
	 * @apiSuccess {Object} Response 返回值对象
	 */
	@RequestMapping(path = "/crm/manage/msgmng/sendRemindMsg", method = RequestMethod.POST)
	public Result sendRemindMsg(@RequestBody String msgJson) {
		Result res = new Result();

		if (msgJson == null || "".equals(msgJson)) {
			throw new ServiceException("发送消息参数不能为空！");
		}

		// 获取信息发送参数
		MsgInfo msgInfo = JsonUtil.parseObject(msgJson, MsgInfo.class);

		// 信息编码不能为空
		if (msgInfo.getMsgCode() == null || "".equals(msgInfo.getMsgCode())) {
			throw new ServiceException("信息编码不能为空！");
		}

		// 提醒消息
		if (EnumType.sendChannel.remind.value.equals(msgInfo.getChannel())) {
			// TODO
			remindMsgSendService.dataCheck(msgInfo);
			remindMsgSendService.sendMsg(msgInfo);
		}

		return res;

	}

	/**
	 * @api {GET} /crm/manage/msgmng/selectSendMsgByEntity 按条件查询信息发送定义列表
	 * @apiName selectSendMsgByEntity
	 * @apiGroup MsgMng
	 *
	 * @apiParam {SysMsgSendDef} msg 信息发送对象
	 *
	 * @apiSuccess {SysMsgSendDef} data 信息发送列表
	 */
	@RequestMapping(path = "/crm/manage/msgmng/selectSendMsgByEntity", method = RequestMethod.GET)
	public Result getMsgByEntity(SysSmsSendDetail smsSend) {
		Result res = new Result();
		System.out.println("获取多条信息发送数据。");
		//List<SysSmsSendDetail> msgList = smsSendService.selectByEntity(smsSend);
		res.setData(smsSendService.selectByEntity(smsSend));
		return res;
	}

}
