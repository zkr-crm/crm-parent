package cn.com.zsyk.crm.ocrm.web.controller.custgroup.scheduler;

import java.util.List;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.util.LogUtil;
import cn.com.zsyk.crm.ocrm.entity.OcDynamGrpRuleRela;
import cn.com.zsyk.crm.ocrm.mapper.OcDynamGrpRuleRelaMapper;
import cn.com.zsyk.crm.ocrm.service.custgroup.CustGroupMngService;

@RestController
public class DynamicGrpGenerateCtrl {

	/* 动态群组策略关联表 */
	@Autowired
	private OcDynamGrpRuleRelaMapper dynamGrpRuleRelaMapper;

	/* 客户群组管理服务 */
	@Autowired
	private CustGroupMngService custGroupMngService;

	Log log = LogUtil.getLogger(DynamicGrpGenerateCtrl.class);

	/**
	 * @api {POST} /crm/ocrm/CustGroupMng/generateDynamicGrp 批量生成（更新）动态群组成员列表
	 * @apiDescription 
	 * @apiName generateDynamicGrp
	 * @apiGroup CustGroupMng
	 * 
	 * @apiSuccess {Void} data 返回值对象
	 */
	@RequestMapping(path = "/crm/ocrm/CustGroupMng/generateDynamicGrp", method = RequestMethod.POST)
	public Result generateDynamicGrp() {

		log = LogUtil.getLogger(DynamicGrpGenerateCtrl.class);
		Result res = new Result();

		// 获取所有动态群组及群组对应的规则ID
		List<OcDynamGrpRuleRela> dynamGrpRuleRelaList = dynamGrpRuleRelaMapper.selectAll();

		// 根据群组ID和规则ID更新动态群组成员表
		for (OcDynamGrpRuleRela item : dynamGrpRuleRelaList) {
			custGroupMngService.modifGrpRuleRela(item);
		}

		return res;
	}
}
