package com.vbiso.utils;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午1:47 2018/5/8
 * @Modified By:
 */
public class DateUtil {



  public static int getDayOfYear(long timeStamp){
    Instant instant=Instant.ofEpochMilli(timeStamp);
    ZoneId zoneId = ZoneId.systemDefault();
    LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
    int dayOfYear = localDateTime.getDayOfYear();
    return dayOfYear;
  }

  public static long getTimeStampOfAddDay(int sumDay,long timeStamp){
   long timeResult=timeStamp+sumDay*24*60*60*1000;
   return timeResult;
  }

  public static long getTimeStampOfLessDay(int sumDay,long timeStamp){
    long timeResult=timeStamp-sumDay*24*60*60*1000;
    return timeResult;
  }



}
