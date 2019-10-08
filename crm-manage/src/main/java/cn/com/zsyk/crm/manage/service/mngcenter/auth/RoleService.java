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
import cn.com.zsyk.crm.common.util.BeanUtil;
import cn.com.zsyk.crm.manage.entity.SysRoleInfo;
import cn.com.zsyk.crm.manage.entity.SysUserInfo;
import cn.com.zsyk.crm.manage.mapper.SysRoleInfoMapper;
import cn.com.zsyk.crm.manage.service.mngcenter.user.UserMngService;

@Service
@Transactional
public class RoleService {

	@Autowired
	private SysRoleInfoMapper roleMapper;
	@Autowired
	private AbstractDao daoUtil;
	@Autowired
	private MenuAuthService menuAuthService;
	@Autowired
	EnterAuthService enterAuthService;
	@Autowired
	DataAuthService dataAuthService;
	@Autowired
	UserMngService userMngService;

	/**
	 * 获得一条角色信息的方法
	 * 
	 * @param roleCode
	 *            角色代码
	 * @param roleName
	 *            角色名称
	 * @return 角色信息
	 */
	public SysRoleInfo getOneRole(String roleCode) {

		if (StringUtils.isEmpty(roleCode)) {
			throw new ServiceException("角色代码[" + roleCode + "]不能为空！");
		}

		SysRoleInfo roleInfoBean = new SysRoleInfo();
		roleInfoBean = roleMapper.selectByPrimaryKey(roleCode);

		return roleInfoBean;
	}

	/**
	 * 获取所有角色信息的方法
	 * 
	 * @return 所有角色信息的列表
	 */
	public List<SysRoleInfo> getAllRole() {

		List<SysRoleInfo> roleInfoList = new ArrayList<SysRoleInfo>();

		roleInfoList = roleMapper.selectAll();

		return roleInfoList;
	}

	/**
	 * 根据入参对象获取所有角色信息的方法
	 * 
	 * @return 所有角色信息的列表
	 */
	public List<SysRoleInfo> getRoleByEntity(SysRoleInfo record) {

		List<SysRoleInfo> roleInfoList = daoUtil
				.selectList("cn.com.zsyk.crm.manage.mapper.SysRoleInfoMapper.selectRoleByEntity", record);

		return roleInfoList;
	}

	/**
	 * 增加角色信息的方法
	 * 
	 * @param roleInfo
	 *            需要增加的角色信息
	 * @return 增加成功的条目数，0为失败
	 */
	public int addRole(SysRoleInfo roleInfo) {

		if (roleInfo == null) {
			throw new ServiceException("输入信息不能为空！");
		}

		if (StringUtils.isEmpty(roleInfo.getRoleCode())) {
			throw new ServiceException("角色代码不能为空！");
		}

		if (StringUtils.isEmpty(roleInfo.getRoleName())) {
			throw new ServiceException("角色名称不能为空！");
		}

		// 存在判断
		SysRoleInfo roleBean = this.getOneRole(roleInfo.getRoleCode());
		if (roleBean != null) {
			throw new ServiceException("角色信息已经存在：角色代码[" + roleInfo.getRoleCode() + "]");
		}

		int insCount = roleMapper.insert(roleInfo);

		return insCount;

	}

	/**
	 * 修改某条角色信息的方法
	 * 
	 * @param roleInfo
	 *            需要修改的角色信息
	 * @return 修改成功的条目数，0为失败
	 */
	public int modRole(SysRoleInfo roleInfo) {

		if (roleInfo == null) {
			throw new ServiceException("输入信息不能为空！");
		}
		// 存在判断
		SysRoleInfo roleBean = this.getOneRole(roleInfo.getRoleCode());
		if (roleBean == null) {
			throw new ServiceException("角色信息不存在：角色代码[" + roleInfo.getRoleCode() + "]");
		}

		BeanUtil.copy(roleInfo, roleBean);

		if (StringUtils.isEmpty(roleInfo.getRoleCode())) {
			throw new ServiceException("角色代码不能为空！");
		}

		if (StringUtils.isEmpty(roleInfo.getRoleName())) {
			throw new ServiceException("角色名称不能为空！");
		}

		int modCount = roleMapper.updateByPrimaryKey(roleBean);

		return modCount;
	}

	/**
	 * 根据主键物理删除某角色信息的方法
	 * 
	 * @param roleCode
	 *            角色代码
	 * @return
	 */
	public int delRole(String roleCode) {

		if (StringUtils.isEmpty(roleCode)) {
			throw new ServiceException("角色代码不能为空！");
		}

		// 角色存在判断
		SysRoleInfo roleBean = this.getOneRole(roleCode);
		if (roleBean == null) {
			throw new ServiceException("角色信息不存在：角色代码[" + roleCode + "]");
		}

		// 角色删除前检查是否存在拥有该角色的用户，若有则不允许删除
		List<SysUserInfo> userList = userMngService.getUserByRole(roleCode);
		if (userList != null && userList.size() > 0) {

			String users = "";
			for (SysUserInfo item : userList) {
				users = users + "[" + item.getUserId() + "],";
			}
			users = users.substring(0, users.length() - 1);

			throw new ServiceException("存在拥有该角色的用户，不允许删除：角色代码[" + roleCode + "]，用户代码：" + users + "!");
		}

		// 删除角色菜单权限数据
		menuAuthService.delMenuAuthByRole(roleCode);

		// 删除人事架构权限数据
		enterAuthService.deleteByRole(roleCode);

		// 删除数据权限数据
		dataAuthService.deleteRoleDataByRole(roleCode);

		// 删除角色
		int delCount = roleMapper.deleteByPrimaryKey(roleCode);

		return delCount;
	}
}
