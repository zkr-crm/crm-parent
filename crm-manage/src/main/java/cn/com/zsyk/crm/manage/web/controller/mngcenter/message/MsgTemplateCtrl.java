package cn.com.zsyk.crm.manage.web.controller.mngcenter.message;

import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.manage.entity.SysMsgTemplate;
import cn.com.zsyk.crm.manage.service.mngcenter.message.MsgTemplateService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class MsgTemplateCtrl {
	@Autowired
	private MsgTemplateService msgTplService;
	@Autowired
	private ObjectMapper om;

	/**
	 * @api {GET} /crm/manage/msgmng/getMsgTplByEntity 按条件查询消息模板列表
	 * @apiName getMsgTplByEntity
	 * @apiGroup MsgMng
	 *
	 * @apiParam {SysMsgTemplate} msgTpl 消息模板对象
	 *
	 * @apiSuccess {SysMsgTemplate} data 消息模板列表
	 */
	@RequestMapping(path = "/crm/manage/msgmng/getMsgTplByEntity", method = RequestMethod.GET)
	public Result getMsgTplByEntity(SysMsgTemplate msgTpl) {
		Result res = new Result();

		// List<SysMsgTemplate> msgList = msgTplService.getMsgByEntity(msgTpl);
		// res.setData(msgList);
		return res;
	}

	/**
	 * @api {GET} /crm/manage/msgmng/getOneMsgTemplate 按条件查询一条消息模板
	 * @apiName getOneMsgTemplate
	 * @apiGroup MsgMng
	 *
	 * @apiParam {String} msgCode 消息模板代码
	 *
	 * @apiSuccess {SysMsgTemplate} data 消息模板对象
	 */
	@RequestMapping(path = "/crm/manage/msgmng/getOneMsgTemplate", method = RequestMethod.GET)
	public Result getOneMsgTemplate(String tplNo) {
		Result res = new Result();

		SysMsgTemplate msgTpl = msgTplService.getOneMsgTemplate(tplNo);
		res.setData(msgTpl);
		return res;
	}
	/**
	 * @api {GET} /crm/manage/msgmng/getMsgTemplateByEntity 按条件查询多条消息模板
	 * @apiName getOneMsgTemplate
	 * @apiGroup MsgMng
	 *
	 * @apiParam {SysMsgTemplate} msgTpl 消息模板对象
	 *
	 * @apiSuccess {SysMsgTemplate} data 消息模板列表
	 */
	@RequestMapping(path = "/crm/manage/msgmng/getMsgTemplateByEntity", method = RequestMethod.GET)
	public Result getMsgTemplateByEntity(SysMsgTemplate msgTpl) {
		Result res = new Result();
		System.out.println("获取多条消息模板数据。");
		//List<SysMsgTemplate> msgList = msgTplService.selectByEntity(msgTpl);
		res.setData(msgTplService.selectByEntity(msgTpl));
		return res;
	}
	/**
	 * @api {GET} /crm/manage/msgmng/selectByTime 按时间查询多条消息模板
	 * @apiName selectByTime
	 * @apiGroup MsgMng
	 *
	 * @apiSuccess {SysMsgTemplate} data 消息模板列表
	 */
	@RequestMapping(path = "/crm/manage/msgmng/selectByTime", method = RequestMethod.GET)
	public Result selectByTime() {
		Result res = new Result();
		System.out.println("获取多条消息模板数据。");
		List<SysMsgTemplate> msgList = msgTplService.selectByTime();
		res.setData(msgList);
		return res;
	}
	/**
	 * @api {GET} /crm/manage/msgmng/selectMsgConfTmpt 按条件查询一条消息模板
	 * @apiName selectMsgConfTmpt
	 * @apiGroup msgmng
	 *
	 * @apiParam {String} msgCode 消息模板代码
	 *
	 * @apiSuccess {SysMsgTemplate} data 消息模板对象
	 */
	@RequestMapping(path = "/crm/manage/msgmng/getMsgConfTmpt", method = RequestMethod.GET)
	public Result getMsgConfTmpt(String tplNo) {
		Result res = new Result();

		SysMsgTemplate msgTpl = msgTplService.getMsgConfTmpt(tplNo);
		res.setData(msgTpl);
		return res;
	}
	/**
	 * @api {POST} /crm/manage/msgmng/addTemplate 新增消息模板--信息状态：0未生效
	 * @apiName addTemplate
	 * @apiGroup MsgMng
	 *
	 * @apiParam {SysMsgTemplate} msgTpl 消息模板对象
	 *
	 * @apiSuccess {int} data 新增记录条数
	 */
	@RequestMapping(path = "/crm/manage/msgmng/addTemplate", method = RequestMethod.POST)
	public Result addTemplate(SysMsgTemplate msgTpl) {
		Result res = new Result();

		msgTpl.setRecStat("0");
		int addCount = msgTplService.addTemplate(msgTpl);
		if (addCount <= 0) {
			throw new ServiceException("新增消息模板数据失败！");
		}
		res.setData(addCount);
		return res;
	}

	/**
	 * @api {PUT} /crm/manage/msgmng/modTemplate 修改消息模板
	 * @apiName modTemplate
	 * @apiGroup MsgMng
	 *
	 * @apiParam {SysMsgTemplate} msgTpl 消息模板对象
	 *
	 * @apiSuccess {int} data 修改记录条数
	 */
	@RequestMapping(path = "/crm/manage/msgmng/modTemplate", method = RequestMethod.PUT)
	public Result modTemplate(SysMsgTemplate msgTpl) {
		Result res = new Result();

		int updCount = msgTplService.modTemplate(msgTpl);
		if (updCount <= 0) {
			throw new ServiceException("修改消息模板数据失败！");
		}
		res.setData(updCount);
		return res;
	}

	/**
	 * @api {DELETE} /crm/manage/msgmng/delTemplate 删除消息模板
	 * @apiName delTemplate
	 * @apiGroup MsgMng
	 *
	 * @apiParam {String} tplCode 消息模板代码
	 *
	 * @apiSuccess {int} data 删除记录条数
	 */
	@RequestMapping(path = "/crm/manage/msgmng/delTemplate", method = RequestMethod.DELETE)
	public Result delTemplate(String tplNo) {
		Result res = new Result();

		int delCount = msgTplService.delTemplate(tplNo);
		if (delCount <= 0) {
			throw new ServiceException("删除消息模板数据失败！");
		}
		res.setData(delCount);
		return res;
	}
	/**
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * @api {DELETE} /crm/manage/msgmng/delTemplateByKey 根据json串传递主键列表, 删除消息模板
	 * @apiName delTemplateByKey
	 * @apiGroup MsgMng
	 *
	 * @apiParam {String} jsonStr json对象字符串(格式同List<SysMsgTemplate>对象一致)
	 *
	 * @apiSuccess  {int} data 删除记录条数
	 */
	
	@RequestMapping(path = "/crm/manage/msgmng/delTemplateByKey", method = RequestMethod.POST)
	public Result delTemplateByKey(@RequestBody String checkedRow) throws Exception {
		Result res = new Result();
		System.out.println("逻辑删除多条消息模板数据。");

		List<SysMsgTemplate> msgList = om.readValue(checkedRow, new TypeReference<List<SysMsgTemplate>>() {
		});

		// 删除的记录条数
		int delCount = 0;

		for (SysMsgTemplate item : msgList) {

			// 执行删除操作
			int addCount = msgTplService.delTemplate(item.getTplNo());
			// 删除是否成功
			if (addCount > 0) {
				System.out.println("删除消息模板数据成功。");
			} else {
				throw new ServiceException("删除消息模板数据失败!");
			}
			delCount += addCount;
		}

		res.setData(delCount);
		res.setMessage("交易成功！");
		return res;
	}
}
