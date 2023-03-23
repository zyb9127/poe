package com.poe.project.poe_project.java.cmdbTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poe.project.poe_project.java.Entry.PersonVo;
import com.poe.project.poe_project.java.config.AttributeCheckErrorException;
import com.poe.project.poe_project.java.constant.ModelConstant;
import com.poe.project.poe_project.utils.JsonUtils;
import com.poe.project.poe_project.utils.SpringContextUtil;
import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import sun.misc.MessageUtils;

import java.io.IOException;
import java.util.*;

/**
 * @author poe.zhang
 * @date 2022年10月21日 16:26
 * @description:测试数字范围
 */
@Slf4j
public class 测试数字范围 {

    private final static String MAX_VALUE = "9223372036854775807";
    private final static String MIN_VALUE = "-9223372036854775808";

    private static JsonUtils jsonUtils=new JsonUtils(new ObjectMapper());

    public static final Map<String, Object> rules  = new HashMap();
    static {
        rules.put("attrName", "数字范围");
        rules.put("attrID", "dataRange");
        rules.put("key", "1661343915000979539");
        rules.put("type", "NUMBERRANGE");
        rules.put("explain", "数字范围");
        rules.put("name", "数字范围属性");
        rules.put("dataReport", false);
        rules.put("mustFill", false);
        rules.put("isOperate", true);
        rules.put("minNumber", 1);
        rules.put("maxNumber", 100);
        rules.put("startNumber", 1);
        rules.put("endNumber", 200);
        rules.put("uGroupId", "US1");
    }

    public static final Map<String, Object> ciContent  = new HashMap();
    static {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        ciContent.put("dataRange",list);
    }

    public static void main(String[] args) throws IOException {

        List<Long> dataRange1 = (List<Long>)ciContent.get("dataRange");
        List<Long> dataRange = jsonUtils.parseObjectThrow(ciContent.get("dataRange"), new TypeReference<List<Long>>() {
        });
        System.out.println(dataRange);
        //checkNumberRange(dataRange,rules);
       // checkNumberRange(rules);
    }


    /**
     * 配置项校验
     * @param range
     * @param rules
     * @throws AttributeCheckErrorException
     */
    private static void checkNumberRange(List<Object> range, Map<String, Object> rules) throws AttributeCheckErrorException {
        if (ObjectUtils.isEmpty(range)) {
            boolean mustFill = Optional.ofNullable((Boolean) rules.get("mustFill")).orElse(false);
            if (!mustFill) {
                return;
            }
            // error - must fill
            AttributeCheckErrorException checkError = new AttributeCheckErrorException();
            checkError.setAttrName(rules.getOrDefault("attrName", "unknown").toString());
            checkError.setReason("字段不能为空");
            throw checkError;
        }
        // length check
        long startNumber = Long.parseLong(rules.get(ModelConstant.START_NUMBER).toString());
        long endNumber = Long.parseLong(rules.get(ModelConstant.END_NUMBER).toString());
        if(range.size()!=2){
            AttributeCheckErrorException checkError = new AttributeCheckErrorException();
            checkError.setAttrName(rules.getOrDefault("attrName", "unknown").toString());
            checkError.setReason(String.format("字段值为%s，超出校验范围%s-%s", range, startNumber, endNumber));
            throw checkError;
        }
        Long start = Long.parseLong(range.get(0).toString());
        Long end = Long.parseLong(range.get(1).toString());
        if (start != null) {
            if (start < startNumber) {
                // error - min length
                AttributeCheckErrorException checkError = new AttributeCheckErrorException();
                checkError.setAttrName(rules.getOrDefault("attrName", "unknown").toString());
                checkError.setReason(String.format("字段值为%s，超出校验范围%s-%s", range, startNumber, endNumber));
                throw checkError;
            }
        }
        if (end != null) {
            if (end > endNumber||start>end) {
                // error - max length
                AttributeCheckErrorException checkError = new AttributeCheckErrorException();
                checkError.setAttrName(rules.getOrDefault("attrName", "unknown").toString());
                checkError.setReason(String.format("字段值为%s，超出校验范围%s-%s", range, startNumber, endNumber));
                throw checkError;
            }
        }
        log.debug("checkInteger returnValue: {}, rules: {}", range, rules);
        System.out.println(Lists.newArrayList(start,end));
    }

    /**
     * 模型校验
     * @param attrMap
     */
    private static void checkNumberRange(Map<String, Object> attrMap) {
        long start = 0;
        long minNumber = Long.parseLong(attrMap.getOrDefault(ModelConstant.MIN_NUMBER, 1).toString());
        long maxNumber = Long.parseLong(attrMap.getOrDefault(ModelConstant.MAX_NUMBER, 100).toString());
        if (ObjectUtils.isNotEmpty(attrMap.get(ModelConstant.START_NUMBER))) {
            start = getLongValue(attrMap.get("attrName").toString(), attrMap.get(ModelConstant.START_NUMBER).toString());
            if (start < minNumber) {
                // 默认值不合法
                System.out.println("开始值不合法");
            }
        }else{
            System.out.println("开始值不能为空");
        }
        if (ObjectUtils.isNotEmpty(attrMap.get(ModelConstant.END_NUMBER))) {
            long end = getLongValue(attrMap.get("attrName").toString(), attrMap.get(ModelConstant.END_NUMBER).toString());
            if (maxNumber < end) {
                // 默认值不合法
                System.out.println("结束值不合法");
            }
            if (end < start) {
                // 最大值不能小于最小值
                System.out.println("最大值不能小于最小值");

            }
        }else{
            System.out.println("结束值不能为空");
        }
    }

    private static Long getLongValue(String name, String strLong) {
        if (null == StringUtils.trimToNull(strLong) || strLong.trim().length() > 20) {
            // 值不合法
            System.out.println("值不合法");
        }

        strLong = strLong.trim();
        String tmpValue;
        if (strLong.charAt(0) == '-') {
            tmpValue = MIN_VALUE;
        } else if (strLong.charAt(0) == '+') {
            tmpValue = "+" + MAX_VALUE;
        } else {
            tmpValue = MAX_VALUE;
        }
        if (strLong.length() < 20) {
            return Long.parseLong(strLong);
        }
        for (int i = 0; i < strLong.length(); i++) {
            if (strLong.charAt(i) > tmpValue.charAt(i)) {
                // 值不合法
                System.out.println("值不合法");
            }
            if (strLong.charAt(i) < tmpValue.charAt(i)) {
                return Long.parseLong(strLong);
            }
        }
        return Long.parseLong(strLong);
    }

}
