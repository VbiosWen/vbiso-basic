package com.vbiso.dao;

import com.vbiso.domain.ExpensesDo;
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

  double getSumData(@Param("userId") long userId);

}
