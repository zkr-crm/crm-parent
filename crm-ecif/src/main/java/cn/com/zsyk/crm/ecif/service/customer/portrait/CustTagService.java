package cn.com.zsyk.crm.ecif.service.customer.portrait;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.com.zsyk.crm.ecif.bo.cust.CustTagList;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.zsyk.crm.common.constant.ServiceType;
import cn.com.zsyk.crm.common.dto.ContextContainer;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.LogUtil;
import cn.com.zsyk.crm.common.util.RestUtil;
import cn.com.zsyk.crm.ecif.entity.EcCustTag;
import cn.com.zsyk.crm.ecif.entity.EcTagDetail;
import cn.com.zsyk.crm.ecif.entity.EcTrackInfo;
import cn.com.zsyk.crm.ecif.entity.EcTag;
import cn.com.zsyk.crm.ecif.mapper.EcCustTagMapper;
import cn.com.zsyk.crm.ecif.mapper.EcTagDetailMapper;
import cn.com.zsyk.crm.generator.EnumType;
import net.sf.json.JSONArray;

@Service
@Transactional 
public class CustTagService {
	/** logger */
	@SuppressWarnings("unused")
	private final Log logger = LogUtil.getLogger(CustTagService.class);

	@Autowired
	private EcCustTagMapper ecCustTagMapper;
	@Autowired
	private EcTagDetailMapper ecTagDetailMapper;
	@Autowired
	private CustDyncTrackService custDyncTrackService;
	@Autowired
	RestUtil restUtil;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, String> getCustTagByTagIdList(List<String> tagIdList) {
		 Map<String, String> retMap = new HashMap<String, String>();

		if (tagIdList == null || tagIdList.size() == 0) {
			return retMap;
		}
		// 返回
		Result response = null;
		try {
			String tagIdListStr = JSONArray.fromObject(tagIdList).toString();

			response = restUtil.getForObject(ServiceType.MANAGE, "/crm/manage/tagmng/getTagByIdList?tagIdListStr={tagIdListStr}", Result.class,  tagIdListStr);
		} catch (Exception e) {
			return retMap;
		}

		if (response == null || response.getData() == null) {
			return retMap;
		}

		if (response != null) {
			List<LinkedHashMap> list = (List<LinkedHashMap>)response.getData();
			if (list != null) {
				for(LinkedHashMap o : list) {
					String tagId = String.valueOf(o.get("tagId"));
					String tagName = (String)o.get("tagName");
					retMap.put(tagId, tagName);
				}
			}
		}
		return retMap;
	}

	/**
	 * Desc: 根据客户号查询客户客户标签列表
	 * @param custNo
	 * @return
	 * @author 
	 * @throws  
	 */
	public  List<EcCustTag> selectByCustNo(String custNo) {
		List<EcCustTag> ecCustTaglst = ecCustTagMapper.selectByCustNo(custNo);
		List<String> tagIdList = new ArrayList<String>();
		// 生成tagId list
		for(EcCustTag obj : ecCustTaglst) {
			tagIdList.add(obj.getCustTagCd());
		}
		// 通过tagId list 获取tag信息
		Map<String, String>tagMap =  getCustTagByTagIdList(tagIdList);

		// 获取名字
		for(EcCustTag obj : ecCustTaglst) {
			obj.setCustTagNam(tagMap.get(obj.getCustTagCd()));
		}
		return ecCustTaglst;
	}

	/**
	 * Desc: 根据客户号、标签code获取客户客户标签
	 * @param custNo
	 * @param custTagCd
	 * @return
	 * @author 
	 */
	public EcCustTag selectCustTagByPK(String custNo, String custTagCd) {
		EcCustTag ecCustTag = ecCustTagMapper.selectByPrimaryKey(custNo, custTagCd);
		EcTagDetail ecTagDetail = ecTagDetailMapper.selectByPrimaryKey(custTagCd);
		if (ecTagDetail != null) {
			ecCustTag.setCustTagNam(ecTagDetail.getTagName());
		}
		return ecCustTag;
	}

	/**
	 * Desc: 添加标签
	 * @param custNo
	 * @param tagCd
	 * @author
	 */
	public void addCustTag(String custNo, String tagCd, String tagNam, String autoFlg) {
		if (StringUtils.isEmpty(custNo)) {
			throw new ServiceException("客户号不能为空");
		}
		if (StringUtils.isEmpty(tagCd)) {
			throw new ServiceException("客户标签编码不能为空");
		}
		EcCustTag ecCustTag = ecCustTagMapper.selectByPrimaryKey(custNo, tagCd);
		if (null != ecCustTag) {
			throw new ServiceException("客户标签已经存在");
		}
		EcCustTag objCustTag = new EcCustTag();
		objCustTag.setCustNo(custNo);
		objCustTag.setCustTagCd(tagCd);
		objCustTag.setRecStat(EnumType.RecStat.normal.value);
		objCustTag.setAutoFlg(autoFlg);
		ecCustTagMapper.insert(objCustTag);
		EcTrackInfo ecTrackInfo = new EcTrackInfo();
		//		客户号
		ecTrackInfo.setCustNo(custNo);
		//		轨迹类型
		ecTrackInfo.setTrackTyp(EnumType.TrackType.info_change.getValue());
		ecTrackInfo.setTrackSubTyp(EnumType.TrackSubType.manual_add_tag.getValue());
		//		内容详情
		ecTrackInfo.setTrackContent(tagNam);
		custDyncTrackService.addDyncTrack(ecTrackInfo);
	}


	/**
	 * Desc: 批量添加标签方法
	 * @param custNo
	 * @param tagCd
	 * @author
	 */
	public void addCustTags(String custNo, String tagCd, String tagNam, String autoFlg) {
		if (StringUtils.isEmpty(custNo)) {
			throw new ServiceException("客户号不能为空");
		}
		if (StringUtils.isEmpty(tagCd)) {
			throw new ServiceException("客户标签编码不能为空");
		}
		EcCustTag ecCustTag = ecCustTagMapper.selectByPrimaryKey(custNo, tagCd);
		if (null != ecCustTag) {

		}else {
			EcCustTag objCustTag = new EcCustTag();
			objCustTag.setCustNo(custNo);
			objCustTag.setCustTagCd(tagCd);
			objCustTag.setRecStat(EnumType.RecStat.normal.value);
			objCustTag.setAutoFlg(autoFlg);
			ecCustTagMapper.insert(objCustTag);
			EcTrackInfo ecTrackInfo = new EcTrackInfo();
			//		客户号
			ecTrackInfo.setCustNo(custNo);
			//		轨迹类型
			ecTrackInfo.setTrackTyp(EnumType.TrackType.info_change.getValue());
			ecTrackInfo.setTrackSubTyp(EnumType.TrackSubType.manual_add_tag.getValue());
			//		内容详情
			ecTrackInfo.setTrackContent(tagNam);
			custDyncTrackService.addDyncTrack(ecTrackInfo);
		}
	}

	/**
	 * Desc: 删除标签
	 * @param custNo
	 * @param tagCd
	 * @author
	 */
	public void delCustTag(String custNo, String tagCd, String tagNam) {
		if (StringUtils.isEmpty(custNo)) {
			throw new ServiceException("客户号不能为空");
		}
		if (StringUtils.isEmpty(tagCd)) {
			throw new ServiceException("客户标签编码不能为空");
		}
		EcCustTag ecCustTag = ecCustTagMapper.selectByPrimaryKey(custNo, tagCd);
		if (null == ecCustTag) {
			throw new ServiceException("客户标签不存在");
		}
		ecCustTagMapper.deleteByPrimaryKey(custNo, tagCd);

		// 写轨迹
		EcTrackInfo ecTrackInfo = new EcTrackInfo();
		//		客户号
		ecTrackInfo.setCustNo(custNo);
		//		轨迹类型
		ecTrackInfo.setTrackTyp(EnumType.TrackType.info_change.getValue());
		ecTrackInfo.setTrackSubTyp(EnumType.TrackSubType.manual_del_tag.getValue());
		//		内容详情
		ecTrackInfo.setTrackContent(tagNam);
		//		记录人
		custDyncTrackService.addDyncTrack(ecTrackInfo);
	}

	/**
	 * Desc: 设置客户标签
	 * @param ecCustTaglst
	 * @return
	 * @author
	 */
	public void setCustTag(String custNo, List<String> custTagList, String autoFlg) {
		if (StringUtils.isEmpty(custNo)) {
			throw new ServiceException("客户号不能为空");
		}
		EcCustTag custTag = new EcCustTag();
		custTag.setCustNo(custNo);
		custTag.setAutoFlg(EnumType.MarkTagType.automatic.getValue());
		ecCustTagMapper.deleteByCustNoAutoFlg(custTag);

		if (custTagList != null) {
			// 通过tagId list 获取tag信息
//			Map<String, String>tagMap =  getCustTagByTagIdList(custTagList);

			for (String tagId : custTagList) {
				EcCustTag ecCustTag = ecCustTagMapper.selectByPrimaryKey(custNo, tagId);
				if (null != ecCustTag) {
					continue;
				}
				EcCustTag objCustTag = new EcCustTag();
				objCustTag.setCustNo(custNo);
				objCustTag.setCustTagCd(tagId);
				objCustTag.setRecStat(EnumType.RecStat.normal.value);
				objCustTag.setAutoFlg(autoFlg);
				ecCustTagMapper.insert(objCustTag);
				String userId = ContextContainer.getContext().getUserId() == null ?"batch" : ContextContainer.getContext().getUserId();

				// 写轨迹
				EcTrackInfo ecTrackInfo = new EcTrackInfo();
				//		客户号
				ecTrackInfo.setCustNo(custNo);
				//		轨迹类型
				ecTrackInfo.setTrackTyp(EnumType.TrackType.info_change.getValue());
				if (autoFlg.equals(EnumType.MarkTagType.manual.getValue())) {
					ecTrackInfo.setTrackSubTyp(EnumType.TrackSubType.manual_add_tag.getValue());
					//		内容详情
					ecTrackInfo.setTrackContent("手动设置标签");
				} else {
					ecTrackInfo.setTrackSubTyp(EnumType.TrackSubType.auto_add_tag.getValue());
					//		内容详情
					ecTrackInfo.setTrackContent("自动设置标签");
				}
				//		记录人
				ecTrackInfo.setCreateUser(userId);
				custDyncTrackService.addDyncTrack(ecTrackInfo);
			}
		}
	}

	/**
	 * Desc: 设置多客户标签
	 * @param tagList
	 * @author
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setCustListTag(Map<String, List<String>> tagList) {
		// del automatic tag data
		//ecCustTagMapper.deleteByAutoFlg(EnumType.MarkTagType.automatic.getValue());
		if (tagList != null) {
			// set tag
			for (Map.Entry entry : tagList.entrySet()) {
				String custNo = entry.getKey().toString();
				List<String> custTagList = (List<String>) entry.getValue();
				setCustTag(custNo, custTagList, EnumType.MarkTagType.automatic.getValue());
			}
		}
	}

	/**
	 * Desc: 添加批量客户标签
	 * @param tagList
	 * @author
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addCustTagList(List<CustTagList> tagList) {
		if (tagList != null) {

			for (CustTagList  tag : tagList) {
				addCustTags(tag.getCustNo(),tag.getTagCd(),tag.getTagNam(), EnumType.MarkTagType.manual.getValue());
			}
		}
	}
}
