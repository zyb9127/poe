package com.poe.project.poe_project.java.TestThread;

import com.poe.project.poe_project.utils.ThreadPoolUtil;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author poe.zhang
 * @date 2023年02月28日 18:20
 * @description:异步
 */
public class 异步获取数据 {

    public static void main(String[] args) {
        long sum=0;
        for (int j = 0; j < 4; j++) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < 10000; i++) {
                list.add(i+"");
            }
            sum+=aync01(list);
            //sum+=aync02(list);
        }
        System.out.println(sum/4);

    }


    public static long aync01(List<String> list) {
        long start = System.currentTimeMillis();
        List<String> result = new CopyOnWriteArrayList<>();
        ThreadPoolExecutor threadPool = ThreadPoolUtil.getDeleteCiThreadPool();
        list.parallelStream().forEach(
                s -> CompletableFuture.supplyAsync(() -> {
                    List<String> listAync = new ArrayList<>();
                    for (int i = 0; i < 100; i++) {
                        listAync.add(i+"");
                    }
                    return listAync;
                },threadPool).thenAcceptAsync(result::addAll).join()
        );
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        return end - start;
    }

    @SneakyThrows
    public static long aync02(List<String> list){
        long start = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(list.size());
        List<String> result = new CopyOnWriteArrayList<>();
        ThreadPoolUtil.getDeleteCiThreadPool().submit(() -> list.parallelStream().forEach(
                ci -> {
                    List<String> listAync = new ArrayList<>();
                    for (int i = 0; i < 100; i++) {
                        listAync.add(i+"");
                    }
                    result.addAll(listAync);
                    countDownLatch.countDown();
                }
        ));
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        return end-start;
    }





}
