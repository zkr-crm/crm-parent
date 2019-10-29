package cn.com.zsyk.crm.common.util.cfca;

import cn.com.zsyk.crm.common.util.RC4.RC4;
import org.acegisecurity.providers.encoding.PlaintextPasswordEncoder;


/**
 * CFCA-单点密码加密、比对
 * @author hx
 * @date 2017-11-06
 */
public class CfcaPasswordEncoder extends PlaintextPasswordEncoder {

	public CfcaPasswordEncoder() {
	}

	public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
		//return CfcaEncryptUtils.isMatched(rawPass, encPass, 13);
		return RC4.isMatched(rawPass, encPass, "CRM");
	}
}
