package cn.com.zsyk.crm.query.mapper;

import java.util.List;
import java.util.Map;

import cn.com.zsyk.crm.query.bo.CusEventCount;
import cn.com.zsyk.crm.query.entity.AgesBar;

/**
 * 事件统计
 * @author houya
 *
 */
public interface EcCustEventMapper {
	
	/**
	 * 根据用户查询事件
	 * @param map
	 * @return
	 */
	List<CusEventCount> selectEventByUserId(Map map);
	List<AgesBar> selectDataBarByAges(Map map);
	
}