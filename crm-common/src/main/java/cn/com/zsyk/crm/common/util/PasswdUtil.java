package cn.com.zsyk.crm.common.util;

import cn.com.zsyk.crm.common.util.cfca.CfcaEncryptUtils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class PasswdUtil {
    /**
     * 对字符串进行加密(小写+字母)
     *
     * @param str 传入要加密的字符串
     * @return  加密后的字符串
     */
    public static String encode(String str) {
        try {
            //return CfcaEncryptUtils.encrypt(str,12);
            return "";
            //return RC4.put(str, "CRM");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 对字符串md5加密(小写+字母)
     *
     * @param plainContent,cryptContent 传入要加密的字符串
     * @return  boolean
     */
    public static boolean isMatched(String plainContent,String cryptContent) {
        try {
            return CfcaEncryptUtils.isMatched(plainContent,cryptContent,13);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
