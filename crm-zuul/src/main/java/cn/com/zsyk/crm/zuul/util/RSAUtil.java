package cn.com.zsyk.crm.zuul.util;


import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * RSA加解密工具类
 *
 * @author wangyuelong
 *
 * @date 2019年04月29日
 */
public class RSAUtil {

    /**
     * 数字签名，密钥算法
     */
    private static final String RSA_KEY_ALGORITHM = "RSA";

    /**
     * 数字签名签名/验证算法
     */
    private static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    /**
     * RSA密钥长度，RSA算法的默认密钥长度是1024密钥长度必须是64的倍数，在512到65536位之间
     */
    private static final int KEY_SIZE = 1024;

    /**
     * 生成密钥对
     */
    public static Map<String, String> initKey() throws Exception {
        KeyPairGenerator keygen = KeyPairGenerator.getInstance(RSA_KEY_ALGORITHM);
        SecureRandom secrand = new SecureRandom();
        /**
         * 初始化随机产生器
         */
        secrand.setSeed("initSeed".getBytes());
        /**
         * 初始化密钥生成器
         */
        keygen.initialize(KEY_SIZE, secrand);
        KeyPair keys = keygen.genKeyPair();

        byte[] pub_key = keys.getPublic().getEncoded();
        String publicKeyString = Base64.encodeBase64String(pub_key);

        byte[] pri_key = keys.getPrivate().getEncoded();
        String privateKeyString = Base64.encodeBase64String(pri_key);

        Map<String, String> keyPairMap = new HashMap<>();
        keyPairMap.put("publicKeyString", publicKeyString);
        keyPairMap.put("privateKeyString", privateKeyString);

        return keyPairMap;
    }

    /**
     * 密钥转成字符串
     *
     * @param key
     * @return
     */
    public static String encodeBase64String(byte[] key) {
        return Base64.encodeBase64String(key);
    }

    /**
     * 密钥转成byte[]
     *
     * @param key
     * @return
     */
    public static byte[] decodeBase64(String key) {
        return Base64.decodeBase64(key);
    }

    /**
     * 公钥加密
     *
     * @param data      加密前的字符串
     * @param publicKey 公钥
     * @return 加密后的字符串
     * @throws Exception
     */
    public static String encryptByPubKey(String data, String publicKey) throws Exception {
        byte[] pubKey = RSAUtil.decodeBase64(publicKey);
        byte[] enSign = encryptByPubKey(data.getBytes(), pubKey);
        return Base64.encodeBase64String(enSign);
    }

    /**
     * 公钥加密
     *
     * @param data   待加密数据
     * @param pubKey 公钥
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPubKey(byte[] data, byte[] pubKey) throws Exception {
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(pubKey);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    /**
     * 私钥加密
     *
     * @param data       加密前的字符串
     * @param privateKey 私钥
     * @return 加密后的字符串
     * @throws Exception
     */
    public static String encryptByPriKey(String data, String privateKey) throws Exception {
        byte[] priKey = RSAUtil.decodeBase64(privateKey);
        byte[] enSign = encryptByPriKey(data.getBytes(), priKey);
        return Base64.encodeBase64String(enSign);
    }

    /**
     * 私钥加密
     *
     * @param data   待加密的数据
     * @param priKey 私钥
     * @return 加密后的数据
     * @throws Exception
     */
    public static byte[] encryptByPriKey(byte[] data, byte[] priKey) throws Exception {
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(priKey);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 公钥解密
     *
     * @param data   待解密的数据
     * @param pubKey 公钥
     * @return 解密后的数据
     * @throws Exception
     */
    public static byte[] decryptByPubKey(byte[] data, byte[] pubKey) throws Exception {
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(pubKey);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    /**
     * 公钥解密
     *
     * @param data      解密前的字符串
     * @param publicKey 公钥
     * @return 解密后的字符串
     * @throws Exception
     */
    public static String decryptByPubKey(String data, String publicKey) throws Exception {
        byte[] pubKey = RSAUtil.decodeBase64(publicKey);
        byte[] design = decryptByPubKey(Base64.decodeBase64(data), pubKey);
        return new String(design);
    }

    /**
     * 私钥解密
     *
     * @param data   待解密的数据
     * @param priKey 私钥
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPriKey(byte[] data, byte[] priKey) throws Exception {
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(priKey);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 私钥解密
     *
     * @param data       解密前的字符串
     * @param privateKey 私钥
     * @return 解密后的字符串
     * @throws Exception
     */
    public static String decryptByPriKey(String data, String privateKey) throws Exception {
        byte[] priKey = RSAUtil.decodeBase64(privateKey);
        byte[] design = decryptByPriKey(Base64.decodeBase64(data), priKey);
        return new String(design);
    }

    /**
     * RSA签名
     *
     * @param data   待签名数据
     * @param priKey 私钥
     * @return 签名
     * @throws Exception
     */
    public static String sign(byte[] data, byte[] priKey) throws Exception {
        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(priKey);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        // 生成私钥
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 实例化Signature
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        // 初始化Signature
        signature.initSign(privateKey);
        // 更新
        signature.update(data);
        return Base64.encodeBase64String(signature.sign());
    }

    /**
     * RSA校验数字签名
     *
     * @param data   待校验数据
     * @param sign   数字签名
     * @param pubKey 公钥
     * @return boolean 校验成功返回true，失败返回false
     */
    public boolean verify(byte[] data, byte[] sign, byte[] pubKey) throws Exception {
        // 实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        // 初始化公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(pubKey);
        // 产生公钥
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
        // 实例化Signature
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        // 初始化Signature
        signature.initVerify(publicKey);
        // 更新
        signature.update(data);
        // 验证
        return signature.verify(sign);
    }

    public static void main(String[] args) {
        try {
            Map<String, String> keyMap = initKey();
            String publicKeyString = keyMap.get("publicKeyString");
            String privateKeyString = keyMap.get("privateKeyString");
            System.out.println("公钥:" + publicKeyString);
            System.out.println("私钥:" + privateKeyString);

            // 待加密数据
            String data = "admin123";
            // 公钥加密
            String encrypt = RSAUtil.encryptByPubKey(data, publicKeyString);
            // 私钥解密
            String decrypt = RSAUtil.decryptByPriKey(encrypt, privateKeyString);

            System.out.println("加密前:" + data);
            System.out.println("加密后:" + encrypt);
            System.out.println("解密后:" + decrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




//    protected static final Log log = LogFactory.getLog(RSAUtil.class);
//    private static String KEY_RSA_TYPE = "RSA";
//    private static int KEY_SIZE = 1024;//JDK方式RSA加密最大只有1024位
//    private static int ENCODE_PART_SIZE = KEY_SIZE/8;
//    public static final String PUBLIC_KEY_NAME = "public";
//    public static final String PRIVATE_KEY_NAME = "private";
//
//    /**
//     * 创建公钥秘钥
//     * @return
//     */
//    public static Map<String,String> createRSAKeys(){
//        Map<String,String> keyPairMap = new HashMap<>();//里面存放公私秘钥的Base64位加密
//        try {
//            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_RSA_TYPE);
//            keyPairGenerator.initialize(KEY_SIZE,new SecureRandom());
//            KeyPair keyPair = keyPairGenerator.generateKeyPair();
//
//            //获取公钥秘钥
//            String publicKeyValue = Base64.encodeBase64String(keyPair.getPublic().getEncoded());
//            String privateKeyValue = Base64.encodeBase64String(keyPair.getPrivate().getEncoded());
//
//            //存入公钥秘钥，以便以后获取
//            keyPairMap.put(PUBLIC_KEY_NAME,publicKeyValue);
//            keyPairMap.put(PRIVATE_KEY_NAME,privateKeyValue);
//        } catch (NoSuchAlgorithmException e) {
//            log.error("当前JDK版本没找到RSA加密算法！");
//            e.printStackTrace();
//        }
//        return keyPairMap;
//    }
//
//    /**
//     * 公钥加密
//     * 描述：
//     *     1字节 = 8位；
//     *     最大加密长度如 1024位私钥时，最大加密长度为 128-11 = 117字节，不管多长数据，加密出来都是 128 字节长度。
//     * @param sourceStr
//     * @param publicKeyBase64Str
//     * @return
//     */
//    public static String encode(String sourceStr,String publicKeyBase64Str){
//        byte [] publicBytes = Base64.decodeBase64(publicKeyBase64Str);
//        //公钥加密
//        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicBytes);
//        List<byte[]> alreadyEncodeListData = new LinkedList<>();
//
//        int maxEncodeSize = ENCODE_PART_SIZE - 11;
//        String encodeBase64Result = null;
//        try {
//            KeyFactory keyFactory = KeyFactory.getInstance(KEY_RSA_TYPE);
//            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
//            Cipher cipher = Cipher.getInstance(KEY_RSA_TYPE);
//            cipher.init(Cipher.ENCRYPT_MODE,publicKey);
//            byte[] sourceBytes = sourceStr.getBytes("utf-8");
//            int sourceLen = sourceBytes.length;
//            for(int i=0;i<sourceLen;i+=maxEncodeSize){
//                int curPosition = sourceLen - i;
//                int tempLen = curPosition;
//                if(curPosition > maxEncodeSize){
//                    tempLen = maxEncodeSize;
//                }
//                byte[] tempBytes = new byte[tempLen];//待加密分段数据
//                System.arraycopy(sourceBytes,i,tempBytes,0,tempLen);
//                byte[] tempAlreadyEncodeData = cipher.doFinal(tempBytes);
//                alreadyEncodeListData.add(tempAlreadyEncodeData);
//            }
//            int partLen = alreadyEncodeListData.size();//加密次数
//
//            int allEncodeLen = partLen * ENCODE_PART_SIZE;
//            byte[] encodeData = new byte[allEncodeLen];//存放所有RSA分段加密数据
//            for (int i = 0; i < partLen; i++) {
//                byte[] tempByteList = alreadyEncodeListData.get(i);
//                System.arraycopy(tempByteList,0,encodeData,i*ENCODE_PART_SIZE,ENCODE_PART_SIZE);
//            }
//            encodeBase64Result = Base64.encodeBase64String(encodeData);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return encodeBase64Result;
//    }
//
//    /**
//     * 私钥解密
//     * @param sourceBase64RSA
//     * @param privateKeyBase64Str
//     */
//    public static String decode(String sourceBase64RSA,String privateKeyBase64Str){
//        byte[] privateBytes = Base64.decodeBase64(privateKeyBase64Str);
//        byte[] encodeSource = Base64.decodeBase64(sourceBase64RSA);
//        int encodePartLen = encodeSource.length/ENCODE_PART_SIZE;
//        List<byte[]> decodeListData = new LinkedList<>();//所有解密数据
//        String decodeStrResult = null;
//        //私钥解密
//        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateBytes);
//        try {
//            KeyFactory keyFactory = KeyFactory.getInstance(KEY_RSA_TYPE);
//            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
//            Cipher cipher = Cipher.getInstance(KEY_RSA_TYPE);
//            cipher.init(Cipher.DECRYPT_MODE,privateKey);
//            int allDecodeByteLen = 0;//初始化所有被解密数据长度
//            for (int i = 0; i < encodePartLen; i++) {
//                byte[] tempEncodedData = new byte[ENCODE_PART_SIZE];
//                System.arraycopy(encodeSource,i*ENCODE_PART_SIZE,tempEncodedData,0,ENCODE_PART_SIZE);
//                byte[] decodePartData = cipher.doFinal(tempEncodedData);
//                decodeListData.add(decodePartData);
//                allDecodeByteLen += decodePartData.length;
//            }
//            byte [] decodeResultBytes = new byte[allDecodeByteLen];
//            for (int i = 0,curPosition = 0; i < encodePartLen; i++) {
//                byte[] tempSorceBytes = decodeListData.get(i);
//                int tempSourceBytesLen = tempSorceBytes.length;
//                System.arraycopy(tempSorceBytes,0,decodeResultBytes,curPosition,tempSourceBytesLen);
//                curPosition += tempSourceBytesLen;
//            }
//            log.info("RSA解密工具类返回字节长度："+decodeResultBytes.length);
//            decodeStrResult = new String(decodeResultBytes,"UTF-8");
//            log.info("RSA解密工具类返回字符："+decodeStrResult);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return decodeStrResult;
//    }

}
