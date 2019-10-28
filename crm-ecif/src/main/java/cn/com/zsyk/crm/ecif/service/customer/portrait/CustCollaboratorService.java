package cn.com.zsyk.crm.ecif.service.customer.portrait;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.com.zsyk.crm.common.constant.ServiceType;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.DateUtil;
import cn.com.zsyk.crm.common.util.RestUtil;
import cn.com.zsyk.crm.ecif.entity.EcCustCollaborator;
import cn.com.zsyk.crm.ecif.mapper.EcCustCollaboratorMapper;

@Service
@Transactional
public class CustCollaboratorService {

	@Autowired
	private EcCustCollaboratorMapper ecCustCollaboratorMapper;
	@Autowired
	private RestUtil restUtil;
	/**
	 * Desc: 客户协作人新增
	 * @param ecCustCntr
	 * @author
	 */
	public void addCustCollaborator(String custNo, List<String> userIdList) {
		// 客户号
		if (StringUtils.isEmpty(custNo)) {
			throw new ServiceException("客户号不能为空");
		}
		if (userIdList == null || userIdList.isEmpty()) {
			throw new ServiceException("用户ID列表不能为空");
		}
		for (String userId : userIdList) {
			EcCustCollaborator obj = ecCustCollaboratorMapper.selectByPrimaryKey(custNo, userId);
			if (obj != null) {
				continue;
			}
			EcCustCollaborator ecCustCollaborator = new EcCustCollaborator();
			ecCustCollaborator.setCustNo(custNo);
			ecCustCollaborator.setCollaborator(userId);
			ecCustCollaborator.setCollaborateDate(DateUtil.getCurTime());
			ecCustCollaboratorMapper.insert(ecCustCollaborator);
		}
	}

	/**
	 * Desc: 客户协作人修改
	 * @param ecCustCollaborator
	 * @author
	 */
	public void uptCustCollaborator(EcCustCollaborator ecCustCollaborator) {
		if (ecCustCollaborator == null) {
			throw new ServiceException("客户协作人信息为空");
		}
		if (StringUtils.isEmpty(ecCustCollaborator.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}
		// 客户号
		if (StringUtils.isEmpty(ecCustCollaborator.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}
		if (StringUtils.isEmpty(ecCustCollaborator.getCollaborator())) {
			throw new ServiceException("客户协作人编号不能为空");
		}
		EcCustCollaborator obj = ecCustCollaboratorMapper.selectByPrimaryKey(ecCustCollaborator.getCustNo(), ecCustCollaborator
				.getCollaborator());
		if (obj == null) {
			throw new ServiceException("客户协作人信息不存在");
		}
		ecCustCollaboratorMapper.updateByPrimaryKey(ecCustCollaborator);
	}

	/**
	 * Desc: 客户协作人删除
	 * @param ecCustCollaborator
	 * @author
	 */
	public void delCustCollaborator(EcCustCollaborator ecCustCollaborator) {
		if (ecCustCollaborator == null) {
			throw new ServiceException("客户协作人信息为空");
		}
		if (StringUtils.isEmpty(ecCustCollaborator.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}
		if (StringUtils.isEmpty(ecCustCollaborator.getCollaborator())) {
			throw new ServiceException("客户协作人编号不能为空");
		}
		EcCustCollaborator obj = ecCustCollaboratorMapper.selectByPrimaryKey(ecCustCollaborator.getCustNo(), ecCustCollaborator
				.getCollaborator());
		if (obj == null) {
			throw new ServiceException("客户协作人信息不存在");
		}
		ecCustCollaboratorMapper.deleteByPrimaryKey(ecCustCollaborator.getCustNo(), ecCustCollaborator.getCollaborator());
	}

	/**
	 * Desc: 查询客户协作人列表
	 * @param ecCustCollaborator
	 * @return
	 * @author
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EcCustCollaborator> getCustCollaboratorList(EcCustCollaborator ecCustCollaborator) {
		List<EcCustCollaborator> retlist = new ArrayList<EcCustCollaborator>();

		List<EcCustCollaborator> busiOppList = ecCustCollaboratorMapper.selectCustCollaboratorList(ecCustCollaborator);
		Result result = restUtil.getForObject(ServiceType.MANAGE, "/crm/manage/usermng/usersByEntity",Result.class);
		if (busiOppList != null) {
			if (result != null) {
				List<LinkedHashMap> list = (List<LinkedHashMap>)result.getData();
				if (list != null) {
					for (EcCustCollaborator collr : busiOppList) {
						for(LinkedHashMap o : list) {
							if (collr.getCollaborator().equals(o.get("userId"))) {
								// 姓名
								collr.setCollrNam(String.valueOf(o.get("userName")));
								// 电话
								collr.setCollrTel(String.valueOf(o.get("telphone")));
								 // 所属机构
								collr.setEnterName(String.valueOf(o.get("enterName")));
								// 所属部门
								collr.setDeptName(String.valueOf(o.get("deptName")));
								// 所属岗位
								collr.setPosiName(String.valueOf(o.get("posiName")));
								 // 用户状态
								collr.setUserStat(String.valueOf(o.get("userStat")));
								collr.setEmployeeId(String.valueOf(o.get("employeeId")));
							}
						}
						if (StringUtils.isEmpty(ecCustCollaborator.getCollrNam())) {
							retlist.add(collr);
						} else {
							String searchString = collr.getCollrNam()+collr.getCollrTel();
							int index = searchString.indexOf(ecCustCollaborator.getCollrNam());
							if (index != -1) {
								retlist.add(collr);
							}
						}
					}
				}
			}
		}
		return retlist;
	}

	/**
	 * Desc: 查询客户协作人(单条)
	 * @param custNo
	 * @param contractCustNo
	 * @return
	 * @author
	 */
	public EcCustCollaborator getCustCollaboratorOne(String custNo, String contractCustNo) {
		if (StringUtils.isEmpty(custNo)) {
			throw new ServiceException("客户号不能为空");
		}
		if (StringUtils.isEmpty(contractCustNo)) {
			throw new ServiceException("客户协作人编号不能为空");
		}
		EcCustCollaborator ecCustCollaborator = ecCustCollaboratorMapper.selectByPrimaryKey(custNo, contractCustNo);
		return ecCustCollaborator;
	}
}
