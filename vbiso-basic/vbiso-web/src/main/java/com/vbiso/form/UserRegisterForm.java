package com.vbiso.form;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午8:55 2018/4/9
 * @Modified By:
 */
public class UserRegisterForm {

  private String userNick;

  private String mobile;

  private String password;

  private Integer sex;

  public String getUserNick() {
    return userNick;
  }

  public void setUserNick(String userNick) {
    this.userNick = userNick;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Integer getSex() {
    return sex;
  }

  public void setSex(Integer sex) {
    this.sex = sex;
  }
}
