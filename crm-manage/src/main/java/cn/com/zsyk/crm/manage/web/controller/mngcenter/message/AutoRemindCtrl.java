
package cn.com.zsyk.crm.manage.web.controller.mngcenter.message;

import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.generator.EnumType;
import cn.com.zsyk.crm.manage.entity.SysAutoRemind;
import cn.com.zsyk.crm.manage.service.mngcenter.message.AutoRemindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


//注册contrller  将类注册为处理器  可以用来处理请求
@RestController
public class AutoRemindCtrl {

	@Autowired
	private AutoRemindService autoRemindService;
	/**
	 * @api {POST} /crm/manage/autoRemind/getAutoRemindByEntity 按条件查询站内提醒定义列表
	 */
	@RequestMapping(path = "/crm/manage/autoRemind/getAutoRemindByEntity", method = RequestMethod.POST)
	public Result getAutoRemindByEntity(SysAutoRemind msgId) {
		Result res = new Result();
		//List<SysAutoRemind> list = autoRemindService.getAutoRemindByEntity(msgId);
		res.setData(autoRemindService.getAutoRemindByEntity(msgId));
		return res;
	}
	/**
	 * @api {POST} /crm/manage/autoRemind/addAutoRemind 新增短信自动发送列表
	 */
	@RequestMapping(path = "/crm/manage/autoRemind/addAutoRemind", method = RequestMethod.POST)
	public Result addAutoRemind(SysAutoRemind autoRemind) {
		Result res = new Result();
        int delCount = autoRemindService.addAutoRemind(autoRemind);
        if (delCount <= 0) {
            throw new ServiceException("删除站内提醒定义数据失败！");
        }
		res.setData(delCount);
		return res;
	}
	/**
	 * @api {PUT} /crm/manage/autoRemind/updAutoRemind 修改短信定义
	 * @apiDescription
	 * @apiName updAutoRemind
	 * @apiGroup autoRemind
	 *
	 * @apiParam {SysAutoRemind} msgId 站内提醒定义对象
	 *
	 * @apiSuccess {int} data 修改记录条数
	 */
	@RequestMapping(path = "/crm/manage/autoRemind/updAutoRemind", method = RequestMethod.PUT)
	public Result updAutoRemind(SysAutoRemind msgId) {
		if(msgId.getMsgEventType().equals(EnumType.EventType.birthday.desc)){
			msgId.setMsgEventType(EnumType.EventType.birthday.value);
		}else  if(msgId.getMsgEventType().equals(EnumType.EventType.renewal_insurance.desc)){
			msgId.setMsgEventType(EnumType.EventType.renewal_insurance.value);
		}else  if(msgId.getMsgEventType().equals(EnumType.EventType.memorial_day.desc)){
			msgId.setMsgEventType(EnumType.EventType.memorial_day.value);
		}else  if(msgId.getMsgEventType().equals(EnumType.EventType.bad_weather.desc)){
			msgId.setMsgEventType(EnumType.EventType.bad_weather.value);
		}
		Result res = new Result();

		int updMsg = autoRemindService.updAutoRemind(msgId);

		res.setData(updMsg);
		return res;
	}
	/**
	 * @api {DELETE} /crm/manage/autoRemind/deleteByPrimaryKey 根据主键删除短信
	 * @apiDescription
	 * @apiName deleteByPrimaryKey
	 * @apiGroup MsgMng
	 *
	 * @apiParam {String} msgId 短信定义代码
	 *
	 * @apiSuccess {int} data 删除记录条数
	 */
	@RequestMapping(path = "/crm/manage/autoRemind/deleteByPrimaryKey", method = RequestMethod.DELETE)
	public Result deleteByPrimaryKey(String msgId) {
		Result res = new Result();

		int delCount = autoRemindService.deleteByPrimaryKey(msgId);
		if (delCount <= 0) {
			throw new ServiceException("删除站内提醒定义数据失败！");
		}
		res.setData(delCount);
		return res;
	}

}




