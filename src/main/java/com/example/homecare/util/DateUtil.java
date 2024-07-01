package com.example.homecare.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static final String getCurrentTime(){
        Long timestamp = System.currentTimeMillis();
        return timestamp.toString();
    }
    public static final String getFirstDayOfMonth(){
        LocalDate firstDay = LocalDate.now().withDayOfMonth(1);
        Long timestamp = firstDay.atStartOfDay().toEpochSecond(ZoneOffset.UTC);
        return timestamp.toString();
    }
    public static final DateTimeFormatter FORMAT_DDMMYYY = DateTimeFormatter.ofPattern("dd/MM/yyyy");


}
