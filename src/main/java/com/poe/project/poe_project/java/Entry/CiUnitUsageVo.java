package com.poe.project.poe_project.java.Entry;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author poe.zhang
 * @date 2022年10月28日 11:03
 * @description:U位占用Vo
 */
@Data
@Accessors(chain = true)
public class CiUnitUsageVo implements Serializable {
    /**
     *点位
     */
    private Integer point;
    /**
     *是否占用
     */
    private boolean usedFlag;
    /**
     *是否置灰
     */
    private boolean ashFlag;
    /**
     *模型ID
     */
    private String modelId;
    /**
     *配置项名称
     */
    private String ciName;
    /**
     *配置项ID
     */
    private String ciId;
}
