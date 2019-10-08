package cn.com.zsyk.crm.manage.service.mngcenter.message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.zsyk.crm.common.dao.AbstractDao;
import cn.com.zsyk.crm.common.util.DateUtil;
import cn.com.zsyk.crm.common.util.HttpConnectionUtils;
import cn.com.zsyk.crm.common.util.LogUtil;
import cn.com.zsyk.crm.manage.bom.SysMsgConfInfo;
import cn.com.zsyk.crm.manage.entity.SysMsgConf;

@Service
public class MsgConfSelfService {
	private final Log logger = LogUtil.getLogger(MsgConfSelfService.class);
    @Autowired
    MsgConfService msgConfService;

    @Autowired
    private AbstractDao daoUtil;
    
    @Autowired
    private HttpConnectionUtils httpConnectionUtils;

    public List<SysMsgConf> getMsgConfSelfByEntity(String msgId) {
        JSONObject postData = new JSONObject();
        //获取模板、事件、时间
        SysMsgConfInfo sysMsgConf = daoUtil.selectOne("cn.com.zsyk.crm.manage.mapper.SysMsgConfMapper.getMsgConfSelfByMsgId", msgId);
        /**
             * 2:生日
             * 6：续保
             **/
        if (sysMsgConf == null) {
        	return null;
        }
        String date = DateUtil.getFetureDate(Integer.parseInt(sysMsgConf.getMsgAdvdays()));//发短信时间  同时也是查询生日时间
        Map<String, String> map = new HashMap<String, String>();
        map.put("endDate", date);
        JSONObject jsonData;
        if("2".equals(sysMsgConf.getMsgEventType())){
            //根据天数获取要发生日短信的人（手机号）
            String url = String.format("http://%s/crm/ecif/getCustomerByBirthday", "CRM-ECIF");
            jsonData = httpConnectionUtils.post(date, url);
        }else{
            //根据天数获取要发续保短信的人（手机号）
            String url = String.format("http://%s/crm/query/getCustomerInfo", "CRM-QUERY");
            jsonData = httpConnectionUtils.post(map, url);
        }
        List<Map<String, String>> customerList = (List<Map<String, String>>) jsonData.get("list");
        for(Map<String, String> customerInfo : customerList) {
        	String content = sysMsgConf.getTplCont();
        	if (StringUtils.isEmpty(customerInfo.get("CONTACT_NAM"))) {
        		content = sysMsgConf.getTplCont().replaceAll("\\{0\\}", customerInfo.get("CUST_NO"));
        	} else {
        		content = sysMsgConf.getTplCont().replaceAll("\\{0\\}", customerInfo.get("CONTACT_NAM"));
        	}
        	if ("2".equals(sysMsgConf.getMsgEventType())) {
        		if (!StringUtils.isEmpty(customerInfo.get("BIRTH_DATE"))) {
        			content = content.replaceAll("\\{1\\}", customerInfo.get("BIRTH_DATE"));
        		} else {
        			content = content.replaceAll("\\{1\\}", date.substring(5));
        		}
        	} else {
        		if (!StringUtils.isEmpty(customerInfo.get("ENDDATE"))) {
        			content = content.replaceAll("\\{1\\}", customerInfo.get("ENDDATE"));
        		} else {
        			content = content.replaceAll("\\{1\\}", date.substring(5));
        		}
        	}
        	customerInfo.put("content", content);
        }
        postData.put("msgList", JSONArray.parseArray(JSON.toJSONString(customerList)));
        // 调用短信接口
        if(postData.get("msgList") != null) {
             String url = String.format("http://%s/crm/supply/schedulerSendMsg", "CRM-SUPPLY");
             httpConnectionUtils.post(postData, url);
        }else {
        	logger.info("短信未发送原因：手机号为空 或者内容为空");
        }
        return null;
    }

}
