package cn.com.zsyk.crm.manage.service.mngcenter.message;


import cn.com.zsyk.crm.common.dao.AbstractDao;
import cn.com.zsyk.crm.common.dao.CoreDaoImpl;
import cn.com.zsyk.crm.common.dto.PageBean;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.BeanUtil;
import cn.com.zsyk.crm.manage.entity.SysMsgTemplate;
import cn.com.zsyk.crm.manage.mapper.SysMsgTemplateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MsgTemplateService {
	@Autowired
	private SysMsgTemplateMapper msgTemplateMapper;
	@Autowired
	private AbstractDao daoUtil;
	@Autowired
	private CoreDaoImpl coreDaoImpl;

	/**
	 * 获得一条消息模板信息的方法
	 * 
	 * @param tplCode
	 *            模板代码
	 * @return 消息模板表对象
	 */
	public SysMsgTemplate getOneMsgTemplate(String tplCode) {
		return msgTemplateMapper.selectByPrimaryKey(tplCode);
	}
	
	/**
	 * 获得一条消息模板信息的方法
	 * 
	 * @param tplCode
	 *            模板代码
	 * @return 消息模板表对象
	 */
	public PageBean selectByEntity(SysMsgTemplate msgTemplate) {
		PageBean pageRetlst = coreDaoImpl.selectPageByMapper(msgTemplateMapper,"selectByEntity",msgTemplate);
		//return daoUtil.selectList("cn.com.zsyk.crm.manage.mapper.SysMsgTemplateMapper.selectByEntity", msgTemplate);
		return pageRetlst;
	}
	
	/**
	 * 获得一条消息模板信息的方法
	 * 
	 * @param tplCode
	 *            模板代码
	 * @return 消息模板表对象
	 */
	public List<SysMsgTemplate> selectByTime() {
		return daoUtil.selectList("cn.com.zsyk.crm.manage.mapper.SysMsgTemplateMapper.selectByTime");
	}
	/**
	 * 获得一条消息模板信息的方法
	 *
	 * @param tplCode
	 *            模板代码
	 * @return 消息模板表对象
	 */
	public SysMsgTemplate getMsgConfTmpt(String tplCode) {
		return msgTemplateMapper.selectByPrimaryKey(tplCode);
	}


	/**
	 * 新增一条消息模板信息
	 * 
	 * @param msgTemplate
	 *            消息模板对象
	 * @return 新增成功的记录条数
	 */
	public int addTemplate(SysMsgTemplate msgTemplate) {

		if (msgTemplate == null) {
			throw new ServiceException("输入信息不能为空！");
		}

		// 存在判断
		SysMsgTemplate extTest = this.getOneMsgTemplate(msgTemplate.getTplNo());
		if (extTest != null) {
			throw new ServiceException("该消息模板已存在：模板代码[" + extTest.getTplNo() + "]");
		}
		int addCount = msgTemplateMapper.insert(msgTemplate);
		return addCount;
	}

	/**
	 * 修改某条消息模板信息
	 * 
	 * @param msgTemplate
	 *            消息模板对象
	 * @return 修改成功的条目数，0为失败
	 */
	public int modTemplate(SysMsgTemplate msgTemplate) {

		if (msgTemplate == null) {
			throw new ServiceException("输入信息不能为空！");
		}

		// 存在判断
		SysMsgTemplate extTest = this.getOneMsgTemplate(msgTemplate.getTplNo());
		if (extTest == null) {
			throw new ServiceException("消息模板信息不存在：模板代码[" + msgTemplate.getTplNo() + "]");
		}

		BeanUtil.copy(msgTemplate, extTest);

		int modCount = msgTemplateMapper.updateByPrimaryKey(extTest);

		return modCount;
	}

	/**
	 * 根据主键物理删除某消息模板信息的方法
	 * 
	 * @param tplCode
	 *            模板代码
	 * @return
	 */
	public int delTemplate(String tplCode) {

		// 模板代码
		if (tplCode == null) {
			throw new ServiceException("模板代码[" + tplCode + "]不能为空！");
		}

		
		// 存在判断
		SysMsgTemplate extTest = this.getOneMsgTemplate(tplCode);
		if (extTest == null) {
			throw new ServiceException("消息模板信息不存在：模板代码[" + tplCode + "]");
		}
		
		List<SysMsgTemplate> list = msgTemplateMapper.selectByTemplateNo(tplCode);
		if(list != null && !list.isEmpty()) {
			throw new ServiceException("消息模板信息正在使用不能删除");
		}

		int delCount = msgTemplateMapper.deleteByPrimaryKey(tplCode);

		return delCount;
	}
}
