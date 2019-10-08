package cn.com.zsyk.crm.manage.entity.bo.engine;


import java.math.BigDecimal;
import java.util.Date;

public class RrmsResult {
    private String reqId;  //请求号

    private String seqNo;//批次单笔序号

    private String priority;//规则的优先级

    private String warnLevel;//预警级别

    private String channel;//交易渠道

    private String tranType;//交易类型

    private String operateCode;//处理方式编码

    private String operateDesc;//处置方式描述

    private Date  tradeTime;//交易时间

    private Date kafakaTime;//获取到KAFKA数据时间

    private Date stormTime;//STORM处理完成时间

    private Date timestamp;//发送KAFKA的时间戳

    private String strategyId;//触发策略的ID

    private String question;//人工核实相关问题

    private String answer;//人工核实相关答案

    private Boolean ifSuccess;//风控是否处理成功

    private String failReason;//风控处理失败原因

    private String accRstCode;//记账结果编码

    private String accRstDesc;//记账失败原因

    private Date accRstTime;//记账时间

    private Boolean isQueried;//风控结果是否被查询

    private String remark;//备注

    private String bak1;//备用1

    private String bak2;//备用2

    private String bak3;//备用3

    private String ip;//交易IP

    private Boolean curAuthMode;//当前认证方式

    private String rcvBank;//对手开户行名称

    private String rcvBranch;//对手开户行网点名称

    private String rcvName;//对手姓名

    private String rcvAccNo;//对手帐号

    private String sessionId;//会话ID

    private String trnType;//交易类型

    private BigDecimal trnAmt;//交易金额

    private Date trnTime;//交易时间

    private Date openAccTime;//开户时间

    private Boolean optAuthMode;//可选认证方式

    private String cstNo;//渠道客户号

    private BigDecimal sumMoney;//单渠道日累计交易金额

    private BigDecimal limitAmtDay;//单渠道日累计交易限额

    private BigDecimal limitAmt;//客户单笔交易限额

    private String term;//设备号MAC，手机唯一标识

    private BigDecimal freeAmt;//手续费

    private String ctfNo;//证件号码

    private String ctfType;//证件类型

    private String payerAcc;//转出帐号

    private BigDecimal payerBalance;//转出帐号余额

    private String coreCstNo;//核心客户号

    private Byte batchNum;//批次记录数

    private String termId;//终端标识

    private Boolean isSetQuestion;//是否设置私密问题

    private String phoneNo;//电话号码

    private String orgMessage; //原始报文

    private  String cstName;  //客户名称

    private String area;//地区的名字

    private String province;//省份的名字

    private String ifRelease;//是否特例放行

    private String terminal;

    private String postAction;//后置动作

    private String largeDelay;//大额顺延

    private String delayNo;//顺延流水号

    private String reactResultCode;//电话核实结果

    private String reactResultName;//电话核实结果

    private String isHit;//是否命中
    private String country;//国家

    private String isHitReal;

    private String strategyIdHit;

    public String getStrategyIdHit() {
        return strategyIdHit;
    }

    public void setStrategyIdHit(String strategyIdHit) {
        this.strategyIdHit = strategyIdHit;
    }



    public String getIsHitReal() {
        return isHitReal;
    }

    public void setIsHitReal(String isHitReal) {
        this.isHitReal = isHitReal;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIsHit() {
        return isHit;
    }

    public void setIsHit(String isHit) {
        this.isHit = isHit;
    }

    public String getLargeDelay() {
        return largeDelay;
    }

    public void setLargeDelay(String largeDelay) {
        this.largeDelay = largeDelay;
    }

    public String getDelayNo() {
        return delayNo;
    }

    public void setDelayNo(String delayNo) {
        this.delayNo = delayNo;
    }

    public String getReactResultCode() {
        return reactResultCode;
    }

    public void setReactResultCode(String reactResultCode) {
        this.reactResultCode = reactResultCode;
    }

    public String getReactResultName() {
        return reactResultName;
    }

    public void setReactResultName(String reactResultName) {
        this.reactResultName = reactResultName;
    }

    public Boolean getQueried() {
        return isQueried;
    }

    public void setQueried(Boolean queried) {
        isQueried = queried;
    }

    public Boolean getSetQuestion() {
        return isSetQuestion;
    }

    public void setSetQuestion(Boolean setQuestion) {
        isSetQuestion = setQuestion;
    }

    public String getPostAction() {
        return postAction;
    }

    public void setPostAction(String postAction) {
        this.postAction = postAction;
    }

    public String getCstName() {
        return cstName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setCstName(String cstName) {
        this.cstName = cstName;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId == null ? null : reqId.trim();
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority == null ? null : priority.trim();
    }

    public String getWarnLevel() {
        return warnLevel;
    }

    public void setWarnLevel(String warnLevel) {
        this.warnLevel = warnLevel == null ? null : warnLevel.trim();
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType == null ? null : tranType.trim();
    }

    public String getOperateCode() {
        return operateCode;
    }

    public void setOperateCode(String operateCode) {
        this.operateCode = operateCode == null ? null : operateCode.trim();
    }

    public String getOperateDesc() {
        return operateDesc;
    }

    public void setOperateDesc(String operateDesc) {
        this.operateDesc = operateDesc == null ? null : operateDesc.trim();
    }

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    public Date getKafakaTime() {
        return kafakaTime;
    }

    public void setKafakaTime(Date kafakaTime) {
        this.kafakaTime = kafakaTime;
    }

    public Date getStormTime() {
        return stormTime;
    }

    public void setStormTime(Date stormTime) {
        this.stormTime = stormTime;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(String strategyId) {
        this.strategyId = strategyId == null ? null : strategyId.trim();
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public Boolean getIfSuccess() {
        return ifSuccess;
    }

    public void setIfSuccess(Boolean ifSuccess) {
        this.ifSuccess = ifSuccess;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason == null ? null : failReason.trim();
    }

    public String getAccRstCode() {
        return accRstCode;
    }

    public void setAccRstCode(String accRstCode) {
        this.accRstCode = accRstCode == null ? null : accRstCode.trim();
    }

    public String getAccRstDesc() {
        return accRstDesc;
    }

    public void setAccRstDesc(String accRstDesc) {
        this.accRstDesc = accRstDesc == null ? null : accRstDesc.trim();
    }

    public Date getAccRstTime() {
        return accRstTime;
    }

    public void setAccRstTime(Date accRstTime) {
        this.accRstTime = accRstTime;
    }

    public Boolean getIsQueried() {
        return isQueried;
    }

    public void setIsQueried(Boolean isQueried) {
        this.isQueried = isQueried;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getBak1() {
        return bak1;
    }

    public void setBak1(String bak1) {
        this.bak1 = bak1 == null ? null : bak1.trim();
    }

    public String getBak2() {
        return bak2;
    }

    public void setBak2(String bak2) {
        this.bak2 = bak2 == null ? null : bak2.trim();
    }

    public String getBak3() {
        return bak3;
    }

    public void setBak3(String bak3) {
        this.bak3 = bak3 == null ? null : bak3.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Boolean getCurAuthMode() {
        return curAuthMode;
    }

    public void setCurAuthMode(Boolean curAuthMode) {
        this.curAuthMode = curAuthMode;
    }

    public String getRcvBank() {
        return rcvBank;
    }

    public void setRcvBank(String rcvBank) {
        this.rcvBank = rcvBank == null ? null : rcvBank.trim();
    }

    public String getRcvBranch() {
        return rcvBranch;
    }

    public void setRcvBranch(String rcvBranch) {
        this.rcvBranch = rcvBranch == null ? null : rcvBranch.trim();
    }

    public String getRcvName() {
        return rcvName;
    }

    public void setRcvName(String rcvName) {
        this.rcvName = rcvName == null ? null : rcvName.trim();
    }

    public String getRcvAccNo() {
        return rcvAccNo;
    }

    public void setRcvAccNo(String rcvAccNo) {
        this.rcvAccNo = rcvAccNo == null ? null : rcvAccNo.trim();
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId == null ? null : sessionId.trim();
    }

    public String getTrnType() {
        return trnType;
    }

    public void setTrnType(String trnType) {
        this.trnType = trnType == null ? null : trnType.trim();
    }

    public BigDecimal getTrnAmt() {
        return trnAmt;
    }

    public void setTrnAmt(BigDecimal trnAmt) {
        this.trnAmt = trnAmt;
    }

    public Date getTrnTime() {
        return trnTime;
    }

    public void setTrnTime(Date trnTime) {
        this.trnTime = trnTime;
    }

    public Date getOpenAccTime() {
        return openAccTime;
    }

    public void setOpenAccTime(Date openAccTime) {
        this.openAccTime = openAccTime;
    }

    public Boolean getOptAuthMode() {
        return optAuthMode;
    }

    public void setOptAuthMode(Boolean optAuthMode) {
        this.optAuthMode = optAuthMode;
    }

    public String getCstNo() {
        return cstNo;
    }

    public void setCstNo(String cstNo) {
        this.cstNo = cstNo == null ? null : cstNo.trim();
    }

    public BigDecimal getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(BigDecimal sumMoney) {
        this.sumMoney = sumMoney;
    }

    public BigDecimal getLimitAmtDay() {
        return limitAmtDay;
    }

    public void setLimitAmtDay(BigDecimal limitAmtDay) {
        this.limitAmtDay = limitAmtDay;
    }

    public BigDecimal getLimitAmt() {
        return limitAmt;
    }

    public void setLimitAmt(BigDecimal limitAmt) {
        this.limitAmt = limitAmt;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term == null ? null : term.trim();
    }

    public BigDecimal getFreeAmt() {
        return freeAmt;
    }

    public void setFreeAmt(BigDecimal freeAmt) {
        this.freeAmt = freeAmt;
    }

    public String getCtfNo() {
        return ctfNo;
    }

    public void setCtfNo(String ctfNo) {
        this.ctfNo = ctfNo == null ? null : ctfNo.trim();
    }

    public String getCtfType() {
        return ctfType;
    }

    public void setCtfType(String ctfType) {
        this.ctfType = ctfType == null ? null : ctfType.trim();
    }

    public String getPayerAcc() {
        return payerAcc;
    }

    public void setPayerAcc(String payerAcc) {
        this.payerAcc = payerAcc == null ? null : payerAcc.trim();
    }

    public BigDecimal getPayerBalance() {
        return payerBalance;
    }

    public void setPayerBalance(BigDecimal payerBalance) {
        this.payerBalance = payerBalance;
    }

    public String getCoreCstNo() {
        return coreCstNo;
    }

    public void setCoreCstNo(String coreCstNo) {
        this.coreCstNo = coreCstNo == null ? null : coreCstNo.trim();
    }

    public Byte getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(Byte batchNum) {
        this.batchNum = batchNum;
    }

    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId == null ? null : termId.trim();
    }

    public Boolean getIsSetQuestion() {
        return isSetQuestion;
    }

    public void setIsSetQuestion(Boolean isSetQuestion) {
        this.isSetQuestion = isSetQuestion;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo == null ? null : phoneNo.trim();
    }

    public String getOrgMessage() {
        return orgMessage;
    }

    public void setOrgMessage(String orgMessage) {
        this.orgMessage = orgMessage == null ? null : orgMessage.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getIfRelease() {
        return ifRelease;
    }

    public void setIfRelease(String ifRelease) {
        this.ifRelease = ifRelease;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }
}