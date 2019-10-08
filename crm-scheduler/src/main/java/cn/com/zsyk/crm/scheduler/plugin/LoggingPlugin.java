package cn.com.zsyk.crm.scheduler.plugin;

import org.quartz.plugins.history.LoggingTriggerHistoryPlugin;

public class LoggingPlugin extends LoggingTriggerHistoryPlugin {
    public LoggingPlugin() {
        super();
        this.setTriggerMisfiredMessage("【{5}】于【{4, date, HH:mm:ss MM/dd/yyyy}】错过执行， 最近一次调度时间【{3, date, HH:mm:ss MM/dd/yyyy}】");
    }
}
