package cn.com.zsyk.crm.ecif.service.clean;

import cn.com.zsyk.crm.common.constant.ServiceType;
import cn.com.zsyk.crm.common.dto.ContextContainer;
import cn.com.zsyk.crm.common.dto.CoreContext;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.util.DateUtil;
import cn.com.zsyk.crm.common.util.LogUtil;
import cn.com.zsyk.crm.common.util.RestUtil;
import cn.com.zsyk.crm.ecif.entity.*;
import cn.com.zsyk.crm.ecif.mapper.*;
import cn.com.zsyk.crm.ecif.service.customer.portrait.CustContactService;
import cn.com.zsyk.crm.ecif.service.scheduler.birthmng.SimCustTaskGnrtService;
import cn.com.zsyk.crm.generator.EnumType;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class CleanCustPerService {
    /** logger */
    @SuppressWarnings("unused")
    private final Log log = LogUtil.getLogger(CleanCustPerService.class);
    @Autowired
    private EcCustPerMapper ecCustPerMapper;
    @Autowired
    private EcCustNameMapper ecCustNameMapper;
    @Autowired
    private EcCustCareerMapper ecCustCareerMapper;
    @Autowired
    private EcCustCertMapper ecCustCertMapper;
    @Autowired
    private EcContactWayMapper ecContactWayMapper;
    @Autowired
    private EcContactAddrMapper ecContactAddrMapper;
    @Autowired
    private SimCustTaskGnrtService simCustTaskGnrt;
    @Autowired
    private RestUtil restUtil;
    @Autowired
    private EcSimCustMapper simCustMapper;
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Autowired
    private CustContactService custContactService;
    @Autowired
    private EcCustManagerMapper ecCustManagerMapper;

    /**
     * 接受核心客户信息
     * @param message
     */
    @RabbitListener(queues = "clientQueue")
    public void addCustpers(String message){
        log.info(message);
        try {
            ECCustpers ecCustPer = JSON.parseObject(message,ECCustpers.class);
            this.addCustNames(ecCustPer);
            this.addCustPers(ecCustPer);
            this.addCustCerts(ecCustPer);
            this.addCustCareers(ecCustPer);
            this.addCustContactWays(ecCustPer);
            this.addCustContactAddrs(ecCustPer);
            amqpTemplate.convertAndSend("simCustTaskGnrt", JSON.toJSONString(ecCustPer));
        }catch (Exception e){
            e.printStackTrace();
            amqpTemplate.convertAndSend("clientQueue_wrong", message);
        }
    }

    /**
     * 将核心客户信息生成相似任务及合并客户
     * @param message
     */
    @RabbitListener(queues = "simCustTaskGnrt")
    public void simCustTaskGnrt(String message){
        log.info(message);
        try {
            ECCustpers ecCustPer = JSON.parseObject(message,ECCustpers.class);
            EcSimCust ecSim = new EcSimCust();
            ecSim.setTaskId("");
            ecSim.setSimilarCustNo(ecCustPer.getCustNo());
            List<EcSimCust> ecSimCust =  simCustMapper.selectByEntitys(ecSim);
            if(ecSimCust.size()==0){
                simCustTaskGnrt.simCustTaskGnrt(ecCustPer);
            }
        }catch (Exception e){
            e.printStackTrace();
            amqpTemplate.convertAndSend("simCustTaskGnrt_wrong", message);
        }
    }

    /**
     * 接受理赔电话信息
     * @param message
     */
    @RabbitListener(queues = "reportorMobile")
    public void addReportorMobile(String message){
        log.info(message);
        try {
        ECCustpers ecCustPer = JSON.parseObject(message,ECCustpers.class);
        CoreContext cc = new CoreContext();
        Map map = new HashMap();
        map.put("userId", "admin");
        cc.putAll(map);
        ContextContainer.setContext(cc);
        EcContactWay oldecContactWay = new EcContactWay();
            String custNos = ecCustPer.getCustNo();
            List<String> custNoNewList= new ArrayList();
            List<String> custNoOldList= new ArrayList();
            custNoOldList.add(custNos);
            custNoNewList.add(custNos);
            while(true){
                List<String> resData = ecCustPerMapper.getCustNoListByCustNo(custNoOldList);
                custNoOldList.clear();
                if(resData.size()>0){
                    custNoNewList.addAll(resData);
                    custNoOldList.addAll(resData);
                    continue;
                }else{
                    break;
                }
            }
            if(custNoNewList.size()>0) {
                for(int i=1;i <= custNoNewList.size();i++) {
                    custNos = custNoNewList.get(i-1);
                    oldecContactWay.setCustNo(custNos);
                    oldecContactWay.setContactSqn(custContactService.getNextSqnByCustNo(custNos));//序号
                    if(ecCustPer.getPhoneNo1()!=null){
                        oldecContactWay.setPhoneNo2(ecCustPer.getPhoneNo1());//手机号码
                    }
                    oldecContactWay.setCustSource(EnumType.DataSource.claim.value);
                    oldecContactWay.setRecStat("0");
                    ecContactWayMapper.insert(oldecContactWay);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            amqpTemplate.convertAndSend("reportorMobile_wrong", message);
        }
    }
    /**
     * 接受并添加客户名称信息
     * @param
     */
    public void addCustNames(ECCustpers ecCustPer){
        String custNo = ecCustPer.getCustNo();
        CoreContext cc = new CoreContext();
        Map map = new HashMap();
        map.put("userId", "admin");
        cc.putAll(map);
        ContextContainer.setContext(cc);
        //客户姓名
        EcCustName ecCustName =ecCustNameMapper.selectByPrimaryKey(custNo);
        if(ecCustName!=null){
            if(ecCustPer.getCustNam()!=null){
                ecCustName.setCustNam(ecCustPer.getCustNam());//客户姓名
            }
            ecCustName.setDataSource(ecCustPer.getCustSource());//数据来源
            //ecCustName.setRecStat(ecCustPer.getRecStat());
            ecCustNameMapper.updateByPrimaryKey(ecCustName);
        }else{
            EcCustName oldecCustName = new EcCustName();
            oldecCustName.setCustNo(custNo);//客户号
            if(ecCustPer.getCustNam()!=null){
                oldecCustName.setCustNam(ecCustPer.getCustNam());//客户姓名
            }
            oldecCustName.setDataSource(ecCustPer.getCustSource());//数据来源
            ecCustNameMapper.insert(oldecCustName);
        }
    }

    /**
     * 接受并添加客户基本信息
     * @param
     * @throws ParseException
     */
    public void addCustPers(ECCustpers ecCustPer) {
        String custNo =ecCustPer.getCustNo();
        CoreContext cc = new CoreContext();
        Map map = new HashMap();
        map.put("userId", "admin");
        cc.putAll(map);
        ContextContainer.setContext(cc);
        //客户基本信息
        EcCustPer ecCustPer1 = ecCustPerMapper.selectByPrimaryKey(custNo);
        if(ecCustPer1!=null){
            if(ecCustPer1.getCustTyp()==null || "".equals(ecCustPer1.getCustTyp())||!EnumType.CustType.per_formal_cust.value.equals(ecCustPer1.getCustTyp())){
                if(ecCustPer.getCustTyp()!=null && !"".equals(ecCustPer.getCustTyp())) {
                    ecCustPer1.setCustTyp(ecCustPer.getCustTyp());//客户类型
                }else{
                    ecCustPer1.setCustTyp(EnumType.CustType.per_latent_cust.value);
                }
            }
            if(ecCustPer.getMarrigeSts()!=null) {
                ecCustPer1.setMarrigeSts(ecCustPer.getMarrigeSts());//婚姻状况
            }
            if(ecCustPer.getNationality()!=null) {
                ecCustPer1.setNationality(ecCustPer.getNationality());//国籍
            }
            if(ecCustPer.getNation()!=null) {
                ecCustPer1.setNation(ecCustPer.getNation());//民族
            }
            if(ecCustPer.getEduDegree()!=null) {
                ecCustPer1.setEduDegree(ecCustPer.getEduDegree());//文化程度
            }
            if(ecCustPer.getSex()!=null){
                ecCustPer1.setSex(ecCustPer.getSex());//性别
            }else{
                ecCustPer1.setSex(EnumType.Sex.unknown.value);
            }
            if(ecCustPer.getBirthDate()!=null) {
                ecCustPer1.setBirthDate(ecCustPer.getBirthDate());//出生日期
            }
            ecCustPer1.setBlacklistFlg("0");
            ecCustPer1.setKeyCustFlg("0");
            ecCustPer1.setVisibleRange("0");
            ecCustPer1.setCustAgent(ecCustPer.getCustAgent());
            ecCustPer1.setCustSource(ecCustPer.getCustSource());//数据来源
            //ecCustPer1.setRecStat(ecCustPer.getRecStat());
            ecCustPerMapper.updateByPrimaryKey(ecCustPer1);
        }else{
            EcCustPer oldecCustPer =new EcCustPer();
            oldecCustPer.setCustNo(custNo);//客户号
            if(ecCustPer.getCustTyp()!=null && ecCustPer.getCustTyp()!="") {
                oldecCustPer.setCustTyp(ecCustPer.getCustTyp());//客户类型
            }else{
                oldecCustPer.setCustTyp(EnumType.CustType.per_formal_cust.value);
            }
            if(ecCustPer.getMarrigeSts()!=null) {
                oldecCustPer.setMarrigeSts(ecCustPer.getMarrigeSts());//婚姻状况
            }
            if(ecCustPer.getNationality()!=null) {
                oldecCustPer.setNationality(ecCustPer.getNationality());//国籍
            }
            if(ecCustPer.getNation()!=null) {
                oldecCustPer.setNation(ecCustPer.getNation());//民族
            }
            if(ecCustPer.getEduDegree()!=null) {
                oldecCustPer.setEduDegree(ecCustPer.getEduDegree());//文化程度
            }
            if(ecCustPer.getSex()!=null&&ecCustPer.getSex()!=""){
                oldecCustPer.setSex(ecCustPer.getSex());//性别
            }else{
                oldecCustPer.setSex(EnumType.Sex.unknown.value);
            }
            if(ecCustPer.getBirthDate()!=null) {
                oldecCustPer.setBirthDate(ecCustPer.getBirthDate());//出生日期
            }
            oldecCustPer.setCustStat("0");
            oldecCustPer.setBlacklistFlg("0");
            oldecCustPer.setKeyCustFlg("0");
            oldecCustPer.setVisibleRange("0");
            oldecCustPer.setCustAgent(ecCustPer.getCustAgent());
            oldecCustPer.setCustSource(ecCustPer.getCustSource());//数据来源
            ecCustPerMapper.insert(oldecCustPer);
        }
        if(custNo!=null&&ecCustPer!=null&&ecCustPer.getCustAgent()!=null && !"".equals(ecCustPer.getCustAgent())){
            EcCustManager ecCustManager = new EcCustManager();
            ecCustManager.setCustNo(custNo);
            ecCustManager.setCustAgent(ecCustPer.getCustAgent());
            ecCustManager.setRiseTime(DateUtil.nowDateTimeStamp());
            ecCustManager.setRecStat("0");
            List<EcCustManager> listTmp=ecCustManagerMapper.selectAgentByConditions(ecCustManager);
            if(listTmp==null || listTmp.size()==0){
                ecCustManagerMapper.insert(ecCustManager);
            }
        }
    }

    /**
     * 接受并添加客户职业信息
     * @param
     */
    public void addCustCareers(ECCustpers ecCustPer){
        String custNo = ecCustPer.getCustNo();
        CoreContext cc = new CoreContext();
        Map map = new HashMap();
        map.put("userId", "admin");
        cc.putAll(map);
        ContextContainer.setContext(cc);
        //客户行业信息
        EcCustCareer ecCustCareer = ecCustCareerMapper.selectByPrimaryKey(custNo);
        if(ecCustCareer!=null){
            if(ecCustPer.getTrade()!=null){
                ecCustCareer.setTrade(ecCustPer.getTrade());//行业
            }
            if(ecCustPer.getUnitNam()!=null) {
                ecCustCareer.setUnitNam(ecCustPer.getUnitNam());//单位名称
            }
            //ecCustCareer.setRecStat(ecCustPer.getRecStat());
            ecCustCareerMapper.updateByPrimaryKey(ecCustCareer);
        }else{
            EcCustCareer oldecCustCareer =  new EcCustCareer();
            oldecCustCareer.setCustNo(custNo);//客户号
            if(ecCustPer.getTrade()!=null){
                oldecCustCareer.setTrade(ecCustPer.getTrade());//行业
            }
            if(ecCustPer.getUnitNam()!=null) {
                oldecCustCareer.setUnitNam(ecCustPer.getUnitNam());//单位名称
            }
            ecCustCareerMapper.insert(oldecCustCareer);
        }
    }

    /**
     * 添加客户证件信息
     * @param
     */
    public void addCustCerts(ECCustpers ecCustPer){
        CoreContext cc = new CoreContext();
        Map map = new HashMap();
        map.put("userId", "admin");
        cc.putAll(map);
        ContextContainer.setContext(cc);
        String custNo = ecCustPer.getCustNo();
        //客户证件信息
        int certSqn =0;
        EcCustCert ecCustCert = ecCustCertMapper.selectByPrimaryKey(custNo,certSqn);
        if(ecCustCert!=null) {
            ecCustCert.setCertSqn(0);
            if(ecCustPer.getCertTyp()!=null){
                if(ecCustPer.getCertTyp().length()>2){
                    ecCustCert.setCertTyp("");//证件类型
                }else{
                    ecCustCert.setCertTyp(ecCustPer.getCertTyp());//证件类型

                }
            }
            if(ecCustPer.getCertNo()!=null){
                ecCustCert.setCertNo(ecCustPer.getCertNo());
            }
            if(ecCustPer.getCertEffDate()!=null) {
                ecCustCert.setCertEffDate(ecCustPer.getCertEffDate());//证件有效期
            }else{
                ecCustCert.setCertEffDate("");
            }
            ecCustCert.setOrigSysFlg(EnumType.DataSource.core_sys.getValue());
            ecCustCert.setCertSts(EnumType.ValidFlg.valid.getValue());//证件状态
            //ecCustCert.setRecStat(ecCustPer.getRecStat());
            ecCustCertMapper.updateByPrimaryKey(ecCustCert);
        }else{
            EcCustCert oldecCustCert = new EcCustCert();
            oldecCustCert.setCustNo(custNo);//客户号
            oldecCustCert.setCertSqn(0);
            if(ecCustPer.getCertTyp()!=null){
                if(ecCustPer.getCertTyp().length()>2){
                    oldecCustCert.setCertTyp("");//证件类型
                }else{
                    oldecCustCert.setCertTyp(ecCustPer.getCertTyp());//证件类型
                }
            }
            if(ecCustPer.getCertNo()!=null){
                oldecCustCert.setCertNo(ecCustPer.getCertNo());//证件号码
            }
            if(ecCustPer.getCertEffDate()!=null) {
                oldecCustCert.setCertEffDate(ecCustPer.getCertEffDate());//证件有效期
            }
            oldecCustCert.setOrigSysFlg(EnumType.DataSource.core_sys.getValue());
            oldecCustCert.setCertSts(EnumType.ValidFlg.valid.getValue());//证件状态
            ecCustCertMapper.insert(oldecCustCert);
        }
    }

    /**
     * 接受并添加联系方式
     * @param
     */
    public void addCustContactWays(ECCustpers ecCustPer) {
        String custNo = ecCustPer.getCustNo();
        CoreContext cc = new CoreContext();
        Map map = new HashMap();
        map.put("userId", "admin");
        cc.putAll(map);
        ContextContainer.setContext(cc);
        //客户联系方式
        EcContactWay ecContactWay = ecContactWayMapper.selectByCustNoMinSqn(custNo);
        if(ecContactWay!=null) {
            if(ecCustPer.getPhoneNo1()!=null){
                ecContactWay.setPhoneNo1(ecCustPer.getPhoneNo1());//手机号码
            }
            if(ecCustPer.getTelephone1()!=null){
                ecContactWay.setTelephone1(ecCustPer.getTelephone1());//固定电话
            }
            if(ecCustPer.getEmailAddr()!=null) {
                ecContactWay.setEmailAddr(ecCustPer.getEmailAddr());
            }
            //ecContactWay.setRecStat(ecCustPer.getRecStat());
            ecContactWayMapper.updateByPrimaryKey(ecContactWay);
        }else{
            EcContactWay oldecContactWay = new EcContactWay();
            oldecContactWay.setCustNo(custNo);//客户号;
            oldecContactWay.setContactSqn(custContactService.getNextSqnByCustNo(custNo));//暂时写死
            if(ecCustPer.getPhoneNo1()!=null){
                oldecContactWay.setPhoneNo1(ecCustPer.getPhoneNo1());//手机号码
            }
            if(ecCustPer.getTelephone1()!=null){
                oldecContactWay.setTelephone1(ecCustPer.getTelephone1());//固定电话
            }
            if(ecCustPer.getEmailAddr()!=null) {
                oldecContactWay.setEmailAddr(ecCustPer.getEmailAddr());
            }
            oldecContactWay.setRecStat(ecCustPer.getRecStat());
            oldecContactWay.setCustSource(EnumType.DataSource.core_sys.value);
            ecContactWayMapper.insert(oldecContactWay);
        }
    }

    /**
     * 接受并添加地址信息
     * @param
     */
    public void addCustContactAddrs(ECCustpers ecCustPer) {
        CoreContext cc = new CoreContext();
        Map map = new HashMap();
        map.put("userId", "admin");
        cc.putAll(map);
        ContextContainer.setContext(cc);
        String custNo = ecCustPer.getCustNo();
        //客户地址信息
        EcContactAddr ecContactAddr = ecContactAddrMapper.selectCount(custNo);
        if(ecContactAddr!=null){
            String sysPr = null;
            String provinceName = ecCustPer.getProvCd();
            String cityNames =ecCustPer.getCityCd();
            if(cityNames!=null){
                if(cityNames.equals("北京市")||cityNames.equals("上海市")||cityNames.equals("天津市")||cityNames.equals("重庆市")){
                    provinceName = cityNames;
                }
            }
            if(provinceName!=null&&!provinceName.equals("")){
                Result addrDetail = restUtil.getForObject(ServiceType.MANAGE,
                        "/crm/manage/nameCode/getprovinceCode?provinceName={provinceName}", Result.class, provinceName);
                sysPr = (String)addrDetail.getData();
                if(sysPr!=null) {
                    ecContactAddr.setProvCd(sysPr);//省份代码
                }else{
                    ecContactAddr.setProvCd("");
                }
            }
            String sysCity =null;
            if(sysPr!=null&&ecCustPer.getCityCd()!=null&&!ecCustPer.getCityCd().equals("")) {
                String cityName = ecCustPer.getCityCd();
                if(sysPr.equals("11")||sysPr.equals("12")||sysPr.equals("31")||sysPr.equals("50")){
                    cityName = "直辖区县";
                }
                Result addrDetail = restUtil.getForObject(ServiceType.MANAGE,
                        "/crm/manage/nameCode/getCitysCode?cityName={cityName}&sysPr={sysPr}", Result.class, cityName,sysPr);
                sysCity = (String)addrDetail.getData();
                if(sysCity!=null) {
                    ecContactAddr.setCityCd(sysCity);//市代码
                }else{
                    ecContactAddr.setCityCd("");
                }
            }
            if(sysCity!=null&&ecCustPer.getCountyCd()!=null&&!ecCustPer.getCountyCd().equals("")) {
                String countyName = ecCustPer.getCountyCd();
                Result addrDetail = restUtil.getForObject(ServiceType.MANAGE,
                        "/crm/manage/nameCode/getCountysCode?countyName={countyName}&sysCity={sysCity}", Result.class, countyName,sysCity);
                String sysCounty = (String)addrDetail.getData();
                if(sysCounty!=null){
                    ecContactAddr.setCountyCd(sysCounty);//区县代码
                }else{
                    ecContactAddr.setCountyCd("");
                }
            }
            if(ecCustPer.getStreetAddr()!=null){
                ecContactAddr.setStreetAddr(ecCustPer.getStreetAddr());//街道信息
            }
            if(ecCustPer.getDetAddr()!=null){
                ecContactAddr.setDetAddr(ecCustPer.getDetAddr());//详细地址
            }
            ecContactAddr.setAddrTyp("1");
            if(ecCustPer.getStreetAddr()!=null){
                ecContactAddr.setPostcode(ecCustPer.getPostcode());//邮政编码
            }
           // ecContactAddr.setRecStat(ecCustPer.getRecStat());
            ecContactAddrMapper.updateByPrimaryKey(ecContactAddr);
        }else {
            EcContactAddr oldecContactAddr = new EcContactAddr();
            oldecContactAddr.setCustNo(custNo);//客户号
            String sysPr = null;
            String provinceName = ecCustPer.getProvCd();
            String cityNames =ecCustPer.getCityCd();
            if(cityNames!=null){
               if(cityNames.equals("北京市")||cityNames.equals("上海市")||cityNames.equals("天津市")||cityNames.equals("重庆市")){
                   provinceName = cityNames;
               }
            }
            if(provinceName!=null&&!provinceName.equals("")){
                Result addrDetail = restUtil.getForObject(ServiceType.MANAGE,
                        "/crm/manage/nameCode/getprovinceCode?provinceName={provinceName}", Result.class, provinceName);
                sysPr = (String)addrDetail.getData();
                if(sysPr!=null) {
                    oldecContactAddr.setProvCd(sysPr);//省份代码
                }else{
                    oldecContactAddr.setProvCd("");
                }
            }
            String sysCity =null;
            if(sysPr!=null&&ecCustPer.getCityCd()!=null&&!ecCustPer.getCityCd().equals("")) {
                String cityName = ecCustPer.getCityCd();
                if(sysPr.equals("11")||sysPr.equals("12")||sysPr.equals("31")||sysPr.equals("50")){
                    cityName = "直辖区县";
                }
                Result addrDetail = restUtil.getForObject(ServiceType.MANAGE,
                        "/crm/manage/nameCode/getCitysCode?cityName={cityName}&sysPr={sysPr}", Result.class, cityName,sysPr);
                sysCity = (String)addrDetail.getData();
                if(sysCity!=null) {
                    oldecContactAddr.setCityCd(sysCity);//市代码
                }else{
                    oldecContactAddr.setCityCd("");
                }
            }
            if(sysCity!=null&&ecCustPer.getCountyCd()!=null&&!ecCustPer.getCountyCd().equals("")) {
                String countyName = ecCustPer.getCountyCd();
                Result addrDetail = restUtil.getForObject(ServiceType.MANAGE,
                        "/crm/manage/nameCode/getCountysCode?countyName={countyName}&sysCity={sysCity}", Result.class, countyName,sysCity);
                String sysCounty = (String)addrDetail.getData();
                if(sysCounty!=null){
                    oldecContactAddr.setCountyCd(sysCounty);//区县代码
                }else{
                    oldecContactAddr.setCountyCd("");
                }
            }
            if(ecCustPer.getStreetAddr()!=null){
                oldecContactAddr.setStreetAddr(ecCustPer.getStreetAddr());//街道信息
            }
            if(ecCustPer.getDetAddr()!=null){
                oldecContactAddr.setDetAddr(ecCustPer.getDetAddr());//详细地址
            }
            oldecContactAddr.setAddrTyp("1");
            if(ecCustPer.getStreetAddr()!=null){
                oldecContactAddr.setPostcode(ecCustPer.getPostcode());//邮政编码
            }
            oldecContactAddr.setCustSource(EnumType.DataSource.core_sys.value);
            ecContactAddrMapper.insert(oldecContactAddr);
        }

    }
}
