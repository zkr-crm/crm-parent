package cn.com.zsyk.crm.manage.service.mngcenter.auth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import cn.com.zsyk.crm.common.dao.AbstractDao;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.manage.entity.SysRoleInfo;
import cn.com.zsyk.crm.manage.entity.SysRoleMenu;
import cn.com.zsyk.crm.manage.mapper.SysRoleMenuMapper;
import cn.com.zsyk.crm.manage.service.mngcenter.menu.MenuService;

@Service
@Transactional
public class MenuAuthService {

	@Autowired
	private SysRoleMenuMapper roleMenuMapper;
	@Autowired
	private AbstractDao daoUtil;
	@Autowired
	MenuService menuService;
	@Autowired
	RoleService roleService;

	/**
	 * 获得一条角色菜单信息的方法
	 * 
	 * @param roleCode
	 *            角色代码
	 * @param menuCode
	 *            菜单代码
	 * @return 角色菜单信息
	 */
	public SysRoleMenu getOneMenuAuth(String roleCode, String menuCode) {

		SysRoleMenu roleMenuInfo = new SysRoleMenu();

		roleMenuInfo = roleMenuMapper.selectByPrimaryKey(roleCode, menuCode);

		return roleMenuInfo;
	}

	/**
	 * 获得角色对应的所有菜单权限信息的方法
	 * 
	 * @param roleCode
	 *            角色代码
	 * 
	 * @return 角色菜单权限信息
	 */
	public List<SysRoleMenu> getAllMenuByRoleCode(String roleCode) {

		if (StringUtils.isEmpty(roleCode)) {
			throw new ServiceException("角色代码不能为空！");
		}

		// 判断角色代码是否存在
		SysRoleInfo zcsRoleInfo = roleService.getOneRole(roleCode);
		if (zcsRoleInfo == null) {
			throw new ServiceException("角色代码不存在：角色代码[" + roleCode + "]");
		}

		List<SysRoleMenu> roleMenuList = new ArrayList<SysRoleMenu>();

		Map map = new HashMap();
		map.put("roleCode", roleCode);
		// 获取角色信息
		roleMenuList = daoUtil.selectList("cn.com.zsyk.crm.manage.mapper.SysRoleMenuMapper.selectMenuByRole", map);

		// 角色信息及菜单代码不为空
		if (roleMenuList == null && 0 == roleMenuList.size()) {
			throw new ServiceException("角色菜单权限信息不存在：角色代码[" + roleCode + "]");
		}

		return roleMenuList;
	}

	/**
	 * 获取所有角色菜单信息的方法
	 * 
	 * @return 所有角色菜单信息的列表
	 */
	public List<SysRoleMenu> getAllMenuAuth() {

		List<SysRoleMenu> lstMenuAuth = new ArrayList<SysRoleMenu>();

		lstMenuAuth = roleMenuMapper.selectAll();

		return lstMenuAuth;
	}

	/**
	 * 根据入参对象获取所有角色菜单信息的方法
	 * 
	 * @return 所有角色菜单信息的列表
	 */
	public List<SysRoleMenu> getMenuAuthByEntity(SysRoleMenu record) {

		if (!StringUtils.isEmpty(record.getRoleCode())) {
			// 判断角色代码是否存在
			SysRoleInfo zcsRoleInfo = roleService.getOneRole(record.getRoleCode());
			if (zcsRoleInfo == null) {
				throw new ServiceException("角色代码不存在：角色代码[" + record.getRoleCode() + "]");
			}
		}

		// 查询角色菜单信息
		List<SysRoleMenu> lstUser = daoUtil
				.selectList("cn.com.zsyk.crm.manage.mapper.SysRoleMenuMapper.selectRoleMenuByEntity", record);

		return lstUser;
	}

	/**
	 * 增加角色菜单信息的方法
	 * 
	 * @param roleMenuInfo
	 *            需要增加的角色菜单信息
	 * @return 增加成功的条目数，0为失败
	 */
	public int addMenuAuth(SysRoleMenu roleMenuInfo) {

		if (StringUtils.isEmpty(roleMenuInfo.getRoleCode())) {
			throw new ServiceException("角色代码不能为空!");
		}

		if (StringUtils.isEmpty(roleMenuInfo.getMenuCode())) {
			throw new ServiceException("菜单代码不能为空!");
		}

		// 判断角色代码是否存在
		SysRoleInfo zcsRoleInfo = roleService.getOneRole(roleMenuInfo.getRoleCode());
		if (zcsRoleInfo == null) {
			throw new ServiceException("角色代码不存在：角色代码[" + roleMenuInfo.getRoleCode() + "]");
		}

		// 角色菜单信息是否存在判断
		SysRoleMenu roleMenuBean = this.getOneMenuAuth(roleMenuInfo.getRoleCode(), roleMenuInfo.getMenuCode());
		if (roleMenuBean != null) {
			throw new ServiceException(
					"角色菜单信息已经存在：角色代码[" + roleMenuInfo.getRoleCode() + "]，菜单代码[" + roleMenuInfo.getMenuCode() + "]");
		}
		int insCount = roleMenuMapper.insert(roleMenuInfo);

		return insCount;

	}

	/**
	 * 批量删除增加角色菜单信息的方法
	 * 
	 * @param roleMenuInfo
	 *            需要增加的角色菜单信息
	 * @return 增加成功的条目数
	 */
	public int batchAddMenuAuth(List<SysRoleMenu> roleMenuList) {

		int insCount = 0;

		// 删除原有的角色代码对应的菜单信息
		this.delMenuAuthByRole(roleMenuList.get(0).getRoleCode());

		// 新增角色代码对应的菜单信息
		for (SysRoleMenu item : roleMenuList) {

			int count = roleMenuMapper.insert(item);
			insCount += count;
		}

		return insCount;

	}

	/**
	 * 修改某条角色菜单信息的方法
	 * 
	 * @param roleMenuInfo
	 *            需要修改的角色菜单信息
	 * @return 修改成功的条目数，0为失败
	 */
	public int modMenuAuth(SysRoleMenu roleMenuInfo) {
		// 存在判断
		SysRoleMenu roleMenuBean = this.getOneMenuAuth(roleMenuInfo.getRoleCode(), roleMenuInfo.getMenuCode());
		if (roleMenuBean == null) {
			throw new ServiceException(
					"角色菜单信息不存在：角色代码[" + roleMenuInfo.getRoleCode() + "]，菜单代码[" + roleMenuInfo.getMenuCode() + "]");
		}

		int modCount = roleMenuMapper.updateByPrimaryKey(roleMenuInfo);

		return modCount;
	}

	/**
	 * 根据主键物理删除某角色菜单信息的方法
	 * 
	 * @param roleCode
	 *            角色代码
	 * @param menuCode
	 *            菜单代码
	 * @return
	 */
	public int delMenuAuth(String roleCode, String menuCode) {
		// 存在判断
		SysRoleMenu roleMenuBean = this.getOneMenuAuth(roleCode, menuCode);
		if (roleMenuBean == null) {
			throw new ServiceException("角色菜单信息不存在：角色代码[" + roleCode + "],菜单代码[" + menuCode + "]");
		}

		int delCount = roleMenuMapper.deleteByPrimaryKey(roleCode, menuCode);

		return delCount;
	}

	/**
	 * 根据角色代码物理删除菜单信息的方法
	 * 
	 * @param roleCode
	 *            角色代码
	 * @return
	 */
	public int delMenuAuthByRole(String roleCode) {

		// 判断角色代码是否存在
		SysRoleInfo zcsRoleInfo = roleService.getOneRole(roleCode);
		if (zcsRoleInfo == null) {
			throw new ServiceException("角色代码不存在：角色代码[" + roleCode + "]");
		}

		Map map = new HashMap();
		map.put("roleCode", roleCode);
		int delCount = daoUtil.delete("cn.com.zsyk.crm.manage.mapper.SysRoleMenuMapper.deleteByRoleKey", map);

		return delCount;
	}
}
