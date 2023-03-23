package com.poe.project.poe_project.java.cmdbTest;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.SetUtils;
import org.mockito.internal.util.collections.ListUtil;

import java.util.*;

/**
 * @author poe.zhang
 * @date 2022年11月03日 14:22
 * @description:测试占用复合
 */
public class 测试占用复合 {

    public static void main(String[] args) {

        List<List<Integer>> checkList = new ArrayList<>();
        checkList.add(Lists.newArrayList(1,3));
        checkList.add(Lists.newArrayList(2,4));
        checkList.add(Lists.newArrayList(3,5));
        checkList.add(Lists.newArrayList(1,6));

        List<List<Integer>> lists = new ArrayList<List<Integer>>(){
            {
                add(new ArrayList<Integer>() {{add(1);add(3);}});
                add(new ArrayList<Integer>() {{add(1);add(6);}});
                add(new ArrayList<Integer>() {{add(2);add(4);}});
                add(new ArrayList<Integer>() {{add(3);add(5);}});
            }
        };
        int[][] d = lists.stream()
                .map(list1->list1.stream().mapToInt(Integer::intValue).toArray())
                .toArray(int[][]::new);
        /*int[][] e = lists.stream()
                .map(list1->list1.stream().mapToInt(i->i.intValue()).toArray())
                .toArray(int[][]::new);
        int[][] f = lists.stream()
                .map(list1->list1.stream().mapToInt(i->i).toArray())
                .toArray(int[][]::new);*/


        //hasConflict(checkList);
        //System.out.println("######");
        //hasConflict2();
        testTransform();



    }
    public static void hasConflict(List<List<Integer>> checkList) {
        // 排序，从小到大
        checkList.sort(Comparator.comparing(x -> x.get(0)));
        for(int i=1;i<checkList.size();i++){
            // 如果与上一个区间有交集
            if(checkList.get(i-1).get(1)>=checkList.get(i).get(0)){
                System.out.println(checkList.get(i-1)+"和"+checkList.get(i)+"冲突");
            }
        }
    }
    public static void hasConflict2() {
        List<List<Integer>> checkList = new ArrayList<>();
        checkList.add(Lists.newArrayList(1,3));
        checkList.add(Lists.newArrayList(2,4));
        checkList.add(Lists.newArrayList(3,5));
        checkList.add(Lists.newArrayList(1,6));
        for (List<Integer> range1 : checkList) {
            for (List<Integer> range2 : checkList) {
                if(!ListUtils.isEqualList(range1,range2)){
                    if(Math.max(range1.get(0),range2.get(0))<=Math.min(range1.get(1),range2.get(1))){
                        System.out.println(range1+"和"+range2+"冲突");
                        break;
                    }
                }

            }
        }
    }
    public static boolean hasIntersection(int[][] intervals) {
        int len = intervals.length;
        if(len<2){
            return false;
        }
        // 排序，从小到大
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        for(int i=1;i<intervals.length;i++){
            // 如果与上一个区间有交集
            if (intervals[i-1][1]>=intervals[i][0]) {
                return true;
            }
        }
        return false;
    }

    public static void  testTransform(){
        long start=System.currentTimeMillis();
        List<Map<String,Long>> transform = new ArrayList<>();
        for (int i = 0; i < 50000; i++) {
            Map<String,Long> map=new HashMap<>();
            map.put("配置项"+i,System.currentTimeMillis());
            transform.add(map);
        }
        long end=System.currentTimeMillis();
        System.out.println("创建共执行："+(end-start)+"毫秒");

        long start1=System.currentTimeMillis();
        Map<String, Long> result = new HashMap<>();
        transform.forEach(result::putAll);
        System.out.println(result.size());
        long end1=System.currentTimeMillis();
        System.out.println("转换共执行："+(end1-start1)+"毫秒");
        System.out.println("总共共执行："+(end1-start)+"毫秒");



    }




}
