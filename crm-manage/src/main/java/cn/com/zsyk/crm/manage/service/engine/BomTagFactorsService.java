package cn.com.zsyk.crm.manage.service.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.zsyk.crm.manage.bom.util.ReadBean;
import net.sf.json.JSONArray;

@Service
@Transactional
public class BomTagFactorsService {

	public String getBomTagFactors() throws Exception {
		List<Map<String, String>> bomTreeFactorsList = new ArrayList<Map<String, String>>();
		try {

			List<String> classNames_under_package = ReadBean.getBomClassNames("cn.com.zsyk.crm.manage.bom.tag");

			for (String className : classNames_under_package) {

				Object obj = Class.forName(className).newInstance();
				Map<String, String> modelAttriButeType = ReadBean.getModelAttriButeType(obj);
				bomTreeFactorsList.add(modelAttriButeType);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return JSONArray.fromObject(bomTreeFactorsList).toString();
	}

}
