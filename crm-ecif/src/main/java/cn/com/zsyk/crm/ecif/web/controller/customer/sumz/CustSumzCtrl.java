package cn.com.zsyk.crm.ecif.web.controller.customer.sumz;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thoughtworks.xstream.converters.basic.BigDecimalConverter;

import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.dto.Result.StatusEnum;
import cn.com.zsyk.crm.common.util.JsonUtil;
import cn.com.zsyk.crm.ecif.bo.cust.CustConsumInfo;
import cn.com.zsyk.crm.ecif.bo.cust.CustSumzInfo;
import cn.com.zsyk.crm.ecif.service.customer.sumz.CustSumzService;

@RestController
public class CustSumzCtrl {

	@Autowired
	private CustSumzService custSumzService;

	/**
	 * @api {GET} /crm/ecif/cust/sumz/totalPremiumForPastYear 过去一年的总保费/消费次数
	 * @apiDescription 
	 * 
	 * @apiName getTotalPremiumForPastYear
	 * @apiGroup Customer
	 *
	 * @apiParam {String} custNo 客户号
	 * 
	 * @apiSuccess {Object} Response 返回值对象
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ecif/cust/sumz/totalPremiumForPastYear", method = RequestMethod.GET)
	public Result getTotalPremiumForPastYear(String custNo) {
		Result res = new Result();
		res.setData(custSumzService.getTotalPremiumForPastYear(custNo));
		return res;
	}

	/**
	 * @api {GET} /crm/ecif/cust/sumz/totalPremium 总保费/消费次数
	 * @apiDescription 
	 * 
	 * @apiName getTotalPremium
	 * @apiGroup Customer
	 *
	 * @apiParam {String} custNo 客户号
	 * 
	 * @apiSuccess {Object} Response 返回值对象
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ecif/cust/sumz/totalPremium", method = RequestMethod.GET)
	public Result getTotalPremium(String custNo) {
		Result res = new Result();
		res.setData(custSumzService.getTotalPremium(custNo));
		return res;
	}

	/**
	 * @api {GET} /crm/ecif/cust/sumz/firtPremium 首保保费/日期
	 * @apiDescription 
	 * 
	 * @apiName getFirtPremium
	 * @apiGroup Customer
	 *
	 * @apiParam {String} custNo 客户号
	 * 
	 * @apiSuccess {Object} Response 返回值对象
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ecif/cust/sumz/firtPremium", method = RequestMethod.GET)
	public Result getFirtPremium(String custNo) {
		Result res = new Result();
		res.setData(custSumzService.getFirtPremium(custNo));
		return res;
	}

	/**
	 * @api {GET} /crm/ecif/cust/sumz/lastFollowUp 首保保费/日期
	 * @apiDescription 
	 * 
	 * @apiName getLastFollowUp
	 * @apiGroup Customer
	 *
	 * @apiParam {String} custNo 客户号
	 * 
	 * @apiSuccess {Object} Response 返回值对象
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ecif/cust/sumz/lastFollowUp", method = RequestMethod.GET)
	public Result getLastFollowUp(String custNo) {
		Result res = new Result();
		res.setData(custSumzService.getLastFollowUp(custNo));
		return res;
	}

	/**
	 * @api {POST} /crm/ecif/cust/getConsumInfoByCustNo 根据客户号获取消费信息
	 * @apiName getConsumInfoByCustNo
	 * @apiGroup Customer
	 * 
	 * @apiSuccess {String} CustNo 多个客户号字符串，逗号隔开
	 */
	@RequestMapping(path = "/crm/ecif/cust/getConsumInfoByCustNo", method = RequestMethod.POST)
	public Result getConsumInfoByCustNo(@RequestBody String custNoStr) {
		Result res = new Result();

		Map map = JsonUtil.parseObject(custNoStr, Map.class);
		custNoStr = map.get("custNoStr").toString(); 
		
		if (custNoStr == null || "".equals(custNoStr)) {
			res.setStatus(StatusEnum.FAIL.getValue());
			res.setMessage("客户号不能为空！");
			return res;
		}

		String[] custNoArr = custNoStr.split(",");

		// 客户消费信息对象列表
		List<CustConsumInfo> custConsumInfoList = new ArrayList<CustConsumInfo>();
		for (String custNo : custNoArr) {

			// 获取客户消费信息
			List<CustSumzInfo> custSumzInfoList = custSumzService.getLastPremium(custNo);

			// 客户消费信息对象
			CustConsumInfo custConsumInfo = new CustConsumInfo();
			if(custSumzInfoList!=null && custSumzInfoList.size()>0) {
				// 客户消费次数
				custConsumInfo.setConsumTimes(custSumzInfoList.size() + "");
				custConsumInfo.setLastSumTime(custSumzInfoList.get(0).getFirstPreDate());//获取最近消费日期
			}else {
				// 客户消费次数
				custConsumInfo.setConsumTimes("0");
			}
		
			BigDecimal sum = new BigDecimal(0);
			// 计算客户总消费额
			if (custSumzInfoList != null && custSumzInfoList.size() > 0) {
				for (CustSumzInfo item : custSumzInfoList) {
					BigDecimal amt = new BigDecimal(item.getPremium());
					sum = sum.add(amt);
				}
			}
			// 客户总消费额
			custConsumInfo.setConsumSum(sum.toString());
			// 客户号
			custConsumInfo.setCustNo(custNo);
			// 客户消费详情
			custConsumInfo.setCustSumzInfoList(custSumzInfoList);

			custConsumInfoList.add(custConsumInfo);
		}

		res.setData(custConsumInfoList);
		return res;
	}
}
