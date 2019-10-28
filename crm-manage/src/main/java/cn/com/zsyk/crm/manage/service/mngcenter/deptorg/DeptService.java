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
import cn.com.zsyk.crm.manage.mapper.SysDeptInfoMapper;
import cn.com.zsyk.crm.manage.mapper.SysEnterInfoMapper;

@Service
@Transactional
public class DeptService {

	@Autowired
	private SysDeptInfoMapper deptInfoMapper;
	@Autowired
	private SysEnterInfoMapper enterInfoMapper;
	@Autowired
	AbstractDao dao;
	@Autowired
	PosiService posiService;

	/**
	 * 查询一条部门信息的方法
	 * 
	 * @param deptCode
	 *            部门代码
	 * @return 查询返回的部门信息
	 */
	public SysDeptInfo getOneDept(String deptCode) {

		// 部门代码
		if (StringUtils.isEmpty(deptCode)) {
			throw new ServiceException("部门代码不能为空！");
		}

		SysDeptInfo deptInfo = new SysDeptInfo();
		deptInfo = deptInfoMapper.selectByPrimaryKey(deptCode);
		return deptInfo;
	}

	/**
	 * 查询全部部门信息的方法
	 * 
	 * @return 查询返回的部门信息列表
	 */
	public List<SysDeptInfo> getAllDept() {
		List<SysDeptInfo> deptList = new ArrayList<SysDeptInfo>();
		deptList = deptInfoMapper.selectAll();
		return deptList;
	}

	/**
	 * 根据参数查询全部部门信息的方法
	 * 
	 * @param deptInfo
	 *            部门信息表实体
	 * @return 查询返回的部门信息列表
	 */
	public List<SysDeptInfo> getDeptByEntity(SysDeptInfo deptInfo) {
		List<SysDeptInfo> deptList = new ArrayList<SysDeptInfo>();
		deptList = dao.getSqlSessionTemplate()
				.selectList("cn.com.zsyk.crm.manage.mapper.SysDeptInfoMapper.selectByEntity", deptInfo);
		return deptList;
	}

	/**
	 * 新增一条部门信息的方法
	 * 
	 * @param deptInfo
	 *            新增的一条部门信息
	 * @return 新增完成的部门信息数量，0为新增失败
	 */
	public int addDept(SysDeptInfo deptInfo) {

		// 入参校验
		// 部门代码
		if (StringUtils.isEmpty(deptInfo.getDeptCode())) {
			throw new ServiceException("部门代码不能为空！");
		}
		// 部门名称
		if (StringUtils.isEmpty(deptInfo.getDeptName())) {
			throw new ServiceException("部门名称不能为空！");
		}
		// 企业代码
		if (StringUtils.isEmpty(deptInfo.getEnterCode())) {
			throw new ServiceException("企业代码不能为空！");
		}
		// 企业代码
		if (StringUtils.isEmpty(deptInfo.getEnterName())) {
			throw new ServiceException("企业代码不能为空！");
		}

		// 判断要新增的部门是否已存在，若存在则报错
		SysDeptInfo deptInfoBean = deptInfoMapper.selectByPrimaryKey(deptInfo.getDeptCode());
		if (deptInfoBean != null && "0".equals(deptInfoBean.getRecStat())) {
			throw new ServiceException(
					"部门信息已经存在：部门代码[" + deptInfo.getDeptCode() + "]，部门名称[" + deptInfo.getDeptName() + "]");
		}

		// 判断企业代码是否存在
		SysEnterInfo enterInfo = enterInfoMapper.selectByPrimaryKey(deptInfo.getEnterCode());
		if (enterInfo == null) {
			throw new ServiceException(
					"部门所属企业信息不存在：企业代码[" + deptInfo.getEnterCode() + "]，企业名称[" + deptInfo.getEnterName() + "]");
		}

		// 判断上级部门代码是否为空，若不为空则判断上级部门是否存在，不存在则报错
		if (StringUtils.isNotEmpty(deptInfo.getSuperDeptCode()) || StringUtils.isNotEmpty(deptInfo.getSuperDept())) {
			// 查询要修改的部门信息是否存在
			SysDeptInfo superDeptInfo = deptInfoMapper.selectByPrimaryKey(deptInfo.getSuperDeptCode());

			// 部门信息不存在时报错
			if (superDeptInfo == null) {
				throw new ServiceException(
						"上级部门信息不存在：部门代码[" + deptInfo.getSuperDeptCode() + "]，部门名称[" + deptInfo.getSuperDept() + "]");
			}
		}

		// 执行新增操作
		deptInfo.setRecStat("0");
		int addCount = deptInfoMapper.insert(deptInfo);

		return addCount;
	}

	/**
	 * 修改一条部门信息的方法
	 * 
	 * @param deptInfo
	 *            需要修改的一条部门信息
	 * @return 修改完成的部门信息的数量，0为修改失败
	 */
	public int modDept(SysDeptInfo deptInfo) {

		// 部门代码
		if (StringUtils.isEmpty(deptInfo.getDeptCode())) {
			throw new ServiceException("部门代码不能为空！");
		}
		// 部门名称
		if (StringUtils.isEmpty(deptInfo.getDeptName())) {
			throw new ServiceException("部门名称不能为空！");
		}
		// 企业代码
		if (StringUtils.isEmpty(deptInfo.getEnterCode())) {
			throw new ServiceException("企业代码不能为空！");
		}
		// 企业代码
		if (StringUtils.isEmpty(deptInfo.getEnterName())) {
			throw new ServiceException("企业代码不能为空！");
		}

		// 获取要修改的部门信息记录
		SysDeptInfo deptInfoBean = new SysDeptInfo();
		deptInfoBean = deptInfoMapper.selectByPrimaryKey(deptInfo.getDeptCode());

		// 判断要修改的记录是否存在，若不存在则抛出异常
		if (deptInfoBean == null) {
			throw new ServiceException(
					"部门信息不存在：部门代码[" + deptInfo.getDeptCode() + "]，部门名称[" + deptInfo.getDeptName() + "]");
		}

		// 判断企业代码是否存在
		SysEnterInfo enterInfo = enterInfoMapper.selectByPrimaryKey(deptInfo.getEnterCode());
		if (enterInfo == null) {
			throw new ServiceException(
					"部门所属企业信息不存在：企业代码[" + deptInfo.getEnterCode() + "]，企业名称[" + deptInfo.getEnterName() + "]");
		}

		// 判断上级部门代码是否为空，若不为空则判断上级部门是否存在，不存在则报错
		if (StringUtils.isNotEmpty(deptInfo.getSuperDeptCode()) || StringUtils.isNotEmpty(deptInfo.getSuperDept())) {
			// 查询要修改的部门信息是否存在
			SysDeptInfo superDeptInfo = deptInfoMapper.selectByPrimaryKey(deptInfo.getSuperDeptCode());

			// 部门信息不存在时报错
			if (superDeptInfo == null) {
				throw new ServiceException(
						"上级部门信息不存在：部门代码[" + deptInfo.getSuperDeptCode() + "]，部门名称[" + deptInfo.getSuperDept() + "]");
			}
		}

		// 执行更新操作
		deptInfo.setRecStat("0");
		int modCount = deptInfoMapper.updateByPrimaryKey(deptInfo);
		return modCount;
	}

	/**
	 * 物理删除一条部门信息的方法
	 * 
	 * @param deptCode
	 *            部门代码
	 * @param deptName
	 *            部门名称
	 * @return 物理删除完成的部门信息数量，0为删除失败
	 */
	public int delDept(String deptCode) {

		// 部门代码
		if (StringUtils.isEmpty(deptCode)) {
			throw new ServiceException("部门代码不能为空！");
		}

		// 判断要删除的记录是否存在，若不存在则抛出异常
		SysDeptInfo deptInfoBean = new SysDeptInfo();
		deptInfoBean = deptInfoMapper.selectByPrimaryKey(deptCode);
		if (deptInfoBean == null) {
			throw new ServiceException("部门信息不存在：部门代码[" + deptCode + "]");
		}

		// 查询当前部门下是否还存在部岗位信息，若存在则不能删除该岗位
		List<SysDeptPosi> deptPosiList = posiService.getPosiByDept(deptCode);
		if (deptPosiList != null && deptPosiList.size() > 0) {
			throw new ServiceException("部门[" + deptCode + "]下存在岗位信息，不允许删除！");
		}

		int delCount = deptInfoMapper.deleteByPrimaryKey(deptCode);
		return delCount;
	}

	/**
	 * 逻辑删除一条部门信息的方法
	 * 
	 * @param deptInfo
	 *            需要无效化的部门信息
	 * @return 成功无效化的信息数量，0为逻辑删除失败
	 */
	public int delModDept(SysDeptInfo deptInfo) {

		// 部门代码
		if (StringUtils.isEmpty(deptInfo.getDeptCode())) {
			throw new ServiceException("部门代码不能为空！");
		}
		// 部门名称
		if (StringUtils.isEmpty(deptInfo.getDeptName())) {
			throw new ServiceException("部门名称不能为空！");
		}

		// 判断要删除的记录是否存在，若不存在则抛出异常
		SysDeptInfo deptInfoBean = deptInfoMapper.selectByPrimaryKey(deptInfo.getDeptCode());
		if (deptInfoBean == null || deptInfoBean.getRecStat().equals("1")) {
			throw new ServiceException(
					"部门信息不存在：部门代码[" + deptInfo.getDeptCode() + "]，部门名称[" + deptInfo.getDeptName() + "]");
		}

		// 查询当前部门下是否还存在部岗位信息，若存在则不能删除该岗位
		List<SysDeptPosi> deptPosiList = posiService.getPosiByDept(deptInfo.getDeptCode());
		if (deptPosiList != null && deptPosiList.size() > 0) {
			throw new ServiceException("部门[" + deptInfo.getDeptCode() + "]下存在岗位信息，不允许删除！");
		}

		deptInfoBean.setRecStat("1");
		int modCount = deptInfoMapper.updateByPrimaryKey(deptInfoBean);
		return modCount;
	}

	/**
	 * 根据机构号查询机构下所有部门信息
	 * 
	 * @param enterCode
	 *            机构代码
	 * 
	 * @return 查询返回的部门信息列表
	 */
	public List<SysDeptInfo> getDeptsByEnter(String enterCode) {
		List<SysDeptInfo> deptList = new ArrayList<SysDeptInfo>();

		Map map = new HashMap();
		map.put("enterCode", enterCode);
		deptList = dao.selectList("cn.com.zsyk.crm.manage.mapper.SysDeptInfoMapper.getDeptUnderEnter", map);
		return deptList;
	}
}
