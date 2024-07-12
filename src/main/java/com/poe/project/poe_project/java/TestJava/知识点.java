package com.poe.project.poe_project.java.TestJava;

import lombok.Data;

public class 知识点 {
    public static void main(String[] args) {
        String s1="poe";
        String s2=new String("poe");
        String s3="po"+"e";
        String s4="poe";

        System.out.println(s1==s2);
        System.out.println(s1.equals(s3));
        System.out.println(s1==s3);
        System.out.println(s1==s4);
        System.out.println(2<<3);
        System.out.println(8>>3);
        System.out.println(Runtime.getRuntime().availableProcessors());
        B b = new B();
        b.setName("poe");
        
        A a = new A();
        a.setName("poe");


    }



    @Data
    static class A{
        protected  String name;
        public void say(){
            System.out.println("A");
        }

    }
    @Data
    static class B extends  A{

        private String color;
        public void say(){
            System.out.println("B");
        }
        public B(String color,String name){
            this.name=name;
            this.color=color;
        }
        public B(){
        }
    }

}
