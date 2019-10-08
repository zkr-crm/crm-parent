package cn.com.zsyk.crm.manage.web.controller.logmng;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.zsyk.crm.common.dao.CoreDaoImpl;
import cn.com.zsyk.crm.common.dto.PageBean;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.entity.SysOperLog;
import cn.com.zsyk.crm.common.mapper.SysOperLogMapper;
import cn.com.zsyk.crm.manage.service.logmng.OperLogService;

@RestController
public class OperLogCtrl {
	@Autowired
	OperLogService operLogService;
	@Autowired
	private CoreDaoImpl dao;
	@Autowired
	private SysOperLogMapper  sysOperLogMapper;
	/**
	 * @api {GET} /crm/manage/logmng/selOperLog 按条件查询所有操作日志
	 * @apiName selOperLog
	 * @apiGroup OperLog
	 *
	 * @apiParam {LoginLogService} operLog 操作日志对象
	 *
	 * @apiSuccess {LoginLogService} operLog 操作日志对象集和
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/manage/logmng/selOperLog", method = RequestMethod.GET)
	public Result selOperLog(String userId, String startDate, String endDate) {
		Result res = new Result();
		System.out.println("查询登陆日志。");
		Map param = new HashMap();
		param.put("userId", userId);
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		PageBean p = dao.selectPageByMapper(sysOperLogMapper, "selectByEntity", param);
		res.setData(p);
		return res;
	}
}
