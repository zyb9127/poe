package com.poe.project.poe_project.java.TestThread;

import com.google.common.collect.Lists;
import com.poe.project.poe_project.utils.ThreadPoolUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @author poe.zhang
 * @date 2023年02月28日 18:20
 * @description:异步
 */
public class TestAync {

    public static void main(String[] args) {
        List<String> list = Lists.newArrayList("poe", "roy", "beck", "roubin");
        /*List<CompletableFuture> futures=new ArrayList<>();
        List<String> result = new ArrayList<>();
        list.forEach(
                s->{
                    CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
                                List<Integer> list1 = Lists.newArrayList(1, 2, 3, 4);
                                return list1.stream().map(m -> s + m).collect(Collectors.toList());
                            },
                            ThreadPoolUtil.getCreateCiThreadPool()).thenAccept(result::addAll);
                    futures.add(future);
                }
        );
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        list.forEach(System.out::println);
        System.out.println(result);*/



        List<CompletableFuture> futures=new ArrayList<>();
        List<String> result = new ArrayList<>();
        list.forEach(
                s->{
                    CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
                                if (s.equals("poe")) {
                                    int sum = 10 / 0;
                                }
                                System.out.println("当前进行的是"+s);
                                List<Integer> list1 = Lists.newArrayList(1, 2, 3, 4);
                                return list1.stream().map(m -> s + m).collect(Collectors.toList());
                            },
                            ThreadPoolUtil.getCreateCiThreadPool()).thenAccept(result::addAll).handleAsync((res,e)->{
                                if(e!=null){
                                    System.out.println(e);
                                }
                                return res;
                    });
                    futures.add(future);
                }
        );
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        System.out.println(result);

    }
}
