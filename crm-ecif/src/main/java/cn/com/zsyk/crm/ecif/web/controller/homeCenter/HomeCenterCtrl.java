
package cn.com.zsyk.crm.ecif.web.controller.homeCenter;

import java.util.ArrayList;
import java.util.List;

import cn.com.zsyk.crm.common.util.JsonUtil;
import cn.com.zsyk.crm.ecif.entity.EcSimTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.ecif.service.homeCenter.HomeCenterService;
//注册contrller  将类注册为处理器  可以用来处理请求
@RestController
public class HomeCenterCtrl{
	//service注入实体
	@Autowired
	private HomeCenterService homeCenterService;

	/**
	 * @api {POST} /crm/ecif/homeCenterCtrl/getMyTaskByEntity 按条件查询所有的未分配审批人的待审批合并任务
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ecif/homeCenterCtrl/getMyTaskByEntity", method = RequestMethod.POST)
	public Result getMyTaskByEntity(@RequestBody String json) {

		Result res = new Result();
		EcSimTask smTaskJson = JsonUtil.parseObject(json, EcSimTask.class);

		if (smTaskJson.getTaskState() != null) {
			String[] arr = smTaskJson.getTaskState().split(",");
			List<String> test = new ArrayList();
			for (String item : arr) {
				test.add(item);
			}
			smTaskJson.setTaskStates(test);
		}
		res.setData(homeCenterService.selectAllNoApprove(smTaskJson));
		return res;
	}
	
	/**
	 * @api {POST} /crm/ecif/homeCenterCtrl/getMyCusByEntity 按条件查询所有的客户地址不详的客户列表
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ecif/homeCenterCtrl/getMyCusByEntity", method = RequestMethod.POST)
	public Result getMyCusByEntity() {
		Result res = new Result();
		List bean = homeCenterService.selectAllAddressInsufficient();
		res.setData(bean);
		return res;
	}
	
}




