package cn.com.zsyk.crm.ecif.web.controller.customer.portrait;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.ecif.entity.EcHealthFile;
import cn.com.zsyk.crm.ecif.service.customer.portrait.CustHealthFileService;

@RestController
public class CustHealthFileCtrl {

	@Autowired
	private CustHealthFileService custHealthFileService;
	/**
	 * @api {GET} /crm/ecif/cust/custHealthFileList 查询客户健康档案列表
	 * @apiDescription 
	 * @apiName getCustHealthFileList
	 * @apiGroup Customer
	 * 
	 * @apiParam {EcHealthFile} ecHealthFile 客户健康档案bean
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			就医/检查日期		chk_date<br/>
	 * 			就医/检查编号		chk_no<br/>
	 * 			客户号					cust_no<br/>
	 * 			医疗机构名称			med_org_nam<br/>
	 * 			诊室				consul_room<br/>
	 * 			负责医生		principal_doctor<br/>
	 * 			档案类型		file_typ->FileType<br/>
	 * 			检查内容		chek_content<br/>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/custHealthFileList", method = RequestMethod.GET)
	public Result getCustHealthFileList(EcHealthFile ecHealthFile) {
		Result res = new Result();
		res.setData(custHealthFileService.getCustHealthFileList(ecHealthFile));
		return res;
	}

}
