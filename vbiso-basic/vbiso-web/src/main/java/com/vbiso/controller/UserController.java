package com.vbiso.controller;

import com.vbiso.domain.UserDo;
import com.vbiso.form.UserLoginForm;
import com.vbiso.form.UserRegisterForm;
import com.vbiso.result.ServiceResult;
import com.vbiso.service.UserService;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login")
    public String login(UserLoginForm form){
        UserDo userDo = new UserDo();
        userDo.setUserMobile(form.getMobile());
        userDo.setPassword(form.getPassword());
        ServiceResult<UserDo> userResult = userService.getByUserId(userDo);
        return "index";
    }

    @RequestMapping(value = "register")
    public String register(UserRegisterForm form){
        UserDo userDo = new UserDo();
        userDo.setUserNick(form.getUserNick());
        userDo.setCreatedTime(System.currentTimeMillis());
        userDo.setUserMobile(form.getMobile());
        userDo.setModifyTime(System.currentTimeMillis());
        userDo.setPassword(form.getPassword());
        userService.insertUser(userDo);
        return "success";
    }
}
