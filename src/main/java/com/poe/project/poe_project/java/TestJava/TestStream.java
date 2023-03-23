package com.poe.project.poe_project.java.TestJava;

import jdk.nashorn.internal.objects.annotations.Setter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhangyabo
 * @description: TODO
 * @date 2021/8/9 3:30 下午
 */
public class TestStream {
   /* public static void main(String[] args) {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));
        personList.add(new Person("Owen", 9500, 25, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 26, "female", "New York"));

        Optional<Person> maxMoney = personList.stream().max(Comparator.comparingInt(Person::getSalary));

        Integer maxSalary = personList.stream().reduce(0, (max, p) -> max > p.getSalary() ? max : p.getSalary(),
                Integer::max);

        Optional<Integer> sum2 = personList.stream().map(Person::getSalary).reduce(Integer::sum);

        // 求最高工资
        Optional<Integer> maxMoney2 = personList.stream().map(Person::getSalary).collect(Collectors.maxBy(Integer::compare));
        Map<Integer, Object> collect = personList.stream().map(e ->

                new Object()


        ).collect(Collectors.toMap(Object::hashCode, e -> e));


        System.out.println("员工工资最大值：" + maxMoney.get().getSalary());
        System.out.println("员工工资最大值：" +maxSalary);
        System.out.println("员工工资最大值：" + maxMoney2);




    }*/
}

