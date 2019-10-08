package cn.com.zsyk.crm.manage.web.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.manage.service.common.ProvCityCtyCtrlService;

@RestController
public class ProvCityCtyCtrl {
	@Autowired
	private ProvCityCtyCtrlService provCityCtyCtrlService;

	/**
	 * @api {GET} /crm/manage/cm/getProv 查询省列表
	 * @apiDescription 
	 * @apiName getProv
	 * @apiGroup Common
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			省份编码	provinceCode<br/>
	 * 			省份名称	provinceName<br/>
	 * 			省份顺序	provinceOrder<br/>
	 * 			有效标志	validFlag->YesNoFlg<br/>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/manage/cm/getProv", method = RequestMethod.GET)
	public Result getProv() {
		Result s = new Result();
		s.setData(provCityCtyCtrlService.getProv());
		return s;
	}

	/**
	 * @api {GET} /crm/manage/cm/getProvByProvCd 根据省代码查询省信息
	 * @apiDescription 
	 * @apiName getProvByProvCd
	 * @apiGroup Common
	 *
	 * @apiParam {String} provinceCode 省编码
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			省份编码	provinceCode<br/>
	 * 			省份名称	provinceName<br/>
	 * 			省份顺序	provinceOrder<br/>
	 * 			有效标志	validFlag->YesNoFlg<br/>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/manage/cm/getProvByProvCd", method = RequestMethod.GET)
	public Result getProvByProvCd(String provinceCode) {
		Result s = new Result();
		s.setData(provCityCtyCtrlService.getProvByProvCd(provinceCode));
		return s;
	}

	/**
	 * @api {GET} /crm/manage/cm/getCityByProv 根据省查询市列表
	 * @apiDescription 
	 * @apiName getCityByProv
	 * @apiGroup Common
	 *
	 * @apiParam {String} provinceCode 省编码
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			地区编码	cityCode<br/>
	 * 			地区名称	cityName<br/>
	 * 			省份编码	provinceCode<br/>
	 * 			地区顺序	cityOrder<br/>
	 * 			有效标志	validFlag->YesNoFlg<br/>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/manage/cm/getCityByProv", method = RequestMethod.GET)
	public Result getCityByProv(String provinceCode) {
		Result s = new Result();
		s.setData(provCityCtyCtrlService.getCityByProv(provinceCode));
		return s;
	}

	/**
	 * @api {GET} /crm/manage/cm/getCityByCityCd 根据市代码查询市信息
	 * @apiDescription 
	 * @apiName getCityByCityCd
	 * @apiGroup Common
	 *
	 * @apiParam {String} cityCode 市编码
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			地区编码	cityCode<br/>
	 * 			地区名称	cityName<br/>
	 * 			省份编码	provinceCode<br/>
	 * 			地区顺序	cityOrder<br/>
	 * 			有效标志	validFlag->YesNoFlg<br/>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/manage/cm/getCityByCityCd", method = RequestMethod.GET)
	public Result getCityByCityCd(String cityCode) {
		Result s = new Result();
		s.setData(provCityCtyCtrlService.getCityByCityCd(cityCode));
		return s;
	}

	/**
	 * @api {GET} /crm/manage/cm/getCountyByProvCity 根据省市查询区县列表
	 * @apiDescription 
	 * @apiName getCountyByProvCity
	 * @apiGroup Common
	 *
	 * @apiParam {String} provinceCode 省编码
	 * @apiParam {String} cityCode 市编码
	 * 
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			区县编码	countyCode<br/>
	 * 			区县名称	countyName<br/>
	 * 			省份编码	provinceCode<br/>
	 * 			地区编码	cityCode<br/>
	 * 			省市区全称	fullName<br/>
	 * 			区县顺序	countyOrder<br/>
	 * 			有效标志	validFlag->YesNoFlg<br/>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/manage/cm/getCountyByProvCity", method = RequestMethod.GET)
	public Result getCountyByProvCity(@RequestParam("provinceCode")String provinceCode, @RequestParam("cityCode")String cityCode) {
		Result s = new Result();
		s.setData(provCityCtyCtrlService.getCountyByProvCity(provinceCode, cityCode));
		return s;
	}

	/**
	 * @api {GET} /crm/manage/cm/getAddrName 获取详细地址(不包含街道)
	 * @apiDescription 
	 * @apiName getAddrName
	 * @apiGroup Common
	 *
	 * @apiParam {String} provinceCode 省编码
	 * @apiParam {String} cityCode 市编码
	 * @apiParam {String} countyCode 区县编码
	 * 
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			省市县地址名称
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/manage/cm/getAddrName", method = RequestMethod.GET)
	public Result getAddrName(@RequestParam("provinceCode")String provinceCode, @RequestParam("cityCode")String cityCode, @RequestParam("countyCode")String countyCode) {
		Result s = new Result();
		s.setData(provCityCtyCtrlService.getDetailAddr(provinceCode, cityCode, countyCode));
		return s;
	}

	/**
	 * @api {GET} /crm/manage/cm/getCountyByCountyCd 根据区县代码查询区县信息
	 * @apiDescription 
	 * @apiName getCountyByCountyCd
	 * @apiGroup Common
	 *
	 * @apiParam {String} countyCode 区县编码
	 * 
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			区县编码	countyCode<br/>
	 * 			区县名称	countyName<br/>
	 * 			省份编码	provinceCode<br/>
	 * 			地区编码	cityCode<br/>
	 * 			省市区全称	fullName<br/>
	 * 			区县顺序	countyOrder<br/>
	 * 			有效标志	validFlag->YesNoFlg<br/>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/manage/cm/getCountyByCountyCd", method = RequestMethod.GET)
	public Result getCountyByCountyCd(String countyCode) {
		Result s = new Result();
		s.setData(provCityCtyCtrlService.getCountyByCountyCd(countyCode));
		return s;
	}
}
