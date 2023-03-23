package com.poe.project.poe_project.java.TestJava;

import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import org.apache.tomcat.util.buf.CharsetUtil;

import java.util.HashMap;

/**
 * @author poe.zhang
 * @date 2022年12月05日 17:41
 * @description:测试加解密
 */
public class 测试加解密 {

    public static void main(String[] args) {
        String content = "poe";
        SymmetricCrypto sm4 = SmUtil.sm4();
        String encryptHex = sm4.encryptHex(content);
        //String decryptStr = sm4.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);
        System.out.println(encryptHex);
    }


}
