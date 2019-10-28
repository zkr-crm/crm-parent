package cn.com.zsyk.crm.ocrm.service.custcnt;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.com.zsyk.crm.common.dao.AbstractDao;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.IdGenerator;
import cn.com.zsyk.crm.ocrm.entity.OcCustContract;
import cn.com.zsyk.crm.ocrm.mapper.OcCustContractMapper;

@Service
@Transactional
public class CustCntService {
	@Autowired
	private AbstractDao daoUtil;
	@Autowired
	private OcCustContractMapper mapper;

	/**
	 * 获得一条联系人的方法
	 * 
	 * @param contractNo
	 *            联系人ID
	 * @return 联系人
	 */
	public OcCustContract getOneCustContract(String contractNo) {

		// 联系人ID非空判断
		OcCustContract custContract = mapper.selectByPrimaryKey(contractNo);

		return custContract;
	}


	/**
	 * 获取所有联系人的方法
	 * 
	 * @return 所有联系人的列表
	 */
	public List<OcCustContract> getAllCustContract() {

		List<OcCustContract> lstcustContract = mapper.selectAll();

		return lstcustContract;
	}


	/**
	 *根据联系人姓名及电话模糊查询
	 * 
	 * @return 联系人的列表
	 */
	public List<OcCustContract> getCustContractByName(OcCustContract custContrac) {

		List<OcCustContract> lstUser = daoUtil
				.selectList("cn.com.zsyk.crm.ocrm.mapper.OcCustContractMapper.selectByName", custContrac);

		return lstUser;
	}


	/**
	 * 新增一条联系人
	 * 
	 * @param contractNo
	 *            联系人ID
	 * @param custContractname
	 *            联系人名称
	 * @return 新增成功的记录条数
	 */
	public int addCustContract(OcCustContract custContract) {

		if (custContract == null) {
			throw new ServiceException("输入信息不能为空！");
		}

		// 存在判断
		List<OcCustContract> lstcustContract = this.getAllCustContract();
		custContract.setContractNo(IdGenerator.getUUID());
		int addCount = mapper.insert(custContract);

		return addCount;
	}

	/**
	 * 修改某条联系人的方法
	 * 
	 * @param custContract
	 *            需要修改的联系人
	 * @return 修改成功的条目数，0为失败
	 */
	public int modCustContract(OcCustContract custContract) {

		if (custContract == null) {
			throw new ServiceException("输入信息不能为空！");
		}

		// 存在判断
		OcCustContract extTest = this.getOneCustContract(custContract.getContractNo());
		if (extTest == null) {
			throw new ServiceException(
					"联系人不存在：联系人ID[" + custContract.getContractNo() + "]，联系人名称[" + custContract.getContractName() + "]");
		}
		if (custContract.getContractName() != null && custContract.getContractName() != "") {
			extTest.setContractName(custContract.getContractName());
		}
		if (custContract.getTelephone() != null && custContract.getTelephone() != "") {
			extTest.setTelephone(custContract.getTelephone());
		}
		if (custContract.getBlnCustNo() != null && custContract.getBlnCustNo() != "") {
			extTest.setBlnCustNo(custContract.getBlnCustNo());
		}
		if (custContract.getBlnCustType() != null && custContract.getBlnCustType() != "") {
			extTest.setBlnCustType(custContract.getBlnCustType());
		}
		if (custContract.getSex() != null && custContract.getSex() != "") {
			extTest.setSex(custContract.getSex());
		}
		if (custContract.getBirthDate() != null) {
			extTest.setBirthDate(custContract.getBirthDate());
		}
		if (custContract.getEduDegree() != null && custContract.getEduDegree() != "") {
			extTest.setEduDegree(custContract.getEduDegree());
		}
		if (custContract.getOccupationCode() != null && custContract.getOccupationCode() != "") {
			extTest.setOccupationCode(custContract.getOccupationCode());
		}
		if (custContract.getBlnBusiness() != null && custContract.getBlnBusiness() != "") {
			extTest.setBlnBusiness(custContract.getBlnBusiness());
		}
		if (custContract.getCertNo() != null && custContract.getCertNo() != "") {
			extTest.setCertNo(custContract.getCertNo());
		}
		if (custContract.getCertEffDate() != null) {
			extTest.setCertEffDate(custContract.getCertEffDate());
		}
		if (custContract.getPassportNo() != null && custContract.getPassportNo() != "") {
			extTest.setPassportNo(custContract.getPassportNo());
		}
		if (custContract.getPassportEffDate() != null) {
			extTest.setPassportEffDate(custContract.getPassportEffDate());
		}
		if (custContract.getAnnualIncome() != null) {
			extTest.setAnnualIncome(custContract.getAnnualIncome());
		}
		if (custContract.getBlnProvince() != null && custContract.getBlnProvince() != "") {
			extTest.setBlnProvince(custContract.getBlnProvince());
		}
		if (custContract.getBlnCity() != null && custContract.getBlnCity() != "") {
			extTest.setBlnCity(custContract.getBlnCity());
		}
		if (custContract.getBlnCounty() != null && custContract.getBlnCounty() != "") {
			extTest.setBlnCounty(custContract.getBlnCounty());
		}
		if (custContract.getStreetAddr() != null && custContract.getStreetAddr() != "") {
			extTest.setStreetAddr(custContract.getStreetAddr());
		}
		if (custContract.getPostcode()!= null && custContract.getPostcode() != "") {
			extTest.setPostcode(custContract.getPostcode());
		}
		if (custContract.getWechatNo() != null && custContract.getWechatNo() != "") {
			extTest.setWechatNo(custContract.getWechatNo());
		}
		if (custContract.getQq() != null && custContract.getQq() != "") {
			extTest.setQq(custContract.getQq());
		}
		if (custContract.getEmailAddr() != null && custContract.getEmailAddr() != "") {
			extTest.setEmailAddr(custContract.getEmailAddr());
		}
		if (custContract.getMobile() != null && custContract.getMobile() != "") {
			extTest.setMobile(custContract.getMobile());
		}
		if (custContract.getMicroblog() != null && custContract.getMicroblog() != "") {
			extTest.setMicroblog(custContract.getMicroblog());
		}
		if (custContract.getAlipay()!= null && custContract.getAlipay() != "") {
			extTest.setAlipay(custContract.getAlipay());
		}
		if (custContract.getOtherPhone() != null && custContract.getOtherPhone() != "") {
			extTest.setOtherPhone(custContract.getOtherPhone());
		}

		int modCount = mapper.updateByPrimaryKey(extTest);

		return modCount;
	}



	/**
	 * 根据主键物理删除某联系人的方法
	 * 
	 * @param contractNo
	 *            联系人ID
	 * @return
	 */
	public int delCustContract(String contractNo) {

		// 联系人ID非空判断
		

		// 存在判断
		OcCustContract extTest = this.getOneCustContract(contractNo);
		if (extTest == null) {
			throw new ServiceException("联系人不存在：联系人ID[" + contractNo + "]");
		}

		int delCount = mapper.deleteByPrimaryKey(contractNo);

		return delCount;
	}

}
