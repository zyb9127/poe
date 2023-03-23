package com.poe.project.poe_project.java.Entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author roubin
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class ModelGroupVo {
    /**
     * 账户ID信息，与其他产品保持统一
     */
    private String accountId;
    /**
     * 分组ID，分组信息的唯一标识
     */
    private String groupId;
    /**
     * 分组名称，用于UI显示
     */
    private String groupName;
    /**
     * 上层列别，分为业务资源，平台资源，基础设施
     */
    private String parentId;

    private int sort;

    public ModelGroupVo(String parentId,String groupName,String accountId,String groupId){
        this.parentId=parentId;
        this.groupName=groupName;
        this.accountId=accountId;
        this.groupId=groupId;
    }

}
