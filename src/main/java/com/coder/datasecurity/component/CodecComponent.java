package com.coder.datasecurity.component;


import com.coder.datasecurity.enums.SecureType;
import com.coder.datasecurity.factory.SecureFactory;
import com.coder.datasecurity.secure.*;
import org.springframework.stereotype.Component;

/**
 * @Author: LiuHao
 * @Descirption:加密组件
 * @Date: 2019/4/25_21:52
 */
@Component
public class CodecComponent
{
	private static final String inputStr = "Super Bayern Super Bayern";


	public void md5Test(String input)
	{
		System.out.println("======= MD5 ========");
		try
		{
			byte[] data = input.getBytes();
			MD5Codec codec = (MD5Codec)SecureFactory.getCodec(SecureType.MD5, null);
			System.out.println("md5:" + codec.getEncryptForHex(data));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	

	public void shaTest(String input)
	{
		System.out.println("======== SHA ========");
		try
		{
			byte[] data = input.getBytes();
			SHACodec codec = (SHACodec)SecureFactory.getCodec(SecureType.SHA, null);
			System.out.println("sha:" + codec.getEncryptForHex(data));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	

	public void desTest(String input)
	{
		System.out.println("========= DES ========");
		try
		{
			byte[] data = input.getBytes();
			DESCodec codecA = (DESCodec)SecureFactory.getCodec(SecureType.DES, null);
			String secretKey = codecA.getSecretKey();
			byte[] encryptData = codecA.encrypt(data);
			DESCodec codecB = (DESCodec)SecureFactory.getCodec(SecureType.DES, secretKey);
			byte[] decryptData = codecB.decrypt(encryptData);
			System.out.println("in:" + input + " , out:" + new String(decryptData));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	

	public void aesTest(String input)
	{
		System.out.println("=========== AES ===========");
		try
		{
			byte[] data = input.getBytes();
			AESCodec codecA = (AESCodec)SecureFactory.getCodec(SecureType.AES, null);
			String secretKey = codecA.getSecretKey();
			byte[] encryptData = codecA.encrypt(data);
			AESCodec codecB = (AESCodec)SecureFactory.getCodec(SecureType.AES, secretKey);
			byte[] decryptData = codecB.decrypt(encryptData);
			System.out.println("in:" + input + " , out:" + new String(decryptData));
			String encryptHex = codecA.parseByteArray2HexStr(encryptData);
			byte[] decryptData2 = codecB.decrypt(codecB.parseHexStr2ByteArray(encryptHex));
			System.out.println("encryptHex:" + encryptHex + " , out:" + new String(decryptData2));
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void rsaTest(String input)
	{
		System.out.println("=========== RSA ============");
		try
		{
			byte[] data = input.getBytes();
			RSAForPrivateCodec codecA = (RSAForPrivateCodec)SecureFactory.getCodec(SecureType.RSA_PRIVATE, null);
			String publicKey = codecA.getPublicKey();
			byte[] encryptData = codecA.encrypt(data);
			String sign = codecA.sign(data);
			RSAForPublicCodec codecB = (RSAForPublicCodec)SecureFactory.getCodec(SecureType.RSA_PUBLIC, publicKey);
			byte[] decryptData = codecB.decrypt(encryptData);
			System.out.println("in:" + input + " , out:" + new String(decryptData) + " , verifySign:" + codecB.verifySign(decryptData, sign));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
