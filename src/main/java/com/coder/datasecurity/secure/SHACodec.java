package com.coder.datasecurity.secure;

import java.security.MessageDigest;


/**
 * @Author: LiuHao
 * @Descirption:单向加密
 * @Date: 2019/4/25_21:52
 */
public class SHACodec extends BasicCodec 
{
	private static final String ALGORITHM = "SHA";

	@Override
	public byte[] encrypt(byte[] data) throws Exception 
	{
		MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
		return messageDigest.digest(data);
	}

	@Override
	public byte[] decrypt(byte[] data) throws Exception 
	{
		return null;
	}

	/**
	 * 返回SHA单向加密后的十六进制字符串
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public String getEncryptForHex(byte[] data) throws Exception
	{
		byte[] digestData = encrypt(data);
		return parseByteArray2HexStr(digestData);
	}
}
