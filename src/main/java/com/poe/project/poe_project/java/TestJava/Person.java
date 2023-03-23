package com.poe.project.poe_project.java.TestJava;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Objects;

/**
 * @author zhangyabo
 * @description: TODO
 * @date 2021/8/9 3:33 下午
 */
@Data
public class Person {
    private String id;//唯一ID
    private String name;  // 姓名
    private int salary; // 薪资
    private int age; // 年龄
    private String sex; //性别
    private String area;  // 地区

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, Object> content;

}