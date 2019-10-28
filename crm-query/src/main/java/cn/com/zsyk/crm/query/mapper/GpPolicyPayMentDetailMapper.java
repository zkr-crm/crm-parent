package cn.com.zsyk.crm.query.mapper;

import java.util.List;
import java.util.Map;

import cn.com.zsyk.crm.query.bo.LoyaltyUserCount;

/**
 * 缴费信息--承保、批改
 * @author houya
 *
 */
public interface GpPolicyPayMentDetailMapper {
	/**
	 * 缴费信息、承保、批改 根据角色用户忠诚度用
	 * @param map
	 * @return
	 */
	List<LoyaltyUserCount>selectLoyaltyUserByRole(Map map);
}