package cn.com.zsyk.crm.manage.web.controller.mngcenter.codemapper;

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
import cn.com.zsyk.crm.manage.entity.SysMsgSendDef;
import cn.com.zsyk.crm.manage.entity.SysCodeMapper;
import cn.com.zsyk.crm.manage.entity.bo.engine.SysMsgSendDetail;
import cn.com.zsyk.crm.manage.service.mngcenter.codemapper.CodeMapperService;

@RestController
public class CodeMapperCtrl {
	@Autowired
	private CodeMapperService codeMapperService;
	@Autowired
	private ObjectMapper om;

	/**
	 * @api {GET} /crm/manage/codemappermng/getOneCodeMapper 按条件查询一条码值映射
	 * @apiName getOneCodeMapper
	 * @apiGroup CodeMapper
	 *
	 * @apiParam {String} msgCode 码值映射代码
	 *
	 * @apiSuccess {SysCodeMapper} data 码值映射对象
	 */
	@RequestMapping(path = "/crm/manage/codemappermng/getOneCodeMapper", method = RequestMethod.GET)
	public Result getOneCodeMapper(String codeType, String codeVal, String extSysFlg, String extCodeType) {
		Result res = new Result();

		SysCodeMapper codeMapper = codeMapperService.getOneCodeMapper(codeType, codeVal, extSysFlg, extCodeType);
		res.setData(codeMapper);
		return res;
	}
	/**
	 * @api {GET} /crm/manage/codemappermng/getCodeMapperByEntity 按条件查询多条码值映射
	 * @apiName getCodeMapperByEntity
	 * @apiGroup CodeMapper
	 *
	 * @apiParam {SysCodeMapper} codeMapper 码值映射对象
	 *
	 * @apiSuccess {SysCodeMapper} data 码值映射列表
	 */
	@RequestMapping(path = "/crm/manage/codemappermng/getCodeMapperByEntity", method = RequestMethod.GET)
	public Result getCodeMapperByEntity(SysCodeMapper codeMapper) {
		Result res = new Result();
		System.out.println("获取多条码值映射数据。");
		//List<SysCodeMapper> list = codeMapperService.getCodeMapperByEntity(codeMapper);
		res.setData(codeMapperService.getCodeMapperByEntity(codeMapper));
		return res;
	}

	/**
	 * @api {POST} /crm/manage/codemappermng/addCodeMapper 新增码值映射
	 * @apiName addCodeMapper
	 * @apiGroup CodeMapper
	 *
	 * @apiParam {SysCodeMapper} codeMapper 码值映射对象
	 *
	 * @apiSuccess {int} data 新增记录条数
	 */
	@RequestMapping(path = "/crm/manage/codemappermng/addCodeMapper", method = RequestMethod.POST)
	public Result addCodeMapper(SysCodeMapper codeMapper) {
		Result res = new Result();

		codeMapper.setRecStat("0");
		int addCount = codeMapperService.addCodeMapper(codeMapper);
		if (addCount <= 0) {
			throw new ServiceException("新增码值映射数据失败！");
		}
		res.setData(addCount);
		return res;
	}

	/**
	 * @api {PUT} /crm/manage/codemappermng/modCodeMapper 修改码值映射
	 * @apiName modCodeMapper
	 * @apiGroup CodeMapper
	 *
	 * @apiParam {SysCodeMapper} codeMapper 码值映射对象
	 *
	 * @apiSuccess {int} data 修改记录条数
	 */
	@RequestMapping(path = "/crm/manage/codemappermng/modCodeMapper", method = RequestMethod.PUT)
	public Result modCodeMapper(SysCodeMapper codeMapper) {
		Result res = new Result();

		int updCount = codeMapperService.modCodeMapper(codeMapper);
		if (updCount <= 0) {
			throw new ServiceException("修改码值映射数据失败！");
		}
		res.setData(updCount);
		return res;
	}

	/**
	 * @api {DELETE} /crm/manage/codemappermng/delCodeMapper 删除码值映射
	 * @apiName delCodeMapper
	 * @apiGroup CodeMapper
	 *
	 * @apiParam {String} tplCode 码值映射代码
	 *
	 * @apiSuccess {int} data 删除记录条数
	 */
	@RequestMapping(path = "/crm/manage/codemappermng/delCodeMapper", method = RequestMethod.DELETE)
	public Result delCodeMapper(String codeType, String codeVal, String extSysFlg, String extCodeType) {
		Result res = new Result();

		int delCount = codeMapperService.delCodeMapper(codeType, codeVal, extSysFlg, extCodeType);
		if (delCount <= 0) {
			throw new ServiceException("删除码值映射数据失败！");
		}
		res.setData(delCount);
		return res;
	}
	/**
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * @api {DELETE} /crm/manage/codemappermng/delCodeMapperByKey 根据json串传递主键列表, 删除码值映射
	 * @apiName delCodeMapperByKey
	 * @apiGroup CodeMapper
	 *
	 * @apiParam {String} jsonStr json对象字符串(格式同List<SysCodeMapper>对象一致)
	 *
	 * @apiSuccess  {int} data 删除记录条数
	 */
	
	@RequestMapping(path = "/crm/manage/codemappermng/delCodeMapperByKey", method = RequestMethod.POST)
	public Result delCodeMapperByKey(@RequestBody String checkedRow) throws Exception {
		Result res = new Result();
		System.out.println("逻辑删除多条码值映射数据。");

		List<SysCodeMapper> msgList = om.readValue(checkedRow, new TypeReference<List<SysCodeMapper>>() {
		});

		// 删除的记录条数
		int delCount = 0;

		for (SysCodeMapper item : msgList) {

			// 执行删除操作
			int addCount = codeMapperService.delCodeMapper(item.getCodeType(), item.getCodeVal(), item.getExtSysFlg(), item.getExtCodeType());
			// 删除是否成功
			if (addCount > 0) {
				System.out.println("删除码值映射数据成功。");
			} else {
				throw new ServiceException("删除码值映射数据失败!");
			}
			delCount += addCount;
		}

		res.setData(delCount);
		res.setMessage("交易成功！");
		return res;
	}
}
