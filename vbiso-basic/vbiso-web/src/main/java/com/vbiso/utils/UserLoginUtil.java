package com.vbiso.utils;

import com.vbiso.domain.UserDo;
import com.vbiso.result.ServiceResult;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午10:20 2018/5/1
 * @Modified By:
 */
public class UserLoginUtil {

  public static UserDo getUserLoginInfo(HttpServletRequest request){
    ServiceResult<UserDo> user = (ServiceResult<UserDo>) request.getSession().getAttribute("user");
    return user.getData();
  }

}
