package cn.com.zsyk.crm.ocrm.web.controller.cnttrack;

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
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.LogUtil;
import cn.com.zsyk.crm.common.util.RestUtil;
import cn.com.zsyk.crm.ocrm.entity.OcContractTrack;
import cn.com.zsyk.crm.ocrm.service.cnttrack.CntTrackService;

@RestController
public class ContractTrackCtrl {

	@Autowired
	private CntTrackService service;
	@Autowired
	private ObjectMapper om;
	@Autowired
	private RestUtil restUtil;
	@Autowired
	private CoreDaoImpl dao;
	

	Log log = LogUtil.getLogger(ContractTrackCtrl.class);

	/**
	 * @api {GET} /crm/ocrm/ContractTrackmng/getAllContractTracks 根据contractCustNo查询所有联系人轨迹列表
	 * @apiDescription 
	 * @apiName getAllContractTracks
	 * @apiGroup ContractTrackMng
	 *
	 * @apiSuccess {OcContractTrack} data 返回值对象
	 */
	@RequestMapping(path = "/crm/ocrm/ContractTrackmng/getAllContractTracks", method = RequestMethod.GET)
	public Result getAllContractTracks(String contractCustNo) {
		log.info(">>>>>>>>>>getAllContractTracks start<<<<<<<<<<");
		log = LogUtil.getLogger(ContractTrackCtrl.class);
		Result res = new Result();
		System.out.println("获取所有联系人轨迹列表。");
		//PageBean p = dao.selectPageById("cn.com.zsyk.crm.ocrm.mapper.OcContractTrackMapper.selectByContractCustNo", contractCustNo);
		List<OcContractTrack> p = service.getCustContractByName(contractCustNo);
		res.setData(p);
		log.info(">>>>>>>>>>getAllContractTracks end<<<<<<<<<<");
		return res;
	}
	/**
	 * @api {GET} /crm/ocrm/ContractTrackmng/getContractTracks 查询一条联系人轨迹记录
	 * @apiDescription 
	 * @apiName getContractTrack
	 * @apiGroup ContractTrackMng
	 *
	 * @apiParam {int} trackCd 联系人轨迹ID
	 *
	 * @apiSuccess {OcContractTrack} data 联系人轨迹信息对象
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ocrm/ContractTrackmng/getContractTracks", method = RequestMethod.GET)
	public Result getContractTrack(String trackCd) {
		Result res = new Result();
		System.out.println("获取单条联系人轨迹数据。");
		OcContractTrack contractTrack = service.getOneContractTrack(trackCd);
		res.setData(contractTrack);
		return res;
	}


	/**
	 * @api {POST} /crm/ocrm/ContractTrackmng/addContractTrack 新增一条联系人轨迹记录
	 * @apiDescription 
	 * @apiName addContractTrack
	 * @apiGroup ContractTrackMng
	 *
	 * @apiParam {OcContractTrack} contractTrack 联系人轨迹信息对象
	 *
	 * @apiSuccess {int} data 新增记录条数
	 */
	@RequestMapping(path = "/crm/ocrm/ContractTrackmng/addContractTrack", method = RequestMethod.POST)
	public Result addContractTrack(OcContractTrack contractTrack) {
		Result res = new Result();
		System.out.println("新增单条联系人轨迹数据。");

		int addCount = service.addContractTrack(contractTrack);

		if (addCount > 0) {
			System.out.println("插入联系人轨迹数据成功。");
		} else {
			throw new ServiceException("新增联系人轨迹数据数据失败！");
		}

		return res;
	}

	/**
	 * @api {PUT} /crm/manage/ContractTrackmng/ContractTrack 更新一条联系人轨迹记录
	 * @apiDescription 
	 * @apiName modContractTrack
	 * @apiGroup ContractTrackMng
	 *
	 * @apiParam {OcContractTrack} ContractTrack 联系人轨迹信息对象
	 *
	 * @apiSuccess {int} data 更新记录条数
	 */
	@RequestMapping(path = "/crm/ocrm/ContractTrackmng/ContractTrack", method = RequestMethod.PUT)
	public Result modContractTrack(OcContractTrack contractTrack) {
		Result res = new Result();
		System.out.println("修改单条联系人轨迹数据。");

		int modCount = service.modContractTrack(contractTrack);

		if (modCount > 0) {
			res.success("修改联系人轨迹数据成功。");
		} else {
			res.fail("修改联系人轨迹数据数据失败！");
		}

		return res;
	}


	/**
	 * @api {DELETE} /crm/manage/ContractTrackmng/delContractTrack 物理删除一条联系人轨迹记录
	 * @apiDescription 
	 * @apiName delContractTrack
	 * @apiGroup ContractTrackMng
	 *
	 * @apiParam {String} trackCd 联系人轨迹ID
	 *
	 * @apiSuccess {int} data 删除成功条数
	 */
	@RequestMapping(path = "/crm/ocrm/ContractTrackmng/delContractTrack", method = RequestMethod.DELETE)
	public Result delContractTrack(String trackCd) {
		Result res = new Result();

		int addCount = service.delContractTrack(trackCd);

		if (addCount <= 0) {
			throw new ServiceException("删除联系人轨迹数据数据失败!");
		}

		res.setData(addCount);
		return res;
	}


	/**
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * @api {DELETE} /crm/manage/ContractTrackmng/delContractTracksByKey 根据json串传递主键列表，逻辑删除多条联系人轨迹记录
	 * @apiDescription 
	 * @apiName delContractTracksByKey
	 * @apiGroup ContractTrackMng
	 *
	 * @apiParam {String} jsonStr json对象字符串(格式同List<OcContractTrack>对象一致)
	 *
	 * @apiSuccess {int} data 删除成功条数
	 */
	@RequestMapping(path = "/crm/ocrm/ContractTrackmng/delContractTracksByKey", method = RequestMethod.DELETE)
	public Result delContractTracksByKey(@RequestBody String checkedRow) throws Exception {
		Result res = new Result();
		System.out.println("逻辑删除多条联系人轨迹数据。");

		List<OcContractTrack> ContractTrackList = om.readValue(checkedRow, new TypeReference<List<OcContractTrack>>() {
		});

		// 删除的记录条数
		int delCount = 0;

		for (OcContractTrack item : ContractTrackList) {

			// 执行删除操作
			int addCount = service.delContractTrack(item.getTrackCd());
			// 删除是否成功
			if (addCount > 0) {
				System.out.println("删除联系人轨迹数据成功。");
			} else {
				throw new ServiceException("删除联系人轨迹数据数据失败!");
			}
			delCount += addCount;
		}

		res.setData(delCount);
		res.setMessage("交易成功！");
		return res;
	}

}
