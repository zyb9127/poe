package com.poe.project.poe_project.java.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.poe.project.poe_project.utils.HttpUtils;
import com.poe.project.poe_project.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author poe.zhang
 * @date 2022年08月03日 15:50
 * @description:测试http接口类
 */
@Slf4j
@Service
public class HttpServiceImpl implements HttpService {
    @Autowired
    JsonUtils jsonUtils;

    private static final String URI_SELECT_ORDER = "http://10.0.16.193:18211/api/v1/cmdb/consumer/model/modelDetail";


    @Override
    public Map<String,String> getHttp(){
        Map<String, String> headers = new HashMap<>(16);
        headers.put("accountId", "110");
        headers.put("userId", "3");

        String resp = HttpUtils.doPostJson(URI_SELECT_ORDER, jsonUtils.toJSONString(Collections.singletonList("application")), headers);
        if (StringUtils.isBlank(resp)) {
            log.error("http request error, result is blank");

        }
        Map<String, Object> respMap = jsonUtils.parseObject(resp, new TypeReference<Map<String, Object>>() {
        });
        if (respMap == null || (Integer) respMap.get("code") != 100000) {
            log.error("http request error, result is {}", jsonUtils.toJSONString(respMap));
        }
        List<Map<String, Object>> data = jsonUtils.parseObject(respMap.get("data"), new TypeReference<List<Map<String, Object>>>() {
        });
        return  null;
    }

}
