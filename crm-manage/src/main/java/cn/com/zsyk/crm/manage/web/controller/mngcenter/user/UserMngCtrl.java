package cn.com.zsyk.crm.manage.web.controller.mngcenter.user;

import cn.com.zsyk.crm.common.annotation.SysOprtLog;
import cn.com.zsyk.crm.common.annotation.SysOprtLog.Module;
import cn.com.zsyk.crm.common.constant.ServiceType;
import cn.com.zsyk.crm.common.dao.CoreDaoImpl;
import cn.com.zsyk.crm.common.dto.ContextContainer;
import cn.com.zsyk.crm.common.dto.PageBean;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.LogUtil;
import cn.com.zsyk.crm.common.util.PasswdUtil;
import cn.com.zsyk.crm.common.util.RestUtil;
import cn.com.zsyk.crm.manage.entity.SysUserInfo;
import cn.com.zsyk.crm.manage.mapper.SysUserInfoMapper;
import cn.com.zsyk.crm.manage.service.mngcenter.user.UserMngService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserMngCtrl {

	@Autowired
	private UserMngService service;
	@Autowired
	private ObjectMapper om;
	@Autowired
	private RestUtil restUtil;
	@Autowired
	private CoreDaoImpl dao;
	@Autowired
	private SysUserInfoMapper sysUserInfoMapper;

	Log log = LogUtil.getLogger(UserMngCtrl.class);

	/**
	 * @api {GET} /crm/manage/usermng/users 查询所有用户列表
	 * @apiDescription 
	 * @apiName getAllUsers
	 * @apiGroup UserMng
	 *
	 * @apiSuccess {SysUserInfo} data 返回值对象
	 */
	@RequestMapping(path = "/crm/manage/usermng/users", method = RequestMethod.GET)
	public Result getAllUsers() {
		log.info(">>>>>>>>>>getAllUsers start<<<<<<<<<<");
		log = LogUtil.getLogger(UserMngCtrl.class);
		Result res = new Result();
		System.out.println("获取所有用户列表。");
		List<SysUserInfo> userList = service.getAllUser();
		res.setData(userList);
		log.info(">>>>>>>>>>getAllUsers end<<<<<<<<<<");
		return res;
	}

	@RequestMapping(path = "/crm/manage/usermng/usersByRest", method = RequestMethod.GET)
	public Result getAllUsers2() {
		Result forObject = restUtil.getForObject(ServiceType.MANAGE, "/crm/manage/usermng/users", Result.class);

		return forObject;
	}

	/**
	 * @api {GET} /crm/manage/usermng/usersByEntity 按条件查询所有用户列表
	 * @apiDescription 
	 * @apiName getUsersByEntity
	 * @apiGroup UserMng
	 *
	 * @apiParam {SysUserInfo} user 用户信息对象
	 *
	 * @apiSuccess {SysUserInfo} data 用户信息列表
	 */
	@RequestMapping(path = "/crm/manage/usermng/usersByEntity", method = RequestMethod.GET)
	public Result getUsersByEntity(SysUserInfo user) {
		Result res = new Result();
		System.out.println("获取单条用户数据。");
		List<SysUserInfo> userList = service.getUsersByEntity(user);
		res.setData(userList);
		return res;
	}
	/**
	 * @api {GET} /crm/manage/usermng/usersByEntity 按条件查询所有用户列表
	 * @apiDescription
	 * @apiName getUsersByEntity
	 * @apiGroup UserMng
	 *
	 * @apiParam {SysUserInfo} user 用户信息对象
	 *
	 * @apiSuccess {SysUserInfo} data 用户信息列表
	 */
	@RequestMapping(path = "/crm/manage/usermng/usersWithPage", method = RequestMethod.GET)
	public Result getUsersWithPage(SysUserInfo user) {
		Result res = new Result();
		System.out.println("获取单条用户数据。");
		Map param = new HashMap();
		param.put("userId", user.getUserId());
		param.put("userName",user.getUserName() );
		param.put("enterCode", user.getEnterCode());
		param.put("deptCode", user.getDeptCode());
		param.put("posiCode", user.getPosiCode());
		param.put("userStat", user.getUserStat());
		param.put("employeeId", user.getEmployeeId());
		PageBean p=dao.selectPageByMapper(sysUserInfoMapper, "selectByEntity", param);
		/*List<SysUserInfo> userList = service.getUsersByEntity(user);*/
		res.setData(p);
		return res;
	}

	/**
	 * @api {GET} /crm/manage/usermng/usersBusiOppByEntity 按条件查询所有用户列表
	 * @apiDescription 
	 * @apiName getUsersBusiOppByEntity
	 * @apiGroup UserMng
	 *
	 * @apiParam {SysUserInfo} user 用户信息对象
	 *
	 * @apiSuccess {SysUserInfo} data 用户信息列表
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/manage/usermng/usersBusiOppByEntity", method = RequestMethod.GET)
	public Result getUsersBusiOppByEntity(SysUserInfo user) {
		Result res = new Result();
		List<SysUserInfo> userList = service.getUsersBusiOppByEntity(user);
		res.setData(userList);
		return res;
	}
	/**
	 * @api {GET} /crm/manage/usermng/usersBusiOppByEntity 按条件查询所有用户列表
	 * @apiDescription
	 * @apiName getUsersBusiOppByEntity
	 * @apiGroup UserMng
	 *
	 * @apiParam {SysUserInfo} user 用户信息对象
	 *
	 * @apiSuccess {SysUserInfo} data 用户信息列表
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/manage/usermng/getUsersByEnterCodes", method = RequestMethod.GET)
	public Result getUsersByEnterCodes(String codes) {
		Result res = new Result();
		List<SysUserInfo> userList = service.getUsersByEnterCodes(codes);
		res.setData(userList);
		return res;
	}

	/**
	 * @api {GET} /crm/manage/usermng/user 查询一条用户记录
	 * @apiDescription 
	 * @apiName getUser
	 * @apiGroup UserMng
	 *
	 * @apiParam {String} userID 用户ID
	 *
	 * @apiSuccess {SysUserInfo} data 用户信息对象
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/manage/usermng/user", method = RequestMethod.GET)
	public Result getUser(String userID) {
		Result res = new Result();
		System.out.println("获取单条用户数据。");
		SysUserInfo userInfo = service.getOneUser(userID);
		res.setData(userInfo);
		return res;
	}

	/**
	 * @api {GET} /crm/manage/usermng/getUserByEmployeeID 根据员工ID查询一条用户记录
	 * @apiDescription 
	 * @apiName getUserByEmployeeID
	 * @apiGroup UserMng
	 *
	 * @apiParam {String} employeeId 员工ID
	 *
	 * @apiSuccess {SysUserInfo} data 用户信息对象
	 */
	@RequestMapping(path = "/crm/manage/usermng/getUserByEmployeeID", method = RequestMethod.GET)
	public Result getUserByEmployeeID(String employeeId) {
		Result res = new Result();
		System.out.println("获取单条用户数据。");
		SysUserInfo userInfo = service.getUserByEmployeeID(employeeId);
		res.setData(userInfo);
		return res;
	}

	/**
	 * @api {POST} /crm/manage/usermng/user 新增一条用户记录
	 * @apiDescription 
	 * @apiName addUser
	 * @apiGroup UserMng
	 *
	 * @apiParam {SysUserInfo} user 用户信息对象
	 *
	 * @apiSuccess {int} data 新增记录条数
	 */
	@RequestMapping(path = "/crm/manage/usermng/user", method = RequestMethod.POST)
	@SysOprtLog(model = Module.USER, bizDesc = "新增用户记录")
	public Result addUser(SysUserInfo userInfo) {
		Result res = new Result();
		System.out.println("新增单条用户数据。");
		userInfo.setPassword(PasswdUtil.encode(userInfo.getPassword()));
		int addCount = service.addUser(userInfo);

		if (addCount > 0) {
			System.out.println("插入用户数据成功。");
		} else {
			throw new ServiceException("新增用户数据数据失败！");
		}

		return res;
	}

	/**
	 * @api {PUT} /crm/manage/usermng/user 更新一条用户记录
	 * @apiDescription 
	 * @apiName modUser
	 * @apiGroup UserMng
	 *
	 * @apiParam {SysUserInfo} user 用户信息对象
	 *
	 * @apiSuccess {int} data 更新记录条数
	 */
	@RequestMapping(path = "/crm/manage/usermng/user", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.USER, bizDesc = "更新用户记录")
	public Result modUser(@RequestBody SysUserInfo userInfo) {
		Result res = new Result();
		System.out.println("修改单条用户数据。");
		if(userInfo.getPassword()!=null && userInfo.getPassword().length()<=20){
			userInfo.setPassword(PasswdUtil.encode(userInfo.getPassword()));
		}
		int modCount = service.modUser(userInfo);

		if (modCount > 0) {
			res.success("修改用户数据成功。");
		} else {
			res.fail("修改用户数据数据失败！");
		}

		return res;
	}

	/**
	 * @api {PUT} /crm/manage/usermng/modUserRole 更新用户角色
	 * @apiDescription 
	 * @apiName modUserRole
	 * @apiGroup UserMng
	 *
	 * @apiParam {SysUserInfo} user 用户信息对象
	 *
	 * @apiSuccess {int} data 更新记录条数
	 */
	@RequestMapping(path = "/crm/manage/usermng/modUserRole", method = RequestMethod.POST)
	@SysOprtLog(model = Module.USER, bizDesc = "更新用户角色")
	public Result modUserRole(@RequestBody SysUserInfo userInfo) {
		Result res = new Result();
		System.out.println("修改单条用户数据。");

		int modCount = service.modUserRole(userInfo);

		if (modCount > 0) {
			System.out.println("修改用户数据成功。");
		} else {
			throw new ServiceException("修改用户数据数据失败！");
		}

		return res;
	}

	/**
	 * @api {PUT} /crm/manage/usermng/deluser 逻辑删除一条用户记录
	 * @apiDescription 
	 * @apiName delModUser
	 * @apiGroup UserMng
	 *
	 * @apiParam {String} userID 用户ID
	 *
	 * @apiSuccess {int} data 逻辑删除记录条数
	 */
	@RequestMapping(path = "/crm/manage/usermng/deluser", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.USER, bizDesc = "删除用户记录")
	public Result delModUser(String userId) {
		Result res = new Result();
		System.out.println("逻辑删除单条用户数据。");

		SysUserInfo userInfo = service.getOneUser(userId);

		if (userInfo == null) {
			throw new ServiceException("删除用户数据数据失败");
		}

		int delCount = service.delModUser(userInfo);

		if (delCount > 0) {
			System.out.println("删除用户数据成功。");
		} else {
			throw new ServiceException("删除用户数据数据失败");
		}

		res.setData(delCount);
		return res;
	}

	/**
	 * @api {DELETE} /crm/manage/usermng/delUser 物理删除一条用户记录
	 * @apiDescription 
	 * @apiName delUser
	 * @apiGroup UserMng
	 *
	 * @apiParam {String} userID 用户ID
	 *
	 * @apiSuccess {int} data 删除成功条数
	 */
	@RequestMapping(path = "/crm/manage/usermng/delUser", method = RequestMethod.DELETE)
	@SysOprtLog(model = Module.USER, bizDesc = "删除用户记录")
	public Result delUser(String userId) {
		Result res = new Result();

		int addCount = service.delUser(userId);

		if (addCount <= 0) {
			throw new ServiceException("删除用户数据数据失败!");
		}

		res.setData(addCount);
		return res;
	}

	/**
	 * @api {POST} /crm/manage/usermng/userLoginChk 用户登录校验
	 * @apiDescription 
	 * @apiName userLoginChk
	 * @apiGroup UserMng
	 *
	 * @apiParam {SysUserInfo} user 用户信息对象
	 *
	 * @apiSuccess {boolean} data true:校验成功，false：校验失败
	 */
	@RequestMapping(path = "/crm/manage/usermng/userLoginChk", method = RequestMethod.POST)
	public Result userLoginChk(@RequestBody SysUserInfo userInfo) {
		System.out.println("用户登录校验");
		SysUserInfo user = service.userLoginChk(userInfo);

		if (null == user||user.getRoleCode()==null||user.getRoleCode().equals("")) {
			throw new ServiceException("该用户无使用权限!");
		}

		// 将当前用户放入上下文，供TokenAspect使用
		ContextContainer.getContext().setUserId(user.getUserId());
		// 将当前用户员工号放入上下文，供TokenAspect使用
		ContextContainer.getContext().setEmployeeId(user.getEmployeeId());

		return new Result<SysUserInfo>().success(user);
	}

	/**
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * @api {POST} /crm/manage/usermng/delUsersByKey 根据json串传递主键列表，逻辑删除多条用户记录
	 * @apiDescription 
	 * @apiName delUsersByKey
	 * @apiGroup UserMng
	 *
	 * @apiParam {String} jsonStr json对象字符串(格式同List<SysUserInfo>对象一致)
	 *
	 * @apiSuccess {int} data 删除成功条数
	 */
	@RequestMapping(path = "/crm/manage/usermng/delUsersByKey", method = RequestMethod.POST)
	@SysOprtLog(model = Module.USER, bizDesc = "删除用户记录")
	public Result delUsersByKey(@RequestBody String checkedRow) throws Exception {
		Result res = new Result();
		System.out.println("逻辑删除多条用户数据。");

		List<SysUserInfo> userList = om.readValue(checkedRow, new TypeReference<List<SysUserInfo>>() {
		});

		// 删除的记录条数
		int delCount = 0;

		for (SysUserInfo item : userList) {

			// 执行删除操作
			//int addCount = service.delUser(item.getUserId());
			int addCount = service.delModUser(item);

			// 删除是否成功
			if (addCount > 0) {
				System.out.println("删除用户数据成功。");
			} else {
				throw new ServiceException("删除用户数据数据失败!");
			}
			delCount += addCount;
		}

		res.setData(delCount);
		res.setMessage("交易成功！");
		return res;
	}

	/**
	 * @api {PUT} /crm/manage/usermng/modPassword 用户密码修改
	 * @apiName modPassword
	 * @apiGroup UserMng
	 *
	 * @apiParam {SysUserInfo} user 用户信息对象 {String} password1 {String} password2
	 *           {String} password3 当前密码
	 *
	 * @apiSuccess {int} data 更新记录条数
	 */
	@RequestMapping(path = "/crm/manage/usermng/modPassword", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.USER, bizDesc = "用户密码修改")
	public Result modPassword(SysUserInfo userInfo, String password1, String password2, String password3) {
		// 校验当前密码
		userInfo.setPassword(password3);
		try {
			service.userLoginChk(userInfo);
		} catch (Exception ServiceException) {
			throw new ServiceException("当前密码错误!");
		}
		// 判断新密码两次输入是否一致
		if (null == password1 || null == password2) {
			throw new ServiceException("新密码为空!");
		}
		if (!password1.equals(password2)) {
			throw new ServiceException("新密码输入不一致!");
		}
		Result res = new Result();
		System.out.println("修改密码。");
		userInfo.setPassword(PasswdUtil.encode(password1));
		int modCount = service.modPassword(userInfo);

		if (modCount > 0) {
			System.out.println("修改用户密码成功。");
		} else {
			throw new ServiceException("修改密码失败！");
		}
		res.setData(modCount);
		res.setMessage("交易成功！");
		return res;
	}

}
