package cn.com.zsyk.crm.ecif.service.customer.key;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.zsyk.crm.common.dao.CoreDaoImpl;
import cn.com.zsyk.crm.common.dto.PageBean;
import cn.com.zsyk.crm.ecif.entity.EcContactWay;
import cn.com.zsyk.crm.ecif.mapper.EcContactWayMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.com.zsyk.crm.common.dto.ContextContainer;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.DateUtil;
import cn.com.zsyk.crm.ecif.bo.cust.PerCustList;
import cn.com.zsyk.crm.ecif.entity.EcCustKey;
import cn.com.zsyk.crm.ecif.entity.EcCustPer;
import cn.com.zsyk.crm.ecif.mapper.EcCustKeyMapper;
import cn.com.zsyk.crm.ecif.mapper.EcCustPerMapper;
import cn.com.zsyk.crm.ecif.service.user.UserService;
import cn.com.zsyk.crm.generator.EnumType;

@Service
@Transactional 
public class KeyCustService {
	@Autowired
	EcCustPerMapper ecCustPerMapper;
	@Autowired
	EcCustKeyMapper ecCustKeyMapper;
	@Autowired
	UserService userService;
	/* 分页查询对象 */
	@Autowired
	private CoreDaoImpl coreDaoImpl;
	@Autowired
	private EcContactWayMapper ecContactWayMapper;

	public List<EcCustPer> getKeyCustList() {
		return null;
	}
	/**
	 * 查询重点客户人员
	 * @param custper
	 * @return
	 */
	public PageBean selectImportantPerCustList(EcCustPer custper){
		//List<PerCustList> retlst = ecCustPerMapper.selectImportantPerCustList(custper);
		PageBean pageRetlst = coreDaoImpl.selectPageByMapper(ecCustPerMapper,"selectImportantPerCustList",custper);
		List<PerCustList> retlst=pageRetlst.getList();
		Map<String, String> agentMap = userService.getAgentListMapByEmployeeId();
		if (retlst != null) {
			for (PerCustList obj : retlst) {
				if (StringUtils.isNotEmpty(obj.getCustAgent())) {
					obj.setCustAgentNam(agentMap.get(obj.getCustAgent()));
				}
				EcContactWay ecContactWay=ecContactWayMapper.selectByCustNoMinSqn(obj.getCustNo());
				obj.setPhoneNumber(ecContactWay.getPhoneNo1());
			}
		}
		return pageRetlst;
	}
	/**
	 * 查询非重点客户人员
	 * @param custper
	 * @return
	 */
	public PageBean selectNotImportantPerCustList(EcCustPer custper){
		//List<PerCustList> retlst = ecCustPerMapper.selectNotImportantPerCustList(custper);
		System.out.println(">>>>>>>>>>>>>>>>>>重点客户开始查询："+DateUtil.nowDateTimeStamp());
		PageBean pageRetlst = coreDaoImpl.selectPageByMapper(ecCustPerMapper,"selectNotImportantPerCustList",custper);
		System.out.println(">>>>>>>>>>>>>>>>>>重点客户查询结束："+DateUtil.nowDateTimeStamp());
		Map<String, String> agentMap = userService.getAgentListMapByEmployeeId();
		System.out.println(">>>>>>>>>>>>>>>>>>重点客户客户经理查询结束："+DateUtil.nowDateTimeStamp());
		List<PerCustList> retlst=pageRetlst.getList();
		if (retlst != null) {
			for (PerCustList obj : retlst) {
				if (StringUtils.isNotEmpty(obj.getCustAgent())) {
					obj.setCustAgentNam(agentMap.get(obj.getCustAgent()));
				}
				//获取手机号
				EcContactWay ecContactWay=ecContactWayMapper.selectByCustNoMinSqn(obj.getCustNo());
				obj.setPhoneNumber(ecContactWay.getPhoneNo1());
			}
		}
		System.out.println(">>>>>>>>>>>>>>>>>>重点客户客户经理翻译结束："+DateUtil.nowDateTimeStamp());
		return pageRetlst;
		
	}
	
	public void setKeyCustomer(String custNo, String blacklistSts, String reason) {
		
		if (StringUtils.isEmpty(custNo)) {
			throw new ServiceException("客户号不能为空值");
		}

//		if (StringUtils.isEmpty(blacklistSts)) {
//			throw new ServiceException("重点客户标志不能为空");
//		}

		if (StringUtils.isEmpty(reason)) {
			throw new ServiceException("重点客户登记原因不能为空值");
		}

		String keyCustFlg = "";
		// 客户类型
		String custType = "";
		EcCustPer ecCustPer = ecCustPerMapper.selectByPrimaryKey(custNo);
		if (ecCustPer == null) {
			throw new ServiceException("客户不存在");
		} else {
			keyCustFlg = ecCustPer.getKeyCustFlg();
			custType = ecCustPer.getCustTyp();
			if(keyCustFlg==null){
				keyCustFlg=EnumType.BlacklistStatus.normal.getValue();
			}
		}
		
		// 重点客户设置
		if (blacklistSts.equals(EnumType.BlacklistStatus.normal.getValue())) {
			if (keyCustFlg.equals(EnumType.BlacklistStatus.normal.getValue())){
				EcCustKey ecCustKey = new EcCustKey();
				
				EcCustPer record = ecCustPerMapper.selectByPrimaryKey(custNo);
				record.setKeyCustFlg(EnumType.YesNoFlg.yes.getValue());
				ecCustPerMapper.updateByPrimaryKey(record);
				
				ecCustKey.setCustNo(custNo);
				ecCustKey.setKeyCustFlg(EnumType.BlacklistStatus.blacklist.getValue());
				ecCustKey.setRegiReason(reason);
				ecCustKey.setRegiUser(ContextContainer.getContext().getUserId());
				Date day=new Date();    
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd"); 
				
				System.out.println(df.format(day));   
				try {
					ecCustKey.setRegiDate(DateUtil.date2FormatStringYMD(df.format(day)));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				EcCustKey haveRec = ecCustKeyMapper.selectByPrimaryKey(custNo);
				if(haveRec == null) {
					ecCustKey.setCustTyp(custType);
					ecCustKeyMapper.insert(ecCustKey);
				}else {
					ecCustKeyMapper.updateByPrimaryKey(ecCustKey);
				}
				
			} else {
				throw new ServiceException("客户号已经是重点客户");
			}
		}
		// 重点客户撤销
				if (blacklistSts.equals(EnumType.BlacklistStatus.blacklist.getValue())) {
					if (keyCustFlg.equals(EnumType.BlacklistStatus.blacklist.getValue())){
						
						EcCustPer record = ecCustPerMapper.selectByPrimaryKey(custNo);
						record.setKeyCustFlg(EnumType.YesNoFlg.no.getValue());
						ecCustPerMapper.updateByPrimaryKey(record);
						
						EcCustKey haveRec = ecCustKeyMapper.selectByPrimaryKey(custNo);
						haveRec.setKeyCustFlg(EnumType.BlacklistStatus.normal.getValue());
						haveRec.setCancelReason(reason);
						haveRec.setCancelUser(ContextContainer.getContext().getUserId());
						ecCustKeyMapper.updateByPrimaryKey(haveRec);
					} else {
						throw new ServiceException("客户号不是重点客户");
					}
				}
	}
	
}
