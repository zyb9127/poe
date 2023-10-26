package com.poe.project.poe_project.smart.function;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author zyb
 * @Description 获取时间
 * @date 2023/10/8 16:09
 */
public class 获取同期时间 {
    public static List<String> getLastMonthsOrYears(String inputDate) {
        List<String> result = new ArrayList<>();

        try {
            DateTimeFormatter formatter;

            if (inputDate.length() == 6) {
                formatter = DateTimeFormatter.ofPattern("yyyyMM");
                YearMonth yearMonth = YearMonth.parse(inputDate, formatter);

                for (int i = 0; i < 24; i++) {
                    result.add(yearMonth.format(formatter));
                    yearMonth = yearMonth.minusMonths(1);
                }
            } else if (inputDate.length() == 4) {
                formatter = DateTimeFormatter.ofPattern("yyyy");
                int startYear = Integer.parseInt(inputDate);

                for (int i = 0; i < 12; i++) {
                    result.add(Integer.toString(startYear - i));
                }
            } else {
                throw new IllegalArgumentException("Invalid input date format");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 处理异常情况
        }

        return result;
    }

    public static void main(String[] args) {
        String inputDate1 = "202306";
        String inputDate2 = "2022";

        List<String> lastMonthsOrYears1 = getLastMonthsOrYears(inputDate1);
        System.out.println("Last 24 months: " +String.join(", ", lastMonthsOrYears1) );

        List<String> lastMonthsOrYears2 = getLastMonthsOrYears(inputDate2);
        System.out.println("Last 12 years: " + lastMonthsOrYears2);
    }
}
