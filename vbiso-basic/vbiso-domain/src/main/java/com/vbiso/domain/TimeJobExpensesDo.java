package com.vbiso.domain;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午9:14 2018/5/16
 * @Modified By:
 */
public class TimeJobExpensesDo {


  private long userId;

  private long expensesId;

  private long expensesDate;

  private double expensesData;

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public long getExpensesId() {
    return expensesId;
  }

  public void setExpensesId(long expensesId) {
    this.expensesId = expensesId;
  }

  public long getExpensesDate() {
    return expensesDate;
  }

  public void setExpensesDate(long expensesDate) {
    this.expensesDate = expensesDate;
  }

  public double getExpensesData() {
    return expensesData;
  }

  public void setExpensesData(double expensesData) {
    this.expensesData = expensesData;
  }
}
