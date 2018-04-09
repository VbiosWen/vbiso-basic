package com.vbiso.dao;

import com.vbiso.domain.ExpensesDo;
import com.vbiso.mapping.FieldDo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpensesDao {
    String DAO_NAME="expenseDao";

    ExpensesDo getByUserId(long userId);

    int insertExpenses(ExpensesDo expensesDo);

    int updateExpensesField(long userId, List<FieldDo> list);

}
