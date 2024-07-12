package com.poe.project.poe_project.java.TestJava;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author poe.zhang
 * @date 2023年03月01日 15:53
 * @description:创建集合
 */
public class 创建集合 {
    public static void main(String[] args) {

        //创建一个List<Map<String, Object>> objects
        List<Map<String, Object>> objects = new ArrayList<>();
        //创建一个Map<String, Object> map
        Map<String, Object> map = new HashMap<>();
        map.put("name", "poe");
        map.put("age", 18);
        map.put("address", "北京");
        map.put("phone", "110");
        map.put("index_full_code", "G330G003D90");
        objects.add(map);
        //创建一个List<String>,并把"name", "age", "address", "phone", "index_full_code"添加到list中
        List<String> keyList = Lists.newArrayList("age", "name", "address", "phone", "index_full_code", "index_full_code1");
        List<Object[]> batchArgs = objects.stream()
                .map(object -> {
                    //按照syncFieldList的顺序获取值
                    return keyList.stream()
                            .map(object::get).toArray();
                }).collect(Collectors.toList());
        //遍历输出batchArgs
        Object[] objects1 = batchArgs.get(0);
        //遍历输出objects1
        for (Object object : objects1) {
            System.out.println(object);
        }
    }
}
