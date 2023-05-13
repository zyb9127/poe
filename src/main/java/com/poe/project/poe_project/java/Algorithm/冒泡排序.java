package com.poe.project.poe_project.java.Algorithm;

import org.assertj.core.util.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class 冒泡排序 {
    public static void main(String[] args) {

        List<Integer> list = Lists.list(1, 3, 2, 5, 7, 9, 4);
        int length=list.size();
        Integer[] list1 =list.toArray(new Integer[length]);

        for (int i = 0; i < length-1; i++) {
            for (int j = 0; j < length-1-i; j++) {
                int temp;
                if(list1[j]>list1[j+1]){
                    //转换
                    temp=list1[j+1];
                    list1[j+1]=list1[j];
                    list1[j]=temp;
                }
            }
            Arrays.stream(list1).map(s->s+"-").forEach(System.out::print);
            System.out.println();
        }
        System.out.println("###################");
        Integer[] list2 =list.toArray(new Integer[length]);

        //如果在所有的循环之前就已经结束了大小判断，则不必再往下比较
        boolean flag = true;
        for(int i = 0;i < list2.length - 1;i++){
            if(!flag) break;
            flag = false;
            for(int j = 0;j < list2.length - 1 - i;j++){
                if(list2[j] > list2[j+1]){
                    int temp = list2[j+1];
                    list2[j+1] = list2[j];
                    list2[j] = temp;
                    flag = true;
                }
            }
            Arrays.stream(list2).map(s->s+"-").forEach(System.out::print);
            System.out.println();
        }
    }
}
