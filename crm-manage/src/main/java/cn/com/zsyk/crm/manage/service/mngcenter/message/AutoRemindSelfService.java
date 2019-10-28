package cn.com.zsyk.crm.manage.service.mngcenter.message;

import cn.com.zsyk.crm.common.dao.AbstractDao;
import cn.com.zsyk.crm.common.dto.ContextContainer;
import cn.com.zsyk.crm.common.dto.CoreContext;
import cn.com.zsyk.crm.common.util.DateUtil;
import cn.com.zsyk.crm.common.util.HttpConnectionUtils;
import cn.com.zsyk.crm.common.util.LogUtil;
import cn.com.zsyk.crm.manage.entity.SysAppRemind;
import cn.com.zsyk.crm.manage.entity.SysAutoRemind;
import cn.com.zsyk.crm.manage.entity.SysJobMsgRela;
import cn.com.zsyk.crm.manage.mapper.SysAppRemindMapper;
import cn.com.zsyk.crm.manage.service.mngcenter.job.JobMsgRelaService;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    
    @Autowired
    private JobMsgRelaService jobMsgRelaService;

    public List<SysAutoRemind> getAutoRemindSelfByEntity(String msgId, String schedulerName, String jobGroup, String jobId) {
        //获取模板、事件、时间
         SysAutoRemind sysAutoRemind = daoUtil.selectOne("cn.com.zsyk.crm.manage.mapper.SysAutoRemindMapper.getAutoRemindSelfByMsgId", msgId);
        if (sysAutoRemind == null) {
        	return null;
        }
        //计算事件时间
        String date = DateUtil.getFetureDate(Integer.parseInt(sysAutoRemind.getMsgAdvdays()));//发短信时间  同时也是查询生日时间
        //根据天数获取要发站内提醒的人（客户经理:创建人）
        Map<String, String> map = new HashMap<String, String>();
        //获取用户数量
        List<Map<String, String>> userList = null;
        SysJobMsgRela jobMsg = null;
        SysJobMsgRela jobMsgRela = null;
        jobMsg = new SysJobMsgRela();
        jobMsg.setJobGroup(jobGroup);
        jobMsg.setJobName(jobId);
        jobMsg.setSchedName(schedulerName);
        jobMsg.setMsgCode(sysAutoRemind.getMsgId());
        jobMsgRela = jobMsgRelaService.getOneRecByPk(jobMsg);
        if("6".equals(sysAutoRemind.getMsgEventType())){//续保，查询query服务
        	//续保提醒时，根据定时任务上次执行时间 + 提前天数，当前时间减去提前天数，取二者较大值作为查询条件
        	/*jobMsg = new SysJobMsgRela();
            jobMsg.setJobGroup(jobGroup);
            jobMsg.setJobName(jobId);
            jobMsg.setSchedName(schedulerName);
            jobMsg.setRemindCode(sysAutoRemind.getMsgId());
        	jobMsg.setRemindCode(sysAutoRemind.getMsgId());
            jobMsgRela = jobMsgRelaService.getOneRecByPk(jobMsg);*/
        	String beginDate = null;
        	int days = Integer.parseInt(sysAutoRemind.getMsgAdvdays());
        	if (jobMsgRela != null) {
        		Date timeStamp = jobMsgRela.getTimeStamp();
        		if (DateUtil.dateAdd(Calendar.DAY_OF_YEAR, Integer.parseInt(sysAutoRemind.getMsgAdvdays()), timeStamp).compareTo(DateUtil.dateAdd(Calendar.DAY_OF_YEAR, -days, new Date())) > 0) {
        			beginDate = DateUtil.formatDate(DateUtil.dateAdd(Calendar.DAY_OF_YEAR, Integer.parseInt(sysAutoRemind.getMsgAdvdays()), timeStamp), "yyyy-MM-dd hh:mm:ss");
        		} else {
        			beginDate = DateUtil.formatDate(DateUtil.dateAdd(Calendar.DAY_OF_YEAR, -days, new Date()), "yyyy-MM-dd hh:mm:ss");
        		}
        	} else {
        		beginDate = DateUtil.formatDate(DateUtil.dateAdd(Calendar.DAY_OF_YEAR, -days, new Date()), "yyyy-MM-dd hh:mm:ss");
        	}
            //根据天数获取要发续保短信的人（手机号）
        	map.put("advdays", sysAutoRemind.getMsgAdvdays());
    		map.put("beginDate", beginDate);
            String url = String.format("http://%s/crm/query/getCustomerInfo", "CRM-QUERY");
            JSONObject jsonData = httpConnectionUtils.post(map, url);
            userList = (List<Map<String, String>>)jsonData.get("list");
        } else {
        	map.put("birthDate", date);
        	map.put("eventDate", date);
        	map.put("eventType", sysAutoRemind.getMsgEventType());
        	userList = daoUtil.selectList("cn.com.zsyk.crm.manage.mapper.SysUserInfoMapper.getUserByEventAndDate", map);
        }
        if(userList != null && !userList.isEmpty()){
            List<SysAppRemind> list = new ArrayList<>();
            CoreContext cc = new CoreContext();
            Map mapp = new HashMap();
            mapp.put("userId", "admin");
            cc.putAll(mapp);
            ContextContainer.setContext(cc);
            for(Map<String, String> sysUserRemindInfo : userList) {
                //组装对象
                SysAppRemind appRemind = new SysAppRemind();
                appRemind.setSendUser(sysAutoRemind.getCreateUser());
                appRemind.setIsRead("0");
                appRemind.setRemindId(UUID.randomUUID().toString().replace("-",""));
                appRemind.setReceivUser(sysUserRemindInfo.get("EMPLOYEE_ID"));
                appRemind.setCustNo(sysUserRemindInfo.get("CUST_NO"));
                appRemind.setCustName(sysUserRemindInfo.get("CONTACT_NAM"));
                appRemind.setEventType(sysAutoRemind.getMsgEventType());
                if ("6".equals(sysAutoRemind.getMsgEventType())) {
                	appRemind.setEventDate(sysUserRemindInfo.get("ENDDATE"));
                    appRemind.setRiskCname(sysUserRemindInfo.get("RISKCNAME"));
                } else {
                	appRemind.setEventDate(date);
                }
                appRemind.setPolicyNo(sysUserRemindInfo.get("POLICYNO"));
                appRemind.setEndDate(sysUserRemindInfo.get("ENDDATE"));
                appRemind.setMobile(sysUserRemindInfo.get("PHONE_NO1"));
                String remindContent = sysAutoRemind.getMsgTplCont();
                if (StringUtils.isEmpty(appRemind.getCustName())) {
                	remindContent = remindContent.replaceAll("\\{0\\}", "");
                } else {
                	remindContent = remindContent.replaceAll("\\{0\\}", appRemind.getCustName());
                }
                if (StringUtils.isEmpty(appRemind.getEventDate())) {
                    remindContent = remindContent.replaceAll("\\{1\\}", "");
                } else {
                    remindContent = remindContent.replaceAll("\\{1\\}", appRemind.getEventDate());
                }
                if (StringUtils.isEmpty(appRemind.getMobile())) {
                	remindContent = remindContent.replaceAll("\\{2\\}", "");
                } else {
                	remindContent = remindContent.replaceAll("\\{2\\}", appRemind.getMobile());
                }
                appRemind.setRemindContent(remindContent);
                appRemind.setRemindTitle("私信");
                appRemind.setRemindLevel("0");
                //发送站内提醒
                sysAppRemindMapper.insertSysAppRemind(appRemind);
            }
            try {
            	this.saveOrUpdate(jobMsgRela, jobMsg);
            } catch (Exception e) {
            	logger.error("自动发送站内提醒时定时任务记录保存/修改失败：", e);
            }
        }
        return null;
    }
    
    /**
     * 定时任务执行后将数据保存/更新至sys_job_msg_rela
     * @param jobMsgRela 数据库查询出的数据
     * @param jobMsg 新增数据
     */
    @Transactional(rollbackFor = { Exception.class })
    public void saveOrUpdate(SysJobMsgRela jobMsgRela, SysJobMsgRela jobMsg) {
    	if (jobMsgRela == null) {
        	jobMsg.setCreateDate(DateUtil.formatDate(new Date(), "yyyyMMdd"));
        	jobMsg.setCreateTime(DateUtil.formatDate(new Date(), "hhmmss"));
        	jobMsg.setUpdateDate(DateUtil.formatDate(new Date(), "yyyyMMdd"));
        	jobMsg.setUpdateTime(DateUtil.formatDate(new Date(), "hhmmss"));
        	jobMsg.setTimeStamp(new Date());
        	jobMsgRelaService.insertOne(jobMsg);
        } else {
        	jobMsgRelaService.updateOne(jobMsgRela);
        }
    }

}
