package cn.com.zsyk.crm.ecif.web.controller.customer.baseinfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.zsyk.crm.ecif.entity.EcContactAddress;
import cn.com.zsyk.crm.ecif.entity.EcCustPhone;
import cn.com.zsyk.crm.ecif.entity.QueryCustPerParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import cn.com.zsyk.crm.common.annotation.SysOprtLog;
import cn.com.zsyk.crm.common.annotation.SysOprtLog.Module;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.common.util.JsonUtil;
import cn.com.zsyk.crm.ecif.bo.cust.PerCustBaseInfo;
import cn.com.zsyk.crm.ecif.entity.EcCustPer;
import cn.com.zsyk.crm.ecif.service.customer.baseinfo.CustService;
import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;


@RestController
public class CustMngCtrl {

	@Autowired
	private CustService custService;
	

	/**
	 * @api {GET} /crm/ecif/cust/perCustList 查询个人客户列表
	 * @apiName getPerCustList
	 * @apiGroup Customer
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			客户号 custNo<br/>
	 * 			客户类型 custTyp->CustType<br/>
	 * 			客户名称 custName<br/>
	 * 			客户号码 phoneNumber<br/>
	 * 			标签 List<EcCustTag> custTag<br/>
	 * 			客户来源 custSource->DataSource<br/>
	 * 			客户阶段 custPhase-(待确认)<br/>
	 * 			客户经理 custAgent<br/>
	 * 			下次跟进时间 nextFollowUpTime<br/>
	 * 			备注 remark<br/>
	 * 			创建人 createUser<br/>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ecif/cust/perCustList", method = RequestMethod.GET)
	public Result getPerCustList(EcCustPer custper) {
		Result res = new Result();
		res.setData(custService.selectPerCustList(custper));
		return res;
	}

	/**
	 * @api {GET} /crm/ecif/cust/entCustList 查询企业客户列表(N)
	 * @apiName getEntCustList
	 * @apiGroup Customer
	 *
	 * @apiSuccess {Object} Result 返回值对象
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ecif/cust/entCustList", method = RequestMethod.GET)
	public Result getEntCustList() {
		Result res = new Result();
		res.setData(custService.selectEntCustList());
		return res;
	}

	/**
	 * @api {GET} /crm/ecif/cust/mng/perCustInfo 维护-查询个人客户信息
	 * @apiName getPerCustInfo
	 * @apiGroup Customer
	 * 
	 * @apiParam {String} custNo 客户号
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 		客户号 custNo<br/>
	 * 		客户姓名 custName<br/>
	 * 		性别 sex<br/>
	 * 		出生日期 birthDate<br/>
	 * 		证件类型 certTyp->IdType<br/>
	 * 		证件号码 certNo<br/>
	 * 		电话号码 phoneNumber<br/>
	 * 		<br/>
	 * 		客户经理 custAgent<br/>
	 * 		客户类型 custTyp->CustType<br/>
	 * 		客户来源 custSource->DataSource<br/>
	 * 		是否为重点 keyCustFlg->YesNoFlg<br/>
	 * 		下次跟进时间 nextFollowUpTm<br/>
	 * 		<br/>
	 * 		国籍 nationality->Nationality<br/>
	 * 		婚姻状况 marrigeSts->YesNoFlg<br/>
	 * 		文化程度 eduDegree->Degree<br/>
	 * 		职业 position<br/>
	 * 		所属行业 trade->BusinessType<br/>
	 * 		工作单位 unitNam<br/>
	 * 		居住地址类型 liveAddrTyp->AddrTyp<br/>
	 * 		居住省 liveProvince<br/>
	 * 		居住市 liveCity<br/>
	 * 		居住区县 liveCounty<br/>
	 * 		居住街道 liveStreet<br/>
	 * 		居住邮编 livePostcode<br/>
	 * 		<br/>
	 * 		单位地址类型 unitAddrTyp->AddrTyp<br/>
	 * 		单位省 unitProvince<br/>
	 * 		单位市  unitCity<br/>
	 * 		单位区县  unitCounty<br/>
	 * 		单位街道  unitStreet<br/>
	 * 		单位邮编  unitPostcode<br/>
	 * 		<br/>
	 * 		其他手机 otherTel<br/>
	 * 		住宅电话 homeTel<br/>
	 * 		公司电话 officeTel<br/>
	 * 		传真 fax<br/>
	 * 		<br/>
	 * 		微信 wechatNo<br/>
	 * 		QQ qq<br/>
	 * 		微博 microblog<br/>
	 * 		支付宝 alipay<br/>
	 * 		电子邮箱 emailAddr<br/>
	 * 		<br/>
	 * 		头像URL perIconUrl<br/>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/mng/perCustInfo", method = RequestMethod.GET)
	public Result getPerCustInfo(String custNo) throws Exception {
		Result res = new Result();
		res.setData(custService.getPerCustInfo(custNo));
		return res;
	}
	
	/**
	 * @api {GET} /crm/ecif/cust/mng/getPerCustInfoByCustNoStr 维护-查询个人客户信息列表
	 * @apiName getPerCustInfoByCustNoStr
	 * @apiGroup Customer
	 * 
	 * @apiParam {String} custNoStr 客户号字符串，客户号之间逗号隔开
	 *
	 * @apiSuccess {List} Result 返回值对象列表<br/>
	 * 		客户号 custNo<br/>
	 * 		客户姓名 custName<br/>
	 * 		性别 sex<br/>
	 * 		出生日期 birthDate<br/>
	 * 		证件类型 certTyp->IdType<br/>
	 * 		证件号码 certNo<br/>
	 * 		电话号码 phoneNumber<br/>
	 * 		<br/>
	 * 		客户经理 custAgent<br/>
	 * 		客户类型 custTyp->CustType<br/>
	 * 		客户来源 custSource->DataSource<br/>
	 * 		是否为重点 keyCustFlg->YesNoFlg<br/>
	 * 		下次跟进时间 nextFollowUpTm<br/>
	 * 		<br/>
	 * 		国籍 nationality->Nationality<br/>
	 * 		婚姻状况 marrigeSts->YesNoFlg<br/>
	 * 		文化程度 eduDegree->Degree<br/>
	 * 		职业 position<br/>
	 * 		所属行业 trade->BusinessType<br/>
	 * 		工作单位 unitNam<br/>
	 * 		居住地址类型 liveAddrTyp->AddrTyp<br/>
	 * 		居住省 liveProvince<br/>
	 * 		居住市 liveCity<br/>
	 * 		居住区县 liveCounty<br/>
	 * 		居住街道 liveStreet<br/>
	 * 		居住邮编 livePostcode<br/>
	 * 		<br/>
	 * 		单位地址类型 unitAddrTyp->AddrTyp<br/>
	 * 		单位省 unitProvince<br/>
	 * 		单位市  unitCity<br/>
	 * 		单位区县  unitCounty<br/>
	 * 		单位街道  unitStreet<br/>
	 * 		单位邮编  unitPostcode<br/>
	 * 		<br/>
	 * 		其他手机 otherTel<br/>
	 * 		住宅电话 homeTel<br/>
	 * 		公司电话 officeTel<br/>
	 * 		传真 fax<br/>
	 * 		<br/>
	 * 		微信 wechatNo<br/>
	 * 		QQ qq<br/>
	 * 		微博 microblog<br/>
	 * 		支付宝 alipay<br/>
	 * 		电子邮箱 emailAddr<br/>
	 * 		<br/>
	 * 		头像URL perIconUrl<br/>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/mng/getPerCustInfoByCustNoStr", method = RequestMethod.GET)
	public Result getPerCustInfoByCustNoStr(String custNoStr) throws Exception {
		Result res = new Result();
		
		List<PerCustBaseInfo> custInfoList = new ArrayList<PerCustBaseInfo>();
		
		String[] custNoArr = custNoStr.split(",");
		for (String custNo : custNoArr) {
			PerCustBaseInfo inof = custService.getPerCustInfo(custNo);
			custInfoList.add(inof);
		}
		res.setData(custInfoList);
		return res;
	}

	/**
	 * @api {POST} /crm/ecif/cust/mng/uptPerCustInfo 维护-更新个人客户信息
	 * @apiName uptPerCustInfo
	 * @apiGroup Customer
	 *
	 * @apiParam {MultipartFile} file 客户头像
	 * @apiParam {String} custBaseInfoStr 客户基本信息json
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/false
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/mng/uptPerCustInfo", method = RequestMethod.POST)
	@SysOprtLog(model = Module.CUSTOMER, bizDesc="更新个人客户信息")
	public Result uptPerCustInfo(@RequestParam(name = "file",required = false) MultipartFile file, @RequestParam(name = "custBaseInfoStr",required = false) String custBaseInfoStr) throws Exception {
		Result res = new Result();
		
		if(custBaseInfoStr==null||custBaseInfoStr.isEmpty()) {
			res.setData(false);
		}

		try {
			String[] dateFormats = new String[] {"yyyy-MM-dd"};
			JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(dateFormats));
            //add 增加addrList phoneList lijiangcheng 2019-08-05
            Map<String, Object> maps =  new HashMap<String, Object>();
            maps.put("addrList", EcContactAddress.class);
            maps.put("phoneList", EcCustPhone.class);
			PerCustBaseInfo custBaseInfo = (PerCustBaseInfo) JSONObject.toBean(JSONObject.fromObject(custBaseInfoStr), PerCustBaseInfo.class,maps);

			if(custBaseInfo==null) {
				res.setData(false);
			} else {
				custBaseInfo.setPerIconUrl(file);
				custService.uptPerCustInfo(custBaseInfo);
				res.setData(true);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("修改个人客户信息失败");
		}
		return res;
	}

	/**
	 * @api {POST} /crm/ecif/cust/mng/addPerCustInfo 维护-新增个人客户信息
	 * @apiName addPerCustInfo
	 * @apiGroup Customer
	 *
	 * @apiParam {MultipartFile} file 客户头像
	 * @apiParam {String} custBaseInfoStr 客户基本信息json
	 * 
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/false
	 */
	@SuppressWarnings({ "unchecked", "rawtypes"})
	@RequestMapping(path = "/crm/ecif/cust/mng/addPerCustInfo", method = RequestMethod.POST)
	@SysOprtLog(model = Module.CUSTOMER, bizDesc="新增个人客户信息")
	public Result addPerCustInfo(@RequestParam(name = "file",required = false) MultipartFile file, @RequestParam(name = "custBaseInfoStr",required = false) String custBaseInfoStr) throws Exception {
		Result res = new Result();
		if(custBaseInfoStr==null||custBaseInfoStr.isEmpty()) {
			res.setData(false);
		}

		try {
			String[] dateFormats = new String[] {"yyyy-MM-dd"};
			JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(dateFormats));
			//add 增加addrList phoneList lijiangcheng 2019-08-05
			Map<String, Object> maps =  new HashMap<String, Object>();
			maps.put("addrList", EcContactAddress.class);
			maps.put("phoneList", EcCustPhone.class);
			PerCustBaseInfo custBaseInfo = (PerCustBaseInfo) JSONObject.toBean(JSONObject.fromObject(custBaseInfoStr), PerCustBaseInfo.class,maps);

			if(custBaseInfo==null) {
				res.setData(false);
			} else {
				custBaseInfo.setPerIconUrl(file);
				custService.addPerCustInfo(custBaseInfo);
				res.setData(true);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("新增个人客户信息失败");
		}
		return res;
	}

	/**
	 * @api {PUT} /crm/ecif/cust/mng/delPerCustInfo 维护-删除个人客户信息
	 * @apiName delPerCustInfo
	 * @apiGroup Customer
	 *
	 * @apiParam {String} custNo 客户号
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			true/false
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/crm/ecif/cust/mng/delPerCustInfo", method = RequestMethod.PUT)
	@SysOprtLog(model = Module.CUSTOMER, bizDesc="删除个人客户信息")
	public Result delPerCustInfo(String custNo) throws Exception {
		Result res = new Result();
		try {
			custService.delPerCustInfo(custNo);
			res.setData(true);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("删除个人客户信息失败");
		}
		return res;
	}
	
	
	/**
	 * @api {POST} /crm/ecif/cust/perCustList 查询个人客户列表
	 * @apiName getPerCustList
	 * @apiGroup Customer
	 *
	 *
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			客户号 custNo<br/>
	 * 			客户类型 custTyp->CustType<br/>
	 * 			客户名称 custName<br/>
	 * 			客户号码 phoneNumber<br/>
	 * 			标签 List<EcCustTag> custTag<br/>
	 * 			客户来源 custSource->DataSource<br/>
	 * 			客户阶段 custPhase-(待确认)<br/>
	 * 			客户经理 custAgent<br/>
	 * 			下次跟进时间 nextFollowUpTime<br/>
	 * 			备注 remark<br/>
	 * 			创建人 createUser<br/>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ecif/cust/getPerCustListByRole", method = RequestMethod.POST)
	public Result getPerCustListByRole(QueryCustPerParam custper , @RequestBody String custAgentJson) {
		Result res = new Result();

		//字符串转换
		List<String> custAgentList = null;
		
		// 字符串转换
		if ("{}".equals(custAgentJson) || "".equals(custAgentJson)) {
			custAgentList = new ArrayList<String>();
		} else {
			custAgentList = JsonUtil.parseArray(custAgentJson, String.class);
		}

		res.setData(custService.getPerCustListByRole(custper,custAgentList));
		return res;
	}
	
	/**
	 * @api {POST} /crm/ecif/cust/getPerCustListByName 查询个人客户列表
	 * @apiName getPerCustList
	 * @apiGroup Customer
	 *
	 *
	 *
	 * @apiSuccess {Object} Result 返回值对象<br/>
	 * 			客户号 custNo<br/>
	 * 			客户类型 custTyp->CustType<br/>
	 * 			客户名称 custName<br/>
	 * 			客户号码 phoneNumber<br/>
	 * 			标签 List<EcCustTag> custTag<br/>
	 * 			客户来源 custSource->DataSource<br/>
	 * 			客户阶段 custPhase-(待确认)<br/>
	 * 			客户经理 custAgent<br/>
	 * 			下次跟进时间 nextFollowUpTime<br/>
	 * 			备注 remark<br/>
	 * 			创建人 createUser<br/>
	 */
	@RequestMapping(path = "/crm/ecif/cust/getPerCustListByName", method = RequestMethod.GET)
	public Result getPerCustListByName(EcCustPer custper) {
		Result res = new Result();
		res.setData(custService.getPerCustListByName(custper,null));
		return res;
	}
	/**
	 * @api {POST} /crm/ecif/cust/updateCustAgent 客户分配
	 * @apiName updateCustAgent
	 * @apiGroup Customer
	 */
	@RequestMapping(path = "/crm/ecif/cust/updateCustAgent", method = RequestMethod.PUT)
	public Result updateCustAgent(EcCustPer custper) {
		Result res = new Result();
		String custNo = custService.updateCustAgent(custper);
		res.setData(custNo);
		return res;
	}
	
	/**
	 * @api {GET} /crm/ecif/cust/getCustNameByCustNo 根据客户号获取客户名
	 * @apiName getCustNameByCustNo
	 * @apiGroup Customer
	 * 
	 * @apiParam {String} custNo 客户号
	 */
	@RequestMapping(path = "/crm/ecif/cust/getCustNameByCustNo", method = RequestMethod.GET)
	public Result getCustNameByCustNo(String custNo) {
		Result res = new Result();

		String custName = custService.getPerCustName(custNo);

		res.setData(custName);
		return res;
	}
}
