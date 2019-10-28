package cn.com.zsyk.crm.manage.web.controller.mngcenter.auth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.zsyk.crm.common.annotation.SysOprtLog;
import cn.com.zsyk.crm.common.annotation.SysOprtLog.Module;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.util.JsonUtil;
import cn.com.zsyk.crm.manage.bom.SysDataAuthInfo;
import cn.com.zsyk.crm.manage.bom.SysRoleDataDetail;
import cn.com.zsyk.crm.manage.bom.SysRuleTypeBean;
import cn.com.zsyk.crm.manage.entity.SysDataOption;
import cn.com.zsyk.crm.manage.entity.SysDataRule;
import cn.com.zsyk.crm.manage.entity.SysRoleData;
import cn.com.zsyk.crm.manage.entity.SysUserInfo;
import cn.com.zsyk.crm.manage.service.mngcenter.auth.DataAuthService;

@RestController
public class DataAuthCtrl {
	@Autowired
	private DataAuthService dataAuthService;

	/**
	 * @api {GET} /crm/manage/auth/getTableOptions 获取所有需要控制数据权限的表名
	 * @apiDescription 
	 * @apiName getTableOptions
	 * @apiGroup Authority
	 *
	 * @apiSuccess {List} SysDataOption 权限控制表列表
	 */
	@RequestMapping(path = "/crm/manage/auth/getTableOptions", method = RequestMethod.GET)
	public Result getTableOptions() {
		Result res = new Result();
		System.out.println("获取所有需要控制数据权限的表名");

		List<SysDataOption> dataOptionList = dataAuthService.getTableOptions();
		res.setData(dataOptionList);
		return res;
	}

	/**
	 * @api {GET} /crm/manage/auth/getDataRuleList 获取所有需要控制数据权限的类型
	 * @apiName getDataRuleList
	 * @apiGroup Authority
	 *
	 * @apiSuccess {List} SysDataRule 角色数据规则类别
	 */
	@RequestMapping(path = "/crm/manage/auth/getDataRuleList", method = RequestMethod.GET)
	public Result getDataRuleList() {
		Result res = new Result();
		System.out.println("获取所有需要控制数据权限的表名");

		List<SysDataRule> dataRuleList = dataAuthService.getSysDataRuleList();
		res.setData(dataRuleList);
		return res;
	}

	/**
	 * @api {GET} /crm/manage/auth/getAllTableList 获取所有需要控制数据权限的表名
	 * @apiDescription 
	 * @apiName getTableOptions
	 * @apiGroup Authority
	 *
	 * @apiSuccess {List} SysDataOption 权限控制表列表
	 */
	@RequestMapping(path = "/crm/manage/auth/getAllTableList", method = RequestMethod.GET)
	public Result getAllTableList() {
		Result res = new Result();
		System.out.println("获取所有需要控制数据权限的表名集合");

		List<SysDataOption> dataOptionList = dataAuthService.getTableOptions();
		Map<String, String> map = new HashMap<>();
		for (SysDataOption vo : dataOptionList) {
			map.put(vo.getTableCode(), vo.getTableName());
		}

		List<SysDataOption> results = new ArrayList<>();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			SysDataOption vo = new SysDataOption();
			vo.setTableCode(entry.getKey());
			vo.setTableName(entry.getValue());
			results.add(vo);
		}

		res.setData(results);
		return res;
	}

	/**
	 * @api {GET} /crm/manage/auth/getFieldByTable 根据表名获取该表的匹配字段
	 * @apiDescription 
	 * @apiName getFieldByTable
	 * @apiGroup Authority
	 *
	 * @apiParam {String} tableCode 表代码
	 *
	 * @apiSuccess {List} SysDataOption 权限控制表列表
	 */
	@RequestMapping(path = "/crm/manage/auth/getFieldByTable", method = RequestMethod.GET)
	public Result getFieldByTable(String tableCode) {
		Result res = new Result();
		System.out.println("根据表名获取该表的匹配字段");

		List<SysDataOption> dataOptionList = dataAuthService.getFieldByTable(tableCode);
		res.setData(dataOptionList);
		return res;
	}

	/**
	 * @api {GET} /crm/manage/auth/getRuleType 根据表名、字段名获取数据权限规则可选类别
	 * @apiDescription 
	 * @apiName getRuleType
	 * @apiGroup Authority
	 *
	 * @apiParam {String} tableCode 表代码
	 * @apiParam {String} tableField 表字段
	 *
	 * @apiSuccess {List} SysRuleType 匹配规则类型列表
	 */
	@RequestMapping(path = "/crm/manage/auth/getRuleType", method = RequestMethod.GET)
	public Result getRuleType(String tableCode, String tableField) {
		Result res = new Result();
		System.out.println("根据表名、字段名获取数据权限规则可选类别");

		List<SysRuleTypeBean> ruleTypeList = dataAuthService.getRuleType(tableCode, tableField);
		res.setData(ruleTypeList);
		return res;
	}

	/**
	 * @api {GET} /crm/manage/auth/getRoleData 根据角色表名查询角色数据权限
	 * @apiDescription 
	 * @apiName getRoleData
	 * @apiGroup Authority
	 *
	 * @apiParam {String} roleCode 角色代码
	 * @apiParam {String} tableCode 表代码
	 *
	 * @apiSuccess {List} SysRoleData 角色数据权限列表
	 */
	@RequestMapping(path = "/crm/manage/auth/getRoleData", method = RequestMethod.GET)
	public Result getRoleData(String roleCode, String tableCode) {
		Result res = new Result();
		System.out.println("根据角色表名查询角色数据权限");

		List<SysDataAuthInfo> roleDataList = dataAuthService.getRoleData(roleCode, tableCode);
		res.setData(roleDataList);
		return res;
	}

	/**
	 * @api {GET} /crm/manage/auth/getRoleDataByField 根据角色表名查询角色数据权限
	 * @apiDescription 
	 * @apiName getRoleData
	 * @apiGroup Authority
	 *
	 * @apiParam {String} roleCode 角色代码
	 * @apiParam {String} tableCode 表代码
	 * @apiParam {String} fieldCode fieldCode
	 *
	 * @apiSuccess {List} SysRoleData 角色数据权限列表
	 */
	@RequestMapping(path = "/crm/manage/auth/getRoleDataByField", method = RequestMethod.GET)
	public Result getRoleDataByField(String roleCode, String tableCode, String fieldCode) {
		Result res = new Result();
		System.out.println("根据角色表名查询角色数据权限");

		List<SysRoleData> roleDataList = dataAuthService.getRoleDataByField(roleCode, tableCode,fieldCode);
		res.setData(roleDataList);
		return res;
	}

	/**
	 * @api {PUT} /crm/manage/auth/updateRoleData 更新角色数据权限表
	 * @apiDescription 
	 * @apiName updateRoleData
	 * @apiGroup Authority
	 *
	 * @apiParam {String} tableCode 表代码
	 *
	 * @apiSuccess {List} SysRuleType 匹配规则类型列表
	 */
	@RequestMapping(path = "/crm/manage/auth/updateRoleData", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.DATAAUTH, bizDesc = "修改角色数据权限")
	public Result updateRoleData(List<SysRoleData> roleDataList) {
		Result res = new Result();
		System.out.println("更新角色数据权限表");

		int result = dataAuthService.updateRoleData(roleDataList);

		return res;
	}

	/**
	 * @api {PUT} /crm/manage/auth/updateRoleDataByJson 批量增加角色数据权限表
	 * @apiDescription 
	 * @apiName updateRoleDataByJson
	 * @apiGroup Authority
	 *
	 * @apiParam {String} roleMenuStr SysRoleMenu对象的json格式字符串
	 *
	 */
	@RequestMapping(path = "/crm/manage/auth/updateRoleDataByJson", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.DATAAUTH, bizDesc = "新增角色菜单数据")
	public Result updateRoleDataByJson(@RequestBody String roleDataStr) {

		Result res = new Result();
		System.out.println("新增单条角色菜单数据");

		List<SysRoleData> roleDataList = JsonUtil.parseArray(roleDataStr, SysRoleData.class);

		int addCount = dataAuthService.updateRoleData(roleDataList);

		if (addCount > 0) {
			System.out.println("插入数据成功:" + addCount + "条");
		}
		return res;
	}

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
	public Result getRoleDateEmployees(String userCode, String tableCode) {

		Result res = new Result();
		System.out.println("获取角色对列表的数据权限");

		List<SysUserInfo> employees = dataAuthService.getRolesDateEmployees(userCode, tableCode);

		res.setData(employees);
		return res;
	}

	/**
	 * @api {DELETE} /crm/manage/auth/deleteByRoleTableField 根据角色和表名和字段名删除角色数据权限表
	 * @apiDescription 
	 * @apiName deleteByRoleTableField
	 * @apiGroup Authority
	 *
	 * @apiParam {String} roleCode 角色代码
	 * @apiParam {String} tableCode 表名
	 * @apiParam {String} fieldCode 字段名
	 * 
	 */
	@RequestMapping(path = "/crm/manage/auth/deleteByRoleTableField", method = RequestMethod.DELETE)
	public Result deleteByRoleTableField(String roleCode, String tableCode, String fieldCode) {

		Result res = new Result();

		int result = dataAuthService.deleteByRoleTableField(roleCode, tableCode, fieldCode);

		return res;
	}


	/**
	 * @api {PUT} /crm/manage/auth/subUpdateRoleDataByJson 批量变更单条角色菜单数据
	 * @apiDescription 
	 * @apiName updateRoleDataByJson
	 * @apiGroup Authority
	 *
	 * @apiParam {String} roleMenuStr SysRoleMenu对象的json格式字符串
	 *
	 */
	@RequestMapping(path = "/crm/manage/auth/subUpdateRoleDataByJson", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.DATAAUTH, bizDesc = "新增角色菜单数据")
	public Result subUpdateRoleDataByJson(@RequestBody String roleDataStr) {

		Result res = new Result();
		System.out.println("变更单条角色菜单数据");

		List<SysRoleData> roleDataList = JsonUtil.parseArray(roleDataStr, SysRoleData.class);

		int addCount = dataAuthService.subUpdateRoleData(roleDataList);

		if (addCount > 0) {
			System.out.println("插入数据成功:" + addCount + "条");
		}
		return res;
	}
	/**
	 * @api {GET} /crm/manage/auth/getRoleDataDetail 获取所有需要控制数据权限的表名
	 * @apiName getRoleDataDetail
	 * @apiGroup Authority
	 *
	 * @apiSuccess {List} SysRoleDataDetail 数据权限明细列表
	 */
	@RequestMapping(path = "/crm/manage/auth/getRoleDataDetail", method = RequestMethod.GET)
	public Result getRoleDataDetail(String roleCode, String tableCode) {
		Result res = new Result();
		System.out.println("获取数据权限明细");

		List<SysRoleDataDetail> list = dataAuthService.getRoleDataDetail(roleCode, tableCode);
		res.setData(list);
		return res;
	}


}