package com.vbiso.dao;

import com.vbiso.domain.EveryIncomeDo;
import com.vbiso.domain.IncomeCategoryDo;
import com.vbiso.domain.IncomeCountDo;
import com.vbiso.domain.IncomeCountQueryDo;
import com.vbiso.domain.IncomeDo;
import com.vbiso.domain.QueryDo;
import com.vbiso.domain.TimeJobIncomeDo;
import com.vbiso.exception.BaseException;
import com.vbiso.mapping.FieldDo;
import com.vbiso.pojo.IncomeExpensesQueryPojo;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.codehaus.jackson.map.Serializers.Base;
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

  Double getSumIncome(@Param("userId") long userId);

  List<IncomeCountDo> getDayIncome(IncomeCountQueryDo incomeCountQueryDo)throws BaseException;

  List<IncomeCategoryDo> getCategoryIncome(IncomeCountQueryDo incomeCountQueryDo)throws BaseException;

  List<EveryIncomeDo> getEveryCategoryIncome(IncomeCountQueryDo incomeCountQueryDo)throws BaseException;

  Double getTotalIncome(IncomeCountQueryDo incomeCountQueryDo) throws BaseException;

  List<TimeJobIncomeDo> getEveryDayIncome(QueryDo queryDo) throws BaseException;

  Integer delSingleIncome(@Param("incomeId")long incomeId)throws BaseException;

  Integer delBatchIncome(@Param("userId") long userId,@Param("categoryId") long categoryId);
}