package com.vbiso.controller;

import com.vbiso.domain.CategoryDo;
import com.vbiso.domain.UserDo;
import com.vbiso.result.ServiceResult;
import com.vbiso.service.CategoryService;
import com.vbiso.utils.UserLoginUtil;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午9:31 2018/4/26
 * @Modified By:
 */
@Controller
@RequestMapping("/category")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;


  @RequestMapping(value = "/selectAll")
  @ResponseBody
  public ServiceResult<List<CategoryDo>> selectAllCategory(HttpServletRequest request){
    UserDo userLoginInfo = UserLoginUtil.getUserLoginInfo(request);
    ServiceResult<List<CategoryDo>> result= categoryService.selectCatList(userLoginInfo.getUserId());
    return result;
  }


}
