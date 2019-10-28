package cn.com.zsyk.crm.ecif.service.customer.portrait;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.BeanUtil;
import cn.com.zsyk.crm.common.util.LogUtil;
import cn.com.zsyk.crm.ecif.bo.cust.PerCustBaseInfo;
import cn.com.zsyk.crm.ecif.entity.EcCustEdu;
import cn.com.zsyk.crm.ecif.mapper.EcCustEduMapper;
import cn.com.zsyk.crm.generator.EnumType;

@Service
@Transactional 
public class CustEduService {
	/** logger */
	private final Log logger = LogUtil.getLogger(CustEduService.class);

	@Autowired
	private EcCustEduMapper ecCustEduMapper;

	/**
	 * Desc: 查询客户教育列表
	 * @param EcCustEdu
	 * @return
	 * @author
	 */
	public List<EcCustEdu> getCustEduList(EcCustEdu ecCustEdu) {
		 List<EcCustEdu> ecCustEdulst = ecCustEduMapper.selectCustEduList(ecCustEdu);
		return ecCustEdulst;
	}

	/**
	 * Desc: 查询客户教育信息
	 * @param custNo
	 * @return
	 * @author
	 */
	public EcCustEdu getCustEduOne(String custNo, String eduTyp) {
		EcCustEdu ecCustEdu = ecCustEduMapper.selectByPrimaryKey(custNo, eduTyp);
		return ecCustEdu;
	}

	/**
	 * Desc: 新增客户教育信息
	 * @param ecCustEdu
	 * @author
	 */
	public void addCustEdu(EcCustEdu ecCustEdu) {
		if (ecCustEdu == null) {
			throw new ServiceException("教育信息为空");
		}
		// 客户号
		if (StringUtils.isEmpty(ecCustEdu.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}
		// 教育类型
		if (StringUtils.isEmpty(ecCustEdu.getEduTyp())) {
			throw new ServiceException("教育类型不能为空");
		}
		EcCustEdu obj = ecCustEduMapper.selectByPrimaryKey(ecCustEdu.getCustNo(), ecCustEdu.getEduTyp());
		if (obj != null) {
			throw new ServiceException("客户教育信息已经存在");
		}
		ecCustEdu.setRecStat(EnumType.RecStat.normal.getValue());
		ecCustEduMapper.insert(ecCustEdu);
	}

	/**
	 * Desc: 修改客户教育信息
	 * @param ecCustEdu
	 * @author
	 */
	public void uptCustEdu(EcCustEdu ecCustEdu) {
		if (ecCustEdu == null) {
			throw new ServiceException("教育信息为空");
		}
		// 客户号
		if (StringUtils.isEmpty(ecCustEdu.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}
		// 教育类型
		if (StringUtils.isEmpty(ecCustEdu.getEduTyp())) {
			throw new ServiceException("教育类型不能为空");
		}
		EcCustEdu obj = ecCustEduMapper.selectByPrimaryKey(ecCustEdu.getCustNo(), ecCustEdu.getEduTyp());
		if (obj == null) {
			throw new ServiceException("客户教育信息不存在");
		}
		BeanUtil.copy(ecCustEdu, obj);
		ecCustEduMapper.updateByPrimaryKey(obj);
	}

	/**
	 * Desc: 删除客户教育信息
	 * @param ecCustEdu
	 * @author
	 */
	public void delCustEdu(EcCustEdu ecCustEdu) {
		if (ecCustEdu == null) {
			throw new ServiceException("教育信息为空");
		}
		// 客户号
		if (StringUtils.isEmpty(ecCustEdu.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}
		// 教育类型
		if (StringUtils.isEmpty(ecCustEdu.getEduTyp())) {
			throw new ServiceException("教育类型不能为空");
		}
		EcCustEdu obj = ecCustEduMapper.selectByPrimaryKey(ecCustEdu.getCustNo(), ecCustEdu.getEduTyp());
		if (obj == null) {
			throw new ServiceException("客户教育信息不存在");
		}
		ecCustEduMapper.deleteByPrimaryKey(ecCustEdu.getCustNo(), ecCustEdu.getEduTyp());
	}

	/**
	 * Desc: 客户学历保存更新
	 * @param perCustBaseInfo
	 * @author
	 */
	public void saveOrUpdateCustEdu(PerCustBaseInfo perCustBaseInfo) {

		if (perCustBaseInfo == null) {
			throw new ServiceException("客户参数不能为空");
		}

		if (StringUtils.isEmpty(perCustBaseInfo.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}

		/*if (StringUtils.isEmpty(perCustBaseInfo.getEduDegree())) {
			throw new ServiceException("客户文化程度不能为空");
		}*/
		//EcCustEdu obj = ecCustEduMapper.selectByPrimaryKey(perCustBaseInfo.getCustNo(), perCustBaseInfo.getEduDegree());
		EcCustEdu obj = ecCustEduMapper.selectHighestEduByCustNo(perCustBaseInfo.getCustNo());
		if (obj != null) {
			ecCustEduMapper.deleteByPrimaryKey(obj.getCustNo(), obj.getEduTyp());
		}
		EcCustEdu newObj = ecCustEduMapper.selectByPrimaryKey(perCustBaseInfo.getCustNo(), perCustBaseInfo.getEduDegree());
		if (newObj == null) {
			EcCustEdu ecCustEdu = new EcCustEdu();
			ecCustEdu.setCustNo(perCustBaseInfo.getCustNo());
			ecCustEdu.setEduTyp(perCustBaseInfo.getEduDegree());
			ecCustEdu.setSchoolNam(perCustBaseInfo.getSchoolNam());
			ecCustEduMapper.insert(ecCustEdu);
		} else {
			throw new ServiceException("客户学历信息存在");
		}
}

	/**
	 * Desc: 客户学历保存更新
	 * @param perCustBaseInfo
	 * @author
	 */
	public void updateCustEdu(PerCustBaseInfo perCustBaseInfo) {

		if (perCustBaseInfo == null) {
			throw new ServiceException("客户参数不能为空");
		}

		if (StringUtils.isEmpty(perCustBaseInfo.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}

		// 文化程度与毕业院校必须同时输入，有效
		if (StringUtils.isEmpty(perCustBaseInfo.getEduDegree())) {
			if (StringUtils.isNotEmpty(perCustBaseInfo.getSchoolNam())) {
				throw new ServiceException("客户文化程度不能为空");
			}
		} else {
			if (StringUtils.isEmpty(perCustBaseInfo.getSchoolNam())) {
				throw new ServiceException("客户毕业院校不能为空");
			}
			EcCustEdu ecCustEdu = new EcCustEdu();
			ecCustEdu.setCustNo(perCustBaseInfo.getCustNo());
			ecCustEdu.setEduTyp(perCustBaseInfo.getEduDegree());
			ecCustEdu.setSchoolNam(perCustBaseInfo.getSchoolNam());
			ecCustEduMapper.insert(ecCustEdu);
		}
	}
}
