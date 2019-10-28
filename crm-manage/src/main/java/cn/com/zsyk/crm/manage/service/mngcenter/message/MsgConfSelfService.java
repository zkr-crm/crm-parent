package cn.com.zsyk.crm.manage.service.mngcenter.message;

import cn.com.zsyk.crm.common.dao.AbstractDao;
import cn.com.zsyk.crm.common.util.DateUtil;
import cn.com.zsyk.crm.common.util.HttpConnectionUtils;
import cn.com.zsyk.crm.common.util.LogUtil;
import cn.com.zsyk.crm.manage.entity.SysJobMsgRela;
import cn.com.zsyk.crm.manage.entity.SysMsgConf;
import cn.com.zsyk.crm.manage.service.mngcenter.job.JobMsgRelaService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class MsgConfSelfService {
	private final Log logger = LogUtil.getLogger(MsgConfSelfService.class);
    @Autowired
    MsgConfService msgConfService;

    @Autowired
    private AbstractDao daoUtil;
    
    @Autowired
    private HttpConnectionUtils httpConnectionUtils;
    
    @Autowired
    private JobMsgRelaService jobMsgRelaService;

    public List<SysMsgConf> getMsgConfSelfByEntity(String msgId, String schedulerName, String jobGroup, String jobId) {
        JSONObject postData = new JSONObject();
        //获取模板、事件、时间
        SysMsgConf sysMsgConf = daoUtil.selectOne("cn.com.zsyk.crm.manage.mapper.SysMsgConfMapper.getMsgConfSelfByMsgId", msgId);
        /**
             * 2:生日
             * 6：续保
             **/
        if (sysMsgConf == null) {
        	return null;
        }
        String date = DateUtil.getFetureDate(Integer.parseInt(sysMsgConf.getMsgAdvdays()));//发短信时间  同时也是查询生日时间
        Map<String, String> map = new HashMap<String, String>();
        JSONObject jsonData;
        List<Map<String, String>> customerList;
        SysJobMsgRela jobMsg = null;
        SysJobMsgRela jobMsgRela = null;
		jobMsg = new SysJobMsgRela();
		jobMsg.setJobGroup(jobGroup);
		jobMsg.setJobName(jobId);
		jobMsg.setSchedName(schedulerName);
		jobMsg.setMsgCode(sysMsgConf.getMsgId());
		jobMsgRela = jobMsgRelaService.getOneRecByPk(jobMsg);
		jobMsg.setCreateUser(sysMsgConf.getCreateUser());
		jobMsg.setUpdateUser(sysMsgConf.getCreateUser());
		map.put("eventType", sysMsgConf.getMsgEventType());
        if("2".equals(sysMsgConf.getMsgEventType())){
        	map.put("birthDate", date);
            //根据天数获取要发生日短信的人（手机号）
        	customerList = daoUtil.selectList("cn.com.zsyk.crm.manage.mapper.SysUserInfoMapper.getUserByEventAndDate", map);
        }else{
        	String beginDate = null;
        	int days = Integer.parseInt(sysMsgConf.getMsgAdvdays());
        	if (jobMsgRela != null) {
        		Date timeStamp = jobMsgRela.getTimeStamp();
        		if (DateUtil.dateAdd(Calendar.DAY_OF_YEAR, Integer.parseInt(sysMsgConf.getMsgAdvdays()), timeStamp).compareTo(DateUtil.dateAdd(Calendar.DAY_OF_YEAR, -days, new Date())) > 0) {
        			beginDate = DateUtil.formatDate(DateUtil.dateAdd(Calendar.DAY_OF_YEAR, Integer.parseInt(sysMsgConf.getMsgAdvdays()), timeStamp), "yyyy-MM-dd hh:mm:ss");
        		} else {
        			beginDate = DateUtil.formatDate(DateUtil.dateAdd(Calendar.DAY_OF_YEAR, -days, new Date()), "yyyy-MM-dd hh:mm:ss");
        		}
        	} else {
        		beginDate = DateUtil.formatDate(DateUtil.dateAdd(Calendar.DAY_OF_YEAR, -days, new Date()), "yyyy-MM-dd hh:mm:ss");
        	}
        	map.put("advdays", sysMsgConf.getMsgAdvdays());
    		map.put("beginDate", beginDate);
            //根据天数获取要发续保短信的人（手机号）
            String url = String.format("http://%s/crm/query/getCustomerInfo", "CRM-QUERY");
            jsonData = httpConnectionUtils.post(map, url);
            customerList = (List<Map<String, String>>) jsonData.get("list");
        }
        for(Map<String, String> customerInfo : customerList) {
        	String content = sysMsgConf.getMsgTplCont();
        	if (StringUtils.isEmpty(customerInfo.get("CONTACT_NAM"))) {
        		content = sysMsgConf.getMsgTplCont().replaceAll("\\{0\\}", customerInfo.get("CUST_NO"));
        	} else {
        		content = sysMsgConf.getMsgTplCont().replaceAll("\\{0\\}", customerInfo.get("CONTACT_NAM"));
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
             try {
     			this.saveOrUpdate(jobMsgRela, jobMsg);
     		} catch (Exception e) {
     			logger.error("自动发送短信时定时任务记录保存/修改失败：", e);
     		}
        }else {
        	logger.info("短信未发送原因：手机号为空 或者内容为空");
        }
        return null;
    }
    
    /**
     * 定时任务执行后将数据保存/更新至sys_job_msg_rela
     * @param jobMsgRela 数据库查询出的数据
     * @param jobMsg 新增数据
     */
//    @Transactional(rollbackFor = { Exception.class })
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
