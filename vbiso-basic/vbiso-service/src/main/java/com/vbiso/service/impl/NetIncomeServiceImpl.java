package com.vbiso.service.impl;

import com.vbiso.dao.CategoryDao;
import com.vbiso.dao.ExpensesDao;
import com.vbiso.dao.IncomeDao;
import com.vbiso.domain.CategoryDo;
import com.vbiso.domain.EveryExpensesDo;
import com.vbiso.domain.EveryIncomeDo;
import com.vbiso.domain.ExpensesCountQueryDo;
import com.vbiso.domain.IncomeCountQueryDo;
import com.vbiso.redis.RedisDao;
import com.vbiso.result.ServiceResult;
import com.vbiso.service.NetIncomeService;
import com.vbiso.service.result.NetIncomeCategoryResult;
import com.vbiso.service.result.TotalNetIncome;
import com.vbiso.utils.StringUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午6:34 2018/5/14
 * @Modified By:
 */
@Service
public class NetIncomeServiceImpl implements NetIncomeService {

  @Autowired
  private IncomeDao incomeDao;

  @Autowired
  private ExpensesDao expensesDao;

  @Autowired
  private RedisDao redisDao;

  @Autowired
  private CategoryDao categoryDao;


  @Override
  public ServiceResult<TotalNetIncome> getTotalNetIncome(IncomeCountQueryDo incomeCountQueryDo) {
    ServiceResult<TotalNetIncome> result = new ServiceResult<>();
    Double totalIncome = incomeDao.getTotalIncome(incomeCountQueryDo);
    Double totalExpenses = expensesDao.getTotalExpenses(incomeCountQueryDo);
    TotalNetIncome totalNetIncome = new TotalNetIncome();
    totalNetIncome.setIncomeData(totalIncome == null ? 0 : totalIncome);
    totalNetIncome.setExpensesData(totalExpenses == null ? 0 : totalExpenses);
    result.setData(totalNetIncome);
    result.setCode(0);
    result.setSuccess(true);

    return result;
  }

  @Override
  public ServiceResult<Map<String, Double>> getTotalNetIncomeByCat(
      IncomeCountQueryDo incomeCountQueryDo) {
    ServiceResult<Map<String,Double>> result=new ServiceResult<>();
    List<EveryIncomeDo> everyCategoryIncome = incomeDao.getEveryCategoryIncome(incomeCountQueryDo);
    ExpensesCountQueryDo expensesCountQueryDo = new ExpensesCountQueryDo();
    expensesCountQueryDo.setEnd(incomeCountQueryDo.getEnd());
    expensesCountQueryDo.setStart(incomeCountQueryDo.getStart());
    expensesCountQueryDo.setUserId(incomeCountQueryDo.getUserId());
    List<EveryExpensesDo> everyCategoryExpenses = expensesDao
        .getEveryCategoryExpenses(expensesCountQueryDo);
    List<CategoryDo> categoryDos = categoryDao.selectCategoryList(incomeCountQueryDo.getUserId());
    List<NetIncomeCategoryResult> results = buildParam(everyCategoryIncome, categoryDos);
    Map<String, Double> data = new HashMap<>();
    for(CategoryDo categoryDo:categoryDos){
      data.put(categoryDo.getCategoryDesc(),Double.valueOf(0));
    }

    for(NetIncomeCategoryResult netIncomeCategoryResult:results){
      if(data.containsKey(netIncomeCategoryResult.getCategory())){
        data.put(netIncomeCategoryResult.getCategory(),netIncomeCategoryResult.getData());
      }
    }
    List<NetIncomeCategoryResult> results1 = buildExpensesParam(everyCategoryExpenses, categoryDos);

    for(NetIncomeCategoryResult netIncomeCategoryResult:results1){
      if(data.containsKey(netIncomeCategoryResult.getCategory())){
        Double aDouble = data.get(netIncomeCategoryResult.getCategory());
        data.put(netIncomeCategoryResult.getCategory(),aDouble-netIncomeCategoryResult.getData());
      }
    }
    result.setSuccess(true);
    result.setData(data);
    result.setCode(0);
    return result;
  }

  private List<NetIncomeCategoryResult> buildExpensesParam(
      List<EveryExpensesDo> everyCategoryExpenses,
      List<CategoryDo> categoryDos) {
    List<NetIncomeCategoryResult> results = new ArrayList<>();
    for (EveryExpensesDo everyExpensesDo : everyCategoryExpenses) {
      NetIncomeCategoryResult netIncomeCategoryResult = new NetIncomeCategoryResult();
      netIncomeCategoryResult.setData(everyExpensesDo.getExpensesData());
      for (CategoryDo categoryDo : categoryDos) {
        if (everyExpensesDo.getCategoryId() == categoryDo.getCategoryId()) {
          netIncomeCategoryResult.setCategory(
              StringUtil.isBlank(categoryDo.getCategoryDesc()) ? String.valueOf(0)
                  : categoryDo.getCategoryDesc());
        }
      }
      if(StringUtil.isBlank(netIncomeCategoryResult.getCategory())){
        netIncomeCategoryResult.setCategory(String.valueOf(0));
      }
      results.add(netIncomeCategoryResult);
    }
    return results;
  }

  private List<NetIncomeCategoryResult> buildParam(List<EveryIncomeDo> everyCategoryIncome,
      List<CategoryDo> categoryDos) {
    List<NetIncomeCategoryResult> results = new ArrayList<>();
    for (EveryIncomeDo everyIncomeDo : everyCategoryIncome) {
      NetIncomeCategoryResult categoryResult = new NetIncomeCategoryResult();
      categoryResult.setData(everyIncomeDo.getIncomeData());
      for (CategoryDo categoryDo : categoryDos) {
        if (everyIncomeDo.getCategoryId() == categoryDo.getCategoryId()) {
          categoryResult.setCategory(
              StringUtil.isBlank(categoryDo.getCategoryDesc()) ? String.valueOf(0)
                  : categoryDo.getCategoryDesc());
        }
      }
      if (StringUtil.isBlank(categoryResult.getCategory())) {
        categoryResult.setCategory(String.valueOf(0));
      }
      results.add(categoryResult);
    }
    return results;
  }
}
