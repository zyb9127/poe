package com.poe.project.poe_project.java.TestJava;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author poe.zhang
 * @date 2022年07月04日 16:09
 * @description:工具
 */
public class DateUtil {

    public static Long getBeginDayOfYesterday() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTimeInMillis();
    }
    public static Long getEndDayOfYesterDay() {

        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTimeInMillis();
    }


    /**
      * 获取昨天开始时间和结束时间
     * @return
      */
    public static Map getYesterdayTime() {
        Long startTime = getBeginDayOfYesterday();
        Long endTime = getEndDayOfYesterDay();
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String startTimeStr = ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(startTime), ZoneId.systemDefault()));
        String endTimeStr = ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(endTime), ZoneId.systemDefault()));
        Map map = new HashMap();
        map.put("startDate", startTime);
        map.put("endDate", endTime);
        return map;

    }

    public static void main(String[] args) {
        System.out.println(getYesterdayTime());
    }



}
