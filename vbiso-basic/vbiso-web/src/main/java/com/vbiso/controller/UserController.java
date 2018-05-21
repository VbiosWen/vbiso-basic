package com.vbiso.controller;


import com.vbiso.domain.UserDo;
import com.vbiso.domain.UserInfo;
import com.vbiso.form.RegisterForm;
import com.vbiso.form.UserForm;
import com.vbiso.form.UserLoginForm;
import com.vbiso.form.UserRegisterForm;
import com.vbiso.result.ServiceResult;
import com.vbiso.service.UserService;
import com.vbiso.service.result.InviteCodeResult;
import com.vbiso.utils.IdUtil;
import com.vbiso.utils.StringUtil;
import com.vbiso.utils.UserLoginUtil;
import java.time.Instant;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
    request.getSession().setAttribute("user",userInfo);
    return result;
  }

  @RequestMapping(value = "/inviteCode")
  @ResponseBody
  public ServiceResult<String> invieteCode( String mobile){
    ServiceResult<String> result=new ServiceResult<>();
    if(StringUtil.isBlank(mobile)){
      result.setCode(-1);
      result.setMsg("手机号为空");
      return result;
    }
//    if(resultServiceResult.isSuccess()){
    ServiceResult<UserDo> userByMobile = userService.getUserByMobile(mobile);
    if(userByMobile.isSuccess()){
      result.setCode(-1);
      result.setMsg("该账号已注册，请直接登录");
      result.setSuccess(false);
      return result;
    }
    ServiceResult<InviteCodeResult> resultServiceResult = userService.sendMobile(mobile);
    if(resultServiceResult.isSuccess()){
      result.setMsg("发送成功，请注意查收");
      result.setSuccess(true);
      result.setCode(0);
    }

    return result;
  }
  @RequestMapping(value = "/registerUser")
  @ResponseBody
  public ServiceResult<Integer> registerUser(@RequestBody RegisterForm form,HttpServletRequest request){

    ServiceResult<Integer> result=new ServiceResult<>();
    ServiceResult<UserDo> userByMobile = userService.getUserByMobile(form.getMobile());
    if(userByMobile.isSuccess()){
      result.setMsg("该手机号已经被注册，请直接登录");
      return result;
    }
    ServiceResult<Boolean> inviteCodeResult = userService
        .confirmInviteCode(form.getMobile(), form.getInviteCode());
    if(!inviteCodeResult.isSuccess()){
      result.setCode(-1);
      result.setMsg("验证码错误");
      result.setSuccess(false);
      return result;
    }
    UserDo userDo = new UserDo();
    userDo.setUserId(IdUtil.generateId());
    userDo.setUserPassword(form.getPassword());
    userDo.setUserSex(form.getSex());
    userDo.setUserNick(form.getUserNick());
    userDo.setUserMobile(form.getMobile());
    userDo.setCreatedTime(Instant.now().toEpochMilli());
    userDo.setModifyTime(Instant.now().toEpochMilli());
    result = userService.insertUser(userDo);
    if(!result.isSuccess()){
      result.setMsg("注册失败，请联系管理员,或者稍后再试");
    }
    ServiceResult<UserDo> userResult=new ServiceResult<>();
    userResult.setData(userDo);
    request.getSession().setAttribute("user",userResult);
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
