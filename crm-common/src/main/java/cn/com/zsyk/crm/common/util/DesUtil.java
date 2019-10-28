package cn.com.zsyk.crm.common.util;

import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * DES算法的入口参数有三个： KEY、Data、Mode。 其中Key为8个字节共64位，是DES算法的工作密钥；
 * Data也为8个字节64位，是要被加密或被解密的数据； Mode为DES的工作方式，有两种：加密或解密。
 * 
 * @author
 * 
 */
public class DesUtil {
	private static Cipher encoder;

	private static Cipher decoder;

	static {
		try {
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			DESKeySpec desKeySpec = new DESKeySpec("1234abcd".getBytes("ASCII"));// 这里的ASCII是为了保证只有ASCII字符
			SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
			IvParameterSpec iv = new IvParameterSpec("00000000".getBytes("ASCII"));// 前提已经满足，所以可以换做其它编码

			encoder = Cipher.getInstance("DES/CBC/NoPadding");
			decoder = Cipher.getInstance("DES/CBC/NoPadding");
			encoder.init(Cipher.ENCRYPT_MODE, secretKey, iv);
			decoder.init(Cipher.DECRYPT_MODE, secretKey, iv);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// 加密
	public static synchronized String encrypt(String plainText) throws Exception {
		byte[] inputs = plainText.getBytes("UTF-8");
		int length = inputs.length, mod = length % 8;
		if (mod > 0) {// 如果不是8的倍数，则扩充为8的倍数
			length += 8 - mod;
		}
		inputs = Arrays.copyOf(inputs, length);// 空出的位用零填充
		byte[] encoded = encoder.doFinal(inputs);
		StringBuffer buf = new StringBuffer();
		for (byte b : encoded) {
			buf.append(String.format("%02X", b));// 16进制大写输出，不足两位前补零
		}
		return buf.toString();
	}

	// 解密
	public static synchronized String decrypt(String encodedText) throws Exception {
		byte[] inputs = new byte[encodedText.length() / 2];
		for (int i = 0; i < inputs.length; i++) {
			// 每两个字节转为一个16进制数
			inputs[i] = (byte) Integer.parseInt(encodedText.substring(i * 2, i * 2 + 2), 16);
		}
		byte[] decoded = decoder.doFinal(inputs);
		return new String(decoded, "UTF-8").trim();// 记得清除加密时补充的零值
	}

//	public static void main(String[] args) throws Exception {
//		String info = "111111";
//		String tmp = encrypt(info);
//		System.out.println(tmp);
//		System.out.println(decrypt(tmp));
//	}
}

