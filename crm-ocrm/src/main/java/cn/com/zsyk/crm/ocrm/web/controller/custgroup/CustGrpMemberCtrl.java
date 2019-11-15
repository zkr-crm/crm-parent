package cn.com.zsyk.crm.ocrm.web.controller.custgroup;

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
import cn.com.zsyk.crm.ocrm.entity.OcCustGrp;
import cn.com.zsyk.crm.ocrm.entity.OcCustGrpMember;
import cn.com.zsyk.crm.ocrm.service.custgroup.CustGrpMemberService;

@RestController
public class CustGrpMemberCtrl {

	@Autowired
	private CustGrpMemberService service;

	Log log = LogUtil.getLogger(CustGrpMemberCtrl.class);

	/**
	 * @api {GET} /crm/ocrm/CustGroupMng/getCustListByGroupId 根据客户群组ID查询群组内客户列表
	 * @apiDescription 
	 * @apiName getCustListByGroupId
	 * @apiGroup CustGroupMng
	 * 
	 * @apiParam {String} groupId 客户群组Id
	 *
	 * @apiSuccess {OcCustGrpMember} data 返回值对象
	 */
	@RequestMapping(path = "/crm/ocrm/CustGroupMng/getCustListByGroupId", method = RequestMethod.GET)
	public Result getCustListByGroupId(String groupId) {
		log.info(">>>>>>>>>>getCustListByGroupId start<<<<<<<<<<");
		log = LogUtil.getLogger(CustGrpMemberCtrl.class);
		Result res = new Result();

		PageBean bean = service.getCustListByGroupIdPaging(groupId);

		res.setData(bean);
		log.info(">>>>>>>>>>getCustListByGroupId end<<<<<<<<<<");
		return res;
	}

	/**
	 * @api {GET} /crm/ocrm/CustGroupMng/getCustListByEntity 根据输入条件查询群组内客户列表
	 * @apiDescription 
	 * @apiName getCustListByEntity
	 * @apiGroup CustGroupMng
	 * 
	 * @apiParam {String} groupId 客户群组Id
	 *
	 * @apiSuccess {OcCustGrpMember} data 返回值对象
	 */
	@RequestMapping(path = "/crm/ocrm/CustGroupMng/getCustListByEntity", method = RequestMethod.GET)
	public Result getCustListByEntity(OcCustGrpMember custGrpMember,String telephone,String certNo) {

		Result res = new Result();

		PageBean custGrpMemberList = service.getCustListByEntity(custGrpMember);

		res.setData(custGrpMemberList);
		return res;
	}

	/**
	 * @api {POST} /crm/ocrm/CustGroupMng/addGroupMember 添加客户群组成员
	 * @apiDescription 
	 * @apiName addGroupMember
	 * @apiGroup CustGroupMng
	 * 
	 * @apiParam {OcCustGrpMember} custGrpMember 客户群组成员对象
	 *
	 * @apiSuccess {int} data 新增记录条数
	 */
	@RequestMapping(path = "/crm/ocrm/CustGroupMng/addGroupMember", method = RequestMethod.POST)
	public Result addGroupMember(@RequestBody String jsonStr) {

		Result res = new Result();

		List<OcCustGrpMember> custGrpMemberList = JsonUtil.parseArray(jsonStr, OcCustGrpMember.class);
		int count = service.addGroupMember(custGrpMemberList);

		res.setData(count);
		return res;
	}

	/**
	 * @api {DELETE} /crm/ocrm/CustGroupMng/delMultiGroupMember 批量删除客户群组成员
	 * @apiDescription 
	 * @apiName delMultiGroupMember
	 * @apiGroup CustGroupMng
	 * 
	 * @apiParam {OcCustGrpMember} custGrpMember 客户群组成员对象
	 *
	 * @apiSuccess {int} data 新增记录条数
	 */
	@RequestMapping(path = "/crm/ocrm/CustGroupMng/delMultiGroupMember", method = RequestMethod.POST)
	public Result delMultiGroupMember(@RequestBody String json) {

		Result res = new Result();

		List<OcCustGrpMember> custGrpMemberList = JsonUtil.parseArray(json, OcCustGrpMember.class);

		int count = service.delMultiGroupMember(custGrpMemberList);

		res.setData(count);
		return res;
	}
}
