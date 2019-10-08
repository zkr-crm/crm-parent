package cn.com.zsyk.crm.ecif.service.customer.importallot;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.zsyk.crm.common.dao.AbstractDao;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.IdGenerator;
import cn.com.zsyk.crm.ecif.entity.EcCustPer;
import cn.com.zsyk.crm.ecif.entity.EcCustSaleChgTrace;
import cn.com.zsyk.crm.ecif.mapper.EcCustPerMapper;
import cn.com.zsyk.crm.ecif.mapper.EcCustSaleChgTraceMapper;


@Service
@Transactional
public class CustSaleChgTraceService {
	@Autowired
	private AbstractDao daoUtil;
	@Autowired
	private EcCustSaleChgTraceMapper mapper;
	@Autowired
	private EcCustPerMapper ecCustPerMapper;
	/**
	 * 获得一条客户销售人员变更轨迹的方法
	 * 
	 * @param traceId
	 *            客户销售人员变更轨迹ID
	 * @return 客户销售人员变更轨迹
	 */
	public EcCustSaleChgTrace getOneCustSaleChgTrace(long traceId) {

		// 客户销售人员变更轨迹ID非空判断
		EcCustSaleChgTrace custSaleChgTrace = mapper.selectByPrimaryKey(traceId);

		return custSaleChgTrace;
	}


	/**
	 * 获取所有客户销售人员变更轨迹的方法
	 * 
	 * @return 所有客户销售人员变更轨迹的列表
	 */
	public List<EcCustSaleChgTrace> getAllCustSaleChgTrace() {

		List<EcCustSaleChgTrace> lstcustSaleChgTrace = mapper.selectAll();

		return lstcustSaleChgTrace;
	}


	/**
	 *根据客户销售人员变更轨迹姓名及电话模糊查询
	 * 
	 * @return 客户销售人员变更轨迹的列表
	 */
	public List<EcCustSaleChgTrace> getCustSaleChgTraceByName(EcCustSaleChgTrace custContrac) {

		List<EcCustSaleChgTrace> lstUser = daoUtil
				.selectList("cn.com.zsyk.crm.ocrm.mapper.EcCustSaleChgTraceMapper.selectByName", custContrac);

		return lstUser;
	}


	/**
	 * 新增一条客户销售人员变更轨迹
	 * 
	 * @param traceId
	 *            客户销售人员变更轨迹ID
	 * @param custSaleChgTracename
	 *            客户销售人员变更轨迹名称
	 * @return 新增成功的记录条数
	 */
	public int addCustSaleChgTrace(EcCustSaleChgTrace custSaleChgTrace) {

		if (custSaleChgTrace == null) {
			throw new ServiceException("输入信息不能为空！");
		}
		EcCustPer per = ecCustPerMapper.selectByPrimaryKey(custSaleChgTrace.getCustNo());
		if (per == null) {
			throw new ServiceException("待分配客户信息不存在");
		}
		custSaleChgTrace.setCustType(per.getCustTyp());
		custSaleChgTrace.setDealTime(new Date());
		custSaleChgTrace.setTraceId(IdGenerator.getDistributedID());
		int addCount = mapper.insert(custSaleChgTrace);

		return addCount;
	}

	/**
	 * 修改某条客户销售人员变更轨迹的方法
	 * 
	 * @param custSaleChgTrace
	 *            需要修改的客户销售人员变更轨迹
	 * @return 修改成功的条目数，0为失败
	 */
	public int modCustSaleChgTrace(EcCustSaleChgTrace custSaleChgTrace) {

		if (custSaleChgTrace == null) {
			throw new ServiceException("输入信息不能为空！");
		}

		// 存在判断
		EcCustSaleChgTrace extTest = this.getOneCustSaleChgTrace(custSaleChgTrace.getTraceId());
		if (extTest == null) {
			throw new ServiceException(
					"客户销售人员变更轨迹不存在：客户销售人员变更轨迹ID[" + custSaleChgTrace.getTraceId() + "]");
		}
		
		

		int modCount = mapper.updateByPrimaryKey(extTest);

		return modCount;
	}



	/**
	 * 根据主键物理删除某客户销售人员变更轨迹的方法
	 * 
	 * @param traceId
	 *            客户销售人员变更轨迹ID
	 * @return
	 */
	public int delCustSaleChgTrace(long traceId) {

		// 客户销售人员变更轨迹ID非空判断
		

		// 存在判断
		EcCustSaleChgTrace extTest = this.getOneCustSaleChgTrace(traceId);
		if (extTest == null) {
			throw new ServiceException("客户销售人员变更轨迹不存在：客户销售人员变更轨迹ID[" + traceId + "]");
		}

		int delCount = mapper.deleteByPrimaryKey(traceId);

		return delCount;
	}
}
