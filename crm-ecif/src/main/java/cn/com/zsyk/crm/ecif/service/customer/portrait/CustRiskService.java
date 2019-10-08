package cn.com.zsyk.crm.ecif.service.customer.portrait;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.com.zsyk.crm.ecif.entity.EcRiskEnt;
import cn.com.zsyk.crm.ecif.entity.EcRiskPer;
import cn.com.zsyk.crm.ecif.mapper.EcRiskEntMapper;
import cn.com.zsyk.crm.ecif.mapper.EcRiskPerMapper;

@Service
@Transactional 
public class CustRiskService {
	@Autowired
	private EcRiskPerMapper ecRiskPerMapper;
	@Autowired
	private EcRiskEntMapper ecRiskEntMapper;

	/**
	 * Desc: 查询个人风险信息
	 * @param custNo
	 * @return
	 * @author
	 */
	public EcRiskPer getPerCustRiskInfo(String custNo) {
		EcRiskPer ecRiskPer = ecRiskPerMapper.selectByPrimaryKey(custNo);
		return ecRiskPer;
	}

	/**
	 * Desc: 查询企业风险信息
	 * @param custNo
	 * @return
	 * @author
	 */
	public EcRiskEnt getEntCustRiskInfo(String custNo) {
		EcRiskEnt ecRiskEnt = ecRiskEntMapper.selectByPrimaryKey(custNo);
		return ecRiskEnt;
	}
}
