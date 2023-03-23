package com.poe.project.poe_project.java.TestJava;

import com.poe.project.poe_project.java.constant.ModelConstant;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.assertj.core.util.Lists;
import org.springframework.beans.BeanUtils;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhangyabo
 * @description: TODO
 * @date 2021/8/9 7:03 下午
 */
public class TestJava {


    public static void main(String[] args) {

        Map<Object,Object> map = new HashMap();
        boolean obj =(Boolean) map.getOrDefault("num",false);
        if(ObjectUtils.isNotEmpty(obj)){
            System.out.println(obj);
        }
        long l = Long.parseLong(String.valueOf(map.getOrDefault("2",0)));;




        merge();
    }

    public static void convert(Integer pageNum, Integer pageSize, List<T> list) {
        //总记录条数
        int totalRecord = list.size();
        //获取总页数
        int totalPage = totalRecord / pageSize;
        if (totalRecord % pageSize != 0) {
            totalPage = totalPage + 1;
        }

        int tempPageCount = pageNum * pageSize;
        // 起始索引
        int fromIndex = (pageNum - 1) * pageSize;
        // 结束索引
        int toIndex = tempPageCount >= totalRecord ? totalRecord : tempPageCount;
        List<T> dataList = list.subList(fromIndex, toIndex);

    }


    /**
     * 合并map,相同的key累加
     */
    public static void  mergeMap(){
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("id", "1");
        map1.put("value", 2);
        list.add(map1);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("id", "1");
        map2.put("value", 5);
        list.add(map2);

        Map<String, Object> map3 = new HashMap<>();
        map3.put("id", "2");
        map3.put("value", 5);
        list.add(map3);

        Map<String, Object> map4 = new HashMap<>();
        map4.put("id", "2");
        map4.put("value", 4);
        list.add(map4);

        Map<String, Object> map5 = new HashMap<>();
        map5.put("id", "1");
        map5.put("value", 10.2);
        list.add(map5);
        Map<String,Long> collectNameSumAge = list.stream().collect(Collectors.groupingBy(s -> String.valueOf(s.get("id")),Collectors.summingLong(s-> ((Number)s.getOrDefault("value",0)).longValue())));
        System.out.println(collectNameSumAge);
    }


    public static void  copy(){
       Person p=new Person();
       p.setId("12");
       p.setName("poe");
        Person newPerson = new Person();
        BeanUtils.copyProperties(p, newPerson);
        p.setName("roy");
        System.out.println(newPerson);
    }

    public static void  merge(){
        List<Integer> list1 = Lists.newArrayList(1, 2, 4, 5);
        List<Integer> list2 = Lists.newArrayList(1, 2, 3,4);
        List list11=new ArrayList<>(list1);
        List list22=new ArrayList<>(list2);




        //删除的
        list22.removeAll(list1);
        //新增的
        list11.removeAll(list2);
        System.out.println(list11);
        System.out.println(list22);



    }



}
