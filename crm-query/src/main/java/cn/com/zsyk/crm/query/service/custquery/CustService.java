package cn.com.zsyk.crm.query.service.custquery;

import cn.com.zsyk.crm.common.constant.ServiceType;
import cn.com.zsyk.crm.common.dao.CoreDaoImpl;
import cn.com.zsyk.crm.common.dto.PageBean;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.util.RestUtil;

import cn.com.zsyk.crm.generator.EnumType;
import cn.com.zsyk.crm.query.bo.*;

import cn.com.zsyk.crm.query.entity.CcCallLogs;
import cn.com.zsyk.crm.query.entity.CcTickets;
import cn.com.zsyk.crm.query.entity.EcCustManager;
import cn.com.zsyk.crm.query.entity.EcCustTag;
import cn.com.zsyk.crm.query.mapper.EcCustManagerMapper;
import cn.com.zsyk.crm.query.mapper.EcCustPerMapper;
import cn.com.zsyk.crm.query.mapper.GuPolicyMainMapper;
import net.sf.json.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class CustService {

    @Autowired
    EcCustPerMapper ecCustPerMapper;
    @Autowired
    GuPolicyMainMapper guPolicyMainMapper;
    @Autowired
    EcCustManagerMapper ecCustManagerMapper;
    /* 分页查询对象 */
    @Autowired
    private CoreDaoImpl coreDaoImpl;
    @Autowired
    RestUtil restUtil;
    /**
     * Desc: 查询个人客户列表
     * @return
     * @author
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public PageBean getPerCustListByRole(QueryCustPerParam custper , List<String> custAgentList) {
//        Map managerMap = new HashMap();
//        managerMap.put("custNoList",this.getCustNoListByCustNo(custper.getCustNo()));
//        managerMap.put("custAgentList", custAgentList);
//        List<EcCustManager>  ecCustManagerList  = ecCustManagerMapper.selectagentAll(managerMap);
//        if(ecCustManagerList.size()>0){
//
//        }
        Map map = new HashMap();
        map.put("custNo",custper.getCustNo());
        map.put("custName",custper.getCustName());
        map.put("telephone",custper.getTelephone());
        map.put("certNo", custper.getCertNo());
        map.put("certTyp", custper.getCertTyp());
        map.put("beginAge", custper.getBeginAge());
        map.put("endAge", custper.getEndAge());
        map.put("custAgent",custper.getCustAgent());
        map.put("custAgentList", custAgentList);
        map.put("custTyp",custper.getCustTyp());
        map.put("sex",custper.getSex());
        map.put("bNo",custper.getbNo());
        map.put("beginPolicy",custper.getBeginPolicy());
        map.put("endPolicy", custper.getEndPolicy());
        map.put("beginsPolicy", custper.getBeginsPolicy());
        map.put("endsPolicy", custper.getEndsPolicy());
        map.put("companycode", custper.getCompanycode());
        map.put("type",custper.getType());
        map.put("beginAmount", custper.getBeginAmount());
        map.put("endAmount",custper.getEndAmount());
        map.put("tag",custper.getTag());
        map.put("group",custper.getGroup());
        map.put("eventType",custper.getEventType());
        map.put("beginEvent",custper.getBeginEvent());
        map.put("endEvent",custper.getEndEvent());
        map.put("orderNo",custper.getOrderNo());
        map.put("mark",custper.getMark());

        //add增加分页功能并优化逻辑 lijiangcheng 2019-08-20
        Map<String, String> agentMap = this.getAgentListMapByEmployeeId();
        PageBean retlst;
        if(StringUtils.isNotEmpty(custper.getOrderNo())&&StringUtils.isNotEmpty(custper.getMark())){
             retlst = coreDaoImpl.selectPageByMapper(ecCustPerMapper, "selectPerCustListByOrderNo", map);
        }else {
             retlst = coreDaoImpl.selectPageByMapper(ecCustPerMapper, "selectPerCustListByRole", map);
        }
        List<PerCustList> retlstList=retlst.getList();
        if (null != retlstList) {
            for(PerCustList info : retlstList) {
                if(info.getCustStat()!=null && EnumType.CustStat.merged.value.equals(info.getCustStat())){
                    Map map1 = new HashMap();
                    map1.put("custNo", info.getCustNo());
                    Result getForObject = restUtil.getForObject(ServiceType.ECIF,
                            "/crm/ecif/similar/getNewCustNoByOldCustNo?custNo={custNo}", Result.class, map1);
                    String newCustNo=(String)getForObject.getData();
                    if(newCustNo!=null && !"".equals(newCustNo)){
                        info.setCustNo(newCustNo);
                    }
                }

                List<EcCustTag>tagLst = this.selectByCustNo(info.getCustNo());
                info.setCustTag(tagLst);
                StringBuffer subbuff = new StringBuffer();
                String retString ="";
                if (tagLst != null && tagLst.size() >0) {
                    for (EcCustTag tagObj : tagLst) {
                        if (tagObj.getCustTagNam() == null || StringUtils.isEmpty(tagObj.getCustTagNam())) {
                            continue;
                        }
                        subbuff.append(tagObj.getCustTagNam());
                        subbuff.append(",");
                    }
                    if (StringUtils.isNotEmpty(subbuff) && subbuff != null) {
                        retString = subbuff.substring(0, subbuff.length() - 1);
                    }
                }
                info.setCustTagNamStr(retString);
                // 通过客户经理工号，获取名字
                info.setCustAgentNam(agentMap.get(info.getCustAgent()));
                //获取手机号
                if(info.getPhoneNumber()==null || "".equals(info.getPhoneNumber())){
                    Map map2 = new HashMap();
                    map2.put("custNo",info.getCustNo());
                    info.setPhoneNumber(ecCustPerMapper.getPhoneNumberById(map2));
                }
            }
        }
        retlst.setList(retlstList);
        return retlst;
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
    /**
     * Desc: 根据客户号查询客户客户标签列表
     * @param custNo
     * @return
     * @author
     * @throws
     */
    public  List<EcCustTag> selectByCustNo(String custNo) {
        List<EcCustTag> ecCustTaglst = ecCustPerMapper.selectByCustNo(custNo);
        List<String> tagIdList = new ArrayList<String>();
        // 生成tagId list
        for(EcCustTag obj : ecCustTaglst) {
            tagIdList.add(obj.getCustTagCd());
        }
        // 通过tagId list 获取tag信息
        Map<String, String>tagMap =  getCustTagByTagIdList(tagIdList);

        // 获取名字
        for(EcCustTag obj : ecCustTaglst) {
            obj.setCustTagNam(tagMap.get(obj.getCustTagCd()));
        }
        return ecCustTaglst;
    }
    public Map<String, String> getCustTagByTagIdList(List<String> tagIdList) {
        Map<String, String> retMap = new HashMap<String, String>();

        if (tagIdList == null || tagIdList.size() == 0) {
            return retMap;
        }
        // 返回
        Result response = null;
        try {
            String tagIdListStr = JSONArray.fromObject(tagIdList).toString();

            response = restUtil.getForObject(ServiceType.MANAGE, "/crm/manage/tagmng/getTagByIdList?tagIdListStr={tagIdListStr}", Result.class,  tagIdListStr);
        } catch (Exception e) {
            return retMap;
        }

        if (response == null || response.getData() == null) {
            return retMap;
        }

        if (response != null) {
            List<LinkedHashMap> list = (List<LinkedHashMap>)response.getData();
            if (list != null) {
                for(LinkedHashMap o : list) {
                    String tagId = String.valueOf(o.get("tagId"));
                    String tagName = (String)o.get("tagName");
                    retMap.put(tagId, tagName);
                }
            }
        }
        return retMap;
    }

    /**
     * Desc: 根据客户号查询被合并的客户
     * @param custNo
     * @return
     * @author
     * @throws
     */
    public  List<String> getCustNoListByCustNo(String custNo) {
        List<String> custNoNewList= new ArrayList();
        List<String> custNoOldList= new ArrayList();
        custNoOldList.add(custNo);
        custNoNewList.add(custNo);
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
        return custNoNewList;
    }

    /**
     * Desc: 根据客户号查询客户保单信息
     * @param policyInfoData
     * @return
     * @author
     * @throws
     */
    public  PageBean getPolicyListByCustNo(PolicyInfoData policyInfoData) {
//        List<PolicyResData> resData = ecCustPerMapper.getPolicyListByCustNo(reqData);
        List<String> custNoList  =this.getCustNoListByCustNo(policyInfoData.getCustNo());
        PageBean resData = coreDaoImpl.selectPageByMapper(ecCustPerMapper,"getPolicyListByCustNo",custNoList);

        return resData;
    }

    /**
     * Desc: 根据客服ID查询客户客服工单信息
     * @param ccTickets
     * @return
     * @author
     * @throws
     */
    public  PageBean getOrderListByCustId(CcTickets ccTickets) {
//        List<ccTickets> resData = ecCustPerMapper.getOrderListByCustId(ccTickets);
        PageBean resData = coreDaoImpl.selectPageByMapper(ecCustPerMapper,"getOrderListByCustId",ccTickets);

        return resData;
    }


    /**
     * Desc: 根据客服ID查询客户客服通话记录信息
     * @param ccCallLogs
     * @return
     * @author
     * @throws
     */
    public  PageBean getCustomerListByCustId(CcCallLogs ccCallLogs) {
//        List<ccCallLogs> returnsData = ecCustPerMapper.getCustomerListByCustId(ccCallLogs);
        PageBean resData = coreDaoImpl.selectPageByMapper(ecCustPerMapper,"getCustomerListByCustId",ccCallLogs);

        return resData;
    }

    /**
     * Desc: 根据保单号查询承保信息
     * @param policyInfoData
     * @return
     * @author
     * @throws
     */
    public  List<PolicyInfoData> getPolicyInfoByPolicy(PolicyInfoData policyInfoData) {
        List<PolicyInfoData> resData = ecCustPerMapper.getPolicyInfoByPolicy(policyInfoData);

        return resData;
    }

    /**
     * Desc: 根据保单号查询批单信息
     * @param endorInfo
     * @return
     * @author
     * @throws
     */
    public  PageBean getEndorInfoByPolicy(EndorInfo endorInfo) {
        PageBean resData = coreDaoImpl.selectPageByMapper(ecCustPerMapper,"getEndorInfoByPolicy",endorInfo);

        return resData;
    }

    /**
     * Desc: 根据保单号查询车理赔信息
     * @param claimInfo
     * @return
     * @author
     * @throws
     */
    public  PageBean getClaimCarInfoByPolicy(ClaimInfo claimInfo) {

        PageBean resData = coreDaoImpl.selectPageByMapper(ecCustPerMapper,"getClaimCarInfoByPolicy",claimInfo);

        return resData;
    }

    /**
     * Desc: 根据保单号查询非车理赔信息
     * @param claimInfo
     * @return
     * @author
     * @throws
     */
    public  PageBean getClaimNoCarInfoByPolicy(ClaimInfo claimInfo) {

        PageBean resData = coreDaoImpl.selectPageByMapper(ecCustPerMapper,"getClaimNoCarInfoByPolicy",claimInfo);

        return resData;
    }

    /**
     * Desc: 根据保单号查询承保险别信息
     * @param claimInfo
     * @return
     * @author
     * @throws
     */
    public  PageBean getItemKindByPolicy(ClaimInfo claimInfo) {

        PageBean resData = coreDaoImpl.selectPageByMapper(ecCustPerMapper,"getItemKindByPolicy",claimInfo);

        return resData;
    }

    /**
     * Desc: 根据保单号查询批单批改项
     * @param claimInfo
     * @return
     * @author
     * @throws
     */
    public  PageBean getEndorInfoKindByEndorNo(ClaimInfo claimInfo) {

        PageBean resData = coreDaoImpl.selectPageByMapper(ecCustPerMapper,"getEndorInfoKindByEndorNo",claimInfo);

        return resData;
    }

    /**
     * Desc: 根据保单号查询--缴费信息--承保、批改
     * @param claimInfo
     * @return
     * @author
     * @throws
     */
    public  PageBean getPayInfoPolicyByPolicyNo(ClaimInfo claimInfo) {

        PageBean resData = coreDaoImpl.selectPageByMapper(ecCustPerMapper,"getPayInfoPolicyByPolicyNo",claimInfo);

        return resData;
    }

    /**
     * Desc: 根据保单号查询--缴费信息--理赔
     * @param claimInfo
     * @return
     * @author
     * @throws
     */
    public  PageBean getPayInfoClaimByPolicyNo(ClaimInfo claimInfo) {

        PageBean resData = coreDaoImpl.selectPageByMapper(ecCustPerMapper,"getPayInfoClaimByPolicyNo",claimInfo);

        return resData;
    }
    /**
     * Desc: 查询保单特别约定条款信息
     * @param claimInfo
     * @return
     * @author
     * @throws
     */
    public  PageBean getSpecialClausesByPolicyNo(ClaimInfo claimInfo) {

        PageBean resData = coreDaoImpl.selectPageByMapper(ecCustPerMapper,"getSpecialClausesByPolicyNo",claimInfo);

        return resData;
    }
    
    /**
     * Desc: 根据客户代码查询客户的各个险类的总保额
     * @param custNo 客户代码
     * @return Map<String, Double> 客户的各个险类的总保额
     * @author chuyingfei
     * @throws
     */
    public List<Map<String, Double>> queryInsuranceAmount(String custNo) {
        List<String> custNoList  =this.getCustNoListByCustNo(custNo);
    	return guPolicyMainMapper.selectInsuranceAmountByCustNo(custNoList);
    }
}
