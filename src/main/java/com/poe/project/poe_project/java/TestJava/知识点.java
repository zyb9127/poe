package com.poe.project.poe_project.java.TestJava;

public class 知识点 {
    public static void main(String[] args) {
        String s1="poe";
        String s2=new String("poe");
        String s3="po"+"e";

        System.out.println(s1==s2);
        System.out.println(s1.equals(s3));
        System.out.println(s1==s3);
        System.out.println(2<<3);
        System.out.println(8>>3);
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
