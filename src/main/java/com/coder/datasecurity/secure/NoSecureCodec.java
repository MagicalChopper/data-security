package com.coder.datasecurity.secure;


/**
 * @Author: LiuHao
 * @Descirption:不适用任何安全加密
 * @Date: 2019/4/25_21:52
 */
public class NoSecureCodec extends BasicCodec
{

	@Override
	public byte[] encrypt(byte[] data) throws Exception
	{
		return data;
	}

	@Override
	public byte[] decrypt(byte[] data) throws Exception
	{
		return data;
	}

}
