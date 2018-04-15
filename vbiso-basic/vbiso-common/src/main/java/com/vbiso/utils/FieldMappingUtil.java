package com.vbiso.utils;

import com.vbiso.mapping.FieldDo;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午1:09 2018/3/18
 * @Modified By:
 */
public class FieldMappingUtil<T>{


  public static<T> List<FieldDo> getFieldAsList(T t) throws IllegalAccessException {
      Class<?> aClass = t.getClass();
      Field[] fields = aClass.getDeclaredFields();
      List<FieldDo> list=new ArrayList<>();
      for(Field field:fields){
          field.setAccessible(true);
          String name = field.getName();
          String key=buildKey(name);
          Type type = field.getAnnotatedType().getType();
          String typeName = type.getTypeName();
          FieldDo fieldDo = new FieldDo();
          if("long".equals(typeName)||"int".equals(typeName)||"double".equals(typeName)){
              Object value = field.get(t);
              if(Long.valueOf(String.valueOf(value))<=0){
                 continue;
              }
              fieldDo.setKey(key);
              fieldDo.setValue(value);
          }else{
              Object value = field.get(t);
              if(value==null){
                  continue;
              }
              fieldDo.setKey(key);
              fieldDo.setValue(value);
          }
          list.add(fieldDo);
      }
      return list;
  }

    private  static String buildKey(String key) {
      String value=null;
      int temp=0;
      for(int i=0;i<key.length();i++){
         int index=key.charAt(i);
         if(index>=65&&index<=90){
             temp=i;
             break;
         }
      }
      String prefix=key.substring(0,temp);
      String suffix=key.substring(temp);
      value=prefix+"_"+suffix.toLowerCase();
      return value;
    }

    public static void main(String[] args){
      String key="userId";
      String s = buildKey(key);
      System.out.println(s);
    }

}
