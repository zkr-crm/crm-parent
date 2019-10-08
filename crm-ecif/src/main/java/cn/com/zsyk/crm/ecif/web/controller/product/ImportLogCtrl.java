package cn.com.zsyk.crm.ecif.web.controller.product;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.StringBufferInputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.com.zsyk.crm.common.dao.CoreDaoImpl;
import cn.com.zsyk.crm.common.dto.PageBean;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.util.LogUtil;
import cn.com.zsyk.crm.ecif.entity.EcImportLog;
import cn.com.zsyk.crm.ecif.service.product.ImportLogService;


@RestController
public class ImportLogCtrl {
	@Autowired
	private ObjectMapper om;
	Log log = LogUtil.getLogger(ImportLogCtrl.class);
	@Autowired
	private ImportLogService service;
	@Autowired
	private CoreDaoImpl dao;

	/**
	 * @api {GET} /crm/ecif/productmng/importLogByEntity 查询产品导入日志列表
	 * @apiName getImportLogByEntity
	 * @apiGroup productmng
	 *
	 * @apiSuccess {EcImportLog} data 返回值对象
	 */
	@RequestMapping(path = "/crm/ecif/productmng/importLogByEntity", method = RequestMethod.GET)
	public Result getImportLogByEntity(String importObjTyp) {
		log.info(">>>>>>>>>>getImportLogByEntity start<<<<<<<<<<");
		log = LogUtil.getLogger(ImportLogCtrl.class);
		Result res = new Result();
		System.out.println("获取产品导入日志列表。");
		PageBean p = dao.selectPageById("cn.com.zsyk.crm.ecif.mapper.EcImportLogMapper.selectByimportObjTyp",importObjTyp);// 2-产品
		res.setData(p);
		log.info(">>>>>>>>>>getImportLogByEntity end<<<<<<<<<<");
		return res;
	}
	
	/**
	 * @api {GET} /crm/ecif/productmng/deleteLog 删除产品导入日志
	 * @apiName deleteLog
	 * @apiGroup productmng
	 *
	 * @apiSuccess {EcImportLog} data 返回值对象
	 */
	@RequestMapping(path = "/crm/ecif/productmng/deleteLog", method = RequestMethod.DELETE)
	public Result deleteLog(String importCd) {
		log.info(">>>>>>>>>>getImportLogByEntity start<<<<<<<<<<");
		log = LogUtil.getLogger(ImportLogCtrl.class);
		Result res = new Result();
		service.delImportDets(importCd);
		service.delImportLog(importCd);
		System.out.println("删除产品导入日志。");
		log.info(">>>>>>>>>>getImportLogByEntity end<<<<<<<<<<");
		return res;
	}
	
	/**
	 * @api {GET} /crm/ecif/productmng/accountFile 查询产品导入日志列表
	 * @apiName accountFile
	 * @apiGroup productmng
	 *
	 * @apiSuccess {EcImportLog} data 返回值对象
	 */
    @RequestMapping(value = "/crm/ecif/productmng/accountFile",method = RequestMethod.GET)
    public void  accountFile(EcImportLog importLog, String importDetsSts, HttpServletResponse response) throws Exception {
         response.setHeader("Content-Disposition", "attachment;filename=" +importLog.getFileNam() );
         response.setContentType("application/octet-stream;charset=utf-8");
         service.exportExcel(importLog, importDetsSts, response.getOutputStream());
   }
    
}
