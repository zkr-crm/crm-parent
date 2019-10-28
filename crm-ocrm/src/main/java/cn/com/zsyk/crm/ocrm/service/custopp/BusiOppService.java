package cn.com.zsyk.crm.ocrm.service.custopp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.com.zsyk.crm.common.constant.ServiceType;
import cn.com.zsyk.crm.common.dto.ContextContainer;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.DateUtil;
import cn.com.zsyk.crm.common.util.IdGenerator;
import cn.com.zsyk.crm.common.util.RestUtil;
import cn.com.zsyk.crm.generator.EnumType;
import cn.com.zsyk.crm.ocrm.bo.custopp.StageInfo;
import cn.com.zsyk.crm.ocrm.entity.OcBusiOpp;
import cn.com.zsyk.crm.ocrm.mapper.OcBusiOppMapper;
import cn.com.zsyk.crm.ocrm.service.user.UserService;

@Service
@Transactional
public class BusiOppService {

	@Autowired
	private OcBusiOppMapper ocBusiOppMapper;
	@Autowired
	RestUtil restUtil;
	@Autowired
	UserService userService;
	/**
	 * Desc: 查询商机
	 * @param ocBusiOpp
	 * @return
	 * @author
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<OcBusiOpp> getBusiOppList(OcBusiOpp ocBusiOpp) {
		List<OcBusiOpp> busiOppList = ocBusiOppMapper.selectBusiOppList(ocBusiOpp);
		Result result = restUtil.getForObject(ServiceType.ECIF, "/crm/ecif/cust/perCustList",Result.class);
		
		if (busiOppList != null) {
			Map<String, String> userIdMap = userService.getAgentListMapByUserId();
			for (OcBusiOpp obj : busiOppList) {
				obj.setCustAgentNam(userIdMap.get(obj.getCustAgent()));
			}
			if (result != null) {
				List<LinkedHashMap> list = (List<LinkedHashMap>)result.getData();
				if (list != null) {
					for (OcBusiOpp busiOpp : busiOppList) {
						for(LinkedHashMap o : list) {
							if (!busiOpp.getCustNo().equals(o.get("custNo"))) {
								continue;
							}
							busiOpp.setCustNam(String.valueOf(o.get("custName")));
							busiOpp.setCustType(String.valueOf(o.get("custTyp")));
							busiOpp.setCustTel(String.valueOf(o.get("phoneNumber")));
						}
					}
				}
			}
		}
		return busiOppList;
	}

	/**
	 * Desc: 根据商机编号获取商机
	 * @param busiOppNo
	 * @return
	 * @author
	 */
	public OcBusiOpp getBusiOppOne(String busiOppNo) {
		OcBusiOpp ocBusiOpp = ocBusiOppMapper.selectByPrimaryKey(busiOppNo);
		return ocBusiOpp;
	}

	/**
	 * Desc: 新增商机
	 * @param ocBusiOpp
	 * @author
	 */
	public void addBusiOpp(OcBusiOpp ocBusiOpp) {
		if (ocBusiOpp == null) {
			throw new ServiceException("商机信息为空");
		}

		ocBusiOpp.setBusiOppNo(String.valueOf(IdGenerator.getSeqID("BusiOppNo")));
		if (StringUtils.isEmpty(ocBusiOpp.getBusiOppNo())) {
			throw new ServiceException("商机编号不能为空");
		}

		// 客户号
		if (StringUtils.isEmpty(ocBusiOpp.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}

		if (StringUtils.isEmpty(ocBusiOpp.getBusiOppType())) {
			throw new ServiceException("商机类别不能为空");
		}

		OcBusiOpp obj = ocBusiOppMapper.selectByPrimaryKey(ocBusiOpp.getBusiOppNo());
		if (obj != null) {
			throw new ServiceException("商机信息已经存在");
		}
		if (StringUtils.isEmpty(ocBusiOpp.getBusiOppStage())) {
			ocBusiOpp.setBusiOppStage(EnumType.BusiOppStage.intention.getValue());
		} else {
			if (!ocBusiOpp.getBusiOppStage().equals(EnumType.BusiOppStage.intention.getValue())) {
				throw new ServiceException("新建商机时，商机阶段状态必须为[意向]");
			}
		}
		ocBusiOpp.setBusiOppCreateDate(DateUtil.getCurTime());
		ocBusiOpp.setBusiOppCreateUser(ContextContainer.getContext().getUserId());
		ocBusiOppMapper.insert(ocBusiOpp);
	}

	/**
	 * Desc: 修改商机
	 * @param ocBusiOpp
	 * @author
	 */
	public void uptBusiOpp(OcBusiOpp ocBusiOpp) {
		if (ocBusiOpp == null) {
			throw new ServiceException("商机信息为空");
		}

		if (StringUtils.isEmpty(ocBusiOpp.getBusiOppNo())) {
			throw new ServiceException("商机编号不能为空");
		}

		// 客户号
		if (StringUtils.isEmpty(ocBusiOpp.getCustNo())) {
			throw new ServiceException("客户号不能为空");
		}

		if (StringUtils.isEmpty(ocBusiOpp.getBusiOppType())) {
			throw new ServiceException("商机类别不能为空");
		}

		OcBusiOpp obj = ocBusiOppMapper.selectByPrimaryKey(ocBusiOpp.getBusiOppNo());
		if (obj == null) {
			throw new ServiceException("商机信息不存在");
		}
		if (!obj.getBusiOppStage().equals(ocBusiOpp.getBusiOppStage())) {
			throw new ServiceException("更新商机信息时，商机阶段状态不能修改");
		}
		ocBusiOppMapper.updateByPrimaryKey(ocBusiOpp);
	}

	/**
	 * Desc: 删除商机
	 * @param ocBusiOpp
	 * @author
	 */
	public void delBusiOpp(OcBusiOpp ocBusiOpp) {
		String userId = ContextContainer.getContext().getUserId();
		if (ocBusiOpp == null) {
			throw new ServiceException("商机信息为空");
		}

		if (StringUtils.isEmpty(ocBusiOpp.getBusiOppNo())) {
			throw new ServiceException("商机编号不能为空");
		}

		OcBusiOpp obj = ocBusiOppMapper.selectByPrimaryKey(ocBusiOpp.getBusiOppNo());
		if (obj == null) {
			throw new ServiceException("商机信息不存在");
		}
		if (!obj.getBusiOppStage().equals(EnumType.BusiOppStage.intention.getValue())) {
			throw new ServiceException("删除商机时，商机阶段状态必须为[意向]。商机编号：" + ocBusiOpp.getBusiOppNo());
		}
		if (!userId.equals(obj.getCreateUser())) {
			throw new ServiceException("只能删除本人创建的商机，并且商机阶段状态为[意向]");
		}
		ocBusiOppMapper.deleteByPrimaryKey(ocBusiOpp.getBusiOppNo());
	}

	/**
	 * Desc: 统计员工跟进商机数量
	 * @return
	 * @author
	 */
	public List<OcBusiOpp> getFollowUpBusiOppQtyList() {
		return ocBusiOppMapper.selectGrpFollowUpByCustAgen();
	}

	/**
	 * Desc: 分配商机
	 * @param custAgent
	 * @param busiOppNoList
	 * @author
	 */
	public void allotBusiOpp(String custAgent, List<String> busiOppNoList) {
		if(StringUtils.isEmpty(custAgent)) {
			throw new ServiceException("分配商机负责人为空");
		}
		if(busiOppNoList == null || busiOppNoList.size() ==0) {
			throw new ServiceException("分配商机信息为空");
		}
		for (String busiOppNo : busiOppNoList) {
			OcBusiOpp ocBusiOpp = ocBusiOppMapper.selectByPrimaryKey(busiOppNo);
			if (ocBusiOpp == null) {
				throw new ServiceException("分配商机信息不存在。商机编号：" + busiOppNo);
			}
			if (!ocBusiOpp.getBusiOppStage().equals(EnumType.BusiOppStage.intention.getValue())) {
				throw new ServiceException("分配商机时，商机阶段状态必须为[意向]。商机编号：" + busiOppNo);
			}
			ocBusiOpp.setBusiOppStage(EnumType.BusiOppStage.allot.getValue());
			ocBusiOpp.setCustAgent(custAgent);
			ocBusiOpp.setAssignDate(DateUtil.getCurTime());
			ocBusiOppMapper.updateByPrimaryKey(ocBusiOpp);
		}
	}

	/**
	 * Desc: 分配商机
	 * @param custAgent
	 * @param busiOppNoList
	 * @author
	 */
	public void cancelBusiOpp(List<String> busiOppNoList, String cancelReason) {
		String userId = ContextContainer.getContext().getUserId();

		if(busiOppNoList == null || busiOppNoList.size() ==0) {
			throw new ServiceException("取消商机信息为空");
		}
		for (String busiOppNo : busiOppNoList) {
			// 校验商机信息是否存在
			OcBusiOpp ocBusiOpp = ocBusiOppMapper.selectByPrimaryKey(busiOppNo);
			if (ocBusiOpp == null) {
				throw new ServiceException("取消商机信息不存在，商机编号：" + busiOppNo);
			}

			// 取消商机时，商机阶段状态必须为[意向、分配、跟进中]
			if (ocBusiOpp.getBusiOppStage().equals(EnumType.BusiOppStage.intention.getValue()) 
					|| ocBusiOpp.getBusiOppStage().equals(EnumType.BusiOppStage.allot.getValue())
					|| ocBusiOpp.getBusiOppStage().equals(EnumType.BusiOppStage.follow_up.getValue())) {
				// 01->06/02->06/03->06
				ocBusiOpp.setBusiOppStage(EnumType.BusiOppStage.cancel.getValue());
				ocBusiOpp.setCancelDate(DateUtil.getCurTime());
				ocBusiOpp.setCancelReason(cancelReason);
				ocBusiOpp.setCancelUser(userId);
				ocBusiOppMapper.updateByPrimaryKey(ocBusiOpp);
			} else {
				throw new ServiceException("取消商机时，商机阶段状态必须为[意向、分配、跟进中]。商机编号：" + busiOppNo);
			}
		}
	}

	/**
	 * Desc: 设置商机阶段状态
	 * @param busiOppNo
	 * @param newStage
	 * @param oldStage
	 * @author
	 */
	public void setBusiOppStage(StageInfo stageInfo) {
		if (stageInfo == null) {
			throw new ServiceException("商机阶段信息不能为空");
		}

		// 商机编码
		String busiOppNo = stageInfo.getBusiOppNo();
		// 新商机阶段
		String newStage = stageInfo.getNewStage();
		// 原商机阶段
		String oldStage = stageInfo.getOldStage();
		// 原因
		String reason = stageInfo.getReason();
		// 参数
		List<String> busiOppNoList = new ArrayList<String>();
		busiOppNoList.add(busiOppNo);

		// 商机编号不能为空
		if (StringUtils.isEmpty(busiOppNo)) {
			throw new ServiceException("商机编号不能为空");
		}
		// 新商机阶段状态不能为空
		if (StringUtils.isEmpty(newStage)) {
			throw new ServiceException("新商机阶段状态不能为空");
		}
		// 原商机阶段状态不能为空
		if (StringUtils.isEmpty(oldStage)) {
			throw new ServiceException("原商机阶段状态不能为空");
		}

		// 校验商机信息是否存在
		OcBusiOpp ocBusiOpp = ocBusiOppMapper.selectByPrimaryKey(busiOppNo);
		if (ocBusiOpp == null) {
			throw new ServiceException("商机信息不存在，商机编号：" + busiOppNo);
		}

		// 设置商机阶段状态不一致
		if (!ocBusiOpp.getBusiOppStage().equals(oldStage)) {
			throw new ServiceException("设置商机阶段状态不一致");
		}

		// 新原状态相同，不处理
		if(newStage.equals(oldStage) || ocBusiOpp.getBusiOppStage().equals(newStage)) {
			throw new ServiceException("商机阶段原状态与新段状态不能相同");
		}

		// intention
		if (oldStage.equals(EnumType.BusiOppStage.intention.getValue())) {

			if(newStage.equals(EnumType.BusiOppStage.allot.getValue())) { 
				// allot
				this.allotBusiOpp(stageInfo.getCustAgent(), busiOppNoList);

			} else if(newStage.equals(EnumType.BusiOppStage.cancel.getValue())) { 
				// cancel
				this.cancelBusiOpp(busiOppNoList, reason);

			} else { // other
				throw new ServiceException("意向商机，只能变更分配或取消");
			}
		}

		// allot
		if (oldStage.equals(EnumType.BusiOppStage.allot.getValue())) {

			if(newStage.equals(EnumType.BusiOppStage.follow_up.getValue())) {
				ocBusiOpp.setBusiOppStage(EnumType.BusiOppStage.follow_up.getValue());
				ocBusiOppMapper.updateByPrimaryKey(ocBusiOpp);

			} else if(newStage.equals(EnumType.BusiOppStage.cancel.getValue())) {
				this.cancelBusiOpp(busiOppNoList, reason);

			} else {
				// other
				throw new ServiceException("分配商机，只能变更跟进中或取消");
			}
		}

		// follow_up
		if (oldStage.equals(EnumType.BusiOppStage.follow_up.getValue())) {
			if(newStage.equals(EnumType.BusiOppStage.succeed.getValue())) {
				//succeed
				this.succeedBusiOpp(stageInfo);

			} else if(newStage.equals(EnumType.BusiOppStage.failure.getValue())) {
				//failure
				ocBusiOpp.setBusiOppStage(EnumType.BusiOppStage.failure.getValue());
				ocBusiOpp.setFailReason(reason);
				ocBusiOppMapper.updateByPrimaryKey(ocBusiOpp);

			} else if(newStage.equals(EnumType.BusiOppStage.cancel.getValue())) {
				//cancel
				this.cancelBusiOpp(busiOppNoList, reason);
			} else {
				// other
				throw new ServiceException("跟进中商机，只能变更为成功、失败、取消");
			}
		}

		// succeed
		if (oldStage.equals(EnumType.BusiOppStage.succeed.getValue())) {
			throw new ServiceException("成功商机，不能变更状态");
		}

		// failure
		if (oldStage.equals(EnumType.BusiOppStage.failure.getValue())) {
			//allot
			if(newStage.equals(EnumType.BusiOppStage.allot.getValue())) { 
				this.allotBusiOpp(stageInfo.getCustAgent(), busiOppNoList);

			} else {
				// other
				throw new ServiceException("失败商机，只能重新分配");
			}
		}

		// cancel
		if (oldStage.equals(EnumType.BusiOppStage.cancel.getValue())) {

			//allot
			if(newStage.equals(EnumType.BusiOppStage.allot.getValue())) {
				this.allotBusiOpp(stageInfo.getCustAgent(), busiOppNoList);

			} else {
				// other
				throw new ServiceException("取消商机，只能重新分配");
			}
		}
	}

	/**
	 * Desc: 商机成功
	 * @param stageInfo
	 * @author
	 */
	public void succeedBusiOpp(StageInfo stageInfo) {
		// 商机编号不能为空
		if (StringUtils.isEmpty(stageInfo.getBusiOppNo())) {
			throw new ServiceException("商机编号不能为空");
		}

		// 保单号不能为空
		if (StringUtils.isEmpty(stageInfo.getPolicyNo())) {
			throw new ServiceException("保单号不能为空");
		}

		// 保费不能为空
		if (StringUtils.isEmpty(stageInfo.getPolicyPrem())) {
			throw new ServiceException("保费不能为空");
		}

		// 产品编号不能为空
		if (StringUtils.isEmpty(stageInfo.getProductCode())) {
			throw new ServiceException("产品编号不能为空");
		}

		// 签单日期不能为空
		if (StringUtils.isEmpty(stageInfo.getSignDate())) {
			throw new ServiceException("签单日期不能为空");
		}

		// 校验商机
		OcBusiOpp ocBusiOpp = ocBusiOppMapper.selectByPrimaryKey(stageInfo.getBusiOppNo());
		if (ocBusiOpp == null) {
			throw new ServiceException("商机信息不存在，商机编号：" + stageInfo.getBusiOppNo());
		}

		// 保单号/产品编号
		ocBusiOpp.setPolicyNo(stageInfo.getPolicyNo());
		ocBusiOpp.setProductCode(stageInfo.getProductCode());

		// 保费
		if(StringUtils.isEmpty(stageInfo.getPolicyPrem())) {
			ocBusiOpp.setPredPrem(BigDecimal.ZERO);
		} else {
			ocBusiOpp.setPredPrem(new BigDecimal(stageInfo.getPolicyPrem()));
		}

		// stage
		ocBusiOpp.setBusiOppStage(EnumType.BusiOppStage.succeed.getValue());
		ocBusiOppMapper.updateByPrimaryKey(ocBusiOpp);
	}
}
