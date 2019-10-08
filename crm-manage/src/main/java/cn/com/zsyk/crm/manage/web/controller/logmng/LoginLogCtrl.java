package cn.com.zsyk.crm.manage.web.controller.logmng;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.zsyk.crm.common.dao.CoreDaoImpl;
import cn.com.zsyk.crm.common.dto.PageBean;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.manage.entity.SysLoginLog;
import cn.com.zsyk.crm.manage.service.logmng.LoginLogService;

@RestController
public class LoginLogCtrl {

	@Autowired
	private LoginLogService loginLogService;
	@Autowired
	private CoreDaoImpl dao;

	/**
	 * @api {POST} /crm/manage/logmng/addLoginLog 新增登陆日志
	 * @apiName addLoginLog
	 * @apiGroup LoginLog
	 *
	 * @apiParam {LoginLogService} loginLog 登录日志对象
	 *
	 * @apiSuccess {LoginLogService} loginLog 登录日志对象
	 */
	@RequestMapping(path = "/crm/manage/logmng/addLoginLog", method = RequestMethod.POST)
	public Result addLoginLog(SysLoginLog loginLog) {
		Result res = new Result();
		System.out.println("新增一条登录日志。");
		loginLogService.addLoginLog(loginLog);
		return res;
	}
	/**
	 * @api {GET} /crm/manage/logmng/selLoginLog 按条件查询所有登陆日志
	 * @apiName selLoginLog
	 * @apiGroup LoginLog
	 *
	 * @apiParam {LoginLogService} loginLog 登录日志对象
	 *
	 * @apiSuccess {LoginLogService} loginLog 登录日志对象集和
	 */
	@RequestMapping(path = "/crm/manage/logmng/selLoginLog", method = RequestMethod.GET)
	public Result selLoginLog(String userId, String startDate, String endDate) {
		Result res = new Result();
		System.out.println("查询登陆日志。");
		Map param = new HashMap();
		param.put("userId", userId);
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		PageBean p = dao.selectPageById("cn.com.zsyk.crm.manage.mapper.SysLoginLogMapper.selectByEntity",param);
		res.setData(p);
		return res;
	}
	

	
	
	

}
