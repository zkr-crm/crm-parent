package cn.com.zsyk.crm.ocrm.service.custgroup;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.zsyk.crm.common.dao.CoreDaoImpl;
import cn.com.zsyk.crm.common.dto.PageBean;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.DateUtil;
import cn.com.zsyk.crm.ocrm.entity.OcCustGrpMember;
import cn.com.zsyk.crm.ocrm.mapper.OcCustGrpMapper;
import cn.com.zsyk.crm.ocrm.mapper.OcCustGrpMemberMapper;
import cn.com.zsyk.crm.ocrm.mapper.OcDynamGrpRuleRelaMapper;
import cn.com.zsyk.crm.ocrm.mapper.OcDynamSnapshotMapper;
import cn.com.zsyk.crm.ocrm.mapper.OcGroupOperCustMapper;
import cn.com.zsyk.crm.ocrm.mapper.OcGroupOperMapper;
import cn.com.zsyk.crm.ocrm.mapper.OcGrpCustTraceMapper;

@Service
@Transactional
public class CustGrpMemberService {

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
	/* 群组运营任务表 */
	@Autowired
	private OcGroupOperCustMapper groupOperCustMapper;
	/* 群组运营任务成员表 */
	@Autowired
	private OcGroupOperMapper groupOperMapper;
	/* 动态客群客户轨迹表 */
	@Autowired
	private OcGrpCustTraceMapper grpCustTraceMapper;

	/* 分页查询对象 */
	@Autowired
	private CoreDaoImpl coreDaoImpl;

	/**
	 * 根据客户群组ID查询群组内客户列表(分页)
	 * 
	 * @param groupId
	 *            客户群组ID
	 * @return 群组内客户信息列表
	 */
	public PageBean getCustListByGroupIdPaging(String groupId) {

		PageBean bean = coreDaoImpl.selectPageByMapper(custGrpMemberMapper, "getCustListByGroupId", groupId);

		return bean;
	}

	/**
	 * 根据客户群组ID查询群组内客户列表(不分页)
	 * 
	 * @param groupId
	 *            客户群组ID
	 * @return 群组内客户信息列表
	 */
	public List<OcCustGrpMember> getCustListByGroupIdNoPaging(String groupId) {

		List<OcCustGrpMember> custGrpMemberList = custGrpMemberMapper.getCustListByGroupId(groupId);
		return custGrpMemberList;
	}

	/**
	 * 根据输入条件查询群组内客户列表
	 * 
	 * @param custGrpMember
	 * @return
	 */
	public PageBean getCustListByEntity(OcCustGrpMember custGrpMember) {

//		List<OcCustGrpMember> custGrpMemberList = custGrpMemberMapper.getCustListByEntity(custGrpMember);
		PageBean bean = coreDaoImpl.selectPageByMapper(custGrpMemberMapper, "getCustListByEntity", custGrpMember);

		return bean;
	}

	/**
	 * 添加客户群组成员
	 * 
	 * @param custGrpMember
	 *            群组成员对象
	 * @return
	 */
	public int addGroupMember(List<OcCustGrpMember> custGrpMemberList) {

		int count = 0;
		for (OcCustGrpMember item : custGrpMemberList) {

			OcCustGrpMember temp = custGrpMemberMapper.selectByPrimaryKey(item.getGroupId(), item.getCustNo());

			if (temp != null) {
				throw new ServiceException("客户[" + item.getCustNo() + "]在本群中已经存在，添加失败！");
			}

			item.setAddTime(DateUtil.getDate(new Date()));
			count += custGrpMemberMapper.insert(item);
		}

		return count;
	}

	/**
	 * 批量删除客户群组成员
	 * 
	 * @param custGrpMember
	 *            群组成员对象
	 * @return
	 */
	public int delMultiGroupMember(List<OcCustGrpMember> custGrpMemberList) {

		int count = 0;
		for (OcCustGrpMember item : custGrpMemberList) {
			count += custGrpMemberMapper.deleteByPrimaryKey(item.getGroupId(), item.getCustNo());
		}

		return count;
	}
}
