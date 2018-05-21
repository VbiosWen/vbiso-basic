package com.vbiso.controller;

import com.vbiso.domain.CategoryDo;
import com.vbiso.domain.UserDo;
import com.vbiso.form.CategoryForm;
import com.vbiso.result.LayUIResult;
import com.vbiso.result.ServiceResult;
import com.vbiso.service.CategoryService;
import com.vbiso.utils.UserLoginUtil;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

  @RequestMapping(value = "/selectPage")
  @ResponseBody
  public LayUIResult<List<CategoryDo>> selectPage(HttpServletRequest request){
    UserDo userLoginInfo = UserLoginUtil.getUserLoginInfo(request);
    ServiceResult<List<CategoryDo>> result = categoryService
        .selectCatList(userLoginInfo.getUserId());
    LayUIResult<List<CategoryDo>> layUIResult=new LayUIResult<>();
    layUIResult.setCode(0);
    layUIResult.setData(result.getData());
    return layUIResult;
  }

  @RequestMapping(value = "/addCategory")
  public String addCategoty(){
    return "addCategory";
  }

  @RequestMapping(value = "/addCategory.json")
  @ResponseBody
  public ServiceResult<Integer> addCategoryJson(@RequestBody CategoryForm categoryForm){
    CategoryDo categoryDo = new CategoryDo();
    categoryDo.setUserId(categoryForm.getUserId());
    categoryDo.setCategoryDesc(categoryForm.getCategoryData());
    ServiceResult<Integer> result = categoryService.insertCategory(categoryDo);
    return result;
  }

  @RequestMapping(value = "/returnCategory")
  public String returnCategory(){
    return "addCategory";
  }

  @RequestMapping(value = "/delSingleCategory",method = RequestMethod.GET)
  @ResponseBody
  public ServiceResult<Integer> delSingleCategory(long categoryId,HttpServletRequest request){
    UserDo userLoginInfo = UserLoginUtil.getUserLoginInfo(request);
    ServiceResult<Integer> result = categoryService.delSingleCategory(categoryId,userLoginInfo.getUserId());
    return result;
  }


}
