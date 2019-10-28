package cn.com.zsyk.crm.query.service.cleanclient;

import cn.com.zsyk.crm.common.constant.ServiceType;
import cn.com.zsyk.crm.common.dao.CoreDaoImpl;
import cn.com.zsyk.crm.common.dto.PageBean;
import cn.com.zsyk.crm.common.dto.PageContainer;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.util.DateUtil;
import cn.com.zsyk.crm.common.util.RedisUtil;
import cn.com.zsyk.crm.common.util.RestUtil;
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
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RestUtil restUtil;
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
                    ECCustper ecCustPer = addCustNames(map);
                    amqpTemplate.convertAndSend("clientQueue", JSON.toJSONString(ecCustPer));
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
        ecCustPer.setCustTyp(EnumType.CustType.per_latent_cust.value);//客户类型
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
                String CertNo = map.getIdentifynumber().toUpperCase();
                boolean matches =  this.isIDNumber(CertNo);
                if(matches) {
                    ecCustPer.setCertNo(CertNo);//证件号码
                    String sex;
                    String birthdate = "";
                    if (null != map.getIdentifynumber() && StringUtils.isNotEmpty(map.getIdentifynumber())) {
                        String stringArr = map.getIdentifynumber().substring(16,17);
                        if (Integer.parseInt(stringArr) % 2 == 0) {
                            sex = "2";
                        } else {
                            sex = "1";
                        }
                        birthdate = map.getIdentifynumber().substring(6, 14);
                        ecCustPer.setSex(sex);//性别
                        try {
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                            Date olddata = formatter.parse("19000101");
                            Date date = formatter.parse(birthdate);
                            Date newdata = formatter.parse(DateUtil.nowDate());
                            if (olddata.before(date) && date.before(newdata)) {
                                ecCustPer.setBirthDate(date);//出生日期
                            }
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
                        try {
                            SimpleDateFormat formatters = new SimpleDateFormat("yyyyMMdd");
                            Date olddatas = formatters.parse("19000101");
                            Date date = map.getBirthdate();
                            Date newdatas = formatters.parse(DateUtil.nowDate());
                            if(olddatas.before(date)&&date.before(newdatas)) {
                                ecCustPer.setBirthDate(date);//出生日期
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
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
                    try {
                        SimpleDateFormat formatters = new SimpleDateFormat("yyyyMMdd");
                        Date olddatas = formatters.parse("19000101");
                        Date date = map.getBirthdate();
                        Date newdatas = formatters.parse(DateUtil.nowDate());
                        if(olddatas.before(date)&&date.before(newdatas)) {
                            ecCustPer.setBirthDate(date);//出生日期
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
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
                try {
                SimpleDateFormat formatters = new SimpleDateFormat("yyyyMMdd");
                Date olddatas = formatters.parse("19000101");
                Date date = map.getBirthdate();
                Date newdatas = formatters.parse(DateUtil.nowDate());
                if(olddatas.before(date)&&date.before(newdatas)) {
                    ecCustPer.setBirthDate(date);//出生日期
                }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
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
        //String regex = new String("(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)(?<county>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)?(?<town>[^区]+区|.+镇)?(?<village>.*)");
        String regex= null;
        try {
            String paramType="String";
            String paramCode="Regex_address_split";
            String tmp=(String)redisUtil.get("sysparam_"+paramType+"_"+paramCode);
            String regexStr="";
            if(tmp==null || "".equals(tmp)){
                Map paramMap = new HashMap();
                paramMap.put("paramType", paramType);
                paramMap.put("paramCode", paramCode);
                Result result = restUtil.getForObject(ServiceType.MANAGE, "/crm/manage/paramng/param?paramType={paramType}&paramCode={paramCode}",Result.class,paramMap);
                Map<String, String> sysParam = (Map<String, String>) result.getData();
                regex=sysParam.get("paramValue")+"";
            }else{
                regex=tmp+"";
            }
            // regex = new String(("(?<provin6ce>[^省]+省|[^市]+市|[^自治区]+自治区|.+自治区)(?<city>[^自治州]+自治州|[^市]+市|[^盟]+盟|[^地区]+地区|.+区划)(?<county>[^市]+市|[^县]+县|[^旗]+旗|[^区]+区|.+qu)?(?<village>.*)").getBytes("GBK"),"utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Matcher m= Pattern.compile(regex).matcher(addresscname);
            String provCd="",cityCd="",countyCd="";
            while(m.find()) {
            provCd = m.group("province");
            ecCustPer.setProvCd(provCd == null ? "" : provCd.trim());//省份代码
            cityCd = m.group("city");
            ecCustPer.setCityCd(cityCd == null ? "" : cityCd.trim());//市代码
            countyCd = m.group("county");
            ecCustPer.setCountyCd(countyCd == null ? "" : countyCd.trim());//区县代码

        }
            if(provCd=="") {
                try {
                    String paramTypes = "String";
                    String paramCodes = "Regex_address_splits";
                    String tmp = (String) redisUtil.get("sysparam_" + paramTypes + "_" + paramCodes);
                    String regexStr = "";
                    if (tmp == null || "".equals(tmp)) {
                        Map paramMaps = new HashMap();
                        paramMaps.put("paramType", paramTypes);
                        paramMaps.put("paramCode", paramCodes);
                        Result result = restUtil.getForObject(ServiceType.MANAGE, "/crm/manage/paramng/param?paramType={paramType}&paramCode={paramCode}", Result.class, paramMaps);
                        Map<String, String> sysParams = (Map<String, String>) result.getData();
                        regex = sysParams.get("paramValue") + "";
                    } else {
                        regex = tmp + "";
                    }
                    // regex = new String(("(?<province>[^省]+省|[^市]+市|[^自治区]+自治区|.+自治区)(?<city>[^自治州]+自治州|[^市]+市|[^盟]+盟|[^地区]+地区|.+区划)(?<county>[^市]+市|[^县]+县|[^旗]+旗|[^区]+区|.+qu)?(?<village>.*)").getBytes("GBK"),"utf-8");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                m = Pattern.compile(regex).matcher(addresscname);
                while (m.find()) {
                    cityCd = m.group("city");
                    ecCustPer.setCityCd(cityCd == null ? "" : cityCd.trim());//市代码
                    countyCd = m.group("county");
                    ecCustPer.setCountyCd(countyCd == null ? "" : countyCd.trim());//区县代码

                }
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
        ecCustPer.setCustSource(EnumType.DataSource.core_sys.value);//数据来源
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

        return telephones;
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

    public static boolean isIDNumber(String CertNo) {
        if (CertNo == null || "".equals(CertNo)) {
            return false;
        }
            //定义判别用户身份证号的正则表达式（15位或者18位，最后一位可以为字母）
        //String regularExpression = "(^[1-9]\\\\d{5}(18|19|20)\\\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\\\d{3}[0-9Xx]$)";
        boolean matches = true;
        //判断第18位校验值
        if (matches==true) {
            if (CertNo.length() == 18) {
                String Id = CertNo.substring(0, 17);
                // 判断出生年月是否有效
                String strYear = Id.substring(6, 10);// 年份
                String strMonth = Id.substring(10, 12);// 月份
                String strDay = Id.substring(12, 14);// 日期
                if (isDate(strYear + "-" + strMonth + "-" + strDay) == false) {
                    matches=false;
                    return matches;
                }
                GregorianCalendar gc = new GregorianCalendar();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
                            || (gc.getTime().getTime() - sdf.parse(
                            strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
                        matches=false;
                        return matches;
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }
                if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
                    matches=false;
                    return matches;
                }
                if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
                    matches=false;
                    return matches;
                }

                String[] VarifyCode = { "1", "0", "X", "9", "8", "7", "6", "5", "4","3", "2" };
                String[] WT = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7","9", "10", "5", "8", "4", "2" };
                int sum = 0;
                for (int i = 0; i < 17; i++) {
                    sum = sum + Integer.parseInt(String.valueOf(Id.charAt(i))) * Integer.parseInt(WT[i]);
                }
                int modValue = sum % 11;
                String strVerifyCode = VarifyCode[modValue];
                Id = Id + strVerifyCode;
                if (CertNo.length() == 18) {
                    if (Id.equals(CertNo) == false) {
                        matches=false;
                        return matches;
                    }
                }
                matches=true;
                return matches;
            }
        }
        return matches;

    }
    /**
     * 判断字符串出生日期是否符合正则表达式：包括年月日，闰年、平年和每月31天、30天和闰月的28天或者29天
     * @param string
     * @return
     */
    public static boolean isDate(String strDate) {
        Pattern pattern = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|" +
                "([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|" +
                "([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|" +
                "([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))?$");
        Matcher m = pattern.matcher(strDate);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }
}
