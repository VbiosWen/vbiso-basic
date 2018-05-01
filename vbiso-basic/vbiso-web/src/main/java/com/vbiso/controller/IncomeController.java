package com.vbiso.controller;

import com.vbiso.domain.IncomeDo;
import com.vbiso.domain.PageDo;
import com.vbiso.domain.UserDo;
import com.vbiso.form.IncomeForm;
import com.vbiso.form.PageForm;
import com.vbiso.pojo.IncomeExpensesQueryPojo;
import com.vbiso.result.LayUIResult;
import com.vbiso.result.ServiceResult;
import com.vbiso.service.IncomeService;
import com.vbiso.utils.JsonUtil;
import com.vbiso.utils.UserLoginUtil;
import java.util.List;
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
    incomeDo.setIncomeId(System.currentTimeMillis());
    incomeDo.setIncomeDate(incomeForm.getIncomeDate());
    incomeDo.setIncomeData(incomeForm.getIncomeData());
    incomeDo.setIncomeDesc(incomeForm.getDesc());
    incomeDo.setUserId(userDo.getUserId());
    ServiceResult<Integer> result = incomeService.addIncome(incomeDo);
    return result;
  }

}
