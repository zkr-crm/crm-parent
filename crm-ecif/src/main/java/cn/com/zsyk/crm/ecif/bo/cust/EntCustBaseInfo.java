package cn.com.zsyk.crm.ecif.bo.cust;

public class EntCustBaseInfo {
	// 客户号
		private String custNo;
		// 客户类型
		private String custTyp;
		// 客户名称
		private String entNam;
		// 客户号码
//		private String bizRegCertNo;
//		private String tax_reg_cert_no;
		private String entTyp;
		  private String certNo;
		    
		    private String certTyp;
		public String getCertNo() {
				return certNo;
			}
			public void setCertNo(String certNo) {
				this.certNo = certNo;
			}
			public String getCertTyp() {
				return certTyp;
			}
			public void setCertTyp(String certTyp) {
				this.certTyp = certTyp;
			}
		//private String rgsDate;
//		private String blacklist_flg;
//		private String key_cust_flg;
		public String getCustNo() {
			return custNo;
		}
		public void setCustNo(String custNo) {
			this.custNo = custNo;
		}
		public String getCustTyp() {
			return custTyp;
		}
		public void setCustTyp(String custTyp) {
			this.custTyp = custTyp;
		}
		
		public String getEntNam() {
			return entNam;
		}
		public void setEntNam(String entNam) {
			this.entNam = entNam;
		}
		public String getEntTyp() {
			return entTyp;
		}
		public void setEntTyp(String entTyp) {
			this.entTyp = entTyp;
		}
		
		

}
