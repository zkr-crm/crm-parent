package cn.com.zsyk.crm.manage.service.common;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.com.zsyk.crm.manage.entity.SysCity;
import cn.com.zsyk.crm.manage.entity.SysCounty;
import cn.com.zsyk.crm.manage.entity.SysProvince;
import cn.com.zsyk.crm.manage.mapper.SysCityMapper;
import cn.com.zsyk.crm.manage.mapper.SysCountyMapper;
import cn.com.zsyk.crm.manage.mapper.SysProvinceMapper;

@Service
@Transactional 
public class ProvCityCtyCtrlService {
	
	@Autowired
	SysProvinceMapper sysProvinceMapper;
	@Autowired
	SysCityMapper sysCityMapper;
	@Autowired
	SysCountyMapper sysCountyMapper;

	/**
	 * Desc: 查询省列表
	 * @return
	 * @author
	 */
	public List<SysProvince> getProv() {
		return sysProvinceMapper.selectAll();
	}

	/**
	 * Desc: 根据省查询市列表
	 * @param provinceCode
	 * @return
	 * @author
	 */
	public List<SysCity> getCityByProv(String provinceCode) {
		return sysCityMapper.selectByProvCd(provinceCode);
	}

	/**
	 * Desc: 根据省市查询区县列表
	 * @param provinceCode
	 * @param cityCode
	 * @return
	 * @author
	 */
	public List<SysCounty> getCountyByProvCity(String provinceCode, String cityCode) {
		return sysCountyMapper.selectByProvCityCd(provinceCode, cityCode);
	}

	/**
	 * Desc: 查询省信息
	 * @param provinceCode
	 * @return
	 * @author
	 */
	public SysProvince getProvByProvCd(String provinceCode) {
		return sysProvinceMapper.selectByPrimaryKey(provinceCode);
	}

	/**
	 * Desc: 查询市信息
	 * @param provinceCode
	 * @return
	 * @author
	 */
	public SysCity getCityByCityCd(String cityCode) {
		return sysCityMapper.selectByPrimaryKey(cityCode);
	}

	/**
	 * Desc: 查询区县信息
	 * @param countyCode
	 * @return
	 * @author
	 */
	public SysCounty getCountyByCountyCd(String countyCode) {
		return sysCountyMapper.selectByPrimaryKey(countyCode);
	}
	/**
	 * Desc: 获取详细地址(不包含街道)
	 * @param provinceCode
	 * @param cityCode
	 * @param countyCode
	 * @return
	 * @author
	 */
	public String getDetailAddr(String provinceCode, String cityCode, String countyCode) {
		StringBuffer detailAddr = new StringBuffer();
		// 省
		if (StringUtils.isNotEmpty(provinceCode)) {
			SysProvince sysProvince = sysProvinceMapper.selectByPrimaryKey(provinceCode);
			if (sysProvince != null) {
				detailAddr.append(sysProvince.getProvinceName());
			}
		}

		// 市
		if (StringUtils.isNotEmpty(cityCode)) {
			SysCity sysCity = sysCityMapper.selectByPrimaryKey(cityCode);
			if (sysCity != null) {
				detailAddr.append( sysCity.getCityName());
			}
		}

		// 区县
		if (StringUtils.isNotEmpty(countyCode)) {
			SysCounty sysCounty = sysCountyMapper.selectByPrimaryKey(countyCode);
			if (sysCounty != null) {
				detailAddr.append(sysCounty.getCountyName());
			}
		}
		return detailAddr.toString();
	}
}
