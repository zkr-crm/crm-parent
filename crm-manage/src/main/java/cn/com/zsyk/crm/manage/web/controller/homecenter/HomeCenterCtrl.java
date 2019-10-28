package cn.com.zsyk.crm.manage.web.controller.homecenter;

import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.util.GetDataAuthUtils;
import cn.com.zsyk.crm.manage.service.homecenter.HomeCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//注册contrller  将类注册为处理器  可以用来处理请求
@RestController
public class HomeCenterCtrl {

	// service注入实体
	@Autowired
	private HomeCenterService homeCenterService;
	@Autowired
	private GetDataAuthUtils getDataAuthUtils;

	/**
	 * @api {POST} /crm/manage/homeCenterCtrl/getMyEventByEntity 按条件查询所有的事件列表客户列表
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/manage/homeCenterCtrl/getMyEventByEntity", method = RequestMethod.POST)
	public Result getMyEventByEntity() {
		Result res = new Result();
		List bean = homeCenterService.selectAllEventByUser();
		res.setData(bean);
		return res;
	}

	/**
	 * @api {POST} /crm/manage/homeCenterCtrl/getMyRenewalByEntity 按条件查询所有续保列表客户列表
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/manage/homeCenterCtrl/getMyRenewalByEntity", method = RequestMethod.POST)
	public Result getMyRenewalByEntity() {
		Result res = new Result();
		List bean = homeCenterService.getMyRenewalByEntity(getDataAuthUtils.getRoleDateAuth(true));
		res.setData(bean);
		return res;
	}

}
