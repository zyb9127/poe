package com.poe.project.poe_project.java.TestJava;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zyb
 * @Description 测试类型转换
 * @date 2023/10/14 14:08
 */
public class 测试类型转换 {
    public static void main(String[] args) {
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("a",1);
        map.put("b",2);
        list.add(map);
        list.forEach(c->{
            c.put("c",3);
        });
        list.forEach(System.out::println);
    }
}
