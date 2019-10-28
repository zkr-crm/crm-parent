package cn.com.zsyk.crm.ecif.service.customer.portrait;

import java.util.List;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.com.zsyk.crm.common.util.LogUtil;
import cn.com.zsyk.crm.ecif.entity.EcCustClaim;
import cn.com.zsyk.crm.ecif.mapper.EcCustClaimMapper;

@Service
@Transactional 
public class CustClaimService {
	@Autowired
	private EcCustClaimMapper ecCustClaimMapper;

	/**
	 * Desc: 查询客户理赔列表
	 * @param ecCustClaim
	 * @return
	 * @author
	 */
	public List<EcCustClaim> getCustClaimList(EcCustClaim ecCustClaim) {
		List<EcCustClaim> ecCustClaimlst = ecCustClaimMapper.selectCustClaimList(ecCustClaim);
		return ecCustClaimlst;
	}

}
