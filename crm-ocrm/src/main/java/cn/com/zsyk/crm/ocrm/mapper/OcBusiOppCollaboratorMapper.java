package cn.com.zsyk.crm.ocrm.mapper;

import cn.com.zsyk.crm.ocrm.entity.OcBusiOppCollaborator;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OcBusiOppCollaboratorMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oc_busi_opp_collaborator
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(@Param("busiOppNo") String busiOppNo, @Param("collaborator") String collaborator);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oc_busi_opp_collaborator
     *
     * @mbggenerated
     */
    int insert(OcBusiOppCollaborator record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oc_busi_opp_collaborator
     *
     * @mbggenerated
     */
    OcBusiOppCollaborator selectByPrimaryKey(@Param("busiOppNo") String busiOppNo, @Param("collaborator") String collaborator);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oc_busi_opp_collaborator
     *
     * @mbggenerated
     */
    List<OcBusiOppCollaborator> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oc_busi_opp_collaborator
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(OcBusiOppCollaborator record);

	List<OcBusiOppCollaborator> selectBusiOppCollaboratorList(OcBusiOppCollaborator ocBusiOppCollaborator);
}