package cn.com.zsyk.crm.ecif.service.customer.portrait;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.ecif.entity.EcCustEvent;
import cn.com.zsyk.crm.ecif.mapper.EcCustEventMapper;
import cn.com.zsyk.crm.generator.EnumType;

@Service
@Transactional 
public class CustEventService {

	@Autowired
	private EcCustEventMapper ecCustEventMapper;
	/**
	 * Desc: 查询客户事件列表
	 * @param ecCustEvent
	 * @return
	 * @author
	 */
	public List<EcCustEvent> selectCustEventList(EcCustEvent ecCustEvent) {
		List<EcCustEvent> ecCustEventlst = ecCustEventMapper.selectCustEventList(ecCustEvent);
		return ecCustEventlst;
	}

	/**
	 * Desc: 查询客户事件
	 * @param custNo
	 * @return
	 * @author
	 */
	public EcCustEvent selectCustEventOne(String custNo, String eventType, String eventDate) {
		EcCustEvent ecCustEvent = ecCustEventMapper.selectByPrimaryKey(custNo, eventType, eventDate);
		return ecCustEvent;
	}

	/**
	 * Desc: 新增客户事件信息
	 * @param ecCustEvent
	 * @author
	 */
	public void addCustEvent(EcCustEvent ecCustEvent) {
		if (ecCustEvent == null) {
			throw new ServiceException("事件信息为空");
		}
		// 客户号
		if (StringUtils.isEmpty(ecCustEvent.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}
		// 事件类型
		if (StringUtils.isEmpty(ecCustEvent.getEventType())) {
			throw new ServiceException("事件类型不能为空");
		}
		// 事件日期
		if (ecCustEvent.getEventDate() == null) {
			throw new ServiceException("事件日期不能为空");
		}
		EcCustEvent obj = ecCustEventMapper.selectByPrimaryKey(ecCustEvent.getCustNo(), ecCustEvent.getEventType(), ecCustEvent.getEventDate());
		if (obj != null) {
			throw new ServiceException("客户事件信息已经存在");
		}
		ecCustEvent.setRecStat(EnumType.RecStat.normal.getValue());
		ecCustEventMapper.insert(ecCustEvent);
	}

	/**
	 * Desc: 修改客户事件信息
	 * @param ecCustEvent
	 * @author
	 */
	public void uptCustEvent(EcCustEvent ecCustEvent) {
		if (ecCustEvent == null) {
			throw new ServiceException("事件信息参数为空");
		}
		// 客户号
		if (StringUtils.isEmpty(ecCustEvent.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}
		// 事件类型
		if (StringUtils.isEmpty(ecCustEvent.getEventType())) {
			throw new ServiceException("事件类型不能为空");
		}
		// 事件日期
		if (ecCustEvent.getEventDate() == null) {
			throw new ServiceException("事件日期不能为空");
		}
		EcCustEvent obj = ecCustEventMapper.selectByPrimaryKey(ecCustEvent.getCustNo(), ecCustEvent.getEventType(), ecCustEvent.getEventDate());
		if (obj == null) {
			throw new ServiceException("客户事件信息不存在");
		}
		obj.setEventDesc(ecCustEvent.getEventDesc());
		ecCustEventMapper.updateByPrimaryKey(obj);
	}

	/**
	 * Desc: 删除客户事件信息
	 * @param ecCustEvent
	 * @author
	 */
	public void delCustEvent(EcCustEvent ecCustEvent) {
		if (ecCustEvent == null) {
			throw new ServiceException("事件信息参数为空");
		}
		// 客户号
		if (StringUtils.isEmpty(ecCustEvent.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}
		// 事件类型
		if (StringUtils.isEmpty(ecCustEvent.getEventType())) {
			throw new ServiceException("事件类型不能为空");
		}
		// 事件日期
		if (ecCustEvent.getEventDate() == null) {
			throw new ServiceException("事件日期不能为空");
		}
		EcCustEvent obj = ecCustEventMapper.selectByPrimaryKey(ecCustEvent.getCustNo(), ecCustEvent.getEventType(), ecCustEvent.getEventDate());
		if (obj == null) {
			throw new ServiceException("客户事件信息不存在");
		}
		ecCustEventMapper.deleteByPrimaryKey(ecCustEvent.getCustNo(), ecCustEvent.getEventType(), ecCustEvent.getEventDate());
	}
}
