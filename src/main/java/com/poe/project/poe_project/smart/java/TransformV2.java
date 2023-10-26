package com.poe.project.poe_project.smart.java;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poe.project.poe_project.utils.JsonUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Maps;
import sun.java2d.pipe.SpanIterator;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zyb
 * @Description 进度条：测试转换
 * @date 2023/6/30 10:34
 */
public class TransformV2 {

    private static JsonUtils jsonUtils=new JsonUtils(new ObjectMapper());
    private static final  String json="[{\"index_full_code\":\"F0203ZB000401LX0101\",\"time_name\":\"2011\",\"unit_name\":\"亿元\",\"val\":346.14,\"type_code\":\"LX0101\",\"index_code_full_cname\":\"第三产业增加值\"},{\"unit_name\":\"%\",\"index_code_full_cname\":\"地区生产总值增速\",\"val\":12.7,\"time_name\":\"2011\",\"type_code\":\"LX0102\",\"index_full_code\":\"F01ZB000401LX0102\"},{\"time_name\":\"2011\",\"index_code_full_cname\":\"第一产业增加值\",\"type_code\":\"LX0101\",\"unit_name\":\"亿元\",\"val\":69.83,\"index_full_code\":\"F0201ZB000401LX0101\"},{\"type_code\":\"LX0101\",\"index_code_full_cname\":\"第二产业增加值\",\"index_full_code\":\"F0202ZB000401LX0101\",\"val\":195.55,\"time_name\":\"2011\",\"unit_name\":\"亿元\"},{\"val\":408.78,\"time_name\":\"2012\",\"index_code_full_cname\":\"第三产业增加值\",\"type_code\":\"LX0101\",\"unit_name\":\"亿元\",\"index_full_code\":\"F0203ZB000401LX0101\"},{\"unit_name\":\"亿元\",\"type_code\":\"LX0101\",\"index_full_code\":\"F0201ZB000401LX0101\",\"index_code_full_cname\":\"第一产业增加值\",\"val\":75.25,\"time_name\":\"2012\"},{\"unit_name\":\"%\",\"time_name\":\"2012\",\"type_code\":\"LX0102\",\"index_full_code\":\"F01ZB000401LX0102\",\"val\":11.8,\"index_code_full_cname\":\"地区生产总值增速\"},{\"unit_name\":\"亿元\",\"type_code\":\"LX0101\",\"index_code_full_cname\":\"第二产业增加值\",\"time_name\":\"2012\",\"index_full_code\":\"F0202ZB000401LX0101\",\"val\":226.13},{\"type_code\":\"LX0101\",\"index_full_code\":\"F0201ZB000401LX0101\",\"index_code_full_cname\":\"第一产业增加值\",\"val\":81.21,\"unit_name\":\"亿元\",\"time_name\":\"2013\"},{\"type_code\":\"LX0101\",\"time_name\":\"2013\",\"index_full_code\":\"F0203ZB000401LX0101\",\"val\":476.21,\"unit_name\":\"亿元\",\"index_code_full_cname\":\"第三产业增加值\"},{\"time_name\":\"2013\",\"index_code_full_cname\":\"第二产业增加值\",\"type_code\":\"LX0101\",\"unit_name\":\"亿元\",\"val\":270.78,\"index_full_code\":\"F0202ZB000401LX0101\"},{\"index_full_code\":\"F01ZB000401LX0102\",\"time_name\":\"2013\",\"type_code\":\"LX0102\",\"unit_name\":\"%\",\"val\":12.1,\"index_code_full_cname\":\"地区生产总值增速\"},{\"type_code\":\"LX0101\",\"unit_name\":\"亿元\",\"index_code_full_cname\":\"第一产业增加值\",\"val\":88.11,\"time_name\":\"2014\",\"index_full_code\":\"F0201ZB000401LX0101\"},{\"index_full_code\":\"F0203ZB000401LX0101\",\"index_code_full_cname\":\"第三产业增加值\",\"unit_name\":\"亿元\",\"val\":541.29,\"time_name\":\"2014\",\"type_code\":\"LX0101\"},{\"index_full_code\":\"F01ZB000401LX0102\",\"time_name\":\"2014\",\"val\":10.8,\"type_code\":\"LX0102\",\"index_code_full_cname\":\"地区生产总值增速\",\"unit_name\":\"%\"},{\"time_name\":\"2014\",\"index_full_code\":\"F0202ZB000401LX0101\",\"unit_name\":\"亿元\",\"index_code_full_cname\":\"第二产业增加值\",\"type_code\":\"LX0101\",\"val\":310.34},{\"val\":93.61,\"index_code_full_cname\":\"第一产业增加值\",\"time_name\":\"2015\",\"unit_name\":\"亿元\",\"index_full_code\":\"F0201ZB000401LX0101\",\"type_code\":\"LX0101\"},{\"time_name\":\"2015\",\"unit_name\":\"%\",\"val\":11,\"index_code_full_cname\":\"地区生产总值增速\",\"index_full_code\":\"F01ZB000401LX0102\",\"type_code\":\"LX0102\"},{\"time_name\":\"2015\",\"val\":341.81,\"type_code\":\"LX0101\",\"index_code_full_cname\":\"第二产业增加值\",\"index_full_code\":\"F0202ZB000401LX0101\",\"unit_name\":\"亿元\"},{\"time_name\":\"2015\",\"type_code\":\"LX0101\",\"unit_name\":\"亿元\",\"val\":607.58,\"index_full_code\":\"F0203ZB000401LX0101\",\"index_code_full_cname\":\"第三产业增加值\"},{\"type_code\":\"LX0101\",\"index_code_full_cname\":\"第一产业增加值\",\"val\":110.52,\"index_full_code\":\"F0201ZB000401LX0101\",\"unit_name\":\"亿元\",\"time_name\":\"2016\"},{\"type_code\":\"LX0101\",\"time_name\":\"2016\",\"unit_name\":\"亿元\",\"index_full_code\":\"F0203ZB000401LX0101\",\"index_code_full_cname\":\"第三产业增加值\",\"val\":672.07},{\"unit_name\":\"%\",\"index_code_full_cname\":\"地区生产总值增速\",\"index_full_code\":\"F01ZB000401LX0102\",\"type_code\":\"LX0102\",\"val\":10,\"time_name\":\"2016\"},{\"index_code_full_cname\":\"第二产业增加值\",\"unit_name\":\"亿元\",\"time_name\":\"2016\",\"val\":390.41,\"type_code\":\"LX0101\",\"index_full_code\":\"F0202ZB000401LX0101\"},{\"time_name\":\"2017\",\"val\":118.15,\"unit_name\":\"亿元\",\"index_code_full_cname\":\"第一产业增加值\",\"type_code\":\"LX0101\",\"index_full_code\":\"F0201ZB000401LX0101\"},{\"time_name\":\"2017\",\"val\":469.61,\"unit_name\":\"亿元\",\"index_code_full_cname\":\"第二产业增加值\",\"index_full_code\":\"F0202ZB000401LX0101\",\"type_code\":\"LX0101\"},{\"val\":761.24,\"unit_name\":\"亿元\",\"type_code\":\"LX0101\",\"index_full_code\":\"F0203ZB000401LX0101\",\"index_code_full_cname\":\"第三产业增加值\",\"time_name\":\"2017\"},{\"val\":10,\"index_full_code\":\"F01ZB000401LX0102\",\"time_name\":\"2017\",\"unit_name\":\"%\",\"index_code_full_cname\":\"地区生产总值增速\",\"type_code\":\"LX0102\"},{\"val\":837.33,\"unit_name\":\"亿元\",\"time_name\":\"2018\",\"type_code\":\"LX0101\",\"index_code_full_cname\":\"第三产业增加值\",\"index_full_code\":\"F0203ZB000401LX0101\"},{\"type_code\":\"LX0102\",\"unit_name\":\"%\",\"index_full_code\":\"F01ZB000401LX0102\",\"val\":8.9,\"time_name\":\"2018\",\"index_code_full_cname\":\"地区生产总值增速\"},{\"unit_name\":\"亿元\",\"type_code\":\"LX0101\",\"index_code_full_cname\":\"第一产业增加值\",\"time_name\":\"2018\",\"val\":128.34,\"index_full_code\":\"F0201ZB000401LX0101\"},{\"unit_name\":\"亿元\",\"val\":582.72,\"time_name\":\"2018\",\"index_code_full_cname\":\"第二产业增加值\",\"index_full_code\":\"F0202ZB000401LX0101\",\"type_code\":\"LX0101\"},{\"val\":8.1,\"index_full_code\":\"F01ZB000401LX0102\",\"time_name\":\"2019\",\"index_code_full_cname\":\"地区生产总值增速\",\"type_code\":\"LX0102\",\"unit_name\":\"%\"},{\"type_code\":\"LX0101\",\"index_code_full_cname\":\"第一产业增加值\",\"time_name\":\"2019\",\"val\":138.19,\"index_full_code\":\"F0201ZB000401LX0101\",\"unit_name\":\"亿元\"},{\"val\":635.62,\"type_code\":\"LX0101\",\"time_name\":\"2019\",\"unit_name\":\"亿元\",\"index_full_code\":\"F0202ZB000401LX0101\",\"index_code_full_cname\":\"第二产业增加值\"},{\"type_code\":\"LX0101\",\"time_name\":\"2019\",\"index_full_code\":\"F0203ZB000401LX0101\",\"index_code_full_cname\":\"第三产业增加值\",\"unit_name\":\"亿元\",\"val\":924.01},{\"unit_name\":\"%\",\"time_name\":\"2020\",\"index_code_full_cname\":\"地区生产总值增速\",\"index_full_code\":\"F01ZB000401LX0102\",\"type_code\":\"LX0102\",\"val\":7.8},{\"val\":150.3,\"unit_name\":\"亿元\",\"type_code\":\"LX0101\",\"index_code_full_cname\":\"第一产业增加值\",\"time_name\":\"2020\",\"index_full_code\":\"F0201ZB000401LX0101\"},{\"index_full_code\":\"F0202ZB000401LX0101\",\"index_code_full_cname\":\"第二产业增加值\",\"time_name\":\"2020\",\"unit_name\":\"亿元\",\"type_code\":\"LX0101\",\"val\":714.9},{\"index_full_code\":\"F0203ZB000401LX0101\",\"type_code\":\"LX0101\",\"unit_name\":\"亿元\",\"index_code_full_cname\":\"第三产业增加值\",\"time_name\":\"2020\",\"val\":1037.5},{\"unit_name\":\"亿元\",\"val\":1158.77,\"type_code\":\"LX0101\",\"index_code_full_cname\":\"第三产业增加值\",\"index_full_code\":\"F0203ZB000401LX0101\",\"time_name\":\"2021\"},{\"val\":6.7,\"index_full_code\":\"F01ZB000401LX0102\",\"time_name\":\"2021\",\"unit_name\":\"%\",\"type_code\":\"LX0102\",\"index_code_full_cname\":\"地区生产总值增速\"},{\"index_code_full_cname\":\"第一产业增加值\",\"unit_name\":\"亿元\",\"time_name\":\"2021\",\"index_full_code\":\"F0201ZB000401LX0101\",\"type_code\":\"LX0101\",\"val\":164.12},{\"index_code_full_cname\":\"第二产业增加值\",\"time_name\":\"2021\",\"type_code\":\"LX0101\",\"index_full_code\":\"F0202ZB000401LX0101\",\"val\":757.28,\"unit_name\":\"亿元\"},{\"val\":180.16,\"time_name\":\"2022\",\"index_full_code\":\"F0201ZB000401LX0101\",\"type_code\":\"LX0101\",\"unit_name\":\"亿元\",\"index_code_full_cname\":\"第一产业增加值\"},{\"index_code_full_cname\":\"第三产业增加值\",\"type_code\":\"LX0101\",\"index_full_code\":\"F0203ZB000401LX0101\",\"val\":1147.81,\"unit_name\":\"亿元\",\"time_name\":\"2022\"},{\"type_code\":\"LX0101\",\"index_full_code\":\"F0202ZB000401LX0101\",\"val\":804.67,\"index_code_full_cname\":\"第二产业增加值\",\"time_name\":\"2022\",\"unit_name\":\"亿元\"},{\"index_full_code\":\"F01ZB000401LX0102\",\"unit_name\":\"%\",\"type_code\":\"LX0102\",\"index_code_full_cname\":\"地区生产总值增速\",\"val\":1.1,\"time_name\":\"2022\"}]\n";
    private static final List<String> transFields= Lists.newArrayList("time_name");
    private static final List<String> fieldList= Lists.newArrayList("time_name");
    private static final List<String> renameFieldList= Lists.newArrayList("type_code","index_code_full_cname");
    private static final String renameField="time_name";
    private static  List<Map<String,Object>> list = jsonUtils.parseObject(json, new TypeReference<List<Map<String,Object>>>() {
    });


    public static List<Map<String,Object>> transformV2() {
        Map<String, Map<String, Object>> dataMap = new HashMap<>();
        List<String> codeList = new ArrayList<>();
        List<Map<String, Object>> result = new ArrayList<>();


        Map<String, Integer> increasMap = new HashMap<String, Integer>();

        Map<String, String> sortedMap = new HashMap() {
            {
                put("F0201ZB000401LX0101", "2");
                put("F0202ZB000401LX0101", "3");
                put("F0203ZB000401LX0101", "4");
                put("F01ZB000401LX0102", "1");
                put("sortedField", "index_full_code");
            }
        };
        System.out.println(sortedMap);
        list = list.stream().sorted(Comparator.comparing(s -> {
            String sortKey = String.valueOf(sortedMap.getOrDefault("sortedField", "type_code"));
            String sortKeyValue = s.get(sortKey).toString();
            return sortedMap.getOrDefault(sortKeyValue, "0");

        })).collect(Collectors.toList());

        for (Map<String, Object> row : list) {
            StringBuilder code = new StringBuilder();
            for (String codeName : transFields) {
                code.append(row.get(codeName));
            }
            if (code.toString().equals("")) {
                continue;
            }
            int num = 1;
            Map<String, Object> data = new HashMap<>();
            String rename = String.valueOf(row.get(renameField));
            if (dataMap.containsKey(code.toString())) {
                //说明存在相同 renameField的数据，此时进行数据合并，但是key值累加  此时应该从2开始累加
                data = dataMap.get(code.toString());
                num = increasMap.get(code.toString());
                num++;
                increasMap.put(code.toString(), num);
            } else {
                codeList.add(code.toString());
                increasMap.put(code.toString(), num);
            }
            if (StringUtils.isNoneBlank(rename)) {
                for (String field : renameFieldList) {
                    if (row.containsKey(field)) {
                        if (num != 0) {
                            data.put(field + "_" + num, row.get(field));
                        } else {
                            data.put(field, row.get(field));
                        }
                    }
                }
            }
            for (String field : fieldList) {
                if (row.containsKey(field)) {
                    data.put(field, row.get(field));
                }
            }
            dataMap.put(code.toString(), data);
        }
        for (String code : codeList) {
            Map<String, Object> map = dataMap.get(code);
            result.add(map);
        }
        return result;
    }


    public static void main(String[] args) {

        List<Map<String, Object>> result = transformV2();

        System.out.println(jsonUtils.toJSONString(result));


    }
}
