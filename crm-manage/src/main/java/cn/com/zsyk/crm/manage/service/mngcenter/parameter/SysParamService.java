package cn.com.zsyk.crm.manage.service.mngcenter.parameter;

import java.util.List;

import cn.com.zsyk.crm.common.constant.RedisConsts;
import cn.com.zsyk.crm.common.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.zsyk.crm.common.dao.AbstractDao;
import cn.com.zsyk.crm.common.dto.ContextContainer;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.manage.entity.SysParam;
import cn.com.zsyk.crm.manage.mapper.SysParamMapper;

@Service
@Transactional 
public class SysParamService {
	@Autowired
	private SysParamMapper paramMapper;
	@Autowired
	private AbstractDao daoUtil;
	@Autowired
	private RedisUtil redisUtil;
	/**
	 * 获取多条参数
	 * 
	 * @param SysParam sysParam
	 *            系统参数
	 * @return 新增成功的记录条数
	 */
	public List<SysParam> getParams(SysParam sysParam){
		// 根据参数类型、参数代码、参数名
		List<SysParam> sysParams = daoUtil.selectList("cn.com.zsyk.crm.manage.mapper.SysParamMapper.selectByEntity", sysParam);
		return sysParams;
	}
	
	/**
	 * 获取一条参数
	 * 
	 * @param param_type param_code
	 *            参数类型，参数代码
	 * @return 新增成功的记录条数
	 */
	public SysParam getOne(String param_type, String param_code) {
		// 主键不能为空
		if (param_type == null || "".equals(param_type)) {
			throw new ServiceException("参数类型[" + param_type + "]不能为空！");
		} 
		if (param_code == null || "".equals(param_code)) {
			throw new ServiceException("参数代码[" + param_code + "]不能为空！");
		} 
		SysParam sysParam = paramMapper.selectByPrimaryKey(param_type, param_code);
		try{
			redisUtil.set("sysparam_"+param_type+"_"+param_code,sysParam.getParamValue(), RedisConsts.REF_TIME);
		}catch(Exception e){
		}
		return sysParam;
	}
	
	/**
	 * 获取所有参数的方法
	 * 
	 * @return 所有参数的列表
	 */
	public List<SysParam> getAllParam() {

		List<SysParam> params = paramMapper.selectAll();

		return params;
	}
	
	/**
	 * 新增一条参数
	 * 
	 * @param SysParam sysParam
	 *            系统参数
	 * @return 新增成功的记录条数
	 */
	public int addParam(SysParam sysParam) {

		if (sysParam == null) {
			throw new ServiceException("输入信息不能为空！");
		}

		// 存在判断
		SysParam paramTest = this.getOne(sysParam.getParamType(), sysParam.getParamCode());
		if (paramTest != null) {
			throw new ServiceException(
					"参数信息已存在：参数类型[" + sysParam.getParamType() + "]，参数代码[" + sysParam.getParamCode() + "]");
		} 
		int addCount = paramMapper.insert(sysParam);

		return addCount;
	}
		
	/**
	 * 修改某条参数的方法
	 * 
	 * @param userInfo
	 *            需要修改的参数
	 * @return 修改成功的条目数，0为失败
	 */
	public int modParam(SysParam sysParam) {

		if (sysParam == null) {
			throw new ServiceException("输入信息不能为空！");
		}

		// 存在判断
		SysParam paramTest = this.getOne(sysParam.getParamType(), sysParam.getParamCode());
		if (paramTest == null) {
			throw new ServiceException(
					"参数不存在：参数类型[" + sysParam.getParamType() + "]，参数代码[" + sysParam.getParamCode() + "]");
		}
		paramTest.setParamValue(sysParam.getParamValue());
		paramTest.setParamDesc(sysParam.getParamDesc());
		

		int modCount = paramMapper.updateByPrimaryKey(paramTest);

		return modCount;
	}

	
	/**
	 * 根据主键物理删除某参数的方法
	 * 
	 * @param param_type param_code
	 *            参数类型，参数代码
	 * @return
	 */
	public int delParam(String param_type, String param_code) {

		// 主键不能为空
		if (param_type == null || "".equals(param_type)) {
			throw new ServiceException("参数类型[" + param_type + "]不能为空！");
		} 
		if (param_code == null || "".equals(param_code)) {
			throw new ServiceException("参数代码[" + param_code + "]不能为空！");
		} 

		// 存在判断
		SysParam paramTest = this.getOne(param_type, param_code);
		if (paramTest == null) {
			throw new ServiceException(
					"参数不存在：参数类型[" + param_type + "]，参数代码[" + param_code + "]");
		}

		int delCount = paramMapper.deleteByPrimaryKey(param_type, param_code);

		return delCount;
	}

	/**
	 * 逻辑删除，即将参数置为无效化的方法
	 * 
	 * @param sysParam
	 *            需要无效化的参数
	 * @return 无效化修改完成的参数数量，0为失败
	 */
	public int delModParam(SysParam sysParam) {

		// 存在判断
		SysParam paramTest = this.getOne(sysParam.getParamType(), sysParam.getParamCode());
		if (paramTest == null) {
			throw new ServiceException(
					"参数不存在：参数类型[" + sysParam.getParamType() + "]，参数代码[" + sysParam.getParamCode() + "]");
		}


		paramTest.setRecStat("1");
		int modCount = paramMapper.updateByPrimaryKey(paramTest);

		return modCount;
	}
	
}
