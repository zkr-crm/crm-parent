package cn.com.zsyk.crm.manage.service.mngcenter.message;

import cn.com.zsyk.crm.common.dao.AbstractDao;
import cn.com.zsyk.crm.common.dao.CoreDaoImpl;
import cn.com.zsyk.crm.common.dto.ContextContainer;
import cn.com.zsyk.crm.common.dto.PageBean;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.IdGenerator;
import cn.com.zsyk.crm.common.util.JsonUtil;
import cn.com.zsyk.crm.common.util.PlaceholderUtil;
import cn.com.zsyk.crm.generator.EnumType;
import cn.com.zsyk.crm.manage.bom.MsgInfo;
import cn.com.zsyk.crm.manage.entity.SysAppMail;
import cn.com.zsyk.crm.manage.entity.SysMsgSendDef;
import cn.com.zsyk.crm.manage.entity.SysMsgTemplate;
import cn.com.zsyk.crm.manage.mapper.SysAppMailMapper;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AppMailService implements IMsgSend {

	@Autowired
	MsgSendDefService msgService;
	@Autowired
	private MsgTemplateService msgTemplateService;
	@Autowired
	private AbstractDao daoUtil;
	@Autowired
	private SysAppMailMapper appMsgMapper;
	@Autowired
	private CoreDaoImpl coreDaoImpl;
	/**
	 * 数据合法性检查
	 * 
	 * @param appMsg
	 *            站内信信息
	 * @return 用户信息
	 */
	public void dataCheck(MsgInfo appMsg) {

		// 信息编码不能为空
		if (StringUtil.isEmpty(appMsg.getMsgCode())) {
			throw new ServiceException("信息编码不能为空！");
		}
	};

	/**
	 * 数据合法性检查
	 * 
	 * @param msgCode
	 *            信息编码
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int sendMsg(MsgInfo msgInfo) {

		int result = 0;

		// 自动
		if (EnumType.msgType.automatic.value.equals(msgInfo.getMsgType())) {

			// 获取消息编码信息
			SysMsgSendDef msgSendDef = msgService.getOneMsg(msgInfo.getMsgCode());
			// 获取消息模板信息
			SysMsgTemplate msgTemplate = msgTemplateService.getOneMsgTemplate(msgSendDef.getTemplateCode());

			// 获取map格式的参数集
			Map paramMap = toMap(msgInfo.getParams());

			// 获取替换占位符后的消息体
			String msg = PlaceholderUtil.stringFormat(new String(msgTemplate.getTplCont()), paramMap);

			// 生成站内信ID编号 TODO seqID 入参未定义
			Long seqId = IdGenerator.getSeqID("");
			String infoId = "mail_" + seqId.toString();

			// 编辑站内信信息
			SysAppMail appMsg = new SysAppMail();
			// 站内信ID
			appMsg.setMailId(infoId);
			// 发送方
			appMsg.setSendUser(ContextContainer.getContext().getUserId());
			// 接收方
			appMsg.setReceivUser(msgInfo.getBusiCode());
			// 发送时间
			appMsg.setSendTime(new Date());
			// 消息主题
			appMsg.setMailTitle(msgSendDef.getMsgTopic());
			// 消息内容
			appMsg.setMailContent(msg);
			// 是否已读
			appMsg.setIsRead(EnumType.YesNoFlg.no.value);

			// 新增一条站内信
			result = addAppMail(appMsg);
		} else {
			// 手动
		}
		return result;
	}

	/**
	 * 获取一条站内信
	 * 
	 * @param mailId
	 *            信息ID
	 * @return 站内信信息表表对象
	 */
	public SysAppMail getOneAppMail(String mailId) {
		return appMsgMapper.selectByPrimaryKey(mailId);
	}

	/**
	 * 根据输入条件获取所有站内信记录
	 * 
	 * @param employeeID
	 *            员工ID
	 * @return 站内信信息表表对象
	 */
	public PageBean getAppMailByEntity(SysAppMail appMail) {
		appMail.setReceivUser(ContextContainer.getContext().getEmployeeId());
		//List<SysAppMail> infoList = daoUtil.selectList("cn.com.zsyk.crm.manage.mapper.SysAppMailMapper.selectByEntity",appMail);
		PageBean pageRetlst = coreDaoImpl.selectPageByMapper(appMsgMapper,"selectByEntity",appMail);
		return pageRetlst;
	}

	/**
	 * 批量新增站内信信息表信息
	 * 
	 * @param appMailList
	 *            站内信信息表
	 * @return 新增成功的记录条数
	 */
	@SuppressWarnings({ "rawtypes"})
	public int addAppMailList(String appMailList) {

		Map appMailObj = JsonUtil.parseObject(appMailList, Map.class);
		String receivUsers = appMailObj.get("receivUsers").toString();
		String[] users = receivUsers.split(",");

		int addCount = 0;

		for (String user : users) {
			// 编辑mail对象
			SysAppMail sysMail = new SysAppMail();

			// 生成规则ID
			Long id = IdGenerator.getSeqID("SysMail");
			String mailId = "M_" + id.toString();
			sysMail.setMailId(mailId);// 站内信ID
			sysMail.setSendUser(appMailObj.get("sendUser").toString());// 发送用户
			sysMail.setReceivUser(user);// 接收用户
			sysMail.setSendTime(new Date());// 发送时间
			sysMail.setMailTitle(appMailObj.get("mailTitle").toString());// 信息标题
			sysMail.setMailContent(appMailObj.get("mailContent").toString());// 信息内容
			sysMail.setIsRead(EnumType.YesNoFlg.no.value);// 是否已读：默认未读
			sysMail.setMailAttr(appMailObj.get("mailAttr").toString());// 信息类型

			int result = appMsgMapper.insert(sysMail);

			addCount = addCount + result;
		}

		return addCount;
	}

	/**
	 * 新增一条站内信信息表信息
	 * 
	 * @param appMail
	 *            站内信信息表
	 * @return 新增成功的记录条数
	 */
	public int addAppMail(SysAppMail appMail) {

		int addCount = appMsgMapper.insert(appMail);
		return addCount;
	}

	/**
	 * 更新一条站内信已读状态
	 * 
	 * @param appMail
	 *            站内信信息表
	 * @return 新增成功的记录条数
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int modMailIsReadStat(String mailId) {

		Map map = new HashMap();
		map.put("mailId", mailId);
		map.put("isRead", EnumType.YesNoFlg.yes.value);
		int addCount = daoUtil.update("cn.com.zsyk.crm.manage.mapper.SysAppMailMapper.updateIsReadStat", map);
		return addCount;
	}

	/**
	 * 根据主键物理删除某站内信信息表信息的方法
	 * 
	 * @param mailId
	 *            信息ID
	 * @return
	 */
	public int delAppMail(String mailId) {

		int delCount = appMsgMapper.deleteByPrimaryKey(mailId);

		return delCount;
	}

	// 以逗号分隔，字符串转换为Map格式
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map toMap(String str) {

		String[] params = str.split(",");
		Map paramMap = new HashMap();
		for (Integer i = 0; i < params.length; i++) {
			paramMap.put(i.toString(), params[i]);
		}

		return paramMap;
	}
}
