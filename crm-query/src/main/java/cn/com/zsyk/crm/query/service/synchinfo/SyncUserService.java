package cn.com.zsyk.crm.query.service.synchinfo;

import java.util.List;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;

import cn.com.zsyk.crm.common.util.DateUtil;
import cn.com.zsyk.crm.query.entity.GGUser;
import cn.com.zsyk.crm.query.entity.SysUserInfo;
import cn.com.zsyk.crm.query.mapper.GGUserMapper;

@Service
@Transactional
public class SyncUserService {

	@Autowired
	private GGUserMapper ggUserMapper;

	@Autowired
	private AmqpTemplate amqpTemplate;
	/**
	 * 全量同步用户信息
	 *
	 * @return int
	 */
	public int getAllUser() {
        List<GGUser> logList = ggUserMapper.selectAll();
		SysUserInfo sysUserInfo;
		for (GGUser gguser:logList) {
			System.out.println(gguser.getUsercode()+"-"+gguser.getUsercname());
			if("1".equals(gguser.getValidind())){
				sysUserInfo=new SysUserInfo();
				sysUserInfo.setUserId(gguser.getUsercode());
				sysUserInfo.setEmployeeId(gguser.getUsercode());
				sysUserInfo.setUserName(gguser.getUsercname());
				sysUserInfo.setPassword(gguser.getPassword());
				sysUserInfo.setEnterCode(gguser.getCompanycode());
				sysUserInfo.setDeptCode(gguser.getCompanycode());
				sysUserInfo.setCreateUser("admin");
				sysUserInfo.setCreateDate(DateUtil.nowDate());
				sysUserInfo.setCreateTime(DateUtil.nowTime());
				sysUserInfo.setUpdateUser("admin");
				sysUserInfo.setRoleCode("0001");
				sysUserInfo.setRoleName("最低权限");
				sysUserInfo.setDeptName("");
				sysUserInfo.setDeptPosi("");
				sysUserInfo.setEmail(gguser.getEmail());
				sysUserInfo.setEnterName("");
				sysUserInfo.setPosiCode("");
				sysUserInfo.setPosiName("");
				sysUserInfo.setTelphone(gguser.getPhone());
				amqpTemplate.convertAndSend("userQueue", JSON.toJSONString(sysUserInfo));
			}
		}
		return 1;
	}

}
