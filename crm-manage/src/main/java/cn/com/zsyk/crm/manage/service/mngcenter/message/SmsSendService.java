package cn.com.zsyk.crm.manage.service.mngcenter.message;

import cn.com.zsyk.crm.common.dao.AbstractDao;
import cn.com.zsyk.crm.common.dao.CoreDaoImpl;
import cn.com.zsyk.crm.common.dto.PageBean;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.IdGenerator;
import cn.com.zsyk.crm.common.util.PlaceholderUtil;
import cn.com.zsyk.crm.generator.EnumType;
import cn.com.zsyk.crm.manage.bom.MsgInfo;
import cn.com.zsyk.crm.manage.entity.SysMsgSendDef;
import cn.com.zsyk.crm.manage.entity.SysMsgTemplate;
import cn.com.zsyk.crm.manage.entity.SysSmsSend;
import cn.com.zsyk.crm.manage.entity.bo.engine.SysSmsSendDetail;
import cn.com.zsyk.crm.manage.mapper.SysSmsSendMapper;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SmsSendService implements IMsgSend {

	@Autowired
	MsgSendDefService msgService;
	@Autowired
	MsgTemplateService msgTemplateService;
	@Autowired
	SysSmsSendMapper smsSendMapper;
	@Autowired
	private AbstractDao daoUtil;
	@Autowired
	private CoreDaoImpl coreDaoImpl;
	/**
	 * 数据合法性检查
	 * 
	 * @param userCode
	 *            用户ID
	 * @return 用户信息
	 */
	public void dataCheck(MsgInfo smsInfo) {

		// 自动发送
		if (EnumType.msgType.automatic.value.equals(smsInfo.getMsgType())) {

			// 信息编码不能为空
			if (StringUtil.isEmpty(smsInfo.getMsgCode())) {
				throw new ServiceException("信息编码不能为空！");
			}

			// 若客群编码为空则手机号码不能为空
			if (StringUtil.isEmpty(smsInfo.getCustGroupCode()) && StringUtil.isEmpty(smsInfo.getMobile())) {
				throw new ServiceException("客群编码和手机号不能同时为空！");
			}

			// 业务编号不能为空
			if (StringUtil.isEmpty(smsInfo.getMsgCode())) {
				throw new ServiceException("业务编号不能为空！");
			}

			// 客户姓名不能为空
			if (StringUtil.isEmpty(smsInfo.getMsgCode())) {
				throw new ServiceException("客户姓名不能为空！");
			}

			// 输入参数个数与消息模板的占位符个数是否相等
			int count = 0;
			if (smsInfo.getParams() != null && !"".equals(smsInfo.getParams())) {
				count = smsInfo.getParams().split(",").length;
			}

			// 获取消息编码信息
			SysMsgSendDef msgSendDef = msgService.getOneMsg(smsInfo.getMsgCode());
			// 获取消息模板信息
			SysMsgTemplate msgTemplate = msgTemplateService.getOneMsgTemplate(msgSendDef.getTemplateCode());

			// 判断占位符个数与参数个数是否一致
			if (count != PlaceholderUtil.placeHolderCount(new String(msgTemplate.getTplCont()))) {
				throw new ServiceException("参数集与消息模板占位符个数不一致！");
			}
		} else if (EnumType.msgType.manual.value.equals(smsInfo.getMsgType())) {
			// 手动发送

		}
	}

	/**
	 * 数据合法性检查
	 * 
	 * @param msgCode
	 *            信息编码
	 * 
	 */
	public int sendMsg(MsgInfo smsInfo) {

		int result = 0;

		// 自动发送
		if (EnumType.msgType.automatic.value.equals(smsInfo.getMsgType())) {
			// 获取消息编码信息
			SysMsgSendDef msgSendDef = msgService.getOneMsg(smsInfo.getMsgCode());
			// 获取消息模板信息
			SysMsgTemplate msgTemplate = msgTemplateService.getOneMsgTemplate(msgSendDef.getTemplateCode());

			// 获取map格式的参数集
			Map paramMap = toMap(smsInfo.getParams());

			// 获取替换占位符后的消息体
			String msg = PlaceholderUtil.stringFormat(new String(msgTemplate.getTplCont()), paramMap);

			// 生成短信ID编号
			Long msgId = IdGenerator.getDistributedID();

			// 编辑短信信息
			SysSmsSend smsSendInfo = new SysSmsSend();
			// 短信ID
			smsSendInfo.setMsgId(msgId);
			// 短信编码
			smsSendInfo.setMsgCode(msgSendDef.getMsgCode());
			// 短信主题
			smsSendInfo.setMsgTopic(msgSendDef.getMsgTopic());
			// 短信内容
			smsSendInfo.setMsgContent(msg);
			// 客群编码
			smsSendInfo.setSendObj(smsInfo.getCustGroupCode());
			// 业务号码
			smsSendInfo.setBizNo(smsInfo.getBusiCode());
			// 客户姓名
			smsSendInfo.setCustName(smsInfo.getCustName());
			// 手机号码
			smsSendInfo.setMobile(smsInfo.getMobile());
			// 发送方式
			smsSendInfo.setSendWay(msgSendDef.getSendWay());
			// 定时日期
			smsSendInfo.setFixDate(msgSendDef.getFixDate());
			// 定时时间
			smsSendInfo.setFixTime(msgSendDef.getFixTime());
			// 循环发送类型
			smsSendInfo.setLoopFlag(msgSendDef.getLoopFlag());
			// 循环类型
			smsSendInfo.setLoopType(msgSendDef.getLoopType());
			// 消息优先级
			smsSendInfo.setMsgOrder(msgSendDef.getMsgOrder());
			// 失败重发次数
			smsSendInfo.setResendTimes(msgSendDef.getResendTimes());
			// 机构编码
			smsSendInfo.setEnterCode("");
			// 部门编码
			smsSendInfo.setDeptCode("");
			// 消息状态：发送中
			smsSendInfo.setMsgStat(EnumType.MsgStat.send.value);

			// 新增一条短信
			result = addMsg(smsSendInfo);
		} else if (EnumType.msgType.manual.value.equals(smsInfo.getMsgType())) {
			// 手动发送
			// 生成短信ID编号
			Long msgId = IdGenerator.getDistributedID();

			// 编辑短信信息
			SysSmsSend smsSendInfo = new SysSmsSend();
			// 短信ID
			smsSendInfo.setMsgId(msgId);
			// 短信编码
			smsSendInfo.setMsgCode(null);
			// 短信主题
			smsSendInfo.setMsgTopic(smsInfo.getMsgTopic());
			// 短信内容
			smsSendInfo.setMsgContent(smsInfo.getMsgContent());
			// 客群编码
			smsSendInfo.setSendObj(smsInfo.getCustGroupCode());
			// 业务号码
			smsSendInfo.setBizNo(smsInfo.getBusiCode());
			// 客户姓名
			smsSendInfo.setCustName(smsInfo.getCustName());
			// 手机号码
			smsSendInfo.setMobile(smsInfo.getMobile());

			if (EnumType.sendWay.direct.value.equals(smsInfo.getSendWay())) {
				// 发送方式：直接发送
				smsSendInfo.setSendWay(EnumType.sendWay.direct.value);
				// 定时日期
				smsSendInfo.setFixDate(null);
				// 定时时间
				smsSendInfo.setFixTime(null);
			} else if (EnumType.sendWay.timing.value.equals(smsInfo.getSendWay())) {
				// 发送方式：定时发送
				smsSendInfo.setSendWay(EnumType.sendWay.timing.value);
				// 定时日期
				smsSendInfo.setFixDate(null); // TODO ：设置发送日期
				// 定时时间
				smsSendInfo.setFixTime(null);// TODO ：设置发送时间
			}

			// 循环发送类型
			smsSendInfo.setLoopFlag(EnumType.loopFlag.no.value);
			// 循环类型
			smsSendInfo.setLoopType(null);
			// 消息优先级
			smsSendInfo.setMsgOrder(null);
			// 失败重发次数
			smsSendInfo.setResendTimes(1);
			// 机构编码
			smsSendInfo.setEnterCode("");
			// 部门编码
			smsSendInfo.setDeptCode("");
			// 消息状态：发送中
			smsSendInfo.setMsgStat(EnumType.MsgStat.send.value);

			// 新增一条短信
			result = addMsg(smsSendInfo);
		}
		return result;
	}

	/**
	 * 获得一条短信发送表信息的方法
	 * 
	 * @param msgId
	 *            消息ID
	 * @return 短信发送表表对象
	 */
	public SysSmsSend getOneMsg(Long msgId) {
		return smsSendMapper.selectByPrimaryKey(msgId);
	}

	/**
	 * 获得多条短信发送表信息的方法
	 * 
	 * @param msgId
	 *            消息ID
	 * @return 短信发送表表对象
	 */
	public PageBean selectByEntity(SysSmsSendDetail smsSend) {
		PageBean pageRetlst = coreDaoImpl.selectPageByMapper(smsSendMapper,"selectByEntity",smsSend);
		return pageRetlst;
		//return daoUtil.selectList("cn.com.zsyk.crm.manage.mapper.SysSmsSendMapper.selectByEntity", smsSend);
	}

	/**
	 * 新增一条短信发送表信息
	 * 
	 * @param smsSend
	 *            短信发送表
	 * @return 新增成功的记录条数
	 */
	public int addMsg(SysSmsSend smsSend) {

		if (smsSend == null) {
			throw new ServiceException("输入信息不能为空！");
		}

		if (StringUtils.isEmpty(smsSend.getMsgId())) {
			throw new ServiceException("短信ID不能为空！");
		}
		// 存在判断
		SysSmsSend extTest = this.getOneMsg(smsSend.getMsgId());
		if (extTest != null) {
			throw new ServiceException("短信ID已存在请重新发送：短信ID[" + extTest.getMsgId() + "]");
		}
		int addCount = smsSendMapper.insert(smsSend);
		return addCount;
	}

	/**
	 * 根据主键物理删除某短信发送表信息的方法
	 * 
	 * @param msgId
	 *            消息ID
	 * @return
	 */
	public int delMsg(Long msgId) {

		// 消息ID
		if (msgId == null) {
			throw new ServiceException("消息ID[" + msgId + "]不能为空！");
		}

		// 存在判断
		SysSmsSend extTest = this.getOneMsg(msgId);
		if (extTest == null) {
			throw new ServiceException("短信发送表信息不存在：消息ID[" + msgId + "]");
		}

		int delCount = smsSendMapper.deleteByPrimaryKey(msgId);

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
