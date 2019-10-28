package cn.com.zsyk.crm.ecif.web.controller.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.generator.EnumGen;

@RestController
public class EnumCtrl {
	@Autowired
	private EnumGen enumGen;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ecif/enum", method = RequestMethod.GET)
	public Result getEnums() throws Exception {
		Result s = new Result();
		List<Map<String, String>> enums = enumGen.getEnumFromDb();
		
		enums = enums.stream().map((Map<String, String> map) -> {
			Map<String,String> enumItem = new HashMap<String,String>();
			enumItem.put("label", map.get("CODE_DESC"));
			enumItem.put("value", map.get("CODE_VAL"));
			enumItem.put("code_type", map.get("CODE_TYPE"));
			enumItem.put("code_name",  map.get("CODE_NAME"));
			return enumItem;
		}).collect(Collectors.toCollection(ArrayList<Map<String, String>>::new));
		
		Map enumMap = (Map) enums.stream().collect(Collectors.groupingBy(map -> ((Map) map).get("code_type")));
		s.setData(enumMap);
		return s;
	}

}
