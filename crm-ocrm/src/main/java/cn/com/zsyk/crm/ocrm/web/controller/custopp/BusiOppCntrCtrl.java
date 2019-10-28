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
import cn.com.zsyk.crm.ocrm.entity.OcBusiOppCntr;
import cn.com.zsyk.crm.ocrm.service.custopp.BusiOppCntrService;

@RestController
public class BusiOppCntrCtrl {
	@Autowired
	BusiOppCntrService busiOppCntrService;

	/**
	 * @api {GET} /crm/ocrm/busiOpp/BusiOppCntrList 查询商机联系人列表
	 * @apiName getBusiOppCntrList
	 * @apiGroup BUSIOPP
	 *
	 * @apiParam {OcBusiOppCntr} ocBusiOppCntr 商机联系人信息bean
	 * 
	 * @apiSuccess {Result} data 返回值对象
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ocrm/busiOpp/BusiOppCntrList", method = RequestMethod.GET)
	public Result getBusiOppCntrList(OcBusiOppCntr ocBusiOppCntr) {
		Result res = new Result();
		res.setData(busiOppCntrService.getBusiOppCntrList(ocBusiOppCntr));
		return res;
	}

	/**
	 * @api {GET} /crm/ocrm/busiOpp/busiOppCntrOne 查询商机联系人(单条)
	 * @apiDescription 
	 * @apiName getBusiOppCntrOne
	 * @apiGroup BUSIOPP
	 * 
	 * @apiParam {String} busiOppNo 商机联系人编号
	 * @apiParam {String} custNo 客户号
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ocrm/busiOpp/busiOppCntrOne", method = RequestMethod.GET)
	public Result getBusiOppCntrOne(String busiOppNo, String custNo) {
		Result res = new Result();
		res.setData(busiOppCntrService.getBusiOppCntrOne(busiOppNo, custNo));
		return res;
	}

	/**
	 * @api {PUT} /crm/ocrm/busiOpp/addBusiOpp 新增商机联系人
	 * @apiDescription 
	 * @apiName addBusiOppCntr
	 * @apiGroup BUSIOPP
	 * 
	 * @apiParam {String} busiOppNo 商机编吗
	 * @apiParam {String} custNo 客户号
	 * @apiParam {List} cntrList 联系人编码List
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ocrm/busiOpp/addBusiOppCntr", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.BUSIOPP, bizDesc="新增商机联系人")
	public Result addBusiOppCntr(@RequestParam(name = "custNo") String custNo, 
			@RequestParam(name = "busiOppNo") String busiOppNo, 
			@RequestParam(name = "cntrList",required = false) List cntrList) {
		Result res = new Result();
		try {
			busiOppCntrService.addBusiOppCntr(custNo, busiOppNo, cntrList);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("新增商机联系人信息失败");
		}
		return res;
	}

	/**
	 * @api {PUT} /crm/ocrm/busiOpp/uptBusiOpp 修改商机联系人
	 * @apiDescription 
	 * @apiName uptBusiOppCntr
	 * @apiGroup BUSIOPP
	 * 
	 * @apiParam {OcBusiOppCntr} ocBusiOppCntr 商机联系人信息Bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ocrm/busiOpp/uptBusiOppCntr", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.BUSIOPP, bizDesc="修改商机联系人")
	public Result uptBusiOppCntr(OcBusiOppCntr ocBusiOppCntr) {
		Result res = new Result();
		try {
			busiOppCntrService.uptBusiOppCntr(ocBusiOppCntr);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("修改商机联系人信息失败");
		}
		return res;
	}

	/**
	 * @api {PUT} /crm/ocrm/busiOpp/delBusiOpp 删除商机联系人
	 * @apiDescription 
	 * @apiName delBusiOppCntr
	 * @apiGroup BUSIOPP
	 * 
	 * @apiParam {OcBusiOppCntr} ocBusiOppCntr 商机联系人信息Bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ocrm/busiOpp/delBusiOppCntr", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.BUSIOPP, bizDesc="删除商机联系人")
	public Result delBusiOppCntr(OcBusiOppCntr ocBusiOppCntr) {
		Result res = new Result();
		try {
			busiOppCntrService.delBusiOppCntr(ocBusiOppCntr);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("删除商机联系人信息失败");
		}
		return res;
	}
}
