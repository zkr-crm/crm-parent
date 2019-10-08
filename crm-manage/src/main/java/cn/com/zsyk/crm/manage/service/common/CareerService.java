package cn.com.zsyk.crm.manage.service.common;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.com.zsyk.crm.manage.entity.SysCareer;
import cn.com.zsyk.crm.manage.mapper.SysCareerMapper;

@Service
@Transactional 
public class CareerService {
	
	@Autowired
	private SysCareerMapper sysCareerMapper;

	/**
	 * Desc: 查询职业列表
	 * @return
	 * @author
	 */
	public List<SysCareer> getCareerList() {
		return sysCareerMapper.selectCareerList();
	}

	/**
	 * Desc: 查询职业信息
	 * @param careerCode
	 * @return
	 * @author
	 */
	public SysCareer getCareerOne(String careerCode) {
		return sysCareerMapper.selectByPrimaryKey(careerCode);
	}
}
