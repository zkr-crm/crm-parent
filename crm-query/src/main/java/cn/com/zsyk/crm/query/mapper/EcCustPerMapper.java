package cn.com.zsyk.crm.query.mapper;


import cn.com.zsyk.crm.query.bo.ClaimInfo;
import cn.com.zsyk.crm.query.bo.EndorInfo;
import cn.com.zsyk.crm.query.bo.PerCustList;
import cn.com.zsyk.crm.query.bo.PolicyInfoData;
import cn.com.zsyk.crm.query.entity.CcCallLogs;
import cn.com.zsyk.crm.query.entity.CcTickets;
import cn.com.zsyk.crm.query.entity.EcCustTag;

import java.util.List;
import java.util.Map;


/**
 * 用户映射类
 * @author houya
 *
 */
public interface EcCustPerMapper {
	/**
	 * 根据权限统计用户
	 * @param map
	 * @return
	 */
	int selectCusCountByRole(Map map);
	
	/**
	 * 根据权限统计真实用户
	 * @param map
	 * @return
	 */
	int  selectRealUserCountByRole(Map map);

	/**
	 * Desc: 根据客户号查询客户标签
	 * @param custNo
	 * @return
	 * @author
	 */
	List<EcCustTag> selectByCustNo(String custNo);

	List<PerCustList> selectPerCustListByRole(Map map);
	List<PerCustList> selectPerCustListByOrderNo(Map map);

	/**
	 * Desc: 查询客户被合并前的客户号
	 * @param custNo
	 * @return
	 * @author
	 */
	List<String> getCustNoListByCustNo(List<String>  custNo);
	/**
	 * Desc: 查询客户保单信息
	 * @param custNo
	 * @return
	 * @author
	 */
	List<PolicyInfoData> getPolicyListByCustNo(List<String>  custNo);

	/**
	 * Desc: 查询客服工单信息
	 * @param ccTickets
	 * @return
	 * @author
	 */
	List<CcTickets> getOrderListByCustId(CcTickets ccTickets);
	/**
	 * Desc: 查询客服通话记录信息
	 * @param ccCallLogs
	 * @return
	 * @author
	 */
	List<CcCallLogs> getCustomerListByCustId(CcCallLogs ccCallLogs);
	/**
	 * Desc: 查询保单承保信息
	 * @param policyInfoData
	 * @return
	 * @author
	 */
	List<PolicyInfoData> getPolicyInfoByPolicy(PolicyInfoData policyInfoData);

	/**
	 * Desc: 查询批单信息
	 * @param endorInfo
	 * @return
	 * @author
	 */
	List<EndorInfo> getEndorInfoByPolicy(EndorInfo endorInfo);
	/**
	 * Desc: 查询保单车理赔信息
	 * @param claimInfo
	 * @return
	 * @author
	 */
	List<ClaimInfo> getClaimCarInfoByPolicy(ClaimInfo claimInfo);
	/**
	 * Desc: 查询保单非车理赔信息
	 * @param claimInfo
	 * @return
	 * @author
	 */
	List<ClaimInfo> getClaimNoCarInfoByPolicy(ClaimInfo claimInfo);

	/**
	 * Desc: 查询承保险别列表
	 * @param claimInfo
	 * @return
	 * @author
	 */
	List<ClaimInfo> getItemKindByPolicy(ClaimInfo claimInfo);
	/**
	 * Desc: 查询批单批改项
	 * @param claimInfo
	 * @return
	 * @author
	 */
	List<ClaimInfo> getEndorInfoKindByEndorNo(ClaimInfo claimInfo);

	/**
	 * Desc: 查询--缴费信息--承保、批改
	 * @param claimInfo
	 * @return
	 * @author
	 */
	List<ClaimInfo> getPayInfoPolicyByPolicyNo(ClaimInfo claimInfo);
	/**
	 * Desc: 查询--缴费信息--理赔
	 * @param claimInfo
	 * @return
	 * @author
	 */
	List<ClaimInfo> getPayInfoClaimByPolicyNo(ClaimInfo claimInfo);
	/**
	 * Desc: 查询保单特别约定条款信息
	 * @param claimInfo
	 * @return
	 * @author
	 */
	List<ClaimInfo> getSpecialClausesByPolicyNo(ClaimInfo claimInfo);
	/**
	 * 发送短信/站内提醒 取客户数据
	 */
	List<Map<String, String>> getCustomerInfo(Map map);
	String getPhoneNumberById(Map map);
}