package com.poe.project.poe_project.java.TestJava;

import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @author zhangyabo
 * @description: TODO
 * @date 2022/1/17 16:51
 */
public class TestAssert {

    public static void main(String[] args) {
        Set list = new HashSet();

        String ss=list.toString();
        System.out.println(list.contains("1"));

        double a=0.0;
        System.out.println(ObjectUtils.isEmpty(a));



    }


}
