package cn.com.zsyk.crm.ocrm.web.controller.custopp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cn.com.zsyk.crm.common.annotation.SysOprtLog;
import cn.com.zsyk.crm.common.annotation.SysOprtLog.Module;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.ocrm.bo.custopp.StageInfo;
import cn.com.zsyk.crm.ocrm.entity.OcBusiOpp;
import cn.com.zsyk.crm.ocrm.service.custopp.BusiOppService;

@RestController
public class BusiOppCtrl {
	@Autowired
	BusiOppService busiOppService;

	/**
	 * @api {GET} /crm/ocrm/busiOpp/busiOppList 查询商机列表
	 * @apiName getBusiOppList
	 * @apiGroup BUSIOPP
	 *
	 * @apiParam {OcBusiOpp} ocBusiOpp 商机信息bean
	 * 
	 * @apiSuccess {Result} data 返回值对象
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ocrm/busiOpp/busiOppList", method = RequestMethod.GET)
	public Result getBusiOppList(OcBusiOpp ocBusiOpp) {
		Result res = new Result();
		res.setData(busiOppService.getBusiOppList(ocBusiOpp));
		return res;
	}

	/**
	 * @api {GET} /crm/ocrm/busiOpp/followUpBusiOppQtyList 统计员工跟进商机数量
	 * @apiName getFollowUpBusiOppQtyList
	 * @apiGroup BUSIOPP
	 *
	 * @apiSuccess {Result} data 返回值对象
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ocrm/busiOpp/followUpBusiOppQtyList", method = RequestMethod.GET)
	public Result getFollowUpBusiOppQtyList() {
		Result res = new Result();
		res.setData(busiOppService.getFollowUpBusiOppQtyList());
		return res;
	}

	/**
	 * @api {GET} /crm/ocrm/busiOpp/busiOppOne 查询商机(单条)
	 * @apiDescription 
	 * @apiName getBusiOppOne
	 * @apiGroup BUSIOPP
	 * 
	 * @apiParam {String} busiOppNo 商机编号
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ocrm/busiOpp/busiOppOne", method = RequestMethod.GET)
	public Result getBusiOppOne(String busiOppNo) {
		Result res = new Result();
		res.setData(busiOppService.getBusiOppOne(busiOppNo));
		return res;
	}

	/**
	 * @api {PUT} /crm/ocrm/busiOpp/addBusiOpp 新增商机
	 * @apiDescription 
	 * @apiName addBusiOpp
	 * @apiGroup BUSIOPP
	 * 
	 * @apiParam {OcBusiOpp} ocBusiOpp 商机信息Bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ocrm/busiOpp/addBusiOpp", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.BUSIOPP, bizDesc="新增商机")
	public Result addBusiOpp(OcBusiOpp ocBusiOpp) {
		Result res = new Result();
		try {
			busiOppService.addBusiOpp(ocBusiOpp);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("新增商机信息失败");
		}
		return res;
	}

	/**
	 * @api {PUT} /crm/ocrm/busiOpp/uptBusiOpp 修改商机
	 * @apiDescription 
	 * @apiName uptBusiOpp
	 * @apiGroup BUSIOPP
	 * 
	 * @apiParam {OcBusiOpp} ocBusiOpp 商机信息Bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ocrm/busiOpp/uptBusiOpp", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.BUSIOPP, bizDesc="修改商机")
	public Result uptBusiOpp(OcBusiOpp ocBusiOpp) {
		Result res = new Result();
		try {
			busiOppService.uptBusiOpp(ocBusiOpp);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("修改商机信息失败");
		}
		return res;
	}

	/**
	 * @api {PUT} /crm/ocrm/busiOpp/delBusiOpp 删除商机
	 * @apiDescription 
	 * @apiName delBusiOpp
	 * @apiGroup BUSIOPP
	 * 
	 * @apiParam {OcBusiOpp} ocBusiOpp 商机信息Bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ocrm/busiOpp/delBusiOpp", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.BUSIOPP, bizDesc="删除商机")
	public Result delBusiOpp(OcBusiOpp ocBusiOpp) {
		Result res = new Result();
		try {
			busiOppService.delBusiOpp(ocBusiOpp);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("删除商机信息失败");
		}
		return res;
	}

	/**
	 * @api {PUT} /crm/ocrm/busiOpp/allotBusiOpp 分配商机
	 * @apiDescription 
	 * @apiName allotBusiOpp
	 * @apiGroup BUSIOPP
	 * 
	 * @apiParam {String} custAgent 分配员工Id
	 * @apiParam {List} busiOppNoList 商机编号list
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ocrm/busiOpp/allotBusiOpp", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.BUSIOPP, bizDesc="分配商机")
	public Result allotBusiOpp(@RequestParam(name = "custAgent") String custAgent, @RequestParam(name = "busiOppNoList",required = false) List busiOppNoList) {
		Result res = new Result();
		try {
			busiOppService.allotBusiOpp(custAgent, busiOppNoList);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("分配商机信息失败");
		}
		return res;
	}

	/**
	 * @api {PUT} /crm/ocrm/busiOpp/cancelBusiOpp 取消商机
	 * @apiDescription 
	 * @apiName cancelBusiOpp
	 * @apiGroup BUSIOPP
	 * 
	 * @apiParam {List} busiOppNoList 商机编号list
	 * @apiParam {String} cancelReason 取消原因
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ocrm/busiOpp/cancelBusiOpp", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.BUSIOPP, bizDesc="分配商机")
	public Result cancelBusiOpp(
			@RequestParam(name = "busiOppNoList",required = false) List busiOppNoList, 
			@RequestParam(name = "cancelReason") String cancelReason) {
		Result res = new Result();
		try {
			busiOppService.cancelBusiOpp(busiOppNoList, cancelReason);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("取消商机信息失败");
		}
		return res;
	}

	/**
	 * @api {PUT} /crm/ocrm/busiOpp/setBusiOppStage 取消商机
	 * @apiDescription 
	 * @apiName setBusiOppStage
	 * @apiGroup BUSIOPP
	 * 
	 * @apiParam {BusiOppStageInfo} stageInfo 商机阶段bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ocrm/busiOpp/setBusiOppStage", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.BUSIOPP, bizDesc="设置商机状态")
	public Result setBusiOppStage(StageInfo stageInfo) {
		Result res = new Result();
		try {
			busiOppService.setBusiOppStage(stageInfo);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("设置商机阶段状态失败");
		}
		return res;
	}
}
