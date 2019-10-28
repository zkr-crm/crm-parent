package cn.com.zsyk.crm.generator;

//自动生成java类
public interface EnumType {
  enum ContentType {
  			telphone_proposal("telphone_proposal","0","电话投保"),
  			cust_complain("cust_complain","1","客户投诉"),
  			call_on_cust("call_on_cust","2","拜访客户"),
  			cust_return_visit("cust_return_visit","3","客户回访"),
  	;
  	private ContentType(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "ContentType";
	public final static String enumDesc = "内容类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static ContentType toEnum(String value){
	  for(EnumType.ContentType item :EnumType.ContentType.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum MsgStat {
  			success("success","2","发送成功"),
  			send("send","1","发送中"),
  			fail("fail","3","发送失败"),
  	;
  	private MsgStat(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "MsgStat";
	public final static String enumDesc = "消息状态";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static MsgStat toEnum(String value){
	  for(EnumType.MsgStat item :EnumType.MsgStat.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum UserSex {
  			male("male","0","男"),
  			female("female","1","女"),
  	;
  	private UserSex(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "UserSex";
	public final static String enumDesc = "员工性别";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static UserSex toEnum(String value){
	  for(EnumType.UserSex item :EnumType.UserSex.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum NameType {
  			chinese_name("chinese_name","0","中文姓名"),
  			english_name("english_name","1","英文姓名"),
  	;
  	private NameType(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "NameType";
	public final static String enumDesc = "姓名类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static NameType toEnum(String value){
	  for(EnumType.NameType item :EnumType.NameType.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum sendChannel {
  			message("message","01","短信"),
  			WeChat("WeChat","02","微信"),
  			email("email","03","邮件"),
  			app("app","04","APP"),
  			information("information","05","站内信"),
  			remind("remind","06","站内提醒"),
  	;
  	private sendChannel(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "sendChannel";
	public final static String enumDesc = "发送渠道";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static sendChannel toEnum(String value){
	  for(EnumType.sendChannel item :EnumType.sendChannel.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum PayMode {
  			bank_insur_connect("bank_insur_connect","B","银保通"),
  			cash_debit_note("cash_debit_note","C","现金缴款单"),
  			third_pay("third_pay","D","第三方代收付"),
  			bank_transfer_("bank_transfer_","E","银行转账(非制盘)"),
  			card_transfer("card_transfer","F","卡折划款"),
  			alipay("alipay","G","支付宝"),
  			other("other","Z","其它"),
  			transfer_cheque("transfer_cheque","3","转账支票"),
  			internet_bank("internet_bank","9","网银"),
  			cash("cash","1","现金"),
  			pos("pos","2","POS机"),
  			bank_transfer("bank_transfer","4","银行转账"),
  			inner_transfer("inner_transfer","5","内部转账"),
  			bank_draft("bank_draft","6","银行汇票"),
  			cashier_cheque("cashier_cheque","7","银行本票"),
  			post_insur_connect("post_insur_connect","A","邮保通"),
  	;
  	private PayMode(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "PayMode";
	public final static String enumDesc = "交费方式";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static PayMode toEnum(String value){
	  for(EnumType.PayMode item :EnumType.PayMode.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum AssetsTyp {
  			house("house","01","房产"),
  			car("car","02","汽车"),
  			insurance("insurance","03","保险"),
  			other("other","04","其他"),
  	;
  	private AssetsTyp(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "AssetsTyp";
	public final static String enumDesc = "资产类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static AssetsTyp toEnum(String value){
	  for(EnumType.AssetsTyp item :EnumType.AssetsTyp.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum ApprovConclusion {
  			pass("pass","0","通过"),
  			send_back("send_back","1","退回"),
  	;
  	private ApprovConclusion(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "ApprovConclusion";
	public final static String enumDesc = "审批结论";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static ApprovConclusion toEnum(String value){
	  for(EnumType.ApprovConclusion item :EnumType.ApprovConclusion.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum ClaimType {
  			disease_medical("disease_medical","200","疾病医疗"),
  			disease_injury("disease_injury","201","疾病伤残"),
  			disease_death("disease_death","202","疾病身故"),
  			disease_total_disability("disease_total_disability","203","疾病全残"),
  			disease_severe("disease_severe","204","疾病重疾"),
  			disease_disability("disease_disability","205","疾病失能"),
  			disease_nowork("disease_nowork","206","疾病失业"),
  			disease_exempt("disease_exempt","209","疾病豁免"),
  			accident_medical("accident_medical","100","意外医疗"),
  			accident_injury("accident_injury","101","意外伤残"),
  			accident_death("accident_death","102","意外身故"),
  			accident_total_disability("accident_total_disability","103","意外全残"),
  			accident_severe_disease("accident_severe_disease","104","意外重疾"),
  			accident_disability("accident_disability","105","意外失能"),
  			accident_nowork("accident_nowork","106","意外失业"),
  			accident_exempt("accident_exempt","109","意外豁免"),
  	;
  	private ClaimType(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "ClaimType";
	public final static String enumDesc = "理赔类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static ClaimType toEnum(String value){
	  for(EnumType.ClaimType item :EnumType.ClaimType.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum AgentKind {
  			general_agent("general_agent","10","普通代理人  "),
  			group_supervisor("group_supervisor","11","组主管      "),
  			dept_supervisor("dept_supervisor","12","部主管      "),
  			superviser("superviser","13","督导长      "),
  			area_superviser("area_superviser","14","区域督导长  "),
  			corp_customer_manager("corp_customer_manager","20","法人客户经理"),
  			corp_group_manager("corp_group_manager","21","法人组经理  "),
  			corp_assistant("corp_assistant","22","法人协理    "),
  			corp_dept_manager("corp_dept_manager","23","法人部经理  "),
  			training_duty("training_duty","24","培训岗      "),
  			planning_duty("planning_duty","25","企划策划岗  "),
  			sales_support_duty("sales_support_duty","26","销售支援岗  "),
  			synthetical_duty("synthetical_duty","27","综合岗       "),
  			sum_up_attache("sum_up_attache","29","综拓专员    "),
  			agency_continue_receive("agency_continue_receive","31","中介续收督导"),
  			charg_supervise("charg_supervise","32","收费督导岗  "),
  			other("other","99","其他"),
  	;
  	private AgentKind(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "AgentKind";
	public final static String enumDesc = "代理人类别";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static AgentKind toEnum(String value){
	  for(EnumType.AgentKind item :EnumType.AgentKind.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum CustGroupTyp {
  			dynamicGroup("dynamicGroup","0","动态群组"),
  			staticGroup("staticGroup","1","静态群组"),
  	;
  	private CustGroupTyp(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "CustGroupTyp";
	public final static String enumDesc = "客户组类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static CustGroupTyp toEnum(String value){
	  for(EnumType.CustGroupTyp item :EnumType.CustGroupTyp.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum IntegralChgType {
  			increase("increase","0","增加"),
  			decrease("decrease","1","减少"),
  	;
  	private IntegralChgType(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "IntegralChgType";
	public final static String enumDesc = "积分变化类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static IntegralChgType toEnum(String value){
	  for(EnumType.IntegralChgType item :EnumType.IntegralChgType.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum TaskType {
  			remind("remind","1","提醒"),
  			caring("caring","2","关怀"),
  			callback("callback","3","回访"),
  			marketing("marketing","4","营销"),
  			others("others","9","其它"),
  	;
  	private TaskType(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "TaskType";
	public final static String enumDesc = "任务类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static TaskType toEnum(String value){
	  for(EnumType.TaskType item :EnumType.TaskType.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum Marriage {
  			other("other","9","其他"),
  			single("single","0","未婚"),
  			married("married","1","已婚"),
  			divorced("divorced","2","离异"),
  			widowed("widowed","3","丧偶"),
  	;
  	private Marriage(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "Marriage";
	public final static String enumDesc = "婚姻状况";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static Marriage toEnum(String value){
	  for(EnumType.Marriage item :EnumType.Marriage.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum RefCustNoType {
  			agent_no("agent_no","0","代理人编号"),
  			ent_cust_no("ent_cust_no","1","企业客户号"),
  	;
  	private RefCustNoType(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "RefCustNoType";
	public final static String enumDesc = "关联客户号类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static RefCustNoType toEnum(String value){
	  for(EnumType.RefCustNoType item :EnumType.RefCustNoType.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum Degree {
  			other("other","00","其它"),
  			master("master","10","研究生或以上"),
  			bachelor("bachelor","20","本科"),
  			college("college","30","大专"),
  			technical_secondary_school("technical_secondary_school","40","中专"),
  			high_school("high_school","50","高中"),
  			junior_school("junior_school","60","初中"),
  			primary_school("primary_school","70","小学"),
  	;
  	private Degree(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "Degree";
	public final static String enumDesc = "学历";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static Degree toEnum(String value){
	  for(EnumType.Degree item :EnumType.Degree.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum UserStat {
  			normal("normal","0","正常"),
  			turnover("turnover","1","注销"),
  			vacation("vacation","2","休假"),
  	;
  	private UserStat(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "UserStat";
	public final static String enumDesc = "用户状态";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static UserStat toEnum(String value){
	  for(EnumType.UserStat item :EnumType.UserStat.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum ProductType {
  			life_insurance("life_insurance","0","寿险"),
  			accident_insurance("accident_insurance","1","意外险"),
  			health_insurance("health_insurance","2","健康险"),
  			property_insurance("property_insurance","3","家财险"),
  			car_insurance("car_insurance","4","车险"),
  			other("other","9","其他"),
  	;
  	private ProductType(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "ProductType";
	public final static String enumDesc = "产品类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static ProductType toEnum(String value){
	  for(EnumType.ProductType item :EnumType.ProductType.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum SimTaskState {
  			unDistribute("unDistribute","0","待分配"),
  			pending("pending","1","待处理"),
  			close("close","10","关闭"),
  			close_apply("close_apply","11","关闭申请"),
  			close_send_back("close_send_back","12","关闭退回"),
  			merge_apply("merge_apply","2","合并申请"),
  			merge_approv("merge_approv","3","合并审批"),
  			merge_send_back("merge_send_back","4","合并退回"),
  			split_apply("split_apply","5","拆分申请"),
  			split_approv("split_approv","6","拆分审批"),
  			split_send_back("split_send_back","7","拆分退回"),
  			merged("merged","8","已合并"),
  			split("split","9","已拆分"),
  	;
  	private SimTaskState(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "SimTaskState";
	public final static String enumDesc = "相似客户任务状态";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static SimTaskState toEnum(String value){
	  for(EnumType.SimTaskState item :EnumType.SimTaskState.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum IdType {
  			hmt_reside("hmt_reside","550","港澳台居民居住证"),
  			passport("passport","02","护照"),
  			student_card("student_card","08","学生证"),
  			cpassport("cpassport","07","中国护照"),
  			id_card("id_card","01","身份证"),
  			military_card("military_card","03","军人证（军官证）"),
  			driver_license("driver_license","04","驾照"),
  			household_register("household_register","06","户口本"),
  			employee_card("employee_card","09","工作证"),
  			birth_cert("birth_cert","10","出生证"),
  			other_cert("other_cert","99","其它"),
  			no_cert("no_cert","11","无证件"),
  			soldier_card("soldier_card","A","士兵证"),
  			return_home_cert("return_home_cert","B","回乡证"),
  			temp_id_card("temp_id_card","C","临时身份证"),
  			police_officer_card("police_officer_card","D","警官证"),
  			mtp("mtp","E","台胞证"),
  			hmt_pass("hmt_pass","05","港澳台通行证"),
  			foreign_resi_perm("foreign_resi_perm","F","外国人居留证"),
  	;
  	private IdType(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "IdType";
	public final static String enumDesc = "证件类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static IdType toEnum(String value){
	  for(EnumType.IdType item :EnumType.IdType.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum BlacklistSrc {
  			src_rs("src_rs","01","融盛保险"),
  			src_others_org("src_others_org","02","其他保险机构"),
  			src_center_bank("src_center_bank","03","央行"),
  			src_police("src_police","04","公安部门"),
  			src_others("src_others","99","其他"),
  	;
  	private BlacklistSrc(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "BlacklistSrc";
	public final static String enumDesc = "黑名单类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static BlacklistSrc toEnum(String value){
	  for(EnumType.BlacklistSrc item :EnumType.BlacklistSrc.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum DataSource {
  			ecif("ecif","01","录入"),
  			core_sys("core_sys","02","核心"),
  			Lazada("Lazada","04","一账通"),
  			wechat_pub_act("wechat_pub_act","05","微信"),
  			mul_import("mul_import","06","批量导入"),
  			other("other","99","其他"),
  			official_website ("official_website ","03","官网"),
  			udesk("udesk","07","客服"),
  			claim("claim","10","理赔系统"),
  	;
  	private DataSource(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "DataSource";
	public final static String enumDesc = "数据来源";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static DataSource toEnum(String value){
	  for(EnumType.DataSource item :EnumType.DataSource.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum CustMergeTyp {
  			sameMerge("sameMerge","0","相同客户合并"),
  			simMerge("simMerge","1","相似客户合并"),
  	;
  	private CustMergeTyp(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "CustMergeTyp";
	public final static String enumDesc = "客户合并类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static CustMergeTyp toEnum(String value){
	  for(EnumType.CustMergeTyp item :EnumType.CustMergeTyp.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum CustChartType {
  			cust_source("cust_source","0","客户来源"),
  			cust_loss("cust_loss","1","流失分布"),
  			cust_follow_up("cust_follow_up","2","跟进分布"),
  			cust_value("cust_value","3","价值分布"),
  			consumption_times("consumption_times","4","消费次数"),
  	;
  	private CustChartType(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "CustChartType";
	public final static String enumDesc = "客户分析图表类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static CustChartType toEnum(String value){
	  for(EnumType.CustChartType item :EnumType.CustChartType.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum CaseStatus {
  			wait_proces("wait_proces","0","待处理"),
  			processing("processing","1","处理中"),
  			closed("closed","2","已关闭"),
  			canceled("canceled","3","已取消"),
  	;
  	private CaseStatus(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "CaseStatus";
	public final static String enumDesc = "案件状态";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static CaseStatus toEnum(String value){
	  for(EnumType.CaseStatus item :EnumType.CaseStatus.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum ClmState {
  			report("report","10","报案"),
  			register("register","20","立案"),
  			audit("audit","30","审核"),
  			sign("sign","40","签批"),
  			endcase("endcase","50","结案"),
  			complete("complete","60","完成"),
  			close("close","70","关闭"),
  	;
  	private ClmState(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "ClmState";
	public final static String enumDesc = "赔案状态";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static ClmState toEnum(String value){
	  for(EnumType.ClmState item :EnumType.ClmState.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum CreditGrade {
  			high_credit("high_credit","1","高等信用"),
  			middle_credit("middle_credit","2","中等信用"),
  			low_credit("low_credit","3","低等信用"),
  	;
  	private CreditGrade(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "CreditGrade";
	public final static String enumDesc = "信用等级";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static CreditGrade toEnum(String value){
	  for(EnumType.CreditGrade item :EnumType.CreditGrade.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum GroupMemberType {
  			all("all","3","个人正式及潜在客户"),
  			formal("formal","1","个人正式客户"),
  			potential("potential","2","个人潜在客户"),
  	;
  	private GroupMemberType(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "GroupMemberType";
	public final static String enumDesc = "群成员类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static GroupMemberType toEnum(String value){
	  for(EnumType.GroupMemberType item :EnumType.GroupMemberType.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum BlacklistType {
  			health("health","01","健康问题"),
  			creadit_level("creadit_level","03","信用等级差"),
  			others("others","99","其他"),
  			fake("fake","02","欺诈问题"),
  	;
  	private BlacklistType(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "BlacklistType";
	public final static String enumDesc = "黑名单类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static BlacklistType toEnum(String value){
	  for(EnumType.BlacklistType item :EnumType.BlacklistType.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum tplType {
  			msgConf("msgConf","01","短信"),
  			sysAutoRemind("sysAutoRemind","02","站内提醒"),
  	;
  	private tplType(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "tplType";
	public final static String enumDesc = "模板类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static tplType toEnum(String value){
	  for(EnumType.tplType item :EnumType.tplType.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum Organization {
  			emply("emply","3","员工"),
  			enter("enter","0","机构"),
  			dept("dept","1","部门"),
  			posi("posi","2","岗位"),
  	;
  	private Organization(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "Organization";
	public final static String enumDesc = "组织架构";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static Organization toEnum(String value){
	  for(EnumType.Organization item :EnumType.Organization.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum msgType {
  			manual("manual","1","手动"),
  			automatic("automatic","0","自动"),
  	;
  	private msgType(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "msgType";
	public final static String enumDesc = "信息类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static msgType toEnum(String value){
	  for(EnumType.msgType item :EnumType.msgType.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum sendWay {
  			direct("direct","0","直接发送"),
  			timing("timing","1","定时发送"),
  	;
  	private sendWay(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "sendWay";
	public final static String enumDesc = "发送方式";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static sendWay toEnum(String value){
	  for(EnumType.sendWay item :EnumType.sendWay.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum msgState {
  			invalid("invalid","0","未生效"),
  			effective("effective","1","生效"),
  	;
  	private msgState(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "msgState";
	public final static String enumDesc = "信息状态";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static msgState toEnum(String value){
	  for(EnumType.msgState item :EnumType.msgState.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum Sex {
  			male("male","1","男"),
  			female("female","2","女"),
  			unknown("unknown","0","未知"),
  			other("other","3","其他"),
  	;
  	private Sex(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "Sex";
	public final static String enumDesc = "性别";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static Sex toEnum(String value){
	  for(EnumType.Sex item :EnumType.Sex.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum isRead {
  			unread("unread","0","未读"),
  			read("read","1","已读"),
  	;
  	private isRead(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "isRead";
	public final static String enumDesc = "是否已读";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static isRead toEnum(String value){
	  for(EnumType.isRead item :EnumType.isRead.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum msg_order {
  			third("third","3","3级"),
  			O("O","0","0级"),
  			first("first","1","1级"),
  			second("second","2","2级"),
  			fourth("fourth","4","4级"),
  			fifth("fifth","5","5级"),
  			sixth("sixth","6","6级"),
  			seventh("seventh","7","7级"),
  			eighth("eighth","8","8级"),
  			ninth("ninth","9","9级"),
  	;
  	private msg_order(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "msg_order";
	public final static String enumDesc = "消息优先级";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static msg_order toEnum(String value){
	  for(EnumType.msg_order item :EnumType.msg_order.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum PayIntv {
  			quarter("quarter","3","季交"),
  			unfixed("unfixed","-1","不定期交"),
  			single("single","0","一次交清"),
  			month("month","1","月交"),
  			year("year","12","年交"),
  			half_year("half_year","6","半年交"),
  	;
  	private PayIntv(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "PayIntv";
	public final static String enumDesc = "交费间隔";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static PayIntv toEnum(String value){
	  for(EnumType.PayIntv item :EnumType.PayIntv.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum EnterLevel {
  			level_1("level_1","1","一级"),
  			level_2("level_2","2","二级"),
  			level_3("level_3","3","三级"),
  			level_4("level_4","4","四级"),
  			level_5("level_5","5","五级"),
  			level_6("level_6","6","六级"),
  			level_7("level_7","7","七级"),
  			level_8("level_8","8","八级"),
  			level_9("level_9","9","九级"),
  	;
  	private EnterLevel(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "EnterLevel";
	public final static String enumDesc = "机构级别";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static EnterLevel toEnum(String value){
	  for(EnumType.EnterLevel item :EnumType.EnterLevel.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum RecStat {
  			normal("normal","0","正常"),
  			delete("delete","1","删除"),
  	;
  	private RecStat(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "RecStat";
	public final static String enumDesc = "记录状态";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static RecStat toEnum(String value){
	  for(EnumType.RecStat item :EnumType.RecStat.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum TagScope {
  			head("head","0","总机构"),
  			branch("branch","1","分机构"),
  			subbranch("subbranch","2","支机构"),
  			office("office","3","分理处"),
  	;
  	private TagScope(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "TagScope";
	public final static String enumDesc = "标签使用范围";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static TagScope toEnum(String value){
	  for(EnumType.TagScope item :EnumType.TagScope.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum busiType {
  			custManage("custManage","01","客户管理"),
  			custService("custService","02","客户服务"),
  			custMarketing("custMarketing","03","客户营销"),
  			saleManage("saleManage","04","销售管理"),
  			companyOperation("companyOperation","05","公司运营"),
  			hr("hr","06","人事相关"),
  			others("others","99","其他"),
  	;
  	private busiType(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "busiType";
	public final static String enumDesc = "业务类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static busiType toEnum(String value){
	  for(EnumType.busiType item :EnumType.busiType.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum GroupTaskType {
  			OutBound("OutBound","0","外呼任务"),
  	;
  	private GroupTaskType(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "GroupTaskType";
	public final static String enumDesc = "群组任务类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static GroupTaskType toEnum(String value){
	  for(EnumType.GroupTaskType item :EnumType.GroupTaskType.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum BizWay {
  			wholesale("wholesale","0","批发"),
  			retail("retail","1","零售"),
  			other("other","2","其他"),
  	;
  	private BizWay(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "BizWay";
	public final static String enumDesc = "经营方式";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static BizWay toEnum(String value){
	  for(EnumType.BizWay item :EnumType.BizWay.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum MarkTagType {
  			automatic("automatic","0","自动"),
  			manual("manual","1","手动"),
  	;
  	private MarkTagType(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "MarkTagType";
	public final static String enumDesc = "打标签类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static MarkTagType toEnum(String value){
	  for(EnumType.MarkTagType item :EnumType.MarkTagType.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum productTyp {
  			accident("accident","01","意外"),
  			life("life","02","寿险"),
  			health("health","03","健康"),
  			auto("auto","04","车险"),
  	;
  	private productTyp(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "productTyp";
	public final static String enumDesc = "产品分类";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static productTyp toEnum(String value){
	  for(EnumType.productTyp item :EnumType.productTyp.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum proposalSts {
  			no_accept("no_accept","0","未受理"),
  			improving("improving","1","改进中"),
  			yet_improve("yet_improve","2","已改进"),
  			yet_cancel("yet_cancel","3","已取消"),
  	;
  	private proposalSts(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "proposalSts";
	public final static String enumDesc = "建议状态";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static proposalSts toEnum(String value){
	  for(EnumType.proposalSts item :EnumType.proposalSts.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum GrpNature {
  			guo_you("guo_you","1","国有"),
  			ji_ti("ji_ti","2","集体"),
  			si_ying("si_ying","3","私营"),
  			ge_ti("ge_ti","4","个体"),
  			gu_fen("gu_fen","5","股份制"),
  			san_zi("san_zi","6","三资"),
  			ji_guan("ji_guan","7","机关"),
  			shi_ye("shi_ye","8","事业单位"),
  			other("other","9","其它"),
  	;
  	private GrpNature(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "GrpNature";
	public final static String enumDesc = "单位性质";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static GrpNature toEnum(String value){
	  for(EnumType.GrpNature item :EnumType.GrpNature.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum Nationality {
  			CHINA("CHINA","CN","中华人民共和国"),
  			ANDORRA("ANDORRA","AD","安道尔公国"),
  			UNITED_ARAB_EMIRATES("UNITED_ARAB_EMIRATES","AE","阿拉伯联合酋长国"),
  			AFGHANISTAN("AFGHANISTAN","AF","阿富汗"),
  			ANTIGUA("ANTIGUA","AG","安提瓜和巴布达"),
  			ANGUILLA("ANGUILLA","AI","安圭拉"),
  			ALBANIA("ALBANIA","AL","阿尔巴尼亚共和国"),
  			ARMENIA("ARMENIA","AM","亚美尼亚共和国"),
  			NETHERLAND_ANTILLES("NETHERLAND_ANTILLES","AN","荷属安的列斯"),
  			ANGOLA("ANGOLA","AO","安哥拉共和国"),
  			ANTARCTICA("ANTARCTICA","AQ","南极洲"),
  			ARGENTINA("ARGENTINA","AR","阿根廷共和国"),
  			WESTERN_SAMOA("WESTERN_SAMOA","AS","美属萨摩亚"),
  			AUSTRIA("AUSTRIA","AT","奥地利共和国"),
  			AUSTRALIA("AUSTRALIA","AU","澳大利亚联邦"),
  			ARUBA("ARUBA","AW","阿鲁巴"),
  			AZERBAIJAN("AZERBAIJAN","AZ","阿塞拜疆共和国"),
  			BOSNIA_AND_HERZEGOVINA("BOSNIA_AND_HERZEGOVINA","BA","波斯尼亚和黑塞哥维那"),
  			BARBADOS("BARBADOS","BB","巴巴多斯"),
  			BANGLADESH("BANGLADESH","BD","孟加拉人民共和国"),
  			BELGIUM("BELGIUM","BE","比利时共和国"),
  			BURKINA_FASO("BURKINA_FASO","BF","布基纳法索"),
  			BULGARIA("BULGARIA","BG","保加利亚共和国"),
  			BAHRAIN("BAHRAIN","BH","巴林国"),
  			BURUNDI("BURUNDI","BI","布隆迪共和国"),
  			BENIN("BENIN","BJ","贝宁共和国"),
  			BERMUDA("BERMUDA","BM","百慕大"),
  			BRUNEI_DARUSSALAM("BRUNEI_DARUSSALAM","BN","文莱达鲁萨兰国"),
  			BOLIVIA("BOLIVIA","BO","玻利维亚共和国"),
  			Botswana("Botswana","BP","博茨瓦纳"),
  			BRAZIL("BRAZIL","BR","巴西联邦共和国"),
  			BAHAMAS("BAHAMAS","BS","巴哈马联邦"),
  			BHUTAN("BHUTAN","BT","不丹王国"),
  			BOUVET_ISLAND("BOUVET_ISLAND","BV","布维岛"),
  			BOTSWANA("BOTSWANA","BW","博茨瓦纳共和国"),
  			BELARUS("BELARUS","BY","白俄罗斯共和国"),
  			BELIZE("BELIZE","BZ","伯利兹"),
  			CANADA("CANADA","CA","加拿大"),
  			COCOS_KEELING_ISLANDS("COCOS_KEELING_ISLANDS","CC","科科斯（基林）群岛"),
  			CONGO_DEM_REP_OF_THE("CONGO_DEM_REP_OF_THE","CD","刚果民主共和国"),
  			CENTRAL_AFRICAN_REPUBLIC("CENTRAL_AFRICAN_REPUBLIC","CF","中非共和国"),
  			CONGO_REPUBLIC_OF("CONGO_REPUBLIC_OF","CG","刚果共和国"),
  			SWITZERLAND("SWITZERLAND","CH","瑞士联邦"),
  			IVORY_COAST("IVORY_COAST","CI","科特迪瓦共和国"),
  			COOK_ISLAND("COOK_ISLAND","CK","库克群岛"),
  			CHILE("CHILE","CL","智利共和国"),
  			CAMEROON("CAMEROON","CM","喀麦隆共和国"),
  			COLOMBIA("COLOMBIA","CO","哥伦比亚共和国"),
  			COSTA_RICA("COSTA_RICA","CR","哥斯达黎加共和国"),
  			The_Republic_of_Serbia("The_Republic_of_Serbia","CS","塞尔维亚"),
  			CUBA("CUBA","CU","古巴共和国"),
  			CAPE_VERDE("CAPE_VERDE","CV","佛得角共和国"),
  			CHRISTMAS_ISLAND("CHRISTMAS_ISLAND","CX","圣诞岛"),
  			CYPRUS("CYPRUS","CY","塞浦路斯共和国"),
  			CZECH_REPUBLIC("CZECH_REPUBLIC","CZ","捷克共和国"),
  			GERMANY("GERMANY","DE","德意志联邦共和国"),
  			DJIBOUTI("DJIBOUTI","DJ","吉布提共和国"),
  			DENMARK("DENMARK","DK","丹麦王国"),
  			DOMINICA("DOMINICA","DM","多米尼克国"),
  			DOMINCAN_REPUBLIC("DOMINCAN_REPUBLIC","DO","多米尼加共和国"),
  			ALGERIA("ALGERIA","DZ","阿尔及利亚民主人民共和国"),
  			ECUADOR("ECUADOR","EC","厄瓜多尔共和国"),
  			ESTONIA("ESTONIA","EE","爱沙尼亚共和国"),
  			EGYPT("EGYPT","EG","阿拉伯埃及共和国"),
  			WESTERN_SAHARA("WESTERN_SAHARA","EH","西撒哈拉"),
  			ERITREA("ERITREA","ER","厄立特里亚国"),
  			SPAIN("SPAIN","ES","西班牙王国"),
  			ETHIOPIA("ETHIOPIA","ET","埃塞俄比亚联邦民主共和国"),
  			FINLAND("FINLAND","FI","芬兰共和国"),
  			FIJI("FIJI","FJ","雯济群岛共和国"),
  			FALKLAND_ISLANDS("FALKLAND_ISLANDS","FK","福克兰群岛（马尔维纳斯）"),
  			MICRONESIA("MICRONESIA","FM","密克罗尼西亚联邦"),
  			FAROE_ISLANDS("FAROE_ISLANDS","FO","法罗群岛"),
  			FRANCE("FRANCE","FR","法兰西共和国"),
  			GABON("GABON","GA","加蓬共和国"),
  			UNITED_KINGDOM("UNITED_KINGDOM","GB","大不列颠及北爱尔兰联合王国"),
  			GRENADA("GRENADA","GD","格林纳达"),
  			GEORGIA_REPUBLIC_OF("GEORGIA_REPUBLIC_OF","GE","格鲁吉亚"),
  			FRENCH_GUIANA("FRENCH_GUIANA","GF","法属圭亚那"),
  			GHANA("GHANA","GH","加纳共和国"),
  			GIBRALTAR("GIBRALTAR","GI","直布罗陀"),
  			GREENLAND("GREENLAND","GL","格陵兰"),
  			GAMBIA("GAMBIA","GM","冈比亚共和国"),
  			GUINEA_REPUBLIC_OF("GUINEA_REPUBLIC_OF","GN","几内亚共和国"),
  			GUADELOUPE("GUADELOUPE","GP","瓜德罗普"),
  			EQUATORIAL_GUINEA_REPUBLIC_OF("EQUATORIAL_GUINEA_REPUBLIC_OF","GQ","赤道几内亚共和国"),
  			GREECE("GREECE","GR","希腊共和国"),
  			SOUTH_GEORGIA_AND_SOUTH_SANDWICH_ISLANDS("SOUTH_GEORGIA_AND_SOUTH_SANDWICH_ISLANDS","GS","南乔治亚岛和南桑德韦奇岛"),
  			GUATEMALA("GUATEMALA","GT","危地马拉共和国"),
  			GUAM("GUAM","GU","关岛"),
  			GUINEA_BISSAU_REPUBLIC_OF("GUINEA_BISSAU_REPUBLIC_OF","GW","几内亚比绍共和国"),
  			GUYANA("GUYANA","GY","圭亚那合作共和国"),
  			HONG_KONG("HONG_KONG","HK","中国香港特别行政区"),
  			HEARD_ISLAND_AND_MCDONALD_ISLANDS("HEARD_ISLAND_AND_MCDONALD_ISLANDS","HM","赫德岛和麦克唐纳岛"),
  			HONDURAS_REPUBLIC_OF("HONDURAS_REPUBLIC_OF","HN","洪都拉斯共和国"),
  			CROATIA("CROATIA","HR","克罗地亚共和国"),
  			HAITI("HAITI","HT","海地共和国"),
  			HUNGARY("HUNGARY","HU","匈牙利共和国"),
  			INDONESIA("INDONESIA","ID","印度尼西亚共和国"),
  			IRELAND("IRELAND","IE","爱尔兰"),
  			ISRAEL("ISRAEL","IL","以色列国"),
  			INDIA("INDIA","IN","印度共和国"),
  			BRITISH_INDIAN_OCEAN_TERRITORY("BRITISH_INDIAN_OCEAN_TERRITORY","IO","英属印度洋领地"),
  			IRAQ("IRAQ","IQ","伊拉克共和国"),
  			IRAN("IRAN","IR","伊朗伊斯兰共和国"),
  			ICELAND("ICELAND","IS","冰岛共和国"),
  			ITALY("ITALY","IT","意大利共和国"),
  			JAMAICA("JAMAICA","JM","牙买加"),
  			JORDAN("JORDAN","JO","约旦哈希姆王国"),
  			JAPAN("JAPAN","JP","日本国"),
  			KENYA("KENYA","KE","肯尼亚共和国"),
  			KYRGYZSTAN("KYRGYZSTAN","KG","吉尔吉斯共和国"),
  			CAMBODIA("CAMBODIA","KH","柬埔寨王国"),
  			KIRIBATI("KIRIBATI","KI","基里巴斯共和国"),
  			COMOROS("COMOROS","KM","科摩罗伊斯兰联邦共和国"),
  			SAINT_KITTS_AND_NEVIS("SAINT_KITTS_AND_NEVIS","KN","圣基茨和尼维斯联邦"),
  			KOREA_NORTH("KOREA_NORTH","KP","朝鲜民主主义人民共和国"),
  			KOREA_SOUTH("KOREA_SOUTH","KR","大韩民国"),
  			KOSOVO("KOSOVO","KSW","科索沃"),
  			KUWAIT("KUWAIT","KW","科威特国"),
  			CAYMAN_ISLANDS("CAYMAN_ISLANDS","KY","开曼群岛"),
  			KAZAKHSTAN("KAZAKHSTAN","KZ","哈萨克斯坦共和国"),
  			LAO_PDR("LAO_PDR","LA","老挝人民民主共和国"),
  			LEBANON("LEBANON","LB","黎巴嫩共和国"),
  			SAINT_LUCIA("SAINT_LUCIA","LC","圣卢西亚"),
  			LIECHTENSTEIN("LIECHTENSTEIN","LI","列支敦士登公国"),
  			SRI_LANKA("SRI_LANKA","LK","斯里兰卡民主社会主义共和国"),
  			LIBERIA("LIBERIA","LR","利比里亚共共和国"),
  			LESOTHO("LESOTHO","LS","莱索托共和国"),
  			LITHUANIA_REPUBLIC_OF("LITHUANIA_REPUBLIC_OF","LT","立陶宛共和国"),
  			LUXEMBOURG("LUXEMBOURG","LU","卢森堡大公国"),
  			LATVIA("LATVIA","LV","拉托维亚共和国"),
  			LIBYA("LIBYA","LY","大阿拉伯利比亚人民社会主义民众国"),
  			MOROCCO("MOROCCO","MA","摩洛哥王国"),
  			MONACO("MONACO","MC","摩纳哥公国"),
  			MOLDOVA_REPUBLIC_OF("MOLDOVA_REPUBLIC_OF","MD","摩尔多瓦共和国"),
  			MADAGASCAR_DEM_REP_OF("MADAGASCAR_DEM_REP_OF","MG","马达加斯加共和国"),
  			MARSHALL_ISLANDS("MARSHALL_ISLANDS","MH","马绍尔群岛共和国"),
  			MACEDONIA("MACEDONIA","MK","前南斯拉夫马其顿共和国"),
  			MALI("MALI","ML","马里共和国"),
  			MYANMAR_UNION_OF("MYANMAR_UNION_OF","MM","缅甸联邦"),
  			MONGOLIA("MONGOLIA","MN","蒙古国"),
  			MACAU("MACAU","MO","中国澳门特别行政区"),
  			MARIANA_ISLANDS_NORTHERN("MARIANA_ISLANDS_NORTHERN","MP","北马里亚纳自由联邦"),
  			MARTINIQUE("MARTINIQUE","MQ","马提尼克"),
  			MAURITANIA("MAURITANIA","MR","毛里塔尼亚伊斯兰共和国"),
  			MONTSERRAT("MONTSERRAT","MS","蒙特塞拉特"),
  			MALTA("MALTA","MT","马其他共和国"),
  			MAURITIUS("MAURITIUS","MU","毛里求斯共和国"),
  			MALDIVES_REPUBLIC_OF("MALDIVES_REPUBLIC_OF","MV","马尔代夫共和国"),
  			MALAWI("MALAWI","MW","马拉维亚共和国"),
  			MEXICO("MEXICO","MX","墨西哥合众国"),
  			MALAYSIA("MALAYSIA","MY","马来西亚"),
  			MOZAMBIQUE("MOZAMBIQUE","MZ","莫桑比克共和国"),
  			NAMIBIA("NAMIBIA","NA","纳米比亚共和国"),
  			NEW_CALEDONIA("NEW_CALEDONIA","NC","新喀里多尼亚"),
  			NIGER_REPUBLIC("NIGER_REPUBLIC","NE","尼日尔共和国"),
  			NORFOLK_ISLANDS("NORFOLK_ISLANDS","NF","诺福克岛"),
  			NIGERIA("NIGERIA","NG","尼日利亚联邦共和国"),
  			NICARAGUA("NICARAGUA","NI","尼加拉瓜共和国"),
  			NETHERLANDS("NETHERLANDS","NL","荷兰王国"),
  			NORWAY("NORWAY","NO","挪威王国"),
  			NEPAL("NEPAL","NP","尼泊尔王国"),
  			NAURU_ISLANDS("NAURU_ISLANDS","NR","瑙鲁共和国"),
  			NIUE("NIUE","NU","纽埃"),
  			NEW_ZEALAND("NEW_ZEALAND","NZ","新西兰"),
  			OMAN_SULTANATE_OF("OMAN_SULTANATE_OF","OM","阿曼苏丹国"),
  			PANAMA_REPUBLIC_OF("PANAMA_REPUBLIC_OF","PA","巴拿马共和国"),
  			PERU("PERU","PE","秘鲁共和国"),
  			FRENCH_POLYNESIA("FRENCH_POLYNESIA","PF","法属波利尼西亚"),
  			PAPUA_NEW_GUINEA("PAPUA_NEW_GUINEA","PG","巴布亚新几内亚独立国"),
  			PHILLIPPINES("PHILLIPPINES","PH","菲律宾共和国"),
  			PAKISTAN("PAKISTAN","PK","巴基斯坦伊斯兰共和国"),
  			POLAND("POLAND","PL","波兰共和国"),
  			ST_PIERRE_AND_MIQUELON("ST_PIERRE_AND_MIQUELON","PM","圣皮埃尔和密克隆"),
  			PITCAIRN_ISLANDS("PITCAIRN_ISLANDS","PN","皮特凯恩"),
  			PUERTO_RICO("PUERTO_RICO","PR","波多黎各"),
  			PALESTINE("PALESTINE","PS","巴勒斯坦国"),
  			PORTUGAL("PORTUGAL","PT","葡萄牙共和国"),
  			PALAU("PALAU","PW","帕劳共和国"),
  			PARAGUAY("PARAGUAY","PY","巴拉圭共和国"),
  			QATAR_STATE_OF("QATAR_STATE_OF","QA","卡塔尔国"),
  			REUNION("REUNION","RE","留尼汪"),
  			South_Korea("South_Korea","RK","韩国"),
  			ROMANIA("ROMANIA","RO","罗马尼亚"),
  			RUSSIA("RUSSIA","RU","俄罗斯联邦"),
  			RWANDA("RWANDA","RW","卢旺达共和国"),
  			SAUDI_ARABIA("SAUDI_ARABIA","SA","沙特阿拉伯王国"),
  			SOLOMON_ISLAND("SOLOMON_ISLAND","SB","所罗门群岛"),
  			SEYCHELLES("SEYCHELLES","SC","塞舌尔共和国"),
  			SUDAN("SUDAN","SD","苏丹共和国"),
  			SWEDEN("SWEDEN","SE","瑞典王国"),
  			SINGAPORE("SINGAPORE","SG","新加坡共和国"),
  			SAINT_HELENA("SAINT_HELENA","SH","圣赫勒拿"),
  			SLOVENIA("SLOVENIA","SI","斯洛文尼亚共和国"),
  			SVALBARD_AND_JAN_MAYAN("SVALBARD_AND_JAN_MAYAN","SJ","斯瓦尔巴岛和扬马延岛"),
  			SLOVAKIA("SLOVAKIA","SK","斯洛伐克共和国"),
  			SIERRA_LEONE("SIERRA_LEONE","SL","塞拉利昂共和国"),
  			SAN_MARINO("SAN_MARINO","SM","圣马力诺共和国"),
  			SENEGAL("SENEGAL","SN","塞内加尔共和国"),
  			SOMALIA("SOMALIA","SO","索马里共和国"),
  			SURINAME("SURINAME","SR","苏里南共和国"),
  			SAO_TOME_AND_PRINCIPE_REPUBLIC_OF("SAO_TOME_AND_PRINCIPE_REPUBLIC_OF","ST","圣多美和普林西比民主共和国"),
  			EI_SALVADOR("EI_SALVADOR","SV","萨尔瓦多共和国"),
  			SYRIA("SYRIA","SY","阿拉伯叙利亚共和国"),
  			SWAZILAND("SWAZILAND","SZ","斯威士兰王国"),
  			TURKS_CAICOS_ISLANDS("TURKS_CAICOS_ISLANDS","TC","特克斯和凯科斯群岛"),
  			CHAD("CHAD","TD","乍得共和国"),
  			FRENCH_SOUTHERN_ANDANTARCTIC_LANDS("FRENCH_SOUTHERN_ANDANTARCTIC_LANDS","TF","法属南部领地"),
  			TOGO("TOGO","TG","多哥共和国"),
  			THAILAND("THAILAND","TH","泰王国"),
  			TADJIKSTAN("TADJIKSTAN","TJ","塔吉克斯坦共和国"),
  			TOKELAU("TOKELAU","TK","托克劳"),
  			TURKMENISTAN("TURKMENISTAN","TM","土库曼斯坦"),
  			TUNISIA("TUNISIA","TN","突尼斯共和国"),
  			TONGA("TONGA","TO","汤加王国"),
  			EAST_TIMOR("EAST_TIMOR","TP","东帝汶"),
  			TURKEY("TURKEY","TR","土耳其共和国"),
  			TRINIDAD_AND_TOBAGO("TRINIDAD_AND_TOBAGO","TT","特立尼达和多巴哥共和国"),
  			TUVALU("TUVALU","TV","图瓦卢"),
  			TAIWAN("TAIWAN","TW","中国台湾"),
  			TANZANIA("TANZANIA","TZ","坦桑尼亚联合共和国"),
  			UKRAINE("UKRAINE","UA","乌克兰"),
  			UGANDA("UGANDA","UG","乌干达共和国"),
  			UNITED_STATES_MINOR_OUTLYING_ISLANDS("UNITED_STATES_MINOR_OUTLYING_ISLANDS","UM","美国本土外小岛屿"),
  			UNITED_STATES_OF_AMERICA("UNITED_STATES_OF_AMERICA","US","美利坚合众国"),
  			URUGUAY("URUGUAY","UY","乌拉圭东岸共和国"),
  			UZBEKISTAN("UZBEKISTAN","UZ","乌兹别克斯坦共和国"),
  			VATICAN_CITY("VATICAN_CITY","VA","梵蒂冈城国"),
  			ST_VINCENT("ST_VINCENT","VC","圣文森特和格林纳丁斯"),
  			VENEZUELA("VENEZUELA","VE","委内瑞拉共和国"),
  			TORTOLA_BRITISH_VIRGIN_ISLANDS("TORTOLA_BRITISH_VIRGIN_ISLANDS","VG","英属维尔京群岛"),
  			VIRGIN_ISLANDS_OF_THE_USA("VIRGIN_ISLANDS_OF_THE_USA","VI","美属维尔京群岛"),
  			VIETNAM ("VIETNAM ","VN","越南社会主义共和国"),
  			VANUATU("VANUATU","VU","瓦努阿图共和国"),
  			WALLIS_AND_FUTUNA("WALLIS_AND_FUTUNA","WF","瓦利斯和富图纳"),
  			SAMOA("SAMOA","WS","萨摩亚独立国"),
  			YEMAN("YEMAN","YE","也门共和国"),
  			MAYOTTE("MAYOTTE","YT","马约特"),
  			YOGOSLAVIA("YOGOSLAVIA","YU","南斯拉夫联盟共和国"),
  			SOUTH_AFRICA("SOUTH_AFRICA","ZA","南非共和国"),
  			ZAMBIA("ZAMBIA","ZM","赞比亚共和国"),
  			ZIMBABWE("ZIMBABWE","ZW","津巴布韦共和国"),
  	;
  	private Nationality(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "Nationality";
	public final static String enumDesc = "国籍";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static Nationality toEnum(String value){
	  for(EnumType.Nationality item :EnumType.Nationality.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum EntScale {
  			more_than_3000("more_than_3000","1","3000人以上"),
  			between_1000_2999("between_1000_2999","2","1000-2999人"),
  			between_500_999("between_500_999","3","500-999人"),
  			between_100_499("between_100_499","4","100-499人"),
  			between_10_99("between_10_99","5","10-99人"),
  			less_than_10("less_than_10","6","10人以下"),
  	;
  	private EntScale(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "EntScale";
	public final static String enumDesc = "企业规模";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static EntScale toEnum(String value){
	  for(EnumType.EntScale item :EnumType.EntScale.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum SaleChannel {
  			group("group","01","团险直销"),
  			individual("individual","02","个人代理"),
  			bank("bank","03","银行代理"),
  			professional("professional","05","专业代理"),
  			sideline("sideline","06","兼业代理"),
  			federal_agency("federal_agency","07","联办代理"),
  			brokerage("brokerage","08","经纪业务"),
  			zongkuo_channel("zongkuo_channel","09","综拓渠道"),
  			shouzhan_business("shouzhan_business","10","收展业务"),
  			economic_agent("economic_agent","11","经纪代理"),
  			personal_sales("personal_sales","12","个人营销"),
  			tele_marketing("tele_marketing","13","电销"),
  			internet("internet","14","网销"),
  			other("other","99","其他"),
  	;
  	private SaleChannel(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "SaleChannel";
	public final static String enumDesc = "渠道";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static SaleChannel toEnum(String value){
	  for(EnumType.SaleChannel item :EnumType.SaleChannel.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum loopType {
  			daily("daily","01","每天"),
  			weekly("weekly","02","每周"),
  			monthly("monthly","03","每月"),
  			quarterly("quarterly","04","每季"),
  			semiannually("semiannually","05","每半年"),
  			yearly("yearly","06","每年"),
  	;
  	private loopType(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "loopType";
	public final static String enumDesc = "循环类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static loopType toEnum(String value){
	  for(EnumType.loopType item :EnumType.loopType.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum AddrTyp {
  			home_addr("home_addr","0","居住地址"),
  			company_addr("company_addr","1","单位地址"),
  			domicile_place("domicile_place","2","户口地址"),
  	;
  	private AddrTyp(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "AddrTyp";
	public final static String enumDesc = "地址类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static AddrTyp toEnum(String value){
	  for(EnumType.AddrTyp item :EnumType.AddrTyp.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum dealAction {
  			importCust("importCust","01","导入"),
  			newCust("newCust","02","新建"),
  			distribute("distribute","03","分配"),
  			deliver("deliver","04","转交"),
  	;
  	private dealAction(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "dealAction";
	public final static String enumDesc = "处理动作";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static dealAction toEnum(String value){
	  for(EnumType.dealAction item :EnumType.dealAction.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum CalSts {
  			doing("doing","0","未完成"),
  			done("done","1","完成"),
  	;
  	private CalSts(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "CalSts";
	public final static String enumDesc = "日程状态";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static CalSts toEnum(String value){
	  for(EnumType.CalSts item :EnumType.CalSts.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum ChannelType {
  			vehicle_interconnection("vehicle_interconnection","9999992011","车联网-CX团队"),
  			online_retailers("online_retailers","9999992012","电商-DS团队"),
  			liaoning_branch("liaoning_branch","9999992041","辽分-SY团队1"),
  			liaoning_branch21("liaoning_branch21","21","辽分-21开头所有机构"),
  			ahi_manage("ahi_manage","9999992009","意健险管理部(大健康)-YJX团队"),
  			yingkou_company("yingkou_company","9999992005","营总-YZ团队4"),
  			yingkou_company1("yingkou_company1","9999992002","营总-团队1"),
  	;
  	private ChannelType(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "ChannelType";
	public final static String enumDesc = "业务渠道";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static ChannelType toEnum(String value){
	  for(EnumType.ChannelType item :EnumType.ChannelType.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum GroupTaskStat {
  			executing("executing","0","进行中"),
  			completed("completed","1","已完成"),
  			cancel("cancel","2","取消"),
  	;
  	private GroupTaskStat(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "GroupTaskStat";
	public final static String enumDesc = "群组任务状态";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static GroupTaskStat toEnum(String value){
	  for(EnumType.GroupTaskStat item :EnumType.GroupTaskStat.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum EntIDType {
  			Z21("Z21","21","组织机构代码"),
  			Z22("Z22","22","营业执照"),
  			Z23("Z23","23","登记证书"),
  			Z24("Z24","24","批文或批复"),
  			Z25("Z25","25","开户证明"),
  			Z26("Z26","26","企业名称预先核准通知书或批文"),
  			Z27("Z27","27","外汇许可证号码"),
  			Z28("Z28","28","国税税务登记证号"),
  			Z29("Z29","29","地税税务登记证号"),
  			Z2a("Z2a","2a","基本账户开户许可证核准号"),
  			Z2b("Z2b","2b","对外贸易备案登记证"),
  			Z2c("Z2c","2c","台港澳侨企业批准证书"),
  			Z2d("Z2d","2d","进出口企业资格证书"),
  			Z2e("Z2e","2e","外资企业外汇登记证"),
  			Z2X("Z2X","2X","其他"),
  	;
  	private EntIDType(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "EntIDType";
	public final static String enumDesc = "企业证件类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static EntIDType toEnum(String value){
	  for(EnumType.EntIDType item :EnumType.EntIDType.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum YesNoFlg {
  			no("no","0","否"),
  			yes("yes","1","是"),
  	;
  	private YesNoFlg(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "YesNoFlg";
	public final static String enumDesc = "是否标识";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static YesNoFlg toEnum(String value){
	  for(EnumType.YesNoFlg item :EnumType.YesNoFlg.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum BlacklistStatus {
  			normal("normal","0","正常"),
  			blacklist("blacklist","1","黑名单"),
  	;
  	private BlacklistStatus(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "BlacklistStatus";
	public final static String enumDesc = "黑名单状态";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static BlacklistStatus toEnum(String value){
	  for(EnumType.BlacklistStatus item :EnumType.BlacklistStatus.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum EventType {
  			renewal_insurance("renewal_insurance","6","续保"),
  			bad_weather("bad_weather","7","恶劣天气"),
  			claim("claim","0","理赔事故"),
  			renewal_due_date("renewal_due_date","1","续保到期日"),
  			birthday("birthday","2","生日"),
  			festival("festival","3","节日"),
  			memorial_day("memorial_day","4","纪念日"),
  			other("other","5","其他"),
  	;
  	private EventType(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "EventType";
	public final static String enumDesc = "事件类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static EventType toEnum(String value){
	  for(EnumType.EventType item :EnumType.EventType.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum BusinessType {
  			ZC31("ZC31","C31","非金属矿物制品业"),
  			ZC32("ZC32","C32","黑色金属冶炼及压延加工业"),
  			ZC34("ZC34","C34","金属制品业"),
  			ZC36("ZC36","C36","专用设备制造业"),
  			ZC37("ZC37","C37","交通运输设备制造业"),
  			ZC40("ZC40","C40","电气机械及器材制造业"),
  			ZC41("ZC41","C41","电子及通信设备制造业"),
  			ZC42("ZC42","C42","仪器仪表及文化、办公用机械制造业"),
  			ZD44("ZD44","D44","电力、蒸汽、热水的生产和供应业"),
  			ZD46("ZD46","D46","自来水的生产和供应业"),
  			ZE47("ZE47","E47","土木工程建筑业"),
  			ZE48("ZE48","E48","线路、管道和设备安装业"),
  			ZEAA("ZEAA","EAA","瓷砖"),
  			ZF50("ZF50","F50","地质勘查业"),
  			ZF51("ZF51","F51","水利管理业"),
  			ZG53("ZG53","G53","公路运输业"),
  			ZG54("ZG54","G54","管道运输业"),
  			ZG56("ZG56","G56","航空运输业"),
  			ZG57("ZG57","G57","交通运输辅助业"),
  			ZG58("ZG58","G58","其他交通运输业"),
  			ZG60("ZG60","G60","邮电通信业"),
  			ZH61("ZH61","H61","食品、饮料、烟草和家庭用品批发业"),
  			ZH63("ZH63","H63","其他批发业"),
  			ZH65("ZH65","H65","商业经纪与代理业"),
  			ZH67("ZH67","H67","餐饮业"),
  			ZI70("ZI70","I70","保险业"),
  			ZJ72("ZJ72","J72","房地产开发与经营业"),
  			ZJ73("ZJ73","J73","房地产管理业"),
  			ZK75("ZK75","K75","公共设施服务业"),
  			ZK76("ZK76","K76","居民服务业"),
  			ZK79("ZK79","K79","租赁服务业"),
  			ZK80("ZK80","K80","旅游业"),
  			ZK82("ZK82","K82","信息、咨询服务业"),
  			ZK83("ZK83","K83","计算机应用服务业"),
  			ZL85("ZL85","L85","卫生"),
  			ZL86("ZL86","L86","体育"),
  			ZL87("ZL87","L87","社会福利保障业"),
  			ZM90("ZM90","M90","文化艺术业"),
  			ZM91("ZM91","M91","广播电影电视业"),
  			ZN93("ZN93","N93","综合技术服务业"),
  			ZO94("ZO94","O94","国家机关"),
  			ZC27("ZC27","C27","医药制造业"),
  			ZC28("ZC28","C28","化学纤维制造业"),
  			ZC30("ZC30","C30","塑料制品业"),
  			ZR04("ZR04","R04","制造业"),
  			ZR05("ZR05","R05","服务业"),
  			ZZ01("ZZ01","Z01","石油及天然气输送业"),
  			ZZ02("ZZ02","Z02","煤层气开采业"),
  			ZZ03("ZZ03","Z03","石油及天然气作业服务业"),
  			Z_2010("Z_2010","_2010","竹、滕、棕、草制品业"),
  			Z_3950("Z_3950","_3950","烟花、爆竹、炸药或其它爆炸品制造业"),
  			Z_5720("Z_5720","_5720","港口业"),
  			Z_5800("Z_5800","_5800","交通运输业"),
  			ZO96("ZO96","O96","社会团体"),
  			ZO97("ZO97","O97","基层群众自治组织"),
  			ZR01("ZR01","R01","律师"),
  			ZR02("ZR02","R02","注册会计师"),
  			Z00("Z00","00","未知"),
  			ZA01("ZA01","A01","农业"),
  			ZA02("ZA02","A02","林业"),
  			ZA03("ZA03","A03","畜牧业"),
  			ZA04("ZA04","A04","渔业"),
  			ZA05("ZA05","A05","农、林、牧、渔服务业"),
  			ZB06("ZB06","B06","煤炭采选业"),
  			ZB07("ZB07","B07","石油和天然气开采业"),
  			ZB08("ZB08","B08","黑色金属矿采选业"),
  			ZB09("ZB09","B09","有色金属矿采选业"),
  			ZB10("ZB10","B10","非金属矿采选业"),
  			ZB11("ZB11","B11","其他矿采选业"),
  			ZB12("ZB12","B12","木材及竹材采运业"),
  			ZC13("ZC13","C13","食品加工业"),
  			ZC14("ZC14","C14","食品制造业"),
  			ZC15("ZC15","C15","饮料制造业"),
  			ZC16("ZC16","C16","烟草加工业"),
  			ZC17("ZC17","C17","纺织业"),
  			ZC18("ZC18","C18","服装及其他纤维制品制造业"),
  			ZC19("ZC19","C19","皮革、毛皮、羽绒及其制品业"),
  			ZC20("ZC20","C20","木材加工及竹、藤、棕、草制品业"),
  			ZC21("ZC21","C21","家具制造业"),
  			ZC22("ZC22","C22","造纸及纸制品业"),
  			ZC23("ZC23","C23","印刷业、记录媒介的复制"),
  			ZC24("ZC24","C24","文教体育用品制造业"),
  			ZC25("ZC25","C25","石油加工及炼焦业"),
  			ZC26("ZC26","C26","化学原料及化学制品制造业"),
  			ZC29("ZC29","C29","橡胶制品业"),
  			ZC33("ZC33","C33","有色金属冶炼及压延加工业"),
  			ZC35("ZC35","C35","普通机械制造业"),
  			ZC39("ZC39","C39","武器弹药制造业"),
  			ZC43("ZC43","C43","其他制造业"),
  			ZD45("ZD45","D45","煤气生产和供应业"),
  			ZDAA("ZDAA","DAA","水电站"),
  			ZE49("ZE49","E49","装修装饰业"),
  			ZEAB("ZEAB","EAB","陶瓷业"),
  			ZG52("ZG52","G52","铁路运输业"),
  			ZG55("ZG55","G55","水上运输业"),
  			ZG59("ZG59","G59","仓储业"),
  			ZH62("ZH62","H62","能源、材料和机械电子设备批发业"),
  			ZH64("ZH64","H64","零售业"),
  			ZI68("ZI68","I68","金融业"),
  			ZJ74("ZJ74","J74","房地产经纪与代理业"),
  			ZK78("ZK78","K78","旅馆业"),
  			ZK81("ZK81","K81","娱乐服务业"),
  			ZK84("ZK84","K84","其他社会服务业"),
  			ZM89("ZM89","M89","教育"),
  			ZN92("ZN92","N92","科学研究业"),
  			ZO95("ZO95","O95","政党机关"),
  			ZP99("ZP99","P99","其他行业"),
  			ZR03("ZR03","R03","医生"),
  			ZR06("ZR06","R06","无职业"),
  			Z_1110("Z_1110","_1110","采石业及挖掘业"),
  			Z_4101("Z_4101","_4101","广播器材制造业"),
  			Z_4901("Z_4901","_4901","建筑材料业"),
  			Z_7300("Z_7300","_7300","物业管理"),
  	;
  	private BusinessType(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "BusinessType";
	public final static String enumDesc = "行业类别";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static BusinessType toEnum(String value){
	  for(EnumType.BusinessType item :EnumType.BusinessType.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum CustStat {
  			normal("normal","0","正常"),
  			cancel("cancel","1","注销"),
  			merged("merged","2","被合并"),
  			split("split","3","被拆分"),
  			wait_for_merge("wait_for_merge","4","待合并"),
  	;
  	private CustStat(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "CustStat";
	public final static String enumDesc = "客户状态";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static CustStat toEnum(String value){
	  for(EnumType.CustStat item :EnumType.CustStat.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum retVisitSts {
  			wait_proces("wait_proces","0","待处理"),
  			unThrough("unThrough","1","未接通"),
  			done("done","2","已完成"),
  			canceled("canceled","3","已取消"),
  	;
  	private retVisitSts(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "retVisitSts";
	public final static String enumDesc = "回访状态";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static retVisitSts toEnum(String value){
	  for(EnumType.retVisitSts item :EnumType.retVisitSts.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum TrackSubType {
  			manual_add_tag("manual_add_tag","11","手动打标签"),
  			manual_del_tag("manual_del_tag","12","手动删标签"),
  			auto_add_tag("auto_add_tag","13","自动打标签"),
  			agent_change("agent_change","14","代理人变更"),
  			task_create("task_create","21","任务创建"),
  			task_finish("task_finish","22","任务完成"),
  			task_cancel("task_cancel","23","任务取消"),
  			task_reopen("task_reopen","24","任务重新打开"),
  			SMS_push("SMS_push","31","短信推送"),
  			email_push("email_push","32","邮件推送"),
  			return_visit_tel("return_visit_tel","41","回访电话"),
  			marketing_tel("marketing_tel","42","营销电话"),
  			cust_complain("cust_complain","52","客户投诉"),
  			cust_feedback("cust_feedback","53","客户反馈"),
  			cust_consulting("cust_consulting","54","客户咨询"),
  			buy_proposal("buy_proposal","55","购买保单"),
  			pay_premium("pay_premium","56","缴纳保费"),
  			endorse_item("endorse_item","57","保全项目"),
  			call_on("call_on","59","上门拜访"),
  			activity_signup("activity_signup","61","活动报名"),
  			join_activity("join_activity","62","活动参加"),
  			service_order("service_order","63","服务预约"),
  			cust_report_case("cust_report_case","51","客户报案"),
  			claim_closed("claim_closed","58","理赔结案"),
  	;
  	private TrackSubType(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "TrackSubType";
	public final static String enumDesc = "轨迹详细类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static TrackSubType toEnum(String value){
	  for(EnumType.TrackSubType item :EnumType.TrackSubType.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum SimCustJudeParam {
  			custName("custName","0","客户姓名"),
  			sex("sex","1","性别"),
  			certTyp("certTyp","3","证件类型"),
  			certNo("certNo","4","证件号码"),
  			phoneNo1("phoneNo1","5","手机号码"),
  			carNo("carNo","6","车牌号码"),
  			homeTel("homeTel","7","家庭电话"),
  			detAddr("detAddr","8","详细地址"),
  			birthDate("birthDate","2","出生日期"),
  	;
  	private SimCustJudeParam(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "SimCustJudeParam";
	public final static String enumDesc = "相似客户判断参数";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static SimCustJudeParam toEnum(String value){
	  for(EnumType.SimCustJudeParam item :EnumType.SimCustJudeParam.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum FeedbackType {
  			success("success","0","失败"),
  			fail("fail","1","成功"),
  	;
  	private FeedbackType(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "FeedbackType";
	public final static String enumDesc = "反馈类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static FeedbackType toEnum(String value){
	  for(EnumType.FeedbackType item :EnumType.FeedbackType.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum PoliticalStatus {
  			party_member("party_member","1","中共党员"),
  			league_member("league_member","2","中共团员"),
  			democrat("democrat","3","民主人士"),
  			masses("masses","4","群众"),
  			other("other","99","其它"),
  	;
  	private PoliticalStatus(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "PoliticalStatus";
	public final static String enumDesc = "政治面貌";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static PoliticalStatus toEnum(String value){
	  for(EnumType.PoliticalStatus item :EnumType.PoliticalStatus.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum saleChnl {
  			personal("personal","01","个人代理"),
  			facultative("facultative","02","兼业代理"),
  	;
  	private saleChnl(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "saleChnl";
	public final static String enumDesc = "销售渠道";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static saleChnl toEnum(String value){
	  for(EnumType.saleChnl item :EnumType.saleChnl.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum ComplainType {
  			not_satisfied_service("not_satisfied_service","01","不满服务"),
  			claim_slow("claim_slow","02","理赔进度慢"),
  			salesman_fraud("salesman_fraud","03","销售人员欺诈"),
  			crank_call("crank_call","04","受到电话骚扰"),
  			other("other","99","其他"),
  	;
  	private ComplainType(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "ComplainType";
	public final static String enumDesc = "投诉类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static ComplainType toEnum(String value){
	  for(EnumType.ComplainType item :EnumType.ComplainType.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum BizSiteOwner {
  			own("own","0","自有"),
  			lease("lease","1","租赁"),
  			other("other","2","其他"),
  	;
  	private BizSiteOwner(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "BizSiteOwner";
	public final static String enumDesc = "经营场所所有权";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static BizSiteOwner toEnum(String value){
	  for(EnumType.BizSiteOwner item :EnumType.BizSiteOwner.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum retVisitType {
  			proposal_finish("proposal_finish","01","投保完成"),
  			claim_finish("claim_finish","02","理赔完成"),
  			complt_task_finish("complt_task_finish","03","投诉任务完成"),
  			marketing("marketing","04","营销活动"),
  			other("other","99","其他"),
  	;
  	private retVisitType(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "retVisitType";
	public final static String enumDesc = "回访类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static retVisitType toEnum(String value){
	  for(EnumType.retVisitType item :EnumType.retVisitType.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum ApplyState {
  			apply("apply","0","申请"),
  			send_back("send_back","1","退回"),
  			merge("merge","2","合并"),
  			close("close","3","关闭"),
  			pending("pending","4","待处理"),
  			unDistribute("unDistribute","5","待分配"),
  	;
  	private ApplyState(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "ApplyState";
	public final static String enumDesc = "申请状态";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static ApplyState toEnum(String value){
	  for(EnumType.ApplyState item :EnumType.ApplyState.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum HealthCondition {
  			health("health","0","健康"),
  			well("well","1","良好"),
  			general("general","2","一般"),
  			worse("worse","3","较差"),
  			major_disease("major_disease","4","严重疾病"),
  			chronic_disease("chronic_disease","5","慢性疾病"),
  			disability("disability","6","身体残疾"),
  	;
  	private HealthCondition(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "HealthCondition";
	public final static String enumDesc = "健康情况";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static HealthCondition toEnum(String value){
	  for(EnumType.HealthCondition item :EnumType.HealthCondition.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum consultType {
  			product("product","01","产品"),
  			contract("contract","02","合同"),
  			payment("payment","03","缴费"),
  			marketing("marketing","04","营销活动"),
  			other("other","99","其他"),
  	;
  	private consultType(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "consultType";
	public final static String enumDesc = "咨询类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static consultType toEnum(String value){
	  for(EnumType.consultType item :EnumType.consultType.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum EstablishType {
  			establish("establish","1","直接创建"),
  			convert("convert","2","动态群转静态群"),
  			operate("operate","3","运营创建"),
  	;
  	private EstablishType(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "EstablishType";
	public final static String enumDesc = "群组创建类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static EstablishType toEnum(String value){
	  for(EnumType.EstablishType item :EnumType.EstablishType.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum CustType {
  			per_formal_cust("per_formal_cust","01","个人正式"),
  			ent_formal_cust("ent_formal_cust","02","企业正式"),
  			per_latent_cust("per_latent_cust","03","个人潜在"),
  			ent_latent_cust("ent_latent_cust","04","企业潜在"),
  			black_list_cust("black_list_cust","09","黑名单"),
  	;
  	private CustType(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "CustType";
	public final static String enumDesc = "客户类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static CustType toEnum(String value){
	  for(EnumType.CustType item :EnumType.CustType.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum SimCustState {
  			normal("normal","0","正常"),
  			wait_for_merge("wait_for_merge","1","待合并"),
  			merged("merged","2","已合并"),
  			splited("splited","4","已拆分"),
  			wait_for_split("wait_for_split","3","待拆分"),
  	;
  	private SimCustState(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "SimCustState";
	public final static String enumDesc = "相似客户状态";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static SimCustState toEnum(String value){
	  for(EnumType.SimCustState item :EnumType.SimCustState.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum UwState {
  			refuse("refuse","1","拒保"),
  			cancel("cancel","2","撤销"),
  			close("close","3","关闭"),
  			standard("standard","9","正常承保"),
  			float_up("float_up","s","上浮费率承保"),
  			float_down("float_down","x","下浮费率承保"),
  	;
  	private UwState(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "UwState";
	public final static String enumDesc = "核保状态";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static UwState toEnum(String value){
	  for(EnumType.UwState item :EnumType.UwState.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum importStsDetail {
  			fail("fail","0","失败"),
  			Success("Success","1","成功"),
  	;
  	private importStsDetail(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "importStsDetail";
	public final static String enumDesc = "导入状态";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static importStsDetail toEnum(String value){
	  for(EnumType.importStsDetail item :EnumType.importStsDetail.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum consultSts {
  			unAnswer("unAnswer","0","未答复"),
  			yetAnswer("yetAnswer","1","已答复"),
  			rising("rising","2","问题上升"),
  			canceled("canceled","3","已取消"),
  	;
  	private consultSts(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "consultSts";
	public final static String enumDesc = "咨询状态";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static consultSts toEnum(String value){
	  for(EnumType.consultSts item :EnumType.consultSts.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum ComplainStatus {
  			unsolved("unsolved","0","未解决"),
  			solved("solved","1","已解决"),
  			canceled("canceled","2","已取消"),
  	;
  	private ComplainStatus(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "ComplainStatus";
	public final static String enumDesc = "投诉状态";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static ComplainStatus toEnum(String value){
	  for(EnumType.ComplainStatus item :EnumType.ComplainStatus.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum DataAuthType {
  			userposi("userposi","02","等于登录人岗位"),
  			userdept("userdept","03","等于登录人部门"),
  			userenter("userenter","04","等于登录人机构"),
  			user("user","01","等于登录人"),
  	;
  	private DataAuthType(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "DataAuthType";
	public final static String enumDesc = "数据权限类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static DataAuthType toEnum(String value){
	  for(EnumType.DataAuthType item :EnumType.DataAuthType.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum FileKind {
  			audio("audio","0","音频文件"),
  			video("video","1","视频文件"),
  	;
  	private FileKind(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "FileKind";
	public final static String enumDesc = "音频视频文件种类";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static FileKind toEnum(String value){
	  for(EnumType.FileKind item :EnumType.FileKind.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum ContType {
  			person("person","1","个单"),
  			group("group","2","团单"),
  	;
  	private ContType(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "ContType";
	public final static String enumDesc = "保单类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static ContType toEnum(String value){
	  for(EnumType.ContType item :EnumType.ContType.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum MsgAttr {
  			user_msg("user_msg","0","用户消息"),
  			sys_msg("sys_msg","1","系统消息"),
  	;
  	private MsgAttr(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "MsgAttr";
	public final static String enumDesc = "消息属性";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static MsgAttr toEnum(String value){
	  for(EnumType.MsgAttr item :EnumType.MsgAttr.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum CaseType {
  			call_center("call_center","01","电话中心"),
  			online("online","02","在线"),
  			counter("counter","03","柜台"),
  	;
  	private CaseType(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "CaseType";
	public final static String enumDesc = "案件类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static CaseType toEnum(String value){
	  for(EnumType.CaseType item :EnumType.CaseType.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum ImportStatus {
  			import_finish("import_finish","0","导入完成"),
  			import_failure("import_failure","1","导入失败"),
  	;
  	private ImportStatus(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "ImportStatus";
	public final static String enumDesc = "导入状态";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static ImportStatus toEnum(String value){
	  for(EnumType.ImportStatus item :EnumType.ImportStatus.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum loopFlag {
  			no("no","0","不循环"),
  			yes("yes","1","循环"),
  	;
  	private loopFlag(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "loopFlag";
	public final static String enumDesc = "循环发送类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static loopFlag toEnum(String value){
	  for(EnumType.loopFlag item :EnumType.loopFlag.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum DemandType {
  			register("register","0","登记"),
  			life_insurance("life_insurance","01","寿险"),
  			accident_insurance("accident_insurance","02","意外险"),
  			health_insurance("health_insurance","03","健康险"),
  			medical_insurance("medical_insurance","04","医疗险"),
  			property_insurance("property_insurance","05","家财险"),
  			car_insurance("car_insurance","06","车险"),
  			following_up("following_up","1","跟进中"),
  			solve("solve","2","解决"),
  			no_solve("no_solve","3","未解决"),
  			other("other","99","其他"),
  	;
  	private DemandType(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "DemandType";
	public final static String enumDesc = "需求类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static DemandType toEnum(String value){
	  for(EnumType.DemandType item :EnumType.DemandType.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum TaskApplyTyp {
  			merge_apply("merge_apply","0","合并申请"),
  			split_apply("split_apply","1","拆分申请"),
  			close_apply("close_apply","2","关闭申请"),
  	;
  	private TaskApplyTyp(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "TaskApplyTyp";
	public final static String enumDesc = "任务申请类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static TaskApplyTyp toEnum(String value){
	  for(EnumType.TaskApplyTyp item :EnumType.TaskApplyTyp.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum chgReason {
  			leave("leave","01","离职"),
  			vacation("vacation","02","修假"),
  			transferPosition("transferPosition","03","调岗"),
  			force("force","04","强制分配"),
  			others("others","99","其他原因"),
  	;
  	private chgReason(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "chgReason";
	public final static String enumDesc = "变更原因";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static chgReason toEnum(String value){
	  for(EnumType.chgReason item :EnumType.chgReason.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum Nation {
  			Han("Han","01","汉族"),
  			Manchu("Manchu","02","满族"),
  			Hui("Hui","03","回族"),
  			Tibetan("Tibetan","04","藏族"),
  			Uyghur("Uyghur","05","维吾尔族"),
  			Miao("Miao","06","苗族"),
  			Yi("Yi","07","彝族"),
  			Zhuang("Zhuang","08","壮族"),
  			Buyei("Buyei","09","布依族"),
  			Korean("Korean","10","朝鲜族"),
  			Mongol("Mongol","11","蒙古族"),
  			Dong("Dong","12","侗族"),
  			Yao("Yao","13","瑶族"),
  			Bai("Bai","14","白族"),
  			Tujia("Tujia","15","土家族"),
  			Hani("Hani","16","哈尼族"),
  			Kazak("Kazak","17","哈萨克族"),
  			Dai("Dai","18","傣族"),
  			Li("Li","19","黎族"),
  			Lisu("Lisu","20","傈僳族"),
  			Wa("Wa","21","佤族"),
  			She("She","22","畲族"),
  			Gaoshan("Gaoshan","23","高山族"),
  			Lahu("Lahu","24","拉祜族"),
  			Sui("Sui","25","水族"),
  			Dongxiang("Dongxiang","26","东乡族"),
  			Naxi("Naxi","27","纳西族"),
  			Jingpo("Jingpo","28","景颇族"),
  			Kirgiz("Kirgiz","29","柯尔克孜族"),
  			Tu("Tu","30","土族"),
  			Daur("Daur","31","达翰尔族"),
  			Mulao("Mulao","32","仫佬族"),
  			Qiang("Qiang","33","羌族"),
  			Blang("Blang","34","布朗族"),
  			Salar("Salar","35","撒拉族"),
  			Maonan("Maonan","36","毛南族"),
  			Gelao("Gelao","37","仡佬族"),
  			Xibe("Xibe","38","锡伯族"),
  			Achang("Achang","39","阿昌族"),
  			Pumi("Pumi","40","普米族"),
  			Tajik("Tajik","41","塔吉克族"),
  			Nu("Nu","42","怒族"),
  			Uzbek("Uzbek","43","乌孜别克族"),
  			Russians("Russians","44","俄罗斯族"),
  			Ewenki("Ewenki","45","鄂温克族"),
  			Deang("Deang","46","德昂族"),
  			Bonan("Bonan","47","保安族"),
  			Yugur("Yugur","48","裕固族"),
  			Gin("Gin","49","京族"),
  			Tatar("Tatar","50","塔塔尔族"),
  			Derung("Derung","51","独龙族"),
  			Oroqen("Oroqen","52","鄂伦春族"),
  			Hezhen("Hezhen","53","赫哲族"),
  			Monba("Monba","54","门巴族"),
  			Lhoba("Lhoba","55","珞巴族"),
  			Jino("Jino","56","基诺族"),
  			Other_Nation("Other_Nation","57","其它族"),
  			Naturalization("Naturalization","58","入籍族"),
  	;
  	private Nation(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "Nation";
	public final static String enumDesc = "民族";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static Nation toEnum(String value){
	  for(EnumType.Nation item :EnumType.Nation.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum InteractionType {
  			return_visits("return_visits","0","回访"),
  			complain("complain","1","投诉"),
  			consult("consult","2","咨询"),
  	;
  	private InteractionType(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "InteractionType";
	public final static String enumDesc = "交互类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static InteractionType toEnum(String value){
	  for(EnumType.InteractionType item :EnumType.InteractionType.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum AccType {
  			accident("accident","1","意外"),
  			disease("disease","2","疾病"),
  	;
  	private AccType(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "AccType";
	public final static String enumDesc = "出险原因";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static AccType toEnum(String value){
	  for(EnumType.AccType item :EnumType.AccType.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum ValidFlg {
  			invalid("invalid","0","无效"),
  			valid("valid","1","有效"),
  	;
  	private ValidFlg(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "ValidFlg";
	public final static String enumDesc = "有效标识";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static ValidFlg toEnum(String value){
	  for(EnumType.ValidFlg item :EnumType.ValidFlg.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum Rating {
  			high("high","0","高"),
  			middle("middle","1","中"),
  			low("low","2","低"),
  	;
  	private Rating(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "Rating";
	public final static String enumDesc = "等级";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static Rating toEnum(String value){
	  for(EnumType.Rating item :EnumType.Rating.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum BusiOppStage {
  			intention("intention","01","意向"),
  			allot("allot","02","分配"),
  			follow_up("follow_up","03","跟进中"),
  			succeed("succeed","04","成功"),
  			failure("failure","05","失败"),
  			cancel("cancel","06","取消"),
  	;
  	private BusiOppStage(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "BusiOppStage";
	public final static String enumDesc = "商机阶段";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static BusiOppStage toEnum(String value){
	  for(EnumType.BusiOppStage item :EnumType.BusiOppStage.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum PosiType {
  			p001("p001","1","总经理"),
  			p010("p010","10","社保专员"),
  			p002("p002","2","分公司经理"),
  			p003("p003","3","财务部门经理"),
  			p004("p004","4","IT部门经理"),
  			p005("p005","5","坐席"),
  			p006("p006","6","核赔处处长"),
  			p007("p007","7","人事经理"),
  			p008("p008","8","人事专员"),
  			p009("p009","9","公积金专员"),
  	;
  	private PosiType(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "PosiType";
	public final static String enumDesc = "岗位类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static PosiType toEnum(String value){
	  for(EnumType.PosiType item :EnumType.PosiType.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum Relation {
  			unknown("unknown","99","未知"),
  			self("self","00","本人"),
  			husband("husband","01","丈夫"),
  			wife("wife","02","妻子"),
  			father("father","03","父亲"),
  			mother("mother","04","母亲"),
  			son("son","05","儿子"),
  			daughter("daughter","06","女儿"),
  			grandfather("grandfather","07","祖父"),
  			grandmother("grandmother","08","祖母"),
  			grandson("grandson","09","孙子"),
  			granddaughter("granddaughter","10","孙女"),
  			mothers_father("mothers_father","11","外祖父"),
  			mothers_mother("mothers_mother","12","外祖母"),
  			daughters_son("daughters_son","13","外孙"),
  			daughters_daughter("daughters_daughter","14","外孙女"),
  			elder_brother("elder_brother","15","哥哥"),
  			elder_sister("elder_sister","16","姐姐"),
  			younger_brother("younger_brother","17","弟弟"),
  			younger_sister("younger_sister","18","妹妹"),
  			father_in_law("father_in_law","19","公公"),
  			mother_in_law("mother_in_law","20","婆婆"),
  			wifes_father("wifes_father","21","岳父"),
  			wifes_mother("wifes_mother","22","岳母"),
  			daughter_in_law("daughter_in_law","23","儿媳"),
  			son_in_law("son_in_law","24","女婿"),
  			colleague("colleague","26","同事"),
  			friend("friend","27","朋友"),
  			employer("employer","28","雇主"),
  			employee("employee","29","雇员"),
  			other_relation("other_relation","30","其他"),
  			uncle("uncle","34","叔叔"),
  			mothers_brother("mothers_brother","35","舅舅"),
  			nephew("nephew","36","侄子"),
  			sisters_son("sisters_son","37","外甥"),
  			mothers_sisters_husband("mothers_sisters_husband","38","姨父"),
  			fathers_sisters_husband("fathers_sisters_husband","39","姑父"),
  			fathers_sister("fathers_sister","40","姑妈"),
  			mothers_sister("mothers_sister","41","姨妈"),
  			mothers_brothers_wife("mothers_brothers_wife","42","舅妈"),
  			aunt("aunt","43","婶婶"),
  			niece("niece","44","侄女"),
  			sisters_daughter("sisters_daughter","45","外甥女"),
  			qniece("qniece","46","妻-侄女"),
  			qnephew("qnephew","47","妻-侄子"),
  			qsisters_son("qsisters_son","48","妻-外甥"),
  			qsisters_daughter("qsisters_daughter","49","妻-外甥女"),
  	;
  	private Relation(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "Relation";
	public final static String enumDesc = "关系类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static Relation toEnum(String value){
	  for(EnumType.Relation item :EnumType.Relation.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum BelongChannel {
  			individual("individual","01","个人代理"),
  			group("group","02","团险"),
  			bank("bank","03","银行保险"),
  			tele_marketing("tele_marketing","05","电话营销"),
  			per_marketing("per_marketing","06","个人营销"),
  			internet("internet","07","网络销售"),
  			brokerage("brokerage","08","经代"),
  			other("other","99","其他"),
  	;
  	private BelongChannel(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "BelongChannel";
	public final static String enumDesc = "归属渠道";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static BelongChannel toEnum(String value){
	  for(EnumType.BelongChannel item :EnumType.BelongChannel.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum AppFlag {
  			application("application","0","投保"),
  			insured("insured","1","承保"),
  			terminate("terminate","4","终止"),
  	;
  	private AppFlag(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "AppFlag";
	public final static String enumDesc = "投保状态";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static AppFlag toEnum(String value){
	  for(EnumType.AppFlag item :EnumType.AppFlag.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum MergeSplitAction {
  			distribute("distribute","10","分配任务"),
  			merge_apply("merge_apply","01","合并申请"),
  			merge_approv_not_pass("merge_approv_not_pass","02","合并审批退回"),
  			merge_approv_pass("merge_approv_pass","03","合并成功"),
  			merge_task_clos("merge_task_clos","04","合并任务关闭"),
  			split_apply("split_apply","05","拆分申请"),
  			split_approv_not_pass("split_approv_not_pass","06","拆分审批退回"),
  			split_approv_pass("split_approv_pass","07","拆分成功"),
  			split_task_clos("split_task_clos","08","拆分任务关闭"),
  			generate("generate","09","生成任务"),
  			close_apply("close_apply","11","关闭申请"),
  			close_approve_not_pass("close_approve_not_pass","12","关闭申请退回"),
  	;
  	private MergeSplitAction(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "MergeSplitAction";
	public final static String enumDesc = "相似客户任务动作";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static MergeSplitAction toEnum(String value){
	  for(EnumType.MergeSplitAction item :EnumType.MergeSplitAction.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum AgentStat {
  			normal("normal","0","正常"),
  			turnover("turnover","1","离职"),
  	;
  	private AgentStat(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "AgentStat";
	public final static String enumDesc = "代理人状态";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static AgentStat toEnum(String value){
	  for(EnumType.AgentStat item :EnumType.AgentStat.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum PositionGrade {
  			F101("F101","F101","见习客户经理"),
  			F111("F111","F111","初级客户经理一档"),
  			F112("F112","F112","初级客户经理二档"),
  			F113("F113","F113","初级客户经理三档"),
  			F121("F121","F121","中级客户经理一档"),
  			F122("F122","F122","中级客户经理二档"),
  			F123("F123","F123","中级客户经理三档"),
  			F131("F131","F131","高级客户经理一档"),
  			F132("F132","F132","高级客户经理二档"),
  			F133("F133","F133","高级客户经理三档"),
  			F141("F141","F141","资深客户经理一档"),
  			F142("F142","F142","资深客户经理二档"),
  			F143("F143","F143","资深客户经理三档"),
  			F211("F211","F211","初级营业部经理一档"),
  			F212("F212","F212","初级营业部经理二档"),
  			F221("F221","F221","中级营业部经理一档"),
  			F222("F222","F222","中级营业部经理二档"),
  			F231("F231","F231","高级营业部经理一档"),
  			F232("F232","F232","高级营业部经理二档"),
  			F311("F311","F311","初级营业区总监"),
  			F321("F321","F321","中级营业区总监"),
  			F331("F331","F331","高级营业区总监"),
  			F341("F341","F341","资深营业区总监"),
  			F351("F351","F351","首席营业区总监"),
  	;
  	private PositionGrade(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "PositionGrade";
	public final static String enumDesc = "职级";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static PositionGrade toEnum(String value){
	  for(EnumType.PositionGrade item :EnumType.PositionGrade.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum FactorOperType {
  			not_equals("not_equals","!=","不等于"),
  			less_than("less_than","<","小于"),
  			not_greater_than("not_greater_than","<=","小于等于"),
  			equals("equals","==","等于"),
  			greater_than("greater_than",">","大于"),
  			not_less_than("not_less_than",">=","大于等于"),
  			contains("contains","contains","包含"),
  			matches("matches","matches","匹配"),
  			member_of("member_of","memberOf","集合于"),
  			not_contains("not_contains","not contains","不包含"),
  			not_matches("not_matches","not matches","不匹配"),
  			not_member_of("not_member_of","not memberOf","不集合于"),
  	;
  	private FactorOperType(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "FactorOperType";
	public final static String enumDesc = "因子操作类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static FactorOperType toEnum(String value){
	  for(EnumType.FactorOperType item :EnumType.FactorOperType.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum importSts {
  			allSuccess("allSuccess","0","全部成功"),
  			someSuccess("someSuccess","1","部分成功"),
  			noneSuccess("noneSuccess","2","全部失败"),
  	;
  	private importSts(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "importSts";
	public final static String enumDesc = "导入状态";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static importSts toEnum(String value){
	  for(EnumType.importSts item :EnumType.importSts.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum ImportObjType {
  			per_cust("per_cust","0","个人客户"),
  			ent_cust("ent_cust","1","企业客户"),
  			product("product","2","产品"),
  			blacklist("blacklist","3","黑名单"),
  	;
  	private ImportObjType(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "ImportObjType";
	public final static String enumDesc = "导入对象类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static ImportObjType toEnum(String value){
	  for(EnumType.ImportObjType item :EnumType.ImportObjType.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum VisibleRange {
  			all("all","0","全部"),
  			upper_lower_level("upper_lower_level","1","上下级"),
  	;
  	private VisibleRange(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "VisibleRange";
	public final static String enumDesc = "可见范围";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static VisibleRange toEnum(String value){
	  for(EnumType.VisibleRange item :EnumType.VisibleRange.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum EndorsementStatus {
  			confirm_valid("confirm_valid","0","确认生效"),
  			entering_done("entering_done","1","录入完成"),
  			apply_confirm("apply_confirm","2","申请确认"),
  			wait_entering("wait_entering","3","等待录入"),
  			overdue_cancel("overdue_cancel","4","逾期终止"),
  			approve_modify("approve_modify","5","复核修改"),
  			confirm_invalid("confirm_invalid","6","确认未生效"),
  			endorse_undo("endorse_undo","7","保全撤销"),
  			underwrite_cancel("underwrite_cancel","8","核保终止"),
  			approve_cancel("approve_cancel","9","复核终止"),
  			approve_pass("approve_pass","a","复核通过"),
  			endorse_rollback("endorse_rollback","b","保全回退"),
  			endorse_cancel("endorse_cancel","c","保全终止"),
  			forced_cancel("forced_cancel","d","强制终止"),
  			approve_forward("approve_forward","e","复核上报"),
  			approved_examine_pass("approved_examine_pass","f","审批通过"),
  			approved_examine_not_pass("approved_examine_not_pass","g","审批不通过"),
  	;
  	private EndorsementStatus(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "EndorsementStatus";
	public final static String enumDesc = "批改状态";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static EndorsementStatus toEnum(String value){
	  for(EnumType.EndorsementStatus item :EnumType.EndorsementStatus.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum EndorsementReasonNo {
  			income_decline("income_decline","01","收入下降"),
  			dire_need_money ("dire_need_money ","02","急需用钱"),
  			human_apply("human_apply","03","人情投保"),
  			service_ng("service_ng","04","服务不理想"),
  			dividend_ng("dividend_ng","05","分红不理想"),
  			safeguard_not_accord("safeguard_not_accord","06","保障不符合需求"),
  			incorrect_information("incorrect_information","07","资料填写有误"),
  			proposal_lose("proposal_lose","08","保单丢失"),
  			cust_info_change("cust_info_change","09","客户信息发生变化"),
  			other("other","99","其他"),
  	;
  	private EndorsementReasonNo(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "EndorsementReasonNo";
	public final static String enumDesc = "批改原因编码";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static EndorsementReasonNo toEnum(String value){
	  for(EnumType.EndorsementReasonNo item :EnumType.EndorsementReasonNo.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum InteracMode {
  			call_in("call_in","01","呼入电话"),
  			call_out("call_out","02","呼出电话"),
  			message("message","03","短信"),
  			email("email","04","邮件"),
  			fax("fax","05","传真"),
  			home_visits("home_visits","06","上门拜访"),
  			marketing("marketing","07","营销活动"),
  			other("other","99","其他"),
  	;
  	private InteracMode(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "InteracMode";
	public final static String enumDesc = "交互方式";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static InteracMode toEnum(String value){
	  for(EnumType.InteracMode item :EnumType.InteracMode.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum BlacklistCustType {
  			per("per","0","个人"),
  			ent("ent","1","企业"),
  	;
  	private BlacklistCustType(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "BlacklistCustType";
	public final static String enumDesc = "黑名单客户类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static BlacklistCustType toEnum(String value){
	  for(EnumType.BlacklistCustType item :EnumType.BlacklistCustType.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum BusiOppSrc {
  			marketing("marketing","01","营销活动"),
  			serve("serve","02","服务"),
  			direct_entry("direct_entry","03","直接录入"),
  			external_purchase("external_purchase","04","外部购买"),
  			other("other","99","其他"),
  	;
  	private BusiOppSrc(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "BusiOppSrc";
	public final static String enumDesc = "商机来源";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static BusiOppSrc toEnum(String value){
	  for(EnumType.BusiOppSrc item :EnumType.BusiOppSrc.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum LogType {
  			login("login","1","登录"),
  			logout("logout","2","退出"),
  	;
  	private LogType(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "LogType";
	public final static String enumDesc = "日志类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static LogType toEnum(String value){
	  for(EnumType.LogType item :EnumType.LogType.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum CalType {
  			tel("tel","0","电话"),
  			Email("Email","1","Email"),
  			meeting("meeting","2","会议"),
  			visit("visit","3","拜访"),
  			post("post","4","直邮"),
  			message("message","5","短信"),
  			other("other","6","其他"),
  	;
  	private CalType(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "CalType";
	public final static String enumDesc = "日程类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static CalType toEnum(String value){
	  for(EnumType.CalType item :EnumType.CalType.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum WorkNature {
  			part_time("part_time","0","兼职"),
  			full_time("full_time","1","专职"),
  	;
  	private WorkNature(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "WorkNature";
	public final static String enumDesc = "工作性质";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static WorkNature toEnum(String value){
	  for(EnumType.WorkNature item :EnumType.WorkNature.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum mailType {
  			privMail("privMail","1","私信"),
  			sysMail("sysMail","2","系统消息"),
  			sysNotic("sysNotic","3","系统公告"),
  	;
  	private mailType(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "mailType";
	public final static String enumDesc = "站内信类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static mailType toEnum(String value){
	  for(EnumType.mailType item :EnumType.mailType.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum FileType {
  			medical_record("medical_record","0","就诊记录"),
  			checkup_reporting("checkup_reporting","1","体检报告"),
  			other("other","2","其他"),
  	;
  	private FileType(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "FileType";
	public final static String enumDesc = "档案类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static FileType toEnum(String value){
	  for(EnumType.FileType item :EnumType.FileType.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum TrackType {
  			info_change("info_change","01","信息变更"),
  			about_task("about_task","02","任务相关"),
  			call_out("call_out","04","外呼事件"),
  			cust_event("cust_event","05","客户事件"),
  			activity_event("activity_event","06","活动事件"),
  			info_push("info_push","03","信息推送"),
  	;
  	private TrackType(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "TrackType";
	public final static String enumDesc = "轨迹类型";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static TrackType toEnum(String value){
	  for(EnumType.TrackType item :EnumType.TrackType.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum TaskStat {
  			in_progress("in_progress","1","进行中"),
  			done("done","2","已完成"),
  			cancel("cancel","3","取消"),
  			reopen("reopen","4","重新打开"),
  	;
  	private TaskStat(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "TaskStat";
	public final static String enumDesc = "任务状态";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static TaskStat toEnum(String value){
	  for(EnumType.TaskStat item :EnumType.TaskStat.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
  enum AnnualIncome {
  			no_income("no_income","0","无"),
  			less_than_5W("less_than_5W","1","5万以下"),
  			between_5_10W("between_5_10W","2","5-10万"),
  			between_10_20W("between_10_20W","3","10-20万"),
  			between_20_50W("between_20_50W","4","20-50万"),
  			between_50_100W("between_50_100W","5","50-100万"),
  			between_100_500W("between_100_500W","6","100-500万"),
  			more_than_500W("more_than_500W","7","500万以上"),
  	;
  	private AnnualIncome(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "AnnualIncome";
	public final static String enumDesc = "年收入";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static AnnualIncome toEnum(String value){
	  for(EnumType.AnnualIncome item :EnumType.AnnualIncome.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
}
