package cn.com.zsyk.crm.manage.web.controller.mngcenter.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.manage.entity.SysJobMsgRela;
import cn.com.zsyk.crm.manage.service.mngcenter.job.JobMsgRelaService;

@RestController
public class JobMsgRelaCtrl {
	@Autowired
	private JobMsgRelaService service;
	/**
	 * @api {POST} /crm/manage/msgmng/getJobMsgByEntity 按条件查询调度任务信息发送关联列表
	 * @apiName getJobMsgByEntity
	 * @apiGroup JobMsgRela
	 *
	 * @apiParam {SysJobMsgRela} jobMsgRela 调度任务信息发送关联对象
	 *
	 * @apiSuccess {SysJobMsgRela} data 调度任务信息发送关联列表
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/manage/msgmng/getJobMsgByEntity", method = RequestMethod.POST)
	public Result getMsgByEntity(@RequestBody SysJobMsgRela jobMsgRela) {
		Result res = new Result();
		System.out.println("获取多条调度任务信息发送关联数据。");
		List<SysJobMsgRela> msgList = service.getMsgByEntity(jobMsgRela);
		res.setData(msgList);
		return res;
	}
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/manage/msgmng/getJobMsgList", method = RequestMethod.GET)
	public Result getList() {
		Result res = new Result();
		List<SysJobMsgRela> retLst = service.getAllRecRows();
		res.setData(retLst);
		return res;
	}
	
	//根据条件查询列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/manage/msgmng/getJobMsgListByEntity", method = RequestMethod.GET)
	public Result getListByEntity(SysJobMsgRela param) {
		Result res = new Result();
		List<SysJobMsgRela> retLst = service.getMultiRec(param);
		res.setData(retLst);
		return res;
	}
	
	//查询单条
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/manage/msgmng/getJobMsg", method = RequestMethod.GET)
	public Result getOne(SysJobMsgRela param) {
		Result res = new Result();
		SysJobMsgRela retLst = service.getOneRec(param);
		res.setData(retLst);
		return res;
	}
	
	//增加一条
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/manage/msgmng/saveJobMsg", method = RequestMethod.POST)
	public Result saveOne(SysJobMsgRela param) {
		Result res = new Result();
		SysJobMsgRela retLst = service.insertOne(param);
		res.setData(retLst);
		return res;
	}
	
	//修改一条
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/manage/msgmng/updateJobMsg", method = RequestMethod.PUT)
	public Result updateOne(SysJobMsgRela param) {
		Result res = new Result();
		SysJobMsgRela retLst = service.updateOne(param);
		res.setData(retLst);
		return res;
	}
	
	//删除一条
	@SuppressWarnings("rawtypes")
	@RequestMapping(path = "/crm/manage/msgmng/delJobMsg", method = RequestMethod.DELETE)
	public Result deleteOne(SysJobMsgRela param) {
		Result res = new Result();
		service.deleteOne(param);
		return res;
	}
	
	//逻辑删除一条
	@SuppressWarnings("rawtypes")
	@RequestMapping(path = "/crm/manage/msgmng/invalidJobMsg", method = RequestMethod.PUT)
	public Result invalidOne(SysJobMsgRela param) {
		Result res = new Result();
		service.invalidOne(param);
		return res;
	}
	
	
	
	


}
