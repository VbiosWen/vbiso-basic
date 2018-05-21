package com.vbiso.timejob;

import com.vbiso.dao.ExpensesDao;
import com.vbiso.dao.UserDao;
import com.vbiso.domain.IncomeCountQueryDo;
import com.vbiso.domain.UserDo;
import com.vbiso.pojo.WarnJobPoJo;
import com.vbiso.redis.RedisDao;
import com.vbiso.utils.DateUtil;
import com.vbiso.utils.JsonUtil;
import com.vbiso.utils.SendMsgUtil;
import java.io.IOException;
import java.time.Instant;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午9:58 2018/5/17
 * @Modified By:
 */
@Component
public class ExpensesWarnJob {

  private static final String WARN_JOB_KEY = "warn_job_key";

  private static final String FIELD_KEY = "warn_%s";

  private static final String STRING_TIMEJOB_WARN_MSG = "【个人收支管理系统】亲爱的用户，您的预期消费已超过预警%s,请注意哦！";


  @Autowired
  private RedisDao redisDao;

  @Autowired
  private ExpensesDao expensesDao;

  @Autowired
  private UserDao userDao;


  public void doJob() {
    Map<String, String> map = redisDao.hgetAll(WARN_JOB_KEY);
    for (String key : map.keySet()) {
      String s = map.get(key);
      try {
        WarnJobPoJo warnJobPoJo = (WarnJobPoJo) JsonUtil.toJson(s, WarnJobPoJo.class);
        long start = DateUtil.getNowDayStartTime();
        long end = DateUtil.getNowDayEndTime();

        IncomeCountQueryDo incomeCountQueryDo = new IncomeCountQueryDo();
        incomeCountQueryDo.setStart(start);
        incomeCountQueryDo.setEnd(end);
        incomeCountQueryDo.setUserId(warnJobPoJo.getUserId());
        Double totalExpenses = expensesDao.getTotalExpenses(incomeCountQueryDo);
        String startDate = warnJobPoJo.getStartDate();
        String replace = replaceTime(startDate);
        String[] split = replace.split(",");
        long now = Instant.now().toEpochMilli();

        if (totalExpenses == null) {
          return;
        }
        long nowTime =
            start + Long.valueOf(split[0]) * 60 * 60 * 1000 + Long.valueOf(split[1]) * 60 * 1000;
        boolean flag = false;
        if (now - nowTime >= 0 && now - nowTime < (5 * 60 * 1000)) {
          flag = true;
        }
        if (flag) {
          if (totalExpenses > warnJobPoJo.getCategoryData()) {
            sendMsg(warnJobPoJo.getUserId(), totalExpenses - warnJobPoJo.getCategoryData());
          }
        }


      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private String replaceTime(String startDate) {
    return startDate.replace("时", ",").replace("分", "");
  }

  private void sendMsg(long userId, double v) {
    UserDo userInfo = userDao.getUserInfo(userId);
    try {
      SendMsgUtil
          .sendMsg(userInfo.getUserMobile(), String.valueOf(v), STRING_TIMEJOB_WARN_MSG);
    } catch (Exception e) {
    }
  }

}
