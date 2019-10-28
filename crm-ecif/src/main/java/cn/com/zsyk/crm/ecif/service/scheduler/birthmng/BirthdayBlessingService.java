package cn.com.zsyk.crm.ecif.service.scheduler.birthmng;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.zsyk.crm.common.constant.ServiceType;
import cn.com.zsyk.crm.common.dao.AbstractDao;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.util.RestUtil;
import cn.com.zsyk.crm.ecif.bo.cust.PerCustList;
import cn.com.zsyk.crm.ecif.bo.cust.SysJobMsgRela;
import cn.com.zsyk.crm.ecif.mapper.EcCustPerMapper;

@Service
@Transactional
public class BirthdayBlessingService {

	@Autowired
	private AbstractDao dao;
	@Autowired
	EcCustPerMapper custMapper;
	@Autowired
	RestUtil restUtil;

	public List<PerCustList> sendBirthdayBlessing( ) {
		// 获取当前日期
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");// 可以方便地修改日期格式
		// 当前日期为发送日期，获得客户生日日期
		String birthday = dateFormat.format(now);
		// 从客户信息表中查询所有过生日的客户信息（list）
		List<PerCustList> custList = dao.selectList("selectByBirthDate", "2018-01-06".substring(5, birthday.length()));

		return custList;
	}
	
	
	@SuppressWarnings({ "rawtypes"})
	public List<String> getMsgCode(SysJobMsgRela jobMsg) {

		Result ret = restUtil.postForObject(ServiceType.MANAGE,"/crm/manage/msgmng/getJobMsgByEntity", jobMsg,Result.class);
		
		
		List resList = (List) ret.getData();
		List<String> retBackList = new ArrayList<String>();
		
		for(Object item : resList) {
			Map resMap = (Map) item;
			String msgCode = (String) resMap.get("msgCode");
			retBackList.add(msgCode);
		}
		
		return retBackList;
	}

}
