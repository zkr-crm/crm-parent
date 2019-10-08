package cn.com.zsyk.crm.query.mapper;

import java.util.List;
import java.util.Map;

import cn.com.zsyk.crm.query.bo.LoyaltyUserCount;

/**
 * --缴费信息--理赔
 * @author houya
 *
 */
public interface GpLossPayMentDetailMapper {
	
	/**
	 * 缴费信息、理赔 根据角色用户忠诚度用
	 * @param map
	 * @return
	 */
	List<LoyaltyUserCount>selectLoyaltyUserByRole(Map map);
	
}