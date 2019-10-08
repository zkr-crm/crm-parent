package cn.com.zsyk.crm.ecif.service.intf.percust;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.zsyk.crm.common.dao.AbstractDao;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.DateUtil;
import cn.com.zsyk.crm.common.util.IdGenerator;
import cn.com.zsyk.crm.ecif.bo.cust.PerCustBaseInfo;
import cn.com.zsyk.crm.ecif.bo.cust.PerCustList;
import cn.com.zsyk.crm.ecif.entity.EcCustName;
import cn.com.zsyk.crm.ecif.entity.EcCustNoCreateParm;
import cn.com.zsyk.crm.ecif.entity.EcCustPer;
import cn.com.zsyk.crm.ecif.entity.EcSameCustRule;
import cn.com.zsyk.crm.ecif.mapper.EcAssetsPerMapper;
import cn.com.zsyk.crm.ecif.mapper.EcAudioVideoMapper;
import cn.com.zsyk.crm.ecif.mapper.EcContactAddrMapper;
import cn.com.zsyk.crm.ecif.mapper.EcContactWayMapper;
import cn.com.zsyk.crm.ecif.mapper.EcCustAgentMapper;
import cn.com.zsyk.crm.ecif.mapper.EcCustCareerMapper;
import cn.com.zsyk.crm.ecif.mapper.EcCustCertMapper;
import cn.com.zsyk.crm.ecif.mapper.EcCustClaimMapper;
import cn.com.zsyk.crm.ecif.mapper.EcCustComplainMapper;
import cn.com.zsyk.crm.ecif.mapper.EcCustDemandMapper;
import cn.com.zsyk.crm.ecif.mapper.EcCustEduMapper;
import cn.com.zsyk.crm.ecif.mapper.EcCustEndorseMapper;
import cn.com.zsyk.crm.ecif.mapper.EcCustEntMapper;
import cn.com.zsyk.crm.ecif.mapper.EcCustEventMapper;
import cn.com.zsyk.crm.ecif.mapper.EcCustHabitavoMapper;
import cn.com.zsyk.crm.ecif.mapper.EcCustNameMapper;
import cn.com.zsyk.crm.ecif.mapper.EcCustPerMapper;
import cn.com.zsyk.crm.ecif.mapper.EcCustProductMapper;
import cn.com.zsyk.crm.ecif.mapper.EcCustProposalMapper;
import cn.com.zsyk.crm.ecif.mapper.EcCustRelationMapper;
import cn.com.zsyk.crm.ecif.mapper.EcCustTagMapper;
import cn.com.zsyk.crm.ecif.mapper.EcFinaceClaimMapper;
import cn.com.zsyk.crm.ecif.mapper.EcFinacePayMapper;
import cn.com.zsyk.crm.ecif.mapper.EcFirstActMapper;
import cn.com.zsyk.crm.ecif.mapper.EcHealthFileMapper;
import cn.com.zsyk.crm.ecif.mapper.EcIntegralMapper;
import cn.com.zsyk.crm.ecif.mapper.EcRiskPerMapper;
import cn.com.zsyk.crm.ecif.mapper.EcTrackComtMapper;
import cn.com.zsyk.crm.ecif.mapper.EcTrackInfoMapper;
import cn.com.zsyk.crm.ecif.service.customer.portrait.CustCareerService;
import cn.com.zsyk.crm.ecif.service.customer.portrait.CustCertService;
import cn.com.zsyk.crm.ecif.service.customer.portrait.CustContactService;
import cn.com.zsyk.crm.ecif.service.customer.portrait.CustTagService;
import cn.com.zsyk.crm.generator.EnumType;

@Service
@Transactional 
public class PerCustCreateService {
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
	CustCareerService custCareerService;
	@Autowired
	private AbstractDao daoUtil;
	
	private String validCheck(EcCustNoCreateParm parm) {
//		1、客户姓名不能为空，不能包含特殊字符
		String tmpname = parm.getCustNam();
		if(tmpname.trim() =="" || tmpname == null) {
			return "客户姓名不能为空";
		}
		boolean flag = CharacterCheck.validateLegalString(tmpname);
		if(!flag) {
			return "客户姓名不能包含特殊字符";
		}
//		2、客户性别不能为空，必须为“男”或“女”或“其他”
		String sex = parm.getSex();
		if(sex.trim() =="" || sex == null) {
			return "客户性别不能为空";
		}else if(sex.trim().equals(EnumType.Sex.female.getValue()) || sex.trim().equals(EnumType.Sex.male.getValue()) ){
			
		}else {
			return "客户性别必须为“男”或“女”";
		}
//		3、客户出生日期不能为空，格式必须为”19000101”格式，日期必须小于当前系统日期
		String strbirthdate = parm.getBirthDate();
		if(strbirthdate.trim() =="" || strbirthdate == null) {
			return "客户出生日期不能为空";
		}
		Date day=new Date();  
		
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		//System.out.println(df.format(strbirthdate)); 
		try {
			System.out.println(df.parse(strbirthdate));
			Date birthdate = df.parse(strbirthdate);
			if(day.before(birthdate)) {
				return "客户出生日期必须小于当前日期";
			}
			//ecBlackList.setRegiDate(DateUtil.date2FormatStringYMD(df.format(day)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "客户出生日期不能为空，格式必须为”19000101”格式";
		}
		
//		4、证件类型和证件号码不能为空
		String certno = parm.getCertNo();
		String certtyp = parm.getCertTyp();
		if(certno.trim() =="" || certno == null) {
			return "客户证件号码不能为空";
		}
		if(certtyp.trim() =="" || certtyp == null) {
			return "客户证件类型不能为空";
		}else {
			if(EnumType.IdType.toEnum(certtyp.trim()) == null ) {
				return "证件类型必须属于系统支持证件类型";
			}
		}
//		5、证件类型必须为系统提供的证件类型，如果证件类型为身份证，如果是15位将15位转换为18位身份证进行合法性校验
//		7、判断证件类型如果为身份证、出生证明、户口本中的一种，且证件号码为15位或18位，转换为18位后是否相同，相同则返回已有客户号，不相同则继续判断；
//		8、判断在已有的为15位或18位证件号码的证件，转换为18位后是否相同，不相同则创建返回新客户号，相同则返回已有客户号；
//		若上面任意一个步骤查询到多个客户号，返回最新创建的客户号
		if(certtyp.trim() == EnumType.IdType.id_card.getValue() ) {
			if(certno.trim().length() == 15) {
				try {
					String idcertno = IdentificationCodeUtils.transIDCard15to18(certno.trim());
					parm.setCertNo(idcertno);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					return "客户证件号码不合法的15位";
					//e.printStackTrace();
				}
			}
		}
		
		return "true";
	}
	
	
	/**
	 * 创建个人客户
	 * @param parm
	 * @return
	 */
	public String createCustNoBy(EcCustNoCreateParm parm, boolean bCreateCustFlag) {
		String newCustNo = ""; //新的客户号
		parm.setCustTyp(EnumType.CustType.per_formal_cust.getValue()); //个人客户
		
		//校验合法性 创建新客户时校验
		if(bCreateCustFlag) {
			String checkRslt = validCheck(parm); 
			if(checkRslt == "true") {
				
			}else {
				return checkRslt;
			}
			
		}
		
		//查询规则
		List<EcSameCustRule> retlst = ecCustPerMapper.selectSameCustRule();
		if (null == retlst || retlst.size() <= 0) {
			return "没有对应的相同客户判断规则，无法获取客户号";
		}
		EcCustNoCreateParm queryEcCustNoCreateParm = new EcCustNoCreateParm() ; //设置输入参数组装，以根据条件是否有重复的客户号
		if (null != retlst) {
			for(EcSameCustRule info : retlst) {
				queryEcCustNoCreateParm = new EcCustNoCreateParm(); //每次都设初始值
				String judgeCon = info.getJudgeCondition();
				String[] field = judgeCon.split(",");
				int confirmSucc = 0; //满足规则条件的字段个数，如果等于 规则字符串的长度，认为可以使用此规则
				for(int i =0; i < field.length ; i++) { 
					//custName,birthDate,sex,certTyp,certNo 
					if("custName".equals(field[i])) {
						if(parm.getCustNam() == "" || parm.getCustNam() == null) {
							
						}else {
							queryEcCustNoCreateParm.setCustNam(parm.getCustNam());
							confirmSucc ++ ;
						}
					}
					if("birthDate".equals(field[i])) {
						if(parm.getBirthDate() == null) {
							
						}else {
							confirmSucc ++ ;
							//待调整设置 日期的查询组装 todo
							queryEcCustNoCreateParm.setBirthDate(parm.getBirthDate());
						}
					}
					if("sex".equals(field[i])) {
						if(parm.getSex() == "" || parm.getSex() == null) {
							
						}else {
							confirmSucc ++ ;
							queryEcCustNoCreateParm.setSex(parm.getSex());
						}
					}
					if("certTyp".equals(field[i])) {
						if(parm.getCertTyp() == "" || parm.getCertTyp() == null) {
							
						}else {
							confirmSucc ++ ;
							queryEcCustNoCreateParm.setCertTyp(parm.getCertTyp());
						}
					}
					if("certNo".equals(field[i])) {
						if(parm.getCertNo() == "" || parm.getCertNo() == null) {
							
						}else {
							confirmSucc ++ ;
							queryEcCustNoCreateParm.setCertNo(parm.getCertNo());
						}
					}
				
				}
				//满足规则的条件
				if(confirmSucc == field.length) {
					//跳出循环
					break;
				}
			}
		} 
		//查询客户号
//		6、根据客户姓名、性别、出生日期，判断ECIF数据库中是否有相同的记录，如果不存在则创建新的客户号；如果存在相同则继续判断；
		List<PerCustList> queryhaveNoList = ecCustPerMapper.selectHaveCustNo(queryEcCustNoCreateParm);
		if (!queryhaveNoList.isEmpty() && queryhaveNoList != null) {
			//存在相同的客户号，返回
			for(PerCustList qinfo : queryhaveNoList) {
				newCustNo = qinfo.getCustNo();
				break;
			}
			
		}else {
			//如果只是判断是否存在相同的客户，而不是创建则返回null -未查询到客户，给请求方返回null
			if(!bCreateCustFlag) {
				return null;
			}
			//如果没有生成新的客户号
			newCustNo = String.valueOf(IdGenerator.getDistributedID());
			//保存数据
			PerCustBaseInfo custbaseinfo = new PerCustBaseInfo();
			custbaseinfo.setCustNo(newCustNo);
		    custbaseinfo.setCustName(parm.getCustNam());
		    custbaseinfo.setSex(parm.getSex());
		    custbaseinfo.setCustTyp(parm.getCustTyp());
		    SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		    try {
				System.out.println(df.parse(parm.getBirthDate()));
				Date birthdate = df.parse(parm.getBirthDate());
				custbaseinfo.setBirthDate(birthdate);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    custbaseinfo.setCertNo(parm.getCertNo());
		    custbaseinfo.setCertTyp(parm.getCertTyp());
		    custbaseinfo.setPhoneNumber(parm.getPhoneNo1());
			//生成客户信息
		    try {
				addPerCustInfo(custbaseinfo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return newCustNo;
		
	}
	
	
	/**
	 * 新增客户的信息
	 */
	private void addPerCustInfo(PerCustBaseInfo perCustBaseInfo) throws Exception {

		if (null == perCustBaseInfo) {
			throw new ServiceException("参数不能为空");
		}

		if (StringUtils.isEmpty(perCustBaseInfo.getCustTyp())) {
			throw new ServiceException("客户类型不能为空");
		}

		if (StringUtils.isEmpty(perCustBaseInfo.getSex())) {
			throw new ServiceException("性别不能为空");
		}

		if (StringUtils.isEmpty(perCustBaseInfo.getCustName())) {
			throw new ServiceException("姓名不能为空");
		}

		if (perCustBaseInfo.getBirthDate() == null) {
			throw new ServiceException("出生日期不能为空");
		}

		/** 基本信息 */
		// 客户号
		//String custNo = String.valueOf(IdGenerator.getDistributedID());
		perCustBaseInfo.setCustNo(perCustBaseInfo.getCustNo());
		EcCustPer ecCustPer = new EcCustPer();
		// 客户号 custNo
		ecCustPer.setCustNo(perCustBaseInfo.getCustNo());
		// 性别 sex;
		ecCustPer.setSex(perCustBaseInfo.getSex());
		// 出生日期 birthDate;
		ecCustPer.setBirthDate(perCustBaseInfo.getBirthDate());
		// 客户经理 custAgent;
		if (StringUtils.isNotEmpty(perCustBaseInfo.getCustAgent())) {
			ecCustPer.setCustAgent(perCustBaseInfo.getCustAgent());
		}
		// 客户类型 custTyp;
		if (StringUtils.isNotEmpty(perCustBaseInfo.getCustTyp())) {
			ecCustPer.setCustTyp(perCustBaseInfo.getCustTyp());
		}
		// 客户来源 custSource;
		if (StringUtils.isNotEmpty(perCustBaseInfo.getCustSource())) {
			ecCustPer.setCustSource(perCustBaseInfo.getCustSource());
		}
		// 是否为重点 keyCustFlg;
		if (StringUtils.isNotEmpty(perCustBaseInfo.getCustSource())) {
			ecCustPer.setKeyCustFlg(perCustBaseInfo.getKeyCustFlg());
		} else {
			ecCustPer.setKeyCustFlg(EnumType.YesNoFlg.no.getValue());
		}
		// 下次跟进时间 nextFollowUpTm;
		if (StringUtils.isNotEmpty(perCustBaseInfo.getNextFollowUpTm())) {
			ecCustPer.setNextFollowUpTm(DateUtil.formatString2Date(perCustBaseInfo.getNextFollowUpTm()));
		}
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
//		if (StringUtils.isNotEmpty(perCustBaseInfo.getPerIconUrl())) {
//			ecCustPer.setPerIconUrl(perCustBaseInfo.getPerIconUrl());
//		}
		// 黑名单标识		blacklist_flg
		ecCustPer.setBlacklistFlg(EnumType.YesNoFlg.no.getValue());
		// 可见范围		visible_range
		ecCustPer.setVisibleRange(EnumType.VisibleRange.all.getValue());
		// 可以证实年龄的证件标识		cnf_age_cert_flg
		ecCustPer.setCnfAgeCertFlg(EnumType.YesNoFlg.yes.getValue());
		ecCustPer.setTimeStamp(new Date());
		ecCustPerMapper.insert(ecCustPer);

		/** 姓名信息 */
		if (perCustBaseInfo.getCustName() != null) {
//			EcCustName ecCustName = ecCustNameMapper.selectByPrimaryKey(custNo);
//			if (ecCustName != null) {
//				if (ecCustName.getRecStat().equals(EnumType.RecStat.delete.getValue())) {
//					ecCustName.setRecStat(EnumType.RecStat.normal.getValue());
//				}
//				ecCustName.setCustNam(perCustBaseInfo.getCustName());
//				ecCustNameMapper.updateByPrimaryKey(ecCustName);
//			} else {
				EcCustName nameObj = new EcCustName();
				// 	客户号		cust_no
				nameObj.setCustNo(perCustBaseInfo.getCustNo());
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
//			}
		}

		/** 证件信息 */
		custCertService.saveOrUpdateCert(perCustBaseInfo);

		/** 联系方式信息 */
		if (StringUtils.isEmpty(perCustBaseInfo.getPhoneNumber())) {
		}else {
			custContactService.saveOrUpdateContactAddr(perCustBaseInfo);
		}
		

		/** 职业信息 */
		custCareerService.saveOrUpdateCareer(perCustBaseInfo);

		/** 居住地址信息 */
		custContactService.saveOrUpdateHomeAddr(perCustBaseInfo);

		/** 单位地址信息 */
		custContactService.saveOrUpdateUnitAddr(perCustBaseInfo);
	}
	 

}
