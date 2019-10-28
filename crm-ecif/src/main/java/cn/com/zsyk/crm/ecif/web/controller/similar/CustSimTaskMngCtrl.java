
package cn.com.zsyk.crm.ecif.web.controller.similar;

import cn.com.zsyk.crm.common.dto.PageBean;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.util.JsonUtil;
import cn.com.zsyk.crm.ecif.bo.cust.SimCustTask;
import cn.com.zsyk.crm.ecif.bo.cust.SimTaskList;
import cn.com.zsyk.crm.ecif.entity.EcSimTask;
import cn.com.zsyk.crm.ecif.entity.EcSimTaskDetil;
import cn.com.zsyk.crm.ecif.service.similar.CustSimTaskMngService;
import cn.com.zsyk.crm.generator.EnumType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author
 *
 */
@RestController
public class CustSimTaskMngCtrl {

	@Autowired
	private CustSimTaskMngService custSimTaskMngService;

	/**
	 * @api {POST} /crm/ecif/similar/getTaskByEntity 按条件查询所有相似客户任务
	 * @apiDescription 
	 * @apiName getTaskByEntity
	 * @apiGroup similar
	 *
	 * @apiParam {EcSimTask} smTask 相似任务对象 TaskState字段为以逗号隔开的字符串
	 *
	 * @apiSuccess {List} EcSimTask 相似任务对象集和
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ecif/similar/getTaskByEntity", method = RequestMethod.POST)
	public Result getTaskByEntity(@RequestBody String json) {
		Result res = new Result();

		SimTaskList smTaskJson = JsonUtil.parseObject(json, SimTaskList.class);
		if (smTaskJson.getTaskState() != null) {
			String[] arr = smTaskJson.getTaskState().split(",");
			List<String> test = new ArrayList();
			for (String item : arr) {
				test.add(item);
			}
			smTaskJson.setTaskStates(test);
		}

		PageBean bean = custSimTaskMngService.selectAllTaskByEntity(smTaskJson);
		res.setData(bean);
		return res;
	}

	/**
	 * @api {GET} /crm/ecif/similar/getMyTaskByEntity 按条件查询所有的未分配审批人的待审批合并任务
	 * @apiDescription 
	 * @apiName getMyTaskByEntity
	 * @apiGroup similar
	 *
	 * @apiParam {EcSimTask} smTask 相似任务对象
	 *
	 * @apiSuccess {List} EcSimTask 相似任务对象集和
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ecif/similar/getMyTaskByEntity", method = RequestMethod.GET)
	public Result getMyTaskByEntity(EcSimTask smTask) {
		Result res = new Result();

		// TODO ： 该列表需要权限控制

		// 获取当前会话的用户ID
		// smTask.setDealUser(ContextContainer.getContext().getUserId());

		PageBean bean = custSimTaskMngService.getMyTaskByEmployeeId(smTask);
		res.setData(bean);
		return res;
	}

	/**
	 * @api {GET} /crm/ecif/similar/getMySplitTaskByEntity 按条件查所有的未分配审批人的待审批拆分任务
	 * @apiDescription 
	 * @apiName getMySplitTaskByEntity
	 * @apiGroup similar
	 *
	 * @apiParam {EcSimTask} smTask 相似任务对象
	 *
	 * @apiSuccess {List} EcSimTask 相似任务对象集和
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/similar/getMySplitTaskByEntity", method = RequestMethod.GET)
	public Result getMySplitTaskByEntity(String employeeId,String taskState) {
		Result res = new Result();

		// TODO ： 该列表需要权限控制

		PageBean bean = custSimTaskMngService.getMySplitTaskByEntity(employeeId,taskState);
		res.setData(bean);
		return res;
	}

	/**
	 * @api {POST} /crm/ecif/similar/selectAllNoApproveUser 按条件查询所有未分配审批人的合并任务
	 * @apiDescription 
	 * @apiName selectAllNoApproveUser
	 * @apiGroup similar
	 *
	 * @apiParam {EcSimTask} smTask 相似任务对象 TaskState字段为以逗号隔开的字符串
	 *
	 * @apiSuccess {List} EcSimTask 相似任务对象集和
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ecif/similar/selectAllNoApproveUser", method = RequestMethod.POST)
	public Result selectAllNoApproveUser(@RequestBody String json) {
		Result res = new Result();

		// TODO ： 该列表需要权限控制

		EcSimTask smTaskJson = JsonUtil.parseObject(json, EcSimTask.class);

		if (smTaskJson.getTaskState() != null) {
			String[] arr = smTaskJson.getTaskState().split(",");
			List<String> test = new ArrayList();
			for (String item : arr) {
				test.add(item);
			}
			smTaskJson.setTaskStates(test);
		}

		
		PageBean bean = custSimTaskMngService.selectAllNoApproveUser(smTaskJson);
		res.setData(bean);
		return res;
	}

	/**
	 * @api {GET} /crm/ecif/similar/selectAllNoApproveSplitTask 按条件查询所有未分配审批人的拆分任务
	 * @apiDescription 
	 * @apiName selectAllNoApproveSplitTask
	 * @apiGroup similar
	 *
	 * @apiParam {EcSimTask} smTask 相似任务对象
	 *
	 * @apiSuccess {List} EcSimTask 相似任务对象集和
	 */
	@RequestMapping(path = "/crm/ecif/similar/selectAllNoApproveSplitTask", method = RequestMethod.GET)
	public Result selectAllNoApproveSplitTask(EcSimTask smTask) {
		Result res = new Result();

		// TODO ： 该列表需要权限控制

		PageBean bean = custSimTaskMngService.selectAllNoApproveSplitTask(smTask);
		res.setData(bean);
		return res;
	}

	/**
	 * @throws Exception
	 * @api {GET} /crm/ecif/similar/getTaskByTaskId 根据任务编号获取任务详情
	 * @apiDescription 
	 * @apiName getTaskByTaskId
	 * @apiGroup similar
	 *
	 * @apiParam {String} taskId 相似任务对象
	 *
	 * @apiSuccess {object} EcSimTaskDetil 相似任务详细信息
	 */
	@RequestMapping(path = "/crm/ecif/similar/getTaskByTaskId", method = RequestMethod.GET)
	public Result getTaskByTaskId(String taskId) throws Exception {
		Result res = new Result();

		EcSimTaskDetil bean = custSimTaskMngService.getTaskDetilByTaskId(taskId);
		res.setData(bean);
		return res;
	}
	/**
	 * @throws Exception
	 * @api {GET} /crm/ecif/similar/getNewCustNoByOldCustNo 获取合并后得最新客户
	 * @apiDescription
	 * @apiName getNewCustNoByOldCustNo
	 * @apiGroup similar
	 *
	 * @apiParam {String} custNo 客户编码
	 *
	 * @apiSuccess {object}
	 */
	@RequestMapping(path = "/crm/ecif/similar/getNewCustNoByOldCustNo", method = RequestMethod.GET)
	public Result getNewCustNoByOldCustNo(String custNo) throws Exception {
		Result res = new Result();

		String newCustNo = custSimTaskMngService.getNewCustNoByOldCustNo(custNo);
		res.setData(newCustNo);
		return res;
	}

	/**
	 * @throws Exception
	 * @api {POST} /crm/ecif/similar/doCustMergeApply 提交相似客户合并申请
	 * @apiDescription 
	 * @apiName doCustMergeApply
	 * @apiGroup similar
	 *
	 * @apiParam {SimCustTask} mergeJson SimCustTask对象对应的json串
	 *
	 */
	@RequestMapping(path = "/crm/ecif/similar/doCustMergeApply", method = RequestMethod.POST)
	public Result doCustMergeApply(@RequestBody String mergeJson) throws Exception {

		Result res = new Result();

		SimCustTask applyInfo = JsonUtil.parseObject(mergeJson, SimCustTask.class);

		custSimTaskMngService.applyMergeSimCust(applyInfo);

		return res;
	}

	/**
	 * @throws Exception
	 * @api {POST} /crm/ecif/similar/doCloseTaskApply 提交相似客户关闭申请
	 * @apiDescription 
	 * @apiName doCloseTaskApply
	 * @apiGroup similar
	 *
	 * @apiParam {SimCustTask} mergeJson SimCustTask对象对应的json串
	 *
	 */
	@RequestMapping(path = "/crm/ecif/similar/doCloseTaskApply", method = RequestMethod.POST)
	public Result doCloseTaskApply(@RequestBody String mergeJson) throws Exception {

		Result res = new Result();

		SimCustTask applyInfo = JsonUtil.parseObject(mergeJson, SimCustTask.class);

		custSimTaskMngService.doCloseTaskApply(applyInfo);

		return res;
	}

	/**
	 * @throws Exception
	 * @api {POST} /crm/ecif/similar/applyMergePass 客户合并/关闭申请审批通过
	 * @apiDescription 
	 * @apiName applyMergePass
	 * @apiGroup similar
	 *
	 * @apiParam {SimCustTask} mergeJson SimCustTask对象对应的json串
	 *
	 */
	@RequestMapping(path = "/crm/ecif/similar/applyMergePass", method = RequestMethod.POST)
	public Result applyMergePass(@RequestBody String mergeJson) throws Exception {

		Result res = new Result();

		SimCustTask applyInfo = JsonUtil.parseObject(mergeJson, SimCustTask.class);

		String newCustNo = null;
		// 合并
		if (EnumType.TaskApplyTyp.merge_apply.value.equals(applyInfo.getCustMergeApply().getApplyTyp())) {
			newCustNo = custSimTaskMngService.applyMergePass(applyInfo);
		} else {
			// 关闭
			custSimTaskMngService.applyCloseTask(applyInfo);
		}
		res.setData(newCustNo);
		return res;
	}

	/**
	 * @throws Exception
	 * @api {POST} /crm/ecif/similar/applyMergeSendBack 客户合并申请审批退回
	 * @apiDescription 
	 * @apiName applyMergeSendBack
	 * @apiGroup similar
	 *
	 * @apiParam {SimCustTask} mergeJson SimCustTask对象对应的json串
	 *
	 */
	@RequestMapping(path = "/crm/ecif/similar/applyMergeSendBack", method = RequestMethod.POST)
	public Result applyMergeSendBack(@RequestBody String mergeJson) throws Exception {

		Result res = new Result();

		SimCustTask applyInfo = JsonUtil.parseObject(mergeJson, SimCustTask.class);

		custSimTaskMngService.applyMergeSendBack(applyInfo);

		return res;
	}

	/**
	 * @throws Exception
	 * @api {POST} /crm/ecif/similar/applyCloseTask 相似客户任务关闭申请
	 * @apiDescription 
	 * @apiName applyCloseTask
	 * @apiGroup similar
	 *
	 * @apiParam {SimCustTask} mergeJson SimCustTask对象对应的json串
	 *
	 */
	@RequestMapping(path = "/crm/ecif/similar/applyCloseTask", method = RequestMethod.POST)
	public Result applyCloseTask(@RequestBody String mergeJson) throws Exception {

		Result res = new Result();
		SimCustTask applyInfo = JsonUtil.parseObject(mergeJson, SimCustTask.class);
		custSimTaskMngService.applyCloseTask(applyInfo);

		return res;
	}

	/**
	 * @throws Exception
	 * @api {POST} /crm/ecif/similar/distributeTask 相似客户任务分配
	 * @apiDescription 
	 * @apiName distributeTask
	 * @apiGroup similar
	 *
	 * @apiParam {SimCustTask} mergeJson SimCustTask对象对应的json串
	 *
	 */
	@RequestMapping(path = "/crm/ecif/similar/distributeTask", method = RequestMethod.POST)
	public Result distributeTask(@RequestBody String params) throws Exception {

		Result res = new Result();
		Map map = JsonUtil.parseObject(params, Map.class);
		custSimTaskMngService.distributeTask(map);

		return res;
	}

	/**
	 * @throws Exception
	 * @api {POST} /crm/ecif/similar/splitApply 相似客户任务拆分申请
	 * @apiDescription 
	 * @apiName splitApply
	 * @apiGroup similar
	 *
	 * @apiParam {SimCustTask} splitJson SimCustTask对象对应的json串
	 *
	 */
	@RequestMapping(path = "/crm/ecif/similar/splitApply", method = RequestMethod.POST)
	public Result splitApply(@RequestBody String splitJson) throws Exception {

		Result res = new Result();

		SimCustTask applyInfo = JsonUtil.parseObject(splitJson, SimCustTask.class);

		custSimTaskMngService.splitApply(applyInfo);

		return res;
	}

	/**
	 * @throws Exception
	 * @api {POST} /crm/ecif/similar/splitApplyPass 相似客户任务拆分申请审批通过
	 * @apiDescription 
	 * @apiName splitApplyPass
	 * @apiGroup similar
	 *
	 * @apiParam {SimCustTask} splitJson SimCustTask对象对应的json串
	 *
	 */
	@RequestMapping(path = "/crm/ecif/similar/splitApplyPass", method = RequestMethod.POST)
	public Result splitApplyPass(@RequestBody String splitJson) throws Exception {

		Result res = new Result();

		SimCustTask applyInfo = JsonUtil.parseObject(splitJson, SimCustTask.class);

		custSimTaskMngService.splitApplyPass(applyInfo);

		return res;
	}

	/**
	 * @throws Exception
	 * @api {POST} /crm/ecif/similar/splitApplyUnPass 相似客户任务拆分申请审批不通过
	 * @apiDescription 
	 * @apiName splitApplyUnPass
	 * @apiGroup similar
	 *
	 * @apiParam {SimCustTask} splitJson SimCustTask对象对应的json串
	 *
	 */
	@RequestMapping(path = "/crm/ecif/similar/splitApplyUnPass", method = RequestMethod.POST)
	public Result splitApplyUnPass(@RequestBody String splitJson) throws Exception {

		Result res = new Result();

		SimCustTask applyInfo = JsonUtil.parseObject(splitJson, SimCustTask.class);

		custSimTaskMngService.splitApplyUnPass(applyInfo);

		return res;
	}

	/**
	 * @throws Exception
	 * @api {POST} /crm/ecif/similar/receiveMergeTask 客户合并任务审批申请
	 * @apiDescription 
	 * @apiName receiveMergeTask
	 * @apiGroup similar
	 *
	 * @apiParam {String} taskId 相似任务编号
	 *
	 */
	@RequestMapping(path = "/crm/ecif/similar/receiveMergeTask", method = RequestMethod.POST)
	public Result receiveMergeTask(String taskId, String taskState) throws Exception {

		Result res = new Result();

		custSimTaskMngService.receiveMergeTask(taskId, taskState);

		return res;
	}

}
