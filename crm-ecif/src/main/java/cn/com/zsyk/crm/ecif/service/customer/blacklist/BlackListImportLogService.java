package cn.com.zsyk.crm.ecif.service.customer.blacklist;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.zsyk.crm.common.dao.AbstractDao;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.IdGenerator;
import cn.com.zsyk.crm.common.util.JsonUtil;
import cn.com.zsyk.crm.ecif.entity.EcBlackList;
import cn.com.zsyk.crm.ecif.entity.EcImportDets;
import cn.com.zsyk.crm.ecif.entity.EcImportLog;
import cn.com.zsyk.crm.ecif.entity.EcProduct;
import cn.com.zsyk.crm.ecif.mapper.EcImportDetsMapper;
import cn.com.zsyk.crm.ecif.mapper.EcImportLogMapper;
import cn.com.zsyk.crm.generator.EnumType;
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
public class BlackListImportLogService {
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

		// 导入对象类型非空判断
		if (iImportObjTyp == null || "".equals(iImportObjTyp)) {
			throw new ServiceException("导入对象类型[" + iImportObjTyp + "]不能为空！");
		}

		List<EcImportLog> logInfo = daoUtil
				.selectList("cn.com.zsyk.crm.ecif.mapper.EcImportLogMapper.selectByimportObjTyp", iImportObjTyp);

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

		List<EcImportDets> logInfo = daoUtil
				.selectList("cn.com.zsyk.crm.ecif.mapper.EcImportDetsMapper.selectByImportCd", importDets);

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
		// logInfo.setImportCd(IdGenerator.getUUID());

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
		// DetsInfo.setImportCd(IdGenerator.getUUID());

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

		daoUtil.selectList("cn.com.zsyk.crm.ecif.mapper.EcImportDetsMapper.deleteByImportCd", importCd);

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
	 * 
	 * @param fileName
	 *            文件的名称，可以设为绝对路径，也可以设为相对路径
	 * @param content
	 *            数据的内容
	 * @throws Exception
	 */
	public void exportExcel(EcImportLog importLog, String importDetsSts, OutputStream out) throws Exception {
		String fileName = importLog.getFileNam();
		WritableWorkbook wwb = Workbook.createWorkbook(out);
		WritableSheet ws = wwb.createSheet("导入模板", 10); // 创建一个工作表
		// 设置单元格的文字格式
		WritableFont wf = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD, false,
				UnderlineStyle.NO_UNDERLINE, Colour.BLUE);
		WritableCellFormat wcf = new WritableCellFormat(wf);
		wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
		wcf.setAlignment(Alignment.CENTRE);
		ws.setRowView(1, 500);

		// 序号,"客户姓名(*必填项）",性别,出生日期,"证件类型(*必填项）","证件号码(*必填项）",手机号码,黑名单类型,登记原因,黑名单来源,备注（用于上传错误信息记录）
		ws.addCell(new Label(0, 0, "序号", wcf));
		ws.addCell(new Label(1, 0, "客户姓名（*必填项）", wcf));
		ws.addCell(new Label(2, 0, "性别", wcf));
		ws.addCell(new Label(3, 0, "出生日期", wcf));
		ws.addCell(new Label(4, 0, "证件类型（*必填项）", wcf));
		ws.addCell(new Label(5, 0, "证件号码（*必填项）", wcf));
		ws.addCell(new Label(6, 0, "手机号码", wcf));
		ws.addCell(new Label(7, 0, "黑名单类型", wcf));
		ws.addCell(new Label(8, 0, "登记原因", wcf));
		ws.addCell(new Label(9, 0, "黑名单来源", wcf));
		ws.addCell(new Label(10, 0, "备注（用于上传错误信息记录）", wcf));

		// 填充数据的内容
		List<EcBlackList> content = new ArrayList<EcBlackList>();
		EcImportDets importDets = new EcImportDets();
		importDets.setImportCd(importLog.getImportCd());
		importDets.setImportSts(importDetsSts);
		List<EcImportDets> retrunImportDets = getImportDets(importDets);
		for (EcImportDets log : retrunImportDets) {

			String prdStr = log.getImportContent();
			/***************************** 开始 ********************************/
			EcBlackList blackList = new EcBlackList();
			// json字符串转换为map
			Map map = JsonUtil.parseObject(prdStr, Map.class);
			blackList.setCustNam(map.get("custNam").toString());// 客户姓名
			// 性别
			String sex = map.get("sex").toString();
			if (EnumType.Sex.male.value.equals(sex)) {// 男
				sex = EnumType.Sex.male.desc;
			} else if (EnumType.Sex.female.value.equals(sex)) {// 女
				sex = EnumType.Sex.female.desc;
			}
			blackList.setSex(sex);// 性别
			blackList.setBirthDate(map.get("birthDate").toString());// 出生日期

			String idType = map.get("certTyp").toString();
			if (EnumType.IdType.id_card.value.equals(idType)) {// 身份证
				idType = EnumType.IdType.id_card.desc;
			} else if (EnumType.IdType.passport.value.equals(idType)) {// 护照
				idType = EnumType.IdType.passport.desc;
			} else {
				idType = EnumType.IdType.other_cert.desc; // 其他
			}
			blackList.setCertTyp(idType);// 证件类型
			String certNo = map.get("certNo").toString();
			blackList.setCertNo(certNo);// 证件号
			// 手机号
			blackList.setPhoneNo(map.get("phoneNo").toString());

			String blacktype = map.get("blacklistType").toString();
			if (EnumType.BlacklistType.health.value.equals(blacktype)) {// 健康问题
				blacktype = EnumType.BlacklistType.health.desc;
			} else if (EnumType.BlacklistType.fake.value.equals(blacktype)) {// 欺诈问题
				blacktype = EnumType.BlacklistType.fake.desc;
			} else if (EnumType.BlacklistType.creadit_level.value.equals(blacktype)) {// 信用等级差
				blacktype = EnumType.BlacklistType.creadit_level.desc;
			} else if (EnumType.BlacklistType.others.value.equals(blacktype)) {// 其他
				blacktype = EnumType.BlacklistType.others.desc;
			}
			blackList.setBlacklistType(blacktype);// 黑名单类型
			blackList.setRegiReason(map.get("regiReason").toString()); // 登记原因

			String blacksrc = map.get("blacklistSrc").toString();// 黑名单来源
			if (EnumType.BlacklistSrc.src_rs.value.equals(blacksrc)) {// 保险
				blacksrc = EnumType.BlacklistSrc.src_rs.desc;
			} else if (EnumType.BlacklistSrc.src_others_org.value.equals(blacksrc)) {// 其他保险机构
				blacksrc = EnumType.BlacklistSrc.src_others_org.desc;
			} else if (EnumType.BlacklistSrc.src_center_bank.value.equals(blacksrc)) {// 央行
				blacksrc = EnumType.BlacklistSrc.src_center_bank.desc;
			} else if (EnumType.BlacklistSrc.src_police.value.equals(blacksrc)) {// 公安部门
				blacksrc = EnumType.BlacklistSrc.src_police.desc;
			} else if (EnumType.BlacklistSrc.src_others.value.equals(blacksrc)) {// 其他
				blacksrc = EnumType.BlacklistSrc.src_others.desc;
			}
			blackList.setBlacklistSrc(blacksrc);
			content.add(blackList);
			/***************************** 结束 ********************************/
		}

		EcBlackList[] p = new EcBlackList[content.size()];
		for (int i = 0; i < content.size(); i++) {
			if (i == 0)
				wcf = new WritableCellFormat();
			p[i] = (EcBlackList) content.get(i);
			ws.addCell(new Label(0, i + 1, "" + i, wcf));
			ws.addCell(new Label(1, i + 1, p[i].getCustNam(), wcf));
			ws.addCell(new Label(2, i + 1, p[i].getSex(), wcf));
			ws.addCell(new Label(3, i + 1, p[i].getBirthDate(), wcf));
			ws.addCell(new Label(4, i + 1, p[i].getCertTyp(), wcf));
			ws.addCell(new Label(5, i + 1, p[i].getCertNo().toString(), wcf));
			ws.addCell(new Label(6, i + 1, p[i].getPhoneNo().toString(), wcf));
			ws.addCell(new Label(7, i + 1, p[i].getBlacklistType(), wcf));
			ws.addCell(new Label(8, i + 1, p[i].getRegiReason(), wcf));
			ws.addCell(new Label(9, i + 1, p[i].getBlacklistSrc(), wcf));
		}
		wwb.write();
		wwb.close();
	}

}
