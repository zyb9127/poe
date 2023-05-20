package com.poe.project.poe_project.java.Algorithm;

import org.assertj.core.util.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 插入排序 {
    public static void main(String[] args) {

        List<Integer> list1 = Lists.list(3,1, 2, 5, 7, 9, 4);
        Integer[] array = list1.toArray(new Integer[0]);
        insertionSort(array);
    }

    /**
     * 插入排序
     * @param arr
     */
    public static void insertionSort(Integer[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
            Arrays.stream(arr).map(s->s+"-").forEach(System.out::print);
            System.out.println();
        }
    }




}
