package com.lsr.util;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class PasswordUtil {

	private static SecretKeySpec secretKey;
	private static byte[] key;
	
	public static void setKey(String text) {
		MessageDigest sha = null;
		
		try {
			key = text.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretKey = new SecretKeySpec(key, "AES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public static String encrypt(String text, String secret) {
		try {
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return Base64.getEncoder().encodeToString(cipher.doFinal(text.getBytes("UTF-8")));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error while encrypting : " + e.toString());
		}
		
		return null;
	}

	public static String decrypt(String text, String secret) {
		try {
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return new String(cipher.doFinal(Base64.getDecoder().decode(text)));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error while decrypting : " + e.toString());
		}
		
		return null;
	}
	
	public static String generatePasswordToMd5(String text) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigestByte = md.digest(text.getBytes());
			BigInteger no = new BigInteger(1, messageDigestByte);
			
			String hashText = no.toString(16);
			while (hashText.length() < 32) {
				hashText = "0" + hashText;
			}
			
			return hashText;
		} catch (Exception e) {
			e.printStackTrace();
			 throw new RuntimeException(e);
		}
	}
}

