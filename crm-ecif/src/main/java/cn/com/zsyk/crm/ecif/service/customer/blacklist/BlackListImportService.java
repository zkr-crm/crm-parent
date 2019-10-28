package cn.com.zsyk.crm.ecif.service.customer.blacklist;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

import cn.com.zsyk.crm.common.constant.ServiceType;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.util.RedisUtil;
import cn.com.zsyk.crm.common.util.RestUtil;
import cn.com.zsyk.crm.ecif.bo.cust.PerCustBaseInfo;
import cn.com.zsyk.crm.ecif.entity.*;
import cn.com.zsyk.crm.ecif.mapper.EcCustPerMapper;
import cn.com.zsyk.crm.ecif.service.customer.baseinfo.CustService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.zsyk.crm.common.dao.AbstractDao;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.IdGenerator;
import cn.com.zsyk.crm.common.util.JsonUtil;
import cn.com.zsyk.crm.ecif.mapper.EcBlackListMapper;
import cn.com.zsyk.crm.generator.EnumType;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

@Service
@Transactional
public class BlackListImportService {

	@Autowired
	private AbstractDao daoUtil;
	@Autowired
	private BlackListImportLogService importLogService;
	@Autowired
	private EcBlackListMapper ecBlackListMapper;
	@Autowired
	private EcCustPerMapper ecCustPerMapper;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private RestUtil restUtil;
	@Autowired
	private CustService custService;
	/**
	 * 获得一条黑名单信息的方法
	 * 
	 * @param productCd
	 *            黑名单ID
	 * @return 黑名单信息
	 */
	public EcBlackList getOneBlackList(String productCd) {

		// 黑名单ID非空判断
		if (productCd == null || "".equals(productCd)) {
			throw new ServiceException("黑名单ID[" + productCd + "]不能为空！");
		}

		EcBlackList balcklistInfo = ecBlackListMapper.selectByPrimaryKey(productCd);

		return balcklistInfo;
	}

	/**
	 * 根据入参对象获取所有黑名单信息的方法
	 * 
	 * @return 所有黑名单信息的列表
	 */
	public List<EcProduct> getProductsByEntity(EcProduct record) {

		List<EcProduct> lstProduct = daoUtil.selectList("cn.com.zsyk.crm.ecif.mapper.EcProductMapper.selectByEntity",
				record);

		return lstProduct;
	}

	/**
	 * 新增一条黑名单信息
	 * 
	 * @param productCd
	 *            黑名单ID
	 * @param productname
	 *            黑名单名称
	 * @return 新增成功的记录条数
	 */
	public int addBlackList(EcBlackList p) {

		// // 存在判断
		// EcBlackList extTest = this.getOneBlackList(p.getBlackCustNo());
		// if (extTest != null) {
		// return "黑名单信息已存在：黑名单[" + p.getBlackCustNo() + "]";
		// }

		int addCount = ecBlackListMapper.insert(p);

		return addCount;
	}

	/**
	 * 从Excel文件里读取数据保存到Vector里
	 * 
	 * @param fileName
	 *            Excel文件的名称
	 * @return Vector对象,里面包含从Excel文件里获取到的数据
	 * @throws IOException
	 * @throws BiffException
	 */
	public int importExcel(String fileName) throws BiffException, IOException,Exception {
		// String fileName = "导出黑名单.xls";
		// Vector<EcProduct> v = new Vector<EcProduct>();
		String path=System.getProperty("user.dir")+"/";
		Workbook book = Workbook.getWorkbook(new File(path + fileName));
		Sheet sheet = book.getSheet(0); // 获得第一个工作表对象
		int rows = sheet.getRows();
		int limitNum=1000;//默认以前
		String paramType="INT";
		String paramCode="import_limit_num";
		try{
			String tmp=(String)redisUtil.get("sysparam_"+paramType+"_"+paramCode);
			if(tmp==null || "".equals(tmp)){
				Map map = new HashMap();
				map.put("paramType", paramType);
				map.put("paramCode", paramCode);
				Result result = restUtil.getForObject(ServiceType.MANAGE, "/crm/manage/paramng/param?paramType={paramType}&paramCode={paramCode}",Result.class,map);
				Map<String, String> sysParam = (Map<String, String>) result.getData();
				limitNum=Integer.parseInt(sysParam.get("paramValue"));
			}else{
				limitNum=Integer.parseInt(tmp);
			}
		}catch(Exception e){

		}
		if(limitNum<(rows-1)){
			throw new ServiceException("每次导入黑名单数量不得超过"+limitNum+"请重新导入！");
		}
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
		importLog.setImportObjTyp("3");// 导入对象类型 3-黑名单
		importLog.setFileNam(fileName);
		importLog.setFileTyp(".xls");

		// 记录导入日志明细
		EcImportDets importDets = new EcImportDets();
		importDets.setImportCd(importCd);

		for (int i = 1; i < rows; i++) {
			// 失败原因
			String importReason = "";
			Cell[] cell = sheet.getRow(i);
			if (cell.length == 0) {
				dataRows--;
				continue;
			}

			// 黑名单表对象
			EcBlackList bkper = new EcBlackList();

			/*
			 * 黑名单代码 黑名单名称 黑名单分类 黑名单版本 责任 保费 保额 销售渠道 生效日期 失效日期 0序号
			 * 1客户姓名(*必填项）" 2性别 3出生日期 4证件类型(*必填项）" 5证件号码(*必填项）" 6手机号码 7黑名单类型 8登记原因 9黑名单来源
			 * 10备注（用于上传错误信息记录）
			 */

			// 客户姓名：若客户姓名为空，则跳过当前行
			if (sheet.getCell(1, i).getContents().trim() == null
					|| "".equals(sheet.getCell(1, i).getContents().trim())) {
				// TODO 打印log
				continue;
			}
			bkper.setCustNam(sheet.getCell(1, i).getContents().trim());
			String sex = sheet.getCell(2, i).getContents().trim();
			if (EnumType.Sex.male.desc.equals(sex)) {// 男
				sex = EnumType.Sex.male.value;
			} else if (EnumType.Sex.female.desc.equals(sex)) {// 女
				sex = EnumType.Sex.female.value;
			}
			// TODO 性别字段,表中无该字段,应添加
			bkper.setSex(sex);
			// TODO 出生日期，表中无字段，应添加
			bkper.setBirthDate(sheet.getCell(3, i).getContents().trim());// 出生日期
			// 证件类型
			String idType = sheet.getCell(4, i).getContents().trim();
			/*if (EnumType.IdType.id_card.desc.equals(idType)) {// 身份证
				idType = EnumType.IdType.id_card.value;
			} else if (EnumType.IdType.passport.desc.equals(idType)) {// 护照
				idType = EnumType.IdType.passport.value;
			} else {
				idType = EnumType.IdType.other_cert.value; // 其他
			}*/
			String idTypeTmp="";
			EnumType.IdType[] idTypes =EnumType.IdType.values();
			for(int x=0;x<idTypes.length;x++){
				if(idTypes[x].desc.equals(idType)){
					idTypeTmp=idTypes[x].value;
				}
			}
			if("".equals(idTypeTmp)){
				idType = EnumType.IdType.other_cert.value; // 其他
			}else{
				idType=idTypeTmp;
			}

			bkper.setCertTyp(idType);// 证件类型

			if (idType == EnumType.IdType.other_cert.value) {// 证件类型为其他时，证件号码默认为空
				bkper.setCertNo("");// 证件号码
			} else {
				bkper.setCertNo(sheet.getCell(5, i).getContents().trim());// 证件号码
			}
			// 手机号字段表中没有
			bkper.setPhoneNo(sheet.getCell(6, i).getContents().trim());// 手机号

			// 客户号规则：09 + 4位当前年份 + 8位流水号，客户表中客户类型为09，即：黑名单客户
			Long id = IdGenerator.getSeqID("BlackList");
			if (id > 99999999) {
				throw new ServiceException("黑名单数量已达上限，不可新增，请联系管理员！");
			}
			// 黑名单客户号自动左侧补零
			String idStr = id.toString();
			StringBuffer strBuffer = null;
			while (idStr.length() < 8) {
				strBuffer = new StringBuffer();
				strBuffer.append("0").append(idStr);// 左补0
				idStr = strBuffer.toString();
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
			String year = dateFormat.format(new Date());
			bkper.setCustNo(EnumType.CustType.black_list_cust.value + year + idStr); // 客户号
			bkper.setCustTyp(EnumType.CustType.black_list_cust.value); // 客户类型
			Date dayseq = new Date();
			SimpleDateFormat dfseq = new SimpleDateFormat("yyyyMMddHHmmss");
			Date day = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			bkper.setRegiDate(df.format(day)); // 登记日期
			String blacktype = sheet.getCell(7, i).getContents().trim();
			if (EnumType.BlacklistType.health.desc.equals(blacktype)) {// 健康问题
				blacktype = EnumType.BlacklistType.health.value;
			} else if (EnumType.BlacklistType.fake.desc.equals(blacktype)) {// 欺诈问题
				blacktype = EnumType.BlacklistType.fake.value;
			} else if (EnumType.BlacklistType.creadit_level.desc.equals(blacktype)) {// 信用等级差
				blacktype = EnumType.BlacklistType.creadit_level.value;
			} else if (EnumType.BlacklistType.others.desc.equals(blacktype)) {// 其他
				blacktype = EnumType.BlacklistType.others.value;
			}
			bkper.setBlacklistType(blacktype);// 黑名单类型
			bkper.setBlacklistSts(EnumType.ValidFlg.valid.value);// 客户状态：有效
			bkper.setRegiReason(sheet.getCell(8, i).getContents().trim());// 登记原因
			String blacksrc = sheet.getCell(9, i).getContents().trim();// 黑名单来源
			if (EnumType.BlacklistSrc.src_rs.desc.equals(blacksrc)) {// 保险
				blacksrc = EnumType.BlacklistSrc.src_rs.value;
			} else if (EnumType.BlacklistSrc.src_others_org.desc.equals(blacksrc)) {// 其他保险机构
				blacksrc = EnumType.BlacklistSrc.src_others_org.value;
			} else if (EnumType.BlacklistSrc.src_center_bank.desc.equals(blacksrc)) {// 央行
				blacksrc = EnumType.BlacklistSrc.src_center_bank.value;
			} else if (EnumType.BlacklistSrc.src_police.desc.equals(blacksrc)) {// 公安部门
				blacksrc = EnumType.BlacklistSrc.src_police.value;
			} else if (EnumType.BlacklistSrc.src_others.desc.equals(blacksrc)) {// 其他
				blacksrc = EnumType.BlacklistSrc.src_others.value;
			}
			bkper.setBlacklistSrc(blacksrc);
			if(StringUtils.isNotEmpty(sheet.getCell(1, i).getContents().trim())&&StringUtils.isNotEmpty(sheet.getCell(4, i).getContents().trim())&&StringUtils.isNotEmpty(sheet.getCell(5, i).getContents().trim())){
				List<String> custNoList = ecBlackListMapper.selectCust(sheet.getCell(1, i).getContents().trim(),sheet.getCell(5, i).getContents().trim(),idType);
				if(custNoList.size()==0){
					PerCustBaseInfo custBaseInfo = new PerCustBaseInfo();
					custBaseInfo.setCertNo(sheet.getCell(5, i).getContents().trim());
					custBaseInfo.setCertTyp(idType);
					custBaseInfo.setCustName(sheet.getCell(1, i).getContents().trim());
					custBaseInfo.setCustTyp(EnumType.CustType.per_latent_cust.getValue());
					custBaseInfo.setBlacklistFlg(EnumType.YesNoFlg.yes.getValue());
					custBaseInfo.setMergeMark(EnumType.MergeSplitAction.merge_approv_pass.desc);
					//添加电话
					custBaseInfo.setPhoneNumber(sheet.getCell(6, i).getContents().trim());
					List<EcCustPhone> phoneList = new ArrayList<EcCustPhone>();
					EcCustPhone ecCustPhone=new EcCustPhone();
					ecCustPhone.setPhoneNo(sheet.getCell(6, i).getContents().trim());
					phoneList.add(ecCustPhone);
					custBaseInfo.setPhoneList(phoneList);
					custBaseInfo.setPerIconSmlBlob("perIconSmlBlob");
					custBaseInfo.setCustSource("99");
					custBaseInfo.setOtherSource("黑名单");
					bkper.setCustNo(custService.addPerCustInfo(custBaseInfo));
				}else{
					for(String custNo: custNoList){
						EcCustPer record = ecCustPerMapper.selectByPrimaryKey(custNo);
						record.setBlacklistFlg(EnumType.YesNoFlg.yes.getValue());
						bkper.setCustNo(custNo);
						ecCustPerMapper.updateByPrimaryKey(record);
					}
				}
			}
			// 存入黑名单表
			int count = 0;
			try {
				if (importReason.equals("")) {
					count = addBlackList(bkper);
				}
				if (count>0) {
					succeedQty++;//导入成功计数
					importDets.setImportSts(EnumType.ImportStatus.import_finish.value);// 0-完成 1-失败
				} else {
					failureQty++;//导入失败计数
					importDets.setImportSts(EnumType.ImportStatus.import_failure.value);// 0-完成 1-失败
				}
				// 记录导入日志明细
				importDets.setImportDetCd(IdGenerator.getUUID());
				importDets.setImportContent(JsonUtil.toJSONString(bkper));
				importDets.setImportReason(importReason);
				importLogService.addImportDets(importDets);
			} catch (Exception e) {
				failureQty++;
				importDets.setImportSts(EnumType.ImportStatus.import_failure.value);// 0-完成 1-失败
				// 记录导入日志明细
				importDets.setImportDetCd(IdGenerator.getUUID());
				importDets.setImportContent(JsonUtil.toJSONString(bkper));
				importReason = "存入数据库异常";
				importDets.setImportReason(importReason);
				importLogService.addImportDets(importDets);
				continue;
			}
		}

		// 记录导入日志
		if (dataRows == succeedQty) {
			importLog.setImportSts("0");// 导入状态 0-全部成功 1-部分成功 2-全部失败
		} else if (dataRows == failureQty) {
			importLog.setImportSts("2");// 导入状态 0-全部成功 1-部分成功 2-全部失败
		} else {
			importLog.setImportSts("1");// 导入状态 0-全部成功 1-部分成功 2-全部失败
		}
		importLog.setFileTtlRow(dataRows);
		importLog.setFailureQty(failureQty);
		importLog.setSucceedQty(succeedQty);
		importLogService.addImportLog(importLog);

		book.close();
		return succeedQty;

	}

	/**
	 * 保存文件
	 * 
	 * @param stream
	 * @param path
	 * @param filename
	 * @throws IOException
	 */
	public void saveFileFromInputStream(InputStream stream, String path, String filename) throws IOException {
		FileOutputStream fs = new FileOutputStream(path + filename);
		byte[] buffer = new byte[1024 * 1024];
		int bytesum = 0;
		int byteread = 0;
		while ((byteread = stream.read(buffer)) != -1) {
			bytesum += byteread;
			fs.write(buffer, 0, byteread);
			fs.flush();
		}
		fs.close();
		stream.close();
	}
}
