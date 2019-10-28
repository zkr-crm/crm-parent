package cn.com.zsyk.crm.ocrm.web.controller.custgroup;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.zsyk.crm.common.dto.PageBean;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.util.LogUtil;
import cn.com.zsyk.crm.ocrm.entity.OcDynamSnapshot;
import cn.com.zsyk.crm.ocrm.entity.OcGroupOper;
import cn.com.zsyk.crm.ocrm.service.custgroup.DynamSnapshotService;

/**
 * 运营任务操作相关接口
 * 
 * @author
 *
 */
@RestController
public class DynamSnapshotCtrl {

	@Autowired
	private DynamSnapshotService service;

	Log log = LogUtil.getLogger(DynamSnapshotCtrl.class);

	/**
	 * @api {GET} /crm/ocrm/CustGroupMng/selectSnapByEntity 按条件查询客户群快照列表（分页）
	 * @apiDescription 
	 * @apiName selectSnapByEntity
	 * @apiGroup CustGroupMng
	 * 
	 * @apiParam {OcDynamSnapshot} dynamSnapshot 快照信息对象
	 *
	 * @apiSuccess {OcDynamSnapshot} data 快照信息列表
	 */
	@RequestMapping(path = "/crm/ocrm/CustGroupMng/selectSnapByEntity", method = RequestMethod.GET)
	public Result selectSnapByEntity(OcDynamSnapshot dynamSnapshot) {

		Result res = new Result();

		PageBean bean = service.selectSnapByEntity(dynamSnapshot);

		res.setData(bean);
		return res;
	}

	/**
	 * @api {GET} /crm/ocrm/CustGroupMng/selectSnapOnFuzzy 模糊查询快照列表（分页）
	 * @apiDescription 
	 * @apiName selectSnapOnFuzzy
	 * @apiGroup CustGroupMng
	 * 
	 * @apiParam {String} groupId 群组ID
	 * @apiParam {String} queryCondition 快照信息查询条件字符串
	 *
	 * @apiSuccess {OcDynamSnapshot} data 快照信息列表
	 */
	@RequestMapping(path = "/crm/ocrm/CustGroupMng/selectSnapOnFuzzy", method = RequestMethod.GET)
	public Result selectSnapOnFuzzy(String groupId,String queryCondition) {

		Result res = new Result();

		PageBean bean = service.selectSnapOnFuzzy(groupId,queryCondition);

		res.setData(bean);
		return res;
	}

	/**
	 * @api {GET} /crm/ocrm/CustGroupMng/addSnap 新增快照
	 * @apiDescription 
	 * @apiName addSnap
	 * @apiGroup CustGroupMng
	 * 
	 * @apiParam {OcDynamSnapshot} dynamSnapshot 快照信息对象
	 *
	 * @apiSuccess {String} snapshotId 生成的快照ID
	 */
	@RequestMapping(path = "/crm/ocrm/CustGroupMng/addSnap", method = RequestMethod.GET)
	public Result addSnap(OcDynamSnapshot dynamSnapshot) {

		Result res = new Result();

		String snapshotId = service.addSnap(dynamSnapshot);

		res.setData(snapshotId);
		return res;
	}
}
