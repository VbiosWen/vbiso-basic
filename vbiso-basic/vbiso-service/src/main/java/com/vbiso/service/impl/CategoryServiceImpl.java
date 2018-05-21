package com.vbiso.service.impl;

import com.vbiso.dao.CategoryDao;
import com.vbiso.dao.ExpensesDao;
import com.vbiso.dao.IncomeDao;
import com.vbiso.domain.CategoryDo;
import com.vbiso.exception.BaseException;
import com.vbiso.result.ServiceResult;
import com.vbiso.service.CategoryService;
import com.vbiso.utils.IdUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

  @Autowired
  private IncomeDao incomeDao;

  @Autowired
  private ExpensesDao expensesDao;

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
      categoryDo.setCategoryId(IdUtil.generateId()/1000);
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
  @Transactional(propagation = Propagation.REQUIRED)
  @Override
  public ServiceResult<Integer> delSingleCategory(long categoryId,long userId) {
    ServiceResult<Integer> result=new ServiceResult<>();
    try {
      Integer integer = categoryDao.delSingleCategory(categoryId,userId);
      Integer integer1 = incomeDao.delBatchIncome(userId, categoryId);
      Integer integer2 = expensesDao.delBatchExpenses(userId, categoryId);
      result.setData(integer+integer1+integer2);
      result.setSuccess(true);
      result.setCode(0);
    } catch (BaseException e) {
      e.printStackTrace();
    }
    return result;
  }
}
