package com.vbiso.service.impl;

import com.vbiso.dao.CategoryDao;
import com.vbiso.domain.CategoryDo;
import com.vbiso.exception.BaseException;
import com.vbiso.result.ServiceResult;
import com.vbiso.service.CategoryService;
import com.vbiso.utils.IdUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午9:51 2018/4/26
 * @Modified By:
 */
@Service
public class CategoryServiceImpl implements CategoryService {

  @Autowired
  private CategoryDao categoryDao;

  @Override
  public ServiceResult<List<CategoryDo>> selectCatList(long userId) {
    ServiceResult<List<CategoryDo>> result=new ServiceResult<>();
    try {
      List<CategoryDo> categoryList = categoryDao.selectCategoryList(userId);
      result.setData(categoryList);
      result.setSuccess(true);
      result.setMsg("success");
      result.setCode(0);
    } catch (BaseException e) {
      result.setSuccess(false);
      result.setEx(e);
    }
    return result;
  }

  @Override
  public ServiceResult<Integer> insertCategory(CategoryDo categoryDo) {
    ServiceResult<Integer> result=new ServiceResult<>();
    try {
      categoryDo.setCategoryId(IdUtil.generateId());
      int count = categoryDao.insertCategory(categoryDo);
      result.setSuccess(true);
      result.setData(count);
      result.setCode(0);
    } catch (BaseException e) {
      result.setCode(-1);
      result.setSuccess(false);
    }

    return result;
  }
}
