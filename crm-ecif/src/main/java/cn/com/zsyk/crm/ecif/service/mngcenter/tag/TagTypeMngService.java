package cn.com.zsyk.crm.ecif.service.mngcenter.tag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.ecif.entity.EcTagType;
import cn.com.zsyk.crm.ecif.mapper.EcTagTypeMapper;;

@Service
@Transactional
public class TagTypeMngService {

	@Autowired
	private EcTagTypeMapper tagTypeMapper;

	/**
	 * 查询标签类别信息
	 * 
	 * @param tagTypeId
	 * @return
	 */
	public EcTagType getOneTagType(String tagTypeId) {

		EcTagType tagType = new EcTagType();

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
	public EcTagType getTagTypeByLevelName(String tagTypeLevel, String tagTypeName) {

		EcTagType p_tagType = new EcTagType();
		p_tagType.setTagTypeLevel(tagTypeLevel);
		p_tagType.setTagTypeName(tagTypeName);
		EcTagType tagType = new EcTagType();

		tagType = tagTypeMapper.selectByTagTypeLevelName(p_tagType);

		return tagType;
	}

	/**
	 * 查询标签类别列表（任意输入级别、标签类别名称）
	 * 
	 * @param tagType
	 * @return
	 */
	public List<EcTagType> getTagTypes(EcTagType tagType) {

		List<EcTagType> restagTypes = new ArrayList<EcTagType>();

		restagTypes = tagTypeMapper.selectByEntity(tagType);

		return restagTypes;
	}

	/**
	 * 增加标签类别
	 * 
	 * @param tagType
	 * @return
	 */
	public int addTagType(EcTagType tagType) {
		EcTagType sametagType = this.getTagTypeByLevelName(tagType.getTagTypeLevel(), tagType.getTagTypeName());
		if (sametagType != null) {
			throw new ServiceException("标签类别已存在!");
		}

		int addCount = tagTypeMapper.insert(tagType);
		return addCount;
	}

	/**
	 * 修改标签类别
	 * 
	 * @param tagType
	 * @return
	 */
	public int modTagType(EcTagType tagType) {
		EcTagType extTagType = this.getOneTagType(tagType.getTagTypeId());
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
	public int delTagType(EcTagType tagType) {
		EcTagType exttagType = this.getOneTagType(tagType.getTagTypeId());
		if (exttagType == null) {
			throw new ServiceException("标签类别不存在!");
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
	public int delModTagType(EcTagType tagType) {
		EcTagType exttagType = this.getOneTagType(tagType.getTagTypeId());
		if (exttagType == null) {
			throw new ServiceException("标签类别不存在!");
		}

		exttagType.setRecStat("0");
		int modCount = tagTypeMapper.updateByPrimaryKey(exttagType);

		return modCount;
	}

}
