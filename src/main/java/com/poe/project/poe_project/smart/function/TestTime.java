package com.poe.project.poe_project.smart.function;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneOffset;


public class TestTime {
    public static void main(String[] args) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(20230610), ZoneId.systemDefault());
        Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(date);

        ZoneId defaultZone = ZoneId.systemDefault();
        System.out.println("Default Time Zone: " + defaultZone);

        String dateString = "2023-06-01";
        LocalDate date1 = LocalDate.parse(dateString);
        long timestamp = date1.atStartOfDay(ZoneOffset.UTC).toInstant().getEpochSecond();

        LocalDateTime parse = LocalDateTime.parse(dateString);
        System.out.println(parse);


        System.out.println(timestamp);
    }

    

}
