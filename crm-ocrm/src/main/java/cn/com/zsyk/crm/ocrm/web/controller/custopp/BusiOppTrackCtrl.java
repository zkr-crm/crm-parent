package cn.com.zsyk.crm.ocrm.web.controller.custopp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import cn.com.zsyk.crm.common.annotation.SysOprtLog;
import cn.com.zsyk.crm.common.annotation.SysOprtLog.Module;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.ocrm.entity.OcBusiOppTrack;
import cn.com.zsyk.crm.ocrm.service.custopp.BusiOppTrackService;

@RestController
public class BusiOppTrackCtrl {


	@Autowired
	private BusiOppTrackService busiOppTrackService;
	/**
	 * @api {GET} /crm/ocrm/busiOpp/dyncTrackList 查询商机轨迹列表
	 * @apiDescription 
	 * @apiName getCustDyncTrackByBusiOppNo
	 * @apiGroup BUSIOPP
	 * 
	 * @apiParam {OcBusiOppTrack} ocBusiOppTrack 客户轨迹bean
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
	@RequestMapping(path = "/crm/ocrm/busiOpp/dyncTrackList", method = RequestMethod.GET)
	public Result getCustDyncTrackByBusiOppNo(OcBusiOppTrack ocBusiOppTrack) {
		Result res = new Result();
		res.setData(busiOppTrackService.selectBusiOppTrackList(ocBusiOppTrack));
		return res;
	}

	/**
	 * @api {GET} /crm/ocrm/busiOpp/dyncTrackOne 查询商机轨迹(单条)
	 * @apiDescription 
	 * @apiName getCustDyncTrackByTrackNo
	 * @apiGroup BUSIOPP
	 * 
	 * @apiParam {String} trackCd 轨迹编码
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			轨迹编号		track_cd<br/>
	 * 			轨迹时间		track_time<br/>
	 * 			商机轨迹号		cust_no<br/>
	 * 			轨迹类型		track_typ->TrackType<br/>
	 * 			内容详情		track_content<br/>
	 * 			记录人		record_user<br/>
	 * 			是否接通		wht_through->YesNoFlg<br/>
	 * 			联系人		contacts<br/>
	 * 			下次跟进时间		next_follow_up_tm<br/>
	 * 			评论标识		comt_flg->YesNoFlg<br/>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ocrm/busiOpp/dyncTrackOne", method = RequestMethod.GET)
	public Result getCustDyncTrackByTrackNo(String trackCd) {
		Result res = new Result();
		res.setData(busiOppTrackService.selectCustDyncTrackOne(trackCd));
		return res;
	}

	/**
	 * @api {PUT} /crm/ocrm/busiOpp/addDyncTrackTel 新增电话商机轨迹
	 * @apiDescription 
	 * @apiName addDyncTrackTel
	 * @apiGroup BUSIOPP
	 * 
	 * @apiParam {OcBusiOppTrack} ocBusiOppTrack 商机轨迹Bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ocrm/busiOpp/addDyncTrackTel", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.BUSIOPP, bizDesc="新增电话商机轨迹")
	public Result addDyncTrackTel(OcBusiOppTrack ocBusiOppTrack) {
		Result res = new Result();
		try {
			busiOppTrackService.addDyncTrackTel(ocBusiOppTrack);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("新增电话商机轨迹信息失败");
		}
		return res;
	}

	/**
	 * @api {PUT} /crm/ocrm/busiOpp/addDyncTrackSms 新增短信商机轨迹
	 * @apiDescription 
	 * @apiName addDyncTrackSms
	 * @apiGroup BUSIOPP
	 * 
	 * @apiParam {OcBusiOppTrack} ocBusiOppTrack 商机轨迹Bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ocrm/busiOpp/addDyncTrackSms", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.BUSIOPP, bizDesc="新增短信商机轨迹")
	public Result addDyncTrackSms(OcBusiOppTrack ocBusiOppTrack) {
		Result res = new Result();
		try {
			busiOppTrackService.addDyncTrackSms(ocBusiOppTrack);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("新增短信商机轨迹信息失败");
		}
		return res;
	}

	/**
	 * @api {PUT} /crm/ocrm/busiOpp/addDyncTrackVis 新增拜访商机轨迹
	 * @apiDescription 
	 * @apiName addDyncTrackVis
	 * @apiGroup BUSIOPP
	 * 
	 * @apiParam {OcBusiOppTrack} ocBusiOppTrack 商机轨迹Bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ocrm/busiOpp/addDyncTrackVis", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.BUSIOPP, bizDesc="新增拜访商机轨迹")
	public Result addDyncTrackVis(OcBusiOppTrack ocBusiOppTrack) {
		Result res = new Result();
		try {
			busiOppTrackService.addDyncTrackVis(ocBusiOppTrack);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("新增商机轨迹信息失败");
		}
		return res;
	}

	/**
	 * @api {PUT} /crm/ocrm/busiOpp/uptDyncTrack 修改商机轨迹
	 * @apiDescription 
	 * @apiName uptDyncTrack
	 * @apiGroup BUSIOPP
	 * 
	 * @apiParam {OcBusiOppTrack} ocBusiOppTrack 商机轨迹Bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ocrm/busiOpp/uptDyncTrack", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.BUSIOPP, bizDesc="修改商机轨迹")
	public Result uptDyncTrack(OcBusiOppTrack ocBusiOppTrack) {
		Result res = new Result();
		try {
			busiOppTrackService.uptDyncTrack(ocBusiOppTrack);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("修改商机轨迹信息失败");
		}
		return res;
	}

	/**
	 * @api {PUT} /crm/ocrm/busiOpp/delDyncTrack 删除商机轨迹
	 * @apiDescription 
	 * @apiName delDyncTrack
	 * @apiGroup BUSIOPP
	 * 
	 * @apiParam {OcBusiOppTrack} ocBusiOppTrack 商机轨迹Bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ocrm/busiOpp/delDyncTrack", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.BUSIOPP, bizDesc="删除商机轨迹")
	public Result delDyncTrack(OcBusiOppTrack ocBusiOppTrack) {
		Result res = new Result();
		try {
			busiOppTrackService.delDyncTrack(ocBusiOppTrack);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("删除商机轨迹信息失败");
		}
		return res;
	}

	/**
	 * @api {PUT} /crm/ocrm/busiOpp/addDyncTrack 新增拜访商机轨迹
	 * @apiDescription 
	 * @apiName addDyncTrack
	 * @apiGroup BUSIOPP
	 * 
	 * @apiParam {OcBusiOppTrack} ocBusiOppTrack 商机轨迹Bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/fasle
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ocrm/busiOpp/addDyncTrack", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.BUSIOPP, bizDesc="新增商机轨迹")
	public Result addDyncTrack(OcBusiOppTrack ocBusiOppTrack) {
		Result res = new Result();
		try {
			busiOppTrackService.addDyncTrack(ocBusiOppTrack);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("新增商机轨迹信息失败");
		}
		return res;
	}

}
