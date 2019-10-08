package cn.com.zsyk.crm.manage.service.engine;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.manage.entity.SysRuleFactor;
import cn.com.zsyk.crm.manage.entity.SysRuleFactorType;
import cn.com.zsyk.crm.manage.mapper.SysRuleFactorMapper;
import cn.com.zsyk.crm.manage.mapper.SysRuleFactorTypeMapper;

@Service
@Transactional
public class RuleFactorTypeService {

    @Autowired
    private SysRuleFactorTypeMapper ruleFactorTypeMapper;
    @Autowired
    private RuleFactorService factorService;
    @Autowired
    private SysRuleFactorMapper ruleFactorMapper;

    //查询一条（记录正常）
    public SysRuleFactorType findOneRec(SysRuleFactorType record) {
    	record.setRecStat("0");
    	List<SysRuleFactorType> retList = ruleFactorTypeMapper.selectAllByEntity(record);
    	if( retList == null || retList.size() <= 0 ) {
    		return null;
    	}
		return retList.get(0);
    }
    //查询一条（记录正常）根据主键
    public SysRuleFactorType findOneRecByPk(SysRuleFactorType record) {
    	record.setRecStat("0");
    	SysRuleFactorType retBean = ruleFactorTypeMapper.selectByPrimaryKey(record.getId());
		return retBean;
    }
    //查询一条（记录存在性）
    public SysRuleFactorType findOne(SysRuleFactorType record) {
    	List<SysRuleFactorType> retList = ruleFactorTypeMapper.selectAllByEntity(record);
    	if( retList == null || retList.size() <= 0 ) {
    		return null;
    	}
		return retList.get(0);
    }
    
    //查询多条（记录正常）
    public List<SysRuleFactorType> findMultyRecByEntity(SysRuleFactorType ruleFactor){
    	ruleFactor.setRecStat("0");
    	List<SysRuleFactorType> retList = ruleFactorTypeMapper.selectAllByEntity(ruleFactor);
    	if( retList == null || retList.size() <= 0  ) {
    		return null;
    	}
        return retList;
    }

    //查询多条（记录存在性）
    public List<SysRuleFactorType> findMultyByEntity(SysRuleFactorType ruleFactor){
    	List<SysRuleFactorType> retList = ruleFactorTypeMapper.selectAllByEntity(ruleFactor);
    	if( retList == null || retList.size() <= 0  ) {
    		return null;
    	}
        return retList;
    }
    //查询列表（记录正常）
    public List<SysRuleFactorType> findAllRec(){
        return ruleFactorTypeMapper.selectAllRec();
    }
    //查询列表（记录存在性）
    public List<SysRuleFactorType> findAll(){
        return ruleFactorTypeMapper.selectAll();
    }
    //新增
    public SysRuleFactorType insertOne(SysRuleFactorType record) {
		//单条记录正常性校验
    	SysRuleFactorType checkBean = this.findOneRec(record);		
		//校验已存在报错
		if( checkBean != null ) {
			throw new ServiceException("欲新增数据已经存在[类型名称："+record.getFactorType()+"，类型描述："+record.getTypeDesc()+"]");
		}
		//存入记录
		record.setRecStat("0");
		ruleFactorTypeMapper.insert(record);
		return record;
	}
    //修改
    public SysRuleFactorType updateOne(SysRuleFactorType record) {
		//单条记录正常性校验
    	SysRuleFactorType checkBean = this.findOneRecByPk(record);		
		//校验不存在报错
		if( checkBean == null ) {
			throw new ServiceException("欲修改数据不存在或已经删除[类型名称："+record.getFactorType()+"，类型描述："+record.getTypeDesc()+"]");
		}
		

		//使用该操作类型的因子更新
		SysRuleFactor factor = new SysRuleFactor();
		factor.setFactorType(checkBean.getFactorType());
		
		List<SysRuleFactor> facList = ruleFactorMapper.findAllByCondition(factor);
		if( facList != null && facList.size() > 0) {
			for( SysRuleFactor item : facList ) {
				item.setFactorType(record.getFactorType());
				item.setRecStat("0");
				ruleFactorMapper.updateByPrimaryKey(item);
			}
		}
		
		//修改记录属性
		checkBean.setFactorType(record.getFactorType());
		checkBean.setTypeDesc(record.getTypeDesc());
		checkBean.setRecStat("0");
		ruleFactorTypeMapper.updateByPrimaryKey(checkBean);
		return record;
	}
    
    //删除
	public void deleteOne(SysRuleFactorType record) {
		//单条记录正常性校验
		SysRuleFactorType checkBean = this.findOneRec(record);		
		
		//校验不存在报错
		if( checkBean == null ) {
			throw new ServiceException("欲删除数据不存在或已删除[类型名称："+record.getFactorType()+"，类型描述："+record.getTypeDesc()+"]");
		}
		
		//查询是否有因子正在使用该类型
		SysRuleFactor factor = new SysRuleFactor();
		factor.setFactorType(record.getFactorType());
		factor.setRecStat("0");
		factor = factorService.findOneRec(factor);
		if( factor != null ) {
			throw new ServiceException("欲删除数据在某因子数据中正在使用，不可删除[因子名称："+factor.getFactorName()+"]");
		}
		
		//删除记录信息，根据已查询到的记录进行主键删除
		ruleFactorTypeMapper.deleteByPrimaryKey(checkBean.getId());
		
	}
	
    //逻辑删除
	public void invalidOne(SysRuleFactorType record) {
		//单条记录正常性校验
		SysRuleFactorType checkBean = this.findOneRec(record);		
		
		//校验不存在时报错
		if( checkBean == null ) {
			throw new ServiceException("欲删除数据不存在或已删除[类型名称："+record.getFactorType()+"，类型描述："+record.getTypeDesc()+"]");
		}
		
		//更新记录-记录状态为删除(1)，根据已查询到的记录进行删除
		checkBean.setRecStat("1");
		ruleFactorTypeMapper.updateByPrimaryKey(checkBean);
		
	}

	
}
