package com.poe.project.poe_project.smart.function;

import java.time.LocalDateTime;

public class ChangeTime {
    public static void main(String[] args) {
//        System.out.println(change(5,202305));
//        System.out.println(change2(change(5,202304)));
//        System.out.println(getStartTime(20231,12,5));
        System.out.println(changeTimeFromQuarter("202306",5));
        System.out.println(changeTimeFromQuarter("2023Q1",5));
        System.out.println(changeTimeFromQuarter("2023-Q1",5));
        System.out.println(changeTimeFromQuarter("202306",6));
        System.out.println(changeTimeFromQuarter("2023",4));
    }


    /**
     * 根据类型转换为对应的时间
     * time_type 4-年，5-季，6-月，7-年度周，8-日，9-季度周，10-月度周，0-不切换
     * @param timeType
     * @param timeCode
     * @return
     */
    public static int change(int timeType,int timeCode) {
        String timeCode_str = String.valueOf(timeCode);
        int timeCode_len = timeCode_str.length();
        if (timeType == 5) {
            if (timeCode_len == 6) {
                int year = timeCode / 100;
                int month = timeCode % 100;
                int quarter = 0;
                if (month >= 3 && month <= 5) {
                    quarter = 1;
                } else if (month >= 6 && month <= 8) {
                    quarter = 2;
                } else if (month >= 9 && month <= 11) {
                    quarter = 3;
                } else {
                    quarter = 4;
                }
                return year * 10 + quarter;
            }
        } else if (timeType == 4) {
            return timeCode / 100;
        }
        return timeCode;
    }

    /**
     * 根据时间处理成对应的季度
     * @param time_code
     * @return
     */
    public static String change2(int time_code){
        String timeCode_str = String.valueOf(time_code);
        int timeCode_len = timeCode_str.length();
        if(timeCode_len==5){
            int year=time_code/10;
            int quarter=time_code%10;
            return year+"-Q"+quarter;
        }else if(timeCode_len==6){
            int year=time_code/100;
            String month=String.valueOf(time_code).substring(4,6);
            String time=year+"-"+month;
            return time;
        }else{
            return time_code+"";
        }
    }

    public static int getStartTime(int end_time, int time_diff, int time_type) {
        int start_time = 0;
        int num1 = 0;
        int num2 = 0;

        if (time_type == 4) {  // 年
            num1 = 1;
            num2 = 1;
        } else if (time_type == 5) {  // 季
            num1 = 10;
            num2 = 4;
        } else if (time_type == 6) {  // 月
            num1 = 100;
            num2 = 12;
        } else if (time_type == 7) {  // 年度周
            num1 = 1000;
            num2 = 53;
        } else if (time_type == 8) {  // 日
            int year = end_time / 10000;
            int month = (end_time % 10000) / 100;
            int day = end_time % 100;
            LocalDateTime start_date = LocalDateTime.of(year, month, day - time_diff, 0, 0, 0, 0);
            start_time = start_date.getYear() * 10000 + start_date.getMonthValue() * 100 + start_date.getDayOfMonth();
            return start_time;
        }

        int divisor = end_time / num1;  // 除数
        int remainder = end_time % num1;  // 余数
        int divisor_diff = time_diff / num2;
        int remainder_diff = time_diff % num2;

        if (remainder > remainder_diff) {
            start_time = (divisor - divisor_diff) * num1 + (remainder - remainder_diff);
        } else {
            start_time = (divisor - divisor_diff - 1) * num1 + (remainder + num2 - remainder_diff);
        }

        return start_time;
    }


    public static String changeTimeFromQuarter(String typeCode, int timeType) {
        try{
            if(typeCode.contains("Q")){
                String year=typeCode.substring(0,4);
                String quarter=typeCode.substring(typeCode.length()-1);
                return year+quarter;
            }
            if (typeCode.length() == 6&&timeType==5) {
                int timeCode=Integer.parseInt(typeCode);
                int year = timeCode / 100;
                int month = timeCode % 100;
                int quarter = 0;
                if (month >= 3 && month <= 5) {
                    quarter = 1;
                } else if (month >= 6 && month <= 8) {
                    quarter = 2;
                } else if (month >= 9 && month <= 11) {
                    quarter = 3;
                } else {
                    quarter = 4;
                }
                return String.valueOf(year * 10 + quarter);
            }
        }catch (Exception e){}
        return typeCode;
    }









}
