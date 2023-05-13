package com.poe.project.poe_project.java.TestJava;

import com.poe.project.poe_project.java.Entry.CloneVo;
import com.poe.project.poe_project.java.Entry.UserVo;

public class TestClone {

    public static void main(String[] args) {
        CloneVo cloneVo=CloneVo.builder().name("poe").color("red").userVo(new UserVo("a","a","a")).build();
        CloneVo cloneVo1 = cloneVo.clone();
        UserVo userVo = cloneVo.getUserVo();
        userVo.setColor("b");
        System.out.println(cloneVo1.getUserVo());
        System.out.println(cloneVo.getUserVo());


    }

}
