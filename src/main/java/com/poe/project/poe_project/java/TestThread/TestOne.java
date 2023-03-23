package com.poe.project.poe_project.java.TestThread;

import com.poe.project.poe_project.utils.ThreadPoolUtil;
import org.assertj.core.util.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author poe.zhang
 * @date 2023年02月01日 14:13
 * @description:测试1
 */
public class TestOne {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor ciTopologyRelationsThreadPool = ThreadPoolUtil.getCiTopologyRelationsThreadPool();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
        System.out.println("开始");
        CountDownLatch countDownLatch = new CountDownLatch(list.size());
        long start = System.currentTimeMillis();
        ConcurrentHashMap<Integer, List<Integer>> result = new ConcurrentHashMap<>();
        ciTopologyRelationsThreadPool.submit(() -> list.parallelStream().forEach(
                ci -> {
                        List<Integer> list1 = new ArrayList<>();
                        for (int i = 0; i < 1000; i++) {
                            list1.add(i);
                        }
                        result.put(ci,list1);
                        System.out.println(ci);
                        countDownLatch.countDown();
                }
        ));
        countDownLatch.await();
        System.out.println("结束");
        System.out.println(result.size());
        long end = System.currentTimeMillis();
        System.out.println("共执行："+(end-start));
    }
}
