package cn.com.zsyk.crm.ecif.service.customer.portrait;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.com.zsyk.crm.ecif.entity.EcCustComplain;
import cn.com.zsyk.crm.ecif.mapper.EcCustComplainMapper;

@Service
@Transactional 
public class CustComplainService {
	@Autowired	
	private EcCustComplainMapper ecCustComplainMapper;
	/**
	 * Desc: 查询客户投诉信息列表
	 * @param ecCustComplain
	 * @return
	 * @author
	 */
	public List<EcCustComplain> getCustComplainList(EcCustComplain ecCustComplain) {
		List<EcCustComplain> ecCustComplainlst = ecCustComplainMapper.selectCustComplainList(ecCustComplain);
		return ecCustComplainlst;
	}

}
