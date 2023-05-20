package com.poe.project.poe_project.java.Algorithm;

import org.assertj.core.util.FailureMessages;
import org.assertj.core.util.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/**
 * 每一轮都比较相邻两个元素的大小
 * 第一轮 最大的在右边
 * 第二轮 最大的在右边第二个
 * 。。。
 */
public class 冒泡排序 {
    public static void main(String[] args) {

        List<Integer> list = Lists.list(6,7,8,5,3,11,9);
        int length=list.size();
        Integer[] list1 =list.toArray(new Integer[length]);
        quickSort(list1,0,list1.length-1);
        Arrays.stream(list1).map(s->s+"-").forEach(System.out::print);
    }
    public static int getMiddle(Integer[] array,int low,int hight){
            int base=array[hight];
            int i=low-1;
            for (int j = low; j <hight ; j++) {
                if(array[j]<base){
                    i++;
                    swap(array,i,j);
                }
            }
            i++;
            swap(array,i,hight);
        return i;
    }
    public static void quickSort(Integer[] array,int low,int hight){
        if(low<hight){
            int middle = getMiddle(array, low, hight);
            quickSort(array,0,middle-1);
            quickSort(array,middle+1,hight);
        }
    }









    private static void swap(Integer[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}
