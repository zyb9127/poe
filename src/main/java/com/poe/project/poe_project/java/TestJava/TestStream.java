package com.poe.project.poe_project.java.TestJava;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author zhangyabo
 * @description: TODO
 * @date 2021/8/9 3:30 下午
 */
public class TestStream {
    public static void main(String[] args) {
        Map<Object, Object> map = new HashMap<>();
        map.put("num", 1);
        map.put("num1", 2);

        Object next = map.keySet().iterator().next();
        Object next2 = map.keySet().iterator().next();
        //取出第一个value值
        AtomicReference<String> num= new AtomicReference<>("");
        map.values().stream().findFirst().ifPresent(s-> num.set(String.valueOf(s)));
        num.get();

        System.out.println(next);
        System.out.println(next2);

    }
}

