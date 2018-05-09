package com.vbiso.controller;

import static org.springframework.http.HttpMethod.POST;

import com.vbiso.domain.UserDo;
import com.vbiso.domain.UserInfo;
import com.vbiso.form.UserForm;
import com.vbiso.form.UserLoginForm;
import com.vbiso.form.UserRegisterForm;
import com.vbiso.result.ServiceResult;
import com.vbiso.service.UserService;
import com.vbiso.utils.StringUtil;
import com.vbiso.utils.UserLoginUtil;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/user")
public class UserController {

  private static Logger logger = Logger.getLogger(UserController.class);

  @Autowired
  private UserService userService;

  @RequestMapping(value = "/login")
  @ResponseBody
  public ServiceResult<UserDo> login(@RequestBody UserLoginForm form, HttpServletResponse response,
      HttpServletRequest request) {
    UserDo userDo = new UserDo();
    userDo.setUserMobile(form.getMobile());
    userDo.setUserPassword(form.getPassword());
    ServiceResult<UserDo> userResult = userService.getByUserId(userDo);
    HttpSession session = request.getSession();
    session.setMaxInactiveInterval(24*60*60);
    session.setAttribute("user",userResult);
    return userResult;
  }

  @RequestMapping(value = "/getUserInfo")
  @ResponseBody
  public ServiceResult<UserInfo>getUserInfo(HttpServletRequest request){
     UserDo userDo= UserLoginUtil.getUserLoginInfo(request);
    ServiceResult<UserInfo> userInfo = userService.getUserInfo(userDo.getUserId());
    return userInfo;
  }

  @RequestMapping(value = "/updateUserInfo")
  @ResponseBody
  public ServiceResult<Integer> updateUserInfo(HttpServletRequest request,@RequestBody UserForm userForm){
    UserDo userLoginInfo = UserLoginUtil.getUserLoginInfo(request);
    UserDo userDo = new UserDo();
    userDo.setUserNick(userForm.getUserNick());
    userDo.setUserPassword(StringUtil.isBlank(userForm.getUserPassword())==true?null:userForm.getUserPassword());
    userDo.setUserSex(userForm.getSex());
    ServiceResult<Integer> result = userService.updateByUserId(userLoginInfo.getUserId(), userDo);
    ServiceResult<UserDo> userInfo = userService.getUserInfoNoPass(userLoginInfo.getUserId());
    request.getSession().removeAttribute("user");
    request.getSession().setAttribute("user",userInfo.getData());
    return result;
  }
  @RequestMapping(value = "/removeSession")
  public String removeSession(HttpServletRequest request){
    request.getSession().removeAttribute("user");
    return "login";
  }

  @RequestMapping(value = "/returnLogin")
  public String returnLogin(){
    return "login";
  }

  @RequestMapping(value = "/register")
  public String register(UserRegisterForm form) {
    return "register";
  }

  @RequestMapping(value = "/userInfo")
  public String userInfo(){
    return "userInfo";
  }
}
