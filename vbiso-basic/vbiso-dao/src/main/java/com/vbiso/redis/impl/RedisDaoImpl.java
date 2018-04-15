package com.vbiso.redis.impl;

import com.vbiso.redis.RedisDao;
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
    return null;
  }

  public Jedis getRedis() {
    return jedisPool.getResource();
  }
}
