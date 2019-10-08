package cn.com.zsyk.crm.query.service.cleanclient;

import cn.com.zsyk.crm.common.dao.CoreDaoImpl;
import cn.com.zsyk.crm.common.dto.PageBean;
import cn.com.zsyk.crm.common.dto.PageContainer;
import cn.com.zsyk.crm.generator.EnumType;
import cn.com.zsyk.crm.query.entity.ECCustper;
import cn.com.zsyk.crm.query.entity.GSClientpermain;
import cn.com.zsyk.crm.query.mapper.GSClientpermainMapper;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
public class CleanClientService {
    @Autowired
    private GSClientpermainMapper gsClientpermainMapper;
    @Autowired
    private AmqpTemplate amqpTemplate;
    /* 分页查询对象 */
    @Autowired
    private CoreDaoImpl coreDaoImpl;

    public int selectClient(String stampTime){
        int perSelectCount = 1000;// 每次查询的数据量
        // 客户的总查询量
        long totalSelectCount = gsClientpermainMapper.selectAllCustCount(stampTime);
        // 循环查询次数
        long cycleCount = totalSelectCount % perSelectCount == 0 ? totalSelectCount / perSelectCount
                : totalSelectCount / perSelectCount + 1;
        List<GSClientpermain> list=new ArrayList<GSClientpermain>();
        for (int i = 0; i < cycleCount; i++) {
            // [0,999] [1000,1999] ... 这里用到了分页的思想
            PageContainer pageContainer =new PageContainer();
            pageContainer.setPageNum(i);
            pageContainer.setPageSize(perSelectCount);
            PageBean bean = coreDaoImpl.selectPageByMapper(gsClientpermainMapper, "selectCustlist", stampTime);

            list= bean.getList();
            for (GSClientpermain map : list) {
                if ("1".equals(map.getClienttype())) {
                    ECCustper ecCustPer = addReportormobile(map);
                    amqpTemplate.convertAndSend("customerQueue", JSON.toJSONString(ecCustPer));
                }
            }
        }

        return 1;
    }

    /**
     * 提取理赔电话
     * @param stampTime
     * @return
     */
    public int selectReportorMobile(String stampTime){
        int perSelectCount = 1000;// 每次查询的数据量
        // 客户的总查询量
        long totalSelectCount = gsClientpermainMapper.selectReportormobileCount(stampTime);
        // 循环查询次数
        long cycleCount = totalSelectCount % perSelectCount == 0 ? totalSelectCount / perSelectCount
                : totalSelectCount / perSelectCount + 1;
        List<GSClientpermain> list = new ArrayList<GSClientpermain>();
        for (int i = 0; i < cycleCount; i++) {
            // [0,999] [1000,1999] ... 这里用到了分页的思想
            PageContainer pageContainer =new PageContainer();
            pageContainer.setPageNum(i);
            pageContainer.setPageSize(perSelectCount);
            PageBean bean = coreDaoImpl.selectPageByMapper(gsClientpermainMapper, "selectReportormobile", stampTime);

            list= bean.getList();
            for (GSClientpermain map : list) {
                ECCustper ecCustPer = addReportormobile(map);
                amqpTemplate.convertAndSend("reportorMobile", JSON.toJSONString(ecCustPer));
            }

        }
        return 1;
    }

    /**
     * 理赔电话
     * @param map
     * @return
     */
    public ECCustper addReportormobile(GSClientpermain map) {
        ECCustper ecCustPer = new ECCustper();
        String custNo = map.getClientcode();
        ecCustPer.setCustNo(custNo);//客户号
        String phone = map.getReportormobile();
        if(phone!=null&&phone!=""){
            ecCustPer.setPhoneNo1(this.isPhone(phone));//手机号码
        }else{
            ecCustPer.setPhoneNo1("");
        }
        return ecCustPer;
    }




    /**
     * 添加客户名称信息
     * @param map
     */
    public ECCustper addCustNames(GSClientpermain map){
        ECCustper ecCustPer = new ECCustper();
        String createDate="";
        String createTime="";
        SimpleDateFormat sim = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat da =  new SimpleDateFormat("HHmmss");
        if(null!=map.getCreatetime()){
            createDate = sim.format(map.getCreatetime());
            createTime = da.format(map.getCreatetime());
        }
        String updateDate="";
        String updateTime="";
        if(null!=map.getCreatetime()) {
             updateDate = sim.format(map.getUpdatetime());
             updateTime = da.format(map.getUpdatetime());
        }
        String custNo = map.getClientcode();
        ecCustPer.setCustNo(custNo);//客户号
        if(null!=map.getClientcname()&&""!=map.getClientcname()){
                ecCustPer.setCustNam((map.getClientcname()).trim());//客户姓名
        }
        if(null!=map.getClienttype()) {
            if(map.getClienttype().equals("1")){
                ecCustPer.setCustTyp("01");//客户类型
            }else{
                ecCustPer.setCustTyp("");//客户类型
            }
        }
        if(null!=map.getMarriage()) {
            ecCustPer.setMarrigeSts(map.getMarriage());//婚姻状况
        }
        if(null!=map.getCountryCode()) {
            ecCustPer.setNationality(map.getCountryCode());//国籍
        }
        if(null!=map.getNationality()) {
            ecCustPer.setNation(map.getNationality());//民族
        }
        if(null!=map.getEducationcode()) {
            ecCustPer.setEduDegree(map.getEducationcode());//文化程度
        }
        String certTyp="";
        if(null!=map.getIdentifytype()) {
            certTyp = map.getIdentifytype();
            ecCustPer.setCertTyp(certTyp);//证件类型
        }
        if(map.getIdentifynumber()!=null){
        if(certTyp!=null&&"01".equals(certTyp)){
            if (map.getIdentifynumber()!=null&&map.getIdentifynumber().length()==18){
                ecCustPer.setCertNo(map.getIdentifynumber());//证件号码
                String sex;
                String birthdate="";
                if(null!=map.getIdentifynumber()&& StringUtils.isNotEmpty(map.getIdentifynumber())){
                    char []stringArr = map.getIdentifynumber().toString().toCharArray();
                    if (stringArr[16]%2==0){
                        sex="1";
                    }else{
                        sex="2";
                    }
                    birthdate = map.getIdentifynumber().substring(6,14);
                    ecCustPer.setSex(sex);//性别
                    try {
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                        Date date = formatter.parse(birthdate);
                        ecCustPer.setBirthDate(date);//出生日期
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }else{
                    ecCustPer.setCertNo("");
                if(null!=map.getSex()) {
                    ecCustPer.setSex(map.getSex());
                }else{
                    ecCustPer.setSex("");
                }
                if(null!=map.getBirthdate()) {
                    ecCustPer.setBirthDate(map.getBirthdate());
                }
            }
        }else{
            if(null!=map.getIdentifynumber()) {
                ecCustPer.setCertNo(map.getIdentifynumber());
            }else{
                ecCustPer.setCertNo("");
            }
            if(null!=map.getSex()) {
                ecCustPer.setSex(map.getSex());
            }else{
                ecCustPer.setSex("");
            }
            if(null!=map.getBirthdate()) {
                ecCustPer.setBirthDate(map.getBirthdate());
            }
        }
        }
        if(null!=map.getIdcardvalidity()) {
            ecCustPer.setCertEffDate(map.getIdcardvalidity());//证件有效期
        }
        ecCustPer.setTrade("");//行业
        if(map.getCompanyname()!=null){
            ecCustPer.setUnitNam(map.getCompanyname());//单位名称
        }else{
            ecCustPer.setUnitNam("");//单位名称
        }

        String phone = map.getMobile();
        if(phone!=null&&phone!=""){
            ecCustPer.setPhoneNo1(this.isPhone(phone));//手机号码
        }else{
            ecCustPer.setPhoneNo1("");
        }
        String telephone = map.getHomephonenumber();
        if(telephone!=null&&telephone!=""){
            ecCustPer.setTelephone1(this.isTelephone(telephone));//固定电话
        }else{
            ecCustPer.setTelephone1("");
        }
        if(map.getEmail()!=null){
            if(map.getEmail().matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+")) {
                ecCustPer.setEmailAddr(map.getEmail());
            }else{
                ecCustPer.setEmailAddr("");
            }
        }else{
            ecCustPer.setEmailAddr("");
        }
        if(map.getAddresscname()!=null){
        String addresscname=map.getAddresscname();
        String regex= null;
        try {
            regex = new String(("(?<province>[^省]+省|[^市]+市|[^自治区]+自治区|.+自治区)(?<city>[^自治州]+自治州|[^市]+市|[^盟]+盟|[^地区]+地区|.+区划)(?<county>[^市]+市|[^县]+县|[^旗]+旗|[^区]+区|.+qu)?(?<village>.*)").getBytes("GBK"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Matcher m= Pattern.compile(regex).matcher(addresscname);
        String provCd="",cityCd="",countyCd="",streetAddr="";
        while(m.find()) {
            provCd = m.group("province");
            ecCustPer.setProvCd(provCd == null ? "" : provCd.trim());//省份代码
            cityCd = m.group("city");
            ecCustPer.setCityCd(cityCd == null ? "" : cityCd.trim());//市代码
            countyCd = m.group("county");
            ecCustPer.setCountyCd(countyCd == null ? "" : countyCd.trim());//区县代码

        }
            ecCustPer.setStreetAddr(map.getAddresscname().trim());//街道信息
            ecCustPer.setDetAddr(map.getAddresscname().trim());//详细地址
        }else{
            ecCustPer.setProvCd("");
            ecCustPer.setCityCd("");
            ecCustPer.setCountyCd("");
            ecCustPer.setStreetAddr("");
            ecCustPer.setDetAddr("");
        }
        if(map.getPostcode()!=null){
            ecCustPer.setPostcode(map.getPostcode());//邮政编码
        }else{
            ecCustPer.setPostcode("");
        }
        ecCustPer.setCustSource(EnumType.DataSource.core_sys.getValue());//数据来源
        if(map.getHandlercode()!=null){
            ecCustPer.setCustAgent(map.getHandlercode());//客户经理
        }else{
            ecCustPer.setCustAgent("");
        }
        ecCustPer.setCreateDate(createDate);//创建日期
        ecCustPer.setCreateTime(createTime);//创建时间
        ecCustPer.setUpdateDate(updateDate);//修改日期
        ecCustPer.setUpdateTime(updateTime);//修改时间
        ecCustPer.setRecStat(EnumType.CustStat.normal.value);//记录状态
        return ecCustPer;

    }

    public String isTelephone(String telephone){
        String regex = "^[1-9]{1}[0-9]{5,8}$";//验证不带区号的
        String regexs= "^[0][1-9]{2,3}-[0-9]{5,10}$";//验证带区号的
        String telephones="";
        boolean isMatch = false;
        if(telephone.length() >9){
            Pattern t = Pattern.compile(regexs);
            Matcher r = t.matcher(telephone);
            isMatch = r.matches();
            if (isMatch==false) {
                telephones = "";
            }else{
                telephones = telephone;
            }
        }else{
            Pattern t = Pattern.compile(regex);
            Matcher r = t.matcher(telephone);
            isMatch = r.matches();
            if (isMatch==false) {
                telephones = "";
            }else{
                telephones = telephone;
            }
        }

        return telephone;
    }
    /**
     * 验证手机号码
     * @param
     * @return
     */
    public String isPhone(String phone){
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        String regexs="(\\\\+86|0086)?\\\\s?1\\\\d{10}";
        String phones="";
        boolean isMatch = false;
            if(phone.length() > 11){
                Pattern t = Pattern.compile(regexs);
                Matcher r = t.matcher(phone);
                isMatch = r.matches();
                if (isMatch==false) {
                    phones = "";
                }else{
                    phones = phone;
                }
            }else if(phone.length() <= 11){
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(phone);
                isMatch = m.matches();
                if (isMatch==false) {
                    phones = "";
                }else{
                    phones = phone;
                }
            }else {
                    phones = "";
            }

        return phones;
    }



}
