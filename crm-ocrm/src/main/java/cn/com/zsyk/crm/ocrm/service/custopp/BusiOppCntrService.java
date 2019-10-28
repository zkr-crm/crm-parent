package cn.com.zsyk.crm.ocrm.service.custopp;

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
import cn.com.zsyk.crm.ocrm.entity.OcBusiOppCntr;
import cn.com.zsyk.crm.ocrm.mapper.OcBusiOppCntrMapper;

@Service
@Transactional
public class BusiOppCntrService {

	@Autowired
	private OcBusiOppCntrMapper ocBusiOppCntrMapper;
	@Autowired
	private RestUtil restUtil;
	/**
	 * Desc: 商机联系人新增
	 * @param ocBusiOppCntr
	 * @author
	 */
	public void addBusiOppCntr(String custNo, String busiOppNo, List<String> cntrList) {
		if (StringUtils.isEmpty(custNo)) {
			throw new ServiceException("客户号不能为空");
		}
		// 客户号
		if (StringUtils.isEmpty(busiOppNo)) {
			throw new ServiceException("商机编号不能为空");
		}
		if (cntrList == null || cntrList.size() == 0) {
			throw new ServiceException("联系人列表不能为空");
		}
		for (String cntrNo : cntrList) {
			OcBusiOppCntr obj = ocBusiOppCntrMapper.selectByPrimaryKey(busiOppNo, cntrNo);
			if (obj != null) {
				throw new ServiceException("商机联系人信息已经存在");
			}
			OcBusiOppCntr ocBusiOppCntr = new OcBusiOppCntr();
			ocBusiOppCntr.setBusiOppNo(busiOppNo);
			ocBusiOppCntr.setCustNo(custNo);
			ocBusiOppCntr.setContractCustNo(cntrNo);
			ocBusiOppCntr.setRelaDate(DateUtil.getCurTime());
			ocBusiOppCntrMapper.insert(ocBusiOppCntr);
		}
	}

	/**
	 * Desc: 商机联系人修改
	 * @param ocBusiOppCntr
	 * @author
	 */
	public void uptBusiOppCntr(OcBusiOppCntr ocBusiOppCntr) {
		if (ocBusiOppCntr == null) {
			throw new ServiceException("商机联系人信息为空");
		}
		if (StringUtils.isEmpty(ocBusiOppCntr.getBusiOppNo())) {
			throw new ServiceException("商机编号不能为空");
		}
		// 客户号
		if (StringUtils.isEmpty(ocBusiOppCntr.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}
		if (StringUtils.isEmpty(ocBusiOppCntr.getContractCustNo())) {
			throw new ServiceException("商机联系人编号不能为空");
		}
		if (ocBusiOppCntr.getRelaDate() == null) {
			throw new ServiceException("关联日期不能为空");
		}
		OcBusiOppCntr obj = ocBusiOppCntrMapper.selectByPrimaryKey(ocBusiOppCntr.getBusiOppNo(), ocBusiOppCntr
				.getContractCustNo());
		if (obj == null) {
			throw new ServiceException("商机联系人信息不存在");
		}
		ocBusiOppCntrMapper.updateByPrimaryKey(ocBusiOppCntr);
	}

	/**
	 * Desc: 商机联系人删除
	 * @param ocBusiOppCntr
	 * @author
	 */
	public void delBusiOppCntr(OcBusiOppCntr ocBusiOppCntr) {
		if (ocBusiOppCntr == null) {
			throw new ServiceException("商机联系人信息为空");
		}
		if (StringUtils.isEmpty(ocBusiOppCntr.getBusiOppNo())) {
			throw new ServiceException("商机编号不能为空");
		}
		if (StringUtils.isEmpty(ocBusiOppCntr.getContractCustNo())) {
			throw new ServiceException("商机联系人编号不能为空");
		}
		OcBusiOppCntr obj = ocBusiOppCntrMapper.selectByPrimaryKey(ocBusiOppCntr.getBusiOppNo(), ocBusiOppCntr
				.getContractCustNo());
		if (obj == null) {
			throw new ServiceException("商机联系人信息不存在");
		}
		ocBusiOppCntrMapper.deleteByPrimaryKey(ocBusiOppCntr.getBusiOppNo(), ocBusiOppCntr.getContractCustNo());
	}

	/**
	 * Desc: 查询商机联系人列表
	 * @param ocBusiOppCntr
	 * @return
	 * @author
	 */
	public List<OcBusiOppCntr> getBusiOppCntrList(OcBusiOppCntr ocBusiOppCntr) {
		List<OcBusiOppCntr> busiOppList = ocBusiOppCntrMapper.selectBusiOppCntrList(ocBusiOppCntr);
		return busiOppList;
	}

	/**
	 * Desc: 查询商机联系人(单条)
	 * @param busiOppNo
	 * @param custNo
	 * @return
	 * @author
	 */
	public OcBusiOppCntr getBusiOppCntrOne(String busiOppNo, String contractCustNo) {
		if (StringUtils.isEmpty(busiOppNo)) {
			throw new ServiceException("商机编号不能为空");
		}
		if (StringUtils.isEmpty(contractCustNo)) {
			throw new ServiceException("商机联系人编号不能为空");
		}
		OcBusiOppCntr ocBusiOppCntr = ocBusiOppCntrMapper.selectByPrimaryKey(busiOppNo, contractCustNo);
		return ocBusiOppCntr;
	}
}
