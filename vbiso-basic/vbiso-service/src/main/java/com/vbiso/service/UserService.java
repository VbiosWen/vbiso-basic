package com.vbiso.service;

import com.vbiso.domain.UserDo;
import com.vbiso.result.ServiceResult;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午2:44 2018/3/13
 * @Modified By:
 */
public interface UserService {

  String SERVICE_NAME="userService";

  ServiceResult<UserDo> getByUserId(UserDo userDo);

  ServiceResult updateByUserId(long id,UserDo userDo);


}
