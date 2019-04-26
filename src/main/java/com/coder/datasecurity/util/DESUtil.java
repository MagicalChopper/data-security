package com.coder.datasecurity.util;

import com.coder.datasecurity.secure.Base64Utils;
import com.google.common.collect.ImmutableMap;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.security.*;
import java.util.Map;

/**
 * @Author: LiuHao
 * @Descirption:
 * @Date: 2019/4/25_22:20
 */
public class DESUtil {

    private static final String ALGORITHM = "DES";

    /**
     * 初始化DES KEY
     * @return
     */
    private String initDESKey()  {
        KeyGenerator keyGenerator = null;
        try {
            keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        SecureRandom secureRandom = new SecureRandom();
        keyGenerator.init(secureRandom);
        SecretKey desKey = keyGenerator.generateKey();
        Base64Utils base64Utils = Base64Utils.getInstance();
        String secret = base64Utils.encoder(desKey.getEncoded());
        return secret;
    }

    /**
     * DES对数据进行加密
     * @param data
     */
    public byte[] encryptByDES(byte[] data){
        String secret = initDESKey();//初始化一个DES加密秘钥
        SecretKey mesKey;
        Cipher cipher;
        try {
            mesKey = getKey(secret);
            cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, mesKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 获取对称密钥
     * @param key base64编码后的密钥字符串
     * @return
     * @throws Exception
     */
    private SecretKey getKey(String key) throws Exception {
        Base64Utils base64Utils = Base64Utils.getInstance();
        DESKeySpec desKeySpec = new DESKeySpec(base64Utils.decoder(key));
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        return secretKeyFactory.generateSecret(desKeySpec);
    }
}
