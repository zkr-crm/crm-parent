package cn.com.zsyk.crm.manage.web.controller.engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.manage.service.engine.BomTagFactorsService;

@RestController
public class BomFactorsController {

	@Autowired
	private BomTagFactorsService service;


	/**
	 * @api {get} /crm/manage/engine/bomTagFactors 查询标签的所有bom因子
	 * @apiName getBomTagFactors
	 * @apiGroup engine
	 * @apiSuccess {Object} data
	 * I
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/manage/engine/bomTagFactors", method = RequestMethod.GET)
	public Result getBomTagFactors() {
		String bomTreeFactors="";
		Result res = new Result();
		try {
			bomTreeFactors = service.getBomTagFactors();
		} catch (Exception e) {
			e.printStackTrace();
			res.exception("取bom树因子失败!");
			
		}
		res.setData(bomTreeFactors);
		return res;
	}

	
}
