package cn.com.zsyk.crm.manage.service.engine;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.manage.entity.SysRuleFactor;
import cn.com.zsyk.crm.manage.entity.SysRuleFactorValueType;
import cn.com.zsyk.crm.manage.mapper.SysRuleFactorMapper;
import cn.com.zsyk.crm.manage.mapper.SysRuleFactorValueTypeMapper;

@Service
@Transactional
public class FactorValueTypeService {

	
	@Autowired
	SysRuleFactorValueTypeMapper valueMapper;
    @Autowired
    private RuleFactorService factorService;
    @Autowired
    private SysRuleFactorMapper ruleFactorMapper;
	
	//查询一条（记录正常）
	public SysRuleFactorValueType getOneRec(SysRuleFactorValueType record) {
		record.setRecStat("0");
		List<SysRuleFactorValueType> recList = valueMapper.selectByEntity(record);
		if( recList == null || recList.size() <= 0 ) {
			return null;
		}
		return recList.get(0);
	}
	//查询一条（记录正常）byPK
	public SysRuleFactorValueType getOneRecByPk(SysRuleFactorValueType record) {
		record.setRecStat("0");
		SysRuleFactorValueType rec = valueMapper.selectByPrimaryKey(record.getId());
		return rec;
	}
	//查询一条（记录存在性）
	public SysRuleFactorValueType getOne(SysRuleFactorValueType record) {
		List<SysRuleFactorValueType> recList = valueMapper.selectByEntity(record);
		if( recList == null || recList.size() <= 0 ) {
			return null;
		}
		return recList.get(0);
	}
	
	//查询多条（记录正常）
	public List<SysRuleFactorValueType> getMultiRec(SysRuleFactorValueType record) {
		record.setRecStat("0");
		List<SysRuleFactorValueType> recList = valueMapper.selectByEntity(record);
		if( recList == null || recList.size() <= 0 ) {
			return null;
		}
		return recList;
	}
	
	//查询多条（记录存在性）
	public List<SysRuleFactorValueType> getMulti(SysRuleFactorValueType record) {
		List<SysRuleFactorValueType> recList = valueMapper.selectByEntity(record);
		if( recList == null || recList.size() <= 0 ) {
			return null;
		}
		return recList;
	}

	//查询列表（全列表）
	public List<SysRuleFactorValueType> getAllRows() {
		List<SysRuleFactorValueType> recList = valueMapper.selectAll();
		return recList;
	}
	
	//查询列表（全列表正常）
	public List<SysRuleFactorValueType> getAllRecRows() {
		List<SysRuleFactorValueType> recList = valueMapper.selectAllRec();
		return recList;
	}
	
	//新增
	public SysRuleFactorValueType insertOne(SysRuleFactorValueType record) {
		//单条记录正常性校验
		SysRuleFactorValueType checkBean = this.getOneRec(record);		
		//校验已存在报错
		if( checkBean != null ) {
			throw new ServiceException("欲新增数据已经存在[数据类型："+record.getValueType()+"，类型说明："+record.getValueDesc()+"]");
		}
		//存入记录
		record.setRecStat("0");
		valueMapper.insert(record);
		return record;
	}
	
	//修改
	public SysRuleFactorValueType updateOne(SysRuleFactorValueType record) {
		//单条记录正常性校验
		SysRuleFactorValueType checkBean = this.getOneRecByPk(record);		
		//校验不存在报错
		if( checkBean == null ) {
			throw new ServiceException("欲修改数据不存在或已删除[数据类型："+record.getValueType()+"，类型说明："+record.getValueDesc()+"]");
		}
		
		//使用该操作类型的因子更新
		SysRuleFactor factor = new SysRuleFactor();
		factor.setValueType(checkBean.getValueType());
		
		List<SysRuleFactor> facList = ruleFactorMapper.findAllByCondition(factor);
		if( facList != null && facList.size() > 0) {
			for( SysRuleFactor item : facList ) {
				item.setValueType(record.getValueType());
				item.setRecStat("0");
				ruleFactorMapper.updateByPrimaryKey(item);
			}
		}
		
		//更新记录信息，使用已查询到进行赋值后更新
		checkBean.setValueDesc(record.getValueDesc());
		checkBean.setValueType(record.getValueType());
		checkBean.setRecStat("0");
		valueMapper.updateByPrimaryKey(checkBean);
		
		return checkBean;
	}
	
	//删除
	public void deleteOne(SysRuleFactorValueType record) {
		//单条记录正常性校验
		SysRuleFactorValueType checkBean = this.getOneRec(record);		
		
		//校验不存在报错
		if( checkBean == null ) {
			throw new ServiceException("欲删除数据不存在或已删除[数据类型："+record.getValueType()+"，类型说明："+record.getValueDesc()+"]");
		}
		
		//查询是否有因子正在使用该类型
		SysRuleFactor factor = new SysRuleFactor();
		factor.setValueType(record.getValueType());
		factor.setRecStat("0");
		factor = factorService.findOneRec(factor);
		if( factor != null ) {
			throw new ServiceException("欲删除数据在某因子数据中正在使用，不可删除[因子名称："+factor.getFactorName()+"]");
		}
		//删除记录信息，根据已查询到的记录进行主键删除
		valueMapper.deleteByPrimaryKey(checkBean.getId());
		
	}
	
	//逻辑删除
	public void invalidOne(SysRuleFactorValueType record) {
		//单条记录正常性校验
		SysRuleFactorValueType checkBean = this.getOneRec(record);		
		
		//校验不存在时报错
		if( checkBean == null ) {
			throw new ServiceException("欲删除数据不存在或已删除[数据类型："+record.getValueType()+"，类型说明："+record.getValueDesc()+"]");
		}
		
		//更新记录-记录状态为删除(1)，根据已查询到的记录进行删除
		checkBean.setRecStat("1");
		valueMapper.updateByPrimaryKey(checkBean);
		
	}
	
	
	
}
