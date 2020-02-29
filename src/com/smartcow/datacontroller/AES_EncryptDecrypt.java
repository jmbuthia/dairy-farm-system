package com.smartcow.datacontroller;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class AES_EncryptDecrypt {
	 private static final String ALGORITHM = "AES";
	    private static final String KEY = "Johnson mbuthia.";
	    
	    public static String encrypt(String value) throws Exception
	    {
	        Key key = generateKey();
	        Cipher cipher = Cipher.getInstance(AES_EncryptDecrypt.ALGORITHM);
	        cipher.init(Cipher.ENCRYPT_MODE, key);
	        byte [] encryptedByteValue = cipher.doFinal(value.getBytes("utf8"));//"utf-8"
	        String encryptedValue64 = new BASE64Encoder().encode(encryptedByteValue);
	        return encryptedValue64;
	               
	    }
	    
	    public static String decrypt(String value) throws Exception
	    {
	        Key key = generateKey();
	        Cipher cipher = Cipher.getInstance(AES_EncryptDecrypt.ALGORITHM);
	        cipher.init(Cipher.DECRYPT_MODE, key);
	        byte [] decryptedValue64 = new BASE64Decoder().decodeBuffer(value);
	        byte [] decryptedByteValue = cipher.doFinal(decryptedValue64);
	        String decryptedValue = new String(decryptedByteValue,"utf8");
	        return decryptedValue;
	                
	    }
	    
	    private static Key generateKey() throws Exception 
	    {
	        Key key = new SecretKeySpec(AES_EncryptDecrypt.KEY.getBytes(),AES_EncryptDecrypt.ALGORITHM);
	        return key;
	    }/*
	     public static String getSalt(){
	    	 Random r=new SecureRandom();
	    	 byte[] saltBytes = new byte[32];
	    	 r.nextBytes(saltBytes);
			return Base64.getEncoder().encodeToString(saltBytes);
	     }*/
		    public static String getRandomNumber() {
		        int i = (int) (Math.random() * 10000000);
		         
		        return String.valueOf(i);
		    }

	   
	    /*public static void main(String[] args) {
			try {
				System.out.println(AES_EncryptDecrypt.generateKey());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println(AES_EncryptDecrypt.getSalt());
			System.out.println(AES_EncryptDecrypt.getRandomNumber());
			try {
				System.out.println(AES_EncryptDecrypt.encrypt("12345678")+"jjjjj"+AES_EncryptDecrypt.decrypt("yEZ+JvhU0Yur4IGy4M33UA=="));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}*/

}