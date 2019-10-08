package cn.com.zsyk.crm.manage.web.controller.mngcenter.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.zsyk.crm.common.annotation.SysOprtLog;
import cn.com.zsyk.crm.common.annotation.SysOprtLog.Module;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.JsonUtil;
import cn.com.zsyk.crm.manage.entity.SysRoleMenu;
import cn.com.zsyk.crm.manage.service.mngcenter.auth.MenuAuthService;

@RestController
public class MenuAuthCtrl {
	@Autowired
	private MenuAuthService service;

	/**
	 * @api {GET} /crm/manage/getAllMenuAuths 获取所有角色菜单权限信息
	 * @apiDescription 
	 * @apiName getAllMenuAuths
	 * @apiGroup Authority
	 *
	 * @apiSuccess {List} SysRoleMenu 角色菜单权限信息列表
	 */
	@RequestMapping(path = "/crm/manage/getAllMenuAuths", method = RequestMethod.GET)
	public Result getAllMenuAuths() {
		Result res = new Result();
		System.out.println("获取角色菜单权限列表");

		List<SysRoleMenu> roleList = service.getAllMenuAuth();
		res.setData(roleList);
		return res;
	}

	/**
	 * @api {GET} /crm/manage/getOneMenuAuth 获取单条角色菜单权限信息
	 * @apiDescription 
	 * @apiName getOneMenuAuth
	 * @apiGroup Authority
	 *
	 * @apiSuccess {SysRoleMenu} SysRoleMenu 角色菜单权限信息对象
	 */
	@RequestMapping(path = "/crm/manage/getOneMenuAuth", method = RequestMethod.GET)
	public Result getOneMenuAuth(String roleCode, String menuCode) {
		Result res = new Result();
		System.out.println("获取单条角色菜单权限数据");
		SysRoleMenu roleMenuInfo = service.getOneMenuAuth(roleCode, menuCode);
		res.setData(roleMenuInfo);
		return res;
	}

	/**
	 * @api {GET} /crm/manage/getMenuAuthsByEntity 根据条件查询角色菜单权限信息
	 * @apiDescription 
	 * @apiName getMenuAuthsByEntity
	 * @apiGroup Authority
	 *
	 * @apiParam {SysRoleMenu} roleMenuInfo 角色菜单信息对象
	 *
	 * @apiSuccess {List} SysRoleMenu 角色菜单权限信息列表
	 */
	@RequestMapping(path = "/crm/manage/getMenuAuthsByEntity", method = RequestMethod.GET)
	public Result getMenuAuthsByEntity(SysRoleMenu roleMenuInfo) {
		Result res = new Result();
		System.out.println("获取角色菜单列表");
		List<SysRoleMenu> roleList = service.getMenuAuthByEntity(roleMenuInfo);
		res.setData(roleList);
		return res;
	}

	/**
	 * @api {POST} /crm/manage/addMenuAuthByEntity 批量增加菜单权限
	 * @apiDescription 
	 * @apiName addMenuAuth
	 * @apiGroup Authority
	 *
	 * @apiParam {List} SysRoleMenu 角色菜单信息对象
	 *
	 */
	@RequestMapping(path = "/crm/manage/addMenuAuthByEntity", method = RequestMethod.POST)
	@SysOprtLog(model = Module.MENUAUTH, bizDesc="新增菜单权限")
	public Result addMenuAuthByEntity(List<SysRoleMenu> roleMenuList) {
		Result res = new Result();
		System.out.println("新增单条角色菜单数据");

		int addCount = service.batchAddMenuAuth(roleMenuList);

		if (addCount > 0) {
			System.out.println("插入数据成功");
		} else {
			throw new ServiceException("新增角色数据数据失败");
		}
		return res;
	}

	/**
	 * @api {PUT} /crm/manage/addMenuAuthByJson 批量增加菜单权限信息
	 * @apiDescription 
	 * @apiName addMenuAuth
	 * @apiGroup Authority
	 *
	 * @apiParam {String} roleMenuStr SysRoleMenu对象的json格式字符串
	 *
	 */
	@RequestMapping(path = "/crm/manage/addMenuAuthByJson", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.MENUAUTH, bizDesc="新增菜单权限")
	public Result addMenuAuthByJson(@RequestBody String roleMenuStr) {

		Result res = new Result();
		System.out.println("新增单条角色菜单数据");

		List<SysRoleMenu> roleMenuList = JsonUtil.parseArray(roleMenuStr, SysRoleMenu.class);

		int addCount = service.batchAddMenuAuth(roleMenuList);

		if (addCount > 0) {
			System.out.println("插入数据成功:" + addCount + "条");
		}
		return res;
	}

	/**
	 * @api {DELETE} /crm/manage/delModMenuAuth 根据角色删除相关角色菜单记录
	 * @apiDescription 
	 * @apiName delModMenuAuth
	 * @apiGroup Authority
	 *
	 * @apiParam {String} roleCode 角色代码
	 *
	 */
	@RequestMapping(path = "/crm/manage/delMenuAuthByRole", method = RequestMethod.DELETE)
	@SysOprtLog(model = Module.MENUAUTH, bizDesc="删除角色菜单")
	public Result delMenuAuthByRole(String roleCode) {
		Result res = new Result();
		System.out.println("逻辑删除单条角色数据");

		int addCount = service.delMenuAuthByRole(roleCode);

		if (addCount > 0) {
			System.out.println("删除数据成功");
		} else {
			throw new ServiceException("删除角色数据数据失败");
		}

		return res;
	}
}
