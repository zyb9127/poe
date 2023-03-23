package com.poe.project.poe_project.java.TestThread;

import com.google.common.collect.Lists;
import com.poe.project.poe_project.utils.ThreadPoolUtil;
import lombok.SneakyThrows;
import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author poe.zhang
 * @date 2023年02月28日 18:20
 * @description:异步
 */
public class 异步获取数据 {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add(i+"");
        }
        System.out.println("主线程日志打印1");
        int i = aync01(list);
        System.out.println(i);
        aync02(list);
        System.out.println("主线程日志打印2");

    }


    public static int aync01(List<String> list){
        long start = System.currentTimeMillis();
        List<CompletableFuture> futures=new CopyOnWriteArrayList<>();
        List<String> result = new CopyOnWriteArrayList<>();
        for (String s : list) {
            CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
                        List<Integer> list1 = Lists.newArrayList(1, 2, 3, 4);
                        return list1.stream().map(m -> s + m).collect(Collectors.toList());
                    }).thenAccept(result::addAll);
            future.join();
//                    futures.add(future);
        }
//        list.parallelStream().forEach(
//                s->{
//                    CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
//                                List<Integer> list1 = Lists.newArrayList(1, 2, 3, 4);
//                                return list1.stream().map(m -> s + m).collect(Collectors.toList());
//                            },
//                            ThreadPoolUtil.getCreateCiThreadPool()).thenAccept(result::addAll);
//                    future.join();
////                    futures.add(future);
//                }
//        );
//        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        long end = System.currentTimeMillis();
        System.out.println("共执行 "+(end-start));
        //System.out.println(result);
        return 1;
    }

    @SneakyThrows
    public static void aync02(List<String> list){
        long start = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(list.size());
        List<String> result = new CopyOnWriteArrayList<>();
        ThreadPoolUtil.getAuditLogThreadPool().submit(() -> list.parallelStream().forEach(
                ci -> {
                    List<Integer> list1 = Lists.newArrayList(1, 2, 3, 4);
                    result.addAll(list1.stream().map(m -> ci + m).collect(Collectors.toList()));
                    countDownLatch.countDown();
                }
        ));
        countDownLatch.await();
        System.out.println(countDownLatch.getCount());
        long end = System.currentTimeMillis();
        System.out.println("共执行 "+(end-start));
        //System.out.println(result);
    }





}
