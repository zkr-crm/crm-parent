package cn.com.zsyk.crm.supply.service;

import cn.com.zsyk.crm.common.dao.CoreDaoImpl;
import cn.com.zsyk.crm.common.dto.PageBean;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.RestUtil;
import cn.com.zsyk.crm.supply.entity.SysSmsSendGroup;
import cn.com.zsyk.crm.supply.mapper.SysSmsSendGroupMapper;
import com.github.pagehelper.util.StringUtil;
import com.wisdom.sms.net.http.Response;
import com.wisdom.sms.net.http.SmsServiceServiceLocator;
import com.wisdom.sms.net.http.SmsServiceSoapBindingStub;
import com.wisdom.sms.net.messages.SmsMessage;
import com.wisdom.sms.net.messages.SmsMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class SendSmsService {
    @Autowired
    private RestUtil restUtil;
    @Autowired
    private SysSmsSendGroupMapper sysSmsSendGroupMapper;
    /* 分页查询对象 */
    @Autowired
    private CoreDaoImpl coreDaoImpl;
    @Value("${smsSend.smsUrl}")
    private String smsUrl;
    @Value("${smsSend.phoneNum}")
    private int phoneNum;
    @Value("${smsSend.userName}")
    private String userName;
    @Value("${smsSend.passWord}")
    private String passWord;
    @Value("${smsSend.serviceType}")
    private String serviceType;
    @Value("${smsSend.agentCode}")
    private String agentCode;
    /**
     * 数据合法性检查
     *
     * @param sysSmsSendGroup
     *
     * @return 用户信息
     */
    public void dataCheck(SysSmsSendGroup sysSmsSendGroup) {
        // 短信标题不能为空
        if (StringUtil.isEmpty(sysSmsSendGroup.getMsgTopic())) {
            throw new ServiceException("短信标题不能为空！");
        }
        if (StringUtil.isEmpty(sysSmsSendGroup.getMsgContent())) {
            throw new ServiceException("短信发送内容不能为空！");
        }
        if (StringUtil.isEmpty(sysSmsSendGroup.getSendObj())) {
            throw new ServiceException("发送对象不能为空，请选择一个客群！");
        }
    }

    /**
     * 客群手动批量发送短信
     *
     * @param sysSmsSendGroup
     *
     */
    public void sendSms(SysSmsSendGroup sysSmsSendGroup) {
        //通过客群代码获取客群内所有成员信息
        List<Map<String,String>> memberList =this.getCustGroupMember(sysSmsSendGroup.getSendObj());
//        String smsUrl = "http://10.16.18.201:8001/wssmsif/services/SmsService?wsdl";
        List<Map<String,String>> infoList = new ArrayList<>();
        Map<String,String> infoMap= new HashMap<>();
//        int phoneNum=500;
//        int num=phoneList.size()/phoneNum;
//        int flag = 0;
//        for(int j=0;j<num;j++) {
//            for (int i = flag; i <phoneNum; i++) {
//                    phoneList.add(memberList.get(i).get("phoneNo"));
//            }
//            for (int i = flag; i <phoneNum; i++) {
//                phoneList.add(memberList.get(i).get("phoneNo"));
//            }
        String msgContent=sysSmsSendGroup.getMsgContent();
        if(phoneNum<memberList.size()){
            for (int i = 0; i <phoneNum; i++) {
                infoMap= new HashMap<>();
                if(sysSmsSendGroup.getMsgContent().contains("{0}")){
                    msgContent=sysSmsSendGroup.getMsgContent().replace("{0}",memberList.get(i).get("CUSTNAME"));
                }
                infoMap.put("PHONENO",memberList.get(i).get("PHONENO"));
                infoMap.put("MSGCONTENT",msgContent);
                infoList.add(i,infoMap);
//                infoList.add(memberList.get(i).get("PHONENO"));
            }
        }else{
            for (int i = 0; i <memberList.size(); i++) {
                infoMap= new HashMap<>();
                if(sysSmsSendGroup.getMsgContent().contains("{0}")){
                    msgContent=sysSmsSendGroup.getMsgContent().replace("{0}",memberList.get(i).get("CUSTNAME"));
                }
                infoMap.put("PHONENO",memberList.get(i).get("PHONENO"));
                infoMap.put("MSGCONTENT",msgContent);
                infoList.add(i,infoMap);
//                infoList.add(memberList.get(i).get("PHONENO"));
            }
        }
            this.smsInvoke(smsUrl, infoList,sysSmsSendGroup.getMsgTopic() ,sysSmsSendGroup.getSendObj() ,sysSmsSendGroup.getBizNo(),sysSmsSendGroup.getMsgContent(), "", "", "");
//            flag=flag+phoneNum;
//            phoneNum=phoneNum*2;

    }

    /**
     * 通过客群编码获取客群中所有客户信息
     *
     * @param groupId
     *
     * @return 用户信息(客户手机号码)
     */
    public List<Map<String,String>> getCustGroupMember(String groupId) {
//        List<OcCustGrpMember> detailMember = new ArrayList<>();
//        Result MemberDetail = restUtil.getForObject(ServiceType.OCRM,
//                "/crm/ocrm/CustGroupMng/getCustListByGroupId?provinceCode={groupId}",
//                Result.class, groupId);
//        if (MemberDetail != null) {
//            List<OcCustGrpMember> memberList = (List<OcCustGrpMember>)MemberDetail.getData();
//            if (memberList.size()!=0) {
//                for(OcCustGrpMember  list: memberList){
//                    detailMember.add(list);
//                }
//            }
//        }
        List<Map<String,String>> memberList =sysSmsSendGroupMapper.selectPhoneByGroupId(groupId);
        if(memberList != null){
            return memberList;
        }else{
            throw new ServiceException("选择客群中不存在客户成员，请重新选择！");
        }
    }

    /**
     * Desc: 获取客群群发短信列表
     * @return
     * @author
     */
    public PageBean getSendGroup(Map map) {
//        List<SysSmsSendGroup> sysSmsSendGroup = sysSmsSendGroupMapper.selectAll();
        PageBean retlst = coreDaoImpl.selectPageByMapper(sysSmsSendGroupMapper,"selectAll",map);
        if (null == retlst) {
            throw new ServiceException("客群短信信息不存在");
        }
        return retlst;
    }


    /**
     * 短信发送方法webservice交互
     * @param url
     * @param infoList
     * @param contents
     * @param remarks
     * @param startTime
     * @param endTime
     * @return 接口返回的日志信息
     */
    public Map<String, Object> smsInvoke(String url, List<Map<String,String>> infoList, String msgTopic,String sendObj,String bizNo,String contents,
                                          String remarks, String startTime, String endTime){
        String taskId = UUID.randomUUID().toString().replace("-", "");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        URL endpoint = null;
        try {
            endpoint = new URL(url);
        }
        catch (MalformedURLException e){
            throw new RuntimeException(e);
        }
        SmsServiceSoapBindingStub binding = null;
        try {
            binding = (SmsServiceSoapBindingStub)
                    new SmsServiceServiceLocator().getSmsService(endpoint);
        }
        catch (javax.xml.rpc.ServiceException jre) {
            throw new RuntimeException(jre);
        }
        binding.setTimeout(6000);
        try {
            SmsMessage msg = null;
            Vector vec = new Vector();
            SmsMessages msgs = new SmsMessages();
            msgs.setTaskId(taskId);
            msgs.setOrganizationId(agentCode);
            msgs.setNeedUseTemplateFlag("false");
            msgs.setTaskValue(remarks);
            msgs.setExtension("true");
            msgs.setServiceType(serviceType);
            msgs.setStartDate(simpleDateFormat.format(new Date()));
            msgs.setStartTime(startTime);
            msgs.setEndDate(simpleDateFormat.format(new Date()));
            msgs.setEndTime(endTime);
            for(Map<String,String>  info:infoList){
                msg = new SmsMessage();
                msg.setReceiver(info.get("PHONENO"));
                msg.setContents(info.get("MSGCONTENT"));
                msg.setOrgCode("0");
                vec.add(msg);
            }
            msgs.setMessages((SmsMessage[])vec.toArray(new SmsMessage[vec.size()]));
        Response value1 = binding.sendSMS(userName, passWord, msgs);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", value1.getStatus());
        result.put("message", value1.getMessage());
        System.out.println(value1.getStatus());
        System.out.println(value1.getMessage());
            SysSmsSendGroup sysSmsSendGroup = new SysSmsSendGroup();
            sysSmsSendGroup.setMsgCode(taskId);
            sysSmsSendGroup.setMsgTopic(msgTopic);
            sysSmsSendGroup.setMsgContent(contents);
            sysSmsSendGroup.setSendObj(sendObj);
            sysSmsSendGroup.setBizNo(bizNo);
            if("OK".equals(value1.getStatus())){
                sysSmsSendGroup.setMsgStat("成功");
            }else if("ERROR".equals(value1.getStatus())){
                sysSmsSendGroup.setMsgStat("失败");
            }
            sysSmsSendGroup.setFailReason(value1.getMessage());
            sysSmsSendGroup.setSendNum(BigDecimal.valueOf(infoList.size()));
            sysSmsSendGroupMapper.insert(sysSmsSendGroup);
        return result;
    }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 短信发送方法webservice交互
     * @param phoneList
     * @param contents
     * @param remarks
     * @param startTime
     * @param endTime
     * @return 接口返回的日志信息
     */
    public Map<String, Object> smsInvokeManage( List<String> phoneList,String contents,
                                          String remarks, String startTime, String endTime){
        String taskId = UUID.randomUUID().toString().replace("-", "");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        URL endpoint = null;
        try {
            endpoint = new URL(smsUrl);
        }
        catch (MalformedURLException e){
            throw new RuntimeException(e);
        }
        SmsServiceSoapBindingStub binding = null;
        try {
            binding = (SmsServiceSoapBindingStub)
                    new SmsServiceServiceLocator().getSmsService(endpoint);
        }
        catch (javax.xml.rpc.ServiceException jre) {
            throw new RuntimeException(jre);
        }
        binding.setTimeout(6000);
        try {
            SmsMessage msg = null;
            Vector vec = new Vector();
            SmsMessages msgs = new SmsMessages();
            msgs.setTaskId(taskId);
            msgs.setOrganizationId(agentCode);
            msgs.setNeedUseTemplateFlag("false");
            msgs.setTaskValue(remarks);
            msgs.setExtension("true");
            msgs.setServiceType(serviceType);
            msgs.setStartDate(simpleDateFormat.format(new Date()));
            msgs.setStartTime(startTime);
            msgs.setEndDate(simpleDateFormat.format(new Date()));
            msgs.setEndTime(endTime);
            for(String  phone:phoneList){
                msg = new SmsMessage();
                msg.setReceiver(phone);
                msg.setContents(contents);
                msg.setOrgCode("0");
                vec.add(msg);
            }
            msgs.setMessages((SmsMessage[])vec.toArray(new SmsMessage[vec.size()]));
            Response value1 = binding.sendSMS(userName, passWord, msgs);
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("status", value1.getStatus());
            result.put("message", value1.getMessage());
//            System.out.println(value1.getStatus());
//            System.out.println(value1.getMessage());
//            SysSmsSendGroup sysSmsSendGroup = new SysSmsSendGroup();
//            sysSmsSendGroup.setMsgCode(taskId);
//            sysSmsSendGroup.setMsgTopic(msgTopic);
//            sysSmsSendGroup.setMsgContent(contents);
//            sysSmsSendGroup.setSendObj(sendObj);
//            sysSmsSendGroup.setBizNo(bizNo);
//            sysSmsSendGroup.setMsgStat(value1.getStatus());
//            sysSmsSendGroup.setFailReason(value1.getMessage());
//            sysSmsSendGroup.setSendNum(BigDecimal.valueOf(phoneList.size()));
//            sysSmsSendGroupMapper.insert(sysSmsSendGroup);
            return result;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 短信发送方法webservice交互
     * @param msgList
     * @param remarks
     * @param startTime
     * @param endTime
     * @return 接口返回的日志信息
     */
    public Map<String, Object> smsInvokeManageByAuto( List<Map<String, String>> msgList,
                                          String remarks, String startTime, String endTime){
        String taskId = UUID.randomUUID().toString().replace("-", "");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        URL endpoint = null;
        try {
            endpoint = new URL(smsUrl);
        }
        catch (MalformedURLException e){
            throw new RuntimeException(e);
        }
        SmsServiceSoapBindingStub binding = null;
        try {
            binding = (SmsServiceSoapBindingStub)
                    new SmsServiceServiceLocator().getSmsService(endpoint);
        }
        catch (javax.xml.rpc.ServiceException jre) {
            throw new RuntimeException(jre);
        }
        binding.setTimeout(6000);
        try {
            SmsMessage msg = null;
            Vector vec = new Vector();
            SmsMessages msgs = new SmsMessages();
            msgs.setTaskId(taskId);
            msgs.setOrganizationId(agentCode);
            msgs.setNeedUseTemplateFlag("false");
            msgs.setTaskValue(remarks);
            msgs.setExtension("true");
            msgs.setServiceType(serviceType);
            msgs.setStartDate(simpleDateFormat.format(new Date()));
            msgs.setStartTime(startTime);
            msgs.setEndDate(simpleDateFormat.format(new Date()));
            msgs.setEndTime(endTime);
            for (Map<String, String> msgInfo:msgList) {
                msg = new SmsMessage();
                msg.setReceiver(msgInfo.get("PHONE_NO1"));
                msg.setContents(msgInfo.get("content"));
                msg.setOrgCode("0");
                vec.add(msg);
            }
            msgs.setMessages((SmsMessage[])vec.toArray(new SmsMessage[vec.size()]));
            Response value1 = binding.sendSMS(userName, passWord, msgs);
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("status", value1.getStatus());
            result.put("message", value1.getMessage());
            return result;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
