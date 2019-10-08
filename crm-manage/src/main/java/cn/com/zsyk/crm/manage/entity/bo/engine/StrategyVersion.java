package cn.com.zsyk.crm.manage.entity.bo.engine;


/**
 * Author: qijp@asiainfo.com
 * Date: 2016/8/23
 * Time: 9:48
 * Desc: 描述
 */
public enum StrategyVersion {
    A("A"),B("B");
    private String code;
    private StrategyVersion(String code){
        this.code = code;
    }
    public String getcode(){
        return this.code;
    }
}
