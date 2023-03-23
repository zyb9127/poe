package com.poe.project.poe_project.java.TestJava;

import com.poe.project.poe_project.java.constant.ModelConstant;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import sun.misc.MessageUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zhangyabo
 * @description: TODO
 * @date 2022/1/10 16:10
 */
public class TestString {

    public static void SQLIN() {
        Set<String> cIdListByCiName = Collections.singleton("123");
        String s="";
        if (ObjectUtils.isNotEmpty(cIdListByCiName)) {
            s = "FILTER ci._key IN ['" + String.join("','", cIdListByCiName) + "'] ";

        }
        System.out.println(s);
    }

    public static void main(String[] args) {
        String s="'1'2'3'";
        System.out.println(customTrim(s,"'".charAt(0)).trim());
        String ss;
        ss=checkInput(null);
        System.out.println(StringUtils.isBlank(ss));
        Object j = null;
        System.out.println((String)j);
        Map map = new HashMap();
        map.put("2",new ArrayList<>());
        boolean relationCascade = (Boolean) map.getOrDefault("2",false);
        System.out.println(relationCascade);




    }
    public static String customTrim(String str, char c) {
        char[] chars = str.toCharArray();
        int len = chars.length;
        int st = 0;
        while ( (st < len) && (chars[st] == c) ){
            st ++;
        }
        while ( (st < len) && (chars[len-1] == c) ){
            len --;
        }

        return (st > 0) || (len < chars.length) ? str.substring(st, len) : str;
    }

    public static String checkInput(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }else{
            return value;
        }
    }






}
