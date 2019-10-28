package cn.com.zsyk.crm.manage.service.mngcenter.codemapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.zsyk.crm.common.dao.CoreDaoImpl;
import cn.com.zsyk.crm.common.dto.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import cn.com.zsyk.crm.common.dao.AbstractDao;
import cn.com.zsyk.crm.common.dto.ContextContainer;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.generator.EnumType;
import cn.com.zsyk.crm.manage.entity.SysCodeMapper;

@Service
@Transactional
public class CodeMapperService {

	@Autowired
	private cn.com.zsyk.crm.manage.mapper.SysCodeMapperMapper mapper;

	@Autowired
	private AbstractDao daoUtil;
	/* 分页查询对象 */
	@Autowired
	private CoreDaoImpl coreDaoImpl;
	/**
	 * 获得一条码值映射的方法
	 * 
	 * @param String codeType, String codeVal, String extSysFlg, String extCodeType
	 *            代码类型 代码值 源系统标志 源代码类型
	 * @return 码值映射信息
	 */
	public SysCodeMapper getOneCodeMapper(String codeType, String codeVal, String extSysFlg, String extCodeType) {

		// 代码类型非空判断
		if (codeType == null || "".equals(codeType)) {
			throw new ServiceException("代码类型[" + codeType + "]不能为空！");
		}
		// 代码值非空判断
		if (codeVal == null || "".equals(codeVal)) {
			throw new ServiceException("代码值[" + codeVal + "]不能为空！");
		}
		// 源系统标志非空判断
		if (extSysFlg == null || "".equals(extSysFlg)) {
			throw new ServiceException("源系统标志[" + extSysFlg + "]不能为空！");
		}
		// 源代码类型非空判断
		if (extCodeType == null || "".equals(extCodeType)) {
			throw new ServiceException("源代码类型[" + extCodeType + "]不能为空！");
		}

		SysCodeMapper codeMapper = mapper.selectByPrimaryKey(codeType, codeVal, extSysFlg, extCodeType);

		return codeMapper;
	}

	/**
	 * 根据入参对象获取所有码值映射信息的方法
	 * 
	 * @return 所有码值映射信息的列表
	 */
	public PageBean getCodeMapperByEntity(SysCodeMapper record) {

		//List<SysCodeMapper> list = daoUtil.selectList("cn.com.zsyk.crm.manage.mapper.SysCodeMapperMapper.selectByEntity", record);
		PageBean pageRetlst = coreDaoImpl.selectPageByMapper(mapper,"selectByEntity",record);
		return pageRetlst;
	}

	/**
	 * 新增一条码值映射信息
	 * 
	 * @param codeMapper
	 *            码值映射对象
	 * @return 新增成功的记录条数
	 */
	public int addCodeMapper(SysCodeMapper codeMapper) {

		if (codeMapper == null) {
			throw new ServiceException("输入信息不能为空！");
		}

		if (StringUtils.isEmpty(codeMapper.getCodeType())) {
			throw new ServiceException("代码类型不能为空！");
		}
		if (StringUtils.isEmpty(codeMapper.getCodeVal())) {
			throw new ServiceException("代码值不能为空！");
		}
		if (StringUtils.isEmpty(codeMapper.getExtCodeType())) {
			throw new ServiceException("源代码类型不能为空！");
		}
		if (StringUtils.isEmpty(codeMapper.getExtSysFlg())) {
			throw new ServiceException("源系统类型不能为空！");
		}

		// 存在判断
		SysCodeMapper extTest = this.getOneCodeMapper(codeMapper.getCodeType(), codeMapper.getCodeVal(), codeMapper.getExtSysFlg(), codeMapper.getExtCodeType());
		if (extTest != null) {
			throw new ServiceException(
					"码值映射信息已存在：代码类型[" + codeMapper.getCodeType() + "]，代码值[" + codeMapper.getCodeVal() + "]"
							+ "，源系统类型[" + codeMapper.getExtCodeType() + "]，源代码类型[" + codeMapper.getExtSysFlg() + "]");
		}

		codeMapper.setRecStat("0");

		int addCount = mapper.insert(codeMapper);

		return addCount;
	}

	/**
	 * 修改某条码值映射信息的方法
	 * 
	 * @param codeMapper
	 *            需要修改的码值映射信息
	 * @return 修改成功的条目数，0为失败
	 */
	public int modCodeMapper(SysCodeMapper codeMapper) {

		if (codeMapper == null) {
			throw new ServiceException("输入信息不能为空！");
		}

		if (StringUtils.isEmpty(codeMapper.getCodeType())) {
			throw new ServiceException("代码类型不能为空！");
		}
		if (StringUtils.isEmpty(codeMapper.getCodeVal())) {
			throw new ServiceException("代码值不能为空！");
		}
		if (StringUtils.isEmpty(codeMapper.getExtCodeType())) {
			throw new ServiceException("源代码类型不能为空！");
		}
		if (StringUtils.isEmpty(codeMapper.getExtSysFlg())) {
			throw new ServiceException("源系统类型不能为空！");
		}

		// 存在判断
		SysCodeMapper extTest = this.getOneCodeMapper(codeMapper.getCodeType(), codeMapper.getCodeVal(), codeMapper.getExtSysFlg(), codeMapper.getExtCodeType());
		if (extTest == null) {
			throw new ServiceException(
					"码值映射信息不存在：代码类型[" + codeMapper.getCodeType() + "]，代码值[" + codeMapper.getCodeVal() + "]"
							+ "，源系统类型[" + codeMapper.getExtCodeType() + "]，源代码类型[" + codeMapper.getExtSysFlg() + "]");
		}

		extTest.setTypeName(codeMapper.getTypeName());
		extTest.setCodeName(codeMapper.getCodeName());
		extTest.setExtCodeVal(codeMapper.getExtCodeVal());
		extTest.setRemark(codeMapper.getRemark());
		
		int modCount = mapper.updateByPrimaryKey(extTest);

		return modCount;
	}

	/**
	 * 根据主键物理删除某码值映射信息的方法
	 * 
	 * @param String codeType, String codeVal, String extSysFlg, String extCodeType
	 *            代码类型 代码值 源系统标志 源代码类型
	 * @return
	 */
	public int delCodeMapper(String codeType, String codeVal, String extSysFlg, String extCodeType) {

		// 代码类型非空判断
		if (codeType == null || "".equals(codeType)) {
			throw new ServiceException("代码类型[" + codeType + "]不能为空！");
		}
		// 代码值非空判断
		if (codeVal == null || "".equals(codeVal)) {
			throw new ServiceException("代码值[" + codeVal + "]不能为空！");
		}
		// 源系统标志非空判断
		if (extSysFlg == null || "".equals(extSysFlg)) {
			throw new ServiceException("源系统标志[" + extSysFlg + "]不能为空！");
		}
		// 源代码类型非空判断
		if (extCodeType == null || "".equals(extCodeType)) {
			throw new ServiceException("源代码类型[" + extCodeType + "]不能为空！");
		}
		// 存在判断
		SysCodeMapper extTest = this.getOneCodeMapper(codeType, codeVal, extSysFlg, extCodeType);
		if (extTest == null) {
			throw new ServiceException(
					"码值映射信息不存在：代码类型[" + codeType + "]，代码值[" + codeVal + "]"
							+ "，源系统类型[" + extSysFlg + "]，源代码类型[" + extCodeType + "]");
		}

		int delCount = mapper.deleteByPrimaryKey(codeType, codeVal, extSysFlg, extCodeType);

		return delCount;
	}



}
