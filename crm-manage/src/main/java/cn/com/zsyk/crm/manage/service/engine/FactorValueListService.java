package cn.com.zsyk.crm.manage.service.engine;

import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.manage.entity.SysRuleFactorParam;
import cn.com.zsyk.crm.manage.mapper.SysRuleFactorParamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FactorValueListService {

	
	@Autowired
	private SysRuleFactorParamMapper paramMapper;
	
	//查询一条（记录正常）
	public SysRuleFactorParam getOneRec(SysRuleFactorParam record) {
		record.setRecStat("0");
		List<SysRuleFactorParam> recList = paramMapper.selectByEntity(record);
		if(recList != null && !recList.isEmpty()) {
			return recList.get(0);
		}
		return null;
	}
	//查询一条（记录正常）byPK
	public SysRuleFactorParam getOneRecByPk(SysRuleFactorParam record) {
		record.setRecStat("0");
		SysRuleFactorParam rec = paramMapper.selectByPrimaryKey(record.getParamName(), record.getItemCode());
		return rec;
	}
	//查询一条（记录存在性）
	public SysRuleFactorParam getOne(SysRuleFactorParam record) {
		List<SysRuleFactorParam> recList = paramMapper.selectByEntity(record);
		return recList.get(0);
	}
	
	//查询多条（记录正常）
	public List<SysRuleFactorParam> getMultiRec(SysRuleFactorParam record) {
		record.setRecStat("0");
		List<SysRuleFactorParam> recList = paramMapper.selectByEntity(record);
		return recList;
	}
	
	//查询多条（记录存在性）
	public List<SysRuleFactorParam> getMulti(SysRuleFactorParam record) {
		List<SysRuleFactorParam> recList = paramMapper.selectByEntity(record);
		return recList;
	}

	//查询列表（全列表）
	public List<SysRuleFactorParam> getAllRows() {
		List<SysRuleFactorParam> recList = paramMapper.selectAll();
		return recList;
	}
	
	//查询列表（全列表正常）
	public List<SysRuleFactorParam> getAllRecRows() {
		List<SysRuleFactorParam> recList = paramMapper.selectAllRec();
		return recList;
	}
	
	//新增
	public SysRuleFactorParam insertOne(SysRuleFactorParam record) {
		//单条记录正常性校验
		SysRuleFactorParam checkBean = this.getOneRec(record);		
		//校验已存在报错
		if( checkBean != null ) {
			throw new ServiceException("欲新增数据已经存在[参数名称："+record.getParamName()+"，条目代码："+record.getItemCode()+"]");
		}
		//存入记录
		record.setRecStat("0");
		paramMapper.insert(record);
		return record;
	}
	
	//修改
	public SysRuleFactorParam updateOne(SysRuleFactorParam record) {
		//单条记录正常性校验
		SysRuleFactorParam checkBean = this.getOneRecByPk(record);		
		//校验不存在报错
		if( checkBean == null ) {
			throw new ServiceException("欲修改数据不存在或已删除[参数名称："+record.getParamName()+"，条目代码："+record.getItemCode()+"]");
		}
		
		//更新记录信息，使用已查询到进行赋值后更新
		checkBean.setItemName(record.getItemName());
		checkBean.setRecStat("0");
		paramMapper.updateByPrimaryKey(checkBean);
		
		return checkBean;
	}
	
	//删除
	public void deleteOne(SysRuleFactorParam record) {
		//单条记录正常性校验
		SysRuleFactorParam checkBean = this.getOneRec(record);		
		
		//校验不存在报错
		if( checkBean == null ) {
			throw new ServiceException("欲删除数据不存在或已删除[参数名称："+record.getParamName()+"，条目代码："+record.getItemCode()+"]");
		}
		
		//删除记录信息，根据已查询到的记录进行主键删除
		paramMapper.deleteByPrimaryKey(checkBean.getParamName(), checkBean.getItemCode());
		
	}
	
	//逻辑删除
	public void invalidOne(SysRuleFactorParam record) {
		//单条记录正常性校验
		SysRuleFactorParam checkBean = this.getOneRec(record);		
		
		//校验不存在时报错
		if( checkBean == null ) {
			throw new ServiceException("欲删除数据不存在或已删除[参数名称："+record.getParamName()+"，条目代码："+record.getItemCode()+"]");
		}
		
		//更新记录-记录状态为删除(1)，根据已查询到的记录进行删除
		checkBean.setRecStat("1");
		paramMapper.updateByPrimaryKey(checkBean);
		
	}
	
	
	
}
