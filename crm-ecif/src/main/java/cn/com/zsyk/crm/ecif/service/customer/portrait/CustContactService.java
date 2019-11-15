package cn.com.zsyk.crm.ecif.service.customer.portrait;

import cn.com.zsyk.crm.common.constant.ServiceType;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.BeanUtil;
import cn.com.zsyk.crm.common.util.RestUtil;
import cn.com.zsyk.crm.ecif.bo.cust.PerCustBaseInfo;
import cn.com.zsyk.crm.ecif.entity.EcContactAddr;
import cn.com.zsyk.crm.ecif.entity.EcContactAddress;
import cn.com.zsyk.crm.ecif.entity.EcContactWay;
import cn.com.zsyk.crm.ecif.entity.EcCustPhone;
import cn.com.zsyk.crm.ecif.mapper.EcContactAddrMapper;
import cn.com.zsyk.crm.ecif.mapper.EcContactAddressMapper;
import cn.com.zsyk.crm.ecif.mapper.EcContactWayMapper;
import cn.com.zsyk.crm.ecif.mapper.EcCustPhoneMapper;
import cn.com.zsyk.crm.generator.EnumType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional 
public class CustContactService {
	@Autowired
	private EcContactWayMapper ecContactWayMapper;
	@Autowired
	private EcContactAddrMapper ecContactAddrMapper;
	@Autowired
	private EcContactAddressMapper ecContactAddressMapper;
	@Autowired
	private EcCustPhoneMapper ecCustPhoneMapper;
	@Autowired
	private RestUtil restUtil;
	/**
	 * Desc: 根据客户号获取客户主联系方式
	 * @param custNo
	 * @return
	 * @author 
	 */
	public  EcContactWay getContactWayOne(String custNo, int contactSqn) {
		EcContactWay ecContactWay = ecContactWayMapper.selectByPrimaryKey(custNo, contactSqn);
		return ecContactWay;
	}
	/**
	 * Desc: 根据客户号获取客户主联系方式数组
	 * @param custNo
	 * @return
	 * @author lijiangcheng 2019-08-06
	 */
	public  List<EcContactWay> getContactWayOneList(String custNo) {
		List<EcContactWay> ecContactWay = ecContactWayMapper.getContactWayOneList(custNo);
		return ecContactWay;
	}

	/**
	 * Desc: 根据客户号获取客户联系方式列表
	 * @return
	 * @author 
	 */
	public  List<EcContactWay> getContactWayList(EcContactWay ecContactWay) {
		List<EcContactWay> ecContactWaylst = ecContactWayMapper.selectContactWayList(ecContactWay);
		return ecContactWaylst;
	}

	/**
	 * Desc: 新增客户联系方式信息
	 * @param ecContactWay
	 * @author
	 */
	public void addContactWay(EcContactWay ecContactWay) {
		if (ecContactWay == null) {
			throw new ServiceException("联系方式信息为空");
		}
		// 客户号
		if (StringUtils.isEmpty(ecContactWay.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}
		if(ecContactWay.getContactSqn() == null) {
			EcContactWay obj = ecContactWayMapper.selectByPrimaryKey(ecContactWay.getCustNo(), ecContactWay.getContactSqn());
			if (obj != null) {
				throw new ServiceException("客户联系方式信息已经存在");
			}
		}
		ecContactWay.setContactSqn(this.getNextSqnByCustNo(ecContactWay.getCustNo()));
		ecContactWay.setRecStat(EnumType.RecStat.normal.getValue());
		ecContactWayMapper.insert(ecContactWay);
	}

	/**
	 * Desc: 修改客户联系方式信息
	 * @param ecContactWay
	 * @author
	 */
	public void uptContactWay(EcContactWay ecContactWay) {
		if (ecContactWay == null) {
			throw new ServiceException("联系方式信息为空");
		}
		// 客户号
		if (StringUtils.isEmpty(ecContactWay.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}
		if (ecContactWay.getContactSqn() == null) {
			throw new ServiceException("联系方式序号不能为空");
		}
		EcContactWay obj = ecContactWayMapper.selectByPrimaryKey(ecContactWay.getCustNo(), ecContactWay.getContactSqn());
		if (obj == null) {
			throw new ServiceException("客户联系方式信息不存在");
		}
		BeanUtil.copy(ecContactWay, obj);
		ecContactWayMapper.updateByPrimaryKey(obj);
	}


	/**
	 * Desc: 设置联系方式列表为无效
	 * @param custNo, recStat
	 * @author
	 */
	public void  updateCustNoPhone(String custNo,String recStat){
		ecContactWayMapper.updateByCustNoPhone(custNo,recStat);
		}

	/**
	 * Desc: 删除客户联系方式信息
	 * @param ecContactWay
	 * @author
	 */
	public void delContactWay(EcContactWay ecContactWay) {
		if (ecContactWay == null) {
			throw new ServiceException("联系方式信息为空");
		}
		// 客户号
		if (StringUtils.isEmpty(ecContactWay.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}
		// 联系方式序号
		if (ecContactWay.getContactSqn() == null) {
			throw new ServiceException("联系方式序号不能为空");
		}
		EcContactWay obj = ecContactWayMapper.selectByPrimaryKey(ecContactWay.getCustNo(), ecContactWay.getContactSqn());
		if (obj == null) {
			throw new ServiceException("客户联系方式信息不存在");
		}
		ecContactWayMapper.deleteByPrimaryKey(ecContactWay.getCustNo(), ecContactWay.getContactSqn());
	}

	/**
	 * Desc: 查询联系方式信息
	 * @param custNo
	 * @param contactSqn
	 * @return
	 * @author
	 */
	public EcContactWay getContactWayOne(String custNo, Integer contactSqn) {
		// 客户号
		if (StringUtils.isEmpty(custNo)) {
			throw new ServiceException("客户号不能为空");
		}
		// 联系方式序号
		if (contactSqn == null) {
			throw new ServiceException("联系方式序号不能为空");
		}
		EcContactWay retObj  = null;
		if (contactSqn == -1) {// contactSqn min
			retObj = ecContactWayMapper.selectByCustNoMinSqn(custNo);
		} else {
			retObj = ecContactWayMapper.selectByPrimaryKey(custNo, contactSqn);
		}
		if (retObj == null) {
			throw new ServiceException("客户联系方式信息不存在");
		}
		return retObj;
	}

	// ---------------------------------

	/**
	 * Desc: 根据客户号、客户类型获取客户联系地址
	 * @param custNo 客户号
	 * @param addrTyp 地址类型
	 * @return
	 * @author 
	 */
	public  EcContactAddr getContactAddrOne(String custNo, String addrTyp) {
		EcContactAddr ecContactAddr = ecContactAddrMapper.selectByPrimaryKey(custNo, addrTyp);
		return ecContactAddr;
	}

	/**
	 * Desc: 根据客户号获取客户联系地址列表
	 * @return
	 * @author 
	 */
	public  List<EcContactAddr> getContactAddrList(EcContactAddr ecContactAddr) {
		List<EcContactAddr> ecContactAddrLst = ecContactAddrMapper.selectContactAddrList(ecContactAddr);
		return ecContactAddrLst;
	}

	/**
	 * Desc: 根据客户号、地址类型获取客户联系地址
	 * @param custNo
	 * @param addrTyp
	 * @return
	 * @author
	 */
	public EcContactAddr selectContactAddrByPk(String custNo, String addrTyp) {
		EcContactAddr ecContactAddr = ecContactAddrMapper.selectByPrimaryKey(custNo, addrTyp);
		return ecContactAddr;
	}

	/**
	 * Desc: 根据客户号、修改地址数据状态 置为无效
	 * @param custNo
	 * @param addrTyp
	 * @return
	 * @author
	 */
	public void updateByCustNo(String custNo, String addrTyp) {
//		List<EcContactAddr> addrList =ecContactAddrMapper.selectByPrimaryKeyList(custNo);
//		for(EcContactAddr addr :addrList){
//			EcContactAddr addrNew=addr;
//			addrNew.setRecStat(addrTyp);
//			BeanUtil.copy(addrNew, addr);
//			ecContactAddrMapper.updateByPrimaryKey(addr);
//
//		}
		ecContactAddrMapper.updateByCustNo(custNo,addrTyp);
	}

	/**
	 * Desc: 根据客户号获取客户联系地址数组
	 * @param custNo
	 * @return
	 * @author
	 */
	public List<EcContactAddr> selectContactAddrByPkList(String custNo) {
		List<EcContactAddr> ecContactAddr = ecContactAddrMapper.selectContactAddrByPkList(custNo);
		return ecContactAddr;
	}
	/**
	 * Desc: 根据客户号、地址类型获取客户联系地址
	 * @param custNo
	 * @param id
	 * @return
	 * @author
	 */
	public EcContactAddress selectContactAddressByPk(String custNo, Integer id) {
		EcContactAddress ecContactAddress = ecContactAddressMapper.selectByPrimaryKeyAndCustNo(custNo, id);
		return ecContactAddress;
	}

	/**
	 * Desc: 主要联系地址
	 * @param custNo
	 * @return
	 * @author
	 */
	public EcContactAddr selectMainContactAddr(String custNo) {
		return ecContactAddrMapper.selectMainContactAddr(custNo);
	}
	/**
	 * Desc: 获取下一个Sqn
	 * @param custNo
	 * @return
	 * @author
	 */
	public int getNextSqnByCustNo(String custNo) {
		int nextSqn = ecContactWayMapper.getNextSqnByCustNo(custNo);
		return nextSqn;
	}
	/**
	 * Desc: 获取下一个Sqn
	 * @param custNo
	 * @return
	 * @author lijiangcheng 2019-08-06
	 */
	public String getNextSqnByCustNo1(String custNo) {
		String nextSqn = ecContactAddrMapper.getNextSqnByCustNo(custNo);
		return nextSqn;
	}

	/**
	 * Desc: 保存或更新联系方式信息
	 * @param perCustBaseInfo
	 * @author
	 */
	public void saveOrUpdateContactAddr(PerCustBaseInfo perCustBaseInfo) {

		// 客户号不能为空00
		if (StringUtils.isEmpty(perCustBaseInfo.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}

		// 手机号码不能为空
		/*if (StringUtils.isEmpty(perCustBaseInfo.getPhoneNumber())
				&&!EnumType.MergeSplitAction.merge_approv_pass.desc.equals(perCustBaseInfo.getMergeMark())) {
			throw new ServiceException("手机号码不能为空");
		}*/
			this.updateCustNoPhone(perCustBaseInfo.getCustNo(),EnumType.RecStat.delete.getValue());
		List<EcCustPhone> phonesList = perCustBaseInfo.getPhoneList();
		if(phonesList!=null && phonesList.size()!=0){
			for (EcCustPhone ecCustPhone : phonesList) {
				EcContactWay ecContactWay=null;
				if(!EnumType.MergeSplitAction.merge_approv_pass.desc.equals(perCustBaseInfo.getMergeMark())) {
					ecContactWay = this.getContactWayOne(perCustBaseInfo.getCustNo(), ecCustPhone.getId().intValue());
				}
				if (null != ecContactWay) {
					if (ecContactWay.getRecStat().equals(EnumType.RecStat.delete.getValue())) {
					}
					ecContactWay.setContactNam(perCustBaseInfo.getCustName());
					// 电话号码 phoneNumber;
					ecContactWay.setPhoneNo1(perCustBaseInfo.getPhoneNumber());
		//			// 其他手机 otherTel;
					ecContactWay.setPhoneNo2(ecCustPhone.getPhoneNo());
					// 住宅电话 homeTel;
					ecContactWay.setTelephone1(perCustBaseInfo.getHomeTel());
					// 公司电话 officeTel;
					ecContactWay.setTelephone2(perCustBaseInfo.getOfficeTel());
					// 传真 fax;
					ecContactWay.setFax(perCustBaseInfo.getFax());
					// 微信 wechatNo;
					ecContactWay.setWechatNo(perCustBaseInfo.getWechatNo());
					// 电子邮箱 emailAddr;
					ecContactWay.setEmailAddr(perCustBaseInfo.getEmailAddr());
					// QQ qq;
					ecContactWay.setQq(perCustBaseInfo.getQq());
					// 微博 microblog;
					ecContactWay.setMicroblog(perCustBaseInfo.getMicroblog());
					// 支付宝 alipay;
					ecContactWay.setAlipay(perCustBaseInfo.getAlipay());
					//设置默认值
					ecContactWay.setWhtDefaultWay(ecCustPhone.getIsDeault());
					//设置为可用状态
					ecContactWay.setRecStat(EnumType.RecStat.normal.getValue());
					if(ecCustPhone.getCustSource()==null || "".equals(ecCustPhone.getCustSource())){
						ecContactWay.setCustSource(EnumType.DataSource.ecif.value);
					}else{
						ecContactWay.setCustSource(ecCustPhone.getCustSource());
					}
					uptContactWay(ecContactWay);
				} else {
					EcContactWay contactWayObj = new EcContactWay();
					contactWayObj.setCustNo(perCustBaseInfo.getCustNo());
					contactWayObj.setContactSqn(getNextSqnByCustNo(perCustBaseInfo.getCustNo()));
					contactWayObj.setContactNam(perCustBaseInfo.getCustName());
					// 电话号码 phoneNumber;
					contactWayObj.setPhoneNo1(perCustBaseInfo.getPhoneNumber());
					// 其他手机 otherTel;
					contactWayObj.setPhoneNo2(ecCustPhone.getPhoneNo());

					// 住宅电话 homeTel;
					contactWayObj.setTelephone1(perCustBaseInfo.getHomeTel());
					// 公司电话 officeTel;
					contactWayObj.setTelephone2(perCustBaseInfo.getOfficeTel());
					// 传真 fax;
					contactWayObj.setFax(perCustBaseInfo.getFax());
					// 微信 wechatNo;
					contactWayObj.setWechatNo(perCustBaseInfo.getWechatNo());
					// 电子邮箱 emailAddr;
					contactWayObj.setEmailAddr(perCustBaseInfo.getEmailAddr());
					// QQ qq;
					contactWayObj.setQq(perCustBaseInfo.getQq());
					// 微博 microblog;
					contactWayObj.setMicroblog(perCustBaseInfo.getMicroblog());
					// 支付宝 alipay;
					contactWayObj.setAlipay(perCustBaseInfo.getAlipay());
					contactWayObj.setWhtDefaultWay(ecCustPhone.getIsDeault());
					//判断是否为ecif系统的标识
					if(ecCustPhone.getCustSource()==null || "".equals(ecCustPhone.getCustSource())){
						contactWayObj.setCustSource(EnumType.DataSource.ecif.value);
					}else{
						contactWayObj.setCustSource(ecCustPhone.getCustSource());
					}

					addContactWay(contactWayObj);
				}
			}
		}else{
			EcContactWay contactWayObj = new EcContactWay();
			contactWayObj.setCustNo(perCustBaseInfo.getCustNo());
			contactWayObj.setContactSqn(getNextSqnByCustNo(perCustBaseInfo.getCustNo()));
			contactWayObj.setContactNam(perCustBaseInfo.getCustName());
			// 电话号码 phoneNumber;
			contactWayObj.setPhoneNo1(perCustBaseInfo.getPhoneNumber());
			// 其他手机 otherTel;
			contactWayObj.setPhoneNo2("");
			// 住宅电话 homeTel;
			contactWayObj.setTelephone1(perCustBaseInfo.getHomeTel());
			// 公司电话 officeTel;
			contactWayObj.setTelephone2(perCustBaseInfo.getOfficeTel());
			// 传真 fax;
			contactWayObj.setFax(perCustBaseInfo.getFax());
			// 微信 wechatNo;
			contactWayObj.setWechatNo(perCustBaseInfo.getWechatNo());
			// 电子邮箱 emailAddr;
			contactWayObj.setEmailAddr(perCustBaseInfo.getEmailAddr());
			// QQ qq;
			contactWayObj.setQq(perCustBaseInfo.getQq());
			// 微博 microblog;
			contactWayObj.setMicroblog(perCustBaseInfo.getMicroblog());
			// 支付宝 alipay;
			contactWayObj.setAlipay(perCustBaseInfo.getAlipay());
			contactWayObj.setWhtDefaultWay(EnumType.YesNoFlg.yes.getValue());
			contactWayObj.setCustSource(perCustBaseInfo.getCustSource());
			addContactWay(contactWayObj);
		}
	}

	/**
	 * Desc: 获取详细地址
	 * @return
	 * @author
	 */
	@SuppressWarnings({ "rawtypes" })
	public String getDetailAddr(String provinceCode, String cityCode, String countyCode , String streetAddr) {
		StringBuffer detailAddr = new StringBuffer();
		Result addrDetail = restUtil.getForObject(ServiceType.MANAGE,
				"/crm/manage/cm/getAddrName?provinceCode={provinceCode}&cityCode={cityCode}&countyCode={countyCode}",
				Result.class, provinceCode, cityCode, countyCode);
		if (addrDetail != null) {
			String addrString = (String)addrDetail.getData();
			if (StringUtils.isNotEmpty(addrString)) {
				detailAddr.append(addrString);
			}
		}
		if (StringUtils.isNotEmpty(streetAddr)) {
			detailAddr.append(streetAddr);
		}
		return detailAddr.toString();
	}
	/**
	 * Desc: 保存或更新居住地址
	 * @param perCustBaseInfo
	 * @author lijiangcheng 2019-08-06
	 */
	public void saveOrUpdateHomeAddr(PerCustBaseInfo perCustBaseInfo) {
		// 客户号不能为空
		if (StringUtils.isEmpty(perCustBaseInfo.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}
		//把所有地址信息置为无效 add lijiangcheng 2019-08-27
		this.updateByCustNo(perCustBaseInfo.getCustNo(),EnumType.RecStat.delete.getValue());
		List<EcContactAddress> addrList = perCustBaseInfo.getAddrList();
		if (addrList!=null&&!addrList.isEmpty()) {
			for (EcContactAddress ecContactAddress : addrList) {
						EcContactAddr homeAddr = this.selectContactAddrByPk(perCustBaseInfo.getCustNo(), ecContactAddress.getId().toString());
						if (homeAddr != null) {
							if (homeAddr.getRecStat().equals(EnumType.RecStat.delete.getValue())) {
							}
					// 居住地址类型 liveAddrTyp;
					homeAddr.setAddrTyp(ecContactAddress.getId().toString());
					// 居住省 liveProvince;
					homeAddr.setProvCd(ecContactAddress.getProvCd());
					// 居住市 liveCity;
					homeAddr.setCityCd(ecContactAddress.getCityCd());
					// 居住区县 liveCounty;
					homeAddr.setCountyCd(ecContactAddress.getCountyCd());
					// 居住街道 liveStreet;
					homeAddr.setStreetAddr(ecContactAddress.getStreetAddr());
					// 居住邮编 livePostcode;
					homeAddr.setPostcode(ecContactAddress.getPostcode());
					//  详细地址
					homeAddr.setDetAddr(this.getDetailAddr(ecContactAddress.getProvCd(), ecContactAddress.getCityCd(), ecContactAddress.getCountyCd(), ecContactAddress.getStreetAddr()));
					//设为默认地址
					homeAddr.setWhtDefaultAddr(ecContactAddress.getWhtDefaultAddr());
					//设置地址为可用
					homeAddr.setRecStat(EnumType.RecStat.normal.getValue());
					this.uptContactAddr(homeAddr);
				} else {
					EcContactAddr homeAddrObj = new EcContactAddr();
					homeAddrObj.setCustNo(perCustBaseInfo.getCustNo());
					// 居住地址类型 liveAddrTyp;
					homeAddrObj.setAddrTyp(getNextSqnByCustNo1(perCustBaseInfo.getCustNo()));
					// 居住省 liveProvince;
					homeAddrObj.setProvCd(ecContactAddress.getProvCd());
					// 居住市 liveCity;
					homeAddrObj.setCityCd(ecContactAddress.getCityCd());
					// 居住区县 liveCounty;
					homeAddrObj.setCountyCd(ecContactAddress.getCountyCd());
					// 居住街道 liveStreet;
					homeAddrObj.setStreetAddr(ecContactAddress.getStreetAddr());
					// 居住邮编 livePostcode;
					homeAddrObj.setPostcode(ecContactAddress.getPostcode());
					//  详细地址
					homeAddrObj.setDetAddr(this.getDetailAddr(ecContactAddress.getProvCd(), ecContactAddress.getCityCd(), ecContactAddress.getCountyCd(), ecContactAddress.getStreetAddr()));
					homeAddrObj.setWhtDefaultAddr(ecContactAddress.getWhtDefaultAddr());
					homeAddrObj.setCountryCd(EnumType.Nationality.CHINA.getValue());
					//判断是否为ecif系统的标识
					if(!EnumType.MergeSplitAction.merge_approv_pass.desc.equals(perCustBaseInfo.getMergeMark())) {
						homeAddrObj.setCustSource("01");
					}else{
						homeAddrObj.setCustSource(perCustBaseInfo.getCustSource());
					}
					this.addContactAddr(homeAddrObj);
				}
			}
		}
	}

	/**
	 * Desc: 保存或更新公司地址
	 * @param perCustBaseInfo
	 * @author
	 */
	public void saveOrUpdateUnitAddr(PerCustBaseInfo perCustBaseInfo) {
		// 客户号不能为空
		if (StringUtils.isEmpty(perCustBaseInfo.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}
		if (perCustBaseInfo.getUnitAddrTyp() != null || perCustBaseInfo.getUnitProvince() != null
				|| perCustBaseInfo.getUnitCity() != null || perCustBaseInfo.getUnitCounty() != null
				|| perCustBaseInfo.getUnitStreet() != null || perCustBaseInfo.getUnitPostcode() != null) {
			EcContactAddr companyAddr = this.selectContactAddrByPk(perCustBaseInfo.getCustNo(), EnumType.AddrTyp.company_addr.getValue());
			if (companyAddr != null) {
				if (companyAddr.getRecStat().equals(EnumType.RecStat.delete.getValue())) {
					companyAddr.setWhtDefaultAddr(EnumType.YesNoFlg.no.getValue());
					companyAddr.setRecStat(EnumType.RecStat.normal.getValue());
				}
				// 单位地址类型 unitAddrTyp;
				companyAddr.setAddrTyp(EnumType.AddrTyp.company_addr.getValue());
				// 单位省 unitProvince;
				companyAddr.setProvCd(perCustBaseInfo.getUnitProvince());
				// 单位市  unitCity;
				companyAddr.setCityCd(perCustBaseInfo.getUnitCity());
				// 单位区县  unitCounty;
				companyAddr.setCountyCd(perCustBaseInfo.getUnitCounty());
				// 单位街道  unitStreet;
				companyAddr.setStreetAddr(perCustBaseInfo.getUnitStreet());
				// 单位邮编  unitPostcode;
				companyAddr.setPostcode(perCustBaseInfo.getUnitPostcode());
				//  详细地址
				companyAddr.setDetAddr(this.getDetailAddr(perCustBaseInfo.getUnitProvince(),perCustBaseInfo.getUnitCity(), perCustBaseInfo.getUnitCounty(), perCustBaseInfo.getUnitStreet()));
				this.uptContactAddr(companyAddr);
			} else {
				EcContactAddr companyAddrObj = new EcContactAddr();
				companyAddrObj.setCustNo(perCustBaseInfo.getCustNo());
				// 单位地址类型 unitAddrTyp;
				companyAddrObj.setAddrTyp(perCustBaseInfo.getUnitAddrTyp() == null ? EnumType.AddrTyp.company_addr.getValue() : perCustBaseInfo.getUnitAddrTyp());
				// 单位省 unitProvince;
				companyAddrObj.setProvCd(perCustBaseInfo.getUnitProvince());
				// 单位市  unitCity;
				companyAddrObj.setCityCd(perCustBaseInfo.getUnitCity());
				// 单位区县  unitCounty;
				companyAddrObj.setCountyCd(perCustBaseInfo.getUnitCounty());
				// 单位街道  unitStreet;
				companyAddrObj.setStreetAddr(perCustBaseInfo.getUnitStreet());
				// 单位邮编  unitPostcode;
				companyAddrObj.setPostcode(perCustBaseInfo.getUnitPostcode());
				companyAddrObj.setWhtDefaultAddr(EnumType.YesNoFlg.no.getValue());
				//  详细地址
				companyAddrObj.setDetAddr(this.getDetailAddr(perCustBaseInfo.getUnitProvince(),perCustBaseInfo.getUnitCity(), perCustBaseInfo.getUnitCounty(), perCustBaseInfo.getUnitStreet()));
				companyAddrObj.setCountryCd(EnumType.Nationality.CHINA.getValue());
				this.addContactAddr(companyAddrObj);
			}
		}
	}
	/**
	 * Desc: 保存或更新批量地址
	 * @param perCustBaseInfo
	 * @author lijiangcheng
	 * @date 2019-08-01
	 */
	public void saveOrUpdateAddress(PerCustBaseInfo perCustBaseInfo) {
		// 客户号不能为空
		if (StringUtils.isEmpty(perCustBaseInfo.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}
		List<EcContactAddress> addrList = perCustBaseInfo.getAddrList();
		List<EcContactAddress> addrListNew=new ArrayList<>();
		if (addrList.size() != 0) {
			for (EcContactAddress ecContactAddress : addrList) {
				if(ecContactAddress.getId()!=null&&ecContactAddress.getId()!=0){
						EcContactAddress homeAddress = this.selectContactAddressByPk(ecContactAddress.getCustNo(), ecContactAddress.getId());
				if (homeAddress != null) {
					if (homeAddress.getRecStat().equals(EnumType.RecStat.delete.getValue())) {
						homeAddress.setWhtDefaultAddr(EnumType.YesNoFlg.no.getValue());
						homeAddress.setRecStat(EnumType.RecStat.normal.getValue());
					}
					// 居住省 liveProvince;
					homeAddress.setProvCd(ecContactAddress.getProvCd());
					// 居住市 liveCity;
					homeAddress.setCityCd(ecContactAddress.getCityCd());
					// 居住区县 liveCounty;
					homeAddress.setCountyCd(ecContactAddress.getCountyCd());
					// 居住街道 liveStreet;
					homeAddress.setStreetAddr(ecContactAddress.getStreetAddr());
					// 居住邮编 livePostcode;
					homeAddress.setPostcode(ecContactAddress.getPostcode());
					//  详细地址
					homeAddress.setDetAddr(this.getDetailAddr(ecContactAddress.getProvCd(), ecContactAddress.getCityCd(), ecContactAddress.getCountyCd(), ecContactAddress.getStreetAddr()));
				this.uptContactAddress(homeAddress);

				}
			} else {
//				List<EcContactAddress> homeAddrObj = new ArrayList<EcContactAddress>();
//				List<EcContactAddress> addrList=perCustBaseInfo.getAddrList();
//				for ( EcContactAddress ecContactAddress:addrList) {
					ecContactAddress.setDetAddr(this.getDetailAddr(ecContactAddress.getProvCd(), ecContactAddress.getCityCd(), ecContactAddress.getCountyCd(), ecContactAddress.getStreetAddr()));
					ecContactAddress.setWhtDefaultAddr(EnumType.YesNoFlg.no.getValue());
					ecContactAddress.setCountryCd(EnumType.Nationality.CHINA.getValue());
					ecContactAddress.setCustNo(perCustBaseInfo.getCustNo());
					addrListNew.add(ecContactAddress);
//			}
//				home>AddrObj.setCustNo(perCustBaseInfo.getCustNo());
//				// 居住地址类型 liveAddrTyp;
//				homeAddrObj.setAddrTyp(perCustBaseInfo.getLiveAddrTyp() == null ? EnumType.AddrTyp.home_addr.getValue() : perCustBaseInfo.getLiveAddrTyp());
					// 居住省 liveProvince;
//				homeAddrObj.setProvCd(perCustBaseInfo.getLiveProvince());
//				// 居住市 liveCity;
//				homeAddrObj.setCityCd(perCustBaseInfo.getLiveCity());
//				// 居住区县 liveCounty;
//				homeAddrObj.setCountyCd(perCustBaseInfo.getLiveCounty());
//				// 居住街道 liveStreet;
//				homeAddrObj.setStreetAddr(perCustBaseInfo.getLiveStreet());
//				// 居住邮编 livePostcode;
//				homeAddrObj.setPostcode(perCustBaseInfo.getLivePostcode());
//				//  详细地址
//				homeAddrObj.setDetAddr(this.getDetailAddr(perCustBaseInfo.getLiveProvince(),perCustBaseInfo.getLiveCity(), perCustBaseInfo.getLiveCounty(), perCustBaseInfo.getLiveStreet()));
//				homeAddrObj.setWhtDefaultAddr(EnumType.YesNoFlg.no.getValue());
//				homeAddrObj.setCountryCd(EnumType.Nationality.CHN.getValue());
//				this.addContactAddress(homeAddrObj);
//			}
					this.addContactAddress(ecContactAddress);
				}
			}
		}
	}
	/**
	 * Desc: 新增客户联系地址信息
	 * @param ecContactAddr
	 * @author
	 */
	public void addContactAddr(EcContactAddr ecContactAddr) {
		if (ecContactAddr == null) {
			throw new ServiceException("联系地址信息为空");
		}
		// 客户号
		if (StringUtils.isEmpty(ecContactAddr.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}
		if (ecContactAddr.getAddrTyp() == null) {
			throw new ServiceException("联系地址类型不能为空");
		}
		EcContactAddr obj = ecContactAddrMapper.selectByPrimaryKey(ecContactAddr.getCustNo(), ecContactAddr.getAddrTyp());
		if (obj != null) {
			throw new ServiceException("客户联系地址信息已经存在");
		}
		ecContactAddr.setRecStat(EnumType.RecStat.normal.getValue());
		ecContactAddr.setAddrTyp(getNextSqnByCustNo1(ecContactAddr.getCustNo()));
		ecContactAddrMapper.insert(ecContactAddr);
	}

	/**
	 * Desc: 修改客户联系地址信息
	 * @param ecContactAddr
	 * @author
	 */
	public void uptContactAddr(EcContactAddr ecContactAddr) {
		if (ecContactAddr == null) {
			throw new ServiceException("联系地址信息为空");
		}
		// 客户号
		if (StringUtils.isEmpty(ecContactAddr.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}
		if (ecContactAddr.getAddrTyp() == null) {
			throw new ServiceException("联系地址序号不能为空");
		}
		EcContactAddr obj = ecContactAddrMapper.selectByPrimaryKey(ecContactAddr.getCustNo(), ecContactAddr.getAddrTyp());
		if (obj == null) {
			throw new ServiceException("客户联系地址信息不存在");
		}
		BeanUtil.copy(ecContactAddr, obj);
		ecContactAddrMapper.updateByPrimaryKey(obj);
	}

	/**
	 * Desc: 删除客户联系地址信息
	 * @param ecContactAddr
	 * @author
	 */
	public void delContactAddress(EcContactAddress ecContactAddr) {
		if (ecContactAddr == null) {
			throw new ServiceException("联系地址信息为空");
		}
		// 客户号
		if (StringUtils.isEmpty(ecContactAddr.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}
		// 联系地址类型
		if (ecContactAddr.getAddrTyp() == null) {
			throw new ServiceException("联系地址序号不能为空");
		}
		EcContactAddr obj = ecContactAddrMapper.selectByPrimaryKey(ecContactAddr.getCustNo(), ecContactAddr.getAddrTyp());
		if (obj == null) {
			throw new ServiceException("客户联系地址信息不存在");
		}
		ecContactAddrMapper.deleteByPrimaryKey(ecContactAddr.getCustNo(), ecContactAddr.getAddrTyp());
	}


	/**
	 * Desc: 新增客户联系地址信息
	 * @param ecContactAddr
	 * @author lijiangcheng
	 * @date 2019-08-01
	 */
	public void addContactAddress(EcContactAddress ecContactAddr) {
		if (ecContactAddr == null) {
			throw new ServiceException("联系地址信息为空");
		}
//		for ( EcContactAddress ecContactAddress:ecContactAddr) {
			// 客户号
			if (StringUtils.isEmpty(ecContactAddr.getCustNo())) {
				throw new ServiceException("客户号不能为空");
			}
			ecContactAddr.setRecStat(EnumType.RecStat.normal.getValue());
//		}
//		if (ecContactAddr.getAddrTyp() == null) {
//			throw new ServiceException("联系地址类型不能为空");
//		}
//		EcContactAddr obj = ecContactAddrMapper.selectByPrimaryKey(ecContactAddr.getCustNo(), ecContactAddr.getAddrTyp());
//		if (obj != null) {
//			throw new ServiceException("客户联系地址信息已经存在");
//		}
//		ecContactAddr.setRecStat(EnumType.RecStat.normal.getValue());
		ecContactAddressMapper.insert(ecContactAddr);
	}

	/**
	 * Desc: 修改客户联系地址信息
	 * @param ecContactAddr
	 * @author lijiangcheng
	 * @date 2019-08-01
	 */
	public void uptContactAddress(EcContactAddress ecContactAddr) {
		if (ecContactAddr == null) {
			throw new ServiceException("联系地址信息为空");
		}
		// 客户号
		if (StringUtils.isEmpty(ecContactAddr.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}
		if (ecContactAddr.getAddrTyp() == null) {
			throw new ServiceException("联系地址序号不能为空");
		}
		EcContactAddress obj = ecContactAddressMapper.selectByPrimaryKeyAndCustNo(ecContactAddr.getCustNo(), ecContactAddr.getId());
		if (obj == null) {
			throw new ServiceException("客户联系地址信息不存在");
		}
		BeanUtil.copy(ecContactAddr, obj);
		ecContactAddressMapper.updateByPrimaryKeyAndCustNo(obj);
	}

	/**
	 * Desc: 删除客户联系地址信息
	 * @param ecContactAddr
	 * @author lijiangcheng
	 * @date 2019-08-01
	 */
	public void delContactAddress(EcContactAddr ecContactAddr) {
		if (ecContactAddr == null) {
			throw new ServiceException("联系地址信息为空");
		}
		// 客户号
		if (StringUtils.isEmpty(ecContactAddr.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}
		// 联系地址类型
		if (ecContactAddr.getAddrTyp() == null) {
			throw new ServiceException("联系地址序号不能为空");
		}
		EcContactAddr obj = ecContactAddrMapper.selectByPrimaryKey(ecContactAddr.getCustNo(), ecContactAddr.getAddrTyp());
		if (obj == null) {
			throw new ServiceException("客户联系地址信息不存在");
		}
		ecContactAddrMapper.deleteByPrimaryKey(ecContactAddr.getCustNo(), ecContactAddr.getAddrTyp());
	}

}
