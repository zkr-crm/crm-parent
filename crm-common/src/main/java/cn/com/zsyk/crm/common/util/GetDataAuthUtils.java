package cn.com.zsyk.crm.common.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.zsyk.crm.common.dto.ContextContainer;

/**
 * 获取用户权限
 * @author houya
 *
 */
@Service
public class GetDataAuthUtils {
    @Autowired
    private HttpConnectionUtils httpConnectionUtils;
    @Autowired
    private RedisUtil redisUtil;
	private final Log logger = LogUtil.getLogger(GetDataAuthUtils.class);
	
    /**
     * 获取用户 employeeId 集合 
     * @param isContain 是否包含本身employeeId true 包含 false 不包含
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<String> getRoleDateAuth(boolean isContain) {
		List<String> employeeIdList = null;
		try {
			String roleDateAuthKey = ContextContainer.getContext().getUserId() + "T001";
			//delete by linbangbo 20191015 begin 放到redis里会涉及到权限变更得问题,目前没有缓存清除机制，暂时注释
			/*try {
				if(redisUtil.exists(roleDateAuthKey)) {
					employeeIdList = (List<String>) redisUtil.get(roleDateAuthKey);
					return employeeIdList;
				}
			} catch (Exception e) {
				logger.error("common-service中得从缓存 redisUtil 方法 获取用户 employeeId 集合 异常：",e);
			}*/
			//delete by linbangbo 20191015 end 放到redis里会涉及到权限变更得问题
	    	//获取权限接口
	    	String url = String.format("http://%s/crm/manage/auth/getRoleDateAuth?userCode={userCode}&tableCode={tableCode}", "CRM-MANAGE");
    		//参数拼接
        	UriComponents uriComponents2 = UriComponentsBuilder.fromUriString(url).build().expand(ContextContainer.getContext().getUserId(), "T001").encode();
    		//接口调用
        	JSONObject userJson =httpConnectionUtils.get(uriComponents2);
        	//数据获取
    		JSONArray jsonArray = (JSONArray) userJson.get("data");
    		//数据组装
    		employeeIdList = new ArrayList<String>();
    		if(jsonArray != null) {
    			for(int i=0;i<jsonArray.size();i++) {
    	            employeeIdList.add((String) jsonArray.getJSONObject(i).get("employeeId"));
    	        }
    		}
    		//是包含 本身employeeId
    		/*if(isContain) {
    			employeeIdList.add(ContextContainer.getContext().getEmployeeId());
    		}*/
    		try {
    			//设置 过期时间默认为30分钟
				redisUtil.set(roleDateAuthKey, employeeIdList, 1800l);
			} catch (Exception e) {
				logger.error("common-service中 用户 employeeId 集合 设置redis 缓存 异常：",e);
			}
    		logger.info("employeeIdList:"+employeeIdList);
		} catch (Exception e) {
			logger.error("common-service中得getRoleDateAuth 方法 获取用户 employeeId 集合 异常：",e);
			throw e; 
		}
		return employeeIdList;
    }
}
