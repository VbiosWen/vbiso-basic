package com.vbiso.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.apache.commons.lang3.time.DateUtils;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午1:47 2018/5/8
 * @Modified By:
 */
public class DateUtil {


  private static final ZoneId zoneId = ZoneId.systemDefault();


  public static int getDayOfYear(long timeStamp) {
    Instant instant = Instant.ofEpochMilli(timeStamp);
    LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
    int dayOfYear = localDateTime.getDayOfYear();
    return dayOfYear;
  }

  public static long getTimeStampOfAddDay(int sumDay, long timeStamp) {
    long timeResult = timeStamp + sumDay * 24 * 60 * 60 * 1000;
    return timeResult;
  }

  public static long getTimeStampOfLessDay(int sumDay, long timeStamp) {
    long timeResult = timeStamp - sumDay * 24 * 60 * 60 * 1000;
    return timeResult;
  }

  public static String getNowTime() {
    Instant instant = Instant.now();
    LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
    int hour = localDateTime.getHour();
    int minute = localDateTime.getMinute();
    return hour + "时" + minute + "fen";
  }

  public static long getNowDayStartTime() {
    Instant instant=Instant.now();
    LocalDateTime localDateTime=LocalDateTime.ofInstant(instant,zoneId);
    long timestamp = instant.toEpochMilli();
    int hour=localDateTime.getHour();
    int minute=localDateTime.getMinute();
    int second=localDateTime.getSecond();
    int milli=localDateTime.getNano()/1000000;
    return timestamp-(hour*60*60*1000+minute*60*1000+second*1000+milli);
  }

  public static long getNowDayEndTime(){
    Instant instant=Instant.now();
    LocalDateTime localDateTime=LocalDateTime.ofInstant(instant,zoneId);
    long nowDayStartTime = getNowDayStartTime();
    return nowDayStartTime+(24*60*60*1000);
  }

  public static void main(String[] args){
    long nowDayStartTime = getNowDayStartTime();
    Instant instant = Instant.ofEpochMilli(nowDayStartTime);
    LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
    DateTimeFormatter dtf=DateTimeFormatter.ISO_DATE_TIME;
    String format = localDateTime.format(dtf);

    long endTime=getNowDayEndTime();
    Instant instant1=Instant.ofEpochMilli(endTime);
    LocalDateTime localDateTime1=LocalDateTime.ofInstant(instant1,zoneId);
    DateTimeFormatter dtf1=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String format1 = localDateTime1.format(dtf1);
    System.out.println(format1);
    System.out.println(format);
  }


}
