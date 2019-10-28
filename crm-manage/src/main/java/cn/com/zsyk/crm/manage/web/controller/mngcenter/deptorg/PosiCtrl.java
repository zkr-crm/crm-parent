package cn.com.zsyk.crm.manage.web.controller.mngcenter.deptorg;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.zsyk.crm.common.annotation.SysOprtLog;
import cn.com.zsyk.crm.common.annotation.SysOprtLog.Module;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.util.JsonUtil;
import cn.com.zsyk.crm.manage.entity.SysDeptPosi;
import cn.com.zsyk.crm.manage.service.mngcenter.deptorg.PosiService;

@RestController
public class PosiCtrl {

	@Autowired
	private PosiService service;

	/**
	 * @api {GET} /crm/manage/posi 查询一条岗位信息
	 * @apiDescription 
	 * @apiName getPosi
	 * @apiGroup Enterprise
	 *
	 * @apiParam {String} posiCode 岗位代码
	 * @apiParam {String} posiName 岗位名称
	 *
	 * @apiSuccess {Object} Response 返回值对象
	 */
	/*
	 * @RequestMapping(path = "/crm/manage/posi", method = RequestMethod.GET) public
	 * Result getPosi(String posiCode, String posiName) { Result res = new Result();
	 * System.out.println("获取单条岗位数据，更换logger形式"); SysPosiInfo posiInfo =
	 * service.getOnePosi(posiCode, posiName); res.setData(posiInfo); return res; }
	 */
	/**
	 * @api {GET} /crm/manage/posi 查询所有岗位信息
	 * @apiDescription 
	 * @apiName getAllPosis
	 * @apiGroup Enterprise
	 *
	 * @apiSuccess {Object} Response 返回值对象
	 */
	/*
	 * @RequestMapping(path = "/crm/manage/posis", method = RequestMethod.GET)
	 * public Result getAllPosis() { Result res = new Result();
	 * System.out.println("获取岗位列表，更换logger形式"); List<SysPosiInfo> posiList =
	 * service.getAllPosi(); res.setData(posiList); return res; }
	 */

	/**
	 * @api {GET} /crm/manage/posisByEntity 根据条件查询岗位信息
	 * @apiDescription 
	 * @apiName getPosisByEntity
	 * @apiGroup Enterprise
	 *
	 * @apiParam {SysPosiInfo} posiInfo 岗位信息对象
	 *
	 * @apiSuccess {Object} Response 返回值对象
	 */
	/*
	 * @RequestMapping(path = "/crm/manage/posisByEntity", method =
	 * RequestMethod.GET) public Result getPosisByEntity(SysPosiInfo posiInfo) {
	 * Result res = new Result(); System.out.println("获取岗位列表，更换logger形式");
	 * posiInfo.setRecStat("0"); List<SysPosiInfo> posiList =
	 * service.getPosiByEntity(posiInfo); res.setData(posiList); return res; }
	 */

	/**
	 * @api {POST} /crm/manage/posi 新增一条岗位信息
	 * @apiDescription 
	 * @apiName addPosi
	 * @apiGroup Enterprise
	 *
	 * @apiParam {SysPosiInfo} posiInfo 岗位信息对象
	 *
	 * @apiSuccess {Object} Response 返回值对象
	 */
	/*
	 * @RequestMapping(path = "/crm/manage/posi", method = RequestMethod.POST)
	 * 
	 * @SysOprtLog(model=Module.DEPT, bizDesc="新增岗位信息") public Result
	 * addPosi(SysPosiInfo posiInfo) { Result res = new Result();
	 * System.out.println("新增单条岗位数据，更换logger形式");
	 * 
	 * int addCount = service.addPosi(posiInfo);
	 * 
	 * if (addCount > 0) { System.out.println("插入数据成功，更换logger形式"); } else { throw
	 * new ServiceException("新增岗位数据数据失败"); }
	 * 
	 * return res; }
	 */

	/**
	 * @api {PUT} /crm/manage/posi 更新一条岗位信息
	 * @apiDescription 
	 * @apiName modPosi
	 * @apiGroup Enterprise
	 *
	 * @apiParam {SysPosiInfo} posiInfo 岗位信息对象
	 *
	 * @apiSuccess {Object} Response 返回值对象
	 */
	/*
	 * @RequestMapping(path = "/crm/manage/posi", method = RequestMethod.PUT)
	 * 
	 * @SysOprtLog(model=Module.DEPT, bizDesc="修改岗位信息") public Result
	 * modPosi(SysPosiInfo posiInfo) { Result res = new Result();
	 * System.out.println("修改单条岗位数据，更换logger形式");
	 * 
	 * int modCount = service.modPosi(posiInfo);
	 * 
	 * if (modCount > 0) { System.out.println("修改数据成功，更换logger形式"); } else { throw
	 * new ServiceException("修改岗位数据数据失败"); }
	 * 
	 * return res; }
	 */

	/**
	 * @api {PUT} /crm/manage/delposi 逻辑删除一条岗位信息
	 * @apiDescription 
	 * @apiName delModPosi
	 * @apiGroup Enterprise
	 *
	 * @apiParam {String} posiCode 岗位代码
	 * @apiParam {String} posiName 岗位名称
	 *
	 * @apiSuccess {Object} Response 返回值对象
	 */
	/*
	 * @RequestMapping(path = "/crm/manage/delposi", method = RequestMethod.PUT)
	 * 
	 * @SysOprtLog(model=Module.DEPT, bizDesc="删除岗位信息") public Result
	 * delModPosi(String posiCode, String posiName) { Result res = new Result();
	 * System.out.println("逻辑删除单条岗位数据，更换logger形式"); SysPosiInfo posiInfo = new
	 * SysPosiInfo(); posiInfo.setPosiCode(posiCode);
	 * posiInfo.setPosiName(posiName); posiInfo.setRecStat("1"); int delModCount =
	 * service.delModPosi(posiInfo);
	 * 
	 * if (delModCount > 0) { System.out.println("删除数据成功，更换logger形式"); } else {
	 * throw new ServiceException("删除岗位数据数据失败"); }
	 * 
	 * return res; }
	 */

	/**
	 * @api {DELETE} /crm/manage/physicPosiDel 物理删除一条岗位信息
	 * @apiDescription 
	 * @apiName delPosi
	 * @apiGroup Enterprise
	 *
	 * @apiParam {String} posiCode 岗位代码
	 * @apiParam {String} posiName 岗位名称
	 *
	 * @apiSuccess {Object} Response 返回值对象
	 */
	/*
	 * @RequestMapping(path = "/crm/manage/physicPosiDel", method =
	 * RequestMethod.DELETE)
	 * 
	 * @SysOprtLog(model=Module.DEPT, bizDesc="删除岗位信息") public Result delPosi(String
	 * posiCode, String posiName) { Result res = new Result();
	 * System.out.println("物理删除单条岗位数据，更换logger形式");
	 * 
	 * int delCount = service.delPosi(posiCode, posiName);
	 * 
	 * if (delCount > 0) { System.out.println("删除数据成功，更换logger形式"); } else { throw
	 * new ServiceException("删除岗位数据数据失败"); }
	 * 
	 * return res; }
	 */

	/**
	 * @api {GET} /crm/manage/getPosiByDept 根据部门代码获取部门下所有岗位信息
	 * @apiName getPosiByDept
	 * @apiGroup Enterprise
	 *
	 * @apiParam {String} deptCode 部门代码
	 *
	 * @apiSuccess {List} SysDeptPosi 部门下所有岗位的列表
	 */
	@RequestMapping(path = "/crm/manage/getPosiByDept", method = RequestMethod.GET)
	public Result getPosiByDept(String deptCode) {
		Result res = new Result();
		System.out.println("根据部门代码获取部门下所有岗位信息");
		List<SysDeptPosi> deptPosiList = new ArrayList<SysDeptPosi>();
		deptPosiList = service.getPosiByDept(deptCode);
		res.setData(deptPosiList);
		return res;
	}

	/**
	 * @api {PUT} /crm/manage/updDeptPosi 更新部门下的岗位列表
	 * @apiName updDeptPosi
	 * @apiGroup Enterprise
	 *
	 * @apiParam {String} deptCode 部门代码
	 * @apiParam {String} deptPosiStr 部门岗位信息列表json字符串
	 *
	 * @apiSuccess {List} SysPosiInfo 部门下所有岗位的列表
	 */
	@RequestMapping(path = "/crm/manage/updDeptPosi", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.DEPT, bizDesc = "修改的岗位列表")
	public Result updDeptPosi(String deptCode, @RequestBody String deptPosiStr) {
		Result res = new Result();
		System.out.println("更新部门下的岗位列表");
		List<SysDeptPosi> deptPosiList = JsonUtil.parseArray(deptPosiStr, SysDeptPosi.class);

		service.updDeptPosi(deptCode, deptPosiList);

		return res;
	}
}
