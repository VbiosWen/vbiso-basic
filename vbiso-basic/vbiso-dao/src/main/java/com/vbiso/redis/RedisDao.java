package com.vbiso.redis;

import java.util.Map;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午4:56 2018/4/15
 * @Modified By:
 */
public interface RedisDao {

  String set(final String key ,String value);

  String get(final String key);

  String setExpire(final String key,String value,int expire);

  String hget(final String key,String fieldKey);

  Long hset(final String key,String fieldKey,String value);

  Long hdel(final String key,String fieldKey);

  Map<String,String> hgetAll(final String key);

}
