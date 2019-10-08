package cn.com.zsyk.crm.ocrm.web.controller.lifecycle;

import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.com.zsyk.crm.common.dao.CoreDaoImpl;
import cn.com.zsyk.crm.common.dto.PageBean;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.LogUtil;
import cn.com.zsyk.crm.common.util.RestUtil;
import cn.com.zsyk.crm.ocrm.entity.OcCustLifeCycleDef;
import cn.com.zsyk.crm.ocrm.service.lifecycle.CustLifeCycleDefService;

@RestController
public class custLifeCycleDefCtrl {

	@Autowired
	private CustLifeCycleDefService service;
	@Autowired
	private ObjectMapper om;
	@Autowired
	private RestUtil restUtil;
	@Autowired
	private CoreDaoImpl dao;
	

	Log log = LogUtil.getLogger(custLifeCycleDefCtrl.class);

	/**
	 * @api {GET} /crm/ocrm/CustLifeCycleDefmng/getAllCustLifeCycleDefs 查询所有客户生命周期定义列表
	 * @apiName getAllCustLifeCycleDefs
	 * @apiGroup CustLifeCycleDefMng
	 *
	 * @apiSuccess {OcCustLifeCycleDef} data 返回值对象
	 */
	@RequestMapping(path = "/crm/ocrm/CustLifeCycleDefmng/getAllCustLifeCycleDefs", method = RequestMethod.GET)
	public Result getAllCustLifeCycleDefs() {
		log.info(">>>>>>>>>>getAllCustLifeCycleDefs start<<<<<<<<<<");
		log = LogUtil.getLogger(custLifeCycleDefCtrl.class);
		Result res = new Result();
		System.out.println("获取所有客户生命周期定义列表。");
		PageBean p = dao.selectPageById("cn.com.zsyk.crm.ocrm.mapper.OcCustLifeCycleDefMapper.selectAll", null);
		res.setData(p);
		log.info(">>>>>>>>>>getAllCustLifeCycleDefs end<<<<<<<<<<");
		return res;
	}
	/**
	 * @api {GET} /crm/ocrm/CustLifeCycleDefmng/getCustLifeCycleDefs 查询一条客户生命周期定义记录
	 * @apiName getCustLifeCycleDef
	 * @apiGroup CustLifeCycleDefMng
	 *
	 * @apiParam {int} stageId 客户生命周期定义ID
	 *
	 * @apiSuccess {OcCustLifeCycleDef} data 客户生命周期定义信息对象
	 */
	@RequestMapping(path = "/crm/ocrm/CustLifeCycleDefmng/getCustLifeCycleDefs", method = RequestMethod.GET)
	public Result getCustLifeCycleDef(int stageId) {
		Result res = new Result();
		System.out.println("获取单条客户生命周期定义数据。");
		OcCustLifeCycleDef custLifeCycleDef = service.getOneCustLifeCycleDef(stageId);
		res.setData(custLifeCycleDef);
		return res;
	}


	/**
	 * @api {POST} /crm/ocrm/CustLifeCycleDefmng/addCustLifeCycleDef 新增一条客户生命周期定义记录
	 * @apiName addCustLifeCycleDef
	 * @apiGroup CustLifeCycleDefMng
	 *
	 * @apiParam {OcCustLifeCycleDef} custLifeCycleDef 客户生命周期定义信息对象
	 *
	 * @apiSuccess {int} data 新增记录条数
	 */
	@RequestMapping(path = "/crm/ocrm/CustLifeCycleDefmng/addCustLifeCycleDef", method = RequestMethod.POST)
	public Result addCustLifeCycleDef(OcCustLifeCycleDef custLifeCycleDef) {
		Result res = new Result();
		System.out.println("新增单条客户生命周期定义数据。");

		int addCount = service.addCustLifeCycleDef(custLifeCycleDef);

		if (addCount > 0) {
			System.out.println("插入客户生命周期定义数据成功。");
		} else {
			throw new ServiceException("新增客户生命周期定义数据数据失败！");
		}

		return res;
	}

	/**
	 * @api {PUT} /crm/manage/CustLifeCycleDefmng/CustLifeCycleDef 更新一条客户生命周期定义记录
	 * @apiName modCustLifeCycleDef
	 * @apiGroup CustLifeCycleDefMng
	 *
	 * @apiParam {OcCustLifeCycleDef} CustLifeCycleDef 客户生命周期定义信息对象
	 *
	 * @apiSuccess {int} data 更新记录条数
	 */
	@RequestMapping(path = "/crm/ocrm/CustLifeCycleDefmng/CustLifeCycleDef", method = RequestMethod.PUT)
	public Result modCustLifeCycleDef(OcCustLifeCycleDef custLifeCycleDef) {
		Result res = new Result();
		System.out.println("修改单条客户生命周期定义数据。");

		int modCount = service.modCustLifeCycleDef(custLifeCycleDef);

		if (modCount > 0) {
			res.success("修改客户生命周期定义数据成功。");
		} else {
			res.fail("修改客户生命周期定义数据数据失败！");
		}

		return res;
	}


	/**
	 * @api {DELETE} /crm/manage/CustLifeCycleDefmng/delCustLifeCycleDef 物理删除一条客户生命周期定义记录
	 * @apiName delCustLifeCycleDef
	 * @apiGroup CustLifeCycleDefMng
	 *
	 * @apiParam {String} stageId 客户生命周期定义ID
	 *
	 * @apiSuccess {int} data 删除成功条数
	 */
	@RequestMapping(path = "/crm/ocrm/CustLifeCycleDefmng/delCustLifeCycleDef", method = RequestMethod.DELETE)
	public Result delCustLifeCycleDef(int stageId) {
		Result res = new Result();

		int addCount = service.delCustLifeCycleDef(stageId);

		if (addCount <= 0) {
			throw new ServiceException("删除客户生命周期定义数据数据失败!");
		}

		res.setData(addCount);
		return res;
	}


	/**
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * @api {DELETE} /crm/manage/CustLifeCycleDefmng/delCustLifeCycleDefsByKey 根据json串传递主键列表，逻辑删除多条客户生命周期定义记录
	 * @apiName delCustLifeCycleDefsByKey
	 * @apiGroup CustLifeCycleDefMng
	 *
	 * @apiParam {String} jsonStr json对象字符串(格式同List<OcCustLifeCycleDef>对象一致)
	 *
	 * @apiSuccess {int} data 删除成功条数
	 */
	@RequestMapping(path = "/crm/ocrm/CustLifeCycleDefmng/delCustLifeCycleDefsByKey", method = RequestMethod.DELETE)
	public Result delCustLifeCycleDefsByKey(@RequestBody String checkedRow) throws Exception {
		Result res = new Result();
		System.out.println("逻辑删除多条客户生命周期定义数据。");

		List<OcCustLifeCycleDef> CustLifeCycleDefList = om.readValue(checkedRow, new TypeReference<List<OcCustLifeCycleDef>>() {
		});

		// 删除的记录条数
		int delCount = 0;

		for (OcCustLifeCycleDef item : CustLifeCycleDefList) {

			// 执行删除操作
			int addCount = service.delCustLifeCycleDef(item.getStageId());
			// 删除是否成功
			if (addCount > 0) {
				System.out.println("删除客户生命周期定义数据成功。");
			} else {
				throw new ServiceException("删除客户生命周期定义数据数据失败!");
			}
			delCount += addCount;
		}

		res.setData(delCount);
		res.setMessage("交易成功！");
		return res;
	}



}
