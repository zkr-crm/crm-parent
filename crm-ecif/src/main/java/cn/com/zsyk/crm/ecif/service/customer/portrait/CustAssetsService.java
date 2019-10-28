package cn.com.zsyk.crm.ecif.service.customer.portrait;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.BeanUtil;
import cn.com.zsyk.crm.common.util.DateUtil;
import cn.com.zsyk.crm.ecif.entity.EcAssetsEnt;
import cn.com.zsyk.crm.ecif.entity.EcAssetsPer;
import cn.com.zsyk.crm.ecif.mapper.EcAssetsEntMapper;
import cn.com.zsyk.crm.ecif.mapper.EcAssetsPerMapper;
import cn.com.zsyk.crm.generator.EnumType;

@Service
@Transactional 
public class CustAssetsService {

	@Autowired
	private EcAssetsPerMapper ecAssetsPerMapper;
	@Autowired
	private EcAssetsEntMapper ecAssetsEntMapper;
	/**
	 * Desc: 查询个人资产列表
	 * @param custNo
	 * @return
	 * @author
	 */
	public List<EcAssetsPer> getPerAssetsList(EcAssetsPer ecAssetsPer) {
		List<EcAssetsPer> ecAssetsPerlst = ecAssetsPerMapper.selectAssetsList(ecAssetsPer);
		return ecAssetsPerlst;
	}

	/**
	 * Desc: 查询个人资产信息
	 * @param custNo
	 * @return
	 * @author
	 */
	public EcAssetsPer getPerCustAssetsByPk(String custNo, int seqNo) {
		EcAssetsPer ecAssetsPer = ecAssetsPerMapper.selectByPrimaryKey(custNo, seqNo);
		return ecAssetsPer;
	}

	/**
	 * Desc: 查询企业资产信息
	 * @param custNo
	 * @return
	 * @author
	 */
	public EcAssetsEnt getEntCustAssetsInfo(String custNo) {
		EcAssetsEnt ecAssetsEnt = ecAssetsEntMapper.selectByPrimaryKey(custNo);
		return ecAssetsEnt;
	}

	/**
	 * Desc: 获取下一个Sqn
	 * @param custNo
	 * @return
	 * @author
	 */
	public int getNextSeqNoByCustNo(String custNo) {
		int nextSqn = ecAssetsPerMapper.getNextSeqNoByCustNo(custNo);
		return nextSqn;
	}

	/**
	 * Desc: 新增个人资产信息
	 * @param ecAssetsPer
	 * @author
	 */
	public void addPerCustAssets(EcAssetsPer ecAssetsPer) {
		if (ecAssetsPer == null) {
			throw new ServiceException("新增个人资产参数为空");
		}
		// 客户号
		if (StringUtils.isEmpty(ecAssetsPer.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}
		if (StringUtils.isEmpty(ecAssetsPer.getAssetsNam())) {
			throw new ServiceException("资产名称不能为空");
		}
		if (StringUtils.isEmpty(ecAssetsPer.getAssetsTyp())) {
			throw new ServiceException("资产类型不能为空");
		}
		if (ecAssetsPer.getObtainTime() == null) {
			throw new ServiceException("获取日期不能为空");
		}
		if (StringUtils.isEmpty(ecAssetsPer.getPrincipal())) {
			throw new ServiceException("负责人不能为空");
		}

		ecAssetsPer.setSeqNo(getNextSeqNoByCustNo(ecAssetsPer.getCustNo()));
		EcAssetsPer assetsPerObj = ecAssetsPerMapper.selectByPrimaryKey(ecAssetsPer.getCustNo(), ecAssetsPer.getSeqNo());
		if (assetsPerObj == null) {
			ecAssetsPer.setRecStat(EnumType.RecStat.normal.getValue());
			ecAssetsPer.setEstablishTime(DateUtil.getCurTime());
			ecAssetsPerMapper.insert(ecAssetsPer);
		} else {
			if (assetsPerObj.getRecStat().equals(EnumType.RecStat.delete.getValue())) {
				BeanUtil.copy(ecAssetsPer, assetsPerObj);
				assetsPerObj.setRecStat(EnumType.RecStat.normal.getValue());
				assetsPerObj.setEstablishTime(DateUtil.getCurTime());
				ecAssetsPerMapper.updateByPrimaryKey(assetsPerObj);
			} else {
				throw new ServiceException("个人资产信息已经存在");
			}
		}

	}

	/**
	 * Desc: 修改个人资产信息
	 * @param ecAssetsPer
	 * @author
	 */
	public void uptPerCustAssets(EcAssetsPer ecAssetsPer) {
		if (ecAssetsPer == null) {
			throw new ServiceException("修改个人资产信息为空");
		}
		// 客户号
		if (StringUtils.isEmpty(ecAssetsPer.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}
		if (ecAssetsPer.getSeqNo() == null) {
			throw new ServiceException("资产序号不能为空");
		}
		if (StringUtils.isEmpty(ecAssetsPer.getAssetsNam())) {
			throw new ServiceException("资产名称不能为空");
		}
		if (StringUtils.isEmpty(ecAssetsPer.getAssetsTyp())) {
			throw new ServiceException("资产类型不能为空");
		}
		if (ecAssetsPer.getObtainTime() == null) {
			throw new ServiceException("获取日期不能为空");
		}
		if (StringUtils.isEmpty(ecAssetsPer.getPrincipal())) {
			throw new ServiceException("负责人不能为空");
		}
		EcAssetsPer assetsPerObj = ecAssetsPerMapper.selectByPrimaryKey(ecAssetsPer.getCustNo(), ecAssetsPer.getSeqNo());
		if (assetsPerObj == null) {
			throw new ServiceException("个人资产信息不存在");
		}
		BeanUtil.copy(ecAssetsPer, assetsPerObj);
		if (assetsPerObj.getRecStat().equals(EnumType.RecStat.delete.getValue())) {
			assetsPerObj.setRecStat(EnumType.RecStat.normal.getValue());
		}
		ecAssetsPerMapper.updateByPrimaryKey(assetsPerObj);
	}

	/**
	 * Desc: 删除个人资产信息
	 * @param ecAssetsPer
	 * @author
	 */
	public void delPerCustAssets(EcAssetsPer ecAssetsPer) {
		if (ecAssetsPer == null) {
			throw new ServiceException("修改个人资产参数为空");
		}
		// 客户号
		if (StringUtils.isEmpty(ecAssetsPer.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}
		if (ecAssetsPer.getSeqNo() == null) {
			throw new ServiceException("资产序号不能为空");
		}
		EcAssetsPer obj = ecAssetsPerMapper.selectByPrimaryKey(ecAssetsPer.getCustNo(), ecAssetsPer.getSeqNo());
		if (obj == null) {
			throw new ServiceException("客户资产信息不存在");
		}
		ecAssetsPerMapper.deleteByPrimaryKey(ecAssetsPer.getCustNo(), ecAssetsPer.getSeqNo());
	}

}
