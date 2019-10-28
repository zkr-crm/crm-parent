package cn.com.zsyk.crm.query.bo;

import java.io.Serializable;

/**
 * 忠诚度用户映射类
 * @author houya
 *
 */
public class LoyaltyUserCount implements Serializable{

	private static final long serialVersionUID = 1L;
	//忠诚数量
	private Integer loyaltyUserCount;
	//其他数量
	private Integer otherUserCount;
	public Integer getLoyaltyUserCount() {
		return loyaltyUserCount;
	}
	public void setLoyaltyUserCount(Integer loyaltyUserCount) {
		this.loyaltyUserCount = loyaltyUserCount;
	}
	public Integer getOtherUserCount() {
		return otherUserCount;
	}
	public void setOtherUserCount(Integer otherUserCount) {
		this.otherUserCount = otherUserCount;
	}

}
