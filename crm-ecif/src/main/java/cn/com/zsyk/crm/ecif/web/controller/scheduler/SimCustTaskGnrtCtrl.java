package cn.com.zsyk.crm.ecif.web.controller.scheduler;

import cn.com.zsyk.crm.common.util.RestUtil;
import cn.com.zsyk.crm.ecif.service.scheduler.birthmng.SimCustTaskGnrtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 相似客户任务生成
 * 
 * @author
 *
 */
@RestController
public class SimCustTaskGnrtCtrl {

	@Autowired
	private SimCustTaskGnrtService service;
	@Autowired
	private RestUtil restUtil;

//	@SuppressWarnings("rawtypes")
//	@RequestMapping(path = "/crm/ecif/scheduler/simCustTaskGnrt", method = RequestMethod.POST)
//	public Result simCustTaskGnrt() {
//		Result res = new Result();
//		service.simCustTaskGnrt();
//		return res;
//	}

}
