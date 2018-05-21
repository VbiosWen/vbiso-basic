package com.vbiso.dao.test;

import com.alibaba.druid.support.json.JSONUtils;
import com.vbiso.dao.ExpensesDao;
import com.vbiso.dao.IncomeDao;
import com.vbiso.dao.NetincomeDao;
import com.vbiso.dao.UserDao;
import com.vbiso.domain.ExpensesDo;
import com.vbiso.domain.NetIncomeDo;
import com.vbiso.domain.UserDo;
import com.vbiso.mapping.FieldDo;
import com.vbiso.utils.JsonUtil;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 上午9:56 2018/3/13
 * @Modified By:
 */
public class UserDaoTest {

  private ApplicationContext context;
  private UserDao userDao;
  private IncomeDao incomeDao;
  private ExpensesDao expensesDao;
  private NetincomeDao netincomeDao;


  @Before
  public void init(){
    context=new ClassPathXmlApplicationContext(new String[]{"classpath:vbiso-dao.xml"});
    userDao = (UserDao) context.getBean(UserDao.DAO_NAME);
    incomeDao= (IncomeDao) context.getBean("incomeDao");
    expensesDao= (ExpensesDao) context.getBean("expensesDao");
    netincomeDao= (NetincomeDao) context.getBean("netincomeDao");
  }

  @Test
  public void testInsertUser() throws Exception {
    UserDo userDo = new UserDo();
    userDo.setUserId(1L);
    userDo.setUserPassword("123456");
    userDo.setModifyTime(System.currentTimeMillis());
    userDo.setUserMobile("15639114941");
    userDo.setCreatedTime(System.currentTimeMillis());
    userDo.setUserNick("test");
    int i = userDao.insertUser(userDo);
    System.out.println(i);
  }

  @Test
  public void testSelectByUserID() throws Exception {
    long userId=1L;
    String password="123456";
    UserDo userDo = new UserDo();
    userDo.setUserId(userId);
    userDo.setUserPassword(password);
    UserDo userDo1 = userDao.getByUserId(userDo);
    System.out.println(userDo1.getUserNick());
  }

  @Test
  public void testUpdateFields() throws Exception {
    ArrayList<FieldDo> fieldDos = new ArrayList<>();
    FieldDo fieldDo = new FieldDo();
    fieldDo.setKey("user_nick");
    fieldDo.setValue("test-wenliujie1");
    fieldDos.add(fieldDo);
    userDao.updateFieldByUserId(1L, fieldDos);
  }

  @Test
  public void testGetByUserId(){
    UserDo userDo = new UserDo();
    userDo.setUserMobile("15639114941");
    userDo.setUserPassword("123456");
    UserDo byUserId = userDao.getByUserId(userDo);
    System.out.println(JsonUtil.toJson(byUserId));
  }

  @Test
  public void insertnetincome(){
    double sumIncome = incomeDao.getSumIncome(1L);
    double sumExpenses=expensesDao.getSumData(1L);
    NetIncomeDo netIncomeDo = new NetIncomeDo();
    netIncomeDo.setUserId(1L);
    netIncomeDo.setNetincomeData(sumIncome-sumExpenses);
    netIncomeDo.setNetincomeId(System.currentTimeMillis());
    netIncomeDo.setNetincomeDate(System.currentTimeMillis());
    netincomeDao.insertNetincome(netIncomeDo);
  }

  @Test
  public void test(){
    System.out.println(new Date(1523333195156L));
  }

  @Test
  public void testGetUserInfoByMobile(){
    UserDo userInfoByMobile = userDao.getUserInfoByMobile("154434444");
    System.out.println(userInfoByMobile);
  }

  @Test
  public void testGetSumData(){
    Double sumIncome = incomeDao.getSumIncome(1526127171268L);
    System.out.println(sumIncome);
  }


}
