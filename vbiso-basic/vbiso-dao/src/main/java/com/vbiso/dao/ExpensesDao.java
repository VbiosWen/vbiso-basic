package com.vbiso.dao;

import com.vbiso.domain.ExpensesDo;
import com.vbiso.exception.BaseException;
import com.vbiso.mapping.FieldDo;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.codehaus.jackson.map.Serializers.Base;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpensesDao {

  String DAO_NAME = "expenseDao";

  ExpensesDo getByUserId(long userId) throws BaseException;

  int insertExpenses(ExpensesDo expensesDo) throws BaseException;

  int updateExpensesField(long userId, List<FieldDo> list) throws BaseException;

  List<ExpensesDo> selectPage(@Param("userId") long userId, @Param("start") int start,
      @Param("size") int size) throws BaseException;

  long getTotcalCount(@Param("userId") long userId)throws BaseException;

}
