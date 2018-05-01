package com.vbiso.utils;

import com.vbiso.domain.UserDo;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午10:20 2018/5/1
 * @Modified By:
 */
public class UserLoginUtil {

  public static UserDo getUserLoginInfo(HttpServletRequest request){
    return (UserDo) request.getSession().getAttribute("user");
  }

}
