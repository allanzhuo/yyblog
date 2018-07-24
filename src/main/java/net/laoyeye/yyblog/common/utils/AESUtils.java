package net.laoyeye.yyblog.common.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
/**
 * AES加解密工具类
 * @author 小卖铺的老爷爷
 * @date 2018年5月24日
 * @website www.laoyeye.net
 */
public class AESUtils {  

    private static final String KEY_AES = "AES";  

    public static String encrypt(String src, String key) throws Exception {  
        if (key == null || key.length() != 16) {  
            throw new Exception("key不满足条件");  
        }   
        byte[] raw = key.getBytes();  
        SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_AES);  
        Cipher cipher = Cipher.getInstance(KEY_AES);  
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);  
        byte[] encrypted = cipher.doFinal(src.getBytes());  
        return byte2hex(encrypted);  
    }  

    public static String decrypt(String src, String key) throws Exception {  
        if (key == null || key.length() != 16) {  
            throw new Exception("key不满足条件");  
        }  
        byte[] raw = key.getBytes();  
        SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_AES);  
        Cipher cipher = Cipher.getInstance(KEY_AES);  
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);  
        byte[] encrypted1 = hex2byte(src);  
        byte[] original = cipher.doFinal(encrypted1);  
        String originalString = new String(original);  
        return originalString;  
    }  

    public static byte[] hex2byte(String strhex) {  
        if (strhex == null) {  
            return null;  
        }  
        int l = strhex.length();  
        if (l % 2 == 1) {  
            return null;  
        }  
        byte[] b = new byte[l / 2];  
        for (int i = 0; i != l / 2; i++) {  
            b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2),  
                    16);  
        }  
        return b;  
    }  

    public static String byte2hex(byte[] b) {  
        String hs = "";  
        String stmp = "";  
        for (int n = 0; n < b.length; n++) {  
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));  
            if (stmp.length() == 1) {  
                hs = hs + "0" + stmp;  
            } else {  
                hs = hs + stmp;  
            }  
        }  
        return hs.toUpperCase();  
    }  

/*    public static void main(String[] args) {
        String src = "小卖铺的老爷爷";
        String key = "smkldospdosldaaa";
        KeyGenerator keyGenerator;
        try {
            keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(192);
            // 产生密钥
            SecretKey secretKey = keyGenerator.generateKey();
            // 获取密钥
            byte[] keyBytes = secretKey.getEncoded();   
            String key = keyBytes.toString();
            System.out.println("key:"+key);
            String encrypt = encrypt(src, key);
            System.out.println("密文："+encrypt);
            String decrypt = decrypt(encrypt, key);
            System.out.println("明文："+decrypt);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }            
    }*/
}  

