package cn.com.zsyk.crm.manage.web.controller.mngcenter.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.zsyk.crm.common.annotation.SysOprtLog;
import cn.com.zsyk.crm.common.annotation.SysOprtLog.Module;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.util.JsonUtil;
import cn.com.zsyk.crm.manage.entity.SysEnterInfo;
import cn.com.zsyk.crm.manage.entity.SysRoleEnter;
import cn.com.zsyk.crm.manage.entity.SysTreeNode;
import cn.com.zsyk.crm.manage.service.mngcenter.auth.EnterAuthService;
import cn.com.zsyk.crm.manage.service.mngcenter.deptorg.EnterService;

@RestController
public class EnterAuthCtrl {
	@Autowired
	private EnterAuthService enterAuthService;
	@Autowired
	private EnterService enterService;

	/**
	 * @api {GET} /crm/manage/auth/getRoleEnterByRole 获取角色的人事架构根节点列表（机构代码）
	 * @apiDescription 
	 * @apiName getRoleEnterByRole
	 * @apiGroup Authority
	 * 
	 * @apiParam {String} roleCode 角色代码
	 *
	 * @apiSuccess {List} SysRoleEnter 角色的人事架构根节点列表（机构代码）
	 */
	@RequestMapping(path = "/crm/manage/auth/getRoleEnterByRole", method = RequestMethod.GET)
	public Result getRoleEnterByRole(String roleCode) {
		Result res = new Result();
		System.out.println("获取角色的人事架构根节点列表（机构代码）");

		List<SysRoleEnter> roleEnterList = enterAuthService.getRoleEnterByRole(roleCode);
		res.setData(roleEnterList);
		return res;
	}

	/**
	 * @api {GET} /crm/manage/auth/getRoleEnterTree
	 *      根据角色的人事架构根节点（机构代码）获取人事架构树（机构根节点下的所有的部门及岗位）
	 * @apiDescription 
	 * @apiName getRoleEnterTree
	 * @apiGroup Authority
	 * 
	 * @apiParam {String} SysRoleEnter 角色的人事架构根节点列表（机构代码）的json字符串
	 *
	 * @apiSuccess {List} SysTreeNode 树节点对象列表
	 */
	@RequestMapping(path = "/crm/manage/auth/getRoleEnterTree", method = RequestMethod.GET)
	public Result getRoleEnterTree(String roleEnterJson) {
		Result res = new Result();
		System.out.println("根据角色的人事架构根节点（机构代码）获取人事架构树（机构根节点下的所有的部门及岗位）");

		List<SysRoleEnter> roleEnterList = JsonUtil.parseArray(roleEnterJson, SysRoleEnter.class);

		List<SysTreeNode> treeNodeList = new ArrayList<SysTreeNode>();

		for (SysRoleEnter item : roleEnterList) {
			List<SysTreeNode> treeNodeListTemp = enterAuthService.getRoleEnterTree(item.getEnterCode());

			treeNodeList.addAll(treeNodeListTemp);
		}

		res.setData(treeNodeList);
		return res;
	}

	/**
	 * @api {GET} /crm/manage/auth/getRoleDeptTree
	 *      根据角色的人事架构根节点（机构代码）获取人事架构树（机构根节点下的所有的部门）
	 * @apiDescription 
	 * @apiName getRoleDeptTree
	 * @apiGroup Authority
	 * 
	 * @apiParam {String} SysRoleEnter 角色的人事架构根节点列表（机构代码）的json字符串
	 *
	 * @apiSuccess {List} SysTreeNode 树节点对象列表
	 */
	@RequestMapping(path = "/crm/manage/auth/getRoleDeptTree", method = RequestMethod.GET)
	public Result getRoleDeptTree(String roleEnterJson) {
		Result res = new Result();

		List<SysEnterInfo> superEnterList = enterService.getAllSuperEnter();

		List<SysTreeNode> treeNodeList = new ArrayList<SysTreeNode>();

		for (SysEnterInfo item : superEnterList) {
			List<SysTreeNode> treeNodeListTemp = enterAuthService.getRoleDeptTree(item.getEnterCode());

			treeNodeList.addAll(treeNodeListTemp);
		}

		res.setData(treeNodeList);
		return res;
	}
	
	/**
	 * @api {GET} /crm/manage/auth/getAllEnterTree 获取机构代码及下属机构的机构树
	 * @apiDescription 
	 * @apiName getAllEnterTree
	 * @apiGroup Authority
	 * 
	 * @apiParam {String} enterCode 机构代码
	 *
	 * @apiSuccess {List} SysTreeNode 树节点对象列表
	 */
	@RequestMapping(path = "/crm/manage/auth/getAllEnterTree", method = RequestMethod.GET)
	public Result getAllEnterTree(String enterCode) {
		Result res = new Result();
		System.out.println("获取机构代码及下属机构的机构树");

		List<SysTreeNode> treeNodeList = enterAuthService.getAllEnterTree(enterCode);

		res.setData(treeNodeList);
		return res;
	}

	/**
	 * @api {PUT} /crm/manage/auth/updateRoleEnter 更新角色的人事架构根节点列表（机构代码）
	 * @apiDescription 
	 * @apiName updateRoleEnter
	 * @apiGroup Authority
	 * 
	 * @apiParam {List} SysRoleEnter 角色机构根节点列表
	 *
	 * @apiSuccess {List} SysRoleEnter 角色的人事架构根节点列表（机构代码）
	 */
	@RequestMapping(path = "/crm/manage/auth/updateRoleEnter", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.PERSTRUCAUTH, bizDesc = "修改角色的人事架构根节点列表（机构代码）")
	public Result updateRoleEnter(List<SysRoleEnter> roleEnterList) {
		Result res = new Result();
		System.out.println("更新角色的人事架构根节点列表（机构代码）");

		int result = enterAuthService.updateRoleEnter(roleEnterList);

		return res;
	}

	/**
	 * @api {PUT} /crm/manage/auth/updateRoleEnterByJson
	 *      根据json字符串更新角色的人事架构根节点列表（机构代码）
	 * @apiDescription 
	 * @apiName updateRoleEnterByJson
	 * @apiGroup Authority
	 * 
	 * @apiParam {String} roleCode 角色代码
	 *
	 * @apiSuccess {List} SysRoleEnter 角色的人事架构根节点列表（机构代码）
	 */
	@RequestMapping(path = "/crm/manage/auth/updateRoleEnterByJson", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.PERSTRUCAUTH, bizDesc = "修改角色的人事架构根节点列表（机构代码）")
	public Result updateRoleEnterByJson(@RequestBody String jsonStr) {
		Result res = new Result();
		System.out.println("更新角色的人事架构根节点列表（机构代码）");

		List<SysRoleEnter> roleEnterList = JsonUtil.parseArray(jsonStr, SysRoleEnter.class);

		int result = enterAuthService.updateRoleEnter(roleEnterList);

		return res;
	}

	/**
	 * @api {GET} /crm/manage/auth/getAllRoleEnterTree 获取所有人事架构树（机构根节点下的所有的部门及岗位）
	 * @apiDescription 
	 * @apiName getRoleEnterTree
	 * @apiGroup Authority
	 *
	 * @apiParam {String} SysRoleEnter 角色的人事架构根节点列表（机构代码）的json字符串
	 *
	 * @apiSuccess {List} SysTreeNode 树节点对象列表
	 */
	@RequestMapping(path = "/crm/manage/auth/getAllRoleEnterTree", method = RequestMethod.GET)
	public Result getAllRoleEnterTree() {
		Result res = new Result();
		System.out.println("获取人事架构树（机构根节点下的所有的部门及岗位）");

		List<SysEnterInfo> superEnterList = enterService.getAllSuperEnter();

		List<SysTreeNode> treeNodeList = new ArrayList<SysTreeNode>();

		for (SysEnterInfo item : superEnterList) {
			List<SysTreeNode> treeNodeListTemp = enterAuthService.getRoleEnterTree(item.getEnterCode());

			treeNodeList.addAll(treeNodeListTemp);
		}

		res.setData(treeNodeList);
		return res;
	}

	/**
	 * @api {DELETE} /crm/manage/auth/deleteEnterAuthByRole 删除角色的人事架构根节点列表（机构代码）
	 * @apiDescription 
	 * @apiName deleteEnterAuthByRole
	 * @apiGroup Authority
	 * 
	 * @apiParam {String} roleCode 角色代码
	 *
	 */
	@RequestMapping(path = "/crm/manage/auth/deleteEnterAuthByRole", method = RequestMethod.DELETE)
	@SysOprtLog(model = Module.PERSTRUCAUTH, bizDesc = "删除角色的人事架构根节点列表（机构代码）")
	public Result deleteEnterAuthByRole(String roleCode) {
		Result res = new Result();

		int result = enterAuthService.deleteByRole(roleCode);

		return res;
	}
}
