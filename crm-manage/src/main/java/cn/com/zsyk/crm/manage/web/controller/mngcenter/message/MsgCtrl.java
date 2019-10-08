package cn.com.zsyk.crm.manage.web.controller.mngcenter.message;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.com.zsyk.crm.common.annotation.SysOprtLog;
import cn.com.zsyk.crm.common.annotation.SysOprtLog.Module;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.manage.entity.SysMsgSendDef;
import cn.com.zsyk.crm.manage.entity.SysUserInfo;
import cn.com.zsyk.crm.manage.entity.bo.engine.SysMsgSendDetail;
import cn.com.zsyk.crm.manage.service.mngcenter.message.MsgSendDefService;

@RestController
public class MsgCtrl {
	@Autowired
	private MsgSendDefService msgService;
	@Autowired
	private ObjectMapper om;
	/**
	 * @api {GET} /crm/manage/msgmng/getMsgByEntity 按条件查询信息发送定义列表
	 * @apiName getMsgByEntity
	 * @apiGroup MsgMng
	 *
	 * @apiParam {SysMsgSendDef} msg 信息发送定义对象
	 *
	 * @apiSuccess {SysMsgSendDef} data 信息发送定义列表
	 */
	@RequestMapping(path = "/crm/manage/msgmng/getMsgByEntity", method = RequestMethod.GET)
	public Result getMsgByEntity(SysMsgSendDef msg) {
		Result res = new Result();
		System.out.println("获取多条信息发送定义数据。");
		List<SysMsgSendDetail> msgList = msgService.getMsgByEntity(msg);
		res.setData(msgList);
		return res;
	}
	/**
	 * @api {GET} /crm/manage/msgmng/getOneMsg 按条件查询一条信息发送定义
	 * @apiName getOneMsg
	 * @apiGroup MsgMng
	 *
	 * @apiParam {String} msgCode 信息发送定义对象
	 *
	 * @apiSuccess {SysMsgSendDef} data 信息发送定义对象
	 */
	@RequestMapping(path = "/crm/manage/msgmng/getOneMsg", method = RequestMethod.GET)
	public Result getOneMsg(String msgCode) {
		Result res = new Result();
		System.out.println("获取单条信息发送定义数据。");
		SysMsgSendDef msg = msgService.getOneMsg(msgCode);
		res.setData(msg);
		return res;
	}
	/**
	 * @api {POST} /crm/manage/msgmng/addMsg 新增信息发送定义--信息状态：0未生效
	 * @apiName addMsg
	 * @apiGroup MsgMng
	 *
	 * @apiParam {SysMsgSendDef} msg 信息发送定义对象
	 *
	 * @apiSuccess  {int} data 新增记录条数
	 */
	@RequestMapping(path = "/crm/manage/msgmng/addMsg", method = RequestMethod.POST)
	public Result addMsg(SysMsgSendDef msg) {
		Result res = new Result();
		System.out.println("新增信息发送定义。");
		msg.setMsgStat("0");
		int addCount = msgService.addMsg(msg);
		if (addCount > 0) {
			System.out.println("插入信息发送定义数据成功。");
		} else {
			throw new ServiceException("新增信息发送定义数据失败！");
		}
		res.setData(addCount);
		return res;
	}
	/**
	 * @api {POST} /crm/manage/msgmng/saveMsg 新增信息发送定义--信息状态：1生效
	 * @apiName saveMsg
	 * @apiGroup MsgMng
	 *
	 * @apiParam {SysMsgSendDef} msg 信息发送定义对象
	 *
	 * @apiSuccess  {int} data 新增记录条数
	 */
	@RequestMapping(path = "/crm/manage/msgmng/saveMsg", method = RequestMethod.POST)
	public Result saveMsg(SysMsgSendDef msg) {
		Result res = new Result();
		System.out.println("新增信息发送定义。");
		msg.setMsgStat("1");
		int addCount = msgService.addMsg(msg);
		if (addCount > 0) {
			System.out.println("插入信息发送定义数据成功。");
		} else {
			throw new ServiceException("新增信息发送定义数据失败！");
		}
		res.setData(addCount);
		return res;
	}
	
	/**
	 * @api {PUT} /crm/manage/msgmng/modMsg 修改信息发送定义
	 * @apiName modMsg
	 * @apiGroup MsgMng
	 *
	 * @apiParam {SysMsgSendDef} msg 信息发送定义对象
	 *
	 * @apiSuccess  {int} data 修改记录条数
	 */
	@RequestMapping(path = "/crm/manage/msgmng/modMsg", method = RequestMethod.PUT)
	public Result modMsg(SysMsgSendDef msg) {
		Result res = new Result();
		System.out.println("修改信息发送定义。");
		int updCount = msgService.modMsg(msg);
		if (updCount > 0) {
			System.out.println("修改信息发送定义数据成功。");
		} else {
			throw new ServiceException("修改信息发送定义数据失败！");
		}
		res.setData(updCount);
		return res;
	}
	/**
	 * @api {PUT} /crm/manage/msgmng/updSaveMsg 修改信息发送定义为生效
	 * @apiName updSaveMsg
	 * @apiGroup MsgMng
	 *
	 * @apiParam {SysMsgSendDef} msg 信息发送定义对象
	 *
	 * @apiSuccess  {int} data 修改记录条数
	 */
	@RequestMapping(path = "/crm/manage/msgmng/updSaveMsg", method = RequestMethod.PUT)
	public Result updSaveMsg(SysMsgSendDef msg) {
		Result res = new Result();
		System.out.println("修改信息发送定义。");
		msg.setMsgStat("1");
		int updCount = msgService.updSaveMsg(msg);
		if (updCount > 0) {
			System.out.println("修改信息发送定义数据成功。");
		} else {
			throw new ServiceException("修改信息发送定义数据失败！");
		}
		res.setData(updCount);
		return res;
	}
	/**
	 * @api {DELETE} /crm/manage/msgmng/delMsg 删除信息发送定义
	 * @apiName delMsg
	 * @apiGroup MsgMng
	 *
	 * @apiParam {SysMsgSendDef} msg 信息发送定义对象
	 *
	 * @apiSuccess  {int} data 删除记录条数
	 */
	@RequestMapping(path = "/crm/manage/msgmng/delMsg", method = RequestMethod.DELETE)
	public Result delMsg(SysMsgSendDef msg) {
		Result res = new Result();
		System.out.println("删除信息发送定义。");
		int delCount = msgService.delMsg(msg.getMsgCode());
		if (delCount > 0) {
			System.out.println("删除信息发送定义数据成功。");
		} else {
			throw new ServiceException("删除信息发送定义数据失败！");
		}
		res.setData(delCount);
		return res;
	}
	/**
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * @api {DELETE} /crm/manage/msgmng/delMsgByKey 根据json串传递主键列表, 删除信息发送定义
	 * @apiName delMsgByKey
	 * @apiGroup MsgMng
	 *
	 * @apiParam {String} jsonStr json对象字符串(格式同List<SysMsgSendDef>对象一致)
	 *
	 * @apiSuccess  {int} data 删除记录条数
	 */
	
	@RequestMapping(path = "/crm/manage/msgmng/delMsgByKey", method = RequestMethod.DELETE)
	public Result delMsgByKey(@RequestBody String checkedRow) throws Exception {
		Result res = new Result();
		System.out.println("逻辑删除多条信息发送定义数据。");

		List<SysMsgSendDef> msgList = om.readValue(checkedRow, new TypeReference<List<SysMsgSendDef>>() {
		});

		// 删除的记录条数
		int delCount = 0;

		for (SysMsgSendDef item : msgList) {

			// 执行删除操作
			int addCount = msgService.delMsg(item.getMsgCode());
			// 删除是否成功
			if (addCount > 0) {
				System.out.println("删除信息发送定义数据成功。");
			} else {
				throw new ServiceException("删除信息发送定义数据失败!");
			}
			delCount += addCount;
		}

		res.setData(delCount);
		res.setMessage("交易成功！");
		return res;
	}
}
