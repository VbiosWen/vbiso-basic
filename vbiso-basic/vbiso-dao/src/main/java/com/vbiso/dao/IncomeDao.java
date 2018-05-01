package com.vbiso.dao;

import com.vbiso.domain.IncomeDo;
import com.vbiso.exception.BaseException;
import com.vbiso.mapping.FieldDo;
import com.vbiso.pojo.IncomeExpensesQueryPojo;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeDao {

  String DAO_NAME = "incomeDao";

  IncomeDo getByUserId(long userId) throws BaseException;

  int insertIncome(IncomeDo incomeDo) throws BaseException;

  int updateIncome(long incomeId, List<FieldDo> list) throws BaseException;

  List<IncomeDo> selectPage(IncomeExpensesQueryPojo incomeExpensesQueryPojo) throws BaseException;

  long getTotalCount(IncomeExpensesQueryPojo incomeExpensesQueryPojo)
      throws BaseException;

  double getSumIncome(@Param("userId") long userId);
}