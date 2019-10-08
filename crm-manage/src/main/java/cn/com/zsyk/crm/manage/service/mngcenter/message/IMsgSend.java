package cn.com.zsyk.crm.manage.service.mngcenter.message;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.zsyk.crm.manage.bom.MsgInfo;

@Service
@Transactional
public interface IMsgSend {

	/**
	 * 数据合法性检查
	 * 
	 * @param obj
	 */
	public void dataCheck(MsgInfo obj);

	/**
	 * 消息发送
	 * 
	 * @param msgId
	 * @param varInfo
	 * @param obj
	 */
	public int sendMsg(MsgInfo obj);
}
