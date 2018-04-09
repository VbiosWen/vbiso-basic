package com.vbiso.service.test;

import com.vbiso.domain.UserDo;
import com.vbiso.result.ServiceResult;
import com.vbiso.service.UserService;
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

  }
}
