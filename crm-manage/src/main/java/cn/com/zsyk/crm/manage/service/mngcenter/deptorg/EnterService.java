package cn.com.zsyk.crm.manage.service.mngcenter.deptorg;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.zsyk.crm.common.dao.AbstractDao;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.manage.entity.SysDeptInfo;
import cn.com.zsyk.crm.manage.entity.SysEnterInfo;
import cn.com.zsyk.crm.manage.mapper.SysEnterInfoMapper;

@Service
@Transactional
public class EnterService {

	@Autowired
	private SysEnterInfoMapper mapper;
	@Autowired
	AbstractDao daoUtil;
	@Autowired
	DeptService deptService;

	/**
	 * 获得一条机构信息方法
	 * 
	 * @param enterCode
	 *            机构代码
	 * @return 机构信息
	 */
	public SysEnterInfo getOneEnter(String enterCode) {

		// 机构代码
		if (StringUtils.isEmpty(enterCode)) {
			throw new ServiceException("机构代码不能为空！");
		}

		SysEnterInfo enterInfo = new SysEnterInfo();

		enterInfo = mapper.selectByPrimaryKey(enterCode);

		return enterInfo;
	}

	/**
	 * 获得机构信息列表方法
	 * 
	 * @return 机构信息列表
	 */
	public List<SysEnterInfo> getAllEnter() {

		List<SysEnterInfo> enterList = new ArrayList<SysEnterInfo>();
		enterList = mapper.selectAll();
		return enterList;
	}

	/**
	 * 获得所有机构信息列表方法（带排序）
	 * 
	 * @return 机构信息列表
	 */
	public List<SysEnterInfo> getAllEntersOnOrder() {

		List<SysEnterInfo> enterList = new ArrayList<SysEnterInfo>();
		enterList = daoUtil.selectList("cn.com.zsyk.crm.manage.mapper.SysEnterInfoMapper.getAllEntersOnOrder");
		return enterList;
	}

	/**
	 * 获取所有顶级机构列表
	 * 
	 * @return 机构信息列表
	 */
	public List<SysEnterInfo> getAllSuperEnter() {

		List<SysEnterInfo> enterList = new ArrayList<SysEnterInfo>();
		enterList = daoUtil.selectList("cn.com.zsyk.crm.manage.mapper.SysEnterInfoMapper.getAllSuperEnter");
		return enterList;
	}

	/**
	 * 根据参数获得机构信息列表方法
	 * 
	 * @param enterInfo
	 *            机构信息表实体
	 * @return 机构信息列表
	 */
	public List<SysEnterInfo> getEntersByEntity(SysEnterInfo enterInfo) {

		List<SysEnterInfo> enterList = daoUtil
				.selectList("cn.com.zsyk.crm.manage.mapper.SysEnterInfoMapper.selectByEntity", enterInfo);

		if (enterList.equals(null)) {
			throw new ServiceException("机构信息不存在");
		}

		return enterList;

	}

	/**
	 * 新增一条机构信息方法
	 * 
	 * @param enterInfo
	 *            新增的机构信息
	 * @return 新增的机构信息
	 */
	public int addEnter(SysEnterInfo enterInfo) {

		// 入参校验
		// 机构代码
		if (StringUtils.isEmpty(enterInfo.getEnterCode())) {
			throw new ServiceException("机构代码不能为空！");
		}
		// 机构名称
		if (StringUtils.isEmpty(enterInfo.getEnterName())) {
			throw new ServiceException("机构名称不能为空！");
		}
		/*// 机构地址
		if (StringUtils.isEmpty(enterInfo.getEnterAddr())) {
			throw new ServiceException("机构地址不能为空！");
		}
		// 机构电话
		if (StringUtils.isEmpty(enterInfo.getEnterTel())) {
			throw new ServiceException("机构电话不能为空！");
		}*/

		// 查询要新增的机构信息是否已经存在
		SysEnterInfo extTest = mapper.selectByPrimaryKey(enterInfo.getEnterCode());

		// 机构已存在时报错
		if (extTest != null && "0".equals(extTest.getRecStat())) {
			throw new ServiceException(
					"机构信息已经存在：机构代码[" + enterInfo.getEnterCode() + "]，机构名称[" + enterInfo.getEnterName() + "]");
		}

		// 判断上级机构代码是否为空，若不为空则判断上级机构是否存在，不存在则报错
		if (StringUtils.isNotEmpty(enterInfo.getSuperEnterCode())
				|| StringUtils.isNotEmpty(enterInfo.getSuperEnter())) {
			// 查询要修改的机构信息是否存在
			SysEnterInfo superEnterInfo = mapper.selectByPrimaryKey(enterInfo.getSuperEnterCode());

			// 机构信息不存在时报错
			if (superEnterInfo == null) {
				throw new ServiceException("上级机构信息不存在：机构代码[" + enterInfo.getSuperEnterCode() + "]，机构名称["
						+ enterInfo.getSuperEnter() + "]");
			}
		}

		// 新增成功记录条数
		int addCount = 0;

		// 存在已逻辑删除的记录时更新，不存在时直接新增
		if (extTest != null && "1".equals(extTest.getRecStat())) {
			// 执行新增处理
			addCount = mapper.updateByPrimaryKey(enterInfo);
		} else {

			// 执行新增处理
			enterInfo.setRecStat("0");
			addCount = mapper.insert(enterInfo);
		}

		return addCount;
	}

	/**
	 * 修改一条机构信息方法
	 * 
	 * @param enterInfo
	 *            修改的机构信息
	 * @return 修改机构信息的数量，0为修改失败
	 */
	public int modEnter(SysEnterInfo enterInfo) {

		// 机构代码
		if (StringUtils.isEmpty(enterInfo.getEnterCode())) {
			throw new ServiceException("机构代码不能为空！");
		}
		// 机构名称
		if (StringUtils.isEmpty(enterInfo.getEnterName())) {
			throw new ServiceException("机构名称不能为空！");
		}
		/*// 机构地址
		if (StringUtils.isEmpty(enterInfo.getEnterAddr())) {
			throw new ServiceException("机构地址不能为空！");
		}
		// 机构电话
		if (StringUtils.isEmpty(enterInfo.getEnterTel())) {
			throw new ServiceException("机构电话不能为空！");
		}*/

		// 查询要修改的机构信息是否存在
		SysEnterInfo enterInfoBean = mapper.selectByPrimaryKey(enterInfo.getEnterCode());

		// 机构信息不存在时报错
		if (enterInfoBean == null) {
			throw new ServiceException(
					"机构信息不存在：机构代码[" + enterInfo.getEnterCode() + "]，机构名称[" + enterInfo.getEnterName() + "]");
		}
		int modCount=0;
		// 判断上级机构代码是否为空，若不为空则判断上级机构是否存在，不存在则报错
		if (StringUtils.isNotEmpty(enterInfo.getSuperEnterCode())
				|| StringUtils.isNotEmpty(enterInfo.getSuperEnter())) {
			// 查询要修改的机构信息是否存在
			SysEnterInfo superEnterInfo = mapper.selectByPrimaryKey(enterInfo.getSuperEnterCode());

			// 机构信息不存在时报错
			if (superEnterInfo == null) {
				throw new ServiceException("上级机构信息不存在：机构代码[" + enterInfo.getSuperEnterCode() + "]，机构名称["
						+ enterInfo.getSuperEnter() + "]");
			}
			// 执行更新处理
			enterInfo.setRecStat("0");
			modCount = mapper.updateByPrimaryKey(enterInfo);
		}

		return modCount;

	}

	/**
	 * 根据物理删除一条机构信息方法
	 * 
	 * @param enterCode
	 *            机构代码
	 * @return 删除的机构信息数量，0为删除失败
	 */
	public int delEnter(String enterCode) {

		// 机构代码
		if (StringUtils.isEmpty(enterCode)) {
			throw new ServiceException("机构代码不能为空！");
		}

		SysEnterInfo extTest = mapper.selectByPrimaryKey(enterCode);

		if (extTest == null) {
			throw new ServiceException("机构信息不存在：机构代码[" + enterCode + "]");
		}

		// 删除前判断机构下是否存在部门，若存在则不允许删除
		SysDeptInfo deptInfo = new SysDeptInfo();
		deptInfo.setEnterCode(enterCode);
		deptInfo.setRecStat("0");
		// 查询当前机构下是否还存在部门信息，若存在则不能删除该机构
		List<SysDeptInfo> deptInfoList = deptService.getDeptByEntity(deptInfo);
		if (deptInfoList != null && deptInfoList.size() > 0) {
			throw new ServiceException("机构[" + enterCode + "]下存在部门信息，不允许删除！");
		}

		int delCount = mapper.deleteByPrimaryKey(enterCode);

		return delCount;
	}

	/**
	 * 逻辑删除一条机构信息方法
	 * 
	 * @param enterInfo
	 *            需要无效化的机构信息
	 * @return 无效化机构信息的修改数量，0为修改失败
	 */
	public int delModEnter(SysEnterInfo enterInfo) {

		// 机构代码
		if (StringUtils.isEmpty(enterInfo.getEnterCode())) {
			throw new ServiceException("机构代码不能为空！");
		}
		// 机构名称
		if (StringUtils.isEmpty(enterInfo.getEnterName())) {
			throw new ServiceException("机构名称不能为空！");
		}

		// 查询要删除的记录是否存在
		SysEnterInfo enterInfoBean = mapper.selectByPrimaryKey(enterInfo.getEnterCode());

		// 若不存在则抛出异常
		if (enterInfoBean == null) {
			throw new ServiceException(
					"机构信息不存在：机构代码[" + enterInfo.getEnterCode() + "]，机构名称[" + enterInfo.getEnterName() + "]");
		}

		// 删除前判断机构下是否存在部门，若存在则不允许删除
		SysDeptInfo deptInfo = new SysDeptInfo();
		deptInfo.setEnterCode(enterInfo.getEnterCode());
		deptInfo.setRecStat("0");
		// 查询当前机构下是否还存在部门信息，若存在则不能删除该机构
		List<SysDeptInfo> deptInfoList = deptService.getDeptByEntity(deptInfo);
		if (deptInfoList != null && deptInfoList.size() > 0) {
			throw new ServiceException("机构[" + enterInfo.getEnterCode() + "]下存在部门信息，不允许删除！");
		}

		// 记录状态置为“1”
		enterInfoBean.setRecStat("1");

		// 执行删除
		int delModCount = mapper.updateByPrimaryKey(enterInfoBean);

		return delModCount;
	}

}
