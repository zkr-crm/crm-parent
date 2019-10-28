package cn.com.zsyk.crm.ecif.service.scheduler.birthmng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.zsyk.crm.common.constant.ServiceType;
import cn.com.zsyk.crm.common.dao.AbstractDao;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.util.RestUtil;
import cn.com.zsyk.crm.ecif.bo.cust.SysJobMsgRela;
import cn.com.zsyk.crm.ecif.entity.EcCustComplain;
import cn.com.zsyk.crm.generator.EnumType;

/**
 * 客户投诉提醒批量
 * 
 * @author
 * 
 *
 */
@Service
@Transactional
public class CustComplainRemindService {

	@Autowired
	private AbstractDao dao;
	@Autowired
	RestUtil restUtil;

	public List<EcCustComplain> getCustComplain() {

		// 获取所有未解决的客户投诉信息
		List<EcCustComplain> complainList = dao
				.selectList("cn.com.zsyk.crm.ecif.mapper.EcCustComplainMapper.selectUnsolved");

		return complainList;
	}

	
	/**
	 * 更新提醒ID为已提醒
	 * 
	 * @param custInfo
	 * @return
	 */
	public int updateRemindFlg(EcCustComplain custInfo) {
		Map map = new HashMap();
		map.put("remindFlg", EnumType.YesNoFlg.yes.value);
		map.put("complainNo", custInfo.getComplainNo());
		map.put("custNo", custInfo.getCustNo());
		map.put("caseNo", custInfo.getCaseNo());
		// 获取所有未解决的客户投诉信息
		int result = dao.update("cn.com.zsyk.crm.ecif.mapper.EcCustComplainMapper.updateRemindFlg", map);

		return result;
	}

	@SuppressWarnings({ "rawtypes" })
	public List<String> getRemindCode(SysJobMsgRela jobMsg) {
		Result ret = restUtil.postForObject(ServiceType.MANAGE, "/crm/manage/msgmng/getJobMsgByEntity", jobMsg,
				Result.class);

		List resList = (List) ret.getData();
		List<String> retBackList = new ArrayList<String>();

		for (Object item : resList) {
			Map resMap = (Map) item;
			String msgCode = (String) resMap.get("remindCode");
			retBackList.add(msgCode);
		}

		return retBackList;
	}

}
