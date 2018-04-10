package com.vbiso.dao;

import com.vbiso.domain.IncomeDo;
import com.vbiso.mapping.FieldDo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeDao {
    String DAO_NAME="incomeDao";

    IncomeDo getByUserId(long userId);

    int insertIncome(IncomeDo incomeDo);

    int updateIncome(long incomeId, List<FieldDo> list);

    List<IncomeDo> selectPage(long userId,int start,int size);
}
