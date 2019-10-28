package cn.com.zsyk.crm.ecif.service.customer.blacklist;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.zsyk.crm.common.dto.ContextContainer;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.DateUtil;
import cn.com.zsyk.crm.common.util.LogUtil;
import cn.com.zsyk.crm.ecif.entity.EcBlackList;
import cn.com.zsyk.crm.ecif.entity.EcCustPer;
import cn.com.zsyk.crm.ecif.mapper.EcBlackListMapper;
import cn.com.zsyk.crm.ecif.mapper.EcCustPerMapper;
import cn.com.zsyk.crm.ecif.service.customer.integrals.CustIntegralService;
import cn.com.zsyk.crm.generator.EnumType;

@Service
@Transactional 
public class BlacklistService {
	/** logger */
	private final Log logger = LogUtil.getLogger(CustIntegralService.class);
	@Autowired
	private EcCustPerMapper ecCustPerMapper;
	/*@Autowired
	private EcCustEntMapper ecCustEntMapper;*/
	@Autowired
	private EcBlackListMapper ecBlackListMapper;
	/**
	 * Desc: 查询所有黑名单
	 * @return
	 * @author
	 */
	public List<EcBlackList> selectBlackList() {
		List<EcBlackList> retlst = ecBlackListMapper.selectAll();
 		return retlst;
	}

	/**
	 * Desc: 校验客户是否为黑名单
	 * @param custNo
	 * @return
	 * @author
	 */
	public boolean isVaildBlacklist(String custNo) {
		if (StringUtils.isEmpty(custNo)) {
			throw new ServiceException("客户号不能为空");
		}

		String blacklistFlg = "";
		EcCustPer ecCustPer = ecCustPerMapper.selectByPrimaryKey(custNo);
		if (ecCustPer == null) {
			//EcCustEnt ecCustEnt = ecCustEntMapper.selectByPrimaryKey(custNo);
			/*if (ecCustEnt == null) {
				throw new ServiceException("客户不存在");
			} {
				blacklistFlg = ecCustEnt.getBlacklistFlg();
			}*/
		} else {
			blacklistFlg = ecCustPer.getBlacklistFlg();
		}

		logger.info("blacklistFlg:" + blacklistFlg);
		if (blacklistFlg.equals(EnumType.YesNoFlg.yes.getValue())) {
			return true;
		}

		return false;
	}

	/**
	 * Desc: 设置/取消客户黑名单
	 * @return
	 * @author
	 * @throws ParseException 
	 */
	public void setBlackList(String custNo, String blacklistSts, String reason,String blacklistType) {
		if (StringUtils.isEmpty(custNo)) {
			throw new ServiceException("客户号不能为空");
		}

		if (StringUtils.isEmpty(blacklistSts)) {
			throw new ServiceException("黑名单标志不能为空");
		}

		if (StringUtils.isEmpty(reason)) {
			throw new ServiceException("黑名单原因不能为空");
		}
		//设置时判断黑名单类型
		if (blacklistSts.equals(EnumType.BlacklistStatus.normal.getValue())) {
			if (StringUtils.isEmpty(blacklistType)) {
				throw new ServiceException("黑名单类型不能为空");
			}
		}

		String blacklistFlg = "";
		// 客户类型
		String custType = "";
		EcCustPer ecCustPer = ecCustPerMapper.selectByPrimaryKey(custNo);
		if (ecCustPer == null) {
			/*EcCustEnt ecCustEnt = ecCustEntMapper.selectByPrimaryKey(custNo);
			if (ecCustEnt == null) {
				throw new ServiceException("客户不存在");
			} {
				blacklistFlg = ecCustEnt.getBlacklistFlg();
				custType = ecCustEnt.getCustTyp();
			}*/
			throw new ServiceException("客户不存在");
		} else {
			blacklistFlg = ecCustPer.getBlacklistFlg();
			custType = ecCustPer.getCustTyp();
		}

		logger.info("blacklistFlg:" + blacklistFlg);
		logger.info("custType:" + custType);

		// 黑名单撤销
		if (blacklistSts.equals(EnumType.BlacklistStatus.blacklist.getValue())) {
			if (blacklistFlg.equals(EnumType.BlacklistStatus.blacklist.getValue())){
				/*if (custType.equals(EnumType.CustType.ent_formal_cust.getValue()) || custType.equals(EnumType.CustType.ent_latent_cust.getValue())) {
					EcCustEnt record = ecCustEntMapper.selectByPrimaryKey(custNo);
					record.setBlacklistFlg(EnumType.YesNoFlg.no.getValue());
					ecCustEntMapper.updateByPrimaryKey(record);
				}*/
				if (custType.equals(EnumType.CustType.per_formal_cust.getValue()) || custType.equals(EnumType.CustType.per_latent_cust.getValue())) {
					EcCustPer record = ecCustPerMapper.selectByPrimaryKey(custNo);
					record.setBlacklistFlg(EnumType.YesNoFlg.no.getValue());
					ecCustPerMapper.updateByPrimaryKey(record);
				}
				EcBlackList ecBlackList = ecBlackListMapper.selectByPrimaryKey(custNo);
				ecBlackList.setBlacklistSts(EnumType.BlacklistStatus.normal.getValue());
				ecBlackList.setCancelReason(reason);
				ecBlackList.setCancelUser(ContextContainer.getContext().getUserId());
				Date day=new Date();    
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd"); 
				
				System.out.println(df.format(day));   
				try {
					ecBlackList.setCancelDate(DateUtil.date2FormatStringYMD(df.format(day)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ecBlackListMapper.updateByPrimaryKeyforcancel(ecBlackList);
			} else {
				throw new ServiceException("客户号不是黑名单客户");
			}
		}

		// 黑名单设置
		if (blacklistSts.equals(EnumType.BlacklistStatus.normal.getValue())) {
			if (blacklistFlg.equals(EnumType.BlacklistStatus.normal.getValue())){
				EcBlackList ecBlackList = new EcBlackList();
				
				//if (custType.equals(EnumType.CustType.per_formal_cust.getValue()) || custType.equals(EnumType.CustType.per_formal_cust.getValue())) {
				EcCustPer record = ecCustPerMapper.selectByPrimaryKey(custNo);
				record.setBlacklistFlg(EnumType.YesNoFlg.yes.getValue());
				ecCustPerMapper.updateByPrimaryKey(record);
				//}
				
				ecBlackList.setCustNo(custNo);
				ecBlackList.setBlacklistSts(EnumType.BlacklistStatus.blacklist.getValue());
				ecBlackList.setRegiReason(reason);
				ecBlackList.setRegiUser(ContextContainer.getContext().getUserId());
				//增加 黑名单类型、黑名单来源
				ecBlackList.setBlacklistType(blacklistType);
				ecBlackList.setBlacklistSrc(EnumType.BlacklistSrc.src_rs.getValue());
				
				Date day=new Date();    
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd"); 
				
				System.out.println(df.format(day));   
				try {
					ecBlackList.setRegiDate(DateUtil.date2FormatStringYMD(df.format(day)));
					/*ecBlackList.setCreateDate(DateUtil.date2FormatStringYMD(df.format(day)));
					ecBlackList.setCreateTime(DateUtil.date2FormatStringYMD(df.format(day)));
					ecBlackList.setCreateUser("ddd");
					ecBlackList.setRecStat("0");*/
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				/*EcBlackList haveRec = ecBlackListMapper.selectByPrimaryKey(custNo);
				if(haveRec == null) {
					ecBlackList.setCustTyp(custType);
					ecBlackListMapper.insert(ecBlackList);
				}else {
					ecBlackListMapper.updateByPrimaryKey(ecBlackList);
				}*/
				//添加流水号，支持历史记录保留，只更新最新的记录 todo
				ecBlackList.setCustTyp(custType);
				Date dayseq =new Date();    
				SimpleDateFormat dfseq = new SimpleDateFormat("yyyyMMddHHmmss"); 
				
				System.out.println(dfseq.format(day));
				
//				ecBlackList.setUpdSeqno(dfseq.format(dayseq));
				//ecBlackList.setUpdSeqno( );  //  更新流水好 20180216
				EcBlackList ecBlackListOld = ecBlackListMapper.selectByPrimaryKey(custNo);
				if(ecBlackListOld==null){
					ecBlackListMapper.insert(ecBlackList);
				}else{
					ecBlackListMapper.updateByPrimaryKey(ecBlackList);
				}

				
			} else {
				throw new ServiceException("客户号已经是黑名单客户");
			}
		}
	}
}
