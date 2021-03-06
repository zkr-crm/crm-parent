package cn.com.zsyk.crm.ocrm.mapper;

import cn.com.zsyk.crm.ocrm.entity.OcTask;
import java.util.List;

public interface OcTaskMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oc_task
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oc_task
     *
     * @mbggenerated
     */
    int insert(OcTask record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oc_task
     *
     * @mbggenerated
     */
    OcTask selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oc_task
     *
     * @mbggenerated
     */
    List<OcTask> selectAll();
    
    List<OcTask> selectByEntity( OcTask record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oc_task
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(OcTask record);
}