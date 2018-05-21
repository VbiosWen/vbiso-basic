package com.vbiso.service.impl;

import com.sun.xml.internal.ws.resources.SenderMessages;
import com.vbiso.dao.ExpensesDao;
import com.vbiso.dao.IncomeDao;
import com.vbiso.dao.NetincomeDao;
import com.vbiso.dao.UserDao;
import com.vbiso.domain.IncomeCountDo;
import com.vbiso.domain.IncomeCountQueryDo;
import com.vbiso.domain.UserDo;
import com.vbiso.domain.UserInfo;
import com.vbiso.exception.BaseException;
import com.vbiso.mapping.FieldDo;
import com.vbiso.redis.RedisDao;
import com.vbiso.result.ServiceResult;
import com.vbiso.service.UserService;
import com.vbiso.service.result.InviteCodeResult;
import com.vbiso.utils.FieldMappingUtil;
import com.vbiso.utils.InviteCodeUtil;
import com.vbiso.utils.JsonUtil;
import com.vbiso.utils.SendMsgUtil;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午3:31 2018/3/13
 * @Modified By:
 */
@Service("userService")
public class UserServiceImpl implements UserService {

  private Logger logger= Logger.getLogger(UserServiceImpl.class);

  private static final String key="INVITECODE_%s";

  @Autowired
  private UserDao userDao;

  @Autowired
  private IncomeDao incomeDao;

  @Autowired
  private ExpensesDao expensesDao;

  @Autowired
  private NetincomeDao netincomeDao;

  @Autowired
  private RedisDao redisDao;

  @Override
  public ServiceResult<UserDo> getByUserId(UserDo userDo) {
    ServiceResult result=new ServiceResult();
    try {
      UserDo user = userDao.getByUserId(userDo);
      if(user!=null){
        result.setCode(0);
        result.setSuccess(true);
        result.setData(user);
      }else{
        result.setCode(-2);
        result.setSuccess(false);
        result.setMsg("未找到数据");
      }
    } catch (BaseException ex) {
      result.setSuccess(false);
      result.setCode(-1);
      result.setEx(ex);
      result.setMsg("dao throw an exception!");
    }
    return result;
  }

  @Override
  public ServiceResult<Integer> updateByUserId(long userId,UserDo userDo) {
    ServiceResult result=new ServiceResult();
    try {
        List<FieldDo> fieldAsList = FieldMappingUtil.getFieldAsList(userDo);
        int count = userDao.updateFieldByUserId(userId, fieldAsList);
        result.setData(count);
        result.setSuccess(true);
    } catch (IllegalAccessException e) {
        result.setSuccess(false);
      e.printStackTrace();
    } catch (BaseException e) {
        result.setSuccess(false);
        logger.error("update By UserId error:"+userId,e);
    }

      return result;
  }

  @Override
  public ServiceResult<Integer> insertUser(UserDo userDo) {
    ServiceResult<Integer> result=new ServiceResult<>();
    userDo.setUserId(System.currentTimeMillis());
    try {
      int count = userDao.insertUser(userDo);
      result.setSuccess(true);
      result.setCode(0);
      result.setData(count);
    } catch (BaseException e) {
      logger.error("error insert",e);
    }
    return result;
  }

  @Override
  public ServiceResult<UserInfo> getUserInfo(long userId) {
    ServiceResult<UserInfo> result=new ServiceResult<>();
    try {
      UserDo userDo = userDao.getUserInfo(userId);
      Double sumIncome = incomeDao.getSumIncome(userId);
      Double sumData = expensesDao.getSumData(userId);
      UserInfo userInfo = new UserInfo();
      userInfo.setUserId(userId);
      userInfo.setSex(userDo.getUserSex());
      userInfo.setUserNick(userDo.getUserNick());
      userInfo.setExpensesData(sumData==null?0:sumData);
      userInfo.setIncomeData(sumIncome==null?0:sumIncome);
      userInfo.setSumData((sumIncome==null?0:sumIncome)-(sumData==null?0:sumData));
      result.setData(userInfo);
      result.setSuccess(true);
      result.setCode(0);
      result.setMsg("success");
    } catch (BaseException e) {
      result.setMsg("failed");
      result.setCode(-1);
      result.setSuccess(false);
      logger.error("error get",e);
    }
    return result;
  }

  @Override
  public ServiceResult<UserDo> getUserInfoNoPass(long userId) {
    UserDo userInfoNoPass = userDao.getUserInfoNoPass(userId);
    ServiceResult<UserDo> result = new ServiceResult<>();
    result.setSuccess(true);
    result.setCode(0);
    result.setData(userInfoNoPass);
    return result;
  }

  @Override
  public ServiceResult<InviteCodeResult> sendMobile(String mobile) {
    ServiceResult<InviteCodeResult> resultServiceResult=new ServiceResult<>();
    String inviteCode = InviteCodeUtil.inviteCode();

    String redisKey = buildKey(mobile);

    redisDao.setExpire(redisKey, inviteCode, 60*6);
    try {
      //String result = SendMsgUtil.sendInviteCodeMsg(mobile,inviteCode);
      InviteCodeResult inviteCodeResult =new InviteCodeResult();
      resultServiceResult.setData(inviteCodeResult);
      resultServiceResult.setSuccess(true);
    } catch (Exception e) {
      logger.error("sendMsg error",e);
      return resultServiceResult;
    }
    return resultServiceResult;
  }

  @Override
  public ServiceResult<UserDo> getUserByMobile(String mobile) {
    ServiceResult<UserDo> result=new ServiceResult<>();
    UserDo userInfoByMobile = userDao.getUserInfoByMobile(mobile);
    if(userInfoByMobile!=null){
      result.setSuccess(true);
      result.setCode(0);
      result.setData(userInfoByMobile);
    }
    return result;
  }

  @Override
  public ServiceResult<Boolean> confirmInviteCode(String mobile, String inviteCode) {
    ServiceResult<Boolean> result=new ServiceResult<>();
    String key = buildKey(mobile);
    String value = redisDao.get(key);
    if(inviteCode.equals(value)){
      result.setMsg("验证成功");
      result.setSuccess(true);
      result.setCode(0);
      result.setData(true);
    }
    return result;
  }

  private String buildKey(String mobile) {
    String format = String.format(key, mobile);
    return format;
  }


  public static void main(String[] args){
    int sum=0;
    for(int i=0;i<=99;i++){
      sum+=i;
    }
    System.out.println(sum);
  }
}
