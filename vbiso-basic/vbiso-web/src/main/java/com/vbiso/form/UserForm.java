package com.vbiso.form;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午10:11 2018/5/2
 * @Modified By:
 */
public class UserForm {

  private String userNick;

  private String userPassword;

  private int sex;

  private String mobile;

  public String getUserNick() {
    return userNick;
  }

  public void setUserNick(String userNick) {
    this.userNick = userNick;
  }

  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }

  public int getSex() {
    return sex;
  }

  public void setSex(int sex) {
    this.sex = sex;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }
}
