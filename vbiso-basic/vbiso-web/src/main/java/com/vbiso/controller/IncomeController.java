package com.vbiso.controller;

import com.vbiso.result.ServiceResult;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午10:23 2018/4/14
 * @Modified By:
 */
@Controller
@RequestMapping("/income")
public class IncomeController {

  @RequestMapping(value = "/page")
  @ResponseBody
  public ServiceResult pageIncome(HttpServletRequest request){
    ServiceResult result =new ServiceResult();
    request.getSession().getAttribute("");
    return result;
  }

}
