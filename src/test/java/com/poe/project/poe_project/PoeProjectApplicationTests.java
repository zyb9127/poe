package com.poe.project.poe_project;

import com.fasterxml.jackson.core.type.TypeReference;
import com.poe.project.poe_project.utils.JsonUtils;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@SpringBootTest
//@ActiveProfiles("prod")
class PoeProjectApplicationTests {

    @Test
    void contextLoads() {
    }
    @Autowired
    private JsonUtils jsonUtils;

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
        rules.put("endNumber", 100);
        rules.put("uGroupId", "US1");
    }

    public static final Map<String, Object> ciContent  = new HashMap();
    static {
        ciContent.put("dataRange", Lists.newArrayList(10, 3));
    }


    @Test
    void TestAssert() {
        List<Object> dataRange = jsonUtils.parseObject(ciContent.get("dataRange"), new TypeReference<List<Object>>() {
        });
        System.out.println(dataRange);
    }

}
