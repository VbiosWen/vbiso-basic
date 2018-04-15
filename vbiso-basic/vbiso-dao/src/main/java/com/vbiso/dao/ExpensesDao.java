package com.vbiso.dao;

import com.vbiso.domain.ExpensesDo;
import com.vbiso.mapping.FieldDo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpensesDao {
    String DAO_NAME="expenseDao";

    ExpensesDo getByUserId(long userId)throws Exception;

    int insertExpenses(ExpensesDo expensesDo)throws Exception;

    int updateExpensesField(long userId, List<FieldDo> list)throws Exception;

    List<ExpensesDo> selectPage(@Param("userId") long userId,@Param("start") int start,@Param("size") int size)throws Exception;

    long getTotcalCount(@Param("userId") long userId);

}
