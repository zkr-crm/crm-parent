package cn.com.zsyk.crm.ocrm.mapper;

import java.util.List;
import java.util.Map;

import cn.com.zsyk.crm.ocrm.entity.OcDynamSnapshot;

public interface OcDynamSnapshotMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oc_dynam_snapshot
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String snapshotId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oc_dynam_snapshot
     *
     * @mbggenerated
     */
    int insert(OcDynamSnapshot record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oc_dynam_snapshot
     *
     * @mbggenerated
     */
    OcDynamSnapshot selectByPrimaryKey(String snapshotId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oc_dynam_snapshot
     *
     * @mbggenerated
     */
    List<OcDynamSnapshot> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oc_dynam_snapshot
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(OcDynamSnapshot record);
    
    List<OcDynamSnapshot> selectSnapByEntity(OcDynamSnapshot dynamSnapshot);
    
    List<OcDynamSnapshot> selectSnapOnFuzzy(Map map);
}