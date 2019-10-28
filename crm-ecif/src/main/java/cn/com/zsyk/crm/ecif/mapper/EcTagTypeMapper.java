package cn.com.zsyk.crm.ecif.mapper;

import java.util.List;

import cn.com.zsyk.crm.ecif.entity.EcTagType;

public interface EcTagTypeMapper {

	int deleteByPrimaryKey(String tagTypeId);

	int insert(EcTagType record);

	EcTagType selectByPrimaryKey(String tagTypeId);

	List<EcTagType> selectAll();

	int updateByPrimaryKey(EcTagType record);

	/**
	 * 查询某个标签等级下某个名称的标签类型信息
	 * 
	 * @param tagType
	 * @return
	 */
	EcTagType selectByTagTypeLevelName(EcTagType tagType);

	/**
	 * 通过标签类别等级、标签类别名称查询标签类别列表
	 * 
	 * @param tagType
	 * @return
	 */
	List<EcTagType> selectByEntity(EcTagType tagType);

}