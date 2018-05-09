package com.vbiso.controller;

import com.vbiso.domain.IncomeCountDo;
import com.vbiso.domain.IncomeCountQueryDo;
import com.vbiso.domain.IncomeDo;
import com.vbiso.domain.PageDo;
import com.vbiso.domain.UserDo;
import com.vbiso.form.IncomeCountForm;
import com.vbiso.form.IncomeForm;
import com.vbiso.form.PageForm;
import com.vbiso.pojo.IncomeExpensesQueryPojo;
import com.vbiso.result.LayUIResult;
import com.vbiso.result.ServiceResult;
import com.vbiso.service.IncomeService;
import com.vbiso.service.result.IncomeCategoryResult;
import com.vbiso.service.result.IncomeEveryCatResult;
import com.vbiso.utils.DateUtil;
import com.vbiso.utils.IdUtil;
import com.vbiso.utils.UserLoginUtil;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午10:23 2018/4/14
 * @Modified By:
 */
@Controller
@RequestMapping("/income")
public class IncomeController {

  @Autowired
  private IncomeService incomeService;

  @RequestMapping(value = "/page")
  @ResponseBody
  public LayUIResult<List<IncomeDo>> pageIncome(PageForm pageForm,HttpServletRequest request){
    UserDo userDo = UserLoginUtil.getUserLoginInfo(request);
    LayUIResult<List<IncomeDo>> layUIResult=new LayUIResult<>();
    IncomeExpensesQueryPojo incomeExpensesQueryPojo=new IncomeExpensesQueryPojo();
    incomeExpensesQueryPojo.setUserId(userDo.getUserId());
    incomeExpensesQueryPojo.setEnd(pageForm.getEnd());
    incomeExpensesQueryPojo.setStart(pageForm.getStart());
    incomeExpensesQueryPojo.setLimit(pageForm.getLimit());
    incomeExpensesQueryPojo.setPage(pageForm.getPage());
    incomeExpensesQueryPojo.setCategoryId(pageForm.getCategoryId());
    ServiceResult<PageDo<List<IncomeDo>>> result = incomeService.selectByPage(incomeExpensesQueryPojo);
    layUIResult.setCode(result.getCode());
    layUIResult.setMsg("success");
    layUIResult.setCount(result.getData().getTotalCount());
    layUIResult.setData(result.getData().getPage());
    return layUIResult;
  }

  @RequestMapping(value = "/listPage")
  public String returnListPage(){
    return "income";
  }

  @RequestMapping(value = "/addIncome")
  public String addIncome(){
    return "addIncome";
  }

  @RequestMapping(value = "/add")
  @ResponseBody
  public ServiceResult<Integer> add(@RequestBody IncomeForm incomeForm,HttpServletRequest request){
    UserDo userDo = UserLoginUtil.getUserLoginInfo(request);
    IncomeDo incomeDo = new IncomeDo();
    incomeDo.setIncomeId(IdUtil.generateId());
    incomeDo.setIncomeDate(incomeForm.getIncomeDate());
    incomeDo.setIncomeData(incomeForm.getIncomeData());
    incomeDo.setIncomeDesc(incomeForm.getDesc());
    incomeDo.setUserId(userDo.getUserId());
    incomeDo.setCategoryId(incomeForm.getCategory());
    ServiceResult<Integer> result = incomeService.addIncome(incomeDo);
    return result;
  }

  @RequestMapping(value = "/selectIncomeDay")
  @ResponseBody
  public ServiceResult<List<IncomeCountDo>>selectIncomeDay(@RequestBody IncomeCountForm incomeCountForm){
    IncomeCountQueryDo incomeCountQueryDo = new IncomeCountQueryDo();
    incomeCountQueryDo.setUserId(incomeCountForm.getUserId());
    incomeCountQueryDo.setStart(incomeCountForm.getStart());
    incomeCountQueryDo.setEnd(incomeCountForm.getEnd());
    ServiceResult<List<IncomeCountDo>> dayIncome = incomeService.getDayIncome(incomeCountQueryDo);
    return dayIncome;
  }

  @RequestMapping(value = "/incomeCount")
  public String returnIncomeCount(){
    return "incomecount";
  }

  @RequestMapping(value = "/incomeCategory")
  @ResponseBody
  public ServiceResult<Map<String,List<IncomeCategoryResult>>> getIncomeCategory(@RequestBody IncomeCountForm incomeCountForm){
    IncomeCountQueryDo incomeCountQueryDo = new IncomeCountQueryDo();
    incomeCountQueryDo.setUserId(incomeCountForm.getUserId());
    incomeCountQueryDo.setStart(incomeCountForm.getStart());
    incomeCountQueryDo.setEnd(incomeCountForm.getEnd());
    ServiceResult<Map<String, List<IncomeCategoryResult>>> incomeCategory = incomeService
        .getIncomeCategory(incomeCountQueryDo);
    return incomeCategory;
  }

  @RequestMapping(value = "/everyCategory")
  @ResponseBody
  public ServiceResult<List<IncomeEveryCatResult>> everyCategory(@RequestBody IncomeCountForm incomeCountForm){
    IncomeCountQueryDo incomeCountQueryDo=new IncomeCountQueryDo();
    incomeCountQueryDo.setEnd(incomeCountForm.getEnd());
    incomeCountQueryDo.setStart(incomeCountForm.getStart());
    incomeCountQueryDo.setUserId(incomeCountForm.getUserId());
    ServiceResult<List<IncomeEveryCatResult>> everyCatIncome = incomeService
        .getEveryCatIncome(incomeCountQueryDo);
    return everyCatIncome;
  }

}
