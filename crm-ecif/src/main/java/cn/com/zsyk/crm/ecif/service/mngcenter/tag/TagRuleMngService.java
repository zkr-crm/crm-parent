package cn.com.zsyk.crm.ecif.service.mngcenter.tag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.ecif.entity.EcTagRule;
import cn.com.zsyk.crm.ecif.mapper.EcTagRuleMapper;;

@Service
@Transactional
public class TagRuleMngService {

	@Autowired
	private EcTagRuleMapper tagRuleMapper;

	/**
	 * 查询标签规则信息
	 * 
	 * @param tagRuleId
	 * @return
	 */
	public EcTagRule getOneTagRule(int seqNo, String tagId) {

		EcTagRule tagRule = new EcTagRule();

		tagRule = tagRuleMapper.selectByPrimaryKey(seqNo, tagId);

		return tagRule;
	}

	/**
	 * 验证新增标签规则唯一（一个标签一个规则类型下属性值唯一）
	 * 
	 * @param tagRuleLevel
	 * @param tagRuleName
	 * @return
	 */
	public EcTagRule checkTagRuleUnique(String tagId, String tagRuleType, String attrName) {

		EcTagRule p_tagRule = new EcTagRule();
		p_tagRule.setAttrName(attrName);
		p_tagRule.setTagId(tagId);
		p_tagRule.setTagRuleType(tagRuleType);

		EcTagRule tagRule = new EcTagRule();

		tagRule = tagRuleMapper.selectTagRuleByIdRuleTypeAttr(p_tagRule);

		return tagRule;
	}

	/**
	 * 查询标签规则列表（任意输入标签id、属性、规则类型、标签规则、标签名称查询条件）
	 * 
	 * @param tagRule
	 * @return
	 */
	public List<EcTagRule> getTagRules(EcTagRule tagRule) {

		List<EcTagRule> resTagRules = new ArrayList<EcTagRule>();

		resTagRules = tagRuleMapper.selectByEntity(tagRule);

		return resTagRules;
	}

	/**
	 * 增加标签规则
	 * 
	 * @param tagRule
	 * @return
	 */
	public int addTagRule(EcTagRule tagRule) {

		EcTagRule sameTagRule = this.checkTagRuleUnique(tagRule.getTagId(), tagRule.getTagRuleType(),
				tagRule.getAttrName());
		if (sameTagRule != null) {
			throw new ServiceException("标签规则已存在!");
		}

		int addCount = tagRuleMapper.insert(tagRule);
		return addCount;
	}

	/**
	 * 修改标签规则
	 * 
	 * @param tagRule
	 * @return
	 */
	public int modTagRule(EcTagRule tagRule) {
		EcTagRule extTagRule = this.getOneTagRule(tagRule.getSeqNo(), tagRule.getTagId());
		if (extTagRule == null) {
			throw new ServiceException("标签规则不存在!");
		}

		int modCount = tagRuleMapper.updateByPrimaryKey(tagRule);

		return modCount;
	}

	/**
	 * 删除标签规则（物理）
	 * 
	 * @param tagRule
	 * @return
	 */
	public int delTagRule(EcTagRule tagRule) {
		EcTagRule extTagRule = this.getOneTagRule(tagRule.getSeqNo(), tagRule.getTagId());
		if (extTagRule == null) {
			throw new ServiceException("标签规则不存在!");
		}

		int delCount = tagRuleMapper.deleteByPrimaryKey(tagRule.getSeqNo(), tagRule.getTagId());

		return delCount;
	}

	/**
	 * 删除标签规则（逻辑）
	 * 
	 * @param tagRule
	 * @return
	 */
	public int delModTagRule(EcTagRule tagRule) {
	
		EcTagRule extTagRule = this.getOneTagRule(tagRule.getSeqNo(), tagRule.getTagId());
		if (extTagRule == null) {
			throw new ServiceException("标签规则不存在!");
		}

		extTagRule.setRecStat("0");
		int modCount = tagRuleMapper.updateByPrimaryKey(extTagRule);

		return modCount;
	}

}
