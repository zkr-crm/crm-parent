package cn.com.zsyk.crm.manage.service.mngcenter.message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.zsyk.crm.common.dao.AbstractDao;
import cn.com.zsyk.crm.common.util.IdGenerator;
import cn.com.zsyk.crm.manage.entity.SysRemindRule;
import cn.com.zsyk.crm.manage.mapper.SysRemindRuleMapper;

@Service
@Transactional
public class RemindSendDefService {

	@Autowired
	private MsgTemplateService msgTemplateService;
	@Autowired
	private AbstractDao daoUtil;
	@Autowired
	private SysRemindRuleMapper remindRuleMapper;

	/**
	 * 获取一条提醒定义消息
	 * 
	 * @param ruleId
	 *            规则ID
	 * @return 提醒定义信息表表对象
	 */
	public SysRemindRule getOneRemindDef(String ruleId) {
		return remindRuleMapper.selectByPrimaryKey(ruleId);
	}

	/**
	 * 根据输入参数获取所有站内提醒定义信息
	 * 
	 * @param remindInfo
	 *            站内提醒定义表对象
	 * 
	 * @return 提醒定义信息表表对象
	 */
	public List<SysRemindRule> getRemindDefByEntity(SysRemindRule remindInfo) {

		List<SysRemindRule> infoList = daoUtil
				.selectList("cn.com.zsyk.crm.manage.mapper.SysRemindRuleMapper.selectByEntity", remindInfo);
		return infoList;
	}

	/**
	 * 新增一条提醒定义信息表信息
	 * 
	 * @param appMsg
	 *            提醒定义信息表
	 * @return 新增成功的记录条数
	 */
	public int addRemindDefMsg(SysRemindRule appMsg) {
		
		//生成规则ID
		Long ruleId = IdGenerator.getSeqID("RemindDef");
		appMsg.setRuleId("RDef_"+ruleId.toString());
		int addCount = remindRuleMapper.insert(appMsg);
		return addCount;
	}

	/**
	 * 修改一条提醒定义信息表信息
	 * 
	 * @param appMsg
	 *            提醒定义信息表
	 * @return 新增成功的记录条数
	 */
	public int updRemindMsg(SysRemindRule appMsg) {

		int addCount = remindRuleMapper.updateByPrimaryKey(appMsg);
		return addCount;
	}

	/**
	 * 根据主键物理删除某提醒定义信息表信息的方法
	 * 
	 * @param ruleId
	 *            规则ID
	 * @return
	 */
	public int delRemindDefMsg(String ruleId) {

		int delCount = remindRuleMapper.deleteByPrimaryKey(ruleId);

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
