package com.vbiso.controller;

import com.vbiso.domain.UserDo;
import com.vbiso.result.ServiceResult;
import com.vbiso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午12:33 2018/3/14
 * @Modified By:
 */
@Controller
public class IndexController {

  @Autowired
  private UserService userService;

  @RequestMapping(value = "/")
  public String init(){
    return "login";
  }

  @RequestMapping(value = "/test.json")
  @ResponseBody
  public ServiceResult getUser(){
      UserDo userDo=new UserDo();
      userDo.setUserId(1L);
      ServiceResult<UserDo> result = userService.getByUserId(userDo);
      return result;
  }

  @RequestMapping(value = "/login.json")
  public String login(){
      return "login";
  }

  @RequestMapping(value ="/page.json")
  public String page(){
    return "income";
  }


}
