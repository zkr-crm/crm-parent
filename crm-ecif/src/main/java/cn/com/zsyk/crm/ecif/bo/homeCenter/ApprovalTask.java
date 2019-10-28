package cn.com.zsyk.crm.ecif.bo.homeCenter;

import lombok.Getter;
import lombok.Setter;

/**
 * 审批任务返回前端类
 * author:xrl
 * 2019/08/06 08:55
 */
@Getter
@Setter
public class ApprovalTask {

    private String taskId;
    private String applyUser;
    private String createTime;
    private String applyTyp;

}
