package cn.com.zsyk.crm.manage.service.mngcenter.user;

import cn.com.zsyk.crm.common.constant.ServiceType;
import cn.com.zsyk.crm.common.dao.AbstractDao;
import cn.com.zsyk.crm.common.dto.ContextContainer;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.RC4.RC4;
import cn.com.zsyk.crm.common.util.RestUtil;
import cn.com.zsyk.crm.common.util.cfca.CfcaEncryptUtils;
import cn.com.zsyk.crm.generator.EnumType;
import cn.com.zsyk.crm.manage.entity.SysUserInfo;
import cn.com.zsyk.crm.manage.mapper.SysUserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserMngService {

	@Autowired
	private SysUserInfoMapper userMapper;

	@Autowired
	private AbstractDao daoUtil;
	@Autowired
	private RestUtil restUtil;
	/**
	 * 获得一条用户信息的方法
	 * 
	 * @param userCode
	 *            用户ID
	 * @return 用户信息
	 */
	public SysUserInfo getOneUser(String userCode) {

		// 用户ID非空判断
		if (userCode == null || "".equals(userCode)) {
			throw new ServiceException("用户ID[" + userCode + "]不能为空！");
		}

		SysUserInfo userInfo = userMapper.selectByPrimaryKey(userCode);

		return userInfo;
	}

	/**
	 * 根据员工ID查询一条用户记录
	 * 
	 * @param employeeId
	 *            员工ID
	 * @return 用户信息
	 */
	public SysUserInfo getUserByEmployeeID(String employeeId) {

		// 用户ID非空判断
		if (employeeId == null || "".equals(employeeId)) {
			throw new ServiceException("员工ID[" + employeeId + "]不能为空！");
		}

		Map map = new HashMap();
		map.put("employeeId", employeeId);

		SysUserInfo userInfo = daoUtil.selectOne("cn.com.zsyk.crm.manage.mapper.SysUserInfoMapper.getUserByEmployeeID", map);

		return userInfo;
	}

	/**
	 * 获取所有用户信息的方法
	 * 
	 * @return 所有用户信息的列表
	 */
	public List<SysUserInfo> getAllUser() {

		List<SysUserInfo> lstUser = userMapper.selectAll();

		return lstUser;
	}

	/**
	 * 获取拥有某角色的所有用户
	 * 
	 * @return 所有用户信息的列表
	 */
	public List<SysUserInfo> getUserByRole(String roleCode) {

		Map map = new HashMap();
		map.put("roleCode", roleCode);
		List<SysUserInfo> lstUser = daoUtil
				.selectList("cn.com.zsyk.crm.manage.mapper.SysUserInfoMapper.selectUserByRole", map);

		return lstUser;
	}

	/**
	 * 根据入参对象获取所有用户信息的方法
	 * 
	 * @return 所有用户信息的列表
	 */
	public List<SysUserInfo> getUsersByEntity(SysUserInfo record) {

		List<SysUserInfo> lstUser = daoUtil.selectList("cn.com.zsyk.crm.manage.mapper.SysUserInfoMapper.selectByEntity",
				record);
		return lstUser;
	}

	/**
	 * 根据入参对象获取所有用户信息商机的方法
	 * 
	 * @return 所有用户信息的列表
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<SysUserInfo> getUsersBusiOppByEntity(SysUserInfo record) {

		List<SysUserInfo> lstUser = daoUtil.selectList("cn.com.zsyk.crm.manage.mapper.SysUserInfoMapper.selectByEntity",
				record);
		Result result = restUtil.getForObject(ServiceType.OCRM, "/crm/ocrm/busiOpp/followUpBusiOppQtyList",Result.class);
		if (result != null) {
			List<LinkedHashMap> list = (List<LinkedHashMap>)result.getData();
			if (list != null) {
				for (SysUserInfo userInfo : lstUser) {
					for(LinkedHashMap o : list) {
						// userName
						String custAgent = (String)o.get("custAgent");
						// 跟进商机数量
						int followUpBusiOppQty = (Integer)o.get("followUpBusiOppQty");
						if (!userInfo.getUserId().equals(custAgent)) {
							continue;
						}
						userInfo.setFollowUpBusiOppQty(followUpBusiOppQty);
					}
				}
			}

		}
		return lstUser;
	}
	/**
	 * 根据入参对象获取所有用户信息商机的方法
	 *
	 * @return 所有用户信息的列表
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<SysUserInfo> getUsersByEnterCodes(String codes) {

		String [] enterList=codes.split(",");
		Map map = new HashMap();
		map.put("enterList", enterList);

		List<SysUserInfo> userInfoListTemp = daoUtil
				.selectList("cn.com.zsyk.crm.manage.mapper.SysUserInfoMapper.selectEmployeeByDataAuth", map);

		return userInfoListTemp;
	}
	/**
	 * 新增一条用户信息
	 * 
	 * @param userCode
	 *            用户ID
	 * @param username
	 *            用户名称
	 * @return 新增成功的记录条数
	 */
	public int addUser(SysUserInfo userInfo) {

		if (userInfo == null) {
			throw new ServiceException("输入信息不能为空！");
		}

		if (StringUtils.isEmpty(userInfo.getUserId())) {
			throw new ServiceException("用户代码不能为空！");
		}
		if (StringUtils.isEmpty(userInfo.getPassword())) {
			throw new ServiceException("用户密码不能为空！");
		}
		if (StringUtils.isEmpty(userInfo.getEmployeeId())) {
			throw new ServiceException("员工编号不能为空！");
		}
		if (StringUtils.isEmpty(userInfo.getEnterCode())) {
			throw new ServiceException("机构代码不能为空！");
		}
		/*if (StringUtils.isEmpty(userInfo.getDeptCode())) {
			throw new ServiceException("部门代码不能为空！");
		}*/
		/*if (StringUtils.isEmpty(userInfo.getPosiCode())) {
			throw new ServiceException("岗位代码不能为空！");
		}*/
		SysUserInfo employee = getUserByEmployeeID(userInfo.getEmployeeId());
		if(employee!=null){
			throw new ServiceException("此员工编号已存在系统用户：用户ID[" + employee.getUserId() + "]，用户名称[" + employee.getUserName() + "]");
		}
		// 存在判断
		SysUserInfo extTest = this.getOneUser(userInfo.getUserId());
		if (extTest != null) {
			throw new ServiceException(
					"用户信息已存在：用户ID[" + userInfo.getUserId() + "]，用户名称[" + userInfo.getUserName() + "]");
		}
		//userInfo.setPassword(MD5Util.getMD5(userInfo.getPassword()));
		userInfo.setUserStat("0");
		userInfo.setRecStat("0");
		userInfo.setDeptPosi(userInfo.getDeptCode() + "_" + userInfo.getPosiCode());

		int addCount = userMapper.insert(userInfo);

		return addCount;
	}

	/**
	 * 修改某条用户信息的方法
	 * 
	 * @param userInfo
	 *            需要修改的用户信息
	 * @return 修改成功的条目数，0为失败
	 */
	public int modUser(SysUserInfo userInfo) {

		if (userInfo == null) {
			throw new ServiceException("输入信息不能为空！");
		}

		if (StringUtils.isEmpty(userInfo.getUserId())) {
			throw new ServiceException("用户代码不能为空！");
		}
		if (StringUtils.isEmpty(userInfo.getPassword())) {
			throw new ServiceException("用户密码不能为空！");
		}
		if (StringUtils.isEmpty(userInfo.getEmployeeId())) {
			throw new ServiceException("员工编号不能为空！");
		}
		if (StringUtils.isEmpty(userInfo.getEnterCode())) {
			throw new ServiceException("机构代码不能为空！");
		}
		/*if (StringUtils.isEmpty(userInfo.getDeptCode())) {
			throw new ServiceException("机构代码不能为空！");
		}
		if (StringUtils.isEmpty(userInfo.getPosiCode())) {
			throw new ServiceException("岗位代码不能为空！");
		}*/
		//判断员工编号是否已存在
		SysUserInfo employee = getUserByEmployeeID(userInfo.getEmployeeId());
		if(employee!=null){
			if(!userInfo.getUserId().equals(employee.getUserId())){
				throw new ServiceException("此员工编号已存在系统用户！");
			}
		}
		// 存在判断
		SysUserInfo extTest = this.getOneUser(userInfo.getUserId());
		if (extTest == null) {
			throw new ServiceException(
					"用户信息不存在：用户ID[" + userInfo.getUserId() + "]，用户名称[" + userInfo.getUserName() + "]");
		}

		extTest.setUserName(userInfo.getUserName());
		extTest.setPassword(userInfo.getPassword());
		extTest.setEmployeeId(userInfo.getEmployeeId());
		extTest.setSex(userInfo.getSex());
		extTest.setUserStat(userInfo.getUserStat());
		extTest.setDeptCode(userInfo.getDeptCode());
		extTest.setDeptName(userInfo.getDeptName());
		extTest.setEnterCode(userInfo.getEnterCode());
		extTest.setEnterName(userInfo.getEnterName());
		extTest.setPosiCode(userInfo.getPosiCode());
		extTest.setPosiName(userInfo.getPosiName());
		extTest.setTelphone(userInfo.getTelphone());
		extTest.setEmail(userInfo.getEmail());
		extTest.setDeptPosi(extTest.getDeptCode() + "_" + extTest.getPosiCode());

		int modCount = userMapper.updateByPrimaryKey(extTest);

		return modCount;
	}

	/**
	 * 修改用户角色信息的方法
	 * 
	 * @param userInfo
	 *            需要修改的用户信息
	 * @return 修改成功的条目数，0为失败
	 */
	public int modUserRole(SysUserInfo userInfo) {

		if (userInfo == null) {
			throw new ServiceException("输入信息不能为空！");
		}

		// 存在判断
		SysUserInfo extTest = this.getOneUser(userInfo.getUserId());
		if (extTest == null) {
			throw new ServiceException(
					"用户信息不存在：用户ID[" + userInfo.getUserId() + "]，用户名称[" + userInfo.getUserName() + "]");
		}

		extTest.setRoleCode(userInfo.getRoleCode());

		int modCount = userMapper.updateByPrimaryKey(extTest);

		return modCount;
	}

	/**
	 * 根据主键物理删除某用户信息的方法
	 * 
	 * @param userCode
	 *            用户ID
	 * @return
	 */
	public int delUser(String userCode) {

		// 用户ID非空判断
		if (userCode == null || "".equals(userCode)) {
			throw new ServiceException("用户ID[" + userCode + "]不能为空！");
		}

		// 存在判断
		SysUserInfo extTest = this.getOneUser(userCode);
		if (extTest == null) {
			throw new ServiceException("用户信息不存在：用户ID[" + userCode + "]");
		}

		int delCount = userMapper.deleteByPrimaryKey(userCode);

		return delCount;
	}

	/**
	 * 逻辑删除，即将用户信息置为无效化的方法
	 * 
	 * @param userInfo
	 *            需要无效化的用户信息
	 * @return 无效化修改完成的用户信息数量，0为失败
	 */
	public int delModUser(SysUserInfo userInfo) {

		// 存在判断
		SysUserInfo extTest = this.getOneUser(userInfo.getUserId());

		if (extTest == null) {
			throw new ServiceException(
					"用户信息不存在：用户ID[" + userInfo.getUserId() + "]，用户名称[" + userInfo.getUserName() + "]");
		}

		// 校验删除的用户是否为当前用户
		if (ContextContainer.getContext().getUserId().equals(userInfo.getUserId())) {
			throw new ServiceException("删除的用户不能为当前登录用户：用户ID[" + userInfo.getUserId() + "]");
		}
		extTest.setUserStat("1");
		extTest.setRecStat("1");
		int modCount = userMapper.updateByPrimaryKey(extTest);

		return modCount;
	}

	/**
	 * 用户登录校验
	 * 
	 * @param userInfo
	 *            需要无效化的用户信息
	 * @return 无效化修改完成的用户信息数量，0为失败
	 */
	public int userLogin(SysUserInfo userInfo) {

		// 存在判断
		SysUserInfo userInfoBean = this.getOneUser(userInfo.getUserId());

		if (userInfoBean == null) {
			throw new ServiceException(
					"用户信息不存在：用户ID[" + userInfo.getUserId() + "]，用户名称[" + userInfo.getUserName() + "]");
		}

		userInfoBean.setRecStat(EnumType.RecStat.delete.value);
		int modCount = userMapper.updateByPrimaryKey(userInfoBean);

		return modCount;
	}

	/**
	 * 用户登录校验
	 * 
	 * @param userInfo
	 *            需要无效化的用户信息
	 * @return true：校验成功，false：校验失败
	 */
	public SysUserInfo userLoginChk(SysUserInfo userInfo) {
		// 存在判断
		SysUserInfo queryUser = this.getOneUser(userInfo.getUserId());

		if (queryUser == null) {
			//throw new ServiceException("用户不存在：用户ID[" + userInfo.getUserId() + "]");
			throw new ServiceException("该用户无使用权限!");
		}
		if (!(userInfo.getUserId().equals(queryUser.getUserId())
				//&& CfcaEncryptUtils.isMatched(userInfo.getPassword(),queryUser.getPassword(),13))) {
				&& RC4.isMatched(userInfo.getPassword(),queryUser.getPassword(),"CRM"))) {
			throw new ServiceException("用户或密码不正确！");
		}

		if (EnumType.UserStat.turnover.value.equals(queryUser.getUserStat())) {
			throw new ServiceException("用户已经注销，无法登陆！");
		}

		return queryUser;
	}

	/**
	 * 修改用户密码的方法
	 * 
	 * @param userInfo
	 *            需要修改的用户信息
	 * @return 修改成功的条目数，0为失败
	 */
	public int modPassword(SysUserInfo userInfo) {

		if (userInfo == null) {
			throw new ServiceException("输入信息不能为空！");
		}
		// 存在判断
		SysUserInfo extTest = this.getOneUser(userInfo.getUserId());
		if (extTest == null) {
			throw new ServiceException(
					"用户信息不存在：用户ID[" + userInfo.getUserId() + "]，用户名称[" + userInfo.getUserName() + "]");
		}

		extTest.setPassword(userInfo.getPassword());
		int modCount = userMapper.updateByPrimaryKey(extTest);

		return modCount;
	}
}
