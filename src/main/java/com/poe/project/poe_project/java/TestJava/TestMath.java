package com.poe.project.poe_project.java.TestJava;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 * @author zyb
 * @Description 测试算术
 * @date 2024/2/28 09:57
 */
public class TestMath {
    public static void main(String[] args) {
        //计算方差
        double[] arr = { 4, 5, 6, 7, 8};
        /*double sum = 0;
        for (double v : arr) {
            sum += v;
        }
        double avg = sum / arr.length;
        double sum2 = 0;
        for (double v : arr) {
            sum2 += (v - avg) * (v - avg);
        }
        double var = sum2 / arr.length;
        System.out.println(var);*/
        DescriptiveStatistics stats = new DescriptiveStatistics();
        for (int i=0; i<arr.length; i++) {
            stats.addValue(arr[i]);
        }
        double variance = stats.getVariance();
        System.out.println(variance);
        double deviation = stats.getStandardDeviation();
        System.out.println(deviation);

    }
}
