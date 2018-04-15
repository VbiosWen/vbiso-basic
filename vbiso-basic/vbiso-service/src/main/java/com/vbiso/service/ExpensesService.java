package com.vbiso.service;

import com.vbiso.domain.ExpensesDo;
import com.vbiso.domain.PageDo;
import com.vbiso.result.ServiceResult;
import java.util.List;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午11:55 2018/4/14
 * @Modified By:
 */
public interface ExpensesService {

  String SERVICENAME="expensesService";

  ServiceResult<PageDo<List<ExpensesDo>>> selectPage(long userId,int start,int size);

}
