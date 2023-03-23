package com.poe.project.poe_project.java.TestJava;

import com.poe.project.poe_project.java.Entry.SortVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhangyabo
 * @description: TODO
 * @date 2022/2/19 18:34
 */
public class TestList {

    public static void main(String[] args) {
        //排序
        List<Integer> list = Lists.newArrayList(1, 2, 4, 5, 7, 8, 3);
        List<String> list11 = Lists.newArrayList("1", "2", "4");
        //list=list.stream().sorted(Integer::compare).collect(Collectors.toList());
        //按A升序，按B降序
        List<SortVo> list2 = Lists.newArrayList(new SortVo(1,1),new SortVo(2,2),new SortVo(null,1),new SortVo(null,2));
        list2=list2.stream().sorted(Comparator.comparing((Function<SortVo,Integer>)s->s.getA(),Comparator.nullsLast(Integer::compareTo)).thenComparing(SortVo::getB,Comparator.reverseOrder())).collect(Collectors.toList());//先以属性一升序,再进行属性二降序
         //System.out.println(list2);


        //判断关键属性是否冲突
        StringBuilder s2 = new StringBuilder("CONCAT(");
        StringBuilder s3 = new StringBuilder();
        list.forEach(key -> {
                    s2.append(" n.content.").append(key).append(",'-',");
                    s3.append(key).append("-");
                }
        );



        Map<String,Long> map1=new HashMap<>();
        map1.put("a",1l);
        map1.put("b",1l);
        Map<String,Number> map2=new HashMap<>();
        map2.put("a",1);
        map2.put("b",1);

        for (String s : map2.keySet()) {
            if(map1.containsKey(s)){
                map1.put(s, map2.get(s).longValue()+map1.get(s));
            }else{
                map1.put(s, map2.get(s).longValue());
            }
        }
        System.out.println(map1);

        List<Map> list7=new ArrayList();
        Map<Object, Object> collect = list7.stream().collect(Collectors.toMap(x -> x.get("key"), y -> y.get("num")));
        System.out.println(collect);
        StringBuffer returnAql = new StringBuffer("return abcd");
        returnAql.deleteCharAt(returnAql.length() - 2);
        System.out.println(returnAql.toString());
        String join = String.join("','", list11);
        System.out.println(join);




        List<Object> list111=Lists.newArrayList(1,3,2,4);
        List<Object> list222=Lists.newArrayList(1,2,3,4);
        System.out.println(CollectionUtils.isEqualCollection(list111,list222));



    }
}
