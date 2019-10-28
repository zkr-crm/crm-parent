package cn.com.zsyk.crm.manage.service.mngcenter.auth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import cn.com.zsyk.crm.common.dao.AbstractDao;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.generator.EnumType;
import cn.com.zsyk.crm.generator.EnumType.Organization;
import cn.com.zsyk.crm.manage.bom.SysDataAuthInfo;
import cn.com.zsyk.crm.manage.bom.SysRoleDataDetail;
import cn.com.zsyk.crm.manage.bom.SysRuleTypeBean;
import cn.com.zsyk.crm.manage.entity.SysDataOption;
import cn.com.zsyk.crm.manage.entity.SysDataRule;
import cn.com.zsyk.crm.manage.entity.SysDeptInfo;
import cn.com.zsyk.crm.manage.entity.SysDeptPosi;
import cn.com.zsyk.crm.manage.entity.SysEnterInfo;
import cn.com.zsyk.crm.manage.entity.SysRoleData;
import cn.com.zsyk.crm.manage.entity.SysRuleType;
import cn.com.zsyk.crm.manage.entity.SysUserInfo;
import cn.com.zsyk.crm.manage.mapper.SysDataOptionMapper;
import cn.com.zsyk.crm.manage.mapper.SysDataRuleMapper;
import cn.com.zsyk.crm.manage.mapper.SysRoleDataMapper;
import cn.com.zsyk.crm.manage.mapper.SysRuleTypeMapper;
import cn.com.zsyk.crm.manage.service.mngcenter.deptorg.DeptService;
import cn.com.zsyk.crm.manage.service.mngcenter.deptorg.EnterService;
import cn.com.zsyk.crm.manage.service.mngcenter.deptorg.PosiService;
import cn.com.zsyk.crm.manage.service.mngcenter.user.UserMngService;

@Service
@Transactional
public class DataAuthService {

	// 角色数据规则表
	@Autowired
	SysDataRuleMapper dataRuleMapper;
	// 角色数据选项表
	@Autowired
	SysDataOptionMapper dataOptionMapper;
	// 角色数据权限表
	@Autowired
	SysRoleDataMapper roleDataMapper;
	// 规则类别表
	@Autowired
	SysRuleTypeMapper ruleTypeMapper;
	@Autowired
	AbstractDao abstractDao;
	@Autowired
	UserMngService userMngService;
	@Autowired
	EnterAuthService enterAuthService;
	@Autowired
	DeptService deptService;
	@Autowired
	EnterService enterService;
	@Autowired
	PosiService posiService;

	private final String IS_RULE_SYMBOL_IN = "in"; // 匹配规则符号
	private final String IS_RULE_SYMBOL_EQUAL = "=";// 匹配规则符号
	private final String RULE_DEPT_LIST_CODE = "1001"; // in部门列表
	private final String RULE_DEPT_CODE = "1002"; // 等于登录人部门
	private final String RULE_POSI_LIST_CODE = "1003"; // in岗位列表
	private final String RULE_POSI_CODE = "1004"; // 等于登录人岗位
	private final String RULE_USER_CODE = "1005"; // 等于登录人
	private final String RULE_ENTER_LIST_CODE = "1006"; // in机构列表
	private final String RULE_ENTER_CODE = "1007"; // 等于登录人机构

	/**
	 * 获取所有需要控制数据权限的表名
	 * 
	 * @return 权限控制表列表
	 */
	public List<SysDataOption> getTableOptions() {

		List<SysDataOption> dataOptionList = abstractDao
				.selectList("cn.com.zsyk.crm.manage.mapper.SysDataOptionMapper.selectAllTable");

		return dataOptionList;
	}

	/**
	 * 获取所有角色数据规则类别
	 *
	 * @return 角色数据规则表
	 */
	public List<SysDataRule> getSysDataRuleList() {
		return dataRuleMapper.selectAll();
	}

	/**
	 * 根据表名获取该表的匹配字段
	 * 
	 * @param tableCode
	 *            表代码
	 * @return 权限控制表列表
	 */
	public List<SysDataOption> getFieldByTable(String tableCode) {

		Map map = new HashMap();
		map.put("tableCode", tableCode);
		List<SysDataOption> dataOptionList = abstractDao
				.selectList("cn.com.zsyk.crm.manage.mapper.SysDataOptionMapper.selectFieldByTable", map);

		return dataOptionList;
	}

	/**
	 * 根据表名、字段名获取数据权限规则可选类别
	 * 
	 * @param tableName
	 *            角色代码
	 * @param tableField
	 *            表名
	 * @return 匹配规则类型列表
	 */
	public List<SysRuleTypeBean> getRuleType(String tableCode, String tableField) {

		SysRuleType ruleType = new SysRuleType();
		ruleType.setTableCode(tableCode);
		ruleType.setFieldCode(tableField);
		List<SysRuleTypeBean> ruleTypeList = abstractDao
				.selectList("cn.com.zsyk.crm.manage.mapper.SysRuleTypeMapper.selectRuleType", ruleType);

		return ruleTypeList;
	}

	/**
	 * 根据角色表名查询角色数据权限
	 * 
	 * @param roleCode
	 *            角色代码
	 * @param tableCode
	 *            表代码
	 * 
	 * @return 角色数据权限列表
	 */
	public List<SysDataAuthInfo> getRoleData(String roleCode, String tableCode) {

		Map map = new HashMap();
		map.put("roleCode", roleCode);
		map.put("tableCode", tableCode);

		List<SysDataAuthInfo> dataAuthList = abstractDao
				.selectList("cn.com.zsyk.crm.manage.mapper.SysRoleDataMapper.selectAuthByRoleAndTable", map);

		return dataAuthList;
	}

	/**
	 * 根据角色表名查询角色数据权限
	 *
	 * @param roleCode
	 *            角色代码
	 * @param tableCode
	 *            表代码
	 * @param fieldCode
	 *            fieldCode
	 *
	 * @return 角色数据权限列表
	 */
	public List<SysRoleData> getRoleDataByField(String roleCode, String tableCode, String fieldCode) {
		Map map = new HashMap();
		map.put("roleCode", roleCode);
		map.put("tableCode", tableCode);
		map.put("fieldCode", fieldCode);
		List<SysRoleData> roleDataList = abstractDao
				.selectList("cn.com.zsyk.crm.manage.mapper.SysRoleDataMapper.selectByFieldCode", map);

		// 档匹配类型为in岗位列表时，匹配字段修改为岗位代码。否则前台无法回显数据
		for (SysRoleData item : roleDataList) {
			if ("1003".equals(item.getRuleCode())) {
				String[] posiArr = item.getMatchCondition().split("_");
				item.setMatchCondition(posiArr[1]);
			}
		}

		return roleDataList;
	}

	/**
	 * 更新角色数据权限表
	 * 
	 * @param roleDataList
	 *            角色数据权限表对象
	 * 
	 * @return 新增成功条数
	 */
	public int updateRoleData(List<SysRoleData> roleDataList) {

		int result = 0;

		if (roleDataList != null && 0 < roleDataList.size()) {

			// 删除所有角色数据权限 TODO
			// deleteRoleDataByRole(roleDataList.get(0).getRoleCode());

			// 新增角色数据权限
			for (SysRoleData item : roleDataList) {

				// 判断要新增的权限是否在数据库中已存在，若存在则先删除再新增

				if (RULE_ENTER_CODE.equals(item.getRuleCode())) {
					item.setMatchCondition(EnumType.DataAuthType.userenter.value);
					item.setIsContainChild(IS_RULE_SYMBOL_EQUAL);
				}
				if (RULE_DEPT_CODE.equals(item.getRuleCode())) {
					item.setMatchCondition(EnumType.DataAuthType.userdept.value);
					item.setIsContainChild(IS_RULE_SYMBOL_EQUAL);
				}
				if (RULE_POSI_CODE.equals(item.getRuleCode())) {
					item.setMatchCondition(EnumType.DataAuthType.userposi.value);
					item.setIsContainChild(IS_RULE_SYMBOL_EQUAL);
				}
				if (RULE_USER_CODE.equals(item.getRuleCode())) {
					item.setMatchCondition(EnumType.DataAuthType.user.value);
					item.setIsContainChild(IS_RULE_SYMBOL_EQUAL);
				}

				result += roleDataMapper.insert(item);
			}
		}

		return result;
	}

	/**
	 * 批量变更单条角色菜单数据
	 *
	 * @param roleDataList
	 *            角色数据权限表对象
	 *
	 * @return 新增成功条数
	 */
	public int subUpdateRoleData(List<SysRoleData> roleDataList) {

		int result = 0;

		if (roleDataList != null && 0 < roleDataList.size()) {

			for (SysRoleData vo : roleDataList) {
				deleteByRoleTableField(vo.getRoleCode(), vo.getTableCode(), vo.getFieldCode());
			}

			// 新增角色数据权限
			for (SysRoleData item : roleDataList) {
				if (!"default".equals(item.getRuleCode())) {

					if (RULE_ENTER_CODE.equals(item.getRuleCode())) {
						item.setMatchCondition(EnumType.DataAuthType.userenter.value);
						item.setIsContainChild(IS_RULE_SYMBOL_EQUAL);
					}
					if (RULE_DEPT_CODE.equals(item.getRuleCode())) {
						item.setMatchCondition(EnumType.DataAuthType.userdept.value);
						item.setIsContainChild(IS_RULE_SYMBOL_EQUAL);
					}
					if (RULE_POSI_CODE.equals(item.getRuleCode())) {
						item.setMatchCondition(EnumType.DataAuthType.userposi.value);
						item.setIsContainChild(IS_RULE_SYMBOL_EQUAL);
					}
					if (RULE_USER_CODE.equals(item.getRuleCode())) {
						item.setMatchCondition(EnumType.DataAuthType.user.value);
						item.setIsContainChild(IS_RULE_SYMBOL_EQUAL);
					}
					result += roleDataMapper.insert(item);
				}
			}
		}

		return result;
	}

	/**
	 * 根据角色和表名删除角色数据权限表
	 * 
	 * @param roleCode
	 *            角色代码
	 * @param tableCode
	 *            表代码
	 * 
	 * @return 新增成功条数
	 */
	public int deleteByRoleAndTable(String roleCode, String tableCode) {

		Map map = new HashMap();
		map.put("roleCode", roleCode);
		map.put("tableCode", tableCode);

		int result = abstractDao.delete("cn.com.zsyk.crm.manage.mapper.SysRoleDataMapper.deleteRoleDataByRole", map);
		return result;
	}

	/**
	 * 根据用户代码获取所有角色所拥有用户权限的用户列表
	 *
	 * @param userCode
	 *            用户代码
	 * @param tableName
	 *            表名
	 * 
	 * @return 新增成功条数
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SysUserInfo> getRolesDateEmployees(String userCode, String tableCode) {

		List<SysUserInfo> userInfoList = new ArrayList<SysUserInfo>();

		// 获取登录用户的信息
		SysUserInfo userInfo = userMngService.getOneUser(userCode);

		if (userInfo.getRoleCode() != null) {

			// 获取登录用户的所有角色
			String[] roleCodes = userInfo.getRoleCode().split(",");

			for (String roleCode : roleCodes) {

				// 根据角色获取数据权限信息
				List<SysDataAuthInfo> dataAuthList = getRoleData(roleCode, tableCode);

				List<String> posiList = new ArrayList<String>();
				List<String> deptList = new ArrayList<String>();
				List<String> enterList = new ArrayList<String>();
				String user = "";
				String userPosi = "";
				String userDept = "";
				String userEnter = "";

				// 判断是否拥有权限：true：有，false：无
				boolean flg = false;
				if(dataAuthList==null || dataAuthList.size()==0){
					userInfoList.add(userInfo);
					return userInfoList;
				}

				for (SysDataAuthInfo item : dataAuthList) {

					if (Organization.emply.value.equals(item.getFieldType())) {

						// 等于登录人
						if (IS_RULE_SYMBOL_EQUAL.equals(item.getIsContainChild())
								&& EnumType.DataAuthType.user.value.equals((item.getMatchCondition()))) {
							user = userCode;
							flg = true;
						}
					} else if (Organization.posi.value.equals(item.getFieldType())) {
						// 等于登录人岗位  ----已无用
						if (IS_RULE_SYMBOL_EQUAL.equals(item.getIsContainChild())
								&& EnumType.DataAuthType.userposi.value.equals((item.getMatchCondition()))) {
							userPosi = userInfo.getPosiCode();
							flg = true;
						}
						// in登录人岗位列表
						if (IS_RULE_SYMBOL_IN.equals(item.getIsContainChild())) {
							posiList.add(item.getMatchCondition());
							flg = true;
						}
					} else if (Organization.dept.value.equals(item.getFieldType())) {

						// 等于登录人部门
						if (IS_RULE_SYMBOL_EQUAL.equals(item.getIsContainChild())
								&& EnumType.DataAuthType.userdept.value.equals((item.getMatchCondition()))) {

							// 获取当前用户所属部门
							SysDeptInfo deptInfo = deptService.getOneDept(userInfo.getDeptCode());
							deptList.add(deptInfo.getDeptCode());

							// 获取当前用户所属部门的所有下级部门列表
							List<SysDeptInfo> deptListTemp = new ArrayList<SysDeptInfo>();
							deptListTemp = enterAuthService.getAllUnderDept(userInfo.getDeptCode(), deptListTemp);
							for (SysDeptInfo obj : deptListTemp) {
								deptList.add(obj.getDeptCode());
							}

							flg = true;
						}

						// in登录人部门列表
						if (IS_RULE_SYMBOL_IN.equals(item.getIsContainChild())) {

							// 获取当前部门
							deptList.add(item.getMatchCondition());
							// 获取当前用户所属部门的所有下级部门列表
							List<SysDeptInfo> deptListTemp = new ArrayList<SysDeptInfo>();
							deptListTemp = enterAuthService.getAllUnderDept(item.getMatchCondition(), deptListTemp);
							for (SysDeptInfo obj : deptListTemp) {
								deptList.add(obj.getDeptCode());
							}

							flg = true;
						}
					} else if (Organization.enter.value.equals(item.getFieldType())) {

						//    in下属部门  =所有
						if (IS_RULE_SYMBOL_EQUAL.equals(item.getIsContainChild())
								&& EnumType.DataAuthType.userenter.value.equals((item.getMatchCondition()))) {
							/*userEnter = userInfo.getEnterCode();

							// 获取当前用户所属机构
							SysEnterInfo enterInfo = enterService.getOneEnter(userInfo.getEnterCode());
							enterList.add(enterInfo.getEnterCode());

							// 获取用户所属机构的所有下级机构
							List<SysEnterInfo> enterListTemp = new ArrayList<SysEnterInfo>();
							enterListTemp = enterAuthService.getAllUnderEnterAll(enterInfo.getEnterCode(), enterListTemp);

							for (SysEnterInfo obj : enterListTemp) {
								enterList.add(obj.getEnterCode());
							}*/
							//当权限为所有部门时判断为管理员,管理员清空集合返回空 modiby lujibing
							userInfoList.clear();
							return userInfoList;
						}
						// in登录人机构列表
						if (IS_RULE_SYMBOL_IN.equals(item.getIsContainChild())) {
							//enterList.add(item.getMatchCondition());
							// 获取当前用户所属机构
							SysEnterInfo enterInfo = enterService.getOneEnter(userInfo.getEnterCode());
							// 获取用户所属机构的所有下级机构
							List<SysEnterInfo> enterListTemp = new ArrayList<SysEnterInfo>();
							enterListTemp = enterAuthService.getAllUnderEnterAll(enterInfo.getEnterCode(), enterListTemp);

							for (SysEnterInfo obj : enterListTemp) {
								enterList.add(obj.getEnterCode());
							}
							flg = true;
						}
					}
				}

				if (flg) {

					// 部门列表去重
					if (deptList != null && deptList.size() > 1) {
						Set deptSet = new HashSet();
						List newDeptList = new ArrayList();
						for (Iterator iter = deptList.iterator(); iter.hasNext();) {
							Object element = iter.next();
							if (deptSet.add(element))
								newDeptList.add(element);
						}
						deptList.clear();
						deptList.addAll(newDeptList);
					}

					// 机构列表去重
					if (enterList != null && enterList.size() > 1) {
						Set enterSet = new HashSet();
						List newEnterList = new ArrayList();
						for (Iterator iter = enterList.iterator(); iter.hasNext();) {
							Object element = iter.next();
							if (enterSet.add(element))
								newEnterList.add(element);
						}
						enterList.clear();
						enterList.addAll(newEnterList);
					}

					Map map = new HashMap();
					map.put("enterList", enterList);
					map.put("deptList", deptList);
					map.put("posiList", posiList);
					// map.put("userDept", userDept);
					map.put("userPosi", userPosi);
					if(enterList.size()==0){
						map.put("user", user);
					}

					List<SysUserInfo> userInfoListTemp = abstractDao
							.selectList("cn.com.zsyk.crm.manage.mapper.SysUserInfoMapper.selectEmployeeByDataAuth", map);

					for (SysUserInfo item : userInfoListTemp) {
						if (!userInfoList.contains(item)) {
							userInfoList.add(item);
						}
					}
				}
			}
		}
		return userInfoList;
	}

	/**
	 * 根据角色删除角色数据权限表
	 * 
	 * @param roleCode
	 *            角色代码
	 * 
	 * @return 新增成功条数
	 */
	public int deleteRoleDataByRole(String roleCode) {

		Map map = new HashMap();
		map.put("roleCode", roleCode);

		int result = abstractDao.delete("cn.com.zsyk.crm.manage.mapper.SysRoleDataMapper.deleteRoleDataByRole", map);
		return result;
	}

	/**
	 * 根据角色表代码字段名删除角色数据权限
	 * 
	 * @param roleCode
	 *            角色代码
	 * @param tableCode
	 *            表代码
	 * @param fieldCode
	 *            字段代码
	 * 
	 * @return 新增成功条数
	 */
	public int deleteByRoleTableField(String roleCode, String tableCode, String fieldCode) {

		if (StringUtils.isEmpty(roleCode)) {
			throw new ServiceException("角色代码[" + roleCode + "]不能为空！");
		}

		if (StringUtils.isEmpty(tableCode)) {
			throw new ServiceException("表代码[" + tableCode + "]不能为空！");
		}

		if (StringUtils.isEmpty(fieldCode)) {
			throw new ServiceException("字段代码[" + fieldCode + "]不能为空！");
		}

		Map map = new HashMap();
		map.put("roleCode", roleCode);
		map.put("tableCode", tableCode);
		map.put("fieldCode", fieldCode);

		int result = abstractDao.delete("cn.com.zsyk.crm.manage.mapper.SysRoleDataMapper.deleteByRoleTableField", map);
		return result;
	}
	/**
	 * 获取数据权限明细 
	 * 
	 * @return 数据权限明细
	 */
	public List<SysRoleDataDetail> getRoleDataDetail(String roleCode, String tableCode) {
		Map param = new HashMap();
		param.put("roleCode", roleCode);
		param.put("tableCode", tableCode);
		
		List<SysRoleDataDetail> list = abstractDao
				.selectList("cn.com.zsyk.crm.manage.mapper.SysRoleDataMapper.selectDetail", param);
		List<SysRoleDataDetail> resultList = new ArrayList();
		// 获取过滤值
		for(SysRoleDataDetail detail : list) {
			String ruleCode = detail.getRuleCode();
			// 过滤值
			StringBuilder sb = new StringBuilder();
			// matchCondition 以逗号隔开
			String matchCondition = detail.getMatchCondition();
			String [] mc = matchCondition.split(",");
			// 根据类别获取过滤值
			switch(ruleCode) {
				case RULE_DEPT_LIST_CODE : // in部门列表
					for(String deptCode : mc) {
						SysDeptInfo dept = deptService.getOneDept(deptCode);
						if(null == dept) {
							throw new ServiceException("部门信息不存在，部门编码：[" + deptCode + "]");
						}
						if(sb.length() != 0) {
							sb.append(",");
						}
						sb.append(dept.getDeptName());
					}
					break;
				case RULE_DEPT_CODE : break;// 等于登录人部门
				case 	RULE_POSI_LIST_CODE : // in岗位列表
					for(String code : mc) {
						String [] params = code.split("_");
						SysDeptPosi posi = posiService.getPosiByPosi(params[0], params[1]).get(0);
						if(null == posi) {
							throw new ServiceException("岗位信息不存在，部门编码：[" + params[0] + "] 岗位编码：[" + params[0] + "]");
						}
						if(sb.length() != 0) {							
							sb.append(",");
						}						
						sb.append(posi.getPosiName());
					}					
					break;
				case 	RULE_POSI_CODE  : break;// 等于登录人岗位
				case RULE_USER_CODE : break;// 等于登录人
				case 	RULE_ENTER_LIST_CODE : // in机构列表
					/*for(String enterCode : mc) {
						SysEnterInfo enter= enterService.getOneEnter(enterCode);
						if(null == enter) {
							throw new ServiceException("机构信息不存在，机构编码：[" + enterCode + "]");
						}
						if(sb.length() != 0) {
							sb.append(",");
						}
						sb.append(enter.getEnterName());
					}*/
					break;
				case 	RULE_ENTER_CODE : break;// 等于登录人机构
			}
			detail.setDesc(sb.toString());
			// 去掉重复项
			int j=0;//是否更新标志
			for(int i=0;  i < resultList.size(); i++) {
				if(detail.getFieldCode().equals(resultList.get(i).getFieldCode()) && detail.getRuleCode().equals(resultList.get(i).getRuleCode())) {
					String desc = resultList.get(i).getDesc()+','+detail.getDesc();
					resultList.get(i).setDesc(desc);
					j=1;//更新后设置为1
					break;
				}
			}
			if( j == 0) {
				resultList.add(detail);
			}
		}
		return resultList;
	}


}
