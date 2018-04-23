package com.vbiso.controller;

import com.vbiso.domain.UserDo;
import com.vbiso.form.UserLoginForm;
import com.vbiso.form.UserRegisterForm;
import com.vbiso.result.ServiceResult;
import com.vbiso.service.UserService;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
  public String login(@RequestBody UserLoginForm form, HttpServletResponse response,
      HttpServletRequest request) {
    UserDo userDo = new UserDo();
    userDo.setUserMobile(form.getMobile());
    userDo.setPassword(form.getPassword());
    ServiceResult<UserDo> userResult = userService.getByUserId(userDo);
    request.getSession().setAttribute("user",userDo);
    String contextPath = request.getContextPath();
    try {
      response.sendRedirect(contextPath + "/index.jsp");
    } catch (IOException e) {
      logger.error("can`t find ", e);
    }
    return "success";
  }

  @RequestMapping(value = "/register")
  public String register(UserRegisterForm form) {
    return "register";
  }
}
