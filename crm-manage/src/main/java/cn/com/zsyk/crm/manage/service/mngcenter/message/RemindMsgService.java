package cn.com.zsyk.crm.manage.service.mngcenter.message;

import cn.com.zsyk.crm.common.dao.AbstractDao;
import cn.com.zsyk.crm.common.dao.CoreDaoImpl;
import cn.com.zsyk.crm.common.dto.PageBean;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.IdGenerator;
import cn.com.zsyk.crm.common.util.PlaceholderUtil;
import cn.com.zsyk.crm.generator.EnumType;
import cn.com.zsyk.crm.manage.bom.MsgInfo;
import cn.com.zsyk.crm.manage.entity.SysAppRemind;
import cn.com.zsyk.crm.manage.entity.SysMsgTemplate;
import cn.com.zsyk.crm.manage.entity.SysRemindRule;
import cn.com.zsyk.crm.manage.mapper.SysAppRemindMapper;
import cn.com.zsyk.crm.manage.mapper.SysRemindRuleMapper;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RemindMsgService implements IMsgSend {

	@Autowired
	private MsgTemplateService msgTemplateService;
	@Autowired
	private AbstractDao daoUtil;
	@Autowired
	private SysAppRemindMapper appRemindMapper;
	@Autowired
	private SysRemindRuleMapper remindRuleMapper;
	@Autowired
	private CoreDaoImpl coreDaoImpl;

	/**
	 * 数据合法性检查
	 * 
	 * @param appMsg
	 *            提醒消息信息
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
	public int sendMsg(MsgInfo msgInfo) {

		int result = 0;

		// 获取消息编码信息
		SysRemindRule remindRule = remindRuleMapper.selectByPrimaryKey(msgInfo.getMsgCode());
		// 获取消息模板信息
		SysMsgTemplate msgTemplate = msgTemplateService.getOneMsgTemplate(remindRule.getRemindTpl());

		// 获取map格式的参数集
		Map paramMap = toMap(msgInfo.getParams());

		// 获取替换占位符后的消息体
		String msg = PlaceholderUtil.stringFormat(new String(msgTemplate.getTplCont()), paramMap);

		// 生成提醒消息ID编号
		Long seqId = IdGenerator.getDistributedID();
		// 获取当前日期
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String infoId = "r" + dateFormat.format(date).toString() + seqId.toString();

		// 编辑提醒消息信息
		SysAppRemind remindMsg = new SysAppRemind();
		// 提醒消息ID
		remindMsg.setRemindId(infoId);
		// 发送方
		remindMsg.setSendUser("System");
		// 接收方
		remindMsg.setReceivUser(msgInfo.getBusiCode());
		// 发送时间
		remindMsg.setSendTime(new Date());
		// 消息主题
		remindMsg.setRemindTitle(remindRule.getRemindTopic());
		// 消息内容
		remindMsg.setRemindContent(msg);
		// 是否已读
		remindMsg.setIsRead(EnumType.YesNoFlg.no.value);
		// 消息级别 TODO : 级别应该由发送方传值，此处暂时写死（可在提醒定义表中定义级别）
		remindMsg.setRemindLevel(EnumType.Rating.high.value);
		// 消息类型 TODO 该字段是否需要，有何意义？
		// 抄送用户
		// appMsg.setCcUser("");

		// 新增一条提醒消息
		result = addRemindMsg(remindMsg);
		// } else {
		// // 手动
		// }
		return result;
	}

	/**
	 * 获取一条提醒消息
	 * 
	 * @param infoId
	 *            消息ID
	 * @return 提醒信息表表对象
	 */
	public SysAppRemind getOneAppRemind(String infoId) {
		return appRemindMapper.selectByPrimaryKey(infoId);
	}

	/**
	 * 根据员工ID获取所有提醒消息记录
	 * 
	 * @param employeeID
	 *            消息ID
	 * @return 提醒信息表表对象
	 */
	public List<SysAppRemind> getAppRemindByEmployeeId(String employeeID) {

		Map map = new HashMap();
		map.put("employeeId", employeeID);
		List<SysAppRemind> infoList = daoUtil
				.selectList("cn.com.zsyk.crm.manage.mapper.SysAppRemindMapper.selectByEmployeeId", map);
		return infoList;
	}

	/**
	 * 根据输入参数获取所有站内提醒信息
	 * 
	 * @param remindInfo
	 *            站内提醒表对象
	 * 
	 * @return 提醒信息表表对象
	 */
	public PageBean getAppRemindByEntity(SysAppRemind remindInfo) {
		//remindInfo.setReceivUser(ContextContainer.getContext().getEmployeeId());
		//List<SysAppRemind> infoList = daoUtil.selectList("cn.com.zsyk.crm.manage.mapper.SysAppRemindMapper.selectByEntity", remindInfo);
		PageBean pageRetlst = coreDaoImpl.selectPageByMapper(appRemindMapper,"selectByEntity",remindInfo);
		return pageRetlst;
	}

	/**
	 * 根据输入参数获取前10条站内提醒信息
	 * 
	 * @param remindInfo
	 *            站内提醒表对象
	 * 
	 * @return 提醒信息表表对象
	 */
	public List<SysAppRemind> selectTop10ByEntity(SysAppRemind remindInfo) {

		List<SysAppRemind> infoList = daoUtil
				.selectList("cn.com.zsyk.crm.manage.mapper.SysAppRemindMapper.selectTop10ByEntity", remindInfo);
		return infoList;
	}

	/**
	 * 新增一条提醒信息表信息
	 * 
	 * @param appMsg
	 *            提醒信息表
	 * @return 新增成功的记录条数
	 */
	public int addRemindMsg(SysAppRemind appMsg) {

		int addCount = appRemindMapper.insert(appMsg);
		return addCount;
	}

	/**
	 * 批量更新消息的已读未读状态
	 * 
	 * @param remindIdList
	 *            消息IDList
	 * @return
	 */
	public int modAllIsReadStat(List<String> remindIdList) {

		int count = 0;

		for (String remindId : remindIdList) {
			Map map = new HashMap();
			map.put("remindId", remindId);
			map.put("isRead", EnumType.YesNoFlg.yes.value);
			int delCount = daoUtil.update("cn.com.zsyk.crm.manage.mapper.SysAppRemindMapper.updateIsReadStat", map);

			count += delCount;
		}

		return count;
	}
	
	/**
	 * 批量更新消息的已读未读状态
	 * 
	 * @param remindIdList
	 *            消息IDList
	 * @return
	 */
	public int modAllMailIsReadStat(List<String> remindIdList) {

		int count = 0;

		for (String remindId : remindIdList) {
			Map map = new HashMap();
			map.put("mailId", remindId);
			map.put("isRead", EnumType.YesNoFlg.yes.value);
			int delCount = daoUtil.update("cn.com.zsyk.crm.manage.mapper.SysAppMailMapper.updateIsReadStat", map);

			count += delCount;
		}

		return count;
	}

	/**
	 * 更新消息的已读未读状态
	 * 
	 * @param remindId
	 *            消息ID
	 * @return
	 */
	public int modIsReadStat(String remindId) {

		Map map = new HashMap();
		map.put("remindId", remindId);
		map.put("isRead", EnumType.YesNoFlg.yes.value);
		int delCount = daoUtil.update("cn.com.zsyk.crm.manage.mapper.SysAppRemindMapper.updateIsReadStat", map);

		return delCount;
	}

	/**
	 * 根据主键物理删除某提醒信息表信息的方法
	 * 
	 * @param infoId
	 *            消息ID
	 * @return
	 */
	public int delRemindMsg(String infoId) {

		int delCount = appRemindMapper.deleteByPrimaryKey(infoId);

		return delCount;
	}

	// 以逗号分隔，字符串转换为Map格式
	private Map toMap(String str) {

		String[] params = str.split(",");
		Map paramMap = new HashMap();
		for (Integer i = 0; i < params.length; i++) {
			paramMap.put(i.toString(), params[i]);
		}

		return paramMap;
	}
}
