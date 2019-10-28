package cn.com.zsyk.crm.ecif.service.product;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.zsyk.crm.common.dao.AbstractDao;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.IdGenerator;
import cn.com.zsyk.crm.ecif.entity.EcImportDets;
import cn.com.zsyk.crm.ecif.entity.EcImportLog;
import cn.com.zsyk.crm.ecif.entity.EcProduct;
import cn.com.zsyk.crm.ecif.mapper.EcImportDetsMapper;
import cn.com.zsyk.crm.ecif.mapper.EcImportLogMapper;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

@Service
@Transactional
public class ImportLogService {
	@Autowired
	private EcImportLogMapper importLogMapper;
	@Autowired
	private EcImportDetsMapper importDetsMapper;
	@Autowired
	private AbstractDao daoUtil;

	/**
	 * 根据导入对象类型获得多条日志的方法
	 * 
	 * @param importCd
	 *            导入对象类型
	 * @return EcImportLog
	 */
	public List<EcImportLog> getImportLog(String iImportObjTyp) {

		//导入对象类型非空判断
		if (iImportObjTyp == null || "".equals(iImportObjTyp)) {
			throw new ServiceException("导入对象类型[" + iImportObjTyp + "]不能为空！");
		}

		List<EcImportLog> logInfo = daoUtil.selectList("cn.com.zsyk.crm.ecif.mapper.EcImportLogMapper.selectByimportObjTyp",
				iImportObjTyp);

		return logInfo;
	}

	/**
	 * 根据日志编号获得多条详细日志的方法
	 * 
	 * @param importCd
	 *            日志编号
	 * @return EcImportLog
	 */
	public List<EcImportDets> getImportDets(EcImportDets importDets) {

		// 产品ID非空判断
		if (importDets.getImportCd() == null || "".equals(importDets.getImportCd())) {
			throw new ServiceException("日志编号[" + importDets.getImportCd() + "]不能为空！");
		}
		// importSts
		
		List<EcImportDets> logInfo = daoUtil.selectList("cn.com.zsyk.crm.ecif.mapper.EcImportDetsMapper.selectByImportCd",
				importDets);

		// 存入excel

		return logInfo;
	}
	
	/**
	 * 新增一条日志信息
	 * 
	 * @param EcImportLog
	 * 
	 * @return 新增成功的记录条数
	 */
	public int addImportLog(EcImportLog logInfo) {

		if (logInfo == null) {
			throw new ServiceException("输入信息不能为空！");
		}
		// UUID
		//logInfo.setImportCd(IdGenerator.getUUID());

		int addCount = importLogMapper.insert(logInfo);

		return addCount;
	}

	/**
	 * 新增一条日志详细信息
	 * 
	 * @param EcImportLog
	 * 
	 * @return 新增成功的记录条数
	 */
	public int addImportDets(EcImportDets DetsInfo) {

		if (DetsInfo == null) {
			throw new ServiceException("输入信息不能为空！");
		}
		// UUID
		//DetsInfo.setImportCd(IdGenerator.getUUID());

		int addCount = importDetsMapper.insert(DetsInfo);

		return addCount;
	}
	
	/**
	 * 删除一条日志详细信息
	 * 
	 * @param EcImportLog
	 * 
	 * @return 删除成功的记录条数
	 */
	public void delImportDets(String importCd) {

		if (importCd == null) {
			throw new ServiceException("输入信息不能为空！");
		}

		daoUtil.selectList("cn.com.zsyk.crm.ecif.mapper.EcImportDetsMapper.deleteByImportCd",importCd);

	}
	/**
	 * 删除一条日志信息
	 * 
	 * @param EcImportLog
	 * 
	 * @return 删除成功的记录条数
	 */
	public void delImportLog(String importCd) {

		if (importCd == null) {
			throw new ServiceException("输入信息不能为空！");
		}

		importLogMapper.deleteByPrimaryKey(importCd);

	}

	 /**
   * 导出数据为XLS格式
   * @param fileName        文件的名称，可以设为绝对路径，也可以设为相对路径
   * @param content        数据的内容
	 * @throws Exception 
   */
  public void  exportExcel(EcImportLog importLog, String importDetsSts, OutputStream out) throws Exception {
	   String fileName = importLog.getFileNam();
      WritableWorkbook wwb;
          wwb = Workbook.createWorkbook(out);
          WritableSheet ws = wwb.createSheet("产品导入", 10);        // 创建一个工作表
          //    设置单元格的文字格式
          WritableFont wf = new WritableFont(WritableFont.ARIAL,12,WritableFont.NO_BOLD,false,
                  UnderlineStyle.NO_UNDERLINE,Colour.BLUE);
          WritableCellFormat wcf = new WritableCellFormat(wf);
          wcf.setVerticalAlignment(VerticalAlignment.CENTRE); 
          wcf.setAlignment(Alignment.CENTRE); 
          ws.setRowView(1, 500);

          // 产品代码 产品名称 产品分类 产品版本 责任 保费 保额 销售渠道 生效日期 失效日期 失败原因
          ws.addCell(new Label(0, 0,"产品代码", wcf));
          ws.addCell(new Label(1, 0,"产品名称", wcf));
          ws.addCell(new Label(2, 0,"产品分类", wcf));
          ws.addCell(new Label(3, 0,"产品版本", wcf));
          ws.addCell(new Label(4, 0,"责任", wcf));
          ws.addCell(new Label(5, 0,"保费", wcf));
          ws.addCell(new Label(6, 0,"保额", wcf));
          ws.addCell(new Label(7, 0,"销售渠道", wcf));
          ws.addCell(new Label(8, 0,"生效日期", wcf));
          ws.addCell(new Label(9, 0,"失效日期", wcf));
          ws.addCell(new Label(10, 0,"原因", wcf));

          //    填充数据的内容
          List<EcProduct> content = new ArrayList();
          List<String> reason = new ArrayList();
          EcImportDets importDets = new EcImportDets();
          importDets.setImportCd(importLog.getImportCd());
          importDets.setImportSts(importDetsSts);
          List<EcImportDets> retrunImportDets= getImportDets(importDets);
          for(EcImportDets log : retrunImportDets) {
        	  String prdStr = log.getImportContent();
        	// 产品分类 01-意外险 02-寿险 03-健康险 04-车险
              String productTyp = prdStr.substring(prdStr.indexOf("productTyp")+"productTyp".length()+1, prdStr.indexOf("productVer")-2);
              if(productTyp.equals("01")) {
           	   productTyp="意外险";
              }else if(productTyp.equals("02")) {
           	   productTyp="寿险";
              }else if(productTyp.equals("03")) {
           	   productTyp="健康险";
              }else if(productTyp.equals("04")) {
           	   productTyp="车险";
              }
              // 销售渠道 01-个人代理 02-兼业代理
              String saleChnl = prdStr.substring(prdStr.indexOf("saleChnl")+"saleChnl".length()+1, prdStr.indexOf("effDate")-2);
              if(saleChnl.equals("个人代理")) {
           	   saleChnl="01";
              }else if(saleChnl.equals("兼业代理")) {
           	   saleChnl="02";
              }
        	  EcProduct product = new EcProduct();
        	  product.setProductCd(prdStr.substring(prdStr.indexOf("productCd")+"productCd".length()+1, prdStr.indexOf("productNam")-2));
        	  product.setProductNam(prdStr.substring(prdStr.indexOf("productNam")+"productNam".length()+1, prdStr.indexOf("productTyp")-2));
        	  product.setProductTyp(productTyp);
        	  product.setProductVer(prdStr.substring(prdStr.indexOf("productVer")+"productVer".length()+1, prdStr.indexOf("duty")-2));
        	  product.setDuty(prdStr.substring(prdStr.indexOf("duty")+"duty".length()+1, prdStr.indexOf("premium")-2));
        	  product.setPremium(new BigDecimal(prdStr.substring(prdStr.indexOf("premium")+"premium".length()+1, prdStr.indexOf("amount")-2)));
        	  product.setAmount(new BigDecimal(prdStr.substring(prdStr.indexOf("amount")+"amount".length()+1, prdStr.indexOf("saleChnl")-2)));
        	  product.setSaleChnl(saleChnl);
        	  product.setEffDate(prdStr.substring(prdStr.indexOf("effDate")+"effDate".length()+1, prdStr.indexOf("expDate")-2));
        	  product.setExpDate(prdStr.substring(prdStr.indexOf("expDate")+"expDate".length()+1, prdStr.indexOf("createDate")-2));
//        	  productCd=12346, productNam=测试, productTyp=3, productVer=2, duty=身故、伤残, 
//        			  premium=2334, amount=3334.2, saleChnl=微信, effDate=20180102, expDate=20990102, createDate
        	  content.add(product);
        	  reason.add(log.getImportReason());
          }
          
          EcProduct[] p = new EcProduct[content.size()];
          String[] r = new String[reason.size()];
          for (int i = 0; i < content.size(); i++){
              if(i == 0)
                  wcf = new WritableCellFormat();
              p[i] = (EcProduct)content.get(i);
              r[i] = reason.get(i);
              ws.addCell(new Label(0, i+1, p[i].getProductCd(), wcf));
              ws.addCell(new Label(1, i+1, p[i].getProductNam(), wcf));
              ws.addCell(new Label(2, i+1, p[i].getProductTyp(), wcf));
              ws.addCell(new Label(3, i+1, p[i].getProductVer(), wcf));
              ws.addCell(new Label(4, i+1, p[i].getDuty(), wcf));
              ws.addCell(new Label(5, i+1, p[i].getPremium().toString(), wcf));
              ws.addCell(new Label(6, i+1, p[i].getAmount().toString(), wcf));
              ws.addCell(new Label(7, i+1, p[i].getSaleChnl(), wcf));
              ws.addCell(new Label(8, i+1, p[i].getEffDate(), wcf));
              ws.addCell(new Label(9, i+1, p[i].getExpDate(), wcf));
              ws.addCell(new Label(10, i+1, r[i], wcf));
          }
          wwb.write();
          wwb.close();
  }


}
