package cn.com.zsyk.crm.ecif.service.customer.portrait;

import java.util.List;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.com.zsyk.crm.common.util.LogUtil;
import cn.com.zsyk.crm.ecif.entity.EcCustTouch;
import cn.com.zsyk.crm.ecif.mapper.EcCustTouchMapper;

@Service
@Transactional 
public class CustTouchService {
	/** logger */
	private final Log logger = LogUtil.getLogger(CustTouchService.class);

	@Autowired
	private EcCustTouchMapper ecCustTouchMapper;

	/**
	 * Desc: 查询客户接触信息列表
	 * @param EcCustTouch
	 * @return
	 * @author 
	 */
	public  List<EcCustTouch> getcustTouchList(EcCustTouch ecCustTouch) {
		List<EcCustTouch> ecCustTouchLst = ecCustTouchMapper.selectcustTouchList(ecCustTouch);
		return ecCustTouchLst;
	}

	/**
	 * Desc: 查询客户接触信息
	 * @param contactNo
	 * @return
	 * @author 
	 */
	public  EcCustTouch selectByContactNo(String contactNo) {
		EcCustTouch ecCustTouch = ecCustTouchMapper.selectByPrimaryKey(contactNo);
		return ecCustTouch;
	}
}
