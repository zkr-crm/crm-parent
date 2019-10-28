package cn.com.zsyk.crm.ecif.service.homeCenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.zsyk.crm.common.dao.CoreDaoImpl;
import cn.com.zsyk.crm.common.dto.ContextContainer;
import cn.com.zsyk.crm.common.dto.PageBean;
import cn.com.zsyk.crm.common.util.LogUtil;
import cn.com.zsyk.crm.ecif.entity.EcSimTask;
import cn.com.zsyk.crm.ecif.service.customer.integrals.CustIntegralService;

@Service
@Transactional
public class HomeCenterService {
	/** logger */
	private final Log logger = LogUtil.getLogger(CustIntegralService.class);
	/** 相似客户任务表 */
	@Autowired
	private CoreDaoImpl dao;
	/**
	 * 按条件查询个人用户的所有合并审批任务
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PageBean selectAllNoApprove(EcSimTask simTask) {
		try {
			logger.info("simTask:" + " employeeId:"+ContextContainer.getContext().getEmployeeId());
			PageBean p = dao.selectPageById("cn.com.zsyk.crm.ecif.mapper.EcSimTaskMapper.selectAllNoApprove",simTask,true);
			
	        if(p != null ) {
	        	return p;
	        }
		} catch (Exception e) {
			logger.error("crm-ecif 服务  按条件查询个人用户的所有合并审批任务 异常：", e);
			throw e;
		}
		return null;
	}
	/**
	 * 按条件查询所有的客户地址不详的客户列表
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List selectAllAddressInsufficient() {
		try {
			logger.info("simTask:" + " employeeId:"+ContextContainer.getContext().getEmployeeId());
			Map param = new HashMap();
			param.put("employeeId", ContextContainer.getContext().getEmployeeId());
			PageBean p = dao.selectPageById("cn.com.zsyk.crm.ecif.mapper.EcCustPerMapper.selectAllCustForAddressInsufficient",param,true);
	        if(p != null ) {
	        	return p.getList();
	        }
		} catch (Exception e) {
			logger.error("crm-ecif 服务  按条件查询所有的客户地址不详的客户列表 异常：", e);
			throw e;
		}
		return null;
	}
	
}
