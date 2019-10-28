package cn.com.zsyk.crm.ecif.web.controller.customer.portrait;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.com.zsyk.crm.common.annotation.SysOprtLog;
import cn.com.zsyk.crm.common.annotation.SysOprtLog.Module;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.ecif.entity.EcAssetsPer;
import cn.com.zsyk.crm.ecif.service.customer.portrait.CustAssetsService;

@RestController
public class CustAssetsCtrl {

	@Autowired
	private CustAssetsService custAssetsService;
	/**
	 * @api {GET} /crm/ecif/cust/perCustAssetsList 查询个人资产列表
	 * @apiDescription 
	 * @apiName getPerCustAssetsList
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcAssetsPer} ecAssetsPer 客户资产bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			客户号		cust_no<br/>
	 * 			资产序号	seq_no<br/>
	 * 			资产名称	assets_nam<br/>
	 * 			资产类型	assets_typ->(待确认)<br/>
	 * 			储值额度	stored_amt<br/>
	 * 			获得时间	obtain_time<br/>
	 * 			到期时间	expire_time<br/>
	 * 			建立时间	establish_time<br/>
	 * 			负责人	principal<br/>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/perCustAssetsList", method = RequestMethod.GET)
	public Result getPerCustAssetsList(EcAssetsPer ecAssetsPer) {
		Result res = new Result();
		res.setData(custAssetsService.getPerAssetsList(ecAssetsPer));
		return res;
	}

	/**
	 * @api {GET} /crm/ecif/cust/perCustAssetsInfo 查询个人资产(单条)
	 * @apiDescription 
	 * @apiName getPerCustAssetsInfo
	 * @apiGroup Customer
	 * 
	 * @apiParam {String} custNo 客户号
	 * @apiParam {int} seqNo 资产序号
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			客户号		cust_no<br/>
	 * 			资产序号	seq_no<br/>
	 * 			资产名称	assets_nam<br/>
	 * 			资产类型	assets_typ->(待确认)<br/>
	 * 			储值额度	stored_amt<br/>
	 * 			获得时间	obtain_time<br/>
	 * 			到期时间	expire_time<br/>
	 * 			建立时间	establish_time<br/>
	 * 			负责人	principal<br/>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/perCustAssetsInfo", method = RequestMethod.GET)
	public Result getPerCustAssetsInfo(@RequestParam("custNo")String custNo, @RequestParam("seqNo")int seqNo) {
		Result res = new Result();
		res.setData(custAssetsService.getPerCustAssetsByPk(custNo, seqNo));
		return res;
	}

	/**
	 * @api {PUT} /crm/ecif/cust/addPerCustAssets 新增个人资产信息
	 * @apiDescription 
	 * @apiName addPerCustAssets
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcAssetsPer} ecAssetsPer 客户资产bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/false
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/addPerCustAssets", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.CUSTOMER, bizDesc="新增个人资产信息")
	public Result addPerCustAssets(EcAssetsPer ecAssetsPer) {
		Result res = new Result();
		try {
			custAssetsService.addPerCustAssets(ecAssetsPer);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("新增客户资产信息失败");
		}

		return res;
	}

	/**
	 * @api {PUT} /crm/ecif/cust/uptPerCustAssets 修改个人资产信息
	 * @apiDescription 
	 * @apiName uptPerCustAssets
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcAssetsPer} ecAssetsPer 客户资产bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/false
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/uptPerCustAssets", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.CUSTOMER, bizDesc="修改个人资产信息")
	public Result uptPerCustAssets(EcAssetsPer ecAssetsPer) {
		Result res = new Result();
		try {
			custAssetsService.uptPerCustAssets(ecAssetsPer);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("修改客户资产信息失败");
		}
		return res;
	}

	/**
	 * @api {PUT} /crm/ecif/cust/delPerCustAssets 删除个人资产信息
	 * @apiDescription 
	 * @apiName delPerCustAssets
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcAssetsPer} ecAssetsPer 客户资产bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/false
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/delPerCustAssets", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.CUSTOMER, bizDesc="删除个人资产信息")
	public Result delPerCustAssets(EcAssetsPer ecAssetsPer) {
		Result res = new Result();
		try {
			custAssetsService.delPerCustAssets(ecAssetsPer);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("删除客户资产信息失败");
		}
		return res;
	}

	/**
	 * @api {GET} /crm/ecif/cust/entCustAssetsList 查询企业资产信息(N)
	 * @apiDescription 
	 * @apiName getEntCustAssetsInfo
	 * @apiGroup Customer
	 * 
	 * @apiParam {String} custNo 客户号
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			客户号		cust_no<br/>
	 * 			办公面积		office_area<br/>
	 * 			固定资产（万元）		fixed_assets<br/>
	 * 			无形资产（万元）		intag_assets<br/>
	 * 			房产数量		house_qty<br/>
	 * 			物业数量		real_estate_qty<br/>
	 * 			车数量		car_qty<br/>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/entCustAssetsList", method = RequestMethod.GET)
	public Result getEntCustAssetsInfo(String custNo) {
		Result res = new Result();
		res.setData(custAssetsService.getEntCustAssetsInfo(custNo));
		return res;
	}
}
