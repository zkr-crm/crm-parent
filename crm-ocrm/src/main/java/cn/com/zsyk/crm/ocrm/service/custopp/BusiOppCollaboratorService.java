package cn.com.zsyk.crm.ocrm.service.custopp;

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
import cn.com.zsyk.crm.ocrm.entity.OcBusiOppCollaborator;
import cn.com.zsyk.crm.ocrm.mapper.OcBusiOppCollaboratorMapper;

@Service
@Transactional
public class BusiOppCollaboratorService {

	@Autowired
	private OcBusiOppCollaboratorMapper ocBusiOppCollaboratorMapper;
	@Autowired
	private RestUtil restUtil;
	/**
	 * Desc: 商机协作人新增
	 * @param busiOppNo
	 * @param custNo
	 * @param userIdList
	 * @author
	 */
	public void addBusiOppCollaborator(String busiOppNo, String custNo, List<String> userIdList) {
		if (StringUtils.isEmpty(busiOppNo)) {
			throw new ServiceException("商机编号不能为空");
		}
		// 客户号
		if (StringUtils.isEmpty(custNo)) {
			throw new ServiceException("客户号不能为空");
		}
		if (userIdList == null || userIdList.isEmpty()) {
			throw new ServiceException("用户ID列表不能为空");
		}
		for (String userId : userIdList) {
			OcBusiOppCollaborator obj = ocBusiOppCollaboratorMapper.selectByPrimaryKey(busiOppNo, userId);
			if (obj != null) {
				continue;
			}
			OcBusiOppCollaborator ocBusiOppCollaborator = new OcBusiOppCollaborator();
			ocBusiOppCollaborator.setBusiOppNo(busiOppNo);
			ocBusiOppCollaborator.setCustNo(custNo);
			ocBusiOppCollaborator.setCollaborator(userId);
			ocBusiOppCollaborator.setCollaborateDate(DateUtil.getCurTime());
			ocBusiOppCollaboratorMapper.insert(ocBusiOppCollaborator);
		}
	}

	/**
	 * Desc: 商机协作人修改
	 * @param ocBusiOppCollaborator
	 * @author
	 */
	public void uptBusiOppCollaborator(OcBusiOppCollaborator ocBusiOppCollaborator) {
		if (ocBusiOppCollaborator == null) {
			throw new ServiceException("商机协作人信息为空");
		}
		if (StringUtils.isEmpty(ocBusiOppCollaborator.getBusiOppNo())) {
			throw new ServiceException("商机编号不能为空");
		}
		// 客户号
		if (StringUtils.isEmpty(ocBusiOppCollaborator.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}
		if (StringUtils.isEmpty(ocBusiOppCollaborator.getCollaborator())) {
			throw new ServiceException("商机协作人编号不能为空");
		}
		OcBusiOppCollaborator obj = ocBusiOppCollaboratorMapper.selectByPrimaryKey(ocBusiOppCollaborator.getBusiOppNo(), ocBusiOppCollaborator
				.getCollaborator());
		if (obj == null) {
			throw new ServiceException("商机协作人信息不存在");
		}
		ocBusiOppCollaboratorMapper.updateByPrimaryKey(ocBusiOppCollaborator);
	}

	/**
	 * Desc: 商机协作人删除
	 * @param ocBusiOppCollaborator
	 * @author
	 */
	public void delBusiOppCollaborator(OcBusiOppCollaborator ocBusiOppCollaborator) {
		if (ocBusiOppCollaborator == null) {
			throw new ServiceException("商机协作人信息为空");
		}
		if (StringUtils.isEmpty(ocBusiOppCollaborator.getBusiOppNo())) {
			throw new ServiceException("商机编号不能为空");
		}
		if (StringUtils.isEmpty(ocBusiOppCollaborator.getCollaborator())) {
			throw new ServiceException("商机协作人编号不能为空");
		}
		OcBusiOppCollaborator obj = ocBusiOppCollaboratorMapper.selectByPrimaryKey(ocBusiOppCollaborator.getBusiOppNo(), ocBusiOppCollaborator
				.getCollaborator());
		if (obj == null) {
			throw new ServiceException("商机协作人信息不存在");
		}
		ocBusiOppCollaboratorMapper.deleteByPrimaryKey(ocBusiOppCollaborator.getBusiOppNo(), ocBusiOppCollaborator.getCollaborator());
	}

	/**
	 * Desc: 查询商机协作人列表
	 * @param ocBusiOppCollaborator
	 * @return
	 * @author
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<OcBusiOppCollaborator> getBusiOppCollaboratorList(OcBusiOppCollaborator ocBusiOppCollaborator) {
		List<OcBusiOppCollaborator> retlist = new ArrayList<OcBusiOppCollaborator>();

		List<OcBusiOppCollaborator> busiOppList = ocBusiOppCollaboratorMapper.selectBusiOppCollaboratorList(ocBusiOppCollaborator);
		Result result = restUtil.getForObject(ServiceType.MANAGE, "/crm/manage/usermng/usersByEntity",Result.class);
		if (busiOppList != null) {
			if (result != null) {
				List<LinkedHashMap> list = (List<LinkedHashMap>)result.getData();
				if (list != null) {
					for (OcBusiOppCollaborator collr : busiOppList) {
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
						if (StringUtils.isEmpty(ocBusiOppCollaborator.getCollrNam())) {
							retlist.add(collr);
						} else {
							String searchString = collr.getCollrNam()+collr.getCollrTel();
							int index = searchString.indexOf(ocBusiOppCollaborator.getCollrNam());
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
	 * Desc: 查询商机协作人(单条)
	 * @param busiOppNo
	 * @param contractCustNo
	 * @return
	 * @author
	 */
	public OcBusiOppCollaborator getBusiOppCollaboratorOne(String busiOppNo, String contractCustNo) {
		if (StringUtils.isEmpty(busiOppNo)) {
			throw new ServiceException("商机编号不能为空");
		}
		if (StringUtils.isEmpty(contractCustNo)) {
			throw new ServiceException("商机协作人编号不能为空");
		}
		OcBusiOppCollaborator ocBusiOppCollaborator = ocBusiOppCollaboratorMapper.selectByPrimaryKey(busiOppNo, contractCustNo);
		return ocBusiOppCollaborator;
	}
}
