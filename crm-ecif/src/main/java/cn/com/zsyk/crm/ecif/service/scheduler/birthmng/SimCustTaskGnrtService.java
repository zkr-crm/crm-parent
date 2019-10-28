package cn.com.zsyk.crm.ecif.service.scheduler.birthmng;

import cn.com.zsyk.crm.common.dao.CoreDaoImpl;
import cn.com.zsyk.crm.common.dto.ContextContainer;
import cn.com.zsyk.crm.common.dto.CoreContext;
import cn.com.zsyk.crm.common.dto.PageBean;
import cn.com.zsyk.crm.common.dto.PageContainer;
import cn.com.zsyk.crm.common.util.DateUtil;
import cn.com.zsyk.crm.common.util.IdGenerator;
import cn.com.zsyk.crm.ecif.bo.cust.SimCustBaseInfo;
import cn.com.zsyk.crm.ecif.bo.cust.SimCustTask;
import cn.com.zsyk.crm.ecif.entity.*;
import cn.com.zsyk.crm.ecif.mapper.*;
import cn.com.zsyk.crm.ecif.service.similar.CustSimTaskMngService;
import cn.com.zsyk.crm.generator.EnumType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 相似客户任务生成批量
 * 
 * @author
 * 
 *
 */
@Service
@Transactional
public class SimCustTaskGnrtService {

	@Autowired
	private EcSimCustRuleMapper simCustRuleMapper;
	@Autowired
	private EcCustPerMapper custPerMapper;
	@Autowired
	private EcSimCustMapper simCustMapper;
	@Autowired
	private EcSimTaskMapper simTaskMapper;
	@Autowired
	private EcDiffCustMapper diffCustMapper;
	/** 相似客户合并拆分轨迹表 */
	@Autowired
	private EcMergeSplitTraceMapper mergeSplitTraceMapper;
    /* 分页查询对象 */
    @Autowired
    private CoreDaoImpl coreDaoImpl;
    @Autowired
	private CustSimTaskMngService custSimTaskMngService;

	/**
	 * 相似客户任务生成
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void simCustTaskGnrt(ECCustpers ecCustPer) {

		// 获取所有相似规则
		List<EcSimCustRule> simCustRuleList = simCustRuleMapper.selectAll();

		// 相似客户任务
		List<SimCustTask> simCustTaskList = new ArrayList<SimCustTask>();

		PageContainer pageContainer =new PageContainer();
		int i = 1;
		int perSelectCount = 1000;
		pageContainer.setPageNum(i);
		pageContainer.setPageSize(perSelectCount);
				// 遍历所有规则，获取当前客户的相似客户
				for (EcSimCustRule item : simCustRuleList) {
					// 匹配条件
					String[] judgeCond = item.getJudgeCondition().split(",");
					// 根据客户号和规则获取与该客户号相似的符合该规则的所有客户信息
					Map mapForSimCust = new HashMap();
					mapForSimCust.put("custNo", ecCustPer.getCustNo());
					//custPerMapper.selectCustlist(mapForSimCust);

					// 获取客户基础信息
                    PageBean beans = coreDaoImpl.selectPageByMapper(custPerMapper, "selectCustlist", mapForSimCust);

                    List<SimCustBaseInfo> custLists = beans.getList();
					SimCustBaseInfo baseCust=null;
                    if(custLists.size()!=0) {
                    	baseCust = custLists.get(0);
					}else{
						continue;
					}
					// 相似客户查询条件
					mapForSimCust.clear();
					int cust = 0;
					// 遍历相似匹配规则
					for (String judgeItem : judgeCond) {

						// 客户名
						if (EnumType.SimCustJudeParam.custName.value.equals(judgeItem)) {
							if(baseCust.getCustNam()==null||"".equals(baseCust.getCustNam())){
								cust = 1;
								continue;
							}
							mapForSimCust.put("custNam", baseCust.getCustNam());
						}
						// 客户性别
						if (EnumType.SimCustJudeParam.sex.value.equals(judgeItem)) {
							if(baseCust.getSex()==null||"".equals(baseCust.getSex())){
								cust = 1;
								continue;
							}
							mapForSimCust.put("sex", baseCust.getSex());
						}
						// 出生日期
						if (EnumType.SimCustJudeParam.birthDate.value.equals(judgeItem)) {
							if(baseCust.getBirthDate()==null){
								cust = 1;
								continue;
							}
							mapForSimCust.put("birthDate", baseCust.getBirthDate());
						}
						// 证件类型
						if (EnumType.SimCustJudeParam.certTyp.value.equals(judgeItem)) {
							if(baseCust.getCertTyp()==null||"".equals(baseCust.getCertTyp())){
								cust = 1;
								continue;
							}
							mapForSimCust.put("certTyp", baseCust.getCertTyp());
						}
						// 证件号码
						if (EnumType.SimCustJudeParam.certNo.value.equals(judgeItem)) {
							if(baseCust.getCertNo()==null||"".equals(baseCust.getCertNo())){
								cust = 1;
								continue;
							}
							mapForSimCust.put("certNo", baseCust.getCertNo());
						}
						// 手机号码
						if (EnumType.SimCustJudeParam.phoneNo1.value.equals(judgeItem)) {
							if(baseCust.getPhoneNo1()==null||"".equals(baseCust.getPhoneNo1())){
								cust = 1;
								continue;
							}
							mapForSimCust.put("phoneNo1", baseCust.getPhoneNo1());
						}
						// 地址
						if (EnumType.SimCustJudeParam.detAddr.value.equals(judgeItem)) {
							if(baseCust.getDetAddr()==null||"".equals(baseCust.getDetAddr())){
								cust = 1;
								continue;
							}
							mapForSimCust.put("detAddr", baseCust.getDetAddr());
						}
						// 家庭电话
						if (EnumType.SimCustJudeParam.homeTel.value.equals(judgeItem)) {
							mapForSimCust.put("telephone1", baseCust.getHomeTel());
                            if(baseCust.getHomeTel()==null||"".equals(baseCust.getHomeTel())){
								cust = 1;
                                continue;
                            }
						}
					}
				if(cust==1){
					continue;
					}
					// 获取符合规则的与该客户相似的所有客户信息
                    PageBean beanss = coreDaoImpl.selectPageByMapper(custPerMapper, "selectCustlist", mapForSimCust);
						if(beanss!=null){

							List<SimCustBaseInfo> simCustBaseInfoList = beanss.getList();

							if (simCustBaseInfoList != null && simCustBaseInfoList.size() > 1) {
									// 临时存储对应规则的相似用户信息
									SimCustTask simCustTask = new SimCustTask();
									simCustTask.setRuleId(item.getRuleId());
									simCustTask.setSimCustList(simCustBaseInfoList);
									simCustTaskList.add(simCustTask);
                                    break;
							}
                        }
				}

		// 所有相似客户信息列表去除重复记录
		List<SimCustTask> simCustTaskListNew = new ArrayList<SimCustTask>();
		for (SimCustTask item : simCustTaskList) {

			boolean flg = false;
			for (SimCustTask itemNew : simCustTaskListNew) {// 去重处理
				if (item.getRuleId().equals(itemNew.getRuleId())
						&& (item.getSimCustList().size() == itemNew.getSimCustList().size())
						&& item.getSimCustList().containsAll(itemNew.getSimCustList())) {
					flg = true;
					break;
				}
			}

			if (!flg) {
				simCustTaskListNew.add(item);
			}
		}
		
		// 生成相似客户任务
		for (SimCustTask obj : simCustTaskListNew) {
			// 相似任务表对象
			EcSimTask simTask = new EcSimTask();
            CoreContext cc = new CoreContext();
            Map map = new HashMap();
            map.put("userId", "admin");
            cc.putAll(map);
            ContextContainer.setContext(cc);
			EcSimCustRule simCustRule = simCustRuleMapper.selectByPrimaryKey(obj.getRuleId());// 获取相似规则相关信息
			simTask.setSimilarPercent(
					(simCustRule.getSimilarPercent() != null) ? simCustRule.getSimilarPercent().toString() : ""); // 相似度
			simTask.setTaskId("ST" + IdGenerator.getSeqID("ST"));// 任务ID
			simTask.setRuleId(obj.getRuleId());// 规则ID
			Date date = new Date();
			simTask.setTaskCreateTime(date);// 生成时间
			simTask.setTaskState(EnumType.SimTaskState.unDistribute.value);// 任务状态：待处理
			simTask.setDealUser(""); // 处理人员
			simTask.setApproveUser("");// 审批人员
			simTaskMapper.insert(simTask);

			for (SimCustBaseInfo simCustBaseInfo : obj.getSimCustList()) {
				// 相似客户表
				EcSimCust simCust = new EcSimCust();
				simCust.setTaskId(simTask.getTaskId());// 任务ID
				simCust.setSimilarCustNo(simCustBaseInfo.getCustNo());
				simCust.setSimCustState(EnumType.SimTaskState.unDistribute.value);// 待处理状态
                EcSimCust ec= new EcSimCust();
                ec.setSimilarCustNo(simCustBaseInfo.getCustNo());
				ec.setTaskId(simTask.getTaskId());
                List<EcSimCust> ecSimCust = simCustMapper.selectByEntity(ec);
                if(ecSimCust.size()<1) {
                    simCustMapper.insert(simCust);
                }else{
                    simCustMapper.deleteByPrimaryKey(simTask.getTaskId(),simCustBaseInfo.getCustNo());
                    simCustMapper.insert(simCust);
                }

			}
            EcSimCust ecs= new EcSimCust();
            ecs.setTaskId(simTask.getTaskId());
            List<EcSimCust> ecSim =  simCustMapper.selectByEntity(ecs);
            if(ecSim.size()<2){
                simTaskMapper.deleteByPrimaryKey(simTask.getTaskId());
				simCustMapper.deleteByPrimaryKey(simTask.getTaskId(),"");
                break;
            }
			/* 更新相似客户合并拆分轨迹表 */
			EcMergeSplitTrace mergeSplitTrace = new EcMergeSplitTrace();
			mergeSplitTrace.setTaskId(simTask.getTaskId());// 任务编号
			mergeSplitTrace.setTraceId(generateTraceId(simTask.getTaskId()));// 轨迹ID
			mergeSplitTrace.setMergeSplitAction(EnumType.MergeSplitAction.generate.value);// 相似客户任务生成
			mergeSplitTrace.setDealUser("System");// 处理人员 TODO 该赋何值？
			mergeSplitTrace.setDealTime(DateUtil.getDate(new Date()));// 处理时间
			mergeSplitTrace.setDealConclusion("");// 处理结论
			mergeSplitTrace.setDealOpinion("待分配");// 处理意见
			mergeSplitTraceMapper.insert(mergeSplitTrace);
			String percent = simCustRule.getSimilarPercent().toString();
			if(percent.equals("1")){
				try {
					SimCustTask applyInfo =obj;
					applyInfo.setTaskId(simTask.getTaskId());
					custSimTaskMngService.applyMergePasss(applyInfo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * Desc: 根据任务编号生成轨迹ID
	 * 
	 * @return
	 * @author
	 */
	private int generateTraceId(String taskId) {
		EcMergeSplitTrace record = new EcMergeSplitTrace();
		record.setTaskId(taskId);
		List<EcMergeSplitTrace> recordList = mergeSplitTraceMapper.selectByEntity(record);
		if (recordList != null && recordList.size() > 0) {
			Integer max = 0;
			for (EcMergeSplitTrace item : recordList) {
				if (item.getTraceId() > max) {
					max = item.getTraceId();
				}
			}
			return max + 1;
		}
		return 1;
	}
}
