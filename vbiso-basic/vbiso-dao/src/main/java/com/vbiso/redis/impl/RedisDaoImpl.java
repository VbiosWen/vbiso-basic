package com.vbiso.redis.impl;

import com.vbiso.redis.RedisDao;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午4:58 2018/4/15
 * @Modified By:
 */
@Component("redisCacheClient")
public class RedisDaoImpl implements RedisDao {

  private static Logger logger = Logger.getLogger(RedisDaoImpl.class);

  private JedisPool jedisPool;

  public void setJedisPool(JedisPool jedisPool) {
    this.jedisPool = jedisPool;
  }

  @Override
  public String set(String key, String value) {
    Jedis redis = null;
    try {
      redis = getRedis();
      String set = redis.set(key, value);
      return set;
    } catch (Exception e) {
      logger.error("db error", e);
      return "error";
    } finally {
      closeJedis(redis);
    }
  }

  private void closeJedis(Jedis redis) {
    try {
      redis.close();
      ;
    } catch (Exception ex) {
      logger.error("db close Error", ex);
    }
  }

  @Override
  public String get(String key) {
    Jedis redis=null;
    try {
      redis=getRedis();
      String s = redis.get(key);
      return s;
    }catch (Exception ex){
      logger.error("db is error",ex);
      return null;
    }finally {
      closeJedis(redis);
    }
  }

  @Override
  public String setExpire(String key, String value, int expire) {
    Jedis redis=null;
    try {
      redis=getRedis();
      String setex = redis.setex(key, expire, value);
      return setex;
    }catch (Exception e){
      logger.error("db is error",e);
      return null;
    }finally {
      closeJedis(redis);
    }

  }

  @Override
  public String hget(String key, String fieldKey) {
    Jedis redis=null;
    try {
      redis=getRedis();
      String value = redis.hget(key, fieldKey);
      return value;
    }catch (Exception e){
      logger.error("value is can`t find",e);
      return null;
    }finally {
      closeJedis(redis);
    }
  }

  @Override
  public Long hset(String key, String fieldKey, String value) {
    Jedis redis=null;
    try {
      redis=getRedis();
      Long hset = redis.hset(key, fieldKey, value);
      return hset;
    }catch (Exception ex){
      logger.error("insert error",ex);
      return null;
    }finally {
      closeJedis(redis);
    }
  }

  @Override
  public Long hdel(String key, String fieldKey) {
    Jedis redis=null;
    try {
      redis=getRedis();
      Long hdel = redis.hdel(key, fieldKey);
      return hdel;
    }catch (Exception ex){
      return null;
    }finally {
      closeJedis(redis);
    }
  }

  @Override
  public Map<String,String> hgetAll(String key) {
    Jedis redis=null;
    try {
      redis=getRedis();
      Map<String, String> map = redis.hgetAll(key);
      return map;
    }catch (Exception ex){
      return null;
    }finally {
      closeJedis(redis);
    }
  }

  public Jedis getRedis() {
    return jedisPool.getResource();
  }
}
