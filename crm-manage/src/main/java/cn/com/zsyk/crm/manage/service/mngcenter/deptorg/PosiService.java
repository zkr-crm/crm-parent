package cn.com.zsyk.crm.manage.service.mngcenter.deptorg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.zsyk.crm.common.dao.AbstractDao;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.manage.entity.SysDeptInfo;
import cn.com.zsyk.crm.manage.entity.SysDeptPosi;
import cn.com.zsyk.crm.manage.entity.SysEnterInfo;
import cn.com.zsyk.crm.manage.entity.SysPosiInfo;
import cn.com.zsyk.crm.manage.entity.SysUserInfo;
import cn.com.zsyk.crm.manage.mapper.SysDeptInfoMapper;
import cn.com.zsyk.crm.manage.mapper.SysDeptPosiMapper;
import cn.com.zsyk.crm.manage.mapper.SysEnterInfoMapper;
import cn.com.zsyk.crm.manage.mapper.SysPosiInfoMapper;

@Service
@Transactional
public class PosiService {

	@Autowired
	private SysPosiInfoMapper posiInfoMapper;
	@Autowired
	private SysDeptInfoMapper deptInfoMapper;
	@Autowired
	private SysEnterInfoMapper enterInfoMapper;
	@Autowired
	private DeptService deptService;
	@Autowired
	private SysDeptPosiMapper deptPosiMapper;
	@Autowired
	AbstractDao dao;

	/**
	 * 查询一条岗位信息的方法
	 * 
	 * @param posiCode
	 *            岗位代码
	 * @param posiName
	 *            岗位名称
	 * @return 查询一条的岗位信息
	 */
	/*
	 * public SysPosiInfo getOnePosi(String posiCode, String posiName) {
	 * 
	 * // 岗位代码 if (StringUtils.isEmpty(posiCode)) { throw new
	 * ServiceException("岗位代码不能为空！"); } // 岗位名称 if (StringUtils.isEmpty(posiName)) {
	 * throw new ServiceException("岗位名称不能为空！"); }
	 * 
	 * SysPosiInfo posiInfo = posiInfoMapper.selectByPrimaryKey(posiCode, posiName);
	 * return posiInfo; }
	 */

	/**
	 * 查询所有岗位信息的方法
	 * 
	 * @return 查询所有岗位信息列表
	 */
	/*
	 * public List<SysPosiInfo> getAllPosi() { List<SysPosiInfo> posiList =
	 * posiInfoMapper.selectAll(); return posiList; }
	 */

	/**
	 * 根据参数查询所有岗位信息的方法
	 * 
	 * @param posiInfo
	 *            岗位信息表实体
	 * @return 查询所有岗位信息列表
	 */
	/*
	 * public List<SysPosiInfo> getPosiByEntity(SysPosiInfo posiInfo) {
	 * List<SysPosiInfo> posiList = new ArrayList<SysPosiInfo>();
	 * posiInfo.setRecStat("0"); posiList = dao.selectList(
	 * "cn.com.zsyk.crm.manage.mapper.SysPosiInfoMapper.selectByEntity", posiInfo);
	 * return posiList; }
	 */

	/**
	 * 新增一条岗位信息的方法
	 * 
	 * @param posiInfo
	 *            新增的某条岗位信息
	 * @return 新增岗位信息的数量，0为新增失败
	 */
	/*
	 * public int addPosi(SysPosiInfo posiInfo) {
	 * 
	 * // 企业信息均为空时，用部门信息查询企业信息 if (StringUtils.isEmpty(posiInfo.getEnterCode()) &&
	 * StringUtils.isEmpty(posiInfo.getEnterName())) { String deptCode =
	 * posiInfo.getDeptCode(); SysDeptInfo deptInfo =
	 * deptService.getOneDept(deptCode);
	 * posiInfo.setEnterCode(deptInfo.getEnterCode()); }
	 * 
	 * // 入参校验 // 企业代码 if (StringUtils.isEmpty(posiInfo.getEnterCode())) { throw new
	 * ServiceException("企业代码不能为空！"); } // 企业名称 if
	 * (StringUtils.isEmpty(posiInfo.getEnterName())) { throw new
	 * ServiceException("企业名称不能为空！"); } // 部门代码 if
	 * (StringUtils.isEmpty(posiInfo.getDeptCode())) { throw new
	 * ServiceException("部门代码不能为空！"); } // 部门名称 if
	 * (StringUtils.isEmpty(posiInfo.getDeptName())) { throw new
	 * ServiceException("部门名称不能为空！"); } // 岗位代码 if
	 * (StringUtils.isEmpty(posiInfo.getPosiCode())) { throw new
	 * ServiceException("岗位代码不能为空！"); } // 岗位名称 if
	 * (StringUtils.isEmpty(posiInfo.getPosiName())) { throw new
	 * ServiceException("岗位名称不能为空！"); }
	 * 
	 * // 判断新增的记录是否已存在 SysPosiInfo extTest =
	 * posiInfoMapper.selectByPrimaryKey(posiInfo.getPosiCode(),
	 * posiInfo.getPosiName()); if (extTest != null) { throw new ServiceException(
	 * "岗位信息已经存在：岗位代码[" + posiInfo.getPosiCode() + "]，岗位名称[" +
	 * posiInfo.getPosiName() + "]"); }
	 * 
	 * // 判断企业代码是否存在 SysEnterInfo enterInfo =
	 * enterInfoMapper.selectByPrimaryKey(posiInfo.getEnterCode()); if (enterInfo ==
	 * null) { throw new ServiceException( "部门所属企业信息不存在：企业代码[" +
	 * posiInfo.getEnterCode() + "]，企业名称[" + posiInfo.getEnterName() + "]"); }
	 * 
	 * // 判断部门代码是否存在 SysDeptInfo deptInfo =
	 * deptInfoMapper.selectByPrimaryKey(posiInfo.getDeptCode()); if (deptInfo ==
	 * null) { throw new ServiceException( "部门所属部门信息不存在：部门代码[" +
	 * posiInfo.getDeptCode() + "]，部门名称[" + posiInfo.getDeptName() + "]"); }
	 * 
	 * // 判断上级岗位代码是否为空，若不为空则判断上级岗位是否存在，不存在则报错 if
	 * (StringUtils.isNotEmpty(posiInfo.getSuperPosiCode()) ||
	 * StringUtils.isNotEmpty(posiInfo.getSuperPosi())) { // 查询要修改的岗位信息是否存在
	 * SysPosiInfo superPosiInfo =
	 * posiInfoMapper.selectByPrimaryKey(posiInfo.getSuperPosiCode(),
	 * posiInfo.getSuperPosi());
	 * 
	 * // 岗位信息不存在时报错 if (superPosiInfo == null) { throw new ServiceException(
	 * "上级岗位信息不存在：岗位代码[" + posiInfo.getSuperPosiCode() + "]，岗位名称[" +
	 * posiInfo.getSuperPosi() + "]"); } }
	 * 
	 * posiInfo.setRecStat("0"); int addCount = posiInfoMapper.insert(posiInfo);
	 * return addCount; }
	 */

	/**
	 * 修改一条岗位信息的方法
	 * 
	 * @param posiInfo
	 *            需要修改的岗位信息
	 * @return 修改的岗位信息数量，0为修改失败
	 */
	/*
	 * public int modPosi(SysPosiInfo posiInfo) { SysPosiInfo extTest =
	 * posiInfoMapper.selectByPrimaryKey(posiInfo.getPosiCode(),
	 * posiInfo.getPosiName()); if (extTest.equals(null)) { throw new
	 * ServiceException( "岗位信息不存在：岗位代码[" + posiInfo.getPosiCode() + "]，岗位名称[" +
	 * posiInfo.getPosiName() + "]"); } posiInfo.setRecStat("0"); int modCount =
	 * posiInfoMapper.updateByPrimaryKey(posiInfo); return modCount;
	 * 
	 * }
	 */

	/**
	 * 物理删除一条岗位信息的方法
	 * 
	 * @param posiCode
	 *            岗位代码
	 * @param posiName
	 *            岗位名称
	 * @return 物理删除成功的岗位信息数量，0为删除失败
	 */
	/*
	 * public int delPosi(String posiCode, String posiName) {
	 * 
	 * // 岗位代码 if (StringUtils.isEmpty(posiCode)) { throw new
	 * ServiceException("岗位代码不能为空！"); } // 岗位名称 if (StringUtils.isEmpty(posiName)) {
	 * throw new ServiceException("岗位名称不能为空！"); }
	 * 
	 * SysPosiInfo extTest = posiInfoMapper.selectByPrimaryKey(posiCode, posiName);
	 * if (extTest.equals(null)) { throw new ServiceException("岗位信息不存在：岗位代码[" +
	 * posiCode + "]，岗位名称[" + posiName + "]"); } int delCount =
	 * posiInfoMapper.deleteByPrimaryKey(posiCode, posiName); return delCount;
	 * 
	 * }
	 */

	/**
	 * 逻辑删除一条岗位信息的方法
	 * 
	 * @param posiInfo
	 *            需要无效化的岗位信息
	 * @return 无效化完成的岗位信息数量，0为逻辑删除失败
	 */
	/*
	 * public int delModPosi(SysPosiInfo posiInfo) { SysPosiInfo extTest =
	 * posiInfoMapper.selectByPrimaryKey(posiInfo.getPosiCode(),
	 * posiInfo.getPosiName()); if (extTest.equals(null)) { throw new
	 * ServiceException( "岗位信息不存在：岗位代码[" + posiInfo.getPosiCode() + "]，岗位名称[" +
	 * posiInfo.getPosiName() + "]"); } extTest.setRecStat("1"); int delModCount =
	 * posiInfoMapper.updateByPrimaryKey(extTest); return delModCount;
	 * 
	 * }
	 */
	/**
	 * 根据部门代码、 岗位代码获取部门下一条岗位信息
	 * 
	 * @param deptCode
	 *            部门代码
	 * @param posiCode
	 *            岗位代码
	 * @return
	 */
	public List<SysDeptPosi> getPosiByPosi(String deptCode, String posiCode) {

		List<SysDeptPosi> deptPosiList = new ArrayList<SysDeptPosi>();

		Map map = new HashMap();
		map.put("deptCode", deptCode);
		map.put("posiCode", posiCode);
		deptPosiList = dao.selectList("cn.com.zsyk.crm.manage.mapper.SysDeptPosiMapper.selectByPosi", map);
		return deptPosiList;

	}

	/**
	 * 根据部门代码获取部门下所有岗位信息
	 * 
	 * @param deptCode
	 *            部门代码
	 * @return
	 */
	public List<SysDeptPosi> getPosiByDept(String deptCode) {

		List<SysDeptPosi> deptPosiList = new ArrayList<SysDeptPosi>();

		Map map = new HashMap();
		map.put("deptCode", deptCode);
		deptPosiList = dao.selectList("cn.com.zsyk.crm.manage.mapper.SysDeptPosiMapper.selectByDept", map);
		return deptPosiList;

	}

	/**
	 * 更新部门岗位信息列表
	 * 
	 * @param deptCode
	 *            部门代码
	 * @param deptPosiList
	 *            部门岗位信息列表
	 * @return result 设置的部门岗位信息的条数
	 */
	public int updDeptPosi(String deptCode, List<SysDeptPosi> deptPosiList) {

		List<SysDeptPosi> deptPosiListTemp = getPosiByDept(deptCode);
		List<SysDeptPosi> tempList = new ArrayList<SysDeptPosi>();
		// 判断是否存在删除岗位，若有判断用户表中是否存在该岗位下的员工，若存在则不允许删除该岗位

		// 获取将要删除的部门岗位列表
		for (SysDeptPosi itemTemp : deptPosiListTemp) {

			boolean flg = false;

			for (SysDeptPosi item : deptPosiList) {
				if (item.getDeptCode().equals(itemTemp.getDeptCode())
						&& item.getPosiCode().equals(itemTemp.getPosiCode())
						&& item.getPosiName().equals(itemTemp.getPosiName())) {
					flg = true;
					break;
				}
			}
			if (flg == false) {
				tempList.add(itemTemp);
			}
		}

		// 判断将要删除的部门岗位列表下是否有员工存在
		if (tempList.size() > 0) {
			String posiNameStr = "";
			for (SysDeptPosi item : tempList) {

				Map map = new HashMap();
				map.put("deptCode", item.getDeptCode());
				map.put("posiCode", item.getPosiCode());
				List<SysUserInfo> userList = dao
						.selectList("cn.com.zsyk.crm.manage.mapper.SysUserInfoMapper.selectByEntity", map);

				if (userList != null && userList.size() > 0) {
					posiNameStr = posiNameStr + item.getPosiName() + ",";
				}
			}
			if (!"".equals(posiNameStr)) {
				posiNameStr = posiNameStr.substring(0, posiNameStr.length() - 1);
				throw new ServiceException("岗位下存在员工信息，不允许删除：岗位名称：[" + posiNameStr + "]!");
			}
		}

		// 根据部门代码删除所有所属部门的岗位信息
		Map map = new HashMap();
		map.put("deptCode", deptCode);
		dao.delete("cn.com.zsyk.crm.manage.mapper.SysDeptPosiMapper.deleteDeptPosiByDept", map);

		int result = 0;
		// 新增部门岗位信息
		for (SysDeptPosi item : deptPosiList) {
			item.setDeptPosi(item.getDeptCode() + item.getPosiCode());
			result += deptPosiMapper.insert(item);
		}

		return result;
	}
}
