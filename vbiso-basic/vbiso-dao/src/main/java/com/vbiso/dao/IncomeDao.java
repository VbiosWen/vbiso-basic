package com.vbiso.dao;

import com.vbiso.domain.IncomeDo;
import com.vbiso.domain.PageDo;
import com.vbiso.mapping.FieldDo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeDao {

  String DAO_NAME = "incomeDao";

  IncomeDo getByUserId(long userId) throws Exception;

  int insertIncome(IncomeDo incomeDo) throws Exception;

  int updateIncome(long incomeId, List<FieldDo> list) throws Exception;

  List<IncomeDo> selectPage(@Param("userId") long userId, @Param("start") int start,
      @Param("size") int size) throws Exception;

  long getTotalCount(long userId)throws Exception;
}
