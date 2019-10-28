package cn.com.zsyk.crm.ecif.service.customer.portrait;

import java.math.BigDecimal;
import java.util.List;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.DateUtil;
import cn.com.zsyk.crm.common.util.LogUtil;
import cn.com.zsyk.crm.ecif.entity.EcContactAddr;
import cn.com.zsyk.crm.ecif.entity.EcContactWay;
import cn.com.zsyk.crm.ecif.entity.EcCustCareer;
import cn.com.zsyk.crm.ecif.entity.EcCustCert;
import cn.com.zsyk.crm.ecif.entity.EcCustEdu;
import cn.com.zsyk.crm.ecif.entity.EcCustEnt;
import cn.com.zsyk.crm.ecif.entity.EcCustPer;
import cn.com.zsyk.crm.ecif.mapper.EcCustCareerMapper;
import cn.com.zsyk.crm.ecif.mapper.EcCustEduMapper;
import cn.com.zsyk.crm.ecif.mapper.EcCustEntMapper;
import cn.com.zsyk.crm.ecif.mapper.EcCustPerMapper;
import cn.com.zsyk.crm.ecif.service.customer.baseinfo.CustService;
import cn.com.zsyk.crm.ecif.service.customer.integrals.CustIntegralService;
import cn.com.zsyk.crm.generator.EnumType;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
@Service
@Transactional
public class CustBaseInfoService {

	/** logger */
	@SuppressWarnings("unused")
	private final Log logger = LogUtil.getLogger(CustBaseInfoService.class);
	@Autowired
	private EcCustPerMapper ecCustPerMapper;
	@Autowired
	private CustContactService custContactService;
	@Autowired
	private EcCustCareerMapper ecCustCareerMapper;
	@Autowired
	private EcCustEduMapper ecCustEduMapper;
	@Autowired
	private CustCertService custCertService;
	@Autowired
	private EcCustEntMapper ecCustEntMapper;
	@Autowired
	private CustIntegralService custIntegralService;
	@Autowired
	private CustService custService;

	/**
	 * Desc: 查询个人客户基本信息
	 * @param custNo
	 * @return
	 * @author
	 * @throws Exception 
	 */
	public EcCustPer selectPerCustBaseInfoByCustNo(String custNo) throws Exception {
		EcCustPer ecCustPer = ecCustPerMapper.selectByPrimaryKey(custNo);

		if (null == ecCustPer) {
			throw new ServiceException("个人客户信息不存在");
		}

		if (ecCustPer.getCustTyp().equals(EnumType.CustType.ent_formal_cust.getValue()) 
				|| ecCustPer.getCustTyp().equals(EnumType.CustType.ent_latent_cust.getValue())) {
			throw new ServiceException("客户类型只能为个人正式客户和个人潜在客户");
		}

		// 姓名
		String custName = custService.getPerCustName(custNo);
		ecCustPer.setCustName(custName);
		ecCustPer.setAge(DateUtil.getAge(ecCustPer.getBirthDate()));

		// 电话号码
		EcContactWay ecContactWay = custContactService.getContactWayOne(custNo, 0);
		if (null != ecContactWay) {
			ecCustPer.setPhoneNumber(ecContactWay.getPhoneNo1());
			ecCustPer.setOtherTel(ecContactWay.getPhoneNo2());
			ecCustPer.setFax(ecContactWay.getFax());
			ecCustPer.setWechatNo(ecContactWay.getWechatNo());
			ecCustPer.setEmailAddr(ecContactWay.getEmailAddr());
		}

		// 地址
		EcContactAddr homeAddr = custContactService.selectMainContactAddr(custNo);
		if (null != homeAddr) {
			ecCustPer.setContactAddr(homeAddr.getDetAddr());
		}

		// 教育
		EcCustEdu ecCustEdu =  ecCustEduMapper.selectHighestEduByCustNo(custNo);
		if (ecCustEdu != null) {
			ecCustPer.setEduDegree(ecCustEdu.getEduTyp());
		}

		// 身份证号
		EcCustCert obj = new EcCustCert();
		obj.setCustNo(custNo);
		List<EcCustCert> ecCustCert = custCertService.selectCustCertList(obj);
		if (null != ecCustCert && ecCustCert.size() > 0) {
			ecCustPer.setCertType(ecCustCert.get(0).getCertTyp());
			ecCustPer.setCertNo(ecCustCert.get(0).getCertNo());
		}

		// 职业
		EcCustCareer ecCustCareer = ecCustCareerMapper.selectByPrimaryKey(custNo);
		if (null != ecCustCareer) {
			ecCustPer.setTrade(ecCustCareer.getTrade());
			ecCustPer.setPosition(ecCustCareer.getPosition());
		}
		// 积分
		/*BigDecimal sumIntegral = custIntegralService.getSumIntegralByCustNo(custNo);
		ecCustPer.setSumIntegral(sumIntegral);*/
		
		// 头像
        BASE64Encoder encoder = new BASE64Encoder();

		if (ecCustPer.getPerIconSmlBlob() != null) {
			ecCustPer.setPerIconUrl(encoder.encode(ecCustPer.getPerIconSmlBlob()));
		}
		return ecCustPer;
	}

	/**
	 * Desc: 查询企业客户基本信息
	 * @param custNo
	 * @return
	 * @author
	 */
	public EcCustEnt selectEntCustBaseInfoByCustNo(String custNo) {
		EcCustEnt ecCustEnt = ecCustEntMapper.selectByPrimaryKey(custNo);

		if (null == ecCustEnt) {
			throw new ServiceException("企业客户信息不存在");
		}

		if (ecCustEnt.getCustTyp().equals(EnumType.CustType.ent_formal_cust.getValue()) 
				|| ecCustEnt.getCustTyp().equals(EnumType.CustType.ent_latent_cust.getValue())) {
			throw new ServiceException("客户类型只能为企业正式客户和企业潜在客户");
		}
		return ecCustEnt;
	}
}
