package com.vbiso.controller;

import static org.springframework.http.HttpMethod.POST;

import com.vbiso.domain.UserDo;
import com.vbiso.domain.UserInfo;
import com.vbiso.form.UserLoginForm;
import com.vbiso.form.UserRegisterForm;
import com.vbiso.result.ServiceResult;
import com.vbiso.service.UserService;
import com.vbiso.utils.UserLoginUtil;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/user")
public class UserController {

  private static Logger logger = Logger.getLogger(UserController.class);

  @Autowired
  private UserService userService;

  @RequestMapping(value = "/login")
  @ResponseBody
  public String login(@RequestBody UserLoginForm form, HttpServletResponse response,
      HttpServletRequest request) {
    UserDo userDo = new UserDo();
    userDo.setUserMobile(form.getMobile());
    userDo.setPassword(form.getPassword());
    ServiceResult<UserDo> userResult = userService.getByUserId(userDo);
    HttpSession session = request.getSession();
    session.setMaxInactiveInterval(24*60*60);
    session.setAttribute("user",userResult.getData());
    try {
      response.sendRedirect("../index.jsp");
    } catch (IOException e) {
      logger.error("erro ",e);
      return "error";
    }
    return "success";
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

  @RequestMapping(value = "/getUserInfo")
  @ResponseBody
  public ServiceResult<UserInfo>getUserInfo(HttpServletRequest request){
     UserDo userDo= UserLoginUtil.getUserLoginInfo(request);
    ServiceResult<UserInfo> userInfo = userService.getUserInfo(userDo.getUserId());
    return userInfo;
  }
}
