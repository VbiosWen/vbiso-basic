package com.vbiso.service;

import com.vbiso.domain.IncomeCountDo;
import com.vbiso.domain.IncomeCountQueryDo;
import com.vbiso.domain.IncomeDo;
import com.vbiso.domain.PageDo;
import com.vbiso.pojo.IncomeExpensesQueryPojo;
import com.vbiso.result.ServiceResult;
import com.vbiso.service.result.IncomeCategoryResult;
import com.vbiso.service.result.IncomeEveryCatResult;
import java.util.List;
import java.util.Map;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午11:03 2018/4/14
 * @Modified By:
 */
public interface IncomeService {

  ServiceResult<PageDo<List<IncomeDo>>> selectByPage(IncomeExpensesQueryPojo incomeExpensesQueryPojo);

  ServiceResult<Integer>addIncome(IncomeDo incomeDo);

  ServiceResult<List<IncomeCountDo>> getDayIncome(IncomeCountQueryDo incomeCountQueryDo);

  ServiceResult<Map<String,List<IncomeCategoryResult>>> getIncomeCategory(IncomeCountQueryDo incomeCountQueryDo);

  ServiceResult<List<IncomeEveryCatResult>> getEveryCatIncome(IncomeCountQueryDo incomeCountQueryDo);

}
