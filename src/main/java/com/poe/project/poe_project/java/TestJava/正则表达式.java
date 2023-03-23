package com.poe.project.poe_project.java.TestJava;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author poe.zhang
 * @date 2022年11月19日 17:11
 * @description:测试正则表达式
 */
public class 正则表达式 {

    public static void main(String[] args) {


        DateTimeFormatter df = DateTimeFormatter.ofPattern(convertFormat("YYYY-MM-DD HH:mm:ss"));
        LocalDateTime ldt = LocalDateTime.parse("2022-02-02 12:27:21", df);
        long l = ldt.atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli();
        System.out.println(String.valueOf(l));

        String s="123,45,67";
        String[] split = s.split(",");
        String result="";
        for (String s1 : split) {
            result = result+s1+", ";
        }
        result.substring(0, result.length() - 2);
        System.out.println(result.substring(0,result.length()-2));








    }
    private static String convertFormat(String format) {
        if ("YYYY-MM-DD HH:mm:ss".equals(format)) {
            return "yyyy-MM-dd HH:mm:ss";
        }
        return "yyyy-MM-dd HH:mm";
    }




}
