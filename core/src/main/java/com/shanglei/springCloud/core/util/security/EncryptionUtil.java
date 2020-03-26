package com.shanglei.springCloud.core.util.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密工具类
 * 未使用加盐算法
 */
public class EncryptionUtil {

    private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f' };

    /**
     * 可以采用的加密算法，暂时只提供了两种，可以根据实际情况自己扩充
     */
    public enum Algorithm {
        MD5,SHA1
    }

    /**
     * 将字符串加密
     * @param str   要加密的字符串
     * @param algorithm 类型，MD5,SHA1
     * @return  返回一个32位的字符串，其中应为字符为大写
     */
    public static String encode(String str,Algorithm algorithm) {

        try {

            MessageDigest messageDigest = MessageDigest.getInstance(String.valueOf(algorithm));
            messageDigest.update(str.getBytes());
            return getFormattedText(messageDigest.digest());

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }


    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }


}
