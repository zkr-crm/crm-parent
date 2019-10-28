package cn.com.zsyk.crm.ecif.service.mngcenter.tag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.ecif.entity.EcTagDetail;
import cn.com.zsyk.crm.ecif.mapper.EcTagDetailMapper;;

@Service
@Transactional
public class TagDetailMngService {

	@Autowired
	private EcTagDetailMapper tagDetailMapper;

	/**
	 * 查询标签明细
	 * 
	 * @param tagId
	 * @return
	 */
	public EcTagDetail getOneTag(String tagId) {

		EcTagDetail tagDetail = new EcTagDetail();

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
	public EcTagDetail getOneTagByNameType(String tagTypeId, String tagName) {

		EcTagDetail p_tagDetail = new EcTagDetail();
		p_tagDetail.setTagTypeId(tagTypeId);
		p_tagDetail.setTagName(tagName);
		EcTagDetail tagDetail = new EcTagDetail();

		tagDetail = tagDetailMapper.selectByTagNamType(p_tagDetail);

		return tagDetail;
	}

	/**
	 * 查询标签明细（任意输入类别、标签名称）
	 * 
	 * @param tagDetail
	 * @return
	 */
	public List<EcTagDetail> getTagDetails(EcTagDetail tagDetail) {

		List<EcTagDetail> resTagDetails = new ArrayList<EcTagDetail>();

		resTagDetails = tagDetailMapper.selectByEntity(tagDetail);

		return resTagDetails;
	}

	/**
	 * 增加标签
	 * 
	 * @param tagDetail
	 * @return
	 */
	public int addTag(EcTagDetail tagDetail) {
		EcTagDetail sameTagDetail = this.getOneTagByNameType(tagDetail.getTagTypeId(), tagDetail.getTagName());
		if (sameTagDetail != null) {
			throw new ServiceException("标签已存在!");
		}

		int addCount = tagDetailMapper.insert(tagDetail);
		return addCount;
	}

	/**
	 * 修改标签
	 * 
	 * @param tagDetail
	 * @return
	 */
	public int modTag(EcTagDetail tagDetail) {
		EcTagDetail extTagDetail = this.getOneTag(tagDetail.getTagId());
		if (extTagDetail == null) {
			throw new ServiceException("标签不存在!");
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
	public int delTag(EcTagDetail tagDetail) {
		EcTagDetail extTagDetail = this.getOneTag(tagDetail.getTagId());
		if (extTagDetail == null) {
			throw new ServiceException("标签不存在!");
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
	public int delModTag(EcTagDetail tagDetail) {
		EcTagDetail extTagDetail = this.getOneTag(tagDetail.getTagId());
		if (extTagDetail == null) {
			throw new ServiceException("标签不存在!");
		}

		extTagDetail.setRecStat("0");
		int modCount = tagDetailMapper.updateByPrimaryKey(extTagDetail);

		return modCount;
	}

}
