package cn.com.zsyk.crm.ecif.service.product;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import cn.com.zsyk.crm.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import cn.com.zsyk.crm.common.dao.AbstractDao;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.IdGenerator;
import cn.com.zsyk.crm.ecif.entity.EcImportDets;
import cn.com.zsyk.crm.ecif.entity.EcImportLog;
import cn.com.zsyk.crm.ecif.entity.EcProduct;
import cn.com.zsyk.crm.ecif.mapper.EcProductMapper;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

@Service
@Transactional
public class ProductImportService {
	@Autowired
	private EcProductMapper ProductMapper;
	@Autowired
	private AbstractDao daoUtil;
	@Autowired
	private ImportLogService importLogService;
	

	/**
	 * 获得一条产品信息的方法
	 * 
	 * @param productCd
	 *            产品ID
	 * @return 产品信息
	 */
	public EcProduct getOneProduct(String productCd) {

		// 产品ID非空判断
		if (productCd == null || "".equals(productCd)) {
			throw new ServiceException("产品ID[" + productCd + "]不能为空！");
		}

		EcProduct productInfo = ProductMapper.selectByPrimaryKey(productCd);

		return productInfo;
	}
	
	/**
	 * 根据入参对象获取所有产品信息的方法
	 * 
	 * @return 所有产品信息的列表
	 */
	public List<EcProduct> getProductsByEntity(EcProduct record) {

		List<EcProduct> lstProduct = daoUtil.selectList("cn.com.zsyk.crm.ecif.mapper.EcProductMapper.selectByEntity",
				record);

		return lstProduct;
	}
	
	/**
	 * 新增一条产品信息
	 * 
	 * @param productCd
	 *            产品ID
	 * @param productname
	 *            产品名称
	 * @return 新增成功的记录条数
	 */
	public String addProduct(EcProduct p) {
		
		if (StringUtils.isEmpty(p.getProductCd())) {
			return "产品代码不能为空：产品代码[" + p.getProductCd() + "]";
		}
		if (StringUtils.isEmpty(p.getProductNam())) {
			return "产品名称不能为空：产品代码[" + p.getProductCd() + "]";
		}
		if (StringUtils.isEmpty(p.getProductTyp())) {
			return "产品分类不能为空：产品代码[" + p.getProductCd() + "]";
		}
		if (StringUtils.isEmpty(p.getProductVer())) {
			return "产品版本不能为空：产品代码[" + p.getProductCd() + "]";
		}
		if (StringUtils.isEmpty(p.getPremium())) {
			return "保费不能为空：产品代码[" + p.getProductCd() + "]";
		}
		if (StringUtils.isEmpty(p.getAmount())) {
			return "保额不能为空：产品代码[" + p.getProductCd() + "]";
		}
		if (StringUtils.isEmpty(p.getEffDate())) {
			return "生效日期不能为空：产品代码[" + p.getProductCd() + "]";
		}
		if (StringUtils.isEmpty(p.getExpDate())) {
			return "失效日期不能为空：产品代码[" + p.getProductCd() + "]";
		}
		
		// 存在判断
		EcProduct extTest = this.getOneProduct(p.getProductCd());
		if (extTest != null) {
   			return "产品信息已存在：产品代码[" + p.getProductCd() + "]";
		}


		int addCount = ProductMapper.insert(p);

		return "新增成功";
	}

   /**
    * 从Excel文件里读取数据保存到Vector里
    * @param fileName        Excel文件的名称
    * @return                Vector对象,里面包含从Excel文件里获取到的数据
 * @throws IOException 
 * @throws BiffException 
    */
   public int importExcel(String fileName) throws BiffException, IOException{
	   // String fileName = "导出产品.xls";
       // Vector<EcProduct> v = new Vector<EcProduct>();
       String path=System.getProperty("user.dir")+"/";
           Workbook book = Workbook.getWorkbook(new File(path+fileName));
           Sheet sheet = book.getSheet(0);        // 获得第一个工作表对象 
           int rows = sheet.getRows();
 
           // 存入成功数、失败数
           int succeedQty = 0;
           int failureQty = 0;
           int dataRows = rows - 1;
           // 导入编码
           String importCd = IdGenerator.getUUID();
           
           // 记录导入日志
           EcImportLog importLog = new EcImportLog();           
           importLog.setImportCd(importCd);
           importLog.setImportTime(new Date());
           importLog.setImportObjTyp("2");// 导入对象类型 2-产品
           importLog.setFileNam(fileName);
           importLog.setFileTyp(".xls");

           
           // 记录导入日志明细
           EcImportDets importDets = new EcImportDets();
    	   importDets.setImportCd(importCd);

           
           for(int i = 1; i < rows; i++) {
               // 失败原因
               String importReason = "";
               Cell [] cell = sheet.getRow(i);
               if(cell.length == 0) {
                   dataRows--;
                   continue;
               }
               System.out.println(sheet.getCell(2, i).getContents());
               System.out.println(sheet.getCell(5, i).getContents());
               System.out.println(sheet.getCell(6, i).getContents());
               System.out.println(sheet.getCell(2, i).getContents());
               // 产品代码 产品名称 产品分类 产品版本 责任 保费 保额 销售渠道 生效日期 失效日期
               // 产品分类 01-意外险 02-寿险 03-健康险 04-车险
               String productTyp = sheet.getCell(2, i).getContents();
               if(productTyp.equals("意外险")) {
            	   productTyp="01";
               }else if(productTyp.equals("寿险")) {
            	   productTyp="02";
               }else if(productTyp.equals("健康险")) {
            	   productTyp="03";
               }else if(productTyp.equals("车险")) {
            	   productTyp="04";
               }else{
            	   importReason = "产品分类输入异常";
               }
               // 销售渠道 01-个人代理 02-兼业代理
               String saleChnl = sheet.getCell(7, i).getContents();
               if(saleChnl.equals("个人代理")) {
            	   saleChnl="01";
               }else if(saleChnl.equals("兼业代理")) {
            	   saleChnl="02";
               }else{
            	   importReason = "销售渠道输入异常";            	   
               }
               EcProduct p = new EcProduct();              
               p.setProductCd(sheet.getCell(0, i).getContents());
               p.setProductNam(sheet.getCell(1, i).getContents());
               p.setProductTyp(productTyp);
               p.setProductVer(sheet.getCell(3, i).getContents());
               p.setDuty(sheet.getCell(4, i).getContents());
               p.setPremium(new BigDecimal(sheet.getCell(5, i).getContents()));
               p.setAmount(new BigDecimal(sheet.getCell(6, i).getContents()));
               p.setSaleChnl(saleChnl);
               p.setEffDate(sheet.getCell(8, i).getContents());
               p.setExpDate(sheet.getCell(9, i).getContents());
               p.setTimeStamp(DateUtil.nowDateTimeStamp());
               //v.add(p);
               // 存入产品表
               int count = 0;
               try {
            	   if(importReason.equals("")) {
                	   importReason = addProduct(p);
            	   }
            	   if (importReason.equals("新增成功")) {
                	   succeedQty ++;
                	   importDets.setImportSts("1");// 1-完成 0-失败
            	   }else {
                	   failureQty ++;
                	   importDets.setImportSts("0");// 1-完成 0-失败
            	   }
                   // 记录导入日志明细
                   importDets.setImportDetCd(IdGenerator.getUUID());
                   importDets.setImportContent(p.toString());
                   importDets.setImportReason(importReason);
                   importLogService.addImportDets(importDets);
               }catch(Exception e) {
            	   failureQty ++;
            	   importDets.setImportSts("0");// 1-完成 0-失败
                   // 记录导入日志明细
                   importDets.setImportDetCd(IdGenerator.getUUID());
                   importDets.setImportContent(p.toString());
                	   importReason = "存入数据库异常";
                   importDets.setImportReason(importReason);
                   importLogService.addImportDets(importDets);
            	   continue;
               }
           }
           
           // 记录导入日志
           if(dataRows == succeedQty) {
               importLog.setImportSts("0");// 导入状态 0-全部成功 1-部分成功 2-全部失败
           }else if (dataRows == failureQty) {
               importLog.setImportSts("2");// 导入状态 0-全部成功 1-部分成功 2-全部失败
           }else {
               importLog.setImportSts("1");// 导入状态 0-全部成功 1-部分成功 2-全部失败
           }    
           importLog.setFileTtlRow(dataRows);
           importLog.setFailureQty(failureQty);
           importLog.setSucceedQty(succeedQty);
           importLogService.addImportLog(importLog);
           
           book.close();
           return succeedQty;

   }		
   /**保存文件
    * @param stream
    * @param path
    * @param filename
    * @throws IOException
    */
   public void saveFileFromInputStream(InputStream stream,String path,String filename) throws IOException
   {      
       FileOutputStream fs=new FileOutputStream( path + filename);
       byte[] buffer =new byte[1024*1024];
       int bytesum = 0;
       int byteread = 0; 
       while ((byteread=stream.read(buffer))!=-1)
       {
          bytesum+=byteread;
          fs.write(buffer,0,byteread);
          fs.flush();
       } 
       fs.close();
       stream.close();      
   }       
}
