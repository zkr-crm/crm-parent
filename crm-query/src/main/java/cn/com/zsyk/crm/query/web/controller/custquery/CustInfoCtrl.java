package cn.com.zsyk.crm.query.web.controller.custquery;


import cn.com.zsyk.crm.common.constant.ServiceType;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.JsonUtil;
import cn.com.zsyk.crm.common.util.RestUtil;
import cn.com.zsyk.crm.query.bo.*;
import cn.com.zsyk.crm.query.entity.CcCallLogs;
import cn.com.zsyk.crm.query.entity.CcTickets;
import cn.com.zsyk.crm.query.service.custquery.CustService;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class CustInfoCtrl {

    @Autowired
    private CustService custService;
    @Autowired
    private RestUtil restUtil;
    /**
     * @api {POST}  查询个人客户列表
     * @apiName getPerCustList
     * @apiGroup Customer
     *1
     *
     *
     * @apiSuccess {Object} Result 返回值对象<br/>
     * 			客户号 custNo<br/>
     * 			客户类型 custTyp->CustType<br/>
     * 			客户名称 custName<br/>
     * 			客户号码 phoneNumber<br/>
     * 			标签 List<EcCustTag> custTag<br/>
     * 			客户来源 custSource->DataSource<br/>
     * 			客户阶段 custPhase-(待确认)<br/>
     * 			客户经理 custAgent<br/>
     * 			下次跟进时间 nextFollowUpTime<br/>
     * 			备注 remark<br/>
     * 			创建人 createUser<br/>
     */

    @RequestMapping(path = "/crm/query/custquery/getPerCustListByRole", method = RequestMethod.POST)
    public Result getPerCustListByRole(QueryCustPerParam custper , @RequestBody String custAgentJson) {
        Result res = new Result();

        //字符串转换
        List<String> custAgentList = null;

        // 字符串转换
        if ("{}".equals(custAgentJson) || "".equals(custAgentJson)) {
            custAgentList = new ArrayList<String>();
        } else {
            custAgentList = JsonUtil.parseArray(custAgentJson, String.class);
        }

        res.setData(custService.getPerCustListByRole(custper,custAgentList));
        return res;
    }

    /**
     * @api {POST}  查询保单列表
     */

    @RequestMapping(path = "/crm/query/custquery/getPolicyListByCustNo", method = RequestMethod.POST)
    public Result getPolicyListByCustNo(@RequestBody String reqData ) {
        Result res = new Result();


        PolicyInfoData policyInfoData = (PolicyInfoData) JSONObject.toBean(JSONObject.fromObject(reqData), PolicyInfoData.class);

        if(policyInfoData==null) {
            res.setData(false);
        } else {
            if (StringUtils.isEmpty(policyInfoData.getCustNo())) {
                throw new ServiceException("客户号不能为空");
            }
            res.setData(custService.getPolicyListByCustNo(policyInfoData));

        }
        return res;
    }

    /**
     * @api {POST}  查询客服信息列表
     */

    @RequestMapping(path = "/crm/query/custquery/getCallLogsByCustNo", method = RequestMethod.POST)
    public Result getCustomerListByCustNo(@RequestBody String reqData ) {
        Result res = new Result();

        CcCallLogs ccCallLogs = (CcCallLogs) JSONObject.toBean(JSONObject.fromObject(reqData), CcCallLogs.class);

        if(ccCallLogs==null) {
            res.setData(false);
        } else {
            if (StringUtils.isEmpty(ccCallLogs.getCustomerId().toString())) {
                throw new ServiceException("客服编号不能为空");
            }
            res.setData(custService.getCustomerListByCustId(ccCallLogs));
        }
        return res;
    }

    /**
     * @api {POST}  查询客服工单列表
     */

    @RequestMapping(path = "/crm/query/custquery/getOrderListByCustId", method = RequestMethod.POST)
    public Result getOrderListByCustId(@RequestBody String reqData ) {
        Result res = new Result();

        CcTickets ccTickets = (CcTickets) JSONObject.toBean(JSONObject.fromObject(reqData), CcTickets.class);

        if(ccTickets==null) {
            res.setData(false);
        } else {
            if (StringUtils.isEmpty(ccTickets.getUserId().toString())) {
                throw new ServiceException("客服编号不能为空");
            }
            String custNo=ccTickets.getUserId().toString();
            Result getForObject = restUtil.getForObject(ServiceType.ECIF, "/crm/ecif/cust/mng/perCustInfo?custNo={custNo}", Result.class,  custNo);
            Map<String, String> perCustBaseInfo = (Map<String, String>) getForObject.getData();
            String userid=perCustBaseInfo.get("customerId");
            if(userid!=null && !"".equals(userid) ){
                ccTickets.setUserId(new BigDecimal(userid));
            }else{
                throw new ServiceException("不存在客服信息!");
            }
            res.setData(custService.getOrderListByCustId(ccTickets));
        }
        return res;
    }

    /**
     * @api {POST}  查询保单详情列表
     */

    @RequestMapping(path = "/crm/query/custquery/getPolicyInfoByPolicy", method = RequestMethod.POST)
    public Result getPolicyInfoByPolicy(@RequestBody String reqData ) {
        Result res = new Result();

        PolicyInfoData policyInfoData = (PolicyInfoData) JSONObject.toBean(JSONObject.fromObject(reqData), PolicyInfoData.class);

        if(policyInfoData==null) {
            res.setData(false);
        } else {
//            if (StringUtils.isEmpty(policyInfoData.getCustNo())) {
//                throw new ServiceException("客户编号不能为空");
//            }
            if (StringUtils.isEmpty(policyInfoData.getPolicyNo())) {
                throw new ServiceException("保单号不能为空");
            }
            if (StringUtils.isEmpty(policyInfoData.getRiskCode())) {
                throw new ServiceException("险种不能为空");
            }
            res.setData(custService.getPolicyInfoByPolicy(policyInfoData));
        }
        return res;
    }


    /**
     * @api {POST}  查询批改详情列表
     */

    @RequestMapping(path = "/crm/query/custquery/getEndorInfoByPolicy", method = RequestMethod.POST)
    public Result getEndorInfoByPolicy(@RequestBody String reqData ) {
        Result res = new Result();

        EndorInfo endorInfo = (EndorInfo) JSONObject.toBean(JSONObject.fromObject(reqData), EndorInfo.class);

        if(endorInfo==null) {
            res.setData(false);
        } else {
            if (StringUtils.isEmpty(endorInfo.getPolicyNo())) {
                throw new ServiceException("保单号不能为空");
            }
            res.setData(custService.getEndorInfoByPolicy(endorInfo));
        }
        return res;
    }

    /**
     * @api {POST}  查询理赔详情列表
     */

    @RequestMapping(path = "/crm/query/custquery/getClaimInfoByPolicy", method = RequestMethod.POST)
    public Result getClaimInfoByPolicy(@RequestBody String reqData ) {
        Result res = new Result();

        ClaimInfo claimInfo = (ClaimInfo) JSONObject.toBean(JSONObject.fromObject(reqData), ClaimInfo.class);

        if(claimInfo==null) {
            res.setData(false);
        } else {
            if (StringUtils.isEmpty(claimInfo.getPolicyNo())) {
                throw new ServiceException("保单号不能为空");
            }
            if (StringUtils.isEmpty(claimInfo.getRiskCode())) {
                throw new ServiceException("险种不能为空");
            }
            if(claimInfo.getRiskCode().substring(0,2).equals("08")){
                res.setData(custService.getClaimCarInfoByPolicy(claimInfo));
            }else if (!claimInfo.getRiskCode().substring(0,2).equals("08")&&!claimInfo.getRiskCode().substring(0,2).equals("13")){
                res.setData(custService.getClaimNoCarInfoByPolicy(claimInfo));
            }else{
                throw new ServiceException("未查询到理赔数据");
            }
        }
        return res;
    }

    /**
     * @api {POST}  查询承保险别列表
     */

    @RequestMapping(path = "/crm/query/custquery/getItemKindByPolicy", method = RequestMethod.POST)
    public Result getItemKindByPolicy(@RequestBody String reqData ) {
        Result res = new Result();

        ClaimInfo claimInfo = (ClaimInfo) JSONObject.toBean(JSONObject.fromObject(reqData), ClaimInfo.class);

        if(claimInfo==null) {
            res.setData(false);
        } else {
            if (StringUtils.isEmpty(claimInfo.getPolicyNo())) {
                throw new ServiceException("保单号不能为空");
            }
            res.setData(custService.getItemKindByPolicy(claimInfo));
        }
        return res;
    }

    /**
     * @api {POST}  查询批单批改项
     */

    @RequestMapping(path = "/crm/query/custquery/getEndorInfoKindByEndorNo", method = RequestMethod.POST)
    public Result getEndorInfoKindByEndorNo(@RequestBody String reqData ) {
        Result res = new Result();

        ClaimInfo claimInfo = (ClaimInfo) JSONObject.toBean(JSONObject.fromObject(reqData), ClaimInfo.class);

        if(claimInfo==null) {
            res.setData(false);
        } else {
            if (StringUtils.isEmpty(claimInfo.getEndorNo())) {
                throw new ServiceException("批单号不能为空");
            }
            res.setData(custService.getEndorInfoKindByEndorNo(claimInfo));
        }
        return res;
    }

    /**
     * @api {POST}  根据保单号查询--缴费信息--承保、批改
     */

    @RequestMapping(path = "/crm/query/custquery/getPayInfoPolicyByPolicyNo", method = RequestMethod.POST)
    public Result getPayInfoPolicyByPolicyNo(@RequestBody String reqData ) {
        Result res = new Result();

        ClaimInfo claimInfo = (ClaimInfo) JSONObject.toBean(JSONObject.fromObject(reqData), ClaimInfo.class);

        if(claimInfo==null) {
            res.setData(false);
        } else {
            if (StringUtils.isEmpty(claimInfo.getPolicyNo())) {
                throw new ServiceException("保单号不能为空");
            }
            res.setData(custService.getPayInfoPolicyByPolicyNo(claimInfo));
        }
        return res;
    }


    /**
     * @api {POST}  根据保单号查询--缴费信息--理赔
     */

    @RequestMapping(path = "/crm/query/custquery/getPayInfoClaimByPolicyNo", method = RequestMethod.POST)
    public Result getPayInfoClaimByPolicyNo(@RequestBody String reqData ) {
        Result res = new Result();

        ClaimInfo claimInfo = (ClaimInfo) JSONObject.toBean(JSONObject.fromObject(reqData), ClaimInfo.class);

        if(claimInfo==null) {
            res.setData(false);
        } else {
            if (StringUtils.isEmpty(claimInfo.getPolicyNo())) {
                throw new ServiceException("保单号不能为空");
            }
            res.setData(custService.getPayInfoClaimByPolicyNo(claimInfo));
        }
        return res;
    }

    /**
     * @api {POST}  查询保单特别约定条款信息
     */

    @RequestMapping(path = "/crm/query/custquery/getSpecialClausesByPolicyNo", method = RequestMethod.POST)
    public Result getSpecialClausesByPolicyNo(@RequestBody String reqData ) {
        Result res = new Result();

        ClaimInfo claimInfo = (ClaimInfo) JSONObject.toBean(JSONObject.fromObject(reqData), ClaimInfo.class);

        if(claimInfo==null) {
            res.setData(false);
        } else {
            if (StringUtils.isEmpty(claimInfo.getPolicyNo())) {
                throw new ServiceException("保单号不能为空");
            }
            if (StringUtils.isEmpty(claimInfo.getRiskCode())) {
                throw new ServiceException("险种编码不能为空");
            }
            res.setData(custService.getSpecialClausesByPolicyNo(claimInfo));
        }
        return res;
    }
    
    /**
     * @api {POST}  根据客户号查询客户的各个险类的总保额
     */
    
    @RequestMapping(path = "/crm/query/custquery/queryInsuranceAmountByCustNo", method = RequestMethod.POST)
    public Result queryInsuranceAmount(@RequestBody String custNo) {
    	Result res = new Result();
    	res.setData(custService.queryInsuranceAmount(JSONObject.fromObject(custNo).getString("custNo")));
    	return res;
    }
}
