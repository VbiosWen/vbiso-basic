package com.vbiso.domain;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午4:58 2018/5/1
 * @Modified By:
 */
public class UserInfo {

  private String userNick;

  private long userId;

  private double incomeData;

  private double expensesData;

  private int sex;

  private double sumData;

  public String getUserNick() {
    return userNick;
  }

  public void setUserNick(String userNick) {
    this.userNick = userNick;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public double getIncomeData() {
    return incomeData;
  }

  public void setIncomeData(double incomeData) {
    this.incomeData = incomeData;
  }

  public double getExpensesData() {
    return expensesData;
  }

  public void setExpensesData(double expensesData) {
    this.expensesData = expensesData;
  }

  public int getSex() {
    return sex;
  }

  public void setSex(int sex) {
    this.sex = sex;
  }

  public double getSumData() {
    return sumData;
  }

  public void setSumData(double sumData) {
    this.sumData = sumData;
  }
}
