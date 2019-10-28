package cn.com.zsyk.crm.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 系统操作日志注解
 * @author
 */
@Documented  
@Target({ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
public @interface SysOprtLog {  
    /**
     * 模块名称
     */
	Module model();  
    
    /**
     * 业务描述
     */
    String bizDesc() default "";  
    
    public enum Module{
		DEPT("1","部门管理"),ORGN("2","机构管理"),USER("3","用户管理"),USERAUTH("4","用户权限管理"),
		MENU("5","菜单管理"),MENUAUTH("6","菜单权限管理"),PERSTRUCAUTH("7","人事架构权限管理"),DATAAUTH("8","数据权限管理"),
		PARAMETER("9","参数管理"),CODEVALUE("10","码值管理"),CODEMAPPING("11","码值映射管理"),TEMPLATE("12","模板管理"),
		CUSTOMER("13","客户管理"),CUTMRELATION("14","客户关系管理"),MAINCUSTOMER("15","重点客户管理"),BLACKLIST("16","黑名单管理"),
		FACTOR("17","基本因子管理"),BASERULE("14","基本规则管理"),COMRULE("15","组合规则管理"),MODEL("20","模型管理"),BUSIOPP("21","商机管理");
    	
    	private final String value;
		private final String desc;
		
		private Module(String value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getValue() {
			return this.value;
		}
		public String desc() {
			return this.desc;
		}
		
		public static Module toEnum(String desc) {
			if (null == desc)
				throw new NullPointerException("desc is null");

			for(Module item : Module.values()) {
				if(item.desc.equals(desc)){
					return item;
				}
			}
			return null;
		}
		
		@Override
		public String toString() {
			return this.desc;
		}
	}
}  

