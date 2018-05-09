package com.vbiso.service.test;

import com.vbiso.domain.UserDo;
import com.vbiso.result.ServiceResult;
import com.vbiso.service.UserService;
import com.vbiso.service.result.IncomeCategoryResult;
import com.vbiso.utils.JsonUtil;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午3:40 2018/3/13
 * @Modified By:
 */
public class UserServiceTest {

  private ApplicationContext context;

  private UserService userService;

  @Before
  public void init(){
    context=new ClassPathXmlApplicationContext(new String[]{"classpath:vbiso-service.xml"});
    userService= (UserService) context.getBean(UserService.SERVICE_NAME);
  }

  @Test
  public void testLogin(){

    Instant instant = Instant.ofEpochMilli(System.currentTimeMillis());
    ZoneId zoneId = ZoneId.systemDefault();
    LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
    int dayOfYear = localDateTime.getDayOfYear();
    System.out.println(dayOfYear);
  }

  @Test
  public void testMap(){
    Map<String,List<Double>> listMap=new HashMap<>();
    listMap.put("test",new ArrayList<>());
    listMap.get("test").add(1.233);
    System.out.println(JsonUtil.toJson(listMap));
    System.out.println(LocalDateTime.now().getDayOfYear());
  }

  @Test
  public void testGet(){
    List<IncomeCategoryResult> list=new ArrayList<>();
    IncomeCategoryResult incomeCategoryResult = new IncomeCategoryResult();
    incomeCategoryResult.setIncomeDate(System.currentTimeMillis()+1000);

    list.add(incomeCategoryResult);
    IncomeCategoryResult incomeCategoryResult1=new IncomeCategoryResult();
    incomeCategoryResult1.setIncomeDate(System.currentTimeMillis()-1000);
    list.add(incomeCategoryResult1);
    IncomeCategoryResult incomeCategoryResult2=new IncomeCategoryResult();
    incomeCategoryResult2.setIncomeDate(System.currentTimeMillis());
    list.add(incomeCategoryResult2);
    System.out.println(JsonUtil.toJson(list));
    System.out.println(Instant.now().toEpochMilli());
    System.out.println(Instant.now().getEpochSecond());
  }
}
