package cn.com.zsyk.crm.manage.web.controller.mngcenter.parameter;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.manage.entity.SysParam;
import cn.com.zsyk.crm.manage.service.mngcenter.parameter.SysParamService;

@RestController
public class SysParamCtrl {
	@Autowired
	private SysParamService paramService;
	@Autowired
	private ObjectMapper om;
	
	/**
	 * @api {GET} /crm/manage/paramng/params 查询所有参数列表
	 * @apiDescription 
	 * @apiName getAllParams
	 * @apiGroup ParaMng
	 *
	 * @apiSuccess {SysParam} data 返回值对象
	 */
	@RequestMapping(path = "/crm/manage/paramng/params", method = RequestMethod.GET)
	public Result getAllParams() {
		Result res = new Result();
		System.out.println("获取所有参数列表。");
		List<SysParam> paramList = paramService.getAllParam();
		res.setData(paramList);
		return res;
	}

	/**
	 * @api {GET} /crm/manage/paramng/selectByEntity 按条件查询参数列表
	 * @apiDescription 
	 * @apiName getParamsByEntity
	 * @apiGroup ParaMng
	 *
	 * @apiParam {SysParam} sysParam 参数信息对象
	 *
	 * @apiSuccess {SysParam} data 参数信息列表
	 */
	@RequestMapping(path = "/crm/manage/paramng/selectByEntity", method = RequestMethod.GET)
	public Result getParamsByEntity(SysParam sysParam) {
		Result res = new Result();
		System.out.println(" 按条件查询参数列表。");
		List<SysParam> paramList = paramService.getParams(sysParam);
		res.setData(paramList);
		return res;
	}

	/**
	 * @api {GET} /crm/manage/paramng/param 查询一条参数记录
	 * @apiDescription 
	 * @apiName getParam
	 * @apiGroup ParaMng
	 *
	 * @apiParam {String} paramType 参数类型
	 * @apiParam {String} paramCode 参数值
	 *
	 * @apiSuccess {SysParam} data 参数信息对象
	 */
	@RequestMapping(path = "/crm/manage/paramng/param", method = RequestMethod.GET)
	public Result getParam(String paramType, String paramCode) {
		Result res = new Result();
		System.out.println("获取单条参数数据。");
		SysParam sysParam = paramService.getOne(paramType, paramCode);
		res.setData(sysParam);
		return res;
	}

	/**
	 * @api {POST} /crm/manage/paramng/param 新增一条参数记录
	 * @apiDescription 
	 * @apiName addParam
	 * @apiGroup ParaMng
	 *
	 * @apiParam {SysParam} sysParam 参数信息对象
	 *
	 * @apiSuccess {int} data 新增记录条数
	 */
	@RequestMapping(path = "/crm/manage/params/saveParam", method = RequestMethod.POST)
	public Result addParam(SysParam sysParam) {
		Result res = new Result();
		System.out.println("新增单条参数数据。");

		int addCount = paramService.addParam(sysParam);

		if (addCount > 0) {
			System.out.println("插入参数数据成功。");
		} else {
			throw new ServiceException("新增参数数据失败！");
		}

		return res;
	}

	/**
	 * @api {PUT} /crm/manage/paramng/param 更新一条参数记录
	 * @apiDescription 
	 * @apiName modParam
	 * @apiGroup ParaMng
	 *
	 * @apiParam {SysParam} sysParam 参数信息对象
	 *
	 * @apiSuccess {int} data 更新记录条数
	 */
	@RequestMapping(path = "/crm/manage/paramng/param", method = RequestMethod.PUT)
	public Result modParam(SysParam sysParam) {
		Result res = new Result();
		System.out.println("修改单条参数数据。");

		int modCount = paramService.modParam(sysParam);

		if (modCount > 0) {
			res.success("修改参数数据成功。");
		} else {
			res.fail("修改参数数据失败！");
		}

		return res;
	}


	/**
	 * @api {PUT} /crm/manage/paramng/delParam 逻辑删除一条参数记录
	 * @apiDescription 
	 * @apiName delModParam
	 * @apiGroup ParaMng
	 *
	 * @apiParam {String} paramType 参数类型
	 * @apiParam {String} paramCode 参数值
	 *
	 * @apiSuccess {int} data 逻辑删除记录条数
	 */
	@RequestMapping(path = "/crm/manage/paramng/delParam", method = RequestMethod.PUT)
	public Result delModParam(String paramType, String paramCode) {
		Result res = new Result();
		System.out.println("逻辑删除单条参数数据。");

		SysParam sysParam = paramService.getOne(paramType, paramCode);

		if (sysParam == null) {
			throw new ServiceException("删除参数数据数据失败");
		}

		int delCount = paramService.delModParam(sysParam);

		if (delCount > 0) {
			System.out.println("删除参数数据成功。");
		} else {
			throw new ServiceException("删除参数数据数据失败");
		}

		res.setData(delCount);
		return res;
	}

	/**
	 * @api {DELETE} /crm/manage/paramng/param 物理删除一条参数记录
	 * @apiDescription  
	 * @apiName delParam
	 * @apiGroup ParaMng
	 *
	 * @apiParam {String} paramType 参数类型
	 * @apiParam {String} paramCode 参数值
	 *
	 * @apiSuccess {int} data 删除成功条数
	 */
	@RequestMapping(path = "/crm/manage/paramng/param", method = RequestMethod.POST)
	public Result delParam(String paramType, String paramCode) {
		Result res = new Result();
		System.out.println("物理删除单条参数数据。");

		int addCount = paramService.delParam(paramType, paramCode);

		if (addCount > 0) {
			System.out.println("删除参数数据成功。");
		} else {
			throw new ServiceException("删除参数数据数据失败!");
		}

		res.setData(addCount);
		return res;
	}

	
	/**
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * @api {DELETE} /crm/manage/paramng/delParamsByKey 根据json串传递主键列表，物理删除多条参数记录
	 * @apiDescription 
	 * @apiName delParamsByKey
	 * @apiGroup ParaMng
	 *
	 * @apiParam {String} jsonStr json对象字符串(格式同List<SysParam>对象一致)
	 *
	 * @apiSuccess {int} data 删除成功条数
	 */
	@RequestMapping(path = "/crm/manage/paramng/delParamsByKey", method = RequestMethod.POST)
	public Result delParamsByKey(@RequestBody List<SysParam> paramList) throws Exception {
		Result res = new Result();
		System.out.println("逻辑删除多条参数数据。");

		// 删除的记录条数
		int delCount = 0;

		for (SysParam item : paramList) {

			// 执行删除操作
			int addCount = paramService.delParam(item.getParamType(),item.getParamCode());
			// 删除是否成功
			if (addCount > 0) {
				System.out.println("删除参数数据成功。");
			} else {
				throw new ServiceException("删除参数数据数据失败!");
			}
			delCount += addCount;
		}

		res.setData(delCount);
		res.setMessage("交易成功！");
		return res;
	}
}
