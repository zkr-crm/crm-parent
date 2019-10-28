
package cn.com.zsyk.crm.manage.web.controller.mngcenter.message;

import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.generator.EnumType;
import cn.com.zsyk.crm.manage.entity.SysMsgConf;
import cn.com.zsyk.crm.manage.service.mngcenter.message.MsgConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


//注册contrller  将类注册为处理器  可以用来处理请求
@RestController
public class MsgConfCtrl {

	@Autowired
	private MsgConfService msgConfService;
	/**
	 * @api {GET} /crm/manage/msgConf/getRemindDefByEntity 按条件查询站内提醒定义列表
	 * @apiDescription
	 * @apiName getRemindDefByEntity
	 * @apiGroup MsgMng
	 *
	 */
	@RequestMapping(path = "/crm/manage/msgConf/getMsgConfByEntity", method = RequestMethod.POST)
	public Result getMsgConfByEntity(SysMsgConf msgId) {
		Result res = new Result();

		//List<SysMsgConf> list = msgConfService.getMsgConfByEntity(msgId);
		res.setData( msgConfService.getMsgConfByEntity(msgId));
		return res;
	}
	/**
	 * @api {GET} /crm/manage/msgConf/addMsgConf 新增短信自动发送列表
	 */
	@RequestMapping(path = "/crm/manage/msgConf/addMsgConf", method = RequestMethod.POST)
	public Result addMsgConf(SysMsgConf msgConf) {
		Result res = new Result();
        int delCount = msgConfService.addMsgConf(msgConf);
        if (delCount <= 0) {
            throw new ServiceException("删除站内提醒定义数据失败！");
        }
		res.setData(delCount);
		return res;
	}
	/**
	 * @api {PUT} /crm/manage/msgConf/updMsgConf 修改短信定义
	 * @apiDescription
	 * @apiName updMsgConf
	 * @apiGroup msgConf
	 *
	 * @apiParam {SysMsgConf} msgId 站内提醒定义对象
	 *
	 * @apiSuccess {int} data 修改记录条数
	 */
	@RequestMapping(path = "/crm/manage/msgConf/updMsgConf", method = RequestMethod.PUT)
	public Result updMsgConf(SysMsgConf msgId) {

		Result res = new Result();
		if(msgId.getMsgEventType().equals(EnumType.EventType.birthday.desc)){
			msgId.setMsgEventType(EnumType.EventType.birthday.value);
		}else  if(msgId.getMsgEventType().equals(EnumType.EventType.renewal_insurance.desc)){
			msgId.setMsgEventType(EnumType.EventType.renewal_insurance.value);
		}
		int updMsg = msgConfService.updMsgConf(msgId);

		res.setData(updMsg);
		return res;
	}
	/**
	 * @api {DELETE} /crm/manage/msgConf/deleteByPrimaryKey 根据主键删除短信
	 * @apiDescription
	 * @apiName deleteByPrimaryKey
	 * @apiGroup MsgMng
	 *
	 * @apiParam {String} msgId 短信定义代码
	 *
	 * @apiSuccess {int} data 删除记录条数
	 */
	@RequestMapping(path = "/crm/manage/msgConf/deleteByPrimaryKey", method = RequestMethod.DELETE)
	public Result deleteByPrimaryKey(String msgId) {
		Result res = new Result();

		int delCount = msgConfService.deleteByPrimaryKey(msgId);
		if (delCount <= 0) {
			throw new ServiceException("删除站内提醒定义数据失败！");
		}
		res.setData(delCount);
		return res;
	}

}




