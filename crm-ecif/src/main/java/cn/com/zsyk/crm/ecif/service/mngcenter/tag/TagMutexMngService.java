package cn.com.zsyk.crm.ecif.service.mngcenter.tag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.ecif.entity.EcTagDetail;
import cn.com.zsyk.crm.ecif.entity.EcTagMutex;
import cn.com.zsyk.crm.ecif.mapper.EcTagDetailMapper;
import cn.com.zsyk.crm.ecif.mapper.EcTagMutexMapper;;

@Service
@Transactional
public class TagMutexMngService {

	@Autowired
	
	private EcTagMutexMapper tagMutexMapper;
	private EcTagDetailMapper tagDetailMapper;
	
	/**
	 * 查询标签互斥单条记录
	 * @param rule_seq_no
	 * @param tagId
	 * @return
	 */
	public EcTagMutex getTagMutex(String rule_seq_no,String tagId) {

		EcTagMutex tagMutex = new EcTagMutex();

		tagMutex = tagMutexMapper.selectByPrimaryKey(rule_seq_no,tagId);

		return tagMutex;
	}
	
	
	
	/**
	 * 查询互斥标签列表(任意输入互斥编号、标签名称、标签id)
	 * @param rule_seq_no
	 * @return
	 */
	public List<EcTagMutex> getTagMutexes(EcTagMutex tagMutex) {

		List<EcTagMutex> resTagMutexes = new ArrayList<EcTagMutex>();

		resTagMutexes = tagMutexMapper.selectByEntity(tagMutex);

		return resTagMutexes;
	}

	/**
	 * 向一个互斥组增加一条互斥标签
	 * @param tagMutex（序号、标签id、标签名称必须）
	 * @return
	 */
	public int addTagMutex(EcTagMutex tagMutex) {
		List<EcTagMutex> sameTagMutexList = this.getTagMutexes(tagMutex);
		if (sameTagMutexList != null) {
			throw new ServiceException("标签已存在于组内!");
		}

		int addCount = tagMutexMapper.insert(tagMutex);
		return addCount;
	}
	
	
	/**
	 * 创建新的互斥组
	 * @param tagIds 传入的互斥的 tagId String数组
	 * @return 本次建立的互斥id
	 */
	public String addTagMutexes(String[] tagIds) {
		
		if(tagIds==null || tagIds.length==0  ) {
			throw new ServiceException("请检查输入参数!");
		}
		
		int addCount = 0;
		String last_seqno="";
		
		for(String oneTag : tagIds) {
			EcTagDetail tagDetail = tagDetailMapper.selectByPrimaryKey(oneTag);
			EcTagMutex ecTagMutex = new EcTagMutex();
			ecTagMutex.setTagId(oneTag);
			ecTagMutex.setTagName(tagDetail.getTagName());
			ecTagMutex.setTagTypeId(tagDetail.getTagTypeId());
			if(last_seqno.equals("")) {
				addCount = tagMutexMapper.insert(ecTagMutex);
				last_seqno = ecTagMutex.getRuleSeqNo();//及时返回上一次插入的id
				
			} else {
				ecTagMutex.setRuleSeqNo(last_seqno);
				addCount = tagMutexMapper.insertRest(ecTagMutex);
			}
			
			if(addCount!=1) throw new ServiceException("插入失败!");
		}
		return last_seqno;
	}

	 
	/**
	 * 删除互斥标签（逻辑）
	 * 在当前互斥标签组里剔除一个
	 * @param tagMutex
	 * @return
	 */
	public int delModTagMutex(EcTagMutex tagMutex) {
		EcTagMutex extTagMutex = this.getTagMutex(tagMutex.getRuleSeqNo(), tagMutex.getTagId());
		if (extTagMutex == null) {
			throw new ServiceException("互斥标签不存在!");
		}

		tagMutex.setRecStat("0");
		int modCount = tagMutexMapper.update(tagMutex);

		return modCount;
	}
	
	/**
	 * 删除互斥标签组（逻辑）
	 * 
	 * @param tagMutex
	 * @return
	 */
	public int delModTagMutexes(EcTagMutex tagMutex) {
		List<EcTagMutex> extTagMutexes = this.getTagMutexes(tagMutex);
		if (extTagMutexes == null||extTagMutexes.isEmpty()) {
			throw new ServiceException("互斥标签组不存在!");
		}
		tagMutex.setRecStat("0");
		int modCount = tagMutexMapper.update(tagMutex);

		return modCount;
	}

}
