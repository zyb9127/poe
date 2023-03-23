package com.poe.project.poe_project.java.TestJava;



import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author zhangyabo
 * @description: TODO
 * @date 2021/12/27 5:56 下午
 */
public class TestMd5 {

    private static final String IV = "_breath__breath_";
    private static final String SECRET = "_breath__breath_";

    private TestMd5() {
    }

    public static String encrypt(String s) throws Exception {
        if (s == null) {
            throw new IllegalArgumentException("empty string argument");
        }
        try {
            IvParameterSpec iv = new IvParameterSpec(IV.getBytes());
            SecretKeySpec key = new SecretKeySpec(SECRET.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
            byte[] encryptedData = cipher.doFinal(s.getBytes(StandardCharsets.UTF_8));
            byte[] base64Data = Base64.encodeBase64(encryptedData);
            return new String(base64Data);
        } catch (Exception e) {
            throw new Exception("encrypt string error", e);
        }
    }

    public static String decrypt(String s) throws Exception {
        if (s == null || s.trim().length() == 0) {
            throw new IllegalArgumentException("empty string argument");
        }
        try {
            IvParameterSpec iv = new IvParameterSpec(IV.getBytes());
            SecretKeySpec key = new SecretKeySpec(SECRET.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            cipher.init(Cipher.DECRYPT_MODE, key, iv);
            byte[] byteMi = Base64.decodeBase64(s.getBytes());
            byte[] decryptedData = cipher.doFinal(byteMi);
            return new String(decryptedData, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new Exception("decrypt string error", e);
        }
    }
}
