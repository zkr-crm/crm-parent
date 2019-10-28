package cn.com.zsyk.crm.ecif.service.customer.portrait;

import java.util.List;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.com.zsyk.crm.common.util.LogUtil;
import cn.com.zsyk.crm.ecif.entity.EcFinaceClaim;
import cn.com.zsyk.crm.ecif.entity.EcFinacePay;
import cn.com.zsyk.crm.ecif.entity.EcFirstAct;
import cn.com.zsyk.crm.ecif.mapper.EcFinaceClaimMapper;
import cn.com.zsyk.crm.ecif.mapper.EcFinacePayMapper;
import cn.com.zsyk.crm.ecif.mapper.EcFirstActMapper;

@Service
@Transactional 
public class CustFinaceService {
	/** logger */
	private final Log logger = LogUtil.getLogger(CustFinaceService.class);

	@Autowired
	private EcFinacePayMapper ecFinacePayMapper;
	@Autowired
	private EcFinaceClaimMapper ecFinaceClaimMapper;
	@Autowired
	private EcFirstActMapper ecFirstActMapper;

	/**
	 * Desc: 查询财务缴费列表
	 * @param ecFinacePay
	 * @return
	 * @author
	 */
	public  List<EcFinacePay> getCustFinacePayoList(EcFinacePay ecFinacePay) {
		List<EcFinacePay> ecFinacePaylst = ecFinacePayMapper.selectFinacePayList(ecFinacePay);
		return ecFinacePaylst;
	}

	/**
	 * Desc: 查询财务理赔列表
	 * @param ecFinaceClaim
	 * @return
	 * @author
	 */
	public  List<EcFinaceClaim> getCustFinaceClaimList(EcFinaceClaim ecFinaceClaim) {
		List<EcFinaceClaim> ecFinaceClaimlst = ecFinaceClaimMapper.selectFinaceClaimList(ecFinaceClaim);
		return ecFinaceClaimlst;
	}

	/**
	 * Desc: 查询首期账户列表
	 * @param custNo
	 * @return
	 * @author
	 */
	public  List<EcFirstAct> getCustFirstActList(String custNo) {
		List<EcFirstAct> ecFirstActlst = ecFirstActMapper.selectByCustNo(custNo);
		return ecFirstActlst;
	}
}
