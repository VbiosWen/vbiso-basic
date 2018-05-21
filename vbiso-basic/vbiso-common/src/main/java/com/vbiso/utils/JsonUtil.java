package com.vbiso.utils;

import java.io.IOException;
import java.io.StringWriter;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午11:28 2018/4/13
 * @Modified By:
 */
public class JsonUtil<T> {

  public static String toJson(Object object){
    ObjectMapper objectMapper=new ObjectMapper();
    StringWriter sw=new StringWriter();
    JsonGenerator jsonGenerator=null;
    try {
      jsonGenerator = new JsonFactory().createJsonGenerator(sw);
      objectMapper.writeValue(jsonGenerator,object);
    } catch (IOException e) {
      e.printStackTrace();
    }finally {
      try {
        jsonGenerator.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return sw.toString();
  }

  public static Object toJson(String str,Class clzz) throws IOException {
    ObjectMapper objectMapper=new ObjectMapper();
    Object o = objectMapper.readValue(str, clzz);
    return o;
  }

}
