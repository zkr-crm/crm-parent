package cn.com.zsyk.crm.ecif.service.customer.portrait;

import java.util.List;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.com.zsyk.crm.common.util.LogUtil;
import cn.com.zsyk.crm.ecif.entity.EcCustProposal;
import cn.com.zsyk.crm.ecif.mapper.EcCustProposalMapper;

@Service
@Transactional
public class CustProposalService {

	/** logger */
	@SuppressWarnings("unused")
	private final Log logger = LogUtil.getLogger(CustProposalService.class);

	@Autowired
	private EcCustProposalMapper ecCustProposalMapper;

	/**
	 * Desc: 查询客户保单列表
	 * @param ecCustProposal
	 * @return
	 * @author
	 */
	public List<EcCustProposal> getCustProposalList(EcCustProposal ecCustProposal) {
		List<EcCustProposal> ecCustProposallst = ecCustProposalMapper.selectCustProposalList(ecCustProposal);
		return ecCustProposallst;
	}
}
