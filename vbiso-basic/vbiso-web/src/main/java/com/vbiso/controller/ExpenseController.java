package com.vbiso.controller;

import com.vbiso.domain.ExpensesDo;
import com.vbiso.domain.PageDo;
import com.vbiso.domain.UserDo;
import com.vbiso.form.ExpenseForm;
import com.vbiso.form.PageForm;
import com.vbiso.pojo.IncomeExpensesQueryPojo;
import com.vbiso.result.LayUIResult;
import com.vbiso.result.ServiceResult;
import com.vbiso.service.ExpensesService;
import com.vbiso.utils.UserLoginUtil;
import java.util.List;
import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午12:32 2018/4/24
 * @Modified By:
 */
@Controller
@RequestMapping("/expense")
public class ExpenseController {

  @Autowired
  private ExpensesService expensesService;

  @RequestMapping(value = "/listPage")
  public String page(){
    return "expense";
  }

  @RequestMapping(value = "/page")
  @ResponseBody
  public LayUIResult<List<ExpensesDo>> pageExpense(PageForm pageForm,HttpServletRequest request){
    UserDo userLoginInfo = UserLoginUtil.getUserLoginInfo(request);
    IncomeExpensesQueryPojo incomeExpensesQueryPojo=new IncomeExpensesQueryPojo();
    incomeExpensesQueryPojo.setCategoryId(pageForm.getCategoryId());
    incomeExpensesQueryPojo.setEnd(pageForm.getEnd());
    incomeExpensesQueryPojo.setStart(pageForm.getStart());
    incomeExpensesQueryPojo.setLimit(pageForm.getLimit());
    incomeExpensesQueryPojo.setPage(pageForm.getPage());
    incomeExpensesQueryPojo.setUserId(userLoginInfo.getUserId());
    ServiceResult<PageDo<List<ExpensesDo>>> result = expensesService
        .selectPage(incomeExpensesQueryPojo);
    LayUIResult<List<ExpensesDo>> layUIResult=new LayUIResult();
    layUIResult.setData(result.getData().getPage());
    layUIResult.setCount(result.getData().getTotalCount());
    layUIResult.setMsg(result.getMsg());
    layUIResult.setCode(result.getCode());
    return layUIResult;
  }

  @RequestMapping(value = "/add")
  public String add(){
    return "addExpense";
  }

  @RequestMapping(value = "/addExpense")
  @ResponseBody
  public ServiceResult<Integer> addExpense(@RequestBody ExpenseForm expenseForm,HttpServletRequest request){
    UserDo userLoginInfo = UserLoginUtil.getUserLoginInfo(request);
    ExpensesDo expensesDo=new ExpensesDo();
    expensesDo.setUserId(userLoginInfo.getUserId());
    expensesDo.setExpensesId(System.currentTimeMillis());
    expensesDo.setExpensesDate(expenseForm.getExpensesDate());
    expensesDo.setExpensesData(expenseForm.getExpensesData());
    expensesDo.setExpensesDesc(expenseForm.getDesc());
    ServiceResult<Integer> result = expensesService.insertExpenses(expensesDo);
    return result;
  }

}
