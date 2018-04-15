package com.vbiso.dao.test;

import com.vbiso.redis.RedisDao;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午5:25 2018/4/15
 * @Modified By:
 */
public class RedisTest {

  private ApplicationContext context;

  private RedisDao redisDao;

  private JedisPool jedisPool;

  @Before
  public void init(){
    context=new ClassPathXmlApplicationContext(new String[]{"classpath:jedis-config.xml"});
    redisDao= (RedisDao) context.getBean("redisCacheClient");
    jedisPool= (JedisPool) context.getBean("jedisPool");
  }
  @Test
  public void set(){
    String test = redisDao.set("test", "1111");
    System.out.println(test);
  }

  @Test
  public void jedisPoolSet(){
    Jedis je = jedisPool.getResource();
    String set = je.set("test", "test");
    System.out.println(set);
    je.close();
  }
}
