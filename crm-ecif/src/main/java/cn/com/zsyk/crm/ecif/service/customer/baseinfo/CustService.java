package cn.com.zsyk.crm.ecif.service.customer.baseinfo;

import cn.com.zsyk.crm.common.constant.ServiceType;
import cn.com.zsyk.crm.common.dao.AbstractDao;
import cn.com.zsyk.crm.common.dao.CoreDaoImpl;
import cn.com.zsyk.crm.common.dto.PageBean;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.*;
import cn.com.zsyk.crm.ecif.bo.bom.TagBasBom;
import cn.com.zsyk.crm.ecif.bo.cust.EntCustList;
import cn.com.zsyk.crm.ecif.bo.cust.PerCustBaseInfo;
import cn.com.zsyk.crm.ecif.bo.cust.PerCustList;
import cn.com.zsyk.crm.ecif.entity.*;
import cn.com.zsyk.crm.ecif.mapper.*;
import cn.com.zsyk.crm.ecif.service.customer.portrait.*;
import cn.com.zsyk.crm.ecif.service.user.UserService;
import cn.com.zsyk.crm.generator.EnumType;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@SuppressWarnings("restriction")
@Service
@Transactional 
public class CustService {
	/** logger */
	@SuppressWarnings("unused")
	private final Log logger = LogUtil.getLogger(CustService.class);
	@Autowired
	EcCustPerMapper ecCustPerMapper;
	@Autowired
	EcCustEntMapper ecCustEntMapper;
	@Autowired
	EcCustNameMapper ecCustNameMapper;
	@Autowired
	CustTagService custTagService;
	@Autowired
	private CustContactService custContactService;
	@Autowired
	private EcCustCareerMapper ecCustCareerMapper;
	@Autowired
	private CustCertService custCertService;
	@Autowired
	private EcCustCertMapper ecCustCertMapper;
	@Autowired
	private EcContactWayMapper ecContactWayMapper;
	@Autowired
	private EcContactAddrMapper ecContactAddrMapper;
	@Autowired
	private EcCustEduMapper ecCustEduMapper;
	@Autowired
	private EcCustAgentMapper ecCustAgentMapper;
	@Autowired
	private EcCustRelationMapper ecCustRelationMapper;
	@Autowired
	private EcCustClaimMapper ecCustClaimMapper;
	@Autowired
	EcCustProposalMapper ecCustProposalMapper;
	@Autowired
	private EcCustEndorseMapper ecCustEndorseMapper;
	@Autowired
	private EcCustComplainMapper ecCustComplainMapper;
	@Autowired
	private EcCustTouchMapper ecCustTouchMapper;
	@Autowired
	private EcCustProductMapper ecCustProductMapper;
	@Autowired
	private EcCustEventMapper ecCustEventMapper;
	@Autowired
	private EcFirstActMapper ecFirstActMapper;
	@Autowired
	private EcFinacePayMapper ecFinacePayMapper;
	@Autowired
	private EcFinaceClaimMapper ecFinaceClaimMapper;
	@Autowired
	private EcIntegralMapper ecIntegralMapper;
	@Autowired
	private EcCustDemandMapper ecCustDemandMapper;
	@Autowired
	private EcHealthFileMapper ecHealthFileMapper;
	@Autowired
	private EcAudioVideoMapper ecAudioVideoMapper;
	@Autowired
	private EcTrackInfoMapper ecTrackInfoMapper;
	@Autowired
	private EcTrackComtMapper ecTrackComtMapper;
	@Autowired
	private EcCustHabitavoMapper ecCustHabitavoMapper;
	@Autowired
	private EcCustTagMapper ecCustTagMapper;
	@Autowired
	private EcAssetsPerMapper ecAssetsPerMapper;
	@Autowired
	private EcRiskPerMapper ecRiskPerMapper;
	@Autowired
	private EcCustManagerMapper ecCustManagerMapper;
	@Autowired
	CustCareerService custCareerService;
	@Autowired
	private AbstractDao daoUtil;
	/* 分页查询对象 */
	@Autowired
	private CoreDaoImpl coreDaoImpl;
	@Autowired
	private CustDyncTrackService custDyncTrackService;
	@Autowired
	private CustEduService custEduService;
	@Autowired
	RestUtil restUtil;
	
	@Autowired
	UserService userService;

	/**
	 * Desc: 获取个人姓名信息
	 * @param custNo
	 * @return
	 * @author
	 */
	public String getPerCustName(String custNo) {
		EcCustName ecCustName = ecCustNameMapper.selectByPrimaryKey(custNo);
		if (null == ecCustName) {
			throw new ServiceException("个人客户姓名信息不存在");
		}
		String custName = ecCustName.getCustNam();
		return custName;
	}

	/**
	 * Desc: 获取企业名称
	 * @param custNo
	 * @return
	 * @author
	 */
	public String getEntCustName(String custNo) {
		EcCustEnt ecCustEnt = ecCustEntMapper.selectByPrimaryKey(custNo);
		if (null == ecCustEnt) {
			throw new ServiceException("个人客户姓名信息不存在");
		}
		String custName = ecCustEnt.getEntEnNam();
		return custName;
	}

	/**
	 * Desc: 获取客户类型
	 * @param custNo
	 * @return
	 * @author
	 */
	public String getCustType(String custNo) {
		// 客户类型
		String custType = "";
		EcCustPer ecCustPer = ecCustPerMapper.selectByPrimaryKey(custNo);
		if (ecCustPer == null) {
			EcCustEnt ecCustEnt = ecCustEntMapper.selectByPrimaryKey(custNo);
			if (ecCustEnt == null) {
				throw new ServiceException("客户不存在");
			} {
				custType = ecCustEnt.getCustTyp();
			}
		} else {
			custType = ecCustPer.getCustTyp();
		}
		if (StringUtils.isEmpty(custType)) {
			throw new ServiceException("客户类型获取失败");
		}

		return custType;
	}

	/**
	 * Desc: 查询个人客户列表
	 * @return
	 * @author 
	 */
	public List<PerCustList> selectPerCustList(EcCustPer custper) {
		List<PerCustList> retlst = ecCustPerMapper.selectPerCustList(custper);
			if (null != retlst) {
				for(PerCustList info : retlst) {
					info.setCustTag(custTagService.selectByCustNo(info.getCustNo()));
				}
			} 
			return retlst;
	}
	/**
	 * 
	 * @param custper
	 * @return
	 */
	public List<PerCustList> selectBlackPerCustList(PerCustList custper) {
		
		//根据标签查询客户列表
		if(custper.getCustTagCd() == null || custper.getCustTagCd() =="") {
			List<PerCustList> retlst = ecCustPerMapper.selectBlackPerCustList(custper);
			if (null != retlst) {
				for(PerCustList info : retlst) {
					info.setCustTag(custTagService.selectByCustNo(info.getCustNo()));
				}
			} 
			return retlst;
		}else {
			List<PerCustList> retlst1 = ecCustPerMapper.selectPerCustListByTag(custper);
			if (null != retlst1) {
				for(PerCustList info : retlst1) {
					info.setCustTag(custTagService.selectByCustNo(info.getCustNo()));
				}
			} 
			return retlst1;
		}
		
	}
	public PageBean selectNotBlackPerCustList(EcCustPer custper) {
		Map map = new HashMap();
		map.put("custNo",custper.getCustNo());
		map.put("custName",custper.getCustName());
		map.put("custTagCd",custper.getCustTagCd());
		//根据标签查询客户列表
		if(custper.getCustTagCd() == null || custper.getCustTagCd() =="") {
			PageBean pageRetlst = coreDaoImpl.selectPageByMapper(ecCustPerMapper,"selectNotBlackPerCustList",map);
			List<PerCustList> retlst = pageRetlst.getList();
			if (null != retlst) {
				for(PerCustList info : retlst) {
					info.setCustTag(custTagService.selectByCustNo(info.getCustNo()));
				}
			}
			pageRetlst.setList(retlst);
			return pageRetlst;
		}else {
			PageBean pageRetlst = coreDaoImpl.selectPageByMapper(ecCustPerMapper,"selectNotBlackPerCustListByTag",map);
			List<PerCustList> retlst = pageRetlst.getList();
			if (null != retlst) {
				for(PerCustList info : retlst) {
					info.setCustTag(custTagService.selectByCustNo(info.getCustNo()));
				}
			}
			pageRetlst.setList(retlst);
			return pageRetlst;
		}
		
	}

	/**
	 * Desc: 查询企业客户列表
	 * @return
	 * @author 
	 */
	public List<EntCustList> selectEntCustList() {
		List<EntCustList> retlst = ecCustEntMapper.selectEntCustList();
		if (null != retlst) {
			for(EntCustList info : retlst) {
				info.setCustTag(custTagService.selectByCustNo(info.getCustNo()));
			}
		}
		return retlst;
	}

	/**
	 * Desc: 查询个人客户信息
	 * @param custNo
	 * @return
	 * @author
	 * @throws Exception 
	 */
	public PerCustBaseInfo getPerCustInfo(String custNo) throws Exception {
		PerCustBaseInfo retCustInfo = new PerCustBaseInfo();

		// check cust info.
		chkPerCustInfo(custNo);

		EcCustPer ecCustPer = ecCustPerMapper.selectByPrimaryKey(custNo);
		// 客户号 custNo;
		retCustInfo.setCustNo(custNo);
		// 客户姓名 custName;
		retCustInfo.setCustName(this.getPerCustName(custNo));
		// 性别 sex;
		retCustInfo.setSex(ecCustPer.getSex());
		// 出生日期 birthDate;
		retCustInfo.setBirthDate(ecCustPer.getBirthDate());
		// 客户经理 custAgent;
		retCustInfo.setCustAgent(ecCustPer.getCustAgent());
		// 客户类型 custTyp;
		retCustInfo.setCustTyp(ecCustPer.getCustTyp());
		// 客户来源 custSource;
		retCustInfo.setCustSource(ecCustPer.getCustSource());
		// 其他来源 otherSource;
		retCustInfo.setOtherSource(ecCustPer.getOtherSource());
		//民族
		retCustInfo.setNation(ecCustPer.getNation());
		//政治面貌
		retCustInfo.setPolitSts(ecCustPer.getPolitSts());
		// 是否为重点 keyCustFlg;
		retCustInfo.setKeyCustFlg(ecCustPer.getKeyCustFlg());
		// 下次跟进时间 nextFollowUpTm;
//		retCustInfo.setNextFollowUpTm(DateUtil.date2FormatString(ecCustPer.getNextFollowUpTm()));
		// 国籍 nationality;
		retCustInfo.setNationality(ecCustPer.getNationality());
		// 婚姻状况 marrigeSts;
		retCustInfo.setMarrigeSts(ecCustPer.getMarrigeSts());
		// 文化程度 eduDegree;
		retCustInfo.setEduDegree(ecCustPer.getEduDegree());
		//客服系统客户ID
		retCustInfo.setCustomerId(ecCustPer.getCustomerId());
		// 头像URL perIconUrl;
        BASE64Encoder encoder = new BASE64Encoder();
		if (ecCustPer.getPerIconSmlBlob() != null) {
			retCustInfo.setPerIconSmlBlob(encoder.encode(ecCustPer.getPerIconSmlBlob()));
		}
		if (ecCustPer.getPerIconBigBlob() != null) {
			retCustInfo.setPerIconBigBlob(encoder.encode(ecCustPer.getPerIconBigBlob()));
		}
		retCustInfo.setNation(ecCustPer.getNation());
		retCustInfo.setPolitSts(ecCustPer.getPolitSts());
		EcCustEdu ecCustEdu =  ecCustEduMapper.selectHighestEduByCustNo(custNo);
		if (ecCustEdu != null) {
			retCustInfo.setEduDegree(ecCustEdu.getEduTyp());
			retCustInfo.setSchoolNam(ecCustEdu.getSchoolNam());
		}

		EcCustCert obj = new EcCustCert();
		obj.setCustNo(custNo);
		List<EcCustCert> ecCustCert = custCertService.selectCustCertList(obj);
		if (null != ecCustCert && ecCustCert.size() > 0) {
			// 证件类型 certTyp;
			retCustInfo.setCertTyp(ecCustCert.get(0).getCertTyp());
			// 证件号码 certNo;
			retCustInfo.setCertNo(ecCustCert.get(0).getCertNo());
			//证件有效期 add lijiangcheng 2019-08-21
			if(!StringUtils.isEmpty(ecCustCert.get(0).getCertEffDate())){
				retCustInfo.setCertDate(new SimpleDateFormat(DateUtil.DATE_FORMAT_YMD_LONG).parse(ecCustCert.get(0).getCertEffDate()));
			}
		}

		List<EcContactWay> ecContactWayList = custContactService.getContactWayOneList(custNo);
		List<EcCustPhone> phoneList= new ArrayList<>();
		if(ecContactWayList.size()!=0){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			for(EcContactWay ecContactWay :ecContactWayList){

				if (null != ecContactWay) {
					if (ecContactWay.getRecStat().equals(EnumType.RecStat.normal.getValue())) {
						// 电话号码 phoneNumber;
						retCustInfo.setPhoneNumber(ecContactWay.getPhoneNo1() == null ? "" : ecContactWay.getPhoneNo1());
						if(StringUtils.isNotEmpty(ecContactWay.getPhoneNo2())){
							// 其他手机 otherTel;
							EcCustPhone ecCustPhone = new EcCustPhone();
							ecCustPhone.setId(ecContactWay.getContactSqn());
							ecCustPhone.setPhoneNo(ecContactWay.getPhoneNo2());
							ecCustPhone.setIsDeault(ecContactWay.getWhtDefaultWay());
							ecCustPhone.setCustSource(ecContactWay.getCustSource());
							if(ecContactWay.getTimeStamp()!=null){
								ecCustPhone.setTimeStamp(sdf.format(ecContactWay.getTimeStamp()));
							}
							ecCustPhone.setCreatDate(ecContactWay.getCreateDate());
							ecCustPhone.setCreatTime(ecContactWay.getCreateTime());
							phoneList.add(ecCustPhone);
						}
						// 住宅电话 homeTel;
						retCustInfo.setHomeTel(ecContactWay.getTelephone1());
						// 公司电话 officeTel;
						retCustInfo.setOfficeTel(ecContactWay.getTelephone2());
						// 传真 fax;
						retCustInfo.setFax(ecContactWay.getFax());
						// 微信 wechatNo;
						retCustInfo.setWechatNo(ecContactWay.getWechatNo());
						// 电子邮箱 emailAddr;
						retCustInfo.setEmailAddr(ecContactWay.getEmailAddr() == null ? "" : ecContactWay.getEmailAddr());
						// QQ qq;
						retCustInfo.setQq(ecContactWay.getQq());
						// 微博 microblog;
						retCustInfo.setMicroblog(ecContactWay.getMicroblog());
						// 支付宝 alipay;
						retCustInfo.setAlipay(ecContactWay.getAlipay());
					}
				}
			}
		}
		if(phoneList.size()==0){
			EcCustPhone ecCustPhone = new EcCustPhone();
			ecCustPhone.setId(1);
			phoneList.add(ecCustPhone);
			retCustInfo.setPhoneList(phoneList);
		}else{
			retCustInfo.setPhoneList(phoneList);
		}

		EcCustCareer ecCustCareer = ecCustCareerMapper.selectByPrimaryKey(custNo);
		if (null != ecCustCareer) {
			// 所属行业 trade;
			retCustInfo.setTrade(ecCustCareer.getTrade());
			// 职业 position;
			retCustInfo.setPosition(ecCustCareer.getPosition());
			// 工作单位 unitNam;
			retCustInfo.setUnitNam(ecCustCareer.getUnitNam());
		}

		// 地址
		List<EcContactAddr> homeAddrList  = custContactService.selectContactAddrByPkList(custNo);
		if(homeAddrList.size()!=0) {
			List<EcContactAddress> addrList= new ArrayList<>();
			for (EcContactAddr homeAddr : homeAddrList) {
				if (homeAddr != null) {
					if (homeAddr.getRecStat().equals(EnumType.RecStat.normal.getValue())) {
						if(StringUtils.isEmpty(homeAddr.getProvCd())&&StringUtils.isEmpty(homeAddr.getCityCd())&&StringUtils.isEmpty(homeAddr.getCountyCd())&&StringUtils.isEmpty(homeAddr.getStreetAddr())){

						}else {
							EcContactAddress ecContactAddress = new EcContactAddress();
							// 居住地址类型 liveAddrTyp;
							ecContactAddress.setId(Integer.parseInt(homeAddr.getAddrTyp()));
							// 居住省 liveProvince;
							ecContactAddress.setProvCd(homeAddr.getProvCd());
							// 居住市 liveCity;
							ecContactAddress.setCityCd(homeAddr.getCityCd());
							// 居住区县 liveCounty;
							ecContactAddress.setCountyCd(homeAddr.getCountyCd());
							// 居住街道 liveStreet;
							ecContactAddress.setStreetAddr(homeAddr.getStreetAddr());
							// 居住邮编 livePostcode;
							ecContactAddress.setPostcode(homeAddr.getPostcode());
							ecContactAddress.setWhtDefaultAddr(homeAddr.getWhtDefaultAddr());
							addrList.add(ecContactAddress);
						}
					}
				}
			}
			if(addrList.size()<0){
				EcContactAddress ecContactAddress = new EcContactAddress();
				ecContactAddress.setWhtDefaultAddr("1");
				addrList.add(ecContactAddress);
				retCustInfo.setAddrList(addrList);
			}else{
				retCustInfo.setAddrList(addrList);
			}
		}

//		EcContactAddr companyAddr = custContactService.selectContactAddrByPk(custNo, EnumType.AddrTyp.company_addr.getValue());
//		if (companyAddr != null) {
//			// 单位地址类型 unitAddrTyp;
//			retCustInfo.setUnitAddrTyp(companyAddr.getAddrTyp());
//			// 单位省 unitProvince;
//			retCustInfo.setUnitProvince(companyAddr.getProvCd());
//			// 单位市  unitCity;
//			retCustInfo.setUnitCity(companyAddr.getCityCd());
//			// 单位区县  unitCounty;
//			retCustInfo.setUnitCounty(companyAddr.getCountyCd());
//			// 单位街道  unitStreet;
//			retCustInfo.setUnitStreet(companyAddr.getStreetAddr());
//			// 单位邮编  unitPostcode;
//			retCustInfo.setUnitPostcode(companyAddr.getPostcode());
//		}

		return retCustInfo;
	}

	/**
	 * Desc: 保存个人客户信息
	 * @param perCustBaseInfo
	 * @return
	 * @author
	 * @throws ParseException 
	 */
	public void uptPerCustInfo(PerCustBaseInfo perCustBaseInfo) throws Exception {
		if (null == perCustBaseInfo) {
			throw new ServiceException("参数不能为空");
		}
		if (StringUtils.isEmpty(perCustBaseInfo.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}

//		if (StringUtils.isEmpty(perCustBaseInfo.getSex())) {
//			throw new ServiceException("性别不能为空");
//		}

		if (StringUtils.isEmpty(perCustBaseInfo.getCustName())) {
			throw new ServiceException("姓名不能为空");
		}

//		if (perCustBaseInfo.getBirthDate() == null) {
//			throw new ServiceException("出生日期不能为空");
//		}
		// check cust info.
		chkPerCustInfo(perCustBaseInfo.getCustNo());

		/** 基本信息 */
		// 客户号
		String custNo = perCustBaseInfo.getCustNo();
		EcCustPer ecCustPer = ecCustPerMapper.selectByPrimaryKey(custNo);

		// 性别 sex;
		if (StringUtils.isNotEmpty(perCustBaseInfo.getSex())) {
			ecCustPer.setSex(perCustBaseInfo.getSex());
		}
		// 出生日期 birthDate;
		if (perCustBaseInfo.getBirthDate() != null) {
			ecCustPer.setBirthDate(perCustBaseInfo.getBirthDate());
		}

//		// 变更代理人
//		if(StringUtils.isNotEmpty(ecCustPer.getCustAgent())) {
//			if (!ecCustPer.getCustAgent().equals(perCustBaseInfo.getCustAgent())) {
//				// 写轨迹
//				EcTrackInfo ecTrackInfo = new EcTrackInfo();
//				//		客户号
//				ecTrackInfo.setCustNo(custNo);
//				//		轨迹类型
//				ecTrackInfo.setTrackTyp(EnumType.TrackType.info_change.getValue());
//				ecTrackInfo.setTrackSubTyp(EnumType.TrackSubType.agent_change.getValue());
//				//		内容详情
//				ecTrackInfo.setTrackContent("代理人由 " + ecCustPer.getCustAgent() + " 变更为 " + perCustBaseInfo.getCustAgent());
//				//		记录人
//				custDyncTrackService.addDyncTrack(ecTrackInfo);
//			}
//		}

//		// 客户经理 custAgent;
//		ecCustPer.setCustAgent(perCustBaseInfo.getCustAgent());
		// 客户类型 custTyp;
		ecCustPer.setCustTyp(perCustBaseInfo.getCustTyp());
		// 客户来源 custSource;
		ecCustPer.setCustSource(perCustBaseInfo.getCustSource());
		// 其他来源 otherSource add lijiangcheng 2019-08-02;
		ecCustPer.setOtherSource(perCustBaseInfo.getOtherSource());
		// 是否为重点 keyCustFlg;
		ecCustPer.setKeyCustFlg(perCustBaseInfo.getKeyCustFlg());
		// 下次跟进时间 nextFollowUpTm;
//		ecCustPer.setNextFollowUpTm(DateUtil.formatString2Date(perCustBaseInfo.getNextFollowUpTm()));
		// 国籍 nationality;
		ecCustPer.setNationality(perCustBaseInfo.getNationality());
		// 婚姻状况 marrigeSts;
		ecCustPer.setMarrigeSts(perCustBaseInfo.getMarrigeSts());
		// 文化程度 eduDegree;
		ecCustPer.setEduDegree(perCustBaseInfo.getEduDegree());
		//民族add lijiangcheng 2019-08-02;
		ecCustPer.setNation(perCustBaseInfo.getNation());
		//政治面貌add lijiangcheng 2019-08-02;
		ecCustPer.setPolitSts(perCustBaseInfo.getPolitSts());
//		EcCustPer custper = new EcCustPer();
//		custper.setCertNo(perCustBaseInfo.getCertNo());
//		custper.setCustName(perCustBaseInfo.getCustName());
//		custper.setCertType(perCustBaseInfo.getCertTyp());
//		List<PerCustList> list = ecCustPerMapper.selectPerCustList(custper);
//		if(list != null && !list.isEmpty()) {
//			PerCustList per = list.get(0);
//			if(!per.getCustNo().equals(custNo)) {
//				throw new ServiceException("已有此用户信息，请勿重复添加。");
//			}
//		}
		// 头像URL perIconUrl;
        BASE64Encoder encoder = new BASE64Encoder();
		BASE64Decoder decode = new BASE64Decoder();
		if (perCustBaseInfo.getPerIconUrl() != null) {
	        String imgData = encoder.encode(perCustBaseInfo.getPerIconUrl().getBytes());
			byte[] perIconBlob = decode.decodeBuffer(imgData);
			ecCustPer.setPerIconSmlBlob(perIconBlob);
			ecCustPer.setPerIconBigBlob(perIconBlob);
		}
		ecCustPerMapper.updateByPrimaryKey(ecCustPer);

		/** 姓名信息 */
		EcCustName ecCustName = ecCustNameMapper.selectByPrimaryKey(custNo);
		if (!perCustBaseInfo.getCustName().equals(ecCustName.getCustNam())) {
			ecCustName.setCustNam(perCustBaseInfo.getCustName());
			ecCustNameMapper.updateByPrimaryKey(ecCustName);
		}

		/** 学历 */
		if(StringUtils.isNotEmpty(perCustBaseInfo.getEduDegree())){
			custEduService.saveOrUpdateCustEdu(perCustBaseInfo);
		}

		/** 证件信息 */
		custCertService.saveOrUpdateCert(perCustBaseInfo);

		/** 联系方式信息 */
		custContactService.saveOrUpdateContactAddr(perCustBaseInfo);

		/** 职业信息 */
		custCareerService.saveOrUpdateCareer(perCustBaseInfo);

		/** 居住地址信息 */
		custContactService.saveOrUpdateHomeAddr(perCustBaseInfo);

		/** 单位地址信息 */
//		custContactService.saveOrUpdateUnitAddr(perCustBaseInfo);

		this.autoAddCustTag(custNo, perCustBaseInfo);
	}

	/**
	 * Desc:新增个人客户信息 
	 * @param perCustBaseInfo
	 * @author
	 * @throws Exception 
	 * 
	 * @return custNo 
	 */
	public String addPerCustInfo(PerCustBaseInfo perCustBaseInfo) throws Exception {

		if (null == perCustBaseInfo) {
			throw new ServiceException("参数不能为空");
		}

		if (StringUtils.isEmpty(perCustBaseInfo.getCustTyp())) {
			throw new ServiceException("客户类型不能为空");
		}

//		if (StringUtils.isEmpty(perCustBaseInfo.getSex())) {
//			throw new ServiceException("性别不能为空");
//		}

		if (StringUtils.isEmpty(perCustBaseInfo.getCustName())) {
			throw new ServiceException("姓名不能为空");
		}

//		if (perCustBaseInfo.getBirthDate() == null) {
//			throw new ServiceException("出生日期不能为空");
//		}

		/** 基本信息 */
		// 客户号
		String custNo = "C"+String.valueOf(IdGenerator.getDistributedID()).substring(8,18);
		// 客户号不能为空
//		if (StringUtils.isEmpty(perCustBaseInfo.getCustNo())) {
				perCustBaseInfo.setCustNo(custNo);
//		}
		EcCustPer ecCustPer = new EcCustPer();
		// 客户号 custNo
		ecCustPer.setCustNo(custNo);
		// 性别 sex;
		if (StringUtils.isNotEmpty(perCustBaseInfo.getSex())) {
			ecCustPer.setSex(perCustBaseInfo.getSex());
		}
		// 出生日期 birthDate;
		if (perCustBaseInfo.getBirthDate() != null) {
			ecCustPer.setBirthDate(perCustBaseInfo.getBirthDate());
		}
		// 客户经理 custAgent;
		if (StringUtils.isNotEmpty(perCustBaseInfo.getCustAgent())) {
			ecCustPer.setCustAgent(perCustBaseInfo.getCustAgent());
			EcCustManager ecCustManager = new EcCustManager();
			ecCustManager.setCustNo(custNo);
			ecCustManager.setCustAgent(perCustBaseInfo.getCustAgent());
			ecCustManager.setRiseTime(DateUtil.nowDateTimeStamp());
			ecCustManager.setRecStat("0");
			List<EcCustManager> listTmp=ecCustManagerMapper.selectAgentByConditions(ecCustManager);
			if(listTmp==null || listTmp.size()==0){
				ecCustManagerMapper.insert(ecCustManager);
			}

		}

		// 客户类型 custTyp;
		if (StringUtils.isNotEmpty(perCustBaseInfo.getCustTyp())) {
			ecCustPer.setCustTyp(perCustBaseInfo.getCustTyp());
		}
		// 客户来源 custSource;
		if (StringUtils.isNotEmpty(perCustBaseInfo.getCustSource())) {
			ecCustPer.setCustSource(perCustBaseInfo.getCustSource());
		}
		// 其他来源 otherSource add lijiangcheng 2019-08-02;
		if (StringUtils.isNotEmpty(perCustBaseInfo.getOtherSource())) {
			ecCustPer.setOtherSource(perCustBaseInfo.getOtherSource());
		}
		//民族add lijiangcheng 2019-08-02;
		if (StringUtils.isNotEmpty(perCustBaseInfo.getNation())) {
			ecCustPer.setNation(perCustBaseInfo.getNation());
		}
		//政治面貌add lijiangcheng 2019-08-02;
		if (StringUtils.isNotEmpty(perCustBaseInfo.getPolitSts())) {
			ecCustPer.setPolitSts(perCustBaseInfo.getPolitSts());
		}
		// 是否为重点 keyCustFlg;
		if (StringUtils.isNotEmpty(perCustBaseInfo.getCustSource())) {
			ecCustPer.setKeyCustFlg(perCustBaseInfo.getKeyCustFlg());
		} else {
			ecCustPer.setKeyCustFlg(EnumType.YesNoFlg.no.getValue());
		}
		// 下次跟进时间 nextFollowUpTm;
//		if (StringUtils.isNotEmpty(perCustBaseInfo.getNextFollowUpTm())) {
//			ecCustPer.setNextFollowUpTm(DateUtil.formatString2Date(perCustBaseInfo.getNextFollowUpTm()));
//		}
		// 国籍 nationality;
		if (StringUtils.isNotEmpty(perCustBaseInfo.getNationality())) {
			ecCustPer.setNationality(perCustBaseInfo.getNationality());
		}
		// 婚姻状况 marrigeSts;
		if (StringUtils.isNotEmpty(perCustBaseInfo.getMarrigeSts())) {
			ecCustPer.setMarrigeSts(perCustBaseInfo.getMarrigeSts());
		}
		// 文化程度 eduDegree;
		if (StringUtils.isNotEmpty(perCustBaseInfo.getEduDegree())) {
			ecCustPer.setEduDegree(perCustBaseInfo.getEduDegree());
		}
		// 头像URL perIconUrl;
        BASE64Encoder encoder = new BASE64Encoder();
		BASE64Decoder decode = new BASE64Decoder();
		if (perCustBaseInfo.getPerIconUrl() != null) {
	        String imgData = encoder.encode(perCustBaseInfo.getPerIconUrl().getBytes());
			byte[] perIconBlob = decode.decodeBuffer(imgData);
			ecCustPer.setPerIconSmlBlob(perIconBlob);
			ecCustPer.setPerIconBigBlob(perIconBlob);
		} else {
			byte[] perIconBlob = decode.decodeBuffer(perCustBaseInfo.getPerIconSmlBlob());
			ecCustPer.setPerIconSmlBlob(perIconBlob);
			ecCustPer.setPerIconBigBlob(perIconBlob);
		}
		// 黑名单 blacklistFlg;
		if (StringUtils.isNotEmpty(perCustBaseInfo.getBlacklistFlg())) {
			ecCustPer.setBlacklistFlg(perCustBaseInfo.getBlacklistFlg());
		}else {
			// 黑名单标识		blacklist_flg
			ecCustPer.setBlacklistFlg(EnumType.YesNoFlg.no.getValue());
		}
		// 可见范围		visible_range
		ecCustPer.setVisibleRange(EnumType.VisibleRange.all.getValue());
		// 可以证实年龄的证件标识		cnf_age_cert_flg
		ecCustPer.setCnfAgeCertFlg(EnumType.YesNoFlg.yes.getValue());
		ecCustPer.setCustStat(EnumType.CustStat.normal.value);//客户状态：正常

		// 客户导入
		// 身高、体重、血型
		if (StringUtils.isNotEmpty(perCustBaseInfo.getBloodTyp())) {
			ecCustPer.setBloodTyp(perCustBaseInfo.getBloodTyp());
		}
		if (new BigDecimal(0) != perCustBaseInfo.getWeight()) {
			ecCustPer.setWeight(perCustBaseInfo.getWeight());
		}
		if (new BigDecimal(0) != perCustBaseInfo.getHeight()) {
			ecCustPer.setHeight(perCustBaseInfo.getHeight());
		}
//		Calendar now = Calendar.getInstance();
//		ecCustPer.setCreateDate(String.valueOf(now.get(Calendar.YEAR))+String.valueOf(now.get(Calendar.MONTH) + 1)+String.valueOf(now.get(Calendar.DAY_OF_MONTH)));
//		ecCustPer.setCreateTime(String.valueOf(now.get(Calendar.HOUR_OF_DAY))+String.valueOf(now.get(Calendar.MINUTE))+String.valueOf(now.get(Calendar.SECOND)));
//		ecCustPer.setCreateUser("admin");//暂时写死
//		ecCustPer.setTimeStamp(now.getTime());
		//客户名称、证件类型、证件号码一致 提示重复客户信息
		if(!EnumType.MergeSplitAction.merge_approv_pass.desc.equals(perCustBaseInfo.getMergeMark())) {
			EcCustPer custper = new EcCustPer();
			custper.setCertNo(perCustBaseInfo.getCertNo());
			custper.setCustName(perCustBaseInfo.getCustName());
			custper.setCertType(perCustBaseInfo.getCertTyp());
			List<PerCustList> list = ecCustPerMapper.selectPerCustList(custper);
			if (list != null && !list.isEmpty()) {
				throw new ServiceException("已有此用户信息，请勿重复添加。");
			}
		}
		ecCustPerMapper.insert(ecCustPer);

		/** 姓名信息 */
		if (perCustBaseInfo.getCustName() != null) {
			EcCustName ecCustName = ecCustNameMapper.selectByPrimaryKey(custNo);
			if (ecCustName != null) {
				if (ecCustName.getRecStat().equals(EnumType.RecStat.delete.getValue())) {
					ecCustName.setRecStat(EnumType.RecStat.normal.getValue());
				}
				ecCustName.setCustNam(perCustBaseInfo.getCustName());
				ecCustNameMapper.updateByPrimaryKey(ecCustName);
			} else {
				EcCustName nameObj = new EcCustName();
				// 	客户号		cust_no
				nameObj.setCustNo(custNo);
				// 	客户姓名		cust_nam
				nameObj.setCustNam(perCustBaseInfo.getCustName());
				// 	曾用名1		frmer_nam1
				// 	曾用名2		frmer_nam2
				// 	生效日期		eff_date
				// 	失效日期		exp_date
				// 	姓名拼音		full_name_py
				// 	数据来源		data_source
				nameObj.setDataSource(EnumType.DataSource.ecif.getValue());
				ecCustNameMapper.insert(nameObj);
			}
		}

		/** 学历 */
		if (StringUtils.isNotEmpty(perCustBaseInfo.getEduDegree())) {
			custEduService.saveOrUpdateCustEdu(perCustBaseInfo);
		}

		/** 证件信息 */
//		if(EnumType.CustType.per_latent_cust.getValue().equals(perCustBaseInfo.getCustTyp()) && StringUtils.isEmpty(perCustBaseInfo.getCertTyp())  &&  StringUtils.isEmpty(perCustBaseInfo.getCertNo()) ){
//
//		}else if (EnumType.CustType.per_latent_cust.getValue().equals(perCustBaseInfo.getCustTyp()) && StringUtils.isNotEmpty(perCustBaseInfo.getCertTyp()) &&  StringUtils.isEmpty(perCustBaseInfo.getCertNo())){
////			if (StringUtils.isEmpty(perCustBaseInfo.getCertNo())) {
////				throw new ServiceException("已选择证件类型，请填写证件号码，证件号码不能为空");
////			}
//		}else if (EnumType.CustType.per_latent_cust.getValue().equals(perCustBaseInfo.getCustTyp()) && StringUtils.isEmpty(perCustBaseInfo.getCertTyp()) &&  StringUtils.isNotEmpty(perCustBaseInfo.getCertNo())){
//
//		}else{
			custCertService.saveOrUpdateCert(perCustBaseInfo);
//		}

		/** 联系方式信息 */
		custContactService.saveOrUpdateContactAddr(perCustBaseInfo);

		/** 职业信息 */
		custCareerService.saveOrUpdateCareer(perCustBaseInfo);

		/** 居住地址信息 默认*/
		custContactService.saveOrUpdateHomeAddr(perCustBaseInfo);

		/** 居住地址信息 批量 add lijiangcheng 2019-08-01*/
//		custContactService.saveOrUpdateAddress(perCustBaseInfo);

		/** 单位地址信息 */
		//custContactService.saveOrUpdateUnitAddr(perCustBaseInfo);

		/** 健康档案 HealthFileCd **/
//		EcHealthFile ecHealthFile = new EcHealthFile();
//		//		chk_date
//		ecHealthFile.setChkDate(DateUtil.getCurTime());
//		//		chk_no
//		ecHealthFile.setChkNo(String.valueOf(IdGenerator.getSeqID("HealthFileCd")));
//		//		cust_no
//		ecHealthFile.setCustNo(custNo);
//		//		med_org_nam
//		ecHealthFile.setMedOrgNam("沈阳市中医院");
//		//		consul_room
//		ecHealthFile.setConsulRoom("体检中心");
//		//		principal_doctor
//		ecHealthFile.setPrincipalDoctor("张翰");
//		//		file_typ
//		ecHealthFile.setFileTyp("1");
//		//		chek_content
//		ecHealthFile.setChekContent("肝功五项、胸透、脑核磁");
//		//		file_url
//		ecHealthFile.setFileUrl("");
//		ecHealthFileMapper.insert(ecHealthFile);
//
//		/** 视频音频 AudioVideoCd **/
//		EcAudioVideo ecAudioVideo = new EcAudioVideo();
//		ecAudioVideo.setAudioVideoCd(String.valueOf(IdGenerator.getSeqID("AudioVideoCd")));
//		ecAudioVideo.setGenDate(DateUtil.getCurTime());
//		ecAudioVideo.setCustNo(custNo);
//		ecAudioVideo.setFileNam("E生宝投保视频");
//		ecAudioVideo.setContentTyp("0");
//		ecAudioVideo.setPrincipal("dev");
//		ecAudioVideo.setFileTyp("1");
//		ecAudioVideo.setFileUrl("bower_components/html5media/src/media/video.mp4");
//		ecAudioVideoMapper.insert(ecAudioVideo);
//
//		EcAudioVideo ecAudioVideo1 = new EcAudioVideo();
//		ecAudioVideo1.setAudioVideoCd(String.valueOf(IdGenerator.getSeqID("AudioVideoCd")));
//		ecAudioVideo1.setGenDate(DateUtil.getCurTime());
//		ecAudioVideo1.setCustNo(custNo);
//		ecAudioVideo1.setFileNam("E生宝投保音频");
//		ecAudioVideo1.setContentTyp("0");
//		ecAudioVideo1.setPrincipal("dev");
//		ecAudioVideo1.setFileTyp("1");
//		ecAudioVideo1.setFileUrl("bower_components/html5media/src/media/audio.mp3");
//		ecAudioVideoMapper.insert(ecAudioVideo1);

		//this.autoAddCustTag(custNo, perCustBaseInfo);
		return custNo;
	}

	/**
	 * Desc: 新建修改客户信息打标签
	 * @param custNo
	 * @param perCustBaseInfo
	 * @throws Exception
	 * @author
	 */
	@SuppressWarnings("unchecked")
	private void autoAddCustTag(String custNo, PerCustBaseInfo perCustBaseInfo) throws Exception {
		TagBasBom tagBasBom = new TagBasBom();
		// 客户号
		tagBasBom.setCustNo(custNo);
		// 性别
		tagBasBom.setSex(perCustBaseInfo.getSex());
		// 籍贯
        if(perCustBaseInfo.getBirthDate()!=null){
            // 年龄(当前日期-出生日期 或者 死亡日期-出生日期)
            tagBasBom.setAge(String.valueOf(DateUtil.getAge(perCustBaseInfo.getBirthDate())));
		    // 出生年份（用于80后、90后、70后标签等
		    tagBasBom.setBirthYear(String.valueOf(DateUtil.getYear(perCustBaseInfo.getBirthDate())));
        }
		// 职业
		tagBasBom.setOccupation(perCustBaseInfo.getPosition());
		// 行业
		tagBasBom.setTrade(perCustBaseInfo.getTrade());
		// 学位
		tagBasBom.setDegree(perCustBaseInfo.getEduDegree());
		//  民族
		tagBasBom.setNation(perCustBaseInfo.getNation());// UI上无输入
		//  出生地
		tagBasBom.setBirthPlace(null);// UI上无输入-待定
		//  国籍
		tagBasBom.setNationality(perCustBaseInfo.getNationality());
		//  婚姻状态
		tagBasBom.setMarrigeSts(perCustBaseInfo.getMarrigeSts());
		// 生日
		tagBasBom.setBirthDate(DateUtil.date2FormatStringYMDLong(perCustBaseInfo.getBirthDate()));
		// 学校
		tagBasBom.setSchoolNam(perCustBaseInfo.getSchoolNam());
		// 政治面貌
		tagBasBom.setPolitSts(perCustBaseInfo.getPolitSts());// UI上无输入
		// 兴趣爱好习惯
		tagBasBom.setHobby(null);// UI上无输入-待定

		// 调用自动打标签 TODO
		String json_list = JSONArray.fromObject(tagBasBom).toString();
		String sceneCode = "POBS02";
		@SuppressWarnings("rawtypes")
		Result addTagsRes = restUtil.getForObject(ServiceType.MANAGE, "/crm/manage/engine/analyseFactorMapList?json_list={json_list}&sceneCode={sceneCode}",Result.class,json_list, sceneCode);
		Object tagData = addTagsRes.getData();
		if (tagData != null) {
			Map<String,  List<String>> tagList = (Map<String, List<String>>) JSONObject.toBean(JSONObject.fromObject(tagData),Map.class);
			custTagService.setCustListTag(tagList);
		} else {
			EcCustTag custTag = new EcCustTag();
			custTag.setCustNo(custNo);
			custTag.setAutoFlg(EnumType.MarkTagType.automatic.getValue());
			ecCustTagMapper.deleteByCustNoAutoFlg(custTag);
		}

	}
	/**
	 * Desc: 删除个人客户信息
	 * @param custNo
	 * @author
	 */
	public void delPerCustInfo(String custNo) {
		// check cust info.
		chkPerCustInfo(custNo);

		isDelPerCust(custNo);
		// add 把客户置有效标志置为无效  lijiangcheng 2019-08-19
		ecCustPerMapper.updateRecStat(custNo,EnumType.RecStat.delete.getValue());

//		// ec_cust_per
//		ecCustPerMapper.deleteByCustNo(custNo);
//
//		// ec_cust_name
//		ecCustNameMapper.deleteByCustNo(custNo);
//
//		// ec_cust_cert
//		ecCustCertMapper.deleteByCustNo(custNo);
//
//		// ec_contact_way
//		ecContactWayMapper.deleteByCustNo(custNo);
//
//		// ec_contact_addr
//		ecContactAddrMapper.deleteByCustNo(custNo);
//
//		// ec_cust_edu
//		ecCustEduMapper.deleteByCustNo(custNo);
//
//		// ec_cust_career
//		ecCustCareerMapper.deleteByCustNo(custNo);
//
//		// ec_cust_agent
//		ecCustAgentMapper.deleteByCustNo(custNo);
//
//		// ec_cust_relation
//		ecCustRelationMapper.deleteByCustNo(custNo);
//
//		// ec_cust_claim
//		ecCustClaimMapper.deleteByCustNo(custNo);
//
//		// ec_cust_proposal
//		ecCustProposalMapper.deleteByCustNo(custNo);
//
//		// ec_cust_endorse
//		ecCustEndorseMapper.deleteByCustNo(custNo);
//
//		// ec_cust_complain
//		ecCustComplainMapper.deleteByCustNo(custNo);
//
//		// ec_cust_contact
//		ecCustTouchMapper.deleteByCustNo(custNo);
//
//		// ec_cust_product
//		ecCustProductMapper.deleteByCustNo(custNo);
//
//		// ec_cust_event
//		ecCustEventMapper.deleteByCustNo(custNo);
//
//		// ec_first_act
//		ecFirstActMapper.deleteByCustNo(custNo);
//
//		// ec_finace_pay
//		ecFinacePayMapper.deleteByCustNo(custNo);
//
//		// ec_finace_claim
//		ecFinaceClaimMapper.deleteByCustNo(custNo);
//
//		// ec_risk_per
//		ecRiskPerMapper.deleteByCustNo(custNo);
//
//		// ec_assets_per
//		ecAssetsPerMapper.deleteByCustNo(custNo);
//
//		// ec_integral
//		ecIntegralMapper.deleteByCustNo(custNo);
//
//		// ec_cust_demand
//		ecCustDemandMapper.deleteByCustNo(custNo);
//
//		// ec_health_file
//		ecHealthFileMapper.deleteByCustNo(custNo);
//
//		// ec_audio_video
//		ecAudioVideoMapper.deleteByCustNo(custNo);
//
//		// ec_track_info
//		List<EcTrackInfo> ecTrackInfolst = ecTrackInfoMapper.selectByCustNo(custNo);
//		if (ecTrackInfolst != null) {
//			for (EcTrackInfo obj : ecTrackInfolst) {
//				// ec_track_comt
//				ecTrackComtMapper.deleteByTrackCd(obj.getTrackCd());
//			}
//		}
//		ecTrackInfoMapper.deleteByCustNo(custNo);
//
//		// ec_cust_habitavo
//		ecCustHabitavoMapper.deleteByCustNo(custNo);
//
//		// ec_cust_tag
//		ecCustTagMapper.deleteByCustNo(custNo);
	}

	/**
	 * Desc: 检查客户信息
	 * @param custNo
	 * @author
	 */
	public void chkPerCustInfo(String custNo) {
		EcCustPer ecCustPer = ecCustPerMapper.selectByPrimaryKey(custNo);

		if (ecCustPer == null || ecCustPer.getRecStat().equals(EnumType.RecStat.delete.getValue())) {
			throw new ServiceException("个人信息不存在或者被删除");
		}

		if (ecCustPer.getCustTyp().equals(EnumType.CustType.ent_formal_cust.getValue()) 
				|| ecCustPer.getCustTyp().equals(EnumType.CustType.ent_latent_cust.getValue())) {
			throw new ServiceException("客户类型只能为个人正式客户和个人潜在客户");
		}

		EcCustName ecCustName = ecCustNameMapper.selectByPrimaryKey(custNo);

		if (ecCustName == null || ecCustName.getRecStat().equals(EnumType.RecStat.delete.getValue())) {
			throw new ServiceException("个人姓名信息不存在或者被删除");
		}
	}

	/**
	 * Desc: 客户信息是否能删除
	 * @param custNo
	 * @author
	 */
	public void isDelPerCust(String custNo) {
		EcCustPer ecCustPer = ecCustPerMapper.selectByPrimaryKey(custNo);
		if (ecCustPer == null || ecCustPer.getRecStat().equals(EnumType.RecStat.delete.getValue())) {
			throw new ServiceException("个人信息不存在或者被删除");
		}

		// 客户来源只能为ecif
		if (!ecCustPer.getCustSource().equals(EnumType.DataSource.ecif.getValue())) {
			throw new ServiceException("个人信息来源:"+ EnumType.DataSource.toEnum(ecCustPer.getCustSource()).desc + "不能进行删除");
		}
	}
	
	
	/**
	 * Desc: 查询个人客户列表
	 * @return
	 * @author 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageBean getPerCustListByRole(QueryCustPerParam custper ,List<String> custAgentList) {
		
		Map map = new HashMap();
		map.put("custNo",custper.getCustNo());
		map.put("custName",custper.getCustName());
		map.put("telePhone",custper.getTelephone());
		map.put("certNo", custper.getCertNo());
		map.put("certTyp", custper.getCertTyp());
		map.put("beginAge", custper.getBeginAge());
		map.put("endAge", custper.getEndAge());
		map.put("custAgent",custper.getCustAgent());
		map.put("custAgentList", custAgentList);
		map.put("custTyp",custper.getCustTyp());
//		List<PerCustList> retlst = daoUtil.selectList("cn.com.zsyk.crm.ecif.mapper.EcCustPerMapper.selectPerCustListByRole", map);
		//ecCustPerMapper.selectPerCustListByRole(map);
//		Map<String, String> agentMap = userService.getAgentListMapByEmployeeId();
//
//		if (null != retlst) {
//			for(PerCustList info : retlst) {
//				List<EcCustTag>tagLst = custTagService.selectByCustNo(info.getCustNo());
//				info.setCustTag(tagLst);
//				StringBuffer subbuff = new StringBuffer();
//				String retString ="";
//				if (tagLst != null && tagLst.size() >0) {
//					for (EcCustTag tagObj : tagLst) {
//						if (tagObj.getCustTagNam() == null || StringUtils.isEmpty(tagObj.getCustTagNam())) {
//							continue;
//						}
//						subbuff.append(tagObj.getCustTagNam());
//						subbuff.append(",");
//					}
//					if (StringUtils.isNotEmpty(subbuff) && subbuff != null) {
//						retString = subbuff.substring(0, subbuff.length() - 1);
//					}
//				}
//				info.setCustTagNamStr(retString);
//				// 通过客户经理工号，获取名字
//				info.setCustAgentNam(agentMap.get(info.getCustAgent()));
//			}
//		}
		//add增加分页功能并优化逻辑 lijiangcheng 2019-08-20
		Map<String, String> agentMap = userService.getAgentListMapByEmployeeId();
		PageBean retlst = coreDaoImpl.selectPageByMapper(ecCustPerMapper,"selectPerCustListByRole",map);
		List<PerCustList> retlstList=retlst.getList();
		if (null != retlstList) {
			for(PerCustList info : retlstList) {
				List<EcCustTag>tagLst = custTagService.selectByCustNo(info.getCustNo());
				info.setCustTag(tagLst);
				StringBuffer subbuff = new StringBuffer();
				String retString ="";
				if (tagLst != null && tagLst.size() >0) {
					for (EcCustTag tagObj : tagLst) {
						if (tagObj.getCustTagNam() == null || StringUtils.isEmpty(tagObj.getCustTagNam())) {
							continue;
						}
						subbuff.append(tagObj.getCustTagNam());
						subbuff.append(",");
					}
					if (StringUtils.isNotEmpty(subbuff) && subbuff != null) {
						retString = subbuff.substring(0, subbuff.length() - 1);
					}
				}
				info.setCustTagNamStr(retString);
				// 通过客户经理工号，获取名字
				//info.setCustAgentNam(agentMap.get(info.getCustAgent()));
				if (!StringUtil.isBlank(info.getCustAgent())) {
					Result result = restUtil.getForObject(ServiceType.MANAGE, "/crm/manage/usermng/getUserByEmployeeID?employeeId={employeeId}",Result.class,info.getCustAgent());
					info.setCustAgentNam(((Map<String, String>)result.getData()).get("userName"));
				}
				//获取手机号
				EcContactWay ecContactWay=ecContactWayMapper.selectByCustNoMinSqn(info.getCustNo());
				info.setPhoneNumber(ecContactWay.getPhoneNo1());
			}
		}
		retlst.setList(retlstList);
		return retlst;
	}
	
	/**
	 * Desc: 查询个人客户列表--客户分配
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<PerCustList> getPerCustListByName(EcCustPer custper ,List<String> custAgentList) {
		
		Map map = new HashMap();
		map.put("custName",custper.getCustName());
		map.put("custAgent",custper.getCustAgent());
		map.put("custAgentList", custAgentList);
		List<PerCustList> retlst = daoUtil.selectList("cn.com.zsyk.crm.ecif.mapper.EcCustPerMapper.selectPerCustListByNameDeli", map);
		
		if (null != retlst) {
			for(PerCustList info : retlst) {
				info.setCustTag(custTagService.selectByCustNo(info.getCustNo()));
			}
		} 
		return retlst;
	}
	
	/**
	 * Desc: 查询个人客户列表--客户分配
	 * @return
	 */
	@SuppressWarnings("unused")
	public String updateCustAgent(EcCustPer custper) {
		/** 姓名信息 */
		EcCustPer per = ecCustPerMapper.selectByPrimaryKey(custper.getCustNo());
		if (per == null) {
			throw new ServiceException("待分配客户信息不存在");
		}
		per.setCustAgent(custper.getCustAgent());
		int count = ecCustPerMapper.updateByPrimaryKey(per);				
		
		return custper.getCustNo();
	}
}
