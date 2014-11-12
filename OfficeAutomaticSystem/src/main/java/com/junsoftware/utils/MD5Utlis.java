package com.junsoftware.utils;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utlis {

	/**
	 * 此方法用来将一个字符串利用md5进行编码
	 * 
	 * @param plainText编码前的字符串
	 * 
	 * @return 编码后的字符串
	 */
	public static String md5(String plainText) {
		byte[] secretBytes = null;
		try {

			// MessageDigest 可以 拿到 某种加密 算法 一个 实例
			secretBytes = MessageDigest.getInstance("md5").digest(
					plainText.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("没有md5这个算法！");
		}
		String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
		// 如果生成数字未满32位，需要前面补0
		for (int i = 0; i < 32 - md5code.length(); i++) {
			md5code = "0" + md5code;
		}
		return md5code;
	}

	public static void main(String[] args) {
		System.out.println(md5("erdandan8("));
	}

	// md5 : 支持加密, 不支持 解密
	/*
	 * 在密码学中 如果 两个 不同的 值 按照 某种 算法 ,加密 后 产生同样的 结果, 这样的事 叫做 碰撞 .
	 * 
	 * 王小云 , 并不是 真是 就 破解了md5 加密, 只是弄出了一套 算法, 来提高 碰撞 的概率.
	 */

}
