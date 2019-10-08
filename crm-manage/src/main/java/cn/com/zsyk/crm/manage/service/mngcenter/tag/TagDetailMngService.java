package cn.com.zsyk.crm.manage.service.mngcenter.tag;

import cn.com.zsyk.crm.common.dao.CoreDaoImpl;
import cn.com.zsyk.crm.common.dto.PageBean;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.IdGenerator;
import cn.com.zsyk.crm.generator.EnumType;
import cn.com.zsyk.crm.manage.entity.SysTag;
import cn.com.zsyk.crm.manage.entity.SysTagType;
import cn.com.zsyk.crm.manage.mapper.SysTagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



@Service
@Transactional
public class TagDetailMngService {

	@Autowired
	private SysTagMapper tagDetailMapper;
	@Autowired
	private SysTagMapper tagMapper;
	@Autowired
	private CoreDaoImpl coreDaoImpl;
	/**
	 * 查询标签明细
	 * 
	 * @param tagId
	 * @return
	 */
	public SysTag getOneTag(int tagId) {

		SysTag tagDetail = new SysTag();

		tagDetail = tagDetailMapper.selectByPrimaryKey(tagId);

		return tagDetail;
	}

	/**
	 * 校验标签新增唯一性
	 * 
	 * @param tagTypeId
	 * @param tagName
	 * @return
	 */
	public SysTag getOneTagByNameType(String tagTypeId, String tagName) {

		SysTag p_tagDetail = new SysTag();
		p_tagDetail.setTagTypeId(tagTypeId);
		p_tagDetail.setTagName(tagName);
		SysTag tagDetail = new SysTag();

		List<SysTag> tagList = tagDetailMapper.selectByTagNamType(p_tagDetail);
		
		if(tagList != null && tagList.size() > 0  ) {
			tagDetail = tagList.get(0);
		}

		return tagDetail;
	}

	/**
	 * 查询标签明细（任意输入类别、标签名称）
	 * 
	 * @param tagDetail
	 * @return
	 */
	public PageBean getTagDetails(SysTag tagDetail) {

		//List<SysTag> resTagDetails = new ArrayList<SysTag>();

		//resTagDetails = tagDetailMapper.selectByEntity(tagDetail);
		PageBean pageRetlst = coreDaoImpl.selectPageByMapper(tagDetailMapper,"selectByEntity",tagDetail);
		return pageRetlst;
	}

	/**
	 * 增加标签
	 * 
	 * @param tagDetail
	 * @return
	 */
	public int addTag(SysTag tagDetail) {
		SysTag sameTagDetail = this.getOneTagByNameType("", tagDetail.getTagName());
		if (sameTagDetail.getTagId() != null) {
			throw new ServiceException("标签已存在!");
		}
		if(tagDetail.getParentTagId() == null ) {
			tagDetail.setParentTagId("");
		}
		tagDetail.setTagId((int) IdGenerator.getSeqID("tagDetail"));
		int addCount = tagDetailMapper.insert(tagDetail);
		return addCount;
	}

	/**
	 * 修改标签
	 * 
	 * @param tagDetail
	 * @return
	 */
	public int modTag(SysTag tagDetail) {
		SysTag extTagDetail = this.getOneTag(tagDetail.getTagId());
		if (extTagDetail.getTagId() == null) {
			throw new ServiceException("标签不存在!");
		}
		if(tagDetail.getParentTagId() == null ) {
			tagDetail.setParentTagId("");
		}
		int modCount = tagDetailMapper.updateByPrimaryKey(tagDetail);

		return modCount;
	}

	/**
	 * 删除标签（物理）
	 * 
	 * @param tagDetail
	 * @return
	 */
	public int delTag(SysTag tagDetail) {
		SysTag extTagDetail = this.getOneTag(tagDetail.getTagId());
		if (extTagDetail.getTagId() == null) {
			throw new ServiceException("标签不存在!");
		}

		//验证是否存在下级标签--报错
		SysTag tagCheck = new SysTag();
		tagCheck.setParentTagId(tagDetail.getTagId().toString());
		List<SysTag> tagCheckList = tagMapper.selectByEntity(tagCheck);
		if(tagCheckList != null && tagCheckList.size() > 0) {
			throw new ServiceException("该标签正在被其他标签引用，不可删除!");
		}
		
		int delCount = tagDetailMapper.deleteByPrimaryKey(tagDetail.getTagId());

		return delCount;
	}

	/**
	 * 删除标签（逻辑）
	 * 
	 * @param tagDetail
	 * @return
	 */
	public int delModTag(SysTag tagDetail) {
		SysTag extTagDetail = this.getOneTag(tagDetail.getTagId());
		if (extTagDetail == null) {
			throw new ServiceException("标签不存在!");
		}

		extTagDetail.setRecStat("1");
		int modCount = tagDetailMapper.updateByPrimaryKey(extTagDetail);

		return modCount;
	}
	
	
	
	
	/**
	 * 根据策略id查询对应有效的标签数组(去除满足多个策略且重复的标签)
	 * 
	 * @param list 策略数组（唯一的）
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<String> getTagidsByStrategyId(List<String> list) {
		List<String> tempList = new ArrayList<String>();
		List<String> resList = new ArrayList<String>();
		for (String strategyId : list) {
			List<String> tags = tagDetailMapper.selectByStrategyId(strategyId);
			if (tags != null && !tags.isEmpty()) {
				tempList.addAll(tags);
			}
		}
		Set set = new HashSet();
		for (String tagid : tempList) {
			if (set.add(tagid)) {
				resList.add(tagid);
			}
		}
		return resList;

	}
	
	
	/**
	 * 根据id获取其下所有tag
	 */
	public List<SysTag> getChildTagsById( String id){
		List<SysTag> retList = new ArrayList<SysTag>();
		SysTag t = new SysTag();
		t.setParentTagId(id);
		List<SysTag> childTags = tagDetailMapper.selectByEntity(t);
		retList.addAll(childTags);
		if( childTags != null && childTags.size() > 0 ) {
			for(SysTag item : childTags) {
				List<SysTag> nextChilds = this.getChildTagsById(item.getTagId().toString());
				retList.addAll(nextChilds);
			}
		}
		return retList;
	}
	
	
	/**
	 * 根据typeList获取对应所有tag
	 */
	public List<SysTag> getTagsByTypes(List<SysTagType> typeList){
		
		List<SysTag> retList = new ArrayList<SysTag>();
		
		if(typeList != null && typeList.size() > 0) {
			for(SysTagType item : typeList) {
				SysTag t = new SysTag();
				t.setTagTypeId(item.getTagTypeId().toString());
				t.setRecStat(EnumType.RecStat.normal.value);
				List<SysTag> childTags = tagDetailMapper.selectByEntity(t);
				retList.addAll(childTags);
			}
		}
		return retList;
	}
	
	
	
	
 }
