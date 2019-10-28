package cn.com.zsyk.crm.ecif.bo.cust;

import java.io.Serializable;
import java.util.List;

/**
 * @author
 * 
 *         客户消费信息
 *
 */
public class CustConsumInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 客户号
	private String custNo;
	// 消费次数
	private String consumTimes;
	// 累计消费金额
	private String consumSum;
	// 末次消费时间
	private String lastSumTime;

	// 消费明细
	private List<CustSumzInfo> custSumzInfoList = null;

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getConsumTimes() {
		return consumTimes;
	}

	public void setConsumTimes(String consumTimes) {
		this.consumTimes = consumTimes;
	}

	public String getConsumSum() {
		return consumSum;
	}

	public void setConsumSum(String consumSum) {
		this.consumSum = consumSum;
	}

	public String getLastSumTime() {
		return lastSumTime;
	}

	public void setLastSumTime(String lastSumTime) {
		this.lastSumTime = lastSumTime;
	}

	public List<CustSumzInfo> getCustSumzInfoList() {
		return custSumzInfoList;
	}

	public void setCustSumzInfoList(List<CustSumzInfo> custSumzInfoList) {
		this.custSumzInfoList = custSumzInfoList;
	}

}
