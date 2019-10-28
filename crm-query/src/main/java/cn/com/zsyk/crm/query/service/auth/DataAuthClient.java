package cn.com.zsyk.crm.query.service.auth;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.zsyk.crm.common.dto.Result;

@FeignClient("CRM-MANAGE")
public interface DataAuthClient {
	/**
	 * @api {GET} /crm/manage/auth/getRoleDateAuth 获取角色对列表的数据权限
	 * @apiDescription 
	 * @apiName getRoleDateEmployees
	 * @apiGroup Authority
	 *
	 * @apiParam {String} userCode 用户代码
	 * @apiParam {String} tableName 表名
	 * 
	 * @apiSuccess {String} employees 拥有查看权限的数据对应的员工号字符串
	 */
	@RequestMapping(path = "/crm/manage/auth/getRoleDateAuth", method = RequestMethod.GET)
	public Result getRoleDateEmployees(String userCode, String tableCode) ;
}