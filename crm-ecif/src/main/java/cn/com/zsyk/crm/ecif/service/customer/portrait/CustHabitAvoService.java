package cn.com.zsyk.crm.ecif.service.customer.portrait;

import java.util.List;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.com.zsyk.crm.common.util.LogUtil;
import cn.com.zsyk.crm.ecif.entity.EcCustHabitavo;
import cn.com.zsyk.crm.ecif.mapper.EcCustHabitavoMapper;
import cn.com.zsyk.crm.ecif.mapper.EcHabitAvocMapper;

import static cn.com.zsyk.crm.common.util.LogUtil.*;

@Service
@Transactional 
public class CustHabitAvoService {
	/** logger */
	private final Log logger = getLogger(CustHabitAvoService.class);

	@Autowired
	private EcHabitAvocMapper ecHabitAvocMapper;
	@Autowired
	private EcCustHabitavoMapper ecCustHabitavoMapper;

	/**
	 * Desc: 查询客户习惯嗜好列表
	 * @param custNo
	 * @return
	 * @author
	 */
	public List<EcCustHabitavo> getCustHabitAvoList(String custNo) {
		List<EcCustHabitavo> ecCustHabitavolst = ecCustHabitavoMapper.selectByCustNo(custNo);
		return ecCustHabitavolst;
	}


}
