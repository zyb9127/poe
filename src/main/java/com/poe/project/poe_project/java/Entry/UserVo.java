package com.poe.project.poe_project.java.Entry;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author poe.zhang
 * @date 2022年08月19日 15:23
 * @description:用户
 */
@Data
@AllArgsConstructor
public class UserVo implements  Cloneable {
    private String name;
    private String color;
    private String sex;

    @Override
    protected UserVo clone() throws CloneNotSupportedException {
        return (UserVo)super.clone();
    }
}
