package com.poe.project.poe_project.java.TestJava;

import java.util.*;

/**
 * @author zhangyabo
 * @description: TODO
 * @date 2021/8/13 6:18 下午
 */
public class TestMd5_1 {

    /**
     * @param accountId    租户ID
     * @param modelId      模型ID
     * @param keys         关键属性key
     * @param preparedAttr 配置项属性信息
     * @return
     */
    private static String filterMapByKey(String accountId, String modelId, Set<String> keys, Map<String, Object> preparedAttr) {
        String cruxKeyString = getCruxKeyString(accountId, modelId, keys, preparedAttr);
        return Md5Utils.getMd5(cruxKeyString);
    }

    private static String getCruxKeyString(String accountId, String modelId, Set<String> keys, Map<String, Object> preparedAttr) {
        StringBuilder sb = new StringBuilder();
        sb.append(accountId).append("_");
        sb.append(modelId).append("_");
        for (String key : keys) {
            sb.append(preparedAttr.get(key)).append("_");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }
    public static void main(String[] args) {

        HashMap map1=new HashMap();
        map1.put("ci_name","test1");
        String curxMd5_1 = filterMapByKey("110", "cs", Collections.singleton("ci_name"),map1);
        System.out.println(curxMd5_1);

        //   crux


        HashMap map2=new HashMap();
        map2.put("ci_name","test1");
        String curxMd5_2 = filterMapByKey("111", "cs", Collections.singleton("ci_name"),map2);
        System.out.println(curxMd5_2);






    }


}
