CREATE TABLE sys_id_sequence(
    biz_tag VARCHAR(50) COMMENT '业务标识',
    max_id BIGINT NOT NULL COMMENT '分配的id号段最大值',
    add_step BIGINT NOT NULL COMMENT '每次拉取的步长',
    last_time DATETIME COMMENT '上一次操作时间戳',
    time_stamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON  UPDATE  CURRENT_TIMESTAMP COMMENT '当前操作时间戳',
    PRIMARY KEY (biz_tag)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务ID自增序列，按1递增';

insert into sys_id_sequence(biz_tag,max_id,add_step,last_time)values('order', 0, 100, CURRENT_TIMESTAMP);
--sys_remind_rule表规则id生成
insert into sys_id_sequence(biz_tag,max_id,add_step,last_time)values('RemindDef', 0, 100, CURRENT_TIMESTAMP);
--sys_app_mail表信息id生成
insert into sys_id_sequence(biz_tag,max_id,add_step,last_time)values('SysMail', 0, 100, CURRENT_TIMESTAMP);
-- ec_track_info表规则id生成
insert into sys_id_sequence(biz_tag,max_id,add_step,last_time)values('TrackCd', 0, 100, CURRENT_TIMESTAMP);
-- ec_track_comt表规则id生成
insert into sys_id_sequence(biz_tag,max_id,add_step,last_time)values('TrackComtCd', 0, 100, CURRENT_TIMESTAMP);
-- ec_sim_task表任务id生成
insert into sys_id_sequence(biz_tag,max_id,add_step,last_time)values('ST', 0, 1, CURRENT_TIMESTAMP);
-- ec_cust_merge_rela 客户合并关系表合并id生成
insert into sys_id_sequence(biz_tag,max_id,add_step,last_time)values('CustMerge', 0, 100, CURRENT_TIMESTAMP);
-- ec_cust_split_rela 客户拆分关系表合并id生成
insert into sys_id_sequence(biz_tag,max_id,add_step,last_time)values('CustSplit', 0, 100, CURRENT_TIMESTAMP);
-- ec_cust_merge_apply 相似客户合并申请表申请id生成
insert into sys_id_sequence(biz_tag,max_id,add_step,last_time)values('MergeApply', 0, 1, CURRENT_TIMESTAMP);
-- ec_cust_split_apply 相似客户拆分申请表申请id生成
insert into sys_id_sequence(biz_tag,max_id,add_step,last_time)values('SplitApply', 0, 1, CURRENT_TIMESTAMP);
-- ec_audio_video表规则id生成
insert into sys_id_sequence(biz_tag,max_id,add_step,last_time)values('AudioVideoCd', 0, 100, CURRENT_TIMESTAMP);
-- oc_cust_grp表群组id生成
insert into sys_id_sequence(biz_tag,max_id,add_step,last_time)values('Grp', 0, 1, CURRENT_TIMESTAMP);
-- oc_group_oper表运营任务ID生成
insert into sys_id_sequence(biz_tag,max_id,add_step,last_time)values('GroupOper', 0, 1, CURRENT_TIMESTAMP);
-- oc_busi_opp表规则id生成
insert into sys_id_sequence(biz_tag,max_id,add_step,last_time)values('BusiOppNo', 0, 100, CURRENT_TIMESTAMP);
-- oc_dynam_snapshot客户群快照表快照ID生成
insert into sys_id_sequence(biz_tag,max_id,add_step,last_time)values('SnapshotId', 0, 1, CURRENT_TIMESTAMP);
-- oc_dynam_grp_rule_rela动态群组策略关联表ID生成
insert into sys_id_sequence(biz_tag,max_id,add_step,last_time)values('GrpRuleRelaId', 0, 1, CURRENT_TIMESTAMP);
-- oc_grp_cust_trace动态客群客户轨迹表ID生成
insert into sys_id_sequence(biz_tag,max_id,add_step,last_time)values('GrpCustTrace', 0, 1, CURRENT_TIMESTAMP);
-- ec_health_file表规则id生成
insert into sys_id_sequence(biz_tag,max_id,add_step,last_time)values('HealthFileCd', 0, 100, CURRENT_TIMESTAMP);
-- ec_black_list黑名单表规则id生成
insert into sys_id_sequence(biz_tag,max_id,add_step,last_time)values('BlackList', 0, 1, CURRENT_TIMESTAMP);
