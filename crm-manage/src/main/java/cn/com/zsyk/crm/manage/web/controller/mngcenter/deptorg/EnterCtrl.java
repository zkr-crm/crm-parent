package cn.com.zsyk.crm.manage.web.controller.mngcenter.deptorg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.zsyk.crm.common.dao.CoreDaoImpl;
import cn.com.zsyk.crm.common.dto.PageBean;
import cn.com.zsyk.crm.manage.mapper.SysEnterInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.zsyk.crm.common.annotation.SysOprtLog;
import cn.com.zsyk.crm.common.annotation.SysOprtLog.Module;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.manage.entity.SysEnterInfo;
import cn.com.zsyk.crm.manage.service.mngcenter.deptorg.EnterService;

@RestController
public class EnterCtrl {

	@Autowired
	private EnterService service;
	@Autowired
	private CoreDaoImpl dao;
	@Autowired
	private SysEnterInfoMapper sysEnterInfoMapper;
	/**
	 * @api {GET} /crm/manage/enter 查询一条机构信息
	 * @apiDescription 
	 * @apiName getEnter
	 * @apiGroup Enterprise
	 *
	 * @apiParam {String} enterCode 机构代码
	 *
	 * @apiSuccess {Object} Response 返回值对象
	 */
	@RequestMapping(path = "/crm/manage/enter", method = RequestMethod.GET)
	public Result getEnter(String enterCode) {
		Result res = new Result();

		SysEnterInfo enterInfo = service.getOneEnter(enterCode);

		res.setData(enterInfo);
		return res;
	}

	/**
	 * @api {GET} /crm/manage/enter 查询所有机构信息
	 * @apiDescription 
	 * @apiName getAllEnters
	 * @apiGroup Enterprise
	 * 
	 * @apiSuccess {Object} Response 返回值对象
	 */
	@RequestMapping(path = "/crm/manage/enters", method = RequestMethod.GET)
	public Result getAllEnters() {
		Result res = new Result();

		List<SysEnterInfo> enterList = service.getAllEnter();

		res.setData(enterList);
		return res;
	}

	/**
	 * @api {GET} /crm/manage/entersByEntity 根据条件查询机构信息
	 * @apiDescription 
	 * @apiName getEntersByEntity
	 * @apiGroup Enterprise
	 *
	 * @apiParam {SysEnterInfo} enterInfo 机构信息对象
	 *
	 * @apiSuccess {Object} Response 返回值对象
	 */
	@RequestMapping(path = "/crm/manage/entersByEntity", method = RequestMethod.GET)
	public Result getEntersByEntity(SysEnterInfo enterInfo) {
		Result res = new Result();
		Map param = new HashMap();
		param.put("enterCode", enterInfo.getEnterCode());
		param.put("enterName",enterInfo.getEnterName() );
		param.put("enterHead", enterInfo.getEnterHead());
		param.put("enterLevel", enterInfo.getEnterLevel());
		param.put("superEnterCode", enterInfo.getSuperEnterCode());
		param.put("superEnter", enterInfo.getSuperEnter());
		param.put("enterAddr", enterInfo.getEnterAddr());
		param.put("enterTel", enterInfo.getEnterTel());
		param.put("enterWeb", enterInfo.getEnterWeb());
		param.put("enterEmail",enterInfo.getEnterEmail() );
		param.put("busiSumm", enterInfo.getBusiSumm());
		param.put("recStat", enterInfo.getRecStat());

		PageBean p=dao.selectPageByMapper(sysEnterInfoMapper, "selectByEntity", param);
		/*List<SysEnterInfo> enterList = service.getEntersByEntity(enterInfo);*/

		res.setData(p);
		return res;
	}

	/**
	 * @api {POST} /crm/manage/enter 新增一条机构信息
	 * @apiDescription 
	 * @apiName addEnter
	 * @apiGroup Enterprise
	 *
	 * @apiParam {SysEnterInfo} enterInfo 机构信息对象
	 *
	 * @apiSuccess {Object} Response 返回值对象
	 */
	@RequestMapping(path = "/crm/manage/enter", method = RequestMethod.POST)
	@SysOprtLog(model = Module.DEPT, bizDesc = "新增机构信息")
	public Result addEnter(SysEnterInfo enterInfo) {

		Result res = new Result();

		int addCount = service.addEnter(enterInfo);

		if (addCount <= 0) {
			throw new ServiceException("新增机构数据数据失败");
		}

		return res;
	}

	/**
	 * @api {PUT} /crm/manage/enter 更新一条机构信息
	 * @apiDescription 
	 * @apiName modEnter
	 * @apiGroup Enterprise
	 *
	 * @apiParam {SysEnterInfo} enterInfo 机构信息对象
	 *
	 * @apiSuccess {Object} Response 返回值对象
	 */
	@RequestMapping(path = "/crm/manage/enter", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.DEPT, bizDesc = "修改机构信息")
	public Result modEnter(SysEnterInfo enterInfo) {
		Result res = new Result();

		int modCount = service.modEnter(enterInfo);

		if (modCount <= 0) {
			throw new ServiceException("修改机构数据数据失败");
		}

		return res;
	}

	/**
	 * @api {PUT} /crm/manage/delenter 逻辑删除一条机构信息
	 * @apiDescription 
	 * @apiName delModEnter
	 * @apiGroup Enterprise
	 *
	 * @apiParam {String} enterCode 机构代码
	 * @apiParam {String} enterName 机构名称
	 *
	 * @apiSuccess {Object} Response 返回值对象
	 */
	@RequestMapping(path = "/crm/manage/delenter", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.DEPT, bizDesc = "逻辑删除机构信息")
	public Result delModEnter(String enterCode) {
		Result res = new Result();

		SysEnterInfo enterInfo = new SysEnterInfo();
		enterInfo.setEnterCode(enterCode);
		enterInfo.setRecStat("1");

		int delModCount = service.delModEnter(enterInfo);

		if (delModCount <= 0) {
			throw new ServiceException("删除机构数据数据失败");
		}

		return res;
	}

	/**
	 * @api {DELETE} /crm/manage/enter 物理删除一条机构信息
	 * @apiDescription 
	 * @apiName delEnter
	 * @apiGroup Enterprise
	 *
	 * @apiParam {String} enterCode 机构代码
	 *
	 * @apiSuccess {Object} Response 返回值对象
	 */
	@RequestMapping(path = "/crm/manage/enter", method = RequestMethod.DELETE)
	@SysOprtLog(model = Module.DEPT, bizDesc = "物理删除单条机构信息")
	public Result delEnter(String enterCode) {
		Result res = new Result();
		System.out.println("物理删除单条机构数据，更换logger形式");

		int delCount = service.delEnter(enterCode);

		if (delCount > 0) {
			System.out.println("删除数据成功，更换logger形式");
		} else {
			throw new ServiceException("删除机构数据数据失败");
		}

		return res;
	}

	/**
	 * @api {GET} /crm/manage/getAllEntersOnOrder 顺序查询所有机构信息
	 * @apiName getAllEntersOnOrder
	 * @apiGroup Enterprise
	 * 
	 * @apiSuccess {Object} Response 返回值对象
	 */
	@RequestMapping(path = "/crm/manage/getAllEntersOnOrder", method = RequestMethod.GET)
	public Result getAllEntersOnOrder() {
		Result res = new Result();

		List<SysEnterInfo> enterList = service.getAllEntersOnOrder();

		res.setData(enterList);
		return res;
	}
}
