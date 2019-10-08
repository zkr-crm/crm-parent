package cn.com.zsyk.crm.ecif.service.mngcenter.tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.zsyk.crm.ecif.mapper.EcRuleBatchMapper;

@Service
@Transactional
public class RuleBatchDataMngService {

	@Autowired
	private EcRuleBatchMapper ruleBatchMapper;

	public List<Map<String, String>> getRuleBatchData() {

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		List<Map<String, String>> basTagRuleBatchDatas = ruleBatchMapper.getTagRuleBatchData();
		list.addAll(basTagRuleBatchDatas);
		return list;
	}

}
