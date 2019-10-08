package cn.com.zsyk.crm.ecif.web.controller.customer.blacklist;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.com.zsyk.crm.common.annotation.SysOprtLog;
import cn.com.zsyk.crm.common.annotation.SysOprtLog.Module;
import cn.com.zsyk.crm.common.dao.CoreDaoImpl;
import cn.com.zsyk.crm.common.dto.PageBean;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.util.LogUtil;
import cn.com.zsyk.crm.ecif.bo.cust.PerCustList;
import cn.com.zsyk.crm.ecif.entity.EcCustPer;
import cn.com.zsyk.crm.ecif.entity.EcImportLog;
import cn.com.zsyk.crm.ecif.entity.EcProduct;
import cn.com.zsyk.crm.ecif.service.customer.baseinfo.CustService;
import cn.com.zsyk.crm.ecif.service.customer.blacklist.BlackListImportLogService;
import cn.com.zsyk.crm.ecif.service.customer.blacklist.BlackListImportService;
import cn.com.zsyk.crm.ecif.service.customer.blacklist.BlacklistService;
import cn.com.zsyk.crm.ecif.web.controller.product.ImportLogCtrl;

@RestController
public class BlacklistCtrl {
	@Autowired
	BlacklistService blacklistService;
	@Autowired
	private CustService custService;
	@Autowired
	private ObjectMapper om;
	Log log = LogUtil.getLogger(BlacklistCtrl.class);
	@Autowired
	private BlackListImportLogService service;
	@Autowired
	private CoreDaoImpl dao;
	@Autowired
	private BlackListImportService bkservice;


	/**
	 * @api {GET} /crm/ecif/cust/blacklist 查询客户黑名单列表(N)
	 * @apiName getAllBlacklist
	 * @apiGroup Customer
	 *
	 * @apiSuccess {Object} Response 返回值对象
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ecif/cust/blacklist", method = RequestMethod.GET)
	public Result getAllBlacklist() {
		Result res = new Result();
		res.setData(blacklistService.selectBlackList());
		return res;
	}

	/**
	 * @api {GET} /crm/ecif/cust/setBlacklist 取消客户黑名单(Y)
	
	 * @apiName getAllBlacklist
	 * @apiGroup Customer
	 *
	 * @apiSuccess {Object} Response 返回值对象
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ecif/cust/cancelBlacklist", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.CUSTOMER, bizDesc="取消客户黑名单")
	public Result cancelBlacklist(String custNo, String blacklistSts, String reason,String blacklistType) {
		Result res = new Result();
		blacklistService.setBlackList(custNo, blacklistSts, reason,blacklistType);
		res.setData(true);
		return res;
	}
	/**
	 * @SysOprtLog(model = Module.CUSTOMER, bizDesc="设置客户黑名单")
	 * @param checkedRow
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(path = "/crm/ecif/cust/setBlacklist", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.CUSTOMER, bizDesc="设置客户黑名单")
	public Result setBlacklist(@RequestBody String checkedRow) throws Exception {
		Result res = new Result();
		System.out.println("设置黑名单");

		List<EcCustPer> ecCustPerList = om.readValue(checkedRow, new TypeReference<List<EcCustPer>>() {
		});
		String reason ="";
		String blacklistType = "";
		for (EcCustPer item : ecCustPerList) {
			
			if(item.getRegiReason() == null || item.getRegiReason() =="" || reason != "") {
				
			}else {
				reason = item.getRegiReason();
				blacklistType = item.getBlacklistFlg();
			}	
		blacklistService.setBlackList(item.getCustNo(), "0", reason,blacklistType);
			
		}
		
		res.setData(true);
		return res;
		
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ecif/cust/blackperCustList", method = RequestMethod.GET)
	public Result getPerCustList(PerCustList custper) {
		Result res = new Result();
		res.setData(custService.selectBlackPerCustList(custper));
		return res;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ecif/cust/NotBlackperCustList", method = RequestMethod.GET)
	public Result getNotBlackPerCustList(EcCustPer custper) {
		Result res = new Result();
		res.setData(custService.selectNotBlackPerCustList(custper));
		return res;
	}
	
	/**
	 * @api {GET} /crm/ecif/backlist/importLogByEntityForBlack 查询产品导入日志列表
	 * @apiName getImportLogByEntityForBlack
	 * @apiGroup productmng
	 *
	 * @apiSuccess {EcImportLog} data 返回值对象
	 */
	@RequestMapping(path = "/crm/ecif/backlist/importLogByEntity", method = RequestMethod.GET)
	public Result getImportLogByEntityForBlack(String importObjTyp) {
		log.info(">>>>>>>>>>getImportLogByEntityForBlack start<<<<<<<<<<");
		log = LogUtil.getLogger(ImportLogCtrl.class);
		Result res = new Result();
		System.out.println("获取产品导入日志列表。");
		PageBean p = dao.selectPageById("cn.com.zsyk.crm.ecif.mapper.EcImportLogMapper.selectByimportObjTyp",importObjTyp);// 2-产品
		res.setData(p);
		log.info(">>>>>>>>>>getImportLogByEntityForBlack end<<<<<<<<<<");
		return res;
	}
	
	/**
	 * @api {GET} /crm/ecif/backlist/deleteLog 删除产品导入日志
	 * @apiName deleteLog
	 * @apiGroup productmng
	 *
	 * @apiSuccess {EcImportLog} data 返回值对象
	 */
	@RequestMapping(path = "/crm/ecif/backlist/deleteLog", method = RequestMethod.DELETE)
	public Result deleteLogForBlack(String importCd) {
		log.info(">>>>>>>>>>getImportLogByEntityForBlack start<<<<<<<<<<");
		log = LogUtil.getLogger(ImportLogCtrl.class);
		Result res = new Result();
		service.delImportDets(importCd);
		service.delImportLog(importCd);
		System.out.println("删除产品导入日志。");
		log.info(">>>>>>>>>>getImportLogByEntityForBlack end<<<<<<<<<<");
		return res;
	}
	
	/**
	 * @api {GET} /crm/ecif/productmng/accountFile 查询产品导入日志列表
	 * @apiName accountFile
	 * @apiGroup productmng
	 *
	 * @apiSuccess {EcImportLog} data 返回值对象
	 */
    @RequestMapping(value = "/crm/ecif/blacklist/accountFile",method = RequestMethod.GET)
    public void  accountFileForBlack(EcImportLog importLog, String importDetsSts, HttpServletResponse response) throws Exception {
         response.setHeader("Content-Disposition", "attachment;filename=" +importLog.getFileNam() );
         response.setContentType("application/octet-stream;charset=utf-8");
         service.exportExcel(importLog, importDetsSts, response.getOutputStream());
   }
    
    @RequestMapping(path = "/crm/ecif/blacklist/productByEntity", method = RequestMethod.GET)
	public Result getProductsByEntity(EcProduct record) {
		log.info(">>>>>>>>>>getProductsByEntity start<<<<<<<<<<");
		/*log = LogUtil.getLogger(ProductImportCtrl.class);*/
		Result res = new Result();
		System.out.println("获取产品列表。");
		Map param = new HashMap();
		param.put("productCd", record.getProductCd());
		param.put("productNam", record.getProductNam());
		PageBean p = dao.selectPageById("cn.com.zsyk.crm.ecif.mapper.EcProductMapper.selectByEntity",param);
		res.setData(p);
		log.info(">>>>>>>>>>getProductsByEntity end<<<<<<<<<<");
		return res;
	}
	/**
	 * @api {GET} /crm/ecif/blacklist/upload 上传产品
	 * @apiName uploadFile
	 * @apiGroup productmng
	 *
	 * @apiSuccess {succeedQry} 保存成功条数
	 */
	
	@RequestMapping(path = "/crm/ecif/blacklist/upload", method = RequestMethod.POST)
    public int uploadFileForBlack(@RequestParam(value = "file" , required = true) MultipartFile file,@RequestParam(value = "fileName" , required = true) String filename) throws Exception {
        //deal with file
		 //得到文件名
        //String filename=file.getOriginalFilename();
		String path=System.getProperty("user.dir")+"/";
        InputStream inputStream = file.getInputStream();
        
        if(file.getSize()>0){
            try {
            	bkservice.saveFileFromInputStream(file.getInputStream(),path,filename);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        else{
            throw new Exception("上传失败：上传文件不能为空");
        }
		int succeedQry = bkservice.importExcel(filename);
		return succeedQry;
    }

}
