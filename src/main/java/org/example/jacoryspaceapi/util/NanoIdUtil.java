package org.example.jacoryspaceapi.util;

import java.security.SecureRandom;

/**
 * NanoId工具类
 * @author Jacory
 * @date 2025/5/10
 */
public class NanoIdUtil {
    
    private static final SecureRandom random = new SecureRandom();
    private static final char[] alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static final int defaultSize = 10;
    
    /**
     * 生成随机NanoId
     * @return 随机NanoId
     */
    public static String randomNanoId() {
        return randomNanoId(defaultSize);
    }
    
    /**
     * 生成指定长度的随机NanoId
     * @param size 长度
     * @return 随机NanoId
     */
    public static String randomNanoId(int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(alphabet[random.nextInt(alphabet.length)]);
        }
        return sb.toString();
    }
}