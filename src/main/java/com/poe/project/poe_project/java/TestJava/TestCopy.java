package com.poe.project.poe_project.java.TestJava;

import com.poe.project.poe_project.java.Entry.UserVo;
import org.assertj.core.util.Lists;

import java.math.BigDecimal;
import java.util.*;

public class TestCopy {


    public static void main(String[] args) {
        List<UserVo> list= Lists.newArrayList(new UserVo("a","a","a"),new UserVo("b","b","b"));
        List<String> list1=new ArrayList<>();
        List<UserVo> list2=new ArrayList<>();
        list.forEach(
                user->{
                    list1.add(user.getName());
                }
        );
        list1.subList(0,1).clear();
        System.out.println(list);




    }


}
