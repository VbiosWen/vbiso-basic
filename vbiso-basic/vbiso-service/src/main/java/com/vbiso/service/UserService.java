package com.vbiso.service;

import com.vbiso.domain.UserDo;
import com.vbiso.domain.UserInfo;
import com.vbiso.result.ServiceResult;
import com.vbiso.service.result.InviteCodeResult;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午2:44 2018/3/13
 * @Modified By:
 */
public interface UserService {

  String SERVICE_NAME="userService";

  ServiceResult<UserDo> getByUserId(UserDo userDo);

  ServiceResult<Integer> updateByUserId(long id,UserDo userDo);

  ServiceResult<Integer> insertUser(UserDo userDo);

  ServiceResult<UserInfo> getUserInfo(long userId);

  ServiceResult<UserDo> getUserInfoNoPass(long userId);

  ServiceResult<InviteCodeResult> sendMobile(String mobile);

  ServiceResult<UserDo> getUserByMobile(String mobile);

  ServiceResult<Boolean> confirmInviteCode(String mobile,String inviteCode);




}
