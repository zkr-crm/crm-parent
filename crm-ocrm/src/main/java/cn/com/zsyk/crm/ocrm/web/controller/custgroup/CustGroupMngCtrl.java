package cn.com.zsyk.crm.ocrm.web.controller.custgroup;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.zsyk.crm.common.dto.PageBean;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.util.JsonUtil;
import cn.com.zsyk.crm.common.util.LogUtil;
import cn.com.zsyk.crm.ocrm.bo.custgroup.CustGroupInfo;
import cn.com.zsyk.crm.ocrm.bo.custgroup.LineChartData;
import cn.com.zsyk.crm.ocrm.bo.custgroup.SendMsgInfo;
import cn.com.zsyk.crm.ocrm.entity.OcCustGrp;
import cn.com.zsyk.crm.ocrm.entity.OcDynamGrpRuleRela;
import cn.com.zsyk.crm.ocrm.service.custgroup.CustGroupMngService;

@RestController
public class CustGroupMngCtrl {

	@Autowired
	private CustGroupMngService service;

	Log log = LogUtil.getLogger(CustGroupMngCtrl.class);

	/**
	 * @api {GET} /crm/ocrm/CustGroupMng/getCustGrpByEntity 根据对象实体查询客户群组列表
	 * @apiDescription 
	 * @apiName getCustGrpByEntity
	 * @apiGroup CustGroupMng
	 * 
	 * @apiParam {OcCustGrp} 客户群组对象
	 *
	 * @apiSuccess {OcCustGrp} data 返回值对象
	 */
	@RequestMapping(path = "/crm/ocrm/CustGroupMng/getCustGrpByEntity", method = RequestMethod.GET)
	public Result getCustGrpByEntity(OcCustGrp custGrp) {
		log.info(">>>>>>>>>>getCustGrpByEntity start<<<<<<<<<<");
		log = LogUtil.getLogger(CustGroupMngCtrl.class);
		Result res = new Result();

		PageBean bean = service.getCustGrpByEntity(custGrp);

		res.setData(bean);
		log.info(">>>>>>>>>>getCustGrpByEntity end<<<<<<<<<<");
		return res;
	}

	/**
	 * @api {GET} /crm/ocrm/CustGroupMng/getCustGroupInfoByGroupId 根据客户群组ID查询群组详细信息
	 * @apiDescription 
	 * @apiName getCustGroupInfoByGroupId
	 * @apiGroup CustGroupMng
	 * 
	 * @apiParam {String} groupId 客户群组Id
	 *
	 * @apiSuccess {CustGroupInfo} data 返回值对象
	 */
	@RequestMapping(path = "/crm/ocrm/CustGroupMng/getCustGroupInfoByGroupId", method = RequestMethod.GET)
	public Result getCustGroupInfoByGroupId(String groupId) {
		log.info(">>>>>>>>>>getCustGroupInfoByGroupId start<<<<<<<<<<");
		log = LogUtil.getLogger(CustGroupMngCtrl.class);
		Result res = new Result();

		CustGroupInfo bean = service.getCustGroupInfoByGroupId(groupId);

		res.setData(bean);
		log.info(">>>>>>>>>>getCustGroupInfoByGroupId end<<<<<<<<<<");
		return res;
	}

	/**
	 * @api {POST} /crm/ocrm/CustGroupMng/addCustGroup 创建客户群组
	 * @apiDescription 
	 * @apiName addCustGroup
	 * @apiGroup CustGroupMng
	 * 
	 * @apiParam {String} groupId 客户群组Id
	 *
	 * @apiSuccess {CustGroupInfo} data 返回值对象
	 */
	@RequestMapping(path = "/crm/ocrm/CustGroupMng/addCustGroup", method = RequestMethod.POST)
	public Result addCustGroup(OcCustGrp custGrp) {
		Result res = new Result();

		String groupId = service.addCustGroup(custGrp);
		if (groupId != "") {
			res.setData(groupId);
		} else {
			res.setStatus("0");
		}
		return res;
	}

	/**
	 * @api {POST} /crm/ocrm/CustGroupMng/editCustGroup 修改客户群组
	 * @apiDescription 
	 * @apiName editCustGroup
	 * @apiGroup editCustGroup
	 * 
	 * @apiParam {OcCustGrp} custGrp 客户群组对象
	 *
	 * @apiSuccess {CustGroupInfo} data 返回值对象
	 */
	@RequestMapping(path = "/crm/ocrm/CustGroupMng/editCustGroup", method = RequestMethod.POST)
	public Result editCustGroup(OcCustGrp custGrp) {
		Result res = new Result();

		service.editCustGroup(custGrp);

		return res;
	}

	/**
	 * @api {POST} /crm/ocrm/CustGroupMng/delOneCustGroup 删除一个客户群组
	 * @apiDescription 
	 * @apiName delOneCustGroup
	 * @apiGroup editCustGroup
	 * 
	 * @apiParam {String} groupId 客户群组Id
	 *
	 * @apiSuccess {CustGroupInfo} data 返回值对象
	 */
	@RequestMapping(path = "/crm/ocrm/CustGroupMng/delOneCustGroup", method = RequestMethod.POST)
	public Result delOneCustGroup(String groupId) {
		Result res = new Result();

		service.delOneCustGroup(groupId);

		return res;
	}

	/**
	 * @api {POST} /crm/ocrm/CustGroupMng/delMultiCustGroup 批量删除客户群组
	 * @apiDescription 
	 * @apiName delMultiCustGroup
	 * @apiGroup editCustGroup
	 * 
	 * @apiParam {OcCustGrp} custGrp 客户群组对象
	 *
	 * @apiSuccess {CustGroupInfo} data 返回值对象
	 */
	@RequestMapping(path = "/crm/ocrm/CustGroupMng/delMultiCustGroup", method = RequestMethod.POST)
	public Result delMultiCustGroup(@RequestBody String custGrp) {
		Result res = new Result();

		int count = 0;

		List<OcCustGrp> custGrpList = JsonUtil.parseArray(custGrp, OcCustGrp.class);
		if (custGrpList != null && custGrpList.size() > 0) {
			count = service.delMultiCustGroup(custGrpList);
		}

		res.setData(count);
		return res;
	}

	/**
	 * @api {POST} /crm/ocrm/CustGroupMng/generateSnap 生成快照群组和任务列表
	 * @apiDescription 
	 * @apiName generateSnap
	 * @apiGroup CustGroupMng
	 * 
	 * @apiParam {CustGroupInfo} custGroupInfoStr 客户群组信息Json字符串
	 *
	 */
	@RequestMapping(path = "/crm/ocrm/CustGroupMng/generateSnap", method = RequestMethod.POST)
	public Result generateSnap(@RequestBody String custGroupInfoStr) {
		Result res = new Result();

		CustGroupInfo custGroupInfo = JsonUtil.parseObject(custGroupInfoStr, CustGroupInfo.class);
		service.generateSnap(custGroupInfo);

		return res;
	}

	/**
	 * @api {POST} /crm/ocrm/CustGroupMng/batchSendMsg 短信群发
	 * @apiDescription 
	 * @apiName batchSendMsg
	 * @apiGroup CustGroupMng
	 * 
	 * @apiParam {SendMsgInfo} sendMsgInfo 消息体相关信息对象Json字符串
	 *
	 */
	@RequestMapping(path = "/crm/ocrm/CustGroupMng/batchSendMsg", method = RequestMethod.POST)
	public Result batchSendMsg(@RequestBody String sendMsgInfoJson) {
		Result res = new Result();

		SendMsgInfo sendMsgInfo = JsonUtil.parseObject(sendMsgInfoJson, SendMsgInfo.class);
		service.batchSendMsg(sendMsgInfo);

		return res;
	}

	/**
	 * @api {POST} /crm/ocrm/CustGroupMng/assignOutBoundTask 分派外呼任务
	 * @apiDescription 
	 * @apiName assignOutBoundTask
	 * @apiGroup CustGroupMng
	 * 
	 * @apiParam {CustGroupInfo} custGroupInfoJson 消息体相关信息对象Json字符串
	 *
	 */
	@RequestMapping(path = "/crm/ocrm/CustGroupMng/assignOutBoundTask", method = RequestMethod.POST)
	public Result assignOutBoundTask(@RequestBody String custGroupInfoJson) {
		Result res = new Result();

		CustGroupInfo custGroupInfo = JsonUtil.parseObject(custGroupInfoJson, CustGroupInfo.class);
		service.assignOutBoundTask(custGroupInfo);

		return res;
	}

	/**
	 * @api {GET} /crm/ocrm/CustGroupMng/queryGrpRuleRela 查询动态群组策略关联关系
	 * @apiDescription 
	 * @apiName queryGrpRuleRela
	 * @apiGroup CustGroupMng
	 * 
	 * @apiParam {OcDynamGrpRuleRela} dynamGrpRuleRela 动态群组策略关联关系表对象
	 *
	 */
	@RequestMapping(path = "/crm/ocrm/CustGroupMng/queryGrpRuleRela", method = RequestMethod.GET)
	public Result queryGrpRuleRela(OcDynamGrpRuleRela dynamGrpRuleRela) {
		Result res = new Result();

		List<OcDynamGrpRuleRela> grpRuleRelaList = service.queryGrpRuleRela(dynamGrpRuleRela);
		res.setData(grpRuleRelaList);
		return res;
	}

	/**
	 * @api {POST} /crm/ocrm/CustGroupMng/modifGrpRuleRela 更新动态群组策略关联关系
	 * @apiDescription 
	 * @apiName modifGrpRuleRela
	 * @apiGroup CustGroupMng
	 * 
	 * @apiParam {OcDynamGrpRuleRela} dynamGrpRuleRela 动态群组策略关联关系表对象
	 *
	 */
	@RequestMapping(path = "/crm/ocrm/CustGroupMng/modifGrpRuleRela", method = RequestMethod.POST)
	public Result modifGrpRuleRela(OcDynamGrpRuleRela dynamGrpRuleRela) {
		Result res = new Result();

		service.modifGrpRuleRela(dynamGrpRuleRela);

		return res;
	}

	/**
	 * @throws ParseException
	 * @api {GET} /crm/ocrm/CustGroupMng/grpMemberTrendAnalysis 群组成员数量趋势分析
	 * @apiDescription 
	 * @apiName grpMemberTrendAnalysis
	 * @apiGroup CustGroupMng
	 * 
	 * @apiParam {String} groupId 客户群组ID
	 *
	 * @apiSuccess {LineChartData} data 线性chart基础数据列表对象
	 */
	@RequestMapping(path = "/crm/ocrm/CustGroupMng/grpMemberTrendAnalysis", method = RequestMethod.GET)
	public Result grpMemberTrendAnalysis(String groupId) throws ParseException {

		Result res = new Result();

		// 按月统计
		// List<LineChartData> lineChartData =
		// service.grpMemberTrendAnalysisByMonth(groupId);

		// 按动态客群生成的次数统计
		List<LineChartData> lineChartData = service.grpMemberTrendAnalysisByDays(groupId);

		res.setData(lineChartData);
		return res;
	}
}
