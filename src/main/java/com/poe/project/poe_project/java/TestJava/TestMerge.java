package com.poe.project.poe_project.java.TestJava;

import org.mockito.internal.util.collections.ListUtil;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author poe.zhang
 * @date 2022年08月19日 11:37
 * @description:合并MAp
 */
public class TestMerge {
    public static void main(String[] args) {
        List list1 = new ArrayList<>();
        List list2 = new ArrayList<>();
        Map map1 = new HashMap();
        Map map2 = new HashMap();
        Map map3 = new HashMap();
        Map map4 = new HashMap();
        Map map5 = new HashMap();
        Map map6 = new HashMap();
        map1.put("id",1);
        map1.put("ç1","1");
        map1.put("c2","1");
        map2.put("id",2);
        map2.put("ç1","2");
        map2.put("c2","2");
        map3.put("id",4);
        map3.put("ç1","4");
        map3.put("c2","4");

        map4.put("id",1);
        map4.put("ç1","11");
        map4.put("c2","11");
        map5.put("id",2);
        map5.put("ç1","22");
        map5.put("c2","2");
        map6.put("id",3);
        map6.put("ç1","3");
        map6.put("c2","3");
        list1.add(map1);
        list1.add(map2);
        list1.add(map3);
        list2.add(map4);
        list2.add(map5);
        list2.add(map6);
        System.out.println("lsit1是"+list1);
        //结果：lsit1是[{name=张三, id=1}, {name=李四, id=2}]
        System.out.println("list2是"+list2);
        //结果：list2是[{name=王五, id=3}, {name=赵六, id=4}]
        List<Map<String, Object>> newList = merge(list1,list2);
        System.out.println("新的List<Map>是"+newList);
        //结果：新的List<Map>是[{name=张三, id=1}, {name=李四, id=2}, {name=王五, id=3}, {name=赵六, id=4}]
    }

    public static List<Map<String, Object>> merge(List<Map> m1, List<Map> m2){

        return new ArrayList<>(Stream.of(m1, m2).flatMap(Collection::stream).collect(Collectors.toMap(
                e -> e.get("id"),
                map -> (Map<String, Object>) map,
                (e1, e2) -> e2
        )).values());
    }
}
