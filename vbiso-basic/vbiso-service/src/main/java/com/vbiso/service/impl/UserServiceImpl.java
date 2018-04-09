package com.vbiso.service.impl;

import com.vbiso.dao.UserDao;
import com.vbiso.domain.UserDo;
import com.vbiso.mapping.FieldDo;
import com.vbiso.result.ServiceResult;
import com.vbiso.service.UserService;
import com.vbiso.utils.FieldMappingUtil;
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

  @Autowired
  private UserDao userDao;

  @Override
  public ServiceResult<UserDo> getByUserId(UserDo userDo) {
    ServiceResult result=new ServiceResult();
    try {
      UserDo user = userDao.getByUserId(userDo);
      if(userDo!=null){
        result.setCode(0);
        result.setSuccess(true);
        result.setData(userDo);
      }else{
        result.setCode(-2);
        result.setSuccess(true);
        result.setMsg("未找到数据");
      }
    } catch (Exception ex) {
      result.setSuccess(false);
      result.setCode(-1);
      result.setEx(ex);
      result.setMsg("dao throw an exception!");
    }
    return result;
  }

  @Override
  public ServiceResult updateByUserId(long userId,UserDo userDo) {
    ServiceResult result=new ServiceResult();
    try {
        List<FieldDo> fieldAsList = FieldMappingUtil.getFieldAsList(userDo);
        int count = userDao.updateFieldByUserId(userId, fieldAsList);
        result.setData(count);
        result.setSuccess(true);
    } catch (IllegalAccessException e) {
        result.setSuccess(false);
      e.printStackTrace();
    } catch (Exception e) {
        result.setSuccess(false);
        e.printStackTrace();
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
    } catch (Exception e) {
      logger.error("error insert",e);
    }
    return result;
  }

  public static void main(String[] args){
    int sum=0;
    for(int i=0;i<=99;i++){
      sum+=i;
    }
    System.out.println(sum);
  }
}