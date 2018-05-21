package com.vbiso.dao.test;

import com.vbiso.dao.ExpensesDao;
import com.vbiso.domain.EveryExpensesDo;
import com.vbiso.domain.ExpensesCategoryDo;
import com.vbiso.domain.ExpensesCountDo;
import com.vbiso.domain.ExpensesCountQueryDo;
import com.vbiso.domain.ExpensesDo;
import com.vbiso.pojo.IncomeExpensesQueryPojo;
import com.vbiso.utils.JsonUtil;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午1:32 2018/4/24
 * @Modified By:
 */
public class ExpenseTest {

  private ApplicationContext context;

  private ExpensesDao expensesDao;

  @Before
  public void init(){
    context=new ClassPathXmlApplicationContext(new String[]{"classpath:vbiso-dao.xml"});
    expensesDao= (ExpensesDao) context.getBean("expensesDao");
  }

  @Test
  public void insert(){
    ExpensesDo expensesDo = new ExpensesDo();
    expensesDo.setUserId(1L);
    expensesDo.setExpensesDate(System.currentTimeMillis());
    expensesDo.setExpensesData(1244.00);
    expensesDo.setExpensesDesc("test");
    expensesDo.setExpensesId(System.currentTimeMillis());
    expensesDo.setCategoryId(1L);
    int i = expensesDao.insertExpenses(expensesDo);
    System.out.println(i);
  }

  @Test
  public void testCount(){
    long totalCount = expensesDao.getTotalCount(null);
    System.out.println(totalCount);
  }

  @Test
  public void testPage(){
    IncomeExpensesQueryPojo incomeExpensesQueryPojo = new IncomeExpensesQueryPojo();
    incomeExpensesQueryPojo.setPage(1);
    incomeExpensesQueryPojo.setLimit(10);
    incomeExpensesQueryPojo.setUserId(1L);
    incomeExpensesQueryPojo.setStart(System.currentTimeMillis()-10000000);
    incomeExpensesQueryPojo.setEnd(System.currentTimeMillis());
    List<ExpensesDo> expensesDos =
        expensesDao.selectPage(incomeExpensesQueryPojo);
    System.out.println(JsonUtil.toJson(expensesDos));
  }

  @Test
  public void testSum(){
    double sumData = expensesDao.getSumData(1L);
    System.out.println(sumData);
  }

  @Test
  public void testQuery(){
    ExpensesCountQueryDo expensesCountQueryDo=new ExpensesCountQueryDo();
    expensesCountQueryDo.setUserId(1L);
    expensesCountQueryDo.setEnd(System.currentTimeMillis());
    expensesCountQueryDo.setStart(1L);
    List<ExpensesCategoryDo> categoryExpenses =
        expensesDao.getCategoryExpenses(expensesCountQueryDo);
    System.out.println(JsonUtil.toJson(categoryExpenses));
  }

  @Test
  public void testQueryQ(){
    ExpensesCountQueryDo expensesCountQueryDo=new ExpensesCountQueryDo();
    expensesCountQueryDo.setUserId(1L);
    expensesCountQueryDo.setEnd(System.currentTimeMillis());
    expensesCountQueryDo.setStart(1L);
    List<EveryExpensesDo> everyCategoryExpenses = expensesDao
        .getEveryCategoryExpenses(expensesCountQueryDo);
    System.out.println(JsonUtil.toJson(everyCategoryExpenses));
  }

  @Test
  public void testQueryCount(){
    ExpensesCountQueryDo expensesCountQueryDo=new ExpensesCountQueryDo();
    expensesCountQueryDo.setUserId(1L);
    expensesCountQueryDo.setEnd(1525881600000L);
    expensesCountQueryDo.setStart(1525276800000L);
    List<ExpensesCountDo> dayExpenses = expensesDao.getDayExpenses(expensesCountQueryDo);
    System.out.println(JsonUtil.toJson(dayExpenses));
  }
}
