package com.poe.project.poe_project.java.Controller;

import com.poe.project.poe_project.java.Service.HttpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author poe.zhang
 * @date 2022年08月03日 16:13
 * @description:外部调用
 */
@RestController
@Slf4j
public class HttpController {


    @Autowired
    private HttpService httpService;
    @Autowired
    private RestTemplate restTemplate;


    @GetMapping( "/get1")
    public void get1() {
        httpService.getHttp();
    }

    @GetMapping( "/get2")
    public void get2() {
        Object news= restTemplate.getForObject("网络地址", Object.class);
        System.out.println(123);
    }
}
