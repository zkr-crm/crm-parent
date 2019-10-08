package cn.com.zsyk.crm.ecif.mapper;

import cn.com.zsyk.crm.ecif.entity.EcSimTask;

import java.util.List;
import java.util.Map;

public interface EcSimTaskMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_sim_task
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String taskId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_sim_task
     *
     * @mbggenerated
     */
    int insert(EcSimTask record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_sim_task
     *
     * @mbggenerated
     */
    EcSimTask selectByPrimaryKey(String taskId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_sim_task
     *
     * @mbggenerated
     */
    List<EcSimTask> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_sim_task
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(EcSimTask record);
    
	List<EcSimTask> selectAllTaskByEntity(EcSimTask record);

	List<EcSimTask> selectAllNoApproveUser(EcSimTask record);
	List<EcSimTask> selectAllNoApproveSplitTask(EcSimTask record);
	List<EcSimTask> getMyTaskByEmployeeId(String employeeId);
	List<EcSimTask> getMySplitTaskByEntity(Map map);

    List<EcSimTask> selectAllNoApprove(Map map);
}