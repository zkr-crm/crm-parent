package cn.com.zsyk.crm.ocrm.web.controller.custgroup;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.zsyk.crm.common.dto.PageBean;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.util.LogUtil;
import cn.com.zsyk.crm.ocrm.entity.OcGroupOper;
import cn.com.zsyk.crm.ocrm.service.custgroup.GroupOperService;

/**
 * 运营任务操作相关接口
 * 
 * @author
 *
 */
@RestController
public class GroupOperCtrl {

	@Autowired
	private GroupOperService service;

	Log log = LogUtil.getLogger(GroupOperCtrl.class);

	/**
	 * @api {GET} /crm/ocrm/CustGroupMng/selectByEntity 按条件查询运营任务列表（分页）
	 * @apiDescription 
	 * @apiName selectByEntity
	 * @apiGroup CustGroupMng
	 * 
	 * @apiParam {OcGroupOper} groupOper 运营任务对象
	 *
	 * @apiSuccess {OcCustGrpMember} data 返回值对象
	 */
	@RequestMapping(path = "/crm/ocrm/CustGroupMng/selectByEntity", method = RequestMethod.GET)
	public Result selectByEntity(OcGroupOper groupOper) {

		Result res = new Result();

		PageBean bean = service.selectByEntity(groupOper);

		res.setData(bean);
		return res;
	}

	/**
	 * @api {GET} /crm/ocrm/CustGroupMng/selectOnFuzzy 模糊查询运营任务列表（分页）
	 * @apiDescription 
	 * @apiName selectOnFuzzy
	 * @apiGroup CustGroupMng
	 * 
	 * @apiParam {String} queryCondition 查询条件字符串
	 *
	 * @apiSuccess {OcCustGrpMember} data 返回值对象
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ocrm/CustGroupMng/selectOnFuzzy", method = RequestMethod.GET)
	public Result selectOnFuzzy(String groupId, String queryCondition) {

		Result res = new Result();

		PageBean bean = service.selectOnFuzzy(groupId, queryCondition);

		res.setData(bean);
		return res;
	}

	/**
	 * @api {GET} /crm/ocrm/CustGroupMng/selectTaskByGrpOperId
	 *      根据运营任务ID获取任务成员详细信息（分页）
	 * @apiDescription 
	 * @apiName selectTaskByGrpOperId
	 * @apiGroup CustGroupMng
	 * 
	 * @apiParam {String} groupOperId 运营任务ID
	 *
	 * @apiSuccess {OcGroupOperCust} data 群组运营任务成员表对象
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ocrm/CustGroupMng/selectTaskByGrpOperId", method = RequestMethod.GET)
	public Result selectTaskByGrpOperId(String groupOperId, String custName, String custAgent) {

		Result res = new Result();

		PageBean bean = service.selectTaskByGrpOperId(groupOperId, custName, custAgent);

		res.setData(bean);
		return res;
	}
}
