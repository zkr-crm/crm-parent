package cn.com.zsyk.crm.ecif.service.mngcenter.tag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.ecif.entity.EcTagInclud;
import cn.com.zsyk.crm.ecif.mapper.EcTagIncludMapper;;

@Service
@Transactional
public class TagIncludMngService {

	@Autowired
	private EcTagIncludMapper tagIncludMapper;

	/**
	 * 查询标签包含信息
	 * @param tagId
	 * @param includ_tag_id
	 * @return
	 */
	public EcTagInclud getTagInclude(String tagId,String includ_tag_id) {

		EcTagInclud TagInclude = new EcTagInclud();

		TagInclude = tagIncludMapper.selectByPrimaryKey(tagId,includ_tag_id);

		return TagInclude;
	}

	/**
	 * 查询标签包含信息列表（任意输入标签id、标签名称、被包含标签id、被包含标签名称）
	 * 
	 * @param TagInclude
	 * @return
	 */
	public List<EcTagInclud> getTagIncludes(EcTagInclud TagInclude) {

		List<EcTagInclud> resTagIncludes = new ArrayList<EcTagInclud>();

		resTagIncludes = tagIncludMapper.selectByEntity(TagInclude);

		return resTagIncludes;
	}

	/**
	 * 增加标签包含信息
	 * 
	 * @param TagInclude
	 * @return
	 */
	public int addTagInclude(EcTagInclud TagInclude) {
		EcTagInclud sameTagInclude = this.getTagInclude(TagInclude.getTagId(), TagInclude.getIncludTagId());
		if (sameTagInclude != null) {
			throw new ServiceException("标签包含信息已存在!");
		}

		int addCount = tagIncludMapper.insert(TagInclude);
		return addCount;
	}

	/**
	 * 修改标签包含信息
	 * 
	 * @param TagInclude
	 * @return
	 */
	public int modTagInclude(EcTagInclud TagInclude) {
		EcTagInclud extTagInclude =  this.getTagInclude(TagInclude.getTagId(), TagInclude.getIncludTagId());
		if (extTagInclude == null) {
			throw new ServiceException("标签包含信息不存在!");
		}

		int modCount = tagIncludMapper.updateByPrimaryKey(TagInclude);

		return modCount;
	}

	/**
	 * 删除标签包含信息（物理）
	 * 
	 * @param TagInclude
	 * @return
	 */
	public int delTagInclude(EcTagInclud TagInclude) {
		EcTagInclud extTagInclude = this.getTagInclude(TagInclude.getTagId(), TagInclude.getIncludTagId());
		if (extTagInclude == null) {
			throw new ServiceException("标签包含信息不存在!");
		}

		int delCount = tagIncludMapper.deleteByPrimaryKey(TagInclude.getTagId(), TagInclude.getIncludTagId());

		return delCount;
	}

	/**
	 * 删除标签包含（逻辑）
	 * 
	 * @param TagInclude
	 * @return
	 */
	public int delModTagInclude(EcTagInclud TagInclude) {
		EcTagInclud extTagInclude = this.getTagInclude(TagInclude.getTagId(), TagInclude.getIncludTagId());
		if (extTagInclude == null) {
			throw new ServiceException("标签包含信息不存在!");
		}

		TagInclude.setRecStat("0");
		int modCount = tagIncludMapper.updateByPrimaryKey(TagInclude);

		return modCount;
	}

}
