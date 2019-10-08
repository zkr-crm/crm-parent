package cn.com.zsyk.crm.manage.service.engine;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.generator.EnumType;
import cn.com.zsyk.crm.manage.entity.SysRuleFactor;
import cn.com.zsyk.crm.manage.entity.SysRuleFactorOperType;
import cn.com.zsyk.crm.manage.entity.SysRuleFactorParam;
import cn.com.zsyk.crm.manage.entity.SysRuleFactorValueType;
import cn.com.zsyk.crm.manage.mapper.SysRuleFactorMapper;
import cn.com.zsyk.crm.manage.mapper.SysRuleFactorParamMapper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class RuleFactorService {

    @Autowired
    private SysRuleFactorMapper ruleFactorMapper;
    @Autowired
    private SysRuleFactorParamMapper factorParamMapper;

    //查询一条（记录正常）
    public SysRuleFactor findOneRec(SysRuleFactor record) {
    	record.setRecStat("0");
    	List<SysRuleFactor> retList = ruleFactorMapper.findAllByCondition(record);
    	if( retList == null || retList.size() <= 0 ) {
    		return null;
    	}
		return retList.get(0);
    }
    //查询一条（记录正常）根据主键
    public SysRuleFactor findOneRecByPk(SysRuleFactor record) {
    	record.setRecStat("0");
    	SysRuleFactor retBean = ruleFactorMapper.selectByPrimaryKey(record.getFactorId());
		return retBean;
    }
    //查询一条（记录存在性）
    public SysRuleFactor findOne(SysRuleFactor record) {
    	List<SysRuleFactor> retList = ruleFactorMapper.findAllByCondition(record);
    	if( retList == null || retList.size() <= 0 ) {
    		return null;
    	}
		return retList.get(0);
    }
    
    //查询多条（记录正常）
    public List<SysRuleFactor> findMultyRecByEntity(SysRuleFactor ruleFactor){
    	ruleFactor.setRecStat("0");
    	List<SysRuleFactor> retList = ruleFactorMapper.findAllByCondition(ruleFactor);
    	if( retList == null || retList.size() <= 0  ) {
    		return null;
    	}
        return retList;
    }

    //查询多条（记录存在性）
    public List<SysRuleFactor> findMultyByEntity(SysRuleFactor ruleFactor){
    	List<SysRuleFactor> retList = ruleFactorMapper.findAllByCondition(ruleFactor);
    	if( retList == null || retList.size() <= 0  ) {
    		return null;
    	}
        return retList;
    }
    //查询列表（记录正常）
    public List<SysRuleFactor> findAllRec(){
        return ruleFactorMapper.selectAllRec();
    }
    //查询列表（记录存在性）
    public List<SysRuleFactor> findAll(){
        return ruleFactorMapper.selectAll();
    }
    //新增
    public SysRuleFactor insertOne(SysRuleFactor record) {
		//单条记录正常性校验
    	SysRuleFactor checkBean = this.findOneRec(record);		
		//校验已存在报错
		if( checkBean != null ) {
			throw new ServiceException("欲新增数据已经存在[因子名称："+record.getFactorName()+"，因子类型："+record.getFactorType()+"]");
		}
		//存入记录
		record.setRecStat("0");
		ruleFactorMapper.insert(record);
		return record;
	}
    //修改
    public SysRuleFactor updateOne(SysRuleFactor record) {
		//单条记录正常性校验
    	SysRuleFactor checkBean = this.findOneRecByPk(record);		
		//校验不存在报错
		if( checkBean.getFactorId() == null || "".equals(checkBean.getFactorId())) {
			throw new ServiceException("欲修改数据不存在或已经删除[因子名称："+record.getFactorName()+"，因子类型："+record.getFactorType()+"]");
		}
		//修改记录属性
	
		checkBean.setFactorName(record.getFactorName());
		checkBean.setDisplayName(record.getDisplayName());
		checkBean.setFactorType(record.getFactorType());
		checkBean.setFactorExp(record.getFactorExp());
		checkBean.setOperateType(record.getOperateType());
		checkBean.setValueList(record.getValueList());
		checkBean.setValueType(record.getValueType());
		checkBean.setRecStat(EnumType.RecStat.normal.value);
		ruleFactorMapper.updateByPrimaryKey(checkBean);
		return record;
	}
    
    //删除
	public void deleteOne(SysRuleFactor record) {
		//单条记录正常性校验
		SysRuleFactor checkBean = this.findOneRec(record);		
		
		//校验不存在报错
		if( checkBean == null ) {
			throw new ServiceException("欲删除数据不存在或已删除[因子名称："+record.getFactorName()+"，因子类型："+record.getFactorType()+"]");
		}
		
		//删除记录信息，根据已查询到的记录进行主键删除
		ruleFactorMapper.deleteByPrimaryKey(checkBean.getFactorId());
		
	}
	
    //逻辑删除
	public void invalidOne(SysRuleFactor record) {
		//单条记录正常性校验
		SysRuleFactor checkBean = this.findOneRec(record);		
		
		//校验不存在时报错
		if( checkBean == null ) {
			throw new ServiceException("欲删除数据不存在或已删除[因子名称："+record.getFactorName()+"，因子类型："+record.getFactorType()+"]");
		}
		
		//更新记录-记录状态为删除(1)，根据已查询到的记录进行删除
		checkBean.setRecStat("1");
		ruleFactorMapper.updateByPrimaryKey(checkBean);
		
	}

	
	//根据对象查询因子参数数据
	public List<SysRuleFactorParam> getAllFactorParamByEntity(SysRuleFactorParam record){
		
		return factorParamMapper.getByEntity(record);
	} 
}
