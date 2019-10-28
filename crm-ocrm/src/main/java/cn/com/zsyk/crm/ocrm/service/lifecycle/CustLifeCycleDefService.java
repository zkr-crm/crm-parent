package cn.com.zsyk.crm.ocrm.service.lifecycle;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.zsyk.crm.common.dao.AbstractDao;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.ocrm.entity.OcCustLifeCycleDef;
import cn.com.zsyk.crm.ocrm.mapper.OcCustLifeCycleDefMapper;

@Service
@Transactional
public class CustLifeCycleDefService {

	@Autowired
	private OcCustLifeCycleDefMapper CustLifeCycleDefMapper;

	@Autowired
	private AbstractDao daoUtil;

	/**
	 * 获得一条客户生命周期定义的方法
	 * 
	 * @param stageId
	 *            客户生命周期定义ID
	 * @return 客户生命周期定义
	 */
	public OcCustLifeCycleDef getOneCustLifeCycleDef(int stageId) {

		// 客户生命周期定义ID非空判断
		OcCustLifeCycleDef CustLifeCycleDef = CustLifeCycleDefMapper.selectByPrimaryKey(stageId);

		return CustLifeCycleDef;
	}


	/**
	 * 获取所有客户生命周期定义的方法
	 * 
	 * @return 所有客户生命周期定义的列表
	 */
	public List<OcCustLifeCycleDef> getAllCustLifeCycleDef() {

		List<OcCustLifeCycleDef> lstCustLifeCycleDef = CustLifeCycleDefMapper.selectAll();

		return lstCustLifeCycleDef;
	}





	/**
	 * 新增一条客户生命周期定义
	 * 
	 * @param stageId
	 *            客户生命周期定义ID
	 * @param CustLifeCycleDefname
	 *            客户生命周期定义名称
	 * @return 新增成功的记录条数
	 */
	public int addCustLifeCycleDef(OcCustLifeCycleDef custLifeCycleDef) {

		if (custLifeCycleDef == null) {
			throw new ServiceException("输入信息不能为空！");
		}

		// 存在判断
		List<OcCustLifeCycleDef> lstCustLifeCycleDef = this.getAllCustLifeCycleDef();
		int stageId = 1;
		if(lstCustLifeCycleDef.size() == 0) {
			custLifeCycleDef.setStageId(stageId);
		}else {
			int i = 1;
			for(OcCustLifeCycleDef def : lstCustLifeCycleDef) {
				if(i != def.getStageId()) {
					custLifeCycleDef.setStageId(i);
					break;
				}
				i++;
			}
		}
	
		int addCount = CustLifeCycleDefMapper.insert(custLifeCycleDef);

		return addCount;
	}

	/**
	 * 修改某条客户生命周期定义的方法
	 * 
	 * @param CustLifeCycleDef
	 *            需要修改的客户生命周期定义
	 * @return 修改成功的条目数，0为失败
	 */
	public int modCustLifeCycleDef(OcCustLifeCycleDef custLifeCycleDef) {

		if (custLifeCycleDef == null) {
			throw new ServiceException("输入信息不能为空！");
		}

		// 存在判断
		OcCustLifeCycleDef extTest = this.getOneCustLifeCycleDef(custLifeCycleDef.getStageId());
		if (extTest == null) {
			throw new ServiceException(
					"客户生命周期定义不存在：客户生命周期定义ID[" + custLifeCycleDef.getStageId() + "]，客户生命周期定义名称[" + custLifeCycleDef.getStageName() + "]");
		}

		

		int modCount = CustLifeCycleDefMapper.updateByPrimaryKey(extTest);

		return modCount;
	}



	/**
	 * 根据主键物理删除某客户生命周期定义的方法
	 * 
	 * @param stageId
	 *            客户生命周期定义ID
	 * @return
	 */
	public int delCustLifeCycleDef(int stageId) {

		// 客户生命周期定义ID非空判断
		

		// 存在判断
		OcCustLifeCycleDef extTest = this.getOneCustLifeCycleDef(stageId);
		if (extTest == null) {
			throw new ServiceException("客户生命周期定义不存在：客户生命周期定义ID[" + stageId + "]");
		}

		int delCount = CustLifeCycleDefMapper.deleteByPrimaryKey(stageId);

		return delCount;
	}

	


}
