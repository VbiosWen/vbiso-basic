package com.vbiso.dao.test;

import com.alibaba.druid.support.json.JSONUtils;
import com.vbiso.dao.IncomeDao;
import com.vbiso.domain.IncomeDo;
import com.vbiso.domain.PageDo;
import com.vbiso.utils.JsonUtil;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午12:03 2018/4/10
 * @Modified By:
 */
public class IncomoTest {

  private IncomeDao incomeDao;

  private ApplicationContext context;
  @Before
  public void init(){
    context=new ClassPathXmlApplicationContext(new String[]{"classpath:vbiso-dao.xml"});
    incomeDao= (IncomeDao) context.getBean("incomeDao");

  }
  @Test
  public void testInsert(){
    IncomeDo incomeDo = new IncomeDo();
    incomeDo.setUserId(1L);
    incomeDo.setIncomeData(55.5);
    incomeDo.setIncomeDate(System.currentTimeMillis());
    incomeDo.setIncomeId(3L);
    incomeDo.setIncomeDesc("test");
    incomeDao.insertIncome(incomeDo);
  }

  @Test
  public void selectPage(){
    PageDo pageDo=new PageDo();
    pageDo.setUserId(1L);
    pageDo.setSize(2);
    pageDo.setStart(0);
    //List<IncomeDo> incomeDos = incomeDao.selectPage(1L,0,2);
    //System.out.println(JsonUtil.toJson(incomeDos));
  }

  @Test
  public void totalCount(){
    //long totalCount = incomeDao.getTotalCount(1L);
    //System.out.println(totalCount);
  }

}
