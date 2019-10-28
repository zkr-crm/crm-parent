package cn.com.zsyk.crm.ecif.web.controller.customer.portrait;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import cn.com.zsyk.crm.common.annotation.SysOprtLog;
import cn.com.zsyk.crm.common.annotation.SysOprtLog.Module;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.ecif.entity.EcTrackInfo;
import cn.com.zsyk.crm.ecif.service.customer.portrait.CustDyncTrackService;

@RestController
public class CustDyncTrackCtrl {

	@Autowired
	private CustDyncTrackService custDyncTrackService;
	/**
	 * @api {GET} /crm/ecif/cust/dyncTrackList 查询动态轨迹列表
	 * @apiDescription 
	 * @apiName getCustDyncTrackByCustNo
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcTrackInfo} ecTrackInfo 客户轨迹bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			轨迹编号		track_cd<br/>
	 * 			轨迹时间		track_time<br/>
	 * 			客户号		cust_no<br/>
	 * 			轨迹类型		track_typ->TrackType<br/>
	 * 			内容详情		track_content<br/>
	 * 			记录人		record_user<br/>
	 * 			是否接通		wht_through->YesNoFlg<br/>
	 * 			联系人		contacts<br/>
	 * 			下次跟进时间		next_follow_up_tm<br/>
	 * 			评论标识		comt_flg->YesNoFlg<br/>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/dyncTrackList", method = RequestMethod.GET)
	public Result getCustDyncTrackByCustNo(EcTrackInfo ecTrackInfo) {
		Result res = new Result();
		res.setData(custDyncTrackService.selectCustDyncTrackList(ecTrackInfo));
		return res;
	}

	/**
	 * @api {GET} /crm/ecif/cust/dyncTrackOne 查询动态轨迹(单条)
	 * @apiDescription 
	 * @apiName getCustDyncTrackByTrackNo
	 * @apiGroup Customer
	 * 
	 * @apiParam {String} trackCd 轨迹编码
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			轨迹编号		track_cd<br/>
	 * 			轨迹时间		track_time<br/>
	 * 			动态轨迹号		cust_no<br/>
	 * 			轨迹类型		track_typ->TrackType<br/>
	 * 			内容详情		track_content<br/>
	 * 			记录人		record_user<br/>
	 * 			是否接通		wht_through->YesNoFlg<br/>
	 * 			联系人		contacts<br/>
	 * 			下次跟进时间		next_follow_up_tm<br/>
	 * 			评论标识		comt_flg->YesNoFlg<br/>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/dyncTrackOne", method = RequestMethod.GET)
	public Result getCustDyncTrackByTrackNo(String trackCd) {
		Result res = new Result();
		res.setData(custDyncTrackService.selectCustDyncTrackOne(trackCd));
		return res;
	}

	/**
	 * @api {PUT} /crm/ecif/cust/addDyncTrackTel 新增电话动态轨迹
	 * @apiDescription 
	 * @apiName addDyncTrackTel
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcTrackInfo} ecTrackInfo 动态轨迹Bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/addDyncTrackTel", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.CUSTOMER, bizDesc="新增电话动态轨迹")
	public Result addDyncTrackTel(EcTrackInfo ecTrackInfo) {
		Result res = new Result();
		try {
			custDyncTrackService.addDyncTrackTel(ecTrackInfo);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("新增电话动态轨迹信息失败");
		}
		return res;
	}

	/**
	 * @api {PUT} /crm/ecif/cust/addDyncTrackSms 新增短信动态轨迹
	 * @apiDescription 
	 * @apiName addDyncTrackSms
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcTrackInfo} ecTrackInfo 动态轨迹Bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/addDyncTrackSms", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.CUSTOMER, bizDesc="新增短信动态轨迹")
	public Result addDyncTrackSms(EcTrackInfo ecTrackInfo) {
		Result res = new Result();
		try {
			custDyncTrackService.addDyncTrackSms(ecTrackInfo);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("新增短信动态轨迹信息失败");
		}
		return res;
	}

	/**
	 * @api {PUT} /crm/ecif/cust/addDyncTrackVis 新增拜访动态轨迹
	 * @apiDescription 
	 * @apiName addDyncTrackVis
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcTrackInfo} ecTrackInfo 动态轨迹Bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/addDyncTrackVis", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.CUSTOMER, bizDesc="新增拜访动态轨迹")
	public Result addDyncTrackVis(EcTrackInfo ecTrackInfo) {
		Result res = new Result();
		try {
			custDyncTrackService.addDyncTrackVis(ecTrackInfo);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("新增动态轨迹信息失败");
		}
		return res;
	}

	/**
	 * @api {PUT} /crm/ecif/cust/uptDyncTrack 修改动态轨迹
	 * @apiDescription 
	 * @apiName uptDyncTrack
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcTrackInfo} ecTrackInfo 动态轨迹Bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/uptDyncTrack", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.CUSTOMER, bizDesc="修改动态轨迹")
	public Result uptDyncTrack(EcTrackInfo ecTrackInfo) {
		Result res = new Result();
		try {
			custDyncTrackService.uptDyncTrack(ecTrackInfo);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("修改动态轨迹信息失败");
		}
		return res;
	}

	/**
	 * @api {PUT} /crm/ecif/cust/delDyncTrack 删除动态轨迹
	 * @apiDescription 
	 * @apiName delDyncTrack
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcTrackInfo} ecTrackInfo 动态轨迹Bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/delDyncTrack", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.CUSTOMER, bizDesc="删除动态轨迹")
	public Result delDyncTrack(EcTrackInfo ecTrackInfo) {
		Result res = new Result();
		try {
			custDyncTrackService.delDyncTrack(ecTrackInfo);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("删除动态轨迹信息失败");
		}
		return res;
	}

	/**
	 * @api {PUT} /crm/ecif/cust/addDyncTrack 新增拜访动态轨迹
	 * @apiDescription 
	 * @apiName addDyncTrack
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcTrackInfo} ecTrackInfo 动态轨迹Bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/addDyncTrack", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.CUSTOMER, bizDesc="新增动态轨迹")
	public Result addDyncTrack(EcTrackInfo ecTrackInfo) {
		Result res = new Result();
		try {
			custDyncTrackService.addDyncTrack(ecTrackInfo);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("新增动态轨迹信息失败");
		}
		return res;
	}
}
