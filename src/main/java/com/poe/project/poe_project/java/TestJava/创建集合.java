package com.poe.project.poe_project.java.TestJava;

import com.google.common.collect.Lists;
import com.sun.tools.javac.util.List;

import java.util.ArrayList;

/**
 * @author poe.zhang
 * @date 2023年03月01日 15:53
 * @description:创建集合
 */
public class 创建集合 {
    public static void main(String[] args) {
        ArrayList<Integer> list1 = Lists.newArrayList(1, 2, 3);
        System.out.println(list1.add(4));
        List<Integer> list2 = List.of(1, 2, 3);
        System.out.println(list2.add(5));


    }
}
