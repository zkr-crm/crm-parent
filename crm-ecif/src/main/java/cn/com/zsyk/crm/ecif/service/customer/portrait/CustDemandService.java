package cn.com.zsyk.crm.ecif.service.customer.portrait;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.LogUtil;
import cn.com.zsyk.crm.ecif.entity.EcCustDemand;
import cn.com.zsyk.crm.ecif.mapper.EcCustDemandMapper;
import cn.com.zsyk.crm.ecif.service.customer.baseinfo.CustService;
import cn.com.zsyk.crm.generator.EnumType;

@Service
@Transactional 
public class CustDemandService {
	/** logger */
	private final Log logger = LogUtil.getLogger(CustDemandService.class);

	@Autowired
	private EcCustDemandMapper ecCustDemandMapper;

	@Autowired
	private CustService custService;

	/**
	 * Desc: 查询客户需求列表
	 * @param custNo
	 * @return
	 * @author
	 */
	public List<EcCustDemand> selectCustDemandList(String custNo) {
		// 获取需求列表
		List<EcCustDemand> ecCustDemandlst = ecCustDemandMapper.selectByCustNo(custNo);
		// 客户类型
		String custTyp = custService.getCustType(custNo);
		String custNam = "";

		logger.info("custTyp:" +custTyp );
		if (custTyp.equals(EnumType.CustType.ent_formal_cust.getValue()) || custTyp.equals(EnumType.CustType.ent_latent_cust.getValue())) {
			custNam = custService.getEntCustName(custNo);
		} else if (custTyp.equals(EnumType.CustType.per_latent_cust.getValue()) || custTyp.equals(EnumType.CustType.per_formal_cust.getValue())) {
			custNam = custService.getPerCustName(custNo);
		} else {
			throw new ServiceException("客户类型错误");
		}
		logger.info("custNam:" +custNam );

		List<EcCustDemand> retList = new ArrayList<EcCustDemand>();
		if (ecCustDemandlst != null && ecCustDemandlst.size() > 0) {
			for(EcCustDemand obj : ecCustDemandlst) {
				obj.setCustNam(custNam);
				retList.add(obj);
			}
		}
		return retList;
	}

	/**
	 * Desc: 查询客户需求(单条)
	 * @param custNo
	 * @return
	 * @author
	 */
	public EcCustDemand selectCustDemandOne(String demandNo) {
		// 获取需求列表
		EcCustDemand ecCustDemand = ecCustDemandMapper.selectByPrimaryKey(demandNo);
		if (null == ecCustDemand) {
			throw new ServiceException("客户需求信息不存在");
		}
		// 客户类型
		String custTyp = custService.getCustType(ecCustDemand.getCustNo());
		String custNam = "";

		logger.info("custTyp:" +custTyp );
		if (custTyp.equals(EnumType.CustType.ent_formal_cust.getValue()) || custTyp.equals(EnumType.CustType.ent_latent_cust.getValue())) {
			custNam = custService.getEntCustName(ecCustDemand.getCustNo());
		} else if (custTyp.equals(EnumType.CustType.per_formal_cust.getValue()) || custTyp.equals(EnumType.CustType.per_formal_cust.getValue())) {
			custNam = custService.getPerCustName(ecCustDemand.getCustNo());
		} else {
			throw new ServiceException("客户类型错误");
		}
		logger.info("custNam:" +custNam );

		ecCustDemand.setCustNam(custNam);

		return ecCustDemand;
	}
}
