package cn.com.zsyk.crm.manage.web.controller.mngcenter.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.zsyk.crm.common.annotation.SysOprtLog;
import cn.com.zsyk.crm.common.annotation.SysOprtLog.Module;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.manage.entity.SysRoleInfo;
import cn.com.zsyk.crm.manage.service.mngcenter.auth.RoleService;

@RestController
public class RoleCtrl {
	@Autowired
	private RoleService service;

	/**
	 * @api {GET} /crm/manage/getAllRoles 查询所有角色信息
	 * @apiDescription 
	 * @apiName getAllRoles
	 * @apiGroup Authority
	 * 
	 * @apiSuccess {List} SysRoleInfo 角色信息列表
	 */
	@RequestMapping(path = "/crm/manage/getAllRoles", method = RequestMethod.GET)
	public Result getAllRoles() {

		Result res = new Result();
		System.out.println("获取角色列表，更换logger形式");

		List<SysRoleInfo> roleList = service.getAllRole();

		res.setData(roleList);
		return res;
	}

	/**
	 * @api {GET} /crm/manage/getOneRole 查询单条角色信息
	 * @apiDescription 
	 * @apiName getOneRole
	 * @apiGroup Authority
	 *
	 * @apiParam {String} roleCode 角色代码
	 *
	 * @apiSuccess {object} SysRoleInfo 角色信息对象
	 */
	@RequestMapping(path = "/crm/manage/getOneRole", method = RequestMethod.GET)
	public Result getOneRole(String roleCode) {

		Result res = new Result();
		System.out.println("获取单条角色数据，更换logger形式");

		SysRoleInfo roleInfo = service.getOneRole(roleCode);

		res.setData(roleInfo);
		return res;
	}

	/**
	 * @api {GET} /crm/manage/getRolesByEntity 根据条件查询角色信息
	 * @apiDescription 
	 * @apiName getRolesByEntity
	 * @apiGroup Authority
	 *
	 * @apiParam {SysRoleInfo} roleInfo 角色信息对象
	 *
	 * @apiSuccess {List} SysRoleInfo 角色信息列表
	 */
	@RequestMapping(path = "/crm/manage/getRolesByEntity", method = RequestMethod.GET)
	public Result getRolesByEntity(SysRoleInfo roleInfo) {
		Result res = new Result();
		System.out.println("获取岗位列表，更换logger形式");
		List<SysRoleInfo> roleList = service.getRoleByEntity(roleInfo);
		res.setData(roleList);
		return res;
	}

	/**
	 * @api {POST} /crm/manage/addRole 增加角色信息
	 * @apiDescription 
	 * @apiName addRole
	 * @apiGroup Authority
	 * 
	 * @apiParam {SysRoleInfo} roleInfo 角色信息对象
	 *
	 */
	@RequestMapping(path = "/crm/manage/addRole", method = RequestMethod.POST)
	@SysOprtLog(model = Module.USERAUTH, bizDesc="新增角色数据")
	public Result addRole(SysRoleInfo roleInfo) {
		Result res = new Result();
		System.out.println("新增单条角色数据，更换logger形式");

		int addCount = service.addRole(roleInfo);

		if (addCount > 0) {
			System.out.println("插入数据成功，更换logger形式");
		} else {
			throw new ServiceException("新增角色数据数据失败");
		}

		return res;
	}

	/**
	 * @api {PUT} /crm/manage/modRole 修改角色信息
	 * @apiDescription 
	 * @apiName modRole
	 * @apiGroup Authority
	 * 
	 * @apiParam {SysRoleInfo} roleInfo 角色信息对象
	 *
	 */
	@RequestMapping(path = "/crm/manage/modRole", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.USERAUTH, bizDesc="修改角色数据")
	public Result modRole(SysRoleInfo roleInfo) {
		Result res = new Result();
		System.out.println("修改单条角色数据，更换logger形式");

		int modCount = service.modRole(roleInfo);

		if (modCount > 0) {
			System.out.println("修改数据成功，更换logger形式");
		} else {
			throw new ServiceException("修改角色数据数据失败");
		}

		return res;
	}

	/**
	 * @api {DELETE} /crm/manage/delRole 物理删除角色信息
	 * @apiDescription 
	 * @apiName delRole
	 * @apiGroup Authority
	 * 
	 * @apiParam {String} roleCode 角色代码
	 * 
	 */
	@RequestMapping(path = "/crm/manage/delRole", method = RequestMethod.DELETE)
	@SysOprtLog(model = Module.USERAUTH, bizDesc="删除角色数据")
	public Result delRole(String roleCode) {
		Result res = new Result();
		System.out.println("物理删除单条角色数据，更换logger形式");

		// 删除角色信息
		int delCount = service.delRole(roleCode);

		if (delCount > 0) {
			System.out.println("删除数据成功，更换logger形式");
		} else {
			throw new ServiceException("删除角色数据数据失败");
		}

		return res;
	}

}
