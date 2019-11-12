package cn.com.zsyk.crm.ecif.service.customer.importallot;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.com.zsyk.crm.ecif.entity.*;
import cn.com.zsyk.crm.ecif.mapper.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.zsyk.crm.common.util.DateUtil;
import cn.com.zsyk.crm.common.util.IdGenerator;
import cn.com.zsyk.crm.common.util.JsonUtil;
import cn.com.zsyk.crm.ecif.bo.cust.PerCustBaseInfo;
import cn.com.zsyk.crm.ecif.service.customer.portrait.CustCareerService;
import cn.com.zsyk.crm.ecif.service.customer.portrait.CustCertService;
import cn.com.zsyk.crm.ecif.service.customer.portrait.CustContactService;
import cn.com.zsyk.crm.ecif.service.customer.portrait.CustEduService;
import cn.com.zsyk.crm.ecif.service.customer.portrait.CustTagService;
import cn.com.zsyk.crm.ecif.service.product.ImportLogService;
import cn.com.zsyk.crm.generator.EnumType;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
@Service
@Transactional
public class CustomerImportService {
	@Autowired
	private EcCustSaleChgTraceMapper ecCustSaleChgTraceMapper;
	@Autowired
	private ImportLogService importLogService;
	@Autowired
	EcCustPerMapper ecCustPerMapper;
	@Autowired
	EcCustEntMapper ecCustEntMapper;
	@Autowired
	EcCustNameMapper ecCustNameMapper;
	@Autowired
	CustTagService custTagService;
	@Autowired
	private CustContactService custContactService;
	@Autowired
	private CustCertService custCertService;
	@Autowired
	EcCustProposalMapper ecCustProposalMapper;
	@Autowired
	private CustEduService custEduService;
	@Autowired
	CustCareerService custCareerService;
    @Autowired
    private EcCustManagerMapper ecCustManagerMapper;

	/**
	 * 客户分配
	 * 
	 * @param productCd
	 *            客户ID
	 * @param productname
	 *            客户名称
	 * @return 新增成功的记录条数
	 * @throws Exception 
	 */
	@SuppressWarnings("unused")
	private void custdeliver(EcCustPer custPer,EcCustSaleChgTrace custSaleChgTrace) {
	}
	
	
	/**
	 * 客户转交
	 * 
	 * @param productCd
	 *            客户ID
	 * @param productname
	 *            客户名称
	 * @return 新增成功的记录条数
	 * @throws Exception 
	 */
	
	/**
	 * 新增一条客户信息
	 * 
	 * @param productCd
	 *            客户ID
	 * @param productname
	 *            客户名称
	 * @return 新增成功的记录条数
	 * @throws Exception 
	 */
	public String addCust(PerCustBaseInfo perCustBaseInfo) throws Exception {
		
		if (StringUtils.isEmpty(perCustBaseInfo.getCustTyp())) {
			return "客户类型不能为空";
		}

//		if (StringUtils.isEmpty(perCustBaseInfo.getSex())) {
//			return  "性别不能为空";
//		}

		if (StringUtils.isEmpty(perCustBaseInfo.getCustName())) {
			return  "姓名不能为空";
		}

        if (StringUtils.isEmpty(perCustBaseInfo.getPhoneNumber())) {
            return  "电话号码不能为空";
        }

        if (StringUtils.isEmpty(perCustBaseInfo.getCustSource())) {
            return  "客户来源不能为空";
        }



		//String custNo = custService.addPerCustInfo(perCustBaseInfo);
		/** 基本信息 */
		// 客户号
		String custNo = "C"+String.valueOf(IdGenerator.getDistributedID()).substring(8,18);
		perCustBaseInfo.setCustNo(custNo);
		EcCustPer ecCustPer = new EcCustPer();
		// 客户号 custNo
		ecCustPer.setCustNo(custNo);
		// 性别 sex;
		ecCustPer.setSex(perCustBaseInfo.getSex());
		// 出生日期 birthDate;
		ecCustPer.setBirthDate(perCustBaseInfo.getBirthDate());
		// 客户经理 custAgent;
		if (StringUtils.isNotEmpty(perCustBaseInfo.getCustAgent())) {
			ecCustPer.setCustAgent(perCustBaseInfo.getCustAgent());
            EcCustManager ecCustManager = new EcCustManager();
            ecCustManager.setCustNo(custNo);
            ecCustManager.setCustAgent(perCustBaseInfo.getCustAgent());
            ecCustManager.setRiseTime(DateUtil.nowDateTimeStamp());
            ecCustManager.setRecStat("0");
            List<EcCustManager> listTmp=ecCustManagerMapper.selectAgentByConditions(ecCustManager);
            if(listTmp==null || listTmp.size()==0){
                ecCustManagerMapper.insert(ecCustManager);
            }
		}
		// 客户类型 custTyp;
 		if (StringUtils.isNotEmpty(perCustBaseInfo.getCustTyp())) {
			ecCustPer.setCustTyp(perCustBaseInfo.getCustTyp());
		}
		// 客户来源 custSource;
		if (StringUtils.isNotEmpty(perCustBaseInfo.getCustSource())) {
			ecCustPer.setCustSource(perCustBaseInfo.getCustSource());
		}
		// 是否为重点 keyCustFlg;
		if (StringUtils.isNotEmpty(perCustBaseInfo.getCustSource())) {
			ecCustPer.setKeyCustFlg(perCustBaseInfo.getKeyCustFlg());
		} else {
			ecCustPer.setKeyCustFlg(EnumType.YesNoFlg.no.getValue());
		}
		// 下次跟进时间 nextFollowUpTm;
//		if (StringUtils.isNotEmpty(perCustBaseInfo.getNextFollowUpTm())) {
//			ecCustPer.setNextFollowUpTm(DateUtil.formatString2Date(perCustBaseInfo.getNextFollowUpTm()));
//		}
		// 国籍 nationality;
		if (StringUtils.isNotEmpty(perCustBaseInfo.getNationality())) {
			ecCustPer.setNationality(perCustBaseInfo.getNationality());
		}
		// 民族 nation
        if (StringUtils.isNotEmpty(perCustBaseInfo.getNation())) {
            ecCustPer.setNation(perCustBaseInfo.getNation());
        }
        // 政治面貌 PolitSts
        if (StringUtils.isNotEmpty(perCustBaseInfo.getPolitSts())) {
            ecCustPer.setPolitSts(perCustBaseInfo.getPolitSts());
        }
		// 婚姻状况 marrigeSts;
		if (StringUtils.isNotEmpty(perCustBaseInfo.getMarrigeSts())) {
			ecCustPer.setMarrigeSts(perCustBaseInfo.getMarrigeSts());
		}
		// 文化程度 eduDegree;
		if (StringUtils.isNotEmpty(perCustBaseInfo.getEduDegree())) {
			ecCustPer.setEduDegree(perCustBaseInfo.getEduDegree());
		}
		//批量导入是头像为空
		if(perCustBaseInfo.getPerIconSmlBlob()!=null) {
			// 头像URL perIconUrl;
	        BASE64Encoder encoder = new BASE64Encoder();
			BASE64Decoder decode = new BASE64Decoder();
			if (perCustBaseInfo.getPerIconUrl() != null) {
		        String imgData = encoder.encode(perCustBaseInfo.getPerIconUrl().getBytes());
				byte[] perIconBlob = decode.decodeBuffer(imgData);
				ecCustPer.setPerIconSmlBlob(perIconBlob);
				ecCustPer.setPerIconBigBlob(perIconBlob);
			} else {
				byte[] perIconBlob = decode.decodeBuffer(perCustBaseInfo.getPerIconSmlBlob());
				ecCustPer.setPerIconSmlBlob(perIconBlob);
				ecCustPer.setPerIconBigBlob(perIconBlob);
			}
		}
		// 黑名单标识		blacklist_flg
		ecCustPer.setBlacklistFlg(EnumType.YesNoFlg.no.getValue());
		// 可见范围		visible_range
		ecCustPer.setVisibleRange(EnumType.VisibleRange.all.getValue());
		// 可以证实年龄的证件标识		cnf_age_cert_flg
		ecCustPer.setCnfAgeCertFlg(EnumType.YesNoFlg.yes.getValue());
		ecCustPer.setCustStat(EnumType.CustStat.normal.value);//客户状态：正常
		
		// 客户导入
		// 身高、体重、血型
		if (StringUtils.isNotEmpty(perCustBaseInfo.getBloodTyp())) {
			ecCustPer.setBloodTyp(perCustBaseInfo.getBloodTyp());
		}
		if (new BigDecimal(0) != perCustBaseInfo.getWeight()) {
			ecCustPer.setWeight(perCustBaseInfo.getWeight());
		}
		if (new BigDecimal(0) != perCustBaseInfo.getHeight()) {
			ecCustPer.setHeight(perCustBaseInfo.getHeight());
		}
		
		ecCustPerMapper.insert(ecCustPer);

		/** 姓名信息 */
		if (perCustBaseInfo.getCustName() != null) {
			EcCustName ecCustName = ecCustNameMapper.selectByPrimaryKey(custNo);
			if (ecCustName != null) {
				if (ecCustName.getRecStat().equals(EnumType.RecStat.delete.getValue())) {
					ecCustName.setRecStat(EnumType.RecStat.normal.getValue());
				}
				ecCustName.setCustNam(perCustBaseInfo.getCustName());
				ecCustNameMapper.updateByPrimaryKey(ecCustName);
			} else {
				EcCustName nameObj = new EcCustName();
				// 	客户号		cust_no
				nameObj.setCustNo(custNo);
				// 	客户姓名		cust_nam
				nameObj.setCustNam(perCustBaseInfo.getCustName());
				// 	曾用名1		frmer_nam1
				// 	曾用名2		frmer_nam2
				// 	生效日期		eff_date
				// 	失效日期		exp_date
				// 	姓名拼音		full_name_py
				// 	数据来源		data_source
				nameObj.setDataSource(EnumType.DataSource.ecif.getValue());
				ecCustNameMapper.insert(nameObj);
			}
		}

		/** 学历 */
		if (StringUtils.isNotEmpty(perCustBaseInfo.getEduDegree())) {
			custEduService.saveOrUpdateCustEdu(perCustBaseInfo);
		}

		/** 证件信息 */
		custCertService.saveOrUpdateCert(perCustBaseInfo);

		/** 联系方式信息 */
		custContactService.saveOrUpdateContactAddr(perCustBaseInfo);

		/** 职业信息 */
		custCareerService.saveOrUpdateCareer(perCustBaseInfo);

		/** 居住地址信息 */
		custContactService.saveOrUpdateHomeAddr(perCustBaseInfo);

		/** 单位地址信息 */
		custContactService.saveOrUpdateUnitAddr(perCustBaseInfo);

		//this.autoAddCustTag(custNo, perCustBaseInfo);
		
		// 存入轨迹
		EcCustSaleChgTrace custSaleChgTrace = new EcCustSaleChgTrace();
		custSaleChgTrace.setTraceId(IdGenerator.getDistributedID());
		custSaleChgTrace.setCustNo(custNo);
		custSaleChgTrace.setCustType(ecCustPer.getCustTyp());
		custSaleChgTrace.setDealUser(ecCustPer.getCreateUser());
		custSaleChgTrace.setDealTime(DateUtil.formatString2Date(ecCustPer.getCreateDate()));
		custSaleChgTrace.setDealAction(EnumType.dealAction.importCust.value);
		ecCustSaleChgTraceMapper.insert(custSaleChgTrace);
		return "新增成功";
	}

   /**
    * 从Excel文件里读取数据保存到Vector里
    * @param fileName        Excel文件的名称
    * @return                Vector对象,里面包含从Excel文件里获取到的数据
 * @throws IOException 
 * @throws BiffException 
 * @throws ParseException 
    */
   public String importExcel(String fileName,String custAgent) throws BiffException, IOException, ParseException{
	   // String fileName = "导出客户.xls";
       // Vector<EcProduct> v = new Vector<EcProduct>();
       String path=System.getProperty("user.dir")+"/";
           Workbook book = Workbook.getWorkbook(new File(path+fileName));
           Sheet sheet = book.getSheet(0);        // 获得第一个工作表对象 
           int rows = sheet.getRows();
 
           // 存入成功数、失败数
           int succeedQty = 0;
           int failureQty = 0;
           String failReason="";
           String str="";
           int dataRows = rows - 1;
           // 导入编码
           String importCd = IdGenerator.getUUID();
           
           // 记录导入日志
           EcImportLog importLog = new EcImportLog();           
           importLog.setImportCd(importCd);
           importLog.setImportTime(new Date());
           importLog.setImportObjTyp("0");// 导入对象类型 0-个人客户
           importLog.setFileNam(fileName);
           importLog.setFileTyp(".xls");

           
           // 记录导入日志明细
           EcImportDets importDets = new EcImportDets();
    	   importDets.setImportCd(importCd);
    	   
    	   // 导入客户list - 判断重复
    	   List<String> custList = new ArrayList<String>();
           
           for(int i = 1; i < rows; i++) {
       		// 失败原因
               String importReason = "";
               PerCustBaseInfo p = new PerCustBaseInfo();

               Cell [] cell = sheet.getRow(i);
               if(cell.length == 0) {
                   dataRows--;
                   continue;
               }

               // 0姓名 1手机号码 2性别 3出生日期 4证件类型 5证件号码 6民族 7政治面貌 8客户来源 9国籍 10婚姻状况 11文化程度 12血型 13身高 14体重 15其他手机 
               // 16住宅电话 17单位电话 18居住详细地址 19居住地邮编 20单位详细地址 21单位邮编 22健康情况 23是否有房产 24是否有车 25车牌号
               // 26年收入 27是否有医保 28习惯、嗜好 29微信号码 30QQ 31微博 32支付宝 33邮箱地址
               DateFormat df2= new SimpleDateFormat("yyyyMMdd");
               try {
           		
               p.setCustName(sheet.getCell(0, i).getContents());
               p.setPhoneNumber(sheet.getCell(1, i).getContents());
               p.setSex(sheet.getCell(2, i).getContents());
//               if (sheet.getCell(3, i).getContents() == null) {
//            	   importReason = "出生日期不能为空";
//       			}else {
       				p.setBirthDate(df2.parse(sheet.getCell(3, i).getContents()));
//       			}
               p.setCertTyp(sheet.getCell(4, i).getContents());
               p.setCertNo(sheet.getCell(5, i).getContents());
               p.setNation(sheet.getCell(6, i).getContents());
               p.setPolitSts(sheet.getCell(7, i).getContents());;
               p.setCustSource(sheet.getCell(8, i).getContents());
               p.setNationality(sheet.getCell(9, i).getContents());
               p.setMarrigeSts(sheet.getCell(10, i).getContents());               
               p.setEduDegree(sheet.getCell(11, i).getContents());
               p.setBloodTyp(sheet.getCell(12, i).getContents());               
               p.setHeight(new BigDecimal((sheet.getCell(13, i).getContents() == null||sheet.getCell(13, i).getContents() == "")?"0":sheet.getCell(13, i).getContents()));
               p.setWeight(new BigDecimal((sheet.getCell(14, i).getContents() == null||sheet.getCell(14, i).getContents() == "")?"0":sheet.getCell(14, i).getContents()));
               p.setOtherTel(sheet.getCell(15, i).getContents());
               p.setHomeTel(sheet.getCell(16, i).getContents());
               p.setOfficeTel(sheet.getCell(17, i).getContents());
               p.setLiveAddrNam(sheet.getCell(18, i).getContents());
               p.setLivePostcode(sheet.getCell(19, i).getContents());
               p.setUnitAddrNam(sheet.getCell(20, i).getContents());
               p.setUnitPostcode(sheet.getCell(21, i).getContents());
               p.setHealthCondition(sheet.getCell(22, i).getContents());
               p.setWhtHouse(sheet.getCell(23, i).getContents());
               p.setWhtCar(sheet.getCell(24, i).getContents());
               p.setLicensePlate(sheet.getCell(25, i).getContents());
               p.setAnnualIncome(new BigDecimal((sheet.getCell(26, i).getContents() == null||sheet.getCell(26, i).getContents() == "")?"0":sheet.getCell(26, i).getContents()));
               p.setWhtMedInsur(sheet.getCell(27, i).getContents());
               p.setHabit(sheet.getCell(28, i).getContents());
               p.setWechatNo(sheet.getCell(29, i).getContents());
               p.setQq(sheet.getCell(30, i).getContents());
               p.setMicroblog(sheet.getCell(31, i).getContents());
               p.setAlipay(sheet.getCell(32, i).getContents());
               p.setEmailAddr(sheet.getCell(33, i).getContents());
               p.setCustAgent(custAgent);
               // 客户类型
               p.setCustTyp(EnumType.CustType.per_latent_cust.value);
               
               // 地址类型
               p.setLiveAddrTyp(EnumType.AddrTyp.home_addr.value);
               p.setUnitAddrTyp(EnumType.AddrTyp.company_addr.value);
               // 枚举类型
               // 性别 证件类型 IdType 民族 Nation 政治面貌 PoliticalStatus 客户来源 DataSource 国籍 Nationality 婚姻状况 Marriage
               // 文化程度 Degree(学历) 健康情况 HealthCondition 是否有房产 YesNoFlg 是否有车 YesNoFlg 是否有医保 YesNoFlg 地址类型 AddrTyp
               for(EnumType.Sex item :EnumType.Sex.values()){
                   if(item.desc.equals(p.getSex())){
                       p.setSex(item.getValue());
                   }
               }                            
               for(EnumType.IdType item :EnumType.IdType.values()){
                   if(item.desc.equals(p.getCertTyp())){
                       p.setCertTyp(item.getValue());
                   }
               }
               for(EnumType.Nation item :EnumType.Nation.values()){
                   if(item.desc.equals(p.getNation())){
                       p.setNation(item.getValue());
                   }
               }
               for(EnumType.PoliticalStatus item :EnumType.PoliticalStatus.values()){
                   if(item.desc.equals(p.getPolitSts())){
                       p.setPolitSts(item.getValue());
                   }
               }
               for(EnumType.DataSource item :EnumType.DataSource.values()){
                   if(item.desc.equals(p.getCustSource())){
                       p.setCustSource(item.getValue());
                   }
               }
               for(EnumType.Nationality item :EnumType.Nationality.values()){
                   if(item.desc.equals(p.getNationality())){
                       p.setNationality(item.getValue());
                   }
               }
               for(EnumType.Marriage item :EnumType.Marriage.values()){
                   if(item.desc.equals(p.getMarrigeSts())){
                       p.setMarrigeSts(item.getValue());
                   }
               }
               for(EnumType.Degree item :EnumType.Degree.values()){
                   if(item.desc.equals(p.getEduDegree())){
                       p.setEduDegree(item.getValue());
                   }
               }
               for(EnumType.HealthCondition item :EnumType.HealthCondition.values()){
                   if(item.desc.equals(p.getHealthCondition())){
                       p.setHealthCondition(item.getValue());
                   }
               }
               for(EnumType.YesNoFlg item :EnumType.YesNoFlg.values()){
                   if(item.desc.equals(p.getWhtHouse())){
                       p.setWhtHouse(item.getValue());
                   }
               }
               for(EnumType.YesNoFlg item :EnumType.YesNoFlg.values()){
                   if(item.desc.equals(p.getWhtCar())){
                       p.setWhtCar(item.getValue());
                   }
               }
               for(EnumType.YesNoFlg item :EnumType.YesNoFlg.values()){
                   if(item.desc.equals(p.getWhtMedInsur())){
                       p.setWhtMedInsur(item.getValue());
                   }
               }


               if(custList.size() == 0) {
            	   custList.add(p.getCustName() +"+"+p.getPhoneNumber());
               }else{
                   if(custList.contains(p.getCustName() +"+"+p.getPhoneNumber())) {
                       importReason = "客户信息重复";
                   }else{
                       custList.add(p.getCustName() +"+"+p.getPhoneNumber());
                   }
               }


               //v.add(p);
               // 存入客户表
               int count = 0;
            	   if(importReason.equals("")) {
                	   importReason = addCust(p);
            	   }
            	   if (importReason.equals("新增成功")) {
                	   succeedQty ++;
                	   importDets.setImportSts("1");// 1-完成 0-失败
            	   }else {
                	   failureQty ++;
                	   failReason+=",第"+i+"条失败原因:"+importReason;
                	   importDets.setImportSts("0");// 1-完成 0-失败
            	   }
                   // 记录导入日志明细
                   importDets.setImportDetCd(IdGenerator.getUUID());
                   importDets.setImportContent(JsonUtil.toJSONString(p));
                   importDets.setImportReason(importReason);
                   importLogService.addImportDets(importDets);
               }catch(Exception e) {
            	   failureQty ++;
            	   importDets.setImportSts("0");// 1-完成 0-失败
                   // 记录导入日志明细
                   importDets.setImportDetCd(IdGenerator.getUUID());
                   importDets.setImportContent(JsonUtil.toJSONString(p));
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
           str+="导入成功"+succeedQty+"条";
           str+=failReason;
           book.close();
           return str;

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
        WritableSheet ws = wwb.createSheet("客户导入", 10);        // 创建一个工作表
        //    设置单元格的文字格式
        WritableFont wf = new WritableFont(WritableFont.ARIAL,12,WritableFont.NO_BOLD,false,
                UnderlineStyle.NO_UNDERLINE,Colour.BLUE);
        WritableCellFormat wcf = new WritableCellFormat(wf);
        wcf.setVerticalAlignment(VerticalAlignment.CENTRE); 
        wcf.setAlignment(Alignment.CENTRE); 
        ws.setRowView(1, 500);

        // 0姓名 1手机号码 2性别 3出生日期 4证件类型 5证件号码 6民族 7政治面貌 8客户来源 9国籍 10婚姻状况 11文化程度 12血型 13身高 14体重 15其他手机 
        // 16住宅电话 17单位电话 18居住详细地址 19居住地邮编 20单位详细地址 21单位邮编 22健康情况 23是否有房产 24是否有车 25车牌号
        // 26年收入 27是否有医保 28习惯、嗜好 29微信号码 30QQ 31微博 32支付宝 33邮箱地址
        ws.addCell(new Label(0, 0,"姓名", wcf));
        ws.addCell(new Label(1, 0,"手机号码", wcf));
        ws.addCell(new Label(2, 0,"性别", wcf));
        ws.addCell(new Label(3, 0,"出生日期", wcf));
        ws.addCell(new Label(4, 0,"证件类型", wcf));
        ws.addCell(new Label(5, 0,"证件号码", wcf));
        ws.addCell(new Label(6, 0,"民族", wcf));
        ws.addCell(new Label(7, 0,"政治面貌", wcf));
        ws.addCell(new Label(8, 0,"客户来源", wcf));
        ws.addCell(new Label(9, 0,"国籍", wcf));
        ws.addCell(new Label(10, 0,"婚姻状况", wcf));
        ws.addCell(new Label(11, 0,"文化程度", wcf));
        ws.addCell(new Label(12, 0,"血型", wcf));
        ws.addCell(new Label(13, 0,"身高", wcf));
        ws.addCell(new Label(14, 0,"体重", wcf));
        ws.addCell(new Label(15, 0,"其他手机", wcf));
        ws.addCell(new Label(16, 0,"住宅电话", wcf));
        ws.addCell(new Label(17, 0,"单位电话", wcf));
        ws.addCell(new Label(18, 0,"居住详细地址", wcf));
        ws.addCell(new Label(19, 0,"居住地邮编", wcf));
        ws.addCell(new Label(20, 0,"单位详细地址", wcf));
        ws.addCell(new Label(21, 0,"单位邮编", wcf));
        ws.addCell(new Label(22, 0,"健康情况", wcf));
        ws.addCell(new Label(23, 0,"是否有房产", wcf));
        ws.addCell(new Label(24, 0,"是否有车", wcf));
        ws.addCell(new Label(25, 0,"车牌号", wcf));
        ws.addCell(new Label(26, 0,"年收入", wcf));
        ws.addCell(new Label(27, 0,"是否有医保", wcf));
        ws.addCell(new Label(28, 0,"习惯、嗜好", wcf));
        ws.addCell(new Label(29, 0,"微信", wcf));
        ws.addCell(new Label(30, 0,"QQ", wcf));
        ws.addCell(new Label(31, 0,"微博", wcf));
        ws.addCell(new Label(32, 0,"支付宝", wcf));
        ws.addCell(new Label(33, 0,"邮箱", wcf));
        ws.addCell(new Label(34, 0,"失败原因", wcf));

        //    填充数据的内容
        List<PerCustBaseInfo> content = new ArrayList();
        List<String> reason = new ArrayList();
        EcImportDets importDets = new EcImportDets();
        importDets.setImportCd(importLog.getImportCd());
        importDets.setImportSts(importDetsSts);
        List<EcImportDets> retrunImportDets= importLogService.getImportDets(importDets);
        for(EcImportDets log : retrunImportDets) {
      	  String prdStr = log.getImportContent();         
          PerCustBaseInfo cust = JsonUtil.parseObject(prdStr, PerCustBaseInfo.class);
          // 枚举类型
          // 证件类型 IdType 民族 Nation 政治面貌 PoliticalStatus 客户来源 DataSource 国籍 Nationality 婚姻状况 Marriage
          // 文化程度 Degree(学历) 健康情况 HealthCondition 是否有房产 YesNoFlg 是否有车 YesNoFlg 是否有医保 YesNoFlg 地址类型 AddrTyp
                                        
          for(EnumType.IdType item :EnumType.IdType.values()){
              if(item.value.equals(cust.getCertTyp())){
                  cust.setCertTyp(item.desc);
              }
          }
          for(EnumType.Nation item :EnumType.Nation.values()){
              if(item.value.equals(cust.getNation())){
                  cust.setNation(item.desc);
              }
          }
          for(EnumType.PoliticalStatus item :EnumType.PoliticalStatus.values()){
              if(item.value.equals(cust.getPolitSts())){
                  cust.setPolitSts(item.desc);
              }
          }
          for(EnumType.DataSource item :EnumType.DataSource.values()){
              if(item.value.equals(cust.getCustSource())){
                  cust.setCustSource(item.desc);
              }
          }
          for(EnumType.Nationality item :EnumType.Nationality.values()){
              if(item.value.equals(cust.getNationality())){
                  cust.setNationality(item.desc);
              }
          }
          for(EnumType.Marriage item :EnumType.Marriage.values()){
              if(item.value.equals(cust.getMarrigeSts())){
                  cust.setMarrigeSts(item.desc);
              }
          }
          for(EnumType.Degree item :EnumType.Degree.values()){
              if(item.value.equals(cust.getEduDegree())){
                  cust.setEduDegree(item.desc);
              }
          }
          for(EnumType.HealthCondition item :EnumType.HealthCondition.values()){
              if(item.value.equals(cust.getHealthCondition())){
                  cust.setHealthCondition(item.desc);
              }
          }
          for(EnumType.YesNoFlg item :EnumType.YesNoFlg.values()){
              if(item.value.equals(cust.getWhtHouse())){
                  cust.setWhtHouse(item.desc);
              }
          }
          for(EnumType.YesNoFlg item :EnumType.YesNoFlg.values()){
              if(item.value.equals(cust.getWhtCar())){
                  cust.setWhtCar(item.desc);
              }
          }
          for(EnumType.YesNoFlg item :EnumType.YesNoFlg.values()){
              if(item.value.equals(cust.getWhtMedInsur())){
                  cust.setWhtMedInsur(item.desc);
              }
          }

      	  content.add(cust);
      	  reason.add(log.getImportReason());
        }
        
        PerCustBaseInfo[] p = new PerCustBaseInfo[content.size()];
        String[] r = new String[reason.size()];
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        for (int i = 0; i < content.size(); i++){
            if(i == 0)
                wcf = new WritableCellFormat();
            p[i] = (PerCustBaseInfo)content.get(i);
            r[i] = reason.get(i);
            ws.addCell(new Label(0, i+1,p[i].getCustName(), wcf));
            ws.addCell(new Label(1, i+1,p[i].getPhoneNumber(), wcf));
            ws.addCell(new Label(2, i+1,p[i].getSex(), wcf));
            if(p[i].getBirthDate()!=null &&!"".equals(p[i].getBirthDate().toString())) {
            	ws.addCell(new Label(3, i+1,df.format(p[i].getBirthDate()), wcf));
            }
            ws.addCell(new Label(4, i+1,p[i].getCertTyp(), wcf));
            ws.addCell(new Label(5, i+1,p[i].getCertNo(), wcf));
            ws.addCell(new Label(6, i+1,p[i].getNation(), wcf));
            ws.addCell(new Label(7, i+1,p[i].getPolitSts(), wcf));
            ws.addCell(new Label(8, i+1,p[i].getCustSource(), wcf));
            ws.addCell(new Label(9, i+1,p[i].getNationality(), wcf));
            ws.addCell(new Label(10, i+1,p[i].getMarrigeSts(), wcf));
            ws.addCell(new Label(11, i+1,p[i].getEduDegree(), wcf));
            ws.addCell(new Label(12, i+1,p[i].getBloodTyp(), wcf));
            if(p[i].getWeight()!=null &&!"".equals(p[i].getWeight())) {
                ws.addCell(new Label(13, i+1,p[i].getHeight().toString(), wcf));
            }
            if(p[i].getWeight()!=null &&!"".equals(p[i].getWeight())) {
                ws.addCell(new Label(14, i+1,p[i].getWeight().toString(), wcf));
            }            
            ws.addCell(new Label(15, i+1,p[i].getOtherTel(), wcf));
            ws.addCell(new Label(16, i+1,p[i].getHomeTel(), wcf));
            ws.addCell(new Label(17, i+1,p[i].getOfficeTel(), wcf));
            ws.addCell(new Label(18, i+1,p[i].getLiveAddrNam(), wcf));
            ws.addCell(new Label(19, i+1,p[i].getLivePostcode(), wcf));
            ws.addCell(new Label(20, i+1,p[i].getUnitAddrNam(), wcf));
            ws.addCell(new Label(21, i+1,p[i].getUnitPostcode(), wcf));
            ws.addCell(new Label(22, i+1,p[i].getHealthCondition(), wcf));
            ws.addCell(new Label(23, i+1,p[i].getWhtHouse(), wcf));
            ws.addCell(new Label(24, i+1,p[i].getWhtCar(), wcf));
            ws.addCell(new Label(25, i+1,p[i].getLicensePlate(), wcf));
            if(p[i].getAnnualIncome()!=null &&!"".equals(p[i].getAnnualIncome())) {
                ws.addCell(new Label(26, i+1,p[i].getAnnualIncome().toString(), wcf));
            }            

            ws.addCell(new Label(27, i+1,p[i].getWhtMedInsur(), wcf));
            ws.addCell(new Label(28, i+1,p[i].getHabit(), wcf));
            ws.addCell(new Label(29, i+1,p[i].getWechatNo(), wcf));
            ws.addCell(new Label(30, i+1,p[i].getQq(), wcf));
            ws.addCell(new Label(31, i+1,p[i].getMicroblog(), wcf));
            ws.addCell(new Label(32, i+1,p[i].getAlipay(), wcf));
            ws.addCell(new Label(33, i+1,p[i].getEmailAddr(), wcf));
            ws.addCell(new Label(34, i+1, r[i], wcf));
        }
        wwb.write();
        wwb.close();
}

   
}
