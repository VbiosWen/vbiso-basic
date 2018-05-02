package com.vbiso.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午4:09 2018/5/2
 * @Modified By:
 */
public class IdUtil {

  private static long tmpId = 0;
  private static boolean tmpIdlocked = false;

  public static long generateId() {
    long ltime = 0;
    while (true) {
      if (!tmpIdlocked) {
        ltime =
            Long.valueOf(new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date()).toString())
                * 1000;
        if (tmpId < ltime) {
          tmpId = ltime;
        } else {
          tmpId = tmpId + 1;
          ltime = tmpId;
        }
        tmpIdlocked = false;
        return ltime;
      }
    }
  }

  public static void main(String[] args){
    long l = IdUtil.generateId();
    System.out.println(Long.MAX_VALUE);
    System.out.println(l);
  }

}
