package com.vbiso.controller;

import com.vbiso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午12:33 2018/3/14
 * @Modified By:
 */
@RestController
public class IndexController {

  @Autowired
  private UserService userService;


}
