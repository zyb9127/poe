package com.poe.project.poe_project.java.Entry;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CloneVo implements Cloneable {
    private String name;

    //浅克隆
    /*@Override
    public CloneVo clone(){
        CloneVo vo;
        try {
            vo = (CloneVo) super.clone();
        } catch (CloneNotSupportedException e) {
            vo = new CloneVo();
            BeanUtils.copyProperties(this, vo);
        }
        return vo;
    }*/

    //深克隆
    @Override
    public CloneVo clone(){
        CloneVo vo;
        try {
            vo = (CloneVo) super.clone();
            vo.userVo=userVo.clone();
        } catch (CloneNotSupportedException e) {
            vo = new CloneVo();
            BeanUtils.copyProperties(this, vo);
        }
        return vo;
    }

    private String color;
    private UserVo userVo;
}
