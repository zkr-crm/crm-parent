package cn.com.zsyk.crm.ecif.bo.cust;

import java.util.List;
import cn.com.zsyk.crm.ecif.entity.EcCustTag;

public class EntCustList {

	// 客户号
	private String custNo;
	// 客户名称
	private String custNam;
	// 客户类型
	private String custTyp;
	// 标签
	private List<EcCustTag> custTag;
	// 客户来源
	private String custSource;
	// 客户阶段
	private String custPhase;

	/**
	 * Desc:
	 * @return
	 * @author
	 */
	public String getCustNo() {
		return custNo;
	}

	/**
	 * Desc: custNo
	 * @author
	 */
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	// 客户经理
	private String custAgent;
	// 下次跟进时间
	private String nextFollowUpTime;
	// 总消费金额
	// 总消费次数
	// 备注
	private String remark;
	// 创建人
	private String createUser;

	/**
	 * Desc:
	 * @return
	 * @author
	 */
	public String getCustNam() {
		return custNam;
	}

	/**
	 * Desc: custNam
	 * @author
	 */
	public void setCustNam(String custNam) {
		this.custNam = custNam;
	}

	/**
	 * Desc:
	 * @return
	 * @author
	 */
	public String getCustTyp() {
		return custTyp;
	}

	/**
	 * Desc: custTyp
	 * @author
	 */
	public void setCustTyp(String custTyp) {
		this.custTyp = custTyp;
	}

	/**
	 * Desc:
	 * @return
	 * @author
	 */
	public List<EcCustTag> getCustTag() {
		return custTag;
	}

	/**
	 * Desc: custTag
	 * @author
	 */
	public void setCustTag(List<EcCustTag> custTag) {
		this.custTag = custTag;
	}

	/**
	 * Desc:
	 * @return
	 * @author
	 */
	public String getCustSource() {
		return custSource;
	}

	/**
	 * Desc: custSource
	 * @author
	 */
	public void setCustSource(String custSource) {
		this.custSource = custSource;
	}

	/**
	 * Desc:
	 * @return
	 * @author
	 */
	public String getCustPhase() {
		return custPhase;
	}

	/**
	 * Desc: custPhase
	 * @author
	 */
	public void setCustPhase(String custPhase) {
		this.custPhase = custPhase;
	}

	/**
	 * Desc:
	 * @return
	 * @author
	 */
	public String getCustAgent() {
		return custAgent;
	}

	/**
	 * Desc: custAgent
	 * @author
	 */
	public void setCustAgent(String custAgent) {
		this.custAgent = custAgent;
	}

	/**
	 * Desc:
	 * @return
	 * @author
	 */
	public String getNextFollowUpTime() {
		return nextFollowUpTime;
	}

	/**
	 * Desc: nextFollowUpTime
	 * @author
	 */
	public void setNextFollowUpTime(String nextFollowUpTime) {
		this.nextFollowUpTime = nextFollowUpTime;
	}

	/**
	 * Desc:
	 * @return
	 * @author
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * Desc: remark
	 * @author
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * Desc:
	 * @return
	 * @author
	 */
	public String getCreateUser() {
		return createUser;
	}

	/**
	 * Desc: createUser
	 * @author
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
}
