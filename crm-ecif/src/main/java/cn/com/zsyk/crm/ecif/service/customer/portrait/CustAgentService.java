package cn.com.zsyk.crm.ecif.service.customer.portrait;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.BeanUtil;
import cn.com.zsyk.crm.common.util.LogUtil;
import cn.com.zsyk.crm.ecif.entity.EcCustAgent;
import cn.com.zsyk.crm.ecif.mapper.EcCustAgentMapper;
import cn.com.zsyk.crm.generator.EnumType;

@Service
@Transactional 
public class CustAgentService {
	/** logger */
	@SuppressWarnings("unused")
	private final Log logger = LogUtil.getLogger(CustAgentService.class);

	@Autowired
	private EcCustAgentMapper ecCustAgentMapper;

	/**
	 * Desc: 查询客户代理人列表
	 * @param ecCustAgent
	 * @return
	 * @author 
	 */
	public  List<EcCustAgent> getCustAgentList(EcCustAgent ecCustAgent) {
		if (ecCustAgent == null || StringUtils.isEmpty(ecCustAgent.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}
		List<EcCustAgent> ecCustAgentLst = ecCustAgentMapper.selectCustAgentList(ecCustAgent);
		return ecCustAgentLst;
	}

	/**
	 * Desc: 查询客户代理人
	 * @param custNo
	 * @param agentNo
	 * @return
	 * @author
	 */
	public  EcCustAgent selectByCustNo(String custNo, String agentNo) {
		if (StringUtils.isEmpty(custNo)) {
			throw new ServiceException("客户号不能为空");
		}
		if (StringUtils.isEmpty(agentNo)) {
			throw new ServiceException("代理人编号不能为空");
		}
		EcCustAgent ecCustAgent = ecCustAgentMapper.selectByPrimaryKey(custNo, agentNo);
		return ecCustAgent;
	}
	
	/**
	 * Desc: 新增客户代理人信息
	 * @param ecCustAgent
	 * @author
	 */
	public void addCustAgent(EcCustAgent ecCustAgent) {
		if (ecCustAgent == null) {
			throw new ServiceException("代理人信息为空");
		}
		// 客户号
		if (StringUtils.isEmpty(ecCustAgent.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}
		// 代理人编号
		if (StringUtils.isEmpty(ecCustAgent.getOrigSysAgentNo())) {
			throw new ServiceException("代理人类型不能为空");
		}
		EcCustAgent obj = ecCustAgentMapper.selectByPrimaryKey(ecCustAgent.getCustNo(), ecCustAgent.getOrigSysAgentNo());
		if (obj != null) {
			throw new ServiceException("客户代理人信息已经存在");
		}
		ecCustAgent.setRecStat(EnumType.RecStat.normal.getValue());
		ecCustAgentMapper.insert(ecCustAgent);
	}

	/**
	 * Desc: 修改客户代理人信息
	 * @param ecCustAgent
	 * @author
	 */
	public void uptCustAgent(EcCustAgent ecCustAgent) {
		if (ecCustAgent == null) {
			throw new ServiceException("代理人信息为空");
		}
		// 客户号
		if (StringUtils.isEmpty(ecCustAgent.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}
		// 代理人编号
		if (StringUtils.isEmpty(ecCustAgent.getOrigSysAgentNo())) {
			throw new ServiceException("代理人类型不能为空");
		}
		EcCustAgent obj = ecCustAgentMapper.selectByPrimaryKey(ecCustAgent.getCustNo(), ecCustAgent.getOrigSysAgentNo());
		if (obj == null) {
			throw new ServiceException("客户代理人信息不存在");
		}
		BeanUtil.copy(ecCustAgent, obj);
		ecCustAgentMapper.updateByPrimaryKey(obj);
	}

	/**
	 * Desc: 删除客户代理人信息
	 * @param ecCustAgent
	 * @author
	 */
	public void delCustAgent(EcCustAgent ecCustAgent) {
		if (ecCustAgent == null) {
			throw new ServiceException("代理人信息为空");
		}
		// 客户号
		if (StringUtils.isEmpty(ecCustAgent.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}
		// 代理人编号
		if (StringUtils.isEmpty(ecCustAgent.getOrigSysAgentNo())) {
			throw new ServiceException("代理人类型不能为空");
		}
		EcCustAgent obj = ecCustAgentMapper.selectByPrimaryKey(ecCustAgent.getCustNo(), ecCustAgent.getOrigSysAgentNo());
		if (obj == null) {
			throw new ServiceException("客户代理人信息不存在");
		}
		ecCustAgentMapper.deleteByPrimaryKey(ecCustAgent.getCustNo(), ecCustAgent.getOrigSysAgentNo());
	}

	/**
	 * Desc: 查询代理人信息
	 * @param custNo
	 * @param origSysAgentNo
	 * @return
	 * @author
	 */
	public EcCustAgent getCustAgentOne(String custNo, String origSysAgentNo) {
		// 客户号
		if (StringUtils.isEmpty(custNo)) {
			throw new ServiceException("客户号不能为空");
		}
		// 代理人编号
		if (StringUtils.isEmpty(origSysAgentNo)) {
			throw new ServiceException("代理人类型不能为空");
		}
		EcCustAgent retObj = ecCustAgentMapper.selectByPrimaryKey(custNo, origSysAgentNo);
		if (retObj == null) {
			throw new ServiceException("客户代理人信息不存在");
		}
		return retObj;
	}
}
