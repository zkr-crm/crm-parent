package cn.com.zsyk.crm.ocrm.service.custgroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.zsyk.crm.common.constant.ServiceType;
import cn.com.zsyk.crm.common.dao.CoreDaoImpl;
import cn.com.zsyk.crm.common.dto.PageBean;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.util.IdGenerator;
import cn.com.zsyk.crm.common.util.RestUtil;
import cn.com.zsyk.crm.ocrm.bo.custgroup.CustOperDetail;
import cn.com.zsyk.crm.ocrm.entity.OcCustGrpMember;
import cn.com.zsyk.crm.ocrm.entity.OcGroupOper;
import cn.com.zsyk.crm.ocrm.entity.OcGroupOperCust;
import cn.com.zsyk.crm.ocrm.mapper.OcCustGrpMapper;
import cn.com.zsyk.crm.ocrm.mapper.OcCustGrpMemberMapper;
import cn.com.zsyk.crm.ocrm.mapper.OcDynamGrpRuleRelaMapper;
import cn.com.zsyk.crm.ocrm.mapper.OcDynamSnapshotMapper;
import cn.com.zsyk.crm.ocrm.mapper.OcGroupOperCustMapper;
import cn.com.zsyk.crm.ocrm.mapper.OcGroupOperMapper;
import cn.com.zsyk.crm.ocrm.mapper.OcGrpCustTraceMapper;

/**
 * 
 * 群组任务管理
 * 
 * @author
 *
 */
@Service
@Transactional
public class GroupOperService {

	/* 客户群组表 */
	@Autowired
	private OcCustGrpMapper custGrpMapper;
	/* 客户群组成员表 */
	@Autowired
	private OcCustGrpMemberMapper custGrpMemberMapper;
	/* 动态群组策略关联表 */
	@Autowired
	private OcDynamGrpRuleRelaMapper dynamGrpRuleRelaMapper;
	/* 客户群快照表 */
	@Autowired
	private OcDynamSnapshotMapper dynamSnapshotMapper;
	/* 群组运营任务成员表 */
	@Autowired
	private OcGroupOperCustMapper groupOperCustMapper;
	/* 群组运营任务表 */
	@Autowired
	private OcGroupOperMapper groupOperMapper;
	/* 动态客群客户轨迹表 */
	@Autowired
	private OcGrpCustTraceMapper grpCustTraceMapper;

	/* 分页查询对象 */
	@Autowired
	private CoreDaoImpl coreDaoImpl;
	/* Rest客户端工具 */
	@Autowired
	private RestUtil restUtil;

	/**
	 * 按条件查询运营任务列表（分页）
	 * 
	 * @param {OcGroupOper}
	 *            groupOper 运营任务对象
	 * @return 运营任务信息列表
	 */
	public PageBean selectByEntity(OcGroupOper groupOper) {

		PageBean bean = coreDaoImpl.selectPageByMapper(groupOperMapper, "selectByEntity", groupOper);

		return bean;
	}

	/**
	 * 模糊查询运营任务列表（分页）
	 * 
	 * @param {String}
	 *            groupId 群组ID
	 * @param {String}
	 *            queryCondition 查询条件
	 * @return 运营任务信息列表
	 */
	public PageBean selectOnFuzzy(String groupId, String queryCondition) {

		Map map = new HashMap();
		map.put("groupId", groupId);
		map.put("queryCondition", queryCondition);
		PageBean bean = coreDaoImpl.selectPageByMapper(groupOperMapper, "selectOnFuzzy", map);

		return bean;
	}

	/**
	 * 模糊查询运营任务列表（分页）
	 * 
	 * @param {OcGroupOper}
	 *            groupOper 运营任务对象
	 * @return 运营任务信息列表
	 */
	public PageBean selectTaskByGrpOperId(String groupOperId, String custName, String custAgent) {

		Map map = new HashMap();
		map.put("groupOperId", groupOperId);

		// 分页获取任务详情信息
		PageBean bean = coreDaoImpl.selectPageByMapper(groupOperMapper, "selectTaskByGrpOperId", map);

		// 获取查询到的任务列表
		List<CustOperDetail> list = bean.getList();

		// 遍历所有任务，获取客户号，查询客户相关信息
		for (CustOperDetail item : list) {

			Map map2 = new HashMap();
			map2.put("custNo", item.getCustNo());

			// ECIF发起GET请求
			Result postForObject = restUtil.getForObject(ServiceType.ECIF,
					"/crm/ecif/cust/mng/perCustInfo?custNo={custNo}", Result.class, map2);

			Map<String, String> map3 = (Map<String, String>) postForObject.getData();
			// 客户姓名
			item.setCustNam(map3.get("custName").toString());
			// 负责人
			item.setCustAgent(map3.get("custAgent").toString());
		}

		boolean custNameflg = (custName != null && !"".equals(custName));
		boolean custAgentflg = (custAgent != null && !"".equals(custAgent));

		// 两者都有值
		if (custNameflg && custAgentflg) {

			List<CustOperDetail> listWaitRemove = new ArrayList<CustOperDetail>();
			for (CustOperDetail item : list) {
				if (!(custName.equals(item.getCustNo()) && custAgent.equals(item.getCustAgent()))) {
					listWaitRemove.add(item);
				}
			}
			// 删除所有不符合条件的数据
			list.removeAll(listWaitRemove);
		}

		// 客户名有值
		if (custNameflg && !custAgentflg) {
			List<CustOperDetail> listWaitRemove = new ArrayList<CustOperDetail>();
			for (CustOperDetail item : list) {
				if (!custName.equals(item.getCustNo())) {
					listWaitRemove.add(item);
				}
			}
			// 删除所有不符合条件的数据
			list.removeAll(listWaitRemove);
		}

		// 负责人有值
		if (!custNameflg && custAgentflg) {
			List<CustOperDetail> listWaitRemove = new ArrayList<CustOperDetail>();
			for (CustOperDetail item : list) {
				if (!custAgent.equals(item.getCustAgent())) {
					listWaitRemove.add(item);
				}
			}
			// 删除所有不符合条件的数据
			list.removeAll(listWaitRemove);
		}

		return bean;
	}

	/**
	 * 生成运营任务
	 * 
	 * @param {OcGroupOper}
	 *            groupOper 群组任务对象
	 * @return 运营任务id
	 */
	public String addOper(OcGroupOper groupOper) {

		String groupOperId = "";

		Long seqId = IdGenerator.getSeqID("GroupOper");
		groupOperId = seqId.toString();
		groupOper.setGroupOperId(groupOperId);// 运营任务ID

		groupOperMapper.insert(groupOper);

		return groupOperId;
	}

	/**
	 * 添加运营任务成员
	 * 
	 * @param {String}
	 *            groupOperId 运营任务ID
	 * @param {OcGroupOperCust}
	 *            groupOperCustList 任务成员列表
	 * @return 运营任务id
	 */

	public void addOperCust(String groupOperId, List<OcGroupOperCust> groupOperCustList) {

		for (OcGroupOperCust item : groupOperCustList) {

			item.setGroupOperId(groupOperId);

			groupOperCustMapper.insert(item);
		}
	}
}
