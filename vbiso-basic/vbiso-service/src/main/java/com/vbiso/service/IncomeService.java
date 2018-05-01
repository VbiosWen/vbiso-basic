package com.vbiso.service;

import com.vbiso.domain.IncomeDo;
import com.vbiso.domain.PageDo;
import com.vbiso.pojo.IncomeExpensesQueryPojo;
import com.vbiso.result.ServiceResult;
import java.util.List;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午11:03 2018/4/14
 * @Modified By:
 */
public interface IncomeService {

  ServiceResult<PageDo<List<IncomeDo>>> selectByPage(IncomeExpensesQueryPojo incomeExpensesQueryPojo);

  ServiceResult<Integer>addIncome(IncomeDo incomeDo);

}
