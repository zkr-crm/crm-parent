
package cn.com.zsyk.crm.query.web.controller.homePage;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.util.GetDataAuthUtils;
import cn.com.zsyk.crm.common.util.JsonUtil;
import cn.com.zsyk.crm.query.entity.ECCustper;
import cn.com.zsyk.crm.query.service.homePage.HomePageService;
//注册contrller  将类注册为处理器  可以用来处理请求
@RestController
@RequestMapping("/crm/query")
public class HomePageCtrl{
	  //service注入实体
  	@Autowired
  	private HomePageService homePageService;

  	@Autowired
  	private GetDataAuthUtils getDataAuthUtils;
  	/**
  	 * @api {GET} /crm/ecif/homeCenterCtrl/getDataStatisticsByEntity 按条件查询所有的客户、保单、保费、真实性客户
  	 */
  	@SuppressWarnings({ "rawtypes", "unchecked" })
  	@RequestMapping(path = "/homePageCtrl/getDataStatisticsByEntity", method = RequestMethod.POST)
  	public Result getDataStatisticsByEntity(String dataType) {
  		Result res = new Result();
  		Map map = homePageService.getDataStatisticsByEntity(getDataAuthUtils.getRoleDateAuth(true),dataType);
  		res.setData(map);
  		return res;
  	}
  	
  	/**
  	 * @api {GET} /crm/ecif/homeCenterCtrl/getDataStatisticsPieByEntity 按条件查询忠诚度、客户占比
  	 */
  	@SuppressWarnings({ "rawtypes", "unchecked" })
  	@RequestMapping(path = "/homePageCtrl/getDataStatisticsPieByEntity", method = RequestMethod.POST)
  	public Result getDataStatisticsPieByEntity(String json) {
  		Result res = new Result();
  		Map map = homePageService.getDataStatisticsPieByEntity(getDataAuthUtils.getRoleDateAuth(true));
  		res.setData(map);
  		return res;
  	}
	/**
	 * @api {GET} /crm/ecif/homeCenterCtrl/getDataBarByAges 客户年龄分布查询
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/homePageCtrl/getDataBarByAges", method = RequestMethod.POST)
	public Result getDataBarByAges(@RequestBody String searchjson) {
		Result res = new Result();
		ECCustper eCCustper = JsonUtil.parseObject(searchjson, ECCustper.class);

		res.setData(homePageService.getDataBarByAges(eCCustper));
		return res;
	}

  	/**
  	 * @api {GET} /crm/ecif/homeCenterCtrl/getDataCustEventsScale 客户占比
  	 */
  	@SuppressWarnings({ "rawtypes", "unchecked" })
  	@RequestMapping(path = "/homePageCtrl/getDataCustEventsScale", method = RequestMethod.POST)
  	public Result getDataCustEventsScale(@RequestBody String searchjson) {
  		Result res = new Result();
		ECCustper eCCustper = JsonUtil.parseObject(searchjson, ECCustper.class);

  		Map map = homePageService.getDataCustEventsScale(eCCustper);
  		res.setData(map);
  		return res;
  	}
}




