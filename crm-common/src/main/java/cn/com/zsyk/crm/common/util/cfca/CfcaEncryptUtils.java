package cn.com.zsyk.crm.common.util.cfca;

import java.io.InputStream;
import java.util.Properties;

import cfca.svs.api.ClientEnvironment;
import cfca.svs.api.SVBusiness;
import cfca.svs.api.util.XmlUtil;

import com.sinosoft.sysframework.exception.BusinessException;

/**
 * CFCA加密
 * 导入的包:logback-cfca-jdk1.6-3.1.0.0.jar、sadk-3.3.0.1.jar、svs-api-jdk1.6.jar
 * @author hx
 * 2017-13-03
 */
public class CfcaEncryptUtils {
	private static SVBusiness svBusiness = null;
	private static Properties cfca_properties = new Properties();
	private static String serviceID = "";
	private static int encryptNo;
	private static int decryptNo;
	private static String verifyCertType = "";
	static {
		try {
			ClientEnvironment.initClientEnvironment("cfcaserver");
			InputStream in = CfcaEncryptUtils.class.getResourceAsStream("/cfcaserver.properties");
			
			cfca_properties.load(in);
			serviceID = cfca_properties.getProperty("serviceID");
			encryptNo = Integer.parseInt(cfca_properties.getProperty("encryptNo"));
			decryptNo = Integer.parseInt(cfca_properties.getProperty("decryptNo"));
			verifyCertType = cfca_properties.getProperty("verifyCertType");
			
		} catch (Exception e) {
			throw new BusinessException("-------------------加载CFCA配置文件失败-----------------------", false);
		}
		svBusiness = new SVBusiness();
	}
	
	/**
	 * 获取加密内容
	 * @param plainContent 原文
	 * @param currEncryptNo 加密算法
	 * @return
	 */
	public static String encrypt(String plainContent,int currEncryptNo) {
		String encrypt_str = "fail...";
		/* 
		 * cfca加密参数如下：
		 * 加密(签)算法
		 * 服务密钥id
		 */
		
		currEncryptNo = (currEncryptNo == -1)?encryptNo:currEncryptNo;
		/****以下只实现一种加密方式****/
		switch (currEncryptNo) {
		case 12:
			try {
				String typeInfo = "SM2 P7 分离式原文签名  ";
				String response = svBusiness.sm2P7DetachSign(serviceID,plainContent.getBytes());
				System.out.println("---------------------serviceID="+serviceID+",plainContent="+plainContent);
				if (dealResponse(response, typeInfo)) {
		           System.out.println(typeInfo + XmlUtil.getNodeText(response, "SignatureBase64"));
					encrypt_str = XmlUtil.getNodeText(response, "SignatureBase64");
		        }
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			break;

		 default:
			break;
		}		
		
		return encrypt_str;
	}
	/**
	 * 比对加密内容
	 * @param plainContent 原文
	 * @param cryptContent 密文
	 * @param currDecryptNo 解密算法
	 * @return
	 */
	public static boolean isMatched(String plainContent,String cryptContent,int currDecryptNo){
		boolean isMatched = false;
		 /* 
		 * cfca解密参数如下：
		 * 解密(签)算法
		 * 原文
		 * 密文
		 * 验证证书标识
		 */
		currDecryptNo = (currDecryptNo == 13) ? decryptNo : currDecryptNo;
		switch (currDecryptNo) {
		case 13:

	        String typeInfo = "SM2 P7 分离原文验签  ";
	        try {
				
	        	String response = svBusiness.sm2P7DetachVerifySign(plainContent.getBytes(), cryptContent, verifyCertType);
	        	System.out.println("---------------------plainContent="+plainContent
						+"\ncryptContent="+cryptContent+"currDecryptNo="+currDecryptNo);
	        	return dealResponse(response, typeInfo);
	        	
			} catch (Exception e) {
				e.printStackTrace();
			}

		 default:
			break;
		}
		return isMatched;
	}

    private static boolean dealResponse(String response, String typeInfo) {
        if ("".equals(response)) {
            System.out.println("Socket 可能发生 通讯异常");
        }
        String errorCode = XmlUtil.getNodeText(response, "ErrorCode");
        if ("0".equals(errorCode)) {
        	System.out.println("成功！");
            return true;
        } else {
            System.out.println(typeInfo + "失败");
            System.out.println("失败信息为:" + errorCode + " 错误信息为:" + XmlUtil.getNodeText(response, "ErrorDesc"));
            return false;
        }
    }
}
