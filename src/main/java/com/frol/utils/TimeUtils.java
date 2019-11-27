package com.frol.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class TimeUtils {

    public static LocalDateTime getStartOfMinute(LocalDateTime localDateTime) {
        return localDateTime.truncatedTo(ChronoUnit.MINUTES);
    }

    public static LocalDateTime getStartOfHour(LocalDateTime localDateTime) {
        return localDateTime.truncatedTo(ChronoUnit.HOURS);
    }

    public static LocalDateTime getStartOfDay(LocalDateTime localDateTime) {
        return localDateTime.truncatedTo(ChronoUnit.DAYS);
    }

    public static LocalDateTime getNextMinute(LocalDateTime localDateTime) {
        return getStartOfMinute(localDateTime).plusMinutes(1);
    }

    public static LocalDateTime getNextHour(LocalDateTime localDateTime) {
        return getStartOfHour(localDateTime).plusHours(1);
    }

    public static LocalDateTime getNextDay(LocalDateTime time) {
        return getStartOfDay(time).plusDays(1);
    }


    public static LocalDateTime getFromDate(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneOffset.UTC);
    }

}
