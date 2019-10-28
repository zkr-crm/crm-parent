package cn.com.zsyk.crm.manage.service.mngcenter.message;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import cn.com.zsyk.crm.common.dao.AbstractDao;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.manage.entity.SysMsgSendDef;
import cn.com.zsyk.crm.manage.entity.bo.engine.SysMsgSendDetail;
import cn.com.zsyk.crm.manage.mapper.SysMsgSendDefMapper;

@Service
@Transactional
public class MsgSendDefService {
	@Autowired
	private SysMsgSendDefMapper msgMapper;
	@Autowired
	private AbstractDao daoUtil;

	/**
	 * 获得一条信息发送定义信息的方法
	 * 
	 * @param SysMsgSendDef
	 *           	信息发送定义表对象
	 * @return List<SysMsgSendDef>
	 * 				信息发送定义表集和
	 */
	public List<SysMsgSendDetail> getMsgByEntity(SysMsgSendDef msg){
		return daoUtil.selectList("cn.com.zsyk.crm.manage.mapper.SysMsgSendDefMapper.selectMsgSendDefDetail", msg);
	}
	/**
	 * 获得一条信息发送定义信息的方法
	 * 
	 * @param msgCode
	 *           	信息编码
	 * @return 信息发送定义表对象
	 */
	public SysMsgSendDef getOneMsg(String msgCode){
		return msgMapper.selectByPrimaryKey(msgCode);
	}
	/**
	 * 新增一条信息发送定义信息
	 * 
	 * @param msg
	 *            信息发送定义
	 * @return 新增成功的记录条数
	 */
	public int addMsg(SysMsgSendDef msg) {

		if (msg == null) {
			throw new ServiceException("输入信息不能为空！");
		}

		if(StringUtils.isEmpty(msg.getMsgCode())) {
			throw new ServiceException("信息编码不能为空！");
		}		
		// 存在判断
		SysMsgSendDef extTest = this.getOneMsg(msg.getMsgCode());
		if (extTest != null) {
			throw new ServiceException(
					"信息发送定义已存在：信息编码[" + extTest.getMsgCode() + "]");
		}
		int addCount = msgMapper.insert(msg);
		return addCount;
	} 
	/**
	 * 修改某条信息发送定义信息
	 * 
	 * @param msg
	 *            需要修改的信息发送定义信息
	 * @return 修改成功的条目数，0为失败
	 */
	public int modMsg(SysMsgSendDef msg) {

		if (msg == null) {
			throw new ServiceException("输入信息不能为空！");
		}

		// 存在判断
		SysMsgSendDef extTest = this.getOneMsg(msg.getMsgCode());
		if (extTest == null) {
			throw new ServiceException(
					"信息发送定义信息不存在：信息编码[" + msg.getMsgCode() + "]");
		}
		extTest.setMsgType(msg.getMsgType());// 消息类型
		extTest.setMsgTopic(msg.getMsgTopic());// 信息标题
		extTest.setFixDate(msg.getFixDate());// 定时日期
		extTest.setFixTime(msg.getFixTime());// 定时时间
		extTest.setMsgOrder(msg.getMsgOrder()); //消息优先级 0-9，9级为最高优先级
		extTest.setLoopFlag(msg.getLoopFlag()); // 循环发送类型  0-不循环 1-循环
		extTest.setLoopType(msg.getLoopType());// 循环类型 01-每日 02-每周 03-每月 04-每季度 05-每半年 06-每年
		extTest.setTemplateCode(msg.getTemplateCode());// template_code 模板代码
		extTest.setSendObj(msg.getSendObj());// send_obj 发送对象
		extTest.setSendChannel(msg.getSendChannel());// send_channel 发送渠道
		extTest.setCc(msg.getCc());// cc 抄送
		extTest.setSendWay(msg.getSendWay());// send_way 发送方式
		int modCount = msgMapper.updateByPrimaryKey(extTest);

		return modCount;
	}
	/**
	 * 修改某条信息发送定义信息为生效
	 * 
	 * @param msg
	 *            需要修改的信息发送定义信息
	 * @return 修改成功的条目数，0为失败
	 */
	public int updSaveMsg(SysMsgSendDef msg) {

		if (msg == null) {
			throw new ServiceException("输入信息不能为空！");
		}

		// 存在判断
		SysMsgSendDef extTest = this.getOneMsg(msg.getMsgCode());
		if (extTest == null) {
			throw new ServiceException(
					"信息发送定义信息不存在：信息编码[" + msg.getMsgCode() + "]");
		}
		if ( "1".equals(extTest.getMsgStat()) ) {
			throw new ServiceException(
					"信息发送定义已生效：信息编码[" + msg.getMsgCode() + "]");
		}
		extTest.setMsgStat(msg.getMsgStat());
		int modCount = msgMapper.updateByPrimaryKey(extTest);

		return modCount;
	}		
	/**
	 * 根据主键物理删除某信息发送定义信息的方法
	 * 
	 * @param msgCode
	 *            信息编码
	 * @return
	 */
	public int delMsg(String msgCode) {

		// 信息编码
		if (msgCode == null || "".equals(msgCode)) {
			throw new ServiceException("信息编码[" + msgCode + "]不能为空！");
		}

		// 存在判断
		SysMsgSendDef extTest = this.getOneMsg(msgCode);
		if (extTest == null) {
			throw new ServiceException("信息发送定义信息不存在：信息编码[" + msgCode + "]");
		}

		int delCount = msgMapper.deleteByPrimaryKey(msgCode);

		return delCount;
	}
}
