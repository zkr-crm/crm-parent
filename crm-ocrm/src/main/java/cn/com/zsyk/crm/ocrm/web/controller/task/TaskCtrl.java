package cn.com.zsyk.crm.ocrm.web.controller.task;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.common.exception.ServiceException;
import cn.com.zsyk.crm.generator.EnumType;
import cn.com.zsyk.crm.ocrm.entity.OcTask;
import cn.com.zsyk.crm.ocrm.service.task.TaskService;
import cn.com.zsyk.crm.ocrm.service.user.UserService;

@RestController
public class TaskCtrl {

	@Autowired
	private TaskService service;
	@Autowired
	UserService userService;
	@Autowired
	private ObjectMapper om;
	
	// 查询所有
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ocrm/task/getAll", method = RequestMethod.GET)
	public Result getAll() {
		Result res = new Result();
		List<OcTask> resList = service.getAll();

		res.setData(resList);
		return res;
	}

	// 查询多条
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ocrm/task/getMulti", method = RequestMethod.GET)
	public Result getMultiByEntity(OcTask record) {
		Result res = new Result();
		//List<OcTask> resList = service.getAllRecByEntity(record);
		res.setData(service.getAllRecByEntity(record));
		return res;
	}

	// 查询单条
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ocrm/task/getOne", method = RequestMethod.GET)
	public Result getOne(OcTask record) {
		Result res = new Result();
		OcTask resBean = service.getOneRecByEntity(record);
		res.setData(resBean);
		return res;
	}

	// 新增
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ocrm/task/postOne", method = RequestMethod.POST)
	public Result insertOne(OcTask record) {
		Result res = new Result();
		String taskId = service.insertOne(record);

		res.setData(taskId);
		return res;
	}

	// 修改
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ocrm/task/putOne", method = RequestMethod.PUT)
	public Result updateOne(OcTask record) {
		Result res = new Result();
		int count = service.updateOne(record);

		res.setData(count);
		return res;
	}

	// 删除delete
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ocrm/task/deleteOne", method = RequestMethod.DELETE)
	public Result deleteOne(String id) {
		Result res = new Result();
		int count = service.deleteByPk(id);

		res.setData(count);
		return res;
	}

	// 无效化
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path = "/crm/ocrm/task/putInvalidOne", method = RequestMethod.PUT)
	public Result invalidOne(String id) {
		Result res = new Result();
		int count = service.modDelByEntity(id);

		res.setData(count);
		return res;
	}
	
	//多选完成
	@RequestMapping(path = "/crm/ocrm/task/putMultiDone", method = RequestMethod.PUT)
	public Result delMsgByKey(@RequestBody String checkedRow) throws Exception {
		Result res = new Result();

		List<OcTask> taskList = om.readValue(checkedRow, new TypeReference<List<OcTask>>() {
		});

		// 更新的记录条数
		int doneCount = 0;

		for (OcTask item : taskList) {

			item.setTaskStat(EnumType.TaskStat.done.value);
			item.setRecStat(EnumType.RecStat.normal.value);
			// 执行操作
			int addCount = service.updateOne(item);
			// 删除是否成功
			if (addCount > 0) {

			} else {
				throw new ServiceException("任务更新状态失败!");
			}
			doneCount += addCount;
		}

		res.setData(doneCount);
		res.setMessage("交易成功！");
		return res;
	}
	

}
