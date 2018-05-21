package com.vbiso.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import com.vbiso.form.WarnJobForm;
import com.vbiso.redis.RedisDao;
import com.vbiso.result.ServiceResult;
import com.vbiso.utils.JsonUtil;
import com.vbiso.utils.StringUtil;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午1:03 2018/5/17
 * @Modified By:
 */
@Controller
@RequestMapping("/warnExpenses")
public class ExpensesWarnController {

  private static final String WARN_JOB_KEY="warn_job_key";

  private static final String FIELD_KEY="warn_%s";

  @Autowired
  private RedisDao redisDao;

  @RequestMapping(value = "/warn")
  public String returnWarn(){
    return "expenseswarn";
  }

  @RequestMapping(value = "getWarnInfo",method = GET)
  @ResponseBody
  public ServiceResult<WarnJobForm>getWarnInfo(long userId){
    ServiceResult<WarnJobForm> result=new ServiceResult<>();
    String hget = redisDao.hget(WARN_JOB_KEY, String.format(FIELD_KEY, userId));
    if(StringUtil.isBlank(hget)){
      result.setSuccess(false);
      result.setData(null);
      result.setCode(-1);
      return result;
    }
    try {
      WarnJobForm warnJobForm= (WarnJobForm) JsonUtil.toJson(hget, WarnJobForm.class);
      result.setCode(0);
      result.setSuccess(true);
      result.setData(warnJobForm);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return result;
  }

  @RequestMapping(value = "/insertWarnInfo",method = POST)
  @ResponseBody
  public ServiceResult<Integer> insertWarnInfo(@RequestBody WarnJobForm warnJobForm){
    ServiceResult result=new ServiceResult();
    String warnForm = JsonUtil.toJson(warnJobForm);
    if(warnJobForm.getOpen()==0){
      Long hdel = redisDao.hdel(WARN_JOB_KEY, String.format(FIELD_KEY, warnJobForm.getUserId()));
      result.setData(hdel);
      result.setCode(1);
      result.setSuccess(true);
      result.setMsg("取消成功");
      return result;
    }
    Long hset = redisDao
        .hset(WARN_JOB_KEY, String.format(FIELD_KEY, warnJobForm.getUserId()), warnForm);
    if(hset==null){
      result.setMsg("设置失败，请联系管理员");
      return result;
    }
    result.setSuccess(true);
    result.setCode(0);
    result.setData(hset);
    result.setMsg("设置成功");
    return result;
  }

}
