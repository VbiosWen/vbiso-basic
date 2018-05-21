package com.vbiso.service;

import com.vbiso.domain.ExpensesCountDo;
import com.vbiso.domain.ExpensesCountQueryDo;
import com.vbiso.domain.ExpensesDo;
import com.vbiso.domain.PageDo;
import com.vbiso.pojo.IncomeExpensesQueryPojo;
import com.vbiso.result.ServiceResult;
import com.vbiso.service.result.ExpensesCategoryResult;
import com.vbiso.service.result.ExpensesEveryCatResult;
import java.util.List;
import java.util.Map;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午11:55 2018/4/14
 * @Modified By:
 */
public interface ExpensesService {

  String SERVICENAME = "expensesService";

  ServiceResult<PageDo<List<ExpensesDo>>> selectPage(
      IncomeExpensesQueryPojo incomeExpensesQueryPojo);

  ServiceResult<Integer> insertExpenses(ExpensesDo expensesDo);

  ServiceResult<List<ExpensesCountDo>> getIncomeDay(ExpensesCountQueryDo expensesCountQueryDo);

  ServiceResult<Map<String, List<ExpensesCategoryResult>>> getExpensesCategory(
      ExpensesCountQueryDo expensesCountQueryDo);

  ServiceResult<List<ExpensesEveryCatResult>> getExpensesEveryCatResult(
      ExpensesCountQueryDo expensesCountQueryDo);

  ServiceResult<Integer> delSingleExpenses(long expensesId);
}
