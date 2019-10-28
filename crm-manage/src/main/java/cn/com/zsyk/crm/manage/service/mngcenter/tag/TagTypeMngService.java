package cn.com.zsyk.crm.manage.service.mngcenter.tag;

import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.IdGenerator;
import cn.com.zsyk.crm.manage.bom.SysTagInfo;
import cn.com.zsyk.crm.manage.entity.SysTag;
import cn.com.zsyk.crm.manage.entity.SysTagType;
import cn.com.zsyk.crm.manage.mapper.SysTagMapper;
import cn.com.zsyk.crm.manage.mapper.SysTagTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

;

@Service
@Transactional
public class TagTypeMngService {

	@Autowired
	private SysTagTypeMapper tagTypeMapper;
	@Autowired
	private SysTagMapper tagMapper;

	/**
	 * 查询标签类别信息
	 * 
	 * @param tagTypeId
	 * @return
	 */
	public SysTagType getOneTagType(int tagTypeId) {

		SysTagType tagType = new SysTagType();

		tagType = tagTypeMapper.selectByPrimaryKey(tagTypeId);

		return tagType;
	}

	/**
	 * 验证新增标签类别唯一
	 * 
	 * @param tagTypeLevel
	 * @param tagTypeName
	 * @return
	 */
	public SysTagType getTagTypeByLevelName(String tagTypeLevel, String tagTypeName) {

		SysTagType p_tagType = new SysTagType();
		p_tagType.setTagTypeLevel(tagTypeLevel);
		p_tagType.setTagTypeName(tagTypeName);
		SysTagType tagType = new SysTagType();

		List<SysTagType> tagTypeList = tagTypeMapper.selectByTagTypeLevelName(p_tagType);
		if( tagTypeList != null && tagTypeList.size() > 0 ) {
			tagType = tagTypeList.get(0);
		}

		return tagType;
	}

	/**
	 * 查询标签类别列表（任意输入级别、标签类别名称）
	 * 
	 * @param tagType
	 * @return
	 */
	public List<SysTagType> getTagTypes(SysTagType tagType) {

		List<SysTagType> restagTypes = new ArrayList<SysTagType>();

		restagTypes = tagTypeMapper.selectByEntity(tagType);

		return restagTypes;
	}

	/**
	 * 增加标签类别
	 * 
	 * @param tagType
	 * @return
	 */
	public int addTagType(SysTagType tagType) {
		SysTagType sametagType = this.getTagTypeByLevelName(tagType.getTagTypeLevel(), tagType.getTagTypeName());
		if (sametagType.getTagTypeId() != null) {

			throw new ServiceException("标签类别已存在!");
		}
		if(tagType.getSuperTagTypeId() == null) {
			tagType.setSuperTagTypeId("");
		}
		tagType.setTagTypeId((int)IdGenerator.getSeqID("tagType"));
		tagType.setRecStat("0");
		int addCount = tagTypeMapper.insert(tagType);
		return addCount;

	}

	/**
	 * 修改标签类别
	 * 
	 * @param tagType
	 * @return
	 */
	public int modTagType(SysTagType tagType) {
		SysTagType extTagType = this.getOneTagType(tagType.getTagTypeId());
		if (extTagType == null) {
			throw new ServiceException("标签类别不存在!");
		}

		int modCount = tagTypeMapper.updateByPrimaryKey(tagType);

		return modCount;
	}

	/**
	 * 删除标签类别（物理）
	 * 
	 * @param tagType
	 * @return
	 */
	public int delTagType(SysTagType tagType) {
		SysTagType exttagType = this.getOneTagType(tagType.getTagTypeId());
		if (exttagType == null) {
			throw new ServiceException("标签类别不存在!");
		}

		//验证标签类型是否子类型--报错
		SysTagType typeCheck = new SysTagType();
		typeCheck.setSuperTagTypeId(tagType.getTagTypeId().toString());
		List<SysTagType> typeCheckList = tagTypeMapper.selectByEntity(typeCheck);
		if(typeCheckList != null && typeCheckList.size() > 0) {
			throw new ServiceException("该标签类别正在被其他标签类别引用，不可删除!");
		}
		//验证类型下是否有标签--报错
		SysTag tagCheck = new SysTag();
		tagCheck.setTagTypeId(tagType.getTagTypeId().toString());
		List<SysTagInfo> tagCheckList = tagMapper.selectByEntity(tagCheck);
		if(tagCheckList != null && tagCheckList.size() > 0) {
			throw new ServiceException("该标签类别正在被标签使用，不可删除!");
		}
		
		int delCount = tagTypeMapper.deleteByPrimaryKey(tagType.getTagTypeId());

		return delCount;
	}

	/**
	 * 删除标签类别（逻辑）
	 * 
	 * @param tagType
	 * @return
	 */
	public int delModTagType(SysTagType tagType) {
		SysTagType exttagType = this.getOneTagType(tagType.getTagTypeId());
		if (exttagType == null) {
			throw new ServiceException("标签类别不存在!");
		}

		exttagType.setRecStat("0");
		int modCount = tagTypeMapper.updateByPrimaryKey(exttagType);

		return modCount;
	}
	
	/**
	 * 根据tagtypeid获取其下所有tagtype
	 */

	public List<SysTagType> getChildTypesByTypeId( String tagTypeId){
		List<SysTagType> retList = new ArrayList<SysTagType>();
		SysTagType t = new SysTagType();
		t.setSuperTagTypeId(tagTypeId);
		List<SysTagType> childTypes = tagTypeMapper.selectByEntity(t);
		retList.addAll(childTypes);
		if( childTypes != null && childTypes.size() > 0 ) {
			for(SysTagType item : childTypes) {
				List<SysTagType> nextChilds = this.getChildTypesByTypeId(item.getTagTypeId().toString());
				retList.addAll(nextChilds);
			}
		}
		return retList;
	}

}
