package com.vbiso.info;

import java.util.Properties;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 上午11:24 2018/6/8
 * @Modified By:
 */
public class MailInfo {

  private String mailServerHost;

  private String mailServerPort="25";

  private String toAddress;

  private String fromAddress;

  private String userName;

  private String password;

  private boolean volidate=false;

  private String subject;

  private String content;

  private String iwallHost;

  public MailInfo() {
    this.mailServerHost = "smtp.exmail.qq.com";
    this.mailServerPort = "25";
    this.fromAddress = "1067477616@qq.com";
    this.userName = "V";
    this.password = "xiaopohaier601";
    this.iwallHost = "htttp://localhost:8080/xxx";
  }



  public Properties getProperties(){
    Properties p=new Properties();

    p.put("mail.smtp.host",this.mailServerHost);
    p.put("mail.smtp.port",this.mailServerPort);
    p.put("mail.smtp.auth",volidate?"true":"false");
    return p;
  }

  public String getMailServerHost() {
    return mailServerHost;
  }

  public void setMailServerHost(String mailServerHost) {
    this.mailServerHost = mailServerHost;
  }

  public String getMailServerPort() {
    return mailServerPort;
  }

  public void setMailServerPort(String mailServerPort) {
    this.mailServerPort = mailServerPort;
  }

  public String getFromAddress() {
    return fromAddress;
  }

  public void setFromAddress(String fromAddress) {
    this.fromAddress = fromAddress;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isVolidate() {
    return volidate;
  }

  public void setVolidate(boolean volidate) {
    this.volidate = volidate;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getIwallHost() {
    return iwallHost;
  }

  public void setIwallHost(String iwallHost) {
    this.iwallHost = iwallHost;
  }
}
