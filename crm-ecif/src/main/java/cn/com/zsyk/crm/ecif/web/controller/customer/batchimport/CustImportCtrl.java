package cn.com.zsyk.crm.ecif.web.controller.customer.batchimport;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import cn.com.zsyk.crm.ecif.bo.cust.ExportCustBaseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.util.DateUtil;
import cn.com.zsyk.crm.common.util.IdGenerator;
import cn.com.zsyk.crm.common.util.JsonUtil;
import cn.com.zsyk.crm.ecif.entity.EcCustPer;
import cn.com.zsyk.crm.ecif.entity.EcCustSaleChgTrace;
import cn.com.zsyk.crm.ecif.entity.EcImportLog;
import cn.com.zsyk.crm.ecif.mapper.EcCustSaleChgTraceMapper;
import cn.com.zsyk.crm.ecif.service.customer.importallot.CustSaleChgTraceService;
import cn.com.zsyk.crm.ecif.service.customer.importallot.CustomerImportService;
import cn.com.zsyk.crm.ecif.service.customer.baseinfo.CustService;
import cn.com.zsyk.crm.generator.EnumType;

@RestController
public class CustImportCtrl {
	@Autowired
	private CustomerImportService service;
	@Autowired
	private CustService custService;
	@Autowired
	private EcCustSaleChgTraceMapper ecCustSaleChgTraceMapper;
	@Autowired
	private CustSaleChgTraceService custSaleChgTraceService;
	//客户转交	
	/**
	 * @api {GET} /crm/ecif/cust/custDeliver 客户转交
	 * @apiName custDistribute
	 * @apiGroup cust
	 *
	 * @apiSuccess {EcImportLog} data 返回值对象
	 */
	@RequestMapping(path = "/crm/ecif/cust/custDeliver", method = RequestMethod.PUT)
	public Result custDeliver(EcCustSaleChgTrace custSaleChgTrace ,@RequestBody String custList) {
		Result res = new Result();

		//字符串转换
		List<EcCustPer> custAgentList = JsonUtil.parseArray(custList, EcCustPer.class);
		for(EcCustPer custPer:custAgentList) {
			custPer.setCustAgent("");
			String custNo = custService.updateCustAgent(custPer);
			// 存入轨迹
			custSaleChgTrace.setDealTime(new Date());
			custSaleChgTrace.setDealAction(EnumType.dealAction.deliver.value);//转交
			custSaleChgTrace.setCurrentAgent("");
			custSaleChgTrace.setCustNo(custNo);
			custSaleChgTraceService.addCustSaleChgTrace(custSaleChgTrace);
		}
		return res;
	}
	//客户分配
	/**
	 * @api {GET} /crm/ecif/cust/custDistribute 客户分配
	 * @apiName custDistribute
	 * @apiGroup cust
	 *
	 * @apiSuccess {EcImportLog} data 返回值对象
	 */
	@RequestMapping(path = "/crm/ecif/cust/custDistribute", method = RequestMethod.PUT)
	public Result custDistribute(EcCustSaleChgTrace custSaleChgTrace ,@RequestBody String custList) {
		Result res = new Result();

		//字符串转换
		List<EcCustPer> custAgentList = JsonUtil.parseArray(custList, EcCustPer.class);
		for(EcCustPer custPer:custAgentList) {
			custPer.setCustAgent(custSaleChgTrace.getCurrentAgent());
			String custNo = custService.updateCustAgent(custPer);
			// 存入轨迹
			custSaleChgTrace.setCustNo(custNo);
			custSaleChgTrace.setDealAction(EnumType.dealAction.distribute.value);//分配
			custSaleChgTraceService.addCustSaleChgTrace(custSaleChgTrace);
		}
		
		return res;
	}
	
	/**
	 * @api {GET} /crm/ecif/cust/accountFile 查询客户导入日志列表
	 * @apiName accountFile
	 * @apiGroup productmng
	 *
	 * @apiSuccess {EcImportLog} data 返回值对象
	 */
    @RequestMapping(value = "/crm/ecif/cust/accountFile",method = RequestMethod.GET)
    public void  accountFile(@RequestParam(value="baseinfo",required = true) ArrayList<ExportCustBaseInfo> baseinfo, HttpServletResponse response) throws Exception {
         response.setHeader("Content-Disposition", "attachment;filename=" +"客户导出信息.xls" );
         response.setContentType("application/octet-stream;charset=utf-8");
         service.exportExcel(baseinfo, response.getOutputStream());
   }
    
    /**
	 * @api {GET} /crm/ecif/cust/upload 上传客户
	 * @apiName uploadFile
	 * @apiGroup productmng
	 *
	 * @apiSuccess {succeedQry} 保存成功条数
	 */
	
	@RequestMapping(path = "/crm/ecif/cust/upload", method = RequestMethod.POST)
    public String uploadFile(@RequestParam(value = "file" , required = true) MultipartFile file,@RequestParam(value = "fileName" , required = true) String filename,@RequestParam(value = "custAgent" , required = true) String custAgent) throws Exception {
        //deal with file
		 //得到文件名
		String path=System.getProperty("user.dir")+"/";
      //  String filename=file.getOriginalFilename();
        
        InputStream inputStream = file.getInputStream();
        
        if(file.getSize()>0){
            try {
            	service.saveFileFromInputStream(file.getInputStream(),path,filename);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        else{
            throw new Exception("上传失败：上传文件不能为空");
        }
		String str= service.importExcel(filename,custAgent);
		return str;
    }
	
}
