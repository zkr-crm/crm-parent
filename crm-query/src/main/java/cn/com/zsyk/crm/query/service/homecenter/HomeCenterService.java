package cn.com.zsyk.crm.query.service.homecenter;

import cn.com.zsyk.crm.common.dao.CoreDaoImpl;
import cn.com.zsyk.crm.common.dto.ContextContainer;
import cn.com.zsyk.crm.common.dto.PageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@Slf4j
public class HomeCenterService {
	
	/** 相似客户任务表 */
	@Autowired
	private CoreDaoImpl dao;

	/**
	 * 按条件查询所有的事件列表客户列表
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List selectAllEventByUser() {
		try {
			log.info("simTask:" + " employeeId:" + ContextContainer.getContext().getEmployeeId());
			Map param = new HashMap();
			param.put("userId", ContextContainer.getContext().getUserId());
			PageBean p = dao.selectPageById("cn.com.zsyk.crm.query.mapper.SysAppRemindMapper.selectAllEventByUser",
					param, true);
			if (p != null) {
				return p.getList();
			}
		} catch (Exception e) {
			log.error("crm-query 服务  按条件查询所有的事件列表客户列表 异常：", e);
			throw e;
		}
		return null;
	}

	/**
	 * 按条件查询所有续保列表客户列表
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getMyRenewalByEntity(List<String> employeeIdList) {
		try {
			log.info("simTask:" + " employeeId:" + ContextContainer.getContext().getEmployeeId());
			Map param = new HashMap();
			param.put("userId", ContextContainer.getContext().getUserId());
			param.put("custAgentList", employeeIdList);
			PageBean p = dao.selectPageById("cn.com.zsyk.crm.query.mapper.SysAppRemindMapper.selectAllRenwalByUser",
					param, true);
			if (p != null) {
				return p.getList();
			}
		} catch (Exception e) {
			log.error("crm-query 服务  按条件查询所有续保列表客户列表 异常：", e);
			throw e;
		}
		return null;

	}

}
