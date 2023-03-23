package com.poe.project.poe_project.java.TestJava;

import org.assertj.core.util.Lists;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author poe.zhang
 * @date 2022年07月05日 17:03
 * @description:计算
 */
public class Calculate {


    public  static void check(String code){

        System.out.println(code);
    }

    public static void main(String[] args) {
        Number m=0.896363;
        double v = m.doubleValue();
        BigDecimal result = new BigDecimal(v);
        result = result.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        BigDecimal totalPercent = BigDecimal.valueOf(0);
        if (totalPercent.compareTo(BigDecimal.ZERO) != 0) {
            System.out.println(totalPercent);
        }

        String s1="1.0";
        Double d1=1.00;
        String s3 = new BigDecimal(s1).stripTrailingZeros().toPlainString();
        String s4 = new BigDecimal(d1).stripTrailingZeros().toPlainString();
        System.out.println(s3);
        System.out.println(s4);



        Integer mm=100;
        Long nn=100l;
        System.out.println(mm.longValue()==nn);

    }






}
