package cn.com.zsyk.crm.ecif.web.controller.customer.portrait;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.ecif.entity.EcAudioVideo;
import cn.com.zsyk.crm.ecif.service.customer.portrait.CustAudioVideoService;

@RestController
public class CustAudioVideoCtrl {

	@Autowired
	private CustAudioVideoService custAudioVideoService;
	/**
	 * @api {GET} /crm/ecif/cust/custAudioVideoList 查询客户音/视频列表
	 * @apiDescription 
	 * @apiName getCustAudioVideoList
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcAudioVideo} ecAudioVideo 客户音/视频bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			生成日期		gen_date<br/>
	 * 			客户号		cust_no<br/>
	 * 			文件名称		file_nam<br/>
	 * 			内容类型		content_typ->ContentType<br/>
	 * 			文件地址		file_url<br/>
	 * 			负责人		principal<br/>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/custAudioVideoList", method = RequestMethod.GET)
	public Result getCustAudioVideoList(EcAudioVideo ecAudioVideo) {
		Result res = new Result();
		res.setData(custAudioVideoService.getCustAudioVideoList(ecAudioVideo));
		return res;
	}

	/**
	 * @api {GET} /crm/ecif/cust/custAudioVideoOne查询客户音/视频(单条)
	 * @apiDescription 
	 * @apiName getCustAudioVideoOne
	 * @apiGroup Customer
	 * 
	 * @apiParam {String} audioVideoCd 音频/视频code
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			音频/视频code audioVideoCd<br/>
	 * 			生成日期		gen_date<br/>
	 * 			客户号		cust_no<br/>
	 * 			文件名称		file_nam<br/>
	 * 			内容类型		content_typ->ContentType<br/>
	 * 			文件地址		file_url<br/>
	 * 			负责人		principal<br/>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/custAudioVideoOne", method = RequestMethod.GET)
	public Result getCustAudioVideoOne(String audioVideoCd) {
		Result res = new Result();
		res.setData(custAudioVideoService.getCustAudioVideoOne(audioVideoCd));
		return res;
	}
}
