package com.vbiso.dao.test;

import com.vbiso.dao.CategoryDao;
import com.vbiso.domain.CategoryDo;
import com.vbiso.utils.JsonUtil;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午10:48 2018/4/26
 * @Modified By:
 */
public class CategoryTest {

  private CategoryDao categoryDao;

  private ApplicationContext context;

  @Before
  public void init(){
    context=new ClassPathXmlApplicationContext(new String[]{"classpath:vbiso-dao.xml"});
    categoryDao= (CategoryDao) context.getBean("categoryDao");
  }

  @Test
  public void testPage(){
    List<CategoryDo> categoryDos =
        categoryDao.selectCategoryList(1L);
    System.out.println(JsonUtil.toJson(categoryDos));
  }

  @Test
  public void insert(){
    CategoryDo categoryDo = new CategoryDo();
    categoryDo.setUserId(1L);
    categoryDo.setCategoryId(1L);
    categoryDo.setCategoryDesc("生活");
    int i = categoryDao.insertCategory(categoryDo);
    System.out.println(i);
  }

}
