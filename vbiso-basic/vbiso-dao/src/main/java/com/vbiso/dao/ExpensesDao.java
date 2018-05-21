package com.vbiso.dao;

import com.vbiso.domain.EveryExpensesDo;
import com.vbiso.domain.ExpensesCategoryDo;
import com.vbiso.domain.ExpensesCountDo;
import com.vbiso.domain.ExpensesCountQueryDo;
import com.vbiso.domain.ExpensesDo;
import com.vbiso.domain.IncomeCountQueryDo;
import com.vbiso.domain.QueryDo;
import com.vbiso.domain.TimeJobExpensesDo;
import com.vbiso.exception.BaseException;
import com.vbiso.mapping.FieldDo;
import com.vbiso.pojo.IncomeExpensesQueryPojo;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.codehaus.jackson.map.Serializers.Base;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpensesDao {

  String DAO_NAME = "expenseDao";

  ExpensesDo getByUserId(long userId) throws BaseException;

  int insertExpenses(ExpensesDo expensesDo) throws BaseException;

  int updateExpensesField(@Param("userId") long userId,@Param("list") List<FieldDo> list) throws BaseException;

  List<ExpensesDo> selectPage(IncomeExpensesQueryPojo incomeExpensesQueryPojo) throws BaseException;

  long getTotalCount(IncomeExpensesQueryPojo incomeExpensesQueryPojo)throws BaseException;

  Double getSumData(@Param("userId") long userId);

  List<ExpensesCountDo> getDayExpenses(ExpensesCountQueryDo expensesCountQueryDo)throws BaseException;

  List<ExpensesCategoryDo> getCategoryExpenses(ExpensesCountQueryDo expensesCountQueryDo)throws BaseException;

  List<EveryExpensesDo> getEveryCategoryExpenses(ExpensesCountQueryDo expensesCountQueryDo)throws BaseException;

  Double getTotalExpenses(IncomeCountQueryDo incomeCountQueryDo) throws BaseException;

  List<TimeJobExpensesDo> getEveryDayExpenses(QueryDo queryDo)throws BaseException;

  Integer delSingleExpenses(@Param("expensesId") long expensesId)throws BaseException;

  Integer delBatchExpenses(@Param("userId") long userId,@Param("categoryId") long categoryId) throws BaseException;
}
