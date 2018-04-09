package com.vbiso.mapping;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 上午10:01 2018/3/16
 * @Modified By:
 */
public class FieldDo {

  private String key;

  private Object value;

  public FieldDo() {
  }

  public FieldDo(String key, Object value) {
    this.key = key;
    this.value = value;
  }

  public static FieldDo create(String key,Object value){
    return new FieldDo(key,value);
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public Object getValue() {
    return value;
  }

  public void setValue(Object value) {
    this.value = value;
  }
}
