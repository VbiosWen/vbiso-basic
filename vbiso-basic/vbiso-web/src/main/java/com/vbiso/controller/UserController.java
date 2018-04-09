package com.vbiso.controller;

import com.vbiso.domain.UserDo;
import com.vbiso.form.UserLoginForm;
import com.vbiso.result.ServiceResult;
import com.vbiso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login")
    @ResponseBody
    public String login(UserLoginForm form){
        UserDo userDo = new UserDo();
        userDo.setUserNick(form.getAccount());
        userDo.setPassword(form.getPassword());
        ServiceResult<UserDo> userResult = userService.getByUserId(userDo);
        if(userResult.isSuccess()&&userResult.getData()!=null){
            return "login";
        }
        return "index";
    }
}
