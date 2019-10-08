package cn.com.zsyk.crm.manage.entity.bo.engine;


/**
 * Author: qijp@asiainfo.com
 * Date: 2016/8/4
 * Time: 14:49
 * Desc: 描述
 */
public enum RunningStatus {

    PRETRAIN("0"),//待训练

    TRAINING("1"),//训练中

    TRAINSUCCESS("2"),//训练成功

    TRAINFAIL("3"),//训练失败

    ONLINE("4"),//上线

    OFFLINE("5");//下线

    private String code;

    private RunningStatus(String code){
        this.code = code;
    }

    public String getCode(){
        return this.code;
    }
}
