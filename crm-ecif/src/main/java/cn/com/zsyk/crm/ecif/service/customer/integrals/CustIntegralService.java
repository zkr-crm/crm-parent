package cn.com.zsyk.crm.ecif.service.customer.integrals;

import java.math.BigDecimal;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.LogUtil;
import cn.com.zsyk.crm.ecif.entity.EcIntegral;
import cn.com.zsyk.crm.ecif.mapper.EcIntegralMapper;

@Service
@Transactional
public class CustIntegralService {
	/** logger */
	@SuppressWarnings("unused")
	private final Log logger = LogUtil.getLogger(CustIntegralService.class);

	@Autowired
	private EcIntegralMapper ecIntegralMapper;

	/**
	 * Desc: 根据客户号查询积分(列表)
	 * @param ecIntegral
	 * @return
	 * @author
	 */
	public List<EcIntegral> getCustIntegralList(EcIntegral ecIntegral) {

		if (ecIntegral == null) {
			throw new ServiceException("参数不能为空");
		}
		if (StringUtils.isEmpty(ecIntegral.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}

		List<EcIntegral> retlst = ecIntegralMapper.getCustIntegralList(ecIntegral);

		return retlst;
	}

	/**
	 * Desc: 根据客户号查询积分(单条)
	 * @param custNo
	 * @param policyNo
	 * @param integChgTyp
	 * @return Object
	 * @author 
	 */
	public Object getCustIntegralByPK(String custNo, String policyNo, String integChgTyp) {

		if (StringUtils.isEmpty(custNo)) {
			throw new ServiceException("客户号不能为空");
		}

		if (StringUtils.isEmpty(policyNo)) {
			throw new ServiceException("保单号号不能为空");
		}

		if (StringUtils.isEmpty(integChgTyp)) {
			throw new ServiceException("积分变化类型不能为空");
		}

		EcIntegral ecIntegral = ecIntegralMapper.selectByPrimaryKey(custNo, policyNo, integChgTyp);

		if (ecIntegral == null) {
			throw new ServiceException("客户积分信息不存在");
		}
		return ecIntegral;
	}

	/**
	 * Desc: 客户积分汇总
	 * @param custNo
	 * @return
	 * @author
	 */
	public BigDecimal getSumIntegralByCustNo(String custNo) {
	BigDecimal ingralSum = ecIntegralMapper.getSumIntegralByCustNo(custNo);
		if (ingralSum == null) {
			ingralSum = BigDecimal.ZERO;
		}
		return ingralSum;
	}
}
