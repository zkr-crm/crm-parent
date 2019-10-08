package cn.com.zsyk.crm.generator;

//${description}
public interface ${className} {
<#list enums?keys as key>
  enum ${key} {
  	<#list enums["${key}"] as item>
  		<#assign TYPE_DESC = item.TYPE_DESC/>
  		<#if item.CODE_NAME?? && item.CODE_NAME != "" >
  			${item.CODE_NAME}("${item.CODE_NAME}","${item.CODE_VAL}","${item.CODE_DESC}"),
  		</#if>
  	</#list>
  	;
  	private ${key}(String name, String value, String desc) {
			this.name = name;
			this.value = value;
			this.desc = desc;
	}
	
	public final String name;
	public final String value;
	public final String desc;
	
	public final static String enumId = "${key}";
	public final static String enumDesc = "${TYPE_DESC}";
	
	public String toString(){
        return this.value;
    }
	
	public String getValue(){
		return this.value;
	}
	
	public static ${key} toEnum(String value){
	  for(${className}.${key} item :${className}.${key}.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
	}
  }
</#list>
}
  