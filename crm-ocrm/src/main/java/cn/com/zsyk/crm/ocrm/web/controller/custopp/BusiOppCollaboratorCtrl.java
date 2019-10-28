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
import cn.com.zsyk.crm.ocrm.entity.OcBusiOppCollaborator;
import cn.com.zsyk.crm.ocrm.service.custopp.BusiOppCollaboratorService;

@RestController
public class BusiOppCollaboratorCtrl {
	@Autowired
	BusiOppCollaboratorService busiOppCollaboratorService;

	/**
	 * @api {GET} /crm/ocrm/busiOpp/busiOppCollaboratorList 查询商机协作人列表
	 * @apiName getBusiOppCollaboratorList
	 * @apiGroup BUSIOPP
	 *
	 * @apiParam {OcBusiOppCollaborator} ocBusiOppCollaborator 商机协作人信息bean
	 * 
	 * @apiSuccess {Result} data 返回值对象
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ocrm/busiOpp/busiOppCollaboratorList", method = RequestMethod.GET)
	public Result getBusiOppCollaboratorList(OcBusiOppCollaborator ocBusiOppCollaborator) {
		Result res = new Result();
		res.setData(busiOppCollaboratorService.getBusiOppCollaboratorList(ocBusiOppCollaborator));
		return res;
	}

	/**
	 * @api {GET} /crm/ocrm/busiOpp/busiOppCollaboratorOne 查询商机协作人(单条)
	 * @apiDescription 
	 * @apiName getBusiOppCollaboratorOne
	 * @apiGroup BUSIOPP
	 * 
	 * @apiParam {String} busiOppNo 商机协作人编号
	 * @apiParam {String} contractCustNo 协作人编号
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ocrm/busiOpp/busiOppCollaboratorOne", method = RequestMethod.GET)
	public Result getBusiOppCollaboratorOne(String busiOppNo, String contractCustNo) {
		Result res = new Result();
		res.setData(busiOppCollaboratorService.getBusiOppCollaboratorOne(busiOppNo, contractCustNo));
		return res;
	}

	/**
	 * @api {PUT} /crm/ocrm/busiOpp/addBusiOpp 新增商机协作人
	 * @apiDescription 
	 * @apiName addBusiOppCollaborator
	 * @apiGroup BUSIOPP
	 * 
	 * @apiParam {OcBusiOppCollaborator} ocBusiOppCollaborator 商机协作人信息Bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ocrm/busiOpp/addBusiOppCollaborator", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.BUSIOPP, bizDesc="新增商机协作人")
	public Result addBusiOppCollaborator(@RequestParam(name = "busiOppNo") String busiOppNo, 
			@RequestParam(name = "custNo") String custNo, 
			@RequestParam(name = "userIdList",required = false) List userIdList) {
		Result res = new Result();
		try {
			busiOppCollaboratorService.addBusiOppCollaborator(busiOppNo, custNo, userIdList);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("新增商机协作人信息失败");
		}
		return res;
	}

	/**
	 * @api {PUT} /crm/ocrm/busiOpp/uptBusiOpp 修改商机协作人
	 * @apiDescription 
	 * @apiName uptBusiOppCollaborator
	 * @apiGroup BUSIOPP
	 * 
	 * @apiParam {OcBusiOppCollaborator} ocBusiOppCollaborator 商机协作人信息Bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ocrm/busiOpp/uptBusiOppCollaborator", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.BUSIOPP, bizDesc="修改商机协作人")
	public Result uptBusiOppCollaborator(OcBusiOppCollaborator ocBusiOppCollaborator) {
		Result res = new Result();
		try {
			busiOppCollaboratorService.uptBusiOppCollaborator(ocBusiOppCollaborator);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("修改商机协作人信息失败");
		}
		return res;
	}

	/**
	 * @api {PUT} /crm/ocrm/busiOpp/delBusiOpp 删除商机协作人
	 * @apiDescription 
	 * @apiName delBusiOppCollaborator
	 * @apiGroup BUSIOPP
	 * 
	 * @apiParam {OcBusiOppCollaborator} ocBusiOppCollaborator 商机协作人信息Bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ocrm/busiOpp/delBusiOppCollaborator", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.BUSIOPP, bizDesc="删除商机协作人")
	public Result delBusiOppCollaborator(OcBusiOppCollaborator ocBusiOppCollaborator) {
		Result res = new Result();
		try {
			busiOppCollaboratorService.delBusiOppCollaborator(ocBusiOppCollaborator);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("删除商机协作人信息失败");
		}
		return res;
	}
}
