package cn.com.zsyk.crm.ecif.service.intf.percust;

import java.io.UnsupportedEncodingException;

public class CharacterCheck {
	
	/**
	 * 验证字符串内容是否包含下列非法字符 
	 * @param content
	 * @return
	 */
	public static boolean validateLegalString(String content) {
		String illegal = "~!@#$%^&*()";
//		String illegal = "@";
		char isLegalChar ='t';
		
		for(int i=0 ; i < content.length(); i++) {
			for(int j=0; j< illegal.length(); j++) {
				if(content.charAt(i) == illegal.charAt(j)) {
					return false;
				}
			}
			
		}
		return true;
		
	}
	/*
	 * 校验一个字符是否是汉字 
	 */
	public static boolean isChineseChar(char c) {
		try {
			return String.valueOf(c).getBytes("UTF-8").length > 1;
			
		}catch(UnsupportedEncodingException e) {
			return false;
		}
		
	}
	
//	 public static void main(String[] args) {
//
//		boolean test =  validateLegalString("的大");
//		System.out.println(test);
//
//		 }

}
