package cn.com.zsyk.crm.manage.service.engine;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.manage.entity.SysRuleFactor;
import cn.com.zsyk.crm.manage.entity.SysRuleFactorOperType;
import cn.com.zsyk.crm.manage.mapper.SysRuleFactorMapper;
import cn.com.zsyk.crm.manage.mapper.SysRuleFactorOperTypeMapper;

@Service
@Transactional
public class FactorOperTypeService {

	
	@Autowired
	SysRuleFactorOperTypeMapper operMapper;
    @Autowired
    private RuleFactorService factorService;
    @Autowired
    private SysRuleFactorMapper ruleFactorMapper;
	
	//查询一条（记录正常）
	public SysRuleFactorOperType getOneRec(SysRuleFactorOperType record) {
		record.setRecStat("0");
		List<SysRuleFactorOperType> recList = operMapper.selectByEntity(record);
		if( recList == null || recList.size() <= 0 ) {
			return null;
		}
		return recList.get(0);
	}
	//查询一条（记录正常）by主键
	public SysRuleFactorOperType getOneRecByPk(SysRuleFactorOperType record) {
		record.setRecStat("0");
		SysRuleFactorOperType recBean = operMapper.selectByPrimaryKey(record.getId());
		return recBean;
	}
	//查询一条（记录存在性）
	public SysRuleFactorOperType getOne(SysRuleFactorOperType record) {
		List<SysRuleFactorOperType> recList = operMapper.selectByEntity(record);
		if( recList == null || recList.size() <= 0 ) {
			return null;
		}
		return recList.get(0);
	}
	
	//查询多条（记录正常）
	public List<SysRuleFactorOperType> getMultiRec(SysRuleFactorOperType record) {
		record.setRecStat("0");
		List<SysRuleFactorOperType> recList = operMapper.selectByEntity(record);
		if( recList == null || recList.size() <= 0 ) {
			return null;
		}
		return recList;
	}
	
	//查询多条（记录存在性）
	public List<SysRuleFactorOperType> getMulti(SysRuleFactorOperType record) {
		List<SysRuleFactorOperType> recList = operMapper.selectByEntity(record);
		if( recList == null || recList.size() <= 0 ) {
			return null;
		}
		return recList;
	}

	//查询列表（全列表）
	public List<SysRuleFactorOperType> getAllRows() {
		List<SysRuleFactorOperType> recList = operMapper.selectAll();
		return recList;
	}
	
	//查询列表（全列表正常）
	public List<SysRuleFactorOperType> getAllRecRows() {
		List<SysRuleFactorOperType> recList = operMapper.selectAllRec();
		return recList;
	}
	
	//新增
	public SysRuleFactorOperType insertOne(SysRuleFactorOperType record) {
		//单条记录正常性校验
		SysRuleFactorOperType checkBean = this.getOneRec(record);		
		//校验已存在报错
		if( checkBean != null ) {
			throw new ServiceException("欲新增数据已经存在[操作类型："+record.getOperateType()+"，类型说明："+record.getOperateDesc()+"]");
		}
		//存入记录
		record.setRecStat("0");
		operMapper.insert(record);
		return record;
	}
	
	//修改
	public SysRuleFactorOperType updateOne(SysRuleFactorOperType record) {
		//单条记录正常性校验
		SysRuleFactorOperType checkBean = this.getOneRecByPk(record);		
		//校验不存在报错
		if( checkBean == null ) {
			throw new ServiceException("欲修改数据不存在或已删除[操作类型："+record.getOperateType()+"，类型说明："+record.getOperateDesc()+"]");
		}
		
		//使用该操作类型的因子更新
		SysRuleFactor factor = new SysRuleFactor();
		factor.setOperateType(checkBean.getOperateType());
		
		List<SysRuleFactor> facList = ruleFactorMapper.findAllByCondition(factor);
		if( facList != null && facList.size() > 0) {
			for( SysRuleFactor item : facList ) {
				item.setOperateType(record.getOperateType());
				item.setRecStat("0");
				ruleFactorMapper.updateByPrimaryKey(item);
			}
		}
		
		//更新记录信息，使用已查询到进行赋值后更新
		checkBean.setOperateDesc(record.getOperateDesc());
		checkBean.setOperateType(record.getOperateType());
		checkBean.setOperateValue(record.getOperateValue());
		checkBean.setRecStat("0");
		operMapper.updateByPrimaryKey(checkBean);
		
		return checkBean;
	}
	
	//删除
	public void deleteOne(SysRuleFactorOperType record) {
		//单条记录正常性校验
		SysRuleFactorOperType checkBean = this.getOneRec(record);		
		
		//校验不存在报错
		if( checkBean == null ) {
			throw new ServiceException("欲删除数据不存在或已删除[操作类型："+record.getOperateType()+"，类型说明："+record.getOperateDesc()+"]");
		}

		//查询是否有因子正在使用该类型
		SysRuleFactor factor = new SysRuleFactor();
		factor.setOperateType(record.getOperateType());
		factor.setRecStat("0");
		factor = factorService.findOneRec(factor);
		if( factor != null ) {
			throw new ServiceException("欲删除数据在某因子数据中正在使用，不可删除[因子名称："+factor.getFactorName()+"]");
		}
		
		//删除记录信息，根据已查询到的记录进行主键删除
		operMapper.deleteByPrimaryKey(checkBean.getId());
		
	}
	
	//逻辑删除
	public void invalidOne(SysRuleFactorOperType record) {
		//单条记录正常性校验
		SysRuleFactorOperType checkBean = this.getOneRec(record);		
		
		//校验不存在时报错
		if( checkBean == null ) {
			throw new ServiceException("欲删除数据不存在或已删除[操作类型："+record.getOperateType()+"，类型说明："+record.getOperateDesc()+"]");
		}
		
		//更新记录-记录状态为删除(1)，根据已查询到的记录进行删除
		checkBean.setRecStat("1");
		operMapper.updateByPrimaryKey(checkBean);
		
	}
	
	
	
}
