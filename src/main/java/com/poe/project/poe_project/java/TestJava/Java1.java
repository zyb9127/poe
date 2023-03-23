package com.poe.project.poe_project.java.TestJava;

import com.poe.project.poe_project.java.Entry.PersonVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zhangyabo
 * @description: TODO
 * @date 2022/2/8 17:58
 */
public class Java1 {

    private final static String MAX_VALUE = "9223372036854775807";
    private final static String MIN_VALUE = "-9223372036854775808";

    public static final  List<PersonVo> PERSONLIST=new ArrayList<>();
    static{
        PERSONLIST.add(new PersonVo("1",null,11));
        PERSONLIST.add(new PersonVo("3",null,13));
        PERSONLIST.add(new PersonVo("4",null,14));
        PERSONLIST.add(new PersonVo("5",null,15));
        PERSONLIST.add(new PersonVo("2",null,16));
        PERSONLIST.add(new PersonVo("2",null,16));
        PERSONLIST.add(new PersonVo("2",null,12));
        PERSONLIST.add(new PersonVo("2",null,13));
        PERSONLIST.add(new PersonVo("2",null,null));
    }

    public static final Map<String, PersonVo> PERSONMAP  = new HashMap();
    static {
        PERSONMAP.put("key1", new PersonVo("key1",null,11));
        PERSONMAP.put("key2", new PersonVo("key2",null,13));
        PERSONMAP.put("key3", new PersonVo("key3",null,14));
        PERSONMAP.put("key4", new PersonVo("key4",null,15));
        PERSONMAP.put("key5", new PersonVo("key5",null,16));
        PERSONMAP.put("key6", new PersonVo("key6",null,16));
        PERSONMAP.put("key7", new PersonVo("key7",null,12));
        PERSONMAP.put("key8", new PersonVo("key8",null,13));
        PERSONMAP.put("key9", new PersonVo("key9",null,13));
    }





    public static void main(String[] args) {
        List<String> repeatString= Lists.newArrayList("1","2","3","1","2");
        Map<String, Long> countMap = repeatString.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(countMap);

        String s="0.0.0.0";
        System.out.println(StringUtils.countMatches(s,"."));
        Map<String, Integer> sourceNameMap=new HashMap<>();
        PERSONLIST.forEach(
                r->{
                    sourceNameMap.put(r.getName(),r.getFlag());
                }
        );
        System.out.println(sourceNameMap);
        bl();
    }


    public  static void getLongValue() {
        String[] strings = {"110", "1101234", "1105678", "1101212"};
        Arrays.sort(strings);
        System.out.println(Arrays.toString(strings));
    }


    public static void bl(){
        List<Integer> list= Lists.newArrayList(1,2,3,4,5);
        Object[] errorArray = list.toArray(new Object[0]);

        System.out.println(errorArray.length);
        List<Integer> list1= new CopyOnWriteArrayList<>(list);
        for (Integer m : list1) {
            list1.add(m+99);
        }
        list1.forEach(
                m->{
                    list1.add(m+99);
                }
        );
        Map<String, String> map=new HashMap<>();
        map.put("1","1");
        map.put("2","1");
        map.put("3","1");
        Map<String, String> map1=new ConcurrentHashMap<>(map);
        map1.forEach((k, v) -> {
            if("3".equals(k)){
                map1.put("33",v);
                map1.remove(k);
            }
        });
        System.out.println(map1);
        List<String> listString= Lists.newArrayList("0","1","2","3");
        listString= listString.stream().sorted(Comparator.comparing(s -> Integer.valueOf(s))).collect(Collectors.toList());
        System.out.println(listString);


    }
}
