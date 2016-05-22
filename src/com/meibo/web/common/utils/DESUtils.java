package com.meibo.web.common.utils;

import java.lang.reflect.Method;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class DESUtils {

	/**
	 * 加密
	 * 
	 * @param datasource
	 *            byte[]
	 * @param password
	 *            String
	 * @return byte[]
	 */
	public static String encode(byte[] data, String key) 
    {
        try
        {
      DESKeySpec dks = new DESKeySpec(key.getBytes());

      SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            //key的长度不能够小于8位字节
            Key secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance( "DES/CBC/PKCS5Padding" );
            IvParameterSpec iv = new IvParameterSpec(key.getBytes());
            AlgorithmParameterSpec paramSpec = iv;
            cipher.init(Cipher.ENCRYPT_MODE, secretKey,paramSpec);

            byte[] bytes = cipher.doFinal(data);
            String isoString = new String(bytes,"ISO-8859-1");
            return isoString;
        } catch (Exception e)
        {
        	e.printStackTrace();
        	return "";
        }
        
    }

	/**
	 * 解密
	 * 
	 * @param src
	 *            byte[]
	 * @param password
	 *            String
	 * @return byte[]
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] src, String password) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom random = new SecureRandom();
		// 创建一个DESKeySpec对象
		DESKeySpec desKey = new DESKeySpec(password.getBytes());
		// 创建一个密匙工厂
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		// 将DESKeySpec对象转换成SecretKey对象
		SecretKey securekey = keyFactory.generateSecret(desKey);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance("DES");
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, random);
		// 真正开始解密操作
		return cipher.doFinal(src);
	}
	
	public static String SHA1(String decript) {
		try {
			MessageDigest digest = java.security.MessageDigest
					.getInstance("SHA-1");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static void main(String[] args) {
		
		String sha1Str = SHA1( "SH2543" );
		sha1Str = sha1Str.toUpperCase().substring( 0, 8 );
		System.out.println( sha1Str ); // B7506071
		
		String s = encode( "userPass=SH2543".getBytes(), "B7506071" );
		System.out.println( s );
//		String params = "userPass=SH2543";
//		String postUrl = "http://4g.1069106.com:9898/FlowAPI/GetProductsInfo.ashx";
//		Map<String, String> param = new HashMap<String, String>();
//		param.put( "userCode", "SBXXLL" );
//		param.put( "submitInfo", params );
//		param.put( "userPass", "SH2543" );
//		
//		String res = HttpRequestUtils.sendHttpPost(postUrl, param);
//		System.out.println( res );
	}
	
	 public static String encodeBase64(byte[]input) throws Exception{  
	        Class clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");  
	        Method mainMethod= clazz.getMethod("encode", byte[].class);  
	        mainMethod.setAccessible(true);  
	         Object retObj=mainMethod.invoke(null, new Object[]{input});  
	         return (String)retObj;  
	    } 

}
