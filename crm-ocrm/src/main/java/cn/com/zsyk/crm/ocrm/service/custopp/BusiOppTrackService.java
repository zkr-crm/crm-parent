package cn.com.zsyk.crm.ocrm.service.custopp;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.com.zsyk.crm.common.dao.AbstractDao;
import cn.com.zsyk.crm.common.dto.ContextContainer;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.BeanUtil;
import cn.com.zsyk.crm.common.util.DateUtil;
import cn.com.zsyk.crm.common.util.IdGenerator;
import cn.com.zsyk.crm.common.util.LogUtil;
import cn.com.zsyk.crm.common.util.RestUtil;
import cn.com.zsyk.crm.generator.EnumType;
import cn.com.zsyk.crm.ocrm.entity.OcBusiOppTrack;
import cn.com.zsyk.crm.ocrm.mapper.OcBusiOppTrackMapper;

@Service
@Transactional
public class BusiOppTrackService {

	/** logger */
	private final Log logger = LogUtil.getLogger(BusiOppTrackService.class);
	@Autowired
	private OcBusiOppTrackMapper ocBusiOppTrackMapper;
	@Autowired
	private AbstractDao daoUtil;
	/**
	 * Desc: 查询客户商机轨迹列表
	 * @param custNo
	 * @return
	 * @author
	 */
	public List<OcBusiOppTrack> selectBusiOppTrackList(OcBusiOppTrack ocBusiOppTrack) {
		if (ocBusiOppTrack == null) {
			throw new ServiceException("客户轨迹筛选参数不能为空");
		}
		String busiOppNo = ocBusiOppTrack.getBusiOppNo();
		if (StringUtils.isEmpty(busiOppNo)) {
			throw new ServiceException("商机编号不能为空");
		}
		List<OcBusiOppTrack> ocBusiOppTracklst = ocBusiOppTrackMapper.selectBusiOppTrackList(ocBusiOppTrack);
		
		List<OcBusiOppTrack> retList = new ArrayList<OcBusiOppTrack>();
		for (OcBusiOppTrack obj : ocBusiOppTracklst) {
			if (obj.getComtFlg().equals(EnumType.YesNoFlg.yes.value)) {
				//obj.setTrackComtList(ecTrackComtMapper.selectByTrackCd(obj.getTrackCd()));
			}
			retList.add(obj);
		}
		return retList;
	}

	/**
	 * Desc: 查询客户商机轨迹信息(单条)
	 * @param trackCd
	 * @return
	 * @author
	 */
	public Object selectCustDyncTrackOne(String trackCd) {
		if (StringUtils.isEmpty(trackCd)) {
			throw new ServiceException("轨迹编号不能为空");
		}
		OcBusiOppTrack ocBusiOppTrack = ocBusiOppTrackMapper.selectByPrimaryKey(trackCd);
		if (ocBusiOppTrack != null) {
			logger.info("ComtFlg:" + ocBusiOppTrack.getComtFlg());
			if (ocBusiOppTrack.getComtFlg().equals(EnumType.YesNoFlg.yes.value)) {
				//ocBusiOppTrack.setTrackComtList(ecTrackComtMapper.selectByTrackCd(ocBusiOppTrack.getTrackCd()));
			}
		}
		return ocBusiOppTrack;
	}

	/**
	 * Desc: 新增客户电话商机轨迹信息
	 * @param ocBusiOppTrack
	 * @author
	 */
	public void addDyncTrackTel(OcBusiOppTrack ocBusiOppTrack) {
		if (ocBusiOppTrack == null) {
			throw new ServiceException("商机轨迹信息为空");
		}
		//		轨迹类型
		ocBusiOppTrack.setTrackTyp(EnumType.TrackType.call_out.getValue());

		this.addDyncTrack(ocBusiOppTrack);
	}

	/**
	 * Desc: 新增客户短信商机轨迹信息
	 * @param ocBusiOppTrack
	 * @author
	 */
	public void addDyncTrackSms(OcBusiOppTrack ocBusiOppTrack) {
		if (ocBusiOppTrack == null) {
			throw new ServiceException("商机轨迹信息为空");
		}
		//		客户号
		ocBusiOppTrack.setCustNo(ocBusiOppTrack.getCustNo());
		//		轨迹类型
		ocBusiOppTrack.setTrackTyp(EnumType.TrackType.info_push.getValue());
		ocBusiOppTrack.setTrackSubTyp(EnumType.TrackSubType.SMS_push.getValue());

		this.addDyncTrack(ocBusiOppTrack);
		// TODO 调用短信发送
	}

	/**
	 * Desc: 新增客户拜访商机轨迹信息
	 * @param ocBusiOppTrack
	 * @author
	 */
	public void addDyncTrackVis(OcBusiOppTrack ocBusiOppTrack) {
		if (ocBusiOppTrack == null) {
			throw new ServiceException("商机轨迹信息为空");
		}
		//		轨迹类型
		ocBusiOppTrack.setTrackTyp(EnumType.TrackType.cust_event.getValue());
		ocBusiOppTrack.setTrackSubTyp(EnumType.TrackSubType.call_on.getValue());
		this.addDyncTrack(ocBusiOppTrack);
	}

	/**
	 * Desc: 新增客户商机轨迹信息
	 * @param ocBusiOppTrack
	 * @author
	 */
	public void addDyncTrack(OcBusiOppTrack ocBusiOppTrack) {
		if (ocBusiOppTrack == null) {
			throw new ServiceException("商机轨迹信息为空");
		}
		// 客户号
		if (StringUtils.isEmpty(ocBusiOppTrack.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}
		//		轨迹编号
		ocBusiOppTrack.setTrackCd(String.valueOf(IdGenerator.getSeqID("TrackCd")));
		OcBusiOppTrack obj = ocBusiOppTrackMapper.selectByPrimaryKey(ocBusiOppTrack.getTrackCd());
		if (obj != null) {
			throw new ServiceException("客户商机轨迹信息已经存在");
		}
		//		轨迹时间
		ocBusiOppTrack.setTrackTime(DateUtil.getCurTime());
		if (ocBusiOppTrack.getNextFollowUpTm() != null) {
			if (ocBusiOppTrack.getNextFollowUpTm().before(ocBusiOppTrack.getTrackTime())) {
				throw new ServiceException("下次跟进日期必须在当前时间之后");
			}
		}
		ocBusiOppTrack.setRecStat(EnumType.RecStat.normal.getValue());
		ocBusiOppTrack.setRecordUser(ContextContainer.getContext().getUserId());
		//		是否接通
		//		联系人
		//		下次跟进时间
		if (ocBusiOppTrack.getNextFollowUpTm() != null) {
//			ZceCustPer custPerobj = ecCustPerMapper.selectByPrimaryKey(ocBusiOppTrack.getCustNo());
//			custPerobj.setNextFollowUpTm(ocBusiOppTrack.getNextFollowUpTm());
//			ecCustPerMapper.updateByPrimaryKey(custPerobj);
		}
		//		评论标识
		ocBusiOppTrack.setComtFlg(EnumType.YesNoFlg.no.getValue());
		ocBusiOppTrackMapper.insert(ocBusiOppTrack);
	}

	/**
	 * Desc: 修改客户商机轨迹信息
	 * @param ocBusiOppTrack
	 * @author
	 */
	public void uptDyncTrack(OcBusiOppTrack ocBusiOppTrack) {
		if (ocBusiOppTrack == null) {
			throw new ServiceException("商机轨迹信息为空");
		}
		// 客户号
		if (StringUtils.isEmpty(ocBusiOppTrack.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}
		// 商机轨迹编号
		if (StringUtils.isEmpty(ocBusiOppTrack.getTrackCd())) {
			throw new ServiceException("商机轨迹类型不能为空");
		}
		OcBusiOppTrack obj = ocBusiOppTrackMapper.selectByPrimaryKey(ocBusiOppTrack.getTrackCd());
		if (obj == null) {
			throw new ServiceException("客户商机轨迹信息不存在");
		}
		BeanUtil.copy(ocBusiOppTrack, obj);
		ocBusiOppTrackMapper.updateByPrimaryKey(obj);
	}

	/**
	 * Desc: 删除客户商机轨迹信息
	 * @param ocBusiOppTrack
	 * @author
	 */
	public void delDyncTrack(OcBusiOppTrack ocBusiOppTrack) {
		if (ocBusiOppTrack == null) {
			throw new ServiceException("商机轨迹信息为空");
		}
		// 客户号
		if (StringUtils.isEmpty(ocBusiOppTrack.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}
		// 商机轨迹编号
		if (StringUtils.isEmpty(ocBusiOppTrack.getTrackCd())) {
			throw new ServiceException("商机轨迹类型不能为空");
		}
		OcBusiOppTrack obj = ocBusiOppTrackMapper.selectByPrimaryKey(ocBusiOppTrack.getTrackCd());
		if (obj == null) {
			throw new ServiceException("客户商机轨迹信息不存在");
		}
		ocBusiOppTrackMapper.deleteByPrimaryKey(ocBusiOppTrack.getTrackCd());
	}

}
