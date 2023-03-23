package com.poe.project.poe_project.java.cmdbTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poe.project.poe_project.java.Entry.CiUnitUsageVo;
import com.poe.project.poe_project.utils.JsonUtils;
import org.apache.commons.lang3.ObjectUtils;
import com.google.common.collect.Lists;


import java.util.*;

/**
 * @author poe.zhang
 * @date 2022年10月28日 14:31
 * @description:测试U位复合
 */
public class 测试U位复合 {
    public static final Map<String, Object> ciContent  = new HashMap();
    static {
        ciContent.put("dataRange", Lists.newArrayList(1, 20));
    }

    public static final List<Map<String,Object>> holdUCiList  = new ArrayList<>();
    static {
        Map<String,Object> holdMap1=new HashMap<>();
        holdMap1.put("range",Lists.newArrayList(1l,2l));
        holdMap1.put("model_id","a");
        holdMap1.put("ci_id","1");
        holdMap1.put("ci_name","a1");
        holdUCiList.add(holdMap1);
        Map<String,Object> holdMap2=new HashMap<>();
        holdMap2.put("range",Lists.newArrayList(2l,3l));
        holdMap2.put("model_id","b");
        holdMap2.put("ci_id","1");
        holdMap2.put("ci_name","b1");
        holdUCiList.add(holdMap2);
        Map<String,Object> holdMap3=new HashMap<>();
        holdMap3.put("range",Lists.newArrayList(1l,3l));
        holdMap3.put("model_id","c");
        holdMap3.put("ci_id","1");
        holdMap3.put("ci_name","c1");
        holdUCiList.add(holdMap3);
        Map<String,Object> holdMap4=new HashMap<>();
        holdMap4.put("range",Lists.newArrayList(4l,6l));
        holdMap4.put("model_id","d");
        holdMap4.put("ci_id","1");
        holdMap4.put("ci_name","d1");
        holdUCiList.add(holdMap4);
        Map<String,Object> holdMap5=new HashMap<>();
        holdMap5.put("range",Lists.newArrayList(9l,10l));
        holdMap5.put("model_id","e");
        holdMap5.put("ci_id","1");
        holdMap5.put("ci_name","e1");
        holdUCiList.add(holdMap5);
    }

    private static final JsonUtils jsonUtils=new JsonUtils(new ObjectMapper());



    public static void main(String[] args) {
        //List<Long> checkRange = Lists.newArrayList(3l, 4l);
        List<Long> checkRange = new ArrayList<>();
        getUBitUsage(checkRange);
    }


    public static List<CiUnitUsageVo>  getUBitUsage(List<Long> checkRange){
        List<CiUnitUsageVo> result = new ArrayList<>();

        List<Long> rangeList = jsonUtils.parseObject(ciContent.get("dataRange"), new TypeReference<List<Long>>() {
        });
        //可选上限
        Long startNum = 1l;
        //可选下限
        Long endNum = 1l;
        if(ObjectUtils.isNotEmpty(rangeList)){
            startNum = rangeList.get(0);
            endNum = rangeList.get(1);
        }

        //校验开始上限
        Long checkStartNum = 1l;
        //校验结束下限
        Long checkEndNum = Math.min(30l, 100L);
        //需要校验的上下限范围
        if(ObjectUtils.isNotEmpty(checkRange)){
            checkStartNum = Math.max(checkRange.get(0),0l);
            checkEndNum = Math.min(checkRange.get(1),checkEndNum);
        }
        Long  max=null;
        if(ObjectUtils.isNotEmpty(holdUCiList)){
            Collection<Long> allRanges = new ArrayList<>();
            holdUCiList.forEach(holder->{
                List<Long> range = (List<Long>)holder.get("range");
                allRanges.addAll(range);
            });
            max=Collections.max(allRanges);
        }
        for (int i = checkStartNum.intValue(); i < checkEndNum.intValue()+1; i++) {
            CiUnitUsageVo unitVo = new CiUnitUsageVo();
            unitVo.setPoint(i);
            //设置置灰：如果当前点位存在与可选方位之内，不置灰
            unitVo.setAshFlag(i < startNum || i > endNum);
            if (ObjectUtils.isNotEmpty(holdUCiList)) {
                if (max != null && i <= max) {
                    for (Map<String, Object> holder : holdUCiList) {
                        List<Long> range = (List<Long>) holder.get("range");
                        //被占用
                        if ((i >= range.get(0)) && (i <= range.get(1))) {
                            unitVo.setUsedFlag(true)
                                    .setCiName(holder.get("ci_name").toString())
                                    .setCiId(holder.get("ci_id").toString())
                                    .setModelId(holder.get("model_id").toString());
                            break;
                        }
                    }
                } else {
                    unitVo.setUsedFlag(false);
                }
            } else {
                unitVo.setUsedFlag(false);
            }
            result.add(unitVo);
        }
        return result;
    }



}
