package cn.com.zsyk.crm.manage.service.mngcenter.message;

import cn.com.zsyk.crm.common.dao.AbstractDao;
import cn.com.zsyk.crm.common.dto.ContextContainer;
import cn.com.zsyk.crm.common.dto.CoreContext;
import cn.com.zsyk.crm.common.util.DateUtil;
import cn.com.zsyk.crm.common.util.HttpConnectionUtils;
import cn.com.zsyk.crm.common.util.LogUtil;
import cn.com.zsyk.crm.manage.bom.SysAutoRemindInfo;
import cn.com.zsyk.crm.manage.bom.SysUserRemindInfo;
import cn.com.zsyk.crm.manage.entity.SysAppRemind;
import cn.com.zsyk.crm.manage.entity.SysAutoRemind;
import cn.com.zsyk.crm.manage.mapper.SysAppRemindMapper;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class AutoRemindSelfService {
	private final Log logger = LogUtil.getLogger(AutoRemindSelfService.class);
    @Autowired
    AutoRemindService autoRemindService;

    @Autowired
    private AbstractDao daoUtil;

    @Autowired
    private SysAppRemindMapper sysAppRemindMapper;

    @Autowired
    private HttpConnectionUtils httpConnectionUtils;

    public List<SysAutoRemind> getAutoRemindSelfByEntity(String msgId) {
        //获取模板、事件、时间
        SysAutoRemindInfo sysAutoRemind = daoUtil.selectOne("cn.com.zsyk.crm.manage.mapper.SysAutoRemindMapper.getAutoRemindSelfByMsgId", msgId);
        if (sysAutoRemind == null) {
        	return null;
        }
        //计算事件时间
        String date = DateUtil.getFetureDate(Integer.parseInt(sysAutoRemind.getMsgAdvdays()));//发短信时间  同时也是查询生日时间
        //根据天数获取要发站内提醒的人（客户经理:创建人）
        Map<String, String> map = new HashMap<String, String>();
        map.put("endDate", date);
        map.put("eventDate", date);
        map.put("eventType", sysAutoRemind.getMsgEventType());
        //获取用户数量
        List<SysUserRemindInfo> userList = null;
        if("6".equals(sysAutoRemind.getMsgEventType())){//续保，查询query服务
            //根据天数获取要发续保短信的人（手机号）
        	String url = String.format("http://%s/crm/query/getCustomerInfo", "CRM-QUERY");
            JSONObject jsonData = httpConnectionUtils.post(map, url);
            userList = convertRenewalData((List<Map<String, String>>)jsonData.get("list"), userList);
        }else{
        	userList = daoUtil.selectList("cn.com.zsyk.crm.manage.mapper.SysUserInfoMapper.getUserByEventAndDate", map);
        }
        if(userList != null && !userList.isEmpty()){
            List<SysAppRemind> list = new ArrayList<>();
            CoreContext cc = new CoreContext();
            Map mapp = new HashMap();
            mapp.put("userId", "admin");
            cc.putAll(mapp);
            ContextContainer.setContext(cc);
            for(SysUserRemindInfo sysUserRemindInfo : userList) {
                //组装对象
                SysAppRemind appRemind = new SysAppRemind();
                appRemind.setSendUser(sysAutoRemind.getCreateUser());
                appRemind.setIsRead("0");
                appRemind.setRemindId(UUID.randomUUID().toString().replace("-",""));
                appRemind.setReceivUser(sysUserRemindInfo.getEmployeeId());
                String remindContent = sysAutoRemind.getTplCont();
                if (StringUtils.isEmpty(sysUserRemindInfo.getCustName())) {
                	remindContent = remindContent.replaceAll("\\{0\\}", sysUserRemindInfo.getCustNo());
                } else {
                	remindContent = remindContent.replaceAll("\\{0\\}", sysUserRemindInfo.getCustName());
                }
                if (StringUtils.isEmpty(sysUserRemindInfo.getBirthDate())) {
                	remindContent = remindContent.replaceAll("\\{1\\}", date.substring(5));
                } else {
                	remindContent = remindContent.replaceAll("\\{1\\}", sysUserRemindInfo.getBirthDate());
                }
                if (StringUtils.isEmpty(sysUserRemindInfo.getPhoneNo())) {
                	remindContent = remindContent.replaceAll("\\{2\\}", "");
                } else {
                	remindContent = remindContent.replaceAll("\\{2\\}", sysUserRemindInfo.getPhoneNo());
                }
                appRemind.setRemindContent(remindContent);
                appRemind.setRemindTitle("私信");
                appRemind.setRemindLevel("0");
                //发送站内提醒
                sysAppRemindMapper.insertSysAppRemind(appRemind);
            }
        }

        return null;
    }

    /**
         *  转换查询query服务的续保提醒的客户信息
     * @param list 
     * @param userList 
     * @return
     */
	private List<SysUserRemindInfo> convertRenewalData(List<Map<String, String>> list, List<SysUserRemindInfo> userList) {
		userList = new ArrayList<SysUserRemindInfo>();
		if (list != null && list.size() > 0) {
			SysUserRemindInfo sysUserRemindInfo;
			for (Map<String, String> customerInfo : list) {
				sysUserRemindInfo = new SysUserRemindInfo();
				sysUserRemindInfo.setUserId(customerInfo.get("USER_ID"));
				sysUserRemindInfo.setUserName(customerInfo.get("USER_NAME"));
				sysUserRemindInfo.setCustNo(customerInfo.get("CUST_NO"));
				sysUserRemindInfo.setCustName(customerInfo.get("CONTACT_NAM"));
				sysUserRemindInfo.setBirthDate(customerInfo.get("ENDDATE"));
				sysUserRemindInfo.setPhoneNo(customerInfo.get("PHONE_NO1"));
                sysUserRemindInfo.setEmployeeId(customerInfo.get("EMPLOYEE_ID"));
				userList.add(sysUserRemindInfo);
			}
		}
		return userList;
	}
}
