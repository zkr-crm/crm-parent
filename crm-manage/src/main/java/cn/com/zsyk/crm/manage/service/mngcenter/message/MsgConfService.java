package cn.com.zsyk.crm.manage.service.mngcenter.message;

import cn.com.zsyk.crm.common.dao.AbstractDao;
import cn.com.zsyk.crm.common.dao.CoreDaoImpl;
import cn.com.zsyk.crm.common.dto.PageBean;
import cn.com.zsyk.crm.common.util.IdGenerator;
import cn.com.zsyk.crm.manage.entity.SysMsgConf;
import cn.com.zsyk.crm.manage.mapper.SysMsgConfMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MsgConfService {

    @Autowired
    private MsgTemplateService msgTemplateService;
    @Autowired
    private AbstractDao daoUtil;
    @Autowired
    private SysMsgConfMapper sysMsgConfMapper;
    @Autowired
    private CoreDaoImpl coreDaoImpl;
    /**
     * 根据输入参数获取所有短信定义信息[定时发送]
     *
     * @param msgInfo
     *            短信定义表对象
     *
     * @return 短信定义信息表表对象
     */
    public PageBean getMsgConfByEntity(SysMsgConf msgInfo) {

        List<SysMsgConf> infoList = daoUtil.selectList("cn.com.zsyk.crm.manage.mapper.SysMsgConfMapper.getMsgConfByEntity", msgInfo);
        PageBean pageRetlst = coreDaoImpl.selectPageByMapper(sysMsgConfMapper,"getMsgConfByEntity",msgInfo);
        return pageRetlst;
    }
    /**
     * 新增一条短信自动发送信息
     * @param msg
     * @return 新增成功的记录条数
     */
    public int addMsgConf(SysMsgConf msg) {

        //生成规则ID
        Long msgId = IdGenerator.getSeqID("MsgConf");
        msg.setMsgId("Mf_"+msgId.toString());
        int addCount = sysMsgConfMapper.insert(msg);
        return addCount;
    }
    /**
     * 修改一条短信定义信息表信息
     *
     * @param msgId
     *            短信定义信息表
     * @return 修改成功的记录条数
     */
    public int updMsgConf(SysMsgConf msgId) {

        int updMsg = sysMsgConfMapper.updateByPrimaryKey(msgId);
        return updMsg;
    }
    /**
     * 根据主键物理删除某提醒定义短信表信息的方法
     *
     * @param msgId
     *            规则ID
     * @return
     */
    public int deleteByPrimaryKey(String msgId) {

        int delCount = sysMsgConfMapper.deleteByPrimaryKey(msgId);

        return delCount;
    }


}
