package cn.com.zsyk.crm.ocrm.service.custgroup;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.zsyk.crm.common.dao.CoreDaoImpl;
import cn.com.zsyk.crm.common.dto.PageBean;
import cn.com.zsyk.crm.common.util.IdGenerator;
import cn.com.zsyk.crm.common.util.RestUtil;
import cn.com.zsyk.crm.ocrm.entity.OcDynamSnapshot;
import cn.com.zsyk.crm.ocrm.mapper.OcCustGrpMapper;
import cn.com.zsyk.crm.ocrm.mapper.OcCustGrpMemberMapper;
import cn.com.zsyk.crm.ocrm.mapper.OcDynamGrpRuleRelaMapper;
import cn.com.zsyk.crm.ocrm.mapper.OcDynamSnapshotMapper;
import cn.com.zsyk.crm.ocrm.mapper.OcGroupOperCustMapper;
import cn.com.zsyk.crm.ocrm.mapper.OcGroupOperMapper;
import cn.com.zsyk.crm.ocrm.mapper.OcGrpCustTraceMapper;

/**
 * 
 * 群组快照管理
 * 
 * @author
 *
 */
@Service
@Transactional
public class DynamSnapshotService {

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
	 * 按条件查询快照列表（分页）
	 * 
	 * @param {OcDynamSnapshot}
	 *            dynamSnapshot 快照信息对象
	 * @return 快照信息列表
	 */
	public PageBean selectSnapByEntity(OcDynamSnapshot dynamSnapshot) {

		PageBean bean = coreDaoImpl.selectPageByMapper(dynamSnapshotMapper, "selectSnapByEntity", dynamSnapshot);

		return bean;
	}

	/**
	 * 按条件查询快照列表（分页）
	 * 
	 * @param {String}
	 *            condition 快照信息查询条件
	 * @return 快照信息列表
	 */
	public PageBean selectSnapOnFuzzy(String groupId,String condition) {

		Map map = new HashMap();
		map.put("groupId", groupId);
		map.put("condition", condition);
		PageBean bean = coreDaoImpl.selectPageByMapper(dynamSnapshotMapper, "selectSnapOnFuzzy", map);

		return bean;
	}

	/**
	 * 新增快照信息
	 * 
	 * @param {OcDynamSnapshot}
	 *            dynamSnapshot 快照信息对象
	 */
	public String addSnap(OcDynamSnapshot dynamSnapshot) {

		String snapshotId = "" + IdGenerator.getSeqID("SnapshotId");
		dynamSnapshot.setSnapshotId(snapshotId);
		dynamSnapshotMapper.insert(dynamSnapshot);

		return snapshotId;
	}
}
