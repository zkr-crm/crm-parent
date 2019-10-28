package cn.com.zsyk.crm.ocrm.bo.custgroup;

import java.io.Serializable;
import java.util.List;

import cn.com.zsyk.crm.ocrm.entity.OcCustGrp;
import cn.com.zsyk.crm.ocrm.entity.OcCustGrpMember;
import cn.com.zsyk.crm.ocrm.entity.OcDynamSnapshot;
import cn.com.zsyk.crm.ocrm.entity.OcGroupOper;

/**
 * 客户群组信息
 * 
 * @author
 *
 */
public class CustGroupInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 客户群组Id
	 */
	private String groupId;

	/**
	 * 员工编号：逗号隔开
	 */
	private String employeeId;

	/**
	 * 群组信息对象
	 */
	private OcCustGrp custGrp = null;

	/**
	 * 群组运营任务对象
	 */
	private OcGroupOper groupOper = null;

	/**
	 * 群组快照对象
	 */
	private OcDynamSnapshot dynamSnapshot = null;

	/**
	 * 群组成员列表
	 */
	private List<OcCustGrpMember> custGrpMemberList = null;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public OcCustGrp getCustGrp() {
		return custGrp;
	}

	public void setCustGrp(OcCustGrp custGrp) {
		this.custGrp = custGrp;
	}

	public OcGroupOper getGroupOper() {
		return groupOper;
	}

	public void setGroupOper(OcGroupOper groupOper) {
		this.groupOper = groupOper;
	}

	public OcDynamSnapshot getDynamSnapshot() {
		return dynamSnapshot;
	}

	public void setDynamSnapshot(OcDynamSnapshot dynamSnapshot) {
		this.dynamSnapshot = dynamSnapshot;
	}

	public List<OcCustGrpMember> getCustGrpMemberList() {
		return custGrpMemberList;
	}

	public void setCustGrpMemberList(List<OcCustGrpMember> custGrpMemberList) {
		this.custGrpMemberList = custGrpMemberList;
	}

}
