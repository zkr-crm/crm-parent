package cn.com.zsyk.crm.ecif.bo.cust;

import cn.com.zsyk.crm.ecif.entity.EcSimTask;
import lombok.Data;

/**
 * 客户拆分申请查询客户姓名 手机号 实体类
 */
@Data
public class SimTaskList extends EcSimTask {

    private String clientName;
    private String clientPhone;

}
