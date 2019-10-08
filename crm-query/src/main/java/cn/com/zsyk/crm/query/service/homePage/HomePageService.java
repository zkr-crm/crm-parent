package cn.com.zsyk.crm.query.service.homePage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.zsyk.crm.common.dao.CoreDaoImpl;
import cn.com.zsyk.crm.common.dto.ContextContainer;
import cn.com.zsyk.crm.common.util.DateUtil;
import cn.com.zsyk.crm.common.util.LogUtil;
import cn.com.zsyk.crm.query.bo.CusEventCount;
import cn.com.zsyk.crm.query.bo.LoyaltyUserCount;

@Service
@Transactional
public class HomePageService {
	/** logger */
	@SuppressWarnings("unused")
	private final Log logger = LogUtil.getLogger(HomePageService.class);
	/** 相似客户任务表 */
	@Autowired
	private CoreDaoImpl dao;
	
	/**
	 * 按条件查询所有的客户、保单、保费、真实性客户
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map getDataStatisticsByEntity(List<String> custAgentList) {
		Map map = null;
		try {
			//客户统计
			Map condition = new HashMap<>();
			condition.put("custAgentList", custAgentList);
			condition.put("count", 3);//获取配置表 //暂时写死
			condition.put("userId", ContextContainer.getContext().getUserId());//交易次数
			//用户统计
			Integer userCount = dao.selectOne("cn.com.zsyk.crm.query.mapper.EcCustPerMapper.selectCusCountByRole", condition);
			//真实用户统计
			Integer realUserCount = dao.selectOne("cn.com.zsyk.crm.query.mapper.EcCustPerMapper.selectRealUserCountByRole", condition);
		    //保单数量
			Integer policyCount = dao.selectOne("cn.com.zsyk.crm.query.mapper.GuPolicyMainMapper.selectPolicyCountByRole", condition);
			//保费
			double premium = dao.selectOne("cn.com.zsyk.crm.query.mapper.GuPolicyMainMapper.selectPremiumCountByRole", condition);
			map = new HashMap<>();
			map.put("cus", userCount);
			map.put("premium", premium);
			map.put("policy", policyCount);
			map.put("realCusCount", realUserCount);
			if(userCount!= null && userCount != 0) {
				BigDecimal eventCount = new BigDecimal(realUserCount);
				BigDecimal eventCountTotal = new BigDecimal(userCount);
				map.put("realCus",eventCount.divide(eventCountTotal,2,BigDecimal.ROUND_DOWN).multiply(new BigDecimal(100)));
			}else {
				map.put("realCus", 0.0);
			}
		} catch (Exception e) {
			logger.error("crm-query服务：getDataStatisticsByEntity 方法按条件查询所有的客户、保单、保费、真实性客户 异常：", e);
			throw e;
		}
		return map;
	}
	
	/**
	 * 按条件查询忠诚度、客户事件占比
	 */
	@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
	public Map getDataStatisticsPieByEntity(List<String> custAgentList) {
		Map map = null;
		try {
			Map condition = new HashMap<>();
			condition.put("custAgentList", custAgentList);
			condition.put("count", 3);//交易次数
			condition.put("userId", ContextContainer.getContext().getUserId());//交易次数
			condition.put("startDate", DateUtil.getFetureDate(-365));//获取配置表 //暂时写死
			condition.put("endDate", DateUtil.date2FormatString(new Date()));//获取配置表 //暂时写死
			/**************忠诚度用户**********************/
			logger.info("**************忠诚度用户**************开始********");
			List loyalListType = new ArrayList<String>();//暂时取消 页面类型写死
			List loyalListCount = new ArrayList<Integer>();
			//承保、批改
			List<LoyaltyUserCount> underLoyaltyUserCount = dao.selectList("cn.com.zsyk.crm.query.mapper.GpPolicyPayMentDetailMapper.selectLoyaltyUserByRole", condition);
			//理赔
			List<LoyaltyUserCount> claimLoyaltyUserCount = dao.selectList("cn.com.zsyk.crm.query.mapper.GpLossPayMentDetailMapper.selectLoyaltyUserByRole", condition);
			int loyaltyUserTotal = 0;//忠诚用户
			int otherUserTotal = 0;//其他用户
			//承保、批改
			if(underLoyaltyUserCount != null && !underLoyaltyUserCount.isEmpty()) {
				for(LoyaltyUserCount cusEventCount : underLoyaltyUserCount) {
					//获取事件总数量
					loyaltyUserTotal += cusEventCount.getLoyaltyUserCount();
					otherUserTotal += cusEventCount.getOtherUserCount();
				}
			}
			
			//理赔
			if(claimLoyaltyUserCount != null && !claimLoyaltyUserCount.isEmpty()) {
				for(LoyaltyUserCount cusEventCount : claimLoyaltyUserCount) {
					//获取事件总数量
					loyaltyUserTotal += cusEventCount.getLoyaltyUserCount();
					otherUserTotal += cusEventCount.getOtherUserCount();
				}
			}
			logger.info("**************忠诚度用户**************结束********");
			loyalListCount.add(loyaltyUserTotal);
			loyalListCount.add(otherUserTotal);
			/**************客户事件占比**********************/
			logger.info("**************客户事件占比**************开始********");
			List eventTypeList = new ArrayList<String>();
			List evenCounttList = new ArrayList<Integer>();
			condition.put("userId", ContextContainer.getContext().getUserId());
			List<CusEventCount> realUserCount = dao.selectList("cn.com.zsyk.crm.query.mapper.EcCustEventMapper.selectEventByUserId", condition);
			int eventTotal = 0;
			if(realUserCount != null && !realUserCount.isEmpty()) {
				for(CusEventCount cusEventCount : realUserCount) {
					//获取事件总数量
					eventTotal += cusEventCount.getEventCount();
				}
				for(CusEventCount cusEventCount : realUserCount) {
					//获取事件总数量
					eventTypeList.add(cusEventCount.getEventType());
					if(eventTotal != 0) {
						BigDecimal eventCount = new BigDecimal(cusEventCount.getEventCount());
						BigDecimal eventCountTotal = new BigDecimal(eventTotal);
						evenCounttList.add(eventCount.divide(eventCountTotal,2,BigDecimal.ROUND_DOWN).multiply(new BigDecimal(100)));
					}else {
						evenCounttList.add(0);
					}
					
					eventTotal += cusEventCount.getEventCount();
				}
			}
			logger.info("**************客户事件占比**************结束********");
			map = new HashMap<>();
			//忠诚度
			map.put("loyalList", loyalListCount);
			//事件
			map.put("eventTypeList", eventTypeList);
			map.put("eventCountList", evenCounttList);
		} catch (Exception e) {
			logger.error("crm-query服务：getDataStatisticsPieByEntity 方法按条件查询忠诚度、客户事件占比 异常：", e);
			throw e;
		}
		return map;
	}
}
