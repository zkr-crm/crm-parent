package cn.com.zsyk.crm.query.service.synchinfo;

import cn.com.zsyk.crm.query.entity.GGCompany;
import cn.com.zsyk.crm.query.entity.SysEnterInfo;
import cn.com.zsyk.crm.query.mapper.GGCompanyMapper;
import com.alibaba.fastjson.JSON;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SyncCompanyService {

	@Autowired
	private GGCompanyMapper ggCompanyMapper;
	@Autowired
	private AmqpTemplate amqpTemplate;
	/**
	 * 全量同步用户信息
	 *
	 * @return int
	 */
	public int getAllCompany() {
        List<GGCompany> logList = ggCompanyMapper.selectAll();
		SysEnterInfo sysEnterInfo;
		for (GGCompany ggcompany:logList) {

			if("1".equals(ggcompany.getValidind())){
                sysEnterInfo=new SysEnterInfo();
                sysEnterInfo.setEnterCode(ggcompany.getCompanycode());
                sysEnterInfo.setEnterName(ggcompany.getCompanycname());
                if(!ggcompany.getUppercompanycode().equals(ggcompany.getCompanycode())){
					sysEnterInfo.setSuperEnterCode(ggcompany.getUppercompanycode());
				}
                sysEnterInfo.setEnterLevel(ggcompany.getComlevel());
                sysEnterInfo.setEnterHead(ggcompany.getInsurercname());
                sysEnterInfo.setEnterAddr(ggcompany.getAddresscname());
                sysEnterInfo.setEnterTel(ggcompany.getPhonenumber());
				amqpTemplate.convertAndSend("companyQueue", JSON.toJSONString(sysEnterInfo));
			}
		}
        return 1;
	}

}
