package com.vbiso.dao.test;

import com.alibaba.druid.support.json.JSONUtils;
import com.vbiso.dao.UserDao;
import com.vbiso.domain.UserDo;
import com.vbiso.mapping.FieldDo;
import java.lang.reflect.Field;
import java.util.ArrayList;
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


  @Before
  public void init(){
    context=new ClassPathXmlApplicationContext(new String[]{"classpath:vbiso-dao.xml"});
    userDao = (UserDao) context.getBean(UserDao.DAO_NAME);
  }

  @Test
  public void testInsertUser() throws Exception {
    UserDo userDo = new UserDo();
    userDo.setUserId(1L);
    userDo.setPassword("123456");
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
    userDo.setPassword(password);
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


}
