package com.vbiso.service.impl;

import com.vbiso.dao.CategoryDao;
import com.vbiso.dao.ExpensesDao;
import com.vbiso.domain.CategoryDo;
import com.vbiso.domain.EveryExpensesDo;
import com.vbiso.domain.ExpensesCategoryDo;
import com.vbiso.domain.ExpensesCountDo;
import com.vbiso.domain.ExpensesCountQueryDo;
import com.vbiso.domain.ExpensesDo;
import com.vbiso.domain.PageDo;
import com.vbiso.exception.BaseException;
import com.vbiso.pojo.IncomeExpensesQueryPojo;
import com.vbiso.result.ServiceResult;
import com.vbiso.service.ExpensesService;
import com.vbiso.service.result.ExpensesCategoryResult;
import com.vbiso.service.result.ExpensesEveryCatResult;
import com.vbiso.service.result.IncomeCategoryResult;
import com.vbiso.utils.StringUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午11:56 2018/4/14
 * @Modified By:
 */
@Service
public class ExpensesServiceImpl implements ExpensesService {

  private static Logger logger=Logger.getLogger(ExpensesServiceImpl.class);

  @Autowired
  private ExpensesDao expensesDao;

  @Autowired
  private CategoryDao categoryDao;

  @Override
  public ServiceResult<PageDo<List<ExpensesDo>>> selectPage(IncomeExpensesQueryPojo incomeExpensesQueryPojo) {
    ServiceResult<PageDo<List<ExpensesDo>>> result=new ServiceResult<>();
    try {
      List<ExpensesDo> expensesDos = expensesDao.selectPage(incomeExpensesQueryPojo);
      long totcalCount = expensesDao.getTotalCount(incomeExpensesQueryPojo);
      PageDo<List<ExpensesDo>> pageDo = new PageDo<>();
      pageDo.setUserId(incomeExpensesQueryPojo.getUserId());
      pageDo.setTotalCount(totcalCount);
      pageDo.setPage(expensesDos);
      result.setData(pageDo);
      result.setSuccess(true);
      result.setCode(0);
    } catch (BaseException e) {
      result.setSuccess(false);
      result.setCode(-1);
      logger.error("select page error:"+incomeExpensesQueryPojo.getUserId(),e);
    }
    return result;
  }

  @Override
  public ServiceResult<Integer> insertExpenses(ExpensesDo expensesDo) {
    ServiceResult<Integer> result=new ServiceResult<>();
    try {
      int i = expensesDao.insertExpenses(expensesDo);
      result.setSuccess(true);
      result.setCode(0);
      result.setMsg("success");
      result.setData(i);
    } catch (BaseException e) {
      result.setSuccess(false);
      logger.error("insert into expenses error:",e);
    }
    return result;
  }

  @Override
  public ServiceResult<List<ExpensesCountDo>> getIncomeDay(
      ExpensesCountQueryDo expensesCountQueryDo) {
    ServiceResult<List<ExpensesCountDo>> result=new ServiceResult<>();
    List<ExpensesCountDo> dayExpenses = expensesDao.getDayExpenses(expensesCountQueryDo);
    result.setMsg("success");
    result.setSuccess(true);
    result.setData(dayExpenses);
    return result;
  }

  @Override
  public ServiceResult<Map<String, List<ExpensesCategoryResult>>> getExpensesCategory(
      ExpensesCountQueryDo expensesCountQueryDo) {
    ServiceResult<Map<String,List<ExpensesCategoryResult>>> result=new ServiceResult<>();
    List<ExpensesCategoryDo> categoryExpenses = expensesDao.getCategoryExpenses(expensesCountQueryDo);
    List<CategoryDo> categoryDos = categoryDao.selectCategoryList(expensesCountQueryDo.getUserId());
    List<ExpensesCategoryResult> list = buildParam(categoryExpenses, categoryDos);
    result.setData(invert(list));
    result.setSuccess(true);
    result.setCode(0);
    return result;
  }

  private Map<String, List<ExpensesCategoryResult>> invert(List<ExpensesCategoryResult> list) {
    Map<String,List<ExpensesCategoryResult>> expensesMap=new HashMap<>();
    for(ExpensesCategoryResult expensesCategoryResult:list){
      expensesMap.put(expensesCategoryResult.getCategoryDesc(),new ArrayList<>());
    }

    for(ExpensesCategoryResult expensesCategoryResult:list){
      expensesMap.get(expensesCategoryResult.getCategoryDesc()).add(expensesCategoryResult);
    }
    return expensesMap;
  }

  private List<ExpensesCategoryResult> buildParam(List<ExpensesCategoryDo> categoryExpenses,
      List<CategoryDo> categoryDos) {
    List<ExpensesCategoryResult> list=new ArrayList<>();
    for(ExpensesCategoryDo expensesCountDo:categoryExpenses){
      ExpensesCategoryResult expensesCategoryResult = new ExpensesCategoryResult();
      expensesCategoryResult.setExpensesData(expensesCountDo.getExpensesData());
      expensesCategoryResult.setExpensesDate(expensesCountDo.getExpensesDate());
      expensesCategoryResult.setUserId(expensesCountDo.getUserId());
      for(CategoryDo categoryDo:categoryDos){
        if(categoryDo.getCategoryId()==expensesCountDo.getCategoryId()){
          expensesCategoryResult.setCategoryDesc(categoryDo.getCategoryDesc());
          break;
        }
      }
      if(StringUtil.isBlank(expensesCategoryResult.getCategoryDesc())){
        expensesCategoryResult.setCategoryDesc(String.valueOf(0));
      }
      list.add(expensesCategoryResult);
    }
    return list;
  }

  @Override
  public ServiceResult<List<ExpensesEveryCatResult>> getExpensesEveryCatResult(
      ExpensesCountQueryDo expensesCountQueryDo) {
    ServiceResult<List<ExpensesEveryCatResult>> result=new ServiceResult<>();
    List<EveryExpensesDo> everyCategoryExpenses = expensesDao
        .getEveryCategoryExpenses(expensesCountQueryDo);
    List<CategoryDo> categoryDos = categoryDao.selectCategoryList(expensesCountQueryDo.getUserId());
    result.setCode(0);
    result.setSuccess(true);
    result.setData(buildToCat(everyCategoryExpenses,categoryDos));
    return result;
  }

  @Override
  public ServiceResult<Integer> delSingleExpenses(long expensesId) {
    ServiceResult<Integer> result=new ServiceResult<>();
    Integer integer = expensesDao.delSingleExpenses(expensesId);
    result.setCode(0);
    result.setSuccess(true);
    result.setData(integer);
    return result;
  }

  private List<ExpensesEveryCatResult> buildToCat(List<EveryExpensesDo> everyCategoryExpenses,
      List<CategoryDo> categoryDos) {
    List<ExpensesEveryCatResult> list=new ArrayList<>();
    for(EveryExpensesDo expensesDo:everyCategoryExpenses){
      ExpensesEveryCatResult expensesEveryCatResult = new ExpensesEveryCatResult();
      expensesEveryCatResult.setSum(expensesDo.getExpensesData());
      expensesEveryCatResult.setUserId(expensesDo.getUserId());
      for(CategoryDo categoryDo:categoryDos){
        if(expensesDo.getCategoryId()==categoryDo.getCategoryId()){
          expensesEveryCatResult.setCategory(categoryDo.getCategoryDesc());
          break;
        }
      }
      if(StringUtil.isBlank(expensesEveryCatResult.getCategory())){
        expensesEveryCatResult.setCategory(String.valueOf(0));
      }
      list.add(expensesEveryCatResult);
    }
    return list;
  }
}
