package com.poe.project.poe_project.smart.java;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poe.project.poe_project.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zyb
 * @Description 测试转换
 * @date 2023/6/30 10:34
 */
public class Transform {

    private static JsonUtils jsonUtils=new JsonUtils(new ObjectMapper());
    private static final  String json="[{\"type_code\":\"LX0301\",\"type_name\":\"累计值\",\"region_code\":\"540000\",\"index_code_full_index_name\":\"高新数字产业项目投资\",\"group_name\":\"全部\",\"change_name\":\"高新数字产业项目投资（亿元）\",\"index_name\":\"高新数字产业项目投资\",\"unit_name\":\"亿元\",\"time_name\":\"202305\",\"index_code_full_type_name\":\"累计值\",\"index_code_full_unit_name\":\"亿元\",\"index_code_full_cname\":\"高新数字产业项目投资\",\"region_name\":\"西藏自治区\",\"index_code\":\"ZB0007121701\",\"group_code\":\"F01\",\"index_full_code\":\"F01ZB0007121701LX0301\",\"val\":\"11.96\"},{\"index_code_full_type_name\":\"年度目标\",\"change_name\":\"高新数字产业投资年度目标（亿元）\",\"index_code_full_index_name\":\"高新数字产业项目投资\",\"index_full_code\":\"F01ZB0007121701LX0118\",\"group_name\":\"全部\",\"type_name\":\"规划目标值\",\"group_code\":\"F01\",\"index_code\":\"ZB0007121701\",\"index_code_full_cname\":\"高新数字产业投资年度目标\",\"index_name\":\"高新数字产业投资年度目标\",\"index_code_full_unit_name\":\"亿元\",\"type_code\":\"LX0118\"},{\"index_code_full_type_name\":\"完成年度目标占比\",\"index_name\":\"高新数字产业项目投资完成年度目标进度\",\"index_full_code\":\"F01ZB0007121701LX1002\",\"group_code\":\"F01\",\"group_name\":\"全部\",\"index_code\":\"ZB0007121701\",\"type_name\":\"累计占比\",\"region_name\":\"西藏自治区\",\"time_name\":\"202305\",\"unit_name\":\"%\",\"type_code\":\"LX1002\",\"val\":\"51.9\",\"index_code_full_cname\":\"高新数字产业项目投资完成年度目标进度\",\"change_name\":\"高新数字产业项目投资完成年度目标进度（）\",\"index_code_full_index_name\":\"高新数字产业项目投资\",\"index_code_full_unit_name\":\"\",\"region_code\":\"540000\"}]";
    private static final List<String> transFields= Lists.newArrayList("group_code");
    private static final List<String> fieldList= Lists.newArrayList("group_code");
    private static final List<String> renameFieldList= Lists.newArrayList("type_code","val","unit_name","index_code_full_cname","change_name");
    private static final String renameField="type_code";
    private static final List<Map<String,Object>> list = jsonUtils.parseObject(json, new TypeReference<List<Map<String,Object>>>() {
    });


    public static List<Map<String,Object>> transform(){
        Map<String, Map<String, Object>> dataMap = new HashMap<>();
        List<String> codeList=new ArrayList<>();
        List<Map<String,Object>> result=new ArrayList<>();


        Map<String, Integer> increasMap = new HashMap<String, Integer>();

        for (Map<String, Object> row : list) {
            StringBuilder code = new StringBuilder();
            for (String codeName :transFields) {
                code.append(row.get(codeName));
            }
            if (code.toString().equals("")) {
                continue;
            }
            int num=1;
            Map<String, Object> data = new HashMap<>();
            if (dataMap.containsKey(code.toString())) {
                //说明存在相同 renameField的数据，此时进行数据合并，但是key值累加  此时应该从2开始累加
                data = dataMap.get(code.toString());
                num=increasMap.get(code.toString());
                num++;
                increasMap.put(code.toString(),num);
            }else{
                codeList.add(code.toString());
                increasMap.put(code.toString(),num);
            }
            String rename = String.valueOf(row.get(renameField));
            if (StringUtils.isNoneBlank(rename)) {
                for (String field : renameFieldList) {
                    if (row.containsKey(field)) {
                        if(num!=0){
                            data.put(field+ "_"+num, row.get(field));
                        }else{
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

        List<Map<String, Object>> result = transform();

        System.out.println(jsonUtils.toJSONString(result));


    }
}
