package com.coder.datasecurity.secure;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * @Author: LiuHao
 * @Descirption:编解码工具类
 * @Date: 2019/4/25_21:52
 */
public class Base64Utils 
{

	private static Base64Utils base64Utils = new Base64Utils();
	private BASE64Encoder base64Encoder;
	private BASE64Decoder base64Decoder;
	
	private Base64Utils() 
	{
		base64Encoder = new BASE64Encoder();
		base64Decoder = new BASE64Decoder();
	}
	
	public static Base64Utils getInstance() 
	{
		return base64Utils;
	}
	
	/**
	 * base64 编码
	 * @param data
	 * @return
	 */
	public String encoder(byte[] data) 
	{
		return base64Encoder.encodeBuffer(data);
	}
	
	/**
	 * base64 解码
	 * @param data
	 * @return
	 * @throws IOException
	 */
	public byte[] decoder(String data) throws IOException 
	{
		return base64Decoder.decodeBuffer(data);
	}
	
}
