package com.vbiso.service.impl;

import com.vbiso.dao.ExpensesDao;
import com.vbiso.domain.ExpensesDo;
import com.vbiso.domain.PageDo;
import com.vbiso.exception.BaseException;
import com.vbiso.pojo.IncomeExpensesQueryPojo;
import com.vbiso.result.ServiceResult;
import com.vbiso.service.ExpensesService;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午11:56 2018/4/14
 * @Modified By:
 */
@Service
public class ExpensesServiceImpl implements ExpensesService {

  private static Logger logger=Logger.getLogger(ExpensesServiceImpl.class);

  @Autowired
  private ExpensesDao expensesDao;

  @Override
  public ServiceResult<PageDo<List<ExpensesDo>>> selectPage(IncomeExpensesQueryPojo incomeExpensesQueryPojo) {
    ServiceResult<PageDo<List<ExpensesDo>>> result=new ServiceResult<>();
    try {
      List<ExpensesDo> expensesDos = expensesDao.selectPage(incomeExpensesQueryPojo);
      long totcalCount = expensesDao.getTotalCount(incomeExpensesQueryPojo);
      PageDo<List<ExpensesDo>> pageDo = new PageDo<>();
      pageDo.setUserId(incomeExpensesQueryPojo.getUserId());
      pageDo.setTotalCount(totcalCount);
      pageDo.setPage(expensesDos);
      result.setData(pageDo);
      result.setSuccess(true);
      result.setCode(0);
    } catch (BaseException e) {
      result.setSuccess(false);
      result.setCode(-1);
      logger.error("select page error:"+incomeExpensesQueryPojo.getUserId(),e);
    }
    return result;
  }

  @Override
  public ServiceResult<Integer> insertExpenses(ExpensesDo expensesDo) {
    ServiceResult<Integer> result=new ServiceResult<>();
    try {
      int i = expensesDao.insertExpenses(expensesDo);
      result.setSuccess(true);
      result.setCode(0);
      result.setMsg("success");
      result.setData(i);
    } catch (BaseException e) {
      result.setSuccess(false);
      logger.error("insert into expenses error:",e);
    }
    return result;
  }
}
