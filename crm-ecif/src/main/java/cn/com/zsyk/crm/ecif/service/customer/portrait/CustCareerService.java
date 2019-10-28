package cn.com.zsyk.crm.ecif.service.customer.portrait;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.com.zsyk.crm.ecif.bo.cust.PerCustBaseInfo;
import cn.com.zsyk.crm.ecif.entity.EcCustCareer;
import cn.com.zsyk.crm.ecif.mapper.EcCustCareerMapper;
import cn.com.zsyk.crm.generator.EnumType;

@Service
@Transactional 
public class CustCareerService {
	@Autowired
	private EcCustCareerMapper ecCustCareerMapper;

	/**
	 * Desc: 查询客户职业信息
	 * @param custNo
	 * @return
	 * @author
	 */
	public EcCustCareer getCustCareerOne(String custNo) {
		EcCustCareer ecCustCareer = ecCustCareerMapper.selectByPrimaryKey(custNo);
		return ecCustCareer;
	}

	/**
	 * Desc: 保存或更新职业信息
	 * @param perCustBaseInfo
	 * @author
	 */
	public void saveOrUpdateCareer(PerCustBaseInfo perCustBaseInfo) {
		if (StringUtils.isNoneEmpty(perCustBaseInfo.getCustNo())) {
			// 行业、岗位、企业名称值时更新/新增
			if (perCustBaseInfo.getTrade() != null 
					|| perCustBaseInfo.getPosition() != null 
					|| perCustBaseInfo.getUnitNam() != null) {
				EcCustCareer ecCustCareer = ecCustCareerMapper.selectByPrimaryKey(perCustBaseInfo.getCustNo());
				if (null != ecCustCareer) {
					if (ecCustCareer.getRecStat().equals(EnumType.RecStat.delete.getValue())) {
						ecCustCareer.setRecStat(EnumType.RecStat.normal.getValue());
					}
					// 所属行业 trade;
					ecCustCareer.setTrade(perCustBaseInfo.getTrade());
					// 职业 position;
					ecCustCareer.setPosition(perCustBaseInfo.getPosition());
					// 工作单位 unitNam;
					ecCustCareer.setUnitNam(perCustBaseInfo.getUnitNam());
					ecCustCareerMapper.updateByPrimaryKey(ecCustCareer);
				} else {
					EcCustCareer careerObj = new EcCustCareer();
					careerObj.setCustNo(perCustBaseInfo.getCustNo());
					careerObj.setTrade(perCustBaseInfo.getTrade());
					careerObj.setPosition(perCustBaseInfo.getPosition());
					careerObj.setUnitNam(perCustBaseInfo.getUnitNam());
					ecCustCareerMapper.insert(careerObj);
				}
			}
		}

	}

	public List<EcCustCareer> getCustCareerList(EcCustCareer ecCustCareer) {
		List<EcCustCareer> ecCustCareerlst = ecCustCareerMapper.selectCustCareerList(ecCustCareer);
		return ecCustCareerlst;
	}
}
