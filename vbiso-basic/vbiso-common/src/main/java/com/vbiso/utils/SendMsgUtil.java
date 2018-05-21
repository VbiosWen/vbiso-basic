package com.vbiso.utils;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午7:17 2018/5/10
 * @Modified By:
 */
public class SendMsgUtil {

  private static HttpClient httpClient=HttpClients.createDefault();

  private static final String APIKEY="fe7ad2ef03a4a96791653b50b3589cd8";

  private static final String SINGLE_MSG_URL="https://sms.yunpian.com/v2/sms/single_send.json";

  private static final String STRING_INVITECODE_MSG="【个人收支管理系统】您的验证码是%s。如非本人操作，请忽略本短信";

  private static final String STRING_TIMEJOB_WARN_MSG="【个人收支管理系统】亲爱的用户，您的预期消费已超过预警%s,请注意哦！";

  public static String sendMsg(String mobile,String data,String signture)throws Exception{

    List<NameValuePair> params=new ArrayList<>();

    params.add(new BasicNameValuePair("apikey",APIKEY));
    params.add(new BasicNameValuePair("mobile",mobile));
    params.add(new BasicNameValuePair("text",StringUtil.format(signture,data)));
    String result=sendToYunPian(params);
    return result;
  }

  private static String sendToYunPian(List<NameValuePair> params) throws Exception{
    RequestConfig config=RequestConfig.custom().setSocketTimeout(1000).setConnectTimeout(2000).build();
    HttpPost httpPost=new HttpPost(SINGLE_MSG_URL);
    httpPost.setConfig(config);
    httpPost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));
    HttpResponse execute = httpClient.execute(httpPost);

    return EntityUtils.toString(execute.getEntity());
  }



  public static void main(String[] args) throws Exception {
    String s = sendMsg("15639114941", "400.0", STRING_TIMEJOB_WARN_MSG);
    System.out.println(s);
  }

}
