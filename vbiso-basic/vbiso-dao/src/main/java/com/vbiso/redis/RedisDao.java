package com.vbiso.redis;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午4:56 2018/4/15
 * @Modified By:
 */
public interface RedisDao {

  String set(final String key ,String value);

  String get(final String key);

}
