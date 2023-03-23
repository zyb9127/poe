package com.poe.project.poe_project.java.TestJava;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.file.Path;
import java.security.MessageDigest;

/**
 * Md5Utils
 *
 * @author Breath
 * @date 2020/7/27
 */
@Slf4j
public class Md5Utils {

    private static final int BUFFER_SIZE = 8 * 1024;
    private static final int HASH_LENGTH = 32;

    private Md5Utils() {
    }

    /**
     * 获取文件的md5
     *
     * @param f 文件对象
     * @return md5
     */
    public static String getMd5(File f) {
        try {
            FileInputStream fis = new FileInputStream(f);
            return getMd5(fis);
        } catch (FileNotFoundException e) {
            log.error("parse file path error, {}", ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    /**
     * 获取文件的md5
     *
     * @param p 文件地址
     * @return md5
     */
    public static String getMd5(Path p) {
        return getMd5(p.toFile());
    }

    /**
     * 获取字符串的md5
     *
     * @param s 字符串
     * @return md5
     */
    public static String getMd5(String s) {
        if (s == null) {
            return null;
        }
        return getMd5(new ByteArrayInputStream(s.getBytes()));
    }

    /**
     * 获取流的md5
     *
     * @param is 输入流
     * @return md5
     */
    public static String getMd5(InputStream is) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[BUFFER_SIZE];
            int length;
            while ((length = is.read(buffer)) != -1) {
                md.update(buffer, 0, length);
            }
            String hash = new BigInteger(1, md.digest()).toString(16);
            StringBuilder sb = new StringBuilder(hash);
            while (sb.length() < HASH_LENGTH) {
                sb.insert(0, "0");
            }
            return sb.toString();
        } catch (Exception e) {
            log.error("get md5 error, {}", ExceptionUtils.getStackTrace(e));
            return null;
        }
    }
}
