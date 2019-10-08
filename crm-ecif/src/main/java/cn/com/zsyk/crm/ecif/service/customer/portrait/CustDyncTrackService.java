package cn.com.zsyk.crm.ecif.service.customer.portrait;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.com.zsyk.crm.common.dto.ContextContainer;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.BeanUtil;
import cn.com.zsyk.crm.common.util.DateUtil;
import cn.com.zsyk.crm.common.util.IdGenerator;
import cn.com.zsyk.crm.common.util.LogUtil;
import cn.com.zsyk.crm.common.util.RestUtil;
import cn.com.zsyk.crm.ecif.entity.EcCustPer;
import cn.com.zsyk.crm.ecif.entity.EcTrackInfo;
import cn.com.zsyk.crm.ecif.mapper.EcCustPerMapper;
import cn.com.zsyk.crm.ecif.mapper.EcTrackComtMapper;
import cn.com.zsyk.crm.ecif.mapper.EcTrackInfoMapper;
import cn.com.zsyk.crm.generator.EnumType;
@SuppressWarnings("rawtypes")
@Service
@Transactional 
public class CustDyncTrackService {
	/** logger */
	private final Log logger = LogUtil.getLogger(CustDyncTrackService.class);

	@Autowired
	private EcTrackInfoMapper ecTrackInfoMapper;
	@Autowired
	private EcTrackComtMapper ecTrackComtMapper;
	@Autowired
	private RestUtil restUtil;
	@Autowired
	private EcCustPerMapper ecCustPerMapper;
	/**
	 * Desc: 查询客户动态轨迹列表
	 * @param custNo
	 * @return
	 * @author
	 */
	public List<EcTrackInfo> selectCustDyncTrackList(EcTrackInfo ecTrackInfo) {
		if (ecTrackInfo == null) {
			throw new ServiceException("客户轨迹筛选参数不能为空");
		}
		String custNo = ecTrackInfo.getCustNo();
		if (StringUtils.isEmpty(custNo)) {
			throw new ServiceException("客户号不能为空");
		}
		List<EcTrackInfo> ecTrackInfolst = ecTrackInfoMapper.selectCustDyncTrackList(ecTrackInfo);
		List<EcTrackInfo> retList = new ArrayList<EcTrackInfo>();
		for (EcTrackInfo obj : ecTrackInfolst) {
			if (obj.getComtFlg().equals(EnumType.YesNoFlg.yes.value)) {
				obj.setTrackComtList(ecTrackComtMapper.selectByTrackCd(obj.getTrackCd()));
			}
			retList.add(obj);
		}
		return retList;
	}

	/**
	 * Desc: 查询客户动态轨迹信息(单条)
	 * @param trackCd
	 * @return
	 * @author
	 */
	public Object selectCustDyncTrackOne(String trackCd) {
		if (StringUtils.isEmpty(trackCd)) {
			throw new ServiceException("轨迹编号不能为空");
		}
		EcTrackInfo ecTrackInfo = ecTrackInfoMapper.selectByPrimaryKey(trackCd);
		if (ecTrackInfo != null) {
			logger.info("ComtFlg:" + ecTrackInfo.getComtFlg());
			if (ecTrackInfo.getComtFlg().equals(EnumType.YesNoFlg.yes.value)) {
				ecTrackInfo.setTrackComtList(ecTrackComtMapper.selectByTrackCd(ecTrackInfo.getTrackCd()));
			}
		}
		return ecTrackInfo;
	}

	/**
	 * Desc: 新增客户电话动态轨迹信息
	 * @param ecTrackInfo
	 * @author
	 */
	public void addDyncTrackTel(EcTrackInfo ecTrackInfo) {
		if (ecTrackInfo == null) {
			throw new ServiceException("动态轨迹信息为空");
		}
		//		轨迹类型
		ecTrackInfo.setTrackTyp(EnumType.TrackType.call_out.getValue());

		this.addDyncTrack(ecTrackInfo);
	}

	/**
	 * Desc: 新增客户短信动态轨迹信息
	 * @param ecTrackInfo
	 * @author
	 */
	public void addDyncTrackSms(EcTrackInfo ecTrackInfo) {
		if (ecTrackInfo == null) {
			throw new ServiceException("动态轨迹信息为空");
		}
		//		客户号
		ecTrackInfo.setCustNo(ecTrackInfo.getCustNo());
		//		轨迹类型
		ecTrackInfo.setTrackTyp(EnumType.TrackType.info_push.getValue());
		ecTrackInfo.setTrackSubTyp(EnumType.TrackSubType.SMS_push.getValue());

		this.addDyncTrack(ecTrackInfo);
		// TODO 调用短信发送
		List<Map> example_list = new ArrayList<Map>();
//		MsgInfo msgInfo = new MsgInfo();
//		msgInfo.setBusiCode("1111");
//		msgInfo.setChannel("01");
//		msgInfo.setCustName("00000");
//		msgInfo.setMobile("15940665066");
//		msgInfo.setSendUser("admin");
//		msgInfo.setBusiCode("213231");
//		msgInfo.setCustGroupCode("dsfsdfs");
//		msgInfo.setEmailAddr("16@1.com");
//		msgInfo.setParams("dddd");
//		msgInfo.setWeChat("4554562");
//		Map map = new HashMap();
//		map.put("busiCode", "1111");
//		map.put("channel", "01");
//		map.put("custName", "00000");
//		map.put("mobile", "15940665066");
//		map.put("sendUser", "admin");
//		Result response = restUtil.getForObject("http://127.0.0.1:8082/crm/manage/msgmng/sendMsg", Result.class,
//				map);
//		example_list = (List<Map>) response.getData();
//		System.out.println(example_list.size());
	}

	/**
	 * Desc: 新增客户拜访动态轨迹信息
	 * @param ecTrackInfo
	 * @author
	 */
	public void addDyncTrackVis(EcTrackInfo ecTrackInfo) {
		if (ecTrackInfo == null) {
			throw new ServiceException("动态轨迹信息为空");
		}
		//		轨迹类型
		ecTrackInfo.setTrackTyp(EnumType.TrackType.cust_event.getValue());
		ecTrackInfo.setTrackSubTyp(EnumType.TrackSubType.call_on.getValue());
		this.addDyncTrack(ecTrackInfo);
	}

	/**
	 * Desc: 新增客户动态轨迹信息
	 * @param ecTrackInfo
	 * @author
	 */
	public void addDyncTrack(EcTrackInfo ecTrackInfo) {
		if (ecTrackInfo == null) {
			throw new ServiceException("动态轨迹信息为空");
		}
		// 客户号
		if (StringUtils.isEmpty(ecTrackInfo.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}
		//		轨迹编号
		ecTrackInfo.setTrackCd(String.valueOf(IdGenerator.getSeqID("TrackCd")));
		EcTrackInfo obj = ecTrackInfoMapper.selectByPrimaryKey(ecTrackInfo.getTrackCd());
		if (obj != null) {
			throw new ServiceException("客户动态轨迹信息已经存在");
		}
		//		轨迹时间
		ecTrackInfo.setTrackTime(DateUtil.getCurTime());
		if (ecTrackInfo.getNextFollowUpTm() != null) {
			if (ecTrackInfo.getNextFollowUpTm().before(ecTrackInfo.getTrackTime())) {
				throw new ServiceException("下次跟进日期必须在当前时间之后");
			}
		}
		ecTrackInfo.setRecStat(EnumType.RecStat.normal.getValue());
		ecTrackInfo.setRecordUser(ContextContainer.getContext().getUserId());
		//		是否接通
		//		联系人
		//		下次跟进时间
		if (ecTrackInfo.getNextFollowUpTm() != null) {
			EcCustPer custPerobj = ecCustPerMapper.selectByPrimaryKey(ecTrackInfo.getCustNo());
			custPerobj.setNextFollowUpTm(ecTrackInfo.getNextFollowUpTm());
			ecCustPerMapper.updateByPrimaryKey(custPerobj);
		}
		//		评论标识
		ecTrackInfo.setComtFlg(EnumType.YesNoFlg.no.getValue());
		ecTrackInfoMapper.insert(ecTrackInfo);
	}

	/**
	 * Desc: 修改客户动态轨迹信息
	 * @param ecTrackInfo
	 * @author
	 */
	public void uptDyncTrack(EcTrackInfo ecTrackInfo) {
		if (ecTrackInfo == null) {
			throw new ServiceException("动态轨迹信息为空");
		}
		// 客户号
		if (StringUtils.isEmpty(ecTrackInfo.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}
		// 动态轨迹编号
		if (StringUtils.isEmpty(ecTrackInfo.getTrackCd())) {
			throw new ServiceException("动态轨迹类型不能为空");
		}
		EcTrackInfo obj = ecTrackInfoMapper.selectByPrimaryKey(ecTrackInfo.getTrackCd());
		if (obj == null) {
			throw new ServiceException("客户动态轨迹信息不存在");
		}
		BeanUtil.copy(ecTrackInfo, obj);
		ecTrackInfoMapper.updateByPrimaryKey(obj);
	}

	/**
	 * Desc: 删除客户动态轨迹信息
	 * @param ecTrackInfo
	 * @author
	 */
	public void delDyncTrack(EcTrackInfo ecTrackInfo) {
		if (ecTrackInfo == null) {
			throw new ServiceException("动态轨迹信息为空");
		}
		// 客户号
		if (StringUtils.isEmpty(ecTrackInfo.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}
		// 动态轨迹编号
		if (StringUtils.isEmpty(ecTrackInfo.getTrackCd())) {
			throw new ServiceException("动态轨迹类型不能为空");
		}
		EcTrackInfo obj = ecTrackInfoMapper.selectByPrimaryKey(ecTrackInfo.getTrackCd());
		if (obj == null) {
			throw new ServiceException("客户动态轨迹信息不存在");
		}
		ecTrackInfoMapper.deleteByPrimaryKey(ecTrackInfo.getTrackCd());
	}
}
