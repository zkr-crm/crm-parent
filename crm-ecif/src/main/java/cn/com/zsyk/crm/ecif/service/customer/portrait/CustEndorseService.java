package cn.com.zsyk.crm.ecif.service.customer.portrait;

import java.util.List;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.com.zsyk.crm.common.util.LogUtil;
import cn.com.zsyk.crm.ecif.entity.EcCustEndorse;
import cn.com.zsyk.crm.ecif.mapper.EcCustEndorseMapper;

@Service
@Transactional 
public class CustEndorseService {
	/** logger */
	@SuppressWarnings("unused")
	private final Log logger = LogUtil.getLogger(CustEndorseService.class);

	@Autowired
	private EcCustEndorseMapper ecCustEndorseMapper;

	/**
	 * Desc: 查询客户保全信息列表
	 * @param ecCustEndorse
	 * @return
	 * @author
	 */
	public List<EcCustEndorse> getCustEndorseList(EcCustEndorse ecCustEndorse) {
		List<EcCustEndorse> ecCustEndorselst = ecCustEndorseMapper.selectEndorseList(ecCustEndorse);
		return ecCustEndorselst;
	}

}
