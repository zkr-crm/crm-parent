package cn.com.zsyk.crm.manage.service.mngcenter.auth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.zsyk.crm.common.dao.AbstractDao;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.manage.entity.SysDeptInfo;
import cn.com.zsyk.crm.manage.entity.SysDeptPosi;
import cn.com.zsyk.crm.manage.entity.SysEnterInfo;
import cn.com.zsyk.crm.manage.entity.SysRoleEnter;
import cn.com.zsyk.crm.manage.entity.SysTreeNode;
import cn.com.zsyk.crm.manage.mapper.SysDeptPosiMapper;
import cn.com.zsyk.crm.manage.mapper.SysRoleEnterMapper;
import cn.com.zsyk.crm.manage.service.mngcenter.deptorg.DeptService;
import cn.com.zsyk.crm.manage.service.mngcenter.deptorg.EnterService;
import cn.com.zsyk.crm.generator.EnumType;

/**
 * @author
 *
 */
@Service
@Transactional
public class EnterAuthService {

	// 角色机构权限表
	@Autowired
	SysRoleEnterMapper roleEnterMapper;
	// dao层工具类
	@Autowired
	private AbstractDao daoUtil;
	// 机构
	@Autowired
	EnterService enterService;
	// 部门
	@Autowired
	DeptService deptService;
	// 岗位
	@Autowired
	SysDeptPosiMapper deptPosiMapper;

	/**
	 * 获取角色的人事架构根节点列表（机构代码）
	 * 
	 * @param roleCode
	 *            角色代码
	 * @return 用户代码列表
	 */
	public List<SysRoleEnter> getRoleEnterByRole(String roleCode) {

		Map map = new HashMap();
		map.put("roleCode", roleCode);
		List<SysRoleEnter> list = daoUtil.selectList("cn.com.zsyk.crm.manage.mapper.SysRoleEnterMapper.selectByRole",
				map);
		return list;
	}

	/**
	 * 更新角色的人事架构根节点列表（机构代码）
	 * 
	 * @param roleCode
	 *            角色代码
	 * @return 用户代码列表
	 */
	public int updateRoleEnter(List<SysRoleEnter> roleEnterList) {

		int result = 0;
		if (roleEnterList.size() > 0) {
			// 删除所有角色机构权限记录
			deleteByRole(roleEnterList.get(0).getRoleCode());

			for (SysRoleEnter item : roleEnterList) {
				// 增加角色机构权限记录
				result += addRoleEnter(item);
			}
		}

		// 返回新增条数
		return result;
	}

	/**
	 * 新增角色的人事架构根节点列表（机构代码）
	 * 
	 * @param roleCode
	 *            角色代码
	 * @return 用户代码列表
	 */
	private int addRoleEnter(SysRoleEnter roleEnter) {

		int result = roleEnterMapper.insert(roleEnter);

		return result;
	}

	/**
	 * 删除角色的人事架构根节点列表（机构代码）
	 * 
	 * @param roleCode
	 *            角色代码
	 * @return 用户代码列表
	 */
	public int deleteByRole(String roleCode) {

		Map map = new HashMap();
		map.put("roleCode", roleCode);
		int result = daoUtil.delete("cn.com.zsyk.crm.manage.mapper.SysRoleEnterMapper.deleteByRole", map);
		return 0;
	}

	/**
	 * 获取机构代码下的人事架构树
	 * 
	 * @param enterCode
	 *            机构代码
	 * 
	 * @return List<SysTreeNode> 树节点对象列表
	 */
	public List<SysTreeNode> getRoleEnterTree(String enterCode) {

		List<SysTreeNode> treeNodeList = new ArrayList<SysTreeNode>();

		int id = 0;

		// 获取根节点
		SysEnterInfo enterInfo = enterService.getOneEnter(enterCode);
		SysTreeNode treeNode = new SysTreeNode();
		treeNode.setId(enterInfo.getEnterCode());
		treeNode.setParent("#");
		treeNode.setType(EnumType.Organization.enter.value);
		treeNode.setText(enterInfo.getEnterName());
		treeNodeList.add(treeNode);

		// 获取所有下属机构节点
		List<SysEnterInfo> enterList = new ArrayList<SysEnterInfo>();
		enterList = getAllUnderEnter(enterCode, enterList);
		for (SysEnterInfo item : enterList) {
			// 树节点
			SysTreeNode node = new SysTreeNode();

			node.setId(item.getEnterCode());
			node.setParent(item.getSuperEnterCode());
			node.setType(EnumType.Organization.enter.value);
			node.setText(item.getEnterName());
			treeNodeList.add(node);
		}

		// 添加跟节点机构信息到机构列表中
		enterList.add(enterInfo);

		List<SysDeptInfo> deptList = new ArrayList<SysDeptInfo>();
		// 获取所有机构下的所有下属部门
		for (SysEnterInfo item : enterList) {

			// 获取机构所有下属部门
			List<SysDeptInfo> deptListTemp = getDeptUnderEnter(item.getEnterCode());
			deptList.addAll(deptListTemp);
		}

		// 将所有部门加入treeNodeList中
		for (SysDeptInfo item : deptList) {
			// 树节点
			SysTreeNode node = new SysTreeNode();

			if ("".equals(item.getSuperDeptCode())) {
				node.setId(item.getDeptCode());
				node.setParent(item.getEnterCode());
				node.setType(EnumType.Organization.dept.value);
				node.setText(item.getDeptName());
				treeNodeList.add(node);
			} else {
				node.setId(item.getDeptCode());
				node.setParent(item.getSuperDeptCode());
				node.setType(EnumType.Organization.dept.value);
				node.setText(item.getDeptName());
				treeNodeList.add(node);
			}
		}

		List<SysDeptPosi> deptPosiList = new ArrayList<SysDeptPosi>();

		// 获取所有部门下的岗位列表
		for (SysDeptInfo item : deptList) {
			Map map = new HashMap();
			map.put("deptCode", item.getDeptCode());
			List<SysDeptPosi> deptPosiListTemp = daoUtil
					.selectList("cn.com.zsyk.crm.manage.mapper.SysDeptPosiMapper.selectByDept", map);
			deptPosiList.addAll(deptPosiListTemp);
		}

		// 将所有岗位加入treeNodeList中
		for (SysDeptPosi item : deptPosiList) {
			// 树节点
			SysTreeNode node = new SysTreeNode();

			node.setId(item.getPosiCode());
			node.setParent(item.getDeptCode());
			node.setType(EnumType.Organization.posi.value);
			node.setText(item.getPosiName());
			treeNodeList.add(node);
		}

		return treeNodeList;
	}

	/**
	 * 获取机构代码下的截止到部门的树
	 * 
	 * @param enterCode
	 *            机构代码
	 * 
	 * @return List<SysTreeNode> 树节点对象列表
	 */
	public List<SysTreeNode> getRoleDeptTree(String enterCode) {

		List<SysTreeNode> treeNodeList = new ArrayList<SysTreeNode>();

		// 获取根节点
		SysEnterInfo enterInfo = enterService.getOneEnter(enterCode);
		SysTreeNode treeNode = new SysTreeNode();
		treeNode.setId(enterInfo.getEnterCode());
		treeNode.setParent("#");
		treeNode.setType(EnumType.Organization.enter.value);
		treeNode.setText(enterInfo.getEnterName());
		treeNodeList.add(treeNode);

		// 获取所有下属机构节点
		List<SysEnterInfo> enterList = new ArrayList<SysEnterInfo>();
		enterList = getAllUnderEnter(enterCode, enterList);
		for (SysEnterInfo item : enterList) {
			// 树节点
			SysTreeNode node = new SysTreeNode();

			node.setId(item.getEnterCode());
			node.setParent(item.getSuperEnterCode());
			node.setType(EnumType.Organization.enter.value);
			node.setText(item.getEnterName());
			treeNodeList.add(node);
		}

		// 添加跟节点机构信息到机构列表中
		enterList.add(enterInfo);

		List<SysDeptInfo> deptList = new ArrayList<SysDeptInfo>();
		// 获取所有机构下的所有下属部门
		for (SysEnterInfo item : enterList) {

			// 获取机构所有下属部门
			List<SysDeptInfo> deptListTemp = getDeptUnderEnter(item.getEnterCode());
			deptList.addAll(deptListTemp);
		}

		// 将所有部门加入treeNodeList中
		for (SysDeptInfo item : deptList) {
			// 树节点
			SysTreeNode node = new SysTreeNode();

			if ("".equals(item.getSuperDeptCode())) {
				node.setId(item.getDeptCode());
				node.setParent(item.getEnterCode());
				node.setType(EnumType.Organization.dept.value);
				node.setText(item.getDeptName());
				treeNodeList.add(node);
			} else {
				node.setId(item.getDeptCode());
				node.setParent(item.getSuperDeptCode());
				node.setType(EnumType.Organization.dept.value);
				node.setText(item.getDeptName());
				treeNodeList.add(node);
			}
		}

		return treeNodeList;
	}

	/**
	 * 获取机构树
	 * 
	 * @param enterCode
	 *            机构代码
	 * 
	 * @return List<SysTreeNode> 树节点对象列表
	 */
	public List<SysTreeNode> getAllEnterTree(String enterCode) {

		List<SysTreeNode> treeNodeList = new ArrayList<SysTreeNode>();

		SysEnterInfo enterInfo = enterService.getOneEnter(enterCode);

		// 获取机构所有下属机构
		List<SysEnterInfo> enterList = new ArrayList<SysEnterInfo>();
		getAllUnderEnter(enterCode, enterList);

		// 添加根节点
		enterList.add(enterInfo);

		for (SysEnterInfo item : enterList) {
			// 树节点
			SysTreeNode node = new SysTreeNode();

			node.setId(item.getEnterCode());
			node.setParent(item.getSuperEnterCode());
			node.setType(EnumType.Organization.enter.value);
			node.setText(item.getEnterName());
			treeNodeList.add(node);
		}

		return treeNodeList;
	}

	/**
	 * 根据机构代码获取机构所有下属机构信息(不包括当前机构信息)
	 * 
	 * @param enterCode
	 * @param enterList
	 * @return
	 */
	public List<SysEnterInfo> getAllUnderEnter(String enterCode, List<SysEnterInfo> enterList) {

		List<SysEnterInfo> enterListTemp = new ArrayList<SysEnterInfo>();
		Map map = new HashMap();
		map.put("enterCode", enterCode);
		enterListTemp = daoUtil.selectList("cn.com.zsyk.crm.manage.mapper.SysEnterInfoMapper.getUnderEnter", map);

		if (enterListTemp != null && enterListTemp.size() > 0) {
			enterList.addAll(enterListTemp);
		}

		for (SysEnterInfo item : enterListTemp) {
			getAllUnderEnter(item.getEnterCode(), enterList);
		}

		return enterList;
	}
	/**
	 * 根据机构代码获取机构所有下属机构信息
	 *
	 * @param enterCode
	 * @param enterList
	 * @return
	 */
	public List<SysEnterInfo> getAllUnderEnterAll(String enterCode, List<SysEnterInfo> enterList) {

		List<SysEnterInfo> enterListTemp = new ArrayList<SysEnterInfo>();
		Map map = new HashMap();
		map.put("enterCode", enterCode);
		enterListTemp = daoUtil.selectList("cn.com.zsyk.crm.manage.mapper.SysEnterInfoMapper.getUnderEnterAll", map);

		if (enterListTemp != null && enterListTemp.size() > 0) {
			enterList.addAll(enterListTemp);
		}

		for (SysEnterInfo item : enterListTemp) {
			getAllUnderEnter(item.getEnterCode(), enterList);
		}

		return enterList;
	}

	/**
	 * 根据机构代码获取所有下属部门
	 * 
	 * @param deptCode
	 * @param deptList
	 * @return
	 */
	private List<SysDeptInfo> getDeptUnderEnter(String enterCode) {

		Map map = new HashMap();
		map.put("enterCode", enterCode);
		List<SysDeptInfo> deptList = daoUtil
				.selectList("cn.com.zsyk.crm.manage.mapper.SysDeptInfoMapper.getDeptUnderEnter", map);

		return deptList;
	}

	/**
	 * 根据部门代码获取部门所有下属部门信息(不包括当前部门)
	 * 
	 * @param deptCode
	 * @param deptList
	 * @return
	 */
	public List<SysDeptInfo> getAllUnderDept(String deptCode, List<SysDeptInfo> deptList) {

		List<SysDeptInfo> deptListTemp = new ArrayList<SysDeptInfo>();
		Map map = new HashMap();
		map.put("deptCode", deptCode);
		deptListTemp = daoUtil.selectList("cn.com.zsyk.crm.manage.mapper.SysDeptInfoMapper.getUnderDept", map);

		if (deptListTemp != null && deptListTemp.size() > 0) {
			deptList.addAll(deptListTemp);
		}

		for (SysDeptInfo item : deptListTemp) {
			getAllUnderDept(item.getDeptCode(), deptList);
		}

		return deptList;
	}

}
