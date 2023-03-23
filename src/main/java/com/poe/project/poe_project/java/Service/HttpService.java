package com.poe.project.poe_project.java.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author poe.zhang
 * @date 2022年08月03日 15:43
 * @description:测试http
 */

public interface HttpService {
    Map<String,String> getHttp();
}
