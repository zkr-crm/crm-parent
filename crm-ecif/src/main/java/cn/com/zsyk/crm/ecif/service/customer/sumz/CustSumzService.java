package cn.com.zsyk.crm.ecif.service.customer.sumz;

import java.util.List;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.com.zsyk.crm.common.dao.AbstractDao;
import cn.com.zsyk.crm.common.util.LogUtil;
import cn.com.zsyk.crm.ecif.bo.cust.CustSumzInfo;
@Service
@Transactional
public class CustSumzService {
	/** logger */
	@SuppressWarnings("unused")
	private final Log logger = LogUtil.getLogger(CustSumzService.class);
	@Autowired
	AbstractDao dao;

	public CustSumzInfo getLastFollowUp(String custNo) {
		CustSumzInfo retInfo = new CustSumzInfo();
		List<CustSumzInfo> sumzList = dao.getSqlSessionTemplate().selectList("cn.com.zsyk.crm.ecif.mapper.EcCustProposalMapper.getLastFollowUp", custNo);
		if (sumzList != null && sumzList.size() >0) {
			retInfo = sumzList.get(0);
		} else {
			retInfo = null;
		}
		return retInfo;
	}


	public CustSumzInfo getFirtPremium(String custNo) {
		CustSumzInfo retInfo = new CustSumzInfo();
		List<CustSumzInfo> sumzList = dao.getSqlSessionTemplate().selectList("cn.com.zsyk.crm.ecif.mapper.EcCustProposalMapper.getFirtPremium", custNo);
		if (sumzList != null && sumzList.size() >0) {
			retInfo = sumzList.get(0);
		} else {
			retInfo = null;
		}
		return retInfo;
	}
	
	
	/**
	 * 获取历次消费时间和金额
	 * @param custNo
	 * @return
	 */
	public List<CustSumzInfo> getLastPremium(String custNo) {
		CustSumzInfo retInfo = new CustSumzInfo();
		List<CustSumzInfo> sumzList = dao.getSqlSessionTemplate().selectList("cn.com.zsyk.crm.ecif.mapper.EcCustProposalMapper.getLastPremium", custNo);

		return sumzList;
	}


	public CustSumzInfo getTotalPremium(String custNo) {
		CustSumzInfo retInfo = new CustSumzInfo();
		List<CustSumzInfo> sumzList = dao.getSqlSessionTemplate().selectList("cn.com.zsyk.crm.ecif.mapper.EcCustProposalMapper.getTotalPremium", custNo);
		if (sumzList != null && sumzList.size() >0) {
			retInfo = sumzList.get(0);
		} else {
			retInfo = null;
		}
		return retInfo;
	}


	public CustSumzInfo getTotalPremiumForPastYear(String custNo) {
		CustSumzInfo retInfo = new CustSumzInfo();
		List<CustSumzInfo> sumzList = dao.getSqlSessionTemplate().selectList("cn.com.zsyk.crm.ecif.mapper.EcCustProposalMapper.getTotalPremiumForPastYear", custNo);
		if (sumzList != null && sumzList.size() >0) {
			retInfo = sumzList.get(0);
		} else {
			retInfo = null;
		}
		return retInfo;
	}

}
