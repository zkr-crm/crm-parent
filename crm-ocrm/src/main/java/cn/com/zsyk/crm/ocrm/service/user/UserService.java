package cn.com.zsyk.crm.ocrm.service.user;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.com.zsyk.crm.common.constant.ServiceType;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.util.RestUtil;

@Service
@Transactional
public class UserService {

	@Autowired
	RestUtil restUtil;
	/**
	 * Desc: 用户map（userId，userName）
	 * @return
	 * @author
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String, String> getAgentListMapByUserId() {
		Map<String, String> retMap = new HashMap<String, String>();
		Result result = restUtil.getForObject(ServiceType.MANAGE, "/crm/manage/usermng/usersByEntity",Result.class);
		if (result != null) {
			List<LinkedHashMap> lstObj = (List<LinkedHashMap>)result.getData() ;
			if (lstObj!= null) {
				for(LinkedHashMap o : lstObj) {
					String userId = (String) o.get("userId");
					String userName = (String)o.get("userName");
					retMap.put(userId, userName);
				}
			}
		}
		return retMap;
	}

	/**
	 * Desc: 用户map（employeeId，userName）
	 * @return
	 * @author
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, String> getAgentListMapByEmployeeId() {
		Map<String, String> retMap = new HashMap<String, String>();
		Result result = restUtil.getForObject(ServiceType.MANAGE, "/crm/manage/usermng/usersByEntity",Result.class);
		if (result != null) {
			List<LinkedHashMap> lstObj = (List<LinkedHashMap>)result.getData() ;
			if (lstObj!= null) {
				for(LinkedHashMap o : lstObj) {
					String employeeId = (String) o.get("employeeId");
					String userName = (String)o.get("userName");
					retMap.put(employeeId, userName);
				}
			}
		}
		return retMap;
	}
	
}
