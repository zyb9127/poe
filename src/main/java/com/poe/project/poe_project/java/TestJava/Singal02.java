package com.poe.project.poe_project.java.TestJava;

public class Singal02 {
    private Singal02(){

    }
    private volatile static   Singal02 singalo2;

    public static Singal02 getInstance(){
        if(singalo2==null){
            synchronized (Singal02.class){
                if (singalo2==null){
                    return singalo2=new Singal02();
                }
            }
        }
        return singalo2;
    }



}
