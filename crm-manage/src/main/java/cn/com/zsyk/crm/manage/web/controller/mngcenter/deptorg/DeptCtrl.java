package cn.com.zsyk.crm.manage.web.controller.mngcenter.deptorg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.zsyk.crm.common.annotation.SysOprtLog;
import cn.com.zsyk.crm.common.annotation.SysOprtLog.Module;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.manage.entity.SysDeptInfo;
import cn.com.zsyk.crm.manage.service.mngcenter.deptorg.DeptService;

@RestController
public class DeptCtrl {

	@Autowired
	private DeptService service;

	/**
	 * @api {GET} /crm/manage/dept 获取一条部门信息
	 * @apiDescription 
	 * @apiName getDept
	 * @apiGroup Department
	 *
	 * @apiParam {String} deptCode 部门代码
	 *
	 * @apiSuccess {Object} Response 返回值对象
	 */
	@RequestMapping(path = "/crm/manage/dept", method = RequestMethod.GET)
	public Result getDept(String deptCode) {
		Result res = new Result();

		SysDeptInfo deptInfo = service.getOneDept(deptCode);

		res.setData(deptInfo);
		return res;
	}

	/**
	 * @api {GET} /crm/manage/deptsByEntity 按条件查询部门信息
	 * @apiDescription 
	 * @apiName getDeptsByEntity
	 * @apiGroup Department
	 *
	 * @apiParam {SysDeptInfo} deptInfo 部门信息对象
	 *
	 * @apiSuccess {Object} Response 返回值对象
	 */
	@RequestMapping(path = "/crm/manage/deptsByEntity", method = RequestMethod.GET)
	public Result getDeptsByEntity(SysDeptInfo deptInfo) {
		Result res = new Result();

		List<SysDeptInfo> deptList = service.getDeptByEntity(deptInfo);

		res.setData(deptList);
		return res;
	}

	/**
	 * @api {GET} /crm/manage/depts 查询所有部门信息
	 * @apiDescription 
	 * @apiName getAllDepts
	 * @apiGroup Department
	 *
	 * @apiSuccess {Object} Response 返回值对象
	 */
	@RequestMapping(path = "/crm/manage/depts", method = RequestMethod.GET)
	public Result getAllDepts() {
		Result res = new Result();

		List<SysDeptInfo> deptList = service.getAllDept();
		res.setData(deptList);
		return res;
	}

	/**
	 * @api {POST} /crm/manage/dept 新增一条部门信息
	 * @apiDescription 
	 * @apiName addDept
	 * @apiGroup Department
	 *
	 * @apiParam {SysDeptInfo} deptInfo 部门信息对象
	 *
	 * @apiSuccess {Object} Response 返回值对象
	 */
	@RequestMapping(path = "/crm/manage/dept", method = RequestMethod.POST)
	@SysOprtLog(model = Module.DEPT, bizDesc = "新增部门信息")
	public Result addDept(SysDeptInfo deptInfo) {
		Result res = new Result();

		int addCount = service.addDept(deptInfo);

		if (addCount <= 0) {
			throw new ServiceException("新增部门数据失败");
		}

		return res;
	}

	/**
	 * @api {PUT} /crm/manage/dept 更新一条部门信息
	 * @apiDescription 
	 * @apiName modDept
	 * @apiGroup Department
	 *
	 * @apiParam {SysDeptInfo} deptInfo 部门信息对象
	 *
	 * @apiSuccess {Object} Response 返回值对象
	 */
	@RequestMapping(path = "/crm/manage/dept", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.DEPT, bizDesc = "修改部门信息")
	public Result modDept(SysDeptInfo deptInfo) {
		Result res = new Result();

		int modCount = service.modDept(deptInfo);

		if (modCount <= 0) {

			throw new ServiceException("修改部门数据数据失败");
		}

		return res;
	}

	/**
	 * @api {PUT} /crm/manage/deldept 逻辑删除一条部门信息
	 * @apiDescription 
	 * @apiName delModDept
	 * @apiGroup Department
	 *
	 * @apiParam {String} deptCode 部门代码
	 * @apiParam {String} deptName 部门名称
	 *
	 * @apiSuccess {Object} Response 返回值对象
	 */
	@RequestMapping(path = "/crm/manage/deldept", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.DEPT, bizDesc = "删除部门信息")
	public Result delModDept(String deptCode, String deptName) {
		Result res = new Result();

		SysDeptInfo deptInfo = new SysDeptInfo();
		deptInfo.setDeptCode(deptCode);
		deptInfo.setDeptName(deptName);
		deptInfo.setRecStat("1");// 状态置为1,逻辑删除

		int delModCount = service.delModDept(deptInfo);

		if (delModCount <= 0) {
			throw new ServiceException("删除部门数据数据失败");
		}

		return res;
	}

	/**
	 * @api {DELETE} /crm/manage/dept 物理删除一条部门信息
	 * @apiDescription 
	 * @apiName delDept
	 * @apiGroup Department
	 *
	 * @apiParam {String} deptCode 部门代码
	 *
	 * @apiSuccess {Object} Response 返回值对象
	 */
	@RequestMapping(path = "/crm/manage/dept", method = RequestMethod.DELETE)
	@SysOprtLog(model = Module.DEPT, bizDesc = "删除部门信息")
	public Result delDept(String deptCode) {
		Result res = new Result();

		int delCount = service.delDept(deptCode);

		if (delCount <= 0) {
			throw new ServiceException("删除部门数据数据失败");
		}

		return res;
	}

	/**
	 * @api {GET} /crm/manage/getDeptsByEnter 根据机构号查询机构下所有部门信息
	 * @apiDescription 
	 * @apiName getDeptsByEnter
	 * @apiGroup Department
	 *
	 * @apiParam {String} enterCode 机构代码
	 *
	 * @apiSuccess {List} SysDeptInfo 返回值对象
	 */
	@RequestMapping(path = "/crm/manage/getDeptsByEnter", method = RequestMethod.GET)
	@SysOprtLog(model = Module.DEPT, bizDesc = "根据机构号查询机构下所有部门信息")
	public Result getDeptsByEnter(String enterCode) {
		Result res = new Result();

		List<SysDeptInfo> deptList = service.getDeptsByEnter(enterCode);

		res.setData(deptList);
		return res;
	}
}
