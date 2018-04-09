package com.vbiso.domain;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午4:17 2018/3/11
 * @Modified By:
 */
public class UserDo {

 private long userId;

 private String userNick;

 private String password;

 private String userMobile;

 private long createdTime;

 private long modifyTime;

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public String getUserNick() {
    return userNick;
  }

  public void setUserNick(String userNick) {
    this.userNick = userNick;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUserMobile() {
    return userMobile;
  }

  public void setUserMobile(String userMobile) {
    this.userMobile = userMobile;
  }

  public long getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(long createdTime) {
    this.createdTime = createdTime;
  }

  public long getModifyTime() {
    return modifyTime;
  }

  public void setModifyTime(long modifyTime) {
    this.modifyTime = modifyTime;
  }
}
