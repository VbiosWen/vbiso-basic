package com.vbiso.controller;

import com.vbiso.domain.IncomeCountQueryDo;
import com.vbiso.form.IncomeCountForm;
import com.vbiso.result.ServiceResult;
import com.vbiso.service.NetIncomeService;
import com.vbiso.service.result.TotalNetIncome;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午12:38 2018/5/14
 * @Modified By:
 */
@Controller
@RequestMapping("/netincome")
public class NetIncomeController {

  @Autowired
  private NetIncomeService netIncomeService;

  @RequestMapping(value = "/netincomeCount")
  public String returnNetIncome() {
    return "netincome";
  }


  @RequestMapping(value = "/totalNetIncome")
  @ResponseBody
  public ServiceResult<TotalNetIncome> getTotalNetIncome(
      @RequestBody IncomeCountForm incomeCountForm) {
    IncomeCountQueryDo incomeCountQueryDo = new IncomeCountQueryDo();
    incomeCountQueryDo.setEnd(incomeCountForm.getEnd());
    incomeCountQueryDo.setStart(incomeCountForm.getStart());
    incomeCountQueryDo.setUserId(incomeCountForm.getUserId());
    ServiceResult<TotalNetIncome> totalNetIncome = netIncomeService
        .getTotalNetIncome(incomeCountQueryDo);
    return totalNetIncome;
  }

  @RequestMapping(value = "/totalNetIncomeByCat")
  @ResponseBody
  public ServiceResult<Map<String, Double>> getTotalNetIncomeByCat(
      @RequestBody IncomeCountForm incomeCountForm) {
    IncomeCountQueryDo incomeCountQueryDo=new IncomeCountQueryDo();
    incomeCountQueryDo.setEnd(incomeCountForm.getEnd());
    incomeCountQueryDo.setStart(incomeCountForm.getStart());
    incomeCountQueryDo.setUserId(incomeCountForm.getUserId());
    ServiceResult<Map<String, Double>> totalNetIncomeByCat = netIncomeService
        .getTotalNetIncomeByCat(incomeCountQueryDo);
    return totalNetIncomeByCat;
  }

}
