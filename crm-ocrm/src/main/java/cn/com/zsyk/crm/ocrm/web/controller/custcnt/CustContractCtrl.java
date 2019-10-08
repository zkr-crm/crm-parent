package cn.com.zsyk.crm.ocrm.web.controller.custcnt;

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
import cn.com.zsyk.crm.ocrm.entity.OcCustContract;
import cn.com.zsyk.crm.ocrm.service.custcnt.CustCntService;

@RestController
public class CustContractCtrl {

	@Autowired
	private CustCntService service;
	@Autowired
	private ObjectMapper om;
	@Autowired
	private CoreDaoImpl dao;
	

	Log log = LogUtil.getLogger(CustContractCtrl.class);

	/**
	 * @api {GET} /crm/ocrm/CustContractmng/getAllCustContracts 查询所有联系人列表（分页）
	 * @apiDescription 
	 * @apiName getAllCustContracts
	 * @apiGroup CustContractMng
	 *
	 * @apiSuccess {OcCustContract} data 返回值对象
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ocrm/CustContractmng/getAllCustContracts", method = RequestMethod.GET)
	public Result getAllCustContracts(OcCustContract custContract) {
		log.info(">>>>>>>>>>getAllCustContracts start<<<<<<<<<<");
		log = LogUtil.getLogger(CustContractCtrl.class);
		Result res = new Result();
		System.out.println("获取所有联系人列表。");
		PageBean p = dao.selectPageById("cn.com.zsyk.crm.ocrm.mapper.OcCustContractMapper.selectByName", custContract);
		res.setData(p);
		log.info(">>>>>>>>>>getAllCustContracts end<<<<<<<<<<");
		return res;
	}

	/**
	 * @api {GET} /crm/ocrm/CustContractmng/getAllCntr 查询所有联系人列表
	 * @apiDescription 
	 * @apiName getAllCntr
	 * @apiGroup CustContractMng
	 *
	 * @apiSuccess {OcCustContract} data 返回值对象
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ocrm/CustContractmng/getAllCntr", method = RequestMethod.GET)
	public Result getAllCntr(OcCustContract custContract) {
		log.info(">>>>>>>>>>getAllCntr start<<<<<<<<<<");
		log = LogUtil.getLogger(CustContractCtrl.class);
		Result res = new Result();
		System.out.println("获取所有联系人列表。");
		List<OcCustContract> p = service.getCustContractByName(custContract);
		res.setData(p);
		log.info(">>>>>>>>>>getAllCntr end<<<<<<<<<<");
		return res;
	}

	/**
	 * @api {GET} /crm/ocrm/CustContractmng/getCustContracts 查询一条联系人记录
	 * @apiDescription 
	 * @apiName getCustContract
	 * @apiGroup CustContractMng
	 *
	 * @apiParam {int} contractNo 联系人ID
	 *
	 * @apiSuccess {OcCustContract} data 联系人信息对象
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ocrm/CustContractmng/getCustContracts", method = RequestMethod.GET)
	public Result getCustContract(String contractNo) {
		Result res = new Result();
		System.out.println("获取单条联系人数据。");
		OcCustContract custContract = service.getOneCustContract(contractNo);
		res.setData(custContract);
		return res;
	}


	/**
	 * @api {POST} /crm/ocrm/CustContractmng/addCustContract 新增一条联系人记录
	 * @apiDescription 
	 * @apiName addCustContract
	 * @apiGroup CustContractMng
	 *
	 * @apiParam {OcCustContract} custContract 联系人信息对象
	 *
	 * @apiSuccess {int} data 新增记录条数
	 */
	@SuppressWarnings({"rawtypes" })
	@RequestMapping(path = "/crm/ocrm/CustContractmng/addCustContract", method = RequestMethod.POST)
	public Result addCustContract(OcCustContract custContract) {
		Result res = new Result();
		System.out.println("新增单条联系人数据。");

		int addCount = service.addCustContract(custContract);

		if (addCount > 0) {
			System.out.println("插入联系人数据成功。");
		} else {
			throw new ServiceException("新增联系人数据数据失败！");
		}

		return res;
	}

	/**
	 * @api {PUT} /crm/manage/CustContractmng/CustContract 更新一条联系人记录
	 * @apiDescription 
	 * @apiName modCustContract
	 * @apiGroup CustContractMng
	 *
	 * @apiParam {OcCustContract} CustContract 联系人信息对象
	 *
	 * @apiSuccess {int} data 更新记录条数
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ocrm/CustContractmng/CustContract", method = RequestMethod.PUT)
	public Result modCustContract(OcCustContract custContract) {
		Result res = new Result();
		System.out.println("修改单条联系人数据。");

		int modCount = service.modCustContract(custContract);

		if (modCount > 0) {
			res.success("修改联系人数据成功。");
		} else {
			res.fail("修改联系人数据数据失败！");
		}

		return res;
	}


	/**
	 * @api {DELETE} /crm/manage/CustContractmng/delCustContract 物理删除一条联系人记录
	 * @apiDescription 
	 * @apiName delCustContract
	 * @apiGroup CustContractMng
	 *
	 * @apiParam {String} contractNo 联系人ID
	 *
	 * @apiSuccess {int} data 删除成功条数
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ocrm/CustContractmng/delCustContract", method = RequestMethod.DELETE)
	public Result delCustContract(String contractNo) {
		Result res = new Result();

		int addCount = service.delCustContract(contractNo);

		if (addCount <= 0) {
			throw new ServiceException("删除联系人数据数据失败!");
		}

		res.setData(addCount);
		return res;
	}


	/**
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * @api {DELETE} /crm/manage/CustContractmng/delCustContractsByKey 根据json串传递主键列表，逻辑删除多条联系人记录
	 * @apiDescription 
	 * @apiName delCustContractsByKey
	 * @apiGroup CustContractMng
	 *
	 * @apiParam {String} jsonStr json对象字符串(格式同List<OcCustContract>对象一致)
	 *
	 * @apiSuccess {int} data 删除成功条数
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ocrm/CustContractmng/delCustContractsByKey", method = RequestMethod.DELETE)
	public Result delCustContractsByKey(@RequestBody String checkedRow) throws Exception {
		Result res = new Result();
		System.out.println("逻辑删除多条联系人数据。");

		List<OcCustContract> CustContractList = om.readValue(checkedRow, new TypeReference<List<OcCustContract>>() {
		});

		// 删除的记录条数
		int delCount = 0;

		for (OcCustContract item : CustContractList) {

			// 执行删除操作
			int addCount = service.delCustContract(item.getContractNo());
			// 删除是否成功
			if (addCount > 0) {
				System.out.println("删除联系人数据成功。");
			} else {
				throw new ServiceException("删除联系人数据数据失败!");
			}
			delCount += addCount;
		}

		res.setData(delCount);
		res.setMessage("交易成功！");
		return res;
	}

}
