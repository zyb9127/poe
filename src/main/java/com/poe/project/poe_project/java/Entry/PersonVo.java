package com.poe.project.poe_project.java.Entry;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * @author zhangyabo
 * @description: TODO
 * @date 2021/9/26 10:49 上午
 */

@Data
@AllArgsConstructor
public class PersonVo {

    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, Object> content;

    private Integer flag;




}
