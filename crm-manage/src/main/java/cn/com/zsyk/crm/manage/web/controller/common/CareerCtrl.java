package cn.com.zsyk.crm.manage.web.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.manage.service.common.CareerService;

@RestController
public class CareerCtrl {
	@Autowired
	private CareerService careerService;

	/**
	 * @api {GET} /crm/manage/cm/getCareerList 查询职业列表
	 * @apiDescription 
	 * @apiName getCareerList
	 * @apiGroup Common
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			职业编码	careerCode<br/>
	 * 			职业名称	careerName<br/>
	 * 			职业顺序	careerOrder<br/>
	 * 			有效标志	validFlag->YesNoFlg<br/>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/manage/cm/getCareerList", method = RequestMethod.GET)
	public Result getCareerList() {
		Result s = new Result();
		s.setData(careerService.getCareerList());
		return s;
	}

	/**
	 * @api {GET} /crm/manage/cm/getCareerOne 查询职业信息
	 * @apiDescription 
	 * @apiName getCareerOne
	 * @apiGroup Common
	 *
	 * @apiParam {String} careerCode 职业编码
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			职业编码	careerCode<br/>
	 * 			职业名称	careerName<br/>
	 * 			职业顺序	careerOrder<br/>
	 * 			有效标志	validFlag->YesNoFlg<br/>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/manage/cm/getCareerOne", method = RequestMethod.GET)
	public Result getCareerOne(String careerCode) {
		Result s = new Result();
		s.setData(careerService.getCareerOne(careerCode));
		return s;
	}
}
