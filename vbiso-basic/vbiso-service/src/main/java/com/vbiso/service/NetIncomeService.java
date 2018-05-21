package com.vbiso.service;

import com.vbiso.domain.IncomeCountQueryDo;
import com.vbiso.result.ServiceResult;
import com.vbiso.service.result.NetIncomeResult;
import com.vbiso.service.result.TotalNetIncome;
import java.util.List;
import java.util.Map;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午6:08 2018/5/14
 * @Modified By:
 */
public interface NetIncomeService {


 ServiceResult<TotalNetIncome> getTotalNetIncome(IncomeCountQueryDo incomeCountQueryDo);

 ServiceResult<Map<String,Double>> getTotalNetIncomeByCat(IncomeCountQueryDo incomeCountQueryDo);

}
