package cn.com.zsyk.crm.ocrm.service.cnttrack;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.zsyk.crm.common.dao.AbstractDao;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.IdGenerator;
import cn.com.zsyk.crm.ocrm.entity.OcContractTrack;
import cn.com.zsyk.crm.ocrm.entity.OcCustContract;
import cn.com.zsyk.crm.ocrm.mapper.OcContractTrackMapper;

@Service
@Transactional
public class CntTrackService {
	@Autowired
	private AbstractDao daoUtil;
	@Autowired
	private OcContractTrackMapper mapper;
	
	/**
	 * 获得一条联系人轨迹的方法
	 * 
	 * @param trackCd
	 *            联系人轨迹ID
	 * @return 联系人轨迹
	 */
	public OcContractTrack getOneContractTrack(String trackCd) {

		// 联系人轨迹ID非空判断
		OcContractTrack contractTrack = mapper.selectByPrimaryKey(trackCd);

		return contractTrack;
	}


	/**
	 * 获取所有联系人轨迹的方法
	 * 
	 * @return 所有联系人轨迹的列表
	 */
	public List<OcContractTrack> getAllContractTrack() {

		List<OcContractTrack> lstcontractTrack = mapper.selectAll();

		return lstcontractTrack;
	}


	/**
	 *根据联系人id查询
	 * 
	 * @return 联系人的列表
	 */
	public List<OcContractTrack> getCustContractByName(String contractCustNo) {

		List<OcContractTrack> lstUser = daoUtil
				.selectList("cn.com.zsyk.crm.ocrm.mapper.OcContractTrackMapper.selectByContractCustNo", contractCustNo);

		return lstUser;
	}


	/**
	 * 新增一条联系人轨迹
	 * 
	 * @param trackCd
	 *            联系人轨迹ID
	 * @return 新增成功的记录条数
	 */
	public int addContractTrack(OcContractTrack contractTrack) {

		if (contractTrack == null) {
			throw new ServiceException("输入信息不能为空！");
		}

		// 存在判断
		List<OcContractTrack> lstcontractTrack = this.getAllContractTrack();
		contractTrack.setTrackCd(IdGenerator.getUUID());
		int addCount = mapper.insert(contractTrack);

		return addCount;
	}

	/**
	 * 修改某条联系人轨迹的方法
	 * 
	 * @param contractTrack
	 *            需要修改的联系人轨迹
	 * @return 修改成功的条目数，0为失败
	 */
	public int modContractTrack(OcContractTrack contractTrack) {

		if (contractTrack == null) {
			throw new ServiceException("输入信息不能为空！");
		}

		// 存在判断
		OcContractTrack extTest = this.getOneContractTrack(contractTrack.getTrackCd());
		if (extTest == null) {
			throw new ServiceException(
					"联系人轨迹不存在：联系人轨迹ID[" + contractTrack.getTrackCd() + "]");
		}

		

		int modCount = mapper.updateByPrimaryKey(extTest);

		return modCount;
	}



	/**
	 * 根据主键物理删除某联系人轨迹的方法
	 * 
	 * @param trackCd
	 *            联系人轨迹ID
	 * @return
	 */
	public int delContractTrack(String trackCd) {

		// 联系人轨迹ID非空判断
		

		// 存在判断
		OcContractTrack extTest = this.getOneContractTrack(trackCd);
		if (extTest == null) {
			throw new ServiceException("联系人轨迹不存在：联系人轨迹ID[" + trackCd + "]");
		}

		int delCount = mapper.deleteByPrimaryKey(trackCd);

		return delCount;
	}

}
