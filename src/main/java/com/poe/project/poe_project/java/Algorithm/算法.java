package com.poe.project.poe_project.java.Algorithm;

import org.assertj.core.util.Lists;

import java.util.Arrays;
import java.util.List;

/**
 * 每一轮选择最小值放到最左边
 * 第一轮 最左边是最小值
 * 第二轮 左边第二个是最小值
 * 。。。
 */
public class 算法 {
    public static void main(String[] args) {
        List<Integer> list = Lists.list(3,1, 2, 5,11, 7, 9, 4);
        int length=list.size();
        System.out.println("选择排序");
        Integer[] list1 =list.toArray(new Integer[length]);
        selectionSort(list1);
        System.out.println("二元选择排序");
        Integer[] list2 =list.toArray(new Integer[length]);
        cocktailSort(list2);
        System.out.println("插入排序");
        Integer[] list3 =list.toArray(new Integer[length]);
        insertionSort(list3);
        System.out.println("冒泡排序");
        Integer[] list4 =list.toArray(new Integer[length]);
        bubbleSort(list4);
        System.out.println("优化冒泡排序");
        Integer[] list5 =list.toArray(new Integer[length]);
        bubbleSort2(list5);
        System.out.println("快速排序");
        Integer[] list6 =list.toArray(new Integer[length]);
        quickSort(list6);
        Arrays.stream(list6).map(s->s+"-").forEach(System.out::print);

    }


    /**
     * 冒泡排序
     * @param arr
     */
    public static void bubbleSort(Integer[] arr) {
        int length=arr.length;
        for (int i = 0; i < length-1; i++) {
            for (int j = 0; j < length-1-i; j++) {
                int temp;
                if(arr[j]>arr[j+1]){
                    //转换
                    temp=arr[j+1];
                    arr[j+1]=arr[j];
                    arr[j]=temp;
                }
            }
            Arrays.stream(arr).map(s->s+"-").forEach(System.out::print);
            System.out.println();
        }
    }
    /**
     * 优化冒泡排序
     * @param arr
     */
    public static void bubbleSort2(Integer[] arr) {
        boolean flag = true;
        for(int i = 0;i < arr.length - 1;i++){
            if(!flag) break;
            flag = false;
            for(int j = 0;j < arr.length - 1 - i;j++){
                if(arr[j] > arr[j+1]){
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                    flag = true;
                }
            }
            Arrays.stream(arr).map(s->s+"-").forEach(System.out::print);
            System.out.println();
        }
    }



    /**
     * 选择排序
     * @param arr
     */
    public static void selectionSort(Integer[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i; // 假设当前未排序部分的第一个元素为最小值
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j; // 更新最小值的下标
                }
            }
            // 将最小值与未排序部分的第一个元素交换位置
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
            Arrays.stream(arr).map(s->s+"-").forEach(System.out::print);
            System.out.println();
        }
    }

    /**
     * 二元排序法
     * @param arr
     */
    public static void cocktailSort(Integer[] arr) {
        int left = 0; // 已排序部分的左边界
        int right = arr.length - 1; // 已排序部分的右边界
        while (left < right) {
            int minIndex = left;
            int maxIndex = right;
            // 在未排序部分中找到最小值和最大值的下标
            for (int i = left; i <= right; i++) {
                if (arr[i] < arr[minIndex]) {
                    minIndex = i;
                }
                if (arr[i] > arr[maxIndex]) {
                    maxIndex = i;
                }
            }
            // 将最小值放置到已排序部分的左端
            int temp = arr[minIndex];
            arr[minIndex] = arr[left];
            arr[left] = temp;
            // 如果最大值的下标等于left，说明最大值原本在未排序部分的左侧，已经被移到了已排序部分的左端，需要更新maxIndex
            if (maxIndex == left) {
                maxIndex = minIndex;
            }
            // 将最大值放置到已排序部分的右端
            temp = arr[maxIndex];
            arr[maxIndex] = arr[right];
            arr[right] = temp;
            left++;
            right--;
            Arrays.stream(arr).map(s->s+"-").forEach(System.out::print);
            System.out.println();
        }
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
    public static void swap(Integer[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 快速排序程序入口
     * @param array
     */
    public  static void quickSort(Integer[] array){
        quickSort(array,0,array.length-1);
    }

    /**
     * 快速排序真正方法：递归
     * @param array 排序数组
     * @param low 最小位置
     * @param high 最大位置
     */
    public  static void quickSort(Integer[] array,int low,int high){
        //至少一个元素的前提才进行排序
        if(low<high){
            //获取中间元素
            int middle = getMiddle(array, low, high);
            //基数不必在排，因为基数已经处于正确的位置
            //递归：对左边的数组进行排序
            quickSort(array,low,middle-1);
            //递归：对右边的数据进行排序
            quickSort(array,middle+1,high);
        }
    }
    public static int getMiddle(Integer[] array,int low,int high){
        //暂定最右侧为最小值
        int base=array[high];
        //定义初始位置 默认队首左侧
        int putIndex=low-1;
        //依次比较，知道找到比基数大的数据，这时候就要转换基数的位置
        for (int checkIndex = low; checkIndex < high; checkIndex++) {
            //如果当前元素比基数小，则放到最左边
            if(array[checkIndex]<base){
                //往前挪一位
                putIndex++;
                //数据往前挪动，依次放到左边
                swap(array,putIndex,checkIndex);
            }
        }
        putIndex++;
        //这时候把比基数大的数据和基数转换，这就把数组一分为二
        swap(array,putIndex,high);
        return putIndex;
    }





}
