package cn.com.zsyk.crm.ecif.service.customer.relationship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.zsyk.crm.common.dao.CoreDaoImpl;
import cn.com.zsyk.crm.common.dto.PageBean;
import cn.com.zsyk.crm.ecif.entity.EcCustName;
import cn.com.zsyk.crm.ecif.mapper.EcCustNameMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.com.zsyk.crm.common.dao.AbstractDao;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.BeanUtil;
import cn.com.zsyk.crm.common.util.LogUtil;
import cn.com.zsyk.crm.ecif.bo.graph.GraphInfo;
import cn.com.zsyk.crm.ecif.bo.graph.Normal;
import cn.com.zsyk.crm.ecif.bo.graph.SeriesData;
import cn.com.zsyk.crm.ecif.bo.graph.SeriesLinks;
import cn.com.zsyk.crm.ecif.entity.EcCustPer;
import cn.com.zsyk.crm.ecif.entity.EcCustRelation;
import cn.com.zsyk.crm.ecif.entity.EcRelationData;
import cn.com.zsyk.crm.ecif.mapper.EcCustPerMapper;
import cn.com.zsyk.crm.ecif.mapper.EcCustRelationMapper;
import cn.com.zsyk.crm.ecif.mapper.EcRelationDataMapper;
import cn.com.zsyk.crm.ecif.service.customer.baseinfo.CustService;
import cn.com.zsyk.crm.generator.EnumType;

@Service
@Transactional
public class CustRelService {
	/** logger */
	private final Log logger = LogUtil.getLogger(CustRelService.class);
	@Autowired
	private EcCustRelationMapper ecCustRelationMapper;
	@Autowired
	private CustService custService;
	@Autowired
	private AbstractDao daoUtil;
	@Autowired
	private EcRelationDataMapper ecRelationDataMapper;
	@Autowired
	EcCustPerMapper ecCustPerMapper;
	@Autowired
	EcCustNameMapper ecCustNameMapper;
	/* 分页查询对象 */
	@Autowired
	private CoreDaoImpl coreDaoImpl;
	/**
	 * Desc: 查询客户关系(单条)
	 * @param custNo
	 * @param relationTyp
	 * @param refCustNo
	 * @return
	 * @author
	 */
	public EcCustRelation getCustRelOne(String custNo, String relationTyp, String refCustNo) {
		EcCustRelation ecCustRelation = ecCustRelationMapper.selectByPrimaryKey(custNo, relationTyp, refCustNo);
		ecCustRelation.setRelationTypNam(EnumType.Relation.toEnum(ecCustRelation.getRelationTyp()).desc);
		ecCustRelation.setCustNam(custService.getPerCustName(custNo));
		ecCustRelation.setRefCustNam(custService.getPerCustName(refCustNo));
		return ecCustRelation;
	}

	/**
	 * Desc: 查询客户关系列表
	 * @param ecCustRelation
	 * @return
	 * @author
	 */
	public PageBean getCustRelList(EcCustRelation ecCustRelation) {
		Map map = new HashMap();
		map.put("custNo", ecCustRelation.getCustNo());
		map.put("relationTyp", ecCustRelation.getRelationTyp());
		map.put("custNam", ecCustRelation.getCustNam());
		PageBean pageRetlst = coreDaoImpl.selectPageByMapper(ecCustRelationMapper, "selectCustRelList", map);
		List<EcCustRelation> custRellst = pageRetlst.getList();
		List<EcCustRelation> retlst = new ArrayList<EcCustRelation>();
		for (EcCustRelation obj : custRellst) {
			obj.setRelationTypNam(EnumType.Relation.toEnum(obj.getRelationTyp()).desc);
			obj.setCustNam(custService.getPerCustName(obj.getCustNo()));
			obj.setRefCustNam(custService.getPerCustName(obj.getRefCustNo()));
			retlst.add(obj);
		}
		pageRetlst.setList(retlst);
		return pageRetlst;
	}

	/**
	 * Desc: 新增客户关系
	 * @param ecCustRelation
	 * @author
	 */
	public void addCustRel(EcCustRelation ecCustRelation) {
		if (ecCustRelation == null) {
			throw new ServiceException("事件信息为空");
		}
		// 客户号
		if (StringUtils.isEmpty(ecCustRelation.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}
		// 关系类型
		if (StringUtils.isEmpty(ecCustRelation.getRelationTyp())) {
			throw new ServiceException("关系类型不能为空");
		}
		// 关联客户号
		if (StringUtils.isEmpty(ecCustRelation.getRefCustNo())) {
			throw new ServiceException("关联客户号不能为空");
		}
		EcCustRelation obj = ecCustRelationMapper.selectByPrimaryKey(ecCustRelation.getCustNo(), ecCustRelation.getRelationTyp(), ecCustRelation.getRefCustNo());
		if (obj != null) {
			throw new ServiceException("相同关系类型客户事件信息已经存在");
		}
		EcCustRelation obj1 = ecCustRelationMapper.selectByPrimaryKey(ecCustRelation.getCustNo(), "", ecCustRelation.getRefCustNo());
		if (obj1 != null) {
			throw new ServiceException("客户事件信息已经存在，请勿添加其他关系类型");
		}
		if (ecCustRelation.getCustNo().equals(ecCustRelation.getRefCustNo())) {
			throw new ServiceException("同一个人请勿添加关系");
		}
		ecCustRelation.setRecStat(EnumType.RecStat.normal.getValue());
//		ecCustRelationMapper.insert(ecCustRelation);

		// 补充反向关系客户关系
		// 1.查询ec_relation_data 获取反向关联关系,输入条件 关联关系类型、性别
		EcCustPer ecCustPer = ecCustPerMapper.selectByPrimaryKey(ecCustRelation.getCustNo());
		EcCustPer ecCustPerRef = ecCustPerMapper.selectByPrimaryKey(ecCustRelation.getRefCustNo());
		EcCustName ecCustName = ecCustNameMapper.selectByPrimaryKey(ecCustRelation.getCustNo());
		EcCustName ecCustNameRef = ecCustNameMapper.selectByPrimaryKey(ecCustRelation.getRefCustNo());
		if (ecCustPer == null) {
			throw new ServiceException("客户信息不存在");
		}
		if (ecCustPerRef == null) {
			throw new ServiceException("关联客户信息不存在");
		}
		EcRelationData ecRelationData;
		EcRelationData ecRelationDataRev;
		if(ecCustRelation.getRelationTyp()!=null && "99".equals(ecCustRelation.getRelationTyp())){
			ecRelationData=new EcRelationData();
			ecRelationData.setForwardRelation("99");
			ecRelationData.setReverseRelation("99");
			ecRelationDataRev=ecRelationData;
		}else{
			ecRelationData = ecRelationDataMapper.selectByPrimaryKey(ecCustRelation.getRelationTyp(), ecCustPer.getSex());
			ecRelationDataRev = ecRelationDataMapper.selectByPrimaryKey(StringUtils.isEmpty(ecRelationData.getReverseRelation()) ? "" :ecRelationData.getReverseRelation(), ecCustPerRef.getSex());
		}

		if (ecRelationData!=null&&StringUtils.isNotEmpty(ecRelationData.getReverseRelation())&&ecRelationDataRev!=null&&StringUtils.isNotEmpty(ecRelationDataRev.getForwardRelation())
		&&StringUtils.isNotEmpty(ecRelationDataRev.getReverseRelation())&&ecRelationDataRev.getReverseRelation().equals(ecRelationData.getForwardRelation())) {
//			EcRelationData ecRelationDataRef = ecRelationDataMapper.selectByPrimaryKey(ecRelationData.getReverseRelation(), ecCustPerRef.getSex());
//			if (StringUtils.isEmpty(ecRelationDataRef.getReverseRelation())) {
//				throw new ServiceException("客户性别为："+EnumType.Sex.toEnum(ecCustPer.getSex()).desc.toString()+",关联客户性别为："+EnumType.Sex.toEnum(ecCustPerRef.getSex()).desc.toString()+"和关系类型不符，请选择其他关系");
//			} else{
				EcCustRelation reversecustRelation = new EcCustRelation();
				reversecustRelation.setCustNo(ecCustRelation.getRefCustNo());
				reversecustRelation.setCustNam(ecCustRelation.getRefCustNam());
				reversecustRelation.setRefCustNam(ecCustRelation.getCustNam());
				reversecustRelation.setRefCustNo(ecCustRelation.getCustNo());
				reversecustRelation.setRelationTypNam(EnumType.Relation.enumDesc);
				reversecustRelation.setRelationDesc(EnumType.Relation.toEnum(ecRelationData.getReverseRelation()).desc);
				reversecustRelation.setRelationTyp(ecRelationData.getReverseRelation());
				// 将传入的客户号作为被关联客户号，关系类型作为为查处的反向关联关系 ，插入ecCustRelationMapper
				List<EcCustRelation> haveRelation = ecCustRelationMapper.selectCustRelList(reversecustRelation);
				if (haveRelation == null || haveRelation.isEmpty()) {
					ecCustRelationMapper.insert(ecCustRelation);
					ecCustRelationMapper.insert(reversecustRelation);
					} else {
					throw new ServiceException("关系已经存在");
				}
//			}
		} else {
			logger.info("无客户反向关系");
			throw new ServiceException("客户["+ecCustName.getCustNam()+"]性别为:"+EnumType.Sex.toEnum(ecCustPer.getSex()).desc.toString()+"；客户["+ecCustNameRef.getCustNam()+"]性别为:"+EnumType.Sex.toEnum(ecCustPerRef.getSex()).desc.toString()+"，和关系类型不符，请选择其他关系");
		}

	}

	/**
	 * Desc: 修改客户关系
	 * @param ecCustRelation
	 * @author
	 */
	public void uptCustRel(EcCustRelation ecCustRelation) {
//		if (ecCustRelation == null) {
//			throw new ServiceException("事件信息为空");
//		}
//		// 客户号
//		if (StringUtils.isEmpty(ecCustRelation.getCustNo())) {
//			throw new ServiceException("客户号不能为空");
//		}
//		// 关系类型
//		if (StringUtils.isEmpty(ecCustRelation.getRelationTyp())) {
//			throw new ServiceException("关系类型不能为空");
//		}
//		// 关联客户号
//		if (StringUtils.isEmpty(ecCustRelation.getRefCustNo())) {
//			throw new ServiceException("关联客户号不能为空");
//		}
//		EcCustRelation obj = ecCustRelationMapper.selectByPrimaryKey(ecCustRelation.getCustNo(), ecCustRelation.getRelationTyp(), ecCustRelation.getRefCustNo());
//		if (obj == null) {
//			throw new ServiceException("客户事件信息不存在");
//		}
//		BeanUtil.copy(ecCustRelation, obj);
//		ecCustRelationMapper.updateByPrimaryKey(obj);
		ecCustRelationMapper.deleteByPrimaryKey(ecCustRelation.getCustNo(), "", ecCustRelation.getRefCustNo());
		ecCustRelationMapper.deleteByPrimaryKey(ecCustRelation.getRefCustNo(), "", ecCustRelation.getCustNo());
		this.addCustRel(ecCustRelation);
	}

	/**
	 * Desc: 删除客户关系
	 * @param ecCustRelation
	 * @author
	 */
	public void delCustRel(EcCustRelation ecCustRelation) {
		if (ecCustRelation == null) {
			throw new ServiceException("事件信息为空");
		}
		// 客户号
		if (StringUtils.isEmpty(ecCustRelation.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}
		// 关系类型
		if (StringUtils.isEmpty(ecCustRelation.getRelationTyp())) {
			throw new ServiceException("关系类型不能为空");
		}
		// 关联客户号
		if (StringUtils.isEmpty(ecCustRelation.getRefCustNo())) {
			throw new ServiceException("关联客户号不能为空");
		}
		EcCustPer ecCustPer = ecCustPerMapper.selectByPrimaryKey(ecCustRelation.getCustNo());
		if (ecCustPer == null) {
			throw new ServiceException("客户信息不存在");
		}
		EcCustRelation obj = ecCustRelationMapper.selectByPrimaryKey(ecCustRelation.getCustNo(), ecCustRelation.getRelationTyp(), ecCustRelation.getRefCustNo());
		if (obj == null) {
			throw new ServiceException("客户关系信息不存在");
		}
		ecCustRelationMapper.deleteByPrimaryKey(ecCustRelation.getCustNo(), ecCustRelation.getRelationTyp(), ecCustRelation.getRefCustNo());

		// 删除反向关系
		EcRelationData ecRelationData = ecRelationDataMapper.selectByPrimaryKey(ecCustRelation.getRelationTyp(), ecCustPer.getSex());
		if (StringUtils.isNotEmpty(ecRelationData.getReverseRelation())) {
			ecCustRelationMapper.deleteByPrimaryKey(ecCustRelation.getRefCustNo(), ecRelationData.getReverseRelation(), ecCustRelation.getCustNo());
		}
	}

	/**
	 * Desc: 关系图谱
	 * @param custNo
	 * @return
	 * @author
	 */
	public GraphInfo getCustGraph(String custNo) {
		// 查找客户的关系客户号
		EcCustRelation selectObj = new EcCustRelation();
		selectObj.setCustNo(custNo);
		List<EcCustRelation> custListObj = this.getCustRelList(selectObj).getList();
		Map<String, Integer> filterLinkMap = new HashMap<String, Integer>();
		filterLinkMap.put(custNo, 1);
		// 关系客户号列表 1 层
		List<String> custNoList = new ArrayList<String>();
		for (EcCustRelation obj : custListObj) {
			custNoList.add(obj.getRefCustNo());
			if (!filterLinkMap.containsKey(obj.getRefCustNo())) {
				filterLinkMap.put(obj.getRefCustNo(), 2);
			}
		}
		// Series data
		List<SeriesData> dataList = new ArrayList<SeriesData>();
		// 添加客户本人，查找列表中客户关系
		custNoList.add(custNo);
		List<EcCustRelation> custList = selectDistinctRelCustByCustNoList(custNoList);
		// 关系客户号列表
		custNoList = new ArrayList<String>();
		for (EcCustRelation obj : custList) {
			custNoList.add(obj.getRefCustNo());
			if (!filterLinkMap.containsKey(obj.getRefCustNo())) {
				filterLinkMap.put(obj.getRefCustNo(), 3);
			}
		}

		// 关系客户信息
		for (EcCustRelation obj : custList) {
			SeriesData dataObj = new SeriesData();
			dataObj.setName(obj.getRefCustNam());
			dataObj.setCustNo(obj.getRefCustNo());
			if (obj.getRefCustNo().equals(custNo)) {
				// 主客户号时 SymbolSize 加大
				dataObj.setSymbolSize("80");
				Normal normal = new Normal();
				normal.setColor("#376ab3");
				dataObj.setItemStyle(normal);
			} else {
				// dataObj.setDes(obj.getRelationDesc());
				dataObj.setSymbolSize("50");
			}
			dataList.add(dataObj);
		}

		// Series links data
		List<SeriesLinks> linksList = new ArrayList<SeriesLinks>();

		List<EcCustRelation> list = selectRelGraphByCustNoList(custNoList);

		for (EcCustRelation obj : list) {
			if (!filterLinkMap.containsKey(obj.getCustNo()) || !filterLinkMap.containsKey(obj.getRefCustNo()) || filterLinkMap.get(obj.getCustNo()) > filterLinkMap.get(obj.getRefCustNo())) {
				continue;
			} else if (filterLinkMap.get(obj.getCustNo()) == filterLinkMap.get(obj.getRefCustNo()) && filterLinkMap.containsKey(obj.getRefCustNo() + "-" + obj.getCustNo())) {
				continue;
			}
			if (filterLinkMap.get(obj.getCustNo()) == filterLinkMap.get(obj.getRefCustNo())){
				filterLinkMap.put(obj.getCustNo() + "-" + obj.getRefCustNo(), 1);
			}
			SeriesLinks links = new SeriesLinks();
			// source: '李四',
			// target: '张三',
			// name: '妻子',
			// des: '夫妻'
			links.setSource(obj.getCustNam());
			links.setSourceCustNo(obj.getCustNo());
			links.setTarget(obj.getRefCustNam());
			links.setTargetCustNo(obj.getRefCustNo());
			links.setRelType(obj.getRelationTyp());
			links.setName(EnumType.Relation.toEnum(obj.getRelationTyp()).desc);
			links.setDes(obj.getRelationDesc());
			linksList.add(links);
		}

		// 返回值
		GraphInfo graphInfo = new GraphInfo();
		graphInfo.setData(dataList);
		graphInfo.setLinks(linksList);

		return graphInfo;
	}

	/**
	 * Desc: 获取所有图谱人员的关系信息
	 * @param custNoList
	 * @return
	 * @author
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<EcCustRelation> selectRelGraphByCustNoList(List<String> custNoList) {
		Map map = new HashMap();
		map.put("custNoList", custNoList);
		List<EcCustRelation> retlst = daoUtil.selectList("cn.com.zsyk.crm.ecif.mapper.EcCustRelationMapper.selectRelGraphByCustNoList", map);
		return retlst;
	}

	/**
	 * Desc: 获取客户关系图谱人员客户号、姓名信息（去重、包含客户本人）
	 * @param custNoList
	 * @return
	 * @author
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<EcCustRelation> selectDistinctRelCustByCustNoList(List<String> custNoList) {
		Map map = new HashMap();
		map.put("custNoList", custNoList);
		List<EcCustRelation> retlst = daoUtil.selectList("cn.com.zsyk.crm.ecif.mapper.EcCustRelationMapper.selectDistinctRelCustByCustNoList", map);
		return retlst;
	}
}
